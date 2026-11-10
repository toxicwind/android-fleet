package androidx.core.net;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketImpl;

/* JADX INFO: loaded from: classes.dex */
public class DatagramSocketWrapper extends Socket {

    private static class DatagramSocketImplWrapper extends SocketImpl {
        public DatagramSocketImplWrapper(DatagramSocket socket, FileDescriptor fd) {
            ((SocketImpl) this).localport = socket.getLocalPort();
            ((SocketImpl) this).fd = fd;
        }

        @Override // java.net.SocketImpl
        public void accept(SocketImpl newSocket) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public int available() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void bind(InetAddress address, int port) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void close() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void connect(String host, int port) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void create(boolean isStreaming) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public InputStream getInputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public Object getOption(int optID) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public OutputStream getOutputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void listen(int backlog) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void sendUrgentData(int value) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public void setOption(int optID, Object val) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void connect(InetAddress address, int port) {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        public void connect(SocketAddress remoteAddr, int timeout) {
            throw new UnsupportedOperationException();
        }
    }

    public DatagramSocketWrapper(DatagramSocket socket, FileDescriptor fd) {
        super(new DatagramSocketImplWrapper(socket, fd));
    }
}
