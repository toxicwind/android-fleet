package org.schabi.newpipe.extractor.services.peertube;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import j$.util.DesugarCollections;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubePlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeSepiaStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubeParsingHelper {
    public static final String COUNT_KEY = "count";
    public static final int ITEMS_PER_PAGE = 12;
    public static final String START_KEY = "start";
    public static final String START_PATTERN = "start=(\\d*)";

    private PeertubeParsingHelper() {
    }

    public static void validate(JsonObject jsonObject) throws ContentNotAvailableException {
        String string = jsonObject.getString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR);
        if (!Utils.isBlank(string)) {
            throw new ContentNotAvailableException(string);
        }
    }

    public static Page getNextPage(String str, long j) {
        try {
            String strMatchGroup1 = Parser.matchGroup1(START_PATTERN, str);
            if (Utils.isBlank(strMatchGroup1)) {
                return null;
            }
            long j2 = Long.parseLong(strMatchGroup1) + 12;
            if (j2 >= j) {
                return null;
            }
            return new Page(str.replace("start=" + strMatchGroup1, "start=" + j2));
        } catch (NumberFormatException | Parser.RegexException unused) {
            return null;
        }
    }

    public static void collectItemsFrom(InfoItemsCollector infoItemsCollector, JsonObject jsonObject, String str) throws ParsingException {
        collectItemsFrom(infoItemsCollector, jsonObject, str, false);
    }

    public static void collectItemsFrom(InfoItemsCollector infoItemsCollector, JsonObject jsonObject, String str, boolean z) throws ParsingException {
        Object peertubeStreamInfoItemExtractor;
        try {
            for (Object obj : (JsonArray) JsonUtils.getValue(jsonObject, "data")) {
                if (obj instanceof JsonObject) {
                    JsonObject object = (JsonObject) obj;
                    if (object.has("video")) {
                        object = object.getObject("video");
                    }
                    boolean zHas = object.has("videosLength");
                    boolean zHas2 = object.has("followersCount");
                    if (z) {
                        peertubeStreamInfoItemExtractor = new PeertubeSepiaStreamInfoItemExtractor(object, str);
                    } else if (zHas) {
                        peertubeStreamInfoItemExtractor = new PeertubePlaylistInfoItemExtractor(object, str);
                    } else if (zHas2) {
                        peertubeStreamInfoItemExtractor = new PeertubeChannelInfoItemExtractor(object, str);
                    } else {
                        peertubeStreamInfoItemExtractor = new PeertubeStreamInfoItemExtractor(object, str);
                    }
                    infoItemsCollector.commit(peertubeStreamInfoItemExtractor);
                }
            }
        } catch (Exception e) {
            throw new ParsingException("Unable to extract list info", e);
        }
    }

    @Nonnull
    public static List<Image> getAvatarsFromOwnerAccountOrVideoChannelObject(@Nonnull String str, @Nonnull JsonObject jsonObject) {
        return getImagesFromAvatarsOrBanners(str, jsonObject, "avatars", "avatar");
    }

    @Nonnull
    public static List<Image> getBannersFromAccountOrVideoChannelObject(@Nonnull String str, @Nonnull JsonObject jsonObject) {
        return getImagesFromAvatarsOrBanners(str, jsonObject, "banners", "banner");
    }

    @Nonnull
    public static List<Image> getThumbnailsFromPlaylistOrVideoItem(@Nonnull String str, @Nonnull JsonObject jsonObject) {
        ArrayList arrayList = new ArrayList(2);
        String string = jsonObject.getString("thumbnailPath");
        if (!Utils.isNullOrEmpty(string)) {
            arrayList.add(new Image(str + string, -1, -1, Image.ResolutionLevel.LOW));
        }
        String string2 = jsonObject.getString("previewPath");
        if (!Utils.isNullOrEmpty(string2)) {
            arrayList.add(new Image(str + string2, -1, -1, Image.ResolutionLevel.MEDIUM));
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    @Nonnull
    private static List<Image> getImagesFromAvatarsOrBanners(@Nonnull String str, @Nonnull JsonObject jsonObject, @Nonnull String str2, @Nonnull String str3) {
        JsonArray array = jsonObject.getArray(str2);
        if (!Utils.isNullOrEmpty(array)) {
            return getImagesFromAvatarOrBannerArray(str, array);
        }
        JsonObject object = jsonObject.getObject(str3);
        String string = object.getString("path");
        if (!Utils.isNullOrEmpty(string)) {
            return UByte$$ExternalSyntheticBackport0.m(new Object[]{new Image(str + string, -1, object.getInt("width", -1), Image.ResolutionLevel.UNKNOWN)});
        }
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    private static List<Image> getImagesFromAvatarOrBannerArray(@Nonnull final String str, @Nonnull JsonArray jsonArray) {
        return (List) jsonArray.streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PeertubeParsingHelper.lambda$getImagesFromAvatarOrBannerArray$0((JsonObject) obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PeertubeParsingHelper.lambda$getImagesFromAvatarOrBannerArray$1(str, (JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ boolean lambda$getImagesFromAvatarOrBannerArray$0(JsonObject jsonObject) {
        return !Utils.isNullOrEmpty(jsonObject.getString("path"));
    }

    static /* synthetic */ Image lambda$getImagesFromAvatarOrBannerArray$1(String str, JsonObject jsonObject) {
        return new Image(str + jsonObject.getString("path"), -1, jsonObject.getInt("width", -1), Image.ResolutionLevel.UNKNOWN);
    }
}
