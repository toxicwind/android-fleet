package j$.desugar.sun.nio.fs;

import java.util.regex.PatternSyntaxException;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DesugarGlobs {
    private static char EOL;

    private static boolean isRegexMeta(char c) {
        return ".^$+{[]|()".indexOf(c) != -1;
    }

    private static boolean isGlobMeta(char c) {
        return "\\*?[{".indexOf(c) != -1;
    }

    private static char next(String str, int i) {
        if (i < str.length()) {
            return str.charAt(i);
        }
        return EOL;
    }

    private static String toRegexPattern(String str, boolean z) {
        int i;
        StringBuilder sb = new StringBuilder("^");
        int i2 = 0;
        while (true) {
            boolean z2 = false;
            while (true) {
                if (i2 >= str.length()) {
                    if (z2) {
                        throw new PatternSyntaxException("Missing '}", str, i2 - 1);
                    }
                    sb.append(Typography.dollar);
                    return sb.toString();
                }
                i = i2 + 1;
                char cCharAt = str.charAt(i2);
                if (cCharAt != '*') {
                    if (cCharAt != ',') {
                        if (cCharAt != '/') {
                            if (cCharAt != '?') {
                                if (cCharAt != '{') {
                                    if (cCharAt != '}') {
                                        if (cCharAt == '[') {
                                            if (z) {
                                                sb.append("[[^\\\\]&&[");
                                            } else {
                                                sb.append("[[^/]&&[");
                                            }
                                            if (next(str, i) == '^') {
                                                sb.append("\\^");
                                                i2 += 2;
                                            } else {
                                                if (next(str, i) == '!') {
                                                    sb.append('^');
                                                    i = i2 + 2;
                                                }
                                                if (next(str, i) == '-') {
                                                    sb.append('-');
                                                    i2 = i + 1;
                                                } else {
                                                    i2 = i;
                                                }
                                            }
                                            boolean z3 = false;
                                            char c = 0;
                                            while (i2 < str.length()) {
                                                int i3 = i2 + 1;
                                                char cCharAt2 = str.charAt(i2);
                                                if (cCharAt2 == ']') {
                                                    i2 = i3;
                                                    cCharAt = cCharAt2;
                                                    break;
                                                }
                                                if (cCharAt2 == '/' || (z && cCharAt2 == '\\')) {
                                                    throw new PatternSyntaxException("Explicit 'name separator' in class", str, i2);
                                                }
                                                if (cCharAt2 == '\\' || cCharAt2 == '[' || (cCharAt2 == '&' && next(str, i3) == '&')) {
                                                    sb.append('\\');
                                                }
                                                sb.append(cCharAt2);
                                                if (cCharAt2 != '-') {
                                                    z3 = true;
                                                    i2 = i3;
                                                    cCharAt = cCharAt2;
                                                    c = cCharAt;
                                                } else {
                                                    if (!z3) {
                                                        throw new PatternSyntaxException("Invalid range", str, i2);
                                                    }
                                                    int i4 = i2 + 2;
                                                    cCharAt = next(str, i3);
                                                    if (cCharAt == EOL || cCharAt == ']') {
                                                        i2 = i4;
                                                        break;
                                                    }
                                                    if (cCharAt < c) {
                                                        throw new PatternSyntaxException("Invalid range", str, i2 - 1);
                                                    }
                                                    sb.append(cCharAt);
                                                    i2 = i4;
                                                    z3 = false;
                                                }
                                            }
                                            if (cCharAt != ']') {
                                                throw new PatternSyntaxException("Missing ']", str, i2 - 1);
                                            }
                                            sb.append("]]");
                                        } else if (cCharAt == '\\') {
                                            if (i == str.length()) {
                                                throw new PatternSyntaxException("No character to escape", str, i2);
                                            }
                                            i2 += 2;
                                            char cCharAt3 = str.charAt(i);
                                            if (isGlobMeta(cCharAt3) || isRegexMeta(cCharAt3)) {
                                                sb.append('\\');
                                            }
                                            sb.append(cCharAt3);
                                        } else {
                                            if (isRegexMeta(cCharAt)) {
                                                sb.append('\\');
                                            }
                                            sb.append(cCharAt);
                                        }
                                    } else {
                                        if (z2) {
                                            break;
                                        }
                                        sb.append(AbstractJsonLexerKt.END_OBJ);
                                    }
                                } else {
                                    if (z2) {
                                        throw new PatternSyntaxException("Cannot nest groups", str, i2);
                                    }
                                    sb.append("(?:(?:");
                                    i2 = i;
                                    z2 = true;
                                }
                            } else if (z) {
                                sb.append("[^\\\\]");
                            } else {
                                sb.append("[^/]");
                            }
                        } else if (z) {
                            sb.append("\\\\");
                        } else {
                            sb.append(cCharAt);
                        }
                    } else if (z2) {
                        sb.append(")|(?:");
                    } else {
                        sb.append(AbstractJsonLexerKt.COMMA);
                    }
                    i2 = i;
                } else if (next(str, i) == '*') {
                    sb.append(".*");
                    i2 += 2;
                } else {
                    if (z) {
                        sb.append("[^\\\\]*");
                    } else {
                        sb.append("[^/]*");
                    }
                    i2 = i;
                }
            }
            sb.append("))");
            i2 = i;
        }
    }

    static String toUnixRegexPattern(String str) {
        return toRegexPattern(str, false);
    }
}
