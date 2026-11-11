package com.lagradost.cloudstream3.utils;

import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DataStore.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u0002H\n¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010\u0011\u001a\u00020\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Editor;", "", "editor", "Landroid/content/SharedPreferences$Editor;", "<init>", "(Landroid/content/SharedPreferences$Editor;)V", "getEditor", "()Landroid/content/SharedPreferences$Editor;", "setKeyRaw", "", ExifInterface.GPS_DIRECTION_TRUE, "path", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/Object;)V", "isStringSet", "", "apply", "component1", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Editor {
    private final SharedPreferences.Editor editor;

    public static /* synthetic */ Editor copy$default(Editor editor, SharedPreferences.Editor editor2, int i, Object obj) {
        if ((i & 1) != 0) {
            editor2 = editor.editor;
        }
        return editor.copy(editor2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    public final Editor copy(SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "editor");
        return new Editor(editor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Editor) && Intrinsics.areEqual(this.editor, ((Editor) other).editor);
    }

    public int hashCode() {
        return this.editor.hashCode();
    }

    public String toString() {
        return "Editor(editor=" + this.editor + ')';
    }

    public Editor(SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "editor");
        this.editor = editor;
    }

    public final SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> void setKeyRaw(String path, T value) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (isStringSet(value)) {
            SharedPreferences.Editor editor = this.editor;
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
            editor.putStringSet(path, (Set) value);
            return;
        }
        if (value instanceof Boolean) {
            this.editor.putBoolean(path, ((Boolean) value).booleanValue());
            return;
        }
        if (value instanceof Integer) {
            this.editor.putInt(path, ((Number) value).intValue());
            return;
        }
        if (value instanceof String) {
            this.editor.putString(path, (String) value);
        } else if (value instanceof Float) {
            this.editor.putFloat(path, ((Number) value).floatValue());
        } else if (value instanceof Long) {
            this.editor.putLong(path, ((Number) value).longValue());
        }
    }

    private final boolean isStringSet(Object value) {
        if (value instanceof Set) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : (Iterable) value) {
                if (obj instanceof String) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() == ((Set) value).size()) {
                return true;
            }
        }
        return false;
    }

    public final void apply() {
        this.editor.apply();
        System.gc();
    }
}
