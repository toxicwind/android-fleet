package com.fleeksoft.ksoup.parser;

import com.fleeksoft.ksoup.nodes.TagSet;
import com.fleeksoft.ksoup.ported.KCloneable;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Tag.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0087\b\u0018\u0000 52\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00015B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\bB\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\tJ\u0006\u0010\u0017\u001a\u00020\u0003J\n\u0010\u0018\u001a\u00020\u0012H\u0096\u0080\u0004J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\u0007J\u0006\u0010\u0019\u001a\u00020\u0003J\u0006\u0010\u001a\u001a\u00020\u0003J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0006\u0010\u001e\u001a\u00020\u001cJ\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u0004\u0018\u00010\"J\b\u0010\u0004\u001a\u00020\u0003H\u0007J\b\u0010\u0005\u001a\u00020\u0003H\u0007J\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0003H\u0007J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010%\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0012J\b\u0010'\u001a\u00020\u001cH\u0007J\u0006\u0010(\u001a\u00020\u001cJ\u0006\u0010)\u001a\u00020\u001cJ\u0006\u0010*\u001a\u00020\u001cJ\u0006\u0010+\u001a\u00020\u001cJ\n\u0010,\u001a\u00020\u0003H\u0096\u0080\u0004J\b\u0010-\u001a\u00020\u0000H\u0016J\u0014\u0010.\u001a\u00020\u001c2\b\u0010/\u001a\u0004\u0018\u000100H\u0096\u0082\u0004J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J'\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\bR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u00066"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Tag;", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "tagName", "", "normalName", "namespace", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getTagName", "()Ljava/lang/String;", "setTagName", "getNormalName", "setNormalName", "getNamespace", "setNamespace", "options", "", "getOptions", "()I", "setOptions", "(I)V", "name", "hashCode", "prefix", "localName", "isBlock", "", "isEmpty", "isFormSubmittable", "setSeenSelfClose", "", "textState", "Lcom/fleeksoft/ksoup/parser/TokeniserState;", "set", "option", "is", "clear", "formatAsBlock", "isInline", "isSelfClosing", "isKnownTag", "preserveWhitespace", "toString", "clone", "equals", "other", "", "component1", "component2", "component3", "copy", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Tag implements KCloneable<Tag> {
    public static final int Block = 4;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int Data = 256;
    public static final int FormSubmittable = 512;
    public static final int InlineContainer = 8;
    public static final int Known = 1;
    public static final int PreserveWhitespace = 64;
    public static final int RcData = 128;
    public static final int SeenSelfClose = 32;
    public static final int SelfClose = 16;
    public static final int Void = 2;
    private String namespace;
    private String normalName;
    private int options;
    private String tagName;

    public static /* synthetic */ Tag copy$default(Tag tag, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tag.tagName;
        }
        if ((i & 2) != 0) {
            str2 = tag.normalName;
        }
        if ((i & 4) != 0) {
            str3 = tag.namespace;
        }
        return tag.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTagName() {
        return this.tagName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNormalName() {
        return this.normalName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getNamespace() {
        return this.namespace;
    }

    public final Tag copy(String tagName, String normalName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(normalName, "normalName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        return new Tag(tagName, normalName, namespace);
    }

    public Tag(String tagName, String normalName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(normalName, "normalName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.tagName = tagName;
        this.normalName = normalName;
        this.namespace = namespace;
    }

    public final String getNamespace() {
        return this.namespace;
    }

    public final String getNormalName() {
        return this.normalName;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public final void setNamespace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.namespace = str;
    }

    public final void setNormalName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.normalName = str;
    }

    public final void setTagName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tagName = str;
    }

    public final int getOptions() {
        return this.options;
    }

    public final void setOptions(int i) {
        this.options = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Tag(String tagName) {
        this(tagName, ParseSettings.INSTANCE.normalName(tagName), "http://www.w3.org/1999/xhtml");
        Intrinsics.checkNotNullParameter(tagName, "tagName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Tag(String tagName, String namespace) {
        this(tagName, ParseSettings.INSTANCE.normalName(tagName), namespace);
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
    }

    public final String name() {
        return this.tagName;
    }

    public int hashCode() {
        return Arrays.hashCode(new String[]{this.tagName, this.namespace});
    }

    public final Tag name(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        this.tagName = tagName;
        this.normalName = ParseSettings.INSTANCE.normalName(tagName);
        return this;
    }

    public final String prefix() {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.tagName, AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return "";
        }
        String strSubstring = this.tagName.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String localName() {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.tagName, AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        String str = this.tagName;
        if (iIndexOf$default == -1) {
            return str;
        }
        String strSubstring = str.substring(iIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final boolean isBlock() {
        return (this.options & 4) != 0;
    }

    public final boolean isEmpty() {
        return (this.options & 2) != 0;
    }

    public final boolean isFormSubmittable() {
        return (this.options & 512) != 0;
    }

    public final void setSeenSelfClose() {
        this.options |= 32;
    }

    public final TokeniserState textState() {
        if (is(128)) {
            return TokeniserState.Rcdata;
        }
        if (is(256)) {
            return TokeniserState.Rawtext;
        }
        return null;
    }

    public final String normalName() {
        return this.normalName;
    }

    public final String namespace() {
        return this.namespace;
    }

    public final Tag namespace(String namespace) {
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.namespace = namespace;
        return this;
    }

    public final Tag set(int option) {
        this.options = option | this.options | 1;
        return this;
    }

    public final boolean is(int option) {
        return (this.options & option) != 0;
    }

    public final Tag clear(int option) {
        int i = this.options & (~option);
        this.options = i;
        if (option != 1) {
            this.options = i | 1;
        }
        return this;
    }

    @Deprecated(message = "setting is only used within the Printer. Will be removed in a future release.")
    public final boolean formatAsBlock() {
        return (this.options & 8) != 0;
    }

    public final boolean isInline() {
        return (this.options & 4) == 0;
    }

    public final boolean isSelfClosing() {
        int i = this.options;
        return ((i & 16) == 0 && (i & 2) == 0) ? false : true;
    }

    public final boolean isKnownTag() {
        return (this.options & 1) != 0;
    }

    public final boolean preserveWhitespace() {
        return (this.options & 64) != 0;
    }

    public String toString() {
        return this.tagName;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Tag clone2() {
        Tag tagCopy$default = copy$default(this, null, null, null, 7, null);
        tagCopy$default.options = this.options;
        return tagCopy$default;
    }

    /* JADX INFO: compiled from: Tag.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Tag$Companion;", "", "<init>", "()V", "Known", "", "Void", "Block", "InlineContainer", "SelfClose", "SeenSelfClose", "PreserveWhitespace", "RcData", "Data", "FormSubmittable", "valueOf", "Lcom/fleeksoft/ksoup/parser/Tag;", "tagName", "", "namespace", "settings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "isKnownTag", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Tag valueOf(String tagName) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            return valueOf$default(this, tagName, null, null, 6, null);
        }

        public final Tag valueOf(String tagName, String namespace) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(namespace, "namespace");
            return valueOf$default(this, tagName, namespace, null, 4, null);
        }

        private Companion() {
        }

        public static /* synthetic */ Tag valueOf$default(Companion companion, String str, String str2, ParseSettings parseSettings, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "http://www.w3.org/1999/xhtml";
            }
            if ((i & 4) != 0) {
                parseSettings = ParseSettings.INSTANCE.getPreserveCase();
            }
            return companion.valueOf(str, str2, parseSettings);
        }

        public final Tag valueOf(String tagName, String namespace, ParseSettings settings) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(namespace, "namespace");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return TagSet.INSTANCE.Html().valueOf(tagName, null, namespace, settings.getPreserveTagCase());
        }

        public final Tag valueOf(String tagName, ParseSettings settings) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return valueOf(tagName, "http://www.w3.org/1999/xhtml", settings);
        }

        public final boolean isKnownTag(String tagName) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            return TagSet.INSTANCE.getHtmlTagSet().get(tagName, "http://www.w3.org/1999/xhtml") != null;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Tag tag = (Tag) other;
        return this.options == tag.options && Intrinsics.areEqual(this.tagName, tag.tagName) && Intrinsics.areEqual(this.normalName, tag.normalName) && Intrinsics.areEqual(this.namespace, tag.namespace);
    }
}
