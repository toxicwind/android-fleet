package com.hisense.serialInfoTest.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IpcManager extends IInterface {
    int[] getSupportSources() throws RemoteException;

    public static abstract class Stub extends Binder implements IpcManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.hisense.serialInfoTest.model.IpcManager");
        }

        public static IpcManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.hisense.serialInfoTest.model.IpcManager");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IpcManager)) {
                return (IpcManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.hisense.serialInfoTest.model.IpcManager");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.hisense.serialInfoTest.model.IpcManager");
            int[] supportSources = getSupportSources();
            parcel2.writeNoException();
            parcel2.writeIntArray(supportSources);
            return true;
        }

        private static class Proxy implements IpcManager {
            public static IpcManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.hisense.serialInfoTest.model.IpcManager
            public int[] getSupportSources() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.serialInfoTest.model.IpcManager");
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportSources();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IpcManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
