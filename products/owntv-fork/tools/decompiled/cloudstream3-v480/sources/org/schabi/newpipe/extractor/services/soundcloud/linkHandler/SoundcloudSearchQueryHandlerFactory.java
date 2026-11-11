package org.schabi.newpipe.extractor.services.soundcloud.linkHandler;

import java.io.IOException;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudSearchQueryHandlerFactory extends SearchQueryHandlerFactory {
    public static final String ALL = "all";
    private static final SoundcloudSearchQueryHandlerFactory INSTANCE = new SoundcloudSearchQueryHandlerFactory();
    public static final int ITEMS_PER_PAGE = 10;
    public static final String PLAYLISTS = "playlists";
    public static final String TRACKS = "tracks";
    public static final String USERS = "users";

    private SoundcloudSearchQueryHandlerFactory() {
    }

    public static SoundcloudSearchQueryHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory, org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        try {
            String str3 = "https://api-v2.soundcloud.com/search";
            if (!list.isEmpty()) {
                String str4 = list.get(0);
                switch (str4.hashCode()) {
                    case -1865828127:
                        if (str4.equals("playlists")) {
                            str3 = "https://api-v2.soundcloud.com/search/playlists";
                        }
                        break;
                    case -865716088:
                        if (str4.equals("tracks")) {
                            str3 = "https://api-v2.soundcloud.com/search/tracks";
                        }
                        break;
                    case 96673:
                        str4.equals("all");
                        break;
                    case 111578632:
                        if (str4.equals(USERS)) {
                            str3 = "https://api-v2.soundcloud.com/search/users";
                        }
                        break;
                }
            }
            return str3 + "?q=" + Utils.encodeUrlUtf8(str) + "&client_id=" + SoundcloudParsingHelper.clientId() + "&limit=10&offset=0";
        } catch (IOException | ExtractionException e) {
            throw new ParsingException("Could not get client id", e);
        } catch (ReCaptchaException e2) {
            throw new ParsingException("ReCaptcha required", e2);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"all", "tracks", USERS, "playlists"};
    }
}
