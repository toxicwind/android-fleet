package androidx.core.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B4\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u001f\u0010\u0004\u001a\u001b\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016R'\u0010\u0004\u001a\u001b\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/core/content/ContinuationBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "continuation", "Lkotlin/coroutines/Continuation;", "onReceiveChecked", "Lkotlin/Function2;", "Landroid/content/Intent;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function2;)V", "onReceive", "context", "Landroid/content/Context;", "intent", "core-ktx"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ContinuationBroadcastReceiver extends BroadcastReceiver {
    private Continuation<?> continuation;
    private final Function2<BroadcastReceiver, Intent, Unit> onReceiveChecked;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationBroadcastReceiver(Continuation<?> continuation, Function2<? super BroadcastReceiver, ? super Intent, Unit> function2) {
        this.onReceiveChecked = function2;
        this.continuation = continuation;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.onReceiveChecked.invoke(this, intent);
        } catch (Throwable th) {
            Continuation<?> continuation = this.continuation;
            if (continuation == null) {
                throw th;
            }
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(th)));
            this.continuation = null;
        }
    }
}
