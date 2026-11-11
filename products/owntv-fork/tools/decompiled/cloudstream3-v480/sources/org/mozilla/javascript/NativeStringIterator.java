package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public final class NativeStringIterator extends ES6Iterator {
    private static final String ITERATOR_TAG = "StringIterator";
    private static final long serialVersionUID = 1;
    private int index;
    private String string;

    static void init(ScriptableObject scriptableObject, boolean z) {
        ES6Iterator.init(scriptableObject, z, new NativeStringIterator(), ITERATOR_TAG);
    }

    private NativeStringIterator() {
    }

    NativeStringIterator(Scriptable scriptable, Object obj) {
        super(scriptable, ITERATOR_TAG);
        this.index = 0;
        this.string = ScriptRuntime.toString(obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "String Iterator";
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected boolean isDone(Context context, Scriptable scriptable) {
        return this.index >= this.string.length();
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected Object nextValue(Context context, Scriptable scriptable) {
        int iOffsetByCodePoints = this.string.offsetByCodePoints(this.index, 1);
        String strSubstring = this.string.substring(this.index, iOffsetByCodePoints);
        this.index = iOffsetByCodePoints;
        return strSubstring;
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected String getTag() {
        return ITERATOR_TAG;
    }
}
