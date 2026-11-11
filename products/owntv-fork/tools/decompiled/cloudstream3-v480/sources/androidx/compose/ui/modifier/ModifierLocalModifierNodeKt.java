package androidx.compose.ui.modifier;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u001a\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0018\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u0002H\u00020\u0006\u001a?\u0010\u0000\u001a\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00042\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\n\"\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u000b\u001ao\u0010\u0000\u001a\u00020\u00012\u0016\u0010\f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u00062\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u000622\u0010\u000f\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u00060\n\"\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\u0010\u0010\u001a)\u0010\u0000\u001a\u00020\u00012\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\n\"\u0006\u0012\u0002\b\u00030\u0004H\u0007¢\u0006\u0002\u0010\u0011\u001aA\u0010\u0000\u001a\u00020\u000122\u0010\u000f\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u00060\n\"\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u0006H\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"modifierLocalMapOf", "Landroidx/compose/ui/modifier/ModifierLocalMap;", ExifInterface.GPS_DIRECTION_TRUE, "key", "Landroidx/compose/ui/modifier/ModifierLocal;", "entry", "Lkotlin/Pair;", "key1", "key2", "keys", "", "(Landroidx/compose/ui/modifier/ModifierLocal;Landroidx/compose/ui/modifier/ModifierLocal;[Landroidx/compose/ui/modifier/ModifierLocal;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "entry1", "", "entry2", "entries", "(Lkotlin/Pair;Lkotlin/Pair;[Lkotlin/Pair;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "([Landroidx/compose/ui/modifier/ModifierLocal;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "([Lkotlin/Pair;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ModifierLocalModifierNodeKt {
    public static final ModifierLocalMap modifierLocalMapOf() {
        return EmptyMap.INSTANCE;
    }

    public static final <T> ModifierLocalMap modifierLocalMapOf(ModifierLocal<T> modifierLocal) {
        return new SingleLocalMap(modifierLocal);
    }

    public static final <T> ModifierLocalMap modifierLocalMapOf(Pair<? extends ModifierLocal<T>, ? extends T> pair) {
        SingleLocalMap singleLocalMap = new SingleLocalMap(pair.getFirst());
        singleLocalMap.mo5717set$ui_release(pair.getFirst(), pair.getSecond());
        return singleLocalMap;
    }

    public static final ModifierLocalMap modifierLocalMapOf(ModifierLocal<?> modifierLocal, ModifierLocal<?> modifierLocal2, ModifierLocal<?>... modifierLocalArr) {
        Pair pair = TuplesKt.to(modifierLocal, null);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(TuplesKt.to(modifierLocal2, null));
        ArrayList arrayList = new ArrayList(modifierLocalArr.length);
        for (ModifierLocal<?> modifierLocal3 : modifierLocalArr) {
            arrayList.add(TuplesKt.to(modifierLocal3, null));
        }
        spreadBuilder.addSpread(arrayList.toArray(new Pair[0]));
        return new MultiLocalMap(pair, (Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()]));
    }

    public static final ModifierLocalMap modifierLocalMapOf(Pair<? extends ModifierLocal<?>, ? extends Object> pair, Pair<? extends ModifierLocal<?>, ? extends Object> pair2, Pair<? extends ModifierLocal<?>, ? extends Object>... pairArr) {
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(pair2);
        spreadBuilder.addSpread(pairArr);
        return new MultiLocalMap(pair, (Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()]));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use a different overloaded version of this function")
    public static final /* synthetic */ ModifierLocalMap modifierLocalMapOf(ModifierLocal... modifierLocalArr) {
        int length = modifierLocalArr.length;
        if (length == 0) {
            return EmptyMap.INSTANCE;
        }
        if (length == 1) {
            return new SingleLocalMap((ModifierLocal) ArraysKt.first(modifierLocalArr));
        }
        Pair pair = TuplesKt.to(ArraysKt.first(modifierLocalArr), null);
        List listDrop = ArraysKt.drop(modifierLocalArr, 1);
        ArrayList arrayList = new ArrayList(listDrop.size());
        int size = listDrop.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(TuplesKt.to((ModifierLocal) listDrop.get(i), null));
        }
        Pair[] pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        return new MultiLocalMap(pair, (Pair[]) Arrays.copyOf(pairArr, pairArr.length));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use a different overloaded version of this function")
    public static final /* synthetic */ ModifierLocalMap modifierLocalMapOf(Pair... pairArr) {
        int length = pairArr.length;
        if (length == 0) {
            return EmptyMap.INSTANCE;
        }
        if (length == 1) {
            return new MultiLocalMap((Pair) ArraysKt.first(pairArr), new Pair[0]);
        }
        Pair pair = (Pair) ArraysKt.first(pairArr);
        Pair[] pairArr2 = (Pair[]) ArraysKt.drop(pairArr, 1).toArray(new Pair[0]);
        return new MultiLocalMap(pair, (Pair[]) Arrays.copyOf(pairArr2, pairArr2.length));
    }
}
