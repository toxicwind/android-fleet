package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {

    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zac();
    public static final Builder zamb = new zab(new String[0], null);
    public boolean mClosed;

    @SafeParcelable.VersionField(id = 1000)
    public final int zali;

    @SafeParcelable.Field(getter = "getColumns", id = 1)
    public final String[] zalt;
    public Bundle zalu;

    @SafeParcelable.Field(getter = "getWindows", id = 2)
    public final CursorWindow[] zalv;

    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    public final int zalw;

    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    public final Bundle zalx;
    public int[] zaly;
    public int zalz;
    public boolean zama;

    public static class zaa extends RuntimeException {
        public zaa(String str) {
            super(str);
        }
    }

    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) Bundle bundle) {
        this.mClosed = false;
        this.zama = true;
        this.zali = i;
        this.zalt = strArr;
        this.zalv = cursorWindowArr;
        this.zalw = i2;
        this.zalx = bundle;
    }

    @KeepForSdk
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null);
    }

    @KeepForSdk
    public static DataHolder empty(int i) {
        Builder builder = zamb;
        return new DataHolder(builder.zalt, zaa(builder, -1), i, (Bundle) null);
    }

    public static CursorWindow[] zaa(Builder builder, int i) {
        if (builder.zalt.length == 0) {
            return new CursorWindow[0];
        }
        List<HashMap<String, Object>> listSubList = (i < 0 || i >= builder.zamc.size()) ? builder.zamc : builder.zamc.subList(0, i);
        int size = listSubList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zalt.length);
        boolean z = false;
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        while (i2 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i2);
                    sb.append(")");
                    Log.d("DataHolder", sb.toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalt.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                HashMap<String, Object> map = listSubList.get(i2);
                int i3 = 0;
                boolean zPutDouble = true;
                while (true) {
                    String[] strArr = builder.zalt;
                    if (i3 >= strArr.length || !zPutDouble) {
                        break;
                    }
                    String str = strArr[i3];
                    Object obj = map.get(str);
                    if (obj == null) {
                        zPutDouble = cursorWindow2.putNull(i2, i3);
                    } else if (obj instanceof String) {
                        zPutDouble = cursorWindow2.putString((String) obj, i2, i3);
                    } else if (obj instanceof Long) {
                        zPutDouble = cursorWindow2.putLong(((Long) obj).longValue(), i2, i3);
                    } else if (obj instanceof Integer) {
                        zPutDouble = cursorWindow2.putLong(((Integer) obj).intValue(), i2, i3);
                    } else if (obj instanceof Boolean) {
                        zPutDouble = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i2, i3);
                    } else if (obj instanceof byte[]) {
                        zPutDouble = cursorWindow2.putBlob((byte[]) obj, i2, i3);
                    } else if (obj instanceof Double) {
                        zPutDouble = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i3);
                    } else {
                        if (!(obj instanceof Float)) {
                            String strValueOf = String.valueOf(obj);
                            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + strValueOf.length());
                            sb2.append("Unsupported object for column ");
                            sb2.append(str);
                            sb2.append(": ");
                            sb2.append(strValueOf);
                            throw new IllegalArgumentException(sb2.toString());
                        }
                        zPutDouble = cursorWindow2.putDouble(((Float) obj).floatValue(), i2, i3);
                    }
                    i3++;
                }
                if (zPutDouble) {
                    z = false;
                } else {
                    if (z) {
                        throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i2);
                    sb3.append(" - allocating new window.");
                    Log.d("DataHolder", sb3.toString());
                    cursorWindow2.freeLastRow();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalt.length);
                    arrayList.add(cursorWindow2);
                    i2--;
                    z = true;
                }
                i2++;
            } catch (RuntimeException e2) {
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ((CursorWindow) arrayList.get(i4)).close();
                }
                throw e2;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zalv.length; i++) {
                    this.zalv[i].close();
                }
            }
        }
    }

    public final void finalize() throws Throwable {
        try {
            if (this.zama && this.zalv.length > 0 && !isClosed()) {
                close();
                String string = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(string).length() + MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(string);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public final boolean getBoolean(String str, int i, int i2) {
        zaa(str, i);
        return Long.valueOf(this.zalv[i2].getLong(i, this.zalu.getInt(str))).longValue() == 1;
    }

    @KeepForSdk
    public final byte[] getByteArray(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getBlob(i, this.zalu.getInt(str));
    }

    @KeepForSdk
    public final int getCount() {
        return this.zalz;
    }

    @KeepForSdk
    public final int getInteger(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getInt(i, this.zalu.getInt(str));
    }

    @KeepForSdk
    public final long getLong(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getLong(i, this.zalu.getInt(str));
    }

    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zalx;
    }

    @KeepForSdk
    public final int getStatusCode() {
        return this.zalw;
    }

    @KeepForSdk
    public final String getString(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getString(i, this.zalu.getInt(str));
    }

    @KeepForSdk
    public final int getWindowIndex(int i) {
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zalz);
        while (true) {
            int[] iArr = this.zaly;
            if (i2 >= iArr.length) {
                break;
            }
            if (i < iArr[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.zaly.length ? i2 - 1 : i2;
    }

    @KeepForSdk
    public final boolean hasColumn(String str) {
        return this.zalu.containsKey(str);
    }

    @KeepForSdk
    public final boolean hasNull(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].isNull(i, this.zalu.getInt(str));
    }

    @KeepForSdk
    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zalt, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zalv, i, false);
        int statusCode = getStatusCode();
        parcel.writeInt(262147);
        parcel.writeInt(statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        int i2 = this.zali;
        parcel.writeInt(263144);
        parcel.writeInt(i2);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final double zab(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getDouble(i, this.zalu.getInt(str));
    }

    public final void zaby() {
        this.zalu = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zalt;
            if (i2 >= strArr.length) {
                break;
            }
            this.zalu.putInt(strArr[i2], i2);
            i2++;
        }
        this.zaly = new int[this.zalv.length];
        int numRows = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zalv;
            if (i >= cursorWindowArr.length) {
                this.zalz = numRows;
                return;
            }
            this.zaly[i] = numRows;
            numRows += this.zalv[i].getNumRows() - (numRows - cursorWindowArr[i].getStartPosition());
            i++;
        }
    }

    @KeepForSdk
    public static class Builder {
        public final String[] zalt;
        public final ArrayList<HashMap<String, Object>> zamc;
        public final String zamd;
        public final HashMap<Object, Integer> zame;
        public boolean zamf;
        public String zamg;

        public Builder(String[] strArr, String str) {
            Preconditions.checkNotNull(strArr);
            this.zalt = strArr;
            this.zamc = new ArrayList<>();
            this.zamd = str;
            this.zame = new HashMap<>();
            this.zamf = false;
            this.zamg = null;
        }

        @KeepForSdk
        public DataHolder build(int i) {
            return new DataHolder(this.zalt, DataHolder.zaa(this, -1), i, (Bundle) null);
        }

        @KeepForSdk
        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zaa(map);
        }

        public Builder zaa(HashMap<String, Object> map) {
            Object obj;
            int iIntValue;
            Asserts.checkNotNull(map);
            String str = this.zamd;
            if (str == null || (obj = map.get(str)) == null) {
                iIntValue = -1;
            } else {
                Integer num = this.zame.get(obj);
                if (num == null) {
                    this.zame.put(obj, Integer.valueOf(this.zamc.size()));
                    iIntValue = -1;
                } else {
                    iIntValue = num.intValue();
                }
            }
            if (iIntValue == -1) {
                this.zamc.add(map);
            } else {
                this.zamc.remove(iIntValue);
                this.zamc.add(iIntValue, map);
            }
            this.zamf = false;
            return this;
        }

        @KeepForSdk
        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this.zalt, DataHolder.zaa(this, -1), i, bundle);
        }

        public /* synthetic */ Builder(String[] strArr, String str, zab zabVar) {
            this(strArr, null);
        }
    }

    @KeepForSdk
    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zama = true;
        this.zali = 1;
        Preconditions.checkNotNull(strArr);
        this.zalt = strArr;
        Preconditions.checkNotNull(cursorWindowArr);
        this.zalv = cursorWindowArr;
        this.zalw = i;
        this.zalx = bundle;
        zaby();
    }

    public DataHolder(CursorWrapper cursorWrapper, int i, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i, bundle);
    }

    @KeepForSdk
    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        CursorWrapper cursorWrapper = new CursorWrapper(cursor);
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i, bundle);
    }

    public DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zalt, zaa(builder, -1), i, (Bundle) null);
    }

    public DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.zalt, zaa(builder, -1), i, bundle);
    }

    public /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, zab zabVar) {
        this(builder.zalt, zaa(builder, -1), i, bundle);
    }

    public /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, zab zabVar) {
        this(builder.zalt, zaa(builder, -1), i, (Bundle) null);
    }

    public static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        int startPosition;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count && cursorWrapper.moveToPosition(startPosition)) {
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private final void zaa(String str, int i) {
        Bundle bundle = this.zalu;
        if (bundle != null && bundle.containsKey(str)) {
            if (!isClosed()) {
                if (i < 0 || i >= this.zalz) {
                    throw new CursorIndexOutOfBoundsException(i, this.zalz);
                }
                return;
            }
            throw new IllegalArgumentException("Buffer is closed.");
        }
        String strValueOf = String.valueOf(str);
        throw new IllegalArgumentException(strValueOf.length() != 0 ? "No such column: ".concat(strValueOf) : new String("No such column: "));
    }

    public final float zaa(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getFloat(i, this.zalu.getInt(str));
    }

    public final void zaa(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zaa(str, i);
        this.zalv[i2].copyStringToBuffer(i, this.zalu.getInt(str), charArrayBuffer);
    }
}
