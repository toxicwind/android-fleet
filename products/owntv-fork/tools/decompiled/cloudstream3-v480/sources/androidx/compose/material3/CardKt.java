package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018\u001aw\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001a\u001aS\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001b\u001a\u0081\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a]\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018¨\u0006\u001d"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/CardColors;", "elevation", "Landroidx/compose/material3/CardElevation;", "border", "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedCard", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedCard", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CardKt {
    /* JADX WARN: Code duplicated, block: B:101:0x0126  */
    /* JADX WARN: Code duplicated, block: B:102:0x013e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0146  */
    /* JADX WARN: Code duplicated, block: B:106:0x014a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0156  */
    /* JADX WARN: Code duplicated, block: B:112:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:116:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:64:0x00af  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:73:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:88:0x00fe A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:89:0x0100  */
    /* JADX WARN: Code duplicated, block: B:90:0x0105  */
    /* JADX WARN: Code duplicated, block: B:93:0x010a  */
    /* JADX WARN: Code duplicated, block: B:94:0x0113  */
    /* JADX WARN: Code duplicated, block: B:97:0x0118  */
    /* JADX WARN: Code duplicated, block: B:98:0x0121  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void Card(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Shape shape2;
        CardColors cardColors2;
        CardElevation cardElevationM1547cardElevationaqJV_2Y;
        BorderStroke borderStroke2;
        int i4;
        InteractionSource interactionSource;
        Modifier.Companion companion;
        Shape shape3;
        CardColors cardColors3;
        Modifier modifier3;
        CardElevation cardElevation2;
        BorderStroke borderStroke3;
        Shape shape4;
        final CardColors cardColors4;
        final Modifier modifier4;
        final Shape shape5;
        final BorderStroke borderStroke4;
        final CardElevation cardElevation3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(1179621553);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Card)P(4,5,1,3)82@3741L5,83@3786L12,84@3844L15,93@4163L57,95@4259L41,88@3951L349:Card.kt#uh7d8r");
        int i5 = i2 & 1;
        if (i5 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                shape2 = shape;
                int i6 = composerStartRestartGroup.changed(shape2) ? 32 : 16;
                i3 |= i6;
            } else {
                shape2 = shape;
            }
            i3 |= i6;
        } else {
            shape2 = shape;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                cardColors2 = cardColors;
                int i7 = composerStartRestartGroup.changed(cardColors2) ? 256 : 128;
                i3 |= i7;
            } else {
                cardColors2 = cardColors;
            }
            i3 |= i7;
        } else {
            cardColors2 = cardColors;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                int i8 = composerStartRestartGroup.changed(cardElevationM1547cardElevationaqJV_2Y) ? 2048 : 1024;
                i3 |= i8;
            } else {
                cardElevationM1547cardElevationaqJV_2Y = cardElevation;
            }
            i3 |= i8;
        } else {
            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
        }
        int i9 = i2 & 16;
        if (i9 == 0) {
            if ((i & 24576) == 0) {
                borderStroke2 = borderStroke;
                i3 |= composerStartRestartGroup.changed(borderStroke2) ? 16384 : 8192;
            }
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i4 = 131072;
                } else {
                    i4 = 65536;
                }
                i3 |= i4;
            }
            if ((74899 & i3) == 74898 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                interactionSource = null;
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        shape3 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        i3 &= -113;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 4) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 8) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        i3 &= -7169;
                    }
                    CardElevation cardElevation4 = cardElevationM1547cardElevationaqJV_2Y;
                    modifier3 = companion;
                    cardElevation2 = cardElevation4;
                    if (i9 != 0) {
                        shape4 = shape3;
                        borderStroke3 = interactionSource;
                    } else {
                        borderStroke3 = borderStroke;
                        shape4 = shape3;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    cardElevation2 = cardElevationM1547cardElevationaqJV_2Y;
                    borderStroke3 = borderStroke2;
                    modifier3 = modifier2;
                    shape4 = shape2;
                    cardColors3 = cardColors2;
                    interactionSource = null;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1179621553, i3, -1, "androidx.compose.material3.Card (Card.kt:87)");
                }
                CardElevation cardElevation5 = cardElevation2;
                SurfaceKt.m2261SurfaceT9BRK9s(modifier3, shape4, cardColors3.m1539containerColorvNxB06k$material3_release(true), cardColors3.m1540contentColorvNxB06k$material3_release(true), 0.0f, cardElevation2.shadowElevation$material3_release(true, interactionSource, composerStartRestartGroup, ((i3 >> 3) & 896) | 54).getValue().m6904unboximpl(), borderStroke3, ComposableLambdaKt.rememberComposableLambda(664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                    public final void invoke(Composer composer2, int i10) {
                        ComposerKt.sourceInformation(composer2, "C96@4269L25:Card.kt#uh7d8r");
                        if ((i10 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(664103990, i10, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:96)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, 0);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12582912 | (i3 & 112) | (3670016 & (i3 << 6)), 16);
                composerStartRestartGroup = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                modifier4 = modifier3;
                shape5 = shape4;
                borderStroke4 = borderStroke3;
                cardElevation3 = cardElevation5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                shape5 = shape2;
                cardColors4 = cardColors2;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                borderStroke4 = borderStroke2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.2
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

                    public final void invoke(Composer composer2, int i10) {
                        CardKt.Card(modifier4, shape5, cardColors4, cardElevation3, borderStroke4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        borderStroke2 = borderStroke;
        if ((i2 & 32) != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i4 = 131072;
            } else {
                i4 = 65536;
            }
            i3 |= i4;
        }
        if ((74899 & i3) == 74898) {
            composerStartRestartGroup.startDefaults();
            interactionSource = null;
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    i3 &= -7169;
                }
                CardElevation cardElevation6 = cardElevationM1547cardElevationaqJV_2Y;
                modifier3 = companion;
                cardElevation2 = cardElevation6;
                if (i9 != 0) {
                    shape4 = shape3;
                    borderStroke3 = interactionSource;
                } else {
                    borderStroke3 = borderStroke;
                    shape4 = shape3;
                }
            } else {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    i3 &= -7169;
                }
                CardElevation cardElevation7 = cardElevationM1547cardElevationaqJV_2Y;
                modifier3 = companion;
                cardElevation2 = cardElevation7;
                if (i9 != 0) {
                    shape4 = shape3;
                    borderStroke3 = interactionSource;
                } else {
                    borderStroke3 = borderStroke;
                    shape4 = shape3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179621553, i3, -1, "androidx.compose.material3.Card (Card.kt:87)");
            }
            CardElevation cardElevation8 = cardElevation2;
            SurfaceKt.m2261SurfaceT9BRK9s(modifier3, shape4, cardColors3.m1539containerColorvNxB06k$material3_release(true), cardColors3.m1540contentColorvNxB06k$material3_release(true), 0.0f, cardElevation2.shadowElevation$material3_release(true, interactionSource, composerStartRestartGroup, ((i3 >> 3) & 896) | 54).getValue().m6904unboximpl(), borderStroke3, ComposableLambdaKt.rememberComposableLambda(664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                public final void invoke(Composer composer2, int i10) {
                    ComposerKt.sourceInformation(composer2, "C96@4269L25:Card.kt#uh7d8r");
                    if ((i10 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(664103990, i10, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:96)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, 0);
                    ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12582912 | (i3 & 112) | (3670016 & (i3 << 6)), 16);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cardColors4 = cardColors3;
            modifier4 = modifier3;
            shape5 = shape4;
            borderStroke4 = borderStroke3;
            cardElevation3 = cardElevation8;
        } else {
            composerStartRestartGroup.startDefaults();
            interactionSource = null;
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    i3 &= -7169;
                }
                CardElevation cardElevation9 = cardElevationM1547cardElevationaqJV_2Y;
                modifier3 = companion;
                cardElevation2 = cardElevation9;
                if (i9 != 0) {
                    shape4 = shape3;
                    borderStroke3 = interactionSource;
                } else {
                    borderStroke3 = borderStroke;
                    shape4 = shape3;
                }
            } else {
                if (i5 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    i3 &= -7169;
                }
                CardElevation cardElevation10 = cardElevationM1547cardElevationaqJV_2Y;
                modifier3 = companion;
                cardElevation2 = cardElevation10;
                if (i9 != 0) {
                    shape4 = shape3;
                    borderStroke3 = interactionSource;
                } else {
                    borderStroke3 = borderStroke;
                    shape4 = shape3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179621553, i3, -1, "androidx.compose.material3.Card (Card.kt:87)");
            }
            CardElevation cardElevation11 = cardElevation2;
            SurfaceKt.m2261SurfaceT9BRK9s(modifier3, shape4, cardColors3.m1539containerColorvNxB06k$material3_release(true), cardColors3.m1540contentColorvNxB06k$material3_release(true), 0.0f, cardElevation2.shadowElevation$material3_release(true, interactionSource, composerStartRestartGroup, ((i3 >> 3) & 896) | 54).getValue().m6904unboximpl(), borderStroke3, ComposableLambdaKt.rememberComposableLambda(664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                public final void invoke(Composer composer2, int i10) {
                    ComposerKt.sourceInformation(composer2, "C96@4269L25:Card.kt#uh7d8r");
                    if ((i10 & 3) == 2 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(664103990, i10, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:96)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, 0);
                    ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer2);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12582912 | (i3 & 112) | (3670016 & (i3 << 6)), 16);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cardColors4 = cardColors3;
            modifier4 = modifier3;
            shape5 = shape4;
            borderStroke4 = borderStroke3;
            cardElevation3 = cardElevation11;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.2
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

                public final void invoke(Composer composer2, int i10) {
                    CardKt.Card(modifier4, shape5, cardColors4, cardElevation3, borderStroke4, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0118  */
    /* JADX WARN: Code duplicated, block: B:105:0x012e  */
    /* JADX WARN: Code duplicated, block: B:107:0x013d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0168 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:122:0x016a  */
    /* JADX WARN: Code duplicated, block: B:124:0x0171  */
    /* JADX WARN: Code duplicated, block: B:127:0x0176  */
    /* JADX WARN: Code duplicated, block: B:130:0x0183  */
    /* JADX WARN: Code duplicated, block: B:131:0x018c  */
    /* JADX WARN: Code duplicated, block: B:134:0x0191  */
    /* JADX WARN: Code duplicated, block: B:135:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:138:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:139:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:141:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:142:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:148:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:150:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:152:0x020d  */
    /* JADX WARN: Code duplicated, block: B:155:0x0263  */
    /* JADX WARN: Code duplicated, block: B:159:0x0274  */
    /* JADX WARN: Code duplicated, block: B:161:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:27:0x0055  */
    /* JADX WARN: Code duplicated, block: B:29:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    /* JADX WARN: Code duplicated, block: B:32:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x006e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0072  */
    /* JADX WARN: Code duplicated, block: B:41:0x007a  */
    /* JADX WARN: Code duplicated, block: B:42:0x007d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0083  */
    /* JADX WARN: Code duplicated, block: B:48:0x0089  */
    /* JADX WARN: Code duplicated, block: B:50:0x008d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0095  */
    /* JADX WARN: Code duplicated, block: B:53:0x0098  */
    /* JADX WARN: Code duplicated, block: B:56:0x009e  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:81:0x00de  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:94:0x0103  */
    /* JADX WARN: Code duplicated, block: B:96:0x0109  */
    /* JADX WARN: Code duplicated, block: B:97:0x010c  */
    public static final void Card(final Function0<Unit> function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        Shape shape2;
        CardColors cardColors2;
        CardElevation cardElevation2;
        int i6;
        BorderStroke borderStroke2;
        int i7;
        int i8;
        int i9;
        int i10;
        CardColors cardColors3;
        Composer composer2;
        CardElevation cardElevationM1547cardElevationaqJV_2Y;
        BorderStroke borderStroke3;
        Modifier modifier3;
        Shape shape3;
        BorderStroke borderStroke4;
        int i11;
        boolean z3;
        MutableInteractionSource mutableInteractionSource2;
        MutableInteractionSource mutableInteractionSource3;
        Composer composer3;
        final CardColors cardColors4;
        final CardElevation cardElevation3;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier4;
        final boolean z4;
        final Shape shape4;
        final BorderStroke borderStroke5;
        Object objRememberedValue;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2024281376);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Card)P(7,6,4,8,1,3!1,5)141@6394L5,142@6439L12,143@6497L15,157@7034L43,160@7163L41,150@6782L422:Card.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i12 = i2 & 2;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        shape2 = shape;
                        int i13 = composerStartRestartGroup.changed(shape2) ? 2048 : 1024;
                        i3 |= i13;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        cardColors2 = cardColors;
                        int i14 = composerStartRestartGroup.changed(cardColors2) ? 16384 : 8192;
                        i3 |= i14;
                    } else {
                        cardColors2 = cardColors;
                    }
                    i3 |= i14;
                } else {
                    cardColors2 = cardColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        cardElevation2 = cardElevation;
                        int i15 = composerStartRestartGroup.changed(cardElevation2) ? 131072 : 65536;
                        i3 |= i15;
                    } else {
                        cardElevation2 = cardElevation;
                    }
                    i3 |= i15;
                } else {
                    cardElevation2 = cardElevation;
                }
                i6 = i2 & 64;
                if (i6 != 0) {
                    if ((1572864 & i) == 0) {
                        borderStroke2 = borderStroke;
                        if (composerStartRestartGroup.changed(borderStroke2)) {
                            i7 = 1048576;
                        } else {
                            i7 = 524288;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 256) != 0) {
                        i3 |= 100663296;
                    } else if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i10 = 67108864;
                        } else {
                            i10 = 33554432;
                        }
                        i3 |= i10;
                    }
                    if ((38347923 & i3) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        boolean z5 = true;
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColors3 = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                            } else {
                                composer2 = composerStartRestartGroup;
                                cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                            }
                            if (i6 != 0) {
                                borderStroke3 = null;
                            } else {
                                borderStroke3 = borderStroke;
                            }
                            if (i8 != 0) {
                                Modifier modifier5 = modifier2;
                                i11 = i3;
                                mutableInteractionSource2 = null;
                                modifier3 = modifier5;
                                shape3 = shape2;
                                borderStroke4 = borderStroke3;
                                z3 = z2;
                            } else {
                                modifier3 = modifier2;
                                shape3 = shape2;
                                borderStroke4 = borderStroke3;
                                i11 = i3;
                                z3 = z2;
                            }
                            composer2.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                            }
                            composer2.startReplaceGroup(1976524431);
                            ComposerKt.sourceInformation(composer2, "149@6738L39");
                            if (mutableInteractionSource2 == null) {
                                ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                                objRememberedValue = composer2.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composer2.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            composer2.endReplaceGroup();
                            composer3 = composer2;
                            SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                    invoke(composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer4, int i16) {
                                    ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                                    if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                                    }
                                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                    ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        composer4.createNode(constructor);
                                    } else {
                                        composer4.useNode();
                                    }
                                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                                    function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    composer4.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            cardColors4 = cardColors3;
                            cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                            mutableInteractionSource4 = mutableInteractionSource2;
                            modifier4 = modifier3;
                            z4 = z3;
                            shape4 = shape3;
                            borderStroke5 = borderStroke4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                            }
                            shape3 = shape2;
                            cardColors3 = cardColors2;
                            z5 = true;
                            borderStroke4 = borderStroke2;
                            composer2 = composerStartRestartGroup;
                            modifier3 = modifier2;
                            z3 = z2;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                            i11 = i3;
                        }
                        mutableInteractionSource2 = mutableInteractionSource;
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                        }
                        composer2.startReplaceGroup(1976524431);
                        ComposerKt.sourceInformation(composer2, "149@6738L39");
                        if (mutableInteractionSource2 == null) {
                            ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                            objRememberedValue = composer2.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composer2.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        composer2.endReplaceGroup();
                        composer3 = composer2;
                        SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i16) {
                                ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                                if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                                }
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                Modifier.Companion companion = Modifier.INSTANCE;
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer4.startReusableNode();
                                if (composer4.getInserting()) {
                                    composer4.createNode(constructor);
                                } else {
                                    composer4.useNode();
                                }
                                Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                                Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                                function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        cardColors4 = cardColors3;
                        cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape4 = shape3;
                        borderStroke5 = borderStroke4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        composer3 = composerStartRestartGroup;
                        modifier4 = modifier2;
                        z4 = z2;
                        shape4 = shape2;
                        cardColors4 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke5 = borderStroke2;
                        mutableInteractionSource4 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i16) {
                                CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                borderStroke2 = borderStroke;
                i8 = i2 & 128;
                if (i8 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i2 & 256) != 0) {
                    i3 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 67108864;
                    } else {
                        i10 = 33554432;
                    }
                    i3 |= i10;
                }
                if ((38347923 & i3) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    boolean z6 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier6 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier6;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier7 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier7;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z6, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                } else {
                    composerStartRestartGroup.startDefaults();
                    boolean z7 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier8 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier8;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier9 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier9;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z7, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColors2 = cardColors;
                    if (composerStartRestartGroup.changed(cardColors2)) {
                    }
                    i3 |= i14;
                } else {
                    cardColors2 = cardColors;
                }
                i3 |= i14;
            } else {
                cardColors2 = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevation2 = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevation2)) {
                    }
                    i3 |= i15;
                } else {
                    cardElevation2 = cardElevation;
                }
                i3 |= i15;
            } else {
                cardElevation2 = cardElevation;
            }
            i6 = i2 & 64;
            if (i6 != 0) {
                if ((1572864 & i) == 0) {
                    borderStroke2 = borderStroke;
                    if (composerStartRestartGroup.changed(borderStroke2)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i2 & 256) != 0) {
                    i3 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 67108864;
                    } else {
                        i10 = 33554432;
                    }
                    i3 |= i10;
                }
                if ((38347923 & i3) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    boolean z8 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier10 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier10;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier11 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier11;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z8, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                } else {
                    composerStartRestartGroup.startDefaults();
                    boolean z9 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier12 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier12;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier13 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier13;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z9, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            borderStroke2 = borderStroke;
            i8 = i2 & 128;
            if (i8 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 67108864;
                } else {
                    i10 = 33554432;
                }
                i3 |= i10;
            }
            if ((38347923 & i3) == 38347922) {
                composerStartRestartGroup.startDefaults();
                boolean z10 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier14 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier14;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier15 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier15;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z10, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            } else {
                composerStartRestartGroup.startDefaults();
                boolean z11 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier16 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier16;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier17 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier17;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z11, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i13;
                } else {
                    shape2 = shape;
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColors2 = cardColors;
                    if (composerStartRestartGroup.changed(cardColors2)) {
                    }
                    i3 |= i14;
                } else {
                    cardColors2 = cardColors;
                }
                i3 |= i14;
            } else {
                cardColors2 = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevation2 = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevation2)) {
                    }
                    i3 |= i15;
                } else {
                    cardElevation2 = cardElevation;
                }
                i3 |= i15;
            } else {
                cardElevation2 = cardElevation;
            }
            i6 = i2 & 64;
            if (i6 != 0) {
                if ((1572864 & i) == 0) {
                    borderStroke2 = borderStroke;
                    if (composerStartRestartGroup.changed(borderStroke2)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
                if ((i2 & 256) != 0) {
                    i3 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 67108864;
                    } else {
                        i10 = 33554432;
                    }
                    i3 |= i10;
                }
                if ((38347923 & i3) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    boolean z12 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier18 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier18;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier19 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier19;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z12, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                } else {
                    composerStartRestartGroup.startDefaults();
                    boolean z13 = true;
                    if ((i & 1) != 0) {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier110 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier110;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    } else {
                        if (i12 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColors3 = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                        } else {
                            composer2 = composerStartRestartGroup;
                            cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                        }
                        if (i6 != 0) {
                            borderStroke3 = null;
                        } else {
                            borderStroke3 = borderStroke;
                        }
                        if (i8 != 0) {
                            Modifier modifier111 = modifier2;
                            i11 = i3;
                            mutableInteractionSource2 = null;
                            modifier3 = modifier111;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            z3 = z2;
                        } else {
                            modifier3 = modifier2;
                            shape3 = shape2;
                            borderStroke4 = borderStroke3;
                            i11 = i3;
                            z3 = z2;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                    }
                    composer2.startReplaceGroup(1976524431);
                    ComposerKt.sourceInformation(composer2, "149@6738L39");
                    if (mutableInteractionSource2 == null) {
                        ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    composer2.endReplaceGroup();
                    composer3 = composer2;
                    SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z13, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                            if ((i16 & 3) == 2 && composer4.getSkipping()) {
                                composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                            }
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                            ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                                composer4.createNode(constructor);
                            } else {
                                composer4.useNode();
                            }
                            Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                            Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    cardColors4 = cardColors3;
                    cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape4 = shape3;
                    borderStroke5 = borderStroke4;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i16) {
                            CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            borderStroke2 = borderStroke;
            i8 = i2 & 128;
            if (i8 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 67108864;
                } else {
                    i10 = 33554432;
                }
                i3 |= i10;
            }
            if ((38347923 & i3) == 38347922) {
                composerStartRestartGroup.startDefaults();
                boolean z14 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier112 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier112;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier113 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier113;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z14, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            } else {
                composerStartRestartGroup.startDefaults();
                boolean z15 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier114 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier114;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier115 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier115;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z15, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i13;
            } else {
                shape2 = shape;
            }
            i3 |= i13;
        } else {
            shape2 = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                cardColors2 = cardColors;
                if (composerStartRestartGroup.changed(cardColors2)) {
                }
                i3 |= i14;
            } else {
                cardColors2 = cardColors;
            }
            i3 |= i14;
        } else {
            cardColors2 = cardColors;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                cardElevation2 = cardElevation;
                if (composerStartRestartGroup.changed(cardElevation2)) {
                }
                i3 |= i15;
            } else {
                cardElevation2 = cardElevation;
            }
            i3 |= i15;
        } else {
            cardElevation2 = cardElevation;
        }
        i6 = i2 & 64;
        if (i6 != 0) {
            if ((1572864 & i) == 0) {
                borderStroke2 = borderStroke;
                if (composerStartRestartGroup.changed(borderStroke2)) {
                    i7 = 1048576;
                } else {
                    i7 = 524288;
                }
                i3 |= i7;
            }
            i8 = i2 & 128;
            if (i8 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 67108864;
                } else {
                    i10 = 33554432;
                }
                i3 |= i10;
            }
            if ((38347923 & i3) == 38347922) {
                composerStartRestartGroup.startDefaults();
                boolean z16 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier116 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier116;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier117 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier117;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z16, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            } else {
                composerStartRestartGroup.startDefaults();
                boolean z17 = true;
                if ((i & 1) != 0) {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier118 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier118;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                } else {
                    if (i12 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColors3 = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                    }
                    if (i6 != 0) {
                        borderStroke3 = null;
                    } else {
                        borderStroke3 = borderStroke;
                    }
                    if (i8 != 0) {
                        Modifier modifier119 = modifier2;
                        i11 = i3;
                        mutableInteractionSource2 = null;
                        modifier3 = modifier119;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        z3 = z2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        borderStroke4 = borderStroke3;
                        i11 = i3;
                        z3 = z2;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
                }
                composer2.startReplaceGroup(1976524431);
                ComposerKt.sourceInformation(composer2, "149@6738L39");
                if (mutableInteractionSource2 == null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                composer2.endReplaceGroup();
                composer3 = composer2;
                SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z17, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                        if ((i16 & 3) == 2 && composer4.getSkipping()) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                            composer4.createNode(constructor);
                        } else {
                            composer4.useNode();
                        }
                        Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                        Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier4 = modifier3;
                z4 = z3;
                shape4 = shape3;
                borderStroke5 = borderStroke4;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i16) {
                        CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        borderStroke2 = borderStroke;
        i8 = i2 & 128;
        if (i8 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                i9 = 8388608;
            } else {
                i9 = 4194304;
            }
            i3 |= i9;
        }
        if ((i2 & 256) != 0) {
            i3 |= 100663296;
        } else if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i10 = 67108864;
            } else {
                i10 = 33554432;
            }
            i3 |= i10;
        }
        if ((38347923 & i3) == 38347922) {
            composerStartRestartGroup.startDefaults();
            boolean z18 = true;
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                }
                if (i6 != 0) {
                    borderStroke3 = null;
                } else {
                    borderStroke3 = borderStroke;
                }
                if (i8 != 0) {
                    Modifier modifier1110 = modifier2;
                    i11 = i3;
                    mutableInteractionSource2 = null;
                    modifier3 = modifier1110;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    z3 = z2;
                } else {
                    modifier3 = modifier2;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    i11 = i3;
                    z3 = z2;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                }
                if (i6 != 0) {
                    borderStroke3 = null;
                } else {
                    borderStroke3 = borderStroke;
                }
                if (i8 != 0) {
                    Modifier modifier1111 = modifier2;
                    i11 = i3;
                    mutableInteractionSource2 = null;
                    modifier3 = modifier1111;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    z3 = z2;
                } else {
                    modifier3 = modifier2;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    i11 = i3;
                    z3 = z2;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
            }
            composer2.startReplaceGroup(1976524431);
            ComposerKt.sourceInformation(composer2, "149@6738L39");
            if (mutableInteractionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            composer2.endReplaceGroup();
            composer3 = composer2;
            SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z18, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i16) {
                    ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                    if ((i16 & 3) == 2 && composer4.getSkipping()) {
                        composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                    ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                        composer4.createNode(constructor);
                    } else {
                        composer4.useNode();
                    }
                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cardColors4 = cardColors3;
            cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
            mutableInteractionSource4 = mutableInteractionSource2;
            modifier4 = modifier3;
            z4 = z3;
            shape4 = shape3;
            borderStroke5 = borderStroke4;
        } else {
            composerStartRestartGroup.startDefaults();
            boolean z19 = true;
            if ((i & 1) != 0) {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                }
                if (i6 != 0) {
                    borderStroke3 = null;
                } else {
                    borderStroke3 = borderStroke;
                }
                if (i8 != 0) {
                    Modifier modifier1112 = modifier2;
                    i11 = i3;
                    mutableInteractionSource2 = null;
                    modifier3 = modifier1112;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    z3 = z2;
                } else {
                    modifier3 = modifier2;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    i11 = i3;
                    z3 = z2;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            } else {
                if (i12 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColors3 = CardDefaults.INSTANCE.cardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColors3 = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    cardElevationM1547cardElevationaqJV_2Y = CardDefaults.INSTANCE.m1547cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1547cardElevationaqJV_2Y = cardElevation;
                }
                if (i6 != 0) {
                    borderStroke3 = null;
                } else {
                    borderStroke3 = borderStroke;
                }
                if (i8 != 0) {
                    Modifier modifier1113 = modifier2;
                    i11 = i3;
                    mutableInteractionSource2 = null;
                    modifier3 = modifier1113;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    z3 = z2;
                } else {
                    modifier3 = modifier2;
                    shape3 = shape2;
                    borderStroke4 = borderStroke3;
                    i11 = i3;
                    z3 = z2;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2024281376, i11, -1, "androidx.compose.material3.Card (Card.kt:147)");
            }
            composer2.startReplaceGroup(1976524431);
            ComposerKt.sourceInformation(composer2, "149@6738L39");
            if (mutableInteractionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart(composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            composer2.endReplaceGroup();
            composer3 = composer2;
            SurfaceKt.m2264Surfaceo_FOJdg(function0, modifier3, z3, shape3, cardColors3.m1539containerColorvNxB06k$material3_release(z3), cardColors3.m1540contentColorvNxB06k$material3_release(z3), 0.0f, cardElevationM1547cardElevationaqJV_2Y.shadowElevation$material3_release(z3, mutableInteractionSource3, composer2, ((i11 >> 6) & 14) | ((i11 >> 9) & 896)).getValue().m6904unboximpl(), borderStroke4, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, z19, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i16) {
                    ComposerKt.sourceInformation(composer4, "C161@7173L25:Card.kt#uh7d8r");
                    if ((i16 & 3) == 2 && composer4.getSkipping()) {
                        composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(776921067, i16, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:161)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                    ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, companion);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                        composer4.createNode(constructor);
                    } else {
                        composer4.useNode();
                    }
                    Composer composerM3454constructorimpl = Updater.m3454constructorimpl(composer4);
                    Updater.m3461setimpl(composerM3454constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3461setimpl(composerM3454constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3454constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3454constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3454constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3454constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3461setimpl(composerM3454constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, composer4, 6);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composer2, 54), composer3, (i11 & 8190) | ((i11 << 6) & 234881024), 6, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cardColors4 = cardColors3;
            cardElevation3 = cardElevationM1547cardElevationaqJV_2Y;
            mutableInteractionSource4 = mutableInteractionSource2;
            modifier4 = modifier3;
            z4 = z3;
            shape4 = shape3;
            borderStroke5 = borderStroke4;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i16) {
                    CardKt.Card(function0, modifier4, z4, shape4, cardColors4, cardElevation3, borderStroke5, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0160  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:78:0x00db A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:91:0x0104  */
    /* JADX WARN: Code duplicated, block: B:92:0x011c  */
    /* JADX WARN: Code duplicated, block: B:95:0x012a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0153  */
    public static final void ElevatedCard(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Shape shape2;
        CardColors cardColors2;
        CardElevation cardElevation2;
        Modifier.Companion companion;
        Shape elevatedShape;
        CardColors cardColorsElevatedCardColors;
        Composer composer2;
        Shape shape3;
        CardColors cardColors3;
        CardElevation cardElevationM1549elevatedCardElevationaqJV_2Y;
        Modifier modifier3;
        Composer composer3;
        final Modifier modifier4;
        final Shape shape4;
        final CardColors cardColors4;
        final CardElevation cardElevation3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(895940201);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ElevatedCard)P(3,4!1,2)195@8668L13,196@8721L20,197@8787L23,200@8868L168:Card.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                shape2 = shape;
                int i5 = composerStartRestartGroup.changed(shape2) ? 32 : 16;
                i3 |= i5;
            } else {
                shape2 = shape;
            }
            i3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                cardColors2 = cardColors;
                int i6 = composerStartRestartGroup.changed(cardColors2) ? 256 : 128;
                i3 |= i6;
            } else {
                cardColors2 = cardColors;
            }
            i3 |= i6;
        } else {
            cardColors2 = cardColors;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                cardElevation2 = cardElevation;
                int i7 = composerStartRestartGroup.changed(cardElevation2) ? 2048 : 1024;
                i3 |= i7;
            } else {
                cardElevation2 = cardElevation;
            }
            i3 |= i7;
        } else {
            cardElevation2 = cardElevation;
        }
        if ((i2 & 16) == 0) {
            if ((i & 24576) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
            }
            if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        i3 &= -113;
                    } else {
                        elevatedShape = shape2;
                    }
                    if ((i2 & 4) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 8) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -7169;
                        modifier3 = companion;
                        shape3 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                        shape3 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                        modifier3 = companion;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(895940201, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:200)");
                }
                Composer composer4 = composer2;
                Card(modifier3, shape3, cardColors3, cardElevationM1549elevatedCardElevationaqJV_2Y, null, function3, composer4, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | ((i3 << 3) & 458752), 0);
                composer3 = composer4;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                shape4 = shape3;
                cardColors4 = cardColors3;
                cardElevation3 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                shape4 = shape2;
                cardColors4 = cardColors2;
                cardElevation3 = cardElevation2;
                composer3 = composerStartRestartGroup;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                        invoke(composer5, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer5, int i8) {
                        CardKt.ElevatedCard(modifier4, shape4, cardColors4, cardElevation3, function3, composer5, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        if ((i3 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    elevatedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -7169;
                    modifier3 = companion;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                    modifier3 = companion;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    elevatedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -7169;
                    modifier3 = companion;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                    modifier3 = companion;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(895940201, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:200)");
            }
            Composer composer5 = composer2;
            Card(modifier3, shape3, cardColors3, cardElevationM1549elevatedCardElevationaqJV_2Y, null, function3, composer5, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | ((i3 << 3) & 458752), 0);
            composer3 = composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            cardColors4 = cardColors3;
            cardElevation3 = cardElevationM1549elevatedCardElevationaqJV_2Y;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    elevatedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -7169;
                    modifier3 = companion;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                    modifier3 = companion;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    elevatedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -7169;
                    modifier3 = companion;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                    shape3 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation2;
                    modifier3 = companion;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(895940201, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:200)");
            }
            Composer composer6 = composer2;
            Card(modifier3, shape3, cardColors3, cardElevationM1549elevatedCardElevationaqJV_2Y, null, function3, composer6, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | ((i3 << 3) & 458752), 0);
            composer3 = composer6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            cardColors4 = cardColors3;
            cardElevation3 = cardElevationM1549elevatedCardElevationaqJV_2Y;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer7, Integer num) {
                    invoke(composer7, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer7, int i8) {
                    CardKt.ElevatedCard(modifier4, shape4, cardColors4, cardElevation3, function3, composer7, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:111:0x0146 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:112:0x0148  */
    /* JADX WARN: Code duplicated, block: B:114:0x014f  */
    /* JADX WARN: Code duplicated, block: B:117:0x0156  */
    /* JADX WARN: Code duplicated, block: B:120:0x0163  */
    /* JADX WARN: Code duplicated, block: B:121:0x016b  */
    /* JADX WARN: Code duplicated, block: B:124:0x0170  */
    /* JADX WARN: Code duplicated, block: B:125:0x0188  */
    /* JADX WARN: Code duplicated, block: B:127:0x018c  */
    /* JADX WARN: Code duplicated, block: B:128:0x0197  */
    /* JADX WARN: Code duplicated, block: B:132:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:135:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:139:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0081  */
    /* JADX WARN: Code duplicated, block: B:48:0x0087  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0093  */
    /* JADX WARN: Code duplicated, block: B:53:0x0096  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:63:0x00af  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:81:0x00de  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:96:0x0112  */
    /* JADX WARN: Code duplicated, block: B:98:0x011f  */
    public static final void ElevatedCard(final Function0<Unit> function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, MutableInteractionSource mutableInteractionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Function0<Unit> function1;
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        Shape elevatedShape;
        CardColors cardColors2;
        CardElevation cardElevationM1549elevatedCardElevationaqJV_2Y;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        int i8;
        CardColors cardColorsElevatedCardColors;
        Composer composer2;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        Composer composer3;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier4;
        final boolean z3;
        final Shape shape2;
        final CardColors cardColors3;
        final CardElevation cardElevation2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1850977784);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ElevatedCard)P(6,5,3,7!1,2,4)248@11071L13,249@11124L20,250@11190L23,254@11328L269:Card.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function1 = function0;
        } else if ((i & 6) == 0) {
            function1 = function0;
            i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            function1 = function0;
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        elevatedShape = shape;
                        int i10 = composerStartRestartGroup.changed(elevatedShape) ? 2048 : 1024;
                        i3 |= i10;
                    } else {
                        elevatedShape = shape;
                    }
                    i3 |= i10;
                } else {
                    elevatedShape = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        cardColors2 = cardColors;
                        int i11 = composerStartRestartGroup.changed(cardColors2) ? 16384 : 8192;
                        i3 |= i11;
                    } else {
                        cardColors2 = cardColors;
                    }
                    i3 |= i11;
                } else {
                    cardColors2 = cardColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                        int i12 = composerStartRestartGroup.changed(cardElevationM1549elevatedCardElevationaqJV_2Y) ? 131072 : 65536;
                        i3 |= i12;
                    } else {
                        cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                }
                i6 = i2 & 64;
                if (i6 != 0) {
                    if ((i & 1572864) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i7 = 1048576;
                        } else {
                            i7 = 524288;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i8 = 8388608;
                            } else {
                                i8 = 4194304;
                            }
                            i3 |= i8;
                        }
                        if ((4793491 & i3) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i9 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z2 = true;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 16) != 0) {
                                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                    i3 &= -57345;
                                } else {
                                    cardColorsElevatedCardColors = cardColors2;
                                }
                                if ((i2 & 32) != 0) {
                                    composer2 = composerStartRestartGroup;
                                    i3 &= -458753;
                                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                                } else {
                                    composer2 = composerStartRestartGroup;
                                }
                                if (i6 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                modifier3 = modifier2;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                composer2 = composerStartRestartGroup;
                                elevatedShape = elevatedShape;
                                cardColorsElevatedCardColors = cardColors2;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevationM1549elevatedCardElevationaqJV_2Y;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = modifier2;
                                z2 = z2;
                            }
                            composer2.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                            }
                            int i13 = i3 << 3;
                            Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i13) | (i13 & 234881024), 0);
                            MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource3;
                            composer3 = composer2;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource4 = mutableInteractionSource5;
                            modifier4 = modifier3;
                            z3 = z2;
                            shape2 = elevatedShape;
                            cardColors3 = cardColorsElevatedCardColors;
                            cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            composer3 = composerStartRestartGroup;
                            modifier4 = modifier2;
                            z3 = z2;
                            shape2 = elevatedShape;
                            cardColors3 = cardColors2;
                            cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                    invoke(composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer4, int i14) {
                                    CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    if ((4793491 & i3) == 4793490) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i14 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i14) | (i14 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource6;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i15 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i15) | (i15 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource7;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i16) {
                                CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((4793491 & i3) == 4793490) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i16 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i16) | (i16 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource8;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i17 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i17) | (i17 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource9;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i18) {
                                CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i18 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i18) | (i18 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource10;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i19 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i19) | (i19 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource11 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource11;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i110) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    elevatedShape = shape;
                    if (composerStartRestartGroup.changed(elevatedShape)) {
                    }
                    i3 |= i10;
                } else {
                    elevatedShape = shape;
                }
                i3 |= i10;
            } else {
                elevatedShape = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColors2 = cardColors;
                    if (composerStartRestartGroup.changed(cardColors2)) {
                    }
                    i3 |= i11;
                } else {
                    cardColors2 = cardColors;
                }
                i3 |= i11;
            } else {
                cardColors2 = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevationM1549elevatedCardElevationaqJV_2Y)) {
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                }
                i3 |= i12;
            } else {
                cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
            }
            i6 = i2 & 64;
            if (i6 != 0) {
                if ((i & 1572864) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((4793491 & i3) == 4793490) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i110 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i110) | (i110 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource12 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource12;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i111 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i111) | (i111 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource13 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource13;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i112) {
                                CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i112 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i112) | (i112 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource14 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource14;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i113 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i113) | (i113 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource15 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource15;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i114) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i114 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i114) | (i114 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource16 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource16;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i115 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i115) | (i115 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource17 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource17;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i116) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((4793491 & i3) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i116 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i116) | (i116 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource18 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource18;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i117 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i117) | (i117 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource19 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource19;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i118) {
                        CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    elevatedShape = shape;
                    if (composerStartRestartGroup.changed(elevatedShape)) {
                    }
                    i3 |= i10;
                } else {
                    elevatedShape = shape;
                }
                i3 |= i10;
            } else {
                elevatedShape = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColors2 = cardColors;
                    if (composerStartRestartGroup.changed(cardColors2)) {
                    }
                    i3 |= i11;
                } else {
                    cardColors2 = cardColors;
                }
                i3 |= i11;
            } else {
                cardColors2 = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevationM1549elevatedCardElevationaqJV_2Y)) {
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                }
                i3 |= i12;
            } else {
                cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
            }
            i6 = i2 & 64;
            if (i6 != 0) {
                if ((i & 1572864) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 1048576;
                    } else {
                        i7 = 524288;
                    }
                    i3 |= i7;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i3 |= i8;
                    }
                    if ((4793491 & i3) == 4793490) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i118 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i118) | (i118 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource110 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource110;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                                i3 &= -57345;
                            } else {
                                cardColorsElevatedCardColors = cardColors2;
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                        }
                        int i119 = i3 << 3;
                        Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i119) | (i119 & 234881024), 0);
                        MutableInteractionSource mutableInteractionSource111 = mutableInteractionSource3;
                        composer3 = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource4 = mutableInteractionSource111;
                        modifier4 = modifier3;
                        z3 = z2;
                        shape2 = elevatedShape;
                        cardColors3 = cardColorsElevatedCardColors;
                        cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i1110) {
                                CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1110 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1110) | (i1110 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource112 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource112;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1111 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1111) | (i1111 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource113 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource113;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i1112) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1112 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1112) | (i1112 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource114 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource114;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1113 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1113) | (i1113 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource115 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource115;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i1114) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((4793491 & i3) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i1114 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1114) | (i1114 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource116 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource116;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i1115 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1115) | (i1115 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource117 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource117;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i1116) {
                        CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                elevatedShape = shape;
                if (composerStartRestartGroup.changed(elevatedShape)) {
                }
                i3 |= i10;
            } else {
                elevatedShape = shape;
            }
            i3 |= i10;
        } else {
            elevatedShape = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                cardColors2 = cardColors;
                if (composerStartRestartGroup.changed(cardColors2)) {
                }
                i3 |= i11;
            } else {
                cardColors2 = cardColors;
            }
            i3 |= i11;
        } else {
            cardColors2 = cardColors;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
                if (composerStartRestartGroup.changed(cardElevationM1549elevatedCardElevationaqJV_2Y)) {
                }
                i3 |= i12;
            } else {
                cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
            }
            i3 |= i12;
        } else {
            cardElevationM1549elevatedCardElevationaqJV_2Y = cardElevation;
        }
        i6 = i2 & 64;
        if (i6 != 0) {
            if ((i & 1572864) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i7 = 1048576;
                } else {
                    i7 = 524288;
                }
                i3 |= i7;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i3 |= i8;
                }
                if ((4793491 & i3) == 4793490) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1116 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1116) | (i1116 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource118 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource118;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        } else {
                            cardColorsElevatedCardColors = cardColors2;
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                    }
                    int i1117 = i3 << 3;
                    Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1117) | (i1117 & 234881024), 0);
                    MutableInteractionSource mutableInteractionSource119 = mutableInteractionSource3;
                    composer3 = composer2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource4 = mutableInteractionSource119;
                    modifier4 = modifier3;
                    z3 = z2;
                    shape2 = elevatedShape;
                    cardColors3 = cardColorsElevatedCardColors;
                    cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i1118) {
                            CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            if ((4793491 & i3) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i1118 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1118) | (i1118 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource1110 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource1110;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i1119 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i1119) | (i1119 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource1111 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource1111;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i11110) {
                        CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i3 |= i8;
            }
            if ((4793491 & i3) == 4793490) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i11110 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i11110) | (i11110 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource1112 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource1112;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    } else {
                        cardColorsElevatedCardColors = cardColors2;
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
                }
                int i11111 = i3 << 3;
                Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i11111) | (i11111 & 234881024), 0);
                MutableInteractionSource mutableInteractionSource1113 = mutableInteractionSource3;
                composer3 = composer2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource4 = mutableInteractionSource1113;
                modifier4 = modifier3;
                z3 = z2;
                shape2 = elevatedShape;
                cardColors3 = cardColorsElevatedCardColors;
                cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i11112) {
                        CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 12582912;
        if ((4793491 & i3) == 4793490) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
            }
            int i11112 = i3 << 3;
            Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i11112) | (i11112 & 234881024), 0);
            MutableInteractionSource mutableInteractionSource1114 = mutableInteractionSource3;
            composer3 = composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource4 = mutableInteractionSource1114;
            modifier4 = modifier3;
            z3 = z2;
            shape2 = elevatedShape;
            cardColors3 = cardColorsElevatedCardColors;
            cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    elevatedShape = CardDefaults.INSTANCE.getElevatedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    cardColorsElevatedCardColors = CardDefaults.INSTANCE.elevatedCardColors(composerStartRestartGroup, 6);
                    i3 &= -57345;
                } else {
                    cardColorsElevatedCardColors = cardColors2;
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1549elevatedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1549elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1850977784, i3, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
            }
            int i11113 = i3 << 3;
            Card(function1, modifier3, z2, elevatedShape, cardColorsElevatedCardColors, cardElevationM1549elevatedCardElevationaqJV_2Y, null, mutableInteractionSource3, function3, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (29360128 & i11113) | (i11113 & 234881024), 0);
            MutableInteractionSource mutableInteractionSource1115 = mutableInteractionSource3;
            composer3 = composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource4 = mutableInteractionSource1115;
            modifier4 = modifier3;
            z3 = z2;
            shape2 = elevatedShape;
            cardColors3 = cardColorsElevatedCardColors;
            cardElevation2 = cardElevationM1549elevatedCardElevationaqJV_2Y;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i11114) {
                    CardKt.ElevatedCard(function0, modifier4, z3, shape2, cardColors3, cardElevation2, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0121  */
    /* JADX WARN: Code duplicated, block: B:102:0x012a  */
    /* JADX WARN: Code duplicated, block: B:105:0x012f  */
    /* JADX WARN: Code duplicated, block: B:106:0x0143  */
    /* JADX WARN: Code duplicated, block: B:109:0x0149  */
    /* JADX WARN: Code duplicated, block: B:110:0x015b  */
    /* JADX WARN: Code duplicated, block: B:113:0x016a  */
    /* JADX WARN: Code duplicated, block: B:116:0x0184  */
    /* JADX WARN: Code duplicated, block: B:120:0x0192  */
    /* JADX WARN: Code duplicated, block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:76:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x0108  */
    /* JADX WARN: Code duplicated, block: B:94:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0113  */
    /* JADX WARN: Code duplicated, block: B:98:0x011c  */
    public static final void OutlinedCard(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Shape shape2;
        CardColors cardColors2;
        CardElevation cardElevation2;
        BorderStroke borderStroke2;
        Modifier.Companion companion;
        Shape outlinedShape;
        CardColors cardColorsOutlinedCardColors;
        Composer composer2;
        CardElevation cardElevationM1551outlinedCardElevationaqJV_2Y;
        BorderStroke borderStrokeOutlinedCardBorder;
        Modifier modifier3;
        Shape shape3;
        CardColors cardColors3;
        CardElevation cardElevation3;
        Composer composer3;
        final Modifier modifier4;
        final Shape shape4;
        final CardColors cardColors4;
        final CardElevation cardElevation4;
        final BorderStroke borderStroke3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(740336179);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OutlinedCard)P(4,5,1,3)297@13151L13,298@13204L20,299@13270L23,300@13335L20,303@13413L170:Card.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                shape2 = shape;
                int i5 = composerStartRestartGroup.changed(shape2) ? 32 : 16;
                i3 |= i5;
            } else {
                shape2 = shape;
            }
            i3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i2 & 4) == 0) {
                cardColors2 = cardColors;
                int i6 = composerStartRestartGroup.changed(cardColors2) ? 256 : 128;
                i3 |= i6;
            } else {
                cardColors2 = cardColors;
            }
            i3 |= i6;
        } else {
            cardColors2 = cardColors;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                cardElevation2 = cardElevation;
                int i7 = composerStartRestartGroup.changed(cardElevation2) ? 2048 : 1024;
                i3 |= i7;
            } else {
                cardElevation2 = cardElevation;
            }
            i3 |= i7;
        } else {
            cardElevation2 = cardElevation;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                borderStroke2 = borderStroke;
                int i8 = composerStartRestartGroup.changed(borderStroke2) ? 16384 : 8192;
                i3 |= i8;
            } else {
                borderStroke2 = borderStroke;
            }
            i3 |= i8;
        } else {
            borderStroke2 = borderStroke;
        }
        if ((i2 & 32) == 0) {
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
            }
            if ((74899 & i3) == 74898 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        i3 &= -113;
                    } else {
                        outlinedShape = shape2;
                    }
                    if ((i2 & 4) != 0) {
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        cardColorsOutlinedCardColors = cardColors2;
                    }
                    if ((i2 & 8) != 0) {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                        i3 &= -7169;
                    } else {
                        composer2 = composerStartRestartGroup;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation2;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        modifier3 = companion;
                        shape3 = outlinedShape;
                        cardColors3 = cardColorsOutlinedCardColors;
                        cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(false, composer2, 48, 1);
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                        modifier3 = companion;
                        shape3 = outlinedShape;
                        cardColors3 = cardColorsOutlinedCardColors;
                        cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStrokeOutlinedCardBorder = borderStroke2;
                    composer2 = composerStartRestartGroup;
                    modifier3 = modifier2;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(740336179, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:303)");
                }
                Composer composer4 = composer2;
                Card(modifier3, shape3, cardColors3, cardElevation3, borderStrokeOutlinedCardBorder, function3, composer4, 524286 & i3, 0);
                composer3 = composer4;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                shape4 = shape3;
                cardColors4 = cardColors3;
                cardElevation4 = cardElevation3;
                borderStroke3 = borderStrokeOutlinedCardBorder;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                shape4 = shape2;
                cardColors4 = cardColors2;
                cardElevation4 = cardElevation2;
                borderStroke3 = borderStroke2;
                composer3 = composerStartRestartGroup;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                        invoke(composer5, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer5, int i9) {
                        CardKt.OutlinedCard(modifier4, shape4, cardColors4, cardElevation4, borderStroke3, function3, composer5, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        if ((74899 & i3) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    outlinedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsOutlinedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    i3 &= -7169;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation2;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(false, composer2, 48, 1);
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    outlinedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsOutlinedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    i3 &= -7169;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation2;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(false, composer2, 48, 1);
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(740336179, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:303)");
            }
            Composer composer5 = composer2;
            Card(modifier3, shape3, cardColors3, cardElevation3, borderStrokeOutlinedCardBorder, function3, composer5, 524286 & i3, 0);
            composer3 = composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            cardColors4 = cardColors3;
            cardElevation4 = cardElevation3;
            borderStroke3 = borderStrokeOutlinedCardBorder;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    outlinedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsOutlinedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    i3 &= -7169;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation2;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(false, composer2, 48, 1);
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
            } else {
                if (i4 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i2 & 2) != 0) {
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    i3 &= -113;
                } else {
                    outlinedShape = shape2;
                }
                if ((i2 & 4) != 0) {
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    i3 &= -897;
                } else {
                    cardColorsOutlinedCardColors = cardColors2;
                }
                if ((i2 & 8) != 0) {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer2, 1572864, 63);
                    i3 &= -7169;
                } else {
                    composer2 = composerStartRestartGroup;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation2;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(false, composer2, 48, 1);
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                    modifier3 = companion;
                    shape3 = outlinedShape;
                    cardColors3 = cardColorsOutlinedCardColors;
                    cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(740336179, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:303)");
            }
            Composer composer6 = composer2;
            Card(modifier3, shape3, cardColors3, cardElevation3, borderStrokeOutlinedCardBorder, function3, composer6, 524286 & i3, 0);
            composer3 = composer6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            cardColors4 = cardColors3;
            cardElevation4 = cardElevation3;
            borderStroke3 = borderStrokeOutlinedCardBorder;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer7, Integer num) {
                    invoke(composer7, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer7, int i9) {
                    CardKt.OutlinedCard(modifier4, shape4, cardColors4, cardElevation4, borderStroke3, function3, composer7, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x011c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0131  */
    /* JADX WARN: Code duplicated, block: B:109:0x0141  */
    /* JADX WARN: Code duplicated, block: B:125:0x0170 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:126:0x0172  */
    /* JADX WARN: Code duplicated, block: B:128:0x0179  */
    /* JADX WARN: Code duplicated, block: B:131:0x0180  */
    /* JADX WARN: Code duplicated, block: B:134:0x018d  */
    /* JADX WARN: Code duplicated, block: B:137:0x019a  */
    /* JADX WARN: Code duplicated, block: B:138:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:141:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:142:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:144:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:149:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:152:0x0207  */
    /* JADX WARN: Code duplicated, block: B:156:0x021d  */
    /* JADX WARN: Code duplicated, block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0081  */
    /* JADX WARN: Code duplicated, block: B:48:0x0087  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0093  */
    /* JADX WARN: Code duplicated, block: B:53:0x0096  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:63:0x00af  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:82:0x00de  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:95:0x0100  */
    /* JADX WARN: Code duplicated, block: B:97:0x0108  */
    /* JADX WARN: Code duplicated, block: B:98:0x010b  */
    public static final void OutlinedCard(final Function0<Unit> function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        Shape outlinedShape;
        CardColors cardColorsOutlinedCardColors;
        CardElevation cardElevationM1551outlinedCardElevationaqJV_2Y;
        BorderStroke borderStroke2;
        int i6;
        MutableInteractionSource mutableInteractionSource2;
        int i7;
        int i8;
        Composer composer2;
        BorderStroke borderStrokeOutlinedCardBorder;
        MutableInteractionSource mutableInteractionSource3;
        BorderStroke borderStroke3;
        Modifier modifier3;
        boolean z3;
        Shape shape2;
        CardColors cardColors2;
        CardElevation cardElevation2;
        Composer composer3;
        final Modifier modifier4;
        final boolean z4;
        final Shape shape3;
        final CardColors cardColors3;
        final CardElevation cardElevation3;
        final BorderStroke borderStroke4;
        final MutableInteractionSource mutableInteractionSource4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-727137250);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OutlinedCard)P(7,6,4,8,1,3!1,5)352@15710L13,353@15763L20,354@15829L23,355@15894L27,359@16036L271:Card.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        outlinedShape = shape;
                        int i10 = composerStartRestartGroup.changed(outlinedShape) ? 2048 : 1024;
                        i3 |= i10;
                    } else {
                        outlinedShape = shape;
                    }
                    i3 |= i10;
                } else {
                    outlinedShape = shape;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        cardColorsOutlinedCardColors = cardColors;
                        int i11 = composerStartRestartGroup.changed(cardColorsOutlinedCardColors) ? 16384 : 8192;
                        i3 |= i11;
                    } else {
                        cardColorsOutlinedCardColors = cardColors;
                    }
                    i3 |= i11;
                } else {
                    cardColorsOutlinedCardColors = cardColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                        int i12 = composerStartRestartGroup.changed(cardElevationM1551outlinedCardElevationaqJV_2Y) ? 131072 : 65536;
                        i3 |= i12;
                    } else {
                        cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        borderStroke2 = borderStroke;
                        int i13 = composerStartRestartGroup.changed(borderStroke2) ? 1048576 : 524288;
                        i3 |= i13;
                    } else {
                        borderStroke2 = borderStroke;
                    }
                    i3 |= i13;
                } else {
                    borderStroke2 = borderStroke;
                }
                i6 = i2 & 128;
                if (i6 != 0) {
                    if ((12582912 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i7 = 8388608;
                        } else {
                            i7 = 4194304;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i8 = 67108864;
                            } else {
                                i8 = 33554432;
                            }
                            i3 |= i8;
                        }
                        if ((i3 & 38347923) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i9 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z2 = true;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    composer2 = composerStartRestartGroup;
                                    i3 &= -458753;
                                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                                } else {
                                    composer2 = composerStartRestartGroup;
                                }
                                if ((i2 & 64) != 0) {
                                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                    i3 = (-3670017) & i3;
                                } else {
                                    borderStrokeOutlinedCardBorder = borderStroke;
                                }
                                if (i6 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                borderStroke3 = borderStrokeOutlinedCardBorder;
                                modifier3 = modifier2;
                                z3 = z2;
                                shape2 = outlinedShape;
                                cardColors2 = cardColorsOutlinedCardColors;
                                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                composer2 = composerStartRestartGroup;
                                z3 = z2;
                                cardColors2 = cardColorsOutlinedCardColors;
                                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                                borderStroke3 = borderStroke2;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = modifier2;
                                shape2 = outlinedShape;
                            }
                            composer2.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                            }
                            Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3 = composer2;
                            modifier4 = modifier3;
                            z4 = z3;
                            shape3 = shape2;
                            cardColors3 = cardColors2;
                            cardElevation3 = cardElevation2;
                            borderStroke4 = borderStroke3;
                            mutableInteractionSource4 = mutableInteractionSource3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            composer3 = composerStartRestartGroup;
                            modifier4 = modifier2;
                            z4 = z2;
                            shape3 = outlinedShape;
                            cardColors3 = cardColorsOutlinedCardColors;
                            cardElevation3 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                            borderStroke4 = borderStroke2;
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                    invoke(composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer4, int i14) {
                                    CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i3 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i14) {
                                CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 12582912;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 67108864;
                        } else {
                            i8 = 33554432;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i14) {
                                CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    outlinedShape = shape;
                    if (composerStartRestartGroup.changed(outlinedShape)) {
                    }
                    i3 |= i10;
                } else {
                    outlinedShape = shape;
                }
                i3 |= i10;
            } else {
                outlinedShape = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColorsOutlinedCardColors = cardColors;
                    if (composerStartRestartGroup.changed(cardColorsOutlinedCardColors)) {
                    }
                    i3 |= i11;
                } else {
                    cardColorsOutlinedCardColors = cardColors;
                }
                i3 |= i11;
            } else {
                cardColorsOutlinedCardColors = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevationM1551outlinedCardElevationaqJV_2Y)) {
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                }
                i3 |= i12;
            } else {
                cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    borderStroke2 = borderStroke;
                    if (composerStartRestartGroup.changed(borderStroke2)) {
                    }
                    i3 |= i13;
                } else {
                    borderStroke2 = borderStroke;
                }
                i3 |= i13;
            } else {
                borderStroke2 = borderStroke;
            }
            i6 = i2 & 128;
            if (i6 != 0) {
                if ((12582912 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 8388608;
                    } else {
                        i7 = 4194304;
                    }
                    i3 |= i7;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 67108864;
                        } else {
                            i8 = 33554432;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i14) {
                                CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 67108864;
                    } else {
                        i8 = 33554432;
                    }
                    i3 |= i8;
                }
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i14) {
                        CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    outlinedShape = shape;
                    if (composerStartRestartGroup.changed(outlinedShape)) {
                    }
                    i3 |= i10;
                } else {
                    outlinedShape = shape;
                }
                i3 |= i10;
            } else {
                outlinedShape = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    cardColorsOutlinedCardColors = cardColors;
                    if (composerStartRestartGroup.changed(cardColorsOutlinedCardColors)) {
                    }
                    i3 |= i11;
                } else {
                    cardColorsOutlinedCardColors = cardColors;
                }
                i3 |= i11;
            } else {
                cardColorsOutlinedCardColors = cardColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                    if (composerStartRestartGroup.changed(cardElevationM1551outlinedCardElevationaqJV_2Y)) {
                    }
                    i3 |= i12;
                } else {
                    cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                }
                i3 |= i12;
            } else {
                cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    borderStroke2 = borderStroke;
                    if (composerStartRestartGroup.changed(borderStroke2)) {
                    }
                    i3 |= i13;
                } else {
                    borderStroke2 = borderStroke;
                }
                i3 |= i13;
            } else {
                borderStroke2 = borderStroke;
            }
            i6 = i2 & 128;
            if (i6 != 0) {
                if ((12582912 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i7 = 8388608;
                    } else {
                        i7 = 4194304;
                    }
                    i3 |= i7;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 67108864;
                        } else {
                            i8 = 33554432;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 38347923) == 38347922) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        } else {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                                outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                composer2 = composerStartRestartGroup;
                                i3 &= -458753;
                                cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                            } else {
                                composer2 = composerStartRestartGroup;
                            }
                            if ((i2 & 64) != 0) {
                                borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                                i3 = (-3670017) & i3;
                            } else {
                                borderStrokeOutlinedCardBorder = borderStroke;
                            }
                            if (i6 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            borderStroke3 = borderStrokeOutlinedCardBorder;
                            modifier3 = modifier2;
                            z3 = z2;
                            shape2 = outlinedShape;
                            cardColors2 = cardColorsOutlinedCardColors;
                            cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                        }
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                        }
                        Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3 = composer2;
                        modifier4 = modifier3;
                        z4 = z3;
                        shape3 = shape2;
                        cardColors3 = cardColors2;
                        cardElevation3 = cardElevation2;
                        borderStroke4 = borderStroke3;
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i14) {
                                CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 12582912;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 67108864;
                    } else {
                        i8 = 33554432;
                    }
                    i3 |= i8;
                }
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i14) {
                        CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                outlinedShape = shape;
                if (composerStartRestartGroup.changed(outlinedShape)) {
                }
                i3 |= i10;
            } else {
                outlinedShape = shape;
            }
            i3 |= i10;
        } else {
            outlinedShape = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                cardColorsOutlinedCardColors = cardColors;
                if (composerStartRestartGroup.changed(cardColorsOutlinedCardColors)) {
                }
                i3 |= i11;
            } else {
                cardColorsOutlinedCardColors = cardColors;
            }
            i3 |= i11;
        } else {
            cardColorsOutlinedCardColors = cardColors;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
                if (composerStartRestartGroup.changed(cardElevationM1551outlinedCardElevationaqJV_2Y)) {
                }
                i3 |= i12;
            } else {
                cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
            }
            i3 |= i12;
        } else {
            cardElevationM1551outlinedCardElevationaqJV_2Y = cardElevation;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                borderStroke2 = borderStroke;
                if (composerStartRestartGroup.changed(borderStroke2)) {
                }
                i3 |= i13;
            } else {
                borderStroke2 = borderStroke;
            }
            i3 |= i13;
        } else {
            borderStroke2 = borderStroke;
        }
        i6 = i2 & 128;
        if (i6 != 0) {
            if ((12582912 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i7 = 8388608;
                } else {
                    i7 = 4194304;
                }
                i3 |= i7;
            }
            if ((i2 & 256) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 67108864;
                    } else {
                        i8 = 33554432;
                    }
                    i3 |= i8;
                }
                if ((i3 & 38347923) == 38347922) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                            cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            composer2 = composerStartRestartGroup;
                            i3 &= -458753;
                            cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                        } else {
                            composer2 = composerStartRestartGroup;
                        }
                        if ((i2 & 64) != 0) {
                            borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                            i3 = (-3670017) & i3;
                        } else {
                            borderStrokeOutlinedCardBorder = borderStroke;
                        }
                        if (i6 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        borderStroke3 = borderStrokeOutlinedCardBorder;
                        modifier3 = modifier2;
                        z3 = z2;
                        shape2 = outlinedShape;
                        cardColors2 = cardColorsOutlinedCardColors;
                        cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                    }
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                    }
                    Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3 = composer2;
                    modifier4 = modifier3;
                    z4 = z3;
                    shape3 = shape2;
                    cardColors3 = cardColors2;
                    cardElevation3 = cardElevation2;
                    borderStroke4 = borderStroke3;
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                            invoke(composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer4, int i14) {
                            CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i14) {
                        CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 12582912;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i2 & 256) != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 67108864;
                } else {
                    i8 = 33554432;
                }
                i3 |= i8;
            }
            if ((i3 & 38347923) == 38347922) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                        cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        composer2 = composerStartRestartGroup;
                        i3 &= -458753;
                        cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    if ((i2 & 64) != 0) {
                        borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                        i3 = (-3670017) & i3;
                    } else {
                        borderStrokeOutlinedCardBorder = borderStroke;
                    }
                    if (i6 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    borderStroke3 = borderStrokeOutlinedCardBorder;
                    modifier3 = modifier2;
                    z3 = z2;
                    shape2 = outlinedShape;
                    cardColors2 = cardColorsOutlinedCardColors;
                    cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
                }
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
                }
                Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer3 = composer2;
                modifier4 = modifier3;
                z4 = z3;
                shape3 = shape2;
                cardColors3 = cardColors2;
                cardElevation3 = cardElevation2;
                borderStroke4 = borderStroke3;
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                        invoke(composer4, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer4, int i14) {
                        CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i3 & 38347923) == 38347922) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if ((i2 & 64) != 0) {
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                    i3 = (-3670017) & i3;
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                borderStroke3 = borderStrokeOutlinedCardBorder;
                modifier3 = modifier2;
                z3 = z2;
                shape2 = outlinedShape;
                cardColors2 = cardColorsOutlinedCardColors;
                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if ((i2 & 64) != 0) {
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                    i3 = (-3670017) & i3;
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                borderStroke3 = borderStrokeOutlinedCardBorder;
                modifier3 = modifier2;
                z3 = z2;
                shape2 = outlinedShape;
                cardColors2 = cardColorsOutlinedCardColors;
                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
            }
            Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer3 = composer2;
            modifier4 = modifier3;
            z4 = z3;
            shape3 = shape2;
            cardColors3 = cardColors2;
            cardElevation3 = cardElevation2;
            borderStroke4 = borderStroke3;
            mutableInteractionSource4 = mutableInteractionSource3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if ((i2 & 64) != 0) {
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                    i3 = (-3670017) & i3;
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                borderStroke3 = borderStrokeOutlinedCardBorder;
                modifier3 = modifier2;
                z3 = z2;
                shape2 = outlinedShape;
                cardColors2 = cardColorsOutlinedCardColors;
                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    outlinedShape = CardDefaults.INSTANCE.getOutlinedShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 16) != 0) {
                    i3 &= -57345;
                    cardColorsOutlinedCardColors = CardDefaults.INSTANCE.outlinedCardColors(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    composer2 = composerStartRestartGroup;
                    i3 &= -458753;
                    cardElevationM1551outlinedCardElevationaqJV_2Y = CardDefaults.INSTANCE.m1551outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 1572864, 63);
                } else {
                    composer2 = composerStartRestartGroup;
                }
                if ((i2 & 64) != 0) {
                    borderStrokeOutlinedCardBorder = CardDefaults.INSTANCE.outlinedCardBorder(z2, composer2, ((i3 >> 6) & 14) | 48, 0);
                    i3 = (-3670017) & i3;
                } else {
                    borderStrokeOutlinedCardBorder = borderStroke;
                }
                if (i6 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                borderStroke3 = borderStrokeOutlinedCardBorder;
                modifier3 = modifier2;
                z3 = z2;
                shape2 = outlinedShape;
                cardColors2 = cardColorsOutlinedCardColors;
                cardElevation2 = cardElevationM1551outlinedCardElevationaqJV_2Y;
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727137250, i3, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
            }
            Card(function0, modifier3, z3, shape2, cardColors2, cardElevation2, borderStroke3, mutableInteractionSource3, function3, composer2, i3 & 268435454, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer3 = composer2;
            modifier4 = modifier3;
            z4 = z3;
            shape3 = shape2;
            cardColors3 = cardColors2;
            cardElevation3 = cardElevation2;
            borderStroke4 = borderStroke3;
            mutableInteractionSource4 = mutableInteractionSource3;
        }
        scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i14) {
                    CardKt.OutlinedCard(function0, modifier4, z4, shape3, cardColors3, cardElevation3, borderStroke4, mutableInteractionSource4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
