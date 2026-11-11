package org.mozilla.javascript.optimizer;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX INFO: loaded from: classes8.dex */
class Optimizer {
    static final int AnyType = 3;
    static final int NoType = 0;
    static final int NumberType = 1;
    private boolean inDirectCallFunction;
    private boolean parameterUsedInNumberContext;
    OptFunctionNode theFunction;

    Optimizer() {
    }

    void optimize(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            optimizeFunction(OptFunctionNode.get(scriptNode, i));
        }
    }

    private void optimizeFunction(OptFunctionNode optFunctionNode) {
        if (optFunctionNode.fnode.requiresActivation()) {
            return;
        }
        this.inDirectCallFunction = optFunctionNode.isTargetOfDirectCall();
        this.theFunction = optFunctionNode;
        ArrayList arrayList = new ArrayList();
        buildStatementList_r(optFunctionNode.fnode, arrayList);
        int size = arrayList.size();
        Node[] nodeArr = new Node[size];
        arrayList.toArray(nodeArr);
        Block.runFlowAnalyzes(optFunctionNode, nodeArr);
        if (optFunctionNode.fnode.requiresActivation()) {
            return;
        }
        this.parameterUsedInNumberContext = false;
        for (int i = 0; i < size; i++) {
            rewriteForNumberVariables(nodeArr[i], 1);
        }
        optFunctionNode.setParameterNumberContext(this.parameterUsedInNumberContext);
    }

    private void markDCPNumberContext(Node node) {
        if (this.inDirectCallFunction && node.getType() == 60) {
            if (this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
                this.parameterUsedInNumberContext = true;
            }
        }
    }

    private boolean convertParameter(Node node) {
        if (!this.inDirectCallFunction || node.getType() != 60) {
            return false;
        }
        if (!this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
            return false;
        }
        node.removeProp(8);
        return true;
    }

    private int rewriteForNumberVariables(Node node, int i) {
        int type = node.getType();
        if (type != 39) {
            if (type != 41) {
                if (type == 43) {
                    Node firstChild = node.getFirstChild();
                    rewriteAsObjectChildren(firstChild, firstChild.getFirstChild());
                    Node next = firstChild.getNext();
                    if (((OptFunctionNode) node.getProp(9)) != null) {
                        while (next != null) {
                            if (rewriteForNumberVariables(next, 1) == 1) {
                                markDCPNumberContext(next);
                            }
                            next = next.getNext();
                        }
                    } else {
                        rewriteAsObjectChildren(node, next);
                    }
                    return 0;
                }
                if (type == 45) {
                    node.putIntProp(8, 0);
                    return 1;
                }
                if (type != 81) {
                    if (type == 147) {
                        if (rewriteForNumberVariables(node.getFirstChild(), 1) == 1) {
                            node.putIntProp(8, 0);
                        }
                        return 0;
                    }
                    if (type != 154) {
                        if (type != 170) {
                            if (type == 60) {
                                int varIndex = this.theFunction.getVarIndex(node);
                                if (this.inDirectCallFunction && this.theFunction.isParameter(varIndex) && i == 1) {
                                    node.putIntProp(8, 0);
                                    return 1;
                                }
                                if (!this.theFunction.isNumberVar(varIndex)) {
                                    return 0;
                                }
                                node.putIntProp(8, 0);
                                return 1;
                            }
                            if (type != 61) {
                                if (type == 119 || type == 120) {
                                    Node firstChild2 = node.getFirstChild();
                                    if (firstChild2.getIntProp(31, 0) == 1) {
                                        return 0;
                                    }
                                    int iRewriteForNumberVariables = rewriteForNumberVariables(firstChild2, 1);
                                    if (firstChild2.getType() == 60) {
                                        if (iRewriteForNumberVariables != 1 || convertParameter(firstChild2)) {
                                            return 0;
                                        }
                                        node.putIntProp(8, 0);
                                        markDCPNumberContext(firstChild2);
                                        return 1;
                                    }
                                    if (firstChild2.getType() == 39 || firstChild2.getType() == 33) {
                                        return iRewriteForNumberVariables;
                                    }
                                    return 0;
                                }
                                switch (type) {
                                    case 9:
                                    case 10:
                                    case 11:
                                        break;
                                    default:
                                        switch (type) {
                                            case 14:
                                            case 15:
                                            case 16:
                                            case 17:
                                                Node firstChild3 = node.getFirstChild();
                                                Node next2 = firstChild3.getNext();
                                                int iRewriteForNumberVariables2 = rewriteForNumberVariables(firstChild3, 1);
                                                int iRewriteForNumberVariables3 = rewriteForNumberVariables(next2, 1);
                                                markDCPNumberContext(firstChild3);
                                                markDCPNumberContext(next2);
                                                if (convertParameter(firstChild3)) {
                                                    if (!convertParameter(next2) && iRewriteForNumberVariables3 == 1) {
                                                        node.putIntProp(8, 2);
                                                    }
                                                } else if (convertParameter(next2)) {
                                                    if (iRewriteForNumberVariables2 == 1) {
                                                        node.putIntProp(8, 1);
                                                    }
                                                } else if (iRewriteForNumberVariables2 == 1) {
                                                    if (iRewriteForNumberVariables3 == 1) {
                                                        node.putIntProp(8, 0);
                                                    } else {
                                                        node.putIntProp(8, 1);
                                                    }
                                                } else if (iRewriteForNumberVariables3 == 1) {
                                                    node.putIntProp(8, 2);
                                                }
                                                return 0;
                                            case 18:
                                            case 19:
                                                break;
                                            default:
                                                switch (type) {
                                                    case 21:
                                                        Node firstChild4 = node.getFirstChild();
                                                        Node next3 = firstChild4.getNext();
                                                        int iRewriteForNumberVariables4 = rewriteForNumberVariables(firstChild4, 1);
                                                        int iRewriteForNumberVariables5 = rewriteForNumberVariables(next3, 1);
                                                        if (convertParameter(firstChild4)) {
                                                            if (!convertParameter(next3) && iRewriteForNumberVariables5 == 1) {
                                                                node.putIntProp(8, 2);
                                                            }
                                                        } else if (convertParameter(next3)) {
                                                            if (iRewriteForNumberVariables4 == 1) {
                                                                node.putIntProp(8, 1);
                                                            }
                                                        } else if (iRewriteForNumberVariables4 == 1) {
                                                            if (iRewriteForNumberVariables5 == 1) {
                                                                node.putIntProp(8, 0);
                                                                return 1;
                                                            }
                                                            node.putIntProp(8, 1);
                                                        } else if (iRewriteForNumberVariables5 == 1) {
                                                            node.putIntProp(8, 2);
                                                        }
                                                        return 0;
                                                    case 22:
                                                    case 23:
                                                    case 24:
                                                    case 25:
                                                        break;
                                                    default:
                                                        switch (type) {
                                                            case 27:
                                                            case 28:
                                                            case 29:
                                                                Node firstChild5 = node.getFirstChild();
                                                                if (rewriteForNumberVariables(firstChild5, 1) != 1 || convertParameter(firstChild5)) {
                                                                    return 0;
                                                                }
                                                                node.putIntProp(8, 0);
                                                                markDCPNumberContext(firstChild5);
                                                                return 1;
                                                            default:
                                                                rewriteAsObjectChildren(node, node.getFirstChild());
                                                                return 0;
                                                        }
                                                }
                                                break;
                                        }
                                        break;
                                }
                            }
                        }
                        Node next4 = node.getFirstChild().getNext();
                        int iRewriteForNumberVariables6 = rewriteForNumberVariables(next4, 1);
                        int varIndex2 = this.theFunction.getVarIndex(node);
                        if (this.inDirectCallFunction && this.theFunction.isParameter(varIndex2)) {
                            if (iRewriteForNumberVariables6 != 1) {
                                return iRewriteForNumberVariables6;
                            }
                            if (!convertParameter(next4)) {
                                node.putIntProp(8, 0);
                                return 1;
                            }
                            markDCPNumberContext(next4);
                            return 0;
                        }
                        if (this.theFunction.isNumberVar(varIndex2)) {
                            if (iRewriteForNumberVariables6 != 1) {
                                node.removeChild(next4);
                                node.addChildToBack(new Node(164, next4));
                            }
                            node.putIntProp(8, 0);
                            markDCPNumberContext(next4);
                            return 1;
                        }
                        if (iRewriteForNumberVariables6 == 1 && !convertParameter(next4)) {
                            node.removeChild(next4);
                            node.addChildToBack(new Node(163, next4));
                        }
                        return 0;
                    }
                }
                Node firstChild6 = node.getFirstChild();
                Node next5 = firstChild6.getNext();
                int iRewriteForNumberVariables7 = rewriteForNumberVariables(firstChild6, 1);
                int iRewriteForNumberVariables8 = rewriteForNumberVariables(next5, 1);
                markDCPNumberContext(firstChild6);
                markDCPNumberContext(next5);
                if (iRewriteForNumberVariables7 != 1) {
                    if (iRewriteForNumberVariables8 != 1) {
                        return 3;
                    }
                    if (!convertParameter(firstChild6)) {
                        node.removeChild(firstChild6);
                        node.addChildToFront(new Node(164, firstChild6));
                        node.putIntProp(8, 0);
                    }
                    return 1;
                }
                if (iRewriteForNumberVariables8 == 1) {
                    node.putIntProp(8, 0);
                    return 1;
                }
                if (!convertParameter(next5)) {
                    node.removeChild(next5);
                    node.addChildToBack(new Node(164, next5));
                    node.putIntProp(8, 0);
                }
                return 1;
            }
            Node firstChild7 = node.getFirstChild();
            Node next6 = firstChild7.getNext();
            Node next7 = next6.getNext();
            if (rewriteForNumberVariables(firstChild7, 1) == 1 && !convertParameter(firstChild7)) {
                node.removeChild(firstChild7);
                node.addChildToFront(new Node(163, firstChild7));
            }
            if (rewriteForNumberVariables(next6, 1) == 1 && !convertParameter(next6)) {
                node.putIntProp(8, 1);
            }
            if (rewriteForNumberVariables(next7, 1) == 1 && !convertParameter(next7)) {
                node.removeChild(next7);
                node.addChildToBack(new Node(163, next7));
            }
            return 0;
        }
        Node firstChild8 = node.getFirstChild();
        Node next8 = firstChild8.getNext();
        if (rewriteForNumberVariables(firstChild8, 1) == 1 && !convertParameter(firstChild8)) {
            node.removeChild(firstChild8);
            node.addChildToFront(new Node(163, firstChild8));
        }
        if (rewriteForNumberVariables(next8, 1) == 1 && !convertParameter(next8)) {
            node.putIntProp(8, 2);
        }
        return 0;
    }

    private void rewriteAsObjectChildren(Node node, Node node2) {
        while (node2 != null) {
            Node next = node2.getNext();
            if (rewriteForNumberVariables(node2, 0) == 1 && !convertParameter(node2)) {
                node.removeChild(node2);
                Node node3 = new Node(163, node2);
                if (next == null) {
                    node.addChildToBack(node3);
                } else {
                    node.addChildBefore(node3, next);
                }
            }
            node2 = next;
        }
    }

    private static void buildStatementList_r(Node node, List<Node> list) {
        int type = node.getType();
        if (type == 143 || type == 155 || type == 146 || type == 122) {
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
                buildStatementList_r(firstChild, list);
            }
            return;
        }
        list.add(node);
    }
}
