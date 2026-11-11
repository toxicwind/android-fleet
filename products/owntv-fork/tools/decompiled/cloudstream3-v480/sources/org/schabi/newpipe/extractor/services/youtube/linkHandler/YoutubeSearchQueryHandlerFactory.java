package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeSearchQueryHandlerFactory extends SearchQueryHandlerFactory {
    public static final String ALL = "all";
    public static final String CHANNELS = "channels";
    private static final YoutubeSearchQueryHandlerFactory INSTANCE = new YoutubeSearchQueryHandlerFactory();
    public static final String MUSIC_ALBUMS = "music_albums";
    public static final String MUSIC_ARTISTS = "music_artists";
    public static final String MUSIC_PLAYLISTS = "music_playlists";
    private static final String MUSIC_SEARCH_URL = "https://music.youtube.com/search?q=";
    public static final String MUSIC_SONGS = "music_songs";
    public static final String MUSIC_VIDEOS = "music_videos";
    public static final String PLAYLISTS = "playlists";
    private static final String SEARCH_URL = "https://www.youtube.com/results?search_query=";
    public static final String VIDEOS = "videos";

    @Nonnull
    public static YoutubeSearchQueryHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory, org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, @Nonnull List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        String str3 = !list.isEmpty() ? list.get(0) : "";
        str3.hashCode();
        switch (str3) {
            case "playlists":
                return SEARCH_URL + Utils.encodeUrlUtf8(str) + "&sp=EgIQA_ABAQ%253D%253D";
            case "music_playlists":
            case "music_artists":
            case "music_albums":
            case "music_songs":
            case "music_videos":
                return MUSIC_SEARCH_URL + Utils.encodeUrlUtf8(str);
            case "videos":
                return SEARCH_URL + Utils.encodeUrlUtf8(str) + "&sp=EgIQAfABAQ%253D%253D";
            case "channels":
                return SEARCH_URL + Utils.encodeUrlUtf8(str) + "&sp=EgIQAvABAQ%253D%253D";
            default:
                return SEARCH_URL + Utils.encodeUrlUtf8(str) + "&sp=8AEB";
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"all", "videos", "channels", "playlists", MUSIC_SONGS, MUSIC_VIDEOS, MUSIC_ALBUMS, MUSIC_PLAYLISTS};
    }

    @Nonnull
    public static String getSearchParameter(String str) {
        if (Utils.isNullOrEmpty(str)) {
            return "8AEB";
        }
        str.hashCode();
        switch (str) {
            case "playlists":
                return "EgIQA_ABAQ%3D%3D";
            case "music_playlists":
            case "music_artists":
            case "music_albums":
            case "music_songs":
            case "music_videos":
                return "";
            case "videos":
                return "EgIQAfABAQ%3D%3D";
            case "channels":
                return "EgIQAvABAQ%3D%3D";
            default:
                return "8AEB";
        }
    }
}
