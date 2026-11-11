package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class IdFunctionObjectES6 extends IdFunctionObject {
    private static final int Id_length = 1;
    private static final int Id_name = 3;
    private static final long serialVersionUID = -8023088662589035261L;
    private boolean myLength;
    private boolean myName;

    public IdFunctionObjectES6(IdFunctionCall idFunctionCall, Object obj, int i, String str, int i2, Scriptable scriptable) {
        super(idFunctionCall, obj, i, str, i2, scriptable);
        this.myLength = true;
        this.myName = true;
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        if (str.equals("length")) {
            return instanceIdInfo(3, 1);
        }
        return str.equals("name") ? instanceIdInfo(3, 3) : super.findInstanceIdInfo(str);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        if (i == 1 && !this.myLength) {
            return NOT_FOUND;
        }
        if (i == 3 && !this.myName) {
            return NOT_FOUND;
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        if (i == 1 && obj == NOT_FOUND) {
            this.myLength = false;
        } else if (i == 3 && obj == NOT_FOUND) {
            this.myName = false;
        } else {
            super.setInstanceIdValue(i, obj);
        }
    }
}
