package com.google.rpc;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface QuotaFailureOrBuilder extends MessageLiteOrBuilder {
    QuotaFailure.Violation getViolations(int i);

    int getViolationsCount();

    List<QuotaFailure.Violation> getViolationsList();
}
