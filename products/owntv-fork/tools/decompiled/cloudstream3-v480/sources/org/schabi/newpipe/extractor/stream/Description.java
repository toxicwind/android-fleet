package org.schabi.newpipe.extractor.stream;

import j$.util.Objects;
import java.io.Serializable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public class Description implements Serializable {
    public static final Description EMPTY_DESCRIPTION = new Description("", 3);
    public static final int HTML = 1;
    public static final int MARKDOWN = 2;
    public static final int PLAIN_TEXT = 3;
    private final String content;
    private final int type;

    public Description(@Nullable String str, int i) {
        this.type = i;
        this.content = (String) Objects.requireNonNullElse(str, "");
    }

    public String getContent() {
        return this.content;
    }

    public int getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Description description = (Description) obj;
            if (this.type == description.type && Objects.equals(this.content, description.content)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.content, Integer.valueOf(this.type));
    }
}
