package androidx.core.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface WindowInsetsAnimationControlListenerCompat {
    void onCancelled(@Nullable WindowInsetsAnimationControllerCompat controller);

    void onFinished(@NonNull WindowInsetsAnimationControllerCompat controller);

    void onReady(@NonNull WindowInsetsAnimationControllerCompat controller, int types);
}
