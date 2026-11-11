package org.schabi.newpipe.extractor;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemsCollector;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;

/* JADX INFO: loaded from: classes8.dex */
public class MultiInfoItemsCollector extends InfoItemsCollector<InfoItem, InfoItemExtractor> {
    private final PlaylistInfoItemsCollector playlistCollector;
    private final StreamInfoItemsCollector streamCollector;
    private final ChannelInfoItemsCollector userCollector;

    public MultiInfoItemsCollector(int i) {
        super(i);
        this.streamCollector = new StreamInfoItemsCollector(i);
        this.userCollector = new ChannelInfoItemsCollector(i);
        this.playlistCollector = new PlaylistInfoItemsCollector(i);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemsCollector, org.schabi.newpipe.extractor.Collector
    public List<Throwable> getErrors() {
        ArrayList arrayList = new ArrayList(super.getErrors());
        arrayList.addAll(this.streamCollector.getErrors());
        arrayList.addAll(this.userCollector.getErrors());
        arrayList.addAll(this.playlistCollector.getErrors());
        return DesugarCollections.unmodifiableList(arrayList);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemsCollector, org.schabi.newpipe.extractor.Collector
    public void reset() {
        super.reset();
        this.streamCollector.reset();
        this.userCollector.reset();
        this.playlistCollector.reset();
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public InfoItem extract(InfoItemExtractor infoItemExtractor) throws ParsingException {
        if (infoItemExtractor instanceof StreamInfoItemExtractor) {
            return this.streamCollector.extract((StreamInfoItemExtractor) infoItemExtractor);
        }
        if (infoItemExtractor instanceof ChannelInfoItemExtractor) {
            return this.userCollector.extract((ChannelInfoItemExtractor) infoItemExtractor);
        }
        if (infoItemExtractor instanceof PlaylistInfoItemExtractor) {
            return this.playlistCollector.extract((PlaylistInfoItemExtractor) infoItemExtractor);
        }
        throw new IllegalArgumentException("Invalid extractor type: " + infoItemExtractor);
    }
}
