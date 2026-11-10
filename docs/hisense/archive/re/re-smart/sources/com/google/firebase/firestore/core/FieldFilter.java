package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class FieldFilter extends Filter {
    public final FieldPath field;
    public final Operator operator;
    public final Value value;

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.FieldFilter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = new int[Operator.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        EQUAL("=="),
        NOT_EQUAL("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        ARRAY_CONTAINS("array_contains"),
        ARRAY_CONTAINS_ANY("array_contains_any"),
        IN(HisenseCountryLanInfo.LOCALE_IND_CODE),
        NOT_IN("not_in");

        public final String text;

        Operator(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public FieldFilter(FieldPath fieldPath, Operator operator, Value value) {
        this.field = fieldPath;
        this.operator = operator;
        this.value = value;
    }

    public static FieldFilter create(FieldPath fieldPath, Operator operator, Value value) {
        if (!fieldPath.isKeyField()) {
            if (operator == Operator.ARRAY_CONTAINS) {
                return new ArrayContainsFilter(fieldPath, value);
            }
            if (operator == Operator.IN) {
                return new InFilter(fieldPath, value);
            }
            if (operator == Operator.ARRAY_CONTAINS_ANY) {
                return new ArrayContainsAnyFilter(fieldPath, value);
            }
            return operator == Operator.NOT_IN ? new NotInFilter(fieldPath, value) : new FieldFilter(fieldPath, operator, value);
        }
        if (operator == Operator.IN) {
            return new KeyFieldInFilter(fieldPath, value);
        }
        if (operator == Operator.NOT_IN) {
            return new KeyFieldNotInFilter(fieldPath, value);
        }
        Assert.hardAssert((operator == Operator.ARRAY_CONTAINS || operator == Operator.ARRAY_CONTAINS_ANY) ? false : true, operator.toString() + "queries don't make sense on document keys", new Object[0]);
        return new KeyFieldFilter(fieldPath, operator, value);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldFilter)) {
            return false;
        }
        FieldFilter fieldFilter = (FieldFilter) obj;
        return this.operator == fieldFilter.operator && this.field.equals(fieldFilter.field) && this.value.equals(fieldFilter.value);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        return getField().canonicalString() + getOperator().toString() + Values.canonicalId(getValue());
    }

    public FieldPath getField() {
        return this.field;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public Value getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + ((this.field.hashCode() + ((this.operator.hashCode() + 1147) * 31)) * 31);
    }

    public boolean isInequality() {
        return Arrays.asList(Operator.LESS_THAN, Operator.LESS_THAN_OR_EQUAL, Operator.GREATER_THAN, Operator.GREATER_THAN_OR_EQUAL, Operator.NOT_EQUAL, Operator.NOT_IN).contains(this.operator);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        Value field = document.getField(this.field);
        if (this.operator == Operator.NOT_EQUAL) {
            return field != null && matchesComparison(Values.compare(field, this.value));
        }
        return field != null && Values.typeOrder(field) == Values.typeOrder(this.value) && matchesComparison(Values.compare(field, this.value));
    }

    public boolean matchesComparison(int i) {
        int iOrdinal = this.operator.ordinal();
        if (iOrdinal == 0) {
            return i < 0;
        }
        if (iOrdinal == 1) {
            return i <= 0;
        }
        if (iOrdinal == 2) {
            return i == 0;
        }
        if (iOrdinal == 3) {
            return i != 0;
        }
        if (iOrdinal == 4) {
            return i > 0;
        }
        if (iOrdinal == 5) {
            return i >= 0;
        }
        Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        throw null;
    }

    public String toString() {
        return this.field.canonicalString() + " " + this.operator + " " + Values.canonicalId(this.value);
    }
}
