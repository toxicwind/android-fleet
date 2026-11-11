package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import io.ktor.http.ContentType;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u0002H\u00060\u000b¢\u0006\u0002\b\fH\u0087\b¢\u0006\u0002\u0010\r\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a\b\u0010\u001d\u001a\u00020\tH\u0007\u001a\u0018\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0011H\u0007\u001a(\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\b\u0010\"\u001a\u00020\u000fH\u0007\u001a\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0007\u001a\u0014\u0010$\u001a\u00020\u000f*\u00020\u00022\u0006\u0010%\u001a\u00020&H\u0000\u001a7\u0010'\u001a\u00020\u000f\"\u0004\b\u0000\u0010(*\u00020\u00022\b\u0010)\u001a\u0004\u0018\u00010*2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H(0,H\u0080\b\u001a\u0014\u00101\u001a\u00020\u000f*\u00020\u00022\u0006\u0010%\u001a\u00020&H\u0000\u001a\u001e\u00102\u001a\u00020\u000f*\u00020\u00022\u0006\u00103\u001a\u00020\u00012\b\u00104\u001a\u0004\u0018\u000105H\u0002\u001a5\u00106\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H907\"\b\b\u0000\u00108*\u000205\"\b\b\u0001\u00109*\u0002052\u0006\u0010:\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010;\u001a(\u0010<\u001a\u0004\u0018\u0001052\b\u0010=\u001a\u0004\u0018\u0001052\b\u0010>\u001a\u0004\u0018\u0001052\b\u0010?\u001a\u0004\u0018\u000105H\u0002\u001a\u001a\u0010@\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010C\u001a\u00020\u0001H\u0002\u001a\u001a\u0010D\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010C\u001a\u00020\u0001H\u0002\u001a,\u0010E\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020B0F2\u0006\u0010C\u001a\u00020\u00012\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u000105H\u0002\u001a$\u0010J\u001a\u0004\u0018\u00010B*\b\u0012\u0004\u0012\u00020B0F2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u0001H\u0002\u001a\u001c\u0010M\u001a\u0004\u0018\u00010B*\b\u0012\u0004\u0012\u00020B0F2\u0006\u0010C\u001a\u00020\u0001H\u0002\u001a\"\u0010N\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020B0F2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u0001H\u0002\u001a7\u0010O\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u000f0PH\u0082\b\u001a\f\u0010Q\u001a\u00020\u0001*\u00020\tH\u0002\u001a\f\u0010R\u001a\u00020\t*\u00020\u0001H\u0002\u001a\u001c\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001050A*\u00020T2\u0006\u0010)\u001a\u00020*H\u0002\u001a\u001c\u0010U\u001a\u00020\u0001*\u00020/2\u0006\u00103\u001a\u00020\u00012\u0006\u0010V\u001a\u00020\u0001H\u0002\u001a$\u0010W\u001a\u00020\u0001*\u00020/2\u0006\u0010X\u001a\u00020\u00012\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010Z\u001a\u00020\u0001H\u0002\u001a,\u0010\u0084\u0001\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\t2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a!\u0010\u0087\u0001\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\t2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0080\b\u001a\u0012\u0010\u0087\u0001\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\tH\u0080\b\u001a\u0012\u0010\u0084\u0001\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\tH\u0080\b\u001a\u0013\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u00020\u0011H\u0000\u001a\u0012\u0010\u008b\u0001\u001a\u00020\u000f2\u0007\u0010\u008a\u0001\u001a\u00020\u0011H\u0000\u001a6\u0010\u008f\u0001\u001a\u00030\u0090\u00012\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\u0007\u0010~\u001a\u00030\u0093\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u00022\u000e\u0010\u0095\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0096\u0001H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0017\"\u001a\u0010\u0018\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\"\u0018\u0010-\u001a\u00020\t*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u0018\u0010-\u001a\u00020\t*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u00100\"\u0018\u0010[\u001a\u000205*\u00020\\8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010^\"\u000e\u0010_\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010b\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bc\u0010\u0017\"\u001c\u0010d\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\be\u0010\u0017\u001a\u0004\bf\u0010g\"\u0016\u0010h\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bi\u0010\u0017\"\u001c\u0010j\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bk\u0010\u0017\u001a\u0004\bl\u0010g\"\u0016\u0010m\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bn\u0010\u0017\"\u001c\u0010o\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bp\u0010\u0017\u001a\u0004\bq\u0010g\"\u0016\u0010r\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bs\u0010\u0017\"\u001c\u0010t\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bu\u0010\u0017\u001a\u0004\bv\u0010g\"\u0016\u0010w\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bx\u0010\u0017\"\u001c\u0010y\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bz\u0010\u0017\u001a\u0004\b{\u0010g\"\u0016\u0010|\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b}\u0010\u0017\"\u001d\u0010~\u001a\u0002058\u0000X\u0081\u0004¢\u0006\u000f\n\u0000\u0012\u0004\b\u007f\u0010\u0017\u001a\u0005\b\u0080\u0001\u0010g\"\u0018\u0010\u0081\u0001\u001a\u00020\u00018\u0000X\u0081T¢\u0006\t\n\u0000\u0012\u0005\b\u0082\u0001\u0010\u0017\"\u000f\u0010\u0083\u0001\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0086\u0001\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000\"!\u0010\u008c\u0001\u001a\u0014\u0012\u0004\u0012\u00020B0\u008d\u0001j\t\u0012\u0004\u0012\u00020B`\u008e\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0097\u0001"}, d2 = {"nextGroup", "", "Landroidx/compose/runtime/SlotWriter;", "getNextGroup", "(Landroidx/compose/runtime/SlotWriter;)I", "cache", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/Composer;", "invalid", "", "block", "Lkotlin/Function0;", "Landroidx/compose/runtime/DisallowComposableCalls;", "(Landroidx/compose/runtime/Composer;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "sourceInformation", "", "composer", "", "sourceInformationMarkerStart", "key", "compositionTracer", "Landroidx/compose/runtime/CompositionTracer;", "getCompositionTracer$annotations", "()V", "composeStackTraceEnabled", "getComposeStackTraceEnabled", "()Z", "setComposeStackTraceEnabled", "(Z)V", "isTraceInProgress", "traceEventStart", "info", "dirty1", "dirty2", "traceEventEnd", "sourceInformationMarkerEnd", "removeCurrentGroup", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "withAfterAnchorInfo", "R", LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "cb", "Lkotlin/Function2;", "isAfterFirstChild", "(Landroidx/compose/runtime/SlotWriter;)Z", "Landroidx/compose/runtime/SlotReader;", "(Landroidx/compose/runtime/SlotReader;)Z", "deactivateCurrentGroup", "removeData", "index", "data", "", "multiMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "initialCapacity", "(I)Landroidx/collection/MutableScatterMap;", "getKey", ES6Iterator.VALUE_PROPERTY, TtmlNode.LEFT, TtmlNode.RIGHT, "findLocation", "", "Landroidx/compose/runtime/Invalidation;", "location", "findInsertLocation", "insertIfMissing", "", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "instance", "firstInRange", "start", TtmlNode.END, "removeLocation", "removeRange", "forEachInRange", "Lkotlin/Function1;", "asInt", "asBool", "collectNodesFrom", "Landroidx/compose/runtime/SlotTable;", "distanceFrom", "root", "nearestCommonRootOf", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "common", "joinedKey", "Landroidx/compose/runtime/KeyInfo;", "getJoinedKey", "(Landroidx/compose/runtime/KeyInfo;)Ljava/lang/Object;", "rootKey", "nodeKey", "defaultsKey", "invocationKey", "getInvocationKey$annotations", "invocation", "getInvocation$annotations", "getInvocation", "()Ljava/lang/Object;", "providerKey", "getProviderKey$annotations", "provider", "getProvider$annotations", "getProvider", "compositionLocalMapKey", "getCompositionLocalMapKey$annotations", "compositionLocalMap", "getCompositionLocalMap$annotations", "getCompositionLocalMap", "providerValuesKey", "getProviderValuesKey$annotations", "providerValues", "getProviderValues$annotations", "getProviderValues", "providerMapsKey", "getProviderMapsKey$annotations", "providerMaps", "getProviderMaps$annotations", "getProviderMaps", "referenceKey", "getReferenceKey$annotations", TypedValues.Custom.S_REFERENCE, "getReference$annotations", "getReference", "reuseKey", "getReuseKey$annotations", "invalidGroupLocation", "runtimeCheck", "lazyMessage", "EnableDebugRuntimeChecks", "debugRuntimeCheck", "composeRuntimeError", "", ContentType.Message.TYPE, "composeImmediateRuntimeError", "InvalidationLocationAscending", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "extractMovableContentAtCurrent", "Landroidx/compose/runtime/MovableContentState;", "composition", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/MovableContentStateReference;", "slots", "applier", "Landroidx/compose/runtime/Applier;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ComposerKt {
    public static final boolean EnableDebugRuntimeChecks = false;
    private static boolean composeStackTraceEnabled = false;
    public static final int compositionLocalMapKey = 202;
    private static CompositionTracer compositionTracer = null;
    public static final int defaultsKey = -127;
    private static final int invalidGroupLocation = -2;
    public static final int invocationKey = 200;
    private static final int nodeKey = 125;
    public static final int providerKey = 201;
    public static final int providerMapsKey = 204;
    public static final int providerValuesKey = 203;
    public static final int referenceKey = 206;
    public static final int reuseKey = 207;
    private static final int rootKey = 100;
    private static final Object invocation = new OpaqueKey("provider");
    private static final Object provider = new OpaqueKey("provider");
    private static final Object compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final Object providerValues = new OpaqueKey("providerValues");
    private static final Object providerMaps = new OpaqueKey(QuickSearchFragment.PROVIDER_KEY);
    private static final Object reference = new OpaqueKey(TypedValues.Custom.S_REFERENCE);
    private static final Comparator<Invalidation> InvalidationLocationAscending = new Comparator() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Intrinsics.compare(((Invalidation) obj).getLocation(), ((Invalidation) obj2).getLocation());
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int i) {
        return i != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    public static final void debugRuntimeCheck(boolean z) {
    }

    public static final void debugRuntimeCheck(boolean z, Function0<String> function0) {
    }

    public static /* synthetic */ void getCompositionLocalMap$annotations() {
    }

    public static /* synthetic */ void getCompositionLocalMapKey$annotations() {
    }

    private static /* synthetic */ void getCompositionTracer$annotations() {
    }

    public static /* synthetic */ void getInvocation$annotations() {
    }

    public static /* synthetic */ void getInvocationKey$annotations() {
    }

    public static /* synthetic */ void getProvider$annotations() {
    }

    public static /* synthetic */ void getProviderKey$annotations() {
    }

    public static /* synthetic */ void getProviderMaps$annotations() {
    }

    public static /* synthetic */ void getProviderMapsKey$annotations() {
    }

    public static /* synthetic */ void getProviderValues$annotations() {
    }

    public static /* synthetic */ void getProviderValuesKey$annotations() {
    }

    public static /* synthetic */ void getReference$annotations() {
    }

    public static /* synthetic */ void getReferenceKey$annotations() {
    }

    public static /* synthetic */ void getReuseKey$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getNextGroup(SlotWriter slotWriter) {
        return slotWriter.getCurrentGroup() + slotWriter.groupSize(slotWriter.getCurrentGroup());
    }

    @ComposeCompilerApi
    public static final <T> T cache(Composer composer, boolean z, Function0<? extends T> function0) {
        T t = (T) composer.rememberedValue();
        if (!z && t != Composer.INSTANCE.getEmpty()) {
            return t;
        }
        T tInvoke = function0.invoke();
        composer.updateRememberedValue(tInvoke);
        return tInvoke;
    }

    @ComposeCompilerApi
    public static final void sourceInformation(Composer composer, String str) {
        composer.sourceInformation(str);
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerStart(Composer composer, int i, String str) {
        composer.sourceInformationMarkerStart(i, str);
    }

    public static final boolean getComposeStackTraceEnabled() {
        return composeStackTraceEnabled;
    }

    public static final void setComposeStackTraceEnabled(boolean z) {
        composeStackTraceEnabled = z;
    }

    @ComposeCompilerApi
    public static final boolean isTraceInProgress() {
        CompositionTracer compositionTracer2 = compositionTracer;
        return compositionTracer2 != null && compositionTracer2.isTraceInProgress();
    }

    @ComposeCompilerApi
    public static final void traceEventStart(int i, int i2, int i3, String str) {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventStart(i, i2, i3, str);
        }
    }

    @ComposeCompilerApi
    public static final void traceEventEnd() {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventEnd();
        }
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerEnd(Composer composer) {
        composer.sourceInformationMarkerEnd();
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, final RememberManager rememberManager) {
        slotWriter.forAllDataInRememberOrder(slotWriter.getCurrentGroup(), new Function2() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ComposerKt.removeCurrentGroup$lambda$2(rememberManager, ((Integer) obj).intValue(), obj2);
            }
        });
        slotWriter.removeGroup();
    }

    static final Unit removeCurrentGroup$lambda$2(RememberManager rememberManager, int i, Object obj) {
        if (obj instanceof ComposeNodeLifecycleCallback) {
            rememberManager.releasing((ComposeNodeLifecycleCallback) obj);
        }
        if (obj instanceof RememberObserverHolder) {
            rememberManager.forgetting((RememberObserverHolder) obj);
        }
        if (obj instanceof RecomposeScopeImpl) {
            ((RecomposeScopeImpl) obj).release();
        }
        return Unit.INSTANCE;
    }

    public static final <R> void withAfterAnchorInfo(SlotWriter slotWriter, Anchor anchor, Function2<? super Integer, ? super Integer, ? extends R> function2) {
        int iAnchorIndex;
        int slotsSize;
        if (anchor == null || !anchor.getValid()) {
            iAnchorIndex = -1;
            slotsSize = -1;
        } else {
            iAnchorIndex = slotWriter.anchorIndex(anchor);
            slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime(iAnchorIndex);
        }
        function2.invoke(Integer.valueOf(iAnchorIndex), Integer.valueOf(slotsSize));
    }

    public static final boolean isAfterFirstChild(SlotWriter slotWriter) {
        return slotWriter.getCurrentGroup() > slotWriter.getParent() + 1;
    }

    public static final boolean isAfterFirstChild(SlotReader slotReader) {
        return slotReader.getCurrentGroup() > slotReader.getParent() + 1;
    }

    public static final void deactivateCurrentGroup(final SlotWriter slotWriter, final RememberManager rememberManager) {
        slotWriter.forAllDataInRememberOrder(slotWriter.getCurrentGroup(), new Function2() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ComposerKt.deactivateCurrentGroup$lambda$3(rememberManager, slotWriter, ((Integer) obj).intValue(), obj2);
            }
        });
    }

    static final Unit deactivateCurrentGroup$lambda$3(RememberManager rememberManager, SlotWriter slotWriter, int i, Object obj) {
        if (obj instanceof ComposeNodeLifecycleCallback) {
            rememberManager.deactivating((ComposeNodeLifecycleCallback) obj);
        } else if (obj instanceof RememberObserverHolder) {
            RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
            if (!(rememberObserverHolder.getWrapped() instanceof ReusableRememberObserver)) {
                removeData(slotWriter, i, obj);
                rememberManager.forgetting(rememberObserverHolder);
            }
        } else if (obj instanceof RecomposeScopeImpl) {
            removeData(slotWriter, i, obj);
            ((RecomposeScopeImpl) obj).release();
        }
        return Unit.INSTANCE;
    }

    private static final void removeData(SlotWriter slotWriter, int i, Object obj) {
        Object objClear = slotWriter.clear(i);
        if (obj == objClear) {
            return;
        }
        composeImmediateRuntimeError("Slot table is out of sync (expected " + obj + ", got " + objClear + ')');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> MutableScatterMap<Object, Object> multiMap(int i) {
        return MultiValueMap.m3532constructorimpl(new MutableScatterMap(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getKey(Object obj, Object obj2, Object obj3) {
        JoinedKey joinedKey = obj instanceof JoinedKey ? (JoinedKey) obj : null;
        if (joinedKey == null) {
            return null;
        }
        if (Intrinsics.areEqual(joinedKey.getLeft(), obj2) && Intrinsics.areEqual(joinedKey.getRight(), obj3)) {
            return obj;
        }
        Object key = getKey(joinedKey.getLeft(), obj2, obj3);
        return key == null ? getKey(joinedKey.getRight(), obj2, obj3) : key;
    }

    private static final int findLocation(List<Invalidation> list, int i) {
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int iCompare = Intrinsics.compare(list.get(i3).getLocation(), i);
            if (iCompare < 0) {
                i2 = i3 + 1;
            } else {
                if (iCompare <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int findInsertLocation(List<Invalidation> list, int i) {
        int iFindLocation = findLocation(list, i);
        return iFindLocation < 0 ? -(iFindLocation + 1) : iFindLocation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List<Invalidation> list, int i, RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation < 0) {
            int i2 = -(iFindLocation + 1);
            if (!(obj instanceof DerivedState)) {
                obj = null;
            }
            list.add(i2, new Invalidation(recomposeScopeImpl, i, obj));
            return;
        }
        Invalidation invalidation = list.get(iFindLocation);
        if (obj instanceof DerivedState) {
            Object instances = invalidation.getInstances();
            if (instances == null) {
                invalidation.setInstances(obj);
                return;
            } else if (instances instanceof MutableScatterSet) {
                ((MutableScatterSet) instances).add(obj);
                return;
            } else {
                invalidation.setInstances(ScatterSetKt.mutableScatterSetOf(instances, obj));
                return;
            }
        }
        invalidation.setInstances(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List<Invalidation> list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        if (iFindInsertLocation >= list.size()) {
            return null;
        }
        Invalidation invalidation = list.get(iFindInsertLocation);
        if (invalidation.getLocation() < i2) {
            return invalidation;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List<Invalidation> list, int i) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation >= 0) {
            return list.remove(iFindLocation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List<Invalidation> list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        while (iFindInsertLocation < list.size() && list.get(iFindInsertLocation).getLocation() < i2) {
            list.remove(iFindInsertLocation);
        }
    }

    private static final void forEachInRange(List<Invalidation> list, int i, int i2, Function1<? super Invalidation, Unit> function1) {
        for (int iFindInsertLocation = findInsertLocation(list, i); iFindInsertLocation < list.size(); iFindInsertLocation++) {
            Invalidation invalidation = list.get(iFindInsertLocation);
            if (invalidation.getLocation() >= i2) {
                return;
            }
            function1.invoke(invalidation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable slotTable, Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        SlotReader slotReaderOpenReader = slotTable.openReader();
        try {
            collectNodesFrom$lambda$8$collectFromGroup(slotReaderOpenReader, arrayList, slotTable.anchorIndex(anchor));
            Unit unit = Unit.INSTANCE;
            return arrayList;
        } finally {
            slotReaderOpenReader.close();
        }
    }

    private static final void collectNodesFrom$lambda$8$collectFromGroup(SlotReader slotReader, List<Object> list, int i) {
        if (slotReader.isNode(i)) {
            list.add(slotReader.node(i));
            return;
        }
        int iGroupSize = i + 1;
        int iGroupSize2 = i + slotReader.groupSize(i);
        while (iGroupSize < iGroupSize2) {
            collectNodesFrom$lambda$8$collectFromGroup(slotReader, list, iGroupSize);
            iGroupSize += slotReader.groupSize(iGroupSize);
        }
    }

    private static final int distanceFrom(SlotReader slotReader, int i, int i2) {
        int i3 = 0;
        while (i > 0 && i != i2) {
            i = slotReader.parent(i);
            i3++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader slotReader, int i, int i2, int i3) {
        if (i != i2) {
            if (i == i3 || i2 == i3) {
                return i3;
            }
            if (slotReader.parent(i) == i2) {
                return i2;
            }
            if (slotReader.parent(i2) != i) {
                if (slotReader.parent(i) == slotReader.parent(i2)) {
                    return slotReader.parent(i);
                }
                int iDistanceFrom = distanceFrom(slotReader, i, i3);
                int iDistanceFrom2 = distanceFrom(slotReader, i2, i3);
                int i4 = iDistanceFrom - iDistanceFrom2;
                for (int i5 = 0; i5 < i4; i5++) {
                    i = slotReader.parent(i);
                }
                int i6 = iDistanceFrom2 - iDistanceFrom;
                for (int i7 = 0; i7 < i6; i7++) {
                    i2 = slotReader.parent(i2);
                }
                while (i != i2) {
                    i = slotReader.parent(i);
                    i2 = slotReader.parent(i2);
                }
                return i;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo keyInfo) {
        return keyInfo.getObjectKey() != null ? new JoinedKey(Integer.valueOf(keyInfo.getKey()), keyInfo.getObjectKey()) : Integer.valueOf(keyInfo.getKey());
    }

    public static final Object getInvocation() {
        return invocation;
    }

    public static final Object getProvider() {
        return provider;
    }

    public static final Object getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static final Object getProviderValues() {
        return providerValues;
    }

    public static final Object getProviderMaps() {
        return providerMaps;
    }

    public static final Object getReference() {
        return reference;
    }

    public static final void runtimeCheck(boolean z, Function0<String> function0) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError(function0.invoke());
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }

    public static final Void composeRuntimeError(String str) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + str + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    public static final void composeImmediateRuntimeError(String str) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + str + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final MovableContentState extractMovableContentAtCurrent(final ControlledComposition controlledComposition, final MovableContentStateReference movableContentStateReference, SlotWriter slotWriter, Applier<?> applier) {
        SlotTable slotTable = new SlotTable();
        if (slotWriter.getCollectingSourceInformation()) {
            slotTable.collectSourceInformation();
        }
        if (slotWriter.getCollectingCalledInformation()) {
            slotTable.collectCalledByInformation();
        }
        int currentGroup = slotWriter.getCurrentGroup();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        Object[] objArr = 0;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        boolean z3 = true;
        boolean z4 = true;
        int i5 = 1;
        if (applier != null && slotWriter.nodeCount(currentGroup) > 0) {
            int parent = slotWriter.getParent();
            while (parent > 0 && !slotWriter.isNode(parent)) {
                parent = slotWriter.parent(parent);
            }
            if (parent >= 0 && slotWriter.isNode(parent)) {
                Object objNode = slotWriter.node(parent);
                int i6 = parent + 1;
                int iGroupSize = parent + slotWriter.groupSize(parent);
                int iNodeCount = i;
                while (i6 < iGroupSize) {
                    int iGroupSize2 = slotWriter.groupSize(i6) + i6;
                    if (iGroupSize2 > currentGroup) {
                        break;
                    }
                    iNodeCount += slotWriter.isNode(i6) ? i3 : slotWriter.nodeCount(i6);
                    i6 = iGroupSize2;
                }
                int iNodeCount2 = slotWriter.isNode(currentGroup) ? i4 : slotWriter.nodeCount(currentGroup);
                applier.down(objNode);
                applier.remove(iNodeCount, iNodeCount2);
                applier.up();
            }
        }
        SlotWriter slotWriterOpenWriter = slotTable.openWriter();
        try {
            slotWriterOpenWriter.beginInsert();
            slotWriterOpenWriter.startGroup(MovableContentKt.movableContentKey, movableContentStateReference.getContent$runtime());
            SlotWriter.markGroup$default(slotWriterOpenWriter, objArr == true ? 1 : 0, i2, null);
            slotWriterOpenWriter.update(movableContentStateReference.getParameter());
            List<Anchor> listMoveTo = slotWriter.moveTo(movableContentStateReference.getAnchor(), i5, slotWriterOpenWriter);
            slotWriterOpenWriter.skipGroup();
            slotWriterOpenWriter.endGroup();
            slotWriterOpenWriter.endInsert();
            slotWriterOpenWriter.close(z4);
            MovableContentState movableContentState = new MovableContentState(slotTable);
            if (!RecomposeScopeImpl.INSTANCE.hasAnchoredRecomposeScopes$runtime(slotTable, listMoveTo)) {
                return movableContentState;
            }
            RecomposeScopeOwner recomposeScopeOwner = new RecomposeScopeOwner() { // from class: androidx.compose.runtime.ComposerKt$extractMovableContentAtCurrent$movableContentRecomposeScopeOwner$1
                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public void recomposeScopeReleased(RecomposeScopeImpl scope) {
                }

                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public void recordReadOf(Object value) {
                }

                @Override // androidx.compose.runtime.RecomposeScopeOwner
                public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
                    InvalidationResult invalidationResultInvalidate;
                    ControlledComposition controlledComposition2 = controlledComposition;
                    RecomposeScopeOwner recomposeScopeOwner2 = controlledComposition2 instanceof RecomposeScopeOwner ? (RecomposeScopeOwner) controlledComposition2 : null;
                    if (recomposeScopeOwner2 == null || (invalidationResultInvalidate = recomposeScopeOwner2.invalidate(scope, instance)) == null) {
                        invalidationResultInvalidate = InvalidationResult.IGNORED;
                    }
                    if (invalidationResultInvalidate != InvalidationResult.IGNORED) {
                        return invalidationResultInvalidate;
                    }
                    MovableContentStateReference movableContentStateReference2 = movableContentStateReference;
                    movableContentStateReference2.setInvalidations$runtime(CollectionsKt.plus((Collection<? extends Pair>) movableContentStateReference2.getInvalidations$runtime(), TuplesKt.to(scope, instance)));
                    return InvalidationResult.SCHEDULED;
                }
            };
            SlotWriter slotWriterOpenWriter2 = slotTable.openWriter();
            try {
                RecomposeScopeImpl.INSTANCE.adoptAnchoredScopes$runtime(slotWriterOpenWriter2, listMoveTo, recomposeScopeOwner);
                Unit unit = Unit.INSTANCE;
                return movableContentState;
            } finally {
                slotWriterOpenWriter2.close(z2);
            }
        } catch (Throwable th) {
            slotWriterOpenWriter.close(z);
            throw th;
        }
    }
}
