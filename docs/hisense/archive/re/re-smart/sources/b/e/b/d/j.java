package b.e.b.d;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.util.Function;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ FirebaseFirestore f567a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final /* synthetic */ Executor f568b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final /* synthetic */ Transaction.Function f569c;

    public /* synthetic */ j(FirebaseFirestore firebaseFirestore, Executor executor, Transaction.Function function) {
        this.f567a = firebaseFirestore;
        this.f568b = executor;
        this.f569c = function;
    }

    @Override // com.google.firebase.firestore.util.Function
    public final Object apply(Object obj) {
        return this.f567a.a(this.f568b, this.f569c, (com.google.firebase.firestore.core.Transaction) obj);
    }
}
