package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: DocumentType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0003J\b\u0010\u000b\u001a\u00020\tH\u0002J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u0005\u001a\u00020\u0003J\b\u0010\f\u001a\u00020\u0003H\u0016J\u001d\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0014H\u0010¢\u0006\u0002\b\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/DocumentType;", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "name", "", DocumentType.PublicId, DocumentType.SystemId, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "setPubSysKey", "", ES6Iterator.VALUE_PROPERTY, "updatePubSyskey", "nodeName", "outerHtmlHead", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "has", "", "attribute", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DocumentType extends LeafNode {
    private static final String NameKey = "name";
    public static final String PUBLIC_KEY = "PUBLIC";
    private static final String PubSysKey = "pubSysKey";
    private static final String PublicId = "publicId";
    public static final String SYSTEM_KEY = "SYSTEM";
    private static final String SystemId = "systemId";
    private final String name;
    private final String publicId;
    private final String systemId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DocumentType(String name, String publicId, String systemId) {
        super(name);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(publicId, "publicId");
        Intrinsics.checkNotNullParameter(systemId, "systemId");
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        attributes().add("name", name).add(PublicId, publicId).add(SystemId, systemId);
        updatePubSyskey();
    }

    public final void setPubSysKey(String value) {
        if (value != null) {
            attr(PubSysKey, value);
        }
    }

    private final void updatePubSyskey() {
        if (has(PublicId)) {
            attributes().add(PubSysKey, "PUBLIC");
        } else if (has(SystemId)) {
            attributes().add(PubSysKey, "SYSTEM");
        }
    }

    public final String name() {
        return attr("name");
    }

    public final String publicId() {
        return attr(PublicId);
    }

    public final String systemId() {
        return attr(SystemId);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#doctype";
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        if (out.syntax() == Document.OutputSettings.Syntax.html && !has(PublicId) && !has(SystemId)) {
            accum.append("<!doctype");
        } else {
            accum.append("<!DOCTYPE");
        }
        if (has("name")) {
            accum.append(Stream.ID_UNKNOWN).append(attr("name"));
        }
        if (has(PubSysKey)) {
            accum.append(Stream.ID_UNKNOWN).append(attr(PubSysKey));
        }
        if (has(PublicId)) {
            accum.append(" \"").append(attr(PublicId)).append('\"');
        }
        if (has(SystemId)) {
            accum.append(" \"").append(attr(SystemId)).append('\"');
        }
        accum.append(Typography.greater);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        return new DocumentType(this.name, this.publicId, this.systemId);
    }

    private final boolean has(String attribute) {
        return !StringUtil.INSTANCE.isBlank(attr(attribute));
    }
}
