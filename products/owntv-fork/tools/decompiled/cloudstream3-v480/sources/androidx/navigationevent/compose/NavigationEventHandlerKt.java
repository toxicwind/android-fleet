package androidx.navigationevent.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigationevent.NavigationEventDispatcher;
import androidx.navigationevent.NavigationEventDispatcherOwner;
import androidx.navigationevent.NavigationEventInfo;
import androidx.navigationevent.NavigationEventTransitionState;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: NavigationEventHandler.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001aq\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\r\u001aE\u0010\u000e\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00032\b\b\u0002\u0010\n\u001a\u00020\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\u000f\u001aE\u0010\u0010\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"NavigationEventHandler", "", "state", "Landroidx/navigationevent/compose/NavigationEventState;", "Landroidx/navigationevent/NavigationEventInfo;", "isForwardEnabled", "", "onForwardCancelled", "Lkotlin/Function0;", "onForwardCompleted", "isBackEnabled", "onBackCancelled", "onBackCompleted", "(Landroidx/navigationevent/compose/NavigationEventState;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "NavigationBackHandler", "(Landroidx/navigationevent/compose/NavigationEventState;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "NavigationForwardHandler", "navigationevent-compose"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationEventHandlerKt {
    static final Unit NavigationBackHandler$lambda$24(NavigationEventState navigationEventState, boolean z, Function0 function0, Function0 function1, int i, int i2, Composer composer, int i3) {
        NavigationBackHandler(navigationEventState, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationEventHandler$lambda$17(NavigationEventState navigationEventState, boolean z, Function0 function0, Function0 function1, boolean z2, Function0 function2, Function0 function3, int i, int i2, Composer composer, int i3) {
        NavigationEventHandler(navigationEventState, z, function0, function1, z2, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationForwardHandler$lambda$31(NavigationEventState navigationEventState, boolean z, Function0 function0, Function0 function1, int i, int i2, Composer composer, int i3) {
        NavigationForwardHandler(navigationEventState, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x013f  */
    /* JADX WARN: Code duplicated, block: B:102:0x0151  */
    /* JADX WARN: Code duplicated, block: B:105:0x0161  */
    /* JADX WARN: Code duplicated, block: B:107:0x0173  */
    /* JADX WARN: Code duplicated, block: B:109:0x0182  */
    /* JADX WARN: Code duplicated, block: B:112:0x018a  */
    /* JADX WARN: Code duplicated, block: B:115:0x019c  */
    /* JADX WARN: Code duplicated, block: B:117:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:118:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:121:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:123:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:126:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:127:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:130:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:131:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:134:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:135:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:138:0x0206  */
    /* JADX WARN: Code duplicated, block: B:139:0x0209  */
    /* JADX WARN: Code duplicated, block: B:142:0x0212  */
    /* JADX WARN: Code duplicated, block: B:143:0x0215  */
    /* JADX WARN: Code duplicated, block: B:146:0x021e  */
    /* JADX WARN: Code duplicated, block: B:147:0x0221  */
    /* JADX WARN: Code duplicated, block: B:150:0x0226  */
    /* JADX WARN: Code duplicated, block: B:151:0x0229  */
    /* JADX WARN: Code duplicated, block: B:154:0x0231  */
    /* JADX WARN: Code duplicated, block: B:158:0x023d  */
    /* JADX WARN: Code duplicated, block: B:161:0x0258  */
    /* JADX WARN: Code duplicated, block: B:164:0x026a  */
    /* JADX WARN: Code duplicated, block: B:166:0x0272  */
    /* JADX WARN: Code duplicated, block: B:169:0x0288  */
    /* JADX WARN: Code duplicated, block: B:170:0x028c  */
    /* JADX WARN: Code duplicated, block: B:172:0x0298  */
    /* JADX WARN: Code duplicated, block: B:175:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    /* JADX WARN: Code duplicated, block: B:24:0x0045  */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:29:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005e  */
    /* JADX WARN: Code duplicated, block: B:35:0x0061  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006d  */
    /* JADX WARN: Code duplicated, block: B:40:0x0070  */
    /* JADX WARN: Code duplicated, block: B:45:0x007a  */
    /* JADX WARN: Code duplicated, block: B:46:0x007d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0081  */
    /* JADX WARN: Code duplicated, block: B:50:0x0089  */
    /* JADX WARN: Code duplicated, block: B:51:0x008c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0099  */
    /* JADX WARN: Code duplicated, block: B:57:0x009e  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:76:0x00db  */
    /* JADX WARN: Code duplicated, block: B:77:0x00de  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:81:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:82:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:87:0x0103  */
    /* JADX WARN: Code duplicated, block: B:89:0x0112  */
    /* JADX WARN: Code duplicated, block: B:91:0x0115  */
    /* JADX WARN: Code duplicated, block: B:93:0x0127  */
    /* JADX WARN: Code duplicated, block: B:95:0x0136  */
    /* JADX WARN: Code duplicated, block: B:97:0x0139  */
    /* JADX WARN: Code duplicated, block: B:98:0x013c  */
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
    public static final void NavigationEventHandler(final NavigationEventState<? extends NavigationEventInfo> navigationEventState, boolean z, Function0<Unit> function0, Function0<Unit> function1, boolean z2, Function0<Unit> function2, Function0<Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        boolean z3;
        int i4;
        Function0<Unit> function4;
        int i5;
        int i6;
        Function0<Unit> function5;
        int i7;
        int i8;
        boolean z4;
        int i9;
        int i10;
        final Function0<Unit> function6;
        int i11;
        int i12;
        int i13;
        boolean z5;
        final boolean z6;
        final Function0<Unit> function7;
        final boolean z7;
        final Function0<Unit> function8;
        final Function0<Unit> function9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        NavigationEventDispatcherOwner current;
        final NavigationEventDispatcher navigationEventDispatcher;
        int i14;
        boolean z8;
        Object objRememberedValue;
        ComposeNavigationEventHandler composeNavigationEventHandler;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        Object obj;
        final ComposeNavigationEventHandler composeNavigationEventHandler2;
        boolean zChangedInstance;
        Object objRememberedValue2;
        Object objRememberedValue3;
        Object objRememberedValue4;
        Object objRememberedValue5;
        Object objRememberedValue6;
        Composer composerStartRestartGroup = composer.startRestartGroup(898330592);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NavigationEventHandler)N(state,isForwardEnabled,onForwardCancelled,onForwardCompleted,isBackEnabled,onBackCancelled,onBackCompleted)73@3839L2,74@3880L2,77@3982L2,78@4020L2,81@4106L7,87@4303L276,96@4596L467,96@4585L478,108@5093L373,108@5069L397:NavigationEventHandler.kt#wc8b4r");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(navigationEventState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i15 = i2 & 2;
        if (i15 == 0) {
            if ((i & 48) == 0) {
                z3 = z;
                i3 |= composerStartRestartGroup.changed(z3) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    function4 = function0;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        function5 = function1;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 16;
                    if (i8 != 0) {
                        if ((i & 24576) == 0) {
                            z4 = z2;
                            if (composerStartRestartGroup.changed(z4)) {
                                i9 = 16384;
                            } else {
                                i9 = 8192;
                            }
                            i3 |= i9;
                        }
                        i10 = i2 & 32;
                        if (i10 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            function6 = function2;
                        } else {
                            function6 = function2;
                            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                if (composerStartRestartGroup.changedInstance(function6)) {
                                    i11 = 131072;
                                } else {
                                    i11 = 65536;
                                }
                                i3 |= i11;
                            }
                        }
                        i12 = i2 & 64;
                        if (i12 != 0) {
                            i3 |= 1572864;
                        } else if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i13 = 1048576;
                            } else {
                                i13 = 524288;
                            }
                            i3 |= i13;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            composerStartRestartGroup.skipToGroupEnd();
                            z6 = z3;
                            function7 = function4;
                            z7 = z4;
                            function8 = function5;
                            function9 = function3;
                        } else {
                            if (i15 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            if (i4 != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Unit.INSTANCE;
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                function7 = (Function0) objRememberedValue6;
                            } else {
                                function7 = function4;
                            }
                            if (i6 != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Unit.INSTANCE;
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                function8 = (Function0) objRememberedValue5;
                            } else {
                                function8 = function5;
                            }
                            if (i8 != 0) {
                                z7 = true;
                            } else {
                                z7 = z4;
                            }
                            if (i10 != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Unit.INSTANCE;
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                function6 = (Function0) objRememberedValue4;
                            }
                            if (i12 != 0) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Unit.INSTANCE;
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                function9 = (Function0) objRememberedValue3;
                            } else {
                                function9 = function3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                            }
                            current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                            if (current != null) {
                                throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                            }
                            navigationEventDispatcher = current.getNavigationEventDispatcher();
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            i14 = i3 & 14;
                            if (i14 == 4) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!z8 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj2) {
                                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                    }
                                });
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                            if ((i3 & 112) == 32) {
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                            boolean z17 = zChangedInstance2 | z9;
                            if ((i3 & 896) == 256) {
                                z10 = true;
                            } else {
                                z10 = false;
                            }
                            boolean z18 = z17 | z10;
                            if ((i3 & 7168) == 2048) {
                                z11 = true;
                            } else {
                                z11 = false;
                            }
                            boolean z19 = z18 | z11;
                            if ((57344 & i3) == 16384) {
                                z12 = true;
                            } else {
                                z12 = false;
                            }
                            boolean z20 = z19 | z12;
                            if ((458752 & i3) == 131072) {
                                z13 = true;
                            } else {
                                z13 = false;
                            }
                            boolean z21 = z20 | z13;
                            if ((i3 & 3670016) == 1048576) {
                                z14 = true;
                            } else {
                                z14 = false;
                            }
                            boolean z22 = z21 | z14;
                            if (i14 == 4) {
                                z15 = true;
                            } else {
                                z15 = false;
                            }
                            z16 = z22 | z15;
                            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                            if (!z16 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                composeNavigationEventHandler2 = composeNavigationEventHandler;
                                obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(obj);
                            } else {
                                obj = objRememberedValue7;
                                composeNavigationEventHandler2 = composeNavigationEventHandler;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj2) {
                                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj2, Object obj3) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 24576;
                    z4 = z2;
                    i10 = i2 & 32;
                    if (i10 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function6 = function2;
                    } else {
                        function6 = function2;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 64;
                    if (i12 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i13 = 1048576;
                        } else {
                            i13 = 524288;
                        }
                        i3 |= i13;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        z6 = z3;
                        function7 = function4;
                        z7 = z4;
                        function8 = function5;
                        function9 = function3;
                    } else {
                        if (i15 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (i4 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function7 = (Function0) objRememberedValue6;
                        } else {
                            function7 = function4;
                        }
                        if (i6 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function8 = (Function0) objRememberedValue5;
                        } else {
                            function8 = function5;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z4;
                        }
                        if (i10 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function6 = (Function0) objRememberedValue4;
                        }
                        if (i12 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function9 = (Function0) objRememberedValue3;
                        } else {
                            function9 = function3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                        }
                        current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                        if (current != null) {
                            throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                        }
                        navigationEventDispatcher = current.getNavigationEventDispatcher();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        i14 = i3 & 14;
                        if (i14 == 4) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z8) {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                        if ((i3 & 112) == 32) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        boolean z110 = zChangedInstance3 | z9;
                        if ((i3 & 896) == 256) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        boolean z111 = z110 | z10;
                        if ((i3 & 7168) == 2048) {
                            z11 = true;
                        } else {
                            z11 = false;
                        }
                        boolean z112 = z111 | z11;
                        if ((57344 & i3) == 16384) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        boolean z23 = z112 | z12;
                        if ((458752 & i3) == 131072) {
                            z13 = true;
                        } else {
                            z13 = false;
                        }
                        boolean z24 = z23 | z13;
                        if ((i3 & 3670016) == 1048576) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z25 = z24 | z14;
                        if (i14 == 4) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        z16 = z25 | z15;
                        Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                        if (z16) {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        } else {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj2, Object obj3) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                function5 = function1;
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 32;
                    if (i10 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function6 = function2;
                    } else {
                        function6 = function2;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 64;
                    if (i12 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i13 = 1048576;
                        } else {
                            i13 = 524288;
                        }
                        i3 |= i13;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        z6 = z3;
                        function7 = function4;
                        z7 = z4;
                        function8 = function5;
                        function9 = function3;
                    } else {
                        if (i15 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (i4 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function7 = (Function0) objRememberedValue6;
                        } else {
                            function7 = function4;
                        }
                        if (i6 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function8 = (Function0) objRememberedValue5;
                        } else {
                            function8 = function5;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z4;
                        }
                        if (i10 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function6 = (Function0) objRememberedValue4;
                        }
                        if (i12 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function9 = (Function0) objRememberedValue3;
                        } else {
                            function9 = function3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                        }
                        current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                        if (current != null) {
                            throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                        }
                        navigationEventDispatcher = current.getNavigationEventDispatcher();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        i14 = i3 & 14;
                        if (i14 == 4) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z8) {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                        if ((i3 & 112) == 32) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        boolean z113 = zChangedInstance4 | z9;
                        if ((i3 & 896) == 256) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        boolean z114 = z113 | z10;
                        if ((i3 & 7168) == 2048) {
                            z11 = true;
                        } else {
                            z11 = false;
                        }
                        boolean z115 = z114 | z11;
                        if ((57344 & i3) == 16384) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        boolean z26 = z115 | z12;
                        if ((458752 & i3) == 131072) {
                            z13 = true;
                        } else {
                            z13 = false;
                        }
                        boolean z27 = z26 | z13;
                        if ((i3 & 3670016) == 1048576) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z28 = z27 | z14;
                        if (i14 == 4) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        z16 = z28 | z15;
                        Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                        if (z16) {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        } else {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj2, Object obj3) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z4 = z2;
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z116 = zChangedInstance5 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z117 = z116 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z118 = z117 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z29 = z118 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z210 = z29 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z211 = z210 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z211 | z15;
                    Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            function4 = function0;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function5 = function1;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 32;
                    if (i10 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function6 = function2;
                    } else {
                        function6 = function2;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 64;
                    if (i12 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i13 = 1048576;
                        } else {
                            i13 = 524288;
                        }
                        i3 |= i13;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        z6 = z3;
                        function7 = function4;
                        z7 = z4;
                        function8 = function5;
                        function9 = function3;
                    } else {
                        if (i15 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (i4 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function7 = (Function0) objRememberedValue6;
                        } else {
                            function7 = function4;
                        }
                        if (i6 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function8 = (Function0) objRememberedValue5;
                        } else {
                            function8 = function5;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z4;
                        }
                        if (i10 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function6 = (Function0) objRememberedValue4;
                        }
                        if (i12 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function9 = (Function0) objRememberedValue3;
                        } else {
                            function9 = function3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                        }
                        current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                        if (current != null) {
                            throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                        }
                        navigationEventDispatcher = current.getNavigationEventDispatcher();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        i14 = i3 & 14;
                        if (i14 == 4) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z8) {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                        if ((i3 & 112) == 32) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        boolean z119 = zChangedInstance6 | z9;
                        if ((i3 & 896) == 256) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        boolean z1110 = z119 | z10;
                        if ((i3 & 7168) == 2048) {
                            z11 = true;
                        } else {
                            z11 = false;
                        }
                        boolean z1111 = z1110 | z11;
                        if ((57344 & i3) == 16384) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        boolean z212 = z1111 | z12;
                        if ((458752 & i3) == 131072) {
                            z13 = true;
                        } else {
                            z13 = false;
                        }
                        boolean z213 = z212 | z13;
                        if ((i3 & 3670016) == 1048576) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z214 = z213 | z14;
                        if (i14 == 4) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        z16 = z214 | z15;
                        Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                        if (z16) {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        } else {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj2, Object obj3) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z4 = z2;
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z1112 = zChangedInstance7 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z1113 = z1112 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z1114 = z1113 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z215 = z1114 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z216 = z215 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z217 = z216 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z217 | z15;
                    Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function5 = function1;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z1115 = zChangedInstance8 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z1116 = z1115 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z1117 = z1116 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z218 = z1117 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z219 = z218 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z2110 = z219 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z2110 | z15;
                    Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            i10 = i2 & 32;
            if (i10 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function6 = function2;
            } else {
                function6 = function2;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z3;
                function7 = function4;
                z7 = z4;
                function8 = function5;
                function9 = function3;
            } else {
                if (i15 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function7 = (Function0) objRememberedValue6;
                } else {
                    function7 = function4;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function8 = (Function0) objRememberedValue5;
                } else {
                    function8 = function5;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z4;
                }
                if (i10 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function6 = (Function0) objRememberedValue4;
                }
                if (i12 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function9 = (Function0) objRememberedValue3;
                } else {
                    function9 = function3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                }
                current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                if (current != null) {
                    throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                }
                navigationEventDispatcher = current.getNavigationEventDispatcher();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                i14 = i3 & 14;
                if (i14 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                if ((i3 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z1118 = zChangedInstance9 | z9;
                if ((i3 & 896) == 256) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                boolean z1119 = z1118 | z10;
                if ((i3 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                boolean z11110 = z1119 | z11;
                if ((57344 & i3) == 16384) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                boolean z2111 = z11110 | z12;
                if ((458752 & i3) == 131072) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z2112 = z2111 | z13;
                if ((i3 & 3670016) == 1048576) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z2113 = z2112 | z14;
                if (i14 == 4) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                z16 = z2113 | z15;
                Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
                if (z16) {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        z3 = z;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                function4 = function0;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function5 = function1;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 32;
                    if (i10 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function6 = function2;
                    } else {
                        function6 = function2;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i11 = 131072;
                            } else {
                                i11 = 65536;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 64;
                    if (i12 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i13 = 1048576;
                        } else {
                            i13 = 524288;
                        }
                        i3 |= i13;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.skipToGroupEnd();
                        z6 = z3;
                        function7 = function4;
                        z7 = z4;
                        function8 = function5;
                        function9 = function3;
                    } else {
                        if (i15 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (i4 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function7 = (Function0) objRememberedValue6;
                        } else {
                            function7 = function4;
                        }
                        if (i6 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function8 = (Function0) objRememberedValue5;
                        } else {
                            function8 = function5;
                        }
                        if (i8 != 0) {
                            z7 = true;
                        } else {
                            z7 = z4;
                        }
                        if (i10 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function6 = (Function0) objRememberedValue4;
                        }
                        if (i12 != 0) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            function9 = (Function0) objRememberedValue3;
                        } else {
                            function9 = function3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                        }
                        current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                        if (current != null) {
                            throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                        }
                        navigationEventDispatcher = current.getNavigationEventDispatcher();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        i14 = i3 & 14;
                        if (i14 == 4) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!z8) {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                                }
                            });
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        boolean zChangedInstance10 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                        if ((i3 & 112) == 32) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        boolean z11111 = zChangedInstance10 | z9;
                        if ((i3 & 896) == 256) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        boolean z11112 = z11111 | z10;
                        if ((i3 & 7168) == 2048) {
                            z11 = true;
                        } else {
                            z11 = false;
                        }
                        boolean z11113 = z11112 | z11;
                        if ((57344 & i3) == 16384) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        boolean z2114 = z11113 | z12;
                        if ((458752 & i3) == 131072) {
                            z13 = true;
                        } else {
                            z13 = false;
                        }
                        boolean z2115 = z2114 | z13;
                        if ((i3 & 3670016) == 1048576) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        boolean z2116 = z2115 | z14;
                        if (i14 == 4) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        z16 = z2116 | z15;
                        Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
                        if (z16) {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        } else {
                            composeNavigationEventHandler2 = composeNavigationEventHandler;
                            obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(obj);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj2, Object obj3) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z4 = z2;
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance11 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z11114 = zChangedInstance11 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z11115 = z11114 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z11116 = z11115 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z2117 = z11116 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z2118 = z2117 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z2119 = z2118 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z2119 | z15;
                    Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function5 = function1;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance12 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z11117 = zChangedInstance12 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z11118 = z11117 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z11119 = z11118 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z21110 = z11119 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z21111 = z21110 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z21112 = z21111 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z21112 | z15;
                    Object objRememberedValue17 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            i10 = i2 & 32;
            if (i10 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function6 = function2;
            } else {
                function6 = function2;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z3;
                function7 = function4;
                z7 = z4;
                function8 = function5;
                function9 = function3;
            } else {
                if (i15 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function7 = (Function0) objRememberedValue6;
                } else {
                    function7 = function4;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function8 = (Function0) objRememberedValue5;
                } else {
                    function8 = function5;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z4;
                }
                if (i10 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function6 = (Function0) objRememberedValue4;
                }
                if (i12 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function9 = (Function0) objRememberedValue3;
                } else {
                    function9 = function3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                }
                current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                if (current != null) {
                    throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                }
                navigationEventDispatcher = current.getNavigationEventDispatcher();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                i14 = i3 & 14;
                if (i14 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                boolean zChangedInstance13 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                if ((i3 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z111110 = zChangedInstance13 | z9;
                if ((i3 & 896) == 256) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                boolean z111111 = z111110 | z10;
                if ((i3 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                boolean z111112 = z111111 | z11;
                if ((57344 & i3) == 16384) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                boolean z21113 = z111112 | z12;
                if ((458752 & i3) == 131072) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z21114 = z21113 | z13;
                if ((i3 & 3670016) == 1048576) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z21115 = z21114 | z14;
                if (i14 == 4) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                z16 = z21115 | z15;
                Object objRememberedValue18 = composerStartRestartGroup.rememberedValue();
                if (z16) {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        function4 = function0;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                function5 = function1;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 32;
                if (i10 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function6 = function2;
                } else {
                    function6 = function2;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 64;
                if (i12 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i13 = 1048576;
                    } else {
                        i13 = 524288;
                    }
                    i3 |= i13;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z3;
                    function7 = function4;
                    z7 = z4;
                    function8 = function5;
                    function9 = function3;
                } else {
                    if (i15 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function7 = (Function0) objRememberedValue6;
                    } else {
                        function7 = function4;
                    }
                    if (i6 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function8 = (Function0) objRememberedValue5;
                    } else {
                        function8 = function5;
                    }
                    if (i8 != 0) {
                        z7 = true;
                    } else {
                        z7 = z4;
                    }
                    if (i10 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function6 = (Function0) objRememberedValue4;
                    }
                    if (i12 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function9 = (Function0) objRememberedValue3;
                    } else {
                        function9 = function3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                    }
                    current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current != null) {
                        throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                    }
                    navigationEventDispatcher = current.getNavigationEventDispatcher();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    i14 = i3 & 14;
                    if (i14 == 4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z8) {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    boolean zChangedInstance14 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                    if ((i3 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z111113 = zChangedInstance14 | z9;
                    if ((i3 & 896) == 256) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    boolean z111114 = z111113 | z10;
                    if ((i3 & 7168) == 2048) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    boolean z111115 = z111114 | z11;
                    if ((57344 & i3) == 16384) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    boolean z21116 = z111115 | z12;
                    if ((458752 & i3) == 131072) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    boolean z21117 = z21116 | z13;
                    if ((i3 & 3670016) == 1048576) {
                        z14 = true;
                    } else {
                        z14 = false;
                    }
                    boolean z21118 = z21117 | z14;
                    if (i14 == 4) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    z16 = z21118 | z15;
                    Object objRememberedValue19 = composerStartRestartGroup.rememberedValue();
                    if (z16) {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    } else {
                        composeNavigationEventHandler2 = composeNavigationEventHandler;
                        obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(obj);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            i10 = i2 & 32;
            if (i10 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function6 = function2;
            } else {
                function6 = function2;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z3;
                function7 = function4;
                z7 = z4;
                function8 = function5;
                function9 = function3;
            } else {
                if (i15 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function7 = (Function0) objRememberedValue6;
                } else {
                    function7 = function4;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function8 = (Function0) objRememberedValue5;
                } else {
                    function8 = function5;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z4;
                }
                if (i10 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function6 = (Function0) objRememberedValue4;
                }
                if (i12 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function9 = (Function0) objRememberedValue3;
                } else {
                    function9 = function3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                }
                current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                if (current != null) {
                    throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                }
                navigationEventDispatcher = current.getNavigationEventDispatcher();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                i14 = i3 & 14;
                if (i14 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                boolean zChangedInstance15 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                if ((i3 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z111116 = zChangedInstance15 | z9;
                if ((i3 & 896) == 256) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                boolean z111117 = z111116 | z10;
                if ((i3 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                boolean z111118 = z111117 | z11;
                if ((57344 & i3) == 16384) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                boolean z21119 = z111118 | z12;
                if ((458752 & i3) == 131072) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z211110 = z21119 | z13;
                if ((i3 & 3670016) == 1048576) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z211111 = z211110 | z14;
                if (i14 == 4) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                z16 = z211111 | z15;
                Object objRememberedValue110 = composerStartRestartGroup.rememberedValue();
                if (z16) {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function5 = function1;
        i8 = i2 & 16;
        if (i8 != 0) {
            if ((i & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            i10 = i2 & 32;
            if (i10 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function6 = function2;
            } else {
                function6 = function2;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 64;
            if (i12 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i13 = 1048576;
                } else {
                    i13 = 524288;
                }
                i3 |= i13;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z3;
                function7 = function4;
                z7 = z4;
                function8 = function5;
                function9 = function3;
            } else {
                if (i15 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function7 = (Function0) objRememberedValue6;
                } else {
                    function7 = function4;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function8 = (Function0) objRememberedValue5;
                } else {
                    function8 = function5;
                }
                if (i8 != 0) {
                    z7 = true;
                } else {
                    z7 = z4;
                }
                if (i10 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function6 = (Function0) objRememberedValue4;
                }
                if (i12 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function9 = (Function0) objRememberedValue3;
                } else {
                    function9 = function3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
                }
                current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                if (current != null) {
                    throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
                }
                navigationEventDispatcher = current.getNavigationEventDispatcher();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
                i14 = i3 & 14;
                if (i14 == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z8) {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
                boolean zChangedInstance16 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
                if ((i3 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z111119 = zChangedInstance16 | z9;
                if ((i3 & 896) == 256) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                boolean z1111110 = z111119 | z10;
                if ((i3 & 7168) == 2048) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                boolean z1111111 = z1111110 | z11;
                if ((57344 & i3) == 16384) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                boolean z211112 = z1111111 | z12;
                if ((458752 & i3) == 131072) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                boolean z211113 = z211112 | z13;
                if ((i3 & 3670016) == 1048576) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                boolean z211114 = z211113 | z14;
                if (i14 == 4) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                z16 = z211114 | z15;
                Object objRememberedValue111 = composerStartRestartGroup.rememberedValue();
                if (z16) {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    composeNavigationEventHandler2 = composeNavigationEventHandler;
                    obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z4 = z2;
        i10 = i2 & 32;
        if (i10 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function2;
        } else {
            function6 = function2;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i3 |= i11;
            }
        }
        i12 = i2 & 64;
        if (i12 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i13 = 1048576;
            } else {
                i13 = 524288;
            }
            i3 |= i13;
        }
        if ((i3 & 599187) != 599186) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            z6 = z3;
            function7 = function4;
            z7 = z4;
            function8 = function5;
            function9 = function3;
        } else {
            if (i15 != 0) {
                z6 = true;
            } else {
                z6 = z3;
            }
            if (i4 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7279202, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function7 = (Function0) objRememberedValue6;
            } else {
                function7 = function4;
            }
            if (i6 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7280514, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function8 = (Function0) objRememberedValue5;
            } else {
                function8 = function5;
            }
            if (i8 != 0) {
                z7 = true;
            } else {
                z7 = z4;
            }
            if (i10 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7283778, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function6 = (Function0) objRememberedValue4;
            }
            if (i12 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7284994, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function9 = (Function0) objRememberedValue3;
            } else {
                function9 = function3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(898330592, i3, -1, "androidx.navigationevent.compose.NavigationEventHandler (NavigationEventHandler.kt:79)");
            }
            current = LocalNavigationEventDispatcherOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
            if (current != null) {
                throw new IllegalStateException("No NavigationEventDispatcher was provided via LocalNavigationEventDispatcherOwner".toString());
            }
            navigationEventDispatcher = current.getNavigationEventDispatcher();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7294324, "CC(remember):NavigationEventHandler.kt#9igjgp");
            i14 = i3 & 14;
            if (i14 == 4) {
                z8 = true;
            } else {
                z8 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z8) {
                objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new ComposeNavigationEventHandler(navigationEventState.getCurrentInfo(), new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$10$lambda$9(navigationEventState, (NavigationEventTransitionState) obj2);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composeNavigationEventHandler = (ComposeNavigationEventHandler) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7303891, "CC(remember):NavigationEventHandler.kt#9igjgp");
            boolean zChangedInstance17 = composerStartRestartGroup.changedInstance(composeNavigationEventHandler);
            if ((i3 & 112) == 32) {
                z9 = true;
            } else {
                z9 = false;
            }
            boolean z1111112 = zChangedInstance17 | z9;
            if ((i3 & 896) == 256) {
                z10 = true;
            } else {
                z10 = false;
            }
            boolean z1111113 = z1111112 | z10;
            if ((i3 & 7168) == 2048) {
                z11 = true;
            } else {
                z11 = false;
            }
            boolean z1111114 = z1111113 | z11;
            if ((57344 & i3) == 16384) {
                z12 = true;
            } else {
                z12 = false;
            }
            boolean z211115 = z1111114 | z12;
            if ((458752 & i3) == 131072) {
                z13 = true;
            } else {
                z13 = false;
            }
            boolean z211116 = z211115 | z13;
            if ((i3 & 3670016) == 1048576) {
                z14 = true;
            } else {
                z14 = false;
            }
            boolean z211117 = z211116 | z14;
            if (i14 == 4) {
                z15 = true;
            } else {
                z15 = false;
            }
            z16 = z211117 | z15;
            Object objRememberedValue112 = composerStartRestartGroup.rememberedValue();
            if (z16) {
                composeNavigationEventHandler2 = composeNavigationEventHandler;
                obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                composeNavigationEventHandler2 = composeNavigationEventHandler;
                obj = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$12$lambda$11(composeNavigationEventHandler2, z6, function7, function8, z7, function6, function9, navigationEventState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 7319701, "CC(remember):NavigationEventHandler.kt#9igjgp");
            zChangedInstance = composerStartRestartGroup.changedInstance(composeNavigationEventHandler2) | (i14 == 4) | composerStartRestartGroup.changedInstance(navigationEventDispatcher);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return NavigationEventHandlerKt.NavigationEventHandler$lambda$16$lambda$15(navigationEventState, composeNavigationEventHandler2, navigationEventDispatcher, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(navigationEventState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return NavigationEventHandlerKt.NavigationEventHandler$lambda$17(navigationEventState, z6, function7, function8, z7, function6, function9, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit NavigationEventHandler$lambda$10$lambda$9(NavigationEventState navigationEventState, NavigationEventTransitionState navigationEventTransitionState) {
        navigationEventState.setTransitionState$navigationevent_compose(navigationEventTransitionState);
        return Unit.INSTANCE;
    }

    static final Unit NavigationEventHandler$lambda$12$lambda$11(ComposeNavigationEventHandler composeNavigationEventHandler, boolean z, Function0 function0, Function0 function1, boolean z2, Function0 function2, Function0 function3, NavigationEventState navigationEventState) {
        composeNavigationEventHandler.setForwardEnabled(z);
        composeNavigationEventHandler.setCurrentOnForwardCancelled(function0);
        composeNavigationEventHandler.setCurrentOnForwardCompleted(function1);
        composeNavigationEventHandler.setBackEnabled(z2);
        composeNavigationEventHandler.setCurrentOnBackCancelled(function2);
        composeNavigationEventHandler.setCurrentOnBackCompleted(function3);
        composeNavigationEventHandler.setInfo(navigationEventState.getCurrentInfo(), navigationEventState.getBackInfo(), navigationEventState.getForwardInfo());
        return Unit.INSTANCE;
    }

    static final DisposableEffectResult NavigationEventHandler$lambda$16$lambda$15(final NavigationEventState navigationEventState, final ComposeNavigationEventHandler composeNavigationEventHandler, NavigationEventDispatcher navigationEventDispatcher, DisposableEffectScope disposableEffectScope) {
        if (navigationEventState.getSourceHandler$navigationevent_compose() != null) {
            throw new IllegalArgumentException(("NavigationEventState '" + navigationEventState + "' is already registered with a NavigationEventHandler '" + composeNavigationEventHandler + "'.").toString());
        }
        ComposeNavigationEventHandler composeNavigationEventHandler2 = composeNavigationEventHandler;
        navigationEventState.setSourceHandler$navigationevent_compose(composeNavigationEventHandler2);
        NavigationEventDispatcher.addHandler$default(navigationEventDispatcher, composeNavigationEventHandler2, 0, 2, null);
        return new DisposableEffectResult() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$NavigationEventHandler$lambda$16$lambda$15$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                composeNavigationEventHandler.remove();
                navigationEventState.setSourceHandler$navigationevent_compose(null);
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0044  */
    /* JADX WARN: Code duplicated, block: B:24:0x0047  */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:36:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:45:0x0080 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x0083  */
    /* JADX WARN: Code duplicated, block: B:50:0x0088  */
    /* JADX WARN: Code duplicated, block: B:52:0x009a  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:57:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:60:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:66:0x0116  */
    /* JADX WARN: Code duplicated, block: B:68:0x011c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0127  */
    /* JADX WARN: Code duplicated, block: B:73:? A[RETURN, SYNTHETIC] */
    public static final void NavigationBackHandler(final NavigationEventState<? extends NavigationEventInfo> navigationEventState, boolean z, Function0<Unit> function0, final Function0<Unit> function1, Composer composer, final int i, final int i2) {
        NavigationEventState<? extends NavigationEventInfo> navigationEventState2;
        int i3;
        boolean z2;
        int i4;
        Function0<Unit> function2;
        int i5;
        boolean z3;
        final boolean z4;
        final Function0<Unit> function3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function0<Unit> function4;
        Object objRememberedValue;
        Object objRememberedValue2;
        Object objRememberedValue3;
        int i6;
        Composer composerStartRestartGroup = composer.startRestartGroup(1220469155);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NavigationBackHandler)N(state,isBackEnabled,onBackCancelled,onBackCompleted)144@6491L2,149@6612L2,150@6645L2,147@6536L296:NavigationEventHandler.kt#wc8b4r");
        if ((i & 6) == 0) {
            navigationEventState2 = navigationEventState;
            i3 = (composerStartRestartGroup.changed(navigationEventState2) ? 4 : 2) | i;
        } else {
            navigationEventState2 = navigationEventState;
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    function2 = function0;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i3 |= i6;
                }
                if ((i3 & 1171) != 1170) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    z4 = z2;
                    function3 = function2;
                } else {
                    boolean z5 = i7 == 0 ? z2 : true;
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344791685, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda10
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function4 = (Function0) objRememberedValue3;
                    } else {
                        function4 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1220469155, i3, -1, "androidx.navigationevent.compose.NavigationBackHandler (NavigationEventHandler.kt:146)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344795557, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function0 function5 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344796613, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function0 function6 = (Function0) objRememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    int i8 = (i3 & 14) | 3504;
                    int i9 = i3 << 9;
                    NavigationEventHandler(navigationEventState2, false, function5, function6, z5, function4, function1, composerStartRestartGroup, i8 | (57344 & i9) | (458752 & i9) | (i9 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z5;
                    function3 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationEventHandlerKt.NavigationBackHandler$lambda$24(navigationEventState, z4, function3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            function2 = function0;
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z4 = z2;
                function3 = function2;
            } else {
                if (i7 == 0) {
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344791685, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function4 = (Function0) objRememberedValue3;
                } else {
                    function4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1220469155, i3, -1, "androidx.navigationevent.compose.NavigationBackHandler (NavigationEventHandler.kt:146)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344795557, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function0 function7 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344796613, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function8 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i10 = (i3 & 14) | 3504;
                int i11 = i3 << 9;
                NavigationEventHandler(navigationEventState2, false, function7, function8, z5, function4, function1, composerStartRestartGroup, i10 | (57344 & i11) | (458752 & i11) | (i11 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                function3 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationEventHandlerKt.NavigationBackHandler$lambda$24(navigationEventState, z4, function3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        z2 = z;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                function2 = function0;
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z4 = z2;
                function3 = function2;
            } else {
                if (i7 == 0) {
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344791685, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function4 = (Function0) objRememberedValue3;
                } else {
                    function4 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1220469155, i3, -1, "androidx.navigationevent.compose.NavigationBackHandler (NavigationEventHandler.kt:146)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344795557, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function0 function9 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344796613, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function10 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                int i12 = (i3 & 14) | 3504;
                int i13 = i3 << 9;
                NavigationEventHandler(navigationEventState2, false, function9, function10, z5, function4, function1, composerStartRestartGroup, i12 | (57344 & i13) | (458752 & i13) | (i13 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                function3 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationEventHandlerKt.NavigationBackHandler$lambda$24(navigationEventState, z4, function3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        function2 = function0;
        if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i6 = 2048;
            } else {
                i6 = 1024;
            }
            i3 |= i6;
        }
        if ((i3 & 1171) != 1170) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            z4 = z2;
            function3 = function2;
        } else {
            if (i7 == 0) {
            }
            if (i4 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344791685, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function4 = (Function0) objRememberedValue3;
            } else {
                function4 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1220469155, i3, -1, "androidx.navigationevent.compose.NavigationBackHandler (NavigationEventHandler.kt:146)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344795557, "CC(remember):NavigationEventHandler.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function0 function11 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 344796613, "CC(remember):NavigationEventHandler.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function0 function12 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i14 = (i3 & 14) | 3504;
            int i15 = i3 << 9;
            NavigationEventHandler(navigationEventState2, false, function11, function12, z5, function4, function1, composerStartRestartGroup, i14 | (57344 & i15) | (458752 & i15) | (i15 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z5;
            function3 = function4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationEventHandlerKt.NavigationBackHandler$lambda$24(navigationEventState, z4, function3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0044  */
    /* JADX WARN: Code duplicated, block: B:24:0x0047  */
    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:28:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:36:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:45:0x0080 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x0082  */
    /* JADX WARN: Code duplicated, block: B:47:0x0084  */
    /* JADX WARN: Code duplicated, block: B:50:0x0089  */
    /* JADX WARN: Code duplicated, block: B:52:0x009b  */
    /* JADX WARN: Code duplicated, block: B:54:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:57:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:66:0x0114  */
    /* JADX WARN: Code duplicated, block: B:68:0x011a  */
    /* JADX WARN: Code duplicated, block: B:71:0x0126  */
    /* JADX WARN: Code duplicated, block: B:73:? A[RETURN, SYNTHETIC] */
    public static final void NavigationForwardHandler(final NavigationEventState<? extends NavigationEventInfo> navigationEventState, boolean z, Function0<Unit> function0, final Function0<Unit> function1, Composer composer, final int i, final int i2) {
        NavigationEventState<? extends NavigationEventInfo> navigationEventState2;
        int i3;
        boolean z2;
        int i4;
        int i5;
        boolean z3;
        final Function0<Unit> function2;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z5;
        Function0<Unit> function3;
        Object objRememberedValue;
        Object objRememberedValue2;
        Object objRememberedValue3;
        int i6;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1737636087);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NavigationForwardHandler)N(state,isForwardEnabled,onForwardCancelled,onForwardCompleted)179@7856L2,187@8120L2,188@8150L2,182@7904L302:NavigationEventHandler.kt#wc8b4r");
        if ((i & 6) == 0) {
            navigationEventState2 = navigationEventState;
            i3 = (composerStartRestartGroup.changed(navigationEventState2) ? 4 : 2) | i;
        } else {
            navigationEventState2 = navigationEventState;
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                    if (composerStartRestartGroup.changedInstance(function0)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i3 |= i6;
                }
                if ((i3 & 1171) != 1170) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.skipToGroupEnd();
                    function2 = function0;
                    z4 = z2;
                } else {
                    if (i7 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                    if (i4 != 0) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874004363, "CC(remember):NavigationEventHandler.kt#9igjgp");
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        function3 = (Function0) objRememberedValue3;
                    } else {
                        function3 = function0;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1737636087, i3, -1, "androidx.navigationevent.compose.NavigationForwardHandler (NavigationEventHandler.kt:181)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874012811, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function0 function4 = (Function0) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874013771, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    NavigationEventHandler(navigationEventState2, z5, function3, function1, false, function4, (Function0) objRememberedValue2, composerStartRestartGroup, (i3 & 14) | 1794048 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z5;
                    function2 = function3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationEventHandlerKt.NavigationForwardHandler$lambda$31(navigationEventState, z4, function2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                function2 = function0;
                z4 = z2;
            } else {
                if (i7 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874004363, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function3 = (Function0) objRememberedValue3;
                } else {
                    function3 = function0;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1737636087, i3, -1, "androidx.navigationevent.compose.NavigationForwardHandler (NavigationEventHandler.kt:181)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874012811, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function0 function5 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874013771, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                NavigationEventHandler(navigationEventState2, z5, function3, function1, false, function5, (Function0) objRememberedValue2, composerStartRestartGroup, (i3 & 14) | 1794048 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                function2 = function3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationEventHandlerKt.NavigationForwardHandler$lambda$31(navigationEventState, z4, function2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        z2 = z;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if (composerStartRestartGroup.changedInstance(function0)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i3 |= i6;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                function2 = function0;
                z4 = z2;
            } else {
                if (i7 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874004363, "CC(remember):NavigationEventHandler.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function3 = (Function0) objRememberedValue3;
                } else {
                    function3 = function0;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1737636087, i3, -1, "androidx.navigationevent.compose.NavigationForwardHandler (NavigationEventHandler.kt:181)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874012811, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function0 function6 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874013771, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                NavigationEventHandler(navigationEventState2, z5, function3, function1, false, function6, (Function0) objRememberedValue2, composerStartRestartGroup, (i3 & 14) | 1794048 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                function2 = function3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationEventHandlerKt.NavigationForwardHandler$lambda$31(navigationEventState, z4, function2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i6 = 2048;
            } else {
                i6 = 1024;
            }
            i3 |= i6;
        }
        if ((i3 & 1171) != 1170) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
            function2 = function0;
            z4 = z2;
        } else {
            if (i7 != 0) {
                z5 = true;
            } else {
                z5 = z2;
            }
            if (i4 != 0) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874004363, "CC(remember):NavigationEventHandler.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function3 = (Function0) objRememberedValue3;
            } else {
                function3 = function0;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1737636087, i3, -1, "androidx.navigationevent.compose.NavigationForwardHandler (NavigationEventHandler.kt:181)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874012811, "CC(remember):NavigationEventHandler.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function0 function7 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 874013771, "CC(remember):NavigationEventHandler.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            NavigationEventHandler(navigationEventState2, z5, function3, function1, false, function7, (Function0) objRememberedValue2, composerStartRestartGroup, (i3 & 14) | 1794048 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z5;
            function2 = function3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigationevent.compose.NavigationEventHandlerKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationEventHandlerKt.NavigationForwardHandler$lambda$31(navigationEventState, z4, function2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
