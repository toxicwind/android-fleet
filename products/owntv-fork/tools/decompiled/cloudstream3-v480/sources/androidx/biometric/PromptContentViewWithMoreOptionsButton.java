package androidx.biometric;

/* JADX INFO: loaded from: classes.dex */
public final class PromptContentViewWithMoreOptionsButton implements PromptContentView {
    private final String mDescription;

    private PromptContentViewWithMoreOptionsButton(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public static final class Builder {
        private String mDescription;

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public PromptContentViewWithMoreOptionsButton build() {
            return new PromptContentViewWithMoreOptionsButton(this.mDescription);
        }
    }
}
