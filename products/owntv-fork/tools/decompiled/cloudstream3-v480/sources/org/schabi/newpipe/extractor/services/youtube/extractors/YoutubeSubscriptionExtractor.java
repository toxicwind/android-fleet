package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.io.DesugarBufferedReader;
import j$.util.Objects;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.services.youtube.YoutubeService;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionItem;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeSubscriptionExtractor extends SubscriptionExtractor {
    private static final String BASE_CHANNEL_URL = "https://www.youtube.com/channel/";

    public YoutubeSubscriptionExtractor(YoutubeService youtubeService) {
        super(youtubeService, Collections.singletonList(SubscriptionExtractor.ContentSource.INPUT_STREAM));
    }

    @Override // org.schabi.newpipe.extractor.subscription.SubscriptionExtractor
    public String getRelatedUrl() {
        return "https://takeout.google.com/takeout/custom/youtube";
    }

    @Override // org.schabi.newpipe.extractor.subscription.SubscriptionExtractor
    public List<SubscriptionItem> fromInputStream(@Nonnull InputStream inputStream) throws ExtractionException {
        return fromJsonInputStream(inputStream);
    }

    @Override // org.schabi.newpipe.extractor.subscription.SubscriptionExtractor
    public List<SubscriptionItem> fromInputStream(@Nonnull InputStream inputStream, @Nonnull String str) throws ExtractionException {
        str.hashCode();
        switch (str) {
            case "text/comma-separated-values":
            case "text/csv":
            case "csv":
                return fromCsvInputStream(inputStream);
            case "application/zip":
            case "zip":
                return fromZipInputStream(inputStream);
            case "application/json":
            case "json":
                return fromJsonInputStream(inputStream);
            default:
                throw new SubscriptionExtractor.InvalidSourceException("Unsupported content type: " + str);
        }
    }

    public List<SubscriptionItem> fromJsonInputStream(@Nonnull InputStream inputStream) throws ExtractionException {
        try {
            JsonArray jsonArrayFrom = JsonParser.array().from(inputStream);
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Object obj : jsonArrayFrom) {
                if (obj instanceof JsonObject) {
                    JsonObject object = ((JsonObject) obj).getObject("snippet");
                    String string = object.getObject("resourceId").getString("channelId", "");
                    if (string.length() == 24) {
                        arrayList.add(new SubscriptionItem(this.service.getServiceId(), BASE_CHANNEL_URL + string, object.getString("title", "")));
                    }
                }
                z = true;
            }
            if (z && arrayList.isEmpty()) {
                throw new SubscriptionExtractor.InvalidSourceException("Found only invalid channel ids");
            }
            return arrayList;
        } catch (JsonParserException e) {
            throw new SubscriptionExtractor.InvalidSourceException("Invalid json input stream", e);
        }
    }

    public List<SubscriptionItem> fromZipInputStream(@Nonnull InputStream inputStream) throws ExtractionException {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        if (nextEntry.getName().toLowerCase().endsWith(".csv")) {
                            try {
                                List<SubscriptionItem> listFromCsvInputStream = fromCsvInputStream(zipInputStream);
                                if (!listFromCsvInputStream.isEmpty()) {
                                    zipInputStream.close();
                                    return listFromCsvInputStream;
                                }
                            } catch (ExtractionException unused) {
                                continue;
                            }
                        }
                    } else {
                        zipInputStream.close();
                        throw new SubscriptionExtractor.InvalidSourceException("Unable to find a valid subscriptions.csv file (try extracting and selecting the csv file)");
                    }
                } catch (Throwable th) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
                throw new SubscriptionExtractor.InvalidSourceException("Error reading contents of zip file", e);
            }
        } catch (IOException e) {
            throw new SubscriptionExtractor.InvalidSourceException("Error reading contents of zip file", e);
        }
    }

    public List<SubscriptionItem> fromCsvInputStream(@Nonnull InputStream inputStream) throws ExtractionException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                List<SubscriptionItem> list = (List) DesugarBufferedReader.lines(bufferedReader).skip(1L).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((String) obj).split(",");
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor$$ExternalSyntheticLambda1
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
                        return YoutubeSubscriptionExtractor.lambda$fromCsvInputStream$1((String[]) obj);
                    }
                }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.f$0.m10983x2cc59c7a((String[]) obj);
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor$$ExternalSyntheticLambda3
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
                        return Objects.nonNull((SubscriptionItem) obj);
                    }
                }).collect(Collectors.toUnmodifiableList());
                bufferedReader.close();
                return list;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | UncheckedIOException e) {
            throw new SubscriptionExtractor.InvalidSourceException("Error reading CSV file", e);
        }
    }

    static /* synthetic */ boolean lambda$fromCsvInputStream$1(String[] strArr) {
        return strArr.length >= 3;
    }

    /* JADX INFO: renamed from: lambda$fromCsvInputStream$2$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeSubscriptionExtractor, reason: not valid java name */
    /* synthetic */ SubscriptionItem m10983x2cc59c7a(String[] strArr) {
        String strReplace = strArr[1].replace(Utils.HTTP, Utils.HTTPS);
        if (strReplace.startsWith(BASE_CHANNEL_URL)) {
            return new SubscriptionItem(this.service.getServiceId(), strReplace, strArr[2]);
        }
        return null;
    }
}
