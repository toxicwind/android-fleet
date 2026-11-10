package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import e.b.a;
import e.b.b;
import e.b.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();

    @VisibleForTesting
    public static Clock zaf = DefaultClock.zza;

    @SafeParcelable.Field(getter = "getId", id = 2)
    public String mId;

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(getter = "getIdToken", id = 3)
    public String zag;

    @SafeParcelable.Field(getter = "getEmail", id = 4)
    public String zah;

    @SafeParcelable.Field(getter = "getDisplayName", id = 5)
    public String zai;

    @SafeParcelable.Field(getter = "getPhotoUrl", id = 6)
    public Uri zaj;

    @SafeParcelable.Field(getter = "getServerAuthCode", id = 7)
    public String zak;

    @SafeParcelable.Field(getter = "getExpirationTimeSecs", id = 8)
    public long zal;

    @SafeParcelable.Field(getter = "getObfuscatedIdentifier", id = 9)
    public String zam;

    @SafeParcelable.Field(id = 10)
    public List<Scope> zan;

    @SafeParcelable.Field(getter = "getGivenName", id = 11)
    public String zao;

    @SafeParcelable.Field(getter = "getFamilyName", id = 12)
    public String zap;
    public Set<Scope> zaq = new HashSet();

    @SafeParcelable.Constructor
    public GoogleSignInAccount(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) Uri uri, @SafeParcelable.Param(id = 7) String str5, @SafeParcelable.Param(id = 8) long j, @SafeParcelable.Param(id = 9) String str6, @SafeParcelable.Param(id = 10) List<Scope> list, @SafeParcelable.Param(id = 11) String str7, @SafeParcelable.Param(id = 12) String str8) {
        this.versionCode = i;
        this.mId = str;
        this.zag = str2;
        this.zah = str3;
        this.zai = str4;
        this.zaj = uri;
        this.zak = str5;
        this.zal = j;
        this.zam = str6;
        this.zan = list;
        this.zao = str7;
        this.zap = str8;
    }

    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        Account account = new Account("<<default account>>", AccountType.GOOGLE);
        return zaa(null, null, account.name, null, null, null, null, 0L, account.name, new HashSet());
    }

    @Nullable
    public static GoogleSignInAccount zaa(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        d dVar = new d(str);
        String strA = dVar.a("photoUrl", (String) null);
        Uri uri = !TextUtils.isEmpty(strA) ? Uri.parse(strA) : null;
        long j = Long.parseLong(dVar.h("expirationTime"));
        HashSet hashSet = new HashSet();
        a aVarE = dVar.e("grantedScopes");
        int iA = aVarE.a();
        for (int i = 0; i < iA; i++) {
            hashSet.add(new Scope(1, aVarE.b(i)));
        }
        GoogleSignInAccount googleSignInAccountZaa = zaa(dVar.a("id", ""), dVar.a("tokenId", (String) null), dVar.a("email", (String) null), dVar.a(FileProvider.DISPLAYNAME_FIELD, (String) null), dVar.a("givenName", (String) null), dVar.a("familyName", (String) null), uri, Long.valueOf(j), dVar.h("obfuscatedIdentifier"), hashSet);
        googleSignInAccountZaa.zak = dVar.a("serverAuthCode", (String) null);
        return googleSignInAccountZaa;
    }

    private final d zad() {
        d dVar = new d();
        try {
            if (getId() != null) {
                dVar.a("id", (Object) getId());
            }
            if (getIdToken() != null) {
                dVar.a("tokenId", (Object) getIdToken());
            }
            if (getEmail() != null) {
                dVar.a("email", (Object) getEmail());
            }
            if (getDisplayName() != null) {
                dVar.a(FileProvider.DISPLAYNAME_FIELD, (Object) getDisplayName());
            }
            if (getGivenName() != null) {
                dVar.a("givenName", (Object) getGivenName());
            }
            if (getFamilyName() != null) {
                dVar.a("familyName", (Object) getFamilyName());
            }
            if (getPhotoUrl() != null) {
                dVar.a("photoUrl", (Object) getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                dVar.a("serverAuthCode", (Object) getServerAuthCode());
            }
            dVar.b("expirationTime", this.zal);
            dVar.a("obfuscatedIdentifier", (Object) this.zam);
            a aVar = new a();
            Scope[] scopeArr = (Scope[]) this.zan.toArray(new Scope[this.zan.size()]);
            Arrays.sort(scopeArr, zaa.zae);
            for (Scope scope : scopeArr) {
                aVar.f2161a.add(scope.getScopeUri());
            }
            dVar.a("grantedScopes", aVar);
            return dVar;
        } catch (b e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zam.equals(this.zam) && googleSignInAccount.getRequestedScopes().equals(getRequestedScopes());
    }

    @Nullable
    public Account getAccount() {
        String str = this.zah;
        if (str == null) {
            return null;
        }
        return new Account(str, AccountType.GOOGLE);
    }

    @Nullable
    public String getDisplayName() {
        return this.zai;
    }

    @Nullable
    public String getEmail() {
        return this.zah;
    }

    @Nullable
    public String getFamilyName() {
        return this.zap;
    }

    @Nullable
    public String getGivenName() {
        return this.zao;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zan);
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    @Nullable
    public String getIdToken() {
        return this.zag;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zaj;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getRequestedScopes() {
        HashSet hashSet = new HashSet(this.zan);
        hashSet.addAll(this.zaq);
        return hashSet;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.zak;
    }

    public int hashCode() {
        return getRequestedScopes().hashCode() + ((this.zam.hashCode() + 527) * 31);
    }

    @KeepForSdk
    public boolean isExpired() {
        return zaf.currentTimeMillis() / 1000 >= this.zal - 300;
    }

    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.zaq, scopeArr);
        }
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPhotoUrl(), i, false);
        SafeParcelWriter.writeString(parcel, 7, getServerAuthCode(), false);
        long j = this.zal;
        parcel.writeInt(524296);
        parcel.writeLong(j);
        SafeParcelWriter.writeString(parcel, 9, this.zam, false);
        SafeParcelWriter.writeTypedList(parcel, 10, this.zan, false);
        SafeParcelWriter.writeString(parcel, 11, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, getFamilyName(), false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final String zab() {
        return this.zam;
    }

    public final String zac() {
        d dVarZad = zad();
        dVarZad.f2163b.remove("serverAuthCode");
        return dVarZad.toString();
    }

    public static GoogleSignInAccount zaa(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set<Scope> set) {
        long jLongValue = (l == null ? Long.valueOf(zaf.currentTimeMillis() / 1000) : l).longValue();
        Preconditions.checkNotEmpty(str7);
        Preconditions.checkNotNull(set);
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, jLongValue, str7, new ArrayList(set), str5, str6);
    }
}
