package j$.io;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class InputStreamRetargetClass {
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ long transferTo(InputStream inputStream, OutputStream outputStream) {
        return inputStream instanceof InputStreamRetargetInterface ? ((InputStreamRetargetInterface) inputStream).transferTo(outputStream) : DesugarInputStream.transferTo(inputStream, outputStream);
    }
}
