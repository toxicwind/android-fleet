package org.schabi.newpipe.extractor.services.peertube.linkHandler;

import j$.util.Collection;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubeTrendingLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final String KIOSK_TRENDING = "Trending";
    private static final PeertubeTrendingLinkHandlerFactory INSTANCE = new PeertubeTrendingLinkHandlerFactory();
    public static final String KIOSK_MOST_LIKED = "Most liked";
    public static final String KIOSK_RECENT = "Recently added";
    public static final String KIOSK_LOCAL = "Local";
    public static final Map<String, String> KIOSK_MAP = UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("Trending", "%s/api/v1/videos?sort=-trending"), new AbstractMap.SimpleEntry(KIOSK_MOST_LIKED, "%s/api/v1/videos?sort=-likes"), new AbstractMap.SimpleEntry(KIOSK_RECENT, "%s/api/v1/videos?sort=-publishedAt"), new AbstractMap.SimpleEntry(KIOSK_LOCAL, "%s/api/v1/videos?sort=-publishedAt&isLocal=true")});

    private PeertubeTrendingLinkHandlerFactory() {
    }

    public static PeertubeTrendingLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return getUrl(str, list, str2, ServiceList.PeerTube.getBaseUrl());
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        return String.format(KIOSK_MAP.get(str), str3);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        final String strReplace = str.replace(ServiceList.PeerTube.getBaseUrl(), "%s");
        if (strReplace.contains("/videos/trending")) {
            return "Trending";
        }
        if (strReplace.contains("/videos/most-liked")) {
            return KIOSK_MOST_LIKED;
        }
        if (strReplace.contains("/videos/recently-added")) {
            return KIOSK_RECENT;
        }
        if (strReplace.contains("/videos/local")) {
            return KIOSK_LOCAL;
        }
        return (String) Collection.EL.stream(KIOSK_MAP.entrySet()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeTrendingLinkHandlerFactory$$ExternalSyntheticLambda1
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
                return strReplace.equals(((Map.Entry) obj).getValue());
            }
        }).findFirst().map(new Function() { // from class: org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeTrendingLinkHandlerFactory$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Map.Entry) obj).getKey();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeTrendingLinkHandlerFactory$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return PeertubeTrendingLinkHandlerFactory.lambda$getId$1();
            }
        });
    }

    static /* synthetic */ ParsingException lambda$getId$1() {
        return new ParsingException("no id found for this url");
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            new URL(str);
            return str.contains("/videos?") || str.contains("/videos/trending") || str.contains("/videos/most-liked") || str.contains("/videos/recently-added") || str.contains("/videos/local");
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
