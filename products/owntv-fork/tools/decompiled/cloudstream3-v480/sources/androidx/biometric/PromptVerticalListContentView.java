package androidx.biometric;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PromptVerticalListContentView implements PromptContentView {
    private final List<PromptContentItem> mContentList;
    private final String mDescription;

    private PromptVerticalListContentView(List<PromptContentItem> list, String str) {
        this.mContentList = list;
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public List<PromptContentItem> getListItems() {
        return new ArrayList(this.mContentList);
    }

    public static final class Builder {
        private final List<PromptContentItem> mContentList = new ArrayList();
        private String mDescription;

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder addListItem(PromptContentItem promptContentItem) {
            this.mContentList.add(promptContentItem);
            return this;
        }

        public Builder addListItem(PromptContentItem promptContentItem, int i) {
            this.mContentList.add(i, promptContentItem);
            return this;
        }

        public PromptVerticalListContentView build() {
            return new PromptVerticalListContentView(this.mContentList, this.mDescription);
        }
    }
}
