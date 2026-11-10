package com.hisense.uifac.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.hisense.serialInfoTest.util.HexData;
import com.mediatek.twoworlds.tv.common.MtkTvTISMsgBase;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TcpServer extends Thread {
    private static int packIndex;
    private ServerSocket serverSocket;
    private boolean isTCPServerOpen = false;
    private ArrayList<ServerSocketThread> clientList = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    public int byteToInt(byte b) {
        return b & 255;
    }

    public TcpServer(Context context) {
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        Log.d("Fac_TcpServer", "start()");
        this.isTCPServerOpen = true;
        super.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            Log.d("Fac_TcpServer", "run()");
            this.serverSocket = new ServerSocket(8099);
            while (this.isTCPServerOpen) {
                Socket socketAccept = this.serverSocket.accept();
                if (socketAccept != null) {
                    Log.d("Fac_TcpServer", "run: new client：" + socketAccept);
                    new ServerSocketThread(socketAccept);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ServerSocketThread extends Thread {
        byte[] bufferTotal;
        private InputStream is;
        int length;
        private Socket socket;
        int cmd = 0;
        int dataLen = 0;
        byte[] buffer = new byte[131072];
        int lengthTotal = 0;
        int lengthRead = 0;

        ServerSocketThread(Socket socket) {
            this.is = null;
            this.socket = socket;
            try {
                socket.setSoTimeout(10000);
                this.is = socket.getInputStream();
                new PrintWriter(socket.getOutputStream(), true);
                start();
            } catch (IOException e) {
                Log.d("Fac_TcpServer", "fail：" + e.getMessage());
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            TcpServer.this.clientList.add(this);
            while (TcpServer.this.isTCPServerOpen && !this.socket.isClosed() && !this.socket.isInputShutdown()) {
                try {
                    int i = this.is.read(this.buffer);
                    this.length = i;
                    if (i != -1) {
                        Log.d("Fac_TcpServer", "receive data:" + HexData.bytesToHexString(this.buffer));
                        Log.d("Fac_TcpServer", "receive length = " + this.length);
                        if (this.buffer[0] == 72 && this.buffer[1] == 83 && this.buffer[2] == 80 && this.buffer[3] == 67) {
                            this.dataLen = (TcpServer.this.byteToInt(this.buffer[4]) * 256 * 256 * 256) + (TcpServer.this.byteToInt(this.buffer[5]) * 256 * 256) + (TcpServer.this.byteToInt(this.buffer[6]) * 256) + TcpServer.this.byteToInt(this.buffer[7]);
                            Log.d("Fac_TcpServer", "dataLen = " + this.dataLen);
                            int i2 = this.dataLen + 8;
                            this.lengthTotal = i2;
                            this.bufferTotal = new byte[i2];
                            this.dataLen = ((this.dataLen - 4) - 1) - 4;
                            this.lengthRead = 0;
                            this.cmd = (this.buffer[8] * MtkTvTISMsgBase.MTK_TIS_VALUE_FALSE * 256 * 256) + (this.buffer[9] * MtkTvTISMsgBase.MTK_TIS_VALUE_FALSE * 256) + (this.buffer[10] * MtkTvTISMsgBase.MTK_TIS_VALUE_FALSE) + this.buffer[11];
                        }
                        if (this.lengthTotal >= this.lengthRead + this.length) {
                            System.arraycopy(this.buffer, 0, this.bufferTotal, this.lengthRead, this.length);
                        } else {
                            Log.d("Fac_TcpServer", "receive data too long error");
                            send(TcpServer.this.getSendByte(this.cmd, 2));
                        }
                        this.lengthRead += this.length;
                        Log.d("Fac_TcpServer", "lengthTotal = " + this.lengthTotal);
                        Log.d("Fac_TcpServer", "lengthRead = " + this.lengthRead);
                        if (this.lengthRead == this.lengthTotal) {
                            Log.d("Fac_TcpServer", "buffer total:" + HexData.bytesToHexString(this.bufferTotal));
                            if (this.cmd == -1559232512) {
                                Log.d("Fac_TcpServer", "buffer[12] = " + ((int) this.bufferTotal[12]));
                                if (this.bufferTotal[12] == 0) {
                                    int unused = TcpServer.packIndex = 0;
                                    send(TcpServer.this.getSendByte(this.cmd, TcpServer.this.deleteFile() ? 1 : 0));
                                } else if (this.bufferTotal[12] != -1) {
                                    if (TcpServer.packIndex < this.bufferTotal[12]) {
                                        byte[] bArrCopyOfRange = Arrays.copyOfRange(this.bufferTotal, 13, this.lengthTotal - 4);
                                        Log.d("Fac_TcpServer", "dataBuf.length = " + bArrCopyOfRange.length);
                                        Log.d("Fac_TcpServer", "dataLen = " + this.dataLen);
                                        Log.d("Fac_TcpServer", "dataBuf = " + HexData.bytesToHexString(bArrCopyOfRange));
                                        boolean zWriteFileWithByte = TcpServer.this.writeFileWithByte(bArrCopyOfRange, bArrCopyOfRange.length);
                                        if (zWriteFileWithByte) {
                                            int unused2 = TcpServer.packIndex = this.bufferTotal[12];
                                        }
                                        send(TcpServer.this.getSendByte(this.cmd, zWriteFileWithByte ? 1 : 0));
                                    }
                                } else {
                                    String strBytesToHexString = HexData.bytesToHexString(Arrays.copyOfRange(this.bufferTotal, 13, 45));
                                    String strConvertHexToASC = TcpServer.convertHexToASC(strBytesToHexString);
                                    Log.d("Fac_TcpServer", "md5Hex = " + strBytesToHexString);
                                    Log.d("Fac_TcpServer", "md5Str = " + strConvertHexToASC);
                                    String md5 = MD5Util.getMD5("/data/vendor/3rd_rw/factory/demura.bin");
                                    Log.d("Fac_TcpServer", "md5File = " + md5);
                                    boolean zEqualsIgnoreCase = (TextUtils.isEmpty(md5) || TextUtils.isEmpty(strConvertHexToASC)) ? false : md5.equalsIgnoreCase(strConvertHexToASC);
                                    Log.d("Fac_TcpServer", "isMatch = " + zEqualsIgnoreCase);
                                    send(TcpServer.this.getSendByte(this.cmd, zEqualsIgnoreCase ? 1 : 0));
                                }
                            } else {
                                send(TcpServer.this.getSendByte(this.cmd, 3));
                            }
                        }
                    } else {
                        Log.d("Fac_TcpServer", "client disconnect，close thread: " + this);
                        break;
                    }
                } catch (SocketTimeoutException unused3) {
                    Log.d("Fac_TcpServer", "long time receive nothing ,close thread:" + this);
                    break;
                } catch (IOException e) {
                    Log.d("Fac_TcpServer", "fail：receive exception:" + e.getMessage());
                    e.printStackTrace();
                }
            }
            try {
                this.socket.close();
                TcpServer.this.clientList.remove(this);
            } catch (IOException e2) {
                Log.d("Fac_TcpServer", "fail：close connection exception:" + e2.getMessage());
                e2.printStackTrace();
            }
        }

        public void send(final byte[] bArr) {
            Socket socket = this.socket;
            if (socket == null || socket.isClosed()) {
                return;
            }
            new Thread(new Runnable() { // from class: com.hisense.uifac.util.TcpServer.ServerSocketThread.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ServerSocketThread.this.socket.getOutputStream().write(bArr);
                        ServerSocketThread.this.socket.getOutputStream().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public byte[] getSendByte(int i, int i2) {
        byte[] bArr = new byte[17];
        bArr[0] = 72;
        bArr[1] = 83;
        bArr[2] = 80;
        bArr[3] = 67;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 9;
        bArr[8] = (byte) ((i >> 24) & 255);
        bArr[9] = (byte) ((i >> 16) & 255);
        bArr[10] = (byte) ((i >> 8) & 255);
        bArr[11] = (byte) (i & 255);
        bArr[12] = (byte) i2;
        int iByteToInt = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            iByteToInt += byteToInt(bArr[i3]);
        }
        bArr[13] = (byte) ((iByteToInt >> 24) & 255);
        bArr[14] = (byte) ((iByteToInt >> 16) & 255);
        bArr[15] = (byte) ((iByteToInt >> 8) & 255);
        bArr[16] = (byte) (iByteToInt & 255);
        Log.d("Fac_TcpServer", "send:" + HexData.bytesToHexString(bArr));
        return bArr;
    }

    public boolean deleteFile() {
        File file = new File("/data/vendor/3rd_rw/factory/demura.bin");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:? A[SYNTHETIC] */
    public boolean writeFileWithByte(byte[] bArr, int i) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        File file = new File("/data/vendor/3rd_rw/factory/demura.bin");
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream2);
                try {
                    bufferedOutputStream2.write(bArr, 0, i);
                    bufferedOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bufferedOutputStream2.close();
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    fileOutputStream = fileOutputStream2;
                    bufferedOutputStream = bufferedOutputStream2;
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                                throw th;
                            } catch (Exception e7) {
                                e7.printStackTrace();
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    fileOutputStream = fileOutputStream2;
                    bufferedOutputStream = bufferedOutputStream2;
                    th = th2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                        throw th;
                    }
                    throw th;
                }
            } catch (Exception e8) {
                e = e8;
                bufferedOutputStream = null;
                fileOutputStream = fileOutputStream2;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception e9) {
            e = e9;
            bufferedOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
        }
    }

    public static String convertHexToASC(String str) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (i < str.length() - 1) {
            int i2 = i + 2;
            int i3 = Integer.parseInt(str.substring(i, i2), 16);
            sb.append((char) i3);
            sb2.append(i3);
            i = i2;
        }
        return sb.toString();
    }
}
