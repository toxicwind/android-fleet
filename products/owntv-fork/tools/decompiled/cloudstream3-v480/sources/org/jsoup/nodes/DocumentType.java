package org.jsoup.nodes;

import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jsoup.helper.Validate;
import org.jsoup.internal.QuietAppendable;
import org.jsoup.internal.StringUtil;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class DocumentType extends LeafNode {
    private static final String InternalSubsetKey = Attributes.internalKey("doctypeInternalSubset");
    private static final String NameKey = "name";
    public static final String PUBLIC_KEY = "PUBLIC";
    private static final String PubSysKey = "pubSysKey";
    private static final String PublicId = "publicId";
    public static final String SYSTEM_KEY = "SYSTEM";
    private static final String SystemId = "systemId";

    public DocumentType(String str, String str2, String str3) {
        super(str);
        Validate.notNull(str2);
        Validate.notNull(str3);
        attributes().add("name", str).add(PublicId, str2).add(SystemId, str3);
        updatePubSyskey();
    }

    public void setPubSysKey(String str) {
        if (str != null) {
            attr(PubSysKey, str);
        }
    }

    public void setInternalSubset(String str) {
        attributes().put(InternalSubsetKey, str);
    }

    private void updatePubSyskey() {
        if (has(PublicId)) {
            attributes().add(PubSysKey, "PUBLIC");
        } else if (has(SystemId)) {
            attributes().add(PubSysKey, "SYSTEM");
        }
    }

    public String name() {
        return attr("name");
    }

    public String publicId() {
        return attr(PublicId);
    }

    public String systemId() {
        return attr(SystemId);
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#doctype";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(QuietAppendable quietAppendable, Document.OutputSettings outputSettings) {
        if (outputSettings.syntax() == Document.OutputSettings.Syntax.html && !has(PublicId) && !has(SystemId)) {
            quietAppendable.append("<!doctype");
        } else {
            quietAppendable.append("<!DOCTYPE");
        }
        if (has("name")) {
            quietAppendable.append(Stream.ID_UNKNOWN).append(attr("name"));
        }
        if (has(PubSysKey)) {
            quietAppendable.append(Stream.ID_UNKNOWN).append(attr(PubSysKey));
        }
        if (has(PublicId)) {
            quietAppendable.append(" \"").append(attr(PublicId)).append('\"');
        }
        if (has(SystemId)) {
            quietAppendable.append(" \"").append(attr(SystemId)).append('\"');
        }
        Attributes attributes = attributes();
        String str = InternalSubsetKey;
        if (attributes.hasKey(str)) {
            quietAppendable.append(" [").append(attr(str)).append(AbstractJsonLexerKt.END_LIST);
        }
        quietAppendable.append(Typography.greater);
    }

    private boolean has(String str) {
        return !StringUtil.isBlank(attr(str));
    }
}
