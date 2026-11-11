package org.schabi.newpipe.extractor.channel;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Info;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public class ChannelInfo extends Info {

    @Nonnull
    private List<Image> avatars;

    @Nonnull
    private List<Image> banners;
    private String description;
    private String[] donationLinks;
    private String feedUrl;

    @Nonnull
    private List<Image> parentChannelAvatars;
    private String parentChannelName;
    private String parentChannelUrl;
    private long subscriberCount;
    private List<ListLinkHandler> tabs;
    private List<String> tags;
    private boolean verified;

    public ChannelInfo(int i, String str, String str2, String str3, String str4) {
        super(i, str, str2, str3, str4);
        this.subscriberCount = -1L;
        this.avatars = Collections.EMPTY_LIST;
        this.banners = Collections.EMPTY_LIST;
        this.parentChannelAvatars = Collections.EMPTY_LIST;
        this.tabs = Collections.EMPTY_LIST;
        this.tags = Collections.EMPTY_LIST;
    }

    public static ChannelInfo getInfo(String str) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(str), str);
    }

    public static ChannelInfo getInfo(StreamingService streamingService, String str) throws IOException, ExtractionException {
        ChannelExtractor channelExtractor = streamingService.getChannelExtractor(str);
        channelExtractor.fetchPage();
        return getInfo(channelExtractor);
    }

    public static ChannelInfo getInfo(ChannelExtractor channelExtractor) throws IOException, ExtractionException {
        ChannelInfo channelInfo = new ChannelInfo(channelExtractor.getServiceId(), channelExtractor.getId(), channelExtractor.getUrl(), channelExtractor.getOriginalUrl(), channelExtractor.getName());
        try {
            channelInfo.setAvatars(channelExtractor.getAvatars());
        } catch (Exception e) {
            channelInfo.addError(e);
        }
        try {
            channelInfo.setBanners(channelExtractor.getBanners());
        } catch (Exception e2) {
            channelInfo.addError(e2);
        }
        try {
            channelInfo.setFeedUrl(channelExtractor.getFeedUrl());
        } catch (Exception e3) {
            channelInfo.addError(e3);
        }
        try {
            channelInfo.setSubscriberCount(channelExtractor.getSubscriberCount());
        } catch (Exception e4) {
            channelInfo.addError(e4);
        }
        try {
            channelInfo.setDescription(channelExtractor.getDescription());
        } catch (Exception e5) {
            channelInfo.addError(e5);
        }
        try {
            channelInfo.setParentChannelName(channelExtractor.getParentChannelName());
        } catch (Exception e6) {
            channelInfo.addError(e6);
        }
        try {
            channelInfo.setParentChannelUrl(channelExtractor.getParentChannelUrl());
        } catch (Exception e7) {
            channelInfo.addError(e7);
        }
        try {
            channelInfo.setParentChannelAvatars(channelExtractor.getParentChannelAvatars());
        } catch (Exception e8) {
            channelInfo.addError(e8);
        }
        try {
            channelInfo.setVerified(channelExtractor.isVerified());
        } catch (Exception e9) {
            channelInfo.addError(e9);
        }
        try {
            channelInfo.setTabs(channelExtractor.getTabs());
        } catch (Exception e10) {
            channelInfo.addError(e10);
        }
        try {
            channelInfo.setTags(channelExtractor.getTags());
        } catch (Exception e11) {
            channelInfo.addError(e11);
        }
        return channelInfo;
    }

    public String getParentChannelName() {
        return this.parentChannelName;
    }

    public void setParentChannelName(String str) {
        this.parentChannelName = str;
    }

    public String getParentChannelUrl() {
        return this.parentChannelUrl;
    }

    public void setParentChannelUrl(String str) {
        this.parentChannelUrl = str;
    }

    @Nonnull
    public List<Image> getParentChannelAvatars() {
        return this.parentChannelAvatars;
    }

    public void setParentChannelAvatars(@Nonnull List<Image> list) {
        this.parentChannelAvatars = list;
    }

    @Nonnull
    public List<Image> getAvatars() {
        return this.avatars;
    }

    public void setAvatars(@Nonnull List<Image> list) {
        this.avatars = list;
    }

    @Nonnull
    public List<Image> getBanners() {
        return this.banners;
    }

    public void setBanners(@Nonnull List<Image> list) {
        this.banners = list;
    }

    public String getFeedUrl() {
        return this.feedUrl;
    }

    public void setFeedUrl(String str) {
        this.feedUrl = str;
    }

    public long getSubscriberCount() {
        return this.subscriberCount;
    }

    public void setSubscriberCount(long j) {
        this.subscriberCount = j;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String[] getDonationLinks() {
        return this.donationLinks;
    }

    public void setDonationLinks(String[] strArr) {
        this.donationLinks = strArr;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    @Nonnull
    public List<ListLinkHandler> getTabs() {
        return this.tabs;
    }

    public void setTabs(@Nonnull List<ListLinkHandler> list) {
        this.tabs = list;
    }

    @Nonnull
    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(@Nonnull List<String> list) {
        this.tags = list;
    }
}
