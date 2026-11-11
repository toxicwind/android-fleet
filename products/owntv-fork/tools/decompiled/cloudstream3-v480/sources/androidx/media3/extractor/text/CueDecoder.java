package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.CueGroup$$ExternalSyntheticLambda1;
import androidx.media3.common.util.BundleCollectionUtil;
import com.google.common.base.Preconditions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class CueDecoder {
    static final String BUNDLE_FIELD_CUES = "c";
    static final String BUNDLE_FIELD_DURATION_US = "d";

    public CuesWithTiming decode(long j, byte[] bArr, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, i, i2);
        parcelObtain.setDataPosition(0);
        Bundle bundle = parcelObtain.readBundle(Bundle.class.getClassLoader());
        parcelObtain.recycle();
        return new CuesWithTiming(BundleCollectionUtil.fromBundleList(new CueGroup$$ExternalSyntheticLambda1(), (ArrayList) Preconditions.checkNotNull(bundle.getParcelableArrayList(BUNDLE_FIELD_CUES))), j, bundle.getLong("d"));
    }
}
