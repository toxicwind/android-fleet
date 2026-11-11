package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonArray$$ExternalSyntheticLambda0;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeSuggestionExtractor extends SuggestionExtractor {
    public YoutubeSuggestionExtractor(StreamingService streamingService) {
        super(streamingService);
    }

    @Override // org.schabi.newpipe.extractor.suggestion.SuggestionExtractor
    public List<String> suggestionList(String str) throws IOException, ExtractionException {
        String str2 = "https://suggestqueries-clients6.youtube.com/complete/search?client=youtube&ds=yt&gl=" + Utils.encodeUrlUtf8(getExtractorContentCountry().getCountryCode()) + "&q=" + Utils.encodeUrlUtf8(str) + "&xhr=t";
        HashMap map = new HashMap();
        map.put("Origin", Collections.singletonList("https://www.youtube.com"));
        map.put("Referer", Collections.singletonList("https://www.youtube.com"));
        Response response = NewPipe.getDownloader().get(str2, map, getExtractorLocalization());
        String header = response.getHeader("Content-Type");
        if (Utils.isNullOrEmpty(header) || !header.contains("application/json")) {
            throw new ExtractionException("Invalid response type (got \"" + header + "\", excepted a JSON response) (response code " + response.responseCode() + ")");
        }
        String strResponseBody = response.responseBody();
        if (strResponseBody.isEmpty()) {
            throw new ExtractionException("Empty response received");
        }
        try {
            final Class<JsonArray> cls = JsonArray.class;
            return (List) Collection.EL.stream(JsonParser.array().from(strResponseBody).getArray(1)).filter(new JsonArray$$ExternalSyntheticLambda0(JsonArray.class)).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSuggestionExtractor$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (JsonArray) cls.cast(obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSuggestionExtractor$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonArray) obj).getString(0);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSuggestionExtractor$$ExternalSyntheticLambda2
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return YoutubeSuggestionExtractor.lambda$suggestionList$1((String) obj);
                }
            }).collect(Collectors.toUnmodifiableList());
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse JSON response", e);
        }
    }

    static /* synthetic */ boolean lambda$suggestionList$1(String str) {
        return !Utils.isBlank(str);
    }
}
