package com.google.firebase.firestore.remote;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import c.a.za;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.FirestoreGrpc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: loaded from: classes.dex */
public class Datastore {
    public static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    public static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList("date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"));
    public final FirestoreChannel channel;
    public final DatabaseInfo databaseInfo;
    public final RemoteSerializer serializer;
    public final AsyncQueue workerQueue;

    /* JADX INFO: renamed from: com.google.firebase.firestore.remote.Datastore$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = new int[FirebaseFirestoreException.Code.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public Datastore(DatabaseInfo databaseInfo, AsyncQueue asyncQueue, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, Context context, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo;
        this.workerQueue = asyncQueue;
        this.serializer = new RemoteSerializer(databaseInfo.getDatabaseId());
        this.channel = initializeChannel(databaseInfo, asyncQueue, credentialsProvider, credentialsProvider2, context, grpcMetadataProvider);
    }

    public static boolean isMissingSslCiphers(za zaVar) {
        za.a aVar = zaVar.p;
        Throwable th = zaVar.r;
        if (th instanceof SSLHandshakeException) {
            th.getMessage().contains("no ciphers available");
        }
        int i = Build.VERSION.SDK_INT;
        return false;
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (code) {
            case OK:
                throw new IllegalArgumentException("Treated status OK as error");
            case CANCELLED:
            case UNKNOWN:
            case DEADLINE_EXCEEDED:
            case RESOURCE_EXHAUSTED:
            case INTERNAL:
            case UNAVAILABLE:
            case UNAUTHENTICATED:
                return false;
            case INVALID_ARGUMENT:
            case NOT_FOUND:
            case ALREADY_EXISTS:
            case PERMISSION_DENIED:
            case FAILED_PRECONDITION:
            case ABORTED:
            case OUT_OF_RANGE:
            case UNIMPLEMENTED:
            case DATA_LOSS:
                return true;
            default:
                throw new IllegalArgumentException(a.a("Unknown gRPC status code: ", code));
        }
    }

    public static boolean isPermanentWriteError(za zaVar) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(zaVar.p.s)) && !zaVar.p.equals(za.a.ABORTED);
    }

    public /* synthetic */ List a(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse commitResponse = (CommitResponse) task.getResult();
        SnapshotVersion snapshotVersionDecodeVersion = this.serializer.decodeVersion(commitResponse.getCommitTime());
        int writeResultsCount = commitResponse.getWriteResultsCount();
        ArrayList arrayList = new ArrayList(writeResultsCount);
        for (int i = 0; i < writeResultsCount; i++) {
            arrayList.add(this.serializer.decodeMutationResult(commitResponse.getWriteResults(i), snapshotVersionDecodeVersion));
        }
        return arrayList;
    }

    public Task<List<MutationResult>> commit(List<Mutation> list) {
        CommitRequest.Builder builderNewBuilder = CommitRequest.newBuilder();
        builderNewBuilder.setDatabase(this.serializer.databaseName());
        Iterator<Mutation> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addWrites(this.serializer.encodeMutation(it.next()));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), builderNewBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: b.e.b.d.f.i
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f499a.a(task);
            }
        });
    }

    public WatchStream createWatchStream(WatchStream.Callback callback) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    public WriteStream createWriteStream(WriteStream.Callback callback) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    public DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    public AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    public FirestoreChannel initializeChannel(DatabaseInfo databaseInfo, AsyncQueue asyncQueue, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, Context context, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        return new FirestoreChannel(asyncQueue, context, credentialsProvider, credentialsProvider2, databaseInfo, grpcMetadataProvider);
    }

    public Task<List<MutableDocument>> lookup(final List<DocumentKey> list) {
        BatchGetDocumentsRequest.Builder builderNewBuilder = BatchGetDocumentsRequest.newBuilder();
        builderNewBuilder.setDatabase(this.serializer.databaseName());
        Iterator<DocumentKey> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addDocuments(this.serializer.encodeKey(it.next()));
        }
        return this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), builderNewBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: b.e.b.d.f.j
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f500a.a(list, task);
            }
        });
    }

    public void shutdown() {
        this.channel.shutdown();
    }

    public static boolean isPermanentError(za zaVar) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(zaVar.p.s));
    }

    public /* synthetic */ List a(List list, Task task) {
        if (!task.isSuccessful() && (task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
            this.channel.invalidateToken();
        }
        HashMap map = new HashMap();
        Iterator it = ((List) task.getResult()).iterator();
        while (it.hasNext()) {
            MutableDocument mutableDocumentDecodeMaybeDocument = this.serializer.decodeMaybeDocument((BatchGetDocumentsResponse) it.next());
            map.put(mutableDocumentDecodeMaybeDocument.getKey(), mutableDocumentDecodeMaybeDocument);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add((MutableDocument) map.get((DocumentKey) it2.next()));
        }
        return arrayList;
    }
}
