package com.regza.persistent.logd;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IRegzaPersistentLogServiceCallback extends IInterface {
    void onLogSaved(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRegzaPersistentLogServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.regza.persistent.logd.IRegzaPersistentLogServiceCallback");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.regza.persistent.logd.IRegzaPersistentLogServiceCallback");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.regza.persistent.logd.IRegzaPersistentLogServiceCallback");
            onLogSaved(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }
}
