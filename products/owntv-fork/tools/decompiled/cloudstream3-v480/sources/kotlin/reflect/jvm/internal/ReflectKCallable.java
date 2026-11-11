package kotlin.reflect.jvm.internal;

import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.calls.Caller;

/* JADX INFO: compiled from: ReflectKCallable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0015\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0018H&¢\u0006\u0002\u0010\u0019J'\u0010\u001a\u001a\u00028\u00002\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0018\"\u0004\u0018\u00010\tH\u0096\u0080\u0004¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u00028\u00002\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001eH\u0096\u0080\u0004¢\u0006\u0002\u0010\u001fR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u0006 À\u0006\u0003"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectKCallable;", "R", "Lkotlin/reflect/KCallable;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "rawBoundReceiver", "", "getRawBoundReceiver", "()Ljava/lang/Object;", "allParameters", "", "Lkotlin/reflect/KParameter;", "getAllParameters", "()Ljava/util/List;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "defaultCaller", "getDefaultCaller", "getAbsentArguments", "", "()[Ljava/lang/Object;", NotificationCompat.CATEGORY_CALL, "args", "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", "", "(Ljava/util/Map;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ReflectKCallable<R> extends KCallable<R>, KTypeParameterOwnerImpl {
    Object[] getAbsentArguments();

    List<KParameter> getAllParameters();

    Caller<?> getCaller();

    KDeclarationContainerImpl getContainer();

    Caller<?> getDefaultCaller();

    Object getRawBoundReceiver();
}
