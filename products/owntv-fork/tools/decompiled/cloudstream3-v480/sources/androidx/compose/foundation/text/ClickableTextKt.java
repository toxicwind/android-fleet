package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ClickableText.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001av\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001e\u0010\u0017\u001a\u00020\f*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"ClickableText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "onHover", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "onClick", "ClickableText-03UYbkw", "(Landroidx/compose/ui/text/AnnotatedString;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ClickableText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "containsWithinBounds", "Landroidx/compose/ui/text/MultiParagraph;", "positionOffset", "Landroidx/compose/ui/geometry/Offset;", "containsWithinBounds-Uv8p0NA", "(Landroidx/compose/ui/text/MultiParagraph;J)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClickableTextKt {
    /* JADX WARN: Code duplicated, block: B:101:0x012d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0136  */
    /* JADX WARN: Code duplicated, block: B:104:0x013a  */
    /* JADX WARN: Code duplicated, block: B:106:0x013e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0143  */
    /* JADX WARN: Code duplicated, block: B:110:0x014b  */
    /* JADX WARN: Code duplicated, block: B:113:0x0169  */
    /* JADX WARN: Code duplicated, block: B:116:0x018b  */
    /* JADX WARN: Code duplicated, block: B:117:0x018d  */
    /* JADX WARN: Code duplicated, block: B:120:0x0195  */
    /* JADX WARN: Code duplicated, block: B:122:0x019d  */
    /* JADX WARN: Code duplicated, block: B:125:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:128:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:130:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:133:0x020e  */
    /* JADX WARN: Code duplicated, block: B:137:0x021f  */
    /* JADX WARN: Code duplicated, block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    /* JADX WARN: Code duplicated, block: B:27:0x0052  */
    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Code duplicated, block: B:31:0x005e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0061  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:38:0x006e  */
    /* JADX WARN: Code duplicated, block: B:40:0x0072  */
    /* JADX WARN: Code duplicated, block: B:42:0x007a  */
    /* JADX WARN: Code duplicated, block: B:43:0x007d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0087  */
    /* JADX WARN: Code duplicated, block: B:49:0x008a  */
    /* JADX WARN: Code duplicated, block: B:51:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0096  */
    /* JADX WARN: Code duplicated, block: B:54:0x0099  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:82:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:89:0x0102  */
    /* JADX WARN: Code duplicated, block: B:94:0x0118 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x011a  */
    /* JADX WARN: Code duplicated, block: B:97:0x0121  */
    /* JADX WARN: Code duplicated, block: B:99:0x012a  */
    @Deprecated(message = "Use Text or BasicText and pass an AnnotatedString that contains a LinkAnnotation")
    /* JADX INFO: renamed from: ClickableText-4YKlhWE, reason: not valid java name */
    public static final void m1027ClickableText4YKlhWE(final AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, boolean z, int i, int i2, Function1<? super TextLayoutResult, Unit> function1, final Function1<? super Integer, Unit> function2, Composer composer, final int i3, final int i4) {
        AnnotatedString annotatedString2;
        int i5;
        Modifier modifier2;
        int i6;
        TextStyle textStyle2;
        int i7;
        int i8;
        boolean z2;
        int i9;
        int i10;
        int iM6815getClipgIe3tQ8;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        final ClickableTextKt$ClickableText$1 clickableTextKt$ClickableText$1;
        Object objRememberedValue;
        final MutableState mutableState;
        boolean z3;
        ClickableTextKt$ClickableText$pressIndicator$1$1 clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue;
        boolean z4;
        Object objRememberedValue2;
        Composer composer2;
        final Function1<? super TextLayoutResult, Unit> function3;
        final TextStyle textStyle3;
        final boolean z5;
        final int i18;
        final Modifier modifier3;
        final int i19;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-246609449);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ClickableText)P(7,1,6,5,4:c#ui.text.style.TextOverflow!1,3)84@3976L52,85@4085L184,100@4490L76,93@4275L297:ClickableText.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i3 | 6;
            annotatedString2 = annotatedString;
        } else {
            annotatedString2 = annotatedString;
            if ((i3 & 6) == 0) {
                i5 = (composerStartRestartGroup.changed(annotatedString2) ? 4 : 2) | i3;
            } else {
                i5 = i3;
            }
        }
        int i20 = i4 & 2;
        if (i20 == 0) {
            if ((i3 & 48) == 0) {
                modifier2 = modifier;
                i5 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    textStyle2 = textStyle;
                    if (composerStartRestartGroup.changed(textStyle2)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            iM6815getClipgIe3tQ8 = i;
                            if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                                i11 = 16384;
                            } else {
                                i11 = 8192;
                            }
                            i5 |= i11;
                        }
                        i12 = i4 & 32;
                        if (i12 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i13 = 131072;
                            } else {
                                i13 = 65536;
                            }
                            i5 |= i13;
                        }
                        i14 = i4 & 64;
                        if (i14 != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i15 = 1048576;
                            } else {
                                i15 = 524288;
                            }
                            i5 |= i15;
                        }
                        if ((i4 & 128) != 0) {
                            i5 |= 12582912;
                        } else if ((i3 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function2)) {
                                i16 = 8388608;
                            } else {
                                i16 = 4194304;
                            }
                            i5 |= i16;
                        }
                        if ((i5 & 4793491) == 4793490 || !composerStartRestartGroup.getSkipping()) {
                            if (i20 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                textStyle2 = TextStyle.INSTANCE.getDefault();
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                            }
                            if (i12 != 0) {
                                i17 = Integer.MAX_VALUE;
                            } else {
                                i17 = i2;
                            }
                            if (i14 != 0) {
                                clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextLayoutResult textLayoutResult) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                        invoke2(textLayoutResult);
                                        return Unit.INSTANCE;
                                    }
                                };
                            } else {
                                clickableTextKt$ClickableText$1 = function1;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableState = (MutableState) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                            int i21 = i5;
                            if ((29360128 & i5) == 8388608) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z3 || clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            Modifier modifierThen = modifier2.then(companion.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                            z4 = (i21 & 3670016) == 1048576;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z4 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                        invoke2(textLayoutResult);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextLayoutResult textLayoutResult) {
                                        mutableState.setValue(textLayoutResult);
                                        clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composer2 = composerStartRestartGroup;
                            int i22 = i17;
                            boolean z6 = z2;
                            TextStyle textStyle4 = textStyle2;
                            BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen, textStyle4, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z6, i22, 0, null, null, composer2, (i21 & 58254) | ((i21 << 6) & 458752) | (3670016 & (i21 << 3)), 896);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function3 = clickableTextKt$ClickableText$1;
                            textStyle3 = textStyle4;
                            z5 = z6;
                            i18 = i22;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            i18 = i2;
                            composer2 = composerStartRestartGroup;
                            textStyle3 = textStyle2;
                            z5 = z2;
                            function3 = function1;
                        }
                        modifier3 = modifier2;
                        i19 = iM6815getClipgIe3tQ8;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i23) {
                                    ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    iM6815getClipgIe3tQ8 = i;
                    i12 = i4 & 32;
                    if (i12 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i13 = 131072;
                        } else {
                            i13 = 65536;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 64;
                    if (i14 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 128) != 0) {
                        i5 |= 12582912;
                    } else if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i16 = 8388608;
                        } else {
                            i16 = 4194304;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 4793491) == 4793490) {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i23 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen2 = modifier2.then(companion2.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i23 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i24 = i17;
                        boolean z7 = z2;
                        TextStyle textStyle5 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen2, textStyle5, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z7, i24, 0, null, null, composer2, (i23 & 58254) | ((i23 << 6) & 458752) | (3670016 & (i23 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle5;
                        z5 = z7;
                        i18 = i24;
                    } else {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i25 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen3 = modifier2.then(companion3.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i25 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i26 = i17;
                        boolean z8 = z2;
                        TextStyle textStyle6 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen3, textStyle6, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z8, i26, 0, null, null, composer2, (i25 & 58254) | ((i25 << 6) & 458752) | (3670016 & (i25 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle6;
                        z5 = z8;
                        i18 = i26;
                    }
                    modifier3 = modifier2;
                    i19 = iM6815getClipgIe3tQ8;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i27) {
                                ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 3072;
                z2 = z;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        iM6815getClipgIe3tQ8 = i;
                        if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                            i11 = 16384;
                        } else {
                            i11 = 8192;
                        }
                        i5 |= i11;
                    }
                    i12 = i4 & 32;
                    if (i12 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i13 = 131072;
                        } else {
                            i13 = 65536;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 64;
                    if (i14 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 128) != 0) {
                        i5 |= 12582912;
                    } else if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i16 = 8388608;
                        } else {
                            i16 = 4194304;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 4793491) == 4793490) {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion4 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i27 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen4 = modifier2.then(companion4.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i27 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i28 = i17;
                        boolean z9 = z2;
                        TextStyle textStyle7 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen4, textStyle7, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z9, i28, 0, null, null, composer2, (i27 & 58254) | ((i27 << 6) & 458752) | (3670016 & (i27 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle7;
                        z5 = z9;
                        i18 = i28;
                    } else {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion5 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i29 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen5 = modifier2.then(companion5.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i29 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i210 = i17;
                        boolean z10 = z2;
                        TextStyle textStyle8 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen5, textStyle8, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z10, i210, 0, null, null, composer2, (i29 & 58254) | ((i29 << 6) & 458752) | (3670016 & (i29 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle8;
                        z5 = z10;
                        i18 = i210;
                    }
                    modifier3 = modifier2;
                    i19 = iM6815getClipgIe3tQ8;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i211) {
                                ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                iM6815getClipgIe3tQ8 = i;
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion6 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i211 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen6 = modifier2.then(companion6.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i211 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i212 = i17;
                    boolean z11 = z2;
                    TextStyle textStyle9 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen6, textStyle9, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z11, i212, 0, null, null, composer2, (i211 & 58254) | ((i211 << 6) & 458752) | (3670016 & (i211 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle9;
                    z5 = z11;
                    i18 = i212;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion7 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i213 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen7 = modifier2.then(companion7.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i213 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i214 = i17;
                    boolean z12 = z2;
                    TextStyle textStyle10 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen7, textStyle10, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z12, i214, 0, null, null, composer2, (i213 & 58254) | ((i213 << 6) & 458752) | (3670016 & (i213 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle10;
                    z5 = z12;
                    i18 = i214;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i215) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            textStyle2 = textStyle;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        iM6815getClipgIe3tQ8 = i;
                        if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                            i11 = 16384;
                        } else {
                            i11 = 8192;
                        }
                        i5 |= i11;
                    }
                    i12 = i4 & 32;
                    if (i12 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i13 = 131072;
                        } else {
                            i13 = 65536;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 64;
                    if (i14 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 128) != 0) {
                        i5 |= 12582912;
                    } else if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i16 = 8388608;
                        } else {
                            i16 = 4194304;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 4793491) == 4793490) {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion8 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i215 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen8 = modifier2.then(companion8.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i215 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i216 = i17;
                        boolean z13 = z2;
                        TextStyle textStyle11 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen8, textStyle11, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z13, i216, 0, null, null, composer2, (i215 & 58254) | ((i215 << 6) & 458752) | (3670016 & (i215 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle11;
                        z5 = z13;
                        i18 = i216;
                    } else {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion9 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i217 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen9 = modifier2.then(companion9.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i217 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i218 = i17;
                        boolean z14 = z2;
                        TextStyle textStyle12 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen9, textStyle12, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z14, i218, 0, null, null, composer2, (i217 & 58254) | ((i217 << 6) & 458752) | (3670016 & (i217 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle12;
                        z5 = z14;
                        i18 = i218;
                    }
                    modifier3 = modifier2;
                    i19 = iM6815getClipgIe3tQ8;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i219) {
                                ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                iM6815getClipgIe3tQ8 = i;
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion10 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i219 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen10 = modifier2.then(companion10.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i219 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i2110 = i17;
                    boolean z15 = z2;
                    TextStyle textStyle13 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen10, textStyle13, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z15, i2110, 0, null, null, composer2, (i219 & 58254) | ((i219 << 6) & 458752) | (3670016 & (i219 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle13;
                    z5 = z15;
                    i18 = i2110;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion11 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i2111 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen11 = modifier2.then(companion11.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i2111 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i2112 = i17;
                    boolean z16 = z2;
                    TextStyle textStyle14 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen11, textStyle14, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z16, i2112, 0, null, null, composer2, (i2111 & 58254) | ((i2111 << 6) & 458752) | (3670016 & (i2111 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle14;
                    z5 = z16;
                    i18 = i2112;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i2113) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            z2 = z;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    iM6815getClipgIe3tQ8 = i;
                    if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                        i11 = 16384;
                    } else {
                        i11 = 8192;
                    }
                    i5 |= i11;
                }
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion12 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i2113 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen12 = modifier2.then(companion12.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i2113 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i2114 = i17;
                    boolean z17 = z2;
                    TextStyle textStyle15 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen12, textStyle15, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z17, i2114, 0, null, null, composer2, (i2113 & 58254) | ((i2113 << 6) & 458752) | (3670016 & (i2113 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle15;
                    z5 = z17;
                    i18 = i2114;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion13 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i2115 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen13 = modifier2.then(companion13.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i2115 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i2116 = i17;
                    boolean z18 = z2;
                    TextStyle textStyle16 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen13, textStyle16, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z18, i2116, 0, null, null, composer2, (i2115 & 58254) | ((i2115 << 6) & 458752) | (3670016 & (i2115 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle16;
                    z5 = z18;
                    i18 = i2116;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i2117) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            iM6815getClipgIe3tQ8 = i;
            i12 = i4 & 32;
            if (i12 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i13 = 131072;
                } else {
                    i13 = 65536;
                }
                i5 |= i13;
            }
            i14 = i4 & 64;
            if (i14 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i15 = 1048576;
                } else {
                    i15 = 524288;
                }
                i5 |= i15;
            }
            if ((i4 & 128) != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 8388608;
                } else {
                    i16 = 4194304;
                }
                i5 |= i16;
            }
            if ((i5 & 4793491) == 4793490) {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion14 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2117 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen14 = modifier2.then(companion14.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2117 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i2118 = i17;
                boolean z19 = z2;
                TextStyle textStyle17 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen14, textStyle17, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z19, i2118, 0, null, null, composer2, (i2117 & 58254) | ((i2117 << 6) & 458752) | (3670016 & (i2117 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle17;
                z5 = z19;
                i18 = i2118;
            } else {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion15 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2119 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen15 = modifier2.then(companion15.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2119 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i21110 = i17;
                boolean z110 = z2;
                TextStyle textStyle18 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen15, textStyle18, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z110, i21110, 0, null, null, composer2, (i2119 & 58254) | ((i2119 << 6) & 458752) | (3670016 & (i2119 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle18;
                z5 = z110;
                i18 = i21110;
            }
            modifier3 = modifier2;
            i19 = iM6815getClipgIe3tQ8;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i21111) {
                        ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 48;
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                textStyle2 = textStyle;
                if (composerStartRestartGroup.changed(textStyle2)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        iM6815getClipgIe3tQ8 = i;
                        if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                            i11 = 16384;
                        } else {
                            i11 = 8192;
                        }
                        i5 |= i11;
                    }
                    i12 = i4 & 32;
                    if (i12 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i13 = 131072;
                        } else {
                            i13 = 65536;
                        }
                        i5 |= i13;
                    }
                    i14 = i4 & 64;
                    if (i14 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                    if ((i4 & 128) != 0) {
                        i5 |= 12582912;
                    } else if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i16 = 8388608;
                        } else {
                            i16 = 4194304;
                        }
                        i5 |= i16;
                    }
                    if ((i5 & 4793491) == 4793490) {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion16 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i21111 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen16 = modifier2.then(companion16.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i21111 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i21112 = i17;
                        boolean z111 = z2;
                        TextStyle textStyle19 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen16, textStyle19, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z111, i21112, 0, null, null, composer2, (i21111 & 58254) | ((i21111 << 6) & 458752) | (3670016 & (i21111 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle19;
                        z5 = z111;
                        i18 = i21112;
                    } else {
                        if (i20 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        }
                        if (i12 != 0) {
                            i17 = Integer.MAX_VALUE;
                        } else {
                            i17 = i2;
                        }
                        if (i14 != 0) {
                            clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$1 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion17 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                        int i21113 = i5;
                        if ((29360128 & i5) == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z3) {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        } else {
                            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen17 = modifier2.then(companion17.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i21113 & 3670016) == 1048576) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z4) {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composer2 = composerStartRestartGroup;
                        int i21114 = i17;
                        boolean z112 = z2;
                        TextStyle textStyle110 = textStyle2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen17, textStyle110, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z112, i21114, 0, null, null, composer2, (i21113 & 58254) | ((i21113 << 6) & 458752) | (3670016 & (i21113 << 3)), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = clickableTextKt$ClickableText$1;
                        textStyle3 = textStyle110;
                        z5 = z112;
                        i18 = i21114;
                    }
                    modifier3 = modifier2;
                    i19 = iM6815getClipgIe3tQ8;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i21115) {
                                ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                iM6815getClipgIe3tQ8 = i;
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion18 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i21115 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen18 = modifier2.then(companion18.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i21115 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i21116 = i17;
                    boolean z113 = z2;
                    TextStyle textStyle111 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen18, textStyle111, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z113, i21116, 0, null, null, composer2, (i21115 & 58254) | ((i21115 << 6) & 458752) | (3670016 & (i21115 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle111;
                    z5 = z113;
                    i18 = i21116;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion19 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i21117 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen19 = modifier2.then(companion19.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i21117 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i21118 = i17;
                    boolean z114 = z2;
                    TextStyle textStyle112 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen19, textStyle112, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z114, i21118, 0, null, null, composer2, (i21117 & 58254) | ((i21117 << 6) & 458752) | (3670016 & (i21117 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle112;
                    z5 = z114;
                    i18 = i21118;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i21119) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            z2 = z;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    iM6815getClipgIe3tQ8 = i;
                    if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                        i11 = 16384;
                    } else {
                        i11 = 8192;
                    }
                    i5 |= i11;
                }
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion110 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i21119 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen110 = modifier2.then(companion110.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i21119 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i211110 = i17;
                    boolean z115 = z2;
                    TextStyle textStyle113 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen110, textStyle113, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z115, i211110, 0, null, null, composer2, (i21119 & 58254) | ((i21119 << 6) & 458752) | (3670016 & (i21119 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle113;
                    z5 = z115;
                    i18 = i211110;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion111 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i211111 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen111 = modifier2.then(companion111.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i211111 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i211112 = i17;
                    boolean z116 = z2;
                    TextStyle textStyle114 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen111, textStyle114, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z116, i211112, 0, null, null, composer2, (i211111 & 58254) | ((i211111 << 6) & 458752) | (3670016 & (i211111 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle114;
                    z5 = z116;
                    i18 = i211112;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i211113) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            iM6815getClipgIe3tQ8 = i;
            i12 = i4 & 32;
            if (i12 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i13 = 131072;
                } else {
                    i13 = 65536;
                }
                i5 |= i13;
            }
            i14 = i4 & 64;
            if (i14 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i15 = 1048576;
                } else {
                    i15 = 524288;
                }
                i5 |= i15;
            }
            if ((i4 & 128) != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 8388608;
                } else {
                    i16 = 4194304;
                }
                i5 |= i16;
            }
            if ((i5 & 4793491) == 4793490) {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion112 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i211113 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen112 = modifier2.then(companion112.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i211113 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i211114 = i17;
                boolean z117 = z2;
                TextStyle textStyle115 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen112, textStyle115, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z117, i211114, 0, null, null, composer2, (i211113 & 58254) | ((i211113 << 6) & 458752) | (3670016 & (i211113 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle115;
                z5 = z117;
                i18 = i211114;
            } else {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion113 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i211115 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen113 = modifier2.then(companion113.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i211115 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i211116 = i17;
                boolean z118 = z2;
                TextStyle textStyle116 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen113, textStyle116, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z118, i211116, 0, null, null, composer2, (i211115 & 58254) | ((i211115 << 6) & 458752) | (3670016 & (i211115 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle116;
                z5 = z118;
                i18 = i211116;
            }
            modifier3 = modifier2;
            i19 = iM6815getClipgIe3tQ8;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i211117) {
                        ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        textStyle2 = textStyle;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    iM6815getClipgIe3tQ8 = i;
                    if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                        i11 = 16384;
                    } else {
                        i11 = 8192;
                    }
                    i5 |= i11;
                }
                i12 = i4 & 32;
                if (i12 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i13 = 131072;
                    } else {
                        i13 = 65536;
                    }
                    i5 |= i13;
                }
                i14 = i4 & 64;
                if (i14 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 8388608;
                    } else {
                        i16 = 4194304;
                    }
                    i5 |= i16;
                }
                if ((i5 & 4793491) == 4793490) {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion114 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i211117 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen114 = modifier2.then(companion114.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i211117 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i211118 = i17;
                    boolean z119 = z2;
                    TextStyle textStyle117 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen114, textStyle117, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z119, i211118, 0, null, null, composer2, (i211117 & 58254) | ((i211117 << 6) & 458752) | (3670016 & (i211117 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle117;
                    z5 = z119;
                    i18 = i211118;
                } else {
                    if (i20 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    }
                    if (i12 != 0) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        i17 = i2;
                    }
                    if (i14 != 0) {
                        clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$1 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion115 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                    int i211119 = i5;
                    if ((29360128 & i5) == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z3) {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    } else {
                        clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen115 = modifier2.then(companion115.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i211119 & 3670016) == 1048576) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z4) {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    int i2111110 = i17;
                    boolean z1110 = z2;
                    TextStyle textStyle118 = textStyle2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen115, textStyle118, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1110, i2111110, 0, null, null, composer2, (i211119 & 58254) | ((i211119 << 6) & 458752) | (3670016 & (i211119 << 3)), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = clickableTextKt$ClickableText$1;
                    textStyle3 = textStyle118;
                    z5 = z1110;
                    i18 = i2111110;
                }
                modifier3 = modifier2;
                i19 = iM6815getClipgIe3tQ8;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i2111111) {
                            ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            iM6815getClipgIe3tQ8 = i;
            i12 = i4 & 32;
            if (i12 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i13 = 131072;
                } else {
                    i13 = 65536;
                }
                i5 |= i13;
            }
            i14 = i4 & 64;
            if (i14 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i15 = 1048576;
                } else {
                    i15 = 524288;
                }
                i5 |= i15;
            }
            if ((i4 & 128) != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 8388608;
                } else {
                    i16 = 4194304;
                }
                i5 |= i16;
            }
            if ((i5 & 4793491) == 4793490) {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion116 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2111111 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen116 = modifier2.then(companion116.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2111111 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i2111112 = i17;
                boolean z1111 = z2;
                TextStyle textStyle119 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen116, textStyle119, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1111, i2111112, 0, null, null, composer2, (i2111111 & 58254) | ((i2111111 << 6) & 458752) | (3670016 & (i2111111 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle119;
                z5 = z1111;
                i18 = i2111112;
            } else {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion117 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2111113 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen117 = modifier2.then(companion117.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2111113 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i2111114 = i17;
                boolean z1112 = z2;
                TextStyle textStyle1110 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen117, textStyle1110, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1112, i2111114, 0, null, null, composer2, (i2111113 & 58254) | ((i2111113 << 6) & 458752) | (3670016 & (i2111113 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle1110;
                z5 = z1112;
                i18 = i2111114;
            }
            modifier3 = modifier2;
            i19 = iM6815getClipgIe3tQ8;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i2111115) {
                        ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 3072;
        z2 = z;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                iM6815getClipgIe3tQ8 = i;
                if (composerStartRestartGroup.changed(iM6815getClipgIe3tQ8)) {
                    i11 = 16384;
                } else {
                    i11 = 8192;
                }
                i5 |= i11;
            }
            i12 = i4 & 32;
            if (i12 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i13 = 131072;
                } else {
                    i13 = 65536;
                }
                i5 |= i13;
            }
            i14 = i4 & 64;
            if (i14 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i15 = 1048576;
                } else {
                    i15 = 524288;
                }
                i5 |= i15;
            }
            if ((i4 & 128) != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 8388608;
                } else {
                    i16 = 4194304;
                }
                i5 |= i16;
            }
            if ((i5 & 4793491) == 4793490) {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion118 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2111115 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen118 = modifier2.then(companion118.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2111115 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i2111116 = i17;
                boolean z1113 = z2;
                TextStyle textStyle1111 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen118, textStyle1111, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1113, i2111116, 0, null, null, composer2, (i2111115 & 58254) | ((i2111115 << 6) & 458752) | (3670016 & (i2111115 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle1111;
                z5 = z1113;
                i18 = i2111116;
            } else {
                if (i20 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                }
                if (i12 != 0) {
                    i17 = Integer.MAX_VALUE;
                } else {
                    i17 = i2;
                }
                if (i14 != 0) {
                    clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$1 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion119 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
                int i2111117 = i5;
                if ((29360128 & i5) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z3) {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                } else {
                    clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen119 = modifier2.then(companion119.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
                if ((i2111117 & 3670016) == 1048576) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z4) {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                int i2111118 = i17;
                boolean z1114 = z2;
                TextStyle textStyle1112 = textStyle2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen119, textStyle1112, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1114, i2111118, 0, null, null, composer2, (i2111117 & 58254) | ((i2111117 << 6) & 458752) | (3670016 & (i2111117 << 3)), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = clickableTextKt$ClickableText$1;
                textStyle3 = textStyle1112;
                z5 = z1114;
                i18 = i2111118;
            }
            modifier3 = modifier2;
            i19 = iM6815getClipgIe3tQ8;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i2111119) {
                        ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 24576;
        iM6815getClipgIe3tQ8 = i;
        i12 = i4 & 32;
        if (i12 != 0) {
            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i13 = 131072;
            } else {
                i13 = 65536;
            }
            i5 |= i13;
        }
        i14 = i4 & 64;
        if (i14 != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i15 = 1048576;
            } else {
                i15 = 524288;
            }
            i5 |= i15;
        }
        if ((i4 & 128) != 0) {
            i5 |= 12582912;
        } else if ((i3 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i16 = 8388608;
            } else {
                i16 = 4194304;
            }
            i5 |= i16;
        }
        if ((i5 & 4793491) == 4793490) {
            if (i20 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i6 != 0) {
                textStyle2 = TextStyle.INSTANCE.getDefault();
            }
            if (i8 != 0) {
                z2 = true;
            }
            if (i10 != 0) {
                iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
            }
            if (i12 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i2;
            }
            if (i14 != 0) {
                clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }
                };
            } else {
                clickableTextKt$ClickableText$1 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier.Companion companion1110 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
            int i2111119 = i5;
            if ((29360128 & i5) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z3) {
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
            } else {
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen1110 = modifier2.then(companion1110.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
            if ((i2111119 & 3670016) == 1048576) {
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            int i21111110 = i17;
            boolean z1115 = z2;
            TextStyle textStyle1113 = textStyle2;
            BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen1110, textStyle1113, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1115, i21111110, 0, null, null, composer2, (i2111119 & 58254) | ((i2111119 << 6) & 458752) | (3670016 & (i2111119 << 3)), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = clickableTextKt$ClickableText$1;
            textStyle3 = textStyle1113;
            z5 = z1115;
            i18 = i21111110;
        } else {
            if (i20 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i6 != 0) {
                textStyle2 = TextStyle.INSTANCE.getDefault();
            }
            if (i8 != 0) {
                z2 = true;
            }
            if (i10 != 0) {
                iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
            }
            if (i12 != 0) {
                i17 = Integer.MAX_VALUE;
            } else {
                i17 = i2;
            }
            if (i14 != 0) {
                clickableTextKt$ClickableText$1 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }
                };
            } else {
                clickableTextKt$ClickableText$1 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:83)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498074812, "CC(remember):ClickableText.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier.Companion companion1111 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498078432, "CC(remember):ClickableText.kt#9igjgp");
            int i21111111 = i5;
            if ((29360128 & i5) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z3) {
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
            } else {
                clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen1111 = modifier2.then(companion1111.then(new SuspendPointerInputElement(function2, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pressIndicator$1$1RememberedValue), 6, null)));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498091284, "CC(remember):ClickableText.kt#9igjgp");
            if ((i21111111 & 3670016) == 1048576) {
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z4) {
                objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$1.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            int i21111112 = i17;
            boolean z1116 = z2;
            TextStyle textStyle1114 = textStyle2;
            BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen1111, textStyle1114, (Function1) objRememberedValue2, iM6815getClipgIe3tQ8, z1116, i21111112, 0, null, null, composer2, (i21111111 & 58254) | ((i21111111 << 6) & 458752) | (3670016 & (i21111111 << 3)), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = clickableTextKt$ClickableText$1;
            textStyle3 = textStyle1114;
            z5 = z1116;
            i18 = i21111112;
        }
        modifier3 = modifier2;
        i19 = iM6815getClipgIe3tQ8;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i21111113) {
                    ClickableTextKt.m1027ClickableText4YKlhWE(annotatedString, modifier3, textStyle3, z5, i19, i18, function3, function2, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0135 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:103:0x0137  */
    /* JADX WARN: Code duplicated, block: B:105:0x013e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0147  */
    /* JADX WARN: Code duplicated, block: B:109:0x014a  */
    /* JADX WARN: Code duplicated, block: B:110:0x0153  */
    /* JADX WARN: Code duplicated, block: B:112:0x0157  */
    /* JADX WARN: Code duplicated, block: B:114:0x015d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0162  */
    /* JADX WARN: Code duplicated, block: B:118:0x016a  */
    /* JADX WARN: Code duplicated, block: B:121:0x0187  */
    /* JADX WARN: Code duplicated, block: B:124:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:127:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:128:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:131:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:132:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:135:0x0204  */
    /* JADX WARN: Code duplicated, block: B:139:0x0210  */
    /* JADX WARN: Code duplicated, block: B:142:0x0246  */
    /* JADX WARN: Code duplicated, block: B:143:0x0249  */
    /* JADX WARN: Code duplicated, block: B:146:0x0251  */
    /* JADX WARN: Code duplicated, block: B:148:0x0259  */
    /* JADX WARN: Code duplicated, block: B:151:0x029a  */
    /* JADX WARN: Code duplicated, block: B:155:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:157:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:39:0x006f  */
    /* JADX WARN: Code duplicated, block: B:41:0x0077  */
    /* JADX WARN: Code duplicated, block: B:42:0x007a  */
    /* JADX WARN: Code duplicated, block: B:47:0x0084  */
    /* JADX WARN: Code duplicated, block: B:48:0x0087  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0093  */
    /* JADX WARN: Code duplicated, block: B:53:0x0096  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:81:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:88:0x0101  */
    /* JADX WARN: Code duplicated, block: B:89:0x0104  */
    /* JADX WARN: Code duplicated, block: B:91:0x0108  */
    /* JADX WARN: Code duplicated, block: B:93:0x010e  */
    /* JADX WARN: Code duplicated, block: B:94:0x0111  */
    /* JADX WARN: Code duplicated, block: B:98:0x011f  */
    @Deprecated(message = "Use Text or BasicText and pass an AnnotatedString that contains a LinkAnnotation")
    /* JADX INFO: renamed from: ClickableText-03UYbkw, reason: not valid java name */
    public static final void m1026ClickableText03UYbkw(final AnnotatedString annotatedString, final Function1<? super Integer, Unit> function1, Modifier modifier, TextStyle textStyle, boolean z, int i, int i2, Function1<? super TextLayoutResult, Unit> function2, final Function1<? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        AnnotatedString annotatedString2;
        int i5;
        Modifier modifier2;
        int i6;
        TextStyle textStyle2;
        int i7;
        int i8;
        boolean z2;
        int i9;
        int i10;
        final int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int iM6815getClipgIe3tQ8;
        final ClickableTextKt$ClickableText$4 clickableTextKt$ClickableText$4;
        Object objRememberedValue;
        final MutableState mutableState;
        Object objRememberedValue2;
        CoroutineScope coroutineScope;
        int i19;
        boolean z3;
        boolean z4;
        boolean z5;
        ClickableTextKt$ClickableText$pointerInputModifier$1$1 clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue;
        Function1<? super Integer, Unit> function4;
        boolean z6;
        Object objRememberedValue3;
        Composer composer2;
        final Function1<? super TextLayoutResult, Unit> function5;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final boolean z7;
        final int i20;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(1020774372);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ClickableText)P(8,3,1,7,6,5:c#ui.text.style.TextOverflow!1,4)163@7538L52,164@7616L24,171@7916L413,193@8556L76,186@8335L303:ClickableText.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i3 | 6;
            annotatedString2 = annotatedString;
        } else {
            annotatedString2 = annotatedString;
            if ((i3 & 6) == 0) {
                i5 = (composerStartRestartGroup.changed(annotatedString2) ? 4 : 2) | i3;
            } else {
                i5 = i3;
            }
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i21 = i4 & 4;
        if (i21 == 0) {
            if ((i3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i5 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i6 = i4 & 8;
            if (i6 != 0) {
                if ((i3 & 3072) == 0) {
                    textStyle2 = textStyle;
                    if (composerStartRestartGroup.changed(textStyle2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 16;
                if (i8 != 0) {
                    if ((i3 & 24576) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 32;
                    if (i10 != 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        i11 = i;
                    } else {
                        i11 = i;
                        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i5 |= i12;
                        }
                    }
                    i13 = i4 & 64;
                    if (i13 != 0) {
                        i5 |= 1572864;
                        i14 = i2;
                    } else {
                        i14 = i2;
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 1048576;
                            } else {
                                i15 = 524288;
                            }
                            i5 |= i15;
                        }
                    }
                    i16 = i4 & 128;
                    if (i16 != 0) {
                        i5 |= 12582912;
                    } else if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i17 = 8388608;
                        } else {
                            i17 = 4194304;
                        }
                        i5 |= i17;
                    }
                    if ((i4 & 256) != 0) {
                        i5 |= 100663296;
                    } else if ((i3 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i18 = 67108864;
                        } else {
                            i18 = 33554432;
                        }
                        i5 |= i18;
                    }
                    if ((i5 & 38347923) == 38347922 || !composerStartRestartGroup.getSkipping()) {
                        if (i21 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            textStyle2 = TextStyle.INSTANCE.getDefault();
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                        } else {
                            iM6815getClipgIe3tQ8 = i11;
                        }
                        if (i13 != 0) {
                            i14 = Integer.MAX_VALUE;
                        }
                        if (i16 != 0) {
                            clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }
                            };
                        } else {
                            clickableTextKt$ClickableText$4 = function2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                            composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            objRememberedValue2 = compositionScopedCoroutineScopeCanceller;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                        boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope);
                        i19 = i5;
                        if ((i5 & 112) == 32) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z8 = zChangedInstance | z3;
                        if ((i19 & 234881024) == 67108864) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        z5 = z8 | z4;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z5 || clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            function4 = function1;
                            clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                            composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                        } else {
                            function4 = function1;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Modifier modifierThen = modifier2.then(companion.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                        if ((i19 & 29360128) == 8388608) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z6 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                    invoke2(textLayoutResult);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextLayoutResult textLayoutResult) {
                                    mutableState.setValue(textLayoutResult);
                                    clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        int i22 = i19 >> 3;
                        composer2 = composerStartRestartGroup;
                        int i23 = i14;
                        TextStyle textStyle4 = textStyle2;
                        boolean z9 = z2;
                        BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen, textStyle4, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z9, i23, 0, null, null, composer2, (i19 & 14) | (i22 & 896) | (i22 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function5 = clickableTextKt$ClickableText$4;
                        modifier3 = modifier2;
                        textStyle3 = textStyle4;
                        i11 = iM6815getClipgIe3tQ8;
                        z7 = z9;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function2;
                        composer2 = composerStartRestartGroup;
                        modifier3 = modifier2;
                        z7 = z2;
                        i20 = i14;
                        textStyle3 = textStyle2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i24) {
                                ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                            }
                        });
                    }
                }
                i5 |= 24576;
                z2 = z;
                i10 = i4 & 32;
                if (i10 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i11 = i;
                } else {
                    i11 = i;
                    if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i5 |= i12;
                    }
                }
                i13 = i4 & 64;
                if (i13 != 0) {
                    i5 |= 1572864;
                    i14 = i2;
                } else {
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                }
                i16 = i4 & 128;
                if (i16 != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i17 = 8388608;
                    } else {
                        i17 = 4194304;
                    }
                    i5 |= i17;
                }
                if ((i4 & 256) != 0) {
                    i5 |= 100663296;
                } else if ((i3 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i18 = 67108864;
                    } else {
                        i18 = 33554432;
                    }
                    i5 |= i18;
                }
                if ((i5 & 38347923) == 38347922) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller2 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller2);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z10 = zChangedInstance2 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z10 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen2 = modifier2.then(companion2.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i24 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i25 = i14;
                    TextStyle textStyle5 = textStyle2;
                    boolean z11 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen2, textStyle5, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z11, i25, 0, null, null, composer2, (i19 & 14) | (i24 & 896) | (i24 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle5;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z11;
                    i20 = i25;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller3 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller3);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller3;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z12 = zChangedInstance3 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z12 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen3 = modifier2.then(companion3.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i26 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i27 = i14;
                    TextStyle textStyle6 = textStyle2;
                    boolean z13 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen3, textStyle6, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z13, i27, 0, null, null, composer2, (i19 & 14) | (i26 & 896) | (i26 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle6;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z13;
                    i20 = i27;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i28) {
                            ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 3072;
            textStyle2 = textStyle;
            i8 = i4 & 16;
            if (i8 != 0) {
                if ((i3 & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 32;
                if (i10 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i11 = i;
                } else {
                    i11 = i;
                    if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i5 |= i12;
                    }
                }
                i13 = i4 & 64;
                if (i13 != 0) {
                    i5 |= 1572864;
                    i14 = i2;
                } else {
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                }
                i16 = i4 & 128;
                if (i16 != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i17 = 8388608;
                    } else {
                        i17 = 4194304;
                    }
                    i5 |= i17;
                }
                if ((i4 & 256) != 0) {
                    i5 |= 100663296;
                } else if ((i3 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i18 = 67108864;
                    } else {
                        i18 = 33554432;
                    }
                    i5 |= i18;
                }
                if ((i5 & 38347923) == 38347922) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller4 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller4);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller4;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion4 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z14 = zChangedInstance4 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z14 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen4 = modifier2.then(companion4.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i28 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i29 = i14;
                    TextStyle textStyle7 = textStyle2;
                    boolean z15 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen4, textStyle7, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z15, i29, 0, null, null, composer2, (i19 & 14) | (i28 & 896) | (i28 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle7;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z15;
                    i20 = i29;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller5 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller5);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller5;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion5 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z16 = zChangedInstance5 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z16 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen5 = modifier2.then(companion5.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i210 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i211 = i14;
                    TextStyle textStyle8 = textStyle2;
                    boolean z17 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen5, textStyle8, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z17, i211, 0, null, null, composer2, (i19 & 14) | (i210 & 896) | (i210 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle8;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z17;
                    i20 = i211;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i212) {
                            ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            z2 = z;
            i10 = i4 & 32;
            if (i10 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i11 = i;
            } else {
                i11 = i;
                if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i5 |= i12;
                }
            }
            i13 = i4 & 64;
            if (i13 != 0) {
                i5 |= 1572864;
                i14 = i2;
            } else {
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
            }
            i16 = i4 & 128;
            if (i16 != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i17 = 8388608;
                } else {
                    i17 = 4194304;
                }
                i5 |= i17;
            }
            if ((i4 & 256) != 0) {
                i5 |= 100663296;
            } else if ((i3 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i18 = 67108864;
                } else {
                    i18 = 33554432;
                }
                i5 |= i18;
            }
            if ((i5 & 38347923) == 38347922) {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller6 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller6);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller6;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion6 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z18 = zChangedInstance6 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z18 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen6 = modifier2.then(companion6.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i212 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i213 = i14;
                TextStyle textStyle9 = textStyle2;
                boolean z19 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen6, textStyle9, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z19, i213, 0, null, null, composer2, (i19 & 14) | (i212 & 896) | (i212 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle9;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z19;
                i20 = i213;
            } else {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller7 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller7);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller7;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion7 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z110 = zChangedInstance7 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z110 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen7 = modifier2.then(companion7.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i214 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i215 = i14;
                TextStyle textStyle10 = textStyle2;
                boolean z111 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen7, textStyle10, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z111, i215, 0, null, null, composer2, (i19 & 14) | (i214 & 896) | (i214 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle10;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z111;
                i20 = i215;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i216) {
                        ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i6 = i4 & 8;
        if (i6 != 0) {
            if ((i3 & 3072) == 0) {
                textStyle2 = textStyle;
                if (composerStartRestartGroup.changed(textStyle2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i5 |= i7;
            }
            i8 = i4 & 16;
            if (i8 != 0) {
                if ((i3 & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 32;
                if (i10 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i11 = i;
                } else {
                    i11 = i;
                    if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i5 |= i12;
                    }
                }
                i13 = i4 & 64;
                if (i13 != 0) {
                    i5 |= 1572864;
                    i14 = i2;
                } else {
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 1048576;
                        } else {
                            i15 = 524288;
                        }
                        i5 |= i15;
                    }
                }
                i16 = i4 & 128;
                if (i16 != 0) {
                    i5 |= 12582912;
                } else if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i17 = 8388608;
                    } else {
                        i17 = 4194304;
                    }
                    i5 |= i17;
                }
                if ((i4 & 256) != 0) {
                    i5 |= 100663296;
                } else if ((i3 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i18 = 67108864;
                    } else {
                        i18 = 33554432;
                    }
                    i5 |= i18;
                }
                if ((i5 & 38347923) == 38347922) {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller8 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller8);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller8;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion8 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z112 = zChangedInstance8 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z112 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen8 = modifier2.then(companion8.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i216 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i217 = i14;
                    TextStyle textStyle11 = textStyle2;
                    boolean z113 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen8, textStyle11, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z113, i217, 0, null, null, composer2, (i19 & 14) | (i216 & 896) | (i216 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle11;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z113;
                    i20 = i217;
                } else {
                    if (i21 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        textStyle2 = TextStyle.INSTANCE.getDefault();
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                    } else {
                        iM6815getClipgIe3tQ8 = i11;
                    }
                    if (i13 != 0) {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i16 != 0) {
                        clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }
                        };
                    } else {
                        clickableTextKt$ClickableText$4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller9 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                        composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller9);
                        objRememberedValue2 = compositionScopedCoroutineScopeCanceller9;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier.Companion companion9 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                    boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(coroutineScope);
                    i19 = i5;
                    if ((i5 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    boolean z114 = zChangedInstance9 | z3;
                    if ((i19 & 234881024) == 67108864) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z114 | z4;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    } else {
                        function4 = function1;
                        clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                        composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierThen9 = modifier2.then(companion9.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                    if ((i19 & 29360128) == 8388608) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                                invoke2(textLayoutResult);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextLayoutResult textLayoutResult) {
                                mutableState.setValue(textLayoutResult);
                                clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i218 = i19 >> 3;
                    composer2 = composerStartRestartGroup;
                    int i219 = i14;
                    TextStyle textStyle12 = textStyle2;
                    boolean z115 = z2;
                    BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen9, textStyle12, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z115, i219, 0, null, null, composer2, (i19 & 14) | (i218 & 896) | (i218 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function5 = clickableTextKt$ClickableText$4;
                    modifier3 = modifier2;
                    textStyle3 = textStyle12;
                    i11 = iM6815getClipgIe3tQ8;
                    z7 = z115;
                    i20 = i219;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i2110) {
                            ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                        }
                    });
                }
            }
            i5 |= 24576;
            z2 = z;
            i10 = i4 & 32;
            if (i10 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i11 = i;
            } else {
                i11 = i;
                if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i5 |= i12;
                }
            }
            i13 = i4 & 64;
            if (i13 != 0) {
                i5 |= 1572864;
                i14 = i2;
            } else {
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
            }
            i16 = i4 & 128;
            if (i16 != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i17 = 8388608;
                } else {
                    i17 = 4194304;
                }
                i5 |= i17;
            }
            if ((i4 & 256) != 0) {
                i5 |= 100663296;
            } else if ((i3 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i18 = 67108864;
                } else {
                    i18 = 33554432;
                }
                i5 |= i18;
            }
            if ((i5 & 38347923) == 38347922) {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller10 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller10);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller10;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion10 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance10 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z116 = zChangedInstance10 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z116 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen10 = modifier2.then(companion10.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2110 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i2111 = i14;
                TextStyle textStyle13 = textStyle2;
                boolean z117 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen10, textStyle13, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z117, i2111, 0, null, null, composer2, (i19 & 14) | (i2110 & 896) | (i2110 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle13;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z117;
                i20 = i2111;
            } else {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller11 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller11);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller11;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion11 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance11 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z118 = zChangedInstance11 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z118 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen11 = modifier2.then(companion11.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2112 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i2113 = i14;
                TextStyle textStyle14 = textStyle2;
                boolean z119 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen11, textStyle14, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z119, i2113, 0, null, null, composer2, (i19 & 14) | (i2112 & 896) | (i2112 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle14;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z119;
                i20 = i2113;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i2114) {
                        ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 3072;
        textStyle2 = textStyle;
        i8 = i4 & 16;
        if (i8 != 0) {
            if ((i3 & 24576) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i5 |= i9;
            }
            i10 = i4 & 32;
            if (i10 != 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i11 = i;
            } else {
                i11 = i;
                if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i5 |= i12;
                }
            }
            i13 = i4 & 64;
            if (i13 != 0) {
                i5 |= 1572864;
                i14 = i2;
            } else {
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 1048576;
                    } else {
                        i15 = 524288;
                    }
                    i5 |= i15;
                }
            }
            i16 = i4 & 128;
            if (i16 != 0) {
                i5 |= 12582912;
            } else if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i17 = 8388608;
                } else {
                    i17 = 4194304;
                }
                i5 |= i17;
            }
            if ((i4 & 256) != 0) {
                i5 |= 100663296;
            } else if ((i3 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i18 = 67108864;
                } else {
                    i18 = 33554432;
                }
                i5 |= i18;
            }
            if ((i5 & 38347923) == 38347922) {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller12 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller12);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller12;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion12 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance12 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z1110 = zChangedInstance12 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z1110 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen12 = modifier2.then(companion12.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2114 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i2115 = i14;
                TextStyle textStyle15 = textStyle2;
                boolean z1111 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen12, textStyle15, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z1111, i2115, 0, null, null, composer2, (i19 & 14) | (i2114 & 896) | (i2114 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle15;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z1111;
                i20 = i2115;
            } else {
                if (i21 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
                } else {
                    iM6815getClipgIe3tQ8 = i11;
                }
                if (i13 != 0) {
                    i14 = Integer.MAX_VALUE;
                }
                if (i16 != 0) {
                    clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }
                    };
                } else {
                    clickableTextKt$ClickableText$4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller13 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                    composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller13);
                    objRememberedValue2 = compositionScopedCoroutineScopeCanceller13;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier.Companion companion13 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
                boolean zChangedInstance13 = composerStartRestartGroup.changedInstance(coroutineScope);
                i19 = i5;
                if ((i5 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z1112 = zChangedInstance13 | z3;
                if ((i19 & 234881024) == 67108864) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z1112 | z4;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                } else {
                    function4 = function1;
                    clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                    composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierThen13 = modifier2.then(companion13.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
                if ((i19 & 29360128) == 8388608) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!z6) {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult textLayoutResult) {
                            mutableState.setValue(textLayoutResult);
                            clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i2116 = i19 >> 3;
                composer2 = composerStartRestartGroup;
                int i2117 = i14;
                TextStyle textStyle16 = textStyle2;
                boolean z1113 = z2;
                BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen13, textStyle16, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z1113, i2117, 0, null, null, composer2, (i19 & 14) | (i2116 & 896) | (i2116 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function5 = clickableTextKt$ClickableText$4;
                modifier3 = modifier2;
                textStyle3 = textStyle16;
                i11 = iM6815getClipgIe3tQ8;
                z7 = z1113;
                i20 = i2117;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i2118) {
                        ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                    }
                });
            }
        }
        i5 |= 24576;
        z2 = z;
        i10 = i4 & 32;
        if (i10 != 0) {
            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i11 = i;
        } else {
            i11 = i;
            if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i5 |= i12;
            }
        }
        i13 = i4 & 64;
        if (i13 != 0) {
            i5 |= 1572864;
            i14 = i2;
        } else {
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i14)) {
                    i15 = 1048576;
                } else {
                    i15 = 524288;
                }
                i5 |= i15;
            }
        }
        i16 = i4 & 128;
        if (i16 != 0) {
            i5 |= 12582912;
        } else if ((i3 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i17 = 8388608;
            } else {
                i17 = 4194304;
            }
            i5 |= i17;
        }
        if ((i4 & 256) != 0) {
            i5 |= 100663296;
        } else if ((i3 & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i18 = 67108864;
            } else {
                i18 = 33554432;
            }
            i5 |= i18;
        }
        if ((i5 & 38347923) == 38347922) {
            if (i21 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i6 != 0) {
                textStyle2 = TextStyle.INSTANCE.getDefault();
            }
            if (i8 != 0) {
                z2 = true;
            }
            if (i10 != 0) {
                iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
            } else {
                iM6815getClipgIe3tQ8 = i11;
            }
            if (i13 != 0) {
                i14 = Integer.MAX_VALUE;
            }
            if (i16 != 0) {
                clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }
                };
            } else {
                clickableTextKt$ClickableText$4 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller14 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller14);
                objRememberedValue2 = compositionScopedCoroutineScopeCanceller14;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier.Companion companion14 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
            boolean zChangedInstance14 = composerStartRestartGroup.changedInstance(coroutineScope);
            i19 = i5;
            if ((i5 & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z1114 = zChangedInstance14 | z3;
            if ((i19 & 234881024) == 67108864) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z1114 | z4;
            clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5) {
                function4 = function1;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
            } else {
                function4 = function1;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen14 = modifier2.then(companion14.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
            if ((i19 & 29360128) == 8388608) {
                z6 = true;
            } else {
                z6 = false;
            }
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!z6) {
                objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i2118 = i19 >> 3;
            composer2 = composerStartRestartGroup;
            int i2119 = i14;
            TextStyle textStyle17 = textStyle2;
            boolean z1115 = z2;
            BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen14, textStyle17, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z1115, i2119, 0, null, null, composer2, (i19 & 14) | (i2118 & 896) | (i2118 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function5 = clickableTextKt$ClickableText$4;
            modifier3 = modifier2;
            textStyle3 = textStyle17;
            i11 = iM6815getClipgIe3tQ8;
            z7 = z1115;
            i20 = i2119;
        } else {
            if (i21 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i6 != 0) {
                textStyle2 = TextStyle.INSTANCE.getDefault();
            }
            if (i8 != 0) {
                z2 = true;
            }
            if (i10 != 0) {
                iM6815getClipgIe3tQ8 = TextOverflow.INSTANCE.m6815getClipgIe3tQ8();
            } else {
                iM6815getClipgIe3tQ8 = i11;
            }
            if (i13 != 0) {
                i14 = Integer.MAX_VALUE;
            }
            if (i16 != 0) {
                clickableTextKt$ClickableText$4 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }
                };
            } else {
                clickableTextKt$ClickableText$4 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1020774372, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:162)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498188796, "CC(remember):ClickableText.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller15 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller15);
                objRememberedValue2 = compositionScopedCoroutineScopeCanceller15;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier.Companion companion15 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498201253, "CC(remember):ClickableText.kt#9igjgp");
            boolean zChangedInstance15 = composerStartRestartGroup.changedInstance(coroutineScope);
            i19 = i5;
            if ((i5 & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z1116 = zChangedInstance15 | z3;
            if ((i19 & 234881024) == 67108864) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z1116 | z4;
            clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5) {
                function4 = function1;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
            } else {
                function4 = function1;
                clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue = new ClickableTextKt$ClickableText$pointerInputModifier$1$1(coroutineScope, function1, mutableState, function3, null);
                composerStartRestartGroup.updateRememberedValue(clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierThen15 = modifier2.then(companion15.then(new SuspendPointerInputElement(function3, function4, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) clickableTextKt$ClickableText$pointerInputModifier$1$1RememberedValue), 4, null)));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1498221396, "CC(remember):ClickableText.kt#9igjgp");
            if ((i19 & 29360128) == 8388608) {
                z6 = true;
            } else {
                z6 = false;
            }
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!z6) {
                objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult textLayoutResult) {
                        mutableState.setValue(textLayoutResult);
                        clickableTextKt$ClickableText$4.invoke(textLayoutResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i21110 = i19 >> 3;
            composer2 = composerStartRestartGroup;
            int i21111 = i14;
            TextStyle textStyle18 = textStyle2;
            boolean z1117 = z2;
            BasicTextKt.m1020BasicTextRWo7tUw(annotatedString2, modifierThen15, textStyle18, (Function1) objRememberedValue3, iM6815getClipgIe3tQ8, z1117, i21111, 0, null, null, composer2, (i19 & 14) | (i21110 & 896) | (i21110 & 57344) | ((i19 << 3) & 458752) | (i19 & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function5 = clickableTextKt$ClickableText$4;
            modifier3 = modifier2;
            textStyle3 = textStyle18;
            i11 = iM6815getClipgIe3tQ8;
            z7 = z1117;
            i20 = i21111;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i21112) {
                    ClickableTextKt.m1026ClickableText03UYbkw(annotatedString, function1, modifier3, textStyle3, z7, i11, i20, function5, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer ClickableText_03UYbkw$getOffset(MutableState<TextLayoutResult> mutableState, long j) {
        MultiParagraph multiParagraph;
        TextLayoutResult value = mutableState.getValue();
        if (value != null && (multiParagraph = value.getMultiParagraph()) != null) {
            if (!m1028containsWithinBoundsUv8p0NA(multiParagraph, j)) {
                multiParagraph = null;
            }
            if (multiParagraph != null) {
                return Integer.valueOf(multiParagraph.m6205getOffsetForPositionk4lQ0M(j));
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: containsWithinBounds-Uv8p0NA, reason: not valid java name */
    private static final boolean m1028containsWithinBoundsUv8p0NA(MultiParagraph multiParagraph, long j) {
        float fM3870getXimpl = Offset.m3870getXimpl(j);
        float fM3871getYimpl = Offset.m3871getYimpl(j);
        return fM3870getXimpl > 0.0f && fM3871getYimpl >= 0.0f && fM3870getXimpl <= multiParagraph.getWidth() && fM3871getYimpl <= multiParagraph.getHeight();
    }
}
