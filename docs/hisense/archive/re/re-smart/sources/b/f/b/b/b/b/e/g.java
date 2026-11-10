package b.f.b.b.b.b.e;

import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/* JADX INFO: loaded from: classes2.dex */
public class g implements OnSuccessListener<DocumentReference> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f735a;

    public g(h hVar, String str) {
        this.f735a = str;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public void onSuccess(DocumentReference documentReference) {
        String str = h.f737b;
        StringBuilder sbA = b.a.a.a.a.a("add [");
        sbA.append(this.f735a);
        sbA.append("] success,docid=");
        sbA.append(documentReference.getId());
        Log.d(str, sbA.toString());
    }
}
