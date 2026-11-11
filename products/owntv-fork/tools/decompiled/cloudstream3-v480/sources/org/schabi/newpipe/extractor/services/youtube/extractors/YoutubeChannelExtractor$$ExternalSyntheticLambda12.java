package org.schabi.newpipe.extractor.services.youtube.extractors;

import java.io.Serializable;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class YoutubeChannelExtractor$$ExternalSyntheticLambda12 implements ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder, Serializable {
    @Override // org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder
    public final ChannelTabExtractor build(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        return new YoutubeChannelTabPlaylistExtractor(streamingService, listLinkHandler);
    }
}
