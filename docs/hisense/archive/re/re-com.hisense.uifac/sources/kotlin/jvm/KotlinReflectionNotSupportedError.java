package kotlin.jvm;

import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KotlinReflectionNotSupportedError.kt */
/* JADX INFO: loaded from: classes.dex */
public class KotlinReflectionNotSupportedError extends Error {
    public KotlinReflectionNotSupportedError() {
        super("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
    }

    public KotlinReflectionNotSupportedError(@Nullable String str) {
        super(str);
    }
}
