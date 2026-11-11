package com.lagradost.cloudstream3.subtitles;

import android.net.Uri;
import coil3.util.UtilsKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.ui.player.SubtitleOrigin;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Okio__JvmOkioKt;

/* JADX INFO: compiled from: AbstractSubProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\"\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\n0\t2\u0006\u0010\f\u001a\u00020\u0005H\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\tJ\u001c\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000bJ\u001a\u0010\u0015\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000bJ4\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u001c\b\u0002\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0018H\u0086@¢\u0006\u0002\u0010\u0019R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;", "", "<init>", "()V", "downloadFile", "Ljava/io/File;", "source", "Lokio/BufferedSource;", "unzip", "", "Lkotlin/Pair;", "", UtilsKt.SCHEME_FILE, "resources", "", "Lcom/lagradost/cloudstream3/subtitles/SubtitleResource$SingleSubtitleResource;", "getSubtitles", "addUrl", "", ImagesContract.URL, "name", "addFile", "addZipUrl", "nameGenerator", "Lkotlin/Function2;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SingleSubtitleResource", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleResource {
    private List<SingleSubtitleResource> resources = new ArrayList();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.subtitles.SubtitleResource$addZipUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: AbstractSubProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.subtitles.SubtitleResource", f = "AbstractSubProvider.kt", i = {0, 0}, l = {84}, m = "addZipUrl", n = {ImagesContract.URL, "nameGenerator"}, nl = {85}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleResource.this.addZipUrl(null, null, this);
        }
    }

    static final String addZipUrl$lambda$0(String str, File file) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        Intrinsics.checkNotNullParameter(file, "<unused var>");
        return null;
    }

    public final File downloadFile(BufferedSource source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        File fileCreateTempFile = File.createTempFile("temp-subtitle", ".tmp");
        MainActivity.Companion companion = MainActivity.INSTANCE;
        Intrinsics.checkNotNull(fileCreateTempFile);
        companion.deleteFileOnExit(fileCreateTempFile);
        Intrinsics.checkNotNull(fileCreateTempFile);
        BufferedSink bufferedSinkBuffer = Okio.buffer(Okio__JvmOkioKt.sink$default(fileCreateTempFile, false, 1, null));
        bufferedSinkBuffer.writeAll(source);
        bufferedSinkBuffer.close();
        source.close();
        return fileCreateTempFile;
    }

    private final List<Pair<String, File>> unzip(File file) {
        ArrayList arrayList = new ArrayList();
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
        try {
            ZipInputStream zipInputStream2 = zipInputStream;
            for (ZipEntry nextEntry = zipInputStream2.getNextEntry(); nextEntry != null; nextEntry = zipInputStream2.getNextEntry()) {
                File fileCreateTempFile = File.createTempFile("unzipped-subtitle", ".tmp");
                MainActivity.Companion companion = MainActivity.INSTANCE;
                Intrinsics.checkNotNull(fileCreateTempFile);
                companion.deleteFileOnExit(fileCreateTempFile);
                arrayList.add(TuplesKt.to(nextEntry.getName(), fileCreateTempFile));
                Intrinsics.checkNotNull(fileCreateTempFile);
                BufferedSink bufferedSinkBuffer = Okio.buffer(Okio__JvmOkioKt.sink$default(fileCreateTempFile, false, 1, null));
                try {
                    bufferedSinkBuffer.writeAll(Okio.source(zipInputStream2));
                    CloseableKt.closeFinally(bufferedSinkBuffer, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedSinkBuffer, th);
                        throw th2;
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipInputStream, null);
            return arrayList;
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(zipInputStream, th3);
                throw th4;
            }
        }
    }

    /* JADX INFO: compiled from: AbstractSubProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/subtitles/SubtitleResource$SingleSubtitleResource;", "", "name", "", ImagesContract.URL, "origin", "Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;)V", "getName", "()Ljava/lang/String;", "getUrl", "getOrigin", "()Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SingleSubtitleResource {
        private final String name;
        private final SubtitleOrigin origin;
        private final String url;

        public static /* synthetic */ SingleSubtitleResource copy$default(SingleSubtitleResource singleSubtitleResource, String str, String str2, SubtitleOrigin subtitleOrigin, int i, Object obj) {
            if ((i & 1) != 0) {
                str = singleSubtitleResource.name;
            }
            if ((i & 2) != 0) {
                str2 = singleSubtitleResource.url;
            }
            if ((i & 4) != 0) {
                subtitleOrigin = singleSubtitleResource.origin;
            }
            return singleSubtitleResource.copy(str, str2, subtitleOrigin);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SubtitleOrigin getOrigin() {
            return this.origin;
        }

        public final SingleSubtitleResource copy(String name, String url, SubtitleOrigin origin) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(origin, "origin");
            return new SingleSubtitleResource(name, url, origin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SingleSubtitleResource)) {
                return false;
            }
            SingleSubtitleResource singleSubtitleResource = (SingleSubtitleResource) other;
            return Intrinsics.areEqual(this.name, singleSubtitleResource.name) && Intrinsics.areEqual(this.url, singleSubtitleResource.url) && this.origin == singleSubtitleResource.origin;
        }

        public int hashCode() {
            String str = this.name;
            return ((((str == null ? 0 : str.hashCode()) * 31) + this.url.hashCode()) * 31) + this.origin.hashCode();
        }

        public String toString() {
            return "SingleSubtitleResource(name=" + this.name + ", url=" + this.url + ", origin=" + this.origin + ')';
        }

        public SingleSubtitleResource(String str, String url, SubtitleOrigin origin) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(origin, "origin");
            this.name = str;
            this.url = url;
            this.origin = origin;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUrl() {
            return this.url;
        }

        public final SubtitleOrigin getOrigin() {
            return this.origin;
        }
    }

    public final List<SingleSubtitleResource> getSubtitles() {
        return CollectionsKt.toList(this.resources);
    }

    public static /* synthetic */ void addUrl$default(SubtitleResource subtitleResource, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        subtitleResource.addUrl(str, str2);
    }

    public final void addUrl(String url, String name) {
        if (url == null) {
            return;
        }
        this.resources.add(new SingleSubtitleResource(name, url, SubtitleOrigin.URL));
    }

    public static /* synthetic */ void addFile$default(SubtitleResource subtitleResource, File file, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        subtitleResource.addFile(file, str);
    }

    public final void addFile(File file, String name) {
        Intrinsics.checkNotNullParameter(file, "file");
        List<SingleSubtitleResource> list = this.resources;
        String string = Uri.fromFile(file).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        list.add(new SingleSubtitleResource(name, string, SubtitleOrigin.DOWNLOADED_FILE));
        MainActivity.INSTANCE.deleteFileOnExit(file);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object addZipUrl(String str, Function2<? super String, ? super File, String> function2, Continuation<? super Unit> continuation) throws IOException {
        AnonymousClass1 anonymousClass1;
        Function2<? super String, ? super File, String> function3;
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
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = function2;
            anonymousClass1.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            function3 = function2;
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function3 = (Function2) anonymousClass1.L$1;
            ResultKt.throwOnFailure(obj);
        }
        File fileDownloadFile = downloadFile(((NiceResponse) obj).getOkhttpResponse().body().getSource());
        List<Pair<String, File>> listUnzip = unzip(fileDownloadFile);
        FilesKt.deleteRecursively(fileDownloadFile);
        Iterator<T> it = listUnzip.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            String str2 = (String) pair.component1();
            File file = (File) pair.component2();
            addFile(file, function3.invoke(str2, file));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object addZipUrl$default(SubtitleResource subtitleResource, String str, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new Function2() { // from class: com.lagradost.cloudstream3.subtitles.SubtitleResource$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SubtitleResource.addZipUrl$lambda$0((String) obj2, (File) obj3);
                }
            };
        }
        return subtitleResource.addZipUrl(str, function2, continuation);
    }
}
