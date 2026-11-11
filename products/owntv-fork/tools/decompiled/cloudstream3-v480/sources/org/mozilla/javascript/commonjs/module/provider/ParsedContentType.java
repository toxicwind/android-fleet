package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes8.dex */
public final class ParsedContentType implements Serializable {
    private static final long serialVersionUID = 1;
    private final String contentType;
    private final String encoding;

    /* JADX WARN: Code duplicated, block: B:20:0x005e  */
    public ParsedContentType(String str) {
        String str2;
        String strTrim = null;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
            if (stringTokenizer.hasMoreTokens()) {
                String strTrim2 = stringTokenizer.nextToken().trim();
                while (stringTokenizer.hasMoreTokens()) {
                    String strTrim3 = stringTokenizer.nextToken().trim();
                    if (strTrim3.startsWith("charset=")) {
                        strTrim = strTrim3.substring(8).trim();
                        int length = strTrim.length();
                        if (length <= 0) {
                            break;
                        }
                        strTrim = strTrim.charAt(0) == '\"' ? strTrim.substring(1) : strTrim;
                        int i = length - 1;
                        if (strTrim.charAt(i) != '\"') {
                            break;
                        }
                        strTrim = strTrim.substring(0, i);
                        break;
                    }
                }
                String str3 = strTrim;
                strTrim = strTrim2;
                str2 = str3;
            } else {
                str2 = null;
            }
        } else {
            str2 = null;
        }
        this.contentType = strTrim;
        this.encoding = str2;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEncoding() {
        return this.encoding;
    }
}
