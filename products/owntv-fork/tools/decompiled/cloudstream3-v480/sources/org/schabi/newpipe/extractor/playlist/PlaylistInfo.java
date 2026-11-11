package org.schabi.newpipe.extractor.playlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public final class PlaylistInfo extends ListInfo<StreamInfoItem> {

    @Nonnull
    private List<Image> banners;
    private Description description;
    private PlaylistType playlistType;
    private long streamCount;

    @Nonnull
    private List<Image> subChannelAvatars;
    private String subChannelName;
    private String subChannelUrl;

    @Nonnull
    private List<Image> thumbnails;

    @Nonnull
    private List<Image> uploaderAvatars;
    private String uploaderName;
    private String uploaderUrl;

    public enum PlaylistType {
        NORMAL,
        MIX_STREAM,
        MIX_MUSIC,
        MIX_CHANNEL,
        MIX_GENRE
    }

    private PlaylistInfo(int i, ListLinkHandler listLinkHandler, String str) throws ParsingException {
        super(i, listLinkHandler, str);
        this.uploaderUrl = "";
        this.uploaderName = "";
        this.banners = Collections.EMPTY_LIST;
        this.subChannelAvatars = Collections.EMPTY_LIST;
        this.thumbnails = Collections.EMPTY_LIST;
        this.uploaderAvatars = Collections.EMPTY_LIST;
    }

    public static PlaylistInfo getInfo(String str) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(str), str);
    }

    public static PlaylistInfo getInfo(StreamingService streamingService, String str) throws IOException, ExtractionException {
        PlaylistExtractor playlistExtractor = streamingService.getPlaylistExtractor(str);
        playlistExtractor.fetchPage();
        return getInfo(playlistExtractor);
    }

    public static ListExtractor.InfoItemsPage<StreamInfoItem> getMoreItems(StreamingService streamingService, String str, Page page) throws IOException, ExtractionException {
        return streamingService.getPlaylistExtractor(str).getPage(page);
    }

    public static PlaylistInfo getInfo(PlaylistExtractor playlistExtractor) throws ExtractionException {
        PlaylistInfo playlistInfo = new PlaylistInfo(playlistExtractor.getServiceId(), playlistExtractor.getLinkHandler(), playlistExtractor.getName());
        ArrayList arrayList = new ArrayList();
        try {
            playlistInfo.setOriginalUrl(playlistExtractor.getOriginalUrl());
        } catch (Exception e) {
            playlistInfo.addError(e);
        }
        try {
            playlistInfo.setStreamCount(playlistExtractor.getStreamCount());
        } catch (Exception e2) {
            playlistInfo.addError(e2);
        }
        try {
            playlistInfo.setDescription(playlistExtractor.getDescription());
        } catch (Exception e3) {
            playlistInfo.addError(e3);
        }
        try {
            playlistInfo.setThumbnails(playlistExtractor.getThumbnails());
        } catch (Exception e4) {
            playlistInfo.addError(e4);
        }
        try {
            playlistInfo.setUploaderUrl(playlistExtractor.getUploaderUrl());
        } catch (Exception e5) {
            arrayList.add(e5);
        }
        try {
            playlistInfo.setUploaderName(playlistExtractor.getUploaderName());
        } catch (Exception e6) {
            arrayList.add(e6);
        }
        try {
            playlistInfo.setUploaderAvatars(playlistExtractor.getUploaderAvatars());
        } catch (Exception e7) {
            arrayList.add(e7);
        }
        try {
            playlistInfo.setSubChannelUrl(playlistExtractor.getSubChannelUrl());
        } catch (Exception e8) {
            arrayList.add(e8);
        }
        try {
            playlistInfo.setSubChannelName(playlistExtractor.getSubChannelName());
        } catch (Exception e9) {
            arrayList.add(e9);
        }
        try {
            playlistInfo.setSubChannelAvatars(playlistExtractor.getSubChannelAvatars());
        } catch (Exception e10) {
            arrayList.add(e10);
        }
        try {
            playlistInfo.setBanners(playlistExtractor.getBanners());
        } catch (Exception e11) {
            playlistInfo.addError(e11);
        }
        try {
            playlistInfo.setPlaylistType(playlistExtractor.getPlaylistType());
        } catch (Exception e12) {
            playlistInfo.addError(e12);
        }
        if (!arrayList.isEmpty() && (!playlistInfo.getErrors().isEmpty() || arrayList.size() < 3)) {
            playlistInfo.addAllErrors(arrayList);
        }
        ListExtractor.InfoItemsPage itemsPageOrLogError = ExtractorHelper.getItemsPageOrLogError(playlistInfo, playlistExtractor);
        playlistInfo.setRelatedItems(itemsPageOrLogError.getItems());
        playlistInfo.setNextPage(itemsPageOrLogError.getNextPage());
        return playlistInfo;
    }

    @Nonnull
    public List<Image> getThumbnails() {
        return this.thumbnails;
    }

    public void setThumbnails(@Nonnull List<Image> list) {
        this.thumbnails = list;
    }

    @Nonnull
    public List<Image> getBanners() {
        return this.banners;
    }

    public void setBanners(@Nonnull List<Image> list) {
        this.banners = list;
    }

    public String getUploaderUrl() {
        return this.uploaderUrl;
    }

    public void setUploaderUrl(String str) {
        this.uploaderUrl = str;
    }

    public String getUploaderName() {
        return this.uploaderName;
    }

    public void setUploaderName(String str) {
        this.uploaderName = str;
    }

    @Nonnull
    public List<Image> getUploaderAvatars() {
        return this.uploaderAvatars;
    }

    public void setUploaderAvatars(@Nonnull List<Image> list) {
        this.uploaderAvatars = list;
    }

    public String getSubChannelUrl() {
        return this.subChannelUrl;
    }

    public void setSubChannelUrl(String str) {
        this.subChannelUrl = str;
    }

    public String getSubChannelName() {
        return this.subChannelName;
    }

    public void setSubChannelName(String str) {
        this.subChannelName = str;
    }

    @Nonnull
    public List<Image> getSubChannelAvatars() {
        return this.subChannelAvatars;
    }

    public void setSubChannelAvatars(@Nonnull List<Image> list) {
        this.subChannelAvatars = list;
    }

    public long getStreamCount() {
        return this.streamCount;
    }

    public void setStreamCount(long j) {
        this.streamCount = j;
    }

    public Description getDescription() {
        return this.description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public PlaylistType getPlaylistType() {
        return this.playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }
}
