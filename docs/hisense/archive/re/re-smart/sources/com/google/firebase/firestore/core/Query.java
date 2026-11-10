package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Query {
    public static final OrderBy KEY_ORDERING_ASC = new OrderBy(OrderBy.Direction.ASCENDING, FieldPath.KEY_PATH);
    public static final OrderBy KEY_ORDERING_DESC = new OrderBy(OrderBy.Direction.DESCENDING, FieldPath.KEY_PATH);

    @Nullable
    public final String collectionGroup;

    @Nullable
    public final Bound endAt;
    public final List<OrderBy> explicitSortOrder;
    public final List<Filter> filters;
    public final long limit;
    public final LimitType limitType;
    public List<OrderBy> memoizedOrderBy;

    @Nullable
    public Target memoizedTarget;
    public final ResourcePath path;

    @Nullable
    public final Bound startAt;

    public enum LimitType {
        LIMIT_TO_FIRST,
        LIMIT_TO_LAST
    }

    private static class QueryComparator implements Comparator<Document> {
        public final List<OrderBy> sortOrder;

        public QueryComparator(List<OrderBy> list) {
            boolean z;
            Iterator<OrderBy> it = list.iterator();
            loop0: while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    } else {
                        z = (z || it.next().getField().equals(FieldPath.KEY_PATH)) ? true : z;
                    }
                }
            }
            if (!z) {
                throw new IllegalArgumentException("QueryComparator needs to have a key ordering");
            }
            this.sortOrder = list;
        }

        @Override // java.util.Comparator
        public int compare(Document document, Document document2) {
            Iterator<OrderBy> it = this.sortOrder.iterator();
            while (it.hasNext()) {
                int iCompare = it.next().compare(document, document2);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return 0;
        }
    }

    public Query(ResourcePath resourcePath, @Nullable String str, List<Filter> list, List<OrderBy> list2, long j, LimitType limitType, @Nullable Bound bound, @Nullable Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.explicitSortOrder = list2;
        this.filters = list;
        this.limit = j;
        this.limitType = limitType;
        this.startAt = bound;
        this.endAt = bound2;
    }

    public static Query atPath(ResourcePath resourcePath) {
        return new Query(resourcePath, null);
    }

    private boolean matchesBounds(Document document) {
        Bound bound = this.startAt;
        if (bound != null && !bound.sortsBeforeDocument(getOrderBy(), document)) {
            return false;
        }
        Bound bound2 = this.endAt;
        return bound2 == null || bound2.sortsAfterDocument(getOrderBy(), document);
    }

    private boolean matchesFilters(Document document) {
        Iterator<Filter> it = this.filters.iterator();
        while (it.hasNext()) {
            if (!it.next().matches(document)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesOrderBy(Document document) {
        for (OrderBy orderBy : this.explicitSortOrder) {
            if (!orderBy.getField().equals(FieldPath.KEY_PATH) && document.getField(orderBy.field) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesPathAndCollectionGroup(Document document) {
        ResourcePath path = document.getKey().getPath();
        if (this.collectionGroup != null) {
            return document.getKey().hasCollectionId(this.collectionGroup) && this.path.isPrefixOf(path);
        }
        if (DocumentKey.isDocumentKey(this.path)) {
            return this.path.equals(path);
        }
        return this.path.isPrefixOf(path) && this.path.length() == path.length() - 1;
    }

    public Query asCollectionQueryAtPath(ResourcePath resourcePath) {
        return new Query(resourcePath, null, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Comparator<Document> comparator() {
        return new QueryComparator(getOrderBy());
    }

    public Query endAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, bound);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Query.class != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.limitType != query.limitType) {
            return false;
        }
        return toTarget().equals(query.toTarget());
    }

    public Query filter(Filter filter) {
        boolean z = true;
        Assert.hardAssert(!isDocumentQuery(), "No filter is allowed for document query", new Object[0]);
        FieldPath field = null;
        if (filter instanceof FieldFilter) {
            FieldFilter fieldFilter = (FieldFilter) filter;
            if (fieldFilter.isInequality()) {
                field = fieldFilter.getField();
            }
        }
        FieldPath fieldPathInequalityField = inequalityField();
        Assert.hardAssert(fieldPathInequalityField == null || field == null || fieldPathInequalityField.equals(field), "Query must only have one inequality field", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() && field != null && !this.explicitSortOrder.get(0).field.equals(field)) {
            z = false;
        }
        Assert.hardAssert(z, "First orderBy must match inequality field", new Object[0]);
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.add(filter);
        return new Query(this.path, this.collectionGroup, arrayList, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    @Nullable
    public FieldFilter.Operator findFilterOperator(List<FieldFilter.Operator> list) {
        for (Filter filter : this.filters) {
            if (filter instanceof FieldFilter) {
                FieldFilter.Operator operator = ((FieldFilter) filter).getOperator();
                if (list.contains(operator)) {
                    return operator;
                }
            }
        }
        return null;
    }

    public String getCanonicalId() {
        return toTarget().getCanonicalId() + "|lt:" + this.limitType;
    }

    @Nullable
    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    @Nullable
    public Bound getEndAt() {
        return this.endAt;
    }

    public List<OrderBy> getExplicitOrderBy() {
        return this.explicitSortOrder;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public FieldPath getFirstOrderByField() {
        if (this.explicitSortOrder.isEmpty()) {
            return null;
        }
        return this.explicitSortOrder.get(0).getField();
    }

    public long getLimitToFirst() {
        Assert.hardAssert(hasLimitToFirst(), "Called getLimitToFirst when no limit was set", new Object[0]);
        return this.limit;
    }

    public long getLimitToLast() {
        Assert.hardAssert(hasLimitToLast(), "Called getLimitToLast when no limit was set", new Object[0]);
        return this.limit;
    }

    public LimitType getLimitType() {
        Assert.hardAssert(hasLimitToLast() || hasLimitToFirst(), "Called getLimitType when no limit was set", new Object[0]);
        return this.limitType;
    }

    public List<OrderBy> getOrderBy() {
        OrderBy.Direction direction;
        if (this.memoizedOrderBy == null) {
            FieldPath fieldPathInequalityField = inequalityField();
            FieldPath firstOrderByField = getFirstOrderByField();
            boolean z = false;
            if (fieldPathInequalityField == null || firstOrderByField != null) {
                ArrayList arrayList = new ArrayList();
                for (OrderBy orderBy : this.explicitSortOrder) {
                    arrayList.add(orderBy);
                    if (orderBy.getField().equals(FieldPath.KEY_PATH)) {
                        z = true;
                    }
                }
                if (!z) {
                    if (this.explicitSortOrder.size() > 0) {
                        List<OrderBy> list = this.explicitSortOrder;
                        direction = list.get(list.size() - 1).getDirection();
                    } else {
                        direction = OrderBy.Direction.ASCENDING;
                    }
                    arrayList.add(direction.equals(OrderBy.Direction.ASCENDING) ? KEY_ORDERING_ASC : KEY_ORDERING_DESC);
                }
                this.memoizedOrderBy = arrayList;
            } else if (fieldPathInequalityField.isKeyField()) {
                this.memoizedOrderBy = Collections.singletonList(KEY_ORDERING_ASC);
            } else {
                this.memoizedOrderBy = Arrays.asList(new OrderBy(OrderBy.Direction.ASCENDING, fieldPathInequalityField), KEY_ORDERING_ASC);
            }
        }
        return this.memoizedOrderBy;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    @Nullable
    public Bound getStartAt() {
        return this.startAt;
    }

    public boolean hasLimitToFirst() {
        return this.limitType == LimitType.LIMIT_TO_FIRST && this.limit != -1;
    }

    public boolean hasLimitToLast() {
        return this.limitType == LimitType.LIMIT_TO_LAST && this.limit != -1;
    }

    public int hashCode() {
        return this.limitType.hashCode() + (toTarget().hashCode() * 31);
    }

    @Nullable
    public FieldPath inequalityField() {
        for (Filter filter : this.filters) {
            if (filter instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) filter;
                if (fieldFilter.isInequality()) {
                    return fieldFilter.getField();
                }
            }
        }
        return null;
    }

    public boolean isCollectionGroupQuery() {
        return this.collectionGroup != null;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public Query limitToFirst(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_FIRST, this.startAt, this.endAt);
    }

    public Query limitToLast(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_LAST, this.startAt, this.endAt);
    }

    public boolean matches(Document document) {
        return document.isFoundDocument() && matchesPathAndCollectionGroup(document) && matchesOrderBy(document) && matchesFilters(document) && matchesBounds(document);
    }

    public boolean matchesAllDocuments() {
        if (this.filters.isEmpty() && this.limit == -1 && this.startAt == null && this.endAt == null) {
            if (getExplicitOrderBy().isEmpty()) {
                return true;
            }
            if (getExplicitOrderBy().size() == 1 && getFirstOrderByField().isKeyField()) {
                return true;
            }
        }
        return false;
    }

    public Query orderBy(OrderBy orderBy) {
        FieldPath fieldPathInequalityField;
        Assert.hardAssert(!isDocumentQuery(), "No ordering is allowed for document query", new Object[0]);
        if (this.explicitSortOrder.isEmpty() && (fieldPathInequalityField = inequalityField()) != null && !fieldPathInequalityField.equals(orderBy.field)) {
            Assert.fail("First orderBy must match inequality field", new Object[0]);
            throw null;
        }
        ArrayList arrayList = new ArrayList(this.explicitSortOrder);
        arrayList.add(orderBy);
        return new Query(this.path, this.collectionGroup, this.filters, arrayList, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query startAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, bound, this.endAt);
    }

    public String toString() {
        StringBuilder sbA = a.a("Query(target=");
        sbA.append(toTarget().toString());
        sbA.append(";limitType=");
        sbA.append(this.limitType.toString());
        sbA.append(")");
        return sbA.toString();
    }

    public Target toTarget() {
        if (this.memoizedTarget == null) {
            if (this.limitType == LimitType.LIMIT_TO_FIRST) {
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), getOrderBy(), this.limit, getStartAt(), getEndAt());
            } else {
                ArrayList arrayList = new ArrayList();
                for (OrderBy orderBy : getOrderBy()) {
                    OrderBy.Direction direction = orderBy.getDirection();
                    OrderBy.Direction direction2 = OrderBy.Direction.DESCENDING;
                    if (direction == direction2) {
                        direction2 = OrderBy.Direction.ASCENDING;
                    }
                    arrayList.add(new OrderBy(direction2, orderBy.getField()));
                }
                Bound bound = this.endAt;
                Bound bound2 = bound != null ? new Bound(bound.getPosition(), !this.endAt.isInclusive()) : null;
                Bound bound3 = this.startAt;
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), arrayList, this.limit, bound2, bound3 != null ? new Bound(bound3.getPosition(), !this.startAt.isInclusive()) : null);
            }
        }
        return this.memoizedTarget;
    }

    public Query(ResourcePath resourcePath, @Nullable String str) {
        this(resourcePath, str, Collections.emptyList(), Collections.emptyList(), -1L, LimitType.LIMIT_TO_FIRST, null, null);
    }
}
