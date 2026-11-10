package androidx.room;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import b.a.a.a.a;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {

    @Nullable
    public DatabaseConfiguration mConfiguration;

    @NonNull
    public final Delegate mDelegate;

    @NonNull
    public final String mIdentityHash;

    @NonNull
    public final String mLegacyHash;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static abstract class Delegate {
        public final int version;

        public Delegate(int i) {
            this.version = i;
        }

        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @NonNull
        public ValidationResult onValidateSchema(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            validateMigration(supportSQLiteDatabase);
            return new ValidationResult(true, null);
        }

        @Deprecated
        public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            throw new UnsupportedOperationException("validateMigration is deprecated");
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static class ValidationResult {

        @Nullable
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, @Nullable String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    public RoomOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration, @NonNull Delegate delegate, @NonNull String str) {
        super(delegate.version);
        this.mConfiguration = databaseConfiguration;
        this.mDelegate = delegate;
        this.mIdentityHash = "";
        this.mLegacyHash = str;
    }

    private void checkIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (!hasRoomMasterTable(supportSQLiteDatabase)) {
            ValidationResult validationResultOnValidateSchema = this.mDelegate.onValidateSchema(supportSQLiteDatabase);
            if (validationResultOnValidateSchema.isValid) {
                this.mDelegate.onPostMigrate(supportSQLiteDatabase);
                updateIdentity(supportSQLiteDatabase);
                return;
            } else {
                StringBuilder sbA = a.a("Pre-packaged database has an invalid schema: ");
                sbA.append(validationResultOnValidateSchema.expectedFoundMsg);
                throw new IllegalStateException(sbA.toString());
            }
        }
        Cursor cursorQuery = supportSQLiteDatabase.query(new SimpleSQLiteQuery(RoomMasterTable.READ_QUERY));
        try {
            String string = cursorQuery.moveToFirst() ? cursorQuery.getString(0) : null;
            cursorQuery.close();
            if (!this.mIdentityHash.equals(string) && !this.mLegacyHash.equals(string)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    private void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
    }

    public static boolean hasEmptySchema(SupportSQLiteDatabase supportSQLiteDatabase) {
        Cursor cursorQuery = supportSQLiteDatabase.query("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (cursorQuery.moveToFirst() && cursorQuery.getInt(0) == 0) {
                z = true;
            }
            return z;
        } finally {
            cursorQuery.close();
        }
    }

    public static boolean hasRoomMasterTable(SupportSQLiteDatabase supportSQLiteDatabase) {
        Cursor cursorQuery = supportSQLiteDatabase.query("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (cursorQuery.moveToFirst() && cursorQuery.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            cursorQuery.close();
        }
    }

    private void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
        supportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.mIdentityHash));
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        boolean zHasEmptySchema = hasEmptySchema(supportSQLiteDatabase);
        this.mDelegate.createAllTables(supportSQLiteDatabase);
        if (!zHasEmptySchema) {
            ValidationResult validationResultOnValidateSchema = this.mDelegate.onValidateSchema(supportSQLiteDatabase);
            if (!validationResultOnValidateSchema.isValid) {
                StringBuilder sbA = a.a("Pre-packaged database has an invalid schema: ");
                sbA.append(validationResultOnValidateSchema.expectedFoundMsg);
                throw new IllegalStateException(sbA.toString());
            }
        }
        updateIdentity(supportSQLiteDatabase);
        this.mDelegate.onCreate(supportSQLiteDatabase);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        checkIdentity(supportSQLiteDatabase);
        this.mDelegate.onOpen(supportSQLiteDatabase);
        this.mConfiguration = null;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        boolean z;
        List<Migration> listFindMigrationPath;
        DatabaseConfiguration databaseConfiguration = this.mConfiguration;
        if (databaseConfiguration == null || (listFindMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(i, i2)) == null) {
            z = false;
        } else {
            this.mDelegate.onPreMigrate(supportSQLiteDatabase);
            Iterator<Migration> it = listFindMigrationPath.iterator();
            while (it.hasNext()) {
                it.next().migrate(supportSQLiteDatabase);
            }
            ValidationResult validationResultOnValidateSchema = this.mDelegate.onValidateSchema(supportSQLiteDatabase);
            if (!validationResultOnValidateSchema.isValid) {
                StringBuilder sbA = a.a("Migration didn't properly handle: ");
                sbA.append(validationResultOnValidateSchema.expectedFoundMsg);
                throw new IllegalStateException(sbA.toString());
            }
            this.mDelegate.onPostMigrate(supportSQLiteDatabase);
            updateIdentity(supportSQLiteDatabase);
            z = true;
        }
        if (z) {
            return;
        }
        DatabaseConfiguration databaseConfiguration2 = this.mConfiguration;
        if (databaseConfiguration2 == null || databaseConfiguration2.isMigrationRequired(i, i2)) {
            throw new IllegalStateException(a.a("A migration from ", i, " to ", i2, " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods."));
        }
        this.mDelegate.dropAllTables(supportSQLiteDatabase);
        this.mDelegate.createAllTables(supportSQLiteDatabase);
    }

    public RoomOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration, @NonNull Delegate delegate, @NonNull String str, @NonNull String str2) {
        super(delegate.version);
        this.mConfiguration = databaseConfiguration;
        this.mDelegate = delegate;
        this.mIdentityHash = str;
        this.mLegacyHash = str2;
    }
}
