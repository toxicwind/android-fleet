package androidx.lifecycle.viewmodel;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreationExtras.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0007\u001a\u0019\u0010\b\u001a\u00020\t*\u00020\u00012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0086\u0002\u001a\u0015\u0010\f\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u000e\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001H\u0086\u0002¨\u0006\u000f"}, d2 = {"CreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "initialExtras", "builderAction", "Lkotlin/Function1;", "Landroidx/lifecycle/viewmodel/MutableCreationExtras;", "", "Lkotlin/ExtensionFunctionType;", "contains", "", "key", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "plus", "creationExtras", "plusAssign", "lifecycle-viewmodel"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CreationExtrasKt {
    public static final CreationExtras CreationExtras() {
        return CreationExtras$default(null, null, 3, null);
    }

    public static final CreationExtras CreationExtras(CreationExtras initialExtras) {
        Intrinsics.checkNotNullParameter(initialExtras, "initialExtras");
        return CreationExtras$default(initialExtras, null, 2, null);
    }

    public static /* synthetic */ CreationExtras CreationExtras$default(CreationExtras creationExtras, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            creationExtras = CreationExtras.Empty.INSTANCE;
        }
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: androidx.lifecycle.viewmodel.CreationExtrasKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return CreationExtrasKt.CreationExtras$lambda$0((MutableCreationExtras) obj2);
                }
            };
        }
        return CreationExtras(creationExtras, function1);
    }

    static final Unit CreationExtras$lambda$0(MutableCreationExtras mutableCreationExtras) {
        Intrinsics.checkNotNullParameter(mutableCreationExtras, "<this>");
        return Unit.INSTANCE;
    }

    public static final CreationExtras CreationExtras(CreationExtras initialExtras, Function1<? super MutableCreationExtras, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(initialExtras, "initialExtras");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(initialExtras);
        builderAction.invoke(mutableCreationExtras);
        return mutableCreationExtras;
    }

    public static final boolean contains(CreationExtras creationExtras, CreationExtras.Key<?> key) {
        Intrinsics.checkNotNullParameter(creationExtras, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return creationExtras.getExtras$lifecycle_viewmodel().containsKey(key);
    }

    public static final MutableCreationExtras plus(CreationExtras creationExtras, CreationExtras creationExtras2) {
        Intrinsics.checkNotNullParameter(creationExtras, "<this>");
        Intrinsics.checkNotNullParameter(creationExtras2, "creationExtras");
        return new MutableCreationExtras((Map<CreationExtras.Key<?>, ? extends Object>) MapsKt.plus(creationExtras.getExtras$lifecycle_viewmodel(), creationExtras2.getExtras$lifecycle_viewmodel()));
    }

    public static final void plusAssign(MutableCreationExtras mutableCreationExtras, CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(mutableCreationExtras, "<this>");
        Intrinsics.checkNotNullParameter(creationExtras, "creationExtras");
        mutableCreationExtras.getExtras$lifecycle_viewmodel().putAll(creationExtras.getExtras$lifecycle_viewmodel());
    }
}
