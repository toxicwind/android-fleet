package androidx.work.multiprocess;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IWorkManagerImpl extends IInterface {

    public static class Default implements IWorkManagerImpl {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelAllWork(IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelAllWorkByTag(String tag, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelUniqueWork(String name, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelWorkById(String id, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void enqueueContinuation(byte[] request, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void enqueueWorkRequests(byte[] request, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void queryWorkInfo(byte[] request, IWorkManagerImplCallback callback) {
        }

        @Override // androidx.work.multiprocess.IWorkManagerImpl
        public void setProgress(byte[] request, IWorkManagerImplCallback callback) {
        }
    }

    public static abstract class Stub extends Binder implements IWorkManagerImpl {
        public static final String DESCRIPTOR = "androidx.work.multiprocess.IWorkManagerImpl";
        public static final int TRANSACTION_cancelAllWork = 6;
        public static final int TRANSACTION_cancelAllWorkByTag = 4;
        public static final int TRANSACTION_cancelUniqueWork = 5;
        public static final int TRANSACTION_cancelWorkById = 3;
        public static final int TRANSACTION_enqueueContinuation = 2;
        public static final int TRANSACTION_enqueueWorkRequests = 1;
        public static final int TRANSACTION_queryWorkInfo = 7;
        public static final int TRANSACTION_setProgress = 8;

        private static class Proxy implements IWorkManagerImpl {
            public static IWorkManagerImpl sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelAllWork(IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelAllWork(callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelAllWorkByTag(String tag, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(tag);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelAllWorkByTag(tag, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelUniqueWork(String name, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(name);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelUniqueWork(name, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelWorkById(String id, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(id);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelWorkById(id, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void enqueueContinuation(byte[] request, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(request);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enqueueContinuation(request, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void enqueueWorkRequests(byte[] request, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(request);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enqueueWorkRequests(request, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void queryWorkInfo(byte[] request, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(request);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().queryWorkInfo(request, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.work.multiprocess.IWorkManagerImpl
            public void setProgress(byte[] request, IWorkManagerImplCallback callback) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(request);
                    parcelObtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setProgress(request, callback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWorkManagerImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IWorkManagerImpl)) ? new Proxy(obj) : (IWorkManagerImpl) iInterfaceQueryLocalInterface;
        }

        public static IWorkManagerImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IWorkManagerImpl impl) {
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
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    enqueueWorkRequests(data.createByteArray(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    enqueueContinuation(data.createByteArray(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    cancelWorkById(data.readString(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    cancelAllWorkByTag(data.readString(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    cancelUniqueWork(data.readString(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    cancelAllWork(IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    queryWorkInfo(data.createByteArray(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    setProgress(data.createByteArray(), IWorkManagerImplCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void cancelAllWork(IWorkManagerImplCallback callback);

    void cancelAllWorkByTag(String tag, IWorkManagerImplCallback callback);

    void cancelUniqueWork(String name, IWorkManagerImplCallback callback);

    void cancelWorkById(String id, IWorkManagerImplCallback callback);

    void enqueueContinuation(byte[] request, IWorkManagerImplCallback callback);

    void enqueueWorkRequests(byte[] request, IWorkManagerImplCallback callback);

    void queryWorkInfo(byte[] request, IWorkManagerImplCallback callback);

    void setProgress(byte[] request, IWorkManagerImplCallback callback);
}
