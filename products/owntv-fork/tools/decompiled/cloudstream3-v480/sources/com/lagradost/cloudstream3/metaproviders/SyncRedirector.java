package com.lagradost.cloudstream3.metaproviders;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.text.Regex;

/* JADX INFO: compiled from: SyncRedirector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000eR \u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/SyncRedirector;", "", "<init>", "()V", "syncIds", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "Lkotlin/text/Regex;", "redirect", "", ImagesContract.URL, "providerApi", "Lcom/lagradost/cloudstream3/MainAPI;", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/MainAPI;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncRedirector {
    public static final SyncRedirector INSTANCE = new SyncRedirector();
    private static final List<Pair<SyncIdName, Regex>> syncIds = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(SyncIdName.MyAnimeList, new Regex("myanimelist\\.net/anime/(\\d+)")), TuplesKt.to(SyncIdName.Anilist, new Regex("anilist\\.co/anime/(\\d+)"))});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.SyncRedirector$redirect$1, reason: invalid class name */
    /* JADX INFO: compiled from: SyncRedirector.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.SyncRedirector", f = "SyncRedirector.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {47}, m = "redirect", n = {ImagesContract.URL, "providerApi", "syncName", "syncRegex", "it", "$i$a$-firstNotNullOfOrNull-SyncRedirector$redirect$2", "$i$a$-let-SyncRedirector$redirect$2$1"}, nl = {49}, s = {"L$0", "L$1", "L$3", "L$4", "L$5", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncRedirector.this.redirect(null, null, this);
        }
    }

    private SyncRedirector() {
    }

    /* JADX WARN: Code duplicated, block: B:15:0x005d A[PHI: r9 r10 r11
      0x005d: PHI (r9v7 java.lang.String) = (r9v5 java.lang.String), (r9v9 java.lang.String) binds: [B:14:0x004f, B:28:0x00c4] A[DONT_GENERATE, DONT_INLINE]
      0x005d: PHI (r10v2 java.util.Iterator) = (r10v1 java.util.Iterator), (r10v3 java.util.Iterator) binds: [B:14:0x004f, B:28:0x00c4] A[DONT_GENERATE, DONT_INLINE]
      0x005d: PHI (r11v1 com.lagradost.cloudstream3.MainAPI) = (r11v0 com.lagradost.cloudstream3.MainAPI), (r11v2 com.lagradost.cloudstream3.MainAPI) binds: [B:14:0x004f, B:28:0x00c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:17:0x0064  */
    /* JADX WARN: Code duplicated, block: B:19:0x0080  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x007e -> B:28:0x00c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0089 -> B:28:0x00c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008f -> B:28:0x00c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00bd -> B:27:0x00c0). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object redirect(java.lang.String r10, com.lagradost.cloudstream3.MainAPI r11, kotlin.coroutines.Continuation<? super java.lang.String> r12) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.SyncRedirector.redirect(java.lang.String, com.lagradost.cloudstream3.MainAPI, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
