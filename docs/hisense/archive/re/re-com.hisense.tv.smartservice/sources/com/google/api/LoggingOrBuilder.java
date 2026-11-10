package com.google.api;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface LoggingOrBuilder extends MessageLiteOrBuilder {
    Logging.LoggingDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<Logging.LoggingDestination> getConsumerDestinationsList();

    Logging.LoggingDestination getProducerDestinations(int i);

    int getProducerDestinationsCount();

    List<Logging.LoggingDestination> getProducerDestinationsList();
}
