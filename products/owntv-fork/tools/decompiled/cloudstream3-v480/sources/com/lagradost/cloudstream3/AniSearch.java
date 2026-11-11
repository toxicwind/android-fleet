package com.lagradost.cloudstream3;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch;", "", "data", "Lcom/lagradost/cloudstream3/AniSearch$Data;", "<init>", "(Lcom/lagradost/cloudstream3/AniSearch$Data;)V", "getData", "()Lcom/lagradost/cloudstream3/AniSearch$Data;", "setData", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Data", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AniSearch {

    @JsonProperty("data")
    private Data data;

    /* JADX WARN: Multi-variable type inference failed */
    public AniSearch() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ AniSearch copy$default(AniSearch aniSearch, Data data, int i, Object obj) {
        if ((i & 1) != 0) {
            data = aniSearch.data;
        }
        return aniSearch.copy(data);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Data getData() {
        return this.data;
    }

    public final AniSearch copy(@JsonProperty("data") Data data) {
        return new AniSearch(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AniSearch) && Intrinsics.areEqual(this.data, ((AniSearch) other).data);
    }

    public int hashCode() {
        Data data = this.data;
        if (data == null) {
            return 0;
        }
        return data.hashCode();
    }

    public String toString() {
        return "AniSearch(data=" + this.data + ')';
    }

    public AniSearch(@JsonProperty("data") Data data) {
        this.data = data;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ AniSearch(Data data, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Data(null, 1, 0 == true ? 1 : 0) : data);
    }

    public final Data getData() {
        return this.data;
    }

    public final void setData(Data data) {
        this.data = data;
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch$Data;", "", "page", "Lcom/lagradost/cloudstream3/AniSearch$Data$Page;", "<init>", "(Lcom/lagradost/cloudstream3/AniSearch$Data$Page;)V", "getPage", "()Lcom/lagradost/cloudstream3/AniSearch$Data$Page;", "setPage", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Page", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Data {

        @JsonProperty("Page")
        private Page page;

        /* JADX WARN: Multi-variable type inference failed */
        public Data() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Data copy$default(Data data, Page page, int i, Object obj) {
            if ((i & 1) != 0) {
                page = data.page;
            }
            return data.copy(page);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Page getPage() {
            return this.page;
        }

        public final Data copy(@JsonProperty("Page") Page page) {
            return new Data(page);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.page, ((Data) other).page);
        }

        public int hashCode() {
            Page page = this.page;
            if (page == null) {
                return 0;
            }
            return page.hashCode();
        }

        public String toString() {
            return "Data(page=" + this.page + ')';
        }

        public Data(@JsonProperty("Page") Page page) {
            this.page = page;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ Data(Page page, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Page(null, 1, 0 == true ? 1 : 0) : page);
        }

        public final Page getPage() {
            return this.page;
        }

        public final void setPage(Page page) {
            this.page = page;
        }

        /* JADX INFO: compiled from: MainAPI.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0014B!\u0012\u0018\b\u0003\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\f\u001a\u00020\u00002\u0018\b\u0003\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R.\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch$Data$Page;", "", LinkHeader.Parameters.Media, "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "getMedia", "()Ljava/util/ArrayList;", "setMedia", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Media", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Page {

            @JsonProperty(LinkHeader.Parameters.Media)
            private ArrayList<Media> media;

            /* JADX WARN: Multi-variable type inference failed */
            public Page() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Page copy$default(Page page, ArrayList arrayList, int i, Object obj) {
                if ((i & 1) != 0) {
                    arrayList = page.media;
                }
                return page.copy(arrayList);
            }

            public final ArrayList<Media> component1() {
                return this.media;
            }

            public final Page copy(@JsonProperty(LinkHeader.Parameters.Media) ArrayList<Media> media) {
                Intrinsics.checkNotNullParameter(media, "media");
                return new Page(media);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Page) && Intrinsics.areEqual(this.media, ((Page) other).media);
            }

            public int hashCode() {
                return this.media.hashCode();
            }

            public String toString() {
                return "Page(media=" + this.media + ')';
            }

            public Page(@JsonProperty(LinkHeader.Parameters.Media) ArrayList<Media> media) {
                Intrinsics.checkNotNullParameter(media, "media");
                this.media = media;
            }

            public /* synthetic */ Page(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? new ArrayList() : arrayList);
            }

            public final ArrayList<Media> getMedia() {
                return this.media;
            }

            public final void setMedia(ArrayList<Media> arrayList) {
                Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
                this.media = arrayList;
            }

            /* JADX INFO: compiled from: MainAPI.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001:\u000245B[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010*\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\tHÆ\u0003Jb\u0010-\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010.J\u0014\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u00020\u0005HÖ\u0081\u0004J\n\u00103\u001a\u00020\tHÖ\u0081\u0004R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u0016R \u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010\f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001f¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media;", "", "title", "Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;", "id", "", "idMal", "seasonYear", "format", "", "coverImage", "Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;", "bannerImage", "<init>", "(Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;Ljava/lang/String;)V", "getTitle", "()Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;", "setTitle", "(Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getIdMal", "setIdMal", "getSeasonYear", "setSeasonYear", "getFormat", "()Ljava/lang/String;", "setFormat", "(Ljava/lang/String;)V", "getCoverImage", "()Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;", "setCoverImage", "(Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;)V", "getBannerImage", "setBannerImage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;Ljava/lang/String;)Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media;", "equals", "", "other", "hashCode", "toString", "CoverImage", "Title", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class Media {

                @JsonProperty("bannerImage")
                private String bannerImage;

                @JsonProperty("coverImage")
                private CoverImage coverImage;

                @JsonProperty("format")
                private String format;

                @JsonProperty("id")
                private Integer id;

                @JsonProperty("idMal")
                private Integer idMal;

                @JsonProperty("seasonYear")
                private Integer seasonYear;

                @JsonProperty("title")
                private Title title;

                public Media() {
                    this(null, null, null, null, null, null, null, 127, null);
                }

                public static /* synthetic */ Media copy$default(Media media, Title title, Integer num, Integer num2, Integer num3, String str, CoverImage coverImage, String str2, int i, Object obj) {
                    if ((i & 1) != 0) {
                        title = media.title;
                    }
                    if ((i & 2) != 0) {
                        num = media.id;
                    }
                    if ((i & 4) != 0) {
                        num2 = media.idMal;
                    }
                    if ((i & 8) != 0) {
                        num3 = media.seasonYear;
                    }
                    if ((i & 16) != 0) {
                        str = media.format;
                    }
                    if ((i & 32) != 0) {
                        coverImage = media.coverImage;
                    }
                    if ((i & 64) != 0) {
                        str2 = media.bannerImage;
                    }
                    CoverImage coverImage2 = coverImage;
                    String str3 = str2;
                    String str4 = str;
                    Integer num4 = num2;
                    return media.copy(title, num, num4, num3, str4, coverImage2, str3);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final Title getTitle() {
                    return this.title;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final Integer getId() {
                    return this.id;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                public final Integer getIdMal() {
                    return this.idMal;
                }

                /* JADX INFO: renamed from: component4, reason: from getter */
                public final Integer getSeasonYear() {
                    return this.seasonYear;
                }

                /* JADX INFO: renamed from: component5, reason: from getter */
                public final String getFormat() {
                    return this.format;
                }

                /* JADX INFO: renamed from: component6, reason: from getter */
                public final CoverImage getCoverImage() {
                    return this.coverImage;
                }

                /* JADX INFO: renamed from: component7, reason: from getter */
                public final String getBannerImage() {
                    return this.bannerImage;
                }

                public final Media copy(@JsonProperty("title") Title title, @JsonProperty("id") Integer id, @JsonProperty("idMal") Integer idMal, @JsonProperty("seasonYear") Integer seasonYear, @JsonProperty("format") String format, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("bannerImage") String bannerImage) {
                    return new Media(title, id, idMal, seasonYear, format, coverImage, bannerImage);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Media)) {
                        return false;
                    }
                    Media media = (Media) other;
                    return Intrinsics.areEqual(this.title, media.title) && Intrinsics.areEqual(this.id, media.id) && Intrinsics.areEqual(this.idMal, media.idMal) && Intrinsics.areEqual(this.seasonYear, media.seasonYear) && Intrinsics.areEqual(this.format, media.format) && Intrinsics.areEqual(this.coverImage, media.coverImage) && Intrinsics.areEqual(this.bannerImage, media.bannerImage);
                }

                public int hashCode() {
                    Title title = this.title;
                    int iHashCode = (title == null ? 0 : title.hashCode()) * 31;
                    Integer num = this.id;
                    int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
                    Integer num2 = this.idMal;
                    int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
                    Integer num3 = this.seasonYear;
                    int iHashCode4 = (iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
                    String str = this.format;
                    int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
                    CoverImage coverImage = this.coverImage;
                    int iHashCode6 = (iHashCode5 + (coverImage == null ? 0 : coverImage.hashCode())) * 31;
                    String str2 = this.bannerImage;
                    return iHashCode6 + (str2 != null ? str2.hashCode() : 0);
                }

                public String toString() {
                    return "Media(title=" + this.title + ", id=" + this.id + ", idMal=" + this.idMal + ", seasonYear=" + this.seasonYear + ", format=" + this.format + ", coverImage=" + this.coverImage + ", bannerImage=" + this.bannerImage + ')';
                }

                public Media(@JsonProperty("title") Title title, @JsonProperty("id") Integer num, @JsonProperty("idMal") Integer num2, @JsonProperty("seasonYear") Integer num3, @JsonProperty("format") String str, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("bannerImage") String str2) {
                    this.title = title;
                    this.id = num;
                    this.idMal = num2;
                    this.seasonYear = num3;
                    this.format = str;
                    this.coverImage = coverImage;
                    this.bannerImage = str2;
                }

                public /* synthetic */ Media(Title title, Integer num, Integer num2, Integer num3, String str, CoverImage coverImage, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? null : title, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : str, (i & 32) != 0 ? null : coverImage, (i & 64) != 0 ? null : str2);
                }

                public final Title getTitle() {
                    return this.title;
                }

                public final void setTitle(Title title) {
                    this.title = title;
                }

                public final Integer getId() {
                    return this.id;
                }

                public final void setId(Integer num) {
                    this.id = num;
                }

                public final Integer getIdMal() {
                    return this.idMal;
                }

                public final void setIdMal(Integer num) {
                    this.idMal = num;
                }

                public final Integer getSeasonYear() {
                    return this.seasonYear;
                }

                public final void setSeasonYear(Integer num) {
                    this.seasonYear = num;
                }

                public final String getFormat() {
                    return this.format;
                }

                public final void setFormat(String str) {
                    this.format = str;
                }

                public final CoverImage getCoverImage() {
                    return this.coverImage;
                }

                public final void setCoverImage(CoverImage coverImage) {
                    this.coverImage = coverImage;
                }

                public final String getBannerImage() {
                    return this.bannerImage;
                }

                public final void setBannerImage(String str) {
                    this.bannerImage = str;
                }

                /* JADX INFO: compiled from: MainAPI.kt */
                @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$CoverImage;", "", "extraLarge", "", "large", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getExtraLarge", "()Ljava/lang/String;", "setExtraLarge", "(Ljava/lang/String;)V", "getLarge", "setLarge", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class CoverImage {

                    @JsonProperty("extraLarge")
                    private String extraLarge;

                    @JsonProperty("large")
                    private String large;

                    /* JADX WARN: Multi-variable type inference failed */
                    public CoverImage() {
                        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                    }

                    public static /* synthetic */ CoverImage copy$default(CoverImage coverImage, String str, String str2, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = coverImage.extraLarge;
                        }
                        if ((i & 2) != 0) {
                            str2 = coverImage.large;
                        }
                        return coverImage.copy(str, str2);
                    }

                    /* JADX INFO: renamed from: component1, reason: from getter */
                    public final String getExtraLarge() {
                        return this.extraLarge;
                    }

                    /* JADX INFO: renamed from: component2, reason: from getter */
                    public final String getLarge() {
                        return this.large;
                    }

                    public final CoverImage copy(@JsonProperty("extraLarge") String extraLarge, @JsonProperty("large") String large) {
                        return new CoverImage(extraLarge, large);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof CoverImage)) {
                            return false;
                        }
                        CoverImage coverImage = (CoverImage) other;
                        return Intrinsics.areEqual(this.extraLarge, coverImage.extraLarge) && Intrinsics.areEqual(this.large, coverImage.large);
                    }

                    public int hashCode() {
                        String str = this.extraLarge;
                        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                        String str2 = this.large;
                        return iHashCode + (str2 != null ? str2.hashCode() : 0);
                    }

                    public String toString() {
                        return "CoverImage(extraLarge=" + this.extraLarge + ", large=" + this.large + ')';
                    }

                    public CoverImage(@JsonProperty("extraLarge") String str, @JsonProperty("large") String str2) {
                        this.extraLarge = str;
                        this.large = str2;
                    }

                    public /* synthetic */ CoverImage(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
                    }

                    public final String getExtraLarge() {
                        return this.extraLarge;
                    }

                    public final void setExtraLarge(String str) {
                        this.extraLarge = str;
                    }

                    public final String getLarge() {
                        return this.large;
                    }

                    public final void setLarge(String str) {
                        this.large = str;
                    }
                }

                /* JADX INFO: compiled from: MainAPI.kt */
                @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/AniSearch$Data$Page$Media$Title;", "", "romaji", "", "english", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getRomaji", "()Ljava/lang/String;", "setRomaji", "(Ljava/lang/String;)V", "getEnglish", "setEnglish", "isMatchingTitles", "", "title", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class Title {

                    @JsonProperty("english")
                    private String english;

                    @JsonProperty("romaji")
                    private String romaji;

                    /* JADX WARN: Multi-variable type inference failed */
                    public Title() {
                        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                    }

                    public static /* synthetic */ Title copy$default(Title title, String str, String str2, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = title.romaji;
                        }
                        if ((i & 2) != 0) {
                            str2 = title.english;
                        }
                        return title.copy(str, str2);
                    }

                    /* JADX INFO: renamed from: component1, reason: from getter */
                    public final String getRomaji() {
                        return this.romaji;
                    }

                    /* JADX INFO: renamed from: component2, reason: from getter */
                    public final String getEnglish() {
                        return this.english;
                    }

                    public final Title copy(@JsonProperty("romaji") String romaji, @JsonProperty("english") String english) {
                        return new Title(romaji, english);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof Title)) {
                            return false;
                        }
                        Title title = (Title) other;
                        return Intrinsics.areEqual(this.romaji, title.romaji) && Intrinsics.areEqual(this.english, title.english);
                    }

                    public int hashCode() {
                        String str = this.romaji;
                        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                        String str2 = this.english;
                        return iHashCode + (str2 != null ? str2.hashCode() : 0);
                    }

                    public String toString() {
                        return "Title(romaji=" + this.romaji + ", english=" + this.english + ')';
                    }

                    public Title(@JsonProperty("romaji") String str, @JsonProperty("english") String str2) {
                        this.romaji = str;
                        this.english = str2;
                    }

                    public /* synthetic */ Title(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
                    }

                    public final String getRomaji() {
                        return this.romaji;
                    }

                    public final void setRomaji(String str) {
                        this.romaji = str;
                    }

                    public final String getEnglish() {
                        return this.english;
                    }

                    public final void setEnglish(String str) {
                        this.english = str;
                    }

                    public final boolean isMatchingTitles(String title) {
                        if (title == null) {
                            return false;
                        }
                        return StringsKt.equals(this.english, title, true) || StringsKt.equals(this.romaji, title, true);
                    }
                }
            }
        }
    }
}
