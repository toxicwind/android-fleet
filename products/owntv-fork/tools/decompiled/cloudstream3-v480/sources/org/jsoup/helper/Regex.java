package org.jsoup.helper;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.jsoup.internal.SharedConstants;

/* JADX INFO: loaded from: classes8.dex */
public class Regex {
    private static final boolean hasRe2j = hasRe2j();
    private final Pattern jdkPattern;

    public interface Matcher {
        boolean find();
    }

    Regex(Pattern pattern) {
        this.jdkPattern = pattern;
    }

    public static Regex compile(String str) {
        if (usingRe2j()) {
            return Re2jRegex.compile(str);
        }
        try {
            return new Regex(Pattern.compile(str));
        } catch (PatternSyntaxException e) {
            throw new ValidationException("Pattern syntax error: " + e.getMessage());
        }
    }

    public static Regex fromPattern(Pattern pattern) {
        return new Regex(pattern);
    }

    public static boolean usingRe2j() {
        return hasRe2j && wantsRe2j();
    }

    static boolean wantsRe2j() {
        return Boolean.parseBoolean(System.getProperty(SharedConstants.UseRe2j, "true"));
    }

    static void wantsRe2j(boolean z) {
        System.setProperty(SharedConstants.UseRe2j, Boolean.toString(z));
    }

    static boolean hasRe2j() {
        try {
            try {
                Class<?> cls = Class.forName("com.google.re2j.Pattern", false, Regex.class.getClassLoader());
                try {
                    Class<?> cls2 = Class.forName("java.lang.Module");
                    Method method = Class.class.getMethod("getModule", null);
                    Object objInvoke = method.invoke(Regex.class, null);
                    Object objInvoke2 = method.invoke(cls, null);
                    if (!((Boolean) cls2.getMethod("canRead", cls2).invoke(objInvoke, objInvoke2)).booleanValue()) {
                        cls2.getMethod("addReads", cls2).invoke(objInvoke, objInvoke2);
                    }
                } catch (ClassNotFoundException unused) {
                }
                return true;
            } catch (ReflectiveOperationException e) {
                System.err.println("Warning: (bug? please report) couldn't access re2j from jsoup due to modules: " + e);
                return false;
            }
        } catch (ClassNotFoundException unused2) {
            return false;
        }
    }

    public Matcher matcher(CharSequence charSequence) {
        return new JdkMatcher(this.jdkPattern.matcher(charSequence));
    }

    public String toString() {
        return this.jdkPattern.toString();
    }

    private static final class JdkMatcher implements Matcher {
        private final java.util.regex.Matcher delegate;

        JdkMatcher(java.util.regex.Matcher matcher) {
            this.delegate = matcher;
        }

        @Override // org.jsoup.helper.Regex.Matcher
        public boolean find() {
            return this.delegate.find();
        }
    }
}
