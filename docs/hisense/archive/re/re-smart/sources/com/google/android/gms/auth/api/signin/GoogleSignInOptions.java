package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import e.b.a;
import e.b.b;
import e.b.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", id = 6)
    public final boolean zaaa;

    @SafeParcelable.Field(getter = "getServerClientId", id = 7)
    public String zaab;

    @SafeParcelable.Field(getter = "getHostedDomain", id = 8)
    public String zaac;

    @SafeParcelable.Field(getter = "getExtensions", id = 9)
    public ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;

    @SafeParcelable.Field(getter = "getLogSessionId", id = 10)
    public String zaae;
    public Map<Integer, GoogleSignInOptionsExtensionParcelable> zaaf;

    @SafeParcelable.Field(getter = "getScopes", id = 2)
    public final ArrayList<Scope> zaw;

    @SafeParcelable.Field(getter = "getAccount", id = 3)
    public Account zax;

    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 4)
    public boolean zay;

    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", id = 5)
    public final boolean zaz;

    @VisibleForTesting
    public static final Scope zar = new Scope(1, Scopes.PROFILE);

    @VisibleForTesting
    public static final Scope zas = new Scope(1, "email");

    @VisibleForTesting
    public static final Scope zat = new Scope(1, Scopes.OPEN_ID);

    @VisibleForTesting
    public static final Scope zau = new Scope(1, Scopes.GAMES_LITE);

    @VisibleForTesting
    public static final Scope zav = new Scope(1, Scopes.GAMES);
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(zau, new Scope[0]).build();
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zad();
    public static Comparator<Scope> zaag = new zac();

    public static final class Builder {
        public Set<Scope> mScopes;
        public boolean zaaa;
        public String zaab;
        public String zaac;
        public String zaae;
        public Map<Integer, GoogleSignInOptionsExtensionParcelable> zaah;
        public Account zax;
        public boolean zay;
        public boolean zaz;

        public Builder() {
            this.mScopes = new HashSet();
            this.zaah = new HashMap();
        }

        private final String zac(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zaab;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zaah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                this.mScopes.addAll(googleSignInOptionsExtension.getImpliedScopes());
            }
            this.zaah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
            return this;
        }

        public final GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.zav) && this.mScopes.contains(GoogleSignInOptions.zau)) {
                this.mScopes.remove(GoogleSignInOptions.zau);
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaah, this.zaae, null);
        }

        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }

        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zay = true;
            zac(str);
            this.zaab = str;
            return this;
        }

        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder setAccountName(String str) {
            Preconditions.checkNotEmpty(str);
            this.zax = new Account(str, AccountType.GOOGLE);
            return this;
        }

        public final Builder setHostedDomain(String str) {
            Preconditions.checkNotEmpty(str);
            this.zaac = str;
            return this;
        }

        @KeepForSdk
        public final Builder setLogSessionId(String str) {
            this.zaae = str;
            return this;
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zaz = true;
            zac(str);
            this.zaab = str;
            this.zaaa = z;
            return this;
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet();
            this.zaah = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.zaw);
            this.zaz = googleSignInOptions.zaz;
            this.zaaa = googleSignInOptions.zaaa;
            this.zay = googleSignInOptions.zay;
            this.zaab = googleSignInOptions.zaab;
            this.zax = googleSignInOptions.zax;
            this.zaac = googleSignInOptions.zaac;
            this.zaah = GoogleSignInOptions.zaa(googleSignInOptions.zaad);
            this.zaae = googleSignInOptions.zaae;
        }
    }

    @SafeParcelable.Constructor
    public GoogleSignInOptions(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList<Scope> arrayList, @SafeParcelable.Param(id = 3) Account account, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) boolean z3, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2, @SafeParcelable.Param(id = 10) String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, zaa(arrayList2), str3);
    }

    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(@Nullable List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap map = new HashMap();
        if (list == null) {
            return map;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            map.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return map;
    }

    @Nullable
    public static GoogleSignInOptions zab(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        d dVar = new d(str);
        HashSet hashSet = new HashSet();
        a aVarE = dVar.e("scopes");
        int iA = aVarE.a();
        for (int i = 0; i < iA; i++) {
            hashSet.add(new Scope(1, aVarE.b(i)));
        }
        String strA = dVar.a("accountName", (String) null);
        return new GoogleSignInOptions(3, (ArrayList<Scope>) new ArrayList(hashSet), !TextUtils.isEmpty(strA) ? new Account(strA, AccountType.GOOGLE) : null, dVar.b("idTokenRequested"), dVar.b("serverAuthRequested"), dVar.b("forceCodeForRefreshToken"), dVar.a("serverClientId", (String) null), dVar.a("hostedDomain", (String) null), new HashMap(), (String) null);
    }

    private final d zad() {
        d dVar = new d();
        try {
            a aVar = new a();
            Collections.sort(this.zaw, zaag);
            ArrayList<Scope> arrayList = this.zaw;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Scope scope = arrayList.get(i);
                i++;
                aVar.f2161a.add(scope.getScopeUri());
            }
            dVar.a("scopes", aVar);
            if (this.zax != null) {
                dVar.a("accountName", (Object) this.zax.name);
            }
            dVar.b("idTokenRequested", this.zay);
            dVar.b("forceCodeForRefreshToken", this.zaaa);
            dVar.b("serverAuthRequested", this.zaz);
            if (!TextUtils.isEmpty(this.zaab)) {
                dVar.a("serverClientId", (Object) this.zaab);
            }
            if (!TextUtils.isEmpty(this.zaac)) {
                dVar.a("hostedDomain", (Object) this.zaac);
            }
            return dVar;
        } catch (b e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0054 A[Catch: ClassCastException -> 0x0091, TryCatch #0 {ClassCastException -> 0x0091, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004c, B:24:0x0054, B:29:0x006b, B:31:0x0073, B:33:0x007b, B:35:0x0083, B:27:0x005f, B:20:0x0040), top: B:41:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x005e  */
    /* JADX WARN: Code duplicated, block: B:27:0x005f A[Catch: ClassCastException -> 0x0091, TryCatch #0 {ClassCastException -> 0x0091, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004c, B:24:0x0054, B:29:0x006b, B:31:0x0073, B:33:0x007b, B:35:0x0083, B:27:0x005f, B:20:0x0040), top: B:41:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x006b A[Catch: ClassCastException -> 0x0091, TryCatch #0 {ClassCastException -> 0x0091, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004c, B:24:0x0054, B:29:0x006b, B:31:0x0073, B:33:0x007b, B:35:0x0083, B:27:0x005f, B:20:0x0040), top: B:41:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0073 A[Catch: ClassCastException -> 0x0091, TryCatch #0 {ClassCastException -> 0x0091, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004c, B:24:0x0054, B:29:0x006b, B:31:0x0073, B:33:0x007b, B:35:0x0083, B:27:0x005f, B:20:0x0040), top: B:41:0x0004 }] */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zaad.size() <= 0 && googleSignInOptions.zaad.size() <= 0 && this.zaw.size() == googleSignInOptions.getScopes().size() && this.zaw.containsAll(googleSignInOptions.getScopes())) {
                if (this.zax == null) {
                    if (googleSignInOptions.getAccount() == null) {
                        if (TextUtils.isEmpty(this.zaab)) {
                            if (TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                                if (this.zaaa != googleSignInOptions.isForceCodeForRefreshToken() && this.zay == googleSignInOptions.isIdTokenRequested() && this.zaz == googleSignInOptions.isServerAuthCodeRequested() && TextUtils.equals(this.zaae, googleSignInOptions.getLogSessionId())) {
                                    return true;
                                }
                            }
                        } else if (this.zaab.equals(googleSignInOptions.getServerClientId())) {
                            if (this.zaaa != googleSignInOptions.isForceCodeForRefreshToken()) {
                            }
                        }
                    }
                } else if (this.zax.equals(googleSignInOptions.getAccount())) {
                    if (TextUtils.isEmpty(this.zaab)) {
                        if (TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                            if (this.zaaa != googleSignInOptions.isForceCodeForRefreshToken()) {
                            }
                        }
                    } else if (this.zaab.equals(googleSignInOptions.getServerClientId())) {
                        if (this.zaaa != googleSignInOptions.isForceCodeForRefreshToken()) {
                        }
                    }
                }
            }
        } catch (ClassCastException unused) {
        }
        return false;
    }

    @KeepForSdk
    public Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }

    @Nullable
    @KeepForSdk
    public String getLogSessionId() {
        return this.zaae;
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zaw;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zaw);
    }

    @KeepForSdk
    public String getServerClientId() {
        return this.zaab;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zaw;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Scope scope = arrayList2.get(i);
            i++;
            arrayList.add(scope.getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).addObject(this.zaae).hash();
    }

    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zay;
    }

    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i, false);
        boolean zIsIdTokenRequested = isIdTokenRequested();
        parcel.writeInt(262148);
        parcel.writeInt(zIsIdTokenRequested ? 1 : 0);
        boolean zIsServerAuthCodeRequested = isServerAuthCodeRequested();
        parcel.writeInt(262149);
        parcel.writeInt(zIsServerAuthCodeRequested ? 1 : 0);
        boolean zIsForceCodeForRefreshToken = isForceCodeForRefreshToken();
        parcel.writeInt(262150);
        parcel.writeInt(zIsForceCodeForRefreshToken ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.writeString(parcel, 10, getLogSessionId(), false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public final String zae() {
        return zad().toString();
    }

    public GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map, String str3) {
        this.versionCode = i;
        this.zaw = arrayList;
        this.zax = account;
        this.zay = z;
        this.zaz = z2;
        this.zaaa = z3;
        this.zaab = str;
        this.zaac = str2;
        this.zaad = new ArrayList<>(map.values());
        this.zaaf = map;
        this.zaae = str3;
    }

    public /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3, zac zacVar) {
        this(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, str, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map, str3);
    }
}
