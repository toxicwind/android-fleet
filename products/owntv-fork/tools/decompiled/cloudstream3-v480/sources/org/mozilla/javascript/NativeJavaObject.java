package org.mozilla.javascript;

import j$.util.Objects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class NativeJavaObject implements Scriptable, SymbolScriptable, Wrapper, Serializable {
    static final byte CONVERSION_NONE = 99;
    static final byte CONVERSION_NONTRIVIAL = 0;
    static final byte CONVERSION_TRIVIAL = 1;
    private static final int JSTYPE_BIGINT = 9;
    private static final int JSTYPE_BOOLEAN = 2;
    private static final int JSTYPE_JAVA_ARRAY = 7;
    private static final int JSTYPE_JAVA_CLASS = 5;
    private static final int JSTYPE_JAVA_OBJECT = 6;
    private static final int JSTYPE_NULL = 1;
    private static final int JSTYPE_NUMBER = 3;
    private static final int JSTYPE_OBJECT = 8;
    private static final int JSTYPE_STRING = 4;
    private static final int JSTYPE_UNDEFINED = 0;
    private static Method adapter_readAdapterObject = null;
    private static Method adapter_writeAdapterObject = null;
    private static final long serialVersionUID = -6948590651130498591L;
    private transient Map<String, FieldAndMethods> fieldAndMethods;
    protected transient boolean isAdapter;
    protected transient Object javaObject;
    protected transient JavaMembers members;
    protected Scriptable parent;
    protected Scriptable prototype;
    protected transient Class<?> staticType;
    private static Callable symbol_iterator = new Callable() { // from class: org.mozilla.javascript.NativeJavaObject$$ExternalSyntheticLambda0
        @Override // org.mozilla.javascript.Callable
        public final Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return NativeJavaObject.lambda$static$0(context, scriptable, scriptable2, objArr);
        }
    };
    private static final Object COERCED_INTERFACE_KEY = "Coerced Interface";

    @Override // org.mozilla.javascript.Scriptable
    public void delete(int i) {
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(String str) {
    }

    public void delete(Symbol symbol) {
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        return false;
    }

    static void init(ScriptableObject scriptableObject, boolean z) {
        JavaIterableIterator.init(scriptableObject, z);
    }

    public NativeJavaObject() {
    }

    public NativeJavaObject(Scriptable scriptable, Object obj, Class<?> cls) {
        this(scriptable, obj, cls, false);
    }

    public NativeJavaObject(Scriptable scriptable, Object obj, Class<?> cls, boolean z) {
        this.parent = scriptable;
        this.javaObject = obj;
        this.staticType = cls;
        this.isAdapter = z;
        initMembers();
    }

    protected void initMembers() {
        Class<?> cls;
        Object obj = this.javaObject;
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = this.staticType;
        }
        JavaMembers javaMembersLookupClass = JavaMembers.lookupClass(this.parent, cls, this.staticType, this.isAdapter);
        this.members = javaMembersLookupClass;
        this.fieldAndMethods = javaMembersLookupClass.getFieldAndMethodsObjects(this, this.javaObject, false);
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return this.members.has(str, false);
    }

    public boolean has(Symbol symbol, Scriptable scriptable) {
        return SymbolKey.ITERATOR.equals(symbol) && (this.javaObject instanceof Iterable);
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        FieldAndMethods fieldAndMethods;
        Map<String, FieldAndMethods> map = this.fieldAndMethods;
        return (map == null || (fieldAndMethods = map.get(str)) == null) ? this.members.get(this, str, this.javaObject, false) : fieldAndMethods;
    }

    public Object get(Symbol symbol, Scriptable scriptable) {
        if (SymbolKey.ITERATOR.equals(symbol) && (this.javaObject instanceof Iterable)) {
            return symbol_iterator;
        }
        return Scriptable.NOT_FOUND;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        throw this.members.reportMemberNotFound(Integer.toString(i));
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        if (this.prototype == null || this.members.has(str, false)) {
            this.members.put(this, str, this.javaObject, obj, false);
        } else {
            Scriptable scriptable2 = this.prototype;
            scriptable2.put(str, scriptable2, obj);
        }
    }

    @Override // org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        String string = symbol.toString();
        if (this.prototype == null || this.members.has(string, false)) {
            this.members.put(this, string, this.javaObject, obj, false);
            return;
        }
        Scriptable scriptable2 = this.prototype;
        if (scriptable2 instanceof SymbolScriptable) {
            ((SymbolScriptable) scriptable2).put(symbol, scriptable2, obj);
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        throw this.members.reportMemberNotFound(Integer.toString(i));
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getPrototype() {
        Scriptable scriptable = this.prototype;
        return (scriptable == null && (this.javaObject instanceof String)) ? TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(this.parent), TopLevel.Builtins.String) : scriptable;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setPrototype(Scriptable scriptable) {
        this.prototype = scriptable;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getParentScope() {
        return this.parent;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setParentScope(Scriptable scriptable) {
        this.parent = scriptable;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        return this.members.getIds(false);
    }

    @Deprecated
    public static Object wrap(Scriptable scriptable, Object obj, Class<?> cls) {
        Context context = Context.getContext();
        return context.getWrapFactory().wrap(context, scriptable, obj, cls);
    }

    public Object unwrap() {
        return this.javaObject;
    }

    @Override // org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "JavaObject";
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        String str;
        if (cls == null) {
            if (this.javaObject instanceof Boolean) {
                cls = ScriptRuntime.BooleanClass;
            }
            if (this.javaObject instanceof Number) {
                cls = ScriptRuntime.NumberClass;
            }
        }
        if (cls == null || cls == ScriptRuntime.StringClass) {
            return this.javaObject.toString();
        }
        if (cls == ScriptRuntime.BooleanClass) {
            str = "booleanValue";
        } else if (cls == ScriptRuntime.NumberClass) {
            str = "doubleValue";
        } else {
            throw Context.reportRuntimeErrorById("msg.default.value", new Object[0]);
        }
        Object obj = get(str, this);
        if (obj instanceof Function) {
            Function function = (Function) obj;
            return function.call(Context.getContext(), function.getParentScope(), this, ScriptRuntime.emptyArgs);
        }
        if (cls == ScriptRuntime.NumberClass) {
            Object obj2 = this.javaObject;
            if (obj2 instanceof Boolean) {
                return ((Boolean) obj2).booleanValue() ? ScriptRuntime.wrapNumber(1.0d) : ScriptRuntime.zeroObj;
            }
        }
        return this.javaObject.toString();
    }

    public static boolean canConvert(Object obj, Class<?> cls) {
        return getConversionWeight(obj, cls) < 99;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:128:0x0108 A[RETURN] */
    static int getConversionWeight(Object obj, Class<?> cls) {
        int jSTypeCode = getJSTypeCode(obj);
        switch (jSTypeCode) {
            case 0:
                if (cls == ScriptRuntime.StringClass || cls == ScriptRuntime.ObjectClass) {
                    return 1;
                }
                return 99;
            case 1:
                if (cls.isPrimitive()) {
                    return 99;
                }
                return 1;
            case 2:
                if (cls == Boolean.TYPE) {
                    return 1;
                }
                if (cls == ScriptRuntime.BooleanClass) {
                    return 2;
                }
                if (cls == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 4;
                }
                return 99;
            case 3:
            case 9:
                if (cls.isPrimitive()) {
                    if (cls == Double.TYPE) {
                        return 1;
                    }
                    if (cls != Boolean.TYPE) {
                        return getSizeRank(cls) + 1;
                    }
                } else {
                    if (cls == ScriptRuntime.StringClass) {
                        return 9;
                    }
                    if (cls == ScriptRuntime.BigIntegerClass) {
                        return 10;
                    }
                    if (cls == ScriptRuntime.ObjectClass) {
                        return 11;
                    }
                    if (ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                        return 2;
                    }
                }
                return 99;
            case 4:
                if (cls == ScriptRuntime.StringClass) {
                    return 1;
                }
                if (cls.isInstance(obj)) {
                    return 2;
                }
                if (cls.isPrimitive()) {
                    if (cls == Character.TYPE) {
                        return 3;
                    }
                    if (cls != Boolean.TYPE) {
                        return 4;
                    }
                }
                return 99;
            case 5:
                if (cls == ScriptRuntime.ClassClass) {
                    return 1;
                }
                if (cls == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 4;
                }
                return 99;
            case 6:
            case 7:
                if (obj instanceof Wrapper) {
                    obj = ((Wrapper) obj).unwrap();
                }
                if (cls.isInstance(obj)) {
                    return 0;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 2;
                }
                if (!cls.isPrimitive() || cls == Boolean.TYPE || jSTypeCode == 7) {
                    return 99;
                }
                return getSizeRank(cls) + 2;
            case 8:
                if (cls != ScriptRuntime.ObjectClass && cls.isInstance(obj)) {
                    return 1;
                }
                if (cls.isArray()) {
                    if (obj instanceof NativeArray) {
                        return 2;
                    }
                } else {
                    if (cls == ScriptRuntime.ObjectClass) {
                        return 3;
                    }
                    if (cls == ScriptRuntime.StringClass) {
                        return 4;
                    }
                    if (cls == ScriptRuntime.DateClass) {
                        if (obj instanceof NativeDate) {
                            return 1;
                        }
                    } else {
                        if (cls.isInterface()) {
                            if (obj instanceof NativeFunction) {
                                return 1;
                            }
                            return obj instanceof NativeObject ? 2 : 12;
                        }
                        if (cls.isPrimitive() && cls != Boolean.TYPE) {
                            return getSizeRank(cls) + 4;
                        }
                    }
                }
                return 99;
            default:
                return 99;
        }
    }

    static int getSizeRank(Class<?> cls) {
        if (cls == Double.TYPE) {
            return 1;
        }
        if (cls == Float.TYPE) {
            return 2;
        }
        if (cls == Long.TYPE) {
            return 3;
        }
        if (cls == Integer.TYPE) {
            return 4;
        }
        if (cls == Short.TYPE) {
            return 5;
        }
        if (cls == Character.TYPE) {
            return 6;
        }
        if (cls == Byte.TYPE) {
            return 7;
        }
        return cls == Boolean.TYPE ? 99 : 8;
    }

    private static int getJSTypeCode(Object obj) {
        if (obj == null) {
            return 1;
        }
        if (obj == Undefined.instance) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return 4;
        }
        if (obj instanceof BigInteger) {
            return 9;
        }
        if (obj instanceof Number) {
            return 3;
        }
        if (obj instanceof Boolean) {
            return 2;
        }
        if (obj instanceof Scriptable) {
            if (obj instanceof NativeJavaClass) {
                return 5;
            }
            if (obj instanceof NativeJavaArray) {
                return 7;
            }
            return obj instanceof Wrapper ? 6 : 8;
        }
        if (obj instanceof Class) {
            return 5;
        }
        return obj.getClass().isArray() ? 7 : 6;
    }

    @Deprecated
    public static Object coerceType(Class<?> cls, Object obj) {
        return coerceTypeImpl(cls, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static Object coerceTypeImpl(Class<?> cls, Object obj) {
        if (obj == null || obj.getClass() != cls) {
            int jSTypeCode = getJSTypeCode(obj);
            switch (jSTypeCode) {
                case 0:
                    if (cls == ScriptRuntime.StringClass || cls == ScriptRuntime.ObjectClass) {
                        return "undefined";
                    }
                    reportConversionError("undefined", cls);
                    return obj;
                case 1:
                    if (!cls.isPrimitive()) {
                        return null;
                    }
                    reportConversionError(obj, cls);
                    return null;
                case 2:
                    if (cls != Boolean.TYPE && cls != ScriptRuntime.BooleanClass && cls != ScriptRuntime.ObjectClass) {
                        if (cls == ScriptRuntime.StringClass) {
                            return obj.toString();
                        }
                        reportConversionError(obj, cls);
                        return obj;
                    }
                    break;
                case 3:
                case 9:
                    if (cls == ScriptRuntime.StringClass) {
                        return ScriptRuntime.toString(obj);
                    }
                    if (cls == ScriptRuntime.ObjectClass) {
                        Context currentContext = Context.getCurrentContext();
                        if (currentContext != null && currentContext.hasFeature(18) && Math.round(toDouble(obj)) == toDouble(obj)) {
                            return coerceToNumber(Long.TYPE, obj);
                        }
                        return coerceToNumber(jSTypeCode == 9 ? BigInteger.class : Double.TYPE, obj);
                    }
                    if ((cls.isPrimitive() && cls != Boolean.TYPE) || ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                        return coerceToNumber(cls, obj);
                    }
                    reportConversionError(obj, cls);
                    return obj;
                case 4:
                    if (cls == ScriptRuntime.StringClass || cls.isInstance(obj)) {
                        return obj.toString();
                    }
                    if (cls == Character.TYPE || cls == ScriptRuntime.CharacterClass) {
                        CharSequence charSequence = (CharSequence) obj;
                        if (charSequence.length() == 1) {
                            return Character.valueOf(charSequence.charAt(0));
                        }
                        return coerceToNumber(cls, obj);
                    }
                    if ((cls.isPrimitive() && cls != Boolean.TYPE) || ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                        return coerceToNumber(cls, obj);
                    }
                    reportConversionError(obj, cls);
                    return obj;
                case 5:
                    if (obj instanceof Wrapper) {
                        obj = ((Wrapper) obj).unwrap();
                    }
                    if (cls != ScriptRuntime.ClassClass && cls != ScriptRuntime.ObjectClass) {
                        if (cls == ScriptRuntime.StringClass) {
                            return obj.toString();
                        }
                        reportConversionError(obj, cls);
                    }
                    return obj;
                case 6:
                case 7:
                    if (obj instanceof Wrapper) {
                        obj = ((Wrapper) obj).unwrap();
                    }
                    if (cls.isPrimitive()) {
                        if (cls == Boolean.TYPE) {
                            reportConversionError(obj, cls);
                        }
                        return coerceToNumber(cls, obj);
                    }
                    if (cls == ScriptRuntime.StringClass) {
                        return obj.toString();
                    }
                    if (cls.isInstance(obj)) {
                        return obj;
                    }
                    reportConversionError(obj, cls);
                    return obj;
                case 8:
                    if (cls == ScriptRuntime.StringClass) {
                        return ScriptRuntime.toString(obj);
                    }
                    if (cls.isPrimitive()) {
                        if (cls == Boolean.TYPE) {
                            reportConversionError(obj, cls);
                        }
                        return coerceToNumber(cls, obj);
                    }
                    if (!cls.isInstance(obj)) {
                        if (cls == ScriptRuntime.DateClass && (obj instanceof NativeDate)) {
                            return new Date((long) ((NativeDate) obj).getJSTimeValue());
                        }
                        if (cls.isArray() && (obj instanceof NativeArray)) {
                            NativeArray nativeArray = (NativeArray) obj;
                            long length = nativeArray.getLength();
                            Class<?> componentType = cls.getComponentType();
                            Object objNewInstance = Array.newInstance(componentType, (int) length);
                            for (int i = 0; i < length; i++) {
                                try {
                                    Array.set(objNewInstance, i, coerceTypeImpl(componentType, nativeArray.get(i, nativeArray)));
                                } catch (EvaluatorException unused) {
                                    reportConversionError(obj, cls);
                                }
                            }
                            return objNewInstance;
                        }
                        if (obj instanceof Wrapper) {
                            Object objUnwrap = ((Wrapper) obj).unwrap();
                            if (cls.isInstance(objUnwrap)) {
                                return objUnwrap;
                            }
                            reportConversionError(objUnwrap, cls);
                            return objUnwrap;
                        }
                        if (cls.isInterface() && ((obj instanceof NativeObject) || ((obj instanceof Callable) && (obj instanceof ScriptableObject)))) {
                            return createInterfaceAdapter(cls, (ScriptableObject) obj);
                        }
                        reportConversionError(obj, cls);
                        return obj;
                    }
                    break;
            }
        }
        return obj;
    }

    protected static Object createInterfaceAdapter(Class<?> cls, ScriptableObject scriptableObject) {
        Object objMakeHashKeyFromPair = Kit.makeHashKeyFromPair(COERCED_INTERFACE_KEY, cls);
        Object associatedValue = scriptableObject.getAssociatedValue(objMakeHashKeyFromPair);
        return associatedValue != null ? associatedValue : scriptableObject.associateValue(objMakeHashKeyFromPair, InterfaceAdapter.create(Context.getContext(), cls, scriptableObject));
    }

    private static Object coerceToNumber(Class<?> cls, Object obj) {
        Class<?> cls2 = obj.getClass();
        if (cls == Character.TYPE || cls == ScriptRuntime.CharacterClass) {
            return cls2 == ScriptRuntime.CharacterClass ? obj : Character.valueOf((char) toInteger(obj, ScriptRuntime.CharacterClass, 0.0d, 65535.0d));
        }
        if (cls == ScriptRuntime.ObjectClass || cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return cls2 == ScriptRuntime.DoubleClass ? obj : Double.valueOf(toDouble(obj));
        }
        if (cls == ScriptRuntime.BigIntegerClass) {
            if (cls2 != ScriptRuntime.BigIntegerClass) {
                return ScriptRuntime.toBigInt(obj);
            }
        } else {
            if (cls == ScriptRuntime.FloatClass || cls == Float.TYPE) {
                if (cls2 == ScriptRuntime.FloatClass) {
                    return obj;
                }
                double d = toDouble(obj);
                if (Double.isInfinite(d) || Double.isNaN(d) || d == 0.0d) {
                    return Float.valueOf((float) d);
                }
                double dAbs = Math.abs(d);
                if (dAbs < 1.401298464324817E-45d) {
                    return Float.valueOf(d > 0.0d ? 0.0f : -0.0f);
                }
                if (dAbs > 3.4028234663852886E38d) {
                    return Float.valueOf(d > 0.0d ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);
                }
                return Float.valueOf((float) d);
            }
            if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
                return cls2 == ScriptRuntime.IntegerClass ? obj : Integer.valueOf((int) toInteger(obj, ScriptRuntime.IntegerClass, -2.147483648E9d, 2.147483647E9d));
            }
            if (cls == ScriptRuntime.LongClass || cls == Long.TYPE) {
                if (cls2 == ScriptRuntime.LongClass) {
                    return obj;
                }
                return Long.valueOf(toInteger(obj, ScriptRuntime.LongClass, Double.longBitsToDouble(-4332462841530417152L), Double.longBitsToDouble(4890909195324358655L)));
            }
            if (cls == ScriptRuntime.ShortClass || cls == Short.TYPE) {
                return cls2 == ScriptRuntime.ShortClass ? obj : Short.valueOf((short) toInteger(obj, ScriptRuntime.ShortClass, -32768.0d, 32767.0d));
            }
            if (cls == ScriptRuntime.ByteClass || cls == Byte.TYPE) {
                if (cls2 != ScriptRuntime.ByteClass) {
                    return Byte.valueOf((byte) toInteger(obj, ScriptRuntime.ByteClass, -128.0d, 127.0d));
                }
            } else {
                return Double.valueOf(toDouble(obj));
            }
        }
        return obj;
    }

    private static double toDouble(Object obj) {
        Method method;
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof String) {
            return ScriptRuntime.toNumber((String) obj);
        }
        if (obj instanceof Scriptable) {
            if (obj instanceof Wrapper) {
                return toDouble(((Wrapper) obj).unwrap());
            }
            return ScriptRuntime.toNumber(obj);
        }
        try {
            method = obj.getClass().getMethod("doubleValue", null);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null) {
            try {
                return ((Number) method.invoke(obj, null)).doubleValue();
            } catch (IllegalAccessException unused2) {
                reportConversionError(obj, Double.TYPE);
            } catch (InvocationTargetException unused3) {
                reportConversionError(obj, Double.TYPE);
            }
        }
        return ScriptRuntime.toNumber(obj.toString());
    }

    private static long toInteger(Object obj, Class<?> cls, double d, double d2) {
        double dCeil;
        double d3 = toDouble(obj);
        if (Double.isInfinite(d3) || Double.isNaN(d3)) {
            reportConversionError(ScriptRuntime.toString(obj), cls);
        }
        if (d3 > 0.0d) {
            dCeil = Math.floor(d3);
        } else {
            dCeil = Math.ceil(d3);
        }
        if (dCeil < d || dCeil > d2) {
            reportConversionError(ScriptRuntime.toString(obj), cls);
        }
        return (long) dCeil;
    }

    static void reportConversionError(Object obj, Class<?> cls) {
        throw Context.reportRuntimeErrorById("msg.conversion.not.allowed", String.valueOf(obj), JavaMembers.javaSignature(cls));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeBoolean(this.isAdapter);
        if (this.isAdapter) {
            Method method = adapter_writeAdapterObject;
            if (method == null) {
                throw new IOException();
            }
            try {
                method.invoke(null, this.javaObject, objectOutputStream);
            } catch (Exception unused) {
                throw new IOException();
            }
        } else {
            objectOutputStream.writeObject(this.javaObject);
        }
        Class<?> cls = this.staticType;
        if (cls != null) {
            objectOutputStream.writeObject(cls.getName());
        } else {
            objectOutputStream.writeObject(null);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        boolean z = objectInputStream.readBoolean();
        this.isAdapter = z;
        if (z) {
            Method method = adapter_readAdapterObject;
            if (method == null) {
                throw new ClassNotFoundException();
            }
            try {
                this.javaObject = method.invoke(null, this, objectInputStream);
            } catch (Exception unused) {
                throw new IOException();
            }
        } else {
            this.javaObject = objectInputStream.readObject();
        }
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.staticType = Class.forName(str);
        } else {
            this.staticType = null;
        }
        initMembers();
    }

    static {
        Class<?>[] clsArr = new Class[2];
        Class<?> clsClassOrNull = Kit.classOrNull("org.mozilla.javascript.JavaAdapter");
        if (clsClassOrNull != null) {
            try {
                clsArr[0] = ScriptRuntime.ObjectClass;
                clsArr[1] = Kit.classOrNull("java.io.ObjectOutputStream");
                adapter_writeAdapterObject = clsClassOrNull.getMethod("writeAdapterObject", clsArr);
                clsArr[0] = ScriptRuntime.ScriptableClass;
                clsArr[1] = Kit.classOrNull("java.io.ObjectInputStream");
                adapter_readAdapterObject = clsClassOrNull.getMethod("readAdapterObject", clsArr);
            } catch (NoSuchMethodException unused) {
                adapter_writeAdapterObject = null;
                adapter_readAdapterObject = null;
            }
        }
    }

    static /* synthetic */ Object lambda$static$0(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!(scriptable2 instanceof NativeJavaObject)) {
            throw ScriptRuntime.typeErrorById("msg.incompat.call", SymbolKey.ITERATOR);
        }
        Object obj = ((NativeJavaObject) scriptable2).javaObject;
        if (obj instanceof Iterable) {
            return new JavaIterableIterator(scriptable, (Iterable) obj);
        }
        throw ScriptRuntime.typeErrorById("msg.incompat.call", SymbolKey.ITERATOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class JavaIterableIterator extends ES6Iterator {
        private static final String ITERATOR_TAG = "JavaIterableIterator";
        private static final long serialVersionUID = 1;
        private Iterator iterator;

        static void init(ScriptableObject scriptableObject, boolean z) {
            ES6Iterator.init(scriptableObject, z, new JavaIterableIterator(), ITERATOR_TAG);
        }

        private JavaIterableIterator() {
        }

        JavaIterableIterator(Scriptable scriptable, Iterable iterable) {
            super(scriptable, ITERATOR_TAG);
            this.iterator = iterable.iterator();
        }

        @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
        public String getClassName() {
            return "Java Iterable Iterator";
        }

        @Override // org.mozilla.javascript.ES6Iterator
        protected boolean isDone(Context context, Scriptable scriptable) {
            return !this.iterator.hasNext();
        }

        @Override // org.mozilla.javascript.ES6Iterator
        protected Object nextValue(Context context, Scriptable scriptable) {
            if (!this.iterator.hasNext()) {
                return Undefined.instance;
            }
            Object next = this.iterator.next();
            return context.getWrapFactory().wrap(context, this, next, next == null ? null : next.getClass());
        }

        @Override // org.mozilla.javascript.ES6Iterator
        protected String getTag() {
            return ITERATOR_TAG;
        }
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(getClass()) && Objects.equals(((NativeJavaObject) obj).javaObject, this.javaObject);
    }

    public int hashCode() {
        Object obj = this.javaObject;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
