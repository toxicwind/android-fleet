package d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f2129a = Logger.getLogger(t.class.getName());

    public static i a(C c2) {
        return new x(c2);
    }

    public static B b(File file) {
        if (file != null) {
            return a(new FileOutputStream(file), new E());
        }
        throw new IllegalArgumentException("file == null");
    }

    public static C c(File file) {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static h a(B b2) {
        return new v(b2);
    }

    public static B a(OutputStream outputStream, E e2) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (e2 != null) {
            return new p(e2, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static C b(Socket socket) {
        if (socket != null) {
            if (socket.getInputStream() != null) {
                s sVar = new s(socket);
                return new C0243b(sVar, a(socket.getInputStream(), sVar));
            }
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static B a(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getOutputStream() != null) {
                s sVar = new s(socket);
                return new C0242a(sVar, a(socket.getOutputStream(), sVar));
            }
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static C a(InputStream inputStream) {
        return a(inputStream, new E());
    }

    public static C a(InputStream inputStream, E e2) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (e2 != null) {
            return new q(e2, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static B a(File file) {
        if (file != null) {
            return a(new FileOutputStream(file, true), new E());
        }
        throw new IllegalArgumentException("file == null");
    }

    public static B a() {
        return new r();
    }

    public static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
