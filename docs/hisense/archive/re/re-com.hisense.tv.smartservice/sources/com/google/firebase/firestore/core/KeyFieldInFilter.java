package com.google.firebase.firestore.core;

import b.a.a.a.a;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyFieldInFilter extends FieldFilter {
    public final List<DocumentKey> keys;

    public KeyFieldInFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, FieldFilter.Operator.IN, value);
        this.keys = new ArrayList();
        this.keys.addAll(extractDocumentKeysFromArrayValue(FieldFilter.Operator.IN, value));
    }

    public static List<DocumentKey> extractDocumentKeysFromArrayValue(FieldFilter.Operator operator, Value value) {
        Assert.hardAssert(operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN, "extractDocumentKeysFromArrayValue requires IN or NOT_IN operators", new Object[0]);
        Assert.hardAssert(Values.isArray(value), "KeyFieldInFilter/KeyFieldNotInFilter expects an ArrayValue", new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (Value value2 : value.getArrayValue().getValuesList()) {
            boolean zIsReferenceValue = Values.isReferenceValue(value2);
            StringBuilder sbA = a.a("Comparing on key with ");
            sbA.append(operator.toString());
            sbA.append(", but an array value was not a ReferenceValue");
            Assert.hardAssert(zIsReferenceValue, sbA.toString(), new Object[0]);
            arrayList.add(DocumentKey.fromName(value2.getReferenceValue()));
        }
        return arrayList;
    }

    @Override // com.google.firebase.firestore.core.FieldFilter, com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        return this.keys.contains(document.getKey());
    }
}
