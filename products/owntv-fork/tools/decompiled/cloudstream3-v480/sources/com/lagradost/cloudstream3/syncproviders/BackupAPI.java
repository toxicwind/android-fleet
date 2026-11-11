package com.lagradost.cloudstream3.syncproviders;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: BackupAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096@¢\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/BackupAPI;", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "()V", ContentDisposition.Parameters.FileName, "", "getFilename", "()Ljava/lang/String;", "downloadFile", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFile", "data", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BackupAPI extends AuthAPI {
    private final String filename = "cloudstream-backup.json";

    public Object downloadFile(AuthData authData, Continuation<? super String> continuation) {
        return downloadFile$suspendImpl(this, authData, continuation);
    }

    public Object uploadFile(AuthData authData, String str, Continuation<? super String> continuation) {
        return uploadFile$suspendImpl(this, authData, str, continuation);
    }

    public String getFilename() {
        return this.filename;
    }

    static /* synthetic */ Object downloadFile$suspendImpl(BackupAPI backupAPI, AuthData authData, Continuation<? super String> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object uploadFile$suspendImpl(BackupAPI backupAPI, AuthData authData, String str, Continuation<? super String> continuation) {
        throw new NotImplementedError(null, 1, null);
    }
}
