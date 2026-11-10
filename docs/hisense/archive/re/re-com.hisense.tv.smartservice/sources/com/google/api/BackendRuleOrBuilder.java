package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes.dex */
public interface BackendRuleOrBuilder extends MessageLiteOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    BackendRule.AuthenticationCase getAuthenticationCase();

    double getDeadline();

    boolean getDisableAuth();

    String getJwtAudience();

    ByteString getJwtAudienceBytes();

    double getMinDeadline();

    double getOperationDeadline();

    BackendRule.PathTranslation getPathTranslation();

    int getPathTranslationValue();

    String getProtocol();

    ByteString getProtocolBytes();

    String getSelector();

    ByteString getSelectorBytes();
}
