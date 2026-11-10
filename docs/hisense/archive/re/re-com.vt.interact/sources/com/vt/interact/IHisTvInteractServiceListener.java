package com.vt.interact;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IHisTvInteractServiceListener extends IInterface {

    public static class Default implements IHisTvInteractServiceListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public ACIFSignalingMsg getSignaling(String str) throws RemoteException {
            return null;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public int getTvserverState(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public String getTvserverStateForString(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public String interactCallTvplayer(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public void notifyRequestAppKey(int[] iArr) throws RemoteException {
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public int onInteractState(int i, String str, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.vt.interact.IHisTvInteractServiceListener
        public int setTvserverOperator(int i, String str, String str2, String str3, String str4) throws RemoteException {
            return 0;
        }
    }

    ACIFSignalingMsg getSignaling(String str) throws RemoteException;

    int getTvserverState(int i, String str, String str2) throws RemoteException;

    String getTvserverStateForString(int i, String str, String str2) throws RemoteException;

    String interactCallTvplayer(String str, String str2, String str3) throws RemoteException;

    void notifyRequestAppKey(int[] iArr) throws RemoteException;

    int onInteractState(int i, String str, int i2) throws RemoteException;

    int setTvserverOperator(int i, String str, String str2, String str3, String str4) throws RemoteException;

    public static abstract class Stub extends Binder implements IHisTvInteractServiceListener {
        private static final String DESCRIPTOR = "com.vt.interact.IHisTvInteractServiceListener";
        static final int TRANSACTION_getSignaling = 5;
        static final int TRANSACTION_getTvserverState = 3;
        static final int TRANSACTION_getTvserverStateForString = 4;
        static final int TRANSACTION_interactCallTvplayer = 7;
        static final int TRANSACTION_notifyRequestAppKey = 6;
        static final int TRANSACTION_onInteractState = 1;
        static final int TRANSACTION_setTvserverOperator = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHisTvInteractServiceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHisTvInteractServiceListener)) {
                return (IHisTvInteractServiceListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOnInteractState = onInteractState(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOnInteractState);
                    return true;
                case TRANSACTION_setTvserverOperator /* 2 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tvserverOperator = setTvserverOperator(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(tvserverOperator);
                    return true;
                case TRANSACTION_getTvserverState /* 3 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tvserverState = getTvserverState(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(tvserverState);
                    return true;
                case TRANSACTION_getTvserverStateForString /* 4 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String tvserverStateForString = getTvserverStateForString(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(tvserverStateForString);
                    return true;
                case TRANSACTION_getSignaling /* 5 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    ACIFSignalingMsg signaling = getSignaling(parcel.readString());
                    parcel2.writeNoException();
                    if (signaling != null) {
                        parcel2.writeInt(1);
                        signaling.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_notifyRequestAppKey /* 6 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRequestAppKey(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interactCallTvplayer /* 7 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strInteractCallTvplayer = interactCallTvplayer(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strInteractCallTvplayer);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IHisTvInteractServiceListener {
            public static IHisTvInteractServiceListener sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public int onInteractState(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onInteractState(i, str, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public int setTvserverOperator(int i, String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (!this.mRemote.transact(Stub.TRANSACTION_setTvserverOperator, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTvserverOperator(i, str, str2, str3, str4);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public int getTvserverState(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(Stub.TRANSACTION_getTvserverState, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTvserverState(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public String getTvserverStateForString(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(Stub.TRANSACTION_getTvserverStateForString, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTvserverStateForString(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public ACIFSignalingMsg getSignaling(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(Stub.TRANSACTION_getSignaling, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSignaling(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ACIFSignalingMsg.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public void notifyRequestAppKey(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(Stub.TRANSACTION_notifyRequestAppKey, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRequestAppKey(iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractServiceListener
            public String interactCallTvplayer(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(Stub.TRANSACTION_interactCallTvplayer, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().interactCallTvplayer(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHisTvInteractServiceListener iHisTvInteractServiceListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHisTvInteractServiceListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHisTvInteractServiceListener;
            return true;
        }

        public static IHisTvInteractServiceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
