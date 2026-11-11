package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudPlaylistExtractor extends PlaylistExtractor {
    private static final int STREAMS_PER_REQUESTED_PAGE = 15;
    private JsonObject playlist;
    private String playlistId;

    public SoundcloudPlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String id = getLinkHandler().getId();
        this.playlistId = id;
        try {
            this.playlist = JsonParser.object().from(downloader.get("https://api-v2.soundcloud.com/playlists/" + id + "?client_id=" + SoundcloudParsingHelper.clientId() + "&representation=compact", getExtractorLocalization()).responseBody());
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return this.playlistId;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return this.playlist.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        String string = this.playlist.getString("artwork_url");
        if (!Utils.isNullOrEmpty(string)) {
            return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(string);
        }
        try {
            Iterator it = getInitialPage().getItems().iterator();
            while (it.hasNext()) {
                List<Image> thumbnails = ((StreamInfoItem) it.next()).getThumbnails();
                if (!Utils.isNullOrEmpty(thumbnails)) {
                    return thumbnails;
                }
            }
        } catch (Exception unused) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderUrl() {
        return SoundcloudParsingHelper.getUploaderUrl(this.playlist);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderName() {
        return SoundcloudParsingHelper.getUploaderName(this.playlist);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(SoundcloudParsingHelper.getAvatarUrl(this.playlist));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return this.playlist.getObject("user").getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public long getStreamCount() {
        return this.playlist.getLong("track_count");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        String string = this.playlist.getString("description");
        if (Utils.isNullOrEmpty(string)) {
            return Description.EMPTY_DESCRIPTION;
        }
        return new Description(string, 3);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() {
        final StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        final ArrayList arrayList = new ArrayList();
        this.playlist.getArray("tracks").streamAsJsonObjects().forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudPlaylistExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SoundcloudPlaylistExtractor.lambda$getInitialPage$0(streamInfoItemsCollector, arrayList, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, new Page(arrayList));
    }

    static /* synthetic */ void lambda$getInitialPage$0(StreamInfoItemsCollector streamInfoItemsCollector, List list, JsonObject jsonObject) {
        if (jsonObject.has("title")) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new SoundcloudStreamInfoItemExtractor(jsonObject));
        } else {
            list.add(String.valueOf(jsonObject.getLong("id")));
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        List<String> listSubList;
        List<String> ids;
        if (page == null || Utils.isNullOrEmpty(page.getIds())) {
            throw new IllegalArgumentException("Page doesn't contain IDs");
        }
        if (page.getIds().size() <= 15) {
            ids = page.getIds();
            listSubList = null;
        } else {
            List<String> listSubList2 = page.getIds().subList(0, 15);
            listSubList = page.getIds().subList(15, page.getIds().size());
            ids = listSubList2;
        }
        String str = "https://api-v2.soundcloud.com/tracks?client_id=" + SoundcloudParsingHelper.clientId() + "&ids=" + UByte$$ExternalSyntheticBackport0.m((CharSequence) ",", (Iterable) ids);
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        try {
            JsonArray jsonArrayFrom = JsonParser.array().from(NewPipe.getDownloader().get(str, getExtractorLocalization()).responseBody());
            HashMap map = new HashMap();
            for (Object obj : jsonArrayFrom) {
                if (obj instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) obj;
                    map.put(Long.valueOf(jsonObject.getLong("id")), jsonObject);
                }
            }
            Iterator<String> it = ids.iterator();
            while (it.hasNext()) {
                long j = Long.parseLong(it.next());
                try {
                    streamInfoItemsCollector.commit((StreamInfoItemExtractor) new SoundcloudStreamInfoItemExtractor((JsonObject) Objects.requireNonNull((JsonObject) map.get(Long.valueOf(j)), "no track with id " + j + " in response")));
                } catch (NullPointerException e) {
                    throw new ParsingException("Could not parse json response", e);
                }
            }
            return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, new Page(listSubList));
        } catch (JsonParserException e2) {
            throw new ParsingException("Could not parse json response", e2);
        }
    }
}
