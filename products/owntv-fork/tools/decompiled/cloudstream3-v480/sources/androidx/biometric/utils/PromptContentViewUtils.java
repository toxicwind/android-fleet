package androidx.biometric.utils;

import android.content.DialogInterface;
import android.hardware.biometrics.PromptContentView;
import androidx.biometric.PromptContentItem;
import androidx.biometric.PromptContentItemBulletedText;
import androidx.biometric.PromptContentItemPlainText;
import androidx.biometric.PromptContentViewWithMoreOptionsButton;
import androidx.biometric.PromptVerticalListContentView;
import j$.lang.Iterable;
import j$.util.function.Consumer$CC;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class PromptContentViewUtils {
    private PromptContentViewUtils() {
    }

    public static PromptContentView wrapForBiometricPrompt(androidx.biometric.PromptContentView promptContentView, Executor executor, DialogInterface.OnClickListener onClickListener) {
        if (promptContentView == null) {
            return null;
        }
        if (promptContentView instanceof PromptVerticalListContentView) {
            return Api35Impl.createPromptVerticalListContentView((PromptVerticalListContentView) promptContentView);
        }
        if (promptContentView instanceof PromptContentViewWithMoreOptionsButton) {
            return Api35Impl.createPromptContentViewWithMoreOptionsButton((PromptContentViewWithMoreOptionsButton) promptContentView, executor, onClickListener);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Api35Impl {
        private Api35Impl() {
        }

        static PromptContentView createPromptVerticalListContentView(PromptVerticalListContentView promptVerticalListContentView) {
            final android.hardware.biometrics.PromptVerticalListContentView.Builder builder = new android.hardware.biometrics.PromptVerticalListContentView.Builder();
            if (promptVerticalListContentView.getDescription() != null) {
                builder.setDescription(promptVerticalListContentView.getDescription());
            }
            Iterable.EL.forEach(promptVerticalListContentView.getListItems(), new Consumer() { // from class: androidx.biometric.utils.PromptContentViewUtils$Api35Impl$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PromptContentViewUtils.Api35Impl.lambda$createPromptVerticalListContentView$0(builder, (PromptContentItem) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            return builder.build();
        }

        static /* synthetic */ void lambda$createPromptVerticalListContentView$0(android.hardware.biometrics.PromptVerticalListContentView.Builder builder, PromptContentItem promptContentItem) {
            if (promptContentItem instanceof PromptContentItemPlainText) {
                builder.addListItem(new android.hardware.biometrics.PromptContentItemPlainText(((PromptContentItemPlainText) promptContentItem).getText()));
            } else if (promptContentItem instanceof PromptContentItemBulletedText) {
                builder.addListItem(new android.hardware.biometrics.PromptContentItemBulletedText(((PromptContentItemBulletedText) promptContentItem).getText()));
            }
        }

        static PromptContentView createPromptContentViewWithMoreOptionsButton(PromptContentViewWithMoreOptionsButton promptContentViewWithMoreOptionsButton, Executor executor, DialogInterface.OnClickListener onClickListener) {
            android.hardware.biometrics.PromptContentViewWithMoreOptionsButton.Builder builder = new android.hardware.biometrics.PromptContentViewWithMoreOptionsButton.Builder();
            if (promptContentViewWithMoreOptionsButton.getDescription() != null) {
                builder.setDescription(promptContentViewWithMoreOptionsButton.getDescription());
            }
            builder.setMoreOptionsButtonListener(executor, onClickListener);
            return builder.build();
        }
    }
}
