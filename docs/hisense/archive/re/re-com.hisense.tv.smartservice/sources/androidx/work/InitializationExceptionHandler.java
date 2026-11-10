package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface InitializationExceptionHandler {
    void handleException(@NonNull Throwable throwable);
}
