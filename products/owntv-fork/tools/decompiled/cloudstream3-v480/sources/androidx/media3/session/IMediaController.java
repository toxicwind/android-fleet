package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IMediaController extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaController";

    public static class Default implements IMediaController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.media3.session.IMediaController
        public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onConnected(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onCustomCommandProgressUpdate(int i, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onDisconnected(int i) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onError(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onExtrasChanged(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onLibraryResult(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onRenderedFirstFrame(int i) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSessionResult(int i, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException {
        }
    }

    void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException;

    void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException;

    void onConnected(int i, Bundle bundle) throws RemoteException;

    void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onCustomCommandProgressUpdate(int i, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException;

    void onDisconnected(int i) throws RemoteException;

    void onError(int i, Bundle bundle) throws RemoteException;

    void onExtrasChanged(int i, Bundle bundle) throws RemoteException;

    void onLibraryResult(int i, Bundle bundle) throws RemoteException;

    void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException;

    void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException;

    void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onRenderedFirstFrame(int i) throws RemoteException;

    void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException;

    void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException;

    void onSessionResult(int i, Bundle bundle) throws RemoteException;

    void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException;

    void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException;

    void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController {
        static final int TRANSACTION_onAvailableCommandsChangedFromPlayer = 3009;
        static final int TRANSACTION_onAvailableCommandsChangedFromSession = 3010;
        static final int TRANSACTION_onChildrenChanged = 4001;
        static final int TRANSACTION_onConnected = 3001;
        static final int TRANSACTION_onCustomCommand = 3005;
        static final int TRANSACTION_onCustomCommandProgressUpdate = 3017;
        static final int TRANSACTION_onDisconnected = 3006;
        static final int TRANSACTION_onError = 3015;
        static final int TRANSACTION_onExtrasChanged = 3012;
        static final int TRANSACTION_onLibraryResult = 3003;
        static final int TRANSACTION_onPeriodicSessionPositionInfoChanged = 3008;
        static final int TRANSACTION_onPlayerInfoChanged = 3007;
        static final int TRANSACTION_onPlayerInfoChangedWithExclusions = 3013;
        static final int TRANSACTION_onRenderedFirstFrame = 3011;
        static final int TRANSACTION_onSearchResultChanged = 4002;
        static final int TRANSACTION_onSessionActivityChanged = 3014;
        static final int TRANSACTION_onSessionResult = 3002;
        static final int TRANSACTION_onSetCustomLayout = 3004;
        static final int TRANSACTION_onSetMediaButtonPreferences = 3016;
        static final int TRANSACTION_onSurfaceSizeChanged = 3018;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMediaController.DESCRIPTOR);
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IMediaController.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaController)) {
                return (IMediaController) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMediaController.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMediaController.DESCRIPTOR);
                return true;
            }
            if (i == 4001) {
                onChildrenChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            } else if (i != 4002) {
                switch (i) {
                    case 3001:
                        onConnected(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3002:
                        onSessionResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3003:
                        onLibraryResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3004:
                        onSetCustomLayout(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                        break;
                    case TRANSACTION_onCustomCommand /* 3005 */:
                        onCustomCommand(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onDisconnected /* 3006 */:
                        onDisconnected(parcel.readInt());
                        break;
                    case TRANSACTION_onPlayerInfoChanged /* 3007 */:
                        onPlayerInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                        break;
                    case TRANSACTION_onPeriodicSessionPositionInfoChanged /* 3008 */:
                        onPeriodicSessionPositionInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onAvailableCommandsChangedFromPlayer /* 3009 */:
                        onAvailableCommandsChangedFromPlayer(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onAvailableCommandsChangedFromSession /* 3010 */:
                        onAvailableCommandsChangedFromSession(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onRenderedFirstFrame /* 3011 */:
                        onRenderedFirstFrame(parcel.readInt());
                        break;
                    case TRANSACTION_onExtrasChanged /* 3012 */:
                        onExtrasChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onPlayerInfoChangedWithExclusions /* 3013 */:
                        onPlayerInfoChangedWithExclusions(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onSessionActivityChanged /* 3014 */:
                        onSessionActivityChanged(parcel.readInt(), (PendingIntent) _Parcel.readTypedObject(parcel, PendingIntent.CREATOR));
                        break;
                    case TRANSACTION_onError /* 3015 */:
                        onError(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onSetMediaButtonPreferences /* 3016 */:
                        onSetMediaButtonPreferences(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                        break;
                    case TRANSACTION_onCustomCommandProgressUpdate /* 3017 */:
                        onCustomCommandProgressUpdate(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onSurfaceSizeChanged /* 3018 */:
                        onSurfaceSizeChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                onSearchResultChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            }
            return true;
        }

        private static class Proxy implements IMediaController {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaController.DESCRIPTOR;
            }

            @Override // androidx.media3.session.IMediaController
            public void onConnected(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSessionResult(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onLibraryResult(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3003, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedList(parcelObtain, list, 0);
                    this.mRemote.transact(3004, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommand, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onCustomCommandProgressUpdate(int i, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle3, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommandProgressUpdate, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onDisconnected(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_onDisconnected, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChangedWithExclusions, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPeriodicSessionPositionInfoChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromPlayer, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromSession, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.mRemote.transact(Stub.TRANSACTION_onSurfaceSizeChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onRenderedFirstFrame(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_onRenderedFirstFrame, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onExtrasChanged(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onExtrasChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, pendingIntent, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSessionActivityChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onError(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onError, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedList(parcelObtain, list, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSetMediaButtonPreferences, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, list.get(i2), i);
            }
        }
    }
}
