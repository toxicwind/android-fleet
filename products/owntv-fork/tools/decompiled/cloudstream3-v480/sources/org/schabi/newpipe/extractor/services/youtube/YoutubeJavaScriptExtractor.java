package org.schabi.newpipe.extractor.services.youtube;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
final class YoutubeJavaScriptExtractor {
    private static final String BASE_JS_PLAYER_URL_FORMAT = "https://www.youtube.com/s/player/%s/player_ias.vflset/en_GB/base.js";
    private static final String HTTPS = "https:";
    private static final Pattern IFRAME_RES_JS_BASE_PLAYER_HASH_PATTERN = Pattern.compile("player\\\\/([a-z0-9]{8})\\\\/");
    private static final Pattern EMBEDDED_WATCH_PAGE_JS_BASE_PLAYER_URL_PATTERN = Pattern.compile("\"jsUrl\":\"(/s/player/[A-Za-z0-9]+/player_ias\\.vflset/[A-Za-z_-]+/base\\.js)\"");

    private YoutubeJavaScriptExtractor() {
    }

    @Nonnull
    static String extractJavaScriptPlayerCode(@Nonnull String str) throws ParsingException {
        try {
            String strCleanJavaScriptUrl = cleanJavaScriptUrl(extractJavaScriptUrlWithIframeResource());
            new URL(strCleanJavaScriptUrl);
            return downloadJavaScriptCode(strCleanJavaScriptUrl);
        } catch (Exception unused) {
            String strCleanJavaScriptUrl2 = cleanJavaScriptUrl(extractJavaScriptUrlWithEmbedWatchPage(str));
            try {
                new URL(strCleanJavaScriptUrl2);
                return downloadJavaScriptCode(strCleanJavaScriptUrl2);
            } catch (MalformedURLException e) {
                throw new ParsingException("The extracted and built JavaScript URL is invalid", e);
            }
        }
    }

    @Nonnull
    static String extractJavaScriptUrlWithIframeResource() throws ParsingException {
        try {
            try {
                return String.format(BASE_JS_PLAYER_URL_FORMAT, Parser.matchGroup1(IFRAME_RES_JS_BASE_PLAYER_HASH_PATTERN, NewPipe.getDownloader().get("https://www.youtube.com/iframe_api", Localization.DEFAULT).responseBody()));
            } catch (Parser.RegexException e) {
                throw new ParsingException("IFrame resource didn't provide JavaScript base player's hash", e);
            }
        } catch (Exception e2) {
            throw new ParsingException("Could not fetch IFrame resource", e2);
        }
    }

    @Nonnull
    static String extractJavaScriptUrlWithEmbedWatchPage(@Nonnull String str) throws ParsingException {
        try {
            String strResponseBody = NewPipe.getDownloader().get("https://www.youtube.com/embed/" + str, Localization.DEFAULT).responseBody();
            Iterator it = Jsoup.parse(strResponseBody).select("script").attr("name", "player/base").iterator();
            while (it.hasNext()) {
                String strAttr = ((Element) it.next()).attr("src");
                if (strAttr.contains("base.js")) {
                    return strAttr;
                }
            }
            try {
                return Parser.matchGroup1(EMBEDDED_WATCH_PAGE_JS_BASE_PLAYER_URL_PATTERN, strResponseBody);
            } catch (Parser.RegexException e) {
                throw new ParsingException("Embedded watch page didn't provide JavaScript base player's URL", e);
            }
        } catch (Exception e2) {
            throw new ParsingException("Could not fetch embedded watch page", e2);
        }
    }

    @Nonnull
    private static String cleanJavaScriptUrl(@Nonnull String str) {
        if (str.startsWith("//")) {
            return HTTPS + str;
        }
        if (!str.startsWith("/")) {
            return str;
        }
        return "https://www.youtube.com" + str;
    }

    @Nonnull
    private static String downloadJavaScriptCode(@Nonnull String str) throws ParsingException {
        try {
            return NewPipe.getDownloader().get(str, Localization.DEFAULT).responseBody();
        } catch (Exception e) {
            throw new ParsingException("Could not get JavaScript base player's code", e);
        }
    }
}
