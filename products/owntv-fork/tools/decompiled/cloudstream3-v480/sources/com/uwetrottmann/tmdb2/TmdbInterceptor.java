package com.uwetrottmann.tmdb2;

import coil3.network.internal.UtilsKt;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes7.dex */
public class TmdbInterceptor implements Interceptor {
    private final Tmdb tmdb;

    public TmdbInterceptor(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(@Nonnull Interceptor.Chain chain) throws IOException {
        return handleIntercept(chain, this.tmdb);
    }

    public static Response handleIntercept(Interceptor.Chain chain, Tmdb tmdb) throws IOException {
        String strHeader;
        Request request = chain.request();
        if (!Tmdb.API_HOST.equals(request.url().host())) {
            return chain.proceed(request);
        }
        HttpUrl.Builder builderNewBuilder = request.url().newBuilder();
        builderNewBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, tmdb.apiKey());
        if (tmdb.isLoggedIn()) {
            List<String> listPathSegments = request.url().pathSegments();
            if ((listPathSegments.size() >= 2 && listPathSegments.get(1).equals("account")) || listPathSegments.get(listPathSegments.size() - 1).equals("account_states") || listPathSegments.get(listPathSegments.size() - 1).equals("rating") || !request.method().equals(UtilsKt.HTTP_METHOD_GET)) {
                addSessionToken(tmdb, builderNewBuilder);
            }
        }
        Request.Builder builderNewBuilder2 = request.newBuilder();
        builderNewBuilder2.url(builderNewBuilder.build());
        Response responseProceed = chain.proceed(builderNewBuilder2.build());
        if (!responseProceed.getIsSuccessful() && (strHeader = responseProceed.header("Retry-After")) != null) {
            try {
                Thread.sleep((int) ((((double) Integer.parseInt(strHeader)) + 0.5d) * 1000.0d));
                if (responseProceed.body() != null) {
                    responseProceed.body().close();
                }
                return handleIntercept(chain, tmdb);
            } catch (InterruptedException | NumberFormatException unused) {
            }
        }
        return responseProceed;
    }

    private static void addSessionToken(Tmdb tmdb, HttpUrl.Builder builder) {
        if (tmdb.getSessionId() != null) {
            builder.addEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, tmdb.getSessionId());
        } else if (tmdb.getGuestSessionId() != null) {
            builder.addEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        }
    }
}
