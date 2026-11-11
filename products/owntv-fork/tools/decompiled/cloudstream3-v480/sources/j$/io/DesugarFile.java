package j$.io;

import j$.nio.file.FileSystems;
import j$.nio.file.Path;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class DesugarFile {
    public static Path toPath(File file) {
        return FileSystems.getDefault().getPath(file.getPath(), new String[0]);
    }
}
