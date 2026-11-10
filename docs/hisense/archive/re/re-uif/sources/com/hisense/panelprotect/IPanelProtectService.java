package com.hisense.panelprotect;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IPanelProtectService extends IInterface {
    void registerStatusChangeListener(IStatusChangeListener iStatusChangeListener) throws RemoteException;

    boolean startJb(int i) throws RemoteException;

    boolean startOffrs(int i, int i2) throws RemoteException;

    void unregisterStatusChangeListener(IStatusChangeListener iStatusChangeListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IPanelProtectService {
        public static IPanelProtectService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.hisense.panelprotect.IPanelProtectService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPanelProtectService)) {
                return (IPanelProtectService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IPanelProtectService {
            public static IPanelProtectService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.hisense.panelprotect.IPanelProtectService
            public boolean startOffrs(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.panelprotect.IPanelProtectService");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startOffrs(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hisense.panelprotect.IPanelProtectService
            public boolean startJb(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.panelprotect.IPanelProtectService");
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startJb(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hisense.panelprotect.IPanelProtectService
            public void registerStatusChangeListener(IStatusChangeListener iStatusChangeListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.panelprotect.IPanelProtectService");
                    parcelObtain.writeStrongBinder(iStatusChangeListener != null ? iStatusChangeListener.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerStatusChangeListener(iStatusChangeListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.hisense.panelprotect.IPanelProtectService
            public void unregisterStatusChangeListener(IStatusChangeListener iStatusChangeListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hisense.panelprotect.IPanelProtectService");
                    parcelObtain.writeStrongBinder(iStatusChangeListener != null ? iStatusChangeListener.asBinder() : null);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterStatusChangeListener(iStatusChangeListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IPanelProtectService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
