package com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
public final class Java8Usage {

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface SomeTypeAnnotation {
    }

    @CanIgnoreReturnValue
    public static String performCheck() {
        new Runnable() { // from class: b.e.a.a.a
            @Override // java.lang.Runnable
            public final void run() {
            }
        }.run();
        return "";
    }
}
