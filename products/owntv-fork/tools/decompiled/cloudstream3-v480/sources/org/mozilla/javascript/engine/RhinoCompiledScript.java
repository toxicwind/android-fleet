package org.mozilla.javascript.engine;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import org.mozilla.javascript.Script;

/* JADX INFO: loaded from: classes8.dex */
public class RhinoCompiledScript extends CompiledScript {
    private final RhinoScriptEngine engine;
    private final Script script;

    RhinoCompiledScript(RhinoScriptEngine rhinoScriptEngine, Script script) {
        this.engine = rhinoScriptEngine;
        this.script = script;
    }

    public Object eval(ScriptContext scriptContext) throws ScriptException {
        return this.engine.eval(this.script, scriptContext);
    }

    public ScriptEngine getEngine() {
        return this.engine;
    }
}
