package com.google.firestore.v1;

import b.b.a.c.a;
import c.a.AbstractC0222g;
import c.a.AbstractC0226j;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.InterfaceC0216d;
import c.a.e.b;
import c.a.e.c;
import c.a.e.d;
import c.a.e.g;
import c.a.e.i;
import c.a.e.j;
import c.a.e.k;
import c.a.e.l;
import c.a.e.n;
import c.a.e.o;
import c.a.e.r;
import c.a.ra;
import c.a.ta;
import c.a.va;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.protobuf.Empty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FirestoreGrpc {
    public static final int METHODID_BATCH_GET_DOCUMENTS = 5;
    public static final int METHODID_BEGIN_TRANSACTION = 6;
    public static final int METHODID_COMMIT = 7;
    public static final int METHODID_CREATE_DOCUMENT = 2;
    public static final int METHODID_DELETE_DOCUMENT = 4;
    public static final int METHODID_GET_DOCUMENT = 0;
    public static final int METHODID_LISTEN = 12;
    public static final int METHODID_LIST_COLLECTION_IDS = 10;
    public static final int METHODID_LIST_DOCUMENTS = 1;
    public static final int METHODID_ROLLBACK = 8;
    public static final int METHODID_RUN_QUERY = 9;
    public static final int METHODID_UPDATE_DOCUMENT = 3;
    public static final int METHODID_WRITE = 11;
    public static final String SERVICE_NAME = "google.firestore.v1.Firestore";
    public static volatile C0223ga<BatchGetDocumentsRequest, BatchGetDocumentsResponse> getBatchGetDocumentsMethod;
    public static volatile C0223ga<BeginTransactionRequest, BeginTransactionResponse> getBeginTransactionMethod;
    public static volatile C0223ga<CommitRequest, CommitResponse> getCommitMethod;
    public static volatile C0223ga<CreateDocumentRequest, Document> getCreateDocumentMethod;
    public static volatile C0223ga<DeleteDocumentRequest, Empty> getDeleteDocumentMethod;
    public static volatile C0223ga<GetDocumentRequest, Document> getGetDocumentMethod;
    public static volatile C0223ga<ListCollectionIdsRequest, ListCollectionIdsResponse> getListCollectionIdsMethod;
    public static volatile C0223ga<ListDocumentsRequest, ListDocumentsResponse> getListDocumentsMethod;
    public static volatile C0223ga<ListenRequest, ListenResponse> getListenMethod;
    public static volatile C0223ga<RollbackRequest, Empty> getRollbackMethod;
    public static volatile C0223ga<RunQueryRequest, RunQueryResponse> getRunQueryMethod;
    public static volatile C0223ga<UpdateDocumentRequest, Document> getUpdateDocumentMethod;
    public static volatile C0223ga<WriteRequest, WriteResponse> getWriteMethod;
    public static volatile va serviceDescriptor;

    public static final class FirestoreBlockingStub extends b<FirestoreBlockingStub> {
        public FirestoreBlockingStub(AbstractC0222g abstractC0222g, C0220f c0220f) {
            super(abstractC0222g, c0220f);
        }

        public Iterator<BatchGetDocumentsResponse> batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest) {
            return g.a(getChannel(), (C0223ga<BatchGetDocumentsRequest, RespT>) FirestoreGrpc.getBatchGetDocumentsMethod(), getCallOptions(), batchGetDocumentsRequest);
        }

        public BeginTransactionResponse beginTransaction(BeginTransactionRequest beginTransactionRequest) {
            return (BeginTransactionResponse) g.b(getChannel(), FirestoreGrpc.getBeginTransactionMethod(), getCallOptions(), beginTransactionRequest);
        }

        public CommitResponse commit(CommitRequest commitRequest) {
            return (CommitResponse) g.b(getChannel(), FirestoreGrpc.getCommitMethod(), getCallOptions(), commitRequest);
        }

        public Document createDocument(CreateDocumentRequest createDocumentRequest) {
            return (Document) g.b(getChannel(), FirestoreGrpc.getCreateDocumentMethod(), getCallOptions(), createDocumentRequest);
        }

        public Empty deleteDocument(DeleteDocumentRequest deleteDocumentRequest) {
            return (Empty) g.b(getChannel(), FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions(), deleteDocumentRequest);
        }

        public Document getDocument(GetDocumentRequest getDocumentRequest) {
            return (Document) g.b(getChannel(), FirestoreGrpc.getGetDocumentMethod(), getCallOptions(), getDocumentRequest);
        }

        public ListCollectionIdsResponse listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest) {
            return (ListCollectionIdsResponse) g.b(getChannel(), FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions(), listCollectionIdsRequest);
        }

        public ListDocumentsResponse listDocuments(ListDocumentsRequest listDocumentsRequest) {
            return (ListDocumentsResponse) g.b(getChannel(), FirestoreGrpc.getListDocumentsMethod(), getCallOptions(), listDocumentsRequest);
        }

        public Empty rollback(RollbackRequest rollbackRequest) {
            return (Empty) g.b(getChannel(), FirestoreGrpc.getRollbackMethod(), getCallOptions(), rollbackRequest);
        }

        public Iterator<RunQueryResponse> runQuery(RunQueryRequest runQueryRequest) {
            return g.a(getChannel(), (C0223ga<RunQueryRequest, RespT>) FirestoreGrpc.getRunQueryMethod(), getCallOptions(), runQueryRequest);
        }

        public Document updateDocument(UpdateDocumentRequest updateDocumentRequest) {
            return (Document) g.b(getChannel(), FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions(), updateDocumentRequest);
        }

        public /* synthetic */ FirestoreBlockingStub(AbstractC0222g abstractC0222g, C0220f c0220f, AnonymousClass1 anonymousClass1) {
            super(abstractC0222g, c0220f);
        }

        @Override // c.a.e.d
        public FirestoreBlockingStub build(AbstractC0222g abstractC0222g, C0220f c0220f) {
            return new FirestoreBlockingStub(abstractC0222g, c0220f);
        }
    }

    public static final class FirestoreFutureStub extends c<FirestoreFutureStub> {
        public FirestoreFutureStub(AbstractC0222g abstractC0222g, C0220f c0220f) {
            super(abstractC0222g, c0220f);
        }

        public ListenableFuture<BeginTransactionResponse> beginTransaction(BeginTransactionRequest beginTransactionRequest) {
            return g.a((AbstractC0226j<BeginTransactionRequest, RespT>) getChannel().a(FirestoreGrpc.getBeginTransactionMethod(), getCallOptions()), beginTransactionRequest);
        }

        public ListenableFuture<CommitResponse> commit(CommitRequest commitRequest) {
            return g.a((AbstractC0226j<CommitRequest, RespT>) getChannel().a(FirestoreGrpc.getCommitMethod(), getCallOptions()), commitRequest);
        }

        public ListenableFuture<Document> createDocument(CreateDocumentRequest createDocumentRequest) {
            return g.a((AbstractC0226j<CreateDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getCreateDocumentMethod(), getCallOptions()), createDocumentRequest);
        }

        public ListenableFuture<Empty> deleteDocument(DeleteDocumentRequest deleteDocumentRequest) {
            return g.a((AbstractC0226j<DeleteDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions()), deleteDocumentRequest);
        }

        public ListenableFuture<Document> getDocument(GetDocumentRequest getDocumentRequest) {
            return g.a((AbstractC0226j<GetDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getGetDocumentMethod(), getCallOptions()), getDocumentRequest);
        }

        public ListenableFuture<ListCollectionIdsResponse> listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest) {
            return g.a((AbstractC0226j<ListCollectionIdsRequest, RespT>) getChannel().a(FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions()), listCollectionIdsRequest);
        }

        public ListenableFuture<ListDocumentsResponse> listDocuments(ListDocumentsRequest listDocumentsRequest) {
            return g.a((AbstractC0226j<ListDocumentsRequest, RespT>) getChannel().a(FirestoreGrpc.getListDocumentsMethod(), getCallOptions()), listDocumentsRequest);
        }

        public ListenableFuture<Empty> rollback(RollbackRequest rollbackRequest) {
            return g.a((AbstractC0226j<RollbackRequest, RespT>) getChannel().a(FirestoreGrpc.getRollbackMethod(), getCallOptions()), rollbackRequest);
        }

        public ListenableFuture<Document> updateDocument(UpdateDocumentRequest updateDocumentRequest) {
            return g.a((AbstractC0226j<UpdateDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions()), updateDocumentRequest);
        }

        public /* synthetic */ FirestoreFutureStub(AbstractC0222g abstractC0222g, C0220f c0220f, AnonymousClass1 anonymousClass1) {
            super(abstractC0222g, c0220f);
        }

        @Override // c.a.e.d
        public FirestoreFutureStub build(AbstractC0222g abstractC0222g, C0220f c0220f) {
            return new FirestoreFutureStub(abstractC0222g, c0220f);
        }
    }

    public static abstract class FirestoreImplBase implements InterfaceC0216d {
        public void batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest, r<BatchGetDocumentsResponse> rVar) {
            a.a(FirestoreGrpc.getBatchGetDocumentsMethod(), rVar);
        }

        public void beginTransaction(BeginTransactionRequest beginTransactionRequest, r<BeginTransactionResponse> rVar) {
            a.a(FirestoreGrpc.getBeginTransactionMethod(), rVar);
        }

        public final ta bindService() {
            ta.a aVar = new ta.a(FirestoreGrpc.getServiceDescriptor(), null);
            aVar.a(FirestoreGrpc.getGetDocumentMethod(), a.a((o) new MethodHandlers(this, 0)));
            aVar.a(FirestoreGrpc.getListDocumentsMethod(), a.a((o) new MethodHandlers(this, 1)));
            aVar.a(FirestoreGrpc.getCreateDocumentMethod(), a.a((o) new MethodHandlers(this, 2)));
            aVar.a(FirestoreGrpc.getUpdateDocumentMethod(), a.a((o) new MethodHandlers(this, 3)));
            aVar.a(FirestoreGrpc.getDeleteDocumentMethod(), a.a((o) new MethodHandlers(this, 4)));
            aVar.a(FirestoreGrpc.getBatchGetDocumentsMethod(), a.a((l) new MethodHandlers(this, 5)));
            aVar.a(FirestoreGrpc.getBeginTransactionMethod(), a.a((o) new MethodHandlers(this, 6)));
            aVar.a(FirestoreGrpc.getCommitMethod(), a.a((o) new MethodHandlers(this, 7)));
            aVar.a(FirestoreGrpc.getRollbackMethod(), a.a((o) new MethodHandlers(this, 8)));
            aVar.a(FirestoreGrpc.getRunQueryMethod(), a.a((l) new MethodHandlers(this, 9)));
            aVar.a(FirestoreGrpc.getWriteMethod(), new n(new MethodHandlers(this, 11), true));
            aVar.a(FirestoreGrpc.getListenMethod(), new n(new MethodHandlers(this, 12), true));
            aVar.a(FirestoreGrpc.getListCollectionIdsMethod(), a.a((o) new MethodHandlers(this, 10)));
            va vaVar = aVar.f1909b;
            if (vaVar == null) {
                ArrayList arrayList = new ArrayList(aVar.f1910c.size());
                Iterator<ra<?, ?>> it = aVar.f1910c.values().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().f1899a);
                }
                va.a aVar2 = new va.a(aVar.f1908a, null);
                Preconditions.checkNotNull(arrayList, "methods");
                aVar2.f1919b.addAll(arrayList);
                vaVar = new va(aVar2);
            }
            HashMap map = new HashMap(aVar.f1910c);
            for (C0223ga<?, ?> c0223ga : vaVar.f1916b) {
                ra raVar = (ra) map.remove(c0223ga.f1833b);
                if (raVar == null) {
                    StringBuilder sbA = b.a.a.a.a.a("No method bound for descriptor entry ");
                    sbA.append(c0223ga.f1833b);
                    throw new IllegalStateException(sbA.toString());
                }
                if (raVar.f1899a != c0223ga) {
                    throw new IllegalStateException(b.a.a.a.a.a(b.a.a.a.a.a("Bound method for "), c0223ga.f1833b, " not same instance as method in service descriptor"));
                }
            }
            if (map.size() <= 0) {
                return new ta(vaVar, aVar.f1910c, null);
            }
            StringBuilder sbA2 = b.a.a.a.a.a("No entry in descriptor matching bound method ");
            sbA2.append(((ra) map.values().iterator().next()).f1899a.f1833b);
            throw new IllegalStateException(sbA2.toString());
        }

        public void commit(CommitRequest commitRequest, r<CommitResponse> rVar) {
            a.a(FirestoreGrpc.getCommitMethod(), rVar);
        }

        public void createDocument(CreateDocumentRequest createDocumentRequest, r<Document> rVar) {
            a.a(FirestoreGrpc.getCreateDocumentMethod(), rVar);
        }

        public void deleteDocument(DeleteDocumentRequest deleteDocumentRequest, r<Empty> rVar) {
            a.a(FirestoreGrpc.getDeleteDocumentMethod(), rVar);
        }

        public void getDocument(GetDocumentRequest getDocumentRequest, r<Document> rVar) {
            a.a(FirestoreGrpc.getGetDocumentMethod(), rVar);
        }

        public void listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest, r<ListCollectionIdsResponse> rVar) {
            a.a(FirestoreGrpc.getListCollectionIdsMethod(), rVar);
        }

        public void listDocuments(ListDocumentsRequest listDocumentsRequest, r<ListDocumentsResponse> rVar) {
            a.a(FirestoreGrpc.getListDocumentsMethod(), rVar);
        }

        public r<ListenRequest> listen(r<ListenResponse> rVar) {
            a.a(FirestoreGrpc.getListenMethod(), rVar);
            return new k();
        }

        public void rollback(RollbackRequest rollbackRequest, r<Empty> rVar) {
            a.a(FirestoreGrpc.getRollbackMethod(), rVar);
        }

        public void runQuery(RunQueryRequest runQueryRequest, r<RunQueryResponse> rVar) {
            a.a(FirestoreGrpc.getRunQueryMethod(), rVar);
        }

        public void updateDocument(UpdateDocumentRequest updateDocumentRequest, r<Document> rVar) {
            a.a(FirestoreGrpc.getUpdateDocumentMethod(), rVar);
        }

        public r<WriteRequest> write(r<WriteResponse> rVar) {
            a.a(FirestoreGrpc.getWriteMethod(), rVar);
            return new k();
        }
    }

    public static final class FirestoreStub extends c.a.e.a<FirestoreStub> {
        public FirestoreStub(AbstractC0222g abstractC0222g, C0220f c0220f) {
            super(abstractC0222g, c0220f);
        }

        public void batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest, r<BatchGetDocumentsResponse> rVar) {
            g.a((AbstractC0226j<BatchGetDocumentsRequest, RespT>) getChannel().a(FirestoreGrpc.getBatchGetDocumentsMethod(), getCallOptions()), batchGetDocumentsRequest, rVar);
        }

        public void beginTransaction(BeginTransactionRequest beginTransactionRequest, r<BeginTransactionResponse> rVar) {
            g.a((AbstractC0226j<BeginTransactionRequest, RespT>) getChannel().a(FirestoreGrpc.getBeginTransactionMethod(), getCallOptions()), beginTransactionRequest, (r) rVar, false);
        }

        public void commit(CommitRequest commitRequest, r<CommitResponse> rVar) {
            g.a((AbstractC0226j<CommitRequest, RespT>) getChannel().a(FirestoreGrpc.getCommitMethod(), getCallOptions()), commitRequest, (r) rVar, false);
        }

        public void createDocument(CreateDocumentRequest createDocumentRequest, r<Document> rVar) {
            g.a((AbstractC0226j<CreateDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getCreateDocumentMethod(), getCallOptions()), createDocumentRequest, (r) rVar, false);
        }

        public void deleteDocument(DeleteDocumentRequest deleteDocumentRequest, r<Empty> rVar) {
            g.a((AbstractC0226j<DeleteDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions()), deleteDocumentRequest, (r) rVar, false);
        }

        public void getDocument(GetDocumentRequest getDocumentRequest, r<Document> rVar) {
            g.a((AbstractC0226j<GetDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getGetDocumentMethod(), getCallOptions()), getDocumentRequest, (r) rVar, false);
        }

        public void listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest, r<ListCollectionIdsResponse> rVar) {
            g.a((AbstractC0226j<ListCollectionIdsRequest, RespT>) getChannel().a(FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions()), listCollectionIdsRequest, (r) rVar, false);
        }

        public void listDocuments(ListDocumentsRequest listDocumentsRequest, r<ListDocumentsResponse> rVar) {
            g.a((AbstractC0226j<ListDocumentsRequest, RespT>) getChannel().a(FirestoreGrpc.getListDocumentsMethod(), getCallOptions()), listDocumentsRequest, (r) rVar, false);
        }

        public r<ListenRequest> listen(r<ListenResponse> rVar) {
            return g.a(getChannel().a(FirestoreGrpc.getListenMethod(), getCallOptions()), (r) rVar);
        }

        public void rollback(RollbackRequest rollbackRequest, r<Empty> rVar) {
            g.a((AbstractC0226j<RollbackRequest, RespT>) getChannel().a(FirestoreGrpc.getRollbackMethod(), getCallOptions()), rollbackRequest, (r) rVar, false);
        }

        public void runQuery(RunQueryRequest runQueryRequest, r<RunQueryResponse> rVar) {
            g.a((AbstractC0226j<RunQueryRequest, RespT>) getChannel().a(FirestoreGrpc.getRunQueryMethod(), getCallOptions()), runQueryRequest, rVar);
        }

        public void updateDocument(UpdateDocumentRequest updateDocumentRequest, r<Document> rVar) {
            g.a((AbstractC0226j<UpdateDocumentRequest, RespT>) getChannel().a(FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions()), updateDocumentRequest, (r) rVar, false);
        }

        public r<WriteRequest> write(r<WriteResponse> rVar) {
            return g.a(getChannel().a(FirestoreGrpc.getWriteMethod(), getCallOptions()), (r) rVar);
        }

        public /* synthetic */ FirestoreStub(AbstractC0222g abstractC0222g, C0220f c0220f, AnonymousClass1 anonymousClass1) {
            super(abstractC0222g, c0220f);
        }

        @Override // c.a.e.d
        public FirestoreStub build(AbstractC0222g abstractC0222g, C0220f c0220f) {
            return new FirestoreStub(abstractC0222g, c0220f);
        }
    }

    public static C0223ga<BatchGetDocumentsRequest, BatchGetDocumentsResponse> getBatchGetDocumentsMethod() {
        C0223ga<BatchGetDocumentsRequest, BatchGetDocumentsResponse> c0223gaA = getBatchGetDocumentsMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getBatchGetDocumentsMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.SERVER_STREAMING;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "BatchGetDocuments");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(BatchGetDocumentsRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(BatchGetDocumentsResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getBatchGetDocumentsMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<BeginTransactionRequest, BeginTransactionResponse> getBeginTransactionMethod() {
        C0223ga<BeginTransactionRequest, BeginTransactionResponse> c0223gaA = getBeginTransactionMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getBeginTransactionMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "BeginTransaction");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(BeginTransactionRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(BeginTransactionResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getBeginTransactionMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<CommitRequest, CommitResponse> getCommitMethod() {
        C0223ga<CommitRequest, CommitResponse> c0223gaA = getCommitMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getCommitMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "Commit");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(CommitRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(CommitResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getCommitMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<CreateDocumentRequest, Document> getCreateDocumentMethod() {
        C0223ga<CreateDocumentRequest, Document> c0223gaA = getCreateDocumentMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getCreateDocumentMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "CreateDocument");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(CreateDocumentRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(Document.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getCreateDocumentMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<DeleteDocumentRequest, Empty> getDeleteDocumentMethod() {
        C0223ga<DeleteDocumentRequest, Empty> c0223gaA = getDeleteDocumentMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getDeleteDocumentMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "DeleteDocument");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(DeleteDocumentRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(Empty.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getDeleteDocumentMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<GetDocumentRequest, Document> getGetDocumentMethod() {
        C0223ga<GetDocumentRequest, Document> c0223gaA = getGetDocumentMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getGetDocumentMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "GetDocument");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(GetDocumentRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(Document.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getGetDocumentMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<ListCollectionIdsRequest, ListCollectionIdsResponse> getListCollectionIdsMethod() {
        C0223ga<ListCollectionIdsRequest, ListCollectionIdsResponse> c0223gaA = getListCollectionIdsMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getListCollectionIdsMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "ListCollectionIds");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(ListCollectionIdsRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(ListCollectionIdsResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getListCollectionIdsMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<ListDocumentsRequest, ListDocumentsResponse> getListDocumentsMethod() {
        C0223ga<ListDocumentsRequest, ListDocumentsResponse> c0223gaA = getListDocumentsMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getListDocumentsMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "ListDocuments");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(ListDocumentsRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(ListDocumentsResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getListDocumentsMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<ListenRequest, ListenResponse> getListenMethod() {
        C0223ga<ListenRequest, ListenResponse> c0223gaA = getListenMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getListenMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.BIDI_STREAMING;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "Listen");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(ListenRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(ListenResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getListenMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<RollbackRequest, Empty> getRollbackMethod() {
        C0223ga<RollbackRequest, Empty> c0223gaA = getRollbackMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getRollbackMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "Rollback");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(RollbackRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(Empty.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getRollbackMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<RunQueryRequest, RunQueryResponse> getRunQueryMethod() {
        C0223ga<RunQueryRequest, RunQueryResponse> c0223gaA = getRunQueryMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getRunQueryMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.SERVER_STREAMING;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "RunQuery");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(RunQueryRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(RunQueryResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getRunQueryMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static va getServiceDescriptor() {
        va vaVar = serviceDescriptor;
        if (vaVar == null) {
            synchronized (FirestoreGrpc.class) {
                vaVar = serviceDescriptor;
                if (vaVar == null) {
                    va.a aVar = new va.a(SERVICE_NAME, null);
                    C0223ga<GetDocumentRequest, Document> getDocumentMethod = getGetDocumentMethod();
                    List<C0223ga<?, ?>> list = aVar.f1919b;
                    Preconditions.checkNotNull(getDocumentMethod, FirebaseAnalytics.Param.METHOD);
                    list.add(getDocumentMethod);
                    C0223ga<ListDocumentsRequest, ListDocumentsResponse> listDocumentsMethod = getListDocumentsMethod();
                    List<C0223ga<?, ?>> list2 = aVar.f1919b;
                    Preconditions.checkNotNull(listDocumentsMethod, FirebaseAnalytics.Param.METHOD);
                    list2.add(listDocumentsMethod);
                    C0223ga<CreateDocumentRequest, Document> createDocumentMethod = getCreateDocumentMethod();
                    List<C0223ga<?, ?>> list3 = aVar.f1919b;
                    Preconditions.checkNotNull(createDocumentMethod, FirebaseAnalytics.Param.METHOD);
                    list3.add(createDocumentMethod);
                    C0223ga<UpdateDocumentRequest, Document> updateDocumentMethod = getUpdateDocumentMethod();
                    List<C0223ga<?, ?>> list4 = aVar.f1919b;
                    Preconditions.checkNotNull(updateDocumentMethod, FirebaseAnalytics.Param.METHOD);
                    list4.add(updateDocumentMethod);
                    C0223ga<DeleteDocumentRequest, Empty> deleteDocumentMethod = getDeleteDocumentMethod();
                    List<C0223ga<?, ?>> list5 = aVar.f1919b;
                    Preconditions.checkNotNull(deleteDocumentMethod, FirebaseAnalytics.Param.METHOD);
                    list5.add(deleteDocumentMethod);
                    C0223ga<BatchGetDocumentsRequest, BatchGetDocumentsResponse> batchGetDocumentsMethod = getBatchGetDocumentsMethod();
                    List<C0223ga<?, ?>> list6 = aVar.f1919b;
                    Preconditions.checkNotNull(batchGetDocumentsMethod, FirebaseAnalytics.Param.METHOD);
                    list6.add(batchGetDocumentsMethod);
                    C0223ga<BeginTransactionRequest, BeginTransactionResponse> beginTransactionMethod = getBeginTransactionMethod();
                    List<C0223ga<?, ?>> list7 = aVar.f1919b;
                    Preconditions.checkNotNull(beginTransactionMethod, FirebaseAnalytics.Param.METHOD);
                    list7.add(beginTransactionMethod);
                    C0223ga<CommitRequest, CommitResponse> commitMethod = getCommitMethod();
                    List<C0223ga<?, ?>> list8 = aVar.f1919b;
                    Preconditions.checkNotNull(commitMethod, FirebaseAnalytics.Param.METHOD);
                    list8.add(commitMethod);
                    C0223ga<RollbackRequest, Empty> rollbackMethod = getRollbackMethod();
                    List<C0223ga<?, ?>> list9 = aVar.f1919b;
                    Preconditions.checkNotNull(rollbackMethod, FirebaseAnalytics.Param.METHOD);
                    list9.add(rollbackMethod);
                    C0223ga<RunQueryRequest, RunQueryResponse> runQueryMethod = getRunQueryMethod();
                    List<C0223ga<?, ?>> list10 = aVar.f1919b;
                    Preconditions.checkNotNull(runQueryMethod, FirebaseAnalytics.Param.METHOD);
                    list10.add(runQueryMethod);
                    C0223ga<WriteRequest, WriteResponse> writeMethod = getWriteMethod();
                    List<C0223ga<?, ?>> list11 = aVar.f1919b;
                    Preconditions.checkNotNull(writeMethod, FirebaseAnalytics.Param.METHOD);
                    list11.add(writeMethod);
                    C0223ga<ListenRequest, ListenResponse> listenMethod = getListenMethod();
                    List<C0223ga<?, ?>> list12 = aVar.f1919b;
                    Preconditions.checkNotNull(listenMethod, FirebaseAnalytics.Param.METHOD);
                    list12.add(listenMethod);
                    C0223ga<ListCollectionIdsRequest, ListCollectionIdsResponse> listCollectionIdsMethod = getListCollectionIdsMethod();
                    List<C0223ga<?, ?>> list13 = aVar.f1919b;
                    Preconditions.checkNotNull(listCollectionIdsMethod, FirebaseAnalytics.Param.METHOD);
                    list13.add(listCollectionIdsMethod);
                    vaVar = new va(aVar);
                    serviceDescriptor = vaVar;
                }
            }
        }
        return vaVar;
    }

    public static C0223ga<UpdateDocumentRequest, Document> getUpdateDocumentMethod() {
        C0223ga<UpdateDocumentRequest, Document> c0223gaA = getUpdateDocumentMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getUpdateDocumentMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.UNARY;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "UpdateDocument");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(UpdateDocumentRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(Document.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getUpdateDocumentMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static C0223ga<WriteRequest, WriteResponse> getWriteMethod() {
        C0223ga<WriteRequest, WriteResponse> c0223gaA = getWriteMethod;
        if (c0223gaA == null) {
            synchronized (FirestoreGrpc.class) {
                c0223gaA = getWriteMethod;
                if (c0223gaA == null) {
                    C0223ga.a aVarA = C0223ga.a();
                    aVarA.f1840c = C0223ga.c.BIDI_STREAMING;
                    aVarA.f1841d = C0223ga.a(SERVICE_NAME, "Write");
                    aVarA.h = true;
                    aVarA.f1838a = c.a.d.a.b.a(WriteRequest.DEFAULT_INSTANCE);
                    aVarA.f1839b = c.a.d.a.b.a(WriteResponse.DEFAULT_INSTANCE);
                    c0223gaA = aVarA.a();
                    getWriteMethod = c0223gaA;
                }
            }
        }
        return c0223gaA;
    }

    public static FirestoreBlockingStub newBlockingStub(AbstractC0222g abstractC0222g) {
        return (FirestoreBlockingStub) b.newStub(new d.a<FirestoreBlockingStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.2
            @Override // c.a.e.d.a
            public FirestoreBlockingStub newStub(AbstractC0222g abstractC0222g2, C0220f c0220f) {
                return new FirestoreBlockingStub(abstractC0222g2, c0220f, null);
            }
        }, abstractC0222g);
    }

    public static FirestoreFutureStub newFutureStub(AbstractC0222g abstractC0222g) {
        return (FirestoreFutureStub) c.newStub(new d.a<FirestoreFutureStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.3
            @Override // c.a.e.d.a
            public FirestoreFutureStub newStub(AbstractC0222g abstractC0222g2, C0220f c0220f) {
                return new FirestoreFutureStub(abstractC0222g2, c0220f, null);
            }
        }, abstractC0222g);
    }

    public static FirestoreStub newStub(AbstractC0222g abstractC0222g) {
        return (FirestoreStub) c.a.e.a.newStub(new d.a<FirestoreStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.1
            @Override // c.a.e.d.a
            public FirestoreStub newStub(AbstractC0222g abstractC0222g2, C0220f c0220f) {
                return new FirestoreStub(abstractC0222g2, c0220f, null);
            }
        }, abstractC0222g);
    }

    private static final class MethodHandlers<Req, Resp> implements o<Req, Resp>, l<Req, Resp>, j<Req, Resp>, i<Req, Resp> {
        public final int methodId;
        public final FirestoreImplBase serviceImpl;

        public MethodHandlers(FirestoreImplBase firestoreImplBase, int i) {
            this.serviceImpl = firestoreImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void invoke(Req req, r<Resp> rVar) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.getDocument((GetDocumentRequest) req, rVar);
                    return;
                case 1:
                    this.serviceImpl.listDocuments((ListDocumentsRequest) req, rVar);
                    return;
                case 2:
                    this.serviceImpl.createDocument((CreateDocumentRequest) req, rVar);
                    return;
                case 3:
                    this.serviceImpl.updateDocument((UpdateDocumentRequest) req, rVar);
                    return;
                case 4:
                    this.serviceImpl.deleteDocument((DeleteDocumentRequest) req, rVar);
                    return;
                case 5:
                    this.serviceImpl.batchGetDocuments((BatchGetDocumentsRequest) req, rVar);
                    return;
                case 6:
                    this.serviceImpl.beginTransaction((BeginTransactionRequest) req, rVar);
                    return;
                case 7:
                    this.serviceImpl.commit((CommitRequest) req, rVar);
                    return;
                case 8:
                    this.serviceImpl.rollback((RollbackRequest) req, rVar);
                    return;
                case 9:
                    this.serviceImpl.runQuery((RunQueryRequest) req, rVar);
                    return;
                case 10:
                    this.serviceImpl.listCollectionIds((ListCollectionIdsRequest) req, rVar);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        public r<Req> invoke(r<Resp> rVar) {
            int i = this.methodId;
            if (i == 11) {
                return (r<Req>) this.serviceImpl.write(rVar);
            }
            if (i == 12) {
                return (r<Req>) this.serviceImpl.listen(rVar);
            }
            throw new AssertionError();
        }
    }
}
