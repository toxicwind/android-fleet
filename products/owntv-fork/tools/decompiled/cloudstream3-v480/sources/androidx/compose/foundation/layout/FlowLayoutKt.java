package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: FlowLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001ag\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001ag\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u001b2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u001d\u001a%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010!\u001a5\u0010\"\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010&\u001a\u0095\u0001\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00102\u001a[\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00106\u001aS\u00107\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0002\u001a\u0090\u0001\u00109\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002\u001a%\u0010:\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010;\u001a5\u0010<\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010=\u001a\\\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020J2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0000ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001a\u001c\u0010M\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010,\u001a\u00020\u000fH\u0000\u001a\u001c\u0010P\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010.\u001a\u00020\u000fH\u0000\u001a<\u0010Q\u001a\u00020(*\u00020E2\u0006\u0010A\u001a\u00020B2\u0006\u0010I\u001a\u00020R2\u0014\u0010S\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010T\u0012\u0004\u0012\u00020\u00070\u0014H\u0000ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001aT\u0010W\u001a\u00020?*\u00020@2\u0006\u0010I\u001a\u00020J2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010Y\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020?0[2\u0006\u0010\\\u001a\u00020B2\u0006\u0010]\u001a\u000204H\u0000ø\u0001\u0000¢\u0006\u0004\b^\u0010_\u001a\u001e\u0010`\u001a\u0004\u0018\u00010E*\b\u0012\u0004\u0012\u00020E0D2\b\u0010a\u001a\u0004\u0018\u00010bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006c"}, d2 = {"CROSS_AXIS_ALIGNMENT_START", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCROSS_AXIS_ALIGNMENT_START", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_TOP", "getCROSS_AXIS_ALIGNMENT_TOP", "FlowColumn", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "maxItemsInEachColumn", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowColumnOverflow;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowColumnOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FlowRow", "maxItemsInEachRow", "Landroidx/compose/foundation/layout/FlowRowOverflow;", "Landroidx/compose/foundation/layout/FlowRowScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowRowOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "columnMeasurementHelper", "Landroidx/compose/ui/layout/MeasurePolicy;", "maxItemsInMainAxis", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "columnMeasurementMultiContentHelper", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "intrinsicCrossAxisSize", "Landroidx/collection/IntIntPair;", "children", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "mainAxisSize", "Lkotlin/Function3;", "crossAxisSize", "mainAxisAvailable", "mainAxisSpacing", "crossAxisSpacing", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "mainAxisSizes", "", "crossAxisSizes", "(Ljava/util/List;[I[IIIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "maxIntrinsicMainAxisSize", "crossAxisAvailable", "minIntrinsicMainAxisSize", "rowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "rowMeasurementMultiContentHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "breakDownItems", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "measurablesIterator", "", "Landroidx/compose/ui/layout/Measurable;", "mainAxisSpacingDp", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacingDp", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "breakDownItems-di9J0FM", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Ljava/util/Iterator;FFJIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/ui/layout/MeasureResult;", "crossAxisMin", "isHorizontal", "", "mainAxisMin", "measureAndCache", "Landroidx/compose/ui/unit/Constraints;", "storePlaceable", "Landroidx/compose/ui/layout/Placeable;", "measureAndCache-rqJ1uqs", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function1;)J", "placeHelper", "mainAxisTotalSize", "crossAxisTotalSize", "items", "Landroidx/compose/runtime/collection/MutableVector;", "measureHelper", "outPosition", "placeHelper-BmaY500", "(Landroidx/compose/ui/layout/MeasureScope;JII[ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;[I)Landroidx/compose/ui/layout/MeasureResult;", "safeNext", "info", "Landroidx/compose/foundation/layout/FlowLineInfo;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlowLayoutKt {
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_TOP = CrossAxisAlignment.INSTANCE.vertical$foundation_layout_release(Alignment.INSTANCE.getTop());
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_START = CrossAxisAlignment.INSTANCE.horizontal$foundation_layout_release(Alignment.INSTANCE.getStart());

    /* JADX WARN: Code duplicated, block: B:100:0x0126  */
    /* JADX WARN: Code duplicated, block: B:103:0x012e  */
    /* JADX WARN: Code duplicated, block: B:106:0x0147  */
    /* JADX WARN: Code duplicated, block: B:107:0x0149  */
    /* JADX WARN: Code duplicated, block: B:110:0x0150  */
    /* JADX WARN: Code duplicated, block: B:112:0x0158  */
    /* JADX WARN: Code duplicated, block: B:115:0x017d  */
    /* JADX WARN: Code duplicated, block: B:116:0x017f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0187  */
    /* JADX WARN: Code duplicated, block: B:120:0x0189  */
    /* JADX WARN: Code duplicated, block: B:123:0x0191  */
    /* JADX WARN: Code duplicated, block: B:125:0x0199  */
    /* JADX WARN: Code duplicated, block: B:128:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:130:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:133:0x021a  */
    /* JADX WARN: Code duplicated, block: B:136:0x0226  */
    /* JADX WARN: Code duplicated, block: B:137:0x022a  */
    /* JADX WARN: Code duplicated, block: B:140:0x024f  */
    /* JADX WARN: Code duplicated, block: B:142:0x025d  */
    /* JADX WARN: Code duplicated, block: B:145:0x028e  */
    /* JADX WARN: Code duplicated, block: B:149:0x029f  */
    /* JADX WARN: Code duplicated, block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:38:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:43:0x007e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:49:0x008b  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0097  */
    /* JADX WARN: Code duplicated, block: B:54:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:88:0x0101  */
    /* JADX WARN: Code duplicated, block: B:89:0x0108  */
    /* JADX WARN: Code duplicated, block: B:91:0x010b  */
    /* JADX WARN: Code duplicated, block: B:94:0x0117  */
    /* JADX WARN: Code duplicated, block: B:96:0x011a  */
    /* JADX WARN: Code duplicated, block: B:97:0x011c  */
    /* JADX WARN: Code duplicated, block: B:99:0x011f  */
    public static final void FlowRow(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, int i2, FlowRowOverflow flowRowOverflow, final Function3<? super FlowRowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        Modifier modifier2;
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        Arrangement.Vertical top;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        Modifier.Companion companion;
        Arrangement.Horizontal start;
        int i17;
        FlowRowOverflow clip;
        int i18;
        boolean z;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        MultiContentMeasurePolicy multiContentMeasurePolicyRowMeasurementMultiContentHelper;
        boolean z2;
        boolean z3;
        boolean z4;
        Object objRememberedValue2;
        Object obj;
        boolean zChanged;
        Object objRememberedValue3;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3454constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        final FlowRowOverflow flowRowOverflow2;
        final Arrangement.Horizontal horizontal3;
        final Arrangement.Vertical vertical2;
        final int i19;
        final Modifier modifier3;
        final int i20;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(437853230);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(FlowRow)P(4,1,6,2,3,5)93@4089L65,96@4179L166,103@4391L261,110@4658L105:FlowLayout.kt#2w3rfo");
        int i21 = i4 & 1;
        if (i21 != 0) {
            i5 = i3 | 6;
            modifier2 = modifier;
        } else if ((i3 & 6) == 0) {
            modifier2 = modifier;
            i5 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i3;
        } else {
            modifier2 = modifier;
            i5 = i3;
        }
        int i22 = i4 & 2;
        if (i22 == 0) {
            if ((i3 & 48) == 0) {
                horizontal2 = horizontal;
                i5 |= composerStartRestartGroup.changed(horizontal2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    top = vertical;
                    if (composerStartRestartGroup.changed(top)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        i9 = i;
                        if (composerStartRestartGroup.changed(i9)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i5 |= i10;
                    }
                    i11 = i4 & 16;
                    if (i11 != 0) {
                        if ((i3 & 24576) == 0) {
                            i12 = i2;
                            if (composerStartRestartGroup.changed(i12)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i5 |= i13;
                        }
                        i14 = i4 & 32;
                        if (i14 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(flowRowOverflow)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i4 & 64) != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i16 = 1048576;
                            } else {
                                i16 = 524288;
                            }
                            i5 |= i16;
                        }
                        if ((i5 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            if (i21 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i22 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            }
                            if (i8 != 0) {
                                i9 = Integer.MAX_VALUE;
                            }
                            if (i11 != 0) {
                                i17 = Integer.MAX_VALUE;
                            } else {
                                i17 = i12;
                            }
                            if (i14 != 0) {
                                clip = FlowRowOverflow.INSTANCE.getClip();
                            } else {
                                clip = flowRowOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                            i18 = 458752 & i5;
                            if (i18 == 131072) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            Arrangement.Vertical vertical3 = top;
                            Arrangement.Horizontal horizontal4 = start;
                            multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal4, vertical3, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                            if (i18 == 131072) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if ((i5 & 3670016) == 1048576) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            z4 = z3 | z2;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z4 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i23) {
                                        ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                        }
                                        function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }));
                                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts((List) obj);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Modifier modifier4 = companion;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            flowRowOverflow2 = clip;
                            horizontal3 = horizontal4;
                            vertical2 = vertical3;
                            i19 = i17;
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            flowRowOverflow2 = flowRowOverflow;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            vertical2 = top;
                            i19 = i12;
                        }
                        i20 = i9;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i12 = i2;
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical4 = top;
                        Arrangement.Horizontal horizontal5 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal5, vertical4, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier5 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts2.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal5;
                        vertical2 = vertical4;
                        i19 = i17;
                        modifier3 = modifier5;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical5 = top;
                        Arrangement.Horizontal horizontal6 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal6, vertical5, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            arrayList4.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts3 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier6 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts3.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal6;
                        vertical2 = vertical5;
                        i19 = i17;
                        modifier3 = modifier6;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 3072;
                i9 = i;
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical6 = top;
                        Arrangement.Horizontal horizontal7 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal7, vertical6, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList6 = new ArrayList();
                            arrayList6.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList6);
                            composerStartRestartGroup.updateRememberedValue(arrayList6);
                            obj = arrayList6;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList7 = new ArrayList();
                            arrayList7.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList7);
                            composerStartRestartGroup.updateRememberedValue(arrayList7);
                            obj = arrayList7;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts4 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier7 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts4.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal7;
                        vertical2 = vertical6;
                        i19 = i17;
                        modifier3 = modifier7;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical7 = top;
                        Arrangement.Horizontal horizontal8 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal8, vertical7, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            arrayList8.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            arrayList9.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts5 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy5 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier8 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal8;
                        vertical2 = vertical7;
                        i19 = i17;
                        modifier3 = modifier8;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical8 = top;
                    Arrangement.Horizontal horizontal9 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal9, vertical8, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        arrayList10.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        arrayList11.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts6 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy6 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier9 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts6.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal9;
                    vertical2 = vertical8;
                    i19 = i17;
                    modifier3 = modifier9;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical9 = top;
                    Arrangement.Horizontal horizontal10 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal10, vertical9, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        arrayList12.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        arrayList13.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts7 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy7 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier10 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts7.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal10;
                    vertical2 = vertical9;
                    i19 = i17;
                    modifier3 = modifier10;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            top = vertical;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    i9 = i;
                    if (composerStartRestartGroup.changed(i9)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i5 |= i10;
                }
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical10 = top;
                        Arrangement.Horizontal horizontal11 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal11, vertical10, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList14 = new ArrayList();
                            arrayList14.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList14);
                            composerStartRestartGroup.updateRememberedValue(arrayList14);
                            obj = arrayList14;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList15 = new ArrayList();
                            arrayList15.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList15);
                            composerStartRestartGroup.updateRememberedValue(arrayList15);
                            obj = arrayList15;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts8 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy8 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier11 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts8.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal11;
                        vertical2 = vertical10;
                        i19 = i17;
                        modifier3 = modifier11;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical11 = top;
                        Arrangement.Horizontal horizontal12 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal12, vertical11, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            arrayList16.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            arrayList17.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts9 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy9 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier12 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts9.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal12;
                        vertical2 = vertical11;
                        i19 = i17;
                        modifier3 = modifier12;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical12 = top;
                    Arrangement.Horizontal horizontal13 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal13, vertical12, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        arrayList18.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        arrayList19.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts10 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy10 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier13 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts10.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal13;
                    vertical2 = vertical12;
                    i19 = i17;
                    modifier3 = modifier13;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical13 = top;
                    Arrangement.Horizontal horizontal14 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal14, vertical13, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        arrayList110.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        arrayList111.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts11 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy11 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier14 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts11.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal14;
                    vertical2 = vertical13;
                    i19 = i17;
                    modifier3 = modifier14;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            i9 = i;
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical14 = top;
                    Arrangement.Horizontal horizontal15 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal15, vertical14, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList112 = new ArrayList();
                        arrayList112.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList112);
                        composerStartRestartGroup.updateRememberedValue(arrayList112);
                        obj = arrayList112;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList113 = new ArrayList();
                        arrayList113.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList113);
                        composerStartRestartGroup.updateRememberedValue(arrayList113);
                        obj = arrayList113;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts12 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy12 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier15 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts12.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal15;
                    vertical2 = vertical14;
                    i19 = i17;
                    modifier3 = modifier15;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical15 = top;
                    Arrangement.Horizontal horizontal16 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal16, vertical15, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        arrayList114.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        arrayList115.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts13 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy13 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier16 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts13.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal16;
                    vertical2 = vertical15;
                    i19 = i17;
                    modifier3 = modifier16;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical16 = top;
                Arrangement.Horizontal horizontal17 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal17, vertical16, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    arrayList116.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    arrayList117.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts14 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy14 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier17 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts14.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal17;
                vertical2 = vertical16;
                i19 = i17;
                modifier3 = modifier17;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical17 = top;
                Arrangement.Horizontal horizontal18 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal18, vertical17, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    arrayList118.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    arrayList119.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts15 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy15 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier18 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts15.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal18;
                vertical2 = vertical17;
                i19 = i17;
                modifier3 = modifier18;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 48;
        horizontal2 = horizontal;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                top = vertical;
                if (composerStartRestartGroup.changed(top)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    i9 = i;
                    if (composerStartRestartGroup.changed(i9)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i5 |= i10;
                }
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical18 = top;
                        Arrangement.Horizontal horizontal19 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal19, vertical18, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1110 = new ArrayList();
                            arrayList1110.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1110);
                            composerStartRestartGroup.updateRememberedValue(arrayList1110);
                            obj = arrayList1110;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1111 = new ArrayList();
                            arrayList1111.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111);
                            composerStartRestartGroup.updateRememberedValue(arrayList1111);
                            obj = arrayList1111;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts16 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy16 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier19 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy16, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts16.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal19;
                        vertical2 = vertical18;
                        i19 = i17;
                        modifier3 = modifier19;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Vertical vertical19 = top;
                        Arrangement.Horizontal horizontal110 = start;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal110, vertical19, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1112 = new ArrayList();
                            arrayList1112.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1112);
                            composerStartRestartGroup.updateRememberedValue(arrayList1112);
                            obj = arrayList1112;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1113 = new ArrayList();
                            arrayList1113.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                    }
                                    function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1113);
                            composerStartRestartGroup.updateRememberedValue(arrayList1113);
                            obj = arrayList1113;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts17 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy17 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier110 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy17, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts17.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowRowOverflow2 = clip;
                        horizontal3 = horizontal110;
                        vertical2 = vertical19;
                        i19 = i17;
                        modifier3 = modifier110;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical110 = top;
                    Arrangement.Horizontal horizontal111 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal111, vertical110, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1114 = new ArrayList();
                        arrayList1114.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1114);
                        composerStartRestartGroup.updateRememberedValue(arrayList1114);
                        obj = arrayList1114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1115 = new ArrayList();
                        arrayList1115.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1115);
                        composerStartRestartGroup.updateRememberedValue(arrayList1115);
                        obj = arrayList1115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts18 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy18 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier111 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy18, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts18.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal111;
                    vertical2 = vertical110;
                    i19 = i17;
                    modifier3 = modifier111;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical111 = top;
                    Arrangement.Horizontal horizontal112 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal112, vertical111, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1116 = new ArrayList();
                        arrayList1116.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1116);
                        composerStartRestartGroup.updateRememberedValue(arrayList1116);
                        obj = arrayList1116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1117 = new ArrayList();
                        arrayList1117.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1117);
                        composerStartRestartGroup.updateRememberedValue(arrayList1117);
                        obj = arrayList1117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts19 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy19 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier112 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy19, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts19.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal112;
                    vertical2 = vertical111;
                    i19 = i17;
                    modifier3 = modifier112;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            i9 = i;
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical112 = top;
                    Arrangement.Horizontal horizontal113 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal113, vertical112, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1118 = new ArrayList();
                        arrayList1118.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1118);
                        composerStartRestartGroup.updateRememberedValue(arrayList1118);
                        obj = arrayList1118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1119 = new ArrayList();
                        arrayList1119.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1119);
                        composerStartRestartGroup.updateRememberedValue(arrayList1119);
                        obj = arrayList1119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts110 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy110 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier113 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier110, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts110.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal113;
                    vertical2 = vertical112;
                    i19 = i17;
                    modifier3 = modifier113;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical113 = top;
                    Arrangement.Horizontal horizontal114 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal114, vertical113, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11110 = new ArrayList();
                        arrayList11110.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11110);
                        composerStartRestartGroup.updateRememberedValue(arrayList11110);
                        obj = arrayList11110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11111 = new ArrayList();
                        arrayList11111.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11111);
                        composerStartRestartGroup.updateRememberedValue(arrayList11111);
                        obj = arrayList11111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts111 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy111 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier114 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier111, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts111.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal114;
                    vertical2 = vertical113;
                    i19 = i17;
                    modifier3 = modifier114;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical114 = top;
                Arrangement.Horizontal horizontal115 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal115, vertical114, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11112 = new ArrayList();
                    arrayList11112.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11112);
                    composerStartRestartGroup.updateRememberedValue(arrayList11112);
                    obj = arrayList11112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11113 = new ArrayList();
                    arrayList11113.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11113);
                    composerStartRestartGroup.updateRememberedValue(arrayList11113);
                    obj = arrayList11113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts112 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy112 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier115 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier112, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts112.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal115;
                vertical2 = vertical114;
                i19 = i17;
                modifier3 = modifier115;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical115 = top;
                Arrangement.Horizontal horizontal116 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal116, vertical115, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11114 = new ArrayList();
                    arrayList11114.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11114);
                    composerStartRestartGroup.updateRememberedValue(arrayList11114);
                    obj = arrayList11114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11115 = new ArrayList();
                    arrayList11115.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11115);
                    composerStartRestartGroup.updateRememberedValue(arrayList11115);
                    obj = arrayList11115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts113 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy113 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier116 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier113, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts113.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal116;
                vertical2 = vertical115;
                i19 = i17;
                modifier3 = modifier116;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        top = vertical;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                i9 = i;
                if (composerStartRestartGroup.changed(i9)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i5 |= i10;
            }
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical116 = top;
                    Arrangement.Horizontal horizontal117 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal117, vertical116, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11116 = new ArrayList();
                        arrayList11116.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11116);
                        composerStartRestartGroup.updateRememberedValue(arrayList11116);
                        obj = arrayList11116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11117 = new ArrayList();
                        arrayList11117.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11117);
                        composerStartRestartGroup.updateRememberedValue(arrayList11117);
                        obj = arrayList11117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts114 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy114 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap114 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier114 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier117 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier114, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts114.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal117;
                    vertical2 = vertical116;
                    i19 = i17;
                    modifier3 = modifier117;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Vertical vertical117 = top;
                    Arrangement.Horizontal horizontal118 = start;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal118, vertical117, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11118 = new ArrayList();
                        arrayList11118.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11118);
                        composerStartRestartGroup.updateRememberedValue(arrayList11118);
                        obj = arrayList11118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11119 = new ArrayList();
                        arrayList11119.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                                }
                                function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11119);
                        composerStartRestartGroup.updateRememberedValue(arrayList11119);
                        obj = arrayList11119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts115 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy115 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap115 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier115 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier118 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier115, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts115.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowRowOverflow2 = clip;
                    horizontal3 = horizontal118;
                    vertical2 = vertical117;
                    i19 = i17;
                    modifier3 = modifier118;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical118 = top;
                Arrangement.Horizontal horizontal119 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal119, vertical118, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111110 = new ArrayList();
                    arrayList111110.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111110);
                    composerStartRestartGroup.updateRememberedValue(arrayList111110);
                    obj = arrayList111110;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111111 = new ArrayList();
                    arrayList111111.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111111);
                    composerStartRestartGroup.updateRememberedValue(arrayList111111);
                    obj = arrayList111111;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts116 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy116 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap116 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier116 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier119 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier116, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts116.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal119;
                vertical2 = vertical118;
                i19 = i17;
                modifier3 = modifier119;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical119 = top;
                Arrangement.Horizontal horizontal1110 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal1110, vertical119, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111112 = new ArrayList();
                    arrayList111112.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111112);
                    composerStartRestartGroup.updateRememberedValue(arrayList111112);
                    obj = arrayList111112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111113 = new ArrayList();
                    arrayList111113.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111113);
                    composerStartRestartGroup.updateRememberedValue(arrayList111113);
                    obj = arrayList111113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts117 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy117 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap117 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier117 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1110 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier117, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts117.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal1110;
                vertical2 = vertical119;
                i19 = i17;
                modifier3 = modifier1110;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 3072;
        i9 = i;
        i11 = i4 & 16;
        if (i11 != 0) {
            if ((i3 & 24576) == 0) {
                i12 = i2;
                if (composerStartRestartGroup.changed(i12)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i5 |= i13;
            }
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical1110 = top;
                Arrangement.Horizontal horizontal1111 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal1111, vertical1110, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111114 = new ArrayList();
                    arrayList111114.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111114);
                    composerStartRestartGroup.updateRememberedValue(arrayList111114);
                    obj = arrayList111114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111115 = new ArrayList();
                    arrayList111115.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111115);
                    composerStartRestartGroup.updateRememberedValue(arrayList111115);
                    obj = arrayList111115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts118 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy118 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap118 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier118 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1111 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier118, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts118.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal1111;
                vertical2 = vertical1110;
                i19 = i17;
                modifier3 = modifier1111;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Vertical vertical1111 = top;
                Arrangement.Horizontal horizontal1112 = start;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal1112, vertical1111, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111116 = new ArrayList();
                    arrayList111116.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111116);
                    composerStartRestartGroup.updateRememberedValue(arrayList111116);
                    obj = arrayList111116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111117 = new ArrayList();
                    arrayList111117.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                            }
                            function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111117);
                    composerStartRestartGroup.updateRememberedValue(arrayList111117);
                    obj = arrayList111117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts119 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy119 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap119 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier119 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1112 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier119, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts119.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowRowOverflow2 = clip;
                horizontal3 = horizontal1112;
                vertical2 = vertical1111;
                i19 = i17;
                modifier3 = modifier1112;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 24576;
        i12 = i2;
        i14 = i4 & 32;
        if (i14 != 0) {
            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(flowRowOverflow)) {
                i15 = 131072;
            } else {
                i15 = 65536;
            }
            i5 |= i15;
        }
        if ((i4 & 64) != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i16 = 1048576;
            } else {
                i16 = 524288;
            }
            i5 |= i16;
        }
        if ((i5 & 599187) == 599186) {
            if (i21 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i22 != 0) {
                start = Arrangement.INSTANCE.getStart();
            } else {
                start = horizontal2;
            }
            if (i6 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i8 != 0) {
                i9 = Integer.MAX_VALUE;
            }
            if (i11 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i12;
            }
            if (i14 != 0) {
                clip = FlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = flowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
            i18 = 458752 & i5;
            if (i18 == 131072) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.Vertical vertical1112 = top;
            Arrangement.Horizontal horizontal1113 = start;
            multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal1113, vertical1112, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
            if (i18 == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i5 & 3670016) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            z4 = z3 | z2;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                obj = objRememberedValue2;
                ArrayList arrayList111118 = new ArrayList();
                arrayList111118.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                        }
                        function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111118);
                composerStartRestartGroup.updateRememberedValue(arrayList111118);
                obj = arrayList111118;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList111119 = new ArrayList();
                arrayList111119.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                        }
                        function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111119);
                composerStartRestartGroup.updateRememberedValue(arrayList111119);
                obj = arrayList111119;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts1110 = LayoutKt.combineAsVirtualLayouts((List) obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy1110 = (MeasurePolicy) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier1113 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy1110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap1110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier1110, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts1110.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            flowRowOverflow2 = clip;
            horizontal3 = horizontal1113;
            vertical2 = vertical1112;
            i19 = i17;
            modifier3 = modifier1113;
        } else {
            if (i21 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i22 != 0) {
                start = Arrangement.INSTANCE.getStart();
            } else {
                start = horizontal2;
            }
            if (i6 != 0) {
                top = Arrangement.INSTANCE.getTop();
            }
            if (i8 != 0) {
                i9 = Integer.MAX_VALUE;
            }
            if (i11 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i12;
            }
            if (i14 != 0) {
                clip = FlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = flowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(437853230, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:92)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454955587, "CC(remember):FlowLayout.kt#9igjgp");
            i18 = 458752 & i5;
            if (i18 == 131072) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.Vertical vertical1113 = top;
            Arrangement.Horizontal horizontal1114 = start;
            multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(horizontal1114, vertical1113, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 454965447, "CC(remember):FlowLayout.kt#9igjgp");
            if (i18 == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i5 & 3670016) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            z4 = z3 | z2;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                obj = objRememberedValue2;
                ArrayList arrayList1111110 = new ArrayList();
                arrayList1111110.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                        }
                        function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111110);
                composerStartRestartGroup.updateRememberedValue(arrayList1111110);
                obj = arrayList1111110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111111 = new ArrayList();
                arrayList1111111.add(ComposableLambdaKt.composableLambdaInstance(-139531073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowRow$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C105@4547L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-139531073, i23, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:105)");
                        }
                        function3.invoke(FlowRowScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111111);
                obj = arrayList1111111;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts1111 = LayoutKt.combineAsVirtualLayouts((List) obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy1111 = (MeasurePolicy) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier1114 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy1111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap1111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier1111, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts1111.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            flowRowOverflow2 = clip;
            horizontal3 = horizontal1114;
            vertical2 = vertical1113;
            i19 = i17;
            modifier3 = modifier1114;
        }
        i20 = i9;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i23) {
                    FlowLayoutKt.FlowRow(modifier3, horizontal3, vertical2, i20, i19, flowRowOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0126  */
    /* JADX WARN: Code duplicated, block: B:103:0x012e  */
    /* JADX WARN: Code duplicated, block: B:106:0x0147  */
    /* JADX WARN: Code duplicated, block: B:107:0x0149  */
    /* JADX WARN: Code duplicated, block: B:110:0x0150  */
    /* JADX WARN: Code duplicated, block: B:112:0x0158  */
    /* JADX WARN: Code duplicated, block: B:115:0x017d  */
    /* JADX WARN: Code duplicated, block: B:116:0x017f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0187  */
    /* JADX WARN: Code duplicated, block: B:120:0x0189  */
    /* JADX WARN: Code duplicated, block: B:123:0x0191  */
    /* JADX WARN: Code duplicated, block: B:125:0x0199  */
    /* JADX WARN: Code duplicated, block: B:128:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:130:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:133:0x021a  */
    /* JADX WARN: Code duplicated, block: B:136:0x0226  */
    /* JADX WARN: Code duplicated, block: B:137:0x022a  */
    /* JADX WARN: Code duplicated, block: B:140:0x024f  */
    /* JADX WARN: Code duplicated, block: B:142:0x025d  */
    /* JADX WARN: Code duplicated, block: B:145:0x028e  */
    /* JADX WARN: Code duplicated, block: B:149:0x029f  */
    /* JADX WARN: Code duplicated, block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:38:0x006f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:43:0x007e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:49:0x008b  */
    /* JADX WARN: Code duplicated, block: B:51:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0097  */
    /* JADX WARN: Code duplicated, block: B:54:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:88:0x0101  */
    /* JADX WARN: Code duplicated, block: B:89:0x0108  */
    /* JADX WARN: Code duplicated, block: B:91:0x010b  */
    /* JADX WARN: Code duplicated, block: B:94:0x0117  */
    /* JADX WARN: Code duplicated, block: B:96:0x011a  */
    /* JADX WARN: Code duplicated, block: B:97:0x011c  */
    /* JADX WARN: Code duplicated, block: B:99:0x011f  */
    public static final void FlowColumn(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, int i2, FlowColumnOverflow flowColumnOverflow, final Function3<? super FlowColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        Modifier modifier2;
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        Arrangement.Horizontal start;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        Modifier.Companion companion;
        Arrangement.Vertical top;
        int i17;
        FlowColumnOverflow clip;
        int i18;
        boolean z;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        MultiContentMeasurePolicy multiContentMeasurePolicyColumnMeasurementMultiContentHelper;
        boolean z2;
        boolean z3;
        boolean z4;
        Object objRememberedValue2;
        Object obj;
        boolean zChanged;
        Object objRememberedValue3;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3454constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        final FlowColumnOverflow flowColumnOverflow2;
        final Arrangement.Vertical vertical3;
        final Arrangement.Horizontal horizontal2;
        final int i19;
        final Modifier modifier3;
        final int i20;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1704849416);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(FlowColumn)P(4,6,1,2,3,5)157@6483L65,160@6573L172,167@6791L264,173@7060L105:FlowLayout.kt#2w3rfo");
        int i21 = i4 & 1;
        if (i21 != 0) {
            i5 = i3 | 6;
            modifier2 = modifier;
        } else if ((i3 & 6) == 0) {
            modifier2 = modifier;
            i5 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i3;
        } else {
            modifier2 = modifier;
            i5 = i3;
        }
        int i22 = i4 & 2;
        if (i22 == 0) {
            if ((i3 & 48) == 0) {
                vertical2 = vertical;
                i5 |= composerStartRestartGroup.changed(vertical2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    start = horizontal;
                    if (composerStartRestartGroup.changed(start)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        i9 = i;
                        if (composerStartRestartGroup.changed(i9)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i5 |= i10;
                    }
                    i11 = i4 & 16;
                    if (i11 != 0) {
                        if ((i3 & 24576) == 0) {
                            i12 = i2;
                            if (composerStartRestartGroup.changed(i12)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i5 |= i13;
                        }
                        i14 = i4 & 32;
                        if (i14 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i4 & 64) != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i16 = 1048576;
                            } else {
                                i16 = 524288;
                            }
                            i5 |= i16;
                        }
                        if ((i5 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            if (i21 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i22 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            }
                            if (i8 != 0) {
                                i9 = Integer.MAX_VALUE;
                            }
                            if (i11 != 0) {
                                i17 = Integer.MAX_VALUE;
                            } else {
                                i17 = i12;
                            }
                            if (i14 != 0) {
                                clip = FlowColumnOverflow.INSTANCE.getClip();
                            } else {
                                clip = flowColumnOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                            i18 = 458752 & i5;
                            if (i18 == 131072) {
                                z = true;
                            } else {
                                z = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            Arrangement.Horizontal horizontal3 = start;
                            Arrangement.Vertical vertical4 = top;
                            multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical4, horizontal3, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                            if (i18 == 131072) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if ((i5 & 3670016) == 1048576) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            z4 = z3 | z2;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z4 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i23) {
                                        ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                        }
                                        function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }));
                                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts((List) obj);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (!zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Modifier modifier4 = companion;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            flowColumnOverflow2 = clip;
                            vertical3 = vertical4;
                            horizontal2 = horizontal3;
                            i19 = i17;
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            flowColumnOverflow2 = flowColumnOverflow;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            horizontal2 = start;
                            i19 = i12;
                        }
                        i20 = i9;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i12 = i2;
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal4 = start;
                        Arrangement.Vertical vertical5 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical5, horizontal4, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier5 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts2.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical5;
                        horizontal2 = horizontal4;
                        i19 = i17;
                        modifier3 = modifier5;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal5 = start;
                        Arrangement.Vertical vertical6 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical6, horizontal5, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            arrayList4.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts3 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier6 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts3.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical6;
                        horizontal2 = horizontal5;
                        i19 = i17;
                        modifier3 = modifier6;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 3072;
                i9 = i;
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal6 = start;
                        Arrangement.Vertical vertical7 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical7, horizontal6, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList6 = new ArrayList();
                            arrayList6.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList6);
                            composerStartRestartGroup.updateRememberedValue(arrayList6);
                            obj = arrayList6;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList7 = new ArrayList();
                            arrayList7.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList7);
                            composerStartRestartGroup.updateRememberedValue(arrayList7);
                            obj = arrayList7;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts4 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier7 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts4.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical7;
                        horizontal2 = horizontal6;
                        i19 = i17;
                        modifier3 = modifier7;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal7 = start;
                        Arrangement.Vertical vertical8 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical8, horizontal7, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            arrayList8.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            arrayList9.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts5 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy5 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier8 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical8;
                        horizontal2 = horizontal7;
                        i19 = i17;
                        modifier3 = modifier8;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal8 = start;
                    Arrangement.Vertical vertical9 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical9, horizontal8, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        arrayList10.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        arrayList11.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts6 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy6 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier9 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts6.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical9;
                    horizontal2 = horizontal8;
                    i19 = i17;
                    modifier3 = modifier9;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal9 = start;
                    Arrangement.Vertical vertical10 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical10, horizontal9, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        arrayList12.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        arrayList13.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts7 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy7 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier10 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts7.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical10;
                    horizontal2 = horizontal9;
                    i19 = i17;
                    modifier3 = modifier10;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            start = horizontal;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    i9 = i;
                    if (composerStartRestartGroup.changed(i9)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i5 |= i10;
                }
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal10 = start;
                        Arrangement.Vertical vertical11 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical11, horizontal10, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList14 = new ArrayList();
                            arrayList14.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList14);
                            composerStartRestartGroup.updateRememberedValue(arrayList14);
                            obj = arrayList14;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList15 = new ArrayList();
                            arrayList15.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList15);
                            composerStartRestartGroup.updateRememberedValue(arrayList15);
                            obj = arrayList15;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts8 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy8 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier11 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts8.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical11;
                        horizontal2 = horizontal10;
                        i19 = i17;
                        modifier3 = modifier11;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal11 = start;
                        Arrangement.Vertical vertical12 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical12, horizontal11, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            arrayList16.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            arrayList17.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts9 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy9 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier12 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts9.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical12;
                        horizontal2 = horizontal11;
                        i19 = i17;
                        modifier3 = modifier12;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal12 = start;
                    Arrangement.Vertical vertical13 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical13, horizontal12, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        arrayList18.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        arrayList19.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts10 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy10 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier13 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts10.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical13;
                    horizontal2 = horizontal12;
                    i19 = i17;
                    modifier3 = modifier13;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal13 = start;
                    Arrangement.Vertical vertical14 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical14, horizontal13, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        arrayList110.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        arrayList111.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts11 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy11 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier14 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts11.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical14;
                    horizontal2 = horizontal13;
                    i19 = i17;
                    modifier3 = modifier14;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            i9 = i;
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal14 = start;
                    Arrangement.Vertical vertical15 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical15, horizontal14, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList112 = new ArrayList();
                        arrayList112.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList112);
                        composerStartRestartGroup.updateRememberedValue(arrayList112);
                        obj = arrayList112;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList113 = new ArrayList();
                        arrayList113.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList113);
                        composerStartRestartGroup.updateRememberedValue(arrayList113);
                        obj = arrayList113;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts12 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy12 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier15 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts12.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical15;
                    horizontal2 = horizontal14;
                    i19 = i17;
                    modifier3 = modifier15;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal15 = start;
                    Arrangement.Vertical vertical16 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical16, horizontal15, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        arrayList114.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        arrayList115.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts13 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy13 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier16 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts13.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical16;
                    horizontal2 = horizontal15;
                    i19 = i17;
                    modifier3 = modifier16;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal16 = start;
                Arrangement.Vertical vertical17 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical17, horizontal16, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    arrayList116.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    arrayList117.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts14 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy14 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier17 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts14.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical17;
                horizontal2 = horizontal16;
                i19 = i17;
                modifier3 = modifier17;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal17 = start;
                Arrangement.Vertical vertical18 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical18, horizontal17, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    arrayList118.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    arrayList119.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts15 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy15 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier18 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts15.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical18;
                horizontal2 = horizontal17;
                i19 = i17;
                modifier3 = modifier18;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 48;
        vertical2 = vertical;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                start = horizontal;
                if (composerStartRestartGroup.changed(start)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    i9 = i;
                    if (composerStartRestartGroup.changed(i9)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i5 |= i10;
                }
                i11 = i4 & 16;
                if (i11 != 0) {
                    if ((i3 & 24576) == 0) {
                        i12 = i2;
                        if (composerStartRestartGroup.changed(i12)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 32;
                    if (i14 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 64) != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i16 = 1048576;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 599187) == 599186) {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal18 = start;
                        Arrangement.Vertical vertical19 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical19, horizontal18, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1110 = new ArrayList();
                            arrayList1110.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1110);
                            composerStartRestartGroup.updateRememberedValue(arrayList1110);
                            obj = arrayList1110;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1111 = new ArrayList();
                            arrayList1111.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111);
                            composerStartRestartGroup.updateRememberedValue(arrayList1111);
                            obj = arrayList1111;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts16 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy16 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier19 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy16, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts16.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical19;
                        horizontal2 = horizontal18;
                        i19 = i17;
                        modifier3 = modifier19;
                    } else {
                        if (i21 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i22 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        }
                        if (i8 != 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i12;
                        }
                        if (i14 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                        i18 = 458752 & i5;
                        if (i18 == 131072) {
                            z = true;
                        } else {
                            z = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z) {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout_release();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Arrangement.Horizontal horizontal19 = start;
                        Arrangement.Vertical vertical110 = top;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical110, horizontal19, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                        if (i18 == 131072) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if ((i5 & 3670016) == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z4 = z3 | z2;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            obj = objRememberedValue2;
                            ArrayList arrayList1112 = new ArrayList();
                            arrayList1112.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1112);
                            composerStartRestartGroup.updateRememberedValue(arrayList1112);
                            obj = arrayList1112;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList1113 = new ArrayList();
                            arrayList1113.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i23) {
                                    ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                    if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                    }
                                    function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1113);
                            composerStartRestartGroup.updateRememberedValue(arrayList1113);
                            obj = arrayList1113;
                        }
                        obj = objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts17 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy17 = (MeasurePolicy) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Modifier modifier110 = companion;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy17, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3454constructorimpl.getInserting()) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts17.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        flowColumnOverflow2 = clip;
                        vertical3 = vertical110;
                        horizontal2 = horizontal19;
                        i19 = i17;
                        modifier3 = modifier110;
                    }
                    i20 = i9;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                i12 = i2;
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal110 = start;
                    Arrangement.Vertical vertical111 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical111, horizontal110, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1114 = new ArrayList();
                        arrayList1114.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1114);
                        composerStartRestartGroup.updateRememberedValue(arrayList1114);
                        obj = arrayList1114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1115 = new ArrayList();
                        arrayList1115.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1115);
                        composerStartRestartGroup.updateRememberedValue(arrayList1115);
                        obj = arrayList1115;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts18 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy18 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier111 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy18, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts18.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical111;
                    horizontal2 = horizontal110;
                    i19 = i17;
                    modifier3 = modifier111;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal111 = start;
                    Arrangement.Vertical vertical112 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical112, horizontal111, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1116 = new ArrayList();
                        arrayList1116.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1116);
                        composerStartRestartGroup.updateRememberedValue(arrayList1116);
                        obj = arrayList1116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1117 = new ArrayList();
                        arrayList1117.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1117);
                        composerStartRestartGroup.updateRememberedValue(arrayList1117);
                        obj = arrayList1117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts19 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy19 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier112 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy19, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts19.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical112;
                    horizontal2 = horizontal111;
                    i19 = i17;
                    modifier3 = modifier112;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            i9 = i;
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal112 = start;
                    Arrangement.Vertical vertical113 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical113, horizontal112, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList1118 = new ArrayList();
                        arrayList1118.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1118);
                        composerStartRestartGroup.updateRememberedValue(arrayList1118);
                        obj = arrayList1118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList1119 = new ArrayList();
                        arrayList1119.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1119);
                        composerStartRestartGroup.updateRememberedValue(arrayList1119);
                        obj = arrayList1119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts110 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy110 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier113 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier110, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts110.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical113;
                    horizontal2 = horizontal112;
                    i19 = i17;
                    modifier3 = modifier113;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal113 = start;
                    Arrangement.Vertical vertical114 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical114, horizontal113, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11110 = new ArrayList();
                        arrayList11110.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11110);
                        composerStartRestartGroup.updateRememberedValue(arrayList11110);
                        obj = arrayList11110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11111 = new ArrayList();
                        arrayList11111.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11111);
                        composerStartRestartGroup.updateRememberedValue(arrayList11111);
                        obj = arrayList11111;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts111 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy111 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier114 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier111, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts111.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical114;
                    horizontal2 = horizontal113;
                    i19 = i17;
                    modifier3 = modifier114;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal114 = start;
                Arrangement.Vertical vertical115 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical115, horizontal114, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11112 = new ArrayList();
                    arrayList11112.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11112);
                    composerStartRestartGroup.updateRememberedValue(arrayList11112);
                    obj = arrayList11112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11113 = new ArrayList();
                    arrayList11113.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11113);
                    composerStartRestartGroup.updateRememberedValue(arrayList11113);
                    obj = arrayList11113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts112 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy112 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier115 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier112, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts112.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical115;
                horizontal2 = horizontal114;
                i19 = i17;
                modifier3 = modifier115;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal115 = start;
                Arrangement.Vertical vertical116 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical116, horizontal115, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList11114 = new ArrayList();
                    arrayList11114.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11114);
                    composerStartRestartGroup.updateRememberedValue(arrayList11114);
                    obj = arrayList11114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList11115 = new ArrayList();
                    arrayList11115.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11115);
                    composerStartRestartGroup.updateRememberedValue(arrayList11115);
                    obj = arrayList11115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts113 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy113 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier116 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier113, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts113.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical116;
                horizontal2 = horizontal115;
                i19 = i17;
                modifier3 = modifier116;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        start = horizontal;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                i9 = i;
                if (composerStartRestartGroup.changed(i9)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i5 |= i10;
            }
            i11 = i4 & 16;
            if (i11 != 0) {
                if ((i3 & 24576) == 0) {
                    i12 = i2;
                    if (composerStartRestartGroup.changed(i12)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 32;
                if (i14 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i16 = 1048576;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i5 & 599187) == 599186) {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal116 = start;
                    Arrangement.Vertical vertical117 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical117, horizontal116, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11116 = new ArrayList();
                        arrayList11116.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11116);
                        composerStartRestartGroup.updateRememberedValue(arrayList11116);
                        obj = arrayList11116;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11117 = new ArrayList();
                        arrayList11117.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11117);
                        composerStartRestartGroup.updateRememberedValue(arrayList11117);
                        obj = arrayList11117;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts114 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy114 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap114 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier114 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier117 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier114, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts114.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical117;
                    horizontal2 = horizontal116;
                    i19 = i17;
                    modifier3 = modifier117;
                } else {
                    if (i21 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i22 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    }
                    if (i8 != 0) {
                        i9 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i12;
                    }
                    if (i14 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                    i18 = 458752 & i5;
                    if (i18 == 131072) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z) {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout_release();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Arrangement.Horizontal horizontal117 = start;
                    Arrangement.Vertical vertical118 = top;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical118, horizontal117, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                    if (i18 == 131072) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((i5 & 3670016) == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z4 = z3 | z2;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        obj = objRememberedValue2;
                        ArrayList arrayList11118 = new ArrayList();
                        arrayList11118.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11118);
                        composerStartRestartGroup.updateRememberedValue(arrayList11118);
                        obj = arrayList11118;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11119 = new ArrayList();
                        arrayList11119.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                                if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                                }
                                function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList11119);
                        composerStartRestartGroup.updateRememberedValue(arrayList11119);
                        obj = arrayList11119;
                    }
                    obj = objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts115 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy115 = (MeasurePolicy) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap115 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier115 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Modifier modifier118 = companion;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3454constructorimpl.getInserting()) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier115, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts115.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    flowColumnOverflow2 = clip;
                    vertical3 = vertical118;
                    horizontal2 = horizontal117;
                    i19 = i17;
                    modifier3 = modifier118;
                }
                i20 = i9;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            i12 = i2;
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal118 = start;
                Arrangement.Vertical vertical119 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical119, horizontal118, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111110 = new ArrayList();
                    arrayList111110.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111110);
                    composerStartRestartGroup.updateRememberedValue(arrayList111110);
                    obj = arrayList111110;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111111 = new ArrayList();
                    arrayList111111.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111111);
                    composerStartRestartGroup.updateRememberedValue(arrayList111111);
                    obj = arrayList111111;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts116 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy116 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap116 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier116 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier119 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier116, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts116.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical119;
                horizontal2 = horizontal118;
                i19 = i17;
                modifier3 = modifier119;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal119 = start;
                Arrangement.Vertical vertical1110 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical1110, horizontal119, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111112 = new ArrayList();
                    arrayList111112.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111112);
                    composerStartRestartGroup.updateRememberedValue(arrayList111112);
                    obj = arrayList111112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111113 = new ArrayList();
                    arrayList111113.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111113);
                    composerStartRestartGroup.updateRememberedValue(arrayList111113);
                    obj = arrayList111113;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts117 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy117 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap117 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier117 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1110 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier117, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts117.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical1110;
                horizontal2 = horizontal119;
                i19 = i17;
                modifier3 = modifier1110;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 3072;
        i9 = i;
        i11 = i4 & 16;
        if (i11 != 0) {
            if ((i3 & 24576) == 0) {
                i12 = i2;
                if (composerStartRestartGroup.changed(i12)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i5 |= i13;
            }
            i14 = i4 & 32;
            if (i14 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i4 & 64) != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i16 = 1048576;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i5 & 599187) == 599186) {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal1110 = start;
                Arrangement.Vertical vertical1111 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical1111, horizontal1110, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111114 = new ArrayList();
                    arrayList111114.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111114);
                    composerStartRestartGroup.updateRememberedValue(arrayList111114);
                    obj = arrayList111114;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111115 = new ArrayList();
                    arrayList111115.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111115);
                    composerStartRestartGroup.updateRememberedValue(arrayList111115);
                    obj = arrayList111115;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts118 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy118 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap118 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier118 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1111 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier118, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts118.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical1111;
                horizontal2 = horizontal1110;
                i19 = i17;
                modifier3 = modifier1111;
            } else {
                if (i21 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i22 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                }
                if (i8 != 0) {
                    i9 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i12;
                }
                if (i14 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
                i18 = 458752 & i5;
                if (i18 == 131072) {
                    z = true;
                } else {
                    z = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z) {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Arrangement.Horizontal horizontal1111 = start;
                Arrangement.Vertical vertical1112 = top;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical1112, horizontal1111, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
                if (i18 == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((i5 & 3670016) == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3 | z2;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    obj = objRememberedValue2;
                    ArrayList arrayList111116 = new ArrayList();
                    arrayList111116.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111116);
                    composerStartRestartGroup.updateRememberedValue(arrayList111116);
                    obj = arrayList111116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList111117 = new ArrayList();
                    arrayList111117.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i23) {
                            ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                            if ((i23 & 3) == 2 && composer2.getSkipping()) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                            }
                            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111117);
                    composerStartRestartGroup.updateRememberedValue(arrayList111117);
                    obj = arrayList111117;
                }
                obj = objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts119 = LayoutKt.combineAsVirtualLayouts((List) obj);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy119 = (MeasurePolicy) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap119 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier119 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Modifier modifier1112 = companion;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3454constructorimpl.getInserting()) {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier119, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts119.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                flowColumnOverflow2 = clip;
                vertical3 = vertical1112;
                horizontal2 = horizontal1111;
                i19 = i17;
                modifier3 = modifier1112;
            }
            i20 = i9;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 24576;
        i12 = i2;
        i14 = i4 & 32;
        if (i14 != 0) {
            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                i15 = 131072;
            } else {
                i15 = 65536;
            }
            i5 |= i15;
        }
        if ((i4 & 64) != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i16 = 1048576;
            } else {
                i16 = 524288;
            }
            i5 |= i16;
        }
        if ((i5 & 599187) == 599186) {
            if (i21 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i22 != 0) {
                top = Arrangement.INSTANCE.getTop();
            } else {
                top = vertical2;
            }
            if (i6 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i8 != 0) {
                i9 = Integer.MAX_VALUE;
            }
            if (i11 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i12;
            }
            if (i14 != 0) {
                clip = FlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = flowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
            i18 = 458752 & i5;
            if (i18 == 131072) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.Horizontal horizontal1112 = start;
            Arrangement.Vertical vertical1113 = top;
            multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical1113, horizontal1112, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
            if (i18 == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i5 & 3670016) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            z4 = z3 | z2;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                obj = objRememberedValue2;
                ArrayList arrayList111118 = new ArrayList();
                arrayList111118.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                        }
                        function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111118);
                composerStartRestartGroup.updateRememberedValue(arrayList111118);
                obj = arrayList111118;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList111119 = new ArrayList();
                arrayList111119.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                        }
                        function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList111119);
                composerStartRestartGroup.updateRememberedValue(arrayList111119);
                obj = arrayList111119;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts1110 = LayoutKt.combineAsVirtualLayouts((List) obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy1110 = (MeasurePolicy) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier1113 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy1110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap1110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier1110, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts1110.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            flowColumnOverflow2 = clip;
            vertical3 = vertical1113;
            horizontal2 = horizontal1112;
            i19 = i17;
            modifier3 = modifier1113;
        } else {
            if (i21 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (i22 != 0) {
                top = Arrangement.INSTANCE.getTop();
            } else {
                top = vertical2;
            }
            if (i6 != 0) {
                start = Arrangement.INSTANCE.getStart();
            }
            if (i8 != 0) {
                i9 = Integer.MAX_VALUE;
            }
            if (i11 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i12;
            }
            if (i14 != 0) {
                clip = FlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = flowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1704849416, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:156)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515615249, "CC(remember):FlowLayout.kt#9igjgp");
            i18 = 458752 & i5;
            if (i18 == 131072) {
                z = true;
            } else {
                z = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.Horizontal horizontal1113 = start;
            Arrangement.Vertical vertical1114 = top;
            multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(vertical1114, horizontal1113, i9, i17, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 8190);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1515605194, "CC(remember):FlowLayout.kt#9igjgp");
            if (i18 == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i5 & 3670016) == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            z4 = z3 | z2;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                obj = objRememberedValue2;
                ArrayList arrayList1111110 = new ArrayList();
                arrayList1111110.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                        }
                        function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111110);
                composerStartRestartGroup.updateRememberedValue(arrayList1111110);
                obj = arrayList1111110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111111 = new ArrayList();
                arrayList1111111.add(ComposableLambdaKt.composableLambdaInstance(985886665, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$FlowColumn$list$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i23) {
                        ComposerKt.sourceInformation(composer2, "C169@6950L9:FlowLayout.kt#2w3rfo");
                        if ((i23 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(985886665, i23, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:169)");
                        }
                        function3.invoke(FlowColumnScopeInstance.INSTANCE, composer2, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                clip.addOverflowComposables$foundation_layout_release(flowLayoutOverflowState, arrayList1111111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111111);
                obj = arrayList1111111;
            }
            obj = objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)174@7002L62,171@6888L182:Layout.kt#80mrfh");
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts1111 = LayoutKt.combineAsVirtualLayouts((List) obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761165, "CC(remember):Layout.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy1111 = (MeasurePolicy) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap1111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier1111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier1114 = companion;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3454constructorimpl = Updater.m3454constructorimpl(composerStartRestartGroup);
            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicy1111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap1111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3454constructorimpl.getInserting()) {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier1111, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts1111.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            flowColumnOverflow2 = clip;
            vertical3 = vertical1114;
            horizontal2 = horizontal1113;
            i19 = i17;
            modifier3 = modifier1114;
        }
        i20 = i9;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i23) {
                    FlowLayoutKt.FlowColumn(modifier3, vertical3, horizontal2, i20, i19, flowColumnOverflow2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                }
            });
        }
    }

    public static final MeasurePolicy rowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1479255111, "C(rowMeasurementHelper)P(!1,2)380@13086L883:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1479255111, i2, -1, "androidx.compose.foundation.layout.rowMeasurementHelper (FlowLayout.kt:379)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -75342195, "CC(remember):FlowLayout.kt#9igjgp");
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i2 & 48) == 32) | ((((i2 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        MeasurePolicy measurePolicyRememberedValue = composer.rememberedValue();
        if (z || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout_release(), null);
            measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.mo668measure3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(measurePolicyRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy rowMeasurementMultiContentHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, -2134502475, "C(rowMeasurementMultiContentHelper)P(!1,4)414@14301L649:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2134502475, i3, -1, "androidx.compose.foundation.layout.rowMeasurementMultiContentHelper (FlowLayout.kt:413)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 744443043, "CC(remember):FlowLayout.kt#9igjgp");
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i3 & 48) == 32) | ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i3 & 3072) == 2048) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return flowMeasurePolicy2;
    }

    public static final MeasurePolicy columnMeasurementHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -2013098357, "C(columnMeasurementHelper)P(2)443@15212L856:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2013098357, i2, -1, "androidx.compose.foundation.layout.columnMeasurementHelper (FlowLayout.kt:442)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1837869538, "CC(remember):FlowLayout.kt#9igjgp");
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i2 & 48) == 32) | ((((i2 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        MeasurePolicy measurePolicyRememberedValue = composer.rememberedValue();
        if (z || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout_release(), null);
            measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.mo668measure3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(measurePolicyRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy columnMeasurementMultiContentHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, 748776953, "C(columnMeasurementMultiContentHelper)P(4)475@16365L652:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(748776953, i3, -1, "androidx.compose.foundation.layout.columnMeasurementMultiContentHelper (FlowLayout.kt:474)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 287541334, "CC(remember):FlowLayout.kt#9igjgp");
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i3 & 48) == 32) | ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i3 & 3072) == 2048) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return flowMeasurePolicy2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.collections.IntIterator] */
    /* JADX WARN: Type inference failed for: r9v13, types: [kotlin.collections.IntIterator] */
    public static final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function4, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6 = i4;
        int i7 = i5;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i8 = 0; i8 < size; i8++) {
            iArr[i8] = 0;
        }
        int size2 = list.size();
        int[] iArr2 = new int[size2];
        for (int i9 = 0; i9 < size2; i9++) {
            iArr2[i9] = 0;
        }
        int size3 = list.size();
        for (int i10 = 0; i10 < size3; i10++) {
            IntrinsicMeasurable intrinsicMeasurable = list.get(i10);
            int iIntValue = function3.invoke(intrinsicMeasurable, Integer.valueOf(i10), Integer.valueOf(i)).intValue();
            iArr[i10] = iIntValue;
            iArr2[i10] = function4.invoke(intrinsicMeasurable, Integer.valueOf(i10), Integer.valueOf(iIntValue)).intValue();
        }
        List<? extends IntrinsicMeasurable> list2 = list;
        int i11 = Integer.MAX_VALUE;
        if (i7 != Integer.MAX_VALUE && i6 != Integer.MAX_VALUE) {
            i11 = i6 * i7;
        }
        int iMin = Math.min(i11 - (((i11 >= list2.size() || !(flowLayoutOverflowState.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandIndicator || flowLayoutOverflowState.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) && (i11 < list2.size() || i7 < flowLayoutOverflowState.getMinLinesToShowCollapse$foundation_layout_release() || flowLayoutOverflowState.getType$foundation_layout_release() != FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? 0 : 1), list2.size());
        int iSum = ArraysKt.sum(iArr) + ((list2.size() - 1) * i2);
        if (size2 != 0) {
            int i12 = iArr2[0];
            ?? it = new IntRange(1, ArraysKt.getLastIndex(iArr2)).iterator();
            while (it.hasNext()) {
                int i13 = iArr2[it.nextInt()];
                if (i12 < i13) {
                    i12 = i13;
                }
            }
            if (size != 0) {
                int i14 = iArr[0];
                ?? it2 = new IntRange(1, ArraysKt.getLastIndex(iArr)).iterator();
                while (it2.hasNext()) {
                    int i15 = iArr[it2.nextInt()];
                    if (i14 < i15) {
                        i14 = i15;
                    }
                }
                int i16 = i14;
                int i17 = iSum;
                while (i16 <= i17 && i12 != i) {
                    int i18 = (i16 + i17) / 2;
                    long jIntrinsicCrossAxisSize = intrinsicCrossAxisSize(list2, iArr, iArr2, i18, i2, i3, i6, i7, flowLayoutOverflowState);
                    int iM62getFirstimpl = IntIntPair.m62getFirstimpl(jIntrinsicCrossAxisSize);
                    int iM63getSecondimpl = IntIntPair.m63getSecondimpl(jIntrinsicCrossAxisSize);
                    if (iM62getFirstimpl > i || iM63getSecondimpl < iMin) {
                        i16 = i18 + 1;
                        if (i16 > i17) {
                            return i16;
                        }
                    } else {
                        if (iM62getFirstimpl >= i) {
                            return i18;
                        }
                        i17 = i18 - 1;
                    }
                    list2 = list;
                    i6 = i4;
                    i7 = i5;
                    iSum = i18;
                    i12 = iM62getFirstimpl;
                }
                return iSum;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    private static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, final int[] iArr, final int[] iArr2, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        return intrinsicCrossAxisSize(list, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.intrinsicCrossAxisSize.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i6, int i7) {
                return Integer.valueOf(iArr[i6]);
            }
        }, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.intrinsicCrossAxisSize.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i6, int i7) {
                return Integer.valueOf(iArr2[i6]);
            }
        }, i, i2, i3, i4, i5, flowLayoutOverflowState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function4, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6;
        if (list.isEmpty()) {
            return IntIntPair.m58constructorimpl(0, 0);
        }
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i4, flowLayoutOverflowState, OrientationIndependentConstraints.m687constructorimpl(0, i, 0, Integer.MAX_VALUE), i5, i2, i3, null);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, 0);
        int iIntValue = intrinsicMeasurable != null ? function4.invoke(intrinsicMeasurable, 0, Integer.valueOf(i)).intValue() : 0;
        int iIntValue2 = intrinsicMeasurable != null ? function3.invoke(intrinsicMeasurable, 0, Integer.valueOf(iIntValue)).intValue() : 0;
        int i7 = 0;
        int iMax = 0;
        if (flowLayoutBuildingBlocks.m642getWrapInfoOpUlnko(list.size() > 1, 0, IntIntPair.m58constructorimpl(i, Integer.MAX_VALUE), intrinsicMeasurable == null ? null : IntIntPair.m55boximpl(IntIntPair.m58constructorimpl(iIntValue2, iIntValue)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
            IntIntPair intIntPairM647ellipsisSizeF35zmw$foundation_layout_release = flowLayoutOverflowState.m647ellipsisSizeF35zmw$foundation_layout_release(intrinsicMeasurable != null, 0, 0);
            return IntIntPair.m58constructorimpl(intIntPairM647ellipsisSizeF35zmw$foundation_layout_release != null ? IntIntPair.m63getSecondimpl(intIntPairM647ellipsisSizeF35zmw$foundation_layout_release.getPackedValue()) : 0, 0);
        }
        int size = list.size();
        int i8 = i;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = iMax;
            if (i9 >= size) {
                i6 = i10;
                break;
            }
            int i14 = i8 - iIntValue2;
            int i15 = i9 + 1;
            iMax = Math.max(i13, iIntValue);
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, i15);
            int iIntValue3 = intrinsicMeasurable2 != null ? function4.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(i)).intValue() : 0;
            int iIntValue4 = intrinsicMeasurable2 != null ? function3.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(iIntValue3)).intValue() + i2 : 0;
            int i16 = i15 - i11;
            i6 = i15;
            int i17 = i12;
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM642getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m642getWrapInfoOpUlnko(i9 + 2 < list.size(), i16, IntIntPair.m58constructorimpl(i14, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : IntIntPair.m55boximpl(IntIntPair.m58constructorimpl(iIntValue4, iIntValue3)), i17, i7, iMax, false, false);
            if (wrapInfoM642getWrapInfoOpUlnko.getIsLastItemInLine()) {
                int iM63getSecondimpl = i7 + iMax + i3;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM642getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i17, iM63getSecondimpl, i14, i16);
                iIntValue4 -= i2;
                i12 = i17 + 1;
                if (wrapInfoM642getWrapInfoOpUlnko.getIsLastItemInContainer()) {
                    if (wrapEllipsisInfo != null) {
                        long ellipsisSize = wrapEllipsisInfo.getEllipsisSize();
                        if (!wrapEllipsisInfo.getPlaceEllipsisOnLastContentLine()) {
                            iM63getSecondimpl += IntIntPair.m63getSecondimpl(ellipsisSize) + i3;
                        }
                    }
                    i7 = iM63getSecondimpl;
                    break;
                }
                i8 = i;
                i11 = i6;
                i7 = iM63getSecondimpl;
                iMax = 0;
            } else {
                i8 = i14;
                i12 = i17;
            }
            iIntValue2 = iIntValue4;
            iIntValue = iIntValue3;
            i9 = i6;
            i10 = i9;
        }
        return IntIntPair.m58constructorimpl(i7 - i3, i6);
    }

    private static final Measurable safeNext(Iterator<? extends Measurable> it, FlowLineInfo flowLineInfo) {
        try {
            if (it instanceof ContextualFlowItemIterator) {
                Intrinsics.checkNotNull(flowLineInfo);
                return ((ContextualFlowItemIterator) it).getNext$foundation_layout_release(flowLineInfo);
            }
            return it.next();
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public static final int mainAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        if (z) {
            return intrinsicMeasurable.minIntrinsicWidth(i);
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public static final int crossAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        if (z) {
            return intrinsicMeasurable.minIntrinsicHeight(i);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_TOP() {
        return CROSS_AXIS_ALIGNMENT_TOP;
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_START() {
        return CROSS_AXIS_ALIGNMENT_START;
    }

    /* JADX INFO: renamed from: measureAndCache-rqJ1uqs, reason: not valid java name */
    public static final long m645measureAndCacherqJ1uqs(Measurable measurable, FlowLineMeasurePolicy flowLineMeasurePolicy, long j, Function1<? super Placeable, Unit> function1) {
        FlowLayoutData flowLayoutData;
        Measurable measurable2 = measurable;
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable2)) == 0.0f) {
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable2);
            if (((rowColumnParentData == null || (flowLayoutData = rowColumnParentData.getFlowLayoutData()) == null) ? null : Float.valueOf(flowLayoutData.getFillCrossAxisFraction())) == null) {
                Placeable placeableMo5581measureBRTryo0 = measurable.mo5581measureBRTryo0(j);
                function1.invoke(placeableMo5581measureBRTryo0);
                return IntIntPair.m58constructorimpl(flowLineMeasurePolicy.mainAxisSize(placeableMo5581measureBRTryo0), flowLineMeasurePolicy.crossAxisSize(placeableMo5581measureBRTryo0));
            }
        }
        int iMainAxisMin = mainAxisMin(measurable2, flowLineMeasurePolicy.isHorizontal(), Integer.MAX_VALUE);
        return IntIntPair.m58constructorimpl(iMainAxisMin, crossAxisMin(measurable2, flowLineMeasurePolicy.isHorizontal(), iMainAxisMin));
    }

    /* JADX INFO: renamed from: placeHelper-BmaY500, reason: not valid java name */
    public static final MeasureResult m646placeHelperBmaY500(MeasureScope measureScope, long j, int i, int i2, int[] iArr, final MutableVector<MeasureResult> mutableVector, FlowLineMeasurePolicy flowLineMeasurePolicy, int[] iArr2) {
        int iCoerceIn;
        int i3;
        int i4;
        boolean zIsHorizontal = flowLineMeasurePolicy.isHorizontal();
        Arrangement.Vertical verticalArrangement = flowLineMeasurePolicy.getVerticalArrangement();
        Arrangement.Horizontal horizontalArrangement = flowLineMeasurePolicy.getHorizontalArrangement();
        if (zIsHorizontal) {
            if (verticalArrangement == null) {
                throw new IllegalArgumentException("null verticalArrangement".toString());
            }
            iCoerceIn = RangesKt.coerceIn(i2 + (measureScope.mo400roundToPx0680j_4(verticalArrangement.getSpacing()) * (mutableVector.getSize() - 1)), Constraints.m6834getMinHeightimpl(j), Constraints.m6832getMaxHeightimpl(j));
            verticalArrangement.arrange(measureScope, iCoerceIn, iArr, iArr2);
        } else {
            if (horizontalArrangement == null) {
                throw new IllegalArgumentException("null horizontalArrangement".toString());
            }
            int iCoerceIn2 = RangesKt.coerceIn(i2 + (measureScope.mo400roundToPx0680j_4(horizontalArrangement.getSpacing()) * (mutableVector.getSize() - 1)), Constraints.m6834getMinHeightimpl(j), Constraints.m6832getMaxHeightimpl(j));
            horizontalArrangement.arrange(measureScope, iCoerceIn2, iArr, measureScope.getLayoutDirection(), iArr2);
            iCoerceIn = iCoerceIn2;
        }
        int iCoerceIn3 = RangesKt.coerceIn(i, Constraints.m6835getMinWidthimpl(j), Constraints.m6833getMaxWidthimpl(j));
        if (zIsHorizontal) {
            i4 = iCoerceIn3;
            i3 = iCoerceIn;
        } else {
            i3 = iCoerceIn3;
            i4 = iCoerceIn;
        }
        return MeasureScope.CC.layout$default(measureScope, i4, i3, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$placeHelper$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                MutableVector<MeasureResult> mutableVector2 = mutableVector;
                int size = mutableVector2.getSize();
                if (size > 0) {
                    MeasureResult[] content = mutableVector2.getContent();
                    int i5 = 0;
                    do {
                        content[i5].placeChildren();
                        i5++;
                    } while (i5 < size);
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, int i, int i2, int i3) {
        int size = list.size();
        int i4 = 0;
        int iMax = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < size) {
            int iIntValue = function3.invoke(list.get(i4), Integer.valueOf(i4), Integer.valueOf(i)).intValue() + i2;
            int i7 = i4 + 1;
            if (i7 - i5 == i3 || i7 == list.size()) {
                iMax = Math.max(iMax, (i6 + iIntValue) - i2);
                i6 = 0;
                i5 = i4;
            } else {
                i6 += iIntValue;
            }
            i4 = i7;
        }
        return iMax;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: breakDownItems-di9J0FM, reason: not valid java name */
    public static final MeasureResult m644breakDownItemsdi9J0FM(MeasureScope measureScope, FlowLineMeasurePolicy flowLineMeasurePolicy, Iterator<? extends Measurable> it, float f, float f2, long j, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i3;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo;
        MutableIntObjectMap mutableIntObjectMap;
        ArrayList arrayList;
        int i4;
        int i5;
        int i6;
        int i7;
        MutableIntList mutableIntList;
        MutableIntList mutableIntList2;
        int i8;
        int i9;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo2;
        int i10;
        int iCoerceAtLeast;
        MeasureScope measureScope2 = measureScope;
        MutableVector mutableVector = new MutableVector(new MeasureResult[16], 0);
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
        int iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        MutableIntObjectMap mutableIntObjectMapMutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList arrayList2 = new ArrayList();
        int iCeil = (int) Math.ceil(measureScope2.mo406toPx0680j_4(f));
        int iCeil2 = (int) Math.ceil(measureScope2.mo406toPx0680j_4(f2));
        long jM687constructorimpl = OrientationIndependentConstraints.m687constructorimpl(0, iM6833getMaxWidthimpl, 0, iM6832getMaxHeightimpl);
        long jM702toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m702toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m691copyyUG9Ft0$default(jM687constructorimpl, 0, 0, 0, 0, 14, null), flowLineMeasurePolicy.isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FlowLineInfo flowLineInfo = it instanceof ContextualFlowItemIterator ? new FlowLineInfo(0, 0, measureScope2.mo403toDpu2uoSUM(iM6833getMaxWidthimpl), measureScope2.mo403toDpu2uoSUM(iM6832getMaxHeightimpl), null) : null;
        Measurable measurableSafeNext = !it.hasNext() ? null : safeNext(it, flowLineInfo);
        IntIntPair intIntPairM55boximpl = measurableSafeNext != null ? IntIntPair.m55boximpl(m645measureAndCacherqJ1uqs(measurableSafeNext, flowLineMeasurePolicy, jM702toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$nextSize$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                invoke2(placeable);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable placeable) {
                objectRef.element = placeable;
            }
        })) : null;
        Integer numValueOf = intIntPairM55boximpl != null ? Integer.valueOf(IntIntPair.m62getFirstimpl(intIntPairM55boximpl.getPackedValue())) : null;
        Integer numValueOf2 = intIntPairM55boximpl != null ? Integer.valueOf(IntIntPair.m63getSecondimpl(intIntPairM55boximpl.getPackedValue())) : null;
        Integer numValueOf3 = numValueOf;
        Measurable measurable = measurableSafeNext;
        MutableIntList mutableIntList3 = new MutableIntList(0, 1, null);
        MutableIntList mutableIntList4 = new MutableIntList(0, 1, null);
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i, flowLayoutOverflowState, j, i2, iCeil, iCeil2, null);
        FlowLayoutBuildingBlocks.WrapInfo wrapInfoM642getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m642getWrapInfoOpUlnko(it.hasNext(), 0, IntIntPair.m58constructorimpl(iM6833getMaxWidthimpl, iM6832getMaxHeightimpl), intIntPairM55boximpl, 0, 0, 0, false, false);
        if (wrapInfoM642getWrapInfoOpUlnko.getIsLastItemInContainer()) {
            wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM642getWrapInfoOpUlnko, intIntPairM55boximpl != null, -1, 0, iM6833getMaxWidthimpl, 0);
            i3 = iM6833getMaxWidthimpl;
        } else {
            i3 = iM6833getMaxWidthimpl;
            wrapEllipsisInfo = null;
        }
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo3 = wrapEllipsisInfo;
        MutableIntList mutableIntList5 = mutableIntList3;
        int i11 = iM6832getMaxHeightimpl;
        int i12 = i3;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfo = wrapInfoM642getWrapInfoOpUlnko;
        int i13 = iM6835getMinWidthimpl;
        Measurable measurableSafeNext2 = measurable;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (!wrapInfo.getIsLastItemInContainer() && measurableSafeNext2 != null) {
            Intrinsics.checkNotNull(numValueOf3);
            int iIntValue = numValueOf3.intValue();
            Intrinsics.checkNotNull(numValueOf2);
            MutableIntList mutableIntList6 = mutableIntList4;
            int i20 = i3;
            int i21 = i17 + iIntValue;
            int iMax = Math.max(i14, numValueOf2.intValue());
            int i22 = i12 - iIntValue;
            int i23 = i15 + 1;
            int i24 = i13;
            flowLayoutOverflowState.setItemShown$foundation_layout_release(i23);
            arrayList2.add(measurableSafeNext2);
            mutableIntObjectMapMutableIntObjectMapOf.set(i15, objectRef.element);
            int i25 = i23 - i16;
            boolean z = i25 < i;
            if (flowLineInfo != null) {
                int i26 = z ? i18 : i18 + 1;
                int i27 = z ? i25 : 0;
                if (z) {
                    i10 = 0;
                    iCoerceAtLeast = RangesKt.coerceAtLeast(i22 - iCeil, 0);
                } else {
                    i10 = 0;
                    iCoerceAtLeast = i20;
                }
                flowLineInfo.m655update4j6BHR0$foundation_layout_release(i26, i27, measureScope2.mo403toDpu2uoSUM(iCoerceAtLeast), measureScope2.mo403toDpu2uoSUM(z ? i11 : RangesKt.coerceAtLeast((i11 - iMax) - iCeil2, i10)));
            } else {
                i25 = i25;
                mutableIntObjectMapMutableIntObjectMapOf = mutableIntObjectMapMutableIntObjectMapOf;
                arrayList2 = arrayList2;
            }
            measurableSafeNext2 = !it.hasNext() ? null : safeNext(it, flowLineInfo);
            IntIntPair intIntPairM55boximpl2 = null;
            objectRef.element = null;
            IntIntPair intIntPairM55boximpl3 = measurableSafeNext2 != null ? IntIntPair.m55boximpl(m645measureAndCacherqJ1uqs(measurableSafeNext2, flowLineMeasurePolicy, jM702toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                    invoke2(placeable);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable placeable) {
                    objectRef.element = placeable;
                }
            })) : null;
            Integer numValueOf4 = intIntPairM55boximpl3 != null ? Integer.valueOf(IntIntPair.m62getFirstimpl(intIntPairM55boximpl3.getPackedValue()) + iCeil) : null;
            numValueOf2 = intIntPairM55boximpl3 != null ? Integer.valueOf(IntIntPair.m63getSecondimpl(intIntPairM55boximpl3.getPackedValue())) : null;
            boolean zHasNext = it.hasNext();
            int i28 = i18;
            long jM58constructorimpl = IntIntPair.m58constructorimpl(i22, i11);
            if (intIntPairM55boximpl3 != null) {
                Intrinsics.checkNotNull(numValueOf4);
                int iIntValue2 = numValueOf4.intValue();
                Intrinsics.checkNotNull(numValueOf2);
                intIntPairM55boximpl2 = IntIntPair.m55boximpl(IntIntPair.m58constructorimpl(iIntValue2, numValueOf2.intValue()));
            }
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM642getWrapInfoOpUlnko2 = flowLayoutBuildingBlocks.m642getWrapInfoOpUlnko(zHasNext, i25, jM58constructorimpl, intIntPairM55boximpl2, i28, i19, iMax, false, false);
            if (wrapInfoM642getWrapInfoOpUlnko2.getIsLastItemInLine()) {
                int iMin = Math.min(Math.max(i24, i21), i20);
                int i29 = i19 + iMax;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo4 = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM642getWrapInfoOpUlnko2, intIntPairM55boximpl3 != null, i28, i29, i22, i25);
                mutableIntList2 = mutableIntList6;
                mutableIntList2.add(iMax);
                i9 = (i11 - i29) - iCeil2;
                MutableIntList mutableIntList7 = mutableIntList5;
                mutableIntList7.add(i23);
                i18 = i28 + 1;
                numValueOf3 = numValueOf4 != null ? Integer.valueOf(numValueOf4.intValue() - iCeil) : null;
                i12 = i20;
                i20 = i12;
                i16 = i23;
                i19 = i29 + iCeil2;
                i8 = 0;
                i13 = iMin;
                wrapEllipsisInfo2 = wrapEllipsisInfo4;
                mutableIntList = mutableIntList7;
                i21 = 0;
            } else {
                i13 = i24;
                mutableIntList = mutableIntList5;
                mutableIntList2 = mutableIntList6;
                numValueOf3 = numValueOf4;
                i8 = iMax;
                i12 = i22;
                i18 = i28;
                i9 = i11;
                wrapEllipsisInfo2 = wrapEllipsisInfo3;
            }
            wrapEllipsisInfo3 = wrapEllipsisInfo2;
            mutableIntList5 = mutableIntList;
            i14 = i8;
            i11 = i9;
            i15 = i23;
            arrayList2 = arrayList2;
            wrapInfo = wrapInfoM642getWrapInfoOpUlnko2;
            mutableIntList4 = mutableIntList2;
            mutableIntObjectMapMutableIntObjectMapOf = mutableIntObjectMapMutableIntObjectMapOf;
            i17 = i21;
            i3 = i20;
        }
        MutableIntObjectMap mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
        ArrayList arrayList3 = arrayList2;
        MutableIntList mutableIntList8 = mutableIntList4;
        MutableIntList mutableIntList9 = mutableIntList5;
        if (wrapEllipsisInfo3 != null) {
            arrayList = arrayList3;
            arrayList.add(wrapEllipsisInfo3.getEllipsis());
            mutableIntObjectMap = mutableIntObjectMap2;
            mutableIntObjectMap.set(arrayList.size() - 1, wrapEllipsisInfo3.getPlaceable());
            int i30 = mutableIntList9._size - 1;
            if (wrapEllipsisInfo3.getPlaceEllipsisOnLastContentLine()) {
                int size = mutableIntList9.getSize() - 1;
                mutableIntList8.set(i30, Math.max(mutableIntList8.get(i30), IntIntPair.m63getSecondimpl(wrapEllipsisInfo3.getEllipsisSize())));
                mutableIntList9.set(size, mutableIntList9.last() + 1);
            } else {
                mutableIntList8.add(IntIntPair.m63getSecondimpl(wrapEllipsisInfo3.getEllipsisSize()));
                mutableIntList9.add(mutableIntList9.last() + 1);
            }
        } else {
            mutableIntObjectMap = mutableIntObjectMap2;
            arrayList = arrayList3;
        }
        int size2 = arrayList.size();
        int i31 = i13;
        Placeable[] placeableArr = new Placeable[size2];
        for (int i32 = 0; i32 < size2; i32++) {
            placeableArr[i32] = mutableIntObjectMap.get(i32);
        }
        int size3 = mutableIntList9.getSize();
        int[] iArr = new int[size3];
        for (int i33 = 0; i33 < size3; i33++) {
            iArr[i33] = 0;
        }
        int size4 = mutableIntList9.getSize();
        int[] iArr2 = new int[size4];
        for (int i34 = 0; i34 < size4; i34++) {
            iArr2[i34] = 0;
        }
        MutableIntList mutableIntList10 = mutableIntList9;
        int[] iArr3 = mutableIntList10.content;
        int i35 = mutableIntList10._size;
        int iMax2 = i31;
        int i36 = 0;
        int i37 = 0;
        int i38 = 0;
        while (i37 < i35) {
            int i39 = iArr3[i37];
            MutableIntList mutableIntList11 = mutableIntList8;
            int i40 = i35;
            MeasureResult measureResultMeasure = RowColumnMeasurePolicyKt.measure(flowLineMeasurePolicy, iMax2, Constraints.m6834getMinHeightimpl(jM687constructorimpl), Constraints.m6833getMaxWidthimpl(jM687constructorimpl), mutableIntList11.get(i37), iCeil, measureScope2, arrayList, placeableArr, i36, i39, iArr, i37);
            if (flowLineMeasurePolicy.isHorizontal()) {
                i6 = measureResultMeasure.getWidth();
                i7 = measureResultMeasure.getHeight();
            } else {
                i6 = measureResultMeasure.getHeight();
                i7 = measureResultMeasure.getWidth();
            }
            iArr2[i37] = i7;
            i38 += i7;
            iMax2 = Math.max(iMax2, i6);
            mutableVector.add(measureResultMeasure);
            i37++;
            i36 = i39;
            i35 = i40;
            mutableIntList8 = mutableIntList11;
            measureScope2 = measureScope;
        }
        if (mutableVector.isEmpty()) {
            i4 = 0;
            i5 = 0;
        } else {
            i4 = iMax2;
            i5 = i38;
        }
        return m646placeHelperBmaY500(measureScope, j, i4, i5, iArr2, mutableVector, flowLineMeasurePolicy, iArr);
    }
}
