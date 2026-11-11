package androidx.compose.foundation;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidExternalSurface.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a]\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00052\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0017H\u0003¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\u001aH\u0003¢\u0006\u0002\u0010\u001b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"AndroidEmbeddedExternalSurface", "", "modifier", "Landroidx/compose/ui/Modifier;", "isOpaque", "", "surfaceSize", "Landroidx/compose/ui/unit/IntSize;", "transform", "Landroidx/compose/ui/graphics/Matrix;", "onInit", "Lkotlin/Function1;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Lkotlin/ExtensionFunctionType;", "AndroidEmbeddedExternalSurface-sv6N_fY", "(Landroidx/compose/ui/Modifier;ZJ[FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "AndroidExternalSurface", "zOrder", "Landroidx/compose/foundation/AndroidExternalSurfaceZOrder;", "isSecure", "AndroidExternalSurface-58FFMhA", "(Landroidx/compose/ui/Modifier;ZJIZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberAndroidEmbeddedExternalSurfaceState", "Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "rememberAndroidExternalSurfaceState", "Landroidx/compose/foundation/AndroidExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidExternalSurfaceState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt {
    private static final AndroidExternalSurfaceState rememberAndroidExternalSurfaceState(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -873615933, "C(rememberAndroidExternalSurfaceState)190@7150L24,191@7186L47:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-873615933, i, -1, "androidx.compose.foundation.rememberAndroidExternalSurfaceState (AndroidExternalSurface.android.kt:189)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1983762950, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new AndroidExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidExternalSurfaceState androidExternalSurfaceState = (AndroidExternalSurfaceState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return androidExternalSurfaceState;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0146  */
    /* JADX WARN: Code duplicated, block: B:104:0x014e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0171  */
    /* JADX WARN: Code duplicated, block: B:109:0x0177  */
    /* JADX WARN: Code duplicated, block: B:115:0x0185  */
    /* JADX WARN: Code duplicated, block: B:116:0x0188  */
    /* JADX WARN: Code duplicated, block: B:119:0x0190  */
    /* JADX WARN: Code duplicated, block: B:120:0x0193  */
    /* JADX WARN: Code duplicated, block: B:123:0x019d  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:133:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:137:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0063  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0086  */
    /* JADX WARN: Code duplicated, block: B:48:0x0089  */
    /* JADX WARN: Code duplicated, block: B:50:0x008d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0095  */
    /* JADX WARN: Code duplicated, block: B:53:0x0098  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:74:0x00de  */
    /* JADX WARN: Code duplicated, block: B:81:0x00f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:82:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:88:0x0100  */
    /* JADX WARN: Code duplicated, block: B:90:0x010b  */
    /* JADX WARN: Code duplicated, block: B:92:0x0114  */
    /* JADX WARN: Code duplicated, block: B:95:0x011e  */
    /* JADX WARN: Code duplicated, block: B:98:0x0137  */
    /* JADX WARN: Code duplicated, block: B:99:0x013a  */
    /* JADX WARN: Instruction removed from duplicated block: B:72:0x00d4, please report this as an issue */
    /* JADX INFO: renamed from: AndroidExternalSurface-58FFMhA, reason: not valid java name */
    public static final void m267AndroidExternalSurface58FFMhA(Modifier modifier, boolean z, long j, int i, boolean z2, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        boolean z3;
        long jM7070getZeroYbymL2g;
        int i5;
        int iM263getBehindB_4ceCc;
        int i6;
        int i7;
        boolean z4;
        int i8;
        int i9;
        Modifier.Companion companion;
        final AndroidExternalSurfaceState androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
        boolean z5;
        boolean zChangedInstance;
        Object objRememberedValue;
        boolean z6;
        boolean z7;
        boolean z8;
        long j2;
        int i10;
        boolean z9;
        Object obj;
        final long j3;
        final boolean z10;
        final Modifier modifier3;
        final int i11;
        final boolean z11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(640888974);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AndroidExternalSurface)P(2!1,4:c#ui.unit.IntSize,5:c#foundation.AndroidExternalSurfaceZOrder)288@12167L37,291@12241L150,299@12462L774,290@12210L1032:AndroidExternalSurface.android.kt#71ulvw");
        int i13 = i3 & 1;
        if (i13 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        int i14 = i3 & 2;
        if (i14 == 0) {
            if ((i2 & 48) == 0) {
                z3 = z;
                i4 |= composerStartRestartGroup.changed(z3) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                jM7070getZeroYbymL2g = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) {
                    i12 = 128;
                } else {
                    i12 = 256;
                }
                i4 |= i12;
            } else {
                jM7070getZeroYbymL2g = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    iM263getBehindB_4ceCc = i;
                    if (composerStartRestartGroup.changed(iM263getBehindB_4ceCc)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 16;
                if (i7 != 0) {
                    if ((i2 & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i4 |= i8;
                    }
                    if ((i3 & 32) != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((i4 & 74899) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i13 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i14 != 0) {
                                z3 = true;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                            }
                            if (i5 != 0) {
                                iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                            }
                            if (i7 != 0) {
                                z4 = false;
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            companion = modifier2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                        }
                        androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                        if ((458752 & i4) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final SurfaceView invoke(Context context) {
                                    SurfaceView surfaceView = new SurfaceView(context);
                                    Function1<AndroidExternalSurfaceScope, Unit> function2 = function1;
                                    AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                    function2.invoke(androidExternalSurfaceState);
                                    surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                    return surfaceView;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Function1 function2 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$2 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }
                        };
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                        boolean z12 = (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i4 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256;
                        if ((i4 & 112) == 32) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        boolean z13 = z12 | z6;
                        if ((i4 & 7168) == 2048) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        z8 = z13 | z7 | ((57344 & i4) == 16384);
                        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z8 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            final boolean z14 = z3;
                            final long j4 = jM7070getZeroYbymL2g;
                            final boolean z15 = z4;
                            final int i15 = iM263getBehindB_4ceCc;
                            j2 = j4;
                            i10 = i15;
                            z9 = z15;
                            obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                    invoke2(surfaceView);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SurfaceView surfaceView) {
                                    if (!IntSize.m7063equalsimpl0(j4, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                        surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j4), IntSize.m7064getHeightimpl(j4));
                                    } else {
                                        surfaceView.getHolder().setSizeFromLayout();
                                    }
                                    surfaceView.getHolder().setFormat(z14 ? -1 : -3);
                                    int i16 = i15;
                                    if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(false);
                                    } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                        surfaceView.setZOrderMediaOverlay(true);
                                    } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(true);
                                    }
                                    surfaceView.setSecure(z15);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        } else {
                            obj = objRememberedValue2;
                            j2 = jM7070getZeroYbymL2g;
                            z9 = z4;
                            i10 = iM263getBehindB_4ceCc;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifier4 = companion;
                        AndroidView_androidKt.AndroidView(function2, modifier4, androidExternalSurface_androidKt$AndroidExternalSurface$2, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j3 = j2;
                        z10 = z3;
                        modifier3 = modifier4;
                        i11 = i10;
                        z11 = z9;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z10 = z3;
                        j3 = jM7070getZeroYbymL2g;
                        z11 = z4;
                        i11 = iM263getBehindB_4ceCc;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                            public final void invoke(Composer composer2, int i16) {
                                AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= 24576;
                z4 = z2;
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function3.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function3.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function3 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$3 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z16 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z16 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z17 = z3;
                        final long j5 = jM7070getZeroYbymL2g;
                        final boolean z18 = z4;
                        final int i16 = iM263getBehindB_4ceCc;
                        j2 = j5;
                        i10 = i16;
                        z9 = z18;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j5, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j5), IntSize.m7064getHeightimpl(j5));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z17 ? -1 : -3);
                                int i17 = i16;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i17, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i17, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i17, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z18);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z19 = z3;
                        final long j6 = jM7070getZeroYbymL2g;
                        final boolean z110 = z4;
                        final int i17 = iM263getBehindB_4ceCc;
                        j2 = j6;
                        i10 = i17;
                        z9 = z110;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j6, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j6), IntSize.m7064getHeightimpl(j6));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z19 ? -1 : -3);
                                int i18 = i17;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i18, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i18, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i18, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier5 = companion;
                    AndroidView_androidKt.AndroidView(function3, modifier5, androidExternalSurface_androidKt$AndroidExternalSurface$3, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier5;
                    i11 = i10;
                    z11 = z9;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function4.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function4.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function4 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$4 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z111 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z111 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z112 = z3;
                        final long j7 = jM7070getZeroYbymL2g;
                        final boolean z113 = z4;
                        final int i18 = iM263getBehindB_4ceCc;
                        j2 = j7;
                        i10 = i18;
                        z9 = z113;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j7, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j7), IntSize.m7064getHeightimpl(j7));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z112 ? -1 : -3);
                                int i19 = i18;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i19, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i19, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i19, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z114 = z3;
                        final long j8 = jM7070getZeroYbymL2g;
                        final boolean z115 = z4;
                        final int i19 = iM263getBehindB_4ceCc;
                        j2 = j8;
                        i10 = i19;
                        z9 = z115;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j8, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j8), IntSize.m7064getHeightimpl(j8));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z114 ? -1 : -3);
                                int i110 = i19;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i110, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i110, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i110, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier6 = companion;
                    AndroidView_androidKt.AndroidView(function4, modifier6, androidExternalSurface_androidKt$AndroidExternalSurface$4, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier6;
                    i11 = i10;
                    z11 = z9;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                        public final void invoke(Composer composer2, int i110) {
                            AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            iM263getBehindB_4ceCc = i;
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function5.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function5.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function5 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$5 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z116 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z116 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z117 = z3;
                        final long j9 = jM7070getZeroYbymL2g;
                        final boolean z118 = z4;
                        final int i110 = iM263getBehindB_4ceCc;
                        j2 = j9;
                        i10 = i110;
                        z9 = z118;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j9, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j9), IntSize.m7064getHeightimpl(j9));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z117 ? -1 : -3);
                                int i111 = i110;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i111, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i111, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i111, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z118);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z119 = z3;
                        final long j10 = jM7070getZeroYbymL2g;
                        final boolean z1110 = z4;
                        final int i111 = iM263getBehindB_4ceCc;
                        j2 = j10;
                        i10 = i111;
                        z9 = z1110;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j10, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j10), IntSize.m7064getHeightimpl(j10));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z119 ? -1 : -3);
                                int i112 = i111;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i112, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i112, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i112, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier7 = companion;
                    AndroidView_androidKt.AndroidView(function5, modifier7, androidExternalSurface_androidKt$AndroidExternalSurface$5, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier7;
                    i11 = i10;
                    z11 = z9;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function6 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function6.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function6 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function6.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function6 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$6 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z1111 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z1111 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z1112 = z3;
                        final long j11 = jM7070getZeroYbymL2g;
                        final boolean z1113 = z4;
                        final int i112 = iM263getBehindB_4ceCc;
                        j2 = j11;
                        i10 = i112;
                        z9 = z1113;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j11, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j11), IntSize.m7064getHeightimpl(j11));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1112 ? -1 : -3);
                                int i113 = i112;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i113, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i113, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i113, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z1114 = z3;
                        final long j12 = jM7070getZeroYbymL2g;
                        final boolean z1115 = z4;
                        final int i113 = iM263getBehindB_4ceCc;
                        j2 = j12;
                        i10 = i113;
                        z9 = z1115;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j12, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j12), IntSize.m7064getHeightimpl(j12));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1114 ? -1 : -3);
                                int i114 = i113;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i114, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i114, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i114, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier8 = companion;
                    AndroidView_androidKt.AndroidView(function6, modifier8, androidExternalSurface_androidKt$AndroidExternalSurface$6, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier8;
                    i11 = i10;
                    z11 = z9;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                        public final void invoke(Composer composer2, int i114) {
                            AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function7 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function7.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function7 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function7.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function7 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$7 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1116 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z1116 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z1117 = z3;
                    final long j13 = jM7070getZeroYbymL2g;
                    final boolean z1118 = z4;
                    final int i114 = iM263getBehindB_4ceCc;
                    j2 = j13;
                    i10 = i114;
                    z9 = z1118;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j13, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j13), IntSize.m7064getHeightimpl(j13));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1117 ? -1 : -3);
                            int i115 = i114;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i115, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i115, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i115, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z1119 = z3;
                    final long j14 = jM7070getZeroYbymL2g;
                    final boolean z11110 = z4;
                    final int i115 = iM263getBehindB_4ceCc;
                    j2 = j14;
                    i10 = i115;
                    z9 = z11110;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j14, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j14), IntSize.m7064getHeightimpl(j14));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1119 ? -1 : -3);
                            int i116 = i115;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i116, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i116, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i116, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier9 = companion;
                AndroidView_androidKt.AndroidView(function7, modifier9, androidExternalSurface_androidKt$AndroidExternalSurface$7, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier9;
                i11 = i10;
                z11 = z9;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function8 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function8.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function8 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function8.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function8 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$8 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z11111 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z11111 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z11112 = z3;
                    final long j15 = jM7070getZeroYbymL2g;
                    final boolean z11113 = z4;
                    final int i116 = iM263getBehindB_4ceCc;
                    j2 = j15;
                    i10 = i116;
                    z9 = z11113;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j15, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j15), IntSize.m7064getHeightimpl(j15));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11112 ? -1 : -3);
                            int i117 = i116;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i117, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i117, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i117, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z11114 = z3;
                    final long j16 = jM7070getZeroYbymL2g;
                    final boolean z11115 = z4;
                    final int i117 = iM263getBehindB_4ceCc;
                    j2 = j16;
                    i10 = i117;
                    z9 = z11115;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j16, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j16), IntSize.m7064getHeightimpl(j16));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11114 ? -1 : -3);
                            int i118 = i117;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i118, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i118, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i118, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier10 = companion;
                AndroidView_androidKt.AndroidView(function8, modifier10, androidExternalSurface_androidKt$AndroidExternalSurface$8, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier10;
                i11 = i10;
                z11 = z9;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                    public final void invoke(Composer composer2, int i118) {
                        AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        z3 = z;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            jM7070getZeroYbymL2g = j;
            if ((i3 & 4) == 0) {
                i12 = 128;
            } else {
                i12 = 128;
            }
            i4 |= i12;
        } else {
            jM7070getZeroYbymL2g = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                iM263getBehindB_4ceCc = i;
                if (composerStartRestartGroup.changed(iM263getBehindB_4ceCc)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function9 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function9.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function9 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function9.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function9 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$9 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z11116 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z11116 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z11117 = z3;
                        final long j17 = jM7070getZeroYbymL2g;
                        final boolean z11118 = z4;
                        final int i118 = iM263getBehindB_4ceCc;
                        j2 = j17;
                        i10 = i118;
                        z9 = z11118;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j17, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j17), IntSize.m7064getHeightimpl(j17));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z11117 ? -1 : -3);
                                int i119 = i118;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i119, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i119, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i119, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z11118);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z11119 = z3;
                        final long j18 = jM7070getZeroYbymL2g;
                        final boolean z111110 = z4;
                        final int i119 = iM263getBehindB_4ceCc;
                        j2 = j18;
                        i10 = i119;
                        z9 = z111110;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j18, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j18), IntSize.m7064getHeightimpl(j18));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z11119 ? -1 : -3);
                                int i1110 = i119;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1110, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1110, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1110, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier11 = companion;
                    AndroidView_androidKt.AndroidView(function9, modifier11, androidExternalSurface_androidKt$AndroidExternalSurface$9, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier11;
                    i11 = i10;
                    z11 = z9;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    } else {
                        if (i13 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i4) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function10 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function10.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function10 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function10.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function10 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$10 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z111111 = z12 | z6;
                    if ((i4 & 7168) == 2048) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z8 = z111111 | z7 | ((57344 & i4) == 16384);
                    Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        final boolean z111112 = z3;
                        final long j19 = jM7070getZeroYbymL2g;
                        final boolean z111113 = z4;
                        final int i1110 = iM263getBehindB_4ceCc;
                        j2 = j19;
                        i10 = i1110;
                        z9 = z111113;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j19, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j19), IntSize.m7064getHeightimpl(j19));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111112 ? -1 : -3);
                                int i1111 = i1110;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1111, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1111, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1111, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        final boolean z111114 = z3;
                        final long j110 = jM7070getZeroYbymL2g;
                        final boolean z111115 = z4;
                        final int i1111 = iM263getBehindB_4ceCc;
                        j2 = j110;
                        i10 = i1111;
                        z9 = z111115;
                        obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m7063equalsimpl0(j110, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j110), IntSize.m7064getHeightimpl(j110));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111114 ? -1 : -3);
                                int i1112 = i1111;
                                if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1112, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1112, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1112, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier12 = companion;
                    AndroidView_androidKt.AndroidView(function10, modifier12, androidExternalSurface_androidKt$AndroidExternalSurface$10, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j2;
                    z10 = z3;
                    modifier3 = modifier12;
                    i11 = i10;
                    z11 = z9;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                        public final void invoke(Composer composer2, int i1112) {
                            AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function11 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function11.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function11 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function11.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function11 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$11 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z111116 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z111116 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z111117 = z3;
                    final long j111 = jM7070getZeroYbymL2g;
                    final boolean z111118 = z4;
                    final int i1112 = iM263getBehindB_4ceCc;
                    j2 = j111;
                    i10 = i1112;
                    z9 = z111118;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j111, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j111), IntSize.m7064getHeightimpl(j111));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z111117 ? -1 : -3);
                            int i1113 = i1112;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1113, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1113, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1113, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z111118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z111119 = z3;
                    final long j112 = jM7070getZeroYbymL2g;
                    final boolean z1111110 = z4;
                    final int i1113 = iM263getBehindB_4ceCc;
                    j2 = j112;
                    i10 = i1113;
                    z9 = z1111110;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j112, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j112), IntSize.m7064getHeightimpl(j112));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z111119 ? -1 : -3);
                            int i1114 = i1113;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1114, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1114, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1114, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier13 = companion;
                AndroidView_androidKt.AndroidView(function11, modifier13, androidExternalSurface_androidKt$AndroidExternalSurface$11, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier13;
                i11 = i10;
                z11 = z9;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function12 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function12.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function12 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function12.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function12 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$12 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1111111 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z1111111 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z1111112 = z3;
                    final long j113 = jM7070getZeroYbymL2g;
                    final boolean z1111113 = z4;
                    final int i1114 = iM263getBehindB_4ceCc;
                    j2 = j113;
                    i10 = i1114;
                    z9 = z1111113;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j113, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j113), IntSize.m7064getHeightimpl(j113));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111112 ? -1 : -3);
                            int i1115 = i1114;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1115, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1115, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1115, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z1111114 = z3;
                    final long j114 = jM7070getZeroYbymL2g;
                    final boolean z1111115 = z4;
                    final int i1115 = iM263getBehindB_4ceCc;
                    j2 = j114;
                    i10 = i1115;
                    z9 = z1111115;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j114, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j114), IntSize.m7064getHeightimpl(j114));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111114 ? -1 : -3);
                            int i1116 = i1115;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1116, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1116, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1116, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier14 = companion;
                AndroidView_androidKt.AndroidView(function12, modifier14, androidExternalSurface_androidKt$AndroidExternalSurface$12, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier14;
                i11 = i10;
                z11 = z9;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                    public final void invoke(Composer composer2, int i1116) {
                        AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        iM263getBehindB_4ceCc = i;
        i7 = i3 & 16;
        if (i7 != 0) {
            if ((i2 & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i4 |= i8;
            }
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function13 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function13.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function13 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function13.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function13 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$13 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1111116 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z1111116 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z1111117 = z3;
                    final long j115 = jM7070getZeroYbymL2g;
                    final boolean z1111118 = z4;
                    final int i1116 = iM263getBehindB_4ceCc;
                    j2 = j115;
                    i10 = i1116;
                    z9 = z1111118;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j115, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j115), IntSize.m7064getHeightimpl(j115));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111117 ? -1 : -3);
                            int i1117 = i1116;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1117, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1117, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1117, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z1111119 = z3;
                    final long j116 = jM7070getZeroYbymL2g;
                    final boolean z11111110 = z4;
                    final int i1117 = iM263getBehindB_4ceCc;
                    j2 = j116;
                    i10 = i1117;
                    z9 = z11111110;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j116, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j116), IntSize.m7064getHeightimpl(j116));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111119 ? -1 : -3);
                            int i1118 = i1117;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1118, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1118, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1118, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier15 = companion;
                AndroidView_androidKt.AndroidView(function13, modifier15, androidExternalSurface_androidKt$AndroidExternalSurface$13, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier15;
                i11 = i10;
                z11 = z9;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                } else {
                    if (i13 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i4) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function14 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function14.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function14 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function14.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function14 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$14 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z11111111 = z12 | z6;
                if ((i4 & 7168) == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z8 = z11111111 | z7 | ((57344 & i4) == 16384);
                Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    final boolean z11111112 = z3;
                    final long j117 = jM7070getZeroYbymL2g;
                    final boolean z11111113 = z4;
                    final int i1118 = iM263getBehindB_4ceCc;
                    j2 = j117;
                    i10 = i1118;
                    z9 = z11111113;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j117, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j117), IntSize.m7064getHeightimpl(j117));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111112 ? -1 : -3);
                            int i1119 = i1118;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1119, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1119, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i1119, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    final boolean z11111114 = z3;
                    final long j118 = jM7070getZeroYbymL2g;
                    final boolean z11111115 = z4;
                    final int i1119 = iM263getBehindB_4ceCc;
                    j2 = j118;
                    i10 = i1119;
                    z9 = z11111115;
                    obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m7063equalsimpl0(j118, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j118), IntSize.m7064getHeightimpl(j118));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111114 ? -1 : -3);
                            int i11110 = i1119;
                            if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11110, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11110, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11110, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier16 = companion;
                AndroidView_androidKt.AndroidView(function14, modifier16, androidExternalSurface_androidKt$AndroidExternalSurface$14, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j2;
                z10 = z3;
                modifier3 = modifier16;
                i11 = i10;
                z11 = z9;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                    public final void invoke(Composer composer2, int i11110) {
                        AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        z4 = z2;
        if ((i3 & 32) != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i9 = 131072;
            } else {
                i9 = 65536;
            }
            i4 |= i9;
        }
        if ((i4 & 74899) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i13 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
            } else {
                if (i13 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
            }
            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if ((458752 & i4) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function15 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function15.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function15 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function15.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function15 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$15 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SurfaceView surfaceView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                    invoke2(surfaceView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
            }
            if ((i4 & 112) == 32) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z11111116 = z12 | z6;
            if ((i4 & 7168) == 2048) {
                z7 = true;
            } else {
                z7 = false;
            }
            z8 = z11111116 | z7 | ((57344 & i4) == 16384);
            Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            if (z8) {
                final boolean z11111117 = z3;
                final long j119 = jM7070getZeroYbymL2g;
                final boolean z11111118 = z4;
                final int i11110 = iM263getBehindB_4ceCc;
                j2 = j119;
                i10 = i11110;
                z9 = z11111118;
                obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m7063equalsimpl0(j119, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j119), IntSize.m7064getHeightimpl(j119));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z11111117 ? -1 : -3);
                        int i11111 = i11110;
                        if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11111, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11111, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11111, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z11111118);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                final boolean z11111119 = z3;
                final long j1110 = jM7070getZeroYbymL2g;
                final boolean z111111110 = z4;
                final int i11111 = iM263getBehindB_4ceCc;
                j2 = j1110;
                i10 = i11111;
                z9 = z111111110;
                obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m7063equalsimpl0(j1110, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j1110), IntSize.m7064getHeightimpl(j1110));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z11111119 ? -1 : -3);
                        int i11112 = i11111;
                        if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11112, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11112, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11112, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111110);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier17 = companion;
            AndroidView_androidKt.AndroidView(function15, modifier17, androidExternalSurface_androidKt$AndroidExternalSurface$15, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j2;
            z10 = z3;
            modifier3 = modifier17;
            i11 = i10;
            z11 = z9;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i13 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
            } else {
                if (i13 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM263getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
            }
            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if ((458752 & i4) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            zChangedInstance = z5 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function16 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function16.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function16 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function16.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function16 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$16 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SurfaceView surfaceView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                    invoke2(surfaceView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if (((i4 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
            }
            if ((i4 & 112) == 32) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z111111111 = z12 | z6;
            if ((i4 & 7168) == 2048) {
                z7 = true;
            } else {
                z7 = false;
            }
            z8 = z111111111 | z7 | ((57344 & i4) == 16384);
            Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
            if (z8) {
                final boolean z111111112 = z3;
                final long j1111 = jM7070getZeroYbymL2g;
                final boolean z111111113 = z4;
                final int i11112 = iM263getBehindB_4ceCc;
                j2 = j1111;
                i10 = i11112;
                z9 = z111111113;
                obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m7063equalsimpl0(j1111, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j1111), IntSize.m7064getHeightimpl(j1111));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111112 ? -1 : -3);
                        int i11113 = i11112;
                        if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11113, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11113, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11113, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111113);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                final boolean z111111114 = z3;
                final long j1112 = jM7070getZeroYbymL2g;
                final boolean z111111115 = z4;
                final int i11113 = iM263getBehindB_4ceCc;
                j2 = j1112;
                i10 = i11113;
                z9 = z111111115;
                obj = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m7063equalsimpl0(j1112, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m7065getWidthimpl(j1112), IntSize.m7064getHeightimpl(j1112));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111114 ? -1 : -3);
                        int i11114 = i11113;
                        if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11114, AndroidExternalSurfaceZOrder.INSTANCE.m263getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11114, AndroidExternalSurfaceZOrder.INSTANCE.m264getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m259equalsimpl0(i11114, AndroidExternalSurfaceZOrder.INSTANCE.m265getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111115);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier18 = companion;
            AndroidView_androidKt.AndroidView(function16, modifier18, androidExternalSurface_androidKt$AndroidExternalSurface$16, null, (Function1) obj, composerStartRestartGroup, ((i4 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j2;
            z10 = z3;
            modifier3 = modifier18;
            i11 = i10;
            z11 = z9;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
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

                public final void invoke(Composer composer2, int i11114) {
                    AndroidExternalSurface_androidKt.m267AndroidExternalSurface58FFMhA(modifier3, z10, j3, i11, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    private static final AndroidEmbeddedExternalSurfaceState rememberAndroidEmbeddedExternalSurfaceState(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1057437053, "C(rememberAndroidEmbeddedExternalSurfaceState)384@15036L24,385@15072L55:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1057437053, i, -1, "androidx.compose.foundation.rememberAndroidEmbeddedExternalSurfaceState (AndroidExternalSurface.android.kt:383)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1142294264, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new AndroidEmbeddedExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = (AndroidEmbeddedExternalSurfaceState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return androidEmbeddedExternalSurfaceState;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0147  */
    /* JADX WARN: Code duplicated, block: B:106:0x014c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0151  */
    /* JADX WARN: Code duplicated, block: B:110:0x015d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0169  */
    /* JADX WARN: Code duplicated, block: B:117:0x0195  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:31:0x005e  */
    /* JADX WARN: Code duplicated, block: B:33:0x0062  */
    /* JADX WARN: Code duplicated, block: B:36:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:39:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x0071  */
    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x007d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0080  */
    /* JADX WARN: Code duplicated, block: B:49:0x0089  */
    /* JADX WARN: Code duplicated, block: B:50:0x008c  */
    /* JADX WARN: Code duplicated, block: B:52:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x0098  */
    /* JADX WARN: Code duplicated, block: B:55:0x009b  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:76:0x00de  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:87:0x0102  */
    /* JADX WARN: Code duplicated, block: B:90:0x0124  */
    /* JADX WARN: Code duplicated, block: B:92:0x012a  */
    /* JADX WARN: Code duplicated, block: B:98:0x013c  */
    /* JADX WARN: Code duplicated, block: B:99:0x013e  */
    /* JADX WARN: Instruction removed from duplicated block: B:64:0x00b9, please report this as an issue */
    /* JADX INFO: renamed from: AndroidEmbeddedExternalSurface-sv6N_fY, reason: not valid java name */
    public static final void m266AndroidEmbeddedExternalSurfacesv6N_fY(Modifier modifier, boolean z, long j, final float[] fArr, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        boolean z2;
        long jM7070getZeroYbymL2g;
        int i4;
        Matrix matrixM4400boximpl;
        int i5;
        Function1<? super AndroidExternalSurfaceScope, Unit> function2;
        int i6;
        Modifier.Companion companion;
        final boolean z3;
        final AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
        boolean z4;
        Matrix matrixM4400boximpl2;
        boolean zChangedInstance;
        Object objRememberedValue;
        final long j2;
        final Modifier modifier3;
        final long j3;
        final boolean z5;
        final float[] fArr2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i7;
        Composer composerStartRestartGroup = composer.startRestartGroup(217541314);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AndroidEmbeddedExternalSurface)P(1!1,3:c#ui.unit.IntSize,4:c#ui.graphics.Matrix)455@18917L45,461@19089L639,457@18968L766:AndroidExternalSurface.android.kt#71ulvw");
        int i8 = i2 & 1;
        if (i8 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                jM7070getZeroYbymL2g = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) {
                    i7 = 128;
                } else {
                    i7 = 256;
                }
                i3 |= i7;
            } else {
                jM7070getZeroYbymL2g = j;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (fArr != null) {
                    matrixM4400boximpl = Matrix.m4400boximpl(fArr);
                } else {
                    matrixM4400boximpl = null;
                }
                if (composerStartRestartGroup.changedInstance(matrixM4400boximpl)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i2 & 16) != 0) {
                if ((i & 24576) == 0) {
                    function2 = function1;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i8 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i9 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                        }
                        if (i4 != 0) {
                            z3 = z2;
                            fArr = null;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                        AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                            @Override // kotlin.jvm.functions.Function1
                            public final TextureView invoke(Context context) {
                                return new TextureView(context);
                            }
                        };
                        AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextureView textureView) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                                invoke2(textureView);
                                return Unit.INSTANCE;
                            }
                        };
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                        boolean zChangedInstance2 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                        if ((57344 & i3) == 16384) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z6 = zChangedInstance2 | z4 | ((i3 & 112) == 32);
                        if (fArr != null) {
                            matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                        } else {
                            matrixM4400boximpl2 = null;
                        }
                        zChangedInstance = z6 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            j2 = jM7070getZeroYbymL2g;
                            final Function1<? super AndroidExternalSurfaceScope, Unit> function3 = function2;
                            objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                                    invoke2(textureView);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextureView textureView) {
                                    android.graphics.Matrix matrix;
                                    SurfaceTexture surfaceTexture;
                                    if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                        surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                                    }
                                    androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                                    TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                                    AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                                    if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                        function3.invoke(androidEmbeddedExternalSurfaceState);
                                        textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                                    }
                                    textureView.setOpaque(z3);
                                    float[] fArr3 = fArr;
                                    if (fArr3 != null) {
                                        matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                        AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                                    } else {
                                        matrix = null;
                                    }
                                    textureView.setTransform(matrix);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            j2 = jM7070getZeroYbymL2g;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifier4 = companion;
                        AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1, modifier4, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        j3 = j2;
                        z5 = z3;
                        fArr2 = fArr;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        companion = modifier2;
                    }
                    z3 = z2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                    }
                    androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                    AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                        @Override // kotlin.jvm.functions.Function1
                        public final TextureView invoke(Context context) {
                            return new TextureView(context);
                        }
                    };
                    AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    boolean zChangedInstance3 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                    if ((57344 & i3) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z7 = zChangedInstance3 | z4 | ((i3 & 112) == 32);
                    if (fArr != null) {
                        matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                    } else {
                        matrixM4400boximpl2 = null;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        j2 = jM7070getZeroYbymL2g;
                        final Function1<? super AndroidExternalSurfaceScope, Unit> function4 = function2;
                        objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                                invoke2(textureView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextureView textureView) {
                                android.graphics.Matrix matrix;
                                SurfaceTexture surfaceTexture;
                                if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                    surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                                }
                                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                                TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                                AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                                if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                    function4.invoke(androidEmbeddedExternalSurfaceState);
                                    textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                                }
                                textureView.setOpaque(z3);
                                float[] fArr3 = fArr;
                                if (fArr3 != null) {
                                    matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                    AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                                } else {
                                    matrix = null;
                                }
                                textureView.setTransform(matrix);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        j2 = jM7070getZeroYbymL2g;
                        final Function1<? super AndroidExternalSurfaceScope, Unit> function5 = function2;
                        objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                                invoke2(textureView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextureView textureView) {
                                android.graphics.Matrix matrix;
                                SurfaceTexture surfaceTexture;
                                if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                    surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                                }
                                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                                TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                                AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                                if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                    function5.invoke(androidEmbeddedExternalSurfaceState);
                                    textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                                }
                                textureView.setOpaque(z3);
                                float[] fArr3 = fArr;
                                if (fArr3 != null) {
                                    matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                    AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                                } else {
                                    matrix = null;
                                }
                                textureView.setTransform(matrix);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifier5 = companion;
                    AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3, modifier5, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j3 = j2;
                    z5 = z3;
                    fArr2 = fArr;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    fArr2 = fArr;
                    modifier3 = modifier2;
                    z5 = z2;
                    j3 = jM7070getZeroYbymL2g;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
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
                            AndroidExternalSurface_androidKt.m266AndroidEmbeddedExternalSurfacesv6N_fY(modifier3, z5, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            function2 = function1;
            if ((i3 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$5 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        return new TextureView(context);
                    }
                };
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$6 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance4 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                if ((57344 & i3) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z8 = zChangedInstance4 | z4 | ((i3 & 112) == 32);
                if (fArr != null) {
                    matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                } else {
                    matrixM4400boximpl2 = null;
                }
                zChangedInstance = z8 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function6 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function6.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function7 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function7.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier6 = companion;
                AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$5, modifier6, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$6, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                j3 = j2;
                z5 = z3;
                fArr2 = fArr;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$7 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        return new TextureView(context);
                    }
                };
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$8 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance5 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                if ((57344 & i3) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z9 = zChangedInstance5 | z4 | ((i3 & 112) == 32);
                if (fArr != null) {
                    matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                } else {
                    matrixM4400boximpl2 = null;
                }
                zChangedInstance = z9 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function8 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function8.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function9 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function9.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier7 = companion;
                AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$7, modifier7, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$8, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier7;
                j3 = j2;
                z5 = z3;
                fArr2 = fArr;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
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
                        AndroidExternalSurface_androidKt.m266AndroidEmbeddedExternalSurfacesv6N_fY(modifier3, z5, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        z2 = z;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            jM7070getZeroYbymL2g = j;
            if ((i2 & 4) == 0) {
                i7 = 128;
            } else {
                i7 = 128;
            }
            i3 |= i7;
        } else {
            jM7070getZeroYbymL2g = j;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (fArr != null) {
                matrixM4400boximpl = Matrix.m4400boximpl(fArr);
            } else {
                matrixM4400boximpl = null;
            }
            if (composerStartRestartGroup.changedInstance(matrixM4400boximpl)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i3 |= i5;
        }
        if ((i2 & 16) != 0) {
            if ((i & 24576) == 0) {
                function2 = function1;
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) == 9362) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$9 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        return new TextureView(context);
                    }
                };
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$10 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance6 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                if ((57344 & i3) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z10 = zChangedInstance6 | z4 | ((i3 & 112) == 32);
                if (fArr != null) {
                    matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                } else {
                    matrixM4400boximpl2 = null;
                }
                zChangedInstance = z10 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function10 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function10.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function11 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function11.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier8 = companion;
                AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$9, modifier8, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$10, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier8;
                j3 = j2;
                z5 = z3;
                fArr2 = fArr;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                    }
                    if (i4 != 0) {
                        z3 = z2;
                        fArr = null;
                    } else {
                        z3 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$11 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        return new TextureView(context);
                    }
                };
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$12 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance7 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                if ((57344 & i3) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z11 = zChangedInstance7 | z4 | ((i3 & 112) == 32);
                if (fArr != null) {
                    matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
                } else {
                    matrixM4400boximpl2 = null;
                }
                zChangedInstance = z11 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function12 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function12.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    j2 = jM7070getZeroYbymL2g;
                    final Function1<? super AndroidExternalSurfaceScope, Unit> function13 = function2;
                    objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                            TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                                function13.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                            }
                            textureView.setOpaque(z3);
                            float[] fArr3 = fArr;
                            if (fArr3 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifier9 = companion;
                AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$11, modifier9, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$12, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier9;
                j3 = j2;
                z5 = z3;
                fArr2 = fArr;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
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
                        AndroidExternalSurface_androidKt.m266AndroidEmbeddedExternalSurfacesv6N_fY(modifier3, z5, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        function2 = function1;
        if ((i3 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i4 != 0) {
                    z3 = z2;
                    fArr = null;
                } else {
                    z3 = z2;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i4 != 0) {
                    z3 = z2;
                    fArr = null;
                } else {
                    z3 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
            }
            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$13 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                @Override // kotlin.jvm.functions.Function1
                public final TextureView invoke(Context context) {
                    return new TextureView(context);
                }
            };
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$14 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextureView textureView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                    invoke2(textureView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance8 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
            if ((57344 & i3) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z12 = zChangedInstance8 | z4 | ((i3 & 112) == 32);
            if (fArr != null) {
                matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
            } else {
                matrixM4400boximpl2 = null;
            }
            zChangedInstance = z12 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j2 = jM7070getZeroYbymL2g;
                final Function1<? super AndroidExternalSurfaceScope, Unit> function14 = function2;
                objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                        TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                            function14.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                        }
                        textureView.setOpaque(z3);
                        float[] fArr3 = fArr;
                        if (fArr3 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                j2 = jM7070getZeroYbymL2g;
                final Function1<? super AndroidExternalSurfaceScope, Unit> function15 = function2;
                objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                        TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                            function15.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                        }
                        textureView.setOpaque(z3);
                        float[] fArr3 = fArr;
                        if (fArr3 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier10 = companion;
            AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$13, modifier10, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$14, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier10;
            j3 = j2;
            z5 = z3;
            fArr2 = fArr;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i4 != 0) {
                    z3 = z2;
                    fArr = null;
                } else {
                    z3 = z2;
                }
            } else {
                if (i8 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jM7070getZeroYbymL2g = IntSize.INSTANCE.m7070getZeroYbymL2g();
                }
                if (i4 != 0) {
                    z3 = z2;
                    fArr = null;
                } else {
                    z3 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
            }
            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$15 = new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1
                @Override // kotlin.jvm.functions.Function1
                public final TextureView invoke(Context context) {
                    return new TextureView(context);
                }
            };
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$16 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextureView textureView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                    invoke2(textureView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184054658, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance9 = ((((i3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(jM7070getZeroYbymL2g)) || (i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
            if ((57344 & i3) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z13 = zChangedInstance9 | z4 | ((i3 & 112) == 32);
            if (fArr != null) {
                matrixM4400boximpl2 = Matrix.m4400boximpl(fArr);
            } else {
                matrixM4400boximpl2 = null;
            }
            zChangedInstance = z13 | composerStartRestartGroup.changedInstance(matrixM4400boximpl2);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j2 = jM7070getZeroYbymL2g;
                final Function1<? super AndroidExternalSurfaceScope, Unit> function16 = function2;
                objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                        TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                            function16.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                        }
                        textureView.setOpaque(z3);
                        float[] fArr3 = fArr;
                        if (fArr3 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                j2 = jM7070getZeroYbymL2g;
                final Function1<? super AndroidExternalSurfaceScope, Unit> function17 = function2;
                objRememberedValue = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m7063equalsimpl0(j2, IntSize.INSTANCE.m7070getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m7065getWidthimpl(j2), IntSize.m7064getHeightimpl(j2));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m255setSurfaceSizeozmzZPI(j2);
                        TextureView.SurfaceTextureListener surfaceTextureListener = textureView.getSurfaceTextureListener();
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        if (surfaceTextureListener != androidEmbeddedExternalSurfaceState) {
                            function17.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                        }
                        textureView.setOpaque(z3);
                        float[] fArr3 = fArr;
                        if (fArr3 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix, fArr3);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier11 = companion;
            AndroidView_androidKt.AndroidView(androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$15, modifier11, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$16, null, (Function1) objRememberedValue, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier11;
            j3 = j2;
            z5 = z3;
            fArr2 = fArr;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
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
                    AndroidExternalSurface_androidKt.m266AndroidEmbeddedExternalSurfacesv6N_fY(modifier3, z5, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
