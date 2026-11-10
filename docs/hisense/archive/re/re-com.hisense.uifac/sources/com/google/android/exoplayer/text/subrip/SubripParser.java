package com.google.android.exoplayer.text.subrip;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.LongArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.ParserUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class SubripParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private String encoding;
    private final StringBuilder textBuilder = new StringBuilder();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        Log.d("SubripParser", "subrip canParse" + "application/x-subrip".equals(str));
        return "application/x-subrip".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public SubripSubtitle parse(byte[] bArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i + i2);
        parsableByteArray.setPosition(i);
        if (this.encoding == null) {
            this.encoding = ParserUtil.getCharset(bArr, i, i2);
        }
        parsableByteArray.setCharset(Charset.forName(this.encoding));
        while (true) {
            String line = parsableByteArray.readLine();
            if (line != null) {
                if (line.length() != 0) {
                    try {
                        Integer.parseInt(line);
                        String line2 = parsableByteArray.readLine();
                        boolean z = true;
                        Assertions.checkState(line2 != null);
                        Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                        if (matcher.matches()) {
                            longArray.add(parseTimecode(matcher, 1));
                            if (TextUtils.isEmpty(matcher.group(6))) {
                                z = false;
                            } else {
                                longArray.add(parseTimecode(matcher, 6));
                            }
                            this.textBuilder.setLength(0);
                            while (true) {
                                String line3 = parsableByteArray.readLine();
                                if (TextUtils.isEmpty(line3)) {
                                    break;
                                }
                                if (this.textBuilder.length() > 0) {
                                    this.textBuilder.append("<br>");
                                }
                                this.textBuilder.append(line3.trim());
                            }
                            arrayList.add(new Cue(Html.fromHtml(this.textBuilder.toString())));
                            if (z) {
                                arrayList.add(null);
                            }
                        } else {
                            Log.w("SubripParser", "Skipping invalid timing: " + line2);
                        }
                    } catch (NumberFormatException unused) {
                        Log.w("SubripParser", "Skipping invalid index: " + line);
                    }
                }
            } else {
                Cue[] cueArr = new Cue[arrayList.size()];
                arrayList.toArray(cueArr);
                return new SubripSubtitle(cueArr, longArray.toArray());
            }
        }
    }

    private static long parseTimecode(Matcher matcher, int i) {
        return ((Long.parseLong(matcher.group(i + 1)) * 60 * 60 * 1000) + (Long.parseLong(matcher.group(i + 2)) * 60 * 1000) + (Long.parseLong(matcher.group(i + 3)) * 1000) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }
}
