package org.mozilla.javascript.engine;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.script.ScriptContext;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: loaded from: classes8.dex */
public class Builtins {
    static final Object BUILTIN_KEY = new Object();
    private Writer stdout;

    void register(Context context, ScriptableObject scriptableObject, ScriptContext scriptContext) {
        if (scriptContext.getWriter() == null) {
            this.stdout = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
        } else {
            this.stdout = scriptContext.getWriter();
        }
        scriptableObject.defineFunctionProperties(new String[]{"print"}, Builtins.class, 6);
    }

    public static void print(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        Builtins self = getSelf(scriptable);
        for (Object obj : objArr) {
            self.stdout.write(ScriptRuntime.toString(obj));
        }
        self.stdout.write(10);
        self.stdout.flush();
    }

    private static Builtins getSelf(Scriptable scriptable) {
        return (Builtins) ScriptableObject.getTopScopeValue(scriptable, BUILTIN_KEY);
    }
}
