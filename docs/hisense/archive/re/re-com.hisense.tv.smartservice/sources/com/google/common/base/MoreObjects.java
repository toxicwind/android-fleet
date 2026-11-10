package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class MoreObjects {

    public static final class ToStringHelper {

        @GuardedBy("ToStringHelper.class")
        public static boolean performedJava8CompatibilityCheck;
        public final String className;
        public final ValueHolder holderHead;
        public ValueHolder holderTail;
        public boolean omitNullValues;

        private static final class ValueHolder {
            public String name;
            public ValueHolder next;
            public Object value;

            public ValueHolder() {
            }

            public /* synthetic */ ValueHolder(AnonymousClass1 anonymousClass1) {
            }
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder(null);
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        public static void java8CompatibilityCheck() {
            if (performedJava8CompatibilityCheck) {
                return;
            }
            synchronized (ToStringHelper.class) {
                if (performedJava8CompatibilityCheck) {
                    return;
                }
                performedJava8CompatibilityCheck = true;
                try {
                    Java8Usage.performCheck();
                } catch (Throwable th) {
                    Logger.getLogger(ToStringHelper.class.getName()).log(Level.WARNING, "Java 7 compatibility warning: See https://github.com/google/guava/issues/5269", (Throwable) new Exception("Guava will drop support for Java 7 in 2021. Please let us know if this will cause you problems: https://github.com/google/guava/issues/5269", th));
                }
            }
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, Object obj) {
            addHolder(str, obj);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean z) {
            addHolder(String.valueOf(z));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public String toString() {
            boolean z = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                if (!z || obj != null) {
                    sb.append(str);
                    String str2 = valueHolder.name;
                    if (str2 != null) {
                        sb.append(str2);
                        sb.append('=');
                    }
                    if (obj == null || !obj.getClass().isArray()) {
                        sb.append(obj);
                    } else {
                        String strDeepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append((CharSequence) strDeepToString, 1, strDeepToString.length() - 1);
                    }
                    str = ", ";
                }
            }
            sb.append('}');
            return sb.toString();
        }

        public ToStringHelper(String str) {
            this.holderHead = new ValueHolder(null);
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            java8CompatibilityCheck();
            if (str == null) {
                throw new NullPointerException();
            }
            this.className = str;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, boolean z) {
            addHolder(str, String.valueOf(z));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char c2) {
            addHolder(String.valueOf(c2));
            return this;
        }

        private ToStringHelper addHolder(Object obj) {
            addHolder().value = obj;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, char c2) {
            addHolder(str, String.valueOf(c2));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double d2) {
            addHolder(String.valueOf(d2));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, double d2) {
            addHolder(str, String.valueOf(d2));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float f2) {
            addHolder(String.valueOf(f2));
            return this;
        }

        private ToStringHelper addHolder(String str, Object obj) {
            ValueHolder valueHolderAddHolder = addHolder();
            valueHolderAddHolder.value = obj;
            if (str != null) {
                valueHolderAddHolder.name = str;
                return this;
            }
            throw new NullPointerException();
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, float f2) {
            addHolder(str, String.valueOf(f2));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int i) {
            addHolder(String.valueOf(i));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, int i) {
            addHolder(str, String.valueOf(i));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long j) {
            addHolder(String.valueOf(j));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, long j) {
            addHolder(str, String.valueOf(j));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(Object obj) {
            addHolder().value = obj;
            return this;
        }
    }

    public static <T> T firstNonNull(T t, T t2) {
        if (t != null) {
            return t;
        }
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }
}
