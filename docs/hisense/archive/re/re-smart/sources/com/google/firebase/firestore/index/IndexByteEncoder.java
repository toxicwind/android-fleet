package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.FieldIndex;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public class IndexByteEncoder {
    public final OrderedCodeWriter orderedCode = new OrderedCodeWriter();
    public final AscendingIndexByteEncoder ascending = new AscendingIndexByteEncoder();
    public final DescendingIndexByteEncoder descending = new DescendingIndexByteEncoder();

    class AscendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        public AscendingIndexByteEncoder() {
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesAscending(byteString);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeDouble(double d2) {
            IndexByteEncoder.this.orderedCode.writeDoubleAscending(d2);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityAscending();
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongAscending(j);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Ascending(str);
        }
    }

    class DescendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        public DescendingIndexByteEncoder() {
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesDescending(byteString);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeDouble(double d2) {
            IndexByteEncoder.this.orderedCode.writeDoubleDescending(d2);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityDescending();
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongDescending(j);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Descending(str);
        }
    }

    public DirectionalIndexByteEncoder forKind(FieldIndex.Segment.Kind kind) {
        return kind.equals(FieldIndex.Segment.Kind.DESCENDING) ? this.descending : this.ascending;
    }

    public byte[] getEncodedBytes() {
        return this.orderedCode.encodedBytes();
    }

    public void reset() {
        this.orderedCode.reset();
    }

    public void seed(byte[] bArr) {
        this.orderedCode.seed(bArr);
    }
}
