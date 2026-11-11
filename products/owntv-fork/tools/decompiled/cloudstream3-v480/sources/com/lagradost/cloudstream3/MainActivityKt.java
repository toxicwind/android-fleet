package com.lagradost.cloudstream3;

import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0001\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"$\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\u0006\"\u0004\b\r\u0010\b¨\u0006\u000e"}, d2 = {"jsonResponseParser", "com/lagradost/cloudstream3/MainActivityKt$jsonResponseParser$1", "Lcom/lagradost/cloudstream3/MainActivityKt$jsonResponseParser$1;", "app", "Lcom/lagradost/nicehttp/Requests;", "getApp", "()Lcom/lagradost/nicehttp/Requests;", "setApp", "(Lcom/lagradost/nicehttp/Requests;)V", "insecureApp", "getInsecureApp$annotations", "()V", "getInsecureApp", "setInsecureApp", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MainActivityKt {

    /* JADX INFO: renamed from: app, reason: collision with root package name */
    private static Requests f84app;
    private static Requests insecureApp;
    private static final MainActivityKt$jsonResponseParser$1 jsonResponseParser;

    public static /* synthetic */ void getInsecureApp$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.lagradost.cloudstream3.MainActivityKt$jsonResponseParser$1] */
    static {
        ?? r0 = new ResponseParser() { // from class: com.lagradost.cloudstream3.MainActivityKt$jsonResponseParser$1
            @Override // com.lagradost.nicehttp.ResponseParser
            public <T> T parse(String text, KClass<T> kClass) {
                Intrinsics.checkNotNullParameter(text, "text");
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                return (T) AppUtils.INSTANCE.parseJson(text, kClass);
            }

            @Override // com.lagradost.nicehttp.ResponseParser
            public <T> T parseSafe(String text, KClass<T> kClass) {
                Intrinsics.checkNotNullParameter(text, "text");
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                try {
                    return (T) parse(text, kClass);
                } catch (Exception unused) {
                    return null;
                }
            }

            @Override // com.lagradost.nicehttp.ResponseParser
            public String writeValueAsString(Object obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                return AppUtils.INSTANCE.toJson(obj);
            }
        };
        jsonResponseParser = r0;
        Requests requests = new Requests(null, null, null, null, null, 0, null, 0L, (ResponseParser) r0, 255, null);
        requests.setDefaultHeaders(MapsKt.mapOf(TuplesKt.to("user-agent", MainAPIKt.USER_AGENT)));
        f84app = requests;
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        Requests requests2 = new Requests(objArr, null, objArr2, null, null, 0, null, 0L, (ResponseParser) r0, 255, null);
        requests2.setDefaultHeaders(MapsKt.mapOf(TuplesKt.to("user-agent", MainAPIKt.USER_AGENT)));
        insecureApp = requests2;
    }

    public static final Requests getApp() {
        return f84app;
    }

    public static final void setApp(Requests requests) {
        Intrinsics.checkNotNullParameter(requests, "<set-?>");
        f84app = requests;
    }

    public static final Requests getInsecureApp() {
        return insecureApp;
    }

    public static final void setInsecureApp(Requests requests) {
        Intrinsics.checkNotNullParameter(requests, "<set-?>");
        insecureApp = requests;
    }
}
