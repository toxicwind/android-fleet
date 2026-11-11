package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.grack.nanojson.JsonStringWriter;
import com.grack.nanojson.JsonWriter;
import j$.time.DateTimeException;
import j$.time.ZonedDateTime;
import j$.time.format.DateTimeFormatter;
import j$.util.Collection;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.utils.ImageSuffix;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampExtractorHelper {
    public static final String BASE_API_URL = "https://bandcamp.com/api";
    public static final String BASE_URL = "https://bandcamp.com";
    private static final String IMAGES_DOMAIN_AND_PATH = "https://f4.bcbits.com/img/";
    private static final String IMAGE_URL_APPENDIX_AND_EXTENSION_REGEX = "_\\d+\\.\\w+";
    private static final List<ImageSuffix> IMAGE_URL_SUFFIXES_AND_RESOLUTIONS = UByte$$ExternalSyntheticBackport0.m((Object[]) new ImageSuffix[]{new ImageSuffix("10.jpg", -1, 1200, Image.ResolutionLevel.HIGH), new ImageSuffix("101.jpg", 90, -1, Image.ResolutionLevel.LOW), new ImageSuffix("170.jpg", 422, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("171.jpg", 646, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("20.jpg", -1, 1024, Image.ResolutionLevel.HIGH), new ImageSuffix("200.jpg", 420, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("201.jpg", 280, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("202.jpg", 140, -1, Image.ResolutionLevel.LOW), new ImageSuffix("204.jpg", 360, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("205.jpg", 240, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("206.jpg", 180, -1, Image.ResolutionLevel.MEDIUM), new ImageSuffix("207.jpg", 120, -1, Image.ResolutionLevel.LOW), new ImageSuffix("43.jpg", 100, -1, Image.ResolutionLevel.LOW), new ImageSuffix("44.jpg", 200, -1, Image.ResolutionLevel.MEDIUM)});

    private BandcampExtractorHelper() {
    }

    public static String getStreamUrlFromIds(long j, long j2, String str) throws ParsingException {
        try {
            return Utils.replaceHttpWithHttps(JsonParser.object().from(NewPipe.getDownloader().get("https://bandcamp.com/api/mobile/22/tralbum_details?band_id=" + j + "&tralbum_id=" + j2 + "&tralbum_type=" + str.charAt(0)).responseBody()).getString("bandcamp_url"));
        } catch (JsonParserException | IOException | ReCaptchaException e) {
            throw new ParsingException("Ids could not be translated to URL", e);
        }
    }

    public static JsonObject getArtistDetails(String str) throws ParsingException {
        try {
            return JsonParser.object().from(NewPipe.getDownloader().postWithContentTypeJson("https://bandcamp.com/api/mobile/22/band_details", Collections.EMPTY_MAP, ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).value("band_id", str)).end()).done().getBytes(StandardCharsets.UTF_8)).responseBody());
        } catch (JsonParserException | IOException | ReCaptchaException e) {
            throw new ParsingException("Could not download band details", e);
        }
    }

    @Nonnull
    public static String getImageUrl(long j, boolean z) {
        return IMAGES_DOMAIN_AND_PATH + (z ? 'a' : "") + j + "_10.jpg";
    }

    public static boolean isArtistDomain(String str) throws ParsingException {
        if (str.toLowerCase().matches("https?://.+\\.bandcamp\\.com(/.*)?")) {
            return true;
        }
        if (str.toLowerCase().matches("https?://bandcamp\\.com(/.*)?")) {
            return false;
        }
        try {
            return ((Element) ((Element) Jsoup.parse(NewPipe.getDownloader().get(Utils.replaceHttpWithHttps(str)).responseBody()).getElementsByClass("cart-wrapper").get(0)).getElementsByTag(CmcdData.OBJECT_TYPE_AUDIO_ONLY).get(0)).attr("href").equals("https://bandcamp.com/cart");
        } catch (IOException | ReCaptchaException unused) {
            throw new ParsingException("Could not determine whether URL is custom domain (not available? network error?)");
        } catch (IndexOutOfBoundsException | NullPointerException unused2) {
            return false;
        }
    }

    public static boolean isRadioUrl(String str) {
        return str.toLowerCase().matches("https?://bandcamp\\.com/\\?show=\\d+");
    }

    public static DateWrapper parseDate(String str) throws ParsingException {
        try {
            return new DateWrapper(ZonedDateTime.parse(str, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)).toInstant());
        } catch (DateTimeException e) {
            throw new ParsingException("Could not parse date '" + str + "'", e);
        }
    }

    @Nonnull
    public static List<Image> getImagesFromSearchResult(@Nonnull Element element) {
        return getImagesFromImageUrl((String) Collection.EL.stream(element.getElementsByClass("art")).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Collection.EL.stream(((Element) obj).getElementsByTag("img"));
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Element) obj).attr("src");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper$$ExternalSyntheticLambda2
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
                return BandcampExtractorHelper.lambda$getImagesFromSearchResult$2((String) obj);
            }
        }).findFirst().orElse(""));
    }

    static /* synthetic */ boolean lambda$getImagesFromSearchResult$2(String str) {
        return !Utils.isNullOrEmpty(str);
    }

    @Nonnull
    public static List<Image> getImagesFromImageUrl(@Nullable String str) {
        if (Utils.isNullOrEmpty(str)) {
            return Collections.EMPTY_LIST;
        }
        return getImagesFromImageBaseUrl(str.replaceFirst(IMAGE_URL_APPENDIX_AND_EXTENSION_REGEX, "_"));
    }

    @Nonnull
    public static List<Image> getImagesFromImageId(long j, boolean z) {
        if (j == 0) {
            return Collections.EMPTY_LIST;
        }
        return getImagesFromImageBaseUrl(IMAGES_DOMAIN_AND_PATH + (z ? 'a' : "") + j + "_");
    }

    @Nonnull
    private static List<Image> getImagesFromImageBaseUrl(@Nonnull final String str) {
        return (List) Collection.EL.stream(IMAGE_URL_SUFFIXES_AND_RESOLUTIONS).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BandcampExtractorHelper.lambda$getImagesFromImageBaseUrl$3(str, (ImageSuffix) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ Image lambda$getImagesFromImageBaseUrl$3(String str, ImageSuffix imageSuffix) {
        return new Image(str + imageSuffix.getSuffix(), imageSuffix.getHeight(), imageSuffix.getWidth(), imageSuffix.getResolutionLevel());
    }
}
