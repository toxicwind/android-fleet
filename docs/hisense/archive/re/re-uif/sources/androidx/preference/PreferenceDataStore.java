package androidx.preference;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceDataStore {
    public abstract boolean getBoolean(String str, boolean z);

    public abstract String getString(String str, String str2);

    public abstract void putBoolean(String str, boolean z);

    public abstract void putString(String str, String str2);
}
