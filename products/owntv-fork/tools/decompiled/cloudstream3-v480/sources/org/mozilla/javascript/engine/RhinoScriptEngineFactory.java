package org.mozilla.javascript.engine;

import androidx.core.os.EnvironmentCompat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.Context;

/* JADX INFO: loaded from: classes8.dex */
public class RhinoScriptEngineFactory implements ScriptEngineFactory {
    public static final String NAME = "rhino";
    private static final String LANGUAGE = "javascript";
    private static final List<String> NAMES = Arrays.asList(NAME, "Rhino", LANGUAGE, "JavaScript");
    private static final List<String> EXTENSIONS = Collections.singletonList("js");
    private static final List<String> MIME_TYPES = Arrays.asList("application/javascript", "application/ecmascript", "text/javascript", "text/ecmascript");
    private static final String LANGUAGE_VERSION = String.valueOf(200);

    public String getEngineName() {
        return NAME;
    }

    public String getEngineVersion() {
        Context contextEnter = Context.enter();
        try {
            String implementationVersion = contextEnter.getImplementationVersion();
            if (implementationVersion == null) {
                implementationVersion = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            if (contextEnter != null) {
                contextEnter.close();
            }
            return implementationVersion;
        } catch (Throwable th) {
            if (contextEnter != null) {
                try {
                    contextEnter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public List<String> getExtensions() {
        return EXTENSIONS;
    }

    public List<String> getMimeTypes() {
        return MIME_TYPES;
    }

    public List<String> getNames() {
        return NAMES;
    }

    public String getLanguageName() {
        return LANGUAGE;
    }

    public String getLanguageVersion() {
        return LANGUAGE_VERSION;
    }

    public Object getParameter(String str) {
        str.hashCode();
        switch (str) {
            case "javax.script.engine_version":
                return getEngineVersion();
            case "javax.script.engine":
                return getEngineName();
            case "javax.script.language":
                return getLanguageName();
            case "javax.script.language_version":
                return getLanguageVersion();
            case "javax.script.name":
                return NAME;
            default:
                return null;
        }
    }

    public String getMethodCallSyntax(String str, String str2, String... strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('.');
        sb.append(str2);
        sb.append('(');
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(strArr[i]);
        }
        sb.append(");");
        return sb.toString();
    }

    public String getOutputStatement(String str) {
        return "print('" + str + "');";
    }

    public String getProgram(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(";\n");
        }
        return sb.toString();
    }

    public ScriptEngine getScriptEngine() {
        return new RhinoScriptEngine(this);
    }
}
