package androidx.preference;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceManager {
    abstract SharedPreferences.Editor getEditor();

    public abstract PreferenceDataStore getPreferenceDataStore();

    public abstract SharedPreferences getSharedPreferences();

    abstract boolean shouldCommit();
}
