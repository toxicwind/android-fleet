package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JacksonInject {
    OptBoolean useInput() default OptBoolean.DEFAULT;

    String value() default "";

    /* JADX WARN: Unexpected interfaces in signature: [java.io.Serializable] */
    public static class Value implements Object<JacksonInject> {
        protected static final Value EMPTY = new Value(null, null);
        private static final long serialVersionUID = 1;
        protected final Object _id;
        protected final Boolean _useInput;

        private static boolean _empty(Object obj, Boolean bool) {
            return obj == null && bool == null;
        }

        protected Value(Object obj, Boolean bool) {
            this._id = obj;
            this._useInput = bool;
        }

        public static Value construct(Object obj, Boolean bool) {
            if ("".equals(obj)) {
                obj = null;
            }
            if (_empty(obj, bool)) {
                return EMPTY;
            }
            return new Value(obj, bool);
        }

        public static Value from(JacksonInject jacksonInject) {
            if (jacksonInject == null) {
                return EMPTY;
            }
            return construct(jacksonInject.value(), jacksonInject.useInput().asBoolean());
        }

        public static Value forId(Object obj) {
            return construct(obj, null);
        }

        public Value withId(Object obj) {
            if (obj == null) {
                if (this._id == null) {
                    return this;
                }
            } else if (obj.equals(this._id)) {
                return this;
            }
            return new Value(obj, this._useInput);
        }

        public Object getId() {
            return this._id;
        }

        public boolean hasId() {
            return this._id != null;
        }

        public boolean willUseInput(boolean z) {
            Boolean bool = this._useInput;
            return bool == null ? z : bool.booleanValue();
        }

        @Override // java.lang.Object
        public String toString() {
            return String.format("JacksonInject.Value(id=%s,useInput=%s)", this._id, this._useInput);
        }

        @Override // java.lang.Object
        public int hashCode() {
            Object obj = this._id;
            int iHashCode = obj != null ? 1 + obj.hashCode() : 1;
            Boolean bool = this._useInput;
            return bool != null ? iHashCode + bool.hashCode() : iHashCode;
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == Value.class) {
                Value value = (Value) obj;
                if (OptBoolean.equals(this._useInput, value._useInput)) {
                    Object obj2 = this._id;
                    if (obj2 == null) {
                        return value._id == null;
                    }
                    return obj2.equals(value._id);
                }
            }
            return false;
        }
    }
}
