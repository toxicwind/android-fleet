package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface INotificationSideChannel extends IInterface {

    public static class Default implements INotificationSideChannel {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancel(String packageName, int id, String tag) {
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancelAll(String packageName) {
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void notify(String packageName, int id, String tag, Notification notification) {
        }
    }

    public static abstract class Stub extends Binder implements INotificationSideChannel {
        public static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        public static final int TRANSACTION_cancel = 2;
        public static final int TRANSACTION_cancelAll = 3;
        public static final int TRANSACTION_notify = 1;

        private static class Proxy implements INotificationSideChannel {
            public static INotificationSideChannel sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public void cancel(String packageName, int id, String tag) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(packageName);
                    parcelObtain.writeInt(id);
                    parcelObtain.writeString(tag);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancel(packageName, id, tag);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public void cancelAll(String packageName) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(packageName);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelAll(packageName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public void notify(String packageName, int id, String tag, Notification notification) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(packageName);
                    parcelObtain.writeInt(id);
                    parcelObtain.writeString(tag);
                    if (notification != null) {
                        parcelObtain.writeInt(1);
                        notification.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notify(packageName, id, tag, notification);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationSideChannel asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof INotificationSideChannel)) ? new Proxy(obj) : (INotificationSideChannel) iInterfaceQueryLocalInterface;
        }

        public static INotificationSideChannel getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(INotificationSideChannel impl) {
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
                notify(data.readString(), data.readInt(), data.readString(), data.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(data) : null);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                cancel(data.readString(), data.readInt(), data.readString());
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                cancelAll(data.readString());
                return true;
            }
            if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            }
            reply.writeString(DESCRIPTOR);
            return true;
        }
    }

    void cancel(String packageName, int id, String tag);

    void cancelAll(String packageName);

    void notify(String packageName, int id, String tag, Notification notification);
}
