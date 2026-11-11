package org.mozilla.javascript.optimizer;

import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.NodeTransformer;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX INFO: loaded from: classes8.dex */
class OptTransformer extends NodeTransformer {
    private List<OptFunctionNode> directCallTargets;
    private Map<String, OptFunctionNode> possibleDirectCalls;

    OptTransformer(Map<String, OptFunctionNode> map, List<OptFunctionNode> list) {
        this.possibleDirectCalls = map;
        this.directCallTargets = list;
    }

    @Override // org.mozilla.javascript.NodeTransformer
    protected void visitNew(Node node, ScriptNode scriptNode) {
        detectDirectCall(node, scriptNode);
        super.visitNew(node, scriptNode);
    }

    @Override // org.mozilla.javascript.NodeTransformer
    protected void visitCall(Node node, ScriptNode scriptNode) {
        detectDirectCall(node, scriptNode);
        super.visitCall(node, scriptNode);
    }

    private void detectDirectCall(Node node, ScriptNode scriptNode) {
        String string;
        OptFunctionNode optFunctionNode;
        if (scriptNode.getType() == 122) {
            Node firstChild = node.getFirstChild();
            int i = 0;
            if (firstChild != null) {
                Node next = firstChild.getNext();
                while (next != null) {
                    next = next.getNext();
                    i++;
                }
            }
            if (i == 0) {
                OptFunctionNode.get(scriptNode).itsContainsCalls0 = true;
            }
            if (this.possibleDirectCalls != null) {
                if (firstChild.getType() == 44) {
                    string = firstChild.getString();
                } else if (firstChild.getType() == 33) {
                    string = firstChild.getFirstChild().getNext().getString();
                } else {
                    if (firstChild.getType() == 34) {
                        throw Kit.codeBug();
                    }
                    string = null;
                }
                if (string == null || (optFunctionNode = this.possibleDirectCalls.get(string)) == null || i != optFunctionNode.fnode.getParamCount() || optFunctionNode.fnode.requiresActivation() || i > 32) {
                    return;
                }
                node.putProp(9, optFunctionNode);
                if (optFunctionNode.isTargetOfDirectCall()) {
                    return;
                }
                int size = this.directCallTargets.size();
                this.directCallTargets.add(optFunctionNode);
                optFunctionNode.setDirectTargetIndex(size);
            }
        }
    }
}
