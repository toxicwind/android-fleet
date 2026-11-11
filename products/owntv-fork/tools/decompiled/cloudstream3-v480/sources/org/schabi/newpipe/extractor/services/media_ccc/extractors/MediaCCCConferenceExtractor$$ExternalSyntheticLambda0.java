package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonObject;
import java.io.Serializable;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class MediaCCCConferenceExtractor$$ExternalSyntheticLambda0 implements ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder, Serializable {
    public final /* synthetic */ JsonObject f$0;

    public /* synthetic */ MediaCCCConferenceExtractor$$ExternalSyntheticLambda0(JsonObject jsonObject) {
        this.f$0 = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder
    public final ChannelTabExtractor build(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        return MediaCCCConferenceExtractor.lambda$getTabs$91397d47$1(this.f$0, streamingService, listLinkHandler);
    }
}
