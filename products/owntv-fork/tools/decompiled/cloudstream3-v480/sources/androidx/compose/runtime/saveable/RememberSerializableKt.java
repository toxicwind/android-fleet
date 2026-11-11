package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.saveable.serialization.SerializableSaverKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.savedstate.serialization.SavedStateConfiguration;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: RememberSerializable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aL\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\b\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0087\b¢\u0006\u0002\u0010\t\u001aU\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0007¢\u0006\u0002\u0010\f\u001aX\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\b\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\r0\bH\u0087\b¢\u0006\u0002\u0010\u000e\u001aa\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\r0\bH\u0007¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"rememberSerializable", ExifInterface.GPS_DIRECTION_TRUE, "", "inputs", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "([Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "serializer", "Lkotlinx/serialization/KSerializer;", "([Ljava/lang/Object;Lkotlinx/serialization/KSerializer;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "Landroidx/compose/runtime/MutableState;", "([Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "stateSerializer", "([Ljava/lang/Object;Lkotlinx/serialization/KSerializer;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "runtime-saveable"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RememberSerializableKt {
    /* JADX INFO: renamed from: rememberSerializable, reason: collision with other method in class */
    public static final /* synthetic */ <T> T m3573rememberSerializable(Object[] objArr, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -438135255, "CC(rememberSerializable)N(inputs,configuration,init)54@2511L182:RememberSerializable.kt#r2ddri");
        if ((i2 & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        SavedStateConfiguration savedStateConfiguration2 = savedStateConfiguration;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        SerializersModule serializersModule = savedStateConfiguration2.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        T t = (T) m3574rememberSerializable(objArrCopyOf, (KSerializer) SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration2, (Function0) function0, composer, (i << 3) & 8064, 0);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    /* JADX INFO: renamed from: rememberSerializable, reason: collision with other method in class */
    public static final <T> T m3574rememberSerializable(Object[] objArr, KSerializer<T> kSerializer, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1261607160, "C(rememberSerializable)N(inputs,serializer,configuration,init)96@4465L65:RememberSerializable.kt#r2ddri");
        if ((i2 & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1261607160, i, -1, "androidx.compose.runtime.saveable.rememberSerializable (RememberSerializable.kt:93)");
        }
        T t = (T) RememberSaveableKt.m3571rememberSaveable(Arrays.copyOf(objArr, objArr.length), SerializableSaverKt.serializableSaver(kSerializer, savedStateConfiguration), (String) null, (Function0) function0, composer, (i & 7168) | RendererCapabilities.DECODER_SUPPORT_MASK, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final /* synthetic */ <T> MutableState<T> rememberSerializable(Object[] objArr, SavedStateConfiguration savedStateConfiguration, Function0<? extends MutableState<T>> function0, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -2049958194, "CC(rememberSerializable)N(inputs,configuration,init)127@6064L187:RememberSerializable.kt#r2ddri");
        if ((i2 & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        SavedStateConfiguration savedStateConfiguration2 = savedStateConfiguration;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        SerializersModule serializersModule = savedStateConfiguration2.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        MutableState<T> mutableStateRememberSerializable = rememberSerializable(objArrCopyOf, (KSerializer) SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration2, (Function0) function0, composer, (i << 3) & 8064, 0);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return mutableStateRememberSerializable;
    }

    public static final <T> MutableState<T> rememberSerializable(Object[] objArr, KSerializer<T> kSerializer, SavedStateConfiguration savedStateConfiguration, Function0<? extends MutableState<T>> function0, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1248826527, "C(rememberSerializable)N(inputs,stateSerializer,configuration,init)166@7972L65:RememberSerializable.kt#r2ddri");
        if ((i2 & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1248826527, i, -1, "androidx.compose.runtime.saveable.rememberSerializable (RememberSerializable.kt:163)");
        }
        MutableState<T> mutableState = (MutableState) RememberSaveableKt.m3571rememberSaveable(Arrays.copyOf(objArr, objArr.length), RememberSaveableKt.mutableStateSaver(SerializableSaverKt.serializableSaver(kSerializer, savedStateConfiguration)), (String) null, (Function0) function0, composer, (i & 7168) | RendererCapabilities.DECODER_SUPPORT_MASK, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return mutableState;
    }
}
