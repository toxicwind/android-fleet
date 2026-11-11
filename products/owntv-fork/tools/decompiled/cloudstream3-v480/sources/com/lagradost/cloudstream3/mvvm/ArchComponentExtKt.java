package com.lagradost.cloudstream3.mvvm;

import androidx.exifinterface.media.ExifInterface;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.utils.AppDebug;
import io.ktor.http.ContentType;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.CancellationException;
import javax.net.ssl.SSLHandshakeException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.full.NoSuchPropertyException;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: ArchComponentExt.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001a\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a$\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a\u001a\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a(\u0010\n\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a(\u0010\t\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f\u001a#\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0006H\u0007¢\u0006\u0002\u0010\u0013\u001a!\u0010\u0014\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0006¢\u0006\u0002\u0010\u0013\u001a4\u0010\u0015\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0016H\u0086@¢\u0006\u0002\u0010\u0019\u001a4\u0010\u001a\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0016H\u0087@¢\u0006\u0002\u0010\u0019\u001a\n\u0010\u001b\u001a\u00020\u0001*\u00020\u000f\u001a\u0014\u0010\u001c\u001a\u00020\u0001*\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\f\u001a\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00110\u001f\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000f\u001aL\u0010 \u001a\u00020!*\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2'\u0010'\u001a#\b\u0001\u0012\u0004\u0012\u00020\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180(¢\u0006\u0002\b)¢\u0006\u0002\u0010*\u001a\u001a\u0010+\u001a\b\u0012\u0004\u0012\u0002H\u00110\u001f\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000f\u001a:\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00110\u001f\"\u0004\b\u0000\u0010\u00112\u001e\b\u0001\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0016H\u0087@¢\u0006\u0002\u0010\u0019\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006-"}, d2 = {"DEBUG_EXCEPTION", "", "DEBUG_PRINT", "debugException", "", ContentType.Message.TYPE, "Lkotlin/Function0;", "debugPrint", "tag", "debugWarning", "debugAssert", "assert", "", "logError", "throwable", "", "normalSafeApiCall", ExifInterface.GPS_DIRECTION_TRUE, "apiCall", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "safe", "safeAsync", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendSafeApiCall", "getAllMessages", "getStackTracePretty", "showMessage", "safeFail", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "launchSafe", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "throwAbleToResource", "safeApiCall", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ArchComponentExtKt {
    public static final String DEBUG_EXCEPTION = "THIS IS A DEBUG EXCEPTION!";
    public static final String DEBUG_PRINT = "DEBUG PRINT";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.mvvm.ArchComponentExtKt$safeAsync$1, reason: invalid class name */
    /* JADX INFO: compiled from: ArchComponentExt.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.mvvm.ArchComponentExtKt", f = "ArchComponentExt.kt", i = {0}, l = {110}, m = "safeAsync", n = {"apiCall"}, nl = {111}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ArchComponentExtKt.safeAsync(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.mvvm.ArchComponentExtKt$suspendSafeApiCall$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ArchComponentExt.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.mvvm.ArchComponentExtKt", f = "ArchComponentExt.kt", i = {0}, l = {124}, m = "suspendSafeApiCall", n = {"apiCall"}, nl = {125}, s = {"L$0"}, v = 2)
    static final class C07411<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07411(Continuation<? super C07411> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ArchComponentExtKt.suspendSafeApiCall(null, this);
        }
    }

    public static final void debugException(Function0<String> message) throws DebugException {
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug()) {
            throw new DebugException(message.invoke());
        }
    }

    public static /* synthetic */ void debugPrint$default(String tag, Function0 message, int i, Object obj) {
        if ((i & 1) != 0) {
            tag = DEBUG_PRINT;
        }
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug()) {
            Log.INSTANCE.d(tag, (String) message.invoke());
        }
    }

    public static final void debugPrint(String tag, Function0<String> message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug()) {
            Log.INSTANCE.d(tag, message.invoke());
        }
    }

    public static final void debugWarning(Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug()) {
            logError(new DebugException(message.invoke()));
        }
    }

    public static final void debugAssert(Function0<Boolean> function0, Function0<String> message) throws DebugException {
        Intrinsics.checkNotNullParameter(function0, "assert");
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug() && function0.invoke().booleanValue()) {
            throw new DebugException(message.invoke());
        }
    }

    public static final void debugWarning(Function0<Boolean> function0, Function0<String> message) {
        Intrinsics.checkNotNullParameter(function0, "assert");
        Intrinsics.checkNotNullParameter(message, "message");
        if (AppDebug.INSTANCE.isDebug() && function0.invoke().booleanValue()) {
            logError(new DebugException(message.invoke()));
        }
    }

    public static final void logError(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Log.INSTANCE.d("ApiError", "-------------------------------------------------------------------");
        Log.INSTANCE.d("ApiError", "safeApiCall: " + throwable.getLocalizedMessage());
        Log.INSTANCE.d("ApiError", "safeApiCall: " + throwable.getMessage());
        throwable.printStackTrace();
        Log.INSTANCE.d("ApiError", "-------------------------------------------------------------------");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Outdated function, use `safe` instead", replaceWith = @ReplaceWith(expression = "safe", imports = {}))
    public static final <T> T normalSafeApiCall(Function0<? extends T> apiCall) {
        Intrinsics.checkNotNullParameter(apiCall, "apiCall");
        try {
            return apiCall.invoke();
        } catch (Throwable th) {
            logError(th);
            return null;
        }
    }

    public static final <T> T safe(Function0<? extends T> apiCall) {
        Intrinsics.checkNotNullParameter(apiCall, "apiCall");
        try {
            return apiCall.invoke();
        } catch (Throwable th) {
            logError(th);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T> Object safeAsync(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        AnonymousClass1 anonymousClass1;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        try {
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass1.label = 1;
            Object objInvoke = function1.invoke(anonymousClass1);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        } catch (Throwable th) {
            logError(th);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Outdated function, use `safeAsync` instead", replaceWith = @ReplaceWith(expression = "safeAsync", imports = {}))
    public static final <T> Object suspendSafeApiCall(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        C07411 c07411;
        if (continuation instanceof C07411) {
            c07411 = (C07411) continuation;
            if ((c07411.label & Integer.MIN_VALUE) != 0) {
                c07411.label -= Integer.MIN_VALUE;
            } else {
                c07411 = new C07411(continuation);
            }
        } else {
            c07411 = new C07411(continuation);
        }
        Object obj = c07411.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07411.label;
        try {
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            c07411.L$0 = SpillingKt.nullOutSpilledVariable(function1);
            c07411.label = 1;
            Object objInvoke = function1.invoke(c07411);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        } catch (Throwable th) {
            logError(th);
            return null;
        }
    }

    public static final String getAllMessages(Throwable th) {
        String allMessages;
        Intrinsics.checkNotNullParameter(th, "<this>");
        StringBuilder sb = new StringBuilder();
        String localizedMessage = th.getLocalizedMessage();
        String str = "";
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        sb.append(localizedMessage);
        Throwable cause = th.getCause();
        if (cause != null && (allMessages = getAllMessages(cause)) != null) {
            String str2 = "\n" + allMessages;
            if (str2 != null) {
                str = str2;
            }
        }
        sb.append(str);
        return sb.toString();
    }

    public static /* synthetic */ String getStackTracePretty$default(Throwable th, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getStackTracePretty(th, z);
    }

    public static final String getStackTracePretty(Throwable th, boolean z) {
        String localizedMessage;
        Intrinsics.checkNotNullParameter(th, "<this>");
        String str = "";
        if (z && (localizedMessage = th.getLocalizedMessage()) != null) {
            String str2 = "\n" + localizedMessage;
            if (str2 != null) {
                str = str2;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        StackTraceElement[] stackTrace = th.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
        sb.append(ArraysKt.joinToString$default(stackTrace, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lagradost.cloudstream3.mvvm.ArchComponentExtKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ArchComponentExtKt.getStackTracePretty$lambda$1((StackTraceElement) obj);
            }
        }, 30, (Object) null));
        return sb.toString();
    }

    static final CharSequence getStackTracePretty$lambda$1(StackTraceElement stackTraceElement) {
        return stackTraceElement.getFileName() + ' ' + stackTraceElement.getLineNumber();
    }

    public static final <T> Resource<T> safeFail(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        return new Resource.Failure(false, getStackTracePretty$default(throwable, false, 1, null));
    }

    public static /* synthetic */ Job launchSafe$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return launchSafe(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Job launchSafe(CoroutineScope coroutineScope, CoroutineContext context, CoroutineStart start, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(block, "block");
        return BuildersKt.launch(coroutineScope, context, start, new ArchComponentExtKt$launchSafe$obj$1(block, null));
    }

    public static final <T> Resource<T> throwAbleToResource(Throwable throwable) {
        Resource<T> resourceThrowAbleToResource;
        String fileName;
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if ((throwable instanceof NoSuchMethodException) || (throwable instanceof NoSuchFieldException) || (throwable instanceof NoSuchMethodError) || (throwable instanceof NoSuchFieldError) || (throwable instanceof NoSuchPropertyException)) {
            return new Resource.Failure(false, "App or extension is outdated, update the app or try pre-release.\n" + throwable.getMessage());
        }
        if (throwable instanceof NullPointerException) {
            StackTraceElement[] stackTrace = ((NullPointerException) throwable).getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (stackTraceElement != null && (fileName = stackTraceElement.getFileName()) != null && StringsKt.endsWith(fileName, "provider.kt", true)) {
                    return new Resource.Failure(false, "NullPointerException at " + stackTraceElement.getFileName() + ' ' + stackTraceElement.getLineNumber() + "\nSite might have updated or added Cloudflare/DDOS protection");
                }
            }
            return safeFail(throwable);
        }
        if ((throwable instanceof SocketTimeoutException) || (throwable instanceof InterruptedIOException)) {
            return new Resource.Failure(true, "Connection Timeout\nPlease try again later.");
        }
        if (throwable instanceof UnknownHostException) {
            return new Resource.Failure(true, "Cannot connect to server, try again later.\n" + throwable.getMessage());
        }
        if (throwable instanceof ErrorLoadingException) {
            String message = throwable.getMessage();
            if (message == null) {
                message = "Error loading, try again later.";
            }
            return new Resource.Failure(true, message);
        }
        if (throwable instanceof NotImplementedError) {
            return new Resource.Failure(false, "This operation is not implemented.");
        }
        if (throwable instanceof SSLHandshakeException) {
            StringBuilder sb = new StringBuilder();
            String message2 = throwable.getMessage();
            if (message2 == null) {
                message2 = "SSLHandshakeException";
            }
            sb.append(message2);
            sb.append("\nTry a VPN or DNS.");
            return new Resource.Failure(true, sb.toString());
        }
        if (throwable instanceof CancellationException) {
            Throwable cause = throwable.getCause();
            return (cause == null || (resourceThrowAbleToResource = throwAbleToResource(cause)) == null) ? safeFail(throwable) : resourceThrowAbleToResource;
        }
        return safeFail(throwable);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.mvvm.ArchComponentExtKt$safeApiCall$2, reason: invalid class name */
    /* JADX INFO: compiled from: ArchComponentExt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/mvvm/Resource;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.mvvm.ArchComponentExtKt$safeApiCall$2", f = "ArchComponentExt.kt", i = {}, l = {243}, m = "invokeSuspend", n = {}, nl = {HebrewProber.NORMAL_PE}, s = {}, v = 2)
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Resource<? extends T>>, Object> {
        final /* synthetic */ Function1<Continuation<? super T>, Object> $apiCall;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$apiCall = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$apiCall, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Resource<? extends T>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Function1<Continuation<? super T>, Object> function1 = this.$apiCall;
                    this.label = 1;
                    obj = function1.invoke(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return new Resource.Success(obj);
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                return ArchComponentExtKt.throwAbleToResource(th);
            }
        }
    }

    public static final <T> Object safeApiCall(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super Resource<? extends T>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(function1, null), continuation);
    }
}
