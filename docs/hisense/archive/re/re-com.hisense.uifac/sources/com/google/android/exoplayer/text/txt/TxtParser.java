package com.google.android.exoplayer.text.txt;

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
public class TxtParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encoding;
    private List<TxtSuffixSubtitleParser> parserList;

    public TxtParser() {
        ArrayList arrayList = new ArrayList();
        this.parserList = arrayList;
        arrayList.clear();
        this.parserList.add(new DvdssTypeParser());
        this.parserList.add(new MdvdsubTypeParser());
        this.parserList.add(new TxtTypeParser());
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
        for (TxtSuffixSubtitleParser txtSuffixSubtitleParser : this.parserList) {
            if (txtSuffixSubtitleParser.preParse(byteArrayInputStream, this.encoding)) {
                try {
                    return txtSuffixSubtitleParser.parse(byteArrayInputStream, this.encoding);
                } catch (IOException e) {
                    throw new ParserException(e);
                }
            }
        }
        return new DefaultSubtitle();
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean z = "text/ext/txt".equals(str) || "text/txt".equals(str);
        MtkDebugLogUtil.log("TxtParser", "canParse  type = " + str + " can = " + z, 16);
        return z;
    }
}
