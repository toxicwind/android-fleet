package org.mozilla.javascript;

import androidx.collection.SieveCacheKt;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class NativeJavaList extends NativeJavaObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 660285467829047519L;
    private List<Object> list;

    public NativeJavaList(Scriptable scriptable, Object obj) {
        super(scriptable, obj, obj.getClass());
        this.list = (List) obj;
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "JavaList";
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        if (str.equals("length")) {
            return true;
        }
        return super.has(str, scriptable);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (isWithValidIndex(i)) {
            return true;
        }
        return super.has(i, scriptable);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
        if (isWithValidIndex(i)) {
            this.list.set(i, null);
        }
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.SymbolScriptable
    public boolean has(Symbol symbol, Scriptable scriptable) {
        if (SymbolKey.IS_CONCAT_SPREADABLE.equals(symbol)) {
            return true;
        }
        return super.has(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        if ("length".equals(str)) {
            return Integer.valueOf(this.list.size());
        }
        return super.get(str, scriptable);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        if (isWithValidIndex(i)) {
            Context currentContext = Context.getCurrentContext();
            Object obj = this.list.get(i);
            if (currentContext != null) {
                return currentContext.getWrapFactory().wrap(currentContext, this, obj, obj == null ? null : obj.getClass());
            }
            return obj;
        }
        return Undefined.instance;
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.SymbolScriptable
    public Object get(Symbol symbol, Scriptable scriptable) {
        if (SymbolKey.IS_CONCAT_SPREADABLE.equals(symbol)) {
            return Boolean.TRUE;
        }
        return super.get(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (i >= 0) {
            Object objJsToJava = Context.jsToJava(obj, Object.class);
            if (i == this.list.size()) {
                this.list.add(objJsToJava);
                return;
            } else {
                ensureCapacity(i + 1);
                this.list.set(i, objJsToJava);
                return;
            }
        }
        super.put(i, scriptable, obj);
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        if (this.list != null && "length".equals(str)) {
            setLength(obj);
        } else {
            super.put(str, scriptable, obj);
        }
    }

    private void ensureCapacity(int i) {
        if (i > this.list.size()) {
            List<Object> list = this.list;
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(i);
            }
            while (i > this.list.size()) {
                this.list.add(null);
            }
        }
    }

    private void setLength(Object obj) {
        double number = ScriptRuntime.toNumber(obj);
        long uint32 = ScriptRuntime.toUint32(number);
        if (uint32 != number || uint32 > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.arraylength.bad", new Object[0]));
        }
        if (uint32 < this.list.size()) {
            List<Object> list = this.list;
            list.subList((int) uint32, list.size()).clear();
        } else {
            ensureCapacity((int) uint32);
        }
    }

    @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        List list = (List) this.javaObject;
        Object[] objArr = new Object[list.size()];
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return objArr;
            }
            objArr[size] = Integer.valueOf(size);
        }
    }

    private boolean isWithValidIndex(int i) {
        return i >= 0 && i < this.list.size();
    }

    @Override // org.mozilla.javascript.NativeJavaObject
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // org.mozilla.javascript.NativeJavaObject
    public int hashCode() {
        return super.hashCode();
    }
}
