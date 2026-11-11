package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import java.io.Serializable;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;
import org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class YoutubeChannelExtractor$$ExternalSyntheticLambda5 implements ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder, Serializable {
    public final /* synthetic */ JsonObject f$0;
    public final /* synthetic */ YoutubeChannelHelper.ChannelHeader f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ YoutubeChannelExtractor$$ExternalSyntheticLambda5(JsonObject jsonObject, YoutubeChannelHelper.ChannelHeader channelHeader, String str, String str2, String str3) {
        this.f$0 = jsonObject;
        this.f$1 = channelHeader;
        this.f$2 = str;
        this.f$3 = str2;
        this.f$4 = str3;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder
    public final ChannelTabExtractor build(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        return YoutubeChannelExtractor.lambda$getTabsForNonAgeRestrictedChannels$3bf8a84$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, streamingService, listLinkHandler);
    }
}
