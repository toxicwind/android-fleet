package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.km.Modality;

/* JADX INFO: compiled from: KotlinKCallable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0015\u0010\u0010\u001a\u00020\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\u0011\u001a\u00020\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0019\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKCallable;", "R", "Lkotlin/reflect/jvm/internal/ReflectKCallableImpl;", "<init>", "()V", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "rawBoundReceiver", "", "getRawBoundReceiver", "()Ljava/lang/Object;", "isFinal", "", "()Z", "isOpen", "isAbstract", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class KotlinKCallable<R> extends ReflectKCallableImpl<R> {
    public abstract Modality getModality();

    @Override // kotlin.reflect.KCallable
    public final boolean isFinal() {
        return getModality() == Modality.FINAL;
    }

    @Override // kotlin.reflect.KCallable
    public final boolean isOpen() {
        return getModality() == Modality.OPEN;
    }

    @Override // kotlin.reflect.KCallable
    public final boolean isAbstract() {
        return getModality() == Modality.ABSTRACT;
    }
}
