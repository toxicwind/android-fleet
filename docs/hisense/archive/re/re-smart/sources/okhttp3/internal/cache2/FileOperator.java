package okhttp3.internal.cache2;

import d.g;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes2.dex */
public final class FileOperator {
    public final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void read(long j, g gVar, long j2) throws IOException {
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferTo = this.fileChannel.transferTo(j, j2, gVar);
            j += jTransferTo;
            j2 -= jTransferTo;
        }
    }

    public void write(long j, g gVar, long j2) throws IOException {
        if (j2 < 0 || j2 > gVar.f2102c) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferFrom = this.fileChannel.transferFrom(gVar, j, j2);
            j += jTransferFrom;
            j2 -= jTransferFrom;
        }
    }
}
