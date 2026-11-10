package com.google.firebase.firestore.remote;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import c.a.za;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.Cursor;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentChange;
import com.google.firestore.v1.DocumentDelete;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.DocumentRemove;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.Precondition;
import com.google.firestore.v1.StructuredQuery;
import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteResult;
import com.google.protobuf.Int32Value;
import com.google.rpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteSerializer {
    public final DatabaseId databaseId;
    public final String databaseName;

    /* JADX INFO: renamed from: com.google.firebase.firestore.remote.RemoteSerializer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$local$QueryPurpose;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase = new int[ListenResponse.ResponseTypeCase.values().length];
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType;
        public static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Write$OperationCase;

        static {
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.TARGET_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_CHANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_REMOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.FILTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.RESPONSETYPE_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType = new int[com.google.firestore.v1.TargetChange.TargetChangeType.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.NO_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.REMOVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.CURRENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[com.google.firestore.v1.TargetChange.TargetChangeType.UNRECOGNIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction = new int[StructuredQuery.Direction.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[StructuredQuery.Direction.ASCENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[StructuredQuery.Direction.DESCENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator = new int[StructuredQuery.FieldFilter.Operator.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.GREATER_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused24) {
            }
            $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = new int[FieldFilter.Operator.values().length];
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused34) {
            }
            $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator = new int[StructuredQuery.UnaryFilter.Operator.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused38) {
            }
            $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase = new int[StructuredQuery.Filter.FilterTypeCase.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[StructuredQuery.Filter.FilterTypeCase.FIELD_FILTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[StructuredQuery.Filter.FilterTypeCase.UNARY_FILTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused41) {
            }
            $SwitchMap$com$google$firebase$firestore$local$QueryPurpose = new int[QueryPurpose.values().length];
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.LISTEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.EXISTENCE_FILTER_MISMATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.LIMBO_RESOLUTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused44) {
            }
            $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase = new int[DocumentTransform.FieldTransform.TransformTypeCase.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.SET_TO_SERVER_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.APPEND_MISSING_ELEMENTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.REMOVE_ALL_FROM_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.INCREMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused48) {
            }
            $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase = new int[Precondition.ConditionTypeCase.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[Precondition.ConditionTypeCase.UPDATE_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[Precondition.ConditionTypeCase.EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[Precondition.ConditionTypeCase.CONDITIONTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused51) {
            }
            $SwitchMap$com$google$firestore$v1$Write$OperationCase = new int[Write.OperationCase.values().length];
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.VERIFY.ordinal()] = 3;
            } catch (NoSuchFieldError unused54) {
            }
        }
    }

    public RemoteSerializer(DatabaseId databaseId) {
        this.databaseId = databaseId;
        this.databaseName = encodedDatabaseId(databaseId).canonicalString();
    }

    private FieldMask decodeDocumentMask(DocumentMask documentMask) {
        int fieldPathsCount = documentMask.getFieldPathsCount();
        HashSet hashSet = new HashSet(fieldPathsCount);
        for (int i = 0; i < fieldPathsCount; i++) {
            hashSet.add(FieldPath.fromServerFormat(documentMask.getFieldPaths(i)));
        }
        return new FieldMask(hashSet);
    }

    private FieldFilter.Operator decodeFieldFilterOperator(StructuredQuery.FieldFilter.Operator operator) {
        switch (operator.ordinal()) {
            case 1:
                return FieldFilter.Operator.LESS_THAN;
            case 2:
                return FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return FieldFilter.Operator.GREATER_THAN;
            case 4:
                return FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 5:
                return FieldFilter.Operator.EQUAL;
            case 6:
                return FieldFilter.Operator.NOT_EQUAL;
            case 7:
                return FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return FieldFilter.Operator.IN;
            case 9:
                return FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return FieldFilter.Operator.NOT_IN;
            default:
                Assert.fail("Unhandled FieldFilter.operator %d", operator);
                throw null;
        }
    }

    private FieldTransform decodeFieldTransform(DocumentTransform.FieldTransform fieldTransform) {
        int iOrdinal = fieldTransform.getTransformTypeCase().ordinal();
        if (iOrdinal == 0) {
            Assert.hardAssert(fieldTransform.getSetToServerValue() == DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME, "Unknown transform setToServerValue: %s", fieldTransform.getSetToServerValue());
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), ServerTimestampOperation.SHARED_INSTANCE);
        }
        if (iOrdinal == 1) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new NumericIncrementTransformOperation(fieldTransform.getIncrement()));
        }
        if (iOrdinal == 4) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Union(fieldTransform.getAppendMissingElements().getValuesList()));
        }
        if (iOrdinal == 5) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Remove(fieldTransform.getRemoveAllFromArray().getValuesList()));
        }
        Assert.fail("Unknown FieldTransform proto: %s", fieldTransform);
        throw null;
    }

    private List<Filter> decodeFilters(StructuredQuery.Filter filter) {
        List<StructuredQuery.Filter> listSingletonList;
        if (filter.getFilterTypeCase() == StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER) {
            Assert.hardAssert(filter.getCompositeFilter().getOp() == StructuredQuery.CompositeFilter.Operator.AND, "Only AND-type composite filters are supported, got %d", filter.getCompositeFilter().getOp());
            listSingletonList = filter.getCompositeFilter().getFiltersList();
        } else {
            listSingletonList = Collections.singletonList(filter);
        }
        ArrayList arrayList = new ArrayList(listSingletonList.size());
        for (StructuredQuery.Filter filter2 : listSingletonList) {
            int iOrdinal = filter2.getFilterTypeCase().ordinal();
            if (iOrdinal == 0) {
                Assert.fail("Nested composite filters are not supported.", new Object[0]);
                throw null;
            }
            if (iOrdinal == 1) {
                arrayList.add(decodeFieldFilter(filter2.getFieldFilter()));
            } else {
                if (iOrdinal != 2) {
                    Assert.fail("Unrecognized Filter.filterType %d", filter2.getFilterTypeCase());
                    throw null;
                }
                arrayList.add(decodeUnaryFilter(filter2.getUnaryFilter()));
            }
        }
        return arrayList;
    }

    private MutableDocument decodeFoundDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND), "Tried to deserialize a found document from a missing document.", new Object[0]);
        DocumentKey documentKeyDecodeKey = decodeKey(batchGetDocumentsResponse.getFound().getName());
        ObjectValue objectValueFromMap = ObjectValue.fromMap(batchGetDocumentsResponse.getFound().getFieldsMap());
        SnapshotVersion snapshotVersionDecodeVersion = decodeVersion(batchGetDocumentsResponse.getFound().getUpdateTime());
        Assert.hardAssert(!snapshotVersionDecodeVersion.equals(SnapshotVersion.NONE), "Got a document response with no snapshot version", new Object[0]);
        return MutableDocument.newFoundDocument(documentKeyDecodeKey, snapshotVersionDecodeVersion, objectValueFromMap);
    }

    private MutableDocument decodeMissingDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING), "Tried to deserialize a missing document from a found document.", new Object[0]);
        DocumentKey documentKeyDecodeKey = decodeKey(batchGetDocumentsResponse.getMissing());
        SnapshotVersion snapshotVersionDecodeVersion = decodeVersion(batchGetDocumentsResponse.getReadTime());
        Assert.hardAssert(!snapshotVersionDecodeVersion.equals(SnapshotVersion.NONE), "Got a no document response with no snapshot version", new Object[0]);
        return MutableDocument.newNoDocument(documentKeyDecodeKey, snapshotVersionDecodeVersion);
    }

    private OrderBy decodeOrderBy(StructuredQuery.Order order) {
        OrderBy.Direction direction;
        FieldPath fieldPathFromServerFormat = FieldPath.fromServerFormat(order.getField().getFieldPath());
        int iOrdinal = order.getDirection().ordinal();
        if (iOrdinal == 1) {
            direction = OrderBy.Direction.ASCENDING;
        } else {
            if (iOrdinal != 2) {
                Assert.fail("Unrecognized direction %d", order.getDirection());
                throw null;
            }
            direction = OrderBy.Direction.DESCENDING;
        }
        return new OrderBy(direction, fieldPathFromServerFormat);
    }

    private com.google.firebase.firestore.model.mutation.Precondition decodePrecondition(Precondition precondition) {
        int iOrdinal = precondition.getConditionTypeCase().ordinal();
        if (iOrdinal == 0) {
            return com.google.firebase.firestore.model.mutation.Precondition.exists(precondition.getExists());
        }
        if (iOrdinal == 1) {
            return com.google.firebase.firestore.model.mutation.Precondition.updateTime(decodeVersion(precondition.getUpdateTime()));
        }
        if (iOrdinal == 2) {
            return com.google.firebase.firestore.model.mutation.Precondition.NONE;
        }
        Assert.fail("Unknown precondition", new Object[0]);
        throw null;
    }

    private ResourcePath decodeQueryPath(String str) {
        ResourcePath resourcePathDecodeResourceName = decodeResourceName(str);
        return resourcePathDecodeResourceName.length() == 4 ? ResourcePath.EMPTY : extractLocalPathFromResourceName(resourcePathDecodeResourceName);
    }

    private ResourcePath decodeResourceName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        Assert.hardAssert(isValidResourceName(resourcePathFromString), "Tried to deserialize invalid key %s", resourcePathFromString);
        return resourcePathFromString;
    }

    private Filter decodeUnaryFilter(StructuredQuery.UnaryFilter unaryFilter) {
        FieldPath fieldPathFromServerFormat = FieldPath.fromServerFormat(unaryFilter.getField().getFieldPath());
        int iOrdinal = unaryFilter.getOp().ordinal();
        if (iOrdinal == 1) {
            return FieldFilter.create(fieldPathFromServerFormat, FieldFilter.Operator.EQUAL, Values.NAN_VALUE);
        }
        if (iOrdinal == 2) {
            return FieldFilter.create(fieldPathFromServerFormat, FieldFilter.Operator.EQUAL, Values.NULL_VALUE);
        }
        if (iOrdinal == 3) {
            return FieldFilter.create(fieldPathFromServerFormat, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE);
        }
        if (iOrdinal == 4) {
            return FieldFilter.create(fieldPathFromServerFormat, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE);
        }
        Assert.fail("Unrecognized UnaryFilter.operator %d", unaryFilter.getOp());
        throw null;
    }

    private DocumentMask encodeDocumentMask(FieldMask fieldMask) {
        DocumentMask.Builder builderNewBuilder = DocumentMask.newBuilder();
        Iterator<FieldPath> it = fieldMask.getMask().iterator();
        while (it.hasNext()) {
            builderNewBuilder.addFieldPaths(it.next().canonicalString());
        }
        return builderNewBuilder.build();
    }

    private StructuredQuery.FieldFilter.Operator encodeFieldFilterOperator(FieldFilter.Operator operator) {
        switch (operator) {
            case LESS_THAN:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN;
            case LESS_THAN_OR_EQUAL:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case EQUAL:
                return StructuredQuery.FieldFilter.Operator.EQUAL;
            case NOT_EQUAL:
                return StructuredQuery.FieldFilter.Operator.NOT_EQUAL;
            case GREATER_THAN:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN;
            case GREATER_THAN_OR_EQUAL:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case ARRAY_CONTAINS:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS;
            case ARRAY_CONTAINS_ANY:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case IN:
                return StructuredQuery.FieldFilter.Operator.IN;
            case NOT_IN:
                return StructuredQuery.FieldFilter.Operator.NOT_IN;
            default:
                Assert.fail("Unknown operator %d", operator);
                throw null;
        }
    }

    private StructuredQuery.FieldReference encodeFieldPath(FieldPath fieldPath) {
        return StructuredQuery.FieldReference.newBuilder().setFieldPath(fieldPath.canonicalString()).build();
    }

    private DocumentTransform.FieldTransform encodeFieldTransform(FieldTransform fieldTransform) {
        TransformOperation operation = fieldTransform.getOperation();
        if (operation instanceof ServerTimestampOperation) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setSetToServerValue(DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME).build();
        }
        if (operation instanceof ArrayTransformOperation.Union) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setAppendMissingElements(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Union) operation).getElements())).build();
        }
        if (operation instanceof ArrayTransformOperation.Remove) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setRemoveAllFromArray(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Remove) operation).getElements())).build();
        }
        if (operation instanceof NumericIncrementTransformOperation) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setIncrement(((NumericIncrementTransformOperation) operation).getOperand()).build();
        }
        Assert.fail("Unknown transform: %s", operation);
        throw null;
    }

    private StructuredQuery.Filter encodeFilters(List<Filter> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Filter filter : list) {
            if (filter instanceof FieldFilter) {
                arrayList.add(encodeUnaryOrFieldFilter((FieldFilter) filter));
            }
        }
        if (list.size() == 1) {
            return (StructuredQuery.Filter) arrayList.get(0);
        }
        StructuredQuery.CompositeFilter.Builder builderNewBuilder = StructuredQuery.CompositeFilter.newBuilder();
        builderNewBuilder.setOp(StructuredQuery.CompositeFilter.Operator.AND);
        builderNewBuilder.addAllFilters(arrayList);
        return StructuredQuery.Filter.newBuilder().setCompositeFilter(builderNewBuilder).build();
    }

    @Nullable
    private String encodeLabel(QueryPurpose queryPurpose) {
        int iOrdinal = queryPurpose.ordinal();
        if (iOrdinal == 0) {
            return null;
        }
        if (iOrdinal == 1) {
            return "existence-filter-mismatch";
        }
        if (iOrdinal == 2) {
            return "limbo-document";
        }
        Assert.fail("Unrecognized query purpose: %s", queryPurpose);
        throw null;
    }

    private StructuredQuery.Order encodeOrderBy(OrderBy orderBy) {
        StructuredQuery.Order.Builder builderNewBuilder = StructuredQuery.Order.newBuilder();
        if (orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) {
            builderNewBuilder.setDirection(StructuredQuery.Direction.ASCENDING);
        } else {
            builderNewBuilder.setDirection(StructuredQuery.Direction.DESCENDING);
        }
        builderNewBuilder.setField(encodeFieldPath(orderBy.getField()));
        return builderNewBuilder.build();
    }

    private Precondition encodePrecondition(com.google.firebase.firestore.model.mutation.Precondition precondition) {
        Assert.hardAssert(!precondition.isNone(), "Can't serialize an empty precondition", new Object[0]);
        Precondition.Builder builderNewBuilder = Precondition.newBuilder();
        if (precondition.getUpdateTime() != null) {
            return builderNewBuilder.setUpdateTime(encodeVersion(precondition.getUpdateTime())).build();
        }
        if (precondition.getExists() != null) {
            return builderNewBuilder.setExists(precondition.getExists().booleanValue()).build();
        }
        Assert.fail("Unknown Precondition", new Object[0]);
        throw null;
    }

    private String encodeQueryPath(ResourcePath resourcePath) {
        return encodeResourceName(this.databaseId, resourcePath);
    }

    private String encodeResourceName(DatabaseId databaseId, ResourcePath resourcePath) {
        return encodedDatabaseId(databaseId).append("documents").append(resourcePath).canonicalString();
    }

    public static ResourcePath encodedDatabaseId(DatabaseId databaseId) {
        return ResourcePath.fromSegments(Arrays.asList("projects", databaseId.getProjectId(), "databases", databaseId.getDatabaseId()));
    }

    public static ResourcePath extractLocalPathFromResourceName(ResourcePath resourcePath) {
        Assert.hardAssert(resourcePath.length() > 4 && resourcePath.getSegment(4).equals("documents"), "Tried to deserialize invalid key %s", resourcePath);
        return resourcePath.popFirst(5);
    }

    private za fromStatus(Status status) {
        return za.a(status.getCode()).b(status.getMessage());
    }

    public static boolean isValidResourceName(ResourcePath resourcePath) {
        return resourcePath.length() >= 4 && resourcePath.getSegment(0).equals("projects") && resourcePath.getSegment(2).equals("databases");
    }

    public String databaseName() {
        return this.databaseName;
    }

    public Target decodeDocumentsTarget(com.google.firestore.v1.Target.DocumentsTarget documentsTarget) {
        int documentsCount = documentsTarget.getDocumentsCount();
        Assert.hardAssert(documentsCount == 1, "DocumentsTarget contained other than 1 document %d", Integer.valueOf(documentsCount));
        return Query.atPath(decodeQueryPath(documentsTarget.getDocuments(0))).toTarget();
    }

    @VisibleForTesting
    public FieldFilter decodeFieldFilter(StructuredQuery.FieldFilter fieldFilter) {
        return FieldFilter.create(FieldPath.fromServerFormat(fieldFilter.getField().getFieldPath()), decodeFieldFilterOperator(fieldFilter.getOp()), fieldFilter.getValue());
    }

    public DocumentKey decodeKey(String str) {
        ResourcePath resourcePathDecodeResourceName = decodeResourceName(str);
        Assert.hardAssert(resourcePathDecodeResourceName.getSegment(1).equals(this.databaseId.getProjectId()), "Tried to deserialize key from different project.", new Object[0]);
        Assert.hardAssert(resourcePathDecodeResourceName.getSegment(3).equals(this.databaseId.getDatabaseId()), "Tried to deserialize key from different database.", new Object[0]);
        return new DocumentKey(extractLocalPathFromResourceName(resourcePathDecodeResourceName));
    }

    public MutableDocument decodeMaybeDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND)) {
            return decodeFoundDocument(batchGetDocumentsResponse);
        }
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING)) {
            return decodeMissingDocument(batchGetDocumentsResponse);
        }
        StringBuilder sbA = a.a("Unknown result case: ");
        sbA.append(batchGetDocumentsResponse.getResultCase());
        throw new IllegalArgumentException(sbA.toString());
    }

    public Mutation decodeMutation(Write write) {
        com.google.firebase.firestore.model.mutation.Precondition preconditionDecodePrecondition = write.hasCurrentDocument() ? decodePrecondition(write.getCurrentDocument()) : com.google.firebase.firestore.model.mutation.Precondition.NONE;
        ArrayList arrayList = new ArrayList();
        Iterator<DocumentTransform.FieldTransform> it = write.getUpdateTransformsList().iterator();
        while (it.hasNext()) {
            arrayList.add(decodeFieldTransform(it.next()));
        }
        int iOrdinal = write.getOperationCase().ordinal();
        if (iOrdinal == 0) {
            return write.hasUpdateMask() ? new PatchMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), decodeDocumentMask(write.getUpdateMask()), preconditionDecodePrecondition, arrayList) : new SetMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), preconditionDecodePrecondition, arrayList);
        }
        if (iOrdinal == 1) {
            return new DeleteMutation(decodeKey(write.getDelete()), preconditionDecodePrecondition);
        }
        if (iOrdinal == 2) {
            return new VerifyMutation(decodeKey(write.getVerify()), preconditionDecodePrecondition);
        }
        Assert.fail("Unknown mutation operation: %d", write.getOperationCase());
        throw null;
    }

    public MutationResult decodeMutationResult(WriteResult writeResult, SnapshotVersion snapshotVersion) {
        SnapshotVersion snapshotVersionDecodeVersion = decodeVersion(writeResult.getUpdateTime());
        if (!SnapshotVersion.NONE.equals(snapshotVersionDecodeVersion)) {
            snapshotVersion = snapshotVersionDecodeVersion;
        }
        int transformResultsCount = writeResult.getTransformResultsCount();
        ArrayList arrayList = new ArrayList(transformResultsCount);
        for (int i = 0; i < transformResultsCount; i++) {
            arrayList.add(writeResult.getTransformResults(i));
        }
        return new MutationResult(snapshotVersion, arrayList);
    }

    public Target decodeQueryTarget(String str, StructuredQuery structuredQuery) {
        ResourcePath resourcePath;
        String collectionId;
        List listEmptyList;
        ResourcePath resourcePathDecodeQueryPath = decodeQueryPath(str);
        int fromCount = structuredQuery.getFromCount();
        if (fromCount <= 0) {
            resourcePath = resourcePathDecodeQueryPath;
            collectionId = null;
        } else {
            Assert.hardAssert(fromCount == 1, "StructuredQuery.from with more than one collection is not supported.", new Object[0]);
            StructuredQuery.CollectionSelector from = structuredQuery.getFrom(0);
            if (from.getAllDescendants()) {
                resourcePath = resourcePathDecodeQueryPath;
                collectionId = from.getCollectionId();
            } else {
                resourcePathDecodeQueryPath = resourcePathDecodeQueryPath.append(from.getCollectionId());
                resourcePath = resourcePathDecodeQueryPath;
                collectionId = null;
            }
        }
        List<Filter> listDecodeFilters = structuredQuery.hasWhere() ? decodeFilters(structuredQuery.getWhere()) : Collections.emptyList();
        int orderByCount = structuredQuery.getOrderByCount();
        if (orderByCount > 0) {
            ArrayList arrayList = new ArrayList(orderByCount);
            for (int i = 0; i < orderByCount; i++) {
                arrayList.add(decodeOrderBy(structuredQuery.getOrderBy(i)));
            }
            listEmptyList = arrayList;
        } else {
            listEmptyList = Collections.emptyList();
        }
        return new Target(resourcePath, collectionId, listDecodeFilters, listEmptyList, structuredQuery.hasLimit() ? structuredQuery.getLimit().getValue() : -1L, structuredQuery.hasStartAt() ? new Bound(structuredQuery.getStartAt().getValuesList(), structuredQuery.getStartAt().getBefore()) : null, structuredQuery.hasEndAt() ? new Bound(structuredQuery.getEndAt().getValuesList(), !structuredQuery.getEndAt().getBefore()) : null);
    }

    public Timestamp decodeTimestamp(com.google.protobuf.Timestamp timestamp) {
        return new Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    public SnapshotVersion decodeVersion(com.google.protobuf.Timestamp timestamp) {
        return (timestamp.getSeconds() == 0 && timestamp.getNanos() == 0) ? SnapshotVersion.NONE : new SnapshotVersion(decodeTimestamp(timestamp));
    }

    public SnapshotVersion decodeVersionFromListenResponse(ListenResponse listenResponse) {
        if (listenResponse.getResponseTypeCase() != ListenResponse.ResponseTypeCase.TARGET_CHANGE) {
            return SnapshotVersion.NONE;
        }
        return listenResponse.getTargetChange().getTargetIdsCount() != 0 ? SnapshotVersion.NONE : decodeVersion(listenResponse.getTargetChange().getReadTime());
    }

    public WatchChange decodeWatchChange(ListenResponse listenResponse) {
        WatchChange.WatchTargetChangeType watchTargetChangeType;
        WatchChange documentChange;
        int iOrdinal = listenResponse.getResponseTypeCase().ordinal();
        za zaVarFromStatus = null;
        if (iOrdinal == 0) {
            com.google.firestore.v1.TargetChange targetChange = listenResponse.getTargetChange();
            int iOrdinal2 = targetChange.getTargetChangeType().ordinal();
            if (iOrdinal2 == 0) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.NoChange;
            } else if (iOrdinal2 == 1) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Added;
            } else if (iOrdinal2 == 2) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Removed;
                zaVarFromStatus = fromStatus(targetChange.getCause());
            } else if (iOrdinal2 == 3) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Current;
            } else {
                if (iOrdinal2 != 4) {
                    throw new IllegalArgumentException("Unknown target change type");
                }
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Reset;
            }
            return new WatchChange.WatchTargetChange(watchTargetChangeType, targetChange.getTargetIdsList(), targetChange.getResumeToken(), zaVarFromStatus);
        }
        if (iOrdinal == 1) {
            DocumentChange documentChange2 = listenResponse.getDocumentChange();
            List<Integer> targetIdsList = documentChange2.getTargetIdsList();
            List<Integer> removedTargetIdsList = documentChange2.getRemovedTargetIdsList();
            DocumentKey documentKeyDecodeKey = decodeKey(documentChange2.getDocument().getName());
            SnapshotVersion snapshotVersionDecodeVersion = decodeVersion(documentChange2.getDocument().getUpdateTime());
            Assert.hardAssert(!snapshotVersionDecodeVersion.equals(SnapshotVersion.NONE), "Got a document change without an update time", new Object[0]);
            MutableDocument mutableDocumentNewFoundDocument = MutableDocument.newFoundDocument(documentKeyDecodeKey, snapshotVersionDecodeVersion, ObjectValue.fromMap(documentChange2.getDocument().getFieldsMap()));
            return new WatchChange.DocumentChange(targetIdsList, removedTargetIdsList, mutableDocumentNewFoundDocument.getKey(), mutableDocumentNewFoundDocument);
        }
        if (iOrdinal == 2) {
            DocumentDelete documentDelete = listenResponse.getDocumentDelete();
            List<Integer> removedTargetIdsList2 = documentDelete.getRemovedTargetIdsList();
            MutableDocument mutableDocumentNewNoDocument = MutableDocument.newNoDocument(decodeKey(documentDelete.getDocument()), decodeVersion(documentDelete.getReadTime()));
            documentChange = new WatchChange.DocumentChange(Collections.emptyList(), removedTargetIdsList2, mutableDocumentNewNoDocument.getKey(), mutableDocumentNewNoDocument);
        } else {
            if (iOrdinal == 3) {
                DocumentRemove documentRemove = listenResponse.getDocumentRemove();
                return new WatchChange.DocumentChange(Collections.emptyList(), documentRemove.getRemovedTargetIdsList(), decodeKey(documentRemove.getDocument()), null);
            }
            if (iOrdinal != 4) {
                throw new IllegalArgumentException("Unknown change type set");
            }
            com.google.firestore.v1.ExistenceFilter filter = listenResponse.getFilter();
            documentChange = new WatchChange.ExistenceFilterWatchChange(filter.getTargetId(), new ExistenceFilter(filter.getCount()));
        }
        return documentChange;
    }

    public Document encodeDocument(DocumentKey documentKey, ObjectValue objectValue) {
        Document.Builder builderNewBuilder = Document.newBuilder();
        builderNewBuilder.setName(encodeKey(documentKey));
        builderNewBuilder.putAllFields(objectValue.getFieldsMap());
        return builderNewBuilder.build();
    }

    public com.google.firestore.v1.Target.DocumentsTarget encodeDocumentsTarget(Target target) {
        com.google.firestore.v1.Target.DocumentsTarget.Builder builderNewBuilder = com.google.firestore.v1.Target.DocumentsTarget.newBuilder();
        builderNewBuilder.addDocuments(encodeQueryPath(target.getPath()));
        return builderNewBuilder.build();
    }

    public String encodeKey(DocumentKey documentKey) {
        return encodeResourceName(this.databaseId, documentKey.getPath());
    }

    @Nullable
    public Map<String, String> encodeListenRequestLabels(TargetData targetData) {
        String strEncodeLabel = encodeLabel(targetData.getPurpose());
        if (strEncodeLabel == null) {
            return null;
        }
        HashMap map = new HashMap(1);
        map.put("goog-listen-tags", strEncodeLabel);
        return map;
    }

    public Write encodeMutation(Mutation mutation) {
        Write.Builder builderNewBuilder = Write.newBuilder();
        if (mutation instanceof SetMutation) {
            builderNewBuilder.setUpdate(encodeDocument(mutation.getKey(), ((SetMutation) mutation).getValue()));
        } else if (mutation instanceof PatchMutation) {
            PatchMutation patchMutation = (PatchMutation) mutation;
            builderNewBuilder.setUpdate(encodeDocument(mutation.getKey(), patchMutation.getValue()));
            builderNewBuilder.setUpdateMask(encodeDocumentMask(patchMutation.getMask()));
        } else if (mutation instanceof DeleteMutation) {
            builderNewBuilder.setDelete(encodeKey(mutation.getKey()));
        } else {
            if (!(mutation instanceof VerifyMutation)) {
                Assert.fail("unknown mutation type %s", mutation.getClass());
                throw null;
            }
            builderNewBuilder.setVerify(encodeKey(mutation.getKey()));
        }
        Iterator<FieldTransform> it = mutation.getFieldTransforms().iterator();
        while (it.hasNext()) {
            builderNewBuilder.addUpdateTransforms(encodeFieldTransform(it.next()));
        }
        if (!mutation.getPrecondition().isNone()) {
            builderNewBuilder.setCurrentDocument(encodePrecondition(mutation.getPrecondition()));
        }
        return builderNewBuilder.build();
    }

    public com.google.firestore.v1.Target.QueryTarget encodeQueryTarget(Target target) {
        com.google.firestore.v1.Target.QueryTarget.Builder builderNewBuilder = com.google.firestore.v1.Target.QueryTarget.newBuilder();
        StructuredQuery.Builder builderNewBuilder2 = StructuredQuery.newBuilder();
        ResourcePath path = target.getPath();
        if (target.getCollectionGroup() != null) {
            Assert.hardAssert(path.length() % 2 == 0, "Collection Group queries should be within a document path or root.", new Object[0]);
            builderNewBuilder.setParent(encodeResourceName(this.databaseId, path));
            StructuredQuery.CollectionSelector.Builder builderNewBuilder3 = StructuredQuery.CollectionSelector.newBuilder();
            builderNewBuilder3.setCollectionId(target.getCollectionGroup());
            builderNewBuilder3.setAllDescendants(true);
            builderNewBuilder2.addFrom(builderNewBuilder3);
        } else {
            Assert.hardAssert(path.length() % 2 != 0, "Document queries with filters are not supported.", new Object[0]);
            builderNewBuilder.setParent(encodeQueryPath(path.popLast()));
            StructuredQuery.CollectionSelector.Builder builderNewBuilder4 = StructuredQuery.CollectionSelector.newBuilder();
            builderNewBuilder4.setCollectionId(path.getLastSegment());
            builderNewBuilder2.addFrom(builderNewBuilder4);
        }
        if (target.getFilters().size() > 0) {
            builderNewBuilder2.setWhere(encodeFilters(target.getFilters()));
        }
        Iterator<OrderBy> it = target.getOrderBy().iterator();
        while (it.hasNext()) {
            builderNewBuilder2.addOrderBy(encodeOrderBy(it.next()));
        }
        if (target.hasLimit()) {
            builderNewBuilder2.setLimit(Int32Value.newBuilder().setValue((int) target.getLimit()));
        }
        if (target.getStartAt() != null) {
            Cursor.Builder builderNewBuilder5 = Cursor.newBuilder();
            builderNewBuilder5.addAllValues(target.getStartAt().getPosition());
            builderNewBuilder5.setBefore(target.getStartAt().isInclusive());
            builderNewBuilder2.setStartAt(builderNewBuilder5);
        }
        if (target.getEndAt() != null) {
            Cursor.Builder builderNewBuilder6 = Cursor.newBuilder();
            builderNewBuilder6.addAllValues(target.getEndAt().getPosition());
            builderNewBuilder6.setBefore(!target.getEndAt().isInclusive());
            builderNewBuilder2.setEndAt(builderNewBuilder6);
        }
        builderNewBuilder.setStructuredQuery(builderNewBuilder2);
        return builderNewBuilder.build();
    }

    public com.google.firestore.v1.Target encodeTarget(TargetData targetData) {
        com.google.firestore.v1.Target.Builder builderNewBuilder = com.google.firestore.v1.Target.newBuilder();
        Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            builderNewBuilder.setDocuments(encodeDocumentsTarget(target));
        } else {
            builderNewBuilder.setQuery(encodeQueryTarget(target));
        }
        builderNewBuilder.setTargetId(targetData.getTargetId());
        if (!targetData.getResumeToken().isEmpty() || targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) <= 0) {
            builderNewBuilder.setResumeToken(targetData.getResumeToken());
        } else {
            builderNewBuilder.setReadTime(encodeTimestamp(targetData.getSnapshotVersion().getTimestamp()));
        }
        return builderNewBuilder.build();
    }

    public com.google.protobuf.Timestamp encodeTimestamp(Timestamp timestamp) {
        com.google.protobuf.Timestamp.Builder builderNewBuilder = com.google.protobuf.Timestamp.newBuilder();
        builderNewBuilder.setSeconds(timestamp.getSeconds());
        builderNewBuilder.setNanos(timestamp.getNanoseconds());
        return builderNewBuilder.build();
    }

    @VisibleForTesting
    public StructuredQuery.Filter encodeUnaryOrFieldFilter(FieldFilter fieldFilter) {
        if (fieldFilter.getOperator() == FieldFilter.Operator.EQUAL || fieldFilter.getOperator() == FieldFilter.Operator.NOT_EQUAL) {
            StructuredQuery.UnaryFilter.Builder builderNewBuilder = StructuredQuery.UnaryFilter.newBuilder();
            builderNewBuilder.setField(encodeFieldPath(fieldFilter.getField()));
            if (Values.isNanValue(fieldFilter.getValue())) {
                builderNewBuilder.setOp(fieldFilter.getOperator() == FieldFilter.Operator.EQUAL ? StructuredQuery.UnaryFilter.Operator.IS_NAN : StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(builderNewBuilder).build();
            }
            if (Values.isNullValue(fieldFilter.getValue())) {
                builderNewBuilder.setOp(fieldFilter.getOperator() == FieldFilter.Operator.EQUAL ? StructuredQuery.UnaryFilter.Operator.IS_NULL : StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(builderNewBuilder).build();
            }
        }
        StructuredQuery.FieldFilter.Builder builderNewBuilder2 = StructuredQuery.FieldFilter.newBuilder();
        builderNewBuilder2.setField(encodeFieldPath(fieldFilter.getField()));
        builderNewBuilder2.setOp(encodeFieldFilterOperator(fieldFilter.getOperator()));
        builderNewBuilder2.setValue(fieldFilter.getValue());
        return StructuredQuery.Filter.newBuilder().setFieldFilter(builderNewBuilder2).build();
    }

    public com.google.protobuf.Timestamp encodeVersion(SnapshotVersion snapshotVersion) {
        return encodeTimestamp(snapshotVersion.getTimestamp());
    }

    public boolean isLocalResourceName(ResourcePath resourcePath) {
        return isValidResourceName(resourcePath) && resourcePath.getSegment(1).equals(this.databaseId.getProjectId()) && resourcePath.getSegment(3).equals(this.databaseId.getDatabaseId());
    }

    public Target decodeQueryTarget(com.google.firestore.v1.Target.QueryTarget queryTarget) {
        return decodeQueryTarget(queryTarget.getParent(), queryTarget.getStructuredQuery());
    }
}
