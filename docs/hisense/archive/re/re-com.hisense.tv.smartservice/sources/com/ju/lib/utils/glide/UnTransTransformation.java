package com.ju.lib.utils.glide;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

/* JADX INFO: loaded from: classes2.dex */
public class UnTransTransformation implements Transformation<Bitmap> {
    public static final UnTransTransformation TRANSFORMATION = new UnTransTransformation();

    public static UnTransTransformation get() {
        return TRANSFORMATION;
    }

    public String getId() {
        return UnTransTransformation.class.getName();
    }

    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        return resource;
    }
}
