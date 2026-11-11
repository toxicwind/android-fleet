package coil3.network.okhttp.internal;

import androidx.exifinterface.media.ExifInterface;
import coil3.network.NetworkClient;
import coil3.network.NetworkRequest;
import coil3.network.NetworkResponse;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JQ\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u00020\t21\u0010\n\u001a-\b\u0001\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000bH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u001b"}, d2 = {"Lcoil3/network/okhttp/internal/CallFactoryNetworkClient;", "Lcoil3/network/NetworkClient;", "callFactory", "Lokhttp3/Call$Factory;", "constructor-impl", "(Lokhttp3/Call$Factory;)Lokhttp3/Call$Factory;", "executeRequest", ExifInterface.GPS_DIRECTION_TRUE, "request", "Lcoil3/network/NetworkRequest;", "block", "Lkotlin/Function2;", "Lcoil3/network/NetworkResponse;", "Lkotlin/ParameterName;", "name", "response", "Lkotlin/coroutines/Continuation;", "", "executeRequest-impl", "(Lokhttp3/Call$Factory;Lcoil3/network/NetworkRequest;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", "other", "hashCode", "", "toString", "", "coil-network-okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class CallFactoryNetworkClient implements NetworkClient {
    private final Call.Factory callFactory;

    /* JADX INFO: renamed from: coil3.network.okhttp.internal.CallFactoryNetworkClient$executeRequest$1, reason: invalid class name */
    /* JADX INFO: compiled from: utils.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.network.okhttp.internal.CallFactoryNetworkClient", f = "utils.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2}, l = {24, 24, 25}, m = "executeRequest-impl", n = {"arg0", "request", "block", "arg0", "request", "block", "arg0", "request", "block", "response", "$i$a$-use-CallFactoryNetworkClient$executeRequest$2"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "I$0"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CallFactoryNetworkClient.m8111executeRequestimpl(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CallFactoryNetworkClient m8107boximpl(Call.Factory factory) {
        return new CallFactoryNetworkClient(factory);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static Call.Factory m8108constructorimpl(Call.Factory factory) {
        return factory;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8109equalsimpl(Call.Factory factory, Object obj) {
        return (obj instanceof CallFactoryNetworkClient) && Intrinsics.areEqual(factory, ((CallFactoryNetworkClient) obj).getCallFactory());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8110equalsimpl0(Call.Factory factory, Call.Factory factory2) {
        return Intrinsics.areEqual(factory, factory2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8112hashCodeimpl(Call.Factory factory) {
        return factory.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8113toStringimpl(Call.Factory factory) {
        return "CallFactoryNetworkClient(callFactory=" + factory + ')';
    }

    public boolean equals(Object other) {
        return m8109equalsimpl(this.callFactory, other);
    }

    public int hashCode() {
        return m8112hashCodeimpl(this.callFactory);
    }

    public String toString() {
        return m8113toStringimpl(this.callFactory);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Call.Factory getCallFactory() {
        return this.callFactory;
    }

    private /* synthetic */ CallFactoryNetworkClient(Call.Factory factory) {
        this.callFactory = factory;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: executeRequest-impl, reason: not valid java name */
    public static <T> Object m8111executeRequestimpl(Call.Factory factory, NetworkRequest networkRequest, Function2<? super NetworkResponse, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        AnonymousClass1 anonymousClass1;
        Call.Factory factory2;
        Function2<? super NetworkResponse, ? super Continuation<? super T>, ? extends Object> function3;
        Call.Factory factory3;
        Closeable closeable;
        Closeable closeable2;
        Object objInvoke;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object request = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(request);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(factory);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(networkRequest);
            anonymousClass1.L$2 = function2;
            anonymousClass1.L$3 = factory;
            anonymousClass1.label = 1;
            request = UtilsKt.toRequest(networkRequest, anonymousClass1);
            if (request != coroutine_suspended) {
                factory2 = factory;
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i2 = anonymousClass1.I$0;
                closeable2 = (Closeable) anonymousClass1.L$3;
                try {
                    ResultKt.throwOnFailure(request);
                    CloseableKt.closeFinally(closeable2, null);
                    return request;
                } catch (Throwable th) {
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(closeable2, th);
                        throw th2;
                    }
                }
            }
            function3 = (Function2) anonymousClass1.L$2;
            networkRequest = (NetworkRequest) anonymousClass1.L$1;
            factory3 = (Call.Factory) anonymousClass1.L$0;
            ResultKt.throwOnFailure(request);
            closeable = (Closeable) request;
            try {
                Response response = (Response) closeable;
                NetworkResponse networkResponse = UtilsKt.toNetworkResponse(response);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(factory3);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(networkRequest);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                anonymousClass1.L$3 = closeable;
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(response);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 3;
                objInvoke = function3.invoke(networkResponse, anonymousClass1);
                if (objInvoke != coroutine_suspended) {
                    request = objInvoke;
                    closeable2 = closeable;
                    CloseableKt.closeFinally(closeable2, null);
                    return request;
                }
                return coroutine_suspended;
            } catch (Throwable th3) {
                th = th3;
                closeable2 = closeable;
                throw th;
            }
        }
        factory = (Call.Factory) anonymousClass1.L$3;
        function2 = (Function2) anonymousClass1.L$2;
        networkRequest = (NetworkRequest) anonymousClass1.L$1;
        factory2 = (Call.Factory) anonymousClass1.L$0;
        ResultKt.throwOnFailure(request);
        Call callNewCall = factory.newCall((Request) request);
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(factory2);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(networkRequest);
        anonymousClass1.L$2 = function2;
        anonymousClass1.L$3 = null;
        anonymousClass1.label = 2;
        request = CallsKt.await(callNewCall, anonymousClass1);
        if (request != coroutine_suspended) {
            function3 = function2;
            factory3 = factory2;
            closeable = (Closeable) request;
            Response response2 = (Response) closeable;
            NetworkResponse networkResponse2 = UtilsKt.toNetworkResponse(response2);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(factory3);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(networkRequest);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function3);
            anonymousClass1.L$3 = closeable;
            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(response2);
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 3;
            objInvoke = function3.invoke(networkResponse2, anonymousClass1);
            if (objInvoke != coroutine_suspended) {
                request = objInvoke;
                closeable2 = closeable;
                CloseableKt.closeFinally(closeable2, null);
                return request;
            }
        }
        return coroutine_suspended;
    }

    @Override // coil3.network.NetworkClient
    public <T> Object executeRequest(NetworkRequest networkRequest, Function2<? super NetworkResponse, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return m8111executeRequestimpl(this.callFactory, networkRequest, function2, continuation);
    }
}
