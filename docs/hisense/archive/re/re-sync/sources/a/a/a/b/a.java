package a.a.a.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.a.a.b.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0002a extends Binder implements a {

        /* JADX INFO: renamed from: a.a.a.b.a$a$a, reason: collision with other inner class name */
        public static class C0003a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f1a;

            public C0003a(IBinder iBinder) {
                this.f1a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1a;
            }

            @Override // a.a.a.b.a
            public void b(int i, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f1a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0002a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a c(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0003a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                b(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void b(int i, Bundle bundle);
}
