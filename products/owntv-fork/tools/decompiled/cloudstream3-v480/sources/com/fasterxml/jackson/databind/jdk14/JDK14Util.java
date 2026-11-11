package com.fasterxml.jackson.databind.jdk14;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class JDK14Util {
    public static String[] getRecordFieldNames(Class<?> cls) {
        return RecordAccessor.instance().getRecordFieldNames(cls);
    }

    public static AnnotatedConstructor findRecordConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, List<String> list) {
        return new CreatorLocator(deserializationContext, beanDescription).locate(list);
    }

    static class RecordAccessor {
        private static final RecordAccessor INSTANCE;
        private static final RuntimeException PROBLEM;
        private final Method RECORD_COMPONENT_GET_NAME;
        private final Method RECORD_COMPONENT_GET_TYPE;
        private final Method RECORD_GET_RECORD_COMPONENTS;

        static {
            RecordAccessor recordAccessor = null;
            try {
                e = null;
                recordAccessor = new RecordAccessor();
            } catch (RuntimeException e) {
                e = e;
            }
            INSTANCE = recordAccessor;
            PROBLEM = e;
        }

        private RecordAccessor() throws RuntimeException {
            try {
                this.RECORD_GET_RECORD_COMPONENTS = Class.class.getMethod("getRecordComponents", null);
                Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
                this.RECORD_COMPONENT_GET_NAME = cls.getMethod("getName", null);
                this.RECORD_COMPONENT_GET_TYPE = cls.getMethod("getType", null);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to access Methods needed to support `java.lang.Record`: (%s) %s", e.getClass().getName(), e.getMessage()), e);
            }
        }

        public static RecordAccessor instance() {
            RuntimeException runtimeException = PROBLEM;
            if (runtimeException != null) {
                throw runtimeException;
            }
            return INSTANCE;
        }

        public String[] getRecordFieldNames(Class<?> cls) throws IllegalArgumentException {
            Object[] objArrRecordComponents = recordComponents(cls);
            String[] strArr = new String[objArrRecordComponents.length];
            for (int i = 0; i < objArrRecordComponents.length; i++) {
                try {
                    strArr[i] = (String) this.RECORD_COMPONENT_GET_NAME.invoke(objArrRecordComponents[i], null);
                } catch (Exception e) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(objArrRecordComponents.length), ClassUtil.nameOf(cls)), e);
                }
            }
            return strArr;
        }

        public RawTypeName[] getRecordFields(Class<?> cls) throws IllegalArgumentException {
            Object[] objArrRecordComponents = recordComponents(cls);
            RawTypeName[] rawTypeNameArr = new RawTypeName[objArrRecordComponents.length];
            for (int i = 0; i < objArrRecordComponents.length; i++) {
                try {
                    try {
                        rawTypeNameArr[i] = new RawTypeName((Class) this.RECORD_COMPONENT_GET_TYPE.invoke(objArrRecordComponents[i], null), (String) this.RECORD_COMPONENT_GET_NAME.invoke(objArrRecordComponents[i], null));
                    } catch (Exception e) {
                        throw new IllegalArgumentException(String.format("Failed to access type of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(objArrRecordComponents.length), ClassUtil.nameOf(cls)), e);
                    }
                } catch (Exception e2) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(objArrRecordComponents.length), ClassUtil.nameOf(cls)), e2);
                }
            }
            return rawTypeNameArr;
        }

        protected Object[] recordComponents(Class<?> cls) throws IllegalArgumentException {
            try {
                return (Object[]) this.RECORD_GET_RECORD_COMPONENTS.invoke(cls, null);
            } catch (Exception unused) {
                throw new IllegalArgumentException("Failed to access RecordComponents of type " + ClassUtil.nameOf(cls));
            }
        }
    }

    static class RawTypeName {
        public final String name;
        public final Class<?> rawType;

        public RawTypeName(Class<?> cls, String str) {
            this.rawType = cls;
            this.name = str;
        }
    }

    static class CreatorLocator {
        protected final BeanDescription _beanDesc;
        protected final DeserializationConfig _config;
        protected final List<AnnotatedConstructor> _constructors;
        protected final AnnotationIntrospector _intr;
        protected final AnnotatedConstructor _primaryConstructor;
        protected final RawTypeName[] _recordFields;

        CreatorLocator(DeserializationContext deserializationContext, BeanDescription beanDescription) {
            AnnotatedConstructor annotatedConstructorFindDefaultConstructor;
            this._beanDesc = beanDescription;
            this._intr = deserializationContext.getAnnotationIntrospector();
            this._config = deserializationContext.getConfig();
            RawTypeName[] recordFields = RecordAccessor.instance().getRecordFields(beanDescription.getBeanClass());
            this._recordFields = recordFields;
            int length = recordFields.length;
            if (length == 0) {
                annotatedConstructorFindDefaultConstructor = beanDescription.findDefaultConstructor();
                this._constructors = Collections.singletonList(annotatedConstructorFindDefaultConstructor);
            } else {
                List<AnnotatedConstructor> constructors = beanDescription.getConstructors();
                this._constructors = constructors;
                Iterator<AnnotatedConstructor> it = constructors.iterator();
                loop0: while (true) {
                    if (!it.hasNext()) {
                        annotatedConstructorFindDefaultConstructor = null;
                        break;
                    }
                    AnnotatedConstructor next = it.next();
                    if (next.getParameterCount() == length) {
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                annotatedConstructorFindDefaultConstructor = next;
                                break loop0;
                            } else if (!next.getRawParameterType(i).equals(this._recordFields[i].rawType)) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
            if (annotatedConstructorFindDefaultConstructor == null) {
                throw new IllegalArgumentException("Failed to find the canonical Record constructor of type " + ClassUtil.getTypeDescription(this._beanDesc.getType()));
            }
            this._primaryConstructor = annotatedConstructorFindDefaultConstructor;
        }

        public AnnotatedConstructor locate(List<String> list) {
            for (AnnotatedConstructor annotatedConstructor : this._constructors) {
                JsonCreator.Mode modeFindCreatorAnnotation = this._intr.findCreatorAnnotation(this._config, annotatedConstructor);
                if (modeFindCreatorAnnotation != null && JsonCreator.Mode.DISABLED != modeFindCreatorAnnotation && (JsonCreator.Mode.DELEGATING == modeFindCreatorAnnotation || annotatedConstructor != this._primaryConstructor)) {
                    return null;
                }
            }
            for (RawTypeName rawTypeName : this._recordFields) {
                list.add(rawTypeName.name);
            }
            return this._primaryConstructor;
        }
    }
}
