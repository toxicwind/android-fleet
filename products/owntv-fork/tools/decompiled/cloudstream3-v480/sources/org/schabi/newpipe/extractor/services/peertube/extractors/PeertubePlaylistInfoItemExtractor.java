package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubePlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final String baseUrl;
    private final JsonObject item;
    private final JsonObject uploader;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public PeertubePlaylistInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nonnull String str) {
        this.item = jsonObject;
        this.uploader = jsonObject.getObject("uploader");
        this.baseUrl = str;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.item.getString("displayName");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.item.getString(ImagesContract.URL);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return PeertubeParsingHelper.getThumbnailsFromPlaylistOrVideoItem(this.baseUrl, this.item);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.uploader.getString("displayName");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        return this.uploader.getString(ImagesContract.URL);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        return this.item.getInt("videosLength");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        String string = this.item.getString("description");
        if (Utils.isNullOrEmpty(string)) {
            return Description.EMPTY_DESCRIPTION;
        }
        return new Description(string, 3);
    }
}
