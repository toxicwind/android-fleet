package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.content.Loader;
import b.a.a.a.a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public class LoaderManagerImpl extends LoaderManager {
    public static boolean DEBUG = false;
    public static final String TAG = "LoaderManager";

    @NonNull
    public final LifecycleOwner mLifecycleOwner;

    @NonNull
    public final LoaderViewModel mLoaderViewModel;

    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {

        @Nullable
        public final Bundle mArgs;
        public final int mId;
        public LifecycleOwner mLifecycleOwner;

        @NonNull
        public final Loader<D> mLoader;
        public LoaderObserver<D> mObserver;
        public Loader<D> mPriorLoader;

        public LoaderInfo(int i, @Nullable Bundle bundle, @NonNull Loader<D> loader, @Nullable Loader<D> loader2) {
            this.mId = i;
            this.mArgs = bundle;
            this.mLoader = loader;
            this.mPriorLoader = loader2;
            this.mLoader.registerListener(i, this);
        }

        @MainThread
        public Loader<D> destroy(boolean z) {
            if (LoaderManagerImpl.DEBUG) {
                a.d("  Destroying: ", this, LoaderManagerImpl.TAG);
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            this.mLoader.dump(a.a(str, "  "), fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                this.mObserver.dump(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        @NonNull
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        public boolean isCallbackWaitingForData() {
            LoaderObserver<D> loaderObserver;
            return (!hasActiveObservers() || (loaderObserver = this.mObserver) == null || loaderObserver.hasDeliveredData()) ? false : true;
        }

        public void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (lifecycleOwner == null || loaderObserver == null) {
                return;
            }
            super.removeObserver(loaderObserver);
            observe(lifecycleOwner, loaderObserver);
        }

        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                a.d("  Starting: ", this, LoaderManagerImpl.TAG);
            }
            this.mLoader.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                a.d("  Stopping: ", this, LoaderManagerImpl.TAG);
            }
            this.mLoader.stopLoading();
        }

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d2) {
            if (LoaderManagerImpl.DEBUG) {
                a.d("onLoadComplete: ", this, LoaderManagerImpl.TAG);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d2);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.w(LoaderManagerImpl.TAG, "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(@NonNull Observer<? super D> observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        @NonNull
        @MainThread
        public Loader<D> setCallback(@NonNull LifecycleOwner lifecycleOwner, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            LoaderObserver<D> loaderObserver = new LoaderObserver<>(this.mLoader, loaderCallbacks);
            observe(lifecycleOwner, loaderObserver);
            LoaderObserver<D> loaderObserver2 = this.mObserver;
            if (loaderObserver2 != null) {
                removeObserver(loaderObserver2);
            }
            this.mLifecycleOwner = lifecycleOwner;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(D d2) {
            super.setValue(d2);
            Loader<D> loader = this.mPriorLoader;
            if (loader != null) {
                loader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            DebugUtils.buildShortClassTag(this.mLoader, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    static class LoaderObserver<D> implements Observer<D> {

        @NonNull
        public final LoaderManager.LoaderCallbacks<D> mCallback;
        public boolean mDeliveredData = false;

        @NonNull
        public final Loader<D> mLoader;

        public LoaderObserver(@NonNull Loader<D> loader, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            this.mLoader = loader;
            this.mCallback = loaderCallbacks;
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }

        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable D d2) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sbA = a.a("  onLoadFinished in ");
                sbA.append(this.mLoader);
                sbA.append(": ");
                sbA.append(this.mLoader.dataToString(d2));
                Log.v(LoaderManagerImpl.TAG, sbA.toString());
            }
            this.mCallback.onLoadFinished(this.mLoader, d2);
            this.mDeliveredData = true;
        }

        @MainThread
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    StringBuilder sbA = a.a("  Resetting: ");
                    sbA.append(this.mLoader);
                    Log.v(LoaderManagerImpl.TAG, sbA.toString());
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }
    }

    static class LoaderViewModel extends ViewModel {
        public static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() { // from class: androidx.loader.app.LoaderManagerImpl.LoaderViewModel.1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            @NonNull
            public <T extends ViewModel> T create(@NonNull Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        public SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat<>(10);
        public boolean mCreatingLoader = false;

        @NonNull
        public static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo loaderInfoValueAt = this.mLoaders.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.keyAt(i));
                    printWriter.print(": ");
                    printWriter.println(loaderInfoValueAt.toString());
                    loaderInfoValueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        public <D> LoaderInfo<D> getLoader(int i) {
            return this.mLoaders.get(i);
        }

        public boolean hasRunningLoaders() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                if (this.mLoaders.valueAt(i).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                this.mLoaders.valueAt(i).markForRedelivery();
            }
        }

        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                this.mLoaders.valueAt(i).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void putLoader(int i, @NonNull LoaderInfo loaderInfo) {
            this.mLoaders.put(i, loaderInfo);
        }

        public void removeLoader(int i) {
            this.mLoaders.remove(i);
        }

        public void startCreatingLoader() {
            this.mCreatingLoader = true;
        }
    }

    public LoaderManagerImpl(@NonNull LifecycleOwner lifecycleOwner, @NonNull ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    @NonNull
    @MainThread
    private <D> Loader<D> createAndInstallLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        try {
            this.mLoaderViewModel.startCreatingLoader();
            Loader<D> loaderOnCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (loaderOnCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (loaderOnCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(loaderOnCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + loaderOnCreateLoader);
            }
            LoaderInfo loaderInfo = new LoaderInfo(i, bundle, loaderOnCreateLoader, loader);
            if (DEBUG) {
                Log.v(TAG, "  Created new loader " + loaderInfo);
            }
            this.mLoaderViewModel.putLoader(i, loaderInfo);
            this.mLoaderViewModel.finishCreatingLoader();
            return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
        } catch (Throwable th) {
            this.mLoaderViewModel.finishCreatingLoader();
            throw th;
        }
    }

    @Override // androidx.loader.app.LoaderManager
    @MainThread
    public void destroyLoader(int i) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        if (DEBUG) {
            Log.v(TAG, "destroyLoader in " + this + " of " + i);
        }
        LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
        if (loader != null) {
            loader.destroy(true);
            this.mLoaderViewModel.removeLoader(i);
        }
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.LoaderManager
    @Nullable
    public <D> Loader<D> getLoader(int i) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
        if (loader != null) {
            return loader.getLoader();
        }
        return null;
    }

    @Override // androidx.loader.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> initLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
        if (DEBUG) {
            Log.v(TAG, "initLoader in " + this + ": args=" + bundle);
        }
        if (loader == null) {
            return createAndInstallLoader(i, bundle, loaderCallbacks, null);
        }
        if (DEBUG) {
            a.d("  Re-using existing loader ", loader, TAG);
        }
        return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> restartLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        if (DEBUG) {
            Log.v(TAG, "restartLoader in " + this + ": args=" + bundle);
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i);
        return createAndInstallLoader(i, bundle, loaderCallbacks, loader != null ? loader.destroy(false) : null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }
}
