package com.ju.lib.datacommunication.network.http.address;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface DynamicAddressUseCase {
    void destroy();

    List<String> getHost(String str);

    String getRandomHost(String str);

    void start();
}
