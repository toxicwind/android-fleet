package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class OrderBy {
    public final Direction direction;
    public final FieldPath field;

    public enum Direction {
        ASCENDING(1),
        DESCENDING(-1);

        public final int comparisonModifier;

        Direction(int i) {
            this.comparisonModifier = i;
        }

        public int getComparisonModifier() {
            return this.comparisonModifier;
        }
    }

    public OrderBy(Direction direction, FieldPath fieldPath) {
        this.direction = direction;
        this.field = fieldPath;
    }

    public static OrderBy getInstance(Direction direction, FieldPath fieldPath) {
        return new OrderBy(direction, fieldPath);
    }

    public int compare(Document document, Document document2) {
        int comparisonModifier;
        int iCompare;
        if (this.field.equals(FieldPath.KEY_PATH)) {
            comparisonModifier = this.direction.getComparisonModifier();
            iCompare = document.getKey().compareTo(document2.getKey());
        } else {
            Value field = document.getField(this.field);
            Value field2 = document2.getField(this.field);
            Assert.hardAssert((field == null || field2 == null) ? false : true, "Trying to compare documents on fields that don't exist.", new Object[0]);
            comparisonModifier = this.direction.getComparisonModifier();
            iCompare = Values.compare(field, field2);
        }
        return iCompare * comparisonModifier;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OrderBy)) {
            return false;
        }
        OrderBy orderBy = (OrderBy) obj;
        return this.direction == orderBy.direction && this.field.equals(orderBy.field);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public FieldPath getField() {
        return this.field;
    }

    public int hashCode() {
        return this.field.hashCode() + ((this.direction.hashCode() + 899) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.direction == Direction.ASCENDING ? "" : MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        sb.append(this.field.canonicalString());
        return sb.toString();
    }
}
