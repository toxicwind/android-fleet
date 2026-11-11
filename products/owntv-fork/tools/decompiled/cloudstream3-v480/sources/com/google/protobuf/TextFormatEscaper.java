package com.google.protobuf;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes6.dex */
final class TextFormatEscaper {
    private TextFormatEscaper() {
    }

    static String escapeBytes(byte[] input) {
        StringBuilder sb = new StringBuilder(input.length);
        for (byte b : input) {
            if (b == 34) {
                sb.append("\\\"");
            } else if (b == 39) {
                sb.append("\\'");
            } else if (b != 92) {
                switch (b) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (b >= 32 && b <= 126) {
                            sb.append((char) b);
                        } else {
                            sb.append('\\');
                            sb.append((char) (((b >>> 6) & 3) + 48));
                            sb.append((char) (((b >>> 3) & 7) + 48));
                            sb.append((char) ((b & 7) + 48));
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static String escapeBytes(final ByteString input) {
        return escapeBytes(input.toByteArray());
    }

    static String escapeText(String input) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < input.length(); i++) {
            char cCharAt = input.charAt(i);
            if (cCharAt < ' ' || cCharAt > '~') {
                return escapeBytes(input.getBytes(StandardCharsets.UTF_8));
            }
            if (cCharAt == '\"') {
                z3 = true;
            } else if (cCharAt == '\'') {
                z2 = true;
            } else if (cCharAt == '\\') {
                z = true;
            }
        }
        if (z) {
            input = input.replace("\\", "\\\\");
        }
        if (z2) {
            input = input.replace("'", "\\'");
        }
        return z3 ? input.replace("\"", "\\\"") : input;
    }

    static String escapeDoubleQuotesAndBackslashes(String input) {
        return input.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
