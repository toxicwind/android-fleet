package com.lagradost.cloudstream3.ui.player;

import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomSubtitleDecoderFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/DelegatingSubtitleDecoder;", "Landroidx/media3/extractor/text/SimpleSubtitleDecoder;", "name", "", "parser", "Landroidx/media3/extractor/text/SubtitleParser;", "<init>", "(Ljava/lang/String;Landroidx/media3/extractor/text/SubtitleParser;)V", "decode", "Landroidx/media3/extractor/text/Subtitle;", "data", "", "length", "", "reset", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DelegatingSubtitleDecoder extends SimpleSubtitleDecoder {
    private final SubtitleParser parser;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelegatingSubtitleDecoder(String name, SubtitleParser parser) {
        super(name);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.parser = parser;
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] data, int length, boolean reset) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (reset) {
            this.parser.reset();
        }
        Subtitle toLegacySubtitle = this.parser.parseToLegacySubtitle(data, 0, length);
        Intrinsics.checkNotNullExpressionValue(toLegacySubtitle, "parseToLegacySubtitle(...)");
        return toLegacySubtitle;
    }
}
