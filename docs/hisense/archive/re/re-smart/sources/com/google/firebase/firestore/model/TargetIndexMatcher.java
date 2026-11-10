package com.google.firebase.firestore.model;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TargetIndexMatcher {
    public final String collectionId;
    public final List<FieldFilter> equalityFilters;

    @Nullable
    public FieldFilter inequalityFilter;
    public final List<OrderBy> orderBys;

    public TargetIndexMatcher(Target target) {
        this.collectionId = target.getCollectionGroup() != null ? target.getCollectionGroup() : target.getPath().getLastSegment();
        this.orderBys = target.getOrderBy();
        this.inequalityFilter = null;
        this.equalityFilters = new ArrayList();
        Iterator<Filter> it = target.getFilters().iterator();
        while (it.hasNext()) {
            FieldFilter fieldFilter = (FieldFilter) it.next();
            if (fieldFilter.isInequality()) {
                FieldFilter fieldFilter2 = this.inequalityFilter;
                Assert.hardAssert(fieldFilter2 == null || fieldFilter2.getField().equals(fieldFilter.getField()), "Only a single inequality is supported", new Object[0]);
                this.inequalityFilter = fieldFilter;
            } else {
                this.equalityFilters.add(fieldFilter);
            }
        }
    }

    private boolean hasMatchingEqualityFilter(FieldIndex.Segment segment) {
        Iterator<FieldFilter> it = this.equalityFilters.iterator();
        while (it.hasNext()) {
            if (matchesFilter(it.next(), segment)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesFilter(@Nullable FieldFilter fieldFilter, FieldIndex.Segment segment) {
        if (fieldFilter == null || !fieldFilter.getField().equals(segment.getFieldPath())) {
            return false;
        }
        return segment.getKind().equals(FieldIndex.Segment.Kind.CONTAINS) == (fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY));
    }

    private boolean matchesOrderBy(OrderBy orderBy, FieldIndex.Segment segment) {
        if (orderBy.getField().equals(segment.getFieldPath())) {
            return (segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING) && orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) || (segment.getKind().equals(FieldIndex.Segment.Kind.DESCENDING) && orderBy.getDirection().equals(OrderBy.Direction.DESCENDING));
        }
        return false;
    }

    public boolean servedByIndex(FieldIndex fieldIndex) {
        Assert.hardAssert(fieldIndex.getCollectionGroup().equals(this.collectionId), "Collection IDs do not match", new Object[0]);
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null && !hasMatchingEqualityFilter(arraySegment)) {
            return false;
        }
        Iterator<OrderBy> it = this.orderBys.iterator();
        List<FieldIndex.Segment> directionalSegments = fieldIndex.getDirectionalSegments();
        int i = 0;
        while (i < directionalSegments.size() && hasMatchingEqualityFilter(directionalSegments.get(i))) {
            i++;
        }
        if (i == directionalSegments.size()) {
            return true;
        }
        if (this.inequalityFilter != null) {
            FieldIndex.Segment segment = directionalSegments.get(i);
            if (!matchesFilter(this.inequalityFilter, segment) || !matchesOrderBy(it.next(), segment)) {
                return false;
            }
            i++;
        }
        while (i < directionalSegments.size()) {
            FieldIndex.Segment segment2 = directionalSegments.get(i);
            if (!it.hasNext() || !matchesOrderBy(it.next(), segment2)) {
                return false;
            }
            i++;
        }
        return true;
    }
}
