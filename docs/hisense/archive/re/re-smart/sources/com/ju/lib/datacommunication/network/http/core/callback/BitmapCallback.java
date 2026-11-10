package com.ju.lib.datacommunication.network.http.core.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BitmapCallback extends AbstractCallback<Bitmap> {
    public BitmapFactory.Options mOpts;
    public Rect mOutPadding;

    public BitmapCallback(Rect rect, BitmapFactory.Options options) {
        this.mOutPadding = rect;
        this.mOpts = options;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ju.lib.datacommunication.network.http.core.callback.AbstractCallback
    public final Bitmap parse(HiResponse hiResponse) throws HttpException {
        try {
            return BitmapFactory.decodeStream(hiResponse.getBody().getInputStream(), this.mOutPadding, this.mOpts);
        } catch (Exception e2) {
            throw new HttpException(1002, e2);
        }
    }
}
