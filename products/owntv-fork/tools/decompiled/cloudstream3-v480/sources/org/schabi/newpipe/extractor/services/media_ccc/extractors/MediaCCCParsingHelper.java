package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class MediaCCCParsingHelper {
    private static final Pattern LIVE_STREAM_ID_PATTERN = Pattern.compile("\\w+/\\w+");
    private static JsonArray liveStreams = null;

    private MediaCCCParsingHelper() {
    }

    public static boolean isLiveStreamId(String str) {
        return LIVE_STREAM_ID_PATTERN.matcher(str).find();
    }

    public static JsonArray getLiveStreams(Downloader downloader, Localization localization) throws ExtractionException {
        if (liveStreams == null) {
            try {
                liveStreams = JsonParser.array().from(downloader.get("https://streaming.media.ccc.de/streams/v2.json", localization).responseBody());
            } catch (JsonParserException e) {
                throw new ExtractionException("Could not parse JSON.", e);
            } catch (IOException | ReCaptchaException e2) {
                throw new ExtractionException("Could not get live stream JSON.", e2);
            }
        }
        return liveStreams;
    }

    @Nonnull
    public static List<Image> getImageListFromLogoImageUrl(@Nullable String str) {
        return Utils.isNullOrEmpty(str) ? Collections.EMPTY_LIST : UByte$$ExternalSyntheticBackport0.m(new Object[]{new Image(str, -1, -1, Image.ResolutionLevel.UNKNOWN)});
    }

    @Nonnull
    public static List<Image> getThumbnailsFromStreamItem(@Nonnull JsonObject jsonObject) {
        return getThumbnailsFromObject(jsonObject, "thumb_url", "poster_url");
    }

    @Nonnull
    public static List<Image> getThumbnailsFromLiveStreamItem(@Nonnull JsonObject jsonObject) {
        return getThumbnailsFromObject(jsonObject, "thumb", "poster");
    }

    @Nonnull
    private static List<Image> getThumbnailsFromObject(@Nonnull JsonObject jsonObject, @Nonnull String str, @Nonnull String str2) {
        ArrayList arrayList = new ArrayList(2);
        String string = jsonObject.getString(str);
        if (!Utils.isNullOrEmpty(string)) {
            arrayList.add(new Image(string, -1, -1, Image.ResolutionLevel.MEDIUM));
        }
        String string2 = jsonObject.getString(str2);
        if (!Utils.isNullOrEmpty(string2)) {
            arrayList.add(new Image(string2, -1, -1, Image.ResolutionLevel.HIGH));
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }
}
