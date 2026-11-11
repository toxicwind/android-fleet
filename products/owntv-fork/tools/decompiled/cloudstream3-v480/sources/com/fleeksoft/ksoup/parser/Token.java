package com.fleeksoft.ksoup.parser;

import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.Range;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Token.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b&\u0018\u0000 52\u00020\u0001:\u000b+,-./012345B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0000H\u0016J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\t\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0018J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u0018J\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\u0018J\u0006\u0010%\u001a\u00020\u0018J\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001e\u0010\t\u001a\u00020\n8\u0004@\u0004X\u0085\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\n8\u0004@\u0004X\u0085\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u00066"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token;", "", "type", "Lcom/fleeksoft/ksoup/parser/Token$TokenType;", "<init>", "(Lcom/fleeksoft/ksoup/parser/Token$TokenType;)V", "getType", "()Lcom/fleeksoft/ksoup/parser/Token$TokenType;", "setType", "startPos", "", "getStartPos", "()I", "setStartPos", "(I)V", "endPos", "getEndPos", "setEndPos", "tokenType", "", "reset", "", CloudStreamPackage.POSITION_EXTRA, "isDoctype", "", "asDoctype", "Lcom/fleeksoft/ksoup/parser/Token$Doctype;", "isStartTag", "asStartTag", "Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "isEndTag", "asEndTag", "Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "isComment", "asComment", "Lcom/fleeksoft/ksoup/parser/Token$Comment;", "isCharacter", "isCData", "asCharacter", "Lcom/fleeksoft/ksoup/parser/Token$Character;", "asXmlDecl", "Lcom/fleeksoft/ksoup/parser/Token$XmlDecl;", "isEOF", "Doctype", "Tag", "StartTag", "EndTag", "Comment", "Character", "CData", "XmlDecl", "EOF", "TokenType", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Token {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int UnsetPos = -1;
    private int endPos;
    private int startPos;
    private TokenType type;

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$TokenType;", "", "<init>", "(Ljava/lang/String;I)V", "Doctype", "StartTag", "EndTag", "Comment", "Character", "XmlDecl", "EOF", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        XmlDecl,
        EOF;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<TokenType> getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ Token(TokenType tokenType, DefaultConstructorMarker defaultConstructorMarker) {
        this(tokenType);
    }

    private Token(TokenType tokenType) {
        this.type = tokenType;
        this.endPos = -1;
    }

    public final TokenType getType() {
        return this.type;
    }

    public final void setType(TokenType tokenType) {
        Intrinsics.checkNotNullParameter(tokenType, "<set-?>");
        this.type = tokenType;
    }

    protected final int getStartPos() {
        return this.startPos;
    }

    protected final void setStartPos(int i) {
        this.startPos = i;
    }

    protected final int getEndPos() {
        return this.endPos;
    }

    protected final void setEndPos(int i) {
        this.endPos = i;
    }

    public final String tokenType() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? "Token" : simpleName;
    }

    public Token reset() {
        this.startPos = -1;
        this.endPos = -1;
        return this;
    }

    public final int startPos() {
        return this.startPos;
    }

    public final void startPos(int pos) {
        this.startPos = pos;
    }

    /* JADX INFO: renamed from: endPos, reason: from getter */
    public final int getEndPos() {
        return this.endPos;
    }

    public final void endPos(int pos) {
        this.endPos = pos;
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0001H\u0016J\u0006\u0010\u0006\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\tJ\n\u0010\u0019\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$Doctype;", "Lcom/fleeksoft/ksoup/parser/Token;", "<init>", "()V", "name", "Lcom/fleeksoft/ksoup/parser/TokenData;", "getName", "()Lcom/fleeksoft/ksoup/parser/TokenData;", "pubSysKey", "", "getPubSysKey", "()Ljava/lang/String;", "setPubSysKey", "(Ljava/lang/String;)V", "publicIdentifier", "getPublicIdentifier", "systemIdentifier", "getSystemIdentifier", "forceQuirks", "", "getForceQuirks", "()Z", "setForceQuirks", "(Z)V", "reset", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Doctype extends Token {
        private boolean forceQuirks;
        private final TokenData name;
        private String pubSysKey;
        private final TokenData publicIdentifier;
        private final TokenData systemIdentifier;

        public Doctype() {
            super(TokenType.Doctype, null);
            this.name = new TokenData();
            this.publicIdentifier = new TokenData();
            this.systemIdentifier = new TokenData();
        }

        public final TokenData getName() {
            return this.name;
        }

        public final String getPubSysKey() {
            return this.pubSysKey;
        }

        public final void setPubSysKey(String str) {
            this.pubSysKey = str;
        }

        public final TokenData getPublicIdentifier() {
            return this.publicIdentifier;
        }

        public final TokenData getSystemIdentifier() {
            return this.systemIdentifier;
        }

        public final boolean getForceQuirks() {
            return this.forceQuirks;
        }

        public final void setForceQuirks(boolean z) {
            this.forceQuirks = z;
        }

        @Override // com.fleeksoft.ksoup.parser.Token
        public Token reset() {
            super.reset();
            this.name.reset();
            this.pubSysKey = null;
            this.publicIdentifier.reset();
            this.systemIdentifier.reset();
            this.forceQuirks = false;
            return this;
        }

        /* JADX INFO: renamed from: getName, reason: collision with other method in class */
        public final String m8178getName() {
            return this.name.value();
        }

        /* JADX INFO: renamed from: getPublicIdentifier, reason: collision with other method in class */
        public final String m8179getPublicIdentifier() {
            return this.publicIdentifier.value();
        }

        /* JADX INFO: renamed from: getSystemIdentifier, reason: collision with other method in class */
        public final String m8180getSystemIdentifier() {
            return this.systemIdentifier.value();
        }

        public String toString() {
            return "<!doctype " + m8178getName() + ">";
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0006\b&\u0018\u0000 E2\u00020\u0001:\u0001EB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010,\u001a\u00020\u0000H\u0016J\b\u0010-\u001a\u00020.H\u0002J\u0006\u0010/\u001a\u00020.J\u0010\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020\u0011H\u0002J\u0006\u00102\u001a\u00020\u0017J\u0010\u00103\u001a\u00020\u00172\b\u00104\u001a\u0004\u0018\u00010\u0011J\u0006\u00105\u001a\u00020.J\u0006\u00101\u001a\u00020\u0011J\u0006\u00106\u001a\u00020\u0011J\u0006\u00107\u001a\u00020\u0011J\u000e\u00101\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u0011J\u000e\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u0011J\u000e\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020:J\u001e\u0010;\u001a\u00020.2\u0006\u00109\u001a\u00020\u00112\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(J\u001e\u0010;\u001a\u00020.2\u0006\u00109\u001a\u00020:2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(J\u001e\u0010>\u001a\u00020.2\u0006\u00109\u001a\u00020\u00112\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(J\u001e\u0010>\u001a\u00020.2\u0006\u00109\u001a\u00020:2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(J\u001e\u0010>\u001a\u00020.2\u0006\u0010?\u001a\u00020@2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(J\u0006\u0010A\u001a\u00020.J\u0018\u0010B\u001a\u00020.2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(H\u0002J\u0018\u0010C\u001a\u00020.2\u0006\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020(H\u0002J\n\u0010D\u001a\u00020\u0011H¦\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$Tag;", "Lcom/fleeksoft/ksoup/parser/Token;", "type", "Lcom/fleeksoft/ksoup/parser/Token$TokenType;", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/Token$TokenType;Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "getTreeBuilder", "()Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "tagName", "Lcom/fleeksoft/ksoup/parser/TokenData;", "getTagName$ksoup_release", "()Lcom/fleeksoft/ksoup/parser/TokenData;", "setTagName$ksoup_release", "(Lcom/fleeksoft/ksoup/parser/TokenData;)V", "normalName", "", "getNormalName$ksoup_release", "()Ljava/lang/String;", "setNormalName$ksoup_release", "(Ljava/lang/String;)V", "selfClosing", "", "getSelfClosing", "()Z", "setSelfClosing", "(Z)V", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "getAttributes", "()Lcom/fleeksoft/ksoup/nodes/Attributes;", "setAttributes", "(Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "attrName", "attrValue", "hasEmptyAttrValue", "trackSource", "getTrackSource$ksoup_release", "attrNameStart", "", "attrNameEnd", "attrValStart", "attrValEnd", "reset", "resetPendingAttr", "", "newAttribute", "trackAttributeRange", "name", "hasAttributes", "hasAttributeIgnoreCase", "key", "finaliseTag", "retrieveNormalName", "toStringName", "appendTagName", "append", "", "appendAttributeName", "startPos", "endPos", "appendAttributeValue", "appendCodepoints", "", "setEmptyAttributeValue", "attrNamePos", "attrValPos", "toString", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Tag extends Token {
        private static final int MaxAttributes = 512;
        private final TokenData attrName;
        private int attrNameEnd;
        private int attrNameStart;
        private int attrValEnd;
        private int attrValStart;
        private final TokenData attrValue;
        private Attributes attributes;
        private boolean hasEmptyAttrValue;
        private String normalName;
        private boolean selfClosing;
        private TokenData tagName;
        private final boolean trackSource;
        private final TreeBuilder treeBuilder;

        public abstract String toString();

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Tag(TokenType type, TreeBuilder treeBuilder) {
            super(type, null);
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
            this.treeBuilder = treeBuilder;
            this.tagName = new TokenData();
            this.attrName = new TokenData();
            this.attrValue = new TokenData();
            this.trackSource = treeBuilder.getTrackSourceRange();
        }

        public final TreeBuilder getTreeBuilder() {
            return this.treeBuilder;
        }

        /* JADX INFO: renamed from: getTagName$ksoup_release, reason: from getter */
        public final TokenData getTagName() {
            return this.tagName;
        }

        public final void setTagName$ksoup_release(TokenData tokenData) {
            Intrinsics.checkNotNullParameter(tokenData, "<set-?>");
            this.tagName = tokenData;
        }

        /* JADX INFO: renamed from: getNormalName$ksoup_release, reason: from getter */
        public final String getNormalName() {
            return this.normalName;
        }

        public final void setNormalName$ksoup_release(String str) {
            this.normalName = str;
        }

        public final boolean getSelfClosing() {
            return this.selfClosing;
        }

        public final void setSelfClosing(boolean z) {
            this.selfClosing = z;
        }

        public final Attributes getAttributes() {
            return this.attributes;
        }

        public final void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        /* JADX INFO: renamed from: getTrackSource$ksoup_release, reason: from getter */
        public final boolean getTrackSource() {
            return this.trackSource;
        }

        @Override // com.fleeksoft.ksoup.parser.Token
        public Tag reset() {
            super.reset();
            this.tagName.reset();
            this.normalName = null;
            this.selfClosing = false;
            this.attributes = null;
            resetPendingAttr();
            return this;
        }

        private final void resetPendingAttr() {
            this.attrName.reset();
            this.attrValue.reset();
            this.hasEmptyAttrValue = false;
            if (this.trackSource) {
                this.attrValEnd = -1;
                this.attrValStart = -1;
                this.attrNameEnd = -1;
                this.attrNameStart = -1;
            }
        }

        public final void newAttribute() {
            String strValue;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.attrName.hasData()) {
                Attributes attributes = this.attributes;
                Intrinsics.checkNotNull(attributes);
                if (attributes.size() < 512) {
                    String strValue2 = this.attrName.value();
                    int length = strValue2.length() - 1;
                    int i = 0;
                    boolean z = false;
                    while (i <= length) {
                        boolean z2 = Intrinsics.compare((int) strValue2.charAt(!z ? i : length), 32) <= 0;
                        if (z) {
                            if (!z2) {
                                break;
                            } else {
                                length--;
                            }
                        } else if (z2) {
                            i++;
                        } else {
                            z = true;
                        }
                    }
                    String string = strValue2.subSequence(i, length + 1).toString();
                    if (string.length() != 0) {
                        if (this.attrValue.hasData()) {
                            strValue = this.attrValue.value();
                        } else {
                            strValue = this.hasEmptyAttrValue ? "" : null;
                        }
                        Attributes attributes2 = this.attributes;
                        Intrinsics.checkNotNull(attributes2);
                        attributes2.add(string, strValue);
                        trackAttributeRange(string);
                    }
                }
            }
            resetPendingAttr();
        }

        private final void trackAttributeRange(String name) {
            if (this.trackSource && isStartTag()) {
                StartTag startTagAsStartTag = asStartTag();
                CharacterReader reader = startTagAsStartTag.getTreeBuilder().getReader();
                if (!startTagAsStartTag.getTreeBuilder().getSettings().getPreserveAttributeCase()) {
                    name = Normalizer.INSTANCE.lowerCase(name);
                }
                Attributes attributes = this.attributes;
                Intrinsics.checkNotNull(attributes);
                if (attributes.sourceRange(name).nameRange().isTracked()) {
                    return;
                }
                if (!this.attrValue.hasData()) {
                    int i = this.attrNameEnd;
                    this.attrValEnd = i;
                    this.attrValStart = i;
                }
                int i2 = this.attrNameStart;
                Range.Position position = new Range.Position(i2, reader.lineNumber(i2), reader.columnNumber(this.attrNameStart));
                int i3 = this.attrNameEnd;
                Range range = new Range(position, new Range.Position(i3, reader.lineNumber(i3), reader.columnNumber(this.attrNameEnd)));
                int i4 = this.attrValStart;
                Range.Position position2 = new Range.Position(i4, reader.lineNumber(i4), reader.columnNumber(this.attrValStart));
                int i5 = this.attrValEnd;
                Range.AttributeRange attributeRange = new Range.AttributeRange(range, new Range(position2, new Range.Position(i5, reader.lineNumber(i5), reader.columnNumber(this.attrValEnd))));
                Attributes attributes2 = this.attributes;
                Intrinsics.checkNotNull(attributes2);
                attributes2.sourceRange(name, attributeRange);
            }
        }

        public final boolean hasAttributes() {
            return this.attributes != null;
        }

        public final boolean hasAttributeIgnoreCase(String key) {
            Attributes attributes = this.attributes;
            if (attributes == null) {
                return false;
            }
            Intrinsics.checkNotNull(attributes);
            Intrinsics.checkNotNull(key);
            return attributes.hasKeyIgnoreCase(key);
        }

        public final void finaliseTag() {
            if (this.attrName.hasData()) {
                newAttribute();
            }
        }

        public final String name() {
            return this.tagName.value();
        }

        /* JADX WARN: Code duplicated, block: B:8:0x0014  */
        public final String retrieveNormalName() {
            boolean z;
            Validate validate = Validate.INSTANCE;
            String str = this.normalName;
            if (str != null) {
                Intrinsics.checkNotNull(str);
                if (str.length() == 0) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = true;
            }
            validate.isFalse(z);
            String str2 = this.normalName;
            return str2 == null ? "" : str2;
        }

        public final String toStringName() {
            String strValue = this.tagName.value();
            if (strValue.length() == 0) {
                strValue = "[unset]";
            }
            return strValue;
        }

        public final Tag name(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.tagName.set(name);
            this.normalName = ParseSettings.INSTANCE.normalName(this.tagName.value());
            return this;
        }

        public final void appendTagName(String append) {
            Intrinsics.checkNotNullParameter(append, "append");
            this.tagName.append(StringsKt.replace$default(append, (char) 0, (char) 65533, false, 4, (Object) null));
            this.normalName = ParseSettings.INSTANCE.normalName(this.tagName.value());
        }

        public final void appendTagName(char append) {
            appendTagName(String.valueOf(append));
        }

        public final void appendAttributeName(String append, int startPos, int endPos) {
            Intrinsics.checkNotNullParameter(append, "append");
            this.attrName.append(StringsKt.replace$default(append, (char) 0, (char) 65533, false, 4, (Object) null));
            attrNamePos(startPos, endPos);
        }

        public final void appendAttributeName(char append, int startPos, int endPos) {
            this.attrName.append(append);
            attrNamePos(startPos, endPos);
        }

        public final void appendAttributeValue(String append, int startPos, int endPos) {
            Intrinsics.checkNotNullParameter(append, "append");
            this.attrValue.append(append);
            attrValPos(startPos, endPos);
        }

        public final void appendAttributeValue(char append, int startPos, int endPos) {
            this.attrValue.append(append);
            attrValPos(startPos, endPos);
        }

        public final void appendAttributeValue(int[] appendCodepoints, int startPos, int endPos) {
            Intrinsics.checkNotNullParameter(appendCodepoints, "appendCodepoints");
            for (int i : appendCodepoints) {
                this.attrValue.appendCodePoint(i);
            }
            attrValPos(startPos, endPos);
        }

        public final void setEmptyAttributeValue() {
            this.hasEmptyAttrValue = true;
        }

        private final void attrNamePos(int startPos, int endPos) {
            if (this.trackSource) {
                int i = this.attrNameStart;
                if (i > -1) {
                    startPos = i;
                }
                this.attrNameStart = startPos;
                this.attrNameEnd = endPos;
            }
        }

        private final void attrValPos(int startPos, int endPos) {
            if (this.trackSource) {
                int i = this.attrValStart;
                if (i > -1) {
                    startPos = i;
                }
                this.attrValStart = startPos;
                this.attrValEnd = endPos;
            }
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0001H\u0016J\u0018\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\n\u0010\f\u001a\u00020\tH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$StartTag;", "Lcom/fleeksoft/ksoup/parser/Token$Tag;", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "reset", "nameAttr", "name", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class StartTag extends Tag {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StartTag(TreeBuilder treeBuilder) {
            super(TokenType.StartTag, treeBuilder);
            Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Tag, com.fleeksoft.ksoup.parser.Token
        public Tag reset() {
            super.reset();
            setAttributes(null);
            return this;
        }

        public final StartTag nameAttr(String name, Attributes attributes) {
            Intrinsics.checkNotNullParameter(name, "name");
            getTagName().set(name);
            setAttributes(attributes);
            setNormalName$ksoup_release(ParseSettings.INSTANCE.normalName(name));
            return this;
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Tag
        public String toString() {
            String str = getSelfClosing() ? "/>" : ">";
            if (hasAttributes()) {
                Attributes attributes = getAttributes();
                Intrinsics.checkNotNull(attributes);
                if (attributes.size() > 0) {
                    return "<" + toStringName() + Stream.ID_UNKNOWN + getAttributes() + str;
                }
            }
            return "<" + toStringName() + str;
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$EndTag;", "Lcom/fleeksoft/ksoup/parser/Token$Tag;", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EndTag extends Tag {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EndTag(TreeBuilder treeBuilder) {
            super(TokenType.EndTag, treeBuilder);
            Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Tag
        public String toString() {
            return "</" + toStringName() + ">";
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u0001H\u0016J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\n\u0010\u0011\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$Comment;", "Lcom/fleeksoft/ksoup/parser/Token;", "<init>", "()V", "data", "Lcom/fleeksoft/ksoup/parser/TokenData;", "bogus", "", "getBogus", "()Z", "setBogus", "(Z)V", "reset", "getData", "", "append", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Comment extends Token {
        private boolean bogus;
        private final TokenData data;

        public Comment() {
            super(TokenType.Comment, null);
            this.data = new TokenData();
        }

        public final boolean getBogus() {
            return this.bogus;
        }

        public final void setBogus(boolean z) {
            this.bogus = z;
        }

        @Override // com.fleeksoft.ksoup.parser.Token
        public Token reset() {
            super.reset();
            this.data.reset();
            this.bogus = false;
            return this;
        }

        public final String getData() {
            return this.data.value();
        }

        public final Comment append(String append) {
            Intrinsics.checkNotNullParameter(append, "append");
            this.data.append(append);
            return this;
        }

        public final Comment append(char append) {
            this.data.append(append);
            return this;
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u0001H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\b\u001a\u00020\fJ\n\u0010\u000e\u001a\u00020\fH\u0096\u0080\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$Character;", "Lcom/fleeksoft/ksoup/parser/Token;", "<init>", "()V", "source", "(Lcom/fleeksoft/ksoup/parser/Token$Character;)V", "data", "Lcom/fleeksoft/ksoup/parser/TokenData;", "getData", "()Lcom/fleeksoft/ksoup/parser/TokenData;", "reset", "str", "", "append", "toString", "normalizeNulls", "", "replace", "", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class Character extends Token {
        private static final String nullString = "\u0000";
        private final TokenData data;

        public Character() {
            super(TokenType.Character, null);
            this.data = new TokenData();
        }

        public final TokenData getData() {
            return this.data;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Character(Character source) {
            this();
            Intrinsics.checkNotNullParameter(source, "source");
            setStartPos(source.getStartPos());
            setEndPos(source.getEndPos());
            this.data.set(source.data.value());
        }

        @Override // com.fleeksoft.ksoup.parser.Token
        public Token reset() {
            super.reset();
            this.data.reset();
            return this;
        }

        public final Character data(String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            this.data.set(str);
            return this;
        }

        public final Character append(String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            this.data.append(str);
            return this;
        }

        /* JADX INFO: renamed from: getData, reason: collision with other method in class */
        public final String m8177getData() {
            return this.data.value();
        }

        public String toString() {
            return m8177getData();
        }

        public final void normalizeNulls(boolean replace) {
            String strValue = this.data.value();
            if (StringsKt.indexOf$default((CharSequence) strValue, (char) 0, 0, false, 6, (Object) null) == -1) {
                return;
            }
            this.data.set(replace ? StringsKt.replace$default(strValue, (char) 0, (char) 65533, false, 4, (Object) null) : StringsKt.replace$default(strValue, nullString, "", false, 4, (Object) null));
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$CData;", "Lcom/fleeksoft/ksoup/parser/Token$Character;", "data", "", "<init>", "(Ljava/lang/String;)V", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class CData extends Character {
        public CData(String data) {
            Intrinsics.checkNotNullParameter(data, "data");
            data(data);
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Character
        public String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0000H\u0016J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$XmlDecl;", "Lcom/fleeksoft/ksoup/parser/Token$Tag;", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "<init>", "(Lcom/fleeksoft/ksoup/parser/TreeBuilder;)V", "isDeclaration", "", "()Z", "setDeclaration", "(Z)V", "reset", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class XmlDecl extends Tag {
        private boolean isDeclaration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public XmlDecl(TreeBuilder treeBuilder) {
            super(TokenType.XmlDecl, treeBuilder);
            Intrinsics.checkNotNullParameter(treeBuilder, "treeBuilder");
            this.isDeclaration = true;
        }

        /* JADX INFO: renamed from: isDeclaration, reason: from getter */
        public final boolean getIsDeclaration() {
            return this.isDeclaration;
        }

        public final void setDeclaration(boolean z) {
            this.isDeclaration = z;
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Tag, com.fleeksoft.ksoup.parser.Token
        public XmlDecl reset() {
            super.reset();
            this.isDeclaration = true;
            return this;
        }

        @Override // com.fleeksoft.ksoup.parser.Token.Tag
        public String toString() {
            boolean z = this.isDeclaration;
            String str = z ? "<!" : "<?";
            String str2 = z ? ">" : "?>";
            if (hasAttributes()) {
                Attributes attributes = getAttributes();
                Intrinsics.checkNotNull(attributes);
                if (attributes.getSize() > 0) {
                    return str + toStringName() + Stream.ID_UNKNOWN + getAttributes() + str2;
                }
            }
            return str + toStringName() + str2;
        }
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016J\n\u0010\u0005\u001a\u00020\u0006H\u0096\u0080\u0004¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$EOF;", "Lcom/fleeksoft/ksoup/parser/Token;", "<init>", "()V", "reset", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EOF extends Token {
        public EOF() {
            super(TokenType.EOF, null);
        }

        @Override // com.fleeksoft.ksoup.parser.Token
        public Token reset() {
            super.reset();
            return this;
        }

        public String toString() {
            return "";
        }
    }

    public final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    public final Doctype asDoctype() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.Doctype");
        return (Doctype) this;
    }

    public final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    public final StartTag asStartTag() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.StartTag");
        return (StartTag) this;
    }

    public final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    public final EndTag asEndTag() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.EndTag");
        return (EndTag) this;
    }

    public final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    public final Comment asComment() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.Comment");
        return (Comment) this;
    }

    public final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    public final boolean isCData() {
        return this instanceof CData;
    }

    public final Character asCharacter() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.Character");
        return (Character) this;
    }

    public final XmlDecl asXmlDecl() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.parser.Token.XmlDecl");
        return (XmlDecl) this;
    }

    public final boolean isEOF() {
        return this.type == TokenType.EOF;
    }

    /* JADX INFO: compiled from: Token.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/parser/Token$Companion;", "", "<init>", "()V", "UnsetPos", "", "reset", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void reset(StringBuilder sb) {
            if (sb != null) {
                StringsKt.clear(sb);
            }
        }
    }
}
