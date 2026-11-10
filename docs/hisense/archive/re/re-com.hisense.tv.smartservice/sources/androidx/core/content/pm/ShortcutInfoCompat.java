package androidx.core.content.pm;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import b.a.a.a.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutInfoCompat {
    public static final String EXTRA_LOCUS_ID = "extraLocusId";
    public static final String EXTRA_LONG_LIVED = "extraLongLived";
    public static final String EXTRA_PERSON_ = "extraPerson_";
    public static final String EXTRA_PERSON_COUNT = "extraPersonCount";
    public static final String EXTRA_SLICE_URI = "extraSliceUri";
    public ComponentName mActivity;
    public Set<String> mCategories;
    public Context mContext;
    public CharSequence mDisabledMessage;
    public int mDisabledReason;
    public PersistableBundle mExtras;
    public boolean mHasKeyFieldsOnly;
    public IconCompat mIcon;
    public String mId;
    public Intent[] mIntents;
    public boolean mIsAlwaysBadged;
    public boolean mIsCached;
    public boolean mIsDeclaredInManifest;
    public boolean mIsDynamic;
    public boolean mIsEnabled = true;
    public boolean mIsImmutable;
    public boolean mIsLongLived;
    public boolean mIsPinned;
    public CharSequence mLabel;
    public long mLastChangedTimestamp;

    @Nullable
    public LocusIdCompat mLocusId;
    public CharSequence mLongLabel;
    public String mPackageName;
    public Person[] mPersons;
    public int mRank;
    public UserHandle mUser;

    public static class Builder {
        public Map<String, Map<String, List<String>>> mCapabilityBindingParams;
        public Set<String> mCapabilityBindings;
        public final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();
        public boolean mIsConversation;
        public Uri mSliceUri;

        public Builder(@NonNull Context context, @NonNull String id) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.mContext = context;
            shortcutInfoCompat.mId = id;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addCapabilityBinding(@NonNull String capability) {
            if (this.mCapabilityBindings == null) {
                this.mCapabilityBindings = new HashSet();
            }
            this.mCapabilityBindings.add(capability);
            return this;
        }

        @NonNull
        @SuppressLint({"UnsafeNewApiCall"})
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            Intent[] intentArr = shortcutInfoCompat.mIntents;
            if (intentArr == null || intentArr.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            if (this.mIsConversation) {
                if (shortcutInfoCompat.mLocusId == null) {
                    shortcutInfoCompat.mLocusId = new LocusIdCompat(shortcutInfoCompat.mId);
                }
                this.mInfo.mIsLongLived = true;
            }
            if (this.mCapabilityBindings != null) {
                ShortcutInfoCompat shortcutInfoCompat2 = this.mInfo;
                if (shortcutInfoCompat2.mCategories == null) {
                    shortcutInfoCompat2.mCategories = new HashSet();
                }
                this.mInfo.mCategories.addAll(this.mCapabilityBindings);
            }
            int i = Build.VERSION.SDK_INT;
            if (this.mCapabilityBindingParams != null) {
                ShortcutInfoCompat shortcutInfoCompat3 = this.mInfo;
                if (shortcutInfoCompat3.mExtras == null) {
                    shortcutInfoCompat3.mExtras = new PersistableBundle();
                }
                for (String str : this.mCapabilityBindingParams.keySet()) {
                    Map<String, List<String>> map = this.mCapabilityBindingParams.get(str);
                    this.mInfo.mExtras.putStringArray(str, (String[]) map.keySet().toArray(new String[0]));
                    for (String str2 : map.keySet()) {
                        List<String> list = map.get(str2);
                        this.mInfo.mExtras.putStringArray(a.a(str, "/", str2), list == null ? new String[0] : (String[]) list.toArray(new String[0]));
                    }
                }
            }
            if (this.mSliceUri != null) {
                ShortcutInfoCompat shortcutInfoCompat4 = this.mInfo;
                if (shortcutInfoCompat4.mExtras == null) {
                    shortcutInfoCompat4.mExtras = new PersistableBundle();
                }
                this.mInfo.mExtras.putString(ShortcutInfoCompat.EXTRA_SLICE_URI, UriCompat.toSafeString(this.mSliceUri));
            }
            return this.mInfo;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName activity) {
            this.mInfo.mActivity = activity;
            return this;
        }

        @NonNull
        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        @NonNull
        public Builder setCategories(@NonNull Set<String> categories) {
            this.mInfo.mCategories = categories;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence disabledMessage) {
            this.mInfo.mDisabledMessage = disabledMessage;
            return this;
        }

        @NonNull
        public Builder setExtras(@NonNull PersistableBundle extras) {
            this.mInfo.mExtras = extras;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat icon) {
            this.mInfo.mIcon = icon;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intents) {
            this.mInfo.mIntents = intents;
            return this;
        }

        @NonNull
        public Builder setIsConversation() {
            this.mIsConversation = true;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable final LocusIdCompat locusId) {
            this.mInfo.mLocusId = locusId;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence longLabel) {
            this.mInfo.mLongLabel = longLabel;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setLongLived() {
            this.mInfo.mIsLongLived = true;
            return this;
        }

        @NonNull
        public Builder setPerson(@NonNull Person person) {
            return setPersons(new Person[]{person});
        }

        @NonNull
        public Builder setPersons(@NonNull Person[] persons) {
            this.mInfo.mPersons = persons;
            return this;
        }

        @NonNull
        public Builder setRank(int rank) {
            this.mInfo.mRank = rank;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence shortLabel) {
            this.mInfo.mLabel = shortLabel;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder setSliceUri(@NonNull Uri sliceUri) {
            this.mSliceUri = sliceUri;
            return this;
        }

        @NonNull
        public Builder setLongLived(boolean longLived) {
            this.mInfo.mIsLongLived = longLived;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addCapabilityBinding(@NonNull String capability, @NonNull String parameter, @NonNull List<String> parameterValues) {
            addCapabilityBinding(capability);
            if (!parameterValues.isEmpty()) {
                if (this.mCapabilityBindingParams == null) {
                    this.mCapabilityBindingParams = new HashMap();
                }
                if (this.mCapabilityBindingParams.get(capability) == null) {
                    this.mCapabilityBindingParams.put(capability, new HashMap());
                }
                this.mCapabilityBindingParams.get(capability).put(parameter, parameterValues);
            }
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull ShortcutInfoCompat shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.mContext = shortcutInfo.mContext;
            shortcutInfoCompat.mId = shortcutInfo.mId;
            shortcutInfoCompat.mPackageName = shortcutInfo.mPackageName;
            Intent[] intentArr = shortcutInfo.mIntents;
            shortcutInfoCompat.mIntents = (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
            ShortcutInfoCompat shortcutInfoCompat2 = this.mInfo;
            shortcutInfoCompat2.mActivity = shortcutInfo.mActivity;
            shortcutInfoCompat2.mLabel = shortcutInfo.mLabel;
            shortcutInfoCompat2.mLongLabel = shortcutInfo.mLongLabel;
            shortcutInfoCompat2.mDisabledMessage = shortcutInfo.mDisabledMessage;
            shortcutInfoCompat2.mDisabledReason = shortcutInfo.mDisabledReason;
            shortcutInfoCompat2.mIcon = shortcutInfo.mIcon;
            shortcutInfoCompat2.mIsAlwaysBadged = shortcutInfo.mIsAlwaysBadged;
            shortcutInfoCompat2.mUser = shortcutInfo.mUser;
            shortcutInfoCompat2.mLastChangedTimestamp = shortcutInfo.mLastChangedTimestamp;
            shortcutInfoCompat2.mIsCached = shortcutInfo.mIsCached;
            shortcutInfoCompat2.mIsDynamic = shortcutInfo.mIsDynamic;
            shortcutInfoCompat2.mIsPinned = shortcutInfo.mIsPinned;
            shortcutInfoCompat2.mIsDeclaredInManifest = shortcutInfo.mIsDeclaredInManifest;
            shortcutInfoCompat2.mIsImmutable = shortcutInfo.mIsImmutable;
            shortcutInfoCompat2.mIsEnabled = shortcutInfo.mIsEnabled;
            shortcutInfoCompat2.mLocusId = shortcutInfo.mLocusId;
            shortcutInfoCompat2.mIsLongLived = shortcutInfo.mIsLongLived;
            shortcutInfoCompat2.mHasKeyFieldsOnly = shortcutInfo.mHasKeyFieldsOnly;
            shortcutInfoCompat2.mRank = shortcutInfo.mRank;
            Person[] personArr = shortcutInfo.mPersons;
            if (personArr != null) {
                shortcutInfoCompat2.mPersons = (Person[]) Arrays.copyOf(personArr, personArr.length);
            }
            Set<String> set = shortcutInfo.mCategories;
            if (set != null) {
                this.mInfo.mCategories = new HashSet(set);
            }
            PersistableBundle persistableBundle = shortcutInfo.mExtras;
            if (persistableBundle != null) {
                this.mInfo.mExtras = persistableBundle;
            }
        }

        @RequiresApi(25)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull Context context, @NonNull ShortcutInfo shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.mContext = context;
            shortcutInfoCompat.mId = shortcutInfo.getId();
            this.mInfo.mPackageName = shortcutInfo.getPackage();
            Intent[] intents = shortcutInfo.getIntents();
            this.mInfo.mIntents = (Intent[]) Arrays.copyOf(intents, intents.length);
            this.mInfo.mActivity = shortcutInfo.getActivity();
            this.mInfo.mLabel = shortcutInfo.getShortLabel();
            this.mInfo.mLongLabel = shortcutInfo.getLongLabel();
            this.mInfo.mDisabledMessage = shortcutInfo.getDisabledMessage();
            if (Build.VERSION.SDK_INT >= 28) {
                this.mInfo.mDisabledReason = shortcutInfo.getDisabledReason();
            } else {
                this.mInfo.mDisabledReason = shortcutInfo.isEnabled() ? 0 : 3;
            }
            this.mInfo.mCategories = shortcutInfo.getCategories();
            this.mInfo.mPersons = ShortcutInfoCompat.getPersonsFromExtra(shortcutInfo.getExtras());
            this.mInfo.mUser = shortcutInfo.getUserHandle();
            this.mInfo.mLastChangedTimestamp = shortcutInfo.getLastChangedTimestamp();
            if (Build.VERSION.SDK_INT >= 30) {
                this.mInfo.mIsCached = shortcutInfo.isCached();
            }
            this.mInfo.mIsDynamic = shortcutInfo.isDynamic();
            this.mInfo.mIsPinned = shortcutInfo.isPinned();
            this.mInfo.mIsDeclaredInManifest = shortcutInfo.isDeclaredInManifest();
            this.mInfo.mIsImmutable = shortcutInfo.isImmutable();
            this.mInfo.mIsEnabled = shortcutInfo.isEnabled();
            this.mInfo.mHasKeyFieldsOnly = shortcutInfo.hasKeyFieldsOnly();
            this.mInfo.mLocusId = ShortcutInfoCompat.getLocusId(shortcutInfo);
            this.mInfo.mRank = shortcutInfo.getRank();
            this.mInfo.mExtras = shortcutInfo.getExtras();
        }
    }

    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    private PersistableBundle buildLegacyExtrasBundle() {
        if (this.mExtras == null) {
            this.mExtras = new PersistableBundle();
        }
        Person[] personArr = this.mPersons;
        if (personArr != null && personArr.length > 0) {
            this.mExtras.putInt(EXTRA_PERSON_COUNT, personArr.length);
            int i = 0;
            while (i < this.mPersons.length) {
                PersistableBundle persistableBundle = this.mExtras;
                StringBuilder sbA = a.a(EXTRA_PERSON_);
                int i2 = i + 1;
                sbA.append(i2);
                persistableBundle.putPersistableBundle(sbA.toString(), this.mPersons[i].toPersistableBundle());
                i = i2;
            }
        }
        LocusIdCompat locusIdCompat = this.mLocusId;
        if (locusIdCompat != null) {
            this.mExtras.putString(EXTRA_LOCUS_ID, locusIdCompat.getId());
        }
        this.mExtras.putBoolean(EXTRA_LONG_LIVED, this.mIsLongLived);
        return this.mExtras;
    }

    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static List<ShortcutInfoCompat> fromShortcuts(@NonNull final Context context, @NonNull final List<ShortcutInfo> shortcuts) {
        ArrayList arrayList = new ArrayList(shortcuts.size());
        Iterator<ShortcutInfo> it = shortcuts.iterator();
        while (it.hasNext()) {
            arrayList.add(new Builder(context, it.next()).build());
        }
        return arrayList;
    }

    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static LocusIdCompat getLocusIdFromExtra(@Nullable PersistableBundle bundle) {
        String string;
        if (bundle == null || (string = bundle.getString(EXTRA_LOCUS_ID)) == null) {
            return null;
        }
        return new LocusIdCompat(string);
    }

    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @VisibleForTesting
    public static boolean getLongLivedFromExtra(@Nullable PersistableBundle bundle) {
        if (bundle == null || !bundle.containsKey(EXTRA_LONG_LIVED)) {
            return false;
        }
        return bundle.getBoolean(EXTRA_LONG_LIVED);
    }

    @VisibleForTesting
    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person[] getPersonsFromExtra(@NonNull PersistableBundle bundle) {
        if (bundle == null || !bundle.containsKey(EXTRA_PERSON_COUNT)) {
            return null;
        }
        int i = bundle.getInt(EXTRA_PERSON_COUNT);
        Person[] personArr = new Person[i];
        int i2 = 0;
        while (i2 < i) {
            StringBuilder sbA = a.a(EXTRA_PERSON_);
            int i3 = i2 + 1;
            sbA.append(i3);
            personArr[i2] = Person.fromPersistableBundle(bundle.getPersistableBundle(sbA.toString()));
            i2 = i3;
        }
        return personArr;
    }

    public Intent addToIntent(Intent outIntent) {
        Intent[] intentArr = this.mIntents;
        outIntent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if (this.mIcon != null) {
            Drawable activityIcon = null;
            if (this.mIsAlwaysBadged) {
                PackageManager packageManager = this.mContext.getPackageManager();
                ComponentName componentName = this.mActivity;
                if (componentName != null) {
                    try {
                        activityIcon = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (activityIcon == null) {
                    activityIcon = this.mContext.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.mIcon.addToShortcutIntent(outIntent, activityIcon, this.mContext);
        }
        return outIntent;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.mActivity;
    }

    @Nullable
    public Set<String> getCategories() {
        return this.mCategories;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    public int getDisabledReason() {
        return this.mDisabledReason;
    }

    @Nullable
    public PersistableBundle getExtras() {
        return this.mExtras;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @NonNull
    public Intent getIntent() {
        Intent[] intentArr = this.mIntents;
        return intentArr[intentArr.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = this.mIntents;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    public long getLastChangedTimestamp() {
        return this.mLastChangedTimestamp;
    }

    @Nullable
    public LocusIdCompat getLocusId() {
        return this.mLocusId;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    @NonNull
    public String getPackage() {
        return this.mPackageName;
    }

    public int getRank() {
        return this.mRank;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    @Nullable
    public UserHandle getUserHandle() {
        return this.mUser;
    }

    public boolean hasKeyFieldsOnly() {
        return this.mHasKeyFieldsOnly;
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public boolean isDeclaredInManifest() {
        return this.mIsDeclaredInManifest;
    }

    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isImmutable() {
        return this.mIsImmutable;
    }

    public boolean isPinned() {
        return this.mIsPinned;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        IconCompat iconCompat = this.mIcon;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon(this.mContext));
        }
        if (!TextUtils.isEmpty(this.mLongLabel)) {
            intents.setLongLabel(this.mLongLabel);
        }
        if (!TextUtils.isEmpty(this.mDisabledMessage)) {
            intents.setDisabledMessage(this.mDisabledMessage);
        }
        ComponentName componentName = this.mActivity;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.mCategories;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.mRank);
        PersistableBundle persistableBundle = this.mExtras;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Person[] personArr = this.mPersons;
            if (personArr != null && personArr.length > 0) {
                android.app.Person[] personArr2 = new android.app.Person[personArr.length];
                for (int i = 0; i < personArr2.length; i++) {
                    personArr2[i] = this.mPersons[i].toAndroidPerson();
                }
                intents.setPersons(personArr2);
            }
            LocusIdCompat locusIdCompat = this.mLocusId;
            if (locusIdCompat != null) {
                intents.setLocusId(locusIdCompat.toLocusId());
            }
            intents.setLongLived(this.mIsLongLived);
        } else {
            intents.setExtras(buildLegacyExtrasBundle());
        }
        return intents.build();
    }

    @Nullable
    @RequiresApi(25)
    public static LocusIdCompat getLocusId(@NonNull final ShortcutInfo shortcutInfo) {
        if (Build.VERSION.SDK_INT < 29) {
            return getLocusIdFromExtra(shortcutInfo.getExtras());
        }
        if (shortcutInfo.getLocusId() == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(shortcutInfo.getLocusId());
    }
}
