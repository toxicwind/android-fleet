package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.parser.Tag;
import com.fleeksoft.ksoup.select.Elements;
import com.fleeksoft.ksoup.select.Evaluator;
import com.fleeksoft.ksoup.select.Selector;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: FormElement.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0001J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0000H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/FormElement;", "Lcom/fleeksoft/ksoup/nodes/Element;", "tag", "Lcom/fleeksoft/ksoup/parser/Tag;", "baseUri", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "<init>", "(Lcom/fleeksoft/ksoup/parser/Tag;Ljava/lang/String;Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "linkedEls", "Lcom/fleeksoft/ksoup/select/Elements;", "submittable", "Lcom/fleeksoft/ksoup/select/Evaluator;", "elements", "addElement", "element", "removeChild", "", "out", "Lcom/fleeksoft/ksoup/nodes/Node;", "clone", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FormElement extends Element {
    private final Elements linkedEls;
    private final Evaluator submittable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FormElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.linkedEls = new Elements(null, 1, 0 == true ? 1 : 0);
        this.submittable = Selector.INSTANCE.evaluatorOf(ArraysKt.joinToString$default(SharedConstants.INSTANCE.getFormSubmitTags(), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    public final Elements elements() {
        Elements elementsSelect = select(this.submittable);
        for (Element element : this.linkedEls) {
            if (element.ownerDocument() != null && !elementsSelect.contains((Object) element)) {
                elementsSelect.add(element);
            }
        }
        return elementsSelect;
    }

    public final FormElement addElement(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.linkedEls.add(element);
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    protected void removeChild(Node out) {
        Intrinsics.checkNotNullParameter(out, "out");
        super.removeChild(out);
        TypeIntrinsics.asMutableCollection(this.linkedEls).remove(out);
    }

    @Override // com.fleeksoft.ksoup.nodes.Element, com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone, reason: avoid collision after fix types in other method */
    public Node clone2() {
        Element elementClone2 = super.clone2();
        Intrinsics.checkNotNull(elementClone2, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.FormElement");
        return (FormElement) elementClone2;
    }
}
