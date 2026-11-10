package com.regza.cesi;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: CesiConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public interface NotifyListener {
    void onError(@NotNull Error error);

    void onNotify(@NotNull JsonNode jsonNode);
}
