package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    public static final PatternCompiler patternCompiler = loadPatternCompiler();

    private static final class JdkPatternCompiler implements PatternCompiler {
        public JdkPatternCompiler() {
        }

        @Override // com.google.common.base.PatternCompiler
        public CommonPattern compile(String str) {
            return new JdkPattern(Pattern.compile(str));
        }

        @Override // com.google.common.base.PatternCompiler
        public boolean isPcreLike() {
            return true;
        }

        public /* synthetic */ JdkPatternCompiler(AnonymousClass1 anonymousClass1) {
        }
    }

    public static void checkGwtRpcEnabled() {
    }

    public static CommonPattern compilePattern(String str) {
        if (str != null) {
            return patternCompiler.compile(str);
        }
        throw new NullPointerException();
    }

    public static String emptyToNull(String str) {
        if (stringIsNullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    public static String formatCompact4Digits(double d2) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(d2));
    }

    public static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> cls, String str) {
        WeakReference<? extends Enum<?>> weakReference = Enums.getEnumConstants(cls).get(str);
        return weakReference == null ? Absent.INSTANCE : Optional.of(cls.cast(weakReference.get()));
    }

    public static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler(null);
    }

    public static void logPatternCompilerError(ServiceConfigurationError serviceConfigurationError) {
        logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", (Throwable) serviceConfigurationError);
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static boolean patternCompilerIsPcreLike() {
        return patternCompiler.isPcreLike();
    }

    public static CharMatcher precomputeCharMatcher(CharMatcher charMatcher) {
        return charMatcher.precomputedInternal();
    }

    public static boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static long systemNanoTime() {
        return System.nanoTime();
    }
}
