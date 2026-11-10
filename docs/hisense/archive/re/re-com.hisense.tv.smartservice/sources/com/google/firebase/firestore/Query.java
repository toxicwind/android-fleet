package com.google.firebase.firestore;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class Query {
    public final FirebaseFirestore firestore;
    public final com.google.firebase.firestore.core.Query query;

    /* JADX INFO: renamed from: com.google.firebase.firestore.Query$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = new int[FieldFilter.Operator.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.IN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_IN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum Direction {
        ASCENDING,
        DESCENDING
    }

    public Query(com.google.firebase.firestore.core.Query query, FirebaseFirestore firebaseFirestore) {
        if (query == null) {
            throw new NullPointerException();
        }
        this.query = query;
        if (firebaseFirestore == null) {
            throw new NullPointerException();
        }
        this.firestore = firebaseFirestore;
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, @Nullable Activity activity, final EventListener<QuerySnapshot> eventListener) {
        validateHasExplicitOrderByForLimitToLast();
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: b.e.b.d.n
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                this.f575a.a(eventListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        });
        ListenerRegistrationImpl listenerRegistrationImpl = new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(this.query, listenOptions, asyncEventListener), asyncEventListener);
        ActivityScope.bind(activity, listenerRegistrationImpl);
        return listenerRegistrationImpl;
    }

    private Bound boundFromDocumentSnapshot(String str, DocumentSnapshot documentSnapshot, boolean z) {
        Preconditions.checkNotNull(documentSnapshot, "Provided snapshot must not be null.");
        if (!documentSnapshot.exists()) {
            throw new IllegalArgumentException(a.a("Can't use a DocumentSnapshot for a document that doesn't exist for ", str, "()."));
        }
        Document document = documentSnapshot.getDocument();
        ArrayList arrayList = new ArrayList();
        for (OrderBy orderBy : this.query.getOrderBy()) {
            if (orderBy.getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                arrayList.add(Values.refValue(this.firestore.getDatabaseId(), document.getKey()));
            } else {
                Value field = document.getField(orderBy.getField());
                if (ServerTimestamps.isServerTimestamp(field)) {
                    StringBuilder sbA = a.a("Invalid query. You are trying to start or end a query using a document for which the field '");
                    sbA.append(orderBy.getField());
                    sbA.append("' is an uncommitted server timestamp. (Since the value of this field is unknown, you cannot start/end a query with it.)");
                    throw new IllegalArgumentException(sbA.toString());
                }
                if (field == null) {
                    StringBuilder sbA2 = a.a("Invalid query. You are trying to start or end a query using a document for which the field '");
                    sbA2.append(orderBy.getField());
                    sbA2.append("' (used as the orderBy) does not exist.");
                    throw new IllegalArgumentException(sbA2.toString());
                }
                arrayList.add(field);
            }
        }
        return new Bound(arrayList, z);
    }

    private Bound boundFromFields(String str, Object[] objArr, boolean z) {
        List<OrderBy> explicitOrderBy = this.query.getExplicitOrderBy();
        if (objArr.length > explicitOrderBy.size()) {
            throw new IllegalArgumentException(a.a("Too many arguments provided to ", str, "(). The number of arguments must be less than or equal to the number of orderBy() clauses."));
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (!explicitOrderBy.get(i).getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                arrayList.add(this.firestore.getUserDataReader().parseQueryValue(obj));
            } else {
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("Invalid query. Expected a string for document ID in " + str + "(), but got " + obj + ".");
                }
                String str2 = (String) obj;
                if (!this.query.isCollectionGroupQuery() && str2.contains("/")) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection and ordering by FieldPath.documentId(), the value passed to " + str + "() must be a plain document ID, but '" + str2 + "' contains a slash.");
                }
                ResourcePath resourcePathAppend = this.query.getPath().append(ResourcePath.fromString(str2));
                if (!DocumentKey.isDocumentKey(resourcePathAppend)) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection group and ordering by FieldPath.documentId(), the value passed to " + str + "() must result in a valid document path, but '" + resourcePathAppend + "' is not because it contains an odd number of segments.");
                }
                arrayList.add(Values.refValue(this.firestore.getDatabaseId(), new DocumentKey(resourcePathAppend)));
            }
        }
        return new Bound(arrayList, z);
    }

    private List<FieldFilter.Operator> conflictingOps(FieldFilter.Operator operator) {
        int iOrdinal = operator.ordinal();
        if (iOrdinal == 3) {
            return Arrays.asList(FieldFilter.Operator.NOT_EQUAL, FieldFilter.Operator.NOT_IN);
        }
        switch (iOrdinal) {
            case 6:
                return Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS, FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.NOT_IN);
            case 7:
                return Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS, FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.IN, FieldFilter.Operator.NOT_IN);
            case 8:
                return Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.IN, FieldFilter.Operator.NOT_IN);
            case 9:
                return Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS, FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.IN, FieldFilter.Operator.NOT_IN, FieldFilter.Operator.NOT_EQUAL);
            default:
                return new ArrayList();
        }
    }

    private Task<QuerySnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, null, new EventListener() { // from class: b.e.b.d.o
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                Query.a(taskCompletionSource, taskCompletionSource2, source, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        }));
        return taskCompletionSource.getTask();
    }

    public static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.waitForSyncWhenOnline = false;
        return listenOptions;
    }

    private Value parseDocumentIdValue(Object obj) {
        if (!(obj instanceof String)) {
            if (obj instanceof DocumentReference) {
                return Values.refValue(getFirestore().getDatabaseId(), ((DocumentReference) obj).getKey());
            }
            StringBuilder sbA = a.a("Invalid query. When querying with FieldPath.documentId() you must provide a valid String or DocumentReference, but it was of type: ");
            sbA.append(Util.typeName(obj));
            throw new IllegalArgumentException(sbA.toString());
        }
        String str = (String) obj;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid document ID, but it was an empty string.");
        }
        if (!this.query.isCollectionGroupQuery() && str.contains("/")) {
            throw new IllegalArgumentException(a.a("Invalid query. When querying a collection by FieldPath.documentId() you must provide a plain document ID, but '", str, "' contains a '/' character."));
        }
        ResourcePath resourcePathAppend = this.query.getPath().append(ResourcePath.fromString(str));
        if (DocumentKey.isDocumentKey(resourcePathAppend)) {
            return Values.refValue(getFirestore().getDatabaseId(), new DocumentKey(resourcePathAppend));
        }
        throw new IllegalArgumentException("Invalid query. When querying a collection group by FieldPath.documentId(), the value provided must result in a valid document path, but '" + resourcePathAppend + "' is not because it has an odd number of segments (" + resourcePathAppend.length() + ").");
    }

    private FieldFilter parseFieldFilter(@NonNull FieldPath fieldPath, FieldFilter.Operator operator, Object obj) {
        Value queryValue;
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(operator, "Provided op must not be null.");
        if (!fieldPath.getInternalPath().isKeyField()) {
            if (operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN || operator == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                validateDisjunctiveFilterElements(obj, operator);
            }
            queryValue = this.firestore.getUserDataReader().parseQueryValue(obj, operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN);
        } else {
            if (operator == FieldFilter.Operator.ARRAY_CONTAINS || operator == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                StringBuilder sbA = a.a("Invalid query. You can't perform '");
                sbA.append(operator.toString());
                sbA.append("' queries on FieldPath.documentId().");
                throw new IllegalArgumentException(sbA.toString());
            }
            if (operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN) {
                validateDisjunctiveFilterElements(obj, operator);
                ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    builderNewBuilder.addValues(parseDocumentIdValue(it.next()));
                }
                queryValue = Value.newBuilder().setArrayValue(builderNewBuilder).build();
            } else {
                queryValue = parseDocumentIdValue(obj);
            }
        }
        FieldFilter fieldFilterCreate = FieldFilter.create(fieldPath.getInternalPath(), operator, queryValue);
        validateNewFilter(fieldFilterCreate);
        return fieldFilterCreate;
    }

    private void validateDisjunctiveFilterElements(Object obj, FieldFilter.Operator operator) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != 0) {
                if (list.size() <= 10) {
                    return;
                }
                StringBuilder sbA = a.a("Invalid Query. '");
                sbA.append(operator.toString());
                sbA.append("' filters support a maximum of 10 elements in the value array.");
                throw new IllegalArgumentException(sbA.toString());
            }
        }
        StringBuilder sbA2 = a.a("Invalid Query. A non-empty array is required for '");
        sbA2.append(operator.toString());
        sbA2.append("' filters.");
        throw new IllegalArgumentException(sbA2.toString());
    }

    private void validateHasExplicitOrderByForLimitToLast() {
        if (this.query.hasLimitToLast() && this.query.getExplicitOrderBy().isEmpty()) {
            throw new IllegalStateException("limitToLast() queries require specifying at least one orderBy() clause");
        }
    }

    private void validateNewFilter(com.google.firebase.firestore.core.Filter filter) {
        if (filter instanceof FieldFilter) {
            FieldFilter fieldFilter = (FieldFilter) filter;
            FieldFilter.Operator operator = fieldFilter.getOperator();
            if (fieldFilter.isInequality()) {
                com.google.firebase.firestore.model.FieldPath fieldPathInequalityField = this.query.inequalityField();
                com.google.firebase.firestore.model.FieldPath field = fieldFilter.getField();
                if (fieldPathInequalityField != null && !fieldPathInequalityField.equals(field)) {
                    throw new IllegalArgumentException(String.format("All where filters with an inequality (notEqualTo, notIn, lessThan, lessThanOrEqualTo, greaterThan, or greaterThanOrEqualTo) must be on the same field. But you have filters on '%s' and '%s'", fieldPathInequalityField.canonicalString(), field.canonicalString()));
                }
                com.google.firebase.firestore.model.FieldPath firstOrderByField = this.query.getFirstOrderByField();
                if (firstOrderByField != null) {
                    validateOrderByFieldMatchesInequality(firstOrderByField, field);
                }
            }
            FieldFilter.Operator operatorFindFilterOperator = this.query.findFilterOperator(conflictingOps(operator));
            if (operatorFindFilterOperator != null) {
                if (operatorFindFilterOperator == operator) {
                    StringBuilder sbA = a.a("Invalid Query. You cannot use more than one '");
                    sbA.append(operator.toString());
                    sbA.append("' filter.");
                    throw new IllegalArgumentException(sbA.toString());
                }
                StringBuilder sbA2 = a.a("Invalid Query. You cannot use '");
                sbA2.append(operator.toString());
                sbA2.append("' filters with '");
                sbA2.append(operatorFindFilterOperator.toString());
                sbA2.append("' filters.");
                throw new IllegalArgumentException(sbA2.toString());
            }
        }
    }

    private void validateOrderByField(com.google.firebase.firestore.model.FieldPath fieldPath) {
        com.google.firebase.firestore.model.FieldPath fieldPathInequalityField = this.query.inequalityField();
        if (this.query.getFirstOrderByField() != null || fieldPathInequalityField == null) {
            return;
        }
        validateOrderByFieldMatchesInequality(fieldPath, fieldPathInequalityField);
    }

    private void validateOrderByFieldMatchesInequality(com.google.firebase.firestore.model.FieldPath fieldPath, com.google.firebase.firestore.model.FieldPath fieldPath2) {
        if (fieldPath.equals(fieldPath2)) {
            return;
        }
        String strCanonicalString = fieldPath2.canonicalString();
        throw new IllegalArgumentException(String.format("Invalid query. You have an inequality where filter (whereLessThan(), whereGreaterThan(), etc.) on field '%s' and so you must also have '%s' as your first orderBy() field, but your first orderBy() is currently on field '%s' instead.", strCanonicalString, strCanonicalString, fieldPath.canonicalString()));
    }

    private Query where(Filter filter) {
        return new Query(this.query.filter(parseFieldFilter(filter.getField(), filter.getOperator(), filter.getValue())), this.firestore);
    }

    public /* synthetic */ QuerySnapshot a(Task task) {
        return new QuerySnapshot(new Query(this.query, this.firestore), (ViewSnapshot) task.getResult(), this.firestore);
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public Query endAt(@NonNull DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot("endAt", documentSnapshot, true)), this.firestore);
    }

    @NonNull
    public Query endBefore(@NonNull DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot("endBefore", documentSnapshot, false)), this.firestore);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Query)) {
            return false;
        }
        Query query = (Query) obj;
        return this.query.equals(query.query) && this.firestore.equals(query.firestore);
    }

    @NonNull
    public Task<QuerySnapshot> get() {
        return get(Source.DEFAULT);
    }

    @NonNull
    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public int hashCode() {
        return this.firestore.hashCode() + (this.query.hashCode() * 31);
    }

    @NonNull
    public Query limit(long j) {
        if (j > 0) {
            return new Query(this.query.limitToFirst(j), this.firestore);
        }
        throw new IllegalArgumentException("Invalid Query. Query limit (" + j + ") is invalid. Limit must be positive.");
    }

    @NonNull
    public Query limitToLast(long j) {
        if (j > 0) {
            return new Query(this.query.limitToLast(j), this.firestore);
        }
        throw new IllegalArgumentException("Invalid Query. Query limitToLast (" + j + ") is invalid. Limit must be positive.");
    }

    @NonNull
    public Query orderBy(@NonNull String str) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), Direction.ASCENDING);
    }

    @NonNull
    public Query startAfter(@NonNull DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot("startAfter", documentSnapshot, false)), this.firestore);
    }

    @NonNull
    public Query startAt(@NonNull DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot("startAt", documentSnapshot, true)), this.firestore);
    }

    @NonNull
    public Query whereArrayContains(@NonNull String str, @NonNull Object obj) {
        return where(Filter.arrayContains(str, obj));
    }

    @NonNull
    public Query whereArrayContainsAny(@NonNull String str, @NonNull List<? extends Object> list) {
        return where(Filter.arrayContainsAny(str, list));
    }

    @NonNull
    public Query whereEqualTo(@NonNull String str, @Nullable Object obj) {
        return where(Filter.equalTo(str, obj));
    }

    @NonNull
    public Query whereGreaterThan(@NonNull String str, @NonNull Object obj) {
        return where(Filter.greaterThan(str, obj));
    }

    @NonNull
    public Query whereGreaterThanOrEqualTo(@NonNull String str, @NonNull Object obj) {
        return where(Filter.greaterThanOrEqualTo(str, obj));
    }

    @NonNull
    public Query whereIn(@NonNull String str, @NonNull List<? extends Object> list) {
        return where(Filter.inArray(str, list));
    }

    @NonNull
    public Query whereLessThan(@NonNull String str, @NonNull Object obj) {
        return where(Filter.lessThan(str, obj));
    }

    @NonNull
    public Query whereLessThanOrEqualTo(@NonNull String str, @NonNull Object obj) {
        return where(Filter.lessThanOrEqualTo(str, obj));
    }

    @NonNull
    public Query whereNotEqualTo(@NonNull String str, @Nullable Object obj) {
        return where(Filter.notEqualTo(str, obj));
    }

    @NonNull
    public Query whereNotIn(@NonNull String str, @NonNull List<? extends Object> list) {
        return where(Filter.notInArray(str, list));
    }

    public static /* synthetic */ void a(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (querySnapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                taskCompletionSource.setResult(querySnapshot);
            }
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            Assert.fail(e2, "Failed to register a listener for a query result", new Object[0]);
            throw null;
        } catch (ExecutionException e3) {
            Assert.fail(e3, "Failed to register a listener for a query result", new Object[0]);
            throw null;
        }
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Executor executor, @NonNull EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public Task<QuerySnapshot> get(@NonNull Source source) {
        validateHasExplicitOrderByForLimitToLast();
        return source == Source.CACHE ? this.firestore.getClient().getDocumentsFromLocalCache(this.query).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.p
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f580a.a(task);
            }
        }) : getViaSnapshotListener(source);
    }

    @NonNull
    public Query orderBy(@NonNull FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), Direction.ASCENDING);
    }

    @NonNull
    public Query whereArrayContains(@NonNull FieldPath fieldPath, @NonNull Object obj) {
        return where(Filter.arrayContains(fieldPath, obj));
    }

    @NonNull
    public Query whereArrayContainsAny(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> list) {
        return where(Filter.arrayContainsAny(fieldPath, list));
    }

    @NonNull
    public Query whereEqualTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return where(Filter.equalTo(fieldPath, obj));
    }

    @NonNull
    public Query whereGreaterThan(@NonNull FieldPath fieldPath, @NonNull Object obj) {
        return where(Filter.greaterThan(fieldPath, obj));
    }

    @NonNull
    public Query whereGreaterThanOrEqualTo(@NonNull FieldPath fieldPath, @NonNull Object obj) {
        return where(Filter.greaterThanOrEqualTo(fieldPath, obj));
    }

    @NonNull
    public Query whereIn(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> list) {
        return where(Filter.inArray(fieldPath, list));
    }

    @NonNull
    public Query whereLessThan(@NonNull FieldPath fieldPath, @NonNull Object obj) {
        return where(Filter.lessThan(fieldPath, obj));
    }

    @NonNull
    public Query whereLessThanOrEqualTo(@NonNull FieldPath fieldPath, @NonNull Object obj) {
        return where(Filter.lessThanOrEqualTo(fieldPath, obj));
    }

    @NonNull
    public Query whereNotEqualTo(@NonNull FieldPath fieldPath, @Nullable Object obj) {
        return where(Filter.notEqualTo(fieldPath, obj));
    }

    @NonNull
    public Query whereNotIn(@NonNull FieldPath fieldPath, @NonNull List<? extends Object> list) {
        return where(Filter.notInArray(fieldPath, list));
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Activity activity, @NonNull EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public Query endAt(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields("endAt", objArr, true)), this.firestore);
    }

    @NonNull
    public Query endBefore(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields("endBefore", objArr, false)), this.firestore);
    }

    @NonNull
    public Query startAfter(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields("startAfter", objArr, false)), this.firestore);
    }

    @NonNull
    public Query startAt(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields("startAt", objArr, true)), this.firestore);
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull MetadataChanges metadataChanges, @NonNull EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    @NonNull
    public Query orderBy(@NonNull String str, @NonNull Direction direction) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), direction);
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Executor executor, @NonNull MetadataChanges metadataChanges, @NonNull EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, eventListener);
    }

    @NonNull
    public Query orderBy(@NonNull FieldPath fieldPath, @NonNull Direction direction) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), direction);
    }

    private Query orderBy(@NonNull com.google.firebase.firestore.model.FieldPath fieldPath, @NonNull Direction direction) {
        OrderBy.Direction direction2;
        Preconditions.checkNotNull(direction, "Provided direction must not be null.");
        if (this.query.getStartAt() == null) {
            if (this.query.getEndAt() == null) {
                validateOrderByField(fieldPath);
                if (direction == Direction.ASCENDING) {
                    direction2 = OrderBy.Direction.ASCENDING;
                } else {
                    direction2 = OrderBy.Direction.DESCENDING;
                }
                return new Query(this.query.orderBy(new OrderBy(direction2, fieldPath)), this.firestore);
            }
            throw new IllegalArgumentException("Invalid query. You must not call Query.endAt() or Query.endBefore() before calling Query.orderBy().");
        }
        throw new IllegalArgumentException("Invalid query. You must not call Query.startAt() or Query.startAfter() before calling Query.orderBy().");
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Activity activity, @NonNull MetadataChanges metadataChanges, @NonNull EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    public /* synthetic */ void a(EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
        } else {
            Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
            eventListener.onEvent(new QuerySnapshot(this, viewSnapshot, this.firestore), null);
        }
    }
}
