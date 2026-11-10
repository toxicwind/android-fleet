package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.protobuf.MessageSchema;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class FileProvider extends ContentProvider {
    public static final String ATTR_NAME = "name";
    public static final String ATTR_PATH = "path";
    public static final String DISPLAYNAME_FIELD = "displayName";
    public static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    public static final String TAG_CACHE_PATH = "cache-path";
    public static final String TAG_EXTERNAL = "external-path";
    public static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    public static final String TAG_EXTERNAL_FILES = "external-files-path";
    public static final String TAG_EXTERNAL_MEDIA = "external-media-path";
    public static final String TAG_FILES_PATH = "files-path";
    public static final String TAG_ROOT_PATH = "root-path";
    public PathStrategy mStrategy;
    public static final String[] COLUMNS = {"_display_name", "_size"};
    public static final File DEVICE_ROOT = new File("/");

    @GuardedBy("sCache")
    public static HashMap<String, PathStrategy> sCache = new HashMap<>();

    interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    static class SimplePathStrategy implements PathStrategy {
        public final String mAuthority;
        public final HashMap<String, File> mRoots = new HashMap<>();

        public SimplePathStrategy(String authority) {
            this.mAuthority = authority;
        }

        public void addRoot(String name, File root) {
            if (TextUtils.isEmpty(name)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                this.mRoots.put(name, root.getCanonicalFile());
            } catch (IOException e2) {
                throw new IllegalArgumentException(a.a("Failed to resolve canonical path for ", root), e2);
            }
        }

        @Override // androidx.core.content.FileProvider.PathStrategy
        public File getFileForUri(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int iIndexOf = encodedPath.indexOf(47, 1);
            String strDecode = Uri.decode(encodedPath.substring(1, iIndexOf));
            String strDecode2 = Uri.decode(encodedPath.substring(iIndexOf + 1));
            File file = this.mRoots.get(strDecode);
            if (file == null) {
                throw new IllegalArgumentException(a.a("Unable to find configured root for ", uri));
            }
            File file2 = new File(file, strDecode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException(a.a("Failed to resolve canonical path for ", file2));
            }
        }

        @Override // androidx.core.content.FileProvider.PathStrategy
        public Uri getUriForFile(File file) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.mRoots.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException(a.a("Failed to find configured root that contains ", canonicalPath));
                }
                String path2 = entry.getValue().getPath();
                return new Uri.Builder().scheme("content").authority(this.mAuthority).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(path2.endsWith("/") ? canonicalPath.substring(path2.length()) : canonicalPath.substring(path2.length() + 1), "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException(a.a("Failed to resolve canonical path for ", file));
            }
        }
    }

    public static File buildPath(File base, String... segments) {
        for (String str : segments) {
            if (str != null) {
                base = new File(base, str);
            }
        }
        return base;
    }

    public static String[] copyOf(String[] original, int newLength) {
        String[] strArr = new String[newLength];
        System.arraycopy(original, 0, strArr, 0, newLength);
        return strArr;
    }

    public static PathStrategy getPathStrategy(Context context, String authority) {
        PathStrategy pathStrategy;
        synchronized (sCache) {
            pathStrategy = sCache.get(authority);
            if (pathStrategy == null) {
                try {
                    pathStrategy = parsePathStrategy(context, authority);
                    sCache.put(authority, pathStrategy);
                } catch (IOException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                } catch (XmlPullParserException e3) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                }
            }
        }
        return pathStrategy;
    }

    public static Uri getUriForFile(@NonNull Context context, @NonNull String authority, @NonNull File file) {
        return getPathStrategy(context, authority).getUriForFile(file);
    }

    public static int modeToMode(String mode) {
        if ("r".equals(mode)) {
            return MessageSchema.REQUIRED_MASK;
        }
        if ("w".equals(mode) || "wt".equals(mode)) {
            return 738197504;
        }
        if ("wa".equals(mode)) {
            return 704643072;
        }
        if ("rw".equals(mode)) {
            return 939524096;
        }
        if ("rwt".equals(mode)) {
            return 1006632960;
        }
        throw new IllegalArgumentException(a.a("Invalid mode: ", mode));
    }

    public static PathStrategy parsePathStrategy(Context context, String authority) {
        SimplePathStrategy simplePathStrategy = new SimplePathStrategy(authority);
        ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 128);
        if (providerInfoResolveContentProvider == null) {
            throw new IllegalArgumentException(a.a("Couldn't find meta-data for provider with authority ", authority));
        }
        XmlResourceParser xmlResourceParserLoadXmlMetaData = providerInfoResolveContentProvider.loadXmlMetaData(context.getPackageManager(), META_DATA_FILE_PROVIDER_PATHS);
        if (xmlResourceParserLoadXmlMetaData == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            int next = xmlResourceParserLoadXmlMetaData.next();
            if (next == 1) {
                return simplePathStrategy;
            }
            if (next == 2) {
                String name = xmlResourceParserLoadXmlMetaData.getName();
                File externalStorageDirectory = null;
                String attributeValue = xmlResourceParserLoadXmlMetaData.getAttributeValue(null, "name");
                String attributeValue2 = xmlResourceParserLoadXmlMetaData.getAttributeValue(null, ATTR_PATH);
                if (TAG_ROOT_PATH.equals(name)) {
                    externalStorageDirectory = DEVICE_ROOT;
                } else if (TAG_FILES_PATH.equals(name)) {
                    externalStorageDirectory = context.getFilesDir();
                } else if (TAG_CACHE_PATH.equals(name)) {
                    externalStorageDirectory = context.getCacheDir();
                } else if (TAG_EXTERNAL.equals(name)) {
                    externalStorageDirectory = Environment.getExternalStorageDirectory();
                } else if (TAG_EXTERNAL_FILES.equals(name)) {
                    File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
                    if (externalFilesDirs.length > 0) {
                        externalStorageDirectory = externalFilesDirs[0];
                    }
                } else if (TAG_EXTERNAL_CACHE.equals(name)) {
                    File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
                    if (externalCacheDirs.length > 0) {
                        externalStorageDirectory = externalCacheDirs[0];
                    }
                } else {
                    int i = Build.VERSION.SDK_INT;
                    if (TAG_EXTERNAL_MEDIA.equals(name)) {
                        File[] externalMediaDirs = context.getExternalMediaDirs();
                        if (externalMediaDirs.length > 0) {
                            externalStorageDirectory = externalMediaDirs[0];
                        }
                    }
                }
                if (externalStorageDirectory != null) {
                    simplePathStrategy.addRoot(attributeValue, buildPath(externalStorageDirectory, attributeValue2));
                }
            }
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo info) {
        super.attachInfo(context, info);
        if (info.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!info.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.mStrategy = getPathStrategy(context, info.authority.split(";")[0]);
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return this.mStrategy.getFileForUri(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        File fileForUri = this.mStrategy.getFileForUri(uri);
        int iLastIndexOf = fileForUri.getName().lastIndexOf(46);
        if (iLastIndexOf < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(iLastIndexOf + 1));
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    @SuppressLint({"UnknownNullness"})
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) {
        return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(uri), modeToMode(mode));
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int i;
        File fileForUri = this.mStrategy.getFileForUri(uri);
        String queryParameter = uri.getQueryParameter(DISPLAYNAME_FIELD);
        if (projection == null) {
            projection = COLUMNS;
        }
        String[] strArr = new String[projection.length];
        Object[] objArr = new Object[projection.length];
        int i2 = 0;
        for (String str : projection) {
            if ("_display_name".equals(str)) {
                strArr[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = queryParameter == null ? fileForUri.getName() : queryParameter;
            } else {
                if ("_size".equals(str)) {
                    strArr[i2] = "_size";
                    i = i2 + 1;
                    objArr[i2] = Long.valueOf(fileForUri.length());
                }
            }
            i2 = i;
        }
        String[] strArrCopyOf = copyOf(strArr, i2);
        Object[] objArrCopyOf = copyOf(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArrCopyOf, 1);
        matrixCursor.addRow(objArrCopyOf);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("No external updates");
    }

    public static Object[] copyOf(Object[] original, int newLength) {
        Object[] objArr = new Object[newLength];
        System.arraycopy(original, 0, objArr, 0, newLength);
        return objArr;
    }

    @NonNull
    @SuppressLint({"StreamFiles"})
    public static Uri getUriForFile(@NonNull Context context, @NonNull String authority, @NonNull File file, @NonNull String displayName) {
        return getUriForFile(context, authority, file).buildUpon().appendQueryParameter(DISPLAYNAME_FIELD, displayName).build();
    }
}
