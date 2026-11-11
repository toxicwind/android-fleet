package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.CharsetKt;
import io.ktor.util.TextKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FileContentType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\r\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0004\b\f\u0010\n\u001a\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u000f\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0012\u001a\u00020\u0011*\u00020\u0003H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0013\u0010\u0014\u001a\u00020\u0003*\u00020\u0003H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001aC\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u001a\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u0017*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00190\u0018H\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001d\u001a\u00020\u0003*\u00020\u0001H\u0000¢\u0006\u0004\b\u001d\u0010\u001e\"-\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"-\u0010&\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b0\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010 \u001a\u0004\b%\u0010\"¨\u0006'"}, d2 = {"Lio/ktor/http/ContentType$Companion;", "", "extension", "Lio/ktor/http/ContentType;", "defaultForFileExtension", "(Lio/ktor/http/ContentType$Companion;Ljava/lang/String;)Lio/ktor/http/ContentType;", "path", "defaultForFilePath", "", "fromFilePath", "(Lio/ktor/http/ContentType$Companion;Ljava/lang/String;)Ljava/util/List;", "ext", "fromFileExtension", "fileExtensions", "(Lio/ktor/http/ContentType;)Ljava/util/List;", "selectDefault", "(Ljava/util/List;)Lio/ktor/http/ContentType;", "", "matchApplicationTypeWithCharset", "(Lio/ktor/http/ContentType;)Z", "withCharsetUTF8IfNeeded", "(Lio/ktor/http/ContentType;)Lio/ktor/http/ContentType;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "", "groupByPairs", "(Lkotlin/sequences/Sequence;)Ljava/util/Map;", "toContentType", "(Ljava/lang/String;)Lio/ktor/http/ContentType;", "contentTypesByExtensions$delegate", "Lkotlin/Lazy;", "getContentTypesByExtensions", "()Ljava/util/Map;", "contentTypesByExtensions", "extensionsByContentType$delegate", "getExtensionsByContentType", "extensionsByContentType", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FileContentTypeKt {
    private static final Lazy contentTypesByExtensions$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.FileContentTypeKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return FileContentTypeKt.contentTypesByExtensions_delegate$lambda$0();
        }
    });
    private static final Lazy extensionsByContentType$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.FileContentTypeKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return FileContentTypeKt.groupByPairs(SequencesKt.map(CollectionsKt.asSequence(MimesKt.getMimes()), new Function1() { // from class: io.ktor.http.FileContentTypeKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FileContentTypeKt.extensionsByContentType_delegate$lambda$0$0((Pair) obj);
                }
            }));
        }
    });

    public static final ContentType defaultForFileExtension(ContentType.Companion companion, String extension) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        return selectDefault(fromFileExtension(ContentType.INSTANCE, extension));
    }

    public static final ContentType defaultForFilePath(ContentType.Companion companion, String path) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return selectDefault(fromFilePath(ContentType.INSTANCE, path));
    }

    public static final List<ContentType> fromFilePath(ContentType.Companion companion, String path) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        String str = path;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '.', StringsKt.lastIndexOfAny$default((CharSequence) str, CharsetKt.toCharArray("/\\"), 0, false, 6, (Object) null) + 1, false, 4, (Object) null);
        if (iIndexOf$default == -1) {
            return CollectionsKt.emptyList();
        }
        String strSubstring = path.substring(iIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return fromFileExtension(companion, strSubstring);
    }

    public static final List<ContentType> fromFileExtension(ContentType.Companion companion, String ext) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(ext, "ext");
        for (String lowerCasePreservingASCIIRules = TextKt.toLowerCasePreservingASCIIRules(StringsKt.removePrefix(ext, (CharSequence) ".")); lowerCasePreservingASCIIRules.length() > 0; lowerCasePreservingASCIIRules = StringsKt.substringAfter(lowerCasePreservingASCIIRules, ".", "")) {
            List<ContentType> list = getContentTypesByExtensions().get(lowerCasePreservingASCIIRules);
            if (list != null) {
                return list;
            }
        }
        return CollectionsKt.emptyList();
    }

    public static final List<String> fileExtensions(ContentType contentType) {
        Intrinsics.checkNotNullParameter(contentType, "<this>");
        List<String> list = getExtensionsByContentType().get(contentType);
        if (list != null) {
            return list;
        }
        List<String> list2 = getExtensionsByContentType().get(contentType.withoutParameters());
        return list2 == null ? CollectionsKt.emptyList() : list2;
    }

    private static final Map<String, List<ContentType>> getContentTypesByExtensions() {
        return (Map) contentTypesByExtensions$delegate.getValue();
    }

    static final Map contentTypesByExtensions_delegate$lambda$0() {
        Map mapCaseInsensitiveMap = io.ktor.util.CollectionsKt.caseInsensitiveMap();
        mapCaseInsensitiveMap.putAll(groupByPairs(CollectionsKt.asSequence(MimesKt.getMimes())));
        return mapCaseInsensitiveMap;
    }

    private static final Map<ContentType, List<String>> getExtensionsByContentType() {
        return (Map) extensionsByContentType$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair extensionsByContentType_delegate$lambda$0$0(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        return TuplesKt.to((ContentType) pair.component2(), (String) pair.component1());
    }

    public static final ContentType selectDefault(List<ContentType> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ContentType octetStream = (ContentType) CollectionsKt.firstOrNull((List) list);
        if (octetStream == null) {
            octetStream = ContentType.Application.INSTANCE.getOctetStream();
        }
        return (octetStream.match(ContentType.Text.INSTANCE.getAny()) || octetStream.match(ContentType.Image.INSTANCE.getSVG()) || matchApplicationTypeWithCharset(octetStream)) ? withCharsetUTF8IfNeeded(octetStream) : octetStream;
    }

    private static final boolean matchApplicationTypeWithCharset(ContentType contentType) {
        if (contentType.match(ContentType.Application.INSTANCE.getAny())) {
            return contentType.match(ContentType.Application.INSTANCE.getAtom()) || contentType.match(ContentType.Application.INSTANCE.getJavaScript()) || contentType.match(ContentType.Application.INSTANCE.getRss()) || contentType.match(ContentType.Application.INSTANCE.getXml()) || contentType.match(ContentType.Application.INSTANCE.getXml_Dtd());
        }
        return false;
    }

    private static final ContentType withCharsetUTF8IfNeeded(ContentType contentType) {
        return ContentTypesKt.charset(contentType) != null ? contentType : ContentTypesKt.withCharset(contentType, Charsets.UTF_8);
    }

    public static final ContentType toContentType(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            return ContentType.INSTANCE.parse(str);
        } catch (Throwable th) {
            throw new IllegalArgumentException("Failed to parse " + str, th);
        }
    }

    public static final <A, B> Map<A, List<B>> groupByPairs(Sequence<? extends Pair<? extends A, ? extends B>> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair<? extends A, ? extends B> pair : sequence) {
            A first = pair.getFirst();
            Object obj = linkedHashMap.get(first);
            if (obj == null) {
                obj = (List) new ArrayList();
                linkedHashMap.put(first, obj);
            }
            ((List) obj).add(pair);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(((Pair) it.next()).getSecond());
            }
            linkedHashMap2.put(key, arrayList);
        }
        return linkedHashMap2;
    }
}
