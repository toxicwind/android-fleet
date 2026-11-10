package com.hisense.panelprotect;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IStatusChangeListener extends IInterface {
    void onStatusChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.hisense.panelprotect.IStatusChangeListener");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.hisense.panelprotect.IStatusChangeListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.hisense.panelprotect.IStatusChangeListener");
            onStatusChanged(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }
}
