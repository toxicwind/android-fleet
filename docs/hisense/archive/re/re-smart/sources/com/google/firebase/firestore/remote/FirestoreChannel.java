package com.google.firebase.firestore.remote;

import android.content.Context;
import c.a.AbstractC0226j;
import c.a.C0219ea;
import c.a.C0223ga;
import c.a.D;
import c.a.za;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.BuildConfig;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FirestoreChannel {
    public final CredentialsProvider<String> appCheckProvider;
    public final AsyncQueue asyncQueue;
    public final CredentialsProvider<User> authProvider;
    public final GrpcCallProvider callProvider;
    public final GrpcMetadataProvider metadataProvider;
    public final String resourcePrefixValue;
    public static final C0219ea.e<String> X_GOOG_API_CLIENT_HEADER = C0219ea.e.a("x-goog-api-client", C0219ea.f1798a);
    public static final C0219ea.e<String> RESOURCE_PREFIX_HEADER = C0219ea.e.a("google-cloud-resource-prefix", C0219ea.f1798a);
    public static volatile String clientLanguage = "gl-java/";

    public FirestoreChannel(AsyncQueue asyncQueue, Context context, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, DatabaseInfo databaseInfo, GrpcMetadataProvider grpcMetadataProvider) {
        this.asyncQueue = asyncQueue;
        this.metadataProvider = grpcMetadataProvider;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.callProvider = new GrpcCallProvider(asyncQueue, context, databaseInfo, new FirestoreCallCredentials(credentialsProvider, credentialsProvider2));
        DatabaseId databaseId = databaseInfo.getDatabaseId();
        this.resourcePrefixValue = String.format("projects/%s/databases/%s", databaseId.getProjectId(), databaseId.getDatabaseId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseFirestoreException exceptionFromStatus(za zaVar) {
        Datastore.isMissingSslCiphers(zaVar);
        return Util.exceptionFromStatus(zaVar);
    }

    private String getGoogApiClientValue() {
        return String.format("%s fire/%s grpc/", clientLanguage, BuildConfig.VERSION_NAME);
    }

    private C0219ea requestHeaders() {
        C0219ea c0219ea = new C0219ea();
        c0219ea.a(X_GOOG_API_CLIENT_HEADER, getGoogApiClientValue());
        c0219ea.a(RESOURCE_PREFIX_HEADER, this.resourcePrefixValue);
        GrpcMetadataProvider grpcMetadataProvider = this.metadataProvider;
        if (grpcMetadataProvider != null) {
            grpcMetadataProvider.updateMetadata(c0219ea);
        }
        return c0219ea;
    }

    public static void setClientLanguage(String str) {
        clientLanguage = str;
    }

    public /* synthetic */ void a(final AbstractC0226j[] abstractC0226jArr, final IncomingStreamObserver incomingStreamObserver, Task task) {
        abstractC0226jArr[0] = (AbstractC0226j) task.getResult();
        abstractC0226jArr[0].start(new AbstractC0226j.a<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.1
            @Override // c.a.AbstractC0226j.a
            public void onClose(za zaVar, C0219ea c0219ea) {
                try {
                    incomingStreamObserver.onClose(zaVar);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            @Override // c.a.AbstractC0226j.a
            public void onHeaders(C0219ea c0219ea) {
                try {
                    incomingStreamObserver.onHeaders(c0219ea);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            @Override // c.a.AbstractC0226j.a
            public void onMessage(RespT respt) {
                try {
                    incomingStreamObserver.onNext(respt);
                    abstractC0226jArr[0].request(1);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            @Override // c.a.AbstractC0226j.a
            public void onReady() {
            }
        }, requestHeaders());
        incomingStreamObserver.onOpen();
        abstractC0226jArr[0].request(1);
    }

    public /* synthetic */ void b(final TaskCompletionSource taskCompletionSource, Object obj, Task task) {
        final AbstractC0226j abstractC0226j = (AbstractC0226j) task.getResult();
        final ArrayList arrayList = new ArrayList();
        abstractC0226j.start(new AbstractC0226j.a<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.3
            @Override // c.a.AbstractC0226j.a
            public void onClose(za zaVar, C0219ea c0219ea) {
                if (zaVar.c()) {
                    taskCompletionSource.setResult(arrayList);
                } else {
                    taskCompletionSource.setException(FirestoreChannel.this.exceptionFromStatus(zaVar));
                }
            }

            @Override // c.a.AbstractC0226j.a
            public void onMessage(RespT respt) {
                arrayList.add(respt);
                abstractC0226j.request(1);
            }
        }, requestHeaders());
        abstractC0226j.request(1);
        abstractC0226j.sendMessage(obj);
        abstractC0226j.halfClose();
    }

    public void invalidateToken() {
        this.authProvider.invalidateToken();
        this.appCheckProvider.invalidateToken();
    }

    public <ReqT, RespT> AbstractC0226j<ReqT, RespT> runBidiStreamingRpc(C0223ga<ReqT, RespT> c0223ga, final IncomingStreamObserver<RespT> incomingStreamObserver) {
        final AbstractC0226j[] abstractC0226jArr = {null};
        final Task<AbstractC0226j<ReqT, RespT>> taskCreateClientCall = this.callProvider.createClientCall(c0223ga);
        taskCreateClientCall.addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: b.e.b.d.f.l
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f505a.a(abstractC0226jArr, incomingStreamObserver, task);
            }
        });
        return new D<ReqT, RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.2
            @Override // c.a.D, c.a.ma
            public AbstractC0226j<ReqT, RespT> delegate() {
                Assert.hardAssert(abstractC0226jArr[0] != null, "ClientCall used before onOpen() callback", new Object[0]);
                return abstractC0226jArr[0];
            }

            @Override // c.a.D, c.a.ma, c.a.AbstractC0226j
            public void halfClose() {
                if (abstractC0226jArr[0] == null) {
                    taskCreateClientCall.addOnSuccessListener(FirestoreChannel.this.asyncQueue.getExecutor(), new OnSuccessListener() { // from class: b.e.b.d.f.v
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            ((AbstractC0226j) obj).halfClose();
                        }
                    });
                } else {
                    super.halfClose();
                }
            }
        };
    }

    public <ReqT, RespT> Task<RespT> runRpc(C0223ga<ReqT, RespT> c0223ga, final ReqT reqt) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.callProvider.createClientCall(c0223ga).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: b.e.b.d.f.m
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f508a.a(taskCompletionSource, reqt, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    public <ReqT, RespT> Task<List<RespT>> runStreamingResponseRpc(C0223ga<ReqT, RespT> c0223ga, final ReqT reqt) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.callProvider.createClientCall(c0223ga).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: b.e.b.d.f.n
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f511a.b(taskCompletionSource, reqt, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    public void shutdown() {
        this.callProvider.shutdown();
    }

    public /* synthetic */ void a(final TaskCompletionSource taskCompletionSource, Object obj, Task task) {
        AbstractC0226j abstractC0226j = (AbstractC0226j) task.getResult();
        abstractC0226j.start(new AbstractC0226j.a<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.4
            @Override // c.a.AbstractC0226j.a
            public void onClose(za zaVar, C0219ea c0219ea) {
                if (!zaVar.c()) {
                    taskCompletionSource.setException(FirestoreChannel.this.exceptionFromStatus(zaVar));
                } else {
                    if (taskCompletionSource.getTask().isComplete()) {
                        return;
                    }
                    taskCompletionSource.setException(new FirebaseFirestoreException("Received onClose with status OK, but no message.", FirebaseFirestoreException.Code.INTERNAL));
                }
            }

            @Override // c.a.AbstractC0226j.a
            public void onMessage(RespT respt) {
                taskCompletionSource.setResult(respt);
            }
        }, requestHeaders());
        abstractC0226j.request(2);
        abstractC0226j.sendMessage(obj);
        abstractC0226j.halfClose();
    }
}
