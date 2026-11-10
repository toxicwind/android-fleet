package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class CoreComponentFactory extends android.app.AppComponentFactory {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface CompatWrapped {
        Object getWrapper();
    }

    public static <T> T checkCompatWrapper(T t) {
        T t2;
        return (!(t instanceof CompatWrapped) || (t2 = (T) ((CompatWrapped) t).getWrapper()) == null) ? t : t2;
    }

    @Override // android.app.AppComponentFactory
    @NonNull
    public Activity instantiateActivity(@NonNull ClassLoader cl, @NonNull String className, @Nullable Intent intent) {
        return (Activity) checkCompatWrapper(super.instantiateActivity(cl, className, intent));
    }

    @Override // android.app.AppComponentFactory
    @NonNull
    public Application instantiateApplication(@NonNull ClassLoader cl, @NonNull String className) {
        return (Application) checkCompatWrapper(super.instantiateApplication(cl, className));
    }

    @Override // android.app.AppComponentFactory
    @NonNull
    public ContentProvider instantiateProvider(@NonNull ClassLoader cl, @NonNull String className) {
        return (ContentProvider) checkCompatWrapper(super.instantiateProvider(cl, className));
    }

    @Override // android.app.AppComponentFactory
    @NonNull
    public BroadcastReceiver instantiateReceiver(@NonNull ClassLoader cl, @NonNull String className, @Nullable Intent intent) {
        return (BroadcastReceiver) checkCompatWrapper(super.instantiateReceiver(cl, className, intent));
    }

    @Override // android.app.AppComponentFactory
    @NonNull
    public Service instantiateService(@NonNull ClassLoader cl, @NonNull String className, @Nullable Intent intent) {
        return (Service) checkCompatWrapper(super.instantiateService(cl, className, intent));
    }
}
