package org.mozilla.javascript;

import java.io.Serializable;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes8.dex */
public class ConsString implements CharSequence, Serializable {
    private static final long serialVersionUID = -8432806714471372570L;
    private boolean isFlat;
    private CharSequence left;
    private final int length;
    private CharSequence right;

    public ConsString(CharSequence charSequence, CharSequence charSequence2) {
        if (!(charSequence instanceof String) && !(charSequence instanceof ConsString)) {
            charSequence = charSequence.toString();
        }
        if (!(charSequence2 instanceof String) && !(charSequence2 instanceof ConsString)) {
            charSequence2 = charSequence2.toString();
        }
        this.left = charSequence;
        this.right = charSequence2;
        this.length = charSequence.length() + this.right.length();
        this.isFlat = false;
    }

    private Object writeReplace() {
        return toString();
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.isFlat ? (String) this.left : flatten();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003f  */
    /* JADX WARN: Code duplicated, block: B:15:0x0041 A[Catch: all -> 0x005d, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0015, B:8:0x0019, B:10:0x001f, B:11:0x0022, B:17:0x0049, B:12:0x002a, B:15:0x0041, B:18:0x0057), top: B:24:0x0001 }] */
    private synchronized String flatten() {
        if (!this.isFlat) {
            int length = this.length;
            char[] cArr = new char[length];
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.addFirst(this.left);
            CharSequence charSequence = this.right;
            do {
                if (charSequence instanceof ConsString) {
                    ConsString consString = (ConsString) charSequence;
                    if (consString.isFlat) {
                        charSequence = consString.left;
                        String str = (String) charSequence;
                        length -= str.length();
                        str.getChars(0, str.length(), cArr, length);
                        if (arrayDeque.isEmpty()) {
                            charSequence = null;
                        } else {
                            charSequence = (CharSequence) arrayDeque.removeFirst();
                        }
                    } else {
                        arrayDeque.addFirst(consString.left);
                        charSequence = consString.right;
                    }
                } else {
                    String str2 = (String) charSequence;
                    length -= str2.length();
                    str2.getChars(0, str2.length(), cArr, length);
                    if (arrayDeque.isEmpty()) {
                        charSequence = null;
                    } else {
                        charSequence = (CharSequence) arrayDeque.removeFirst();
                    }
                }
            } while (charSequence != null);
            this.left = new String(cArr);
            this.right = "";
            this.isFlat = true;
        }
        return (String) this.left;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return (this.isFlat ? (String) this.left : flatten()).charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return (this.isFlat ? (String) this.left : flatten()).substring(i, i2);
    }
}
