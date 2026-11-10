package com.vt.tvplayer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public interface IHMediaPlayerInterface extends IInterface {
    void livetv_setSurface(Surface surface) throws RemoteException;

    public static abstract class Stub extends Binder implements IHMediaPlayerInterface {
        public static IHMediaPlayerInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.vt.tvplayer.IHMediaPlayerInterface");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHMediaPlayerInterface)) {
                return (IHMediaPlayerInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IHMediaPlayerInterface {
            public static IHMediaPlayerInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.vt.tvplayer.IHMediaPlayerInterface
            public void livetv_setSurface(Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vt.tvplayer.IHMediaPlayerInterface");
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().livetv_setSurface(surface);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IHMediaPlayerInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
