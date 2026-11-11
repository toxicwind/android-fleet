package org.jsoup.nodes;

import org.jsoup.internal.QuietAppendable;

/* JADX INFO: loaded from: classes8.dex */
public class DataNode extends LeafNode {
    public DataNode(String str) {
        super(str);
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#data";
    }

    public String getWholeData() {
        return coreValue();
    }

    public DataNode setWholeData(String str) {
        coreValue(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(QuietAppendable quietAppendable, Document.OutputSettings outputSettings) {
        String wholeData = getWholeData();
        if (outputSettings.syntax() == Document.OutputSettings.Syntax.xml && !wholeData.contains("<![CDATA[")) {
            if (parentNameIs("script")) {
                quietAppendable.append("//<![CDATA[\n").append(wholeData).append("\n//]]>");
                return;
            } else if (parentNameIs("style")) {
                quietAppendable.append("/*<![CDATA[*/\n").append(wholeData).append("\n/*]]>*/");
                return;
            } else {
                quietAppendable.append("<![CDATA[").append(wholeData).append("]]>");
                return;
            }
        }
        quietAppendable.append(wholeData);
    }

    @Override // org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone */
    public DataNode mo10839clone() {
        return (DataNode) super.mo10839clone();
    }
}
