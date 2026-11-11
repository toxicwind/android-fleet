package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.schabi.newpipe.extractor.channel.ChannelInfoItem;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemsCollector;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudService;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionItem;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudSubscriptionExtractor extends SubscriptionExtractor {
    public SoundcloudSubscriptionExtractor(SoundcloudService soundcloudService) {
        super(soundcloudService, Collections.singletonList(SubscriptionExtractor.ContentSource.CHANNEL_URL));
    }

    @Override // org.schabi.newpipe.extractor.subscription.SubscriptionExtractor
    public String getRelatedUrl() {
        return "https://soundcloud.com/you";
    }

    @Override // org.schabi.newpipe.extractor.subscription.SubscriptionExtractor
    public List<SubscriptionItem> fromChannelUrl(String str) throws IOException, ExtractionException {
        if (str == null) {
            throw new SubscriptionExtractor.InvalidSourceException("Channel url is null");
        }
        try {
            String id = this.service.getChannelLHFactory().fromUrl(getUrlFrom(str)).getId();
            String str2 = "https://api-v2.soundcloud.com/users/" + id + "/followings?client_id=" + SoundcloudParsingHelper.clientId() + "&limit=200";
            ChannelInfoItemsCollector channelInfoItemsCollector = new ChannelInfoItemsCollector(this.service.getServiceId());
            SoundcloudParsingHelper.getUsersFromApiMinItems(2500, channelInfoItemsCollector, str2);
            return toSubscriptionItems(channelInfoItemsCollector.getItems());
        } catch (ExtractionException e) {
            throw new SubscriptionExtractor.InvalidSourceException(e);
        }
    }

    private String getUrlFrom(String str) {
        String strReplaceHttpWithHttps = Utils.replaceHttpWithHttps(str);
        if (strReplaceHttpWithHttps.startsWith(Utils.HTTPS)) {
            return str;
        }
        if (!strReplaceHttpWithHttps.contains("soundcloud.com/")) {
            return "https://soundcloud.com/" + strReplaceHttpWithHttps;
        }
        return Utils.HTTPS + strReplaceHttpWithHttps;
    }

    private List<SubscriptionItem> toSubscriptionItems(List<ChannelInfoItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ChannelInfoItem channelInfoItem : list) {
            arrayList.add(new SubscriptionItem(channelInfoItem.getServiceId(), channelInfoItem.getUrl(), channelInfoItem.getName()));
        }
        return arrayList;
    }
}
