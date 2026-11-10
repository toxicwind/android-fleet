package com.google.rpc;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface HelpOrBuilder extends MessageLiteOrBuilder {
    Help.Link getLinks(int i);

    int getLinksCount();

    List<Help.Link> getLinksList();
}
