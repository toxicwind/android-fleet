package org.schabi.newpipe.extractor.services.peertube.extractors;

import java.util.Collections;
import java.util.List;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeSuggestionExtractor extends SuggestionExtractor {
    public PeertubeSuggestionExtractor(StreamingService streamingService) {
        super(streamingService);
    }

    @Override // org.schabi.newpipe.extractor.suggestion.SuggestionExtractor
    public List<String> suggestionList(String str) {
        return Collections.EMPTY_LIST;
    }
}
