package com.fleeksoft.ksoup.parser;

import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.nodes.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParseSettings.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0013\b\u0010\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0005\u0010\bJ\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/ksoup/parser/ParseSettings;", "", "preserveTagCase", "", "preserveAttributeCase", "<init>", "(ZZ)V", "copy", "(Lcom/fleeksoft/ksoup/parser/ParseSettings;)V", "normalizeTag", "", "name", "normalizeAttribute", "normalizeAttributes", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ParseSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ParseSettings htmlDefault = new ParseSettings(false, false);
    private static final ParseSettings preserveCase = new ParseSettings(true, true);
    private final boolean preserveAttributeCase;
    private final boolean preserveTagCase;

    public ParseSettings(boolean z, boolean z2) {
        this.preserveTagCase = z;
        this.preserveAttributeCase = z2;
    }

    /* JADX INFO: renamed from: preserveTagCase, reason: from getter */
    public final boolean getPreserveTagCase() {
        return this.preserveTagCase;
    }

    /* JADX INFO: renamed from: preserveAttributeCase, reason: from getter */
    public final boolean getPreserveAttributeCase() {
        return this.preserveAttributeCase;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ParseSettings(ParseSettings parseSettings) {
        this(parseSettings.preserveTagCase, parseSettings.preserveAttributeCase);
        Intrinsics.checkNotNull(parseSettings);
    }

    public final void normalizeAttributes(Attributes attributes) {
        if (this.preserveAttributeCase || attributes == null) {
            return;
        }
        attributes.normalize();
    }

    /* JADX INFO: compiled from: ParseSettings.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/parser/ParseSettings$Companion;", "", "<init>", "()V", "htmlDefault", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "getHtmlDefault", "()Lcom/fleeksoft/ksoup/parser/ParseSettings;", "preserveCase", "getPreserveCase", "normalName", "", "name", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ParseSettings getHtmlDefault() {
            return ParseSettings.htmlDefault;
        }

        public final ParseSettings getPreserveCase() {
            return ParseSettings.preserveCase;
        }

        public final String normalName(String name) {
            Normalizer normalizer = Normalizer.INSTANCE;
            Intrinsics.checkNotNull(name);
            String str = name;
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            return normalizer.normalize(str.subSequence(i, length + 1).toString());
        }
    }

    public final String normalizeTag(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i, length + 1).toString();
        return !this.preserveTagCase ? Normalizer.INSTANCE.lowerCase(string) : string;
    }

    public final String normalizeAttribute(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i, length + 1).toString();
        return !this.preserveAttributeCase ? Normalizer.INSTANCE.lowerCase(string) : string;
    }
}
