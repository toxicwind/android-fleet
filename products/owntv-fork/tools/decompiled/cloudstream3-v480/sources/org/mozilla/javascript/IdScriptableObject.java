package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public abstract class IdScriptableObject extends ScriptableObject implements IdFunctionCall {
    private static final long serialVersionUID = -3744239272168621609L;
    private transient PrototypeValues prototypeValues;

    protected static int instanceIdInfo(int i, int i2) {
        return (i << 16) | i2;
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
    }

    protected int findInstanceIdInfo(String str) {
        return 0;
    }

    protected int findInstanceIdInfo(Symbol symbol) {
        return 0;
    }

    protected int findPrototypeId(Symbol symbol) {
        return 0;
    }

    protected int getMaxInstanceId() {
        return 0;
    }

    private static final class PrototypeValues implements Serializable {
        private static final int NAME_SLOT = 1;
        private static final int SLOT_SPAN = 2;
        private static final long serialVersionUID = 3038645279153854371L;
        private short[] attributeArray;
        private IdFunctionObject constructor;
        private short constructorAttrs;
        int constructorId;
        private int maxId;
        private IdScriptableObject obj;
        private Object[] valueArray;

        PrototypeValues(IdScriptableObject idScriptableObject, int i) {
            if (idScriptableObject == null) {
                throw new IllegalArgumentException();
            }
            if (i < 1) {
                throw new IllegalArgumentException();
            }
            this.obj = idScriptableObject;
            this.maxId = i;
        }

        final int getMaxId() {
            return this.maxId;
        }

        final void initValue(int i, String str, Object obj, int i2) {
            if (1 > i || i > this.maxId) {
                throw new IllegalArgumentException();
            }
            if (str == null) {
                throw new IllegalArgumentException();
            }
            if (obj == Scriptable.NOT_FOUND) {
                throw new IllegalArgumentException();
            }
            ScriptableObject.checkValidAttributes(i2);
            if (this.obj.findPrototypeId(str) != i) {
                throw new IllegalArgumentException(str);
            }
            if (i == this.constructorId) {
                if (!(obj instanceof IdFunctionObject)) {
                    throw new IllegalArgumentException("constructor should be initialized with IdFunctionObject");
                }
                this.constructor = (IdFunctionObject) obj;
                this.constructorAttrs = (short) i2;
                return;
            }
            initSlot(i, str, obj, i2);
        }

        final void initValue(int i, Symbol symbol, Object obj, int i2) {
            if (1 > i || i > this.maxId) {
                throw new IllegalArgumentException();
            }
            if (symbol == null) {
                throw new IllegalArgumentException();
            }
            if (obj == Scriptable.NOT_FOUND) {
                throw new IllegalArgumentException();
            }
            ScriptableObject.checkValidAttributes(i2);
            if (this.obj.findPrototypeId(symbol) != i) {
                throw new IllegalArgumentException(symbol.toString());
            }
            if (i == this.constructorId) {
                if (!(obj instanceof IdFunctionObject)) {
                    throw new IllegalArgumentException("constructor should be initialized with IdFunctionObject");
                }
                this.constructor = (IdFunctionObject) obj;
                this.constructorAttrs = (short) i2;
                return;
            }
            initSlot(i, symbol, obj, i2);
        }

        private void initSlot(int i, Object obj, Object obj2, int i2) {
            Object[] objArr = this.valueArray;
            if (objArr == null) {
                throw new IllegalStateException();
            }
            if (obj2 == null) {
                obj2 = UniqueTag.NULL_VALUE;
            }
            int i3 = i - 1;
            int i4 = i3 * 2;
            synchronized (this) {
                if (objArr[i4] == null) {
                    objArr[i4] = obj2;
                    objArr[i4 + 1] = obj;
                    this.attributeArray[i3] = (short) i2;
                } else if (!obj.equals(objArr[i4 + 1])) {
                    throw new IllegalStateException();
                }
            }
        }

        final IdFunctionObject createPrecachedConstructor() {
            if (this.constructorId != 0) {
                throw new IllegalStateException();
            }
            int iFindPrototypeId = this.obj.findPrototypeId("constructor");
            this.constructorId = iFindPrototypeId;
            if (iFindPrototypeId == 0) {
                throw new IllegalStateException("No id for constructor property");
            }
            this.obj.initPrototypeId(iFindPrototypeId);
            IdFunctionObject idFunctionObject = this.constructor;
            IdScriptableObject idScriptableObject = this.obj;
            if (idFunctionObject == null) {
                throw new IllegalStateException(idScriptableObject.getClass().getName() + ".initPrototypeId() did not initialize id=" + this.constructorId);
            }
            idFunctionObject.initFunction(idScriptableObject.getClassName(), ScriptableObject.getTopLevelScope(this.obj));
            this.constructor.markAsConstructor(this.obj);
            return this.constructor;
        }

        final int findId(String str) {
            return this.obj.findPrototypeId(str);
        }

        final int findId(Symbol symbol) {
            return this.obj.findPrototypeId(symbol);
        }

        final boolean has(int i) {
            Object obj;
            Object[] objArr = this.valueArray;
            return objArr == null || (obj = objArr[(i - 1) * 2]) == null || obj != Scriptable.NOT_FOUND;
        }

        final Object get(int i) {
            Object objEnsureId = ensureId(i);
            if (objEnsureId == UniqueTag.NULL_VALUE) {
                return null;
            }
            return objEnsureId;
        }

        final void set(int i, Scriptable scriptable, Object obj) {
            if (obj == Scriptable.NOT_FOUND) {
                throw new IllegalArgumentException();
            }
            ensureId(i);
            int i2 = i - 1;
            if ((this.attributeArray[i2] & 1) == 0) {
                if (scriptable == this.obj) {
                    if (obj == null) {
                        obj = UniqueTag.NULL_VALUE;
                    }
                    int i3 = i2 * 2;
                    synchronized (this) {
                        this.valueArray[i3] = obj;
                    }
                    return;
                }
                Object obj2 = this.valueArray[(i2 * 2) + 1];
                if (obj2 instanceof Symbol) {
                    if (scriptable instanceof SymbolScriptable) {
                        ((SymbolScriptable) scriptable).put((Symbol) obj2, scriptable, obj);
                        return;
                    }
                    return;
                }
                scriptable.put((String) obj2, scriptable, obj);
            }
        }

        final void delete(int i) {
            ensureId(i);
            int i2 = i - 1;
            if ((this.attributeArray[i2] & 4) != 0) {
                if (Context.getContext().isStrictMode()) {
                    throw ScriptRuntime.typeErrorById("msg.delete.property.with.configurable.false", (String) this.valueArray[(i2 * 2) + 1]);
                }
            } else {
                int i3 = i2 * 2;
                synchronized (this) {
                    this.valueArray[i3] = Scriptable.NOT_FOUND;
                    this.attributeArray[i2] = 0;
                }
            }
        }

        final int getAttributes(int i) {
            ensureId(i);
            return this.attributeArray[i - 1];
        }

        final void setAttributes(int i, int i2) {
            ScriptableObject.checkValidAttributes(i2);
            ensureId(i);
            synchronized (this) {
                this.attributeArray[i - 1] = (short) i2;
            }
        }

        final Object[] getNames(boolean z, boolean z2, Object[] objArr) {
            int i;
            Object[] objArr2 = null;
            int i2 = 0;
            for (int i3 = 1; i3 <= this.maxId; i3++) {
                Object objEnsureId = ensureId(i3);
                if ((z || (this.attributeArray[i3 - 1] & 2) == 0) && objEnsureId != Scriptable.NOT_FOUND) {
                    Object obj = this.valueArray[((i3 - 1) * 2) + 1];
                    if (obj instanceof String) {
                        if (objArr2 == null) {
                            objArr2 = new Object[this.maxId];
                        }
                        i = i2 + 1;
                        objArr2[i2] = obj;
                    } else if (z2 && (obj instanceof Symbol)) {
                        if (objArr2 == null) {
                            objArr2 = new Object[this.maxId];
                        }
                        i = i2 + 1;
                        objArr2[i2] = obj.toString();
                    }
                    i2 = i;
                }
            }
            if (i2 == 0) {
                return objArr;
            }
            if (objArr == null || objArr.length == 0) {
                if (i2 == objArr2.length) {
                    return objArr2;
                }
                Object[] objArr3 = new Object[i2];
                System.arraycopy(objArr2, 0, objArr3, 0, i2);
                return objArr3;
            }
            int length = objArr.length;
            Object[] objArr4 = new Object[length + i2];
            System.arraycopy(objArr, 0, objArr4, 0, length);
            System.arraycopy(objArr2, 0, objArr4, length, i2);
            return objArr4;
        }

        private Object ensureId(int i) {
            Object[] objArr = this.valueArray;
            synchronized (this) {
                if (objArr == null) {
                    objArr = this.valueArray;
                    if (objArr == null) {
                        int i2 = this.maxId;
                        Object[] objArr2 = new Object[i2 * 2];
                        this.valueArray = objArr2;
                        this.attributeArray = new short[i2];
                        objArr = objArr2;
                    }
                }
            }
            int i3 = (i - 1) * 2;
            Object obj = objArr[i3];
            if (obj != null) {
                return obj;
            }
            int i4 = this.constructorId;
            if (i == i4) {
                initSlot(i4, "constructor", this.constructor, this.constructorAttrs);
                this.constructor = null;
            } else {
                this.obj.initPrototypeId(i);
            }
            Object obj2 = objArr[i3];
            if (obj2 != null) {
                return obj2;
            }
            throw new IllegalStateException(this.obj.getClass().getName() + ".initPrototypeId(int id) did not initialize id=" + i);
        }
    }

    public IdScriptableObject() {
    }

    public IdScriptableObject(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable, scriptable2);
    }

    protected final boolean defaultHas(String str) {
        return super.has(str, this);
    }

    protected final Object defaultGet(String str) {
        return super.get(str, this);
    }

    protected final void defaultPut(String str, Object obj) {
        super.put(str, this, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0) {
            if (((iFindInstanceIdInfo >>> 16) & 4) != 0) {
                return true;
            }
            return NOT_FOUND != getInstanceIdValue(65535 & iFindInstanceIdInfo);
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(str)) != 0) {
            return this.prototypeValues.has(iFindId);
        }
        return super.has(str, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public boolean has(Symbol symbol, Scriptable scriptable) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(symbol);
        if (iFindInstanceIdInfo != 0) {
            if (((iFindInstanceIdInfo >>> 16) & 4) != 0) {
                return true;
            }
            return NOT_FOUND != getInstanceIdValue(65535 & iFindInstanceIdInfo);
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(symbol)) != 0) {
            return this.prototypeValues.has(iFindId);
        }
        return super.has(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        int iFindId;
        Object obj;
        Object instanceIdValue;
        Object obj2 = super.get(str, scriptable);
        if (obj2 != NOT_FOUND) {
            return obj2;
        }
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0 && (instanceIdValue = getInstanceIdValue(iFindInstanceIdInfo & 65535)) != NOT_FOUND) {
            return instanceIdValue;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        return (prototypeValues == null || (iFindId = prototypeValues.findId(str)) == 0 || (obj = this.prototypeValues.get(iFindId)) == NOT_FOUND) ? NOT_FOUND : obj;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public Object get(Symbol symbol, Scriptable scriptable) {
        int iFindId;
        Object obj;
        Object instanceIdValue;
        Object obj2 = super.get(symbol, scriptable);
        if (obj2 != NOT_FOUND) {
            return obj2;
        }
        int iFindInstanceIdInfo = findInstanceIdInfo(symbol);
        if (iFindInstanceIdInfo != 0 && (instanceIdValue = getInstanceIdValue(iFindInstanceIdInfo & 65535)) != NOT_FOUND) {
            return instanceIdValue;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        return (prototypeValues == null || (iFindId = prototypeValues.findId(symbol)) == 0 || (obj = this.prototypeValues.get(iFindId)) == NOT_FOUND) ? NOT_FOUND : obj;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0) {
            if (scriptable == this && isSealed()) {
                throw Context.reportRuntimeErrorById("msg.modify.sealed", str);
            }
            if (((iFindInstanceIdInfo >>> 16) & 1) == 0) {
                if (scriptable == this) {
                    setInstanceIdValue(65535 & iFindInstanceIdInfo, obj);
                    return;
                } else {
                    scriptable.put(str, scriptable, obj);
                    return;
                }
            }
            return;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(str)) != 0) {
            if (scriptable == this && isSealed()) {
                throw Context.reportRuntimeErrorById("msg.modify.sealed", str);
            }
            this.prototypeValues.set(iFindId, scriptable, obj);
            return;
        }
        super.put(str, scriptable, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(symbol);
        if (iFindInstanceIdInfo != 0) {
            if (scriptable == this && isSealed()) {
                throw Context.reportRuntimeErrorById("msg.modify.sealed", new Object[0]);
            }
            if (((iFindInstanceIdInfo >>> 16) & 1) == 0) {
                if (scriptable == this) {
                    setInstanceIdValue(65535 & iFindInstanceIdInfo, obj);
                    return;
                } else {
                    ensureSymbolScriptable(scriptable).put(symbol, scriptable, obj);
                    return;
                }
            }
            return;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(symbol)) != 0) {
            if (scriptable == this && isSealed()) {
                throw Context.reportRuntimeErrorById("msg.modify.sealed", new Object[0]);
            }
            this.prototypeValues.set(iFindId, scriptable, obj);
            return;
        }
        super.put(symbol, scriptable, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(String str) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0 && !isSealed()) {
            if (((iFindInstanceIdInfo >>> 16) & 4) != 0) {
                if (Context.getContext().isStrictMode()) {
                    throw ScriptRuntime.typeErrorById("msg.delete.property.with.configurable.false", str);
                }
                return;
            } else {
                setInstanceIdValue(65535 & iFindInstanceIdInfo, NOT_FOUND);
                return;
            }
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(str)) != 0) {
            if (isSealed()) {
                return;
            }
            this.prototypeValues.delete(iFindId);
            return;
        }
        super.delete(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void delete(Symbol symbol) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(symbol);
        if (iFindInstanceIdInfo != 0 && !isSealed()) {
            if (((iFindInstanceIdInfo >>> 16) & 4) != 0) {
                if (Context.getContext().isStrictMode()) {
                    throw ScriptRuntime.typeErrorById("msg.delete.property.with.configurable.false", new Object[0]);
                }
                return;
            } else {
                setInstanceIdValue(65535 & iFindInstanceIdInfo, NOT_FOUND);
                return;
            }
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(symbol)) != 0) {
            if (isSealed()) {
                return;
            }
            this.prototypeValues.delete(iFindId);
            return;
        }
        super.delete(symbol);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(String str) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0) {
            return iFindInstanceIdInfo >>> 16;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(str)) != 0) {
            return this.prototypeValues.getAttributes(iFindId);
        }
        return super.getAttributes(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(Symbol symbol) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(symbol);
        if (iFindInstanceIdInfo != 0) {
            return iFindInstanceIdInfo >>> 16;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(symbol)) != 0) {
            return this.prototypeValues.getAttributes(iFindId);
        }
        return super.getAttributes(symbol);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public void setAttributes(String str, int i) {
        int iFindId;
        ScriptableObject.checkValidAttributes(i);
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0) {
            int i2 = 65535 & iFindInstanceIdInfo;
            if (i != (iFindInstanceIdInfo >>> 16)) {
                setInstanceIdAttributes(i2, i);
                return;
            }
            return;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null && (iFindId = prototypeValues.findId(str)) != 0) {
            this.prototypeValues.setAttributes(iFindId, i);
        } else {
            super.setAttributes(str, i);
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject
    Object[] getIds(boolean z, boolean z2) {
        Object[] ids = super.getIds(z, z2);
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues != null) {
            ids = prototypeValues.getNames(z, z2, ids);
        }
        int maxInstanceId = getMaxInstanceId();
        if (maxInstanceId != 0) {
            Object[] objArr = null;
            int i = 0;
            while (maxInstanceId != 0) {
                String instanceIdName = getInstanceIdName(maxInstanceId);
                int iFindInstanceIdInfo = findInstanceIdInfo(instanceIdName);
                if (iFindInstanceIdInfo != 0) {
                    int i2 = iFindInstanceIdInfo >>> 16;
                    if (((i2 & 4) != 0 || NOT_FOUND != getInstanceIdValue(maxInstanceId)) && (z || (i2 & 2) == 0)) {
                        if (i == 0) {
                            objArr = new Object[maxInstanceId];
                        }
                        objArr[i] = instanceIdName;
                        i++;
                    }
                }
                maxInstanceId--;
            }
            if (i != 0) {
                if (ids.length == 0 && objArr.length == i) {
                    return objArr;
                }
                Object[] objArr2 = new Object[ids.length + i];
                System.arraycopy(ids, 0, objArr2, 0, ids.length);
                System.arraycopy(objArr, 0, objArr2, ids.length, i);
                return objArr2;
            }
        }
        return ids;
    }

    protected String getInstanceIdName(int i) {
        throw new IllegalArgumentException(String.valueOf(i));
    }

    protected Object getInstanceIdValue(int i) {
        throw new IllegalStateException(String.valueOf(i));
    }

    protected void setInstanceIdValue(int i, Object obj) {
        throw new IllegalStateException(String.valueOf(i));
    }

    protected void setInstanceIdAttributes(int i, int i2) {
        throw ScriptRuntime.constructError("InternalError", "Changing attributes not supported for " + getClassName() + Stream.ID_UNKNOWN + getInstanceIdName(i) + " property");
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        throw idFunctionObject.unknown();
    }

    public final IdFunctionObject exportAsJSClass(int i, Scriptable scriptable, boolean z) {
        if (scriptable != this && scriptable != null) {
            setParentScope(scriptable);
            setPrototype(getObjectPrototype(scriptable));
        }
        activatePrototypeMap(i);
        IdFunctionObject idFunctionObjectCreatePrecachedConstructor = this.prototypeValues.createPrecachedConstructor();
        if (z) {
            sealObject();
        }
        fillConstructorProperties(idFunctionObjectCreatePrecachedConstructor);
        if (z) {
            idFunctionObjectCreatePrecachedConstructor.sealObject();
        }
        idFunctionObjectCreatePrecachedConstructor.exportAsScopeProperty();
        return idFunctionObjectCreatePrecachedConstructor;
    }

    public final boolean hasPrototypeMap() {
        return this.prototypeValues != null;
    }

    public final void activatePrototypeMap(int i) {
        PrototypeValues prototypeValues = new PrototypeValues(this, i);
        synchronized (this) {
            if (this.prototypeValues != null) {
                throw new IllegalStateException();
            }
            this.prototypeValues = prototypeValues;
        }
    }

    public final IdFunctionObject initPrototypeMethod(Object obj, int i, String str, int i2) {
        return initPrototypeMethod(obj, i, str, str, i2);
    }

    public final IdFunctionObject initPrototypeMethod(Object obj, int i, String str, String str2, int i2) {
        IdFunctionObject idFunctionObjectNewIdFunction = newIdFunction(obj, i, str2 != null ? str2 : str, i2, ScriptableObject.getTopLevelScope(this));
        this.prototypeValues.initValue(i, str, idFunctionObjectNewIdFunction, 2);
        return idFunctionObjectNewIdFunction;
    }

    public final IdFunctionObject initPrototypeMethod(Object obj, int i, Symbol symbol, String str, int i2) {
        IdFunctionObject idFunctionObjectNewIdFunction = newIdFunction(obj, i, str, i2, ScriptableObject.getTopLevelScope(this));
        this.prototypeValues.initValue(i, symbol, idFunctionObjectNewIdFunction, 2);
        return idFunctionObjectNewIdFunction;
    }

    public final void initPrototypeConstructor(IdFunctionObject idFunctionObject) {
        int i = this.prototypeValues.constructorId;
        if (i == 0) {
            throw new IllegalStateException();
        }
        if (idFunctionObject.methodId() != i) {
            throw new IllegalArgumentException();
        }
        if (isSealed()) {
            idFunctionObject.sealObject();
        }
        this.prototypeValues.initValue(i, "constructor", idFunctionObject, 2);
    }

    public final void initPrototypeValue(int i, String str, Object obj, int i2) {
        this.prototypeValues.initValue(i, str, obj, i2);
    }

    public final void initPrototypeValue(int i, Symbol symbol, Object obj, int i2) {
        this.prototypeValues.initValue(i, symbol, obj, i2);
    }

    protected void initPrototypeId(int i) {
        throw new IllegalStateException(String.valueOf(i));
    }

    protected int findPrototypeId(String str) {
        throw new IllegalStateException(str);
    }

    protected void addIdFunctionProperty(Scriptable scriptable, Object obj, int i, String str, int i2) {
        newIdFunction(obj, i, str, i2, ScriptableObject.getTopLevelScope(scriptable)).addAsProperty(scriptable);
    }

    protected static <T> T ensureType(Object obj, Class<T> cls, IdFunctionObject idFunctionObject) {
        return (T) ensureType(obj, cls, idFunctionObject.getFunctionName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected static <T> T ensureType(Object obj, Class<T> cls, String str) {
        if (cls.isInstance(obj)) {
            return obj;
        }
        if (obj == 0) {
            throw ScriptRuntime.typeErrorById("msg.incompat.call.details", str, AbstractJsonLexerKt.NULL, cls.getName());
        }
        throw ScriptRuntime.typeErrorById("msg.incompat.call.details", str, obj.getClass().getName(), cls.getName());
    }

    private IdFunctionObject newIdFunction(Object obj, int i, String str, int i2, Scriptable scriptable) {
        IdFunctionObject idFunctionObjectES6;
        if (Context.getContext().getLanguageVersion() < 200) {
            idFunctionObjectES6 = new IdFunctionObject(this, obj, i, str, i2, scriptable);
        } else {
            idFunctionObjectES6 = new IdFunctionObjectES6(this, obj, i, str, i2, scriptable);
        }
        if (isSealed()) {
            idFunctionObjectES6.sealObject();
        }
        return idFunctionObjectES6;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    protected boolean defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        int iFindId;
        if (obj instanceof CharSequence) {
            String string = obj.toString();
            int iFindInstanceIdInfo = findInstanceIdInfo(string);
            if (iFindInstanceIdInfo != 0) {
                int i = 65535 & iFindInstanceIdInfo;
                if (isAccessorDescriptor(scriptableObject)) {
                    delete(i);
                } else {
                    checkPropertyDefinition(scriptableObject);
                    checkPropertyChangeForSlot(string, queryOrFakeSlot(context, obj), scriptableObject);
                    int i2 = iFindInstanceIdInfo >>> 16;
                    Object property = getProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY);
                    if (property != NOT_FOUND && (((i2 & 1) == 0 || (i2 & 4) == 0) && !sameValue(property, getInstanceIdValue(i)))) {
                        setInstanceIdValue(i, property);
                    }
                    setAttributes(string, applyDescriptorToAttributeBitset(i2, getProperty(scriptableObject, "enumerable"), getProperty(scriptableObject, "writable"), getProperty(scriptableObject, "configurable")));
                    return true;
                }
            }
            PrototypeValues prototypeValues = this.prototypeValues;
            if (prototypeValues != null && (iFindId = prototypeValues.findId(string)) != 0) {
                if (isAccessorDescriptor(scriptableObject)) {
                    this.prototypeValues.delete(iFindId);
                } else {
                    checkPropertyDefinition(scriptableObject);
                    checkPropertyChangeForSlot(string, queryOrFakeSlot(context, obj), scriptableObject);
                    int attributes = this.prototypeValues.getAttributes(iFindId);
                    Object property2 = getProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY);
                    if (property2 != NOT_FOUND && (attributes & 1) == 0 && !sameValue(property2, this.prototypeValues.get(iFindId))) {
                        this.prototypeValues.set(iFindId, this, property2);
                    }
                    this.prototypeValues.setAttributes(iFindId, applyDescriptorToAttributeBitset(attributes, getProperty(scriptableObject, "enumerable"), getProperty(scriptableObject, "writable"), getProperty(scriptableObject, "configurable")));
                    if (super.has(string, this)) {
                        super.delete(string);
                    }
                    return true;
                }
            }
        }
        return super.defineOwnProperty(context, obj, scriptableObject, z);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        ScriptableObject ownPropertyDescriptor = super.getOwnPropertyDescriptor(context, obj);
        if (ownPropertyDescriptor == null) {
            if (obj instanceof String) {
                return getBuiltInDataDescriptor((String) obj);
            }
            if (ScriptRuntime.isSymbol(obj)) {
                if (obj instanceof SymbolKey) {
                    return getBuiltInDataDescriptor((SymbolKey) obj);
                }
                return getBuiltInDataDescriptor(((NativeSymbol) obj).getKey());
            }
        }
        return ownPropertyDescriptor;
    }

    private Slot queryOrFakeSlot(Context context, Object obj) {
        Slot slotQuerySlot = querySlot(context, obj);
        if (slotQuerySlot == null) {
            if (obj instanceof String) {
                return getBuiltInSlot((String) obj);
            }
            if (ScriptRuntime.isSymbol(obj)) {
                if (obj instanceof SymbolKey) {
                    return getBuiltInSlot((SymbolKey) obj);
                }
                return getBuiltInSlot(((NativeSymbol) obj).getKey());
            }
        }
        return slotQuerySlot;
    }

    private ScriptableObject getBuiltInDataDescriptor(String str) {
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        Slot builtInSlot = getBuiltInSlot(str);
        if (builtInSlot == null) {
            return null;
        }
        return buildDataDescriptor(parentScope, builtInSlot.value, builtInSlot.getAttributes());
    }

    private Slot getBuiltInSlot(String str) {
        int iFindId;
        int iFindInstanceIdInfo = findInstanceIdInfo(str);
        if (iFindInstanceIdInfo != 0) {
            Object instanceIdValue = getInstanceIdValue(65535 & iFindInstanceIdInfo);
            Slot slot = new Slot(str, 0, iFindInstanceIdInfo >>> 16);
            slot.value = instanceIdValue;
            return slot;
        }
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues == null || (iFindId = prototypeValues.findId(str)) == 0) {
            return null;
        }
        Object obj = this.prototypeValues.get(iFindId);
        Slot slot2 = new Slot(str, 0, this.prototypeValues.getAttributes(iFindId));
        slot2.value = obj;
        return slot2;
    }

    private ScriptableObject getBuiltInDataDescriptor(Symbol symbol) {
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        Slot builtInSlot = getBuiltInSlot(symbol);
        if (builtInSlot == null) {
            return null;
        }
        return buildDataDescriptor(parentScope, builtInSlot.value, builtInSlot.getAttributes());
    }

    private Slot getBuiltInSlot(Symbol symbol) {
        int iFindId;
        PrototypeValues prototypeValues = this.prototypeValues;
        if (prototypeValues == null || (iFindId = prototypeValues.findId(symbol)) == 0) {
            return null;
        }
        Object obj = this.prototypeValues.get(iFindId);
        Slot slot = new Slot(symbol, 0, this.prototypeValues.getAttributes(iFindId));
        slot.value = obj;
        return slot;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        if (i != 0) {
            activatePrototypeMap(i);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        PrototypeValues prototypeValues = this.prototypeValues;
        objectOutputStream.writeInt(prototypeValues != null ? prototypeValues.getMaxId() : 0);
    }
}
