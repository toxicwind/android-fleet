package com.google.android.exoplayer.text.webvtt;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class WebvttParserUtil {
    private static final Pattern HEADER = Pattern.compile("^\ufeff?WEBVTT(( |\t).*)?$");

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) throws ParserException {
        String line = parsableByteArray.readLine();
        if (line == null || !HEADER.matcher(line).matches()) {
            throw new ParserException("Expected WEBVTT. Got " + line);
        }
    }

    public static long parseTimestampUs(String str) throws NumberFormatException {
        String[] strArrSplit = str.split("\\.", 2);
        long j = 0;
        for (String str2 : strArrSplit[0].split(":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        return ((j * 1000) + Long.parseLong(strArrSplit[1])) * 1000;
    }

    public static float parsePercentage(String str) throws NumberFormatException {
        if (!str.endsWith("%")) {
            throw new NumberFormatException("Percentages must end with %");
        }
        return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
    }
}
