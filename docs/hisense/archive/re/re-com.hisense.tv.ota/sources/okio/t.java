package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f275a = Logger.getLogger(t.class.getName());

    private t() {
    }

    public static A a(File file) {
        if (file != null) {
            return g(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static A b() {
        return new r();
    }

    public static i c(A a2) {
        return new v(a2);
    }

    public static j d(B b) {
        return new x(b);
    }

    static boolean e(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static A f(File file) {
        if (file != null) {
            return g(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static A g(OutputStream outputStream) {
        return h(outputStream, new D());
    }

    private static A h(OutputStream outputStream, D d) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (d != null) {
            return new p(d, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static A i(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        C0069d c0069dN = n(socket);
        return c0069dN.r(h(socket.getOutputStream(), c0069dN));
    }

    public static B j(File file) {
        if (file != null) {
            return k(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static B k(InputStream inputStream) {
        return l(inputStream, new D());
    }

    private static B l(InputStream inputStream, D d) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (d != null) {
            return new q(d, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static B m(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        C0069d c0069dN = n(socket);
        return c0069dN.s(l(socket.getInputStream(), c0069dN));
    }

    private static C0069d n(Socket socket) {
        return new s(socket);
    }
}
