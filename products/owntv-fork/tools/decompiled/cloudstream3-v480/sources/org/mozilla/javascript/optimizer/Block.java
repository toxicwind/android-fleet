package org.mozilla.javascript.optimizer;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.Jump;

/* JADX INFO: loaded from: classes8.dex */
class Block {
    static final boolean DEBUG = false;
    private static int debug_blockCount;
    private int itsBlockID;
    private int itsEndNodeIndex;
    private BitSet itsLiveOnEntrySet;
    private BitSet itsLiveOnExitSet;
    private BitSet itsNotDefSet;
    private Block[] itsPredecessors;
    private int itsStartNodeIndex;
    private Block[] itsSuccessors;
    private BitSet itsUseBeforeDefSet;

    private void printLiveOnEntrySet(OptFunctionNode optFunctionNode) {
    }

    private static String toString(Block[] blockArr, Node[] nodeArr) {
        return null;
    }

    private static class FatBlock {
        private final HashSet<FatBlock> predecessors;
        Block realBlock;
        private final HashSet<FatBlock> successors;

        private FatBlock() {
            this.successors = new HashSet<>();
            this.predecessors = new HashSet<>();
        }

        private static Block[] reduceToArray(Set<FatBlock> set) {
            if (set.isEmpty()) {
                return null;
            }
            Block[] blockArr = new Block[set.size()];
            Iterator<FatBlock> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                blockArr[i] = it.next().realBlock;
                i++;
            }
            return blockArr;
        }

        void addSuccessor(FatBlock fatBlock) {
            this.successors.add(fatBlock);
        }

        void addPredecessor(FatBlock fatBlock) {
            this.predecessors.add(fatBlock);
        }

        Block[] getSuccessors() {
            return reduceToArray(this.successors);
        }

        Block[] getPredecessors() {
            return reduceToArray(this.predecessors);
        }
    }

    Block(int i, int i2) {
        this.itsStartNodeIndex = i;
        this.itsEndNodeIndex = i2;
    }

    static void runFlowAnalyzes(OptFunctionNode optFunctionNode, Node[] nodeArr) {
        int paramCount = optFunctionNode.fnode.getParamCount();
        int paramAndVarCount = optFunctionNode.fnode.getParamAndVarCount();
        int[] iArr = new int[paramAndVarCount];
        for (int i = 0; i != paramCount; i++) {
            iArr[i] = 3;
        }
        for (int i2 = paramCount; i2 != paramAndVarCount; i2++) {
            iArr[i2] = 0;
        }
        Block[] blockArrBuildBlocks = buildBlocks(nodeArr);
        reachingDefDataFlow(optFunctionNode, nodeArr, blockArrBuildBlocks, iArr);
        typeFlow(optFunctionNode, nodeArr, blockArrBuildBlocks, iArr);
        while (paramCount != paramAndVarCount) {
            if (iArr[paramCount] == 1) {
                optFunctionNode.setIsNumberVar(paramCount);
            }
            paramCount++;
        }
    }

    private static Block[] buildBlocks(Node[] nodeArr) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < nodeArr.length; i2++) {
            int type = nodeArr[i2].getType();
            if (type == 5 || type == 6 || type == 7) {
                FatBlock fatBlockNewFatBlock = newFatBlock(i, i2);
                if (nodeArr[i].getType() == 145) {
                    map.put(nodeArr[i], fatBlockNewFatBlock);
                }
                arrayList.add(fatBlockNewFatBlock);
                i = i2 + 1;
            } else if (type == 145 && i2 != i) {
                FatBlock fatBlockNewFatBlock2 = newFatBlock(i, i2 - 1);
                if (nodeArr[i].getType() == 145) {
                    map.put(nodeArr[i], fatBlockNewFatBlock2);
                }
                arrayList.add(fatBlockNewFatBlock2);
                i = i2;
            }
        }
        if (i != nodeArr.length) {
            FatBlock fatBlockNewFatBlock3 = newFatBlock(i, nodeArr.length - 1);
            if (nodeArr[i].getType() == 145) {
                map.put(nodeArr[i], fatBlockNewFatBlock3);
            }
            arrayList.add(fatBlockNewFatBlock3);
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            FatBlock fatBlock = (FatBlock) arrayList.get(i3);
            Node node = nodeArr[fatBlock.realBlock.itsEndNodeIndex];
            int type2 = node.getType();
            if (type2 != 5 && i3 < arrayList.size() - 1) {
                FatBlock fatBlock2 = (FatBlock) arrayList.get(i3 + 1);
                fatBlock.addSuccessor(fatBlock2);
                fatBlock2.addPredecessor(fatBlock);
            }
            if (type2 == 7 || type2 == 6 || type2 == 5) {
                Node node2 = ((Jump) node).target;
                FatBlock fatBlock3 = (FatBlock) map.get(node2);
                node2.putProp(6, fatBlock3.realBlock);
                fatBlock.addSuccessor(fatBlock3);
                fatBlock3.addPredecessor(fatBlock);
            }
        }
        Block[] blockArr = new Block[arrayList.size()];
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            FatBlock fatBlock4 = (FatBlock) arrayList.get(i4);
            Block block = fatBlock4.realBlock;
            block.itsSuccessors = fatBlock4.getSuccessors();
            block.itsPredecessors = fatBlock4.getPredecessors();
            block.itsBlockID = i4;
            blockArr[i4] = block;
        }
        return blockArr;
    }

    private static FatBlock newFatBlock(int i, int i2) {
        FatBlock fatBlock = new FatBlock();
        fatBlock.realBlock = new Block(i, i2);
        return fatBlock;
    }

    private static void reachingDefDataFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, Block[] blockArr, int[] iArr) {
        Block[] blockArr2;
        for (Block block : blockArr) {
            block.initLiveOnEntrySets(optFunctionNode, nodeArr);
        }
        boolean[] zArr = new boolean[blockArr.length];
        boolean[] zArr2 = new boolean[blockArr.length];
        int length = blockArr.length - 1;
        zArr[length] = true;
        while (true) {
            boolean z = false;
            while (true) {
                if (zArr[length] || !zArr2[length]) {
                    zArr2[length] = true;
                    zArr[length] = false;
                    if (blockArr[length].doReachedUseDataFlow() && (blockArr2 = blockArr[length].itsPredecessors) != null) {
                        for (Block block2 : blockArr2) {
                            int i = block2.itsBlockID;
                            zArr[i] = true;
                            z |= i > length;
                        }
                    }
                }
                if (length == 0) {
                    break;
                } else {
                    length--;
                }
            }
            if (z) {
                length = blockArr.length - 1;
            } else {
                blockArr[0].markAnyTypeVariables(iArr);
                return;
            }
        }
    }

    private static void typeFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, Block[] blockArr, int[] iArr) {
        boolean z;
        Block[] blockArr2;
        boolean[] zArr = new boolean[blockArr.length];
        boolean[] zArr2 = new boolean[blockArr.length];
        zArr[0] = true;
        do {
            int i = 0;
            z = false;
            while (true) {
                if (zArr[i] || !zArr2[i]) {
                    zArr2[i] = true;
                    zArr[i] = false;
                    if (blockArr[i].doTypeFlow(optFunctionNode, nodeArr, iArr) && (blockArr2 = blockArr[i].itsSuccessors) != null) {
                        for (Block block : blockArr2) {
                            int i2 = block.itsBlockID;
                            zArr[i2] = true;
                            z |= i2 < i;
                        }
                    }
                }
                if (i == blockArr.length - 1) {
                    break;
                } else {
                    i++;
                }
            }
        } while (z);
    }

    private static boolean assignType(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        int i4 = i2 | i3;
        iArr[i] = i4;
        return i3 != i4;
    }

    private void markAnyTypeVariables(int[] iArr) {
        for (int i = 0; i != iArr.length; i++) {
            if (this.itsLiveOnEntrySet.get(i)) {
                assignType(iArr, i, 3);
            }
        }
    }

    private void lookForVariableAccess(OptFunctionNode optFunctionNode, Node node) {
        int type = node.getType();
        if (type != 60) {
            if (type != 61) {
                if (type == 119 || type == 120) {
                    Node firstChild = node.getFirstChild();
                    if (firstChild.getType() == 60) {
                        int varIndex = optFunctionNode.getVarIndex(firstChild);
                        if (!this.itsNotDefSet.get(varIndex)) {
                            this.itsUseBeforeDefSet.set(varIndex);
                        }
                        this.itsNotDefSet.set(varIndex);
                        return;
                    }
                    lookForVariableAccess(optFunctionNode, firstChild);
                    return;
                }
                if (type == 151) {
                    int indexForNameNode = optFunctionNode.fnode.getIndexForNameNode(node);
                    if (indexForNameNode <= -1 || this.itsNotDefSet.get(indexForNameNode)) {
                        return;
                    }
                    this.itsUseBeforeDefSet.set(indexForNameNode);
                    return;
                }
                if (type != 170) {
                    for (Node firstChild2 = node.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNext()) {
                        lookForVariableAccess(optFunctionNode, firstChild2);
                    }
                    return;
                }
            }
            lookForVariableAccess(optFunctionNode, node.getFirstChild().getNext());
            this.itsNotDefSet.set(optFunctionNode.getVarIndex(node));
            return;
        }
        int varIndex2 = optFunctionNode.getVarIndex(node);
        if (this.itsNotDefSet.get(varIndex2)) {
            return;
        }
        this.itsUseBeforeDefSet.set(varIndex2);
    }

    private void initLiveOnEntrySets(OptFunctionNode optFunctionNode, Node[] nodeArr) {
        int varCount = optFunctionNode.getVarCount();
        this.itsUseBeforeDefSet = new BitSet(varCount);
        this.itsNotDefSet = new BitSet(varCount);
        this.itsLiveOnEntrySet = new BitSet(varCount);
        this.itsLiveOnExitSet = new BitSet(varCount);
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; i++) {
            lookForVariableAccess(optFunctionNode, nodeArr[i]);
        }
        this.itsNotDefSet.flip(0, varCount);
    }

    private boolean doReachedUseDataFlow() {
        this.itsLiveOnExitSet.clear();
        Block[] blockArr = this.itsSuccessors;
        if (blockArr != null) {
            for (Block block : blockArr) {
                this.itsLiveOnExitSet.or(block.itsLiveOnEntrySet);
            }
        }
        return updateEntrySet(this.itsLiveOnEntrySet, this.itsLiveOnExitSet, this.itsUseBeforeDefSet, this.itsNotDefSet);
    }

    private static boolean updateEntrySet(BitSet bitSet, BitSet bitSet2, BitSet bitSet3, BitSet bitSet4) {
        int iCardinality = bitSet.cardinality();
        bitSet.or(bitSet2);
        bitSet.and(bitSet4);
        bitSet.or(bitSet3);
        return bitSet.cardinality() != iCardinality;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:38:0x007a A[FALL_THROUGH, RETURN] */
    private static int findExpressionType(OptFunctionNode optFunctionNode, Node node, int[] iArr) {
        int type = node.getType();
        if (type != 37 && type != 41) {
            if (type != 45 && type != 81) {
                if (type != 98) {
                    if (type == 115) {
                        Node next = node.getFirstChild().getNext();
                        Node next2 = next.getNext();
                        return findExpressionType(optFunctionNode, next2, iArr) | findExpressionType(optFunctionNode, next, iArr);
                    }
                    if (type != 170) {
                        if (type == 60) {
                            return iArr[optFunctionNode.getVarIndex(node)];
                        }
                        if (type != 61) {
                            switch (type) {
                                case 8:
                                    break;
                                default:
                                    switch (type) {
                                        case 18:
                                        case 19:
                                        case 20:
                                        case 22:
                                        case 23:
                                        case 24:
                                        case 25:
                                            break;
                                        case 21:
                                            Node firstChild = node.getFirstChild();
                                            return findExpressionType(optFunctionNode, firstChild.getNext(), iArr) | findExpressionType(optFunctionNode, firstChild, iArr);
                                        default:
                                            switch (type) {
                                                default:
                                                    switch (type) {
                                                        case 117:
                                                        case 118:
                                                            Node firstChild2 = node.getFirstChild();
                                                            return findExpressionType(optFunctionNode, firstChild2.getNext(), iArr) | findExpressionType(optFunctionNode, firstChild2, iArr);
                                                        case 119:
                                                        case 120:
                                                            break;
                                                        default:
                                                            return 3;
                                                    }
                                                case 27:
                                                case 28:
                                                case 29:
                                                    return 1;
                                            }
                                            break;
                                    }
                                case 9:
                                case 10:
                                case 11:
                                    return 1;
                            }
                        }
                    }
                }
            }
            return 1;
        }
        return findExpressionType(optFunctionNode, node.getLastChild(), iArr);
    }

    private static boolean findDefPoints(OptFunctionNode optFunctionNode, Node node, int[] iArr) {
        boolean zAssignType;
        Node firstChild = node.getFirstChild();
        boolean zFindDefPoints = false;
        for (Node next = firstChild; next != null; next = next.getNext()) {
            zFindDefPoints |= findDefPoints(optFunctionNode, next, iArr);
        }
        int type = node.getType();
        if (type != 61 && type != 170) {
            if ((type == 119 || type == 120) && firstChild.getType() == 60) {
                int varIndex = optFunctionNode.getVarIndex(firstChild);
                if (!optFunctionNode.fnode.getParamAndVarConst()[varIndex]) {
                    zAssignType = assignType(iArr, varIndex, 1);
                    return zAssignType | zFindDefPoints;
                }
            }
            return zFindDefPoints;
        }
        int iFindExpressionType = findExpressionType(optFunctionNode, firstChild.getNext(), iArr);
        int varIndex2 = optFunctionNode.getVarIndex(node);
        if (node.getType() != 61 || !optFunctionNode.fnode.getParamAndVarConst()[varIndex2]) {
            zAssignType = assignType(iArr, varIndex2, iFindExpressionType);
            return zAssignType | zFindDefPoints;
        }
        return zFindDefPoints;
    }

    private boolean doTypeFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, int[] iArr) {
        boolean zFindDefPoints = false;
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; i++) {
            Node node = nodeArr[i];
            if (node != null) {
                zFindDefPoints |= findDefPoints(optFunctionNode, node, iArr);
            }
        }
        return zFindDefPoints;
    }
}
