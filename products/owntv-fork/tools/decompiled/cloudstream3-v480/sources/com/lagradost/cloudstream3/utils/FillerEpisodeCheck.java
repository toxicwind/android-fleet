package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.ExoPlayer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.AnimeDB;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.result.ResultViewModel2Kt;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: FillerEpisodeCheck.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005\u0017\u0018\u0019\u001a\u001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u0005J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\b\u0010\f\u001a\u00020\u0007H\u0007J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\f\u0010\u0016\u001a\u00020\u000f*\u00020\u000fH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00100\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck;", "", "<init>", "()V", "toClassDir", "", "database", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Database;", "strip", "Lkotlin/text/Regex;", "stripName", "name", "loadJson", "loadCache", "Ljava/util/HashMap;", "", "Ljava/util/HashSet;", "getLoadCache", "()Ljava/util/HashMap;", "getFillerEpisodes", "data", "Lcom/lagradost/cloudstream3/LoadResponse;", "calc", "Show", "MappingRoot", "Season", "CombinedMedia", "Database", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FillerEpisodeCheck {
    private static Database database;
    public static final FillerEpisodeCheck INSTANCE = new FillerEpisodeCheck();
    private static final Regex strip = new Regex("[ :\\-.!]");
    private static final HashMap<Integer, HashSet<Integer>> loadCache = new HashMap<>();

    private FillerEpisodeCheck() {
    }

    public final String toClassDir(String str) {
        if (str == null) {
            str = AbstractJsonLexerKt.NULL;
        }
        return str + "cache" + calc(RangesKt.random(new IntRange(6, 10), Random.INSTANCE));
    }

    /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 52\u00020\u0001:\u000245B[\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u000b\u0010\fBo\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J]\u0010&\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0003\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0003\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0001¢\u0006\u0002\b3R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0019R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u0019R\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u0019¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;", "", "slug", "", "title", "filler", "Ljava/util/ArrayList;", "", "mixedCanon", "mangaCanon", "animeCanon", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSlug$annotations", "()V", "getSlug", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "getFiller$annotations", "getFiller", "()Ljava/util/ArrayList;", "getMixedCanon$annotations", "getMixedCanon", "getMangaCanon$annotations", "getMangaCanon", "getAnimeCanon$annotations", "getAnimeCanon", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Show {

        @JsonProperty("animeCanon")
        private final ArrayList<Integer> animeCanon;

        @JsonProperty("filler")
        private final ArrayList<Integer> filler;

        @JsonProperty("mangaCanon")
        private final ArrayList<Integer> mangaCanon;

        @JsonProperty("mixedCanon")
        private final ArrayList<Integer> mixedCanon;

        @JsonProperty("slug")
        private final String slug;

        @JsonProperty("title")
        private final String title;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$Show$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FillerEpisodeCheck.Show._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$Show$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FillerEpisodeCheck.Show._childSerializers$_anonymous_$0();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$Show$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FillerEpisodeCheck.Show._childSerializers$_anonymous_$1();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$Show$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FillerEpisodeCheck.Show._childSerializers$_anonymous_$2();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(IntSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(IntSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new ArrayListSerializer(IntSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$2() {
            return new ArrayListSerializer(IntSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Show copy$default(Show show, String str, String str2, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = show.slug;
            }
            if ((i & 2) != 0) {
                str2 = show.title;
            }
            if ((i & 4) != 0) {
                arrayList = show.filler;
            }
            if ((i & 8) != 0) {
                arrayList2 = show.mixedCanon;
            }
            if ((i & 16) != 0) {
                arrayList3 = show.mangaCanon;
            }
            if ((i & 32) != 0) {
                arrayList4 = show.animeCanon;
            }
            ArrayList arrayList5 = arrayList3;
            ArrayList arrayList6 = arrayList4;
            return show.copy(str, str2, arrayList, arrayList2, arrayList5, arrayList6);
        }

        @SerialName("animeCanon")
        public static /* synthetic */ void getAnimeCanon$annotations() {
        }

        @SerialName("filler")
        public static /* synthetic */ void getFiller$annotations() {
        }

        @SerialName("mangaCanon")
        public static /* synthetic */ void getMangaCanon$annotations() {
        }

        @SerialName("mixedCanon")
        public static /* synthetic */ void getMixedCanon$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final ArrayList<Integer> component3() {
            return this.filler;
        }

        public final ArrayList<Integer> component4() {
            return this.mixedCanon;
        }

        public final ArrayList<Integer> component5() {
            return this.mangaCanon;
        }

        public final ArrayList<Integer> component6() {
            return this.animeCanon;
        }

        public final Show copy(@JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("filler") ArrayList<Integer> filler, @JsonProperty("mixedCanon") ArrayList<Integer> mixedCanon, @JsonProperty("mangaCanon") ArrayList<Integer> mangaCanon, @JsonProperty("animeCanon") ArrayList<Integer> animeCanon) {
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(filler, "filler");
            Intrinsics.checkNotNullParameter(mixedCanon, "mixedCanon");
            Intrinsics.checkNotNullParameter(mangaCanon, "mangaCanon");
            Intrinsics.checkNotNullParameter(animeCanon, "animeCanon");
            return new Show(slug, title, filler, mixedCanon, mangaCanon, animeCanon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Show)) {
                return false;
            }
            Show show = (Show) other;
            return Intrinsics.areEqual(this.slug, show.slug) && Intrinsics.areEqual(this.title, show.title) && Intrinsics.areEqual(this.filler, show.filler) && Intrinsics.areEqual(this.mixedCanon, show.mixedCanon) && Intrinsics.areEqual(this.mangaCanon, show.mangaCanon) && Intrinsics.areEqual(this.animeCanon, show.animeCanon);
        }

        public int hashCode() {
            return (((((((((this.slug.hashCode() * 31) + this.title.hashCode()) * 31) + this.filler.hashCode()) * 31) + this.mixedCanon.hashCode()) * 31) + this.mangaCanon.hashCode()) * 31) + this.animeCanon.hashCode();
        }

        public String toString() {
            return "Show(slug=" + this.slug + ", title=" + this.title + ", filler=" + this.filler + ", mixedCanon=" + this.mixedCanon + ", mangaCanon=" + this.mangaCanon + ", animeCanon=" + this.animeCanon + ')';
        }

        /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Show> serializer() {
                return FillerEpisodeCheck$Show$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Show(int i, String str, String str2, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, FillerEpisodeCheck$Show$$serializer.INSTANCE.getDescriptor());
            }
            this.slug = str;
            this.title = str2;
            this.filler = arrayList;
            this.mixedCanon = arrayList2;
            this.mangaCanon = arrayList3;
            this.animeCanon = arrayList4;
        }

        public Show(@JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("filler") ArrayList<Integer> filler, @JsonProperty("mixedCanon") ArrayList<Integer> mixedCanon, @JsonProperty("mangaCanon") ArrayList<Integer> mangaCanon, @JsonProperty("animeCanon") ArrayList<Integer> animeCanon) {
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(filler, "filler");
            Intrinsics.checkNotNullParameter(mixedCanon, "mixedCanon");
            Intrinsics.checkNotNullParameter(mangaCanon, "mangaCanon");
            Intrinsics.checkNotNullParameter(animeCanon, "animeCanon");
            this.slug = slug;
            this.title = title;
            this.filler = filler;
            this.mixedCanon = mixedCanon;
            this.mangaCanon = mangaCanon;
            this.animeCanon = animeCanon;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Show self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.slug);
            output.encodeStringElement(serialDesc, 1, self.title);
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.filler);
            output.encodeSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.mixedCanon);
            output.encodeSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.mangaCanon);
            output.encodeSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.animeCanon);
        }

        public final String getSlug() {
            return this.slug;
        }

        public final String getTitle() {
            return this.title;
        }

        public final ArrayList<Integer> getFiller() {
            return this.filler;
        }

        public final ArrayList<Integer> getMixedCanon() {
            return this.mixedCanon;
        }

        public final ArrayList<Integer> getMangaCanon() {
            return this.mangaCanon;
        }

        public final ArrayList<Integer> getAnimeCanon() {
            return this.animeCanon;
        }
    }

    /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ]2\u00020\u0001:\u0002\\]B»\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015B±\u0001\b\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u0014\u0010\u001aJ\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010@\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010A\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010K\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010L\u001a\u0004\u0018\u00010\u0013HÆ\u0003JÂ\u0001\u0010M\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u0010NJ\u0014\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010R\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010S\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u00002\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020ZH\u0001¢\u0006\u0002\b[R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010!R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b#\u0010\u001c\u001a\u0004\b$\u0010!R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010!R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b'\u0010\u001c\u001a\u0004\b(\u0010!R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001c\u001a\u0004\b*\u0010\u001eR \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b+\u0010\u001c\u001a\u0004\b,\u0010!R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001c\u001a\u0004\b.\u0010\u001eR \u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b/\u0010\u001c\u001a\u0004\b0\u0010!R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b1\u0010\u001c\u001a\u0004\b2\u0010!R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b3\u0010\u001c\u001a\u0004\b4\u0010!R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b5\u0010\u001c\u001a\u0004\b6\u0010!R \u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b7\u0010\u001c\u001a\u0004\b8\u0010!R \u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\"\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010!R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u0010\u001c\u001a\u0004\b<\u0010=¨\u0006^"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "", "type", "", "anidbId", "", "anilistId", "animecountdownId", "animenewsnetworkId", "animePlanetId", "anisearchId", "imdbId", "kitsuId", "livechartId", "malId", "simklId", "themoviedbId", "tvdbId", "season", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType$annotations", "()V", "getType", "()Ljava/lang/String;", "getAnidbId$annotations", "getAnidbId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAnilistId$annotations", "getAnilistId", "getAnimecountdownId$annotations", "getAnimecountdownId", "getAnimenewsnetworkId$annotations", "getAnimenewsnetworkId", "getAnimePlanetId$annotations", "getAnimePlanetId", "getAnisearchId$annotations", "getAnisearchId", "getImdbId$annotations", "getImdbId", "getKitsuId$annotations", "getKitsuId", "getLivechartId$annotations", "getLivechartId", "getMalId$annotations", "getMalId", "getSimklId$annotations", "getSimklId", "getThemoviedbId$annotations", "getThemoviedbId", "getTvdbId$annotations", "getTvdbId", "getSeason$annotations", "getSeason", "()Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;)Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MappingRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("anidb_id")
        private final Long anidbId;

        @JsonProperty("anilist_id")
        private final Long anilistId;

        @JsonProperty("anime-planet_id")
        private final String animePlanetId;

        @JsonProperty("animecountdown_id")
        private final Long animecountdownId;

        @JsonProperty("animenewsnetwork_id")
        private final Long animenewsnetworkId;

        @JsonProperty("anisearch_id")
        private final Long anisearchId;

        @JsonProperty("imdb_id")
        private final String imdbId;

        @JsonProperty("kitsu_id")
        private final Long kitsuId;

        @JsonProperty("livechart_id")
        private final Long livechartId;

        @JsonProperty("mal_id")
        private final Long malId;

        @JsonProperty("season")
        private final Season season;

        @JsonProperty("simkl_id")
        private final Long simklId;

        @JsonProperty("themoviedb_id")
        private final Long themoviedbId;

        @JsonProperty("tvdb_id")
        private final Long tvdbId;

        @JsonProperty("type")
        private final String type;

        @SerialName("anidb_id")
        public static /* synthetic */ void getAnidbId$annotations() {
        }

        @SerialName("anilist_id")
        public static /* synthetic */ void getAnilistId$annotations() {
        }

        @SerialName("anime-planet_id")
        public static /* synthetic */ void getAnimePlanetId$annotations() {
        }

        @SerialName("animecountdown_id")
        public static /* synthetic */ void getAnimecountdownId$annotations() {
        }

        @SerialName("animenewsnetwork_id")
        public static /* synthetic */ void getAnimenewsnetworkId$annotations() {
        }

        @SerialName("anisearch_id")
        public static /* synthetic */ void getAnisearchId$annotations() {
        }

        @SerialName("imdb_id")
        public static /* synthetic */ void getImdbId$annotations() {
        }

        @SerialName("kitsu_id")
        public static /* synthetic */ void getKitsuId$annotations() {
        }

        @SerialName("livechart_id")
        public static /* synthetic */ void getLivechartId$annotations() {
        }

        @SerialName("mal_id")
        public static /* synthetic */ void getMalId$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("simkl_id")
        public static /* synthetic */ void getSimklId$annotations() {
        }

        @SerialName("themoviedb_id")
        public static /* synthetic */ void getThemoviedbId$annotations() {
        }

        @SerialName("tvdb_id")
        public static /* synthetic */ void getTvdbId$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Long getLivechartId() {
            return this.livechartId;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Long getMalId() {
            return this.malId;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Long getSimklId() {
            return this.simklId;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Long getThemoviedbId() {
            return this.themoviedbId;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Long getTvdbId() {
            return this.tvdbId;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Season getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getAnidbId() {
            return this.anidbId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getAnilistId() {
            return this.anilistId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getAnimecountdownId() {
            return this.animecountdownId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Long getAnimenewsnetworkId() {
            return this.animenewsnetworkId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getAnimePlanetId() {
            return this.animePlanetId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Long getAnisearchId() {
            return this.anisearchId;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Long getKitsuId() {
            return this.kitsuId;
        }

        public final MappingRoot copy(@JsonProperty("type") String type, @JsonProperty("anidb_id") Long anidbId, @JsonProperty("anilist_id") Long anilistId, @JsonProperty("animecountdown_id") Long animecountdownId, @JsonProperty("animenewsnetwork_id") Long animenewsnetworkId, @JsonProperty("anime-planet_id") String animePlanetId, @JsonProperty("anisearch_id") Long anisearchId, @JsonProperty("imdb_id") String imdbId, @JsonProperty("kitsu_id") Long kitsuId, @JsonProperty("livechart_id") Long livechartId, @JsonProperty("mal_id") Long malId, @JsonProperty("simkl_id") Long simklId, @JsonProperty("themoviedb_id") Long themoviedbId, @JsonProperty("tvdb_id") Long tvdbId, @JsonProperty("season") Season season) {
            return new MappingRoot(type, anidbId, anilistId, animecountdownId, animenewsnetworkId, animePlanetId, anisearchId, imdbId, kitsuId, livechartId, malId, simklId, themoviedbId, tvdbId, season);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MappingRoot)) {
                return false;
            }
            MappingRoot mappingRoot = (MappingRoot) other;
            return Intrinsics.areEqual(this.type, mappingRoot.type) && Intrinsics.areEqual(this.anidbId, mappingRoot.anidbId) && Intrinsics.areEqual(this.anilistId, mappingRoot.anilistId) && Intrinsics.areEqual(this.animecountdownId, mappingRoot.animecountdownId) && Intrinsics.areEqual(this.animenewsnetworkId, mappingRoot.animenewsnetworkId) && Intrinsics.areEqual(this.animePlanetId, mappingRoot.animePlanetId) && Intrinsics.areEqual(this.anisearchId, mappingRoot.anisearchId) && Intrinsics.areEqual(this.imdbId, mappingRoot.imdbId) && Intrinsics.areEqual(this.kitsuId, mappingRoot.kitsuId) && Intrinsics.areEqual(this.livechartId, mappingRoot.livechartId) && Intrinsics.areEqual(this.malId, mappingRoot.malId) && Intrinsics.areEqual(this.simklId, mappingRoot.simklId) && Intrinsics.areEqual(this.themoviedbId, mappingRoot.themoviedbId) && Intrinsics.areEqual(this.tvdbId, mappingRoot.tvdbId) && Intrinsics.areEqual(this.season, mappingRoot.season);
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Long l = this.anidbId;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.anilistId;
            int iHashCode3 = (iHashCode2 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Long l3 = this.animecountdownId;
            int iHashCode4 = (iHashCode3 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.animenewsnetworkId;
            int iHashCode5 = (iHashCode4 + (l4 == null ? 0 : l4.hashCode())) * 31;
            String str2 = this.animePlanetId;
            int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Long l5 = this.anisearchId;
            int iHashCode7 = (iHashCode6 + (l5 == null ? 0 : l5.hashCode())) * 31;
            String str3 = this.imdbId;
            int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Long l6 = this.kitsuId;
            int iHashCode9 = (iHashCode8 + (l6 == null ? 0 : l6.hashCode())) * 31;
            Long l7 = this.livechartId;
            int iHashCode10 = (iHashCode9 + (l7 == null ? 0 : l7.hashCode())) * 31;
            Long l8 = this.malId;
            int iHashCode11 = (iHashCode10 + (l8 == null ? 0 : l8.hashCode())) * 31;
            Long l9 = this.simklId;
            int iHashCode12 = (iHashCode11 + (l9 == null ? 0 : l9.hashCode())) * 31;
            Long l10 = this.themoviedbId;
            int iHashCode13 = (iHashCode12 + (l10 == null ? 0 : l10.hashCode())) * 31;
            Long l11 = this.tvdbId;
            int iHashCode14 = (iHashCode13 + (l11 == null ? 0 : l11.hashCode())) * 31;
            Season season = this.season;
            return iHashCode14 + (season != null ? season.hashCode() : 0);
        }

        public String toString() {
            return "MappingRoot(type=" + this.type + ", anidbId=" + this.anidbId + ", anilistId=" + this.anilistId + ", animecountdownId=" + this.animecountdownId + ", animenewsnetworkId=" + this.animenewsnetworkId + ", animePlanetId=" + this.animePlanetId + ", anisearchId=" + this.anisearchId + ", imdbId=" + this.imdbId + ", kitsuId=" + this.kitsuId + ", livechartId=" + this.livechartId + ", malId=" + this.malId + ", simklId=" + this.simklId + ", themoviedbId=" + this.themoviedbId + ", tvdbId=" + this.tvdbId + ", season=" + this.season + ')';
        }

        /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MappingRoot> serializer() {
                return FillerEpisodeCheck$MappingRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MappingRoot(int i, String str, Long l, Long l2, Long l3, Long l4, String str2, Long l5, String str3, Long l6, Long l7, Long l8, Long l9, Long l10, Long l11, Season season, SerializationConstructorMarker serializationConstructorMarker) {
            if (32767 != (i & 32767)) {
                PluginExceptionsKt.throwMissingFieldException(i, 32767, FillerEpisodeCheck$MappingRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.type = str;
            this.anidbId = l;
            this.anilistId = l2;
            this.animecountdownId = l3;
            this.animenewsnetworkId = l4;
            this.animePlanetId = str2;
            this.anisearchId = l5;
            this.imdbId = str3;
            this.kitsuId = l6;
            this.livechartId = l7;
            this.malId = l8;
            this.simklId = l9;
            this.themoviedbId = l10;
            this.tvdbId = l11;
            this.season = season;
        }

        public MappingRoot(@JsonProperty("type") String str, @JsonProperty("anidb_id") Long l, @JsonProperty("anilist_id") Long l2, @JsonProperty("animecountdown_id") Long l3, @JsonProperty("animenewsnetwork_id") Long l4, @JsonProperty("anime-planet_id") String str2, @JsonProperty("anisearch_id") Long l5, @JsonProperty("imdb_id") String str3, @JsonProperty("kitsu_id") Long l6, @JsonProperty("livechart_id") Long l7, @JsonProperty("mal_id") Long l8, @JsonProperty("simkl_id") Long l9, @JsonProperty("themoviedb_id") Long l10, @JsonProperty("tvdb_id") Long l11, @JsonProperty("season") Season season) {
            this.type = str;
            this.anidbId = l;
            this.anilistId = l2;
            this.animecountdownId = l3;
            this.animenewsnetworkId = l4;
            this.animePlanetId = str2;
            this.anisearchId = l5;
            this.imdbId = str3;
            this.kitsuId = l6;
            this.livechartId = l7;
            this.malId = l8;
            this.simklId = l9;
            this.themoviedbId = l10;
            this.tvdbId = l11;
            this.season = season;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MappingRoot self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.type);
            output.encodeNullableSerializableElement(serialDesc, 1, LongSerializer.INSTANCE, self.anidbId);
            output.encodeNullableSerializableElement(serialDesc, 2, LongSerializer.INSTANCE, self.anilistId);
            output.encodeNullableSerializableElement(serialDesc, 3, LongSerializer.INSTANCE, self.animecountdownId);
            output.encodeNullableSerializableElement(serialDesc, 4, LongSerializer.INSTANCE, self.animenewsnetworkId);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.animePlanetId);
            output.encodeNullableSerializableElement(serialDesc, 6, LongSerializer.INSTANCE, self.anisearchId);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.imdbId);
            output.encodeNullableSerializableElement(serialDesc, 8, LongSerializer.INSTANCE, self.kitsuId);
            output.encodeNullableSerializableElement(serialDesc, 9, LongSerializer.INSTANCE, self.livechartId);
            output.encodeNullableSerializableElement(serialDesc, 10, LongSerializer.INSTANCE, self.malId);
            output.encodeNullableSerializableElement(serialDesc, 11, LongSerializer.INSTANCE, self.simklId);
            output.encodeNullableSerializableElement(serialDesc, 12, LongSerializer.INSTANCE, self.themoviedbId);
            output.encodeNullableSerializableElement(serialDesc, 13, LongSerializer.INSTANCE, self.tvdbId);
            output.encodeNullableSerializableElement(serialDesc, 14, FillerEpisodeCheck$Season$$serializer.INSTANCE, self.season);
        }

        public final String getType() {
            return this.type;
        }

        public final Long getAnidbId() {
            return this.anidbId;
        }

        public final Long getAnilistId() {
            return this.anilistId;
        }

        public final Long getAnimecountdownId() {
            return this.animecountdownId;
        }

        public final Long getAnimenewsnetworkId() {
            return this.animenewsnetworkId;
        }

        public final String getAnimePlanetId() {
            return this.animePlanetId;
        }

        public final Long getAnisearchId() {
            return this.anisearchId;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final Long getKitsuId() {
            return this.kitsuId;
        }

        public final Long getLivechartId() {
            return this.livechartId;
        }

        public final Long getMalId() {
            return this.malId;
        }

        public final Long getSimklId() {
            return this.simklId;
        }

        public final Long getThemoviedbId() {
            return this.themoviedbId;
        }

        public final Long getTvdbId() {
            return this.tvdbId;
        }

        public final Season getSeason() {
            return this.season;
        }
    }

    /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ&\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u000f¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;", "", "tvdb", "", "tmdb", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTvdb$annotations", "()V", "getTvdb", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTmdb$annotations", "getTmdb", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/lang/Long;)Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Season {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("tmdb")
        private final Long tmdb;

        @JsonProperty("tvdb")
        private final Long tvdb;

        public static /* synthetic */ Season copy$default(Season season, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = season.tvdb;
            }
            if ((i & 2) != 0) {
                l2 = season.tmdb;
            }
            return season.copy(l, l2);
        }

        @SerialName("tmdb")
        public static /* synthetic */ void getTmdb$annotations() {
        }

        @SerialName("tvdb")
        public static /* synthetic */ void getTvdb$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getTvdb() {
            return this.tvdb;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getTmdb() {
            return this.tmdb;
        }

        public final Season copy(@JsonProperty("tvdb") Long tvdb, @JsonProperty("tmdb") Long tmdb) {
            return new Season(tvdb, tmdb);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Season)) {
                return false;
            }
            Season season = (Season) other;
            return Intrinsics.areEqual(this.tvdb, season.tvdb) && Intrinsics.areEqual(this.tmdb, season.tmdb);
        }

        public int hashCode() {
            Long l = this.tvdb;
            int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
            Long l2 = this.tmdb;
            return iHashCode + (l2 != null ? l2.hashCode() : 0);
        }

        public String toString() {
            return "Season(tvdb=" + this.tvdb + ", tmdb=" + this.tmdb + ')';
        }

        /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Season;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Season> serializer() {
                return FillerEpisodeCheck$Season$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Season(int i, Long l, Long l2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, FillerEpisodeCheck$Season$$serializer.INSTANCE.getDescriptor());
            }
            this.tvdb = l;
            this.tmdb = l2;
        }

        public Season(@JsonProperty("tvdb") Long l, @JsonProperty("tmdb") Long l2) {
            this.tvdb = l;
            this.tmdb = l2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Season self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.tvdb);
            output.encodeNullableSerializableElement(serialDesc, 1, LongSerializer.INSTANCE, self.tmdb);
        }

        public final Long getTvdb() {
            return this.tvdb;
        }

        public final Long getTmdb() {
            return this.tmdb;
        }
    }

    /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001d\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$CombinedMedia;", "", "mapping", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "show", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;", "<init>", "(Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMapping$annotations", "()V", "getMapping", "()Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "getShow$annotations", "getShow", "()Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Show;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CombinedMedia {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("mapping")
        private final MappingRoot mapping;

        @JsonProperty("show")
        private final Show show;

        public static /* synthetic */ CombinedMedia copy$default(CombinedMedia combinedMedia, MappingRoot mappingRoot, Show show, int i, Object obj) {
            if ((i & 1) != 0) {
                mappingRoot = combinedMedia.mapping;
            }
            if ((i & 2) != 0) {
                show = combinedMedia.show;
            }
            return combinedMedia.copy(mappingRoot, show);
        }

        @SerialName("mapping")
        public static /* synthetic */ void getMapping$annotations() {
        }

        @SerialName("show")
        public static /* synthetic */ void getShow$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MappingRoot getMapping() {
            return this.mapping;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Show getShow() {
            return this.show;
        }

        public final CombinedMedia copy(@JsonProperty("mapping") MappingRoot mapping, @JsonProperty("show") Show show) {
            Intrinsics.checkNotNullParameter(show, "show");
            return new CombinedMedia(mapping, show);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CombinedMedia)) {
                return false;
            }
            CombinedMedia combinedMedia = (CombinedMedia) other;
            return Intrinsics.areEqual(this.mapping, combinedMedia.mapping) && Intrinsics.areEqual(this.show, combinedMedia.show);
        }

        public int hashCode() {
            MappingRoot mappingRoot = this.mapping;
            return ((mappingRoot == null ? 0 : mappingRoot.hashCode()) * 31) + this.show.hashCode();
        }

        public String toString() {
            return "CombinedMedia(mapping=" + this.mapping + ", show=" + this.show + ')';
        }

        /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$CombinedMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$CombinedMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CombinedMedia> serializer() {
                return FillerEpisodeCheck$CombinedMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CombinedMedia(int i, MappingRoot mappingRoot, Show show, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, FillerEpisodeCheck$CombinedMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.mapping = mappingRoot;
            this.show = show;
        }

        public CombinedMedia(@JsonProperty("mapping") MappingRoot mappingRoot, @JsonProperty("show") Show show) {
            Intrinsics.checkNotNullParameter(show, "show");
            this.mapping = mappingRoot;
            this.show = show;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CombinedMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, FillerEpisodeCheck$MappingRoot$$serializer.INSTANCE, self.mapping);
            output.encodeSerializableElement(serialDesc, 1, FillerEpisodeCheck$Show$$serializer.INSTANCE, self.show);
        }

        public final MappingRoot getMapping() {
            return this.mapping;
        }

        public final Show getShow() {
            return this.show;
        }
    }

    /* JADX INFO: compiled from: FillerEpisodeCheck.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u008d\u0001\u0010\u001b\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\nHÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$Database;", "", "mal", "Ljava/util/HashMap;", "", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$CombinedMedia;", "anilist", "kitsu", "tmdb", "imdb", "", "name", "<init>", "(Ljava/util/HashMap;Ljava/util/HashMap;Ljava/util/HashMap;Ljava/util/HashMap;Ljava/util/HashMap;Ljava/util/HashMap;)V", "getMal", "()Ljava/util/HashMap;", "getAnilist", "getKitsu", "getTmdb", "getImdb", "getName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Database {
        private final HashMap<Long, CombinedMedia> anilist;
        private final HashMap<String, CombinedMedia> imdb;
        private final HashMap<Long, CombinedMedia> kitsu;
        private final HashMap<Long, CombinedMedia> mal;
        private final HashMap<String, CombinedMedia> name;
        private final HashMap<Long, CombinedMedia> tmdb;

        public Database() {
            this(null, null, null, null, null, null, 63, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Database copy$default(Database database, HashMap map, HashMap map2, HashMap map3, HashMap map4, HashMap map5, HashMap map6, int i, Object obj) {
            if ((i & 1) != 0) {
                map = database.mal;
            }
            if ((i & 2) != 0) {
                map2 = database.anilist;
            }
            if ((i & 4) != 0) {
                map3 = database.kitsu;
            }
            if ((i & 8) != 0) {
                map4 = database.tmdb;
            }
            if ((i & 16) != 0) {
                map5 = database.imdb;
            }
            if ((i & 32) != 0) {
                map6 = database.name;
            }
            HashMap map7 = map5;
            HashMap map8 = map6;
            return database.copy(map, map2, map3, map4, map7, map8);
        }

        public final HashMap<Long, CombinedMedia> component1() {
            return this.mal;
        }

        public final HashMap<Long, CombinedMedia> component2() {
            return this.anilist;
        }

        public final HashMap<Long, CombinedMedia> component3() {
            return this.kitsu;
        }

        public final HashMap<Long, CombinedMedia> component4() {
            return this.tmdb;
        }

        public final HashMap<String, CombinedMedia> component5() {
            return this.imdb;
        }

        public final HashMap<String, CombinedMedia> component6() {
            return this.name;
        }

        public final Database copy(HashMap<Long, CombinedMedia> mal, HashMap<Long, CombinedMedia> anilist, HashMap<Long, CombinedMedia> kitsu, HashMap<Long, CombinedMedia> tmdb, HashMap<String, CombinedMedia> imdb, HashMap<String, CombinedMedia> name) {
            Intrinsics.checkNotNullParameter(mal, "mal");
            Intrinsics.checkNotNullParameter(anilist, "anilist");
            Intrinsics.checkNotNullParameter(kitsu, "kitsu");
            Intrinsics.checkNotNullParameter(tmdb, "tmdb");
            Intrinsics.checkNotNullParameter(imdb, "imdb");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Database(mal, anilist, kitsu, tmdb, imdb, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Database)) {
                return false;
            }
            Database database = (Database) other;
            return Intrinsics.areEqual(this.mal, database.mal) && Intrinsics.areEqual(this.anilist, database.anilist) && Intrinsics.areEqual(this.kitsu, database.kitsu) && Intrinsics.areEqual(this.tmdb, database.tmdb) && Intrinsics.areEqual(this.imdb, database.imdb) && Intrinsics.areEqual(this.name, database.name);
        }

        public int hashCode() {
            return (((((((((this.mal.hashCode() * 31) + this.anilist.hashCode()) * 31) + this.kitsu.hashCode()) * 31) + this.tmdb.hashCode()) * 31) + this.imdb.hashCode()) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "Database(mal=" + this.mal + ", anilist=" + this.anilist + ", kitsu=" + this.kitsu + ", tmdb=" + this.tmdb + ", imdb=" + this.imdb + ", name=" + this.name + ')';
        }

        public Database(HashMap<Long, CombinedMedia> mal, HashMap<Long, CombinedMedia> anilist, HashMap<Long, CombinedMedia> kitsu, HashMap<Long, CombinedMedia> tmdb, HashMap<String, CombinedMedia> imdb, HashMap<String, CombinedMedia> name) {
            Intrinsics.checkNotNullParameter(mal, "mal");
            Intrinsics.checkNotNullParameter(anilist, "anilist");
            Intrinsics.checkNotNullParameter(kitsu, "kitsu");
            Intrinsics.checkNotNullParameter(tmdb, "tmdb");
            Intrinsics.checkNotNullParameter(imdb, "imdb");
            Intrinsics.checkNotNullParameter(name, "name");
            this.mal = mal;
            this.anilist = anilist;
            this.kitsu = kitsu;
            this.tmdb = tmdb;
            this.imdb = imdb;
            this.name = name;
        }

        public /* synthetic */ Database(HashMap map, HashMap map2, HashMap map3, HashMap map4, HashMap map5, HashMap map6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new HashMap() : map, (i & 2) != 0 ? new HashMap() : map2, (i & 4) != 0 ? new HashMap() : map3, (i & 8) != 0 ? new HashMap() : map4, (i & 16) != 0 ? new HashMap() : map5, (i & 32) != 0 ? new HashMap() : map6);
        }

        public final HashMap<Long, CombinedMedia> getMal() {
            return this.mal;
        }

        public final HashMap<Long, CombinedMedia> getAnilist() {
            return this.anilist;
        }

        public final HashMap<Long, CombinedMedia> getKitsu() {
            return this.kitsu;
        }

        public final HashMap<Long, CombinedMedia> getTmdb() {
            return this.tmdb;
        }

        public final HashMap<String, CombinedMedia> getImdb() {
            return this.imdb;
        }

        public final HashMap<String, CombinedMedia> getName() {
            return this.name;
        }
    }

    public final String stripName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String lowerCase = strip.replace(name, "").toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:23:0x0076  */
    /* JADX WARN: Code duplicated, block: B:30:0x008c A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00b1 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d0 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d6 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00e5 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00fe A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0117 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x0130 A[Catch: all -> 0x0151, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x0042, B:20:0x006f, B:24:0x0077, B:31:0x009d, B:33:0x00b1, B:53:0x0149, B:36:0x00d0, B:38:0x00d6, B:39:0x00df, B:41:0x00e5, B:42:0x00f8, B:44:0x00fe, B:45:0x0111, B:47:0x0117, B:48:0x012a, B:50:0x0130, B:52:0x0136, B:54:0x014d, B:30:0x008c, B:29:0x0087, B:19:0x0065, B:11:0x0038, B:16:0x004a, B:8:0x001f, B:26:0x007b), top: B:61:0x0001, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x0149 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public final synchronized Database loadJson() {
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objDecodeFromString;
        Database database2;
        int i;
        MappingRoot mapping;
        String imdbId;
        Long malId;
        Long anilistId;
        Long kitsuId;
        Season season;
        Long tmdb;
        Database database3 = database;
        if (database3 != null) {
            return database3;
        }
        InputStream databaseStream = AnimeDB.INSTANCE.getDatabaseStream();
        Intrinsics.checkNotNull(databaseStream);
        String text = TextStreamsKt.readText(new InputStreamReader(databaseStream, Charsets.UTF_8));
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(CombinedMedia.class), CombinedMedia.INSTANCE.serializer()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = null;
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                obj = objM8936constructorimpl;
            }
            kSerializer = (KSerializer) obj;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<CombinedMedia[]>() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$loadJson$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<CombinedMedia[]>() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$loadJson$$inlined$parseJson$1
                    });
                }
                database2 = new Database(null, null, null, null, null, null, 63, null);
                for (CombinedMedia combinedMedia : (CombinedMedia[]) objDecodeFromString) {
                    database2.getName().put(stripName(combinedMedia.getShow().getTitle()), combinedMedia);
                    mapping = combinedMedia.getMapping();
                    if (mapping != null) {
                        imdbId = mapping.getImdbId();
                        if (imdbId != null) {
                            database2.getImdb().put(imdbId, combinedMedia);
                        }
                        malId = mapping.getMalId();
                        if (malId != null) {
                            database2.getMal().put(Long.valueOf(malId.longValue()), combinedMedia);
                        }
                        anilistId = mapping.getAnilistId();
                        if (anilistId != null) {
                            database2.getAnilist().put(Long.valueOf(anilistId.longValue()), combinedMedia);
                        }
                        kitsuId = mapping.getKitsuId();
                        if (kitsuId != null) {
                            database2.getKitsu().put(Long.valueOf(kitsuId.longValue()), combinedMedia);
                        }
                        season = mapping.getSeason();
                        if (season == null && (tmdb = season.getTmdb()) != null) {
                            database2.getTmdb().put(Long.valueOf(tmdb.longValue()), combinedMedia);
                        }
                    }
                }
                database = database2;
                return database2;
            }
            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<CombinedMedia[]>() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$loadJson$$inlined$parseJson$1
            });
            database2 = new Database(null, null, null, null, null, null, 63, null);
            while (i < r2) {
                database2.getName().put(stripName(combinedMedia.getShow().getTitle()), combinedMedia);
                mapping = combinedMedia.getMapping();
                if (mapping != null) {
                    imdbId = mapping.getImdbId();
                    if (imdbId != null) {
                        database2.getImdb().put(imdbId, combinedMedia);
                    }
                    malId = mapping.getMalId();
                    if (malId != null) {
                        database2.getMal().put(Long.valueOf(malId.longValue()), combinedMedia);
                    }
                    anilistId = mapping.getAnilistId();
                    if (anilistId != null) {
                        database2.getAnilist().put(Long.valueOf(anilistId.longValue()), combinedMedia);
                    }
                    kitsuId = mapping.getKitsuId();
                    if (kitsuId != null) {
                        database2.getKitsu().put(Long.valueOf(kitsuId.longValue()), combinedMedia);
                    }
                    season = mapping.getSeason();
                    if (season == null) {
                    }
                }
            }
            database = database2;
            return database2;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(CombinedMedia[].class), null, 2, null));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            obj = objM8936constructorimpl;
        }
        kSerializer = (KSerializer) obj;
        if (kSerializer != null) {
            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
            database2 = new Database(null, null, null, null, null, null, 63, null);
            while (i < r2) {
                database2.getName().put(stripName(combinedMedia.getShow().getTitle()), combinedMedia);
                mapping = combinedMedia.getMapping();
                if (mapping != null) {
                    imdbId = mapping.getImdbId();
                    if (imdbId != null) {
                        database2.getImdb().put(imdbId, combinedMedia);
                    }
                    malId = mapping.getMalId();
                    if (malId != null) {
                        database2.getMal().put(Long.valueOf(malId.longValue()), combinedMedia);
                    }
                    anilistId = mapping.getAnilistId();
                    if (anilistId != null) {
                        database2.getAnilist().put(Long.valueOf(anilistId.longValue()), combinedMedia);
                    }
                    kitsuId = mapping.getKitsuId();
                    if (kitsuId != null) {
                        database2.getKitsu().put(Long.valueOf(kitsuId.longValue()), combinedMedia);
                    }
                    season = mapping.getSeason();
                    if (season == null) {
                    }
                }
            }
            database = database2;
            return database2;
        }
        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<CombinedMedia[]>() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$loadJson$$inlined$parseJson$1
        });
        database2 = new Database(null, null, null, null, null, null, 63, null);
        while (i < r2) {
            database2.getName().put(stripName(combinedMedia.getShow().getTitle()), combinedMedia);
            mapping = combinedMedia.getMapping();
            if (mapping != null) {
                imdbId = mapping.getImdbId();
                if (imdbId != null) {
                    database2.getImdb().put(imdbId, combinedMedia);
                }
                malId = mapping.getMalId();
                if (malId != null) {
                    database2.getMal().put(Long.valueOf(malId.longValue()), combinedMedia);
                }
                anilistId = mapping.getAnilistId();
                if (anilistId != null) {
                    database2.getAnilist().put(Long.valueOf(anilistId.longValue()), combinedMedia);
                }
                kitsuId = mapping.getKitsuId();
                if (kitsuId != null) {
                    database2.getKitsu().put(Long.valueOf(kitsuId.longValue()), combinedMedia);
                }
                season = mapping.getSeason();
                if (season == null) {
                }
            }
        }
        database = database2;
        return database2;
        throw th;
    }

    public final HashMap<Integer, HashSet<Integer>> getLoadCache() {
        return loadCache;
    }

    public final synchronized HashSet<Integer> getFillerEpisodes(LoadResponse data) {
        Show show;
        ArrayList<Integer> filler;
        Intrinsics.checkNotNullParameter(data, "data");
        HashSet<Integer> hashSet = null;
        if (data.getType() != TvType.Anime) {
            return null;
        }
        HashMap<Integer, HashSet<Integer>> map = loadCache;
        HashSet<Integer> hashSet2 = map.get(Integer.valueOf(ResultViewModel2Kt.getId(data)));
        if (hashSet2 != null) {
            return hashSet2;
        }
        Database databaseLoadJson = loadJson();
        HashMap<Long, CombinedMedia> mal = databaseLoadJson.getMal();
        String malId = LoadResponse.INSTANCE.getMalId(data);
        CombinedMedia combinedMedia = mal.get(malId != null ? StringsKt.toLongOrNull(malId) : null);
        if (combinedMedia == null) {
            HashMap<Long, CombinedMedia> anilist = databaseLoadJson.getAnilist();
            String aniListId = LoadResponse.INSTANCE.getAniListId(data);
            combinedMedia = anilist.get(aniListId != null ? StringsKt.toLongOrNull(aniListId) : null);
            if (combinedMedia == null) {
                HashMap<Long, CombinedMedia> kitsu = databaseLoadJson.getKitsu();
                String kitsuId = LoadResponse.INSTANCE.getKitsuId(data);
                combinedMedia = kitsu.get(kitsuId != null ? StringsKt.toLongOrNull(kitsuId) : null);
                if (combinedMedia == null && (combinedMedia = databaseLoadJson.getImdb().get(LoadResponse.INSTANCE.getImdbId(data))) == null) {
                    HashMap<Long, CombinedMedia> tmdb = databaseLoadJson.getTmdb();
                    String tMDbId = LoadResponse.INSTANCE.getTMDbId(data);
                    combinedMedia = tmdb.get(tMDbId != null ? StringsKt.toLongOrNull(tMDbId) : null);
                    if (combinedMedia == null) {
                        combinedMedia = databaseLoadJson.getName().get(stripName(data.getName()));
                    }
                }
            }
        }
        if (combinedMedia != null && (show = combinedMedia.getShow()) != null && (filler = show.getFiller()) != null) {
            hashSet = CollectionsKt.toHashSet(filler);
        }
        map.put(Integer.valueOf(ResultViewModel2Kt.getId(data)), hashSet);
        return hashSet;
    }

    private final int calc(final int i) {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 10;
        ThreadsKt.thread$default(false, false, null, null, 0, new Function0() { // from class: com.lagradost.cloudstream3.utils.FillerEpisodeCheck$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FillerEpisodeCheck.calc$lambda$0(i, intRef);
            }
        }, 31, null);
        return intRef.element;
    }

    static final Unit calc$lambda$0(int i, Ref.IntRef intRef) throws InterruptedException {
        Thread.sleep(ExoPlayer.DEFAULT_STUCK_PLAYING_NOT_ENDING_TIMEOUT_MS * i);
        Coroutines.INSTANCE.main(Integer.valueOf(i), new FillerEpisodeCheck$calc$1$1(intRef, i, null));
        return Unit.INSTANCE;
    }
}
