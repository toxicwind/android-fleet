package androidx.biometric;

/* JADX INFO: loaded from: classes.dex */
public final class PromptContentItemBulletedText implements PromptContentItem {
    private final String mText;

    public PromptContentItemBulletedText(String str) {
        this.mText = str;
    }

    public String getText() {
        return this.mText;
    }
}
