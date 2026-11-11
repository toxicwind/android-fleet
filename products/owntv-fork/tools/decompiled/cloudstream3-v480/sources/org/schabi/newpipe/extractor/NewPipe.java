package org.schabi.newpipe.extractor;

import j$.util.Collection;
import j$.util.function.Predicate$CC;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.ExtractorLogger;

/* JADX INFO: loaded from: classes8.dex */
public final class NewPipe {
    private static final String TAG = "NewPipe";
    private static Downloader downloader;
    private static ContentCountry preferredContentCountry;
    private static Localization preferredLocalization;

    private NewPipe() {
    }

    public static void init(Downloader downloader2) {
        ExtractorLogger.d(TAG, "Default init called");
        init(downloader2, Localization.DEFAULT);
    }

    public static void init(Downloader downloader2, Localization localization) {
        ExtractorLogger.d(TAG, "Default init called with localization={}");
        init(downloader2, localization, localization.getCountryCode().isEmpty() ? ContentCountry.DEFAULT : new ContentCountry(localization.getCountryCode()));
    }

    public static void init(Downloader downloader2, Localization localization, ContentCountry contentCountry) {
        ExtractorLogger.d(TAG, "Initializing with downloader={}, localization={}, country={}", downloader2, localization, contentCountry);
        downloader = downloader2;
        preferredLocalization = localization;
        preferredContentCountry = contentCountry;
    }

    public static Downloader getDownloader() {
        return downloader;
    }

    public static List<StreamingService> getServices() {
        return ServiceList.all();
    }

    public static StreamingService getService(final int i) throws ExtractionException {
        return (StreamingService) Collection.EL.stream(ServiceList.all()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.NewPipe$$ExternalSyntheticLambda0
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
                return NewPipe.lambda$getService$0(i, (StreamingService) obj);
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.NewPipe$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return NewPipe.lambda$getService$1(i);
            }
        });
    }

    static /* synthetic */ boolean lambda$getService$0(int i, StreamingService streamingService) {
        return streamingService.getServiceId() == i;
    }

    static /* synthetic */ ExtractionException lambda$getService$1(int i) {
        return new ExtractionException("There's no service with the id = \"" + i + "\"");
    }

    public static StreamingService getService(final String str) throws ExtractionException {
        return (StreamingService) Collection.EL.stream(ServiceList.all()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.NewPipe$$ExternalSyntheticLambda2
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
                return ((StreamingService) obj).getServiceInfo().getName().equals(str);
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.NewPipe$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return NewPipe.lambda$getService$3(str);
            }
        });
    }

    static /* synthetic */ ExtractionException lambda$getService$3(String str) {
        return new ExtractionException("There's no service with the name = \"" + str + "\"");
    }

    public static StreamingService getServiceByUrl(String str) throws ExtractionException {
        for (StreamingService streamingService : ServiceList.all()) {
            if (streamingService.getLinkTypeByUrl(str) != StreamingService.LinkType.NONE) {
                return streamingService;
            }
        }
        throw new ExtractionException("No service can handle the url = \"" + str + "\"");
    }

    public static void setupLocalization(Localization localization) {
        setupLocalization(localization, null);
    }

    public static void setupLocalization(Localization localization, @Nullable ContentCountry contentCountry) {
        ContentCountry contentCountry2;
        preferredLocalization = localization;
        if (contentCountry != null) {
            preferredContentCountry = contentCountry;
            return;
        }
        if (localization.getCountryCode().isEmpty()) {
            contentCountry2 = ContentCountry.DEFAULT;
        } else {
            contentCountry2 = new ContentCountry(localization.getCountryCode());
        }
        preferredContentCountry = contentCountry2;
    }

    @Nonnull
    public static Localization getPreferredLocalization() {
        Localization localization = preferredLocalization;
        return localization == null ? Localization.DEFAULT : localization;
    }

    public static void setPreferredLocalization(Localization localization) {
        preferredLocalization = localization;
    }

    @Nonnull
    public static ContentCountry getPreferredContentCountry() {
        ContentCountry contentCountry = preferredContentCountry;
        return contentCountry == null ? ContentCountry.DEFAULT : contentCountry;
    }

    public static void setPreferredContentCountry(ContentCountry contentCountry) {
        preferredContentCountry = contentCountry;
    }
}
