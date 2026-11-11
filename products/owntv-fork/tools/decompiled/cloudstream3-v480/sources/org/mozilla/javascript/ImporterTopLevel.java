package org.mozilla.javascript;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class ImporterTopLevel extends TopLevel {
    private static final String AKEY = "importedPackages";
    private static final Object IMPORTER_TAG = "Importer";
    private static final int Id_constructor = 1;
    private static final int Id_importClass = 2;
    private static final int Id_importPackage = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final long serialVersionUID = -9095380847465315412L;
    private boolean topScopeFlag;

    public ImporterTopLevel() {
    }

    public ImporterTopLevel(Context context) {
        this(context, false);
    }

    public ImporterTopLevel(Context context, boolean z) {
        initStandardObjects(context, z);
    }

    @Override // org.mozilla.javascript.TopLevel, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return this.topScopeFlag ? "global" : "JavaImporter";
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new ImporterTopLevel().exportAsJSClass(3, scriptable, z);
    }

    public void initStandardObjects(Context context, boolean z) {
        context.initStandardObjects(this, z);
        this.topScopeFlag = true;
        IdFunctionObject idFunctionObjectExportAsJSClass = exportAsJSClass(3, this, false);
        if (z) {
            idFunctionObjectExportAsJSClass.sealObject();
        }
        delete("constructor");
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return super.has(str, scriptable) || getPackageProperty(str, scriptable) != NOT_FOUND;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        Object obj = super.get(str, scriptable);
        return obj != NOT_FOUND ? obj : getPackageProperty(str, scriptable);
    }

    private Object getPackageProperty(String str, Scriptable scriptable) {
        Object obj = NOT_FOUND;
        Object[] nativeJavaPackages = getNativeJavaPackages(this.topScopeFlag ? ScriptableObject.getTopLevelScope(scriptable) : scriptable);
        if (nativeJavaPackages == null) {
            return obj;
        }
        for (Object obj2 : nativeJavaPackages) {
            Object pkgProperty = ((NativeJavaPackage) obj2).getPkgProperty(str, scriptable, false);
            if (pkgProperty != null && !(pkgProperty instanceof NativeJavaPackage)) {
                if (obj != NOT_FOUND) {
                    throw Context.reportRuntimeErrorById("msg.ambig.import", obj.toString(), pkgProperty.toString());
                }
                obj = pkgProperty;
            }
        }
        return obj;
    }

    private static Object[] getNativeJavaPackages(Scriptable scriptable) {
        ArrayList arrayList;
        synchronized (scriptable) {
            if (!(scriptable instanceof ScriptableObject) || (arrayList = (ArrayList) ((ScriptableObject) scriptable).getAssociatedValue(AKEY)) == null) {
                return null;
            }
            return arrayList.toArray();
        }
    }

    @Deprecated
    public void importPackage(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        js_importPackage(this, objArr);
    }

    private Object js_construct(Scriptable scriptable, Object[] objArr) {
        ImporterTopLevel importerTopLevel = new ImporterTopLevel();
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (obj instanceof NativeJavaClass) {
                importClass(importerTopLevel, (NativeJavaClass) obj);
            } else if (obj instanceof NativeJavaPackage) {
                importPackage(importerTopLevel, (NativeJavaPackage) obj);
            } else {
                throw Context.reportRuntimeErrorById("msg.not.class.not.pkg", Context.toString(obj));
            }
        }
        importerTopLevel.setParentScope(scriptable);
        importerTopLevel.setPrototype(this);
        return importerTopLevel;
    }

    private static Object js_importClass(Scriptable scriptable, Object[] objArr) {
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (!(obj instanceof NativeJavaClass)) {
                throw Context.reportRuntimeErrorById("msg.not.class", Context.toString(obj));
            }
            importClass(scriptable, (NativeJavaClass) obj);
        }
        return Undefined.instance;
    }

    private static Object js_importPackage(ScriptableObject scriptableObject, Object[] objArr) {
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (!(obj instanceof NativeJavaPackage)) {
                throw Context.reportRuntimeErrorById("msg.not.pkg", Context.toString(obj));
            }
            importPackage(scriptableObject, (NativeJavaPackage) obj);
        }
        return Undefined.instance;
    }

    private static void importPackage(ScriptableObject scriptableObject, NativeJavaPackage nativeJavaPackage) {
        if (nativeJavaPackage == null) {
            return;
        }
        synchronized (scriptableObject) {
            ArrayList arrayList = (ArrayList) scriptableObject.getAssociatedValue(AKEY);
            if (arrayList == null) {
                arrayList = new ArrayList();
                scriptableObject.associateValue(AKEY, arrayList);
            }
            for (int i = 0; i != arrayList.size(); i++) {
                if (nativeJavaPackage.equals(arrayList.get(i))) {
                    return;
                }
            }
            arrayList.add(nativeJavaPackage);
        }
    }

    private static void importClass(Scriptable scriptable, NativeJavaClass nativeJavaClass) {
        String name = nativeJavaClass.getClassObject().getName();
        String strSubstring = name.substring(name.lastIndexOf(46) + 1);
        Object obj = scriptable.get(strSubstring, scriptable);
        if (obj != NOT_FOUND) {
            if (!obj.equals(nativeJavaClass)) {
                throw Context.reportRuntimeErrorById("msg.prop.defined", strSubstring);
            }
        } else {
            scriptable.put(strSubstring, scriptable, nativeJavaClass);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        if (i == 1) {
            i2 = 0;
            str = "constructor";
        } else if (i == 2) {
            str = "importClass";
        } else if (i == 3) {
            str = "importPackage";
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(IMPORTER_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(IMPORTER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == 1) {
            return js_construct(scriptable, objArr);
        }
        if (iMethodId == 2) {
            return js_importClass(realScope(scriptable, scriptable2, idFunctionObject), objArr);
        }
        if (iMethodId == 3) {
            return js_importPackage(realScope(scriptable, scriptable2, idFunctionObject), objArr);
        }
        throw new IllegalArgumentException(String.valueOf(iMethodId));
    }

    private ScriptableObject realScope(Scriptable scriptable, Scriptable scriptable2, IdFunctionObject idFunctionObject) {
        if (this.topScopeFlag) {
            scriptable2 = ScriptableObject.getTopLevelScope(scriptable);
        }
        return (ScriptableObject) ensureType(scriptable2, ScriptableObject.class, idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "constructor":
                return 1;
            case "importClass":
                return 2;
            case "importPackage":
                return 3;
            default:
                return 0;
        }
    }
}
