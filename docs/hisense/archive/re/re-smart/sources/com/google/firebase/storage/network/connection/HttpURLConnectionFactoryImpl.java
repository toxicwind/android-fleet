package com.google.firebase.storage.network.connection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class HttpURLConnectionFactoryImpl implements HttpURLConnectionFactory {
    @Override // com.google.firebase.storage.network.connection.HttpURLConnectionFactory
    @Nullable
    public HttpURLConnection createInstance(@NonNull URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
