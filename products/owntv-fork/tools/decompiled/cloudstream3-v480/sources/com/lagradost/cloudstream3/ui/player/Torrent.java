package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import androidx.compose.runtime.ComposerKt;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import go.Seq;
import j$.net.URLEncoder;
import java.io.File;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.mozilla.classfile.ByteCode;
import org.mozilla.universalchardet.prober.HebrewProber;
import torrServer.TorrServer;

/* JADX INFO: compiled from: Torrent.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003)*+B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0082@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\fH\u0082@¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\fH\u0082@¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001c\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\fH\u0082@¢\u0006\u0002\u0010\u001aJ\u0016\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\fH\u0082@¢\u0006\u0002\u0010\u001aJ\"\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00170#2\u0006\u0010%\u001a\u00020$H\u0086@¢\u0006\u0002\u0010&R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Torrent;", "", "<init>", "()V", "hasAcceptedTorrentForThisSession", "", "getHasAcceptedTorrentForThisSession", "()Ljava/lang/Boolean;", "setHasAcceptedTorrentForThisSession", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "TORRENT_SERVER_PATH", "", "TIMEOUT", "", "TAG", "deleteAllFiles", "TORRENT_SERVER_URL", "echo", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", "list", "", "Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentStatus;", "drop", "hash", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rem", "clearAll", "get", "add", ImagesContract.URL, "setup", "dir", "transformLink", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "link", "(Lcom/lagradost/cloudstream3/utils/ExtractorLink;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackers", "", "TorrentRequest", "TorrentStatus", "TorrentFileStat", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Torrent {
    private static final String TAG = "Torrent";
    private static final long TIMEOUT = 3;
    private static final String TORRENT_SERVER_PATH = "torrent_tmp";
    private static Boolean hasAcceptedTorrentForThisSession;
    public static final Torrent INSTANCE = new Torrent();
    private static String TORRENT_SERVER_URL = "";
    private static final List<String> trackers = CollectionsKt.listOf((Object[]) new String[]{"udp://tracker.opentrackr.org:1337/announce", "https://tracker2.ctix.cn/announce", "https://tracker1.520.jp:443/announce", "udp://opentracker.i2p.rocks:6969/announce", "udp://open.tracker.cl:1337/announce", "udp://open.demonii.com:1337/announce", "http://tracker.openbittorrent.com:80/announce", "udp://tracker.openbittorrent.com:6969/announce", "udp://open.stealth.si:80/announce", "udp://exodus.desync.com:6969/announce", "udp://tracker-udp.gbitt.info:80/announce", "udp://explodie.org:6969/announce", "https://tracker.gbitt.info:443/announce", "http://tracker.gbitt.info:80/announce", "udp://uploads.gamecoast.net:6969/announce", "udp://tracker1.bt.moack.co.kr:80/announce", "udp://tracker.tiny-vps.com:6969/announce", "udp://tracker.theoks.net:6969/announce", "udp://tracker.dump.cl:6969/announce", "udp://tracker.bittor.pw:1337/announce", "https://tracker1.520.jp:443/announce", "udp://opentracker.i2p.rocks:6969/announce", "udp://open.tracker.cl:1337/announce", "udp://open.demonii.com:1337/announce", "http://tracker.openbittorrent.com:80/announce", "udp://tracker.openbittorrent.com:6969/announce", "udp://open.stealth.si:80/announce", "udp://exodus.desync.com:6969/announce", "udp://tracker-udp.gbitt.info:80/announce", "udp://explodie.org:6969/announce", "https://tracker.gbitt.info:443/announce", "http://tracker.gbitt.info:80/announce", "udp://uploads.gamecoast.net:6969/announce", "udp://tracker1.bt.moack.co.kr:80/announce", "udp://tracker.tiny-vps.com:6969/announce", "udp://tracker.theoks.net:6969/announce", "udp://tracker.dump.cl:6969/announce", "udp://tracker.bittor.pw:1337/announce"});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$add$1, reason: invalid class name */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0}, l = {ByteCode.ARRAYLENGTH}, m = "add", n = {ImagesContract.URL}, nl = {ByteCode.MULTIANEWARRAY}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.add(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$clearAll$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {1, 1, 1, 1, 2, 2, 2, 2}, l = {133, 142, 149}, m = "clearAll", n = {"items", "item", "hash", "allSuccess", "items", "item", "hash", "allSuccess"}, nl = {134, 143, 150}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C08641 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C08641(Continuation<? super C08641> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.clearAll(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$drop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0}, l = {90}, m = "drop", n = {"hash"}, nl = {98}, s = {"L$0"}, v = 2)
    static final class C08651 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08651(Continuation<? super C08651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.drop(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$echo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {}, l = {39}, m = "echo", n = {}, nl = {41}, s = {}, v = 2)
    static final class C08661 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C08661(Continuation<? super C08661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.echo(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$get$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0}, l = {173}, m = "get", n = {"hash"}, nl = {181}, s = {"L$0"}, v = 2)
    static final class C08671 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08671(Continuation<? super C08671> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.get(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$list$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {}, l = {74}, m = "list", n = {}, nl = {81}, s = {}, v = 2)
    static final class C08681 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C08681(Continuation<? super C08681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.list(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$rem$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0}, l = {111}, m = "rem", n = {"hash"}, nl = {119}, s = {"L$0"}, v = 2)
    static final class C08691 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08691(Continuation<? super C08691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.rem(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$setup$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0, 1, 1}, l = {203, 212}, m = "setup", n = {"dir", "dir", "port"}, nl = {ComposerKt.providerMapsKey, -1}, s = {"L$0", "L$0", "J$0"}, v = 2)
    static final class C08701 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08701(Continuation<? super C08701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.setup(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$shutdown$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {}, l = {59}, m = "shutdown", n = {}, nl = {61}, s = {}, v = 2)
    static final class C08711 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C08711(Continuation<? super C08711> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.shutdown(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$transformLink$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent", f = "Torrent.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2}, l = {221, 224, 226}, m = "transformLink", n = {"link", "act", "defaultDirectory", "link", "act", "defaultDirectory", "link", "act", "defaultDirectory", NotificationCompat.CATEGORY_STATUS}, nl = {222, 226, HebrewProber.FINAL_KAF}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C08721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08721(Continuation<? super C08721> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Torrent.this.transformLink(null, this);
        }
    }

    private Torrent() {
    }

    public final Boolean getHasAcceptedTorrentForThisSession() {
        return hasAcceptedTorrentForThisSession;
    }

    public final void setHasAcceptedTorrentForThisSession(Boolean bool) {
        hasAcceptedTorrentForThisSession = bool;
    }

    public final boolean deleteAllFiles() {
        Activity activity = CommonActivity.INSTANCE.getActivity();
        if (activity == null) {
            return false;
        }
        return FilesKt.deleteRecursively(new File(activity.getCacheDir().getPath() + "/torrent_tmp"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object echo(Continuation<? super Boolean> continuation) {
        C08661 c08661;
        boolean z;
        Object obj;
        if (continuation instanceof C08661) {
            c08661 = (C08661) continuation;
            if ((c08661.label & Integer.MIN_VALUE) != 0) {
                c08661.label -= Integer.MIN_VALUE;
            } else {
                c08661 = new C08661(continuation);
            }
        } else {
            c08661 = new C08661(continuation);
        }
        Object obj2 = c08661.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08661.label;
        boolean z2 = false;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                if (TORRENT_SERVER_URL.length() == 0) {
                    return Boxing.boxBoolean(false);
                }
                Requests app2 = MainActivityKt.getApp();
                String str = TORRENT_SERVER_URL + "/echo";
                c08661.label = 1;
                z = true;
                obj = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c08661, 4094, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj2);
                obj = obj2;
                z = true;
            }
            z2 = ((NiceResponse) obj).getText().length() > 0 ? z : false;
        } catch (ConnectException unused) {
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
        return Boxing.boxBoolean(z2);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object shutdown(Continuation<? super Boolean> continuation) {
        C08711 c08711;
        if (continuation instanceof C08711) {
            c08711 = (C08711) continuation;
            if ((c08711.label & Integer.MIN_VALUE) != 0) {
                c08711.label -= Integer.MIN_VALUE;
            } else {
                c08711 = new C08711(continuation);
            }
        } else {
            c08711 = new C08711(continuation);
        }
        Object obj = c08711.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08711.label;
        boolean isSuccessful = false;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (TORRENT_SERVER_URL.length() == 0) {
                    return Boxing.boxBoolean(false);
                }
                Requests app2 = MainActivityKt.getApp();
                String str = TORRENT_SERVER_URL + "/shutdown";
                c08711.label = 1;
                Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c08711, 4094, null);
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
            isSuccessful = ((NiceResponse) obj).getIsSuccessful();
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
        return Boxing.boxBoolean(isSuccessful);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object list(Continuation<? super TorrentStatus[]> continuation) throws ErrorLoadingException {
        C08681 c08681;
        if (continuation instanceof C08681) {
            c08681 = (C08681) continuation;
            if ((c08681.label & Integer.MIN_VALUE) != 0) {
                c08681.label -= Integer.MIN_VALUE;
            } else {
                c08681 = new C08681(continuation);
            }
        } else {
            c08681 = new C08681(continuation);
        }
        Object obj = c08681.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08681.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (TORRENT_SERVER_URL.length() == 0) {
                throw new ErrorLoadingException("Not initialized");
            }
            Requests app2 = MainActivityKt.getApp();
            String str = TORRENT_SERVER_URL + "/torrents";
            TorrentRequest torrentRequest = new TorrentRequest("list", null, null, null, null, null, false, 126, null);
            Map mapEmptyMap = MapsKt.emptyMap();
            c08681.label = 1;
            Object objPost$default = Requests.post$default(app2, str, mapEmptyMap, null, null, null, null, null, torrentRequest, null, false, 0, null, 3L, null, false, null, c08681, 61308, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(TorrentStatus[].class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object drop(String str, Continuation<? super Boolean> continuation) {
        C08651 c08651;
        if (continuation instanceof C08651) {
            c08651 = (C08651) continuation;
            if ((c08651.label & Integer.MIN_VALUE) != 0) {
                c08651.label -= Integer.MIN_VALUE;
            } else {
                c08651 = new C08651(continuation);
            }
        } else {
            c08651 = new C08651(continuation);
        }
        Object obj = c08651.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08651.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (TORRENT_SERVER_URL.length() == 0) {
                    return Boxing.boxBoolean(false);
                }
                Requests app2 = MainActivityKt.getApp();
                String str2 = TORRENT_SERVER_URL + "/torrents";
                TorrentRequest torrentRequest = new TorrentRequest("drop", str, null, null, null, null, false, 124, null);
                Map mapEmptyMap = MapsKt.emptyMap();
                c08651.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c08651.label = 1;
                Object objPost$default = Requests.post$default(app2, str2, mapEmptyMap, null, null, null, null, null, torrentRequest, null, false, 0, null, 3L, null, false, null, c08651, 61308, null);
                if (objPost$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objPost$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(((NiceResponse) obj).getIsSuccessful());
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return Boxing.boxBoolean(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object rem(String str, Continuation<? super Boolean> continuation) {
        C08691 c08691;
        if (continuation instanceof C08691) {
            c08691 = (C08691) continuation;
            if ((c08691.label & Integer.MIN_VALUE) != 0) {
                c08691.label -= Integer.MIN_VALUE;
            } else {
                c08691 = new C08691(continuation);
            }
        } else {
            c08691 = new C08691(continuation);
        }
        Object obj = c08691.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08691.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (TORRENT_SERVER_URL.length() == 0) {
                    return Boxing.boxBoolean(false);
                }
                Requests app2 = MainActivityKt.getApp();
                String str2 = TORRENT_SERVER_URL + "/torrents";
                TorrentRequest torrentRequest = new TorrentRequest("rem", str, null, null, null, null, false, 124, null);
                Map mapEmptyMap = MapsKt.emptyMap();
                c08691.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c08691.label = 1;
                Object objPost$default = Requests.post$default(app2, str2, mapEmptyMap, null, null, null, null, null, torrentRequest, null, false, 0, null, 3L, null, false, null, c08691, 61308, null);
                if (objPost$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objPost$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(((NiceResponse) obj).getIsSuccessful());
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return Boxing.boxBoolean(false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0098 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:14:0x0045, B:46:0x0113, B:48:0x011b, B:33:0x0098, B:35:0x00a0, B:36:0x00bd, B:40:0x00d7, B:42:0x00df, B:51:0x0153, B:49:0x0136, B:19:0x0064, B:20:0x006e, B:31:0x0091, B:28:0x0087), top: B:59:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00a0 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:14:0x0045, B:46:0x0113, B:48:0x011b, B:33:0x0098, B:35:0x00a0, B:36:0x00bd, B:40:0x00d7, B:42:0x00df, B:51:0x0153, B:49:0x0136, B:19:0x0064, B:20:0x006e, B:31:0x0091, B:28:0x0087), top: B:59:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00a0 -> B:53:0x016f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x0112 -> B:46:0x0113). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x016d -> B:53:0x016f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object clearAll(kotlin.coroutines.Continuation<? super java.lang.Boolean> r18) {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.player.Torrent.clearAll(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object get(String str, Continuation<? super TorrentStatus> continuation) throws ErrorLoadingException {
        C08671 c08671;
        if (continuation instanceof C08671) {
            c08671 = (C08671) continuation;
            if ((c08671.label & Integer.MIN_VALUE) != 0) {
                c08671.label -= Integer.MIN_VALUE;
            } else {
                c08671 = new C08671(continuation);
            }
        } else {
            c08671 = new C08671(continuation);
        }
        Object obj = c08671.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08671.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (TORRENT_SERVER_URL.length() == 0) {
                throw new ErrorLoadingException("Not initialized");
            }
            Requests app2 = MainActivityKt.getApp();
            String str2 = TORRENT_SERVER_URL + "/torrents";
            TorrentRequest torrentRequest = new TorrentRequest("get", str, null, null, null, null, false, 124, null);
            Map mapEmptyMap = MapsKt.emptyMap();
            c08671.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c08671.label = 1;
            Object objPost$default = Requests.post$default(app2, str2, mapEmptyMap, null, null, null, null, null, torrentRequest, null, false, 0, null, 3L, null, false, null, c08671, 61308, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(TorrentStatus.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object add(String str, Continuation<? super TorrentStatus> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (TORRENT_SERVER_URL.length() == 0) {
                throw new ErrorLoadingException("Not initialized");
            }
            Requests app2 = MainActivityKt.getApp();
            String str2 = TORRENT_SERVER_URL + "/torrents";
            TorrentRequest torrentRequest = new TorrentRequest("add", null, str, null, null, null, false, 122, null);
            Map mapEmptyMap = MapsKt.emptyMap();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.label = 1;
            Object objPost$default = Requests.post$default(app2, str2, mapEmptyMap, null, null, null, null, null, torrentRequest, null, false, 0, null, 0L, null, false, null, anonymousClass1, 65404, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(TorrentStatus.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object setup(String str, Continuation<? super Boolean> continuation) {
        C08701 c08701;
        String str2;
        Object objEcho;
        if (continuation instanceof C08701) {
            c08701 = (C08701) continuation;
            if ((c08701.label & Integer.MIN_VALUE) != 0) {
                c08701.label -= Integer.MIN_VALUE;
            } else {
                c08701 = new C08701(continuation);
            }
        } else {
            c08701 = new C08701(continuation);
        }
        Object obj = c08701.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08701.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Seq.load();
            str2 = str;
            c08701.L$0 = str2;
            c08701.label = 1;
            objEcho = echo(c08701);
            if (objEcho != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j = c08701.J$0;
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        String str3 = (String) c08701.L$0;
        ResultKt.throwOnFailure(obj);
        objEcho = obj;
        str2 = str3;
        if (((Boolean) objEcho).booleanValue()) {
            return Boxing.boxBoolean(true);
        }
        long jStartTorrentServer = TorrServer.startTorrentServer(str2, 0L);
        if (jStartTorrentServer < 0) {
            return Boxing.boxBoolean(false);
        }
        TORRENT_SERVER_URL = "http://127.0.0.1:" + jStartTorrentServer;
        TorrServer.addTrackers(CollectionsKt.joinToString$default(trackers, ",\n", null, null, 0, null, null, 62, null));
        c08701.L$0 = SpillingKt.nullOutSpilledVariable(str2);
        c08701.J$0 = jStartTorrentServer;
        c08701.label = 2;
        Object objEcho2 = echo(c08701);
        return objEcho2 == coroutine_suspended ? coroutine_suspended : objEcho2;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x011c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object transformLink(ExtractorLink extractorLink, Continuation<? super Pair<? extends ExtractorLink, TorrentStatus>> continuation) throws ErrorLoadingException {
        C08721 c08721;
        Activity activity;
        ExtractorLink extractorLink2;
        String str;
        Object obj;
        String str2;
        Activity activity2;
        ExtractorLink extractorLink3;
        TorrentStatus torrentStatus;
        Object objNewExtractorLink;
        TorrentStatus torrentStatus2;
        if (continuation instanceof C08721) {
            c08721 = (C08721) continuation;
            if ((c08721.label & Integer.MIN_VALUE) != 0) {
                c08721.label -= Integer.MIN_VALUE;
            } else {
                c08721 = new C08721(continuation);
            }
        } else {
            c08721 = new C08721(continuation);
        }
        C08721 c08722 = c08721;
        Object obj2 = c08722.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08722.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            activity = CommonActivity.INSTANCE.getActivity();
            if (activity == null) {
                throw new IllegalArgumentException("No activity");
            }
            String str3 = activity.getCacheDir().getPath() + "/torrent_tmp";
            new File(str3).mkdir();
            c08722.L$0 = extractorLink;
            c08722.L$1 = SpillingKt.nullOutSpilledVariable(activity);
            c08722.L$2 = SpillingKt.nullOutSpilledVariable(str3);
            c08722.label = 1;
            Object upVar = setup(str3, c08722);
            if (upVar != coroutine_suspended) {
                extractorLink2 = extractorLink;
                str = str3;
                obj = upVar;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            str = (String) c08722.L$2;
            Activity activity3 = (Activity) c08722.L$1;
            extractorLink2 = (ExtractorLink) c08722.L$0;
            ResultKt.throwOnFailure(obj2);
            obj = obj2;
            activity = activity3;
        } else {
            if (i == 2) {
                str2 = (String) c08722.L$2;
                activity2 = (Activity) c08722.L$1;
                extractorLink3 = (ExtractorLink) c08722.L$0;
                ResultKt.throwOnFailure(obj2);
                torrentStatus = (TorrentStatus) obj2;
                ExtractorLink extractorLink4 = extractorLink3;
                String source = extractorLink4.getSource();
                String name = extractorLink4.getName();
                String strStreamUrl = torrentStatus.streamUrl(extractorLink4.getUrl());
                ExtractorLinkType extractorLinkType = ExtractorLinkType.VIDEO;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(extractorLink4, null);
                c08722.L$0 = SpillingKt.nullOutSpilledVariable(extractorLink4);
                c08722.L$1 = SpillingKt.nullOutSpilledVariable(activity2);
                c08722.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                c08722.L$3 = torrentStatus;
                c08722.label = 3;
                objNewExtractorLink = ExtractorApiKt.newExtractorLink(source, name, strStreamUrl, extractorLinkType, anonymousClass2, c08722);
                if (objNewExtractorLink != coroutine_suspended) {
                    obj2 = objNewExtractorLink;
                    torrentStatus2 = torrentStatus;
                }
                return coroutine_suspended;
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            torrentStatus2 = (TorrentStatus) c08722.L$3;
            ResultKt.throwOnFailure(obj2);
        }
        return TuplesKt.to(obj2, torrentStatus2);
        if (!((Boolean) obj).booleanValue()) {
            throw new ErrorLoadingException("Unable to setup the torrent server");
        }
        String url = extractorLink2.getUrl();
        c08722.L$0 = extractorLink2;
        c08722.L$1 = SpillingKt.nullOutSpilledVariable(activity);
        c08722.L$2 = SpillingKt.nullOutSpilledVariable(str);
        c08722.label = 2;
        Object objAdd = add(url, c08722);
        if (objAdd != coroutine_suspended) {
            Activity activity4 = activity;
            obj2 = objAdd;
            str2 = str;
            activity2 = activity4;
            extractorLink3 = extractorLink2;
            torrentStatus = (TorrentStatus) obj2;
            ExtractorLink extractorLink5 = extractorLink3;
            String source2 = extractorLink5.getSource();
            String name2 = extractorLink5.getName();
            String strStreamUrl2 = torrentStatus.streamUrl(extractorLink5.getUrl());
            ExtractorLinkType extractorLinkType2 = ExtractorLinkType.VIDEO;
            AnonymousClass2 anonymousClass3 = new AnonymousClass2(extractorLink5, null);
            c08722.L$0 = SpillingKt.nullOutSpilledVariable(extractorLink5);
            c08722.L$1 = SpillingKt.nullOutSpilledVariable(activity2);
            c08722.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c08722.L$3 = torrentStatus;
            c08722.label = 3;
            objNewExtractorLink = ExtractorApiKt.newExtractorLink(source2, name2, strStreamUrl2, extractorLinkType2, anonymousClass3, c08722);
            if (objNewExtractorLink != coroutine_suspended) {
                obj2 = objNewExtractorLink;
                torrentStatus2 = torrentStatus;
                return TuplesKt.to(obj2, torrentStatus2);
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.Torrent$transformLink$2, reason: invalid class name */
    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.Torrent$transformLink$2", f = "Torrent.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ ExtractorLink $link;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ExtractorLink extractorLink, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$link = extractorLink;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$link, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            extractorLink.setReferer("");
            extractorLink.setQuality(this.$link.getQuality());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0003\u0010\b\u001a\u00020\u0003\u0012\b\b\u0003\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentRequest;", "", "action", "", "hash", "link", "title", "poster", "data", "saveToDB", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAction", "()Ljava/lang/String;", "getHash", "getLink", "getTitle", "getPoster", "getData", "getSaveToDB", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TorrentRequest {

        @JsonProperty("action")
        private final String action;

        @JsonProperty("data")
        private final String data;

        @JsonProperty("hash")
        private final String hash;

        @JsonProperty("link")
        private final String link;

        @JsonProperty("poster")
        private final String poster;

        @JsonProperty("save_to_db")
        private final boolean saveToDB;

        @JsonProperty("title")
        private final String title;

        public static /* synthetic */ TorrentRequest copy$default(TorrentRequest torrentRequest, String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = torrentRequest.action;
            }
            if ((i & 2) != 0) {
                str2 = torrentRequest.hash;
            }
            if ((i & 4) != 0) {
                str3 = torrentRequest.link;
            }
            if ((i & 8) != 0) {
                str4 = torrentRequest.title;
            }
            if ((i & 16) != 0) {
                str5 = torrentRequest.poster;
            }
            if ((i & 32) != 0) {
                str6 = torrentRequest.data;
            }
            if ((i & 64) != 0) {
                z = torrentRequest.saveToDB;
            }
            String str7 = str6;
            boolean z2 = z;
            String str8 = str5;
            String str9 = str3;
            return torrentRequest.copy(str, str2, str9, str4, str8, str7, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getData() {
            return this.data;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getSaveToDB() {
            return this.saveToDB;
        }

        public final TorrentRequest copy(@JsonProperty("action") String action, @JsonProperty("hash") String hash, @JsonProperty("link") String link, @JsonProperty("title") String title, @JsonProperty("poster") String poster, @JsonProperty("data") String data, @JsonProperty("save_to_db") boolean saveToDB) {
            Intrinsics.checkNotNullParameter(action, "action");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(data, "data");
            return new TorrentRequest(action, hash, link, title, poster, data, saveToDB);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TorrentRequest)) {
                return false;
            }
            TorrentRequest torrentRequest = (TorrentRequest) other;
            return Intrinsics.areEqual(this.action, torrentRequest.action) && Intrinsics.areEqual(this.hash, torrentRequest.hash) && Intrinsics.areEqual(this.link, torrentRequest.link) && Intrinsics.areEqual(this.title, torrentRequest.title) && Intrinsics.areEqual(this.poster, torrentRequest.poster) && Intrinsics.areEqual(this.data, torrentRequest.data) && this.saveToDB == torrentRequest.saveToDB;
        }

        public int hashCode() {
            return (((((((((((this.action.hashCode() * 31) + this.hash.hashCode()) * 31) + this.link.hashCode()) * 31) + this.title.hashCode()) * 31) + this.poster.hashCode()) * 31) + this.data.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.saveToDB);
        }

        public String toString() {
            return "TorrentRequest(action=" + this.action + ", hash=" + this.hash + ", link=" + this.link + ", title=" + this.title + ", poster=" + this.poster + ", data=" + this.data + ", saveToDB=" + this.saveToDB + ')';
        }

        public TorrentRequest(@JsonProperty("action") String action, @JsonProperty("hash") String hash, @JsonProperty("link") String link, @JsonProperty("title") String title, @JsonProperty("poster") String poster, @JsonProperty("data") String data, @JsonProperty("save_to_db") boolean z) {
            Intrinsics.checkNotNullParameter(action, "action");
            Intrinsics.checkNotNullParameter(hash, "hash");
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(data, "data");
            this.action = action;
            this.hash = hash;
            this.link = link;
            this.title = title;
            this.poster = poster;
            this.data = data;
            this.saveToDB = z;
        }

        public /* synthetic */ TorrentRequest(String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? false : z);
        }

        public final String getAction() {
            return this.action;
        }

        public final String getHash() {
            return this.hash;
        }

        public final String getLink() {
            return this.link;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final String getData() {
            return this.data;
        }

        public final boolean getSaveToDB() {
            return this.saveToDB;
        }
    }

    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u007f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B¡\u0003\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\"\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010#\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010$\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'\u0012\u0010\b\u0001\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010'¢\u0006\u0004\b*\u0010+J\u0010\u0010\u0081\u0001\u001a\u00020\u00032\u0007\u0010\u0082\u0001\u001a\u00020\u0003J\n\u0010\u0083\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0084\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u000bHÆ\u0003J\n\u0010\u008a\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010NJ\u0011\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010NJ\u0011\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010UJ\u0011\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010UJ\u0011\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010UJ\u0011\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010UJ\u0011\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010UJ\u0011\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010 \u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010¡\u0001\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010NJ\f\u0010¢\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0012\u0010£\u0001\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'HÆ\u0003J\u0012\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010'HÆ\u0003Jª\u0003\u0010¥\u0001\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010$\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\u0010\b\u0003\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010'HÆ\u0001¢\u0006\u0003\u0010¦\u0001J\u0017\u0010§\u0001\u001a\u00030¨\u00012\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010ª\u0001\u001a\u00020\u000bHÖ\u0081\u0004J\u000b\u0010«\u0001\u001a\u00020\u0003HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010-\"\u0004\b1\u0010/R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010-\"\u0004\b3\u0010/R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010-\"\u0004\b9\u0010/R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010-\"\u0004\bA\u0010/R\"\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\"\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bG\u0010C\"\u0004\bH\u0010ER\"\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bI\u0010C\"\u0004\bJ\u0010ER\"\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bK\u0010C\"\u0004\bL\u0010ER\"\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010Q\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\"\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010Q\u001a\u0004\bR\u0010N\"\u0004\bS\u0010PR\"\u0010\u0014\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010\u0015\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\bY\u0010U\"\u0004\bZ\u0010WR\"\u0010\u0016\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\b[\u0010U\"\u0004\b\\\u0010WR\"\u0010\u0017\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\b]\u0010U\"\u0004\b^\u0010WR\"\u0010\u0018\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\b_\u0010U\"\u0004\b`\u0010WR\"\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\ba\u0010C\"\u0004\bb\u0010ER\"\u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bc\u0010C\"\u0004\bd\u0010ER\"\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\be\u0010C\"\u0004\bf\u0010ER\"\u0010\u001c\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bg\u0010C\"\u0004\bh\u0010ER\"\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bi\u0010C\"\u0004\bj\u0010ER\"\u0010\u001e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bk\u0010C\"\u0004\bl\u0010ER\"\u0010\u001f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bm\u0010C\"\u0004\bn\u0010ER\"\u0010 \u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bo\u0010C\"\u0004\bp\u0010ER\"\u0010!\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bq\u0010C\"\u0004\br\u0010ER\"\u0010\"\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bs\u0010C\"\u0004\bt\u0010ER\"\u0010#\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010F\u001a\u0004\bu\u0010C\"\u0004\bv\u0010ER\"\u0010$\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010Q\u001a\u0004\bw\u0010N\"\u0004\bx\u0010PR \u0010%\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010-\"\u0004\bz\u0010/R&\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R'\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010'8\u0006@\u0006X\u0087\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010|\"\u0005\b\u0080\u0001\u0010~¨\u0006¬\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentStatus;", "", "title", "", "poster", "data", "timestamp", "", "name", "hash", "stat", "", "statString", "loadedSize", "torrentSize", "preloadedBytes", "preloadSize", "downloadSpeed", "", "uploadSpeed", "totalPeers", "pendingPeers", "activePeers", "connectedSeeders", "halfOpenPeers", "bytesWritten", "bytesWrittenData", "bytesRead", "bytesReadData", "bytesReadUsefulData", "chunksWritten", "chunksRead", "chunksReadUseful", "chunksReadWasted", "piecesDirtiedGood", "piecesDirtiedBad", "durationSeconds", "bitRate", "fileStats", "", "Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentFileStat;", "trackers", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getPoster", "setPoster", "getData", "setData", "getTimestamp", "()J", "setTimestamp", "(J)V", "getName", "setName", "getHash", "setHash", "getStat", "()I", "setStat", "(I)V", "getStatString", "setStatString", "getLoadedSize", "()Ljava/lang/Long;", "setLoadedSize", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getTorrentSize", "setTorrentSize", "getPreloadedBytes", "setPreloadedBytes", "getPreloadSize", "setPreloadSize", "getDownloadSpeed", "()Ljava/lang/Double;", "setDownloadSpeed", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getUploadSpeed", "setUploadSpeed", "getTotalPeers", "()Ljava/lang/Integer;", "setTotalPeers", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPendingPeers", "setPendingPeers", "getActivePeers", "setActivePeers", "getConnectedSeeders", "setConnectedSeeders", "getHalfOpenPeers", "setHalfOpenPeers", "getBytesWritten", "setBytesWritten", "getBytesWrittenData", "setBytesWrittenData", "getBytesRead", "setBytesRead", "getBytesReadData", "setBytesReadData", "getBytesReadUsefulData", "setBytesReadUsefulData", "getChunksWritten", "setChunksWritten", "getChunksRead", "setChunksRead", "getChunksReadUseful", "setChunksReadUseful", "getChunksReadWasted", "setChunksReadWasted", "getPiecesDirtiedGood", "setPiecesDirtiedGood", "getPiecesDirtiedBad", "setPiecesDirtiedBad", "getDurationSeconds", "setDurationSeconds", "getBitRate", "setBitRate", "getFileStats", "()Ljava/util/List;", "setFileStats", "(Ljava/util/List;)V", "getTrackers", "setTrackers", "streamUrl", ImagesContract.URL, "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentStatus;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TorrentStatus {

        @JsonProperty("active_peers")
        private Integer activePeers;

        @JsonProperty("bit_rate")
        private String bitRate;

        @JsonProperty("bytes_read")
        private Long bytesRead;

        @JsonProperty("bytes_read_data")
        private Long bytesReadData;

        @JsonProperty("bytes_read_useful_data")
        private Long bytesReadUsefulData;

        @JsonProperty("bytes_written")
        private Long bytesWritten;

        @JsonProperty("bytes_written_data")
        private Long bytesWrittenData;

        @JsonProperty("chunks_read")
        private Long chunksRead;

        @JsonProperty("chunks_read_useful")
        private Long chunksReadUseful;

        @JsonProperty("chunks_read_wasted")
        private Long chunksReadWasted;

        @JsonProperty("chunks_written")
        private Long chunksWritten;

        @JsonProperty("connected_seeders")
        private Integer connectedSeeders;

        @JsonProperty("data")
        private String data;

        @JsonProperty("download_speed")
        private Double downloadSpeed;

        @JsonProperty("duration_seconds")
        private Double durationSeconds;

        @JsonProperty("file_stats")
        private List<TorrentFileStat> fileStats;

        @JsonProperty("half_open_peers")
        private Integer halfOpenPeers;

        @JsonProperty("hash")
        private String hash;

        @JsonProperty("loaded_size")
        private Long loadedSize;

        @JsonProperty("name")
        private String name;

        @JsonProperty("pending_peers")
        private Integer pendingPeers;

        @JsonProperty("pieces_dirtied_bad")
        private Long piecesDirtiedBad;

        @JsonProperty("pieces_dirtied_good")
        private Long piecesDirtiedGood;

        @JsonProperty("poster")
        private String poster;

        @JsonProperty("preload_size")
        private Long preloadSize;

        @JsonProperty("preloaded_bytes")
        private Long preloadedBytes;

        @JsonProperty("stat")
        private int stat;

        @JsonProperty("stat_string")
        private String statString;

        @JsonProperty("timestamp")
        private long timestamp;

        @JsonProperty("title")
        private String title;

        @JsonProperty("torrent_size")
        private Long torrentSize;

        @JsonProperty("total_peers")
        private Integer totalPeers;

        @JsonProperty("trackers")
        private List<String> trackers;

        @JsonProperty("upload_speed")
        private Double uploadSpeed;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TorrentStatus copy$default(TorrentStatus torrentStatus, String str, String str2, String str3, long j, String str4, String str5, int i, String str6, Long l, Long l2, Long l3, Long l4, Double d, Double d2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Long l5, Long l6, Long l7, Long l8, Long l9, Long l10, Long l11, Long l12, Long l13, Long l14, Long l15, Double d3, String str7, List list, List list2, int i2, int i3, Object obj) {
            List list3;
            List list4;
            String str8 = (i2 & 1) != 0 ? torrentStatus.title : str;
            String str9 = (i2 & 2) != 0 ? torrentStatus.poster : str2;
            String str10 = (i2 & 4) != 0 ? torrentStatus.data : str3;
            long j2 = (i2 & 8) != 0 ? torrentStatus.timestamp : j;
            String str11 = (i2 & 16) != 0 ? torrentStatus.name : str4;
            String str12 = (i2 & 32) != 0 ? torrentStatus.hash : str5;
            int i4 = (i2 & 64) != 0 ? torrentStatus.stat : i;
            String str13 = (i2 & 128) != 0 ? torrentStatus.statString : str6;
            Long l16 = (i2 & 256) != 0 ? torrentStatus.loadedSize : l;
            Long l17 = (i2 & 512) != 0 ? torrentStatus.torrentSize : l2;
            Long l18 = (i2 & 1024) != 0 ? torrentStatus.preloadedBytes : l3;
            Long l19 = (i2 & 2048) != 0 ? torrentStatus.preloadSize : l4;
            Double d4 = (i2 & 4096) != 0 ? torrentStatus.downloadSpeed : d;
            String str14 = str8;
            Double d5 = (i2 & 8192) != 0 ? torrentStatus.uploadSpeed : d2;
            Integer num6 = (i2 & 16384) != 0 ? torrentStatus.totalPeers : num;
            Integer num7 = (i2 & 32768) != 0 ? torrentStatus.pendingPeers : num2;
            Integer num8 = (i2 & 65536) != 0 ? torrentStatus.activePeers : num3;
            Integer num9 = (i2 & 131072) != 0 ? torrentStatus.connectedSeeders : num4;
            Integer num10 = (i2 & 262144) != 0 ? torrentStatus.halfOpenPeers : num5;
            Long l20 = (i2 & 524288) != 0 ? torrentStatus.bytesWritten : l5;
            Long l21 = (i2 & 1048576) != 0 ? torrentStatus.bytesWrittenData : l6;
            Long l22 = (i2 & 2097152) != 0 ? torrentStatus.bytesRead : l7;
            Long l23 = (i2 & 4194304) != 0 ? torrentStatus.bytesReadData : l8;
            Long l24 = (i2 & 8388608) != 0 ? torrentStatus.bytesReadUsefulData : l9;
            Long l25 = (i2 & 16777216) != 0 ? torrentStatus.chunksWritten : l10;
            Long l26 = (i2 & 33554432) != 0 ? torrentStatus.chunksRead : l11;
            Long l27 = (i2 & 67108864) != 0 ? torrentStatus.chunksReadUseful : l12;
            Long l28 = (i2 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? torrentStatus.chunksReadWasted : l13;
            Long l29 = (i2 & 268435456) != 0 ? torrentStatus.piecesDirtiedGood : l14;
            Long l30 = (i2 & 536870912) != 0 ? torrentStatus.piecesDirtiedBad : l15;
            Double d6 = (i2 & 1073741824) != 0 ? torrentStatus.durationSeconds : d3;
            String str15 = (i2 & Integer.MIN_VALUE) != 0 ? torrentStatus.bitRate : str7;
            List list5 = (i3 & 1) != 0 ? torrentStatus.fileStats : list;
            if ((i3 & 2) != 0) {
                list4 = list5;
                list3 = torrentStatus.trackers;
            } else {
                list3 = list2;
                list4 = list5;
            }
            return torrentStatus.copy(str14, str9, str10, j2, str11, str12, i4, str13, l16, l17, l18, l19, d4, d5, num6, num7, num8, num9, num10, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, d6, str15, list4, list3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Long getTorrentSize() {
            return this.torrentSize;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Long getPreloadedBytes() {
            return this.preloadedBytes;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Long getPreloadSize() {
            return this.preloadSize;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Double getDownloadSpeed() {
            return this.downloadSpeed;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Double getUploadSpeed() {
            return this.uploadSpeed;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getTotalPeers() {
            return this.totalPeers;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Integer getPendingPeers() {
            return this.pendingPeers;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Integer getActivePeers() {
            return this.activePeers;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final Integer getConnectedSeeders() {
            return this.connectedSeeders;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final Integer getHalfOpenPeers() {
            return this.halfOpenPeers;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final Long getBytesWritten() {
            return this.bytesWritten;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final Long getBytesWrittenData() {
            return this.bytesWrittenData;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final Long getBytesRead() {
            return this.bytesRead;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final Long getBytesReadData() {
            return this.bytesReadData;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final Long getBytesReadUsefulData() {
            return this.bytesReadUsefulData;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final Long getChunksWritten() {
            return this.chunksWritten;
        }

        /* JADX INFO: renamed from: component26, reason: from getter */
        public final Long getChunksRead() {
            return this.chunksRead;
        }

        /* JADX INFO: renamed from: component27, reason: from getter */
        public final Long getChunksReadUseful() {
            return this.chunksReadUseful;
        }

        /* JADX INFO: renamed from: component28, reason: from getter */
        public final Long getChunksReadWasted() {
            return this.chunksReadWasted;
        }

        /* JADX INFO: renamed from: component29, reason: from getter */
        public final Long getPiecesDirtiedGood() {
            return this.piecesDirtiedGood;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getData() {
            return this.data;
        }

        /* JADX INFO: renamed from: component30, reason: from getter */
        public final Long getPiecesDirtiedBad() {
            return this.piecesDirtiedBad;
        }

        /* JADX INFO: renamed from: component31, reason: from getter */
        public final Double getDurationSeconds() {
            return this.durationSeconds;
        }

        /* JADX INFO: renamed from: component32, reason: from getter */
        public final String getBitRate() {
            return this.bitRate;
        }

        public final List<TorrentFileStat> component33() {
            return this.fileStats;
        }

        public final List<String> component34() {
            return this.trackers;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getStat() {
            return this.stat;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getStatString() {
            return this.statString;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Long getLoadedSize() {
            return this.loadedSize;
        }

        public final TorrentStatus copy(@JsonProperty("title") String title, @JsonProperty("poster") String poster, @JsonProperty("data") String data, @JsonProperty("timestamp") long timestamp, @JsonProperty("name") String name, @JsonProperty("hash") String hash, @JsonProperty("stat") int stat, @JsonProperty("stat_string") String statString, @JsonProperty("loaded_size") Long loadedSize, @JsonProperty("torrent_size") Long torrentSize, @JsonProperty("preloaded_bytes") Long preloadedBytes, @JsonProperty("preload_size") Long preloadSize, @JsonProperty("download_speed") Double downloadSpeed, @JsonProperty("upload_speed") Double uploadSpeed, @JsonProperty("total_peers") Integer totalPeers, @JsonProperty("pending_peers") Integer pendingPeers, @JsonProperty("active_peers") Integer activePeers, @JsonProperty("connected_seeders") Integer connectedSeeders, @JsonProperty("half_open_peers") Integer halfOpenPeers, @JsonProperty("bytes_written") Long bytesWritten, @JsonProperty("bytes_written_data") Long bytesWrittenData, @JsonProperty("bytes_read") Long bytesRead, @JsonProperty("bytes_read_data") Long bytesReadData, @JsonProperty("bytes_read_useful_data") Long bytesReadUsefulData, @JsonProperty("chunks_written") Long chunksWritten, @JsonProperty("chunks_read") Long chunksRead, @JsonProperty("chunks_read_useful") Long chunksReadUseful, @JsonProperty("chunks_read_wasted") Long chunksReadWasted, @JsonProperty("pieces_dirtied_good") Long piecesDirtiedGood, @JsonProperty("pieces_dirtied_bad") Long piecesDirtiedBad, @JsonProperty("duration_seconds") Double durationSeconds, @JsonProperty("bit_rate") String bitRate, @JsonProperty("file_stats") List<TorrentFileStat> fileStats, @JsonProperty("trackers") List<String> trackers) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(statString, "statString");
            return new TorrentStatus(title, poster, data, timestamp, name, hash, stat, statString, loadedSize, torrentSize, preloadedBytes, preloadSize, downloadSpeed, uploadSpeed, totalPeers, pendingPeers, activePeers, connectedSeeders, halfOpenPeers, bytesWritten, bytesWrittenData, bytesRead, bytesReadData, bytesReadUsefulData, chunksWritten, chunksRead, chunksReadUseful, chunksReadWasted, piecesDirtiedGood, piecesDirtiedBad, durationSeconds, bitRate, fileStats, trackers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TorrentStatus)) {
                return false;
            }
            TorrentStatus torrentStatus = (TorrentStatus) other;
            return Intrinsics.areEqual(this.title, torrentStatus.title) && Intrinsics.areEqual(this.poster, torrentStatus.poster) && Intrinsics.areEqual(this.data, torrentStatus.data) && this.timestamp == torrentStatus.timestamp && Intrinsics.areEqual(this.name, torrentStatus.name) && Intrinsics.areEqual(this.hash, torrentStatus.hash) && this.stat == torrentStatus.stat && Intrinsics.areEqual(this.statString, torrentStatus.statString) && Intrinsics.areEqual(this.loadedSize, torrentStatus.loadedSize) && Intrinsics.areEqual(this.torrentSize, torrentStatus.torrentSize) && Intrinsics.areEqual(this.preloadedBytes, torrentStatus.preloadedBytes) && Intrinsics.areEqual(this.preloadSize, torrentStatus.preloadSize) && Intrinsics.areEqual((Object) this.downloadSpeed, (Object) torrentStatus.downloadSpeed) && Intrinsics.areEqual((Object) this.uploadSpeed, (Object) torrentStatus.uploadSpeed) && Intrinsics.areEqual(this.totalPeers, torrentStatus.totalPeers) && Intrinsics.areEqual(this.pendingPeers, torrentStatus.pendingPeers) && Intrinsics.areEqual(this.activePeers, torrentStatus.activePeers) && Intrinsics.areEqual(this.connectedSeeders, torrentStatus.connectedSeeders) && Intrinsics.areEqual(this.halfOpenPeers, torrentStatus.halfOpenPeers) && Intrinsics.areEqual(this.bytesWritten, torrentStatus.bytesWritten) && Intrinsics.areEqual(this.bytesWrittenData, torrentStatus.bytesWrittenData) && Intrinsics.areEqual(this.bytesRead, torrentStatus.bytesRead) && Intrinsics.areEqual(this.bytesReadData, torrentStatus.bytesReadData) && Intrinsics.areEqual(this.bytesReadUsefulData, torrentStatus.bytesReadUsefulData) && Intrinsics.areEqual(this.chunksWritten, torrentStatus.chunksWritten) && Intrinsics.areEqual(this.chunksRead, torrentStatus.chunksRead) && Intrinsics.areEqual(this.chunksReadUseful, torrentStatus.chunksReadUseful) && Intrinsics.areEqual(this.chunksReadWasted, torrentStatus.chunksReadWasted) && Intrinsics.areEqual(this.piecesDirtiedGood, torrentStatus.piecesDirtiedGood) && Intrinsics.areEqual(this.piecesDirtiedBad, torrentStatus.piecesDirtiedBad) && Intrinsics.areEqual((Object) this.durationSeconds, (Object) torrentStatus.durationSeconds) && Intrinsics.areEqual(this.bitRate, torrentStatus.bitRate) && Intrinsics.areEqual(this.fileStats, torrentStatus.fileStats) && Intrinsics.areEqual(this.trackers, torrentStatus.trackers);
        }

        public int hashCode() {
            int iHashCode = ((this.title.hashCode() * 31) + this.poster.hashCode()) * 31;
            String str = this.data;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.timestamp)) * 31;
            String str2 = this.name;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.hash;
            int iHashCode4 = (((((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.stat) * 31) + this.statString.hashCode()) * 31;
            Long l = this.loadedSize;
            int iHashCode5 = (iHashCode4 + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.torrentSize;
            int iHashCode6 = (iHashCode5 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Long l3 = this.preloadedBytes;
            int iHashCode7 = (iHashCode6 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.preloadSize;
            int iHashCode8 = (iHashCode7 + (l4 == null ? 0 : l4.hashCode())) * 31;
            Double d = this.downloadSpeed;
            int iHashCode9 = (iHashCode8 + (d == null ? 0 : d.hashCode())) * 31;
            Double d2 = this.uploadSpeed;
            int iHashCode10 = (iHashCode9 + (d2 == null ? 0 : d2.hashCode())) * 31;
            Integer num = this.totalPeers;
            int iHashCode11 = (iHashCode10 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.pendingPeers;
            int iHashCode12 = (iHashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.activePeers;
            int iHashCode13 = (iHashCode12 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.connectedSeeders;
            int iHashCode14 = (iHashCode13 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.halfOpenPeers;
            int iHashCode15 = (iHashCode14 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Long l5 = this.bytesWritten;
            int iHashCode16 = (iHashCode15 + (l5 == null ? 0 : l5.hashCode())) * 31;
            Long l6 = this.bytesWrittenData;
            int iHashCode17 = (iHashCode16 + (l6 == null ? 0 : l6.hashCode())) * 31;
            Long l7 = this.bytesRead;
            int iHashCode18 = (iHashCode17 + (l7 == null ? 0 : l7.hashCode())) * 31;
            Long l8 = this.bytesReadData;
            int iHashCode19 = (iHashCode18 + (l8 == null ? 0 : l8.hashCode())) * 31;
            Long l9 = this.bytesReadUsefulData;
            int iHashCode20 = (iHashCode19 + (l9 == null ? 0 : l9.hashCode())) * 31;
            Long l10 = this.chunksWritten;
            int iHashCode21 = (iHashCode20 + (l10 == null ? 0 : l10.hashCode())) * 31;
            Long l11 = this.chunksRead;
            int iHashCode22 = (iHashCode21 + (l11 == null ? 0 : l11.hashCode())) * 31;
            Long l12 = this.chunksReadUseful;
            int iHashCode23 = (iHashCode22 + (l12 == null ? 0 : l12.hashCode())) * 31;
            Long l13 = this.chunksReadWasted;
            int iHashCode24 = (iHashCode23 + (l13 == null ? 0 : l13.hashCode())) * 31;
            Long l14 = this.piecesDirtiedGood;
            int iHashCode25 = (iHashCode24 + (l14 == null ? 0 : l14.hashCode())) * 31;
            Long l15 = this.piecesDirtiedBad;
            int iHashCode26 = (iHashCode25 + (l15 == null ? 0 : l15.hashCode())) * 31;
            Double d3 = this.durationSeconds;
            int iHashCode27 = (iHashCode26 + (d3 == null ? 0 : d3.hashCode())) * 31;
            String str4 = this.bitRate;
            int iHashCode28 = (iHashCode27 + (str4 == null ? 0 : str4.hashCode())) * 31;
            List<TorrentFileStat> list = this.fileStats;
            int iHashCode29 = (iHashCode28 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.trackers;
            return iHashCode29 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "TorrentStatus(title=" + this.title + ", poster=" + this.poster + ", data=" + this.data + ", timestamp=" + this.timestamp + ", name=" + this.name + ", hash=" + this.hash + ", stat=" + this.stat + ", statString=" + this.statString + ", loadedSize=" + this.loadedSize + ", torrentSize=" + this.torrentSize + ", preloadedBytes=" + this.preloadedBytes + ", preloadSize=" + this.preloadSize + ", downloadSpeed=" + this.downloadSpeed + ", uploadSpeed=" + this.uploadSpeed + ", totalPeers=" + this.totalPeers + ", pendingPeers=" + this.pendingPeers + ", activePeers=" + this.activePeers + ", connectedSeeders=" + this.connectedSeeders + ", halfOpenPeers=" + this.halfOpenPeers + ", bytesWritten=" + this.bytesWritten + ", bytesWrittenData=" + this.bytesWrittenData + ", bytesRead=" + this.bytesRead + ", bytesReadData=" + this.bytesReadData + ", bytesReadUsefulData=" + this.bytesReadUsefulData + ", chunksWritten=" + this.chunksWritten + ", chunksRead=" + this.chunksRead + ", chunksReadUseful=" + this.chunksReadUseful + ", chunksReadWasted=" + this.chunksReadWasted + ", piecesDirtiedGood=" + this.piecesDirtiedGood + ", piecesDirtiedBad=" + this.piecesDirtiedBad + ", durationSeconds=" + this.durationSeconds + ", bitRate=" + this.bitRate + ", fileStats=" + this.fileStats + ", trackers=" + this.trackers + ')';
        }

        public TorrentStatus(@JsonProperty("title") String title, @JsonProperty("poster") String poster, @JsonProperty("data") String str, @JsonProperty("timestamp") long j, @JsonProperty("name") String str2, @JsonProperty("hash") String str3, @JsonProperty("stat") int i, @JsonProperty("stat_string") String statString, @JsonProperty("loaded_size") Long l, @JsonProperty("torrent_size") Long l2, @JsonProperty("preloaded_bytes") Long l3, @JsonProperty("preload_size") Long l4, @JsonProperty("download_speed") Double d, @JsonProperty("upload_speed") Double d2, @JsonProperty("total_peers") Integer num, @JsonProperty("pending_peers") Integer num2, @JsonProperty("active_peers") Integer num3, @JsonProperty("connected_seeders") Integer num4, @JsonProperty("half_open_peers") Integer num5, @JsonProperty("bytes_written") Long l5, @JsonProperty("bytes_written_data") Long l6, @JsonProperty("bytes_read") Long l7, @JsonProperty("bytes_read_data") Long l8, @JsonProperty("bytes_read_useful_data") Long l9, @JsonProperty("chunks_written") Long l10, @JsonProperty("chunks_read") Long l11, @JsonProperty("chunks_read_useful") Long l12, @JsonProperty("chunks_read_wasted") Long l13, @JsonProperty("pieces_dirtied_good") Long l14, @JsonProperty("pieces_dirtied_bad") Long l15, @JsonProperty("duration_seconds") Double d3, @JsonProperty("bit_rate") String str4, @JsonProperty("file_stats") List<TorrentFileStat> list, @JsonProperty("trackers") List<String> list2) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intrinsics.checkNotNullParameter(statString, "statString");
            this.title = title;
            this.poster = poster;
            this.data = str;
            this.timestamp = j;
            this.name = str2;
            this.hash = str3;
            this.stat = i;
            this.statString = statString;
            this.loadedSize = l;
            this.torrentSize = l2;
            this.preloadedBytes = l3;
            this.preloadSize = l4;
            this.downloadSpeed = d;
            this.uploadSpeed = d2;
            this.totalPeers = num;
            this.pendingPeers = num2;
            this.activePeers = num3;
            this.connectedSeeders = num4;
            this.halfOpenPeers = num5;
            this.bytesWritten = l5;
            this.bytesWrittenData = l6;
            this.bytesRead = l7;
            this.bytesReadData = l8;
            this.bytesReadUsefulData = l9;
            this.chunksWritten = l10;
            this.chunksRead = l11;
            this.chunksReadUseful = l12;
            this.chunksReadWasted = l13;
            this.piecesDirtiedGood = l14;
            this.piecesDirtiedBad = l15;
            this.durationSeconds = d3;
            this.bitRate = str4;
            this.fileStats = list;
            this.trackers = list2;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.title = str;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final void setPoster(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.poster = str;
        }

        public final String getData() {
            return this.data;
        }

        public final void setData(String str) {
            this.data = str;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final void setTimestamp(long j) {
            this.timestamp = j;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getHash() {
            return this.hash;
        }

        public final void setHash(String str) {
            this.hash = str;
        }

        public final int getStat() {
            return this.stat;
        }

        public final void setStat(int i) {
            this.stat = i;
        }

        public final String getStatString() {
            return this.statString;
        }

        public final void setStatString(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.statString = str;
        }

        public final Long getLoadedSize() {
            return this.loadedSize;
        }

        public final void setLoadedSize(Long l) {
            this.loadedSize = l;
        }

        public final Long getTorrentSize() {
            return this.torrentSize;
        }

        public final void setTorrentSize(Long l) {
            this.torrentSize = l;
        }

        public final Long getPreloadedBytes() {
            return this.preloadedBytes;
        }

        public final void setPreloadedBytes(Long l) {
            this.preloadedBytes = l;
        }

        public final Long getPreloadSize() {
            return this.preloadSize;
        }

        public final void setPreloadSize(Long l) {
            this.preloadSize = l;
        }

        public final Double getDownloadSpeed() {
            return this.downloadSpeed;
        }

        public final void setDownloadSpeed(Double d) {
            this.downloadSpeed = d;
        }

        public final Double getUploadSpeed() {
            return this.uploadSpeed;
        }

        public final void setUploadSpeed(Double d) {
            this.uploadSpeed = d;
        }

        public final Integer getTotalPeers() {
            return this.totalPeers;
        }

        public final void setTotalPeers(Integer num) {
            this.totalPeers = num;
        }

        public final Integer getPendingPeers() {
            return this.pendingPeers;
        }

        public final void setPendingPeers(Integer num) {
            this.pendingPeers = num;
        }

        public final Integer getActivePeers() {
            return this.activePeers;
        }

        public final void setActivePeers(Integer num) {
            this.activePeers = num;
        }

        public final Integer getConnectedSeeders() {
            return this.connectedSeeders;
        }

        public final void setConnectedSeeders(Integer num) {
            this.connectedSeeders = num;
        }

        public final Integer getHalfOpenPeers() {
            return this.halfOpenPeers;
        }

        public final void setHalfOpenPeers(Integer num) {
            this.halfOpenPeers = num;
        }

        public final Long getBytesWritten() {
            return this.bytesWritten;
        }

        public final void setBytesWritten(Long l) {
            this.bytesWritten = l;
        }

        public final Long getBytesWrittenData() {
            return this.bytesWrittenData;
        }

        public final void setBytesWrittenData(Long l) {
            this.bytesWrittenData = l;
        }

        public final Long getBytesRead() {
            return this.bytesRead;
        }

        public final void setBytesRead(Long l) {
            this.bytesRead = l;
        }

        public final Long getBytesReadData() {
            return this.bytesReadData;
        }

        public final void setBytesReadData(Long l) {
            this.bytesReadData = l;
        }

        public final Long getBytesReadUsefulData() {
            return this.bytesReadUsefulData;
        }

        public final void setBytesReadUsefulData(Long l) {
            this.bytesReadUsefulData = l;
        }

        public final Long getChunksWritten() {
            return this.chunksWritten;
        }

        public final void setChunksWritten(Long l) {
            this.chunksWritten = l;
        }

        public final Long getChunksRead() {
            return this.chunksRead;
        }

        public final void setChunksRead(Long l) {
            this.chunksRead = l;
        }

        public final Long getChunksReadUseful() {
            return this.chunksReadUseful;
        }

        public final void setChunksReadUseful(Long l) {
            this.chunksReadUseful = l;
        }

        public final Long getChunksReadWasted() {
            return this.chunksReadWasted;
        }

        public final void setChunksReadWasted(Long l) {
            this.chunksReadWasted = l;
        }

        public final Long getPiecesDirtiedGood() {
            return this.piecesDirtiedGood;
        }

        public final void setPiecesDirtiedGood(Long l) {
            this.piecesDirtiedGood = l;
        }

        public final Long getPiecesDirtiedBad() {
            return this.piecesDirtiedBad;
        }

        public final void setPiecesDirtiedBad(Long l) {
            this.piecesDirtiedBad = l;
        }

        public final Double getDurationSeconds() {
            return this.durationSeconds;
        }

        public final void setDurationSeconds(Double d) {
            this.durationSeconds = d;
        }

        public final String getBitRate() {
            return this.bitRate;
        }

        public final void setBitRate(String str) {
            this.bitRate = str;
        }

        public final List<TorrentFileStat> getFileStats() {
            return this.fileStats;
        }

        public final void setFileStats(List<TorrentFileStat> list) {
            this.fileStats = list;
        }

        public final List<String> getTrackers() {
            return this.trackers;
        }

        public final void setTrackers(List<String> list) {
            this.trackers = list;
        }

        public final String streamUrl(String url) throws ErrorLoadingException {
            String path;
            Intrinsics.checkNotNullParameter(url, "url");
            List<TorrentFileStat> list = this.fileStats;
            if (list != null) {
                for (TorrentFileStat torrentFileStat : list) {
                    String path2 = torrentFileStat.getPath();
                    if (!(path2 == null || StringsKt.isBlank(path2))) {
                        if (torrentFileStat != null && (path = torrentFileStat.getPath()) != null) {
                            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.substringBefore$default(StringsKt.substringAfter$default(url, "index=", (String) null, 2, (Object) null), "&", (String) null, 2, (Object) null));
                            return Torrent.TORRENT_SERVER_URL + "/stream/" + URLEncoder.encode(path, "utf-8") + "?link=" + this.hash + "&index=" + (intOrNull != null ? intOrNull.intValue() : 0) + "&play";
                        }
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            throw new ErrorLoadingException("Null path");
        }
    }

    /* JADX INFO: compiled from: Torrent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J2\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentFileStat;", "", "id", "", "path", "", "length", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPath", "()Ljava/lang/String;", "getLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)Lcom/lagradost/cloudstream3/ui/player/Torrent$TorrentFileStat;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TorrentFileStat {

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("length")
        private final Long length;

        @JsonProperty("path")
        private final String path;

        public static /* synthetic */ TorrentFileStat copy$default(TorrentFileStat torrentFileStat, Integer num, String str, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                num = torrentFileStat.id;
            }
            if ((i & 2) != 0) {
                str = torrentFileStat.path;
            }
            if ((i & 4) != 0) {
                l = torrentFileStat.length;
            }
            return torrentFileStat.copy(num, str, l);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getLength() {
            return this.length;
        }

        public final TorrentFileStat copy(@JsonProperty("id") Integer id, @JsonProperty("path") String path, @JsonProperty("length") Long length) {
            return new TorrentFileStat(id, path, length);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TorrentFileStat)) {
                return false;
            }
            TorrentFileStat torrentFileStat = (TorrentFileStat) other;
            return Intrinsics.areEqual(this.id, torrentFileStat.id) && Intrinsics.areEqual(this.path, torrentFileStat.path) && Intrinsics.areEqual(this.length, torrentFileStat.length);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.path;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Long l = this.length;
            return iHashCode2 + (l != null ? l.hashCode() : 0);
        }

        public String toString() {
            return "TorrentFileStat(id=" + this.id + ", path=" + this.path + ", length=" + this.length + ')';
        }

        public TorrentFileStat(@JsonProperty("id") Integer num, @JsonProperty("path") String str, @JsonProperty("length") Long l) {
            this.id = num;
            this.path = str;
            this.length = l;
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getPath() {
            return this.path;
        }

        public final Long getLength() {
            return this.length;
        }
    }
}
