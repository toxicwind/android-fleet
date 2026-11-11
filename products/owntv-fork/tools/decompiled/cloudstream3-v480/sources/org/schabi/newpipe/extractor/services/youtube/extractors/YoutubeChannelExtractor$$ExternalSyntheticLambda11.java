package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import j$.util.function.Function$CC;
import java.util.function.Function;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class YoutubeChannelExtractor$$ExternalSyntheticLambda11 implements Function {
    @Override // java.util.function.Function
    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return YoutubeParsingHelper.getImagesFromThumbnailsArray((JsonArray) obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
