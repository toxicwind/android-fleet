package okhttp3.internal.http;

import b.a.a.a.a;
import d.g;
import d.j;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpHeaders {
    public static final j QUOTED_STRING_DELIMITERS = j.d("\"\\");
    public static final j TOKEN_DELIMITERS = j.d("\t ,=");

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int iCode = response.code();
        return (((iCode >= 100 && iCode < 200) || iCode == 204 || iCode == 304) && contentLength(response.headers()) == -1 && !"chunked".equalsIgnoreCase(response.header(com.google.common.net.HttpHeaders.TRANSFER_ENCODING))) ? false : true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0085  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:61:0x007a A[EDGE_INSN: B:61:0x007a->B:28:0x007a BREAK  A[LOOP:2: B:22:0x0066->B:51:0x00c2], SYNTHETIC] */
    public static void parseChallengeHeader(List<Challenge> list, g gVar) {
        String token;
        while (true) {
            String token2 = null;
            while (true) {
                if (token2 == null) {
                    skipWhitespaceAndCommas(gVar);
                    token2 = readToken(gVar);
                    if (token2 == null) {
                        return;
                    }
                }
                boolean zSkipWhitespaceAndCommas = skipWhitespaceAndCommas(gVar);
                String token3 = readToken(gVar);
                if (token3 == null) {
                    if (gVar.e()) {
                        list.add(new Challenge(token2, (Map<String, String>) Collections.emptyMap()));
                        return;
                    }
                    return;
                }
                int iSkipAll = skipAll(gVar, (byte) 61);
                boolean zSkipWhitespaceAndCommas2 = skipWhitespaceAndCommas(gVar);
                if (zSkipWhitespaceAndCommas || !(zSkipWhitespaceAndCommas2 || gVar.e())) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    int iSkipAll2 = skipAll(gVar, (byte) 61) + iSkipAll;
                    while (true) {
                        if (token3 == null) {
                            token3 = readToken(gVar);
                            if (skipWhitespaceAndCommas(gVar)) {
                                break;
                            }
                            iSkipAll2 = skipAll(gVar, (byte) 61);
                            if (iSkipAll2 == 0) {
                                break;
                            }
                            if (iSkipAll2 <= 1 || skipWhitespaceAndCommas(gVar)) {
                                return;
                            }
                            if (gVar.e() && gVar.g(0L) == 34) {
                                token = readQuotedString(gVar);
                            } else {
                                token = readToken(gVar);
                            }
                            if (token != null || ((String) linkedHashMap.put(token3, token)) != null) {
                                return;
                            }
                            if (skipWhitespaceAndCommas(gVar) && !gVar.e()) {
                                return;
                            } else {
                                token3 = null;
                            }
                        } else {
                            if (iSkipAll2 == 0) {
                                break;
                                break;
                            }
                            if (iSkipAll2 <= 1) {
                                return;
                            }
                            if (gVar.e()) {
                                token = readToken(gVar);
                            } else {
                                token = readToken(gVar);
                            }
                            if (token != null) {
                                return;
                            }
                            if (skipWhitespaceAndCommas(gVar)) {
                            }
                            token3 = null;
                        }
                    }
                    list.add(new Challenge(token2, linkedHashMap));
                    token2 = token3;
                } else {
                    StringBuilder sbA = a.a(token3);
                    sbA.append(repeat('=', iSkipAll));
                    list.add(new Challenge(token2, (Map<String, String>) Collections.singletonMap(null, sbA.toString())));
                }
            }
        }
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < headers.size(); i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                g gVar = new g();
                gVar.a(headers.value(i));
                parseChallengeHeader(arrayList, gVar);
            }
        }
        return arrayList;
    }

    public static int parseSeconds(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static String readQuotedString(g gVar) {
        if (gVar.readByte() != 34) {
            throw new IllegalArgumentException();
        }
        g gVar2 = new g();
        while (true) {
            long jB = gVar.b(QUOTED_STRING_DELIMITERS);
            if (jB == -1) {
                return null;
            }
            if (gVar.g(jB) == 34) {
                gVar2.write(gVar, jB);
                gVar.readByte();
                return gVar2.o();
            }
            if (gVar.f2102c == jB + 1) {
                return null;
            }
            gVar2.write(gVar, jB);
            gVar.readByte();
            gVar2.write(gVar, 1L);
        }
    }

    public static String readToken(g gVar) {
        try {
            long jB = gVar.b(TOKEN_DELIMITERS);
            if (jB == -1) {
                jB = gVar.f2102c;
            }
            if (jB != 0) {
                return gVar.h(jB);
            }
            return null;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> all = Cookie.parseAll(httpUrl, headers);
        if (all.isEmpty()) {
            return;
        }
        cookieJar.saveFromResponse(httpUrl, all);
    }

    public static String repeat(char c2, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c2);
        return new String(cArr);
    }

    public static int skipAll(g gVar, byte b2) {
        int i = 0;
        while (!gVar.e() && gVar.g(0L) == b2) {
            i++;
            gVar.readByte();
        }
        return i;
    }

    public static int skipUntil(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int skipWhitespace(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    public static boolean skipWhitespaceAndCommas(g gVar) {
        boolean z = false;
        while (!gVar.e()) {
            byte bG = gVar.g(0L);
            if (bG != 44) {
                if (bG != 32 && bG != 9) {
                    break;
                }
                gVar.readByte();
            } else {
                gVar.readByte();
                z = true;
            }
        }
        return z;
    }

    public static long stringToLong(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response.headers())) {
            if (!Util.equal(headers.values(str), request.headers(str))) {
                return false;
            }
        }
        return true;
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains("*");
    }

    public static Set<String> varyFields(Headers headers) {
        Set<String> setEmptySet = Collections.emptySet();
        int size = headers.size();
        Set<String> treeSet = setEmptySet;
        for (int i = 0; i < size; i++) {
            if (com.google.common.net.HttpHeaders.VARY.equalsIgnoreCase(headers.name(i))) {
                String strValue = headers.value(i);
                if (treeSet.isEmpty()) {
                    treeSet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : strValue.split(",")) {
                    treeSet.add(str.trim());
                }
            }
        }
        return treeSet;
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set<String> setVaryFields = varyFields(headers2);
        if (setVaryFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            if (setVaryFields.contains(strName)) {
                builder.add(strName, headers.value(i));
            }
        }
        return builder.build();
    }
}
