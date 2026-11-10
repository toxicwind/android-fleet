package b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {

        /* JADX INFO: renamed from: b.b.a.c$a$a, reason: collision with other inner class name */
        public static class C0005a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f8a;

            public C0005a(IBinder iBinder) {
                this.f8a = iBinder;
            }

            @Override // b.b.a.c
            public void a(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.udpserver.IReceiverListener");
                    parcelObtain.writeString(str);
                    this.f8a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8a;
            }
        }

        public static c c(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.hisense.udpserver.IReceiverListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0005a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }
    }

    void a(String str);
}
