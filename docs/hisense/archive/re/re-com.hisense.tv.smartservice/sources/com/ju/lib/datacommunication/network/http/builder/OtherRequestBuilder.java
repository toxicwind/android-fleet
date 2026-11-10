package com.ju.lib.datacommunication.network.http.builder;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;

/* JADX INFO: loaded from: classes2.dex */
public final class OtherRequestBuilder extends HttpRequestBuilder<OtherRequestBuilder> {
    public HiRequest.Method mMethod;
    public HiRequest.Body mRequestBody;

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.builder.OtherRequestBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method = new int[HiRequest.Method.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.HEAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.PATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.POST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.PUT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public OtherRequestBuilder(HiHttpClient hiHttpClient, HiRequest.Method method) {
        super(hiHttpClient);
        this.mMethod = method;
    }

    public OtherRequestBuilder body(HiRequest.Body body) {
        this.mRequestBody = body;
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ void enqueue(ICallback iCallback) {
        super.enqueue(iCallback);
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HiResponse execute() {
        return super.execute();
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public OtherRequestBuilder getTypeObject() {
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
        int iOrdinal = this.mMethod.ordinal();
        if (iOrdinal == 0) {
            builder.get();
            return;
        }
        if (iOrdinal == 1) {
            builder.head();
            return;
        }
        if (iOrdinal == 2) {
            builder.put(this.mRequestBody);
            return;
        }
        if (iOrdinal == 3) {
            builder.delete(this.mRequestBody);
            return;
        }
        if (iOrdinal == 4) {
            builder.post(this.mRequestBody);
        } else if (iOrdinal == 5) {
            builder.patch(this.mRequestBody);
        } else {
            StringBuilder sbA = a.a("Method is error! ");
            sbA.append(this.mMethod);
            throw new IllegalArgumentException(sbA.toString());
        }
    }

    public OtherRequestBuilder body(String str, String str2) {
        this.mRequestBody = HiRequest.createBody(str, str2);
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls) {
        return super.execute(cls);
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls, IDeserializer iDeserializer) {
        return super.execute(cls, iDeserializer);
    }
}
