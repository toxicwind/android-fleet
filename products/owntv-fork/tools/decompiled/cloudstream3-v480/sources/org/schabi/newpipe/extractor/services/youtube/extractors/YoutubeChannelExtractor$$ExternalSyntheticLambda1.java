package org.schabi.newpipe.extractor.services.youtube.extractors;

import j$.util.function.Function$CC;
import java.util.function.Function;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class YoutubeChannelExtractor$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ Class f$0;

    @Override // java.util.function.Function
    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (String) this.f$0.cast(obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
