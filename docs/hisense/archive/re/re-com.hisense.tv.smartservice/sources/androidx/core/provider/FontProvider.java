package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.FontResourcesParserCompat;
import b.a.a.a.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FontProvider {
    public static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: androidx.core.provider.FontProvider.1
        @Override // java.util.Comparator
        public int compare(byte[] l, byte[] r) {
            int length;
            int length2;
            if (l.length == r.length) {
                for (int i = 0; i < l.length; i++) {
                    if (l[i] != r[i]) {
                        length = l[i];
                        length2 = r[i];
                    }
                }
                return 0;
            }
            length = l.length;
            length2 = r.length;
            return length - length2;
        }
    };

    public static List<byte[]> convertToByteArrayList(Signature[] signatures) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatures) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    public static boolean equalsByteArrayList(List<byte[]> signatures, List<byte[]> requestSignatures) {
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals(signatures.get(i), requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<List<byte[]>> getCertificates(FontRequest request, Resources resources) {
        return request.getCertificates() != null ? request.getCertificates() : FontResourcesParserCompat.readCerts(resources, request.getCertificatesArrayResId());
    }

    @NonNull
    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(@NonNull Context context, @NonNull FontRequest request, @Nullable CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), request, context.getResources());
        return provider == null ? new FontsContractCompat.FontFamilyResult(1, null) : new FontsContractCompat.FontFamilyResult(0, query(context, request, provider.authority, cancellationSignal));
    }

    @Nullable
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest request, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = request.getProviderAuthority();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(a.a("No package found for authority: ", providerAuthority));
        }
        if (!providerInfoResolveContentProvider.packageName.equals(request.getProviderPackage())) {
            StringBuilder sbB = a.b("Found content provider ", providerAuthority, ", but package was not ");
            sbB.append(request.getProviderPackage());
            throw new PackageManager.NameNotFoundException(sbB.toString());
        }
        List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = getCertificates(request, resources);
        for (int i = 0; i < certificates.size(); i++) {
            ArrayList arrayList = new ArrayList(certificates.get(i));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    @NonNull
    @VisibleForTesting
    public static FontsContractCompat.FontInfo[] query(Context context, FontRequest request, String authority, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(authority).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(authority).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            String[] strArr = {"_id", FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE};
            int i = Build.VERSION.SDK_INT;
            int i2 = 0;
            cursorQuery = context.getContentResolver().query(uriBuild, strArr, "query = ?", new String[]{request.getQuery()}, null, cancellationSignal);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.FILE_ID);
                int columnIndex4 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX);
                int columnIndex5 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.WEIGHT);
                int columnIndex6 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.ITALIC);
                while (cursorQuery.moveToNext()) {
                    int i3 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : i2;
                    arrayList2.add(FontsContractCompat.FontInfo.create(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : i2, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i3));
                    i2 = 0;
                }
                arrayList = arrayList2;
            }
            return (FontsContractCompat.FontInfo[]) arrayList.toArray(new FontsContractCompat.FontInfo[0]);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }
}
