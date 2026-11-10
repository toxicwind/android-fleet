package com.google.android.exoplayer.text.webvtt;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.ParserUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class WebvttParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encoding;
    private final WebvttCueParser cueParser = new WebvttCueParser();
    private final ParsableByteArray parsableWebvttData = new ParsableByteArray();
    private final WebvttCue.Builder webvttCueBuilder = new WebvttCue.Builder();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public final boolean canParse(String str) {
        Log.d("WebvttParser", "vtt canParse" + "text/vtt".equals(str));
        return "text/vtt".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public final WebvttSubtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        if (this.encoding == null) {
            this.encoding = ParserUtil.getCharset(bArr, i, i2);
        }
        this.parsableWebvttData.setCharset(Charset.forName(this.encoding));
        this.parsableWebvttData.reset(bArr, i2 + i);
        this.parsableWebvttData.setPosition(i);
        this.webvttCueBuilder.reset();
        WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
        while (!TextUtils.isEmpty(this.parsableWebvttData.readLine())) {
        }
        ArrayList arrayList = new ArrayList();
        while (this.cueParser.parseNextValidCue(this.parsableWebvttData, this.webvttCueBuilder)) {
            arrayList.add(this.webvttCueBuilder.build());
            this.webvttCueBuilder.reset();
        }
        return new WebvttSubtitle(arrayList);
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }
}
