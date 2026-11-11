package com.hippo.unifile;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import coil3.util.UtilsKt;
import coil3.util.Utils_androidKt;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class UniFile {
    private static final int ASSET_PATH_PREFIX_LENGTH = 15;
    private static List<UriHandler> sUriHandlerArray;
    private final UniFile mParent;

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract UniFile createDirectory(String str);

    public abstract UniFile createFile(String str);

    public abstract UniRandomAccessFile createRandomAccessFile(String str) throws IOException;

    public abstract boolean delete();

    public abstract boolean exists();

    public abstract UniFile findFile(String str);

    public abstract String getFilePath();

    public abstract String getName();

    public abstract String getType();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract long lastModified();

    public abstract long length();

    public abstract UniFile[] listFiles();

    public abstract UniFile[] listFiles(FilenameFilter filenameFilter);

    public abstract InputStream openInputStream() throws IOException;

    public abstract OutputStream openOutputStream() throws IOException;

    public abstract OutputStream openOutputStream(boolean z) throws IOException;

    public abstract boolean renameTo(String str);

    UniFile(UniFile uniFile) {
        this.mParent = uniFile;
    }

    public static void addUriHandler(UriHandler uriHandler) {
        if (sUriHandlerArray == null) {
            sUriHandlerArray = new ArrayList();
        }
        sUriHandlerArray.add(uriHandler);
    }

    public static void removeUriHandler(UriHandler uriHandler) {
        List<UriHandler> list = sUriHandlerArray;
        if (list != null) {
            list.remove(uriHandler);
        }
    }

    public static UniFile fromFile(File file) {
        if (file != null) {
            return new RawFile(null, file);
        }
        return null;
    }

    public static UniFile fromAsset(AssetManager assetManager, String str) {
        return fromAssetUri(assetManager, new Uri.Builder().scheme(UtilsKt.SCHEME_FILE).authority("").path("android_asset/" + str).build());
    }

    public static UniFile fromResource(Context context, int i) {
        Resources resources = context.getResources();
        try {
            return new ResourceFile(resources, context.getPackageName(), i, resources.getResourceEntryName(i));
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private static UniFile fromAssetUri(AssetManager assetManager, Uri uri) {
        return new AssetFile(null, assetManager, Utils.normalize(uri.getPath().substring(ASSET_PATH_PREFIX_LENGTH)));
    }

    private static UniFile fromSingleDocumentUri(Context context, Uri uri) {
        return new SingleDocumentFile(null, context, uri);
    }

    private static UniFile fromTreeDocumentUri(Context context, Uri uri) {
        return new TreeDocumentFile(null, context, DocumentsContractApi21.prepareTreeUri(uri));
    }

    private static UniFile fromMediaUri(Context context, Uri uri) {
        return new MediaFile(context, uri);
    }

    public static UniFile fromUri(Context context, Uri uri) {
        if (context != null && uri != null) {
            List<UriHandler> list = sUriHandlerArray;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    UniFile uniFileFromUri = sUriHandlerArray.get(i).fromUri(context, uri);
                    if (uniFileFromUri != null) {
                        return uniFileFromUri;
                    }
                }
            }
            if (isFileUri(uri)) {
                if (isAssetUri(uri)) {
                    return fromAssetUri(context.getAssets(), uri);
                }
                return fromFile(new File(uri.getPath()));
            }
            if (isDocumentUri(context, uri)) {
                if (isTreeDocumentUri(context, uri)) {
                    return fromTreeDocumentUri(context, uri);
                }
                return fromSingleDocumentUri(context, uri);
            }
            if (isMediaUri(uri)) {
                return new MediaFile(context, uri);
            }
            ResourcesContract.OpenResourceResult openResourceResultOpenResource = ResourcesContract.openResource(context, uri);
            if (openResourceResultOpenResource != null) {
                return new ResourceFile(openResourceResultOpenResource.r, openResourceResultOpenResource.p, openResourceResultOpenResource.id, openResourceResultOpenResource.name);
            }
        }
        return null;
    }

    public static boolean isFileUri(Uri uri) {
        return uri != null && UtilsKt.SCHEME_FILE.equals(uri.getScheme());
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        return uri != null && DocumentsContractApi19.isDocumentUri(context, uri);
    }

    public static boolean isTreeDocumentUri(Context context, Uri uri) {
        return uri != null && DocumentsContractApi21.isTreeDocumentUri(context, uri);
    }

    public static boolean isAssetUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        List<String> pathSegments = uri.getPathSegments();
        return UtilsKt.SCHEME_FILE.equals(uri.getScheme()) && pathSegments.size() >= 2 && Utils_androidKt.ASSET_FILE_PATH_ROOT.equals(pathSegments.get(0));
    }

    public static boolean isMediaUri(Uri uri) {
        return uri != null && "content".equals(uri.getScheme());
    }

    public UniFile getParentFile() {
        return this.mParent;
    }
}
