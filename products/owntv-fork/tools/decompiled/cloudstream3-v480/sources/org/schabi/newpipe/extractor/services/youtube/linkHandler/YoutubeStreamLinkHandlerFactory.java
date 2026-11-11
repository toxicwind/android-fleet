package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import com.google.common.base.Ascii;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.exceptions.FoundAdException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeStreamLinkHandlerFactory extends LinkHandlerFactory {
    private static final Pattern YOUTUBE_VIDEO_ID_REGEX_PATTERN = Pattern.compile("^([a-zA-Z0-9_-]{11})");
    private static final YoutubeStreamLinkHandlerFactory INSTANCE = new YoutubeStreamLinkHandlerFactory();
    private static final List<String> SUBPATHS = UByte$$ExternalSyntheticBackport0.m(new Object[]{"embed/", "live/", "shorts/", "watch/", "v/", "w/"});

    private YoutubeStreamLinkHandlerFactory() {
    }

    public static YoutubeStreamLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Nullable
    private static String extractId(@Nullable String str) {
        if (str != null) {
            Matcher matcher = YOUTUBE_VIDEO_ID_REGEX_PATTERN.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    @Nonnull
    private static String assertIsId(@Nullable String str) throws ParsingException {
        String strExtractId = extractId(str);
        if (strExtractId != null) {
            return strExtractId;
        }
        throw new ParsingException("The given string is not a YouTube video ID");
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    @Nonnull
    public String getUrl(String str) throws UnsupportedOperationException, ParsingException {
        return "https://www.youtube.com/watch?v=" + str;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    @Nonnull
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        String queryValue;
        byte b = 2;
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            if (scheme != null && (scheme.equals("vnd.youtube") || scheme.equals("vnd.youtube.launch"))) {
                String schemeSpecificPart = uri.getSchemeSpecificPart();
                if (schemeSpecificPart.startsWith("//")) {
                    String strExtractId = extractId(schemeSpecificPart.substring(2));
                    if (strExtractId != null) {
                        return strExtractId;
                    }
                    str = "https:" + schemeSpecificPart;
                } else {
                    return assertIsId(schemeSpecificPart);
                }
            }
        } catch (URISyntaxException unused) {
        }
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            String host = urlStringToURL.getHost();
            String path = urlStringToURL.getPath();
            if (!path.isEmpty()) {
                path = path.substring(1);
            }
            if (!Utils.isHTTP(urlStringToURL) || (!YoutubeParsingHelper.isYoutubeURL(urlStringToURL) && !YoutubeParsingHelper.isYoutubeServiceURL(urlStringToURL) && !YoutubeParsingHelper.isHooktubeURL(urlStringToURL) && !YoutubeParsingHelper.isInvidiousURL(urlStringToURL) && !YoutubeParsingHelper.isY2ubeURL(urlStringToURL))) {
                if (host.equalsIgnoreCase("googleads.g.doubleclick.net")) {
                    throw new FoundAdException("Error: found ad: " + str);
                }
                throw new ParsingException("The URL is not a YouTube URL");
            }
            if (YoutubePlaylistLinkHandlerFactory.getInstance().acceptUrl(str)) {
                throw new ParsingException("Error: no suitable URL: " + str);
            }
            String upperCase = host.toUpperCase();
            upperCase.hashCode();
            switch (upperCase.hashCode()) {
                case -2114213841:
                    b = !upperCase.equals("INVIDIOUS.ZEE.LI") ? (byte) -1 : (byte) 0;
                    break;
                case -2087592960:
                    b = !upperCase.equals("TUBUS.EDUVID.ORG") ? (byte) -1 : (byte) 1;
                    break;
                case -1698218251:
                    if (!upperCase.equals("Y2U.BE")) {
                        b = -1;
                    }
                    break;
                case -1693444139:
                    b = !upperCase.equals("M.YOUTUBE.COM") ? (byte) -1 : (byte) 3;
                    break;
                case -1604112842:
                    b = !upperCase.equals("YT.CYBERHOST.UK") ? (byte) -1 : (byte) 4;
                    break;
                case -1416474788:
                    b = !upperCase.equals("VID.MINT.LGBT") ? (byte) -1 : (byte) 5;
                    break;
                case -1146372515:
                    b = !upperCase.equals("YTB.TROM.TF") ? (byte) -1 : (byte) 6;
                    break;
                case -1141849304:
                    b = !upperCase.equals("VID.PUFFYAN.US") ? (byte) -1 : (byte) 7;
                    break;
                case -1092206675:
                    b = !upperCase.equals("PIPED.KAVIN.ROCKS") ? (byte) -1 : (byte) 8;
                    break;
                case -827318675:
                    b = !upperCase.equals("INVIDIOUS.NAMAZSO.EU") ? (byte) -1 : (byte) 9;
                    break;
                case -397809965:
                    b = !upperCase.equals("DEV.INVIDIO.US") ? (byte) -1 : (byte) 10;
                    break;
                case -397058810:
                    b = !upperCase.equals("INV.RIVERSIDE.ROCKS") ? (byte) -1 : (byte) 11;
                    break;
                case -244508014:
                    b = !upperCase.equals("INVIDIOU.SITE") ? (byte) -1 : Ascii.FF;
                    break;
                case -124567835:
                    b = !upperCase.equals("YEWTU.BE") ? (byte) -1 : Ascii.CR;
                    break;
                case -52807283:
                    b = !upperCase.equals("MUSIC.YOUTUBE.COM") ? (byte) -1 : Ascii.SO;
                    break;
                case 73534791:
                    b = !upperCase.equals("INVIDIOUS.KAVIN.ROCKS") ? (byte) -1 : Ascii.SI;
                    break;
                case 103116914:
                    b = !upperCase.equals("INVIDIOUS-US.KAVIN.ROCKS") ? (byte) -1 : Ascii.DLE;
                    break;
                case 103276081:
                    b = !upperCase.equals("YOUTU.BE") ? (byte) -1 : (byte) 17;
                    break;
                case 169254170:
                    b = !upperCase.equals("HOOKTUBE.COM") ? (byte) -1 : Ascii.DC2;
                    break;
                case 301798888:
                    b = !upperCase.equals("INVIDIOUS.MOOMOO.ME") ? (byte) -1 : (byte) 19;
                    break;
                case 360245068:
                    b = !upperCase.equals("Y.COM.CM") ? (byte) -1 : Ascii.DC4;
                    break;
                case 405074072:
                    b = !upperCase.equals("INVIDIOUS.EXONIP.DE") ? (byte) -1 : Ascii.NAK;
                    break;
                case 941554175:
                    b = !upperCase.equals("WWW.YOUTUBE.COM") ? (byte) -1 : Ascii.SYN;
                    break;
                case 1061977963:
                    b = !upperCase.equals("TUBE.CONNECT.CAFE") ? (byte) -1 : Ascii.ETB;
                    break;
                case 1229581114:
                    b = !upperCase.equals("INVIDIO.US") ? (byte) -1 : Ascii.CAN;
                    break;
                case 1607514856:
                    b = !upperCase.equals("INVIDIOUS.FDN.FR") ? (byte) -1 : Ascii.EM;
                    break;
                case 1649009762:
                    b = !upperCase.equals("INVIDIOUS.SNOPYTA.ORG") ? (byte) -1 : Ascii.SUB;
                    break;
                case 1720784822:
                    b = !upperCase.equals("REDIRECT.INVIDIOUS.IO") ? (byte) -1 : Ascii.ESC;
                    break;
                case 1739373659:
                    b = !upperCase.equals("YTPRIVATE.COM") ? (byte) -1 : Ascii.FS;
                    break;
                case 1818283559:
                    b = !upperCase.equals("INVIDIOUS.048596.XYZ") ? (byte) -1 : Ascii.GS;
                    break;
                case 1876693401:
                    b = !upperCase.equals("INVIDIOUS.BLAMEFRAN.NET") ? (byte) -1 : Ascii.RS;
                    break;
                case 1999754024:
                    b = !upperCase.equals("INVIDIOUS.SILKKY.CLOUD") ? (byte) -1 : Ascii.US;
                    break;
                case 2022964729:
                    b = !upperCase.equals("WWW.YOUTUBE-NOCOOKIE.COM") ? (byte) -1 : (byte) 32;
                    break;
                case 2024273937:
                    b = !upperCase.equals("WWW.INVIDIO.US") ? (byte) -1 : (byte) 33;
                    break;
                case 2035582341:
                    b = !upperCase.equals("INVIDIOUS.SITE") ? (byte) -1 : (byte) 34;
                    break;
                case 2075880438:
                    b = !upperCase.equals("YOUTUBE.COM") ? (byte) -1 : (byte) 35;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                case 1:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 15:
                case 16:
                case 18:
                case 19:
                case 20:
                case 21:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33:
                case 34:
                    if (path.equals("watch") && (queryValue = Utils.getQueryValue(urlStringToURL, "v")) != null) {
                        return assertIsId(queryValue);
                    }
                    String idFromSubpathsInPath = getIdFromSubpathsInPath(path);
                    if (idFromSubpathsInPath != null) {
                        return idFromSubpathsInPath;
                    }
                    String queryValue2 = Utils.getQueryValue(urlStringToURL, "v");
                    if (queryValue2 != null) {
                        return assertIsId(queryValue2);
                    }
                    return assertIsId(path);
                case 2:
                case 17:
                    String queryValue3 = Utils.getQueryValue(urlStringToURL, "v");
                    if (queryValue3 != null) {
                        return assertIsId(queryValue3);
                    }
                    return assertIsId(path);
                case 3:
                case 14:
                case 22:
                case 35:
                    if (path.equals("attribution_link")) {
                        try {
                            return assertIsId(Utils.getQueryValue(Utils.stringToURL("https://www.youtube.com" + Utils.getQueryValue(urlStringToURL, "u")), "v"));
                        } catch (MalformedURLException unused2) {
                            throw new ParsingException("Error: no suitable URL: " + str);
                        }
                    }
                    String idFromSubpathsInPath2 = getIdFromSubpathsInPath(path);
                    return idFromSubpathsInPath2 != null ? idFromSubpathsInPath2 : assertIsId(Utils.getQueryValue(urlStringToURL, "v"));
                case 32:
                    if (path.startsWith("embed/")) {
                        return assertIsId(path.substring(6));
                    }
                    break;
            }
            throw new ParsingException("Error: no suitable URL: " + str);
        } catch (MalformedURLException e) {
            throw new ParsingException("The given URL is not valid", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws FoundAdException {
        try {
            getId(str);
            return true;
        } catch (FoundAdException e) {
            throw e;
        } catch (ParsingException unused) {
            return false;
        }
    }

    @Nullable
    private String getIdFromSubpathsInPath(@Nonnull String str) throws ParsingException {
        for (String str2 : SUBPATHS) {
            if (str.startsWith(str2)) {
                return assertIsId(str.substring(str2.length()));
            }
        }
        return null;
    }
}
