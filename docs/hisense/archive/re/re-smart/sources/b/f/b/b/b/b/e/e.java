package b.f.b.b.b.b.e;

import com.google.firebase.storage.UploadTask;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static /* synthetic */ void a(String str, boolean z, File file, UploadTask.TaskSnapshot taskSnapshot) {
        b.a.a.a.a.c("upload successed,filename=", str, "FirebaseStorageUtils");
        if (z) {
            file.deleteOnExit();
        }
    }
}
