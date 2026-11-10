package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class InputMerger {
    public static final String TAG = Logger.tagWithPrefix("InputMerger");

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMerger fromClassName(String className) {
        try {
            return (InputMerger) Class.forName(className).newInstance();
        } catch (Exception e2) {
            Logger.get().error(TAG, a.a("Trouble instantiating + ", className), e2);
            return null;
        }
    }

    @NonNull
    public abstract Data merge(@NonNull List<Data> inputs);
}
