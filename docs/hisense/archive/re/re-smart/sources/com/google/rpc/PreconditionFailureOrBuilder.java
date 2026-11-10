package com.google.rpc;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface PreconditionFailureOrBuilder extends MessageLiteOrBuilder {
    PreconditionFailure.Violation getViolations(int i);

    int getViolationsCount();

    List<PreconditionFailure.Violation> getViolationsList();
}
