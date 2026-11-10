package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Target {
    public static final long NO_LIMIT = -1;

    @Nullable
    public final String collectionGroup;

    @Nullable
    public final Bound endAt;
    public final List<Filter> filters;
    public final long limit;

    @Nullable
    public String memoizedCanonicalId;
    public final List<OrderBy> orderBys;
    public final ResourcePath path;

    @Nullable
    public final Bound startAt;

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.Target$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = new int[FieldFilter.Operator.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_IN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.LESS_THAN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public Target(ResourcePath resourcePath, @Nullable String str, List<Filter> list, List<OrderBy> list2, long j, @Nullable Bound bound, @Nullable Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.orderBys = list2;
        this.filters = list;
        this.limit = j;
        this.startAt = bound;
        this.endAt = bound2;
    }

    private List<FieldFilter> getFieldFiltersForPath(FieldPath fieldPath) {
        ArrayList arrayList = new ArrayList();
        for (Filter filter : this.filters) {
            if (filter instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) filter;
                if (fieldFilter.getField().equals(fieldPath)) {
                    arrayList.add(fieldFilter);
                }
            }
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Target.class != obj.getClass()) {
            return false;
        }
        Target target = (Target) obj;
        String str = this.collectionGroup;
        if (str == null ? target.collectionGroup != null : !str.equals(target.collectionGroup)) {
            return false;
        }
        if (this.limit != target.limit || !this.orderBys.equals(target.orderBys) || !this.filters.equals(target.filters) || !this.path.equals(target.path)) {
            return false;
        }
        Bound bound = this.startAt;
        if (bound == null ? target.startAt != null : !bound.equals(target.startAt)) {
            return false;
        }
        Bound bound2 = this.endAt;
        if (bound2 != null) {
            return bound2.equals(target.endAt);
        }
        return target.endAt == null;
    }

    @Nullable
    public List<Value> getArrayValues(FieldIndex fieldIndex) {
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment == null) {
            return null;
        }
        for (FieldFilter fieldFilter : getFieldFiltersForPath(arraySegment.getFieldPath())) {
            int iOrdinal = fieldFilter.getOperator().ordinal();
            if (iOrdinal == 6) {
                return Collections.singletonList(fieldFilter.getValue());
            }
            if (iOrdinal == 7) {
                return fieldFilter.getValue().getArrayValue().getValuesList();
            }
        }
        return null;
    }

    public String getCanonicalId() {
        String str = this.memoizedCanonicalId;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getPath().canonicalString());
        if (this.collectionGroup != null) {
            sb.append("|cg:");
            sb.append(this.collectionGroup);
        }
        sb.append("|f:");
        Iterator<Filter> it = getFilters().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getCanonicalId());
        }
        sb.append("|ob:");
        for (OrderBy orderBy : getOrderBy()) {
            sb.append(orderBy.getField().canonicalString());
            sb.append(orderBy.getDirection().equals(OrderBy.Direction.ASCENDING) ? "asc" : ThirdAddressResponse.DESC);
        }
        if (hasLimit()) {
            sb.append("|l:");
            sb.append(getLimit());
        }
        if (this.startAt != null) {
            sb.append("|lb:");
            sb.append(this.startAt.isInclusive() ? "b:" : FirebaseInstallationServiceClient.SDK_VERSION_PREFIX);
            sb.append(this.startAt.positionString());
        }
        if (this.endAt != null) {
            sb.append("|ub:");
            sb.append(this.endAt.isInclusive() ? FirebaseInstallationServiceClient.SDK_VERSION_PREFIX : "b:");
            sb.append(this.endAt.positionString());
        }
        this.memoizedCanonicalId = sb.toString();
        return this.memoizedCanonicalId;
    }

    @Nullable
    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    @Nullable
    public Bound getEndAt() {
        return this.endAt;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x008b  */
    /* JADX WARN: Code duplicated, block: B:56:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x002a A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Nullable
    public Bound getLowerBound(FieldIndex fieldIndex) {
        int i;
        Value value;
        ?? r7;
        boolean zIsInclusive;
        Value lowerBound;
        ArrayList arrayList = new ArrayList();
        ?? r2 = 1;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Iterator<FieldFilter> it = getFieldFiltersForPath(segment.getFieldPath()).iterator();
            int i2 = 1;
            Value value2 = null;
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                FieldFilter next = it.next();
                int iOrdinal = next.getOperator().ordinal();
                if (iOrdinal == 0 || iOrdinal == 1) {
                    lowerBound = Values.getLowerBound(next.getValue().getValueTypeCase());
                } else {
                    if (iOrdinal == 2) {
                        lowerBound = next.getValue();
                    } else if (iOrdinal == 3) {
                        lowerBound = Values.MIN_VALUE;
                    } else if (iOrdinal == 4) {
                        lowerBound = next.getValue();
                    } else if (iOrdinal == 5 || iOrdinal == 8) {
                        lowerBound = next.getValue();
                    } else if (iOrdinal != 9) {
                        i = 1;
                        lowerBound = null;
                    } else {
                        ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
                        while (i < next.getValue().getArrayValue().getValuesCount()) {
                            builderNewBuilder.addValues(Values.MIN_VALUE);
                            i++;
                        }
                        lowerBound = Value.newBuilder().setArrayValue(builderNewBuilder).build();
                    }
                    if (Values.max(value2, lowerBound) == lowerBound) {
                        value2 = lowerBound;
                        i2 = i;
                    }
                }
                i = 1;
                if (Values.max(value2, lowerBound) == lowerBound) {
                    value2 = lowerBound;
                    i2 = i;
                }
            }
            if (this.startAt == null) {
                value = value2;
                r7 = i2;
                break;
            }
            while (true) {
                if (i < this.orderBys.size()) {
                    if (this.orderBys.get(i).getField().equals(segment.getFieldPath())) {
                        value = this.startAt.getPosition().get(i);
                        if (Values.max(value2, value) == value) {
                            zIsInclusive = this.startAt.isInclusive();
                            break;
                        }
                    } else {
                        i++;
                    }
                }
                value = value2;
                r7 = i2;
                break;
            }
            if (value == null) {
                r7 = zIsInclusive;
                return null;
            }
            r7 = zIsInclusive;
            arrayList.add(value);
            r2 &= r7;
        }
        return new Bound(arrayList, r2);
    }

    @Nullable
    public List<Value> getNotInValues(FieldIndex fieldIndex) {
        ArrayList arrayList = new ArrayList();
        Iterator<FieldIndex.Segment> it = fieldIndex.getDirectionalSegments().iterator();
        while (it.hasNext()) {
            for (FieldFilter fieldFilter : getFieldFiltersForPath(it.next().getFieldPath())) {
                int iOrdinal = fieldFilter.getOperator().ordinal();
                if (iOrdinal != 2) {
                    if (iOrdinal != 3) {
                        if (iOrdinal != 8) {
                            if (iOrdinal != 9) {
                            }
                        }
                    }
                    arrayList.add(fieldFilter.getValue());
                    return arrayList;
                }
                arrayList.add(fieldFilter.getValue());
            }
        }
        return null;
    }

    public List<OrderBy> getOrderBy() {
        return this.orderBys;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    @Nullable
    public Bound getStartAt() {
        return this.startAt;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0093  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Nullable
    public Bound getUpperBound(FieldIndex fieldIndex) {
        int i;
        Value value;
        ?? r7;
        boolean zIsInclusive;
        Value value2;
        ArrayList arrayList = new ArrayList();
        ?? r2 = 1;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Iterator<FieldFilter> it = getFieldFiltersForPath(segment.getFieldPath()).iterator();
            int i2 = 1;
            Value value3 = null;
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                FieldFilter next = it.next();
                int iOrdinal = next.getOperator().ordinal();
                if (iOrdinal == 0) {
                    value2 = next.getValue();
                } else if (iOrdinal == 1 || iOrdinal == 2) {
                    value2 = next.getValue();
                    i = 1;
                } else {
                    if (iOrdinal == 3) {
                        value2 = Values.MAX_VALUE;
                    } else if (iOrdinal == 4 || iOrdinal == 5) {
                        value2 = Values.getUpperBound(next.getValue().getValueTypeCase());
                    } else if (iOrdinal == 8) {
                        value2 = next.getValue();
                    } else if (iOrdinal != 9) {
                        i = 1;
                        value2 = null;
                    } else {
                        ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
                        while (i < next.getValue().getArrayValue().getValuesCount()) {
                            builderNewBuilder.addValues(Values.MAX_VALUE);
                            i++;
                        }
                        value2 = Value.newBuilder().setArrayValue(builderNewBuilder).build();
                    }
                    i = 1;
                }
                if (Values.min(value3, value2) == value2) {
                    value3 = value2;
                    i2 = i;
                }
            }
            if (this.endAt == null) {
                value = value3;
                r7 = i2;
                break;
            }
            while (true) {
                if (i < this.orderBys.size()) {
                    if (this.orderBys.get(i).getField().equals(segment.getFieldPath())) {
                        value = this.endAt.getPosition().get(i);
                        if (Values.min(value3, value) == value) {
                            zIsInclusive = this.endAt.isInclusive();
                            break;
                        }
                    } else {
                        i++;
                    }
                }
                value = value3;
                r7 = i2;
                break;
            }
            if (value == null) {
                r7 = zIsInclusive;
                return null;
            }
            r7 = zIsInclusive;
            arrayList.add(value);
            r2 &= r7;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Bound(arrayList, r2);
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public int hashCode() {
        int iHashCode = this.orderBys.hashCode() * 31;
        String str = this.collectionGroup;
        int iHashCode2 = (this.path.hashCode() + ((this.filters.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31)) * 31)) * 31;
        long j = this.limit;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        Bound bound = this.startAt;
        int iHashCode3 = (i + (bound != null ? bound.hashCode() : 0)) * 31;
        Bound bound2 = this.endAt;
        return iHashCode3 + (bound2 != null ? bound2.hashCode() : 0);
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public String toString() {
        StringBuilder sbA = a.a("Query(");
        sbA.append(this.path.canonicalString());
        if (this.collectionGroup != null) {
            sbA.append(" collectionGroup=");
            sbA.append(this.collectionGroup);
        }
        if (!this.filters.isEmpty()) {
            sbA.append(" where ");
            for (int i = 0; i < this.filters.size(); i++) {
                if (i > 0) {
                    sbA.append(" and ");
                }
                sbA.append(this.filters.get(i));
            }
        }
        if (!this.orderBys.isEmpty()) {
            sbA.append(" order by ");
            for (int i2 = 0; i2 < this.orderBys.size(); i2++) {
                if (i2 > 0) {
                    sbA.append(", ");
                }
                sbA.append(this.orderBys.get(i2));
            }
        }
        sbA.append(")");
        return sbA.toString();
    }
}
