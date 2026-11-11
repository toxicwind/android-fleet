package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.autofill.HintConstants;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudChannelInfoItemExtractor implements ChannelInfoItemExtractor {
    private final JsonObject itemObject;

    public SoundcloudChannelInfoItemExtractor(JsonObject jsonObject) {
        this.itemObject = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.itemObject.getString(HintConstants.AUTOFILL_HINT_USERNAME);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return Utils.replaceHttpWithHttps(this.itemObject.getString("permalink_url"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.itemObject.getString("avatar_url"));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getSubscriberCount() {
        return this.itemObject.getLong("followers_count");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getStreamCount() {
        return this.itemObject.getLong("track_count");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public boolean isVerified() {
        return this.itemObject.getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public String getDescription() {
        return this.itemObject.getString("description", "");
    }
}
