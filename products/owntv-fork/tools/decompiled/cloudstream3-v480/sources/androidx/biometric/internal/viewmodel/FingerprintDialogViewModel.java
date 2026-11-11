package androidx.biometric.internal.viewmodel;

import androidx.biometric.R;
import androidx.biometric.internal.FingerprintManagerCompat;
import androidx.biometric.internal.ui.FingerprintDialogState;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FingerprintDialogViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000bJ\u0016\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020 J\u000e\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020 J\u0018\u00101\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015H\u0002J\u0018\u00102\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR \u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000b0\u001f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000b0\u001f0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001d¨\u00063"}, d2 = {"Landroidx/biometric/internal/viewmodel/FingerprintDialogViewModel;", "Landroidx/lifecycle/ViewModel;", "fingerprintPreAuthChecker", "Lkotlin/Function1;", "Landroidx/biometric/internal/FingerprintManagerCompat;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getFingerprintPreAuthChecker", "()Lkotlin/jvm/functions/Function1;", "isDismissedInstantly", "", "()Z", "setDismissedInstantly", "(Z)V", "_isCancelPending", "Lkotlinx/coroutines/flow/MutableStateFlow;", "isCancelPending", "Lkotlinx/coroutines/flow/Flow;", "()Lkotlinx/coroutines/flow/Flow;", "_previousState", "Landroidx/biometric/internal/ui/FingerprintDialogState;", "previousState", "getPreviousState", "()Landroidx/biometric/internal/ui/FingerprintDialogState;", "_state", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "_helpMessage", "Lkotlin/Pair;", "", "helpMessageInfo", "getHelpMessageInfo", "_drawableResId", "drawableResId", "getDrawableResId", "_shouldAnimateIcon", "shouldAnimateIcon", "getShouldAnimateIcon", "setCancelPending", "", "cancelPending", "setState", "newState", "newHelpMessage", "resetState", "defaultHelpMessage", "shouldAnimateForTransition", "getAssetForTransition", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FingerprintDialogViewModel extends ViewModel {
    private final MutableStateFlow<Integer> _drawableResId;
    private final MutableStateFlow<Pair<CharSequence, Boolean>> _helpMessage;
    private final MutableStateFlow<Boolean> _isCancelPending;
    private FingerprintDialogState _previousState;
    private final MutableStateFlow<Boolean> _shouldAnimateIcon;
    private final MutableStateFlow<FingerprintDialogState> _state;
    private final StateFlow<Integer> drawableResId;
    private final Function1<FingerprintManagerCompat, Integer> fingerprintPreAuthChecker;
    private final StateFlow<Pair<CharSequence, Boolean>> helpMessageInfo;
    private final Flow<Boolean> isCancelPending;
    private boolean isDismissedInstantly;
    private final StateFlow<Boolean> shouldAnimateIcon;
    private final StateFlow<FingerprintDialogState> state;

    /* JADX WARN: Multi-variable type inference failed */
    public FingerprintDialogViewModel() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FingerprintDialogViewModel(Function1<? super FingerprintManagerCompat, Integer> fingerprintPreAuthChecker) {
        Intrinsics.checkNotNullParameter(fingerprintPreAuthChecker, "fingerprintPreAuthChecker");
        this.fingerprintPreAuthChecker = fingerprintPreAuthChecker;
        this.isDismissedInstantly = true;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(false);
        this._isCancelPending = MutableStateFlow;
        final MutableStateFlow<Boolean> mutableStateFlow = MutableStateFlow;
        this.isCancelPending = new Flow<Boolean>() { // from class: androidx.biometric.internal.viewmodel.FingerprintDialogViewModel$special$$inlined$filter$1

            /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.FingerprintDialogViewModel$special$$inlined$filter$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", ES6Iterator.VALUE_PROPERTY, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 1, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.FingerprintDialogViewModel$special$$inlined$filter$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
                @DebugMetadata(c = "androidx.biometric.internal.viewmodel.FingerprintDialogViewModel$special$$inlined$filter$1$2", f = "FingerprintDialogViewModel.kt", i = {}, l = {223}, m = "emit", n = {}, s = {}, v = 1)
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        if (((Boolean) obj).booleanValue()) {
                            anonymousClass1.label = 1;
                            if (flowCollector.emit(obj, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Boolean> flowCollector, Continuation continuation) {
                Object objCollect = mutableStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        this._previousState = FingerprintDialogState.NONE;
        MutableStateFlow<FingerprintDialogState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(FingerprintDialogState.NONE);
        this._state = MutableStateFlow2;
        this.state = MutableStateFlow2;
        MutableStateFlow<Pair<CharSequence, Boolean>> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new Pair("", false));
        this._helpMessage = MutableStateFlow3;
        this.helpMessageInfo = MutableStateFlow3;
        MutableStateFlow<Integer> MutableStateFlow4 = StateFlowKt.MutableStateFlow(0);
        this._drawableResId = MutableStateFlow4;
        this.drawableResId = MutableStateFlow4;
        MutableStateFlow<Boolean> MutableStateFlow5 = StateFlowKt.MutableStateFlow(false);
        this._shouldAnimateIcon = MutableStateFlow5;
        this.shouldAnimateIcon = MutableStateFlow5;
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.viewmodel.FingerprintDialogViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: FingerprintDialogViewModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FingerprintManagerCompat, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, FingerprintDialogViewModelKt.class, "checkForFingerprintPreAuthenticationErrors", "checkForFingerprintPreAuthenticationErrors(Landroidx/biometric/internal/FingerprintManagerCompat;)I", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Integer invoke(FingerprintManagerCompat p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Integer.valueOf(FingerprintDialogViewModelKt.checkForFingerprintPreAuthenticationErrors(p0));
        }
    }

    public final Function1<FingerprintManagerCompat, Integer> getFingerprintPreAuthChecker() {
        return this.fingerprintPreAuthChecker;
    }

    public /* synthetic */ FingerprintDialogViewModel(AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? AnonymousClass1.INSTANCE : anonymousClass1);
    }

    /* JADX INFO: renamed from: isDismissedInstantly, reason: from getter */
    public final boolean getIsDismissedInstantly() {
        return this.isDismissedInstantly;
    }

    public final void setDismissedInstantly(boolean z) {
        this.isDismissedInstantly = z;
    }

    public final Flow<Boolean> isCancelPending() {
        return this.isCancelPending;
    }

    /* JADX INFO: renamed from: getPreviousState, reason: from getter */
    public final FingerprintDialogState get_previousState() {
        return this._previousState;
    }

    public final StateFlow<FingerprintDialogState> getState() {
        return this.state;
    }

    public final StateFlow<Pair<CharSequence, Boolean>> getHelpMessageInfo() {
        return this.helpMessageInfo;
    }

    public final StateFlow<Integer> getDrawableResId() {
        return this.drawableResId;
    }

    public final StateFlow<Boolean> getShouldAnimateIcon() {
        return this.shouldAnimateIcon;
    }

    public final void setCancelPending(boolean cancelPending) {
        this._isCancelPending.setValue(Boolean.valueOf(cancelPending));
    }

    public final void setState(FingerprintDialogState newState, CharSequence newHelpMessage) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        Intrinsics.checkNotNullParameter(newHelpMessage, "newHelpMessage");
        this._state.setValue(newState);
        this._helpMessage.setValue(new Pair<>(newHelpMessage, Boolean.valueOf(newState == FingerprintDialogState.FINGERPRINT_ERROR)));
        this._drawableResId.setValue(Integer.valueOf(getAssetForTransition(get_previousState(), newState)));
        this._shouldAnimateIcon.setValue(Boolean.valueOf(shouldAnimateForTransition(get_previousState(), newState)));
        this._previousState = newState;
    }

    public final void resetState(CharSequence defaultHelpMessage) {
        Intrinsics.checkNotNullParameter(defaultHelpMessage, "defaultHelpMessage");
        this._previousState = FingerprintDialogState.NONE;
        setState(FingerprintDialogState.FINGERPRINT, defaultHelpMessage);
    }

    private final boolean shouldAnimateForTransition(FingerprintDialogState previousState, FingerprintDialogState state) {
        if (previousState == FingerprintDialogState.NONE && state == FingerprintDialogState.FINGERPRINT) {
            return false;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT && state == FingerprintDialogState.FINGERPRINT_ERROR) {
            return true;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT_ERROR && state == FingerprintDialogState.FINGERPRINT) {
            return true;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT) {
            FingerprintDialogState fingerprintDialogState = FingerprintDialogState.FINGERPRINT_AUTHENTICATED;
        }
        return false;
    }

    private final int getAssetForTransition(FingerprintDialogState previousState, FingerprintDialogState state) {
        if (previousState == FingerprintDialogState.NONE && state == FingerprintDialogState.FINGERPRINT) {
            return R.drawable.fingerprint_dialog_fp_icon;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT && state == FingerprintDialogState.FINGERPRINT_ERROR) {
            return R.drawable.fingerprint_dialog_error;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT_ERROR && state == FingerprintDialogState.FINGERPRINT) {
            return R.drawable.fingerprint_dialog_fp_icon;
        }
        if (previousState == FingerprintDialogState.FINGERPRINT && state == FingerprintDialogState.FINGERPRINT_AUTHENTICATED) {
            return R.drawable.fingerprint_dialog_fp_icon;
        }
        return 0;
    }
}
