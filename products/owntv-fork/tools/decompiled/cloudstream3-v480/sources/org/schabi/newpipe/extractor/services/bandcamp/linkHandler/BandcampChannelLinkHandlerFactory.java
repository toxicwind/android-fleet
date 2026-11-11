package org.schabi.newpipe.extractor.services.bandcamp.linkHandler;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.List;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampChannelLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final BandcampChannelLinkHandlerFactory INSTANCE = new BandcampChannelLinkHandlerFactory();

    private BandcampChannelLinkHandlerFactory() {
    }

    public static BandcampChannelLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        try {
            return String.valueOf(JsonUtils.getJsonData(NewPipe.getDownloader().get(Utils.replaceHttpWithHttps(str)).responseBody(), "data-band").getLong("id"));
        } catch (JsonParserException | IOException | ArrayIndexOutOfBoundsException | ReCaptchaException e) {
            throw new ParsingException("Download failed", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        JsonObject artistDetails = BandcampExtractorHelper.getArtistDetails(str);
        if (artistDetails.getBoolean(MediaRouteProviderProtocol.SERVICE_DATA_ERROR)) {
            throw new ParsingException("JSON does not contain a channel URL (invalid id?) or is otherwise invalid");
        }
        return Utils.replaceHttpWithHttps(artistDetails.getString("bandcamp_url"));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        String lowerCase = str.toLowerCase();
        String[] strArrSplit = lowerCase.split("/");
        if (strArrSplit.length != 3 && strArrSplit.length != 4) {
            return false;
        }
        if ((strArrSplit.length != 4 || strArrSplit[3].equals("releases") || strArrSplit[3].equals("music") || strArrSplit[3].equals("album") || strArrSplit[3].equals("track")) && !strArrSplit[2].equals("daily.bandcamp.com")) {
            return BandcampExtractorHelper.isArtistDomain(lowerCase);
        }
        return false;
    }
}
