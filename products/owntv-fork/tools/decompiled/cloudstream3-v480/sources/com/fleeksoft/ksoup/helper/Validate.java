package com.fleeksoft.ksoup.helper;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.exception.ValidationException;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Validate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0015\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/helper/Validate;", "", "<init>", "()V", "expectNotNull", ExifInterface.GPS_DIRECTION_TRUE, "obj", NotificationCompat.CATEGORY_MESSAGE, "", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "isTrue", "", ES6Iterator.VALUE_PROPERTY, "", "isFalse", "notEmpty", TypedValues.Custom.S_STRING, "notEmptyParam", "param", "wtf", "fail", "assertFail", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Validate {
    public static final Validate INSTANCE = new Validate();

    private Validate() {
    }

    public static /* synthetic */ Object expectNotNull$default(Validate validate, Object obj, String str, int i, Object obj2) {
        if ((i & 2) != 0) {
            str = null;
        }
        return validate.expectNotNull(obj, str);
    }

    public final <T> T expectNotNull(T obj, String msg) {
        if (obj != null) {
            return obj;
        }
        if (msg == null) {
            msg = "Object must not be null";
        }
        throw new ValidationException(msg);
    }

    public final void isTrue(boolean value) {
        if (!value) {
            throw new ValidationException("Must be true");
        }
    }

    public final void isTrue(boolean value, String msg) {
        if (!value) {
            throw new ValidationException(msg);
        }
    }

    public final void isFalse(boolean value) {
        if (value) {
            throw new ValidationException("Must be false");
        }
    }

    public final void isFalse(boolean value, String msg) {
        if (value) {
            throw new ValidationException(msg);
        }
    }

    public final void notEmpty(String string) {
        String str = string;
        if (str == null || str.length() == 0) {
            throw new ValidationException("String must not be empty");
        }
    }

    public final void notEmptyParam(String string, String param) {
        String str = string;
        if (str == null || str.length() == 0) {
            throw new ValidationException("The '" + param + "' parameter must not be empty.");
        }
    }

    public final void notEmpty(String string, String msg) {
        String str = string;
        if (str == null || str.length() == 0) {
            throw new ValidationException(msg);
        }
    }

    public final void wtf(String msg) {
        throw new IllegalStateException(msg);
    }

    public final void fail(String msg) {
        throw new ValidationException(msg);
    }

    public final boolean assertFail(String msg) {
        fail(msg);
        return false;
    }
}
