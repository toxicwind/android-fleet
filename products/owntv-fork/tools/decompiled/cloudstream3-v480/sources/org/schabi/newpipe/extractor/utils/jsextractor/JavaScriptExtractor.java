package org.schabi.newpipe.extractor.utils.jsextractor;

import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public final class JavaScriptExtractor {
    private JavaScriptExtractor() {
    }

    @Nonnull
    public static String matchToClosingBrace(String str, String str2) throws ParsingException {
        int iIndexOf = str.indexOf(str2);
        if (iIndexOf < 0) {
            throw new ParsingException("Start not found");
        }
        String strSubstring = str.substring(iIndexOf + str2.length());
        Lexer lexer = new Lexer(strSubstring);
        boolean z = false;
        while (true) {
            Lexer.ParsedToken nextToken = lexer.getNextToken();
            Token token = nextToken.token;
            if (token == Token.LC) {
                z = true;
            } else {
                if (z && lexer.isBalanced()) {
                    return strSubstring.substring(0, nextToken.end);
                }
                if (token == Token.EOF) {
                    throw new ParsingException("Could not find matching braces");
                }
            }
        }
    }
}
