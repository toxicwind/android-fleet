package com.google.firebase.database.collection;

/* JADX INFO: loaded from: classes.dex */
public class LLRBRedValueNode<K, V> extends LLRBValueNode<K, V> {
    /* JADX WARN: Illegal instructions before constructor call */
    public LLRBRedValueNode(K k, V v) {
        LLRBEmptyNode lLRBEmptyNode = LLRBEmptyNode.INSTANCE;
        super(k, v, lLRBEmptyNode, lLRBEmptyNode);
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    public LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        if (k == null) {
            k = getKey();
        }
        if (v == null) {
            v = getValue();
        }
        if (lLRBNode == null) {
            lLRBNode = getLeft();
        }
        if (lLRBNode2 == null) {
            lLRBNode2 = getRight();
        }
        return new LLRBRedValueNode(k, v, lLRBNode, lLRBNode2);
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    public LLRBNode.Color getColor() {
        return LLRBNode.Color.RED;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isRed() {
        return true;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public int size() {
        return getRight().size() + getLeft().size() + 1;
    }

    public LLRBRedValueNode(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        super(k, v, lLRBNode, lLRBNode2);
    }
}
