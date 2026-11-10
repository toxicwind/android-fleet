package b.f.b.b.c.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.hisense.tv.smartsense.sdk.dynamicdomain.bean.DynamicDomainObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Parcelable.Creator<DynamicDomainObject> {
    @Override // android.os.Parcelable.Creator
    public DynamicDomainObject createFromParcel(Parcel parcel) {
        return new DynamicDomainObject(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public DynamicDomainObject[] newArray(int i) {
        return new DynamicDomainObject[i];
    }
}
