package com.google.android.exoplayer.text.sub;

import com.google.android.exoplayer.text.Subtitle;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public interface SubSuffixSubtitleParser {
    Subtitle parse(InputStream inputStream, String str) throws IOException;

    boolean preParse(InputStream inputStream, String str);
}
