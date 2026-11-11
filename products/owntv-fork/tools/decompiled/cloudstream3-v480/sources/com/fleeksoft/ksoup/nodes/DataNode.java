package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.Unbaser;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DataNode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\u0003H\u0016J\u0006\u0010\n\u001a\u00020\u0003J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\f\u001a\u00020\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\r\u0010\u0013\u001a\u00020\u0014H\u0010¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/DataNode;", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "data", "", "<init>", "(Ljava/lang/String;)V", "isPacked", "", "()Z", "nodeName", "getWholeData", "setWholeData", "getUnpackedData", "outerHtmlHead", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "createClone", "Lcom/fleeksoft/ksoup/nodes/Node;", "createClone$ksoup_release", "clone", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DataNode extends LeafNode {
    private static final Regex packedRegex = new Regex("eval[(]function[(]p,a,c,k,e,[rd][)][{].*?[}][)]{2}", (Set<? extends RegexOption>) SetsKt.setOf((Object[]) new RegexOption[]{RegexOption.IGNORE_CASE, RegexOption.MULTILINE}));
    private static final Regex packedExtractRegex = new Regex("[}][(]'(.*)', *(\\d+), *(\\d+), *'(.*?)'[.]split[(]'[|]'[)]", (Set<? extends RegexOption>) SetsKt.setOf((Object[]) new RegexOption[]{RegexOption.IGNORE_CASE, RegexOption.MULTILINE}));
    private static final Regex unpackReplaceRegex = new Regex("\\b\\w+\\b", (Set<? extends RegexOption>) SetsKt.setOf((Object[]) new RegexOption[]{RegexOption.IGNORE_CASE, RegexOption.MULTILINE}));

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataNode(String data) {
        super(data);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    public final boolean isPacked() {
        return isPacked(getWholeData());
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return "#data";
    }

    public final String getWholeData() {
        return coreValue();
    }

    public final DataNode setWholeData(String data) {
        coreValue(data);
        return this;
    }

    public final String getUnpackedData() {
        String wholeData = getWholeData();
        if (!isPacked(wholeData)) {
            return wholeData;
        }
        return packedRegex.replace(wholeData, new Function1() { // from class: com.fleeksoft.ksoup.nodes.DataNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DataNode.getUnpackedData$lambda$0((MatchResult) obj);
            }
        });
    }

    static final CharSequence getUnpackedData$lambda$0(MatchResult packed) {
        Intrinsics.checkNotNullParameter(packed, "packed");
        return SequencesKt.joinToString$default(SequencesKt.mapNotNull(Regex.findAll$default(packedExtractRegex, packed.getValue(), 0, 2, null), new Function1() { // from class: com.fleeksoft.ksoup.nodes.DataNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DataNode.getUnpackedData$lambda$0$0((MatchResult) obj);
            }
        }), "", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getUnpackedData$lambda$0$0(MatchResult matchResult) {
        String value;
        String value2;
        Integer intOrNull;
        String value3;
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        MatchGroup matchGroup = matchResult.getGroups().get(1);
        String value4 = matchGroup != null ? matchGroup.getValue() : null;
        MatchGroup matchGroup2 = matchResult.getGroups().get(4);
        final List listSplit$default = (matchGroup2 == null || (value3 = matchGroup2.getValue()) == null) ? null : StringsKt.split$default((CharSequence) value3, new char[]{'|'}, false, 0, 6, (Object) null);
        MatchGroup matchGroup3 = matchResult.getGroups().get(2);
        int iIntValue = (matchGroup3 == null || (value2 = matchGroup3.getValue()) == null || (intOrNull = StringsKt.toIntOrNull(value2)) == null) ? 10 : intOrNull.intValue();
        MatchGroup matchGroup4 = matchResult.getGroups().get(3);
        Integer intOrNull2 = (matchGroup4 == null || (value = matchGroup4.getValue()) == null) ? null : StringsKt.toIntOrNull(value);
        final Unbaser unbaser = new Unbaser(iIntValue);
        if (listSplit$default == null || intOrNull2 == null || listSplit$default.size() != intOrNull2.intValue() || value4 == null) {
            return null;
        }
        return unpackReplaceRegex.replace(value4, new Function1() { // from class: com.fleeksoft.ksoup.nodes.DataNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DataNode.getUnpackedData$lambda$0$0$0(listSplit$default, unbaser, (MatchResult) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getUnpackedData$lambda$0$0$0(List list, Unbaser unbaser, MatchResult match) {
        Intrinsics.checkNotNullParameter(match, "match");
        String value = match.getValue();
        String str = (String) list.get(unbaser.unbase(value));
        if (str.length() != 0) {
            value = str;
        }
        return value;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    /* JADX INFO: renamed from: outerHtmlHead, reason: merged with bridge method [inline-methods] */
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        String wholeData = getWholeData();
        if (out.syntax() == Document.OutputSettings.Syntax.xml) {
            String str = wholeData;
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "<![CDATA[", false, 2, (Object) null)) {
                if (parentNameIs("script")) {
                    accum.append("//<![CDATA[\n").append(str).append("\n//]]>");
                    return;
                } else if (parentNameIs("style")) {
                    accum.append("/*<![CDATA[*/\n").append(str).append("\n/*]]>*/");
                    return;
                } else {
                    accum.append("<![CDATA[").append(str).append("]]>");
                    return;
                }
            }
        }
        accum.append(wholeData);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        Object value$ksoup_release = getValue();
        Intrinsics.checkNotNull(value$ksoup_release, "null cannot be cast to non-null type kotlin.String");
        return new DataNode((String) value$ksoup_release);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        Node nodeClone = super.clone2();
        Intrinsics.checkNotNull(nodeClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.DataNode");
        return (DataNode) nodeClone;
    }

    private final boolean isPacked(String data) {
        if (parentNameIs("script")) {
            return packedRegex.containsMatchIn(data);
        }
        return false;
    }
}
