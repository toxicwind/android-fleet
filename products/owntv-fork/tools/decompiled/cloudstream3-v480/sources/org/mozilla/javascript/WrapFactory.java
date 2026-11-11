package org.mozilla.javascript;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class WrapFactory {
    private boolean javaPrimitiveWrap = true;

    public Object wrap(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        if (obj != null && obj != Undefined.instance && !(obj instanceof Scriptable)) {
            if (cls != null && cls.isPrimitive()) {
                if (cls == Void.TYPE) {
                    return Undefined.instance;
                }
                if (cls == Character.TYPE) {
                    return Integer.valueOf(((Character) obj).charValue());
                }
            } else {
                if (!isJavaPrimitiveWrap()) {
                    if (!(obj instanceof String) && !(obj instanceof Boolean) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof BigInteger)) {
                        if (obj instanceof Character) {
                            return String.valueOf(((Character) obj).charValue());
                        }
                    }
                }
                if (obj.getClass().isArray()) {
                    return NativeJavaArray.wrap(scriptable, obj);
                }
                return wrapAsJavaObject(context, scriptable, obj, cls);
            }
        }
        return obj;
    }

    public Scriptable wrapNewObject(Context context, Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj.getClass().isArray()) {
            return NativeJavaArray.wrap(scriptable, obj);
        }
        return wrapAsJavaObject(context, scriptable, obj, null);
    }

    public Scriptable wrapAsJavaObject(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        if (List.class.isAssignableFrom(obj.getClass())) {
            return new NativeJavaList(scriptable, obj);
        }
        if (Map.class.isAssignableFrom(obj.getClass())) {
            return new NativeJavaMap(scriptable, obj);
        }
        return new NativeJavaObject(scriptable, obj, cls);
    }

    public Scriptable wrapJavaClass(Context context, Scriptable scriptable, Class<?> cls) {
        return new NativeJavaClass(scriptable, cls);
    }

    public final boolean isJavaPrimitiveWrap() {
        return this.javaPrimitiveWrap;
    }

    public final void setJavaPrimitiveWrap(boolean z) {
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null && currentContext.isSealed()) {
            Context.onSealedMutation();
        }
        this.javaPrimitiveWrap = z;
    }
}
