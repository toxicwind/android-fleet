package androidx.savedstate.serialization;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import androidx.savedstate.internal.CanonicalName_jvmKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.KSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SavedStateRegistryOwnerDelegate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0004:\u0001#B=\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e¢\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0012\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0096\u0002¢\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001aJ\u0011\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016¢\u0006\u0002\u0010\u001eJ\u0017\u0010\u001f\u001a\u0004\u0018\u00018\u00002\u0006\u0010 \u001a\u00020\nH\u0002¢\u0006\u0002\u0010!J\u001e\u0010\"\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/savedstate/serialization/SavedStateRegistryOwnerDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/properties/ReadWriteProperty;", "", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "registry", "Landroidx/savedstate/SavedStateRegistry;", "serializer", "Lkotlinx/serialization/KSerializer;", "key", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "<init>", "(Landroidx/savedstate/SavedStateRegistry;Lkotlinx/serialization/KSerializer;Ljava/lang/String;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;)V", "cachedValue", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "saveState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "()Landroid/os/Bundle;", "loadInitialValue", "qualifiedKey", "(Ljava/lang/String;)Ljava/lang/Object;", "getQualifiedKey", "UNINITIALIZED", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SavedStateRegistryOwnerDelegate<T> implements ReadWriteProperty<Object, T>, SavedStateRegistry.SavedStateProvider {
    private Object cachedValue;
    private final SavedStateConfiguration configuration;
    private final Function0<T> init;
    private final String key;
    private final SavedStateRegistry registry;
    private final KSerializer<T> serializer;

    /* JADX WARN: Multi-variable type inference failed */
    public SavedStateRegistryOwnerDelegate(SavedStateRegistry registry, KSerializer<T> serializer, String str, SavedStateConfiguration configuration, Function0<? extends T> init) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        this.registry = registry;
        this.serializer = serializer;
        this.key = str;
        this.configuration = configuration;
        this.init = init;
        this.cachedValue = UNINITIALIZED.INSTANCE;
    }

    /* JADX INFO: compiled from: SavedStateRegistryOwnerDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/savedstate/serialization/SavedStateRegistryOwnerDelegate$UNINITIALIZED;", "", "<init>", "()V", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class UNINITIALIZED {
        public static final UNINITIALIZED INSTANCE = new UNINITIALIZED();

        private UNINITIALIZED() {
        }
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(this.cachedValue, UNINITIALIZED.INSTANCE)) {
            String qualifiedKey = getQualifiedKey(thisRef, property);
            this.registry.registerSavedStateProvider(qualifiedKey, this);
            this.cachedValue = loadInitialValue(qualifiedKey);
        }
        return (T) this.cachedValue;
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(Object thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(this.cachedValue, UNINITIALIZED.INSTANCE)) {
            this.registry.registerSavedStateProvider(getQualifiedKey(thisRef, property), this);
        }
        this.cachedValue = value;
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public Bundle saveState() {
        Pair[] pairArr;
        if (!Intrinsics.areEqual(this.cachedValue, UNINITIALIZED.INSTANCE)) {
            return SavedStateEncoderKt.encodeToSavedStateNullable(this.serializer, this.cachedValue, this.configuration);
        }
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m8016constructorimpl(bundleBundleOf);
        return bundleBundleOf;
    }

    private final T loadInitialValue(String qualifiedKey) {
        Bundle bundleConsumeRestoredStateForKey = this.registry.consumeRestoredStateForKey(qualifiedKey);
        if (bundleConsumeRestoredStateForKey == null) {
            return this.init.invoke();
        }
        KSerializer<T> kSerializer = this.serializer;
        Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<{T of androidx.savedstate.serialization.SavedStateRegistryOwnerDelegate & Any}>");
        return (T) SavedStateDecoderKt.decodeFromSavedStateNullable(kSerializer, bundleConsumeRestoredStateForKey, this.configuration);
    }

    private final String getQualifiedKey(Object thisRef, KProperty<?> property) {
        String str;
        String str2 = this.key;
        if (str2 != null) {
            return str2;
        }
        if (thisRef != null) {
            str = CanonicalName_jvmKt.getCanonicalName(Reflection.getOrCreateKotlinClass(thisRef.getClass())) + '.';
        } else {
            str = "";
        }
        return str + property.getName();
    }
}
