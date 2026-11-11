package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class Synchronizer extends Delegator {
    private Object syncObject;

    public Synchronizer(Scriptable scriptable) {
        super(scriptable);
    }

    public Synchronizer(Scriptable scriptable, Object obj) {
        super(scriptable);
        this.syncObject = obj;
    }

    @Override // org.mozilla.javascript.Delegator, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object objCall;
        Object objUnwrap = this.syncObject;
        if (objUnwrap == null) {
            objUnwrap = scriptable2;
        }
        if (objUnwrap instanceof Wrapper) {
            objUnwrap = ((Wrapper) objUnwrap).unwrap();
        }
        synchronized (objUnwrap) {
            objCall = ((Function) this.obj).call(context, scriptable, scriptable2, objArr);
        }
        return objCall;
    }
}
