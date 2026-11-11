package org.schabi.newpipe.extractor.utils;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: loaded from: classes8.dex */
public final class JavaScript {
    private JavaScript() {
    }

    public static void compileOrThrow(String str) {
        Context contextEnter = Context.enter();
        try {
            contextEnter.setInterpretedMode(true);
            contextEnter.compileString(str, null, 1, null);
            if (contextEnter != null) {
                contextEnter.close();
            }
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

    public static String run(String str, String str2, String... strArr) {
        Context contextEnter = Context.enter();
        try {
            contextEnter.setInterpretedMode(true);
            ScriptableObject scriptableObjectInitSafeStandardObjects = contextEnter.initSafeStandardObjects();
            contextEnter.evaluateString(scriptableObjectInitSafeStandardObjects, str, str2, 1, null);
            String string = ((Function) scriptableObjectInitSafeStandardObjects.get(str2, scriptableObjectInitSafeStandardObjects)).call(contextEnter, scriptableObjectInitSafeStandardObjects, scriptableObjectInitSafeStandardObjects, strArr).toString();
            if (contextEnter != null) {
                contextEnter.close();
            }
            return string;
        } catch (Throwable th) {
            if (contextEnter == null) {
                throw th;
            }
            try {
                contextEnter.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }
}
