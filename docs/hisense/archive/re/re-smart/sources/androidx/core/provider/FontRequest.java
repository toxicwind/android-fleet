package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FontRequest {
    public final List<List<byte[]>> mCertificates;
    public final int mCertificatesArray;
    public final String mIdentifier;
    public final String mProviderAuthority;
    public final String mProviderPackage;
    public final String mQuery;

    public FontRequest(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query, @NonNull List<List<byte[]>> certificates) {
        if (providerAuthority == null) {
            throw new NullPointerException();
        }
        this.mProviderAuthority = providerAuthority;
        if (providerPackage == null) {
            throw new NullPointerException();
        }
        this.mProviderPackage = providerPackage;
        if (query == null) {
            throw new NullPointerException();
        }
        this.mQuery = query;
        if (certificates == null) {
            throw new NullPointerException();
        }
        this.mCertificates = certificates;
        this.mCertificatesArray = 0;
        this.mIdentifier = createIdentifier(providerAuthority, providerPackage, query);
    }

    private String createIdentifier(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query) {
        return providerAuthority + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + providerPackage + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + query;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String getId() {
        return this.mIdentifier;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @NonNull
    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    @NonNull
    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    @NonNull
    public String getQuery() {
        return this.mQuery;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbA = a.a("FontRequest {mProviderAuthority: ");
        sbA.append(this.mProviderAuthority);
        sbA.append(", mProviderPackage: ");
        sbA.append(this.mProviderPackage);
        sbA.append(", mQuery: ");
        sbA.append(this.mQuery);
        sbA.append(", mCertificates:");
        sb.append(sbA.toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.mCertificates.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.mCertificatesArray);
        return sb.toString();
    }

    public FontRequest(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query, @ArrayRes int certificates) {
        if (providerAuthority != null) {
            this.mProviderAuthority = providerAuthority;
            if (providerPackage != null) {
                this.mProviderPackage = providerPackage;
                if (query != null) {
                    this.mQuery = query;
                    this.mCertificates = null;
                    Preconditions.checkArgument(certificates != 0);
                    this.mCertificatesArray = certificates;
                    this.mIdentifier = createIdentifier(providerAuthority, providerPackage, query);
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }
}
