package com.ju.lib.datacommunication.network.http.core.callback;

import android.os.Handler;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.ICallback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractCallback<T> implements ICallback {
    private void error(final HiRequest hiRequest, final HiResponse.Trace trace, final int i, final Exception exc) {
        Handler handler = getHandler();
        if (handler == null) {
            onFailure(hiRequest, trace, i, exc);
        } else {
            handler.post(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.core.callback.AbstractCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractCallback.this.onFailure(hiRequest, trace, i, exc);
                }
            });
        }
    }

    private void success(final HiResponse hiResponse, final T t) {
        Handler handler = getHandler();
        if (handler == null) {
            onResponse(hiResponse, t);
        } else {
            handler.post(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.core.callback.AbstractCallback.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    AbstractCallback.this.onResponse(hiResponse, t);
                }
            });
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.ICallback
    public final void failure(HiRequest hiRequest, HiResponse.Trace trace, Exception exc) {
        error(hiRequest, trace, 1001, exc);
    }

    public Handler getHandler() {
        return null;
    }

    public abstract void onFailure(HiRequest hiRequest, HiResponse.Trace trace, int i, Exception exc);

    public abstract void onResponse(HiResponse hiResponse, T t);

    public abstract T parse(HiResponse hiResponse);

    @Override // com.ju.lib.datacommunication.network.http.core.ICallback
    public final void response(HiResponse hiResponse) {
        Exception e2;
        if (!hiResponse.isSuccessful()) {
            error(hiResponse.getRequest(), hiResponse.getTrace(), hiResponse.getCode(), new HttpException(hiResponse.getCode(), hiResponse.getMessage()));
            return;
        }
        boolean z = false;
        try {
            try {
                T t = parse(hiResponse);
                try {
                    if (t != null) {
                        success(hiResponse, t);
                    } else {
                        error(hiResponse.getRequest(), hiResponse.getTrace(), 1002, new HttpException(1002));
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    if (z) {
                        error(hiResponse.getRequest(), hiResponse.getTrace(), -1, e2);
                    } else {
                        e2.printStackTrace();
                    }
                }
            } catch (HttpException e4) {
                error(hiResponse.getRequest(), hiResponse.getTrace(), e4.getCode(), e4);
            }
        } catch (Exception e5) {
            z = true;
            e2 = e5;
        }
    }
}
