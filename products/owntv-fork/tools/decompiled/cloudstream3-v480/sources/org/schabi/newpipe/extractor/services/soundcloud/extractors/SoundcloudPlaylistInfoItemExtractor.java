package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.autofill.HintConstants;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudPlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private static final String ARTWORK_URL_KEY = "artwork_url";
    private static final String AVATAR_URL_KEY = "avatar_url";
    private static final String USER_KEY = "user";
    private final JsonObject itemObject;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    public SoundcloudPlaylistInfoItemExtractor(JsonObject jsonObject) {
        this.itemObject = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.itemObject.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return Utils.replaceHttpWithHttps(this.itemObject.getString("permalink_url"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        if (this.itemObject.isString(ARTWORK_URL_KEY)) {
            String string = this.itemObject.getString(ARTWORK_URL_KEY);
            if (!Utils.isNullOrEmpty(string)) {
                return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(string);
            }
        }
        try {
            for (JsonObject jsonObject : this.itemObject.getArray("tracks")) {
                if (jsonObject.isString(ARTWORK_URL_KEY)) {
                    String string2 = jsonObject.getString(ARTWORK_URL_KEY);
                    if (!Utils.isNullOrEmpty(string2)) {
                        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(string2);
                    }
                }
                String string3 = jsonObject.getObject(USER_KEY).getString(AVATAR_URL_KEY);
                if (!Utils.isNullOrEmpty(string3)) {
                    return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(string3);
                }
            }
        } catch (Exception unused) {
        }
        try {
            return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.itemObject.getObject(USER_KEY).getString(AVATAR_URL_KEY));
        } catch (Exception e) {
            throw new ParsingException("Failed to extract playlist thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        try {
            return this.itemObject.getObject(USER_KEY).getString(HintConstants.AUTOFILL_HINT_USERNAME);
        } catch (Exception e) {
            throw new ParsingException("Failed to extract playlist uploader", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() {
        return this.itemObject.getObject(USER_KEY).getString("permalink_url");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() {
        return this.itemObject.getObject(USER_KEY).getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() {
        return this.itemObject.getLong("track_count");
    }
}
