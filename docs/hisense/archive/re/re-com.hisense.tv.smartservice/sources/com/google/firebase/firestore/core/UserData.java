package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class UserData {

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.UserData$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$UserData$Source = new int[Source.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.Set.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.MergeSet.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.Update.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.Argument.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.ArrayArgument.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static class ParsedSetData {
        public final ObjectValue data;

        @Nullable
        public final FieldMask fieldMask;
        public final List<FieldTransform> fieldTransforms;

        public ParsedSetData(ObjectValue objectValue, @Nullable FieldMask fieldMask, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask;
            this.fieldTransforms = list;
        }

        public ObjectValue getData() {
            return this.data;
        }

        @Nullable
        public FieldMask getFieldMask() {
            return this.fieldMask;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public Mutation toMutation(DocumentKey documentKey, Precondition precondition) {
            FieldMask fieldMask = this.fieldMask;
            return fieldMask != null ? new PatchMutation(documentKey, this.data, fieldMask, precondition, this.fieldTransforms) : new SetMutation(documentKey, this.data, precondition, this.fieldTransforms);
        }
    }

    public static class ParsedUpdateData {
        public final ObjectValue data;
        public final FieldMask fieldMask;
        public final List<FieldTransform> fieldTransforms;

        public ParsedUpdateData(ObjectValue objectValue, FieldMask fieldMask, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask;
            this.fieldTransforms = list;
        }

        public ObjectValue getData() {
            return this.data;
        }

        public FieldMask getFieldMask() {
            return this.fieldMask;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public Mutation toMutation(DocumentKey documentKey, Precondition precondition) {
            return new PatchMutation(documentKey, this.data, this.fieldMask, precondition, this.fieldTransforms);
        }
    }

    public enum Source {
        Set,
        MergeSet,
        Update,
        Argument,
        ArrayArgument
    }

    public static class ParseAccumulator {
        public final Source dataSource;
        public final Set<FieldPath> fieldMask = new HashSet();
        public final ArrayList<FieldTransform> fieldTransforms = new ArrayList<>();

        public ParseAccumulator(Source source) {
            this.dataSource = source;
        }

        public void addToFieldMask(FieldPath fieldPath) {
            this.fieldMask.add(fieldPath);
        }

        public void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.fieldTransforms.add(new FieldTransform(fieldPath, transformOperation));
        }

        public boolean contains(FieldPath fieldPath) {
            Iterator<FieldPath> it = this.fieldMask.iterator();
            while (it.hasNext()) {
                if (fieldPath.isPrefixOf(it.next())) {
                    return true;
                }
            }
            Iterator<FieldTransform> it2 = this.fieldTransforms.iterator();
            while (it2.hasNext()) {
                if (fieldPath.isPrefixOf(it2.next().getFieldPath())) {
                    return true;
                }
            }
            return false;
        }

        public Source getDataSource() {
            return this.dataSource;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public ParseContext rootContext() {
            return new ParseContext(this, FieldPath.EMPTY_PATH, false, null);
        }

        public ParsedSetData toMergeData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, new FieldMask(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedSetData toSetData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, null, Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedUpdateData toUpdateData(ObjectValue objectValue) {
            return new ParsedUpdateData(objectValue, new FieldMask(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedSetData toMergeData(ObjectValue objectValue, FieldMask fieldMask) {
            ArrayList arrayList = new ArrayList();
            for (FieldTransform fieldTransform : this.fieldTransforms) {
                if (fieldMask.covers(fieldTransform.getFieldPath())) {
                    arrayList.add(fieldTransform);
                }
            }
            return new ParsedSetData(objectValue, fieldMask, Collections.unmodifiableList(arrayList));
        }
    }

    public static class ParseContext {
        public static final String RESERVED_FIELD_DESIGNATOR = "__";
        public final ParseAccumulator accumulator;
        public final boolean arrayElement;

        @Nullable
        public final FieldPath path;

        public ParseContext(ParseAccumulator parseAccumulator, @Nullable FieldPath fieldPath, boolean z) {
            this.accumulator = parseAccumulator;
            this.path = fieldPath;
            this.arrayElement = z;
        }

        private void validatePath() {
            if (this.path == null) {
                return;
            }
            for (int i = 0; i < this.path.length(); i++) {
                validatePathSegment(this.path.getSegment(i));
            }
        }

        private void validatePathSegment(String str) {
            if (str.isEmpty()) {
                throw createError("Document fields must not be empty");
            }
            if (isWrite() && str.startsWith(RESERVED_FIELD_DESIGNATOR) && str.endsWith(RESERVED_FIELD_DESIGNATOR)) {
                throw createError("Document fields cannot begin and end with \"__\"");
            }
        }

        public void addToFieldMask(FieldPath fieldPath) {
            this.accumulator.addToFieldMask(fieldPath);
        }

        public void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.accumulator.addToFieldTransforms(fieldPath, transformOperation);
        }

        public ParseContext childContext(String str) {
            FieldPath fieldPath = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath == null ? null : fieldPath.append(str), false);
            parseContext.validatePathSegment(str);
            return parseContext;
        }

        public RuntimeException createError(String str) {
            String string;
            FieldPath fieldPath = this.path;
            if (fieldPath == null || fieldPath.isEmpty()) {
                string = "";
            } else {
                StringBuilder sbA = a.a(" (found in field ");
                sbA.append(this.path.toString());
                sbA.append(")");
                string = sbA.toString();
            }
            return new IllegalArgumentException(a.a("Invalid data. ", str, string));
        }

        public Source getDataSource() {
            return this.accumulator.dataSource;
        }

        @Nullable
        public FieldPath getPath() {
            return this.path;
        }

        public boolean isArrayElement() {
            return this.arrayElement;
        }

        public boolean isWrite() {
            int iOrdinal = this.accumulator.dataSource.ordinal();
            if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
                return true;
            }
            if (iOrdinal == 3 || iOrdinal == 4) {
                return false;
            }
            Assert.fail("Unexpected case for UserDataSource: %s", this.accumulator.dataSource.name());
            throw null;
        }

        public ParseContext childContext(FieldPath fieldPath) {
            FieldPath fieldPath2 = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath2 == null ? null : fieldPath2.append(fieldPath), false);
            parseContext.validatePath();
            return parseContext;
        }

        public /* synthetic */ ParseContext(ParseAccumulator parseAccumulator, FieldPath fieldPath, boolean z, AnonymousClass1 anonymousClass1) {
            this.accumulator = parseAccumulator;
            this.path = fieldPath;
            this.arrayElement = z;
        }

        public ParseContext childContext(int i) {
            return new ParseContext(this.accumulator, null, true);
        }
    }
}
