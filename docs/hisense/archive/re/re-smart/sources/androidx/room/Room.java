package androidx.room;

import android.content.Context;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.common.net.InetAddresses;

/* JADX INFO: loaded from: classes.dex */
public class Room {
    public static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    public static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    @Deprecated
    public Room() {
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(@NonNull Context context, @NonNull Class<T> cls, @NonNull String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
        }
        return new RoomDatabase.Builder<>(context, cls, str);
    }

    @NonNull
    public static <T, C> T getGeneratedImplementation(Class<C> cls, String str) {
        String str2;
        String name = cls.getPackage().getName();
        String canonicalName = cls.getCanonicalName();
        if (!name.isEmpty()) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        String str3 = canonicalName.replace(InetAddresses.IPV4_DELIMITER, '_') + str;
        try {
            if (name.isEmpty()) {
                str2 = str3;
            } else {
                str2 = name + "." + str3;
            }
            return (T) Class.forName(str2).newInstance();
        } catch (ClassNotFoundException unused) {
            StringBuilder sbA = a.a("cannot find implementation for ");
            sbA.append(cls.getCanonicalName());
            sbA.append(". ");
            sbA.append(str3);
            sbA.append(" does not exist");
            throw new RuntimeException(sbA.toString());
        } catch (IllegalAccessException unused2) {
            StringBuilder sbA2 = a.a("Cannot access the constructor");
            sbA2.append(cls.getCanonicalName());
            throw new RuntimeException(sbA2.toString());
        } catch (InstantiationException unused3) {
            StringBuilder sbA3 = a.a("Failed to create an instance of ");
            sbA3.append(cls.getCanonicalName());
            throw new RuntimeException(sbA3.toString());
        }
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(@NonNull Context context, @NonNull Class<T> cls) {
        return new RoomDatabase.Builder<>(context, cls, null);
    }
}
