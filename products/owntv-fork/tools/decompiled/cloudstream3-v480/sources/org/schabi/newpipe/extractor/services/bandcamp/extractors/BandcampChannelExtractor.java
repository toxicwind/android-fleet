package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import j$.util.Collection;
import j$.util.DesugarCollections;
import j$.util.Objects;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;
import org.schabi.newpipe.extractor.services.bandcamp.linkHandler.BandcampChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampChannelExtractor extends ChannelExtractor {
    private JsonObject channelInfo;

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getFeedUrl() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelName() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelUrl() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public long getSubscriberCount() {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public boolean isVerified() throws ParsingException {
        return false;
    }

    public BandcampChannelExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getAvatars() {
        return BandcampExtractorHelper.getImagesFromImageId(this.channelInfo.getLong("bio_image_id"), false);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getBanners() throws ParsingException {
        try {
            return (List) Stream.CC.of(Jsoup.parse(getDownloader().get(Utils.replaceHttpWithHttps(this.channelInfo.getString("bandcamp_url"))).responseBody()).getElementById("customHeader")).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampChannelExtractor$$ExternalSyntheticLambda0
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
                    return Objects.nonNull((Element) obj);
                }
            }).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampChannelExtractor$$ExternalSyntheticLambda1
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
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampChannelExtractor$$ExternalSyntheticLambda2
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
            }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampChannelExtractor$$ExternalSyntheticLambda3
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
                    return BandcampChannelExtractor.lambda$getBanners$2((String) obj);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampChannelExtractor$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BandcampChannelExtractor.lambda$getBanners$3((String) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).collect(Collectors.toUnmodifiableList());
        } catch (IOException | ReCaptchaException e) {
            throw new ParsingException("Could not download artist web site", e);
        }
    }

    static /* synthetic */ boolean lambda$getBanners$2(String str) {
        return !str.isEmpty();
    }

    static /* synthetic */ Image lambda$getBanners$3(String str) {
        return new Image(Utils.replaceHttpWithHttps(str), -1, -1, Image.ResolutionLevel.UNKNOWN);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getDescription() {
        return this.channelInfo.getString("bio");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getParentChannelAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<ListLinkHandler> getTabs() throws ParsingException {
        JsonArray array = this.channelInfo.getArray("discography");
        TabExtractorBuilder tabExtractorBuilder = new TabExtractorBuilder(array);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        boolean z2 = false;
        for (Object obj : array) {
            if (z && z2) {
                break;
            }
            if (obj instanceof JsonObject) {
                String string = ((JsonObject) obj).getString("item_type");
                if (!z && "track".equals(string)) {
                    arrayList.add(new ReadyChannelTabListLinkHandler(getUrl() + BandcampChannelTabLinkHandlerFactory.getUrlSuffix("tracks"), getId(), "tracks", tabExtractorBuilder));
                    z = true;
                }
                if (!z2 && "album".equals(string)) {
                    arrayList.add(new ReadyChannelTabListLinkHandler(getUrl() + BandcampChannelTabLinkHandlerFactory.getUrlSuffix(ChannelTabs.ALBUMS), getId(), ChannelTabs.ALBUMS, tabExtractorBuilder));
                    z2 = true;
                }
            }
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.channelInfo = BandcampExtractorHelper.getArtistDetails(getId());
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return this.channelInfo.getString("name");
    }

    private static final class TabExtractorBuilder implements ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder {
        private final JsonArray discography;

        TabExtractorBuilder(JsonArray jsonArray) {
            this.discography = jsonArray;
        }

        @Override // org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler.ChannelTabExtractorBuilder
        @Nonnull
        public ChannelTabExtractor build(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler) {
            return BandcampChannelTabExtractor.fromDiscography(streamingService, listLinkHandler, this.discography);
        }
    }
}
