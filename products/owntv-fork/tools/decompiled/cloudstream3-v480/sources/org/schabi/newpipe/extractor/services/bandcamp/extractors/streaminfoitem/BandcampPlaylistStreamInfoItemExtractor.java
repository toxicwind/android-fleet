package org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.grack.nanojson.JsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.stream.StreamExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampPlaylistStreamInfoItemExtractor extends BandcampStreamInfoItemExtractor {
    private final StreamingService service;
    private List<Image> substituteCovers;
    private final JsonObject track;

    public BandcampPlaylistStreamInfoItemExtractor(JsonObject jsonObject, String str, StreamingService streamingService) {
        super(str);
        this.track = jsonObject;
        this.service = streamingService;
        this.substituteCovers = Collections.EMPTY_LIST;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BandcampPlaylistStreamInfoItemExtractor(JsonObject jsonObject, String str, List<Image> list) {
        this(jsonObject, str, (StreamingService) null);
        this.substituteCovers = list;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.track.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        String string = this.track.getString("title_link");
        if (string == null) {
            return null;
        }
        return getUploaderUrl() + string;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return this.track.getLong(TypedValues.TransitionType.S_DURATION);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        if (this.substituteCovers.isEmpty() && getUrl() != null) {
            try {
                StreamExtractor streamExtractor = this.service.getStreamExtractor(getUrl());
                streamExtractor.fetchPage();
                return streamExtractor.getThumbnails();
            } catch (IOException | ExtractionException e) {
                throw new ParsingException("Could not download cover art location", e);
            }
        }
        return this.substituteCovers;
    }
}
