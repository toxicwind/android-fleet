package org.schabi.newpipe.extractor.playlist;

import org.schabi.newpipe.extractor.InfoItemsCollector;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public class PlaylistInfoItemsCollector extends InfoItemsCollector<PlaylistInfoItem, PlaylistInfoItemExtractor> {
    public PlaylistInfoItemsCollector(int i) {
        super(i);
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public PlaylistInfoItem extract(PlaylistInfoItemExtractor playlistInfoItemExtractor) throws ParsingException {
        PlaylistInfoItem playlistInfoItem = new PlaylistInfoItem(getServiceId(), playlistInfoItemExtractor.getUrl(), playlistInfoItemExtractor.getName());
        try {
            playlistInfoItem.setUploaderName(playlistInfoItemExtractor.getUploaderName());
        } catch (Exception e) {
            addError(e);
        }
        try {
            playlistInfoItem.setUploaderUrl(playlistInfoItemExtractor.getUploaderUrl());
        } catch (Exception e2) {
            addError(e2);
        }
        try {
            playlistInfoItem.setUploaderVerified(playlistInfoItemExtractor.isUploaderVerified());
        } catch (Exception e3) {
            addError(e3);
        }
        try {
            playlistInfoItem.setThumbnails(playlistInfoItemExtractor.getThumbnails());
        } catch (Exception e4) {
            addError(e4);
        }
        try {
            playlistInfoItem.setStreamCount(playlistInfoItemExtractor.getStreamCount());
        } catch (Exception e5) {
            addError(e5);
        }
        try {
            playlistInfoItem.setDescription(playlistInfoItemExtractor.getDescription());
        } catch (Exception e6) {
            addError(e6);
        }
        try {
            playlistInfoItem.setPlaylistType(playlistInfoItemExtractor.getPlaylistType());
        } catch (Exception e7) {
            addError(e7);
        }
        return playlistInfoItem;
    }
}
