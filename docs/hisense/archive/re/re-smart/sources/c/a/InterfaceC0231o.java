package c.a;

import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: renamed from: c.a.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0231o extends InterfaceC0233q, InterfaceC0239x {

    /* JADX INFO: renamed from: c.a.o$a */
    public static final class a implements InterfaceC0231o {
        @Override // c.a.InterfaceC0233q
        public OutputStream a(OutputStream outputStream) {
            return new GZIPOutputStream(outputStream);
        }

        @Override // c.a.InterfaceC0233q, c.a.InterfaceC0239x
        public String a() {
            return FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING;
        }

        @Override // c.a.InterfaceC0239x
        public InputStream a(InputStream inputStream) {
            return new GZIPInputStream(inputStream);
        }
    }

    /* JADX INFO: renamed from: c.a.o$b */
    public static final class b implements InterfaceC0231o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final InterfaceC0231o f1883a = new b();

        @Override // c.a.InterfaceC0239x
        public InputStream a(InputStream inputStream) {
            return inputStream;
        }

        @Override // c.a.InterfaceC0233q
        public OutputStream a(OutputStream outputStream) {
            return outputStream;
        }

        @Override // c.a.InterfaceC0233q, c.a.InterfaceC0239x
        public String a() {
            return "identity";
        }
    }
}
