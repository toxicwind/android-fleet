package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class CallbackWithHandler {

    @NonNull
    public final FontsContractCompat.FontRequestCallback mCallback;

    @NonNull
    public final Handler mCallbackHandler;

    public CallbackWithHandler(@NonNull FontsContractCompat.FontRequestCallback callback, @NonNull Handler callbackHandler) {
        this.mCallback = callback;
        this.mCallbackHandler = callbackHandler;
    }

    private void onTypefaceRequestFailed(final int reason) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mCallbackHandler.post(new Runnable() { // from class: androidx.core.provider.CallbackWithHandler.2
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(reason);
            }
        });
    }

    private void onTypefaceRetrieved(@NonNull final Typeface typeface) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.mCallback;
        this.mCallbackHandler.post(new Runnable() { // from class: androidx.core.provider.CallbackWithHandler.1
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRetrieved(typeface);
            }
        });
    }

    public void onTypefaceResult(@NonNull FontRequestWorker.TypefaceResult typefaceResult) {
        if (typefaceResult.isSuccess()) {
            onTypefaceRetrieved(typefaceResult.mTypeface);
        } else {
            onTypefaceRequestFailed(typefaceResult.mResult);
        }
    }

    public CallbackWithHandler(@NonNull FontsContractCompat.FontRequestCallback callback) {
        this.mCallback = callback;
        this.mCallbackHandler = CalleeHandler.create();
    }
}
