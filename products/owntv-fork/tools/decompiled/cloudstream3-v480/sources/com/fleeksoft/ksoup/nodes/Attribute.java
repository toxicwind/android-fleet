package com.fleeksoft.ksoup.nodes;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.ported.KCloneable;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Attribute.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0017\u0018\u0000 12\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0003:\u00011B#\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB\u001b\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u001c\u001a\u00020\u0002J\u0006\u0010\u001d\u001a\u00020\u0002J\u0006\u0010\u001e\u001a\u00020\u0002J\u0006\u0010\u001f\u001a\u00020\u0002J\u001c\u0010\u001f\u001a\u00020\u00162\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010#\u001a\u00020$H\u0005J,\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010#\u001a\u00020$H\u0005J\u0006\u0010%\u001a\u00020&J\u0018\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020'2\u0006\u0010#\u001a\u00020$H\u0005J\n\u0010(\u001a\u00020\u0002H\u0096\u0080\u0004J\u0014\u0010)\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+H\u0096\u0082\u0004J\n\u0010,\u001a\u00020-H\u0096\u0080\u0004J\b\u0010.\u001a\u00020\u0000H\u0016J\u0006\u0010/\u001a\u00020\u0019J\u0006\u00100\u001a\u00020\u0019R\u001e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0004\u001a\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0015\u0010\u0005\u001a\u00020\u00028VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r¨\u00062"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Attribute;", "", "", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "key", ES6Iterator.VALUE_PROPERTY, "parent", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "attributeKey", "getAttributeKey", "()Ljava/lang/String;", "attributeValue", "getAttributeValue", "getParent", "()Lcom/fleeksoft/ksoup/nodes/Attributes;", "setParent", "(Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "getKey", "setKey", "", "getValue", "hasDeclaredValue", "", "setValue", "newValue", "prefix", "localName", "namespace", "html", "accum", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "sourceRange", "Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange;", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "toString", "equals", "other", "", "hashCode", "", "clone", "isDataAttribute", "isInternal", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Attribute implements Map.Entry<String, String>, KCloneable<Attribute>, KMappedMarker {
    private String attributeKey;
    private String attributeValue;
    private Attributes parent;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private static final Regex xmlKeyReplace = new Regex("[^-a-zA-Z0-9_:.]+");
    private static final Regex htmlKeyReplace = new Regex("[\\x00-\\x1f\\x7f-\\x9f \"'/=]+");

    public Attribute(String key, String str, Attributes attributes) {
        Intrinsics.checkNotNullParameter(key, "key");
        String str2 = key;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
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
        String string = str2.subSequence(i, length + 1).toString();
        Validate.INSTANCE.notEmpty(string);
        this.attributeKey = string;
        this.attributeValue = str;
        this.parent = attributes;
    }

    public final String getAttributeKey() {
        return this.attributeKey;
    }

    public final String getAttributeValue() {
        return this.attributeValue;
    }

    public final Attributes getParent() {
        return this.parent;
    }

    public final void setParent(Attributes attributes) {
        this.parent = attributes;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Attribute(String key, String str) {
        this(key, str, null);
        Intrinsics.checkNotNullParameter(key, "key");
    }

    @Override // java.util.Map.Entry
    public final String getKey() {
        return this.attributeKey;
    }

    @Override // java.util.Map.Entry
    public String getValue() {
        return Attributes.INSTANCE.checkNotNull(this.attributeValue);
    }

    public final boolean hasDeclaredValue() {
        return this.attributeValue != null;
    }

    @Override // java.util.Map.Entry
    public final String setValue(String newValue) {
        String str = this.attributeValue;
        Attributes attributes = this.parent;
        if (attributes != null) {
            Intrinsics.checkNotNull(attributes);
            int iIndexOfKey = attributes.indexOfKey(this.attributeKey);
            if (iIndexOfKey != -1) {
                Attributes attributes2 = this.parent;
                Intrinsics.checkNotNull(attributes2);
                str = attributes2.get(this.attributeKey);
                Attributes attributes3 = this.parent;
                Intrinsics.checkNotNull(attributes3);
                attributes3.getVals()[iIndexOfKey] = newValue;
            }
        }
        this.attributeValue = newValue;
        return Attributes.INSTANCE.checkNotNull(str);
    }

    public final String prefix() {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) getKey(), AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return "";
        }
        String strSubstring = getKey().substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String localName() {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) getKey(), AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
        String key = getKey();
        if (iIndexOf$default == -1) {
            return key;
        }
        String strSubstring = key.substring(iIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String namespace() {
        Object objUserData;
        Attributes attributes = this.parent;
        if (attributes == null) {
            return "";
        }
        if (attributes != null) {
            objUserData = attributes.userData(SharedConstants.XmlnsAttr + prefix());
        } else {
            objUserData = null;
        }
        String str = objUserData instanceof String ? (String) objUserData : null;
        return str == null ? "" : str;
    }

    public final String html() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        html(QuietAppendable.INSTANCE.wrap(sbBorrowBuilder), new Document.OutputSettings(null, null, false, false, 0, 0, null, 127, null));
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    @Deprecated(message = "internal method and will be removed ")
    protected final void html(Appendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        html(getKey(), getValue(), accum, out);
    }

    @Deprecated(message = "internal method and will be removed ")
    protected final void html(String key, String value, Appendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        INSTANCE.html(key, value, QuietAppendable.INSTANCE.wrap(accum), out);
    }

    public final Range.AttributeRange sourceRange() {
        Attributes attributes = this.parent;
        if (attributes == null) {
            return Range.AttributeRange.INSTANCE.getUntrackedAttr();
        }
        Intrinsics.checkNotNull(attributes);
        return attributes.sourceRange(getKey());
    }

    protected final void html(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        INSTANCE.html(this.attributeKey, this.attributeValue, accum, out);
    }

    public String toString() {
        return html();
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && getClass() == other.getClass()) {
            Attribute attribute = (Attribute) other;
            if (Intrinsics.areEqual(this.attributeKey, attribute.attributeKey) && Intrinsics.areEqual(this.attributeKey, attribute.getKey()) && Intrinsics.areEqual(this.attributeValue, attribute.attributeValue)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return Arrays.hashCode(new String[]{this.attributeKey, this.attributeValue});
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Attribute clone2() {
        Attribute attribute = new Attribute(this.attributeKey, this.attributeValue);
        attribute.parent = this.parent;
        return attribute;
    }

    public final boolean isDataAttribute() {
        return INSTANCE.isDataAttribute(getKey());
    }

    public final boolean isInternal() {
        return Attributes.INSTANCE.isInternalKey(getKey());
    }

    /* JADX INFO: compiled from: Attribute.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0004J(\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0006H\u0002J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0006H\u0002J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0006H\u0004J\"\u0010\u001f\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0004J\u000e\u0010 \u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0006R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Attribute$Companion;", "", "<init>", "()V", "booleanAttributes", "", "", "[Ljava/lang/String;", "html", "", "key", ES6Iterator.VALUE_PROPERTY, "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "htmlNoValidate", "xmlKeyReplace", "Lkotlin/text/Regex;", "htmlKeyReplace", "getValidKey", "syntax", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "isValidXmlKey", "", "isValidHtmlKey", "createFromEncoded", "Lcom/fleeksoft/ksoup/nodes/Attribute;", "unencodedKey", "encodedValue", "isDataAttribute", "shouldCollapseAttribute", "isBooleanAttribute", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: Attribute.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Document.OutputSettings.Syntax.values().length];
                try {
                    iArr[Document.OutputSettings.Syntax.xml.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Document.OutputSettings.Syntax.html.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final void html(String key, String value, QuietAppendable accum, Document.OutputSettings out) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(accum, "accum");
            Intrinsics.checkNotNullParameter(out, "out");
            String validKey = getValidKey(key, out.syntax());
            if (validKey == null) {
                return;
            }
            htmlNoValidate(validKey, value, accum, out);
        }

        public final void htmlNoValidate(String key, String value, QuietAppendable accum, Document.OutputSettings out) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(accum, "accum");
            Intrinsics.checkNotNullParameter(out, "out");
            accum.append(key);
            if (shouldCollapseAttribute(key, value, out)) {
                return;
            }
            accum.append("=\"");
            Entities.INSTANCE.escape(accum, Attributes.INSTANCE.checkNotNull(value), out, 2);
            accum.append('\"');
        }

        public final String getValidKey(String key, Document.OutputSettings.Syntax syntax) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(syntax, "syntax");
            int i = WhenMappings.$EnumSwitchMapping$0[syntax.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!isValidHtmlKey(key)) {
                    String strReplace = Attribute.htmlKeyReplace.replace(key, "_");
                    if (isValidHtmlKey(strReplace)) {
                        return strReplace;
                    }
                    return null;
                }
            } else if (!isValidXmlKey(key)) {
                String strReplace2 = Attribute.xmlKeyReplace.replace(key, "_");
                if (isValidXmlKey(strReplace2)) {
                    return strReplace2;
                }
                return null;
            }
            return key;
        }

        private final boolean isValidXmlKey(String key) {
            int length = key.length();
            if (length == 0) {
                return false;
            }
            char cCharAt = key.charAt(0);
            if (('a' > cCharAt || cCharAt >= '{') && !(('A' <= cCharAt && cCharAt < '[') || cCharAt == '_' || cCharAt == ':')) {
                return false;
            }
            for (int i = 1; i < length; i++) {
                char cCharAt2 = key.charAt(i);
                if (('a' > cCharAt2 || cCharAt2 >= '{') && (('A' > cCharAt2 || cCharAt2 >= '[') && !(('0' <= cCharAt2 && cCharAt2 < ':') || cCharAt2 == '-' || cCharAt2 == '_' || cCharAt2 == ':' || cCharAt2 == '.'))) {
                    return false;
                }
            }
            return true;
        }

        private final boolean isValidHtmlKey(String key) {
            int length = key.length();
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                char cCharAt = key.charAt(i);
                if (cCharAt <= 31 || ((127 <= cCharAt && cCharAt < 160) || cCharAt == ' ' || cCharAt == '\"' || cCharAt == '\'' || cCharAt == '/' || cCharAt == '=')) {
                    return false;
                }
            }
            return true;
        }

        public final Attribute createFromEncoded(String unencodedKey, String encodedValue) {
            Intrinsics.checkNotNullParameter(unencodedKey, "unencodedKey");
            Intrinsics.checkNotNullParameter(encodedValue, "encodedValue");
            return new Attribute(unencodedKey, Entities.INSTANCE.unescape(encodedValue, true), null);
        }

        protected final boolean isDataAttribute(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return StringsKt.startsWith$default(key, Attributes.dataPrefix, false, 2, (Object) null) && key.length() > 5;
        }

        protected final boolean shouldCollapseAttribute(String key, String value, Document.OutputSettings out) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(out, "out");
            if (out.syntax() == Document.OutputSettings.Syntax.html) {
                return value == null || ((value.length() == 0 || StringsKt.equals(value, key, true)) && isBooleanAttribute(key));
            }
            return false;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0032 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:12:0x0033 A[RETURN] */
        public final boolean isBooleanAttribute(String key) {
            int i;
            Intrinsics.checkNotNullParameter(key, "key");
            String[] strArr = Attribute.booleanAttributes;
            int length = strArr.length - 1;
            int i2 = 0;
            while (i2 <= length) {
                i = (i2 + length) >>> 1;
                String str = strArr[i];
                String lowerCase = key.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                int iCompareTo = str.compareTo(lowerCase);
                if (iCompareTo < 0) {
                    i2 = i + 1;
                } else {
                    if (iCompareTo <= 0) {
                        if (i >= 0) {
                            return true;
                        }
                        return false;
                    }
                    length = i - 1;
                }
            }
            i = -(i2 + 1);
            if (i >= 0) {
                return true;
            }
            return false;
        }
    }

    public final void setKey(String key) {
        Range.AttributeRange attributeRangeRemove;
        Intrinsics.checkNotNullParameter(key, "key");
        String str = key;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
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
        String string = str.subSequence(i, length + 1).toString();
        Validate.INSTANCE.notEmpty(string);
        Attributes attributes = this.parent;
        if (attributes != null) {
            Intrinsics.checkNotNull(attributes);
            int iIndexOfKey = attributes.indexOfKey(getKey());
            if (iIndexOfKey != -1) {
                Attributes attributes2 = this.parent;
                Intrinsics.checkNotNull(attributes2);
                String str2 = attributes2.getKeys()[iIndexOfKey];
                Intrinsics.checkNotNull(str2);
                Attributes attributes3 = this.parent;
                Intrinsics.checkNotNull(attributes3);
                attributes3.getKeys()[iIndexOfKey] = string;
                Attributes attributes4 = this.parent;
                Intrinsics.checkNotNull(attributes4);
                Map<String, Range.AttributeRange> ranges = attributes4.getRanges();
                if (ranges != null && (attributeRangeRemove = ranges.remove(str2)) != null) {
                    ranges.put(string, attributeRangeRemove);
                }
            }
        }
        this.attributeKey = string;
    }
}
