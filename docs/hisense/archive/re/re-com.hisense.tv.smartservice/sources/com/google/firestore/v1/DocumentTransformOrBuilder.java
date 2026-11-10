package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface DocumentTransformOrBuilder extends MessageLiteOrBuilder {
    String getDocument();

    ByteString getDocumentBytes();

    DocumentTransform.FieldTransform getFieldTransforms(int i);

    int getFieldTransformsCount();

    List<DocumentTransform.FieldTransform> getFieldTransformsList();
}
