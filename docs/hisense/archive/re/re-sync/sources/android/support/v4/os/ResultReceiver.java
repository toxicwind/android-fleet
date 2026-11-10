package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f2a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.a.a.b.a f3b;

    public static class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ResultReceiver[] newArray(int i) {
            return new ResultReceiver[i];
        }
    }

    public class b extends a.a.a.b.a.AbstractBinderC0002a {
        public b() {
        }

        @Override // a.a.a.b.a
        public void b(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f2a;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.a();
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f5a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f6b;

        public c(int i, Bundle bundle) {
            this.f5a = i;
            this.f6b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a();
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f3b = a.a.a.b.a.AbstractBinderC0002a.c(parcel.readStrongBinder());
    }

    public void a() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f3b == null) {
                this.f3b = new b();
            }
            parcel.writeStrongBinder(this.f3b.asBinder());
        }
    }
}
