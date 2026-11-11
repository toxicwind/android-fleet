package com.lagradost.cloudstream3.ui.settings;

import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.DeviceAuthBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthPinData;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import qrcode.QRCode;

/* JADX INFO: compiled from: SettingsAccount.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/SettingsAccount$Companion;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showPin$2", f = "SettingsAccount.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, nl = {178}, s = {}, v = 2)
final class SettingsAccount$Companion$showPin$2 extends SuspendLambda implements Function3<CoroutineScope, SettingsAccount.Companion, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ AuthRepo $api;
    final /* synthetic */ DeviceAuthBinding $binding;
    final /* synthetic */ AlertDialog $dialog;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsAccount$Companion$showPin$2(AuthRepo authRepo, FragmentActivity fragmentActivity, AlertDialog alertDialog, DeviceAuthBinding deviceAuthBinding, Continuation<? super SettingsAccount$Companion$showPin$2> continuation) {
        super(3, continuation);
        this.$api = authRepo;
        this.$activity = fragmentActivity;
        this.$dialog = alertDialog;
        this.$binding = deviceAuthBinding;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SettingsAccount.Companion companion, Continuation<? super Unit> continuation) {
        return new SettingsAccount$Companion$showPin$2(this.$api, this.$activity, this.$dialog, this.$binding, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws ErrorLoadingException {
        final AuthPinData authPinData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.$api.pinRequest(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            authPinData = (AuthPinData) obj;
        } catch (ErrorLoadingException e) {
            if (e.getMessage() != null) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, e.getMessage(), (Integer) null, 2, (Object) null);
                authPinData = null;
            } else {
                throw e;
            }
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            authPinData = null;
        }
        if (authPinData == null) {
            if (this.$api.getHasOAuth2()) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.device_pin_error_message, (Integer) null, 2, (Object) null);
                this.$api.openOAuth2PageWithToast();
            } else {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, this.$api.getName()), (Integer) null, 2, (Object) null);
            }
            return Unit.INSTANCE;
        }
        Object objNativeImage = QRCode.render$default(QRCode.INSTANCE.ofRoundedSquares().withColor(UIHelper.INSTANCE.colorFromAttribute(this.$activity, R.attr.textColor)).withBackgroundColor(UIHelper.INSTANCE.colorFromAttribute(this.$activity, R.attr.primaryBlackBackground)).build(authPinData.getVerificationUrl()), null, 0, 0, 7, null).nativeImage();
        Intrinsics.checkNotNull(objNativeImage, "null cannot be cast to non-null type android.graphics.Bitmap");
        final Bitmap bitmap = (Bitmap) objNativeImage;
        final FragmentActivity fragmentActivity = this.$activity;
        final AlertDialog alertDialog = this.$dialog;
        final DeviceAuthBinding deviceAuthBinding = this.$binding;
        final AuthRepo authRepo = this.$api;
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showPin$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SettingsAccount$Companion$showPin$2.invokeSuspend$lambda$0(alertDialog, deviceAuthBinding, authPinData, bitmap, authRepo, fragmentActivity);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showPin$2$1$2] */
    static final void invokeSuspend$lambda$0(final AlertDialog alertDialog, final DeviceAuthBinding deviceAuthBinding, final AuthPinData authPinData, Bitmap bitmap, final AuthRepo authRepo, final FragmentActivity fragmentActivity) {
        alertDialog.show();
        TextUtilKt.setText(deviceAuthBinding.devicePinCode, TextUtilKt.txt(authPinData.getUserCode()));
        TextUtilKt.setText(deviceAuthBinding.deviceAuthMessage, TextUtilKt.txt(R.string.device_pin_url_message, authPinData.getVerificationUrl()));
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        ImageView deviceAuthQrcode = deviceAuthBinding.deviceAuthQrcode;
        Intrinsics.checkNotNullExpressionValue(deviceAuthQrcode, "deviceAuthQrcode");
        ImageLoader.loadImage$default(imageLoader, deviceAuthQrcode, bitmap, (Function1) null, 2, (Object) null);
        final long expiresIn = authPinData.getExpiresIn() * 1000;
        new CountDownTimer(expiresIn) { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showPin$2$1$2
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                int i = (int) (millisUntilFinished / 1000);
                TextUtilKt.setText(deviceAuthBinding.deviceAuthValidationCounter, TextUtilKt.txt(R.string.device_pin_counter_text, Integer.valueOf(i / 60), Integer.valueOf(i % 60)));
                Coroutines.INSTANCE.ioSafe(this, new SettingsAccount$Companion$showPin$2$1$2$onTick$1(i, authPinData, authRepo, alertDialog, fragmentActivity, this, null));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.device_pin_expired_message, (Integer) null, 2, (Object) null);
                UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
            }
        }.start();
    }
}
