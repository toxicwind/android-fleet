package androidx.work.multiprocess;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IWorkManagerImplCallback extends IInterface {

    public static class Default implements IWorkManagerImplCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.work.multiprocess.IWorkManagerImplCallback
        public void onFailure(String error) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImplCallback
        public void onSuccess(byte[] response) {
        }
    }

    public static abstract class Stub extends Binder implements IWorkManagerImplCallback {
        public static final String DESCRIPTOR = "androidx.work.multiprocess.IWorkManagerImplCallback";
        public static final int TRANSACTION_onFailure = 2;
        public static final int TRANSACTION_onSuccess = 1;

        private static class Proxy implements IWorkManagerImplCallback {
            public static IWorkManagerImplCallback sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // androidx.work.multiprocess.IWorkManagerImplCallback
            public void onFailure(String error) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(error);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFailure(error);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImplCallback
            public void onSuccess(byte[] response) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(response);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuccess(response);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWorkManagerImplCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IWorkManagerImplCallback)) ? new Proxy(obj) : (IWorkManagerImplCallback) iInterfaceQueryLocalInterface;
        }

        public static IWorkManagerImplCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWorkManagerImplCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onSuccess(data.createByteArray());
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onFailure(data.readString());
                return true;
            }
            if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            }
            reply.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onFailure(String error);

    void onSuccess(byte[] response);
}
