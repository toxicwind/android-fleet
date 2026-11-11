package j$.io;

import j$.util.Spliterators;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.io.BufferedReader;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class DesugarBufferedReader {
    public static Stream lines(BufferedReader bufferedReader) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DesugarBufferedReaderLinesIterator(bufferedReader), 272), false);
    }
}
