package com.uwetrottmann.tmdb2;

import com.uwetrottmann.tmdb2.entities.GuestSession;
import com.uwetrottmann.tmdb2.entities.RequestToken;
import com.uwetrottmann.tmdb2.entities.Session;
import com.uwetrottmann.tmdb2.exceptions.TmdbAuthenticationFailedException;
import com.uwetrottmann.tmdb2.services.AuthenticationService;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/* JADX INFO: loaded from: classes7.dex */
public class TmdbAuthenticator implements Authenticator {
    private final Tmdb tmdb;

    public TmdbAuthenticator(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override // okhttp3.Authenticator
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        return handleRequest(response, this.tmdb);
    }

    @Nullable
    public static Request handleRequest(Response response, Tmdb tmdb) throws IOException {
        if (response.request().url().pathSegments().get(0).equals(Tmdb.PATH_AUTHENTICATION)) {
            return null;
        }
        if (responseCount(response) >= 2) {
            throw new TmdbAuthenticationFailedException(30, "Authentication failed: You do not have permissions to access the service.");
        }
        HttpUrl.Builder builderNewBuilder = response.request().url().newBuilder();
        if (tmdb.useAccountSession()) {
            if (tmdb.getUsername() == null || tmdb.getPassword() == null) {
                throw new TmdbAuthenticationFailedException(26, "You must provide a username and password.");
            }
            String strAcquireAccountSession = acquireAccountSession(tmdb);
            if (strAcquireAccountSession == null) {
                return null;
            }
            builderNewBuilder.setEncodedQueryParameter(Tmdb.PARAM_SESSION_ID, strAcquireAccountSession);
        } else if (tmdb.useGuestSession()) {
            if (acquireGuestSession(tmdb) == null) {
                return null;
            }
            builderNewBuilder.setEncodedQueryParameter(Tmdb.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        } else {
            throw new TmdbAuthenticationFailedException(30, "Authentication failed: You do not have permissions to access the service.");
        }
        return response.request().newBuilder().url(builderNewBuilder.build()).build();
    }

    @Nullable
    public static String acquireAccountSession(Tmdb tmdb) throws IOException {
        RequestToken requestTokenBody;
        Session sessionBody;
        AuthenticationService authenticationService = (AuthenticationService) tmdb.getRetrofit().create(AuthenticationService.class);
        RequestToken requestTokenBody2 = authenticationService.requestToken().execute().body();
        if (requestTokenBody2 == null || (requestTokenBody = authenticationService.validateToken(tmdb.getUsername(), tmdb.getPassword(), requestTokenBody2.request_token).execute().body()) == null || (sessionBody = authenticationService.createSession(requestTokenBody.request_token).execute().body()) == null) {
            return null;
        }
        tmdb.setSessionId(sessionBody.session_id);
        return sessionBody.session_id;
    }

    @Nullable
    public static String acquireGuestSession(Tmdb tmdb) throws IOException {
        GuestSession guestSessionBody = ((AuthenticationService) tmdb.getRetrofit().create(AuthenticationService.class)).createGuestSession().execute().body();
        if (guestSessionBody == null) {
            return null;
        }
        tmdb.setGuestSessionId(guestSessionBody.guest_session_id);
        return guestSessionBody.guest_session_id;
    }

    private static int responseCount(Response response) {
        int i = 1;
        while (true) {
            response = response.priorResponse();
            if (response == null) {
                return i;
            }
            i++;
        }
    }
}
