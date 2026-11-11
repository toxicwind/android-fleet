package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonObject;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeSepiaStreamInfoItemExtractor extends PeertubeStreamInfoItemExtractor {
    public PeertubeSepiaStreamInfoItemExtractor(JsonObject jsonObject, String str) {
        super(jsonObject, str);
        setBaseUrl(this.item.getString("embedUrl").replace(this.item.getString("embedPath"), ""));
    }
}
