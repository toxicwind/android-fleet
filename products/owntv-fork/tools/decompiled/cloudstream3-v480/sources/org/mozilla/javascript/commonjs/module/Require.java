package org.mozilla.javascript.commonjs.module;

import com.google.android.gms.cast.MediaTrack;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: loaded from: classes8.dex */
public class Require extends BaseFunction {
    private static final ThreadLocal<Map<String, Scriptable>> loadingModuleInterfaces = new ThreadLocal<>();
    private static final long serialVersionUID = 1;
    private Scriptable mainExports;
    private final ModuleScriptProvider moduleScriptProvider;
    private final Scriptable nativeScope;
    private final Scriptable paths;
    private final Script postExec;
    private final Script preExec;
    private final boolean sandboxed;
    private String mainModuleId = null;
    private final Map<String, Scriptable> exportedModuleInterfaces = new ConcurrentHashMap();
    private final Object loadLock = new Object();

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        return 1;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        return 1;
    }

    public Require(Context context, Scriptable scriptable, ModuleScriptProvider moduleScriptProvider, Script script, Script script2, boolean z) {
        this.moduleScriptProvider = moduleScriptProvider;
        this.nativeScope = scriptable;
        this.sandboxed = z;
        this.preExec = script;
        this.postExec = script2;
        setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
        if (!z) {
            Scriptable scriptableNewArray = context.newArray(scriptable, 0);
            this.paths = scriptableNewArray;
            defineReadOnlyProperty(this, "paths", scriptableNewArray);
            return;
        }
        this.paths = null;
    }

    public Scriptable requireMain(Context context, String str) {
        Require require;
        String str2;
        URI uri;
        String str3 = this.mainModuleId;
        if (str3 != null) {
            if (!str3.equals(str)) {
                throw new IllegalStateException("Main module already set to " + this.mainModuleId);
            }
            return this.mainExports;
        }
        try {
            if (this.moduleScriptProvider.getModuleScript(context, str, null, null, this.paths) != null) {
                require = this;
                Scriptable exportedModuleInterface = require.getExportedModuleInterface(context, str, null, null, true);
                str2 = str;
                require.mainExports = exportedModuleInterface;
            } else {
                require = this;
                str2 = str;
                if (!require.sandboxed) {
                    try {
                        uri = new URI(str2);
                    } catch (URISyntaxException unused) {
                        uri = null;
                    }
                    if (uri == null || !uri.isAbsolute()) {
                        File file = new File(str2);
                        if (!file.isFile()) {
                            throw ScriptRuntime.throwError(context, require.nativeScope, "Module \"" + str2 + "\" not found.");
                        }
                        uri = file.toURI();
                    }
                    URI uri2 = uri;
                    require.mainExports = require.getExportedModuleInterface(context, uri2.toString(), uri2, null, true);
                }
            }
            require.mainModuleId = str2;
            return require.mainExports;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void install(Scriptable scriptable) {
        ScriptableObject.putProperty(scriptable, "require", this);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        URI uri;
        URI uri2;
        if (objArr == null || objArr.length < 1) {
            throw ScriptRuntime.throwError(context, scriptable, "require() needs one argument");
        }
        String string = (String) Context.jsToJava(objArr[0], String.class);
        if (!string.startsWith("./") && !string.startsWith("../")) {
            uri2 = null;
            uri = null;
        } else {
            if (!(scriptable2 instanceof ModuleScope)) {
                throw ScriptRuntime.throwError(context, scriptable, "Can't resolve relative module ID \"" + string + "\" when require() is used outside of a module");
            }
            ModuleScope moduleScope = (ModuleScope) scriptable2;
            URI base = moduleScope.getBase();
            URI uri3 = moduleScope.getUri();
            URI uriResolve = uri3.resolve(string);
            if (base == null) {
                string = uriResolve.toString();
            } else {
                string = base.relativize(uri3).resolve(string).toString();
                if (string.charAt(0) == '.') {
                    if (this.sandboxed) {
                        throw ScriptRuntime.throwError(context, scriptable, "Module \"" + string + "\" is not contained in sandbox.");
                    }
                    string = uriResolve.toString();
                }
            }
            uri = base;
            uri2 = uriResolve;
        }
        return getExportedModuleInterface(context, string, uri2, uri, false);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.throwError(context, scriptable, "require() can not be invoked as a constructor");
    }

    private Scriptable getExportedModuleInterface(Context context, String str, URI uri, URI uri2, boolean z) {
        Scriptable scriptable;
        Scriptable scriptable2 = this.exportedModuleInterfaces.get(str);
        if (scriptable2 != null) {
            if (z) {
                throw new IllegalStateException("Attempt to set main module after it was loaded");
            }
            return scriptable2;
        }
        ThreadLocal<Map<String, Scriptable>> threadLocal = loadingModuleInterfaces;
        Map<String, Scriptable> map = threadLocal.get();
        if (map != null && (scriptable = map.get(str)) != null) {
            return scriptable;
        }
        synchronized (this.loadLock) {
            Scriptable scriptable3 = this.exportedModuleInterfaces.get(str);
            if (scriptable3 != null) {
                return scriptable3;
            }
            ModuleScript module = getModule(context, str, uri, uri2);
            if (this.sandboxed && !module.isSandboxed()) {
                throw ScriptRuntime.throwError(context, this.nativeScope, "Module \"" + str + "\" is not contained in sandbox.");
            }
            Scriptable scriptableNewObject = context.newObject(this.nativeScope);
            boolean z2 = map == null;
            if (z2) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            map.put(str, scriptableNewObject);
            try {
                try {
                    Scriptable scriptableExecuteModuleScript = executeModuleScript(context, str, scriptableNewObject, module, z);
                    if (scriptableNewObject != scriptableExecuteModuleScript) {
                        map.put(str, scriptableExecuteModuleScript);
                        scriptableNewObject = scriptableExecuteModuleScript;
                    }
                    if (z2) {
                        this.exportedModuleInterfaces.putAll(map);
                        threadLocal.set(null);
                    }
                    return scriptableNewObject;
                } catch (RuntimeException e) {
                    map.remove(str);
                    throw e;
                }
            } catch (Throwable th) {
                if (z2) {
                    this.exportedModuleInterfaces.putAll(map);
                    loadingModuleInterfaces.set(null);
                    throw th;
                }
                throw th;
            }
        }
    }

    private Scriptable executeModuleScript(Context context, String str, Scriptable scriptable, ModuleScript moduleScript, boolean z) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(this.nativeScope);
        URI uri = moduleScript.getUri();
        URI base = moduleScript.getBase();
        defineReadOnlyProperty(scriptableObject, "id", str);
        if (!this.sandboxed) {
            defineReadOnlyProperty(scriptableObject, "uri", uri.toString());
        }
        Scriptable moduleScope = new ModuleScope(this.nativeScope, uri, base);
        moduleScope.put("exports", moduleScope, scriptable);
        moduleScope.put("module", moduleScope, scriptableObject);
        scriptableObject.put("exports", scriptableObject, scriptable);
        install(moduleScope);
        if (z) {
            defineReadOnlyProperty(this, MediaTrack.ROLE_MAIN, scriptableObject);
        }
        executeOptionalScript(this.preExec, context, moduleScope);
        moduleScript.getScript().exec(context, moduleScope);
        executeOptionalScript(this.postExec, context, moduleScope);
        return ScriptRuntime.toObject(context, this.nativeScope, ScriptableObject.getProperty(scriptableObject, "exports"));
    }

    private static void executeOptionalScript(Script script, Context context, Scriptable scriptable) {
        if (script != null) {
            script.exec(context, scriptable);
        }
    }

    private static void defineReadOnlyProperty(ScriptableObject scriptableObject, String str, Object obj) {
        ScriptableObject.putProperty(scriptableObject, str, obj);
        scriptableObject.setAttributes(str, 5);
    }

    private ModuleScript getModule(Context context, String str, URI uri, URI uri2) {
        try {
            ModuleScript moduleScript = this.moduleScriptProvider.getModuleScript(context, str, uri, uri2, this.paths);
            if (moduleScript != null) {
                return moduleScript;
            }
            throw ScriptRuntime.throwError(context, this.nativeScope, "Module \"" + str + "\" not found.");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        return "require";
    }
}
