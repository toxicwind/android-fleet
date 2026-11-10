package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    public CancellationSignal mCancellationSignal;
    public Cursor mCursor;
    public final Loader<Cursor>.ForceLoadContentObserver mObserver;
    public String[] mProjection;
    public String mSelection;
    public String[] mSelectionArgs;
    public String mSortOrder;
    public Uri mUri;

    public CursorLoader(@NonNull Context context) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        synchronized (this) {
            if (this.mCancellationSignal != null) {
                this.mCancellationSignal.cancel();
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.mUri);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.mProjection));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.mSelection);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.mSelectionArgs));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.mSortOrder);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.mCursor);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.mContentChanged);
    }

    @Nullable
    public String[] getProjection() {
        return this.mProjection;
    }

    @Nullable
    public String getSelection() {
        return this.mSelection;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    @Nullable
    public String getSortOrder() {
        return this.mSortOrder;
    }

    @NonNull
    public Uri getUri() {
        return this.mUri;
    }

    @Override // androidx.loader.content.Loader
    public void onReset() {
        onStopLoading();
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    @Override // androidx.loader.content.Loader
    public void onStartLoading() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    public void onStopLoading() {
        cancelLoad();
    }

    public void setProjection(@Nullable String[] strArr) {
        this.mProjection = strArr;
    }

    public void setSelection(@Nullable String str) {
        this.mSelection = str;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.mSelectionArgs = strArr;
    }

    public void setSortOrder(@Nullable String str) {
        this.mSortOrder = str;
    }

    public void setUri(@NonNull Uri uri) {
        this.mUri = uri;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type androidx.loader.content.Loader to androidx.loader.content.CursorLoader for r2v1 'this'  androidx.loader.content.Loader
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // androidx.loader.content.Loader
    public void deliverResult(android.database.Cursor r3) {
        /*
            r2 = this;
            boolean r0 = r2.isReset()
            if (r0 == 0) goto Lc
            if (r3 == 0) goto Lb
            r3.close()
        Lb:
            return
        Lc:
            android.database.Cursor r0 = r2.mCursor
            r2.mCursor = r3
            boolean r1 = r2.isStarted()
            if (r1 == 0) goto L1d
            androidx.loader.content.Loader$OnLoadCompleteListener<D> r1 = r2.mListener
            if (r1 == 0) goto L1d
            r1.onLoadComplete(r2, r3)
        L1d:
            if (r0 == 0) goto L2a
            if (r0 == r3) goto L2a
            boolean r3 = r0.isClosed()
            if (r3 != 0) goto L2a
            r0.close()
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.loader.content.CursorLoader.deliverResult(android.database.Cursor):void");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.loader.content.AsyncTaskLoader
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException(null);
            }
            this.mCancellationSignal = new CancellationSignal();
        }
        try {
            Cursor cursorQuery = ContentResolverCompat.query(getContext().getContentResolver(), this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder, this.mCancellationSignal);
            if (cursorQuery != null) {
                try {
                    cursorQuery.getCount();
                    cursorQuery.registerContentObserver(this.mObserver);
                } catch (RuntimeException e2) {
                    cursorQuery.close();
                    throw e2;
                }
            }
            synchronized (this) {
                this.mCancellationSignal = null;
            }
            return cursorQuery;
        } catch (Throwable th) {
            synchronized (this) {
                this.mCancellationSignal = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
        this.mUri = uri;
        this.mProjection = strArr;
        this.mSelection = str;
        this.mSelectionArgs = strArr2;
        this.mSortOrder = str2;
    }
}
