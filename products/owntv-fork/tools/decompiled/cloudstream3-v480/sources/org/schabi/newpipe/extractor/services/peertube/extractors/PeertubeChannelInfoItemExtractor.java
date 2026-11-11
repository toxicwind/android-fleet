package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeChannelInfoItemExtractor implements ChannelInfoItemExtractor {
    private final String baseUrl;
    private final JsonObject item;

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public boolean isVerified() throws ParsingException {
        return false;
    }

    public PeertubeChannelInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nonnull String str) {
        this.item = jsonObject;
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
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.item);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public String getDescription() throws ParsingException {
        return this.item.getString("description");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getSubscriberCount() throws ParsingException {
        return this.item.getInt("followersCount");
    }
}
