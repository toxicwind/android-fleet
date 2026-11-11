package com.fleeksoft.ksoup.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetaData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b:\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bß\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003Já\u0001\u0010<\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010@\u001a\u00020AHÖ\u0081\u0004J\n\u0010B\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018¨\u0006C"}, d2 = {"Lcom/fleeksoft/ksoup/model/MetaData;", "", "ogTitle", "", "ogSiteName", "ogDescription", "ogImage", "ogUrl", "ogType", "ogLocale", "twitterCard", "twitterTitle", "twitterDescription", "twitterImage", "title", "description", "canonical", "htmlTitle", "author", "favicon", "shortcutIcon", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOgTitle", "()Ljava/lang/String;", "getOgSiteName", "getOgDescription", "getOgImage", "getOgUrl", "getOgType", "getOgLocale", "getTwitterCard", "getTwitterTitle", "getTwitterDescription", "getTwitterImage", "getTitle", "getDescription", "getCanonical", "getHtmlTitle", "getAuthor", "getFavicon", "getShortcutIcon", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MetaData {
    private final String author;
    private final String canonical;
    private final String description;
    private final String favicon;
    private final String htmlTitle;
    private final String ogDescription;
    private final String ogImage;
    private final String ogLocale;
    private final String ogSiteName;
    private final String ogTitle;
    private final String ogType;
    private final String ogUrl;
    private final String shortcutIcon;
    private final String title;
    private final String twitterCard;
    private final String twitterDescription;
    private final String twitterImage;
    private final String twitterTitle;

    public MetaData() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
    }

    public static /* synthetic */ MetaData copy$default(MetaData metaData, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, int i, Object obj) {
        String str19;
        String str20;
        String str21 = (i & 1) != 0 ? metaData.ogTitle : str;
        String str22 = (i & 2) != 0 ? metaData.ogSiteName : str2;
        String str23 = (i & 4) != 0 ? metaData.ogDescription : str3;
        String str24 = (i & 8) != 0 ? metaData.ogImage : str4;
        String str25 = (i & 16) != 0 ? metaData.ogUrl : str5;
        String str26 = (i & 32) != 0 ? metaData.ogType : str6;
        String str27 = (i & 64) != 0 ? metaData.ogLocale : str7;
        String str28 = (i & 128) != 0 ? metaData.twitterCard : str8;
        String str29 = (i & 256) != 0 ? metaData.twitterTitle : str9;
        String str30 = (i & 512) != 0 ? metaData.twitterDescription : str10;
        String str31 = (i & 1024) != 0 ? metaData.twitterImage : str11;
        String str32 = (i & 2048) != 0 ? metaData.title : str12;
        String str33 = (i & 4096) != 0 ? metaData.description : str13;
        String str34 = (i & 8192) != 0 ? metaData.canonical : str14;
        String str35 = str21;
        String str36 = (i & 16384) != 0 ? metaData.htmlTitle : str15;
        String str37 = (i & 32768) != 0 ? metaData.author : str16;
        String str38 = (i & 65536) != 0 ? metaData.favicon : str17;
        if ((i & 131072) != 0) {
            str20 = str38;
            str19 = metaData.shortcutIcon;
        } else {
            str19 = str18;
            str20 = str38;
        }
        return metaData.copy(str35, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32, str33, str34, str36, str37, str20, str19);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOgTitle() {
        return this.ogTitle;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getTwitterDescription() {
        return this.twitterDescription;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTwitterImage() {
        return this.twitterImage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getCanonical() {
        return this.canonical;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getHtmlTitle() {
        return this.htmlTitle;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getAuthor() {
        return this.author;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getFavicon() {
        return this.favicon;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getShortcutIcon() {
        return this.shortcutIcon;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getOgSiteName() {
        return this.ogSiteName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOgDescription() {
        return this.ogDescription;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOgImage() {
        return this.ogImage;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOgUrl() {
        return this.ogUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOgType() {
        return this.ogType;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOgLocale() {
        return this.ogLocale;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getTwitterCard() {
        return this.twitterCard;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getTwitterTitle() {
        return this.twitterTitle;
    }

    public final MetaData copy(String ogTitle, String ogSiteName, String ogDescription, String ogImage, String ogUrl, String ogType, String ogLocale, String twitterCard, String twitterTitle, String twitterDescription, String twitterImage, String title, String description, String canonical, String htmlTitle, String author, String favicon, String shortcutIcon) {
        return new MetaData(ogTitle, ogSiteName, ogDescription, ogImage, ogUrl, ogType, ogLocale, twitterCard, twitterTitle, twitterDescription, twitterImage, title, description, canonical, htmlTitle, author, favicon, shortcutIcon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetaData)) {
            return false;
        }
        MetaData metaData = (MetaData) other;
        return Intrinsics.areEqual(this.ogTitle, metaData.ogTitle) && Intrinsics.areEqual(this.ogSiteName, metaData.ogSiteName) && Intrinsics.areEqual(this.ogDescription, metaData.ogDescription) && Intrinsics.areEqual(this.ogImage, metaData.ogImage) && Intrinsics.areEqual(this.ogUrl, metaData.ogUrl) && Intrinsics.areEqual(this.ogType, metaData.ogType) && Intrinsics.areEqual(this.ogLocale, metaData.ogLocale) && Intrinsics.areEqual(this.twitterCard, metaData.twitterCard) && Intrinsics.areEqual(this.twitterTitle, metaData.twitterTitle) && Intrinsics.areEqual(this.twitterDescription, metaData.twitterDescription) && Intrinsics.areEqual(this.twitterImage, metaData.twitterImage) && Intrinsics.areEqual(this.title, metaData.title) && Intrinsics.areEqual(this.description, metaData.description) && Intrinsics.areEqual(this.canonical, metaData.canonical) && Intrinsics.areEqual(this.htmlTitle, metaData.htmlTitle) && Intrinsics.areEqual(this.author, metaData.author) && Intrinsics.areEqual(this.favicon, metaData.favicon) && Intrinsics.areEqual(this.shortcutIcon, metaData.shortcutIcon);
    }

    public int hashCode() {
        String str = this.ogTitle;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.ogSiteName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.ogDescription;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.ogImage;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.ogUrl;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.ogType;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.ogLocale;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.twitterCard;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.twitterTitle;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.twitterDescription;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.twitterImage;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.title;
        int iHashCode12 = (iHashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.description;
        int iHashCode13 = (iHashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.canonical;
        int iHashCode14 = (iHashCode13 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.htmlTitle;
        int iHashCode15 = (iHashCode14 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.author;
        int iHashCode16 = (iHashCode15 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.favicon;
        int iHashCode17 = (iHashCode16 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.shortcutIcon;
        return iHashCode17 + (str18 != null ? str18.hashCode() : 0);
    }

    public String toString() {
        return "MetaData(ogTitle=" + this.ogTitle + ", ogSiteName=" + this.ogSiteName + ", ogDescription=" + this.ogDescription + ", ogImage=" + this.ogImage + ", ogUrl=" + this.ogUrl + ", ogType=" + this.ogType + ", ogLocale=" + this.ogLocale + ", twitterCard=" + this.twitterCard + ", twitterTitle=" + this.twitterTitle + ", twitterDescription=" + this.twitterDescription + ", twitterImage=" + this.twitterImage + ", title=" + this.title + ", description=" + this.description + ", canonical=" + this.canonical + ", htmlTitle=" + this.htmlTitle + ", author=" + this.author + ", favicon=" + this.favicon + ", shortcutIcon=" + this.shortcutIcon + ")";
    }

    public MetaData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18) {
        this.ogTitle = str;
        this.ogSiteName = str2;
        this.ogDescription = str3;
        this.ogImage = str4;
        this.ogUrl = str5;
        this.ogType = str6;
        this.ogLocale = str7;
        this.twitterCard = str8;
        this.twitterTitle = str9;
        this.twitterDescription = str10;
        this.twitterImage = str11;
        this.title = str12;
        this.description = str13;
        this.canonical = str14;
        this.htmlTitle = str15;
        this.author = str16;
        this.favicon = str17;
        this.shortcutIcon = str18;
    }

    public /* synthetic */ MetaData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10, (i & 1024) != 0 ? null : str11, (i & 2048) != 0 ? null : str12, (i & 4096) != 0 ? null : str13, (i & 8192) != 0 ? null : str14, (i & 16384) != 0 ? null : str15, (i & 32768) != 0 ? null : str16, (i & 65536) != 0 ? null : str17, (i & 131072) != 0 ? null : str18);
    }

    public final String getOgTitle() {
        return this.ogTitle;
    }

    public final String getOgSiteName() {
        return this.ogSiteName;
    }

    public final String getOgDescription() {
        return this.ogDescription;
    }

    public final String getOgImage() {
        return this.ogImage;
    }

    public final String getOgUrl() {
        return this.ogUrl;
    }

    public final String getOgType() {
        return this.ogType;
    }

    public final String getOgLocale() {
        return this.ogLocale;
    }

    public final String getTwitterCard() {
        return this.twitterCard;
    }

    public final String getTwitterTitle() {
        return this.twitterTitle;
    }

    public final String getTwitterDescription() {
        return this.twitterDescription;
    }

    public final String getTwitterImage() {
        return this.twitterImage;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCanonical() {
        return this.canonical;
    }

    public final String getHtmlTitle() {
        return this.htmlTitle;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final String getFavicon() {
        return this.favicon;
    }

    public final String getShortcutIcon() {
        return this.shortcutIcon;
    }
}
