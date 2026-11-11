package com.lagradost.cloudstream3.utils;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: compiled from: M3u8Helper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u000234B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000fH\u0002J(\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001c2\b\b\u0002\u0010#\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000fJ\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J&\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\t2\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010*\u001a\u00020&H\u0086@¢\u0006\u0002\u0010+J2\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020(2\b\b\u0002\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&2\b\b\u0002\u00101\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u00102R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper2;", "", "<init>", "()V", "TAG", "", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "generateM3u8", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "source", "streamUrl", "referer", "quality", "", "headers", "", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ENCRYPTION_DETECTION_REGEX", "Lkotlin/text/Regex;", "ENCRYPTION_URL_IV_REGEX", "QUALITY_REGEX", "TS_EXTENSION_REGEX", "absoluteExtensionDetermination", ImagesContract.URL, "toBytes16Big", "", "n", "defaultIv", "index", "getDecrypted", "secretKey", "data", "iv", "getParentLink", "isNotCompleteUrl", "", "m3u8Generation", "Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;", "m3u8", "returnThis", "(Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hslLazy", "Lcom/lagradost/cloudstream3/utils/M3u8Helper2$LazyHlsDownloadData;", "playlistStream", "selectBest", "requireAudio", "depth", "(Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;ZZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TsLink", "LazyHlsDownloadData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class M3u8Helper2 {
    public static final M3u8Helper2 INSTANCE = new M3u8Helper2();
    private static final String TAG = "M3u8Helper";
    private static final AES.CBC aesCbc = (AES.CBC) CryptographyProvider.INSTANCE.getDefault().get(AES.CBC.INSTANCE);
    private static final Regex ENCRYPTION_DETECTION_REGEX = new Regex("#EXT-X-KEY:METHOD=([^,]+),");
    private static final Regex ENCRYPTION_URL_IV_REGEX = new Regex("#EXT-X-KEY:METHOD=([^,]+),URI=\"([^\"]+)\"(?:,IV=(.*))?");
    private static final Regex QUALITY_REGEX = new Regex("#EXT-X-STREAM-INF:(?:(?:.*?(?:RESOLUTION=\\d+x(\\d+)).*?\\s+(.*))|(?:.*?\\s+(.*)))");
    private static final Regex TS_EXTENSION_REGEX = new Regex("#EXTINF:(([0-9]*[.])?[0-9]+|).*\\n(.+?\\n)");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.M3u8Helper2$generateM3u8$1, reason: invalid class name */
    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.M3u8Helper2", f = "M3u8Helper.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {54, 62}, m = "generateM3u8", n = {"source", "streamUrl", "referer", "quality", "headers", "name", "source", "streamUrl", "referer", "quality", "headers", "name", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "stream", "$i$f$map", "$i$f$mapTo", "$i$a$-map-M3u8Helper2$generateM3u8$2"}, nl = {61, 71}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$11", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return M3u8Helper2.this.generateM3u8(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.M3u8Helper2$hslLazy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.M3u8Helper2", f = "M3u8Helper.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED, 300, MediaError.DetailedErrorCode.SMOOTH_NETWORK}, m = "hslLazy", n = {"playlistStream", "selectBest", "requireAudio", "depth", "playlistStream", "playlistResponse", "parsed", "variants", "bestVideo", "selectBest", "requireAudio", "depth", "quality", "playlistStream", "playlistResponse", "parsed", "encryptionIv", "encryptionData", "match", "encryptionUrl", "selectBest", "requireAudio", "depth", "encryptionState"}, nl = {269, 315, 330}, s = {"L$0", "Z$0", "Z$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "Z$1", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "Z$1", "I$0", "I$1"}, v = 2)
    static final class C09351 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        C09351(Continuation<? super C09351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return M3u8Helper2.this.hslLazy(null, false, false, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.M3u8Helper2$m3u8Generation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.M3u8Helper2", f = "M3u8Helper.kt", i = {0, 0, 0}, l = {130}, m = "m3u8Generation", n = {"m3u8", "list", "returnThis"}, nl = {131}, s = {"L$0", "L$1", "Z$0"}, v = 2)
    static final class C09361 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C09361(Continuation<? super C09361> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return M3u8Helper2.this.m3u8Generation(null, false, this);
        }
    }

    private M3u8Helper2() {
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:25:0x016d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x016d -> B:26:0x017b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object generateM3u8(java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.Integer r24, java.util.Map<java.lang.String, java.lang.String> r25, java.lang.String r26, kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.utils.ExtractorLink>> r27) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.M3u8Helper2.generateM3u8(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.util.Map, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object generateM3u8$default(M3u8Helper2 m3u8Helper2, String str, String str2, String str3, Integer num, Map map, String str4, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            num = null;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            map = MapsKt.emptyMap();
        }
        return m3u8Helper2.generateM3u8(str, str2, str3, num2, map, (i & 32) != 0 ? str : str4, continuation);
    }

    private final String absoluteExtensionDetermination(String url) {
        List listSplit$default = StringsKt.split$default((CharSequence) url, new String[]{"/"}, false, 0, 6, (Object) null);
        String str = (String) StringsKt.split$default((CharSequence) listSplit$default.get(listSplit$default.size() - 1), new String[]{"?"}, false, 0, 6, (Object) null).get(0);
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
            List listSplit$default2 = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null);
            if (listSplit$default2.isEmpty()) {
                listSplit$default2 = null;
            }
            List list = listSplit$default2;
            if (list != null) {
                return (String) CollectionsKt.last(list);
            }
        }
        return null;
    }

    private final byte[] toBytes16Big(int n) {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (Math.max(0, (int) (((double) n) / Math.pow(256.0d, 15 - i))) % 256);
        }
        return bArr;
    }

    private final byte[] defaultIv(int index) {
        return toBytes16Big(index + 1);
    }

    public static /* synthetic */ byte[] getDecrypted$default(M3u8Helper2 m3u8Helper2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            bArr3 = new byte[0];
        }
        return m3u8Helper2.getDecrypted(bArr, bArr2, bArr3, i);
    }

    public final byte[] getDecrypted(byte[] secretKey, byte[] data, byte[] iv, int index) {
        Intrinsics.checkNotNullParameter(secretKey, "secretKey");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(iv, "iv");
        if (iv.length == 0) {
            iv = defaultIv(index);
        }
        return aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, secretKey).cipher(true).decryptWithIvBlocking(iv, data);
    }

    private final String getParentLink(String url) {
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) url, new String[]{"/"}, false, 0, 6, (Object) null));
        mutableList.remove(CollectionsKt.getLastIndex(mutableList));
        return CollectionsKt.joinToString$default(mutableList, "/", null, null, 0, null, null, 62, null);
    }

    private final boolean isNotCompleteUrl(String url) {
        return (StringsKt.startsWith$default(url, Utils.HTTPS, false, 2, (Object) null) || StringsKt.startsWith$default(url, Utils.HTTP, false, 2, (Object) null)) ? false : true;
    }

    public static /* synthetic */ Object m3u8Generation$default(M3u8Helper2 m3u8Helper2, M3u8Helper.M3u8Stream m3u8Stream, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return m3u8Helper2.m3u8Generation(m3u8Stream, z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object m3u8Generation(M3u8Helper.M3u8Stream m3u8Stream, boolean z, Continuation<? super List<M3u8Helper.M3u8Stream>> continuation) {
        C09361 c09361;
        M3u8Helper.M3u8Stream m3u8Stream2;
        List list;
        boolean z2;
        if (continuation instanceof C09361) {
            c09361 = (C09361) continuation;
            if ((c09361.label & Integer.MIN_VALUE) != 0) {
                c09361.label -= Integer.MIN_VALUE;
            } else {
                c09361 = new C09361(continuation);
            }
        } else {
            c09361 = new C09361(continuation);
        }
        Object obj = c09361.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09361.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            Requests app2 = MainActivityKt.getApp();
            String streamUrl = m3u8Stream.getStreamUrl();
            Map<String, String> headers = m3u8Stream.getHeaders();
            c09361.L$0 = m3u8Stream;
            c09361.L$1 = arrayList;
            c09361.Z$0 = z;
            c09361.label = 1;
            Object obj2 = Requests.get$default(app2, streamUrl, headers, null, null, null, false, 0, null, 0L, null, false, null, c09361, 3068, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            m3u8Stream2 = m3u8Stream;
            list = arrayList;
            obj = obj2;
            z2 = z;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z2 = c09361.Z$0;
            list = (List) c09361.L$1;
            m3u8Stream2 = (M3u8Helper.M3u8Stream) c09361.L$0;
            ResultKt.throwOnFailure(obj);
        }
        String text = ((NiceResponse) obj).getText();
        HlsPlaylistParser.HlsMultivariantPlaylist hlsMultivariantPlaylist = HlsPlaylistParser.INSTANCE.parse(m3u8Stream2.getStreamUrl(), text);
        boolean z3 = false;
        if (hlsMultivariantPlaylist != null) {
            for (HlsPlaylistParser.Variant variant : hlsMultivariantPlaylist.getVariants()) {
                if (!variant.isTrickPlay()) {
                    int height = variant.getFormat().getHeight();
                    list.add(new M3u8Helper.M3u8Stream(variant.getUrl().getUrlString(), height > 0 ? Boxing.boxInt(height) : null, m3u8Stream2.getHeaders()));
                    z3 = true;
                }
            }
        }
        if (hlsMultivariantPlaylist != null && z3 && !z2) {
            return list;
        }
        if (hlsMultivariantPlaylist != null || TS_EXTENSION_REGEX.containsMatchIn(text)) {
            list.add(m3u8Stream2);
            return list;
        }
        Log.INSTANCE.i(TAG, "M3u8 Playlist is not a \"Master Playlist\" nor a \"Media Playlist\". Removing this link as it is invalid and will not open in player: " + m3u8Stream2.getStreamUrl());
        return list;
    }

    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper2$TsLink;", "", ImagesContract.URL, "", "time", "", "<init>", "(Ljava/lang/String;Ljava/lang/Double;)V", "getUrl", "()Ljava/lang/String;", "getTime", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Double;)Lcom/lagradost/cloudstream3/utils/M3u8Helper2$TsLink;", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TsLink {
        private final Double time;
        private final String url;

        public static /* synthetic */ TsLink copy$default(TsLink tsLink, String str, Double d, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tsLink.url;
            }
            if ((i & 2) != 0) {
                d = tsLink.time;
            }
            return tsLink.copy(str, d);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Double getTime() {
            return this.time;
        }

        public final TsLink copy(String url, Double time) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new TsLink(url, time);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TsLink)) {
                return false;
            }
            TsLink tsLink = (TsLink) other;
            return Intrinsics.areEqual(this.url, tsLink.url) && Intrinsics.areEqual((Object) this.time, (Object) tsLink.time);
        }

        public int hashCode() {
            int iHashCode = this.url.hashCode() * 31;
            Double d = this.time;
            return iHashCode + (d == null ? 0 : d.hashCode());
        }

        public String toString() {
            return "TsLink(url=" + this.url + ", time=" + this.time + ')';
        }

        public TsLink(String url, Double d) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.time = d;
        }

        public final String getUrl() {
            return this.url;
        }

        public final Double getTime() {
            return this.time;
        }
    }

    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0004\b\u000e\u0010\u000fJ:\u0010\u001b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060!H\u0086@¢\u0006\u0002\u0010\"J,\u0010#\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010&J\t\u0010'\u001a\u00020\u0003HÂ\u0003J\t\u0010(\u001a\u00020\u0003HÂ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\rHÆ\u0003JW\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\rHÆ\u0001J\u0014\u0010.\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u00020\u0018HÖ\u0081\u0004J\n\u00101\u001a\u00020\u000bHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u00062"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper2$LazyHlsDownloadData;", "", "encryptionData", "", "encryptionIv", "isEncrypted", "", "allTsLinks", "", "Lcom/lagradost/cloudstream3/utils/M3u8Helper2$TsLink;", "relativeUrl", "", "headers", "", "<init>", "([B[BZLjava/util/List;Ljava/lang/String;Ljava/util/Map;)V", "()Z", "getAllTsLinks", "()Ljava/util/List;", "getRelativeUrl", "()Ljava/lang/String;", "getHeaders", "()Ljava/util/Map;", ContentDisposition.Parameters.Size, "", "getSize", "()I", "resolveLinkWhileSafe", "index", "tries", "failDelay", "", "condition", "Lkotlin/Function0;", "(IIJLkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveLinkSafe", "(IIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveLink", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LazyHlsDownloadData {
        private final List<TsLink> allTsLinks;
        private final byte[] encryptionData;
        private final byte[] encryptionIv;
        private final Map<String, String> headers;
        private final boolean isEncrypted;
        private final String relativeUrl;

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final byte[] getEncryptionData() {
            return this.encryptionData;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final byte[] getEncryptionIv() {
            return this.encryptionIv;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LazyHlsDownloadData copy$default(LazyHlsDownloadData lazyHlsDownloadData, byte[] bArr, byte[] bArr2, boolean z, List list, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = lazyHlsDownloadData.encryptionData;
            }
            if ((i & 2) != 0) {
                bArr2 = lazyHlsDownloadData.encryptionIv;
            }
            if ((i & 4) != 0) {
                z = lazyHlsDownloadData.isEncrypted;
            }
            if ((i & 8) != 0) {
                list = lazyHlsDownloadData.allTsLinks;
            }
            if ((i & 16) != 0) {
                str = lazyHlsDownloadData.relativeUrl;
            }
            if ((i & 32) != 0) {
                map = lazyHlsDownloadData.headers;
            }
            String str2 = str;
            Map map2 = map;
            return lazyHlsDownloadData.copy(bArr, bArr2, z, list, str2, map2);
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsEncrypted() {
            return this.isEncrypted;
        }

        public final List<TsLink> component4() {
            return this.allTsLinks;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getRelativeUrl() {
            return this.relativeUrl;
        }

        public final Map<String, String> component6() {
            return this.headers;
        }

        public final LazyHlsDownloadData copy(byte[] encryptionData, byte[] encryptionIv, boolean isEncrypted, List<TsLink> allTsLinks, String relativeUrl, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(encryptionData, "encryptionData");
            Intrinsics.checkNotNullParameter(encryptionIv, "encryptionIv");
            Intrinsics.checkNotNullParameter(allTsLinks, "allTsLinks");
            Intrinsics.checkNotNullParameter(relativeUrl, "relativeUrl");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new LazyHlsDownloadData(encryptionData, encryptionIv, isEncrypted, allTsLinks, relativeUrl, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LazyHlsDownloadData)) {
                return false;
            }
            LazyHlsDownloadData lazyHlsDownloadData = (LazyHlsDownloadData) other;
            return Intrinsics.areEqual(this.encryptionData, lazyHlsDownloadData.encryptionData) && Intrinsics.areEqual(this.encryptionIv, lazyHlsDownloadData.encryptionIv) && this.isEncrypted == lazyHlsDownloadData.isEncrypted && Intrinsics.areEqual(this.allTsLinks, lazyHlsDownloadData.allTsLinks) && Intrinsics.areEqual(this.relativeUrl, lazyHlsDownloadData.relativeUrl) && Intrinsics.areEqual(this.headers, lazyHlsDownloadData.headers);
        }

        public int hashCode() {
            return (((((((((Arrays.hashCode(this.encryptionData) * 31) + Arrays.hashCode(this.encryptionIv)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isEncrypted)) * 31) + this.allTsLinks.hashCode()) * 31) + this.relativeUrl.hashCode()) * 31) + this.headers.hashCode();
        }

        public String toString() {
            return "LazyHlsDownloadData(encryptionData=" + Arrays.toString(this.encryptionData) + ", encryptionIv=" + Arrays.toString(this.encryptionIv) + ", isEncrypted=" + this.isEncrypted + ", allTsLinks=" + this.allTsLinks + ", relativeUrl=" + this.relativeUrl + ", headers=" + this.headers + ')';
        }

        public LazyHlsDownloadData(byte[] encryptionData, byte[] encryptionIv, boolean z, List<TsLink> allTsLinks, String relativeUrl, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(encryptionData, "encryptionData");
            Intrinsics.checkNotNullParameter(encryptionIv, "encryptionIv");
            Intrinsics.checkNotNullParameter(allTsLinks, "allTsLinks");
            Intrinsics.checkNotNullParameter(relativeUrl, "relativeUrl");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.encryptionData = encryptionData;
            this.encryptionIv = encryptionIv;
            this.isEncrypted = z;
            this.allTsLinks = allTsLinks;
            this.relativeUrl = relativeUrl;
            this.headers = headers;
        }

        public final boolean isEncrypted() {
            return this.isEncrypted;
        }

        public final List<TsLink> getAllTsLinks() {
            return this.allTsLinks;
        }

        public final String getRelativeUrl() {
            return this.relativeUrl;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final int getSize() {
            return this.allTsLinks.size();
        }

        public static /* synthetic */ Object resolveLinkWhileSafe$default(LazyHlsDownloadData lazyHlsDownloadData, int i, int i2, long j, Function0 function0, Continuation continuation, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i2 = 3;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                j = 3000;
            }
            return lazyHlsDownloadData.resolveLinkWhileSafe(i, i4, j, function0, continuation);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(4:44|26|(1:29)|38) */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00a4, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00a5, code lost:
        
            r2 = r15;
            r15 = r2;
            r7 = r13;
            r14 = r11;
            r13 = r12;
            r11 = r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00c2, code lost:
        
            if (kotlinx.coroutines.DelayKt.delay(r11, r0) == r1) goto L38;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00c2 -> B:13:0x003d). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object resolveLinkWhileSafe(int r10, int r11, long r12, kotlin.jvm.functions.Function0<java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super byte[]> r15) {
            /*
                r9 = this;
                boolean r0 = r15 instanceof com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1
                if (r0 == 0) goto L14
                r0 = r15
                com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1 r0 = (com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r15 = r0.label
                int r15 = r15 - r2
                r0.label = r15
                goto L19
            L14:
                com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1 r0 = new com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1
                r0.<init>(r9, r15)
            L19:
                java.lang.Object r15 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L5f
                if (r2 == r4) goto L4c
                if (r2 != r3) goto L44
                int r10 = r0.I$2
                long r11 = r0.J$0
                int r13 = r0.I$1
                int r14 = r0.I$0
                java.lang.Object r2 = r0.L$1
                java.lang.Throwable r2 = (java.lang.Throwable) r2
                java.lang.Object r2 = r0.L$0
                kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
                kotlin.ResultKt.throwOnFailure(r15)
            L3d:
                r15 = r2
                r7 = r11
                r12 = r13
                r11 = r14
                r13 = r7
                goto Lc5
            L44:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L4c:
                int r10 = r0.I$2
                long r11 = r0.J$0
                int r13 = r0.I$1
                int r14 = r0.I$0
                java.lang.Object r2 = r0.L$0
                kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
                kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> Lc7
                goto L94
            L5c:
                r15 = move-exception
                goto Lac
            L5f:
                kotlin.ResultKt.throwOnFailure(r15)
                r15 = 0
                r7 = r11
                r11 = r10
                r10 = r15
                r15 = r14
                r13 = r12
                r12 = r7
            L69:
                if (r10 >= r12) goto Lc7
                java.lang.Object r2 = r15.invoke()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 != 0) goto L78
                return r5
            L78:
                r0.L$0 = r15     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.L$1 = r5     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.I$0 = r11     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.I$1 = r12     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.J$0 = r13     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.I$2 = r10     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                r0.label = r4     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                java.lang.Object r2 = r9.resolveLink(r11, r0)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                if (r2 != r1) goto L8d
                goto Lc4
            L8d:
                r7 = r2
                r2 = r15
                r15 = r7
                r7 = r13
                r14 = r11
                r13 = r12
                r11 = r7
            L94:
                byte[] r15 = (byte[]) r15     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                java.lang.Object r6 = r2.invoke()     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                boolean r9 = r6.booleanValue()     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> Lc7 java.lang.Throwable -> Lc7
                if (r9 == 0) goto La3
                r5 = r15
            La3:
                return r5
            La4:
                r2 = move-exception
                r7 = r2
                r2 = r15
                r15 = r7
                r7 = r13
                r14 = r11
                r13 = r12
                r11 = r7
            Lac:
                r0.L$0 = r2
                java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
                r0.L$1 = r15
                r0.I$0 = r14
                r0.I$1 = r13
                r0.J$0 = r11
                r0.I$2 = r10
                r0.label = r3
                java.lang.Object r15 = kotlinx.coroutines.DelayKt.delay(r11, r0)
                if (r15 != r1) goto L3d
            Lc4:
                return r1
            Lc5:
                int r10 = r10 + r4
                goto L69
            Lc7:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.M3u8Helper2.LazyHlsDownloadData.resolveLinkWhileSafe(int, int, long, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public static /* synthetic */ Object resolveLinkSafe$default(LazyHlsDownloadData lazyHlsDownloadData, int i, int i2, long j, Continuation continuation, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i2 = 3;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                j = 3000;
            }
            return lazyHlsDownloadData.resolveLinkSafe(i, i4, j, continuation);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(3:38|23|(1:31)(1:26)) */
        /* JADX WARN: Code duplicated, block: B:26:0x006c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x008b A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
        
            r2 = r10;
            r10 = r14;
            r14 = r2;
            r13 = r11;
            r11 = r12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0089, code lost:
        
            if (kotlinx.coroutines.DelayKt.delay(r11, r0) == r1) goto L31;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0089 -> B:13:0x0039). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object resolveLinkSafe(int r10, int r11, long r12, kotlin.coroutines.Continuation<? super byte[]> r14) {
            /*
                r9 = this;
                boolean r0 = r14 instanceof com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkSafe$1
                if (r0 == 0) goto L14
                r0 = r14
                com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkSafe$1 r0 = (com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkSafe$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r14 = r0.label
                int r14 = r14 - r2
                r0.label = r14
                goto L19
            L14:
                com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkSafe$1 r0 = new com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData$resolveLinkSafe$1
                r0.<init>(r9, r14)
            L19:
                java.lang.Object r14 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L53
                if (r2 == r4) goto L45
                if (r2 != r3) goto L3d
                int r10 = r0.I$2
                long r11 = r0.J$0
                int r13 = r0.I$1
                int r2 = r0.I$0
                java.lang.Object r6 = r0.L$0
                java.lang.Throwable r6 = (java.lang.Throwable) r6
                kotlin.ResultKt.throwOnFailure(r14)
            L39:
                r7 = r11
                r11 = r13
                r12 = r7
                goto L8c
            L3d:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L45:
                int r10 = r0.I$2
                long r11 = r0.J$0
                int r13 = r0.I$1
                int r2 = r0.I$0
                kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L90
                return r14
            L51:
                r14 = move-exception
                goto L75
            L53:
                kotlin.ResultKt.throwOnFailure(r14)
                r14 = 0
            L57:
                if (r14 >= r11) goto L90
                r0.L$0 = r5     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                r0.I$0 = r10     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                r0.I$1 = r11     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                r0.J$0 = r12     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                r0.I$2 = r14     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                r0.label = r4     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                java.lang.Object r9 = r9.resolveLink(r10, r0)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L90 java.lang.Throwable -> L90
                if (r9 != r1) goto L6c
                goto L8b
            L6c:
                return r9
            L6d:
                r2 = move-exception
                r7 = r2
                r2 = r10
                r10 = r14
                r14 = r7
                r7 = r12
                r13 = r11
                r11 = r7
            L75:
                java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
                r0.L$0 = r14
                r0.I$0 = r2
                r0.I$1 = r13
                r0.J$0 = r11
                r0.I$2 = r10
                r0.label = r3
                java.lang.Object r14 = kotlinx.coroutines.DelayKt.delay(r11, r0)
                if (r14 != r1) goto L39
            L8b:
                return r1
            L8c:
                int r14 = r10 + 1
                r10 = r2
                goto L57
            L90:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.M3u8Helper2.LazyHlsDownloadData.resolveLinkSafe(int, int, long, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Code duplicated, block: B:7:0x001a  */
        public final Object resolveLink(int i, Continuation<? super byte[]> continuation) throws ErrorLoadingException, IOException {
            M3u8Helper2$LazyHlsDownloadData$resolveLink$1 m3u8Helper2$LazyHlsDownloadData$resolveLink$1;
            int i2 = i;
            if (continuation instanceof M3u8Helper2$LazyHlsDownloadData$resolveLink$1) {
                m3u8Helper2$LazyHlsDownloadData$resolveLink$1 = (M3u8Helper2$LazyHlsDownloadData$resolveLink$1) continuation;
                if ((m3u8Helper2$LazyHlsDownloadData$resolveLink$1.label & Integer.MIN_VALUE) != 0) {
                    m3u8Helper2$LazyHlsDownloadData$resolveLink$1.label -= Integer.MIN_VALUE;
                } else {
                    m3u8Helper2$LazyHlsDownloadData$resolveLink$1 = new M3u8Helper2$LazyHlsDownloadData$resolveLink$1(this, continuation);
                }
            } else {
                m3u8Helper2$LazyHlsDownloadData$resolveLink$1 = new M3u8Helper2$LazyHlsDownloadData$resolveLink$1(this, continuation);
            }
            Object obj = m3u8Helper2$LazyHlsDownloadData$resolveLink$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = m3u8Helper2$LazyHlsDownloadData$resolveLink$1.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (i2 < 0 || i2 >= getSize()) {
                    throw new IllegalArgumentException("index must be in the bounds of the ts");
                }
                TsLink tsLink = this.allTsLinks.get(i2);
                Requests app2 = MainActivityKt.getApp();
                String url = tsLink.getUrl();
                Map<String, String> map = this.headers;
                m3u8Helper2$LazyHlsDownloadData$resolveLink$1.L$0 = SpillingKt.nullOutSpilledVariable(tsLink);
                m3u8Helper2$LazyHlsDownloadData$resolveLink$1.I$0 = i2;
                m3u8Helper2$LazyHlsDownloadData$resolveLink$1.label = 1;
                Object obj2 = Requests.get$default(app2, url, map, null, null, null, false, 0, null, 0L, null, false, null, m3u8Helper2$LazyHlsDownloadData$resolveLink$1, 3068, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = obj2;
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i2 = m3u8Helper2$LazyHlsDownloadData$resolveLink$1.I$0;
                ResultKt.throwOnFailure(obj);
            }
            ResponseBody body = ((NiceResponse) obj).getBody();
            byte[] bArrBytes = body.bytes();
            body.close();
            if (bArrBytes.length == 0) {
                throw new ErrorLoadingException("no data");
            }
            if (bArrBytes.length < 128) {
                int length = bArrBytes.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        throw new ErrorLoadingException("ASCII found instead of data");
                    }
                    if (bArrBytes[i4] < 0) {
                        break;
                    }
                    i4++;
                }
            }
            return this.isEncrypted ? M3u8Helper2.INSTANCE.getDecrypted(this.encryptionData, bArrBytes, this.encryptionIv, i2) : bArrBytes;
        }
    }

    public static /* synthetic */ Object hslLazy$default(M3u8Helper2 m3u8Helper2, M3u8Helper.M3u8Stream m3u8Stream, boolean z, boolean z2, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i2 & 8) != 0) {
            i = 3;
        }
        return m3u8Helper2.hslLazy(m3u8Stream, z3, z2, i, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:110:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:112:0x03f8  */
    /* JADX WARN: Code duplicated, block: B:113:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:116:0x0403  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object hslLazy(M3u8Helper.M3u8Stream m3u8Stream, boolean z, boolean z2, int i, Continuation<? super LazyHlsDownloadData> continuation) throws IOException {
        C09351 c09351;
        boolean z3;
        Object obj;
        boolean z4;
        int i2;
        M3u8Helper.M3u8Stream m3u8Stream2;
        byte[] bArrBytes;
        M3u8Helper.M3u8Stream m3u8Stream3;
        boolean z5;
        byte[] bArr;
        String str;
        int i3;
        int i4;
        M3u8Helper.M3u8Stream m3u8Stream4;
        byte[] bArr2;
        String str2;
        ArrayList arrayList;
        HlsPlaylistParser.Variant variant;
        String str3;
        byte[] bArr3;
        final String parentLink;
        List list;
        boolean z6;
        if (continuation instanceof C09351) {
            c09351 = (C09351) continuation;
            if ((c09351.label & Integer.MIN_VALUE) != 0) {
                c09351.label -= Integer.MIN_VALUE;
            } else {
                c09351 = new C09351(continuation);
            }
        } else {
            c09351 = new C09351(continuation);
        }
        C09351 c09352 = c09351;
        Object obj2 = c09352.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = c09352.label;
        if (i5 != 0) {
            if (i5 == 1) {
                int i6 = c09352.I$0;
                boolean z7 = c09352.Z$1;
                z3 = c09352.Z$0;
                M3u8Helper.M3u8Stream m3u8Stream5 = (M3u8Helper.M3u8Stream) c09352.L$0;
                ResultKt.throwOnFailure(obj2);
                obj = obj2;
                z4 = z7;
                i2 = i6;
                m3u8Stream2 = m3u8Stream5;
            } else {
                if (i5 == 2) {
                    int i7 = c09352.I$1;
                    int i8 = c09352.I$0;
                    boolean z8 = c09352.Z$1;
                    boolean z9 = c09352.Z$0;
                    ResultKt.throwOnFailure(obj2);
                    return obj2;
                }
                if (i5 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i9 = c09352.I$1;
                int i10 = c09352.I$0;
                boolean z10 = c09352.Z$1;
                boolean z11 = c09352.Z$0;
                bArr2 = (byte[]) c09352.L$3;
                str2 = (String) c09352.L$1;
                m3u8Stream4 = (M3u8Helper.M3u8Stream) c09352.L$0;
                ResultKt.throwOnFailure(obj2);
                i4 = i9;
                z5 = true;
            }
            ResponseBody body = ((NiceResponse) obj2).getBody();
            bArrBytes = body.bytes();
            body.close();
            str = str2;
            i3 = i4;
            bArr = bArr2;
            m3u8Stream3 = m3u8Stream4;
            bArr3 = bArrBytes;
            parentLink = getParentLink(m3u8Stream3.getStreamUrl());
            list = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(TS_EXTENSION_REGEX, str + '\n', 0, 2, null), new Function1() { // from class: com.lagradost.cloudstream3.utils.M3u8Helper2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return M3u8Helper2.hslLazy$lambda$4(parentLink, (MatchResult) obj3);
                }
            }));
            if (!list.isEmpty()) {
                throw new IllegalStateException("M3u8 must contains TS files");
            }
            if (i3 != 0) {
                z6 = z5;
            } else {
                z6 = false;
            }
            return new LazyHlsDownloadData(bArr3, bArr, z6, list, parentLink, m3u8Stream3.getHeaders());
        }
        ResultKt.throwOnFailure(obj2);
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        Requests app2 = MainActivityKt.getApp();
        String streamUrl = m3u8Stream.getStreamUrl();
        Map<String, String> headers = m3u8Stream.getHeaders();
        c09352.L$0 = m3u8Stream;
        c09352.Z$0 = z;
        c09352.Z$1 = z2;
        c09352.I$0 = i;
        c09352.label = 1;
        Object obj3 = Requests.get$default(app2, streamUrl, headers, null, null, null, false, 0, null, 0L, null, false, null, c09352, 3068, null);
        if (obj3 == coroutine_suspended) {
            c09352 = c09352;
            return coroutine_suspended;
        }
        c09352 = c09352;
        z3 = z;
        obj = obj3;
        z4 = z2;
        i2 = i;
        m3u8Stream2 = m3u8Stream;
        String text = ((NiceResponse) obj).getText();
        HlsPlaylistParser.HlsMultivariantPlaylist hlsMultivariantPlaylist = HlsPlaylistParser.INSTANCE.parse(m3u8Stream2.getStreamUrl(), text);
        if (hlsMultivariantPlaylist != null) {
            if (z4) {
                List<HlsPlaylistParser.Variant> variants = hlsMultivariantPlaylist.getVariants();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj4 : variants) {
                    if (((HlsPlaylistParser.Variant) obj4).isPlayableStandalone(hlsMultivariantPlaylist)) {
                        arrayList2.add(obj4);
                    }
                }
                arrayList = arrayList2;
            } else {
                List<HlsPlaylistParser.Variant> variants2 = hlsMultivariantPlaylist.getVariants();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj5 : variants2) {
                    if (!((HlsPlaylistParser.Variant) obj5).isTrickPlay()) {
                        arrayList3.add(obj5);
                    }
                }
                arrayList = arrayList3;
            }
            if (arrayList.isEmpty()) {
                if (z4) {
                    str3 = "M3u8 contains no video with audio";
                } else {
                    str3 = "M3u8 contains no video";
                }
                throw new IllegalStateException(str3);
            }
            if (z3) {
                Iterator it = arrayList.iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object next = it.next();
                if (it.hasNext()) {
                    HlsPlaylistParser.Variant variant2 = (HlsPlaylistParser.Variant) next;
                    long width = (((long) (variant2.getFormat().getWidth() * variant2.getFormat().getHeight())) * 1000) + ((long) variant2.getFormat().getAverageBitrate());
                    do {
                        Object next2 = it.next();
                        HlsPlaylistParser.Variant variant3 = (HlsPlaylistParser.Variant) next2;
                        long width2 = (((long) (variant3.getFormat().getWidth() * variant3.getFormat().getHeight())) * 1000) + ((long) variant3.getFormat().getAverageBitrate());
                        if (width < width2) {
                            next = next2;
                            width = width2;
                        }
                    } while (it.hasNext());
                }
                variant = (HlsPlaylistParser.Variant) next;
            } else {
                z4 = z4;
                Iterator it2 = arrayList.iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object next3 = it2.next();
                if (it2.hasNext()) {
                    HlsPlaylistParser.Variant variant4 = (HlsPlaylistParser.Variant) next3;
                    long width3 = (((long) (variant4.getFormat().getWidth() * variant4.getFormat().getHeight())) * 1000) + ((long) variant4.getFormat().getAverageBitrate());
                    while (true) {
                        Object next4 = it2.next();
                        HlsPlaylistParser.Variant variant5 = (HlsPlaylistParser.Variant) next4;
                        Iterator it3 = it2;
                        Object obj6 = next3;
                        long width4 = (((long) (variant5.getFormat().getWidth() * variant5.getFormat().getHeight())) * 1000) + ((long) variant5.getFormat().getAverageBitrate());
                        if (width3 > width4) {
                            width3 = width4;
                            next3 = next4;
                        } else {
                            next3 = obj6;
                        }
                        if (!it3.hasNext()) {
                            break;
                        }
                        it2 = it3;
                    }
                }
                variant = (HlsPlaylistParser.Variant) next3;
            }
            int height = variant.getFormat().getHeight();
            M3u8Helper.M3u8Stream m3u8Stream6 = m3u8Stream2;
            String string = variant.getUrl().getUrlString();
            Integer numBoxInt = height > 0 ? Boxing.boxInt(height) : null;
            c09352.L$0 = SpillingKt.nullOutSpilledVariable(m3u8Stream6);
            c09352.L$1 = SpillingKt.nullOutSpilledVariable(text);
            c09352.L$2 = SpillingKt.nullOutSpilledVariable(hlsMultivariantPlaylist);
            c09352.L$3 = SpillingKt.nullOutSpilledVariable(arrayList);
            c09352.L$4 = SpillingKt.nullOutSpilledVariable(variant);
            c09352.Z$0 = z3;
            boolean z12 = z4;
            c09352.Z$1 = z12;
            c09352.I$0 = i2;
            c09352.I$1 = height;
            c09352.label = 2;
            Object objHslLazy = hslLazy(new M3u8Helper.M3u8Stream(string, numBoxInt, m3u8Stream6.getHeaders()), z3, z12, i2 - 1, c09352);
            return objHslLazy == coroutine_suspended ? coroutine_suspended : objHslLazy;
        }
        M3u8Helper.M3u8Stream m3u8Stream7 = m3u8Stream2;
        boolean z13 = z4;
        boolean z14 = z3;
        byte[] bArr4 = new byte[0];
        bArrBytes = new byte[0];
        MatchResult matchResultFind$default = Regex.find$default(ENCRYPTION_URL_IV_REGEX, text, 0, 2, null);
        List<String> groupValues = matchResultFind$default != null ? matchResultFind$default.getGroupValues() : null;
        List<String> list2 = groupValues;
        if (list2 == null || list2.isEmpty()) {
            m3u8Stream3 = m3u8Stream7;
            z5 = true;
            bArr = bArr4;
            str = text;
            i3 = 0;
        } else {
            String str4 = groupValues.get(2);
            if (isNotCompleteUrl(str4)) {
                str4 = getParentLink(m3u8Stream7.getStreamUrl()) + '/' + str4;
            }
            byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(groupValues.get(3));
            Requests app3 = MainActivityKt.getApp();
            Map<String, String> headers2 = m3u8Stream7.getHeaders();
            c09352.L$0 = m3u8Stream7;
            c09352.L$1 = text;
            c09352.L$2 = SpillingKt.nullOutSpilledVariable(hlsMultivariantPlaylist);
            c09352.L$3 = bArrEncodeToByteArray;
            c09352.L$4 = SpillingKt.nullOutSpilledVariable(bArrBytes);
            c09352.L$5 = SpillingKt.nullOutSpilledVariable(groupValues);
            c09352.L$6 = SpillingKt.nullOutSpilledVariable(str4);
            c09352.Z$0 = z14;
            c09352.Z$1 = z13;
            c09352.I$0 = i2;
            z5 = true;
            c09352.I$1 = 1;
            c09352.label = 3;
            Object obj7 = Requests.get$default(app3, str4, headers2, null, null, null, false, 0, null, 0L, null, false, null, c09352, 3068, null);
            if (obj7 == coroutine_suspended) {
                return coroutine_suspended;
            }
            i4 = 1;
            m3u8Stream4 = m3u8Stream7;
            obj2 = obj7;
            bArr2 = bArrEncodeToByteArray;
            str2 = text;
            ResponseBody body2 = ((NiceResponse) obj2).getBody();
            bArrBytes = body2.bytes();
            body2.close();
            str = str2;
            i3 = i4;
            bArr = bArr2;
            m3u8Stream3 = m3u8Stream4;
        }
        bArr3 = bArrBytes;
        parentLink = getParentLink(m3u8Stream3.getStreamUrl());
        list = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(TS_EXTENSION_REGEX, str + '\n', 0, 2, null), new Function1() { // from class: com.lagradost.cloudstream3.utils.M3u8Helper2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj8) {
                return M3u8Helper2.hslLazy$lambda$4(parentLink, (MatchResult) obj8);
            }
        }));
        if (!list.isEmpty()) {
            throw new IllegalStateException("M3u8 must contains TS files");
        }
        if (i3 != 0) {
            z6 = z5;
        } else {
            z6 = false;
        }
        return new LazyHlsDownloadData(bArr3, bArr, z6, list, parentLink, m3u8Stream3.getHeaders());
    }

    static final TsLink hslLazy$lambda$4(String str, MatchResult ts) {
        Intrinsics.checkNotNullParameter(ts, "ts");
        String str2 = ts.getGroupValues().get(1);
        String str3 = ts.getGroupValues().get(3);
        if (INSTANCE.isNotCompleteUrl(str3)) {
            str3 = str + '/' + str3;
        }
        return new TsLink(str3, StringsKt.toDoubleOrNull(str2));
    }
}
