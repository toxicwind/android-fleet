package org.mozilla.javascript.commonjs.module.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class UrlModuleSourceProvider extends ModuleSourceProviderBase {
    private static final long serialVersionUID = 1;
    private final Iterable<URI> fallbackUris;
    private final Iterable<URI> privilegedUris;
    private final UrlConnectionExpiryCalculator urlConnectionExpiryCalculator;
    private final UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider;

    protected void onFailedClosingUrlConnection(URLConnection uRLConnection, IOException iOException) {
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2) {
        this(iterable, iterable2, new DefaultUrlConnectionExpiryCalculator(), null);
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator, UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider) {
        this.privilegedUris = iterable;
        this.fallbackUris = iterable2;
        this.urlConnectionExpiryCalculator = urlConnectionExpiryCalculator;
        this.urlConnectionSecurityDomainProvider = urlConnectionSecurityDomainProvider;
    }

    @Override // org.mozilla.javascript.commonjs.module.provider.ModuleSourceProviderBase
    protected ModuleSource loadFromPrivilegedLocations(String str, Object obj) throws URISyntaxException, IOException {
        return loadFromPathList(str, obj, this.privilegedUris);
    }

    @Override // org.mozilla.javascript.commonjs.module.provider.ModuleSourceProviderBase
    protected ModuleSource loadFromFallbackLocations(String str, Object obj) throws URISyntaxException, IOException {
        return loadFromPathList(str, obj, this.fallbackUris);
    }

    private ModuleSource loadFromPathList(String str, Object obj, Iterable<URI> iterable) throws URISyntaxException, IOException {
        if (iterable == null) {
            return null;
        }
        for (URI uri : iterable) {
            ModuleSource moduleSourceLoadFromUri = loadFromUri(uri.resolve(str), uri, obj);
            if (moduleSourceLoadFromUri != null) {
                return moduleSourceLoadFromUri;
            }
        }
        return null;
    }

    @Override // org.mozilla.javascript.commonjs.module.provider.ModuleSourceProviderBase
    protected ModuleSource loadFromUri(URI uri, URI uri2, Object obj) throws URISyntaxException, IOException {
        ModuleSource moduleSourceLoadFromActualUri = loadFromActualUri(new URI(String.valueOf(uri) + ".js"), uri2, obj);
        return moduleSourceLoadFromActualUri != null ? moduleSourceLoadFromActualUri : loadFromActualUri(uri, uri2, obj);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0032  */
    protected ModuleSource loadFromActualUri(URI uri, URI uri2, Object obj) throws IOException {
        URI uri3;
        URLValidator uRLValidator;
        URL url = new URL(uri2 == null ? null : uri2.toURL(), uri.toString());
        long jCurrentTimeMillis = System.currentTimeMillis();
        URLConnection uRLConnectionOpenUrlConnection = openUrlConnection(url);
        try {
            if (obj instanceof URLValidator) {
                uRLValidator = (URLValidator) obj;
                uri3 = uri;
                if (!uRLValidator.appliesTo(uri3)) {
                }
                if (uRLValidator != null) {
                    uRLValidator.applyConditionals(uRLConnectionOpenUrlConnection);
                }
                uRLConnectionOpenUrlConnection.connect();
                if (uRLValidator == null && !uRLValidator.updateValidator(uRLConnectionOpenUrlConnection, jCurrentTimeMillis, this.urlConnectionExpiryCalculator)) {
                    close(uRLConnectionOpenUrlConnection);
                    return NOT_MODIFIED;
                }
                return new ModuleSource(getReader(uRLConnectionOpenUrlConnection), getSecurityDomain(uRLConnectionOpenUrlConnection), uri, uri2, new URLValidator(uri3, uRLConnectionOpenUrlConnection, jCurrentTimeMillis, this.urlConnectionExpiryCalculator));
            }
            uri3 = uri;
            uRLConnectionOpenUrlConnection.connect();
            if (uRLValidator == null) {
            }
            return new ModuleSource(getReader(uRLConnectionOpenUrlConnection), getSecurityDomain(uRLConnectionOpenUrlConnection), uri, uri2, new URLValidator(uri3, uRLConnectionOpenUrlConnection, jCurrentTimeMillis, this.urlConnectionExpiryCalculator));
        } catch (FileNotFoundException unused) {
            return null;
        } catch (IOException e) {
            close(uRLConnectionOpenUrlConnection);
            throw e;
        } catch (RuntimeException e2) {
            close(uRLConnectionOpenUrlConnection);
            throw e2;
        }
        uRLValidator = null;
        if (uRLValidator != null) {
            uRLValidator.applyConditionals(uRLConnectionOpenUrlConnection);
        }
    }

    private Reader getReader(URLConnection uRLConnection) throws IOException {
        return new InputStreamReader(uRLConnection.getInputStream(), getCharacterEncoding(uRLConnection));
    }

    protected String getCharacterEncoding(URLConnection uRLConnection) {
        ParsedContentType parsedContentType = new ParsedContentType(uRLConnection.getContentType());
        String encoding = parsedContentType.getEncoding();
        if (encoding != null) {
            return encoding;
        }
        String contentType = parsedContentType.getContentType();
        if (contentType != null && contentType.startsWith("text/")) {
            return "8859_1";
        }
        return "utf-8";
    }

    private Object getSecurityDomain(URLConnection uRLConnection) {
        UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider = this.urlConnectionSecurityDomainProvider;
        if (urlConnectionSecurityDomainProvider == null) {
            return null;
        }
        return urlConnectionSecurityDomainProvider.getSecurityDomain(uRLConnection);
    }

    private void close(URLConnection uRLConnection) {
        try {
            uRLConnection.getInputStream().close();
        } catch (IOException e) {
            onFailedClosingUrlConnection(uRLConnection, e);
        }
    }

    protected URLConnection openUrlConnection(URL url) throws IOException {
        return url.openConnection();
    }

    @Override // org.mozilla.javascript.commonjs.module.provider.ModuleSourceProviderBase
    protected boolean entityNeedsRevalidation(Object obj) {
        return !(obj instanceof URLValidator) || ((URLValidator) obj).entityNeedsRevalidation();
    }

    private static class URLValidator implements Serializable {
        private static final long serialVersionUID = 1;
        private final String entityTags;
        private long expiry;
        private final long lastModified;
        private final URI uri;

        public URLValidator(URI uri, URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            this.uri = uri;
            this.lastModified = uRLConnection.getLastModified();
            this.entityTags = getEntityTags(uRLConnection);
            this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
        }

        boolean updateValidator(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) throws IOException {
            boolean zIsResourceChanged = isResourceChanged(uRLConnection);
            if (!zIsResourceChanged) {
                this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
            }
            return zIsResourceChanged;
        }

        private boolean isResourceChanged(URLConnection uRLConnection) throws IOException {
            if (uRLConnection instanceof HttpURLConnection) {
                return ((HttpURLConnection) uRLConnection).getResponseCode() != 304;
            }
            return this.lastModified != uRLConnection.getLastModified();
        }

        private static long calculateExpiry(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            if ("no-cache".equals(uRLConnection.getHeaderField("Pragma"))) {
                return 0L;
            }
            String headerField = uRLConnection.getHeaderField("Cache-Control");
            if (headerField != null) {
                if (headerField.indexOf("no-cache") != -1) {
                    return 0L;
                }
                int maxAge = getMaxAge(headerField);
                if (-1 != maxAge) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    return (((long) maxAge) * 1000) + (jCurrentTimeMillis - (Math.max(Math.max(0L, jCurrentTimeMillis - uRLConnection.getDate()), ((long) uRLConnection.getHeaderFieldInt("Age", 0)) * 1000) + (jCurrentTimeMillis - j)));
                }
            }
            long headerFieldDate = uRLConnection.getHeaderFieldDate("Expires", -1L);
            if (headerFieldDate != -1) {
                return headerFieldDate;
            }
            if (urlConnectionExpiryCalculator == null) {
                return 0L;
            }
            return urlConnectionExpiryCalculator.calculateExpiry(uRLConnection);
        }

        private static int getMaxAge(String str) {
            int iIndexOf;
            String strSubstring;
            int iIndexOf2 = str.indexOf("max-age");
            if (iIndexOf2 == -1 || (iIndexOf = str.indexOf(61, iIndexOf2 + 7)) == -1) {
                return -1;
            }
            int i = iIndexOf + 1;
            int iIndexOf3 = str.indexOf(44, i);
            if (iIndexOf3 == -1) {
                strSubstring = str.substring(i);
            } else {
                strSubstring = str.substring(i, iIndexOf3);
            }
            try {
                return Integer.parseInt(strSubstring);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        private static String getEntityTags(URLConnection uRLConnection) {
            List<String> list = uRLConnection.getHeaderFields().get("ETag");
            if (list == null || list.isEmpty()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = list.iterator();
            sb.append(it.next());
            while (it.hasNext()) {
                sb.append(", ");
                sb.append(it.next());
            }
            return sb.toString();
        }

        boolean appliesTo(URI uri) {
            return this.uri.equals(uri);
        }

        void applyConditionals(URLConnection uRLConnection) {
            long j = this.lastModified;
            if (j != 0) {
                uRLConnection.setIfModifiedSince(j);
            }
            String str = this.entityTags;
            if (str == null || str.length() <= 0) {
                return;
            }
            uRLConnection.addRequestProperty("If-None-Match", this.entityTags);
        }

        boolean entityNeedsRevalidation() {
            return System.currentTimeMillis() > this.expiry;
        }
    }
}
