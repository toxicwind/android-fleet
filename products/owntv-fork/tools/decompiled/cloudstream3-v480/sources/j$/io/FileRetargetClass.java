package j$.io;

import j$.nio.file.Path;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class FileRetargetClass {
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Path toPath(File file) {
        return file instanceof FileRetargetInterface ? ((FileRetargetInterface) file).toPath() : DesugarFile.toPath(file);
    }
}
