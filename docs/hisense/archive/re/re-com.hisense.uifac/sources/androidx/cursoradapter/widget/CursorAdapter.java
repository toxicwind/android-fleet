package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.widget.BaseAdapter;
import android.widget.Filterable;

/* JADX INFO: loaded from: classes.dex */
public abstract class CursorAdapter extends BaseAdapter implements Filterable {
    public abstract CharSequence convertToString(Cursor cursor);

    public abstract Cursor getCursor();
}
