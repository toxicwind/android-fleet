package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.grack.nanojson.JsonStringWriter;
import com.grack.nanojson.JsonWriter;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampSuggestionExtractor extends SuggestionExtractor {
    private static final String AUTOCOMPLETE_URL = "https://bandcamp.com/api/bcsearch_public_api/1/autocomplete_elastic";

    public BandcampSuggestionExtractor(StreamingService streamingService) {
        super(streamingService);
    }

    @Override // org.schabi.newpipe.extractor.suggestion.SuggestionExtractor
    public List<String> suggestionList(String str) throws IOException, ExtractionException {
        try {
            return (List) JsonParser.object().from(NewPipe.getDownloader().postWithContentTypeJson(AUTOCOMPLETE_URL, Collections.EMPTY_MAP, ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).value("fan_id", (String) null)).value("full_page", false)).value("search_filter", "")).value("search_text", str)).end()).done().getBytes(StandardCharsets.UTF_8)).responseBody()).getObject("auto").getArray("results").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampSuggestionExtractor$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getString("name");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).distinct().collect(Collectors.toList());
        } catch (JsonParserException unused) {
            return Collections.EMPTY_LIST;
        }
    }
}
