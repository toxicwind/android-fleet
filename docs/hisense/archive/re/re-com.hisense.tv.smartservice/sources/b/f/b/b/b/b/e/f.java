package b.f.b.b.b.b.e;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;

/* JADX INFO: loaded from: classes2.dex */
public class f implements OnFailureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f734a;

    public f(h hVar, String str) {
        this.f734a = str;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public void onFailure(@NonNull Exception exc) {
        String str = h.f737b;
        StringBuilder sbA = b.a.a.a.a.a("add [");
        sbA.append(this.f734a);
        sbA.append("] fail.");
        Log.d(str, sbA.toString());
    }
}
