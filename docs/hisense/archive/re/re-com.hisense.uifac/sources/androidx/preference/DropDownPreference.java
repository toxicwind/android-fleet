package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

/* JADX INFO: loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    private final ArrayAdapter mAdapter;
    private final Context mContext;

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.dropdownPreferenceStyle);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
        this.mAdapter = createAdapter();
        updateEntries();
    }

    protected ArrayAdapter createAdapter() {
        return new ArrayAdapter(this.mContext, R.layout.simple_spinner_dropdown_item);
    }

    private void updateEntries() {
        this.mAdapter.clear();
        if (getEntries() != null) {
            for (CharSequence charSequence : getEntries()) {
                this.mAdapter.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    protected void notifyChanged() {
        super.notifyChanged();
        ArrayAdapter arrayAdapter = this.mAdapter;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
