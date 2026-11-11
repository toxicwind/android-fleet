package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.parser.Tag;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PseudoTextElement.kt */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated(message = "use {@link Element#selectNodes(String, Class)} instead, with selector of <code>::textnode</code> and class <code>TextNode</code>.")
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\u001d\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/PseudoTextElement;", "Lcom/fleeksoft/ksoup/nodes/Element;", "tag", "Lcom/fleeksoft/ksoup/parser/Tag;", "baseUri", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "<init>", "(Lcom/fleeksoft/ksoup/parser/Tag;Ljava/lang/String;Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "outerHtmlHead", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "outerHtmlTail", "outerHtmlTail$ksoup_release", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PseudoTextElement extends Element {
    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
    }

    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlTail$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PseudoTextElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
        Intrinsics.checkNotNullParameter(tag, "tag");
    }
}
