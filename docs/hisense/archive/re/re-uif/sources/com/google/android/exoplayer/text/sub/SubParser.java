package com.google.android.exoplayer.text.sub;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SubParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encoding;
    private int mFrameRate = 30;
    private List<SubSuffixSubtitleParser> parserList;

    public SubParser() {
        ArrayList arrayList = new ArrayList();
        this.parserList = arrayList;
        arrayList.clear();
        this.parserList.add(new MdvdsubTypeParser());
        this.parserList.add(new SubTypeParser());
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        if (this.encoding == null) {
            this.encoding = ParserUtil.getCharset(bArr, i, i2);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        for (SubSuffixSubtitleParser subSuffixSubtitleParser : this.parserList) {
            if (subSuffixSubtitleParser.preParse(byteArrayInputStream, this.encoding)) {
                if (subSuffixSubtitleParser instanceof MdvdsubTypeParser) {
                    ((MdvdsubTypeParser) subSuffixSubtitleParser).setFrameRate(this.mFrameRate);
                }
                try {
                    return subSuffixSubtitleParser.parse(byteArrayInputStream, this.encoding);
                } catch (IOException e) {
                    throw new ParserException(e);
                }
            }
        }
        return new DefaultSubtitle();
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean z = "text/ext/sub".equals(str) || "text/sub".equals(str);
        MtkDebugLogUtil.log("SubParser", "canParse  type = " + str + " can = " + z, 16);
        return z;
    }

    public void setFrameRate(int i) {
        this.mFrameRate = i;
        Log.d("SubParser", "setFrameRate,mFrameRate==" + this.mFrameRate);
    }
}
