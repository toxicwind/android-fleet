package com.regza.persistent.logd;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IRegzaPersistentLogService extends IInterface {
    void saveLog(IRegzaPersistentLogServiceCallback iRegzaPersistentLogServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IRegzaPersistentLogService {
        public static IRegzaPersistentLogService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.regza.persistent.logd.IRegzaPersistentLogService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRegzaPersistentLogService)) {
                return (IRegzaPersistentLogService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IRegzaPersistentLogService {
            public static IRegzaPersistentLogService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.regza.persistent.logd.IRegzaPersistentLogService
            public void saveLog(IRegzaPersistentLogServiceCallback iRegzaPersistentLogServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.regza.persistent.logd.IRegzaPersistentLogService");
                    parcelObtain.writeStrongBinder(iRegzaPersistentLogServiceCallback != null ? iRegzaPersistentLogServiceCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().saveLog(iRegzaPersistentLogServiceCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IRegzaPersistentLogService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
