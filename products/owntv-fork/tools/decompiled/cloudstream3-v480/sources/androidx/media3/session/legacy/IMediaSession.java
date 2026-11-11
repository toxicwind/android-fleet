package androidx.media3.session.legacy;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public interface IMediaSession extends IInterface {
    PlaybackStateCompat getPlaybackState() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    Bundle getSessionInfo() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getSessionInfo = 50;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaSession)) {
                return (IMediaSession) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 3) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                registerCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                return true;
            }
            if (i == 28) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                PlaybackStateCompat playbackState = getPlaybackState();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                if (playbackState != null) {
                    ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(1);
                    playbackState.writeToParcel(parcel2, 1);
                } else {
                    ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(0);
                }
                return true;
            }
            if (i == 37) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int repeatMode = getRepeatMode();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(repeatMode);
                return true;
            }
            if (i == 45) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean zIsCaptioningEnabled = isCaptioningEnabled();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(zIsCaptioningEnabled ? 1 : 0);
                return true;
            }
            if (i == 47) {
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int shuffleMode = getShuffleMode();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(shuffleMode);
                return true;
            }
            if (i != 50) {
                if (i == 1598968902) {
                    ((Parcel) Preconditions.checkNotNull(parcel2)).writeString("android.support.v4.media.session.IMediaSession");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle sessionInfo = getSessionInfo();
            ((Parcel) Preconditions.checkNotNull(parcel2)).writeNoException();
            if (sessionInfo != null) {
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(1);
                sessionInfo.writeToParcel(parcel2, 1);
            } else {
                ((Parcel) Preconditions.checkNotNull(parcel2)).writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IMediaSession {
            public static IMediaSession defaultImpl;
            private IBinder remote;

            Proxy(IBinder iBinder) {
                this.remote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.remote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.media.session.IMediaSession";
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.remote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).registerCallbackListener(iMediaControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.remote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).unregisterCallbackListener(iMediaControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                PlaybackStateCompat playbackStateCompatCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        playbackStateCompatCreateFromParcel = ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).getPlaybackState();
                    } else {
                        parcelObtain2.readException();
                        playbackStateCompatCreateFromParcel = parcelObtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                    }
                    return playbackStateCompatCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isCaptioningEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).isCaptioningEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRepeatMode() throws RemoteException {
                int repeatMode;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        repeatMode = ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).getRepeatMode();
                    } else {
                        parcelObtain2.readException();
                        repeatMode = parcelObtain2.readInt();
                    }
                    return repeatMode;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getShuffleMode() throws RemoteException {
                int shuffleMode;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        shuffleMode = ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).getShuffleMode();
                    } else {
                        parcelObtain2.readException();
                        shuffleMode = parcelObtain2.readInt();
                    }
                    return shuffleMode;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getSessionInfo() throws RemoteException {
                Bundle sessionInfo;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.remote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        sessionInfo = ((IMediaSession) Preconditions.checkNotNull(Stub.getDefaultImpl())).getSessionInfo();
                    } else {
                        parcelObtain2.readException();
                        sessionInfo = parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                    }
                    return sessionInfo;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaSession iMediaSession) {
            if (Proxy.defaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaSession == null) {
                return false;
            }
            Proxy.defaultImpl = iMediaSession;
            return true;
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.defaultImpl;
        }
    }
}
