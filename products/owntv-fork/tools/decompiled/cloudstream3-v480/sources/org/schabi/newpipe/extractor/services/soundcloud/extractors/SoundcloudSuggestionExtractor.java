package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import com.google.android.gms.actions.SearchIntents;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudSuggestionExtractor extends SuggestionExtractor {
    public SoundcloudSuggestionExtractor(StreamingService streamingService) {
        super(streamingService);
    }

    @Override // org.schabi.newpipe.extractor.suggestion.SuggestionExtractor
    public List<String> suggestionList(String str) throws IOException, ExtractionException {
        ArrayList arrayList = new ArrayList();
        try {
            for (Object obj : JsonParser.object().from(NewPipe.getDownloader().get("https://api-v2.soundcloud.com/search/queries?q=" + Utils.encodeUrlUtf8(str) + "&client_id=" + SoundcloudParsingHelper.clientId() + "&limit=10", getExtractorLocalization()).responseBody()).getArray("collection")) {
                if (obj instanceof JsonObject) {
                    arrayList.add(((JsonObject) obj).getString(SearchIntents.EXTRA_QUERY));
                }
            }
            return arrayList;
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }
}
