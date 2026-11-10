package com.ju.lib.datacommunication.network.http.core.callback;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.deserialization.DeserializeException;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.deserialization.JsonDeserializer;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeserializationCallback<T> extends AbstractCallback<T> {
    public static final String TAG;
    public Class<T> mCls;
    public IDeserializer mDeserializer;

    static {
        StringBuilder sbA = a.a(HiHttpClient.HTTP_COMMON_TAG);
        sbA.append(DeserializationCallback.class.getSimpleName());
        TAG = sbA.toString();
    }

    public DeserializationCallback(Class<T> cls) {
        this(cls, new JsonDeserializer());
    }

    public static final IDeserializer getDefaultDeserializer() {
        return new JsonDeserializer();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.callback.AbstractCallback
    public final T parse(HiResponse hiResponse) throws HttpException {
        try {
            return (T) this.mDeserializer.transform(hiResponse.getBody().string(), this.mCls);
        } catch (DeserializeException e2) {
            throw new HttpException(1002, "Parse response error! ", e2);
        }
    }

    public DeserializationCallback(Class<T> cls, IDeserializer iDeserializer) {
        if (iDeserializer == null) {
            iDeserializer = new JsonDeserializer();
            HttpLog.e(TAG, "DeserializationCallback: Deserializer is null! use default Deserializer");
        }
        this.mCls = cls;
        this.mDeserializer = iDeserializer;
    }
}
