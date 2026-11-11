package okio;

import j$.nio.file.Files;
import j$.nio.file.OpenOption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.DefaultSocket;
import okio.internal.PipeSocket;
import okio.internal.ResourceFileSystem;
import okio.internal.SocketAsyncTimeout;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\b¢\u0006\u0004\b\u0002\u0010\t\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\b¢\u0006\u0004\b\u0006\u0010\n\u001a\u0013\u0010\u000e\u001a\u00020\u000b*\u00020\bH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001d\u0010\u0002\u001a\u00020\u0001*\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0002\u0010\u0017\u001a\u0011\u0010\u0018\u001a\u00020\u0001*\u00020\u0014¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0014¢\u0006\u0004\b\u0006\u0010\u001a\u001a%\u0010\u0002\u001a\u00020\u0001*\u00020\u001b2\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u0011\"\u00020\u001c¢\u0006\u0004\b\u0002\u0010\u001e\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u001b2\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u0011\"\u00020\u001c¢\u0006\u0004\b\u0006\u0010\u001f\u001a\u0019\u0010#\u001a\u00020\"*\u00020\u00012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b#\u0010$\u001a\u0019\u0010&\u001a\u00020%*\u00020\u00052\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b&\u0010'\u001a\u0019\u0010+\u001a\u00020**\u00020\u00012\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b+\u0010,\u001a\u0019\u0010.\u001a\u00020-*\u00020\u00052\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b.\u0010/\u001a\u0019\u0010+\u001a\u00020**\u00020\u00012\u0006\u00101\u001a\u000200¢\u0006\u0004\b+\u00102\u001a\u0019\u0010.\u001a\u00020-*\u00020\u00052\u0006\u00101\u001a\u000200¢\u0006\u0004\b.\u00103\u001a\u0011\u00106\u001a\u000205*\u000204¢\u0006\u0004\b6\u00107¨\u00068"}, d2 = {"Ljava/io/OutputStream;", "Lokio/Sink;", "sink", "(Ljava/io/OutputStream;)Lokio/Sink;", "Ljava/io/InputStream;", "Lokio/Source;", "source", "(Ljava/io/InputStream;)Lokio/Source;", "Ljava/net/Socket;", "(Ljava/net/Socket;)Lokio/Sink;", "(Ljava/net/Socket;)Lokio/Source;", "Lokio/Socket;", "socket", "(Ljava/net/Socket;)Lokio/Socket;", "asOkioSocket", "", "maxBufferSize", "", "inMemorySocketPair", "(J)[Lokio/Socket;", "Ljava/io/File;", "", "append", "(Ljava/io/File;Z)Lokio/Sink;", "appendingSink", "(Ljava/io/File;)Lokio/Sink;", "(Ljava/io/File;)Lokio/Source;", "j$/nio/file/Path", "j$/nio/file/OpenOption", "options", "(Lj$/nio/file/Path;[Lj$/nio/file/OpenOption;)Lokio/Sink;", "(Lj$/nio/file/Path;[Lj$/nio/file/OpenOption;)Lokio/Source;", "Ljavax/crypto/Cipher;", "cipher", "Lokio/CipherSink;", "cipherSink", "(Lokio/Sink;Ljavax/crypto/Cipher;)Lokio/CipherSink;", "Lokio/CipherSource;", "cipherSource", "(Lokio/Source;Ljavax/crypto/Cipher;)Lokio/CipherSource;", "Ljavax/crypto/Mac;", "mac", "Lokio/HashingSink;", "hashingSink", "(Lokio/Sink;Ljavax/crypto/Mac;)Lokio/HashingSink;", "Lokio/HashingSource;", "hashingSource", "(Lokio/Source;Ljavax/crypto/Mac;)Lokio/HashingSource;", "Ljava/security/MessageDigest;", "digest", "(Lokio/Sink;Ljava/security/MessageDigest;)Lokio/HashingSink;", "(Lokio/Source;Ljava/security/MessageDigest;)Lokio/HashingSource;", "Ljava/lang/ClassLoader;", "Lokio/FileSystem;", "asResourceFileSystem", "(Ljava/lang/ClassLoader;)Lokio/FileSystem;", "okio"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "okio/Okio")
final /* synthetic */ class Okio__JvmOkioKt {
    public static final Sink sink(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return sink$default(file, false, 1, null);
    }

    public static final Sink sink(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        return new OutputStreamSink(outputStream, new Timeout());
    }

    public static final Source source(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        return new InputStreamSource(inputStream, new Timeout());
    }

    public static final Sink sink(java.net.Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        OutputStream outputStream = socket.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
        return socketAsyncTimeout.sink(new OutputStreamSink(outputStream, socketAsyncTimeout));
    }

    public static final Source source(java.net.Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        InputStream inputStream = socket.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return socketAsyncTimeout.source(new InputStreamSource(inputStream, socketAsyncTimeout));
    }

    public static final Socket socket(java.net.Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        return new DefaultSocket(socket);
    }

    public static final Socket[] inMemorySocketPair(long j) {
        Pipe pipe = new Pipe(j);
        Pipe pipe2 = new Pipe(j);
        return new Socket[]{new PipeSocket(pipe, pipe2), new PipeSocket(pipe2, pipe)};
    }

    public static final Sink sink(File file, boolean z) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, z));
    }

    public static /* synthetic */ Sink sink$default(File file, boolean z, int i, Object obj) throws FileNotFoundException {
        if ((i & 1) != 0) {
            z = false;
        }
        return Okio.sink(file, z);
    }

    public static final Sink appendingSink(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, true));
    }

    public static final Source source(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new InputStreamSource(new FileInputStream(file), Timeout.NONE);
    }

    public static final Sink sink(j$.nio.file.Path path, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        return Okio.sink(outputStreamNewOutputStream);
    }

    public static final Source source(j$.nio.file.Path path, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        InputStream inputStreamNewInputStream = Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        return Okio.source(inputStreamNewInputStream);
    }

    public static final CipherSink cipherSink(Sink sink, Cipher cipher) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        return new CipherSink(Okio.buffer(sink), cipher);
    }

    public static final CipherSource cipherSource(Source source, Cipher cipher) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        return new CipherSource(Okio.buffer(source), cipher);
    }

    public static final HashingSink hashingSink(Sink sink, Mac mac) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(mac, "mac");
        return new HashingSink(sink, mac);
    }

    public static final HashingSource hashingSource(Source source, Mac mac) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(mac, "mac");
        return new HashingSource(source, mac);
    }

    public static final HashingSink hashingSink(Sink sink, MessageDigest digest) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new HashingSink(sink, digest);
    }

    public static final HashingSource hashingSource(Source source, MessageDigest digest) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new HashingSource(source, digest);
    }

    public static final FileSystem asResourceFileSystem(ClassLoader classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "<this>");
        return new ResourceFileSystem(classLoader, true, null, 4, null);
    }
}
