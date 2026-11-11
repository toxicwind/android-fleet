package org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem;

import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampDiscographStreamInfoItemExtractor extends BandcampStreamInfoItemExtractor {
    private final JsonObject discograph;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return -1L;
    }

    public BandcampDiscographStreamInfoItemExtractor(JsonObject jsonObject, String str) {
        super(str);
        this.discograph = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        return this.discograph.getString("band_name");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.discograph.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return BandcampExtractorHelper.getStreamUrlFromIds(this.discograph.getLong("band_id"), this.discograph.getLong("item_id"), this.discograph.getString("item_type"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return BandcampExtractorHelper.getImagesFromImageId(this.discograph.getLong("art_id"), true);
    }
}
