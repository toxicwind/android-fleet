package com.google.firebase.database.collection;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public abstract class LLRBValueNode<K, V> implements LLRBNode<K, V> {
    public final K key;
    public LLRBNode<K, V> left;
    public final LLRBNode<K, V> right;
    public final V value;

    public LLRBValueNode(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        this.key = k;
        this.value = v;
        this.left = lLRBNode == null ? LLRBEmptyNode.INSTANCE : lLRBNode;
        this.right = lLRBNode2 == null ? LLRBEmptyNode.INSTANCE : lLRBNode2;
    }

    private LLRBValueNode<K, V> colorFlip() {
        LLRBNode<K, V> lLRBNode = this.left;
        LLRBNode<K, V> lLRBNodeCopy = lLRBNode.copy(null, null, oppositeColor(lLRBNode), null, null);
        LLRBNode<K, V> lLRBNode2 = this.right;
        return copy((Object) null, (Object) null, isRed() ? LLRBNode.Color.BLACK : LLRBNode.Color.RED, (LLRBNode) lLRBNodeCopy, (LLRBNode) lLRBNode2.copy(null, null, oppositeColor(lLRBNode2), null, null));
    }

    private LLRBValueNode<K, V> fixUp() {
        LLRBValueNode<K, V> lLRBValueNodeRotateLeft = (!this.right.isRed() || this.left.isRed()) ? this : rotateLeft();
        if (lLRBValueNodeRotateLeft.left.isRed() && ((LLRBValueNode) lLRBValueNodeRotateLeft.left).left.isRed()) {
            lLRBValueNodeRotateLeft = lLRBValueNodeRotateLeft.rotateRight();
        }
        return (lLRBValueNodeRotateLeft.left.isRed() && lLRBValueNodeRotateLeft.right.isRed()) ? lLRBValueNodeRotateLeft.colorFlip() : lLRBValueNodeRotateLeft;
    }

    private LLRBValueNode<K, V> moveRedLeft() {
        LLRBValueNode<K, V> lLRBValueNodeColorFlip = colorFlip();
        return lLRBValueNodeColorFlip.getRight().getLeft().isRed() ? lLRBValueNodeColorFlip.copy(null, null, null, ((LLRBValueNode) lLRBValueNodeColorFlip.getRight()).rotateRight()).rotateLeft().colorFlip() : lLRBValueNodeColorFlip;
    }

    private LLRBValueNode<K, V> moveRedRight() {
        LLRBValueNode<K, V> lLRBValueNodeColorFlip = colorFlip();
        return lLRBValueNodeColorFlip.getLeft().getLeft().isRed() ? lLRBValueNodeColorFlip.rotateRight().colorFlip() : lLRBValueNodeColorFlip;
    }

    public static LLRBNode.Color oppositeColor(LLRBNode lLRBNode) {
        return lLRBNode.isRed() ? LLRBNode.Color.BLACK : LLRBNode.Color.RED;
    }

    private LLRBNode<K, V> removeMin() {
        if (this.left.isEmpty()) {
            return LLRBEmptyNode.INSTANCE;
        }
        LLRBValueNode<K, V> lLRBValueNodeMoveRedLeft = (getLeft().isRed() || getLeft().getLeft().isRed()) ? this : moveRedLeft();
        return lLRBValueNodeMoveRedLeft.copy(null, null, ((LLRBValueNode) lLRBValueNodeMoveRedLeft.left).removeMin(), null).fixUp();
    }

    private LLRBValueNode<K, V> rotateLeft() {
        return (LLRBValueNode) this.right.copy(null, null, getColor(), copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) null, (LLRBNode) ((LLRBValueNode) this.right).left), null);
    }

    private LLRBValueNode<K, V> rotateRight() {
        return (LLRBValueNode) this.left.copy(null, null, getColor(), null, copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) ((LLRBValueNode) this.left).right, (LLRBNode) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.database.collection.LLRBNode
    public /* bridge */ /* synthetic */ LLRBNode copy(Object obj, Object obj2, LLRBNode.Color color, LLRBNode lLRBNode, LLRBNode lLRBNode2) {
        return copy(obj, obj2, color, (LLRBNode<Object, Object>) lLRBNode, (LLRBNode<Object, Object>) lLRBNode2);
    }

    public abstract LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2);

    public abstract LLRBNode.Color getColor();

    @Override // com.google.firebase.database.collection.LLRBNode
    public K getKey() {
        return this.key;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getLeft() {
        return this.left;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getMax() {
        return this.right.isEmpty() ? this : this.right.getMax();
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getMin() {
        return this.left.isEmpty() ? this : this.left.getMin();
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getRight() {
        return this.right;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public V getValue() {
        return this.value;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
        this.left.inOrderTraversal(nodeVisitor);
        nodeVisitor.visitEntry(this.key, this.value);
        this.right.inOrderTraversal(nodeVisitor);
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> insert(K k, V v, Comparator<K> comparator) {
        LLRBValueNode<K, V> lLRBValueNodeCopy;
        int iCompare = comparator.compare(k, this.key);
        if (iCompare < 0) {
            lLRBValueNodeCopy = copy(null, null, this.left.insert(k, v, comparator), null);
        } else {
            lLRBValueNodeCopy = iCompare == 0 ? copy(k, v, null, null) : copy(null, null, null, this.right.insert(k, v, comparator));
        }
        return lLRBValueNodeCopy.fixUp();
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isEmpty() {
        return false;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> remove(K k, Comparator<K> comparator) {
        LLRBValueNode<K, V> lLRBValueNodeCopy;
        if (comparator.compare(k, this.key) < 0) {
            LLRBValueNode<K, V> lLRBValueNodeMoveRedLeft = (this.left.isEmpty() || this.left.isRed() || ((LLRBValueNode) this.left).left.isRed()) ? this : moveRedLeft();
            lLRBValueNodeCopy = lLRBValueNodeMoveRedLeft.copy(null, null, lLRBValueNodeMoveRedLeft.left.remove(k, comparator), null);
        } else {
            LLRBValueNode<K, V> lLRBValueNodeRotateRight = this.left.isRed() ? rotateRight() : this;
            if (!lLRBValueNodeRotateRight.right.isEmpty() && !lLRBValueNodeRotateRight.right.isRed() && !((LLRBValueNode) lLRBValueNodeRotateRight.right).left.isRed()) {
                lLRBValueNodeRotateRight = lLRBValueNodeRotateRight.moveRedRight();
            }
            if (comparator.compare(k, lLRBValueNodeRotateRight.key) == 0) {
                if (lLRBValueNodeRotateRight.right.isEmpty()) {
                    return LLRBEmptyNode.INSTANCE;
                }
                LLRBNode<K, V> min = lLRBValueNodeRotateRight.right.getMin();
                lLRBValueNodeRotateRight = lLRBValueNodeRotateRight.copy(min.getKey(), min.getValue(), null, ((LLRBValueNode) lLRBValueNodeRotateRight.right).removeMin());
            }
            lLRBValueNodeCopy = lLRBValueNodeRotateRight.copy(null, null, null, lLRBValueNodeRotateRight.right.remove(k, comparator));
        }
        return lLRBValueNodeCopy.fixUp();
    }

    public void setLeft(LLRBNode<K, V> lLRBNode) {
        this.left = lLRBNode;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean shortCircuitingInOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        if (this.left.shortCircuitingInOrderTraversal(shortCircuitingNodeVisitor) && shortCircuitingNodeVisitor.shouldContinue(this.key, this.value)) {
            return this.right.shortCircuitingInOrderTraversal(shortCircuitingNodeVisitor);
        }
        return false;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean shortCircuitingReverseOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        if (this.right.shortCircuitingReverseOrderTraversal(shortCircuitingNodeVisitor) && shortCircuitingNodeVisitor.shouldContinue(this.key, this.value)) {
            return this.left.shortCircuitingReverseOrderTraversal(shortCircuitingNodeVisitor);
        }
        return false;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBValueNode<K, V> copy(K k, V v, LLRBNode.Color color, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        if (k == null) {
            k = this.key;
        }
        if (v == null) {
            v = this.value;
        }
        if (lLRBNode == null) {
            lLRBNode = this.left;
        }
        if (lLRBNode2 == null) {
            lLRBNode2 = this.right;
        }
        return color == LLRBNode.Color.RED ? new LLRBRedValueNode(k, v, lLRBNode, lLRBNode2) : new LLRBBlackValueNode(k, v, lLRBNode, lLRBNode2);
    }
}
