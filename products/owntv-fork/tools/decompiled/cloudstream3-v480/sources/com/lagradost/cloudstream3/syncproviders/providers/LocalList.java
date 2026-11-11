package com.lagradost.cloudstream3.syncproviders.providers;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeTrendingLinkHandlerFactory;

/* JADX INFO: compiled from: LocalList.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0096@¢\u0006\u0002\u0010\"R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/LocalList;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "icon", "", "getIcon", "()Ljava/lang/Integer;", "requiresLogin", "", "getRequiresLogin", "()Z", "createAccountUrl", "", "getCreateAccountUrl", "()Ljava/lang/Void;", "requireLibraryRefresh", "getRequireLibraryRefresh", "setRequireLibraryRefresh", "(Z)V", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LocalList extends SyncAPI {
    private final Void createAccountUrl;
    private final boolean requiresLogin;
    private final String name = PeertubeTrendingLinkHandlerFactory.KIOSK_LOCAL;
    private final String idPrefix = ImagesContract.LOCAL;
    private final int icon = R.drawable.ic_baseline_storage_24;
    private boolean requireLibraryRefresh = true;
    private final SyncIdName syncIdName = SyncIdName.LocalList;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.LocalList$library$1, reason: invalid class name */
    /* JADX INFO: compiled from: LocalList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.LocalList", f = "LocalList.kt", i = {0, 1, 1}, l = {30, 36}, m = "library", n = {"auth", "auth", "watchStatusIds"}, nl = {34, 77}, s = {"L$0", "L$0", "L$1"}, v = 2)
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
            return LocalList.this.library(null, this);
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public /* bridge */ /* synthetic */ String getCreateAccountUrl() {
        return (String) getCreateAccountUrl();
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getIdPrefix() {
        return this.idPrefix;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Integer getIcon() {
        return Integer.valueOf(this.icon);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getRequiresLogin() {
        return this.requiresLogin;
    }

    public Void getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public boolean getRequireLibraryRefresh() {
        return this.requireLibraryRefresh;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public void setRequireLibraryRefresh(boolean z) {
        this.requireLibraryRefresh = z;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public SyncIdName getSyncIdName() {
        return this.syncIdName;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b8, code lost:
    
        if (r11 == r1) goto L29;
     */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object library(com.lagradost.cloudstream3.syncproviders.AuthData r10, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.syncproviders.SyncAPI.LibraryMetadata> r11) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.LocalList.library(com.lagradost.cloudstream3.syncproviders.AuthData, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
