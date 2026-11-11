package org.schabi.newpipe.extractor.linkhandler;

import java.io.Serializable;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class ReadyChannelTabListLinkHandler extends ListLinkHandler {
    private final ChannelTabExtractorBuilder extractorBuilder;

    public interface ChannelTabExtractorBuilder extends Serializable {
        @Nonnull
        ChannelTabExtractor build(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler);
    }

    public ReadyChannelTabListLinkHandler(String str, String str2, @Nonnull String str3, @Nonnull ChannelTabExtractorBuilder channelTabExtractorBuilder) {
        super(str, str, str2, UByte$$ExternalSyntheticBackport0.m(new Object[]{str3}), "");
        this.extractorBuilder = channelTabExtractorBuilder;
    }

    @Nonnull
    public ChannelTabExtractor getChannelTabExtractor(@Nonnull StreamingService streamingService) {
        return this.extractorBuilder.build(streamingService, new ListLinkHandler((ListLinkHandler) this));
    }
}
