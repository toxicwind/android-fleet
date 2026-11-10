package com.ju.lib.utils.glide;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.ju.lib.utils.log.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class GlideRequestListener implements RequestListener {
    public static final String TAG = "GlideRequestListener";

    @Nullable
    public IGlideExceptionHandler mIGlideExceptionHandler;

    public GlideRequestListener(@NonNull IGlideExceptionHandler iGlideExceptionHandler) {
        this.mIGlideExceptionHandler = iGlideExceptionHandler;
    }

    public boolean onException(@Nullable Exception exc, Object obj, Target target, boolean z) {
        IGlideExceptionHandler iGlideExceptionHandler;
        if (exc != null) {
            if (exc.getMessage() == null || !exc.getMessage().startsWith("Failed to load model:") || Log.isLoggable(TAG, 3)) {
                exc.printStackTrace();
                LogUtil.i(TAG, exc, "mode=", obj);
            } else {
                LogUtil.e(TAG, exc.getMessage(), "mode=", obj);
            }
        }
        int id = target instanceof ViewTarget ? ((ViewTarget) target).getView().getId() : 0;
        if (obj instanceof String) {
            IGlideExceptionHandler iGlideExceptionHandler2 = this.mIGlideExceptionHandler;
            if (iGlideExceptionHandler2 != null) {
                iGlideExceptionHandler2.onLoadUrlFailed((String) obj, id, exc);
            }
        } else if (obj instanceof Uri) {
            IGlideExceptionHandler iGlideExceptionHandler3 = this.mIGlideExceptionHandler;
            if (iGlideExceptionHandler3 != null) {
                iGlideExceptionHandler3.onLoadUrlFailed(obj.toString(), id, exc);
            }
        } else if (obj instanceof Integer) {
            IGlideExceptionHandler iGlideExceptionHandler4 = this.mIGlideExceptionHandler;
            if (iGlideExceptionHandler4 != null) {
                iGlideExceptionHandler4.onLoadLocalResFailed(((Integer) obj).intValue(), id, exc);
            }
        } else if ((obj instanceof byte[]) && (iGlideExceptionHandler = this.mIGlideExceptionHandler) != null) {
            iGlideExceptionHandler.onLoadByteArrayFailed("glide load byte[] failed", id, exc);
        }
        return false;
    }

    public boolean onResourceReady(Object obj, Object obj2, Target target, boolean z, boolean z2) {
        return false;
    }
}
