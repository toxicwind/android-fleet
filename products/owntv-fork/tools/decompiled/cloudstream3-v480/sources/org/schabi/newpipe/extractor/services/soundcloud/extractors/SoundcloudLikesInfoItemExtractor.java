package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import com.grack.nanojson.JsonObject;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudLikesInfoItemExtractor extends SoundcloudStreamInfoItemExtractor {
    public SoundcloudLikesInfoItemExtractor(JsonObject jsonObject) {
        super(jsonObject.getObject("track"));
    }
}
