package com.vt.interact;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IHisTvInteractService extends IInterface {

    public static class Default implements IHisTvInteractService {
        @Override // com.vt.interact.IHisTvInteractService
        public void OnReceivedHELD(ICSignalingMsg iCSignalingMsg, ICHTMLEntryPkg[] iCHTMLEntryPkgArr) throws RemoteException {
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void OnReceivedNRTFile(ICFilesMsg iCFilesMsg, ICFileDetail[] iCFileDetailArr) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vt.interact.IHisTvInteractService
        public int notifyOpratorInteract(int i, String str, String str2, String str3, String str4) throws RemoteException {
            return 0;
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void registerListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void unregisterListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
        }
    }

    void OnReceivedHELD(ICSignalingMsg iCSignalingMsg, ICHTMLEntryPkg[] iCHTMLEntryPkgArr) throws RemoteException;

    void OnReceivedNRTFile(ICFilesMsg iCFilesMsg, ICFileDetail[] iCFileDetailArr) throws RemoteException;

    int notifyOpratorInteract(int i, String str, String str2, String str3, String str4) throws RemoteException;

    void registerListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException;

    void unregisterListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IHisTvInteractService {
        private static final String DESCRIPTOR = "com.vt.interact.IHisTvInteractService";
        static final int TRANSACTION_OnReceivedHELD = 3;
        static final int TRANSACTION_OnReceivedNRTFile = 2;
        static final int TRANSACTION_notifyOpratorInteract = 1;
        static final int TRANSACTION_registerListener = 4;
        static final int TRANSACTION_unregisterListener = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHisTvInteractService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHisTvInteractService)) {
                return (IHisTvInteractService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int iNotifyOpratorInteract = notifyOpratorInteract(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iNotifyOpratorInteract);
                return true;
            }
            if (i == TRANSACTION_OnReceivedNRTFile) {
                parcel.enforceInterface(DESCRIPTOR);
                OnReceivedNRTFile(parcel.readInt() != 0 ? ICFilesMsg.CREATOR.createFromParcel(parcel) : null, (ICFileDetail[]) parcel.createTypedArray(ICFileDetail.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            if (i == TRANSACTION_OnReceivedHELD) {
                parcel.enforceInterface(DESCRIPTOR);
                OnReceivedHELD(parcel.readInt() != 0 ? ICSignalingMsg.CREATOR.createFromParcel(parcel) : null, (ICHTMLEntryPkg[]) parcel.createTypedArray(ICHTMLEntryPkg.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            if (i == TRANSACTION_registerListener) {
                parcel.enforceInterface(DESCRIPTOR);
                registerListener(IHisTvInteractServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != TRANSACTION_unregisterListener) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            unregisterListener(IHisTvInteractServiceListener.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IHisTvInteractService {
            public static IHisTvInteractService sDefaultImpl;
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

            @Override // com.vt.interact.IHisTvInteractService
            public int notifyOpratorInteract(int i, String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyOpratorInteract(i, str, str2, str3, str4);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractService
            public void OnReceivedNRTFile(ICFilesMsg iCFilesMsg, ICFileDetail[] iCFileDetailArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iCFilesMsg != null) {
                        parcelObtain.writeInt(1);
                        iCFilesMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(iCFileDetailArr, 0);
                    if (!this.mRemote.transact(Stub.TRANSACTION_OnReceivedNRTFile, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnReceivedNRTFile(iCFilesMsg, iCFileDetailArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractService
            public void OnReceivedHELD(ICSignalingMsg iCSignalingMsg, ICHTMLEntryPkg[] iCHTMLEntryPkgArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iCSignalingMsg != null) {
                        parcelObtain.writeInt(1);
                        iCSignalingMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(iCHTMLEntryPkgArr, 0);
                    if (!this.mRemote.transact(Stub.TRANSACTION_OnReceivedHELD, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnReceivedHELD(iCSignalingMsg, iCHTMLEntryPkgArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractService
            public void registerListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iHisTvInteractServiceListener != null ? iHisTvInteractServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(Stub.TRANSACTION_registerListener, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iHisTvInteractServiceListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vt.interact.IHisTvInteractService
            public void unregisterListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iHisTvInteractServiceListener != null ? iHisTvInteractServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(Stub.TRANSACTION_unregisterListener, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iHisTvInteractServiceListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHisTvInteractService iHisTvInteractService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHisTvInteractService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHisTvInteractService;
            return true;
        }

        public static IHisTvInteractService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
