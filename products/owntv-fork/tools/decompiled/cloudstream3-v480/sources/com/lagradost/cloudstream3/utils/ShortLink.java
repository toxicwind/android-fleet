package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Element;
import org.mozilla.classfile.ByteCode;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: UnshortenUrl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\"\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u0016\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u0016\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/ShortLink;", "", "<init>", "()V", "adflyRegex", "", "linkupRegex", "linksafeRegex", "nuovoIndirizzoRegex", "nuovoLinkRegex", "uprotRegex", "davisonbarkerRegex", "isecureRegex", "shortList", "", "Lcom/lagradost/cloudstream3/utils/ShortLink$ShortUrl;", "isShortLink", "", ImagesContract.URL, "unshorten", "type", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unshortenAdfly", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unshortenLinkup", "unshortenLinksafe", "unshortenNuovoIndirizzo", "unshortenNuovoLink", "unshortenUprot", "unshortenDavisonbarker", "unshortenIsecure", "ShortUrl", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ShortLink {
    public static final ShortLink INSTANCE;
    private static final String adflyRegex;
    private static final String davisonbarkerRegex;
    private static final String isecureRegex;
    private static final String linksafeRegex;
    private static final String linkupRegex;
    private static final String nuovoIndirizzoRegex;
    private static final String nuovoLinkRegex;
    private static final List<ShortUrl> shortList;
    private static final String uprotRegex;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshorten$1, reason: invalid class name */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {63}, m = "unshorten", n = {ImagesContract.URL, "type", "currentUrl", "visitedUrls", "domain", "it", PeertubeParsingHelper.COUNT_KEY, "$i$a$-let-ShortLink$unshorten$3"}, nl = {63}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshorten(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenAdfly$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0}, l = {69}, m = "unshortenAdfly", n = {ImagesContract.URL}, nl = {70}, s = {"L$0"}, v = 2)
    static final class C09451 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09451(Continuation<? super C09451> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenAdfly(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenIsecure$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0}, l = {189}, m = "unshortenIsecure", n = {ImagesContract.URL}, nl = {ByteCode.ARRAYLENGTH}, s = {"L$0"}, v = 2)
    static final class C09461 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09461(Continuation<? super C09461> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenIsecure(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenLinkup$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {115, 134, 146}, m = "unshortenLinkup", n = {ImagesContract.URL, ImagesContract.URL, ImagesContract.URL, "r", ImagesContract.URL, "short", ImagesContract.URL, "r", ImagesContract.URL, "short"}, nl = {116, 138, 149}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C09471 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C09471(Continuation<? super C09471> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenLinkup(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenNuovoIndirizzo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0}, l = {157}, m = "unshortenNuovoIndirizzo", n = {ImagesContract.URL}, nl = {158}, s = {"L$0"}, v = 2)
    static final class C09481 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09481(Continuation<? super C09481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenNuovoIndirizzo(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenNuovoLink$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0}, l = {168}, m = "unshortenNuovoLink", n = {ImagesContract.URL}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class C09491 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09491(Continuation<? super C09491> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenNuovoLink(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ShortLink$unshortenUprot$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ShortLink", f = "UnshortenUrl.kt", i = {0}, l = {173}, m = "unshortenUprot", n = {ImagesContract.URL}, nl = {174}, s = {"L$0"}, v = 2)
    static final class C09501 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09501(Continuation<? super C09501> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShortLink.this.unshortenUprot(null, this);
        }
    }

    /* JADX INFO: compiled from: UnshortenUrl.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0004\b\t\u0010\nB=\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0004\b\t\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J*\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011JH\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052$\b\u0002\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR/\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ShortLink$ShortUrl;", "", "regex", "Lkotlin/text/Regex;", "type", "", "function", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "<init>", "(Lkotlin/text/Regex;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "getRegex", "()Lkotlin/text/Regex;", "getType", "()Ljava/lang/String;", "getFunction", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "component1", "component2", "component3", "copy", "(Lkotlin/text/Regex;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)Lcom/lagradost/cloudstream3/utils/ShortLink$ShortUrl;", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ShortUrl {
        private final Function2<String, Continuation<? super String>, Object> function;
        private final Regex regex;
        private final String type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ShortUrl copy$default(ShortUrl shortUrl, Regex regex, String str, Function2 function2, int i, Object obj) {
            if ((i & 1) != 0) {
                regex = shortUrl.regex;
            }
            if ((i & 2) != 0) {
                str = shortUrl.type;
            }
            if ((i & 4) != 0) {
                function2 = shortUrl.function;
            }
            return shortUrl.copy(regex, str, function2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Regex getRegex() {
            return this.regex;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final Function2<String, Continuation<? super String>, Object> component3() {
            return this.function;
        }

        public final ShortUrl copy(Regex regex, String type, Function2<? super String, ? super Continuation<? super String>, ? extends Object> function) {
            Intrinsics.checkNotNullParameter(regex, "regex");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(function, "function");
            return new ShortUrl(regex, type, function);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShortUrl)) {
                return false;
            }
            ShortUrl shortUrl = (ShortUrl) other;
            return Intrinsics.areEqual(this.regex, shortUrl.regex) && Intrinsics.areEqual(this.type, shortUrl.type) && Intrinsics.areEqual(this.function, shortUrl.function);
        }

        public int hashCode() {
            return (((this.regex.hashCode() * 31) + this.type.hashCode()) * 31) + this.function.hashCode();
        }

        public String toString() {
            return "ShortUrl(regex=" + this.regex + ", type=" + this.type + ", function=" + this.function + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ShortUrl(Regex regex, String type, Function2<? super String, ? super Continuation<? super String>, ? extends Object> function) {
            Intrinsics.checkNotNullParameter(regex, "regex");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(function, "function");
            this.regex = regex;
            this.type = type;
            this.function = function;
        }

        public final Regex getRegex() {
            return this.regex;
        }

        public final String getType() {
            return this.type;
        }

        public final Function2<String, Continuation<? super String>, Object> getFunction() {
            return this.function;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public ShortUrl(String regex, String type, Function2<? super String, ? super Continuation<? super String>, ? extends Object> function) {
            this(new Regex(regex), type, function);
            Intrinsics.checkNotNullParameter(regex, "regex");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(function, "function");
        }
    }

    private ShortLink() {
    }

    static {
        ShortLink shortLink = new ShortLink();
        INSTANCE = shortLink;
        adflyRegex = "adf\\.ly|j\\.gs|q\\.gs|u\\.bb|ay\\.gy|atominik\\.com|tinyium\\.com|microify\\.com|threadsphere\\.bid|clearload\\.bid|activetect\\.net|swiftviz\\.net|briskgram\\.net|activetect\\.net|stoodsef\\.com|baymaleti\\.net|thouth\\.net|uclaut\\.net|gloyah\\.net|larati\\.net|scuseami\\.net";
        linkupRegex = "linkup\\.pro|buckler.link";
        linksafeRegex = "linksafe\\.cc";
        nuovoIndirizzoRegex = "mixdrop\\.nuovoindirizzo\\.com";
        nuovoLinkRegex = "nuovolink\\.com";
        uprotRegex = "uprot\\.net";
        davisonbarkerRegex = "davisonbarker\\.pro|lowrihouston\\.pro";
        isecureRegex = "isecure\\.link";
        shortList = CollectionsKt.listOf((Object[]) new ShortUrl[]{new ShortUrl("adf\\.ly|j\\.gs|q\\.gs|u\\.bb|ay\\.gy|atominik\\.com|tinyium\\.com|microify\\.com|threadsphere\\.bid|clearload\\.bid|activetect\\.net|swiftviz\\.net|briskgram\\.net|activetect\\.net|stoodsef\\.com|baymaleti\\.net|thouth\\.net|uclaut\\.net|gloyah\\.net|larati\\.net|scuseami\\.net", "adfly", new ShortLink$shortList$1(shortLink)), new ShortUrl("linkup\\.pro|buckler.link", "linkup", new ShortLink$shortList$2(shortLink)), new ShortUrl("linksafe\\.cc", "linksafe", new ShortLink$shortList$3(shortLink)), new ShortUrl("mixdrop\\.nuovoindirizzo\\.com", "nuovoindirizzo", new ShortLink$shortList$4(shortLink)), new ShortUrl("nuovolink\\.com", "nuovolink", new ShortLink$shortList$5(shortLink)), new ShortUrl("uprot\\.net", "uprot", new ShortLink$shortList$6(shortLink)), new ShortUrl("davisonbarker\\.pro|lowrihouston\\.pro", "uprot", new ShortLink$shortList$7(shortLink)), new ShortUrl("isecure\\.link", "isecure", new ShortLink$shortList$8(shortLink))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object shortList$unshortenLinksafe(ShortLink shortLink, String str, Continuation continuation) {
        return shortLink.unshortenLinksafe(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object shortList$unshortenDavisonbarker(ShortLink shortLink, String str, Continuation continuation) {
        return shortLink.unshortenDavisonbarker(str);
    }

    public final boolean isShortLink(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        List<ShortUrl> list = shortList;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (Regex.find$default(((ShortUrl) it.next()).getRegex(), url, 0, 2, null) != null) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ Object unshorten$default(ShortLink shortLink, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return shortLink.unshorten(str, str2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0080 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:21:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:23:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:30:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:34:0x0108 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x0109  */
    /* JADX WARN: Code duplicated, block: B:38:0x0113  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d5 A[EDGE_INSN: B:42:0x00d5->B:28:0x00d5 BREAK  A[LOOP:0: B:19:0x00aa->B:26:0x00d2], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00d4 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0109 -> B:36:0x010f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object unshorten(java.lang.String r18, java.lang.String r19, kotlin.coroutines.Continuation<? super java.lang.String> r20) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.ShortLink.unshorten(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object unshortenAdfly(String str, Continuation<? super String> continuation) {
        C09451 c09451;
        int i;
        Object obj;
        String str2;
        List<String> listEmptyList;
        List<List> listEmptyList2;
        if (continuation instanceof C09451) {
            c09451 = (C09451) continuation;
            if ((c09451.label & Integer.MIN_VALUE) != 0) {
                c09451.label -= Integer.MIN_VALUE;
            } else {
                c09451 = new C09451(continuation);
            }
        } else {
            c09451 = new C09451(continuation);
        }
        Object obj2 = c09451.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c09451.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            c09451.L$0 = str;
            c09451.label = 1;
            C09451 c09452 = c09451;
            i = 1;
            obj = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c09452, 4094, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            str2 = str;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str3 = (String) c09451.L$0;
            ResultKt.throwOnFailure(obj2);
            obj = obj2;
            str2 = str3;
            i = 1;
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("var ysmm =.*;?"), ((NiceResponse) obj).getText(), 0, 2, null);
        Intrinsics.checkNotNull(matchResultFind$default);
        String value = matchResultFind$default.getValue();
        if (value.length() <= 0) {
            return str2;
        }
        List<String> listChunked = StringsKt.chunked(new Regex("var ysmm = '|';").replace(value, ""), 2);
        if (!listChunked.isEmpty()) {
            ListIterator<String> listIterator = listChunked.listIterator(listChunked.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != i) {
                        listEmptyList = CollectionsKt.take(listChunked, listIterator.nextIndex() + i);
                        break;
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        String str4 = "";
        String string = str4;
        for (String str5 : listEmptyList) {
            str4 = str4 + str5.charAt(0);
            char cCharAt = str5.charAt(i);
            StringBuilder sb = new StringBuilder();
            sb.append(cCharAt);
            sb.append(string);
            string = sb.toString();
        }
        List<Character> mutableList = StringsKt.toMutableList(str4 + string);
        List<Character> list = mutableList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i3 = 0;
        for (Object obj3 : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new Pair(Boxing.boxInt(i3), Boxing.boxChar(((Character) obj3).charValue())));
            i3 = i4;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj4 : arrayList) {
            if (Character.isDigit(((Character) ((Pair) obj4).getSecond()).charValue())) {
                arrayList2.add(obj4);
            }
        }
        List listChunked2 = CollectionsKt.chunked(arrayList2, 2);
        if (!listChunked2.isEmpty()) {
            ListIterator listIterator2 = listChunked2.listIterator(listChunked2.size());
            while (true) {
                if (listIterator2.hasPrevious()) {
                    if (((List) listIterator2.previous()).size() != i) {
                        listEmptyList2 = CollectionsKt.take(listChunked2, listIterator2.nextIndex() + i);
                        break;
                    }
                } else {
                    listEmptyList2 = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            listEmptyList2 = CollectionsKt.emptyList();
            break;
        }
        for (List list2 : listEmptyList2) {
            int iCharValue = ((Character) ((Pair) list2.get(0)).getSecond()).charValue() ^ ((Character) ((Pair) list2.get(i)).getSecond()).charValue();
            if (iCharValue < 10) {
                mutableList.set(((Number) ((Pair) list2.get(0)).getFirst()).intValue(), Boxing.boxChar(CharsKt.digitToChar(iCharValue)));
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(Boxing.boxByte((byte) ((Character) it.next()).charValue()));
        }
        String strDrop = StringsKt.drop(StringsKt.dropLast(MainAPIKt.base64Decode(StringsKt.decodeToString(CollectionsKt.toByteArray(arrayList3))), 16), 16);
        String str6 = strDrop;
        return Regex.find$default(new Regex("go\\.php\\?u="), str6, 0, 2, null) != null ? MainAPIKt.base64Decode(new Regex("(.*?)u=").replace(str6, "")) : strDrop;
    }

    /* JADX WARN: Code duplicated, block: B:57:0x01da  */
    /* JADX WARN: Code duplicated, block: B:58:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:60:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:62:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:65:0x0228  */
    /* JADX WARN: Code duplicated, block: B:68:0x023c  */
    /* JADX WARN: Code duplicated, block: B:70:0x024e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0254  */
    /* JADX WARN: Code duplicated, block: B:74:0x0265  */
    /* JADX WARN: Code duplicated, block: B:76:0x026f  */
    /* JADX WARN: Code duplicated, block: B:78:0x0282  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code duplicated, block: B:80:0x028c  */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02c1, code lost:
    
        if (r0 == r1) goto L82;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object unshortenLinkup(java.lang.String r24, kotlin.coroutines.Continuation<? super java.lang.String> r25) {
        /*
            Method dump skipped, instruction units count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.ShortLink.unshortenLinkup(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String unshortenLinksafe(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return MainAPIKt.base64Decode((String) CollectionsKt.last(StringsKt.split$default((CharSequence) url, new String[]{"?url="}, false, 0, 6, (Object) null)));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object unshortenNuovoIndirizzo(String str, Continuation<? super String> continuation) {
        C09481 c09481;
        if (continuation instanceof C09481) {
            c09481 = (C09481) continuation;
            if ((c09481.label & Integer.MIN_VALUE) != 0) {
                c09481.label -= Integer.MIN_VALUE;
            } else {
                c09481 = new C09481(continuation);
            }
        } else {
            c09481 = new C09481(continuation);
        }
        Object obj = c09481.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09481.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            c09481.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c09481.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, true, 0, null, 0L, null, false, null, c09481, 4062, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        if (niceResponse.getHeaders().get("refresh") != null) {
            String str2 = niceResponse.getHeaders().get("refresh");
            Intrinsics.checkNotNull(str2);
            return StringsKt.substringAfter$default(str2, "=", (String) null, 2, (Object) null);
        }
        return "non trovato";
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object unshortenNuovoLink(String str, Continuation<? super String> continuation) {
        C09491 c09491;
        if (continuation instanceof C09491) {
            c09491 = (C09491) continuation;
            if ((c09491.label & Integer.MIN_VALUE) != 0) {
                c09491.label -= Integer.MIN_VALUE;
            } else {
                c09491 = new C09491(continuation);
            }
        } else {
            c09491 = new C09491(continuation);
        }
        Object obj = c09491.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09491.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            c09491.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c09491.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, true, 0, null, 0L, null, false, null, c09491, 4062, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Element elementSelectFirst = ((NiceResponse) obj).getDocument().selectFirst(CmcdData.OBJECT_TYPE_AUDIO_ONLY);
        Intrinsics.checkNotNull(elementSelectFirst);
        String strAttr = elementSelectFirst.attr("href");
        Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
        return strAttr;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object unshortenUprot(String str, Continuation<? super String> continuation) {
        C09501 c09501;
        String str2;
        if (continuation instanceof C09501) {
            c09501 = (C09501) continuation;
            if ((c09501.label & Integer.MIN_VALUE) != 0) {
                c09501.label -= Integer.MIN_VALUE;
            } else {
                c09501 = new C09501(continuation);
            }
        } else {
            c09501 = new C09501(continuation);
        }
        Object obj = c09501.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09501.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            c09501.L$0 = str;
            c09501.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c09501, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            str2 = str;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) c09501.L$0;
            ResultKt.throwOnFailure(obj);
        }
        for (String str3 : SequencesKt.toList(SequencesKt.map(Regex.findAll$default(new Regex("<a[^>]+href=\"([^\"]+)\".*Continue"), ((NiceResponse) obj).getText(), 0, 2, null), new Function1() { // from class: com.lagradost.cloudstream3.utils.ShortLink$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return ShortLink.unshortenUprot$lambda$0((MatchResult) obj3);
            }
        }))) {
            String str4 = str3;
            if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "https://maxstream.video", false, 2, (Object) null) || (StringsKt.contains$default((CharSequence) str4, (CharSequence) "https://uprot.net", false, 2, (Object) null) && !Intrinsics.areEqual(str3, str2))) {
                return str3;
            }
        }
        return str2;
    }

    static final String unshortenUprot$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.replace$default(it.getValue(), "<a href=\"", "", false, 4, (Object) null);
    }

    public final String unshortenDavisonbarker(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringUtils.INSTANCE.decodeUrl(StringsKt.substringAfter$default(url, "dest=", (String) null, 2, (Object) null));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object unshortenIsecure(String str, Continuation<? super String> continuation) {
        C09461 c09461;
        String str2;
        String strAttr;
        String string;
        if (continuation instanceof C09461) {
            c09461 = (C09461) continuation;
            if ((c09461.label & Integer.MIN_VALUE) != 0) {
                c09461.label -= Integer.MIN_VALUE;
            } else {
                c09461 = new C09461(continuation);
            }
        } else {
            c09461 = new C09461(continuation);
        }
        Object obj = c09461.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09461.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            c09461.L$0 = str;
            c09461.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c09461, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            str2 = str;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) c09461.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Element elementSelectFirst = ((NiceResponse) obj).getDocument().selectFirst("iframe");
        return (elementSelectFirst == null || (strAttr = elementSelectFirst.attr("src")) == null || (string = StringsKt.trim((CharSequence) strAttr).toString()) == null) ? str2 : string;
    }
}
