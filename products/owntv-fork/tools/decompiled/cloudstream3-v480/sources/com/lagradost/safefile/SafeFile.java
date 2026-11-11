package com.lagradost.safefile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import coil3.util.UtilsKt;
import com.hippo.unifile.UniFile;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SafeFile.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 82\u00020\u0001:\u00018J\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H&J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H&J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00002\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000e\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\nH&J\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0003H&J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0003H&J\n\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u0014\u001a\u00020\nH&J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00002\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0003H\u0016J\u001c\u0010\u0017\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0003H&J\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0019\u001a\u00020\u0003H\u0016J\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0003H&J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u0003H&J\u000f\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u001fH&J\u000f\u0010\"\u001a\u0004\u0018\u00010\u001fH\u0016¢\u0006\u0002\u0010 J\b\u0010#\u001a\u00020\u001fH&J\u0010\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010%H\u0016J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00000%H&J\n\u0010'\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010(\u001a\u00020\nH&J\n\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020*H&J\u0014\u0010,\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010.\u001a\u00020\u0003H\u0016J\u0012\u0010/\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020\u0003H&J\u0019\u00100\u001a\u0004\u0018\u00010\u00032\b\u0010'\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u00101J\u0012\u00102\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\nH&J\n\u00103\u001a\u0004\u0018\u00010\nH\u0016J\b\u00104\u001a\u00020\nH&J\n\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u000206H&¨\u00069"}, d2 = {"Lcom/lagradost/safefile/SafeFile;", "", "canRead", "", "()Ljava/lang/Boolean;", "canReadOrThrow", "canWrite", "canWriteOrThrow", "createDirectory", "directoryName", "", "createDirectoryOrThrow", "createFile", "displayName", "createFileOrThrow", "delete", "deleteOrThrow", "exists", "existsOrThrow", "filePath", "filePathOrThrow", "findFile", "ignoreCase", "findFileOrThrow", "gotoDirectory", "createMissingDirectories", "isDirectory", "isDirectoryOrThrow", "isFile", "isFileOrThrow", "lastModified", "", "()Ljava/lang/Long;", "lastModifiedOrThrow", "length", "lengthOrThrow", "listFiles", "", "listFilesOrThrow", "name", "nameOrThrow", "openInputStream", "Ljava/io/InputStream;", "openInputStreamOrThrow", "openOutputStream", "Ljava/io/OutputStream;", "append", "openOutputStreamOrThrow", "renameTo", "(Ljava/lang/String;)Ljava/lang/Boolean;", "renameToOrThrow", "type", "typeOrThrow", "uri", "Landroid/net/Uri;", "uriOrThrow", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SafeFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    Boolean canRead();

    boolean canReadOrThrow();

    Boolean canWrite();

    boolean canWriteOrThrow();

    SafeFile createDirectory(String directoryName);

    SafeFile createDirectoryOrThrow(String directoryName);

    SafeFile createFile(String displayName);

    SafeFile createFileOrThrow(String displayName);

    Boolean delete();

    boolean deleteOrThrow();

    Boolean exists();

    boolean existsOrThrow();

    String filePath();

    String filePathOrThrow();

    SafeFile findFile(String displayName, boolean ignoreCase);

    SafeFile findFileOrThrow(String displayName, boolean ignoreCase);

    SafeFile gotoDirectory(String directoryName, boolean createMissingDirectories);

    Boolean isDirectory();

    /* JADX INFO: renamed from: isDirectoryOrThrow */
    boolean getIsDir();

    Boolean isFile();

    /* JADX INFO: renamed from: isFileOrThrow */
    boolean getIsFile();

    Long lastModified();

    long lastModifiedOrThrow();

    Long length();

    long lengthOrThrow();

    List<SafeFile> listFiles();

    List<SafeFile> listFilesOrThrow();

    String name();

    String nameOrThrow();

    InputStream openInputStream();

    InputStream openInputStreamOrThrow();

    OutputStream openOutputStream(boolean append);

    OutputStream openOutputStreamOrThrow(boolean append);

    Boolean renameTo(String name);

    boolean renameToOrThrow(String name);

    String type();

    String typeOrThrow();

    Uri uri();

    Uri uriOrThrow();

    /* JADX INFO: compiled from: SafeFile.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ SafeFile gotoDirectory$default(SafeFile safeFile, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: gotoDirectory");
            }
            if ((i & 2) != 0) {
                z = true;
            }
            return safeFile.gotoDirectory(str, z);
        }

        public static SafeFile gotoDirectory(SafeFile safeFile, String str, boolean z) {
            if (str == null) {
                return safeFile;
            }
            List listSplit$default = StringsKt.split$default((CharSequence) str, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            ArrayList<String> arrayList = new ArrayList();
            for (Object obj : listSplit$default) {
                if (!StringsKt.isBlank((String) obj)) {
                    arrayList.add(obj);
                }
            }
            SafeFile safeFileCreateDirectory = safeFile;
            for (String str2 : arrayList) {
                if (z || (safeFile instanceof MediaFile)) {
                    safeFileCreateDirectory = safeFileCreateDirectory != null ? safeFileCreateDirectory.createDirectory(str2) : null;
                } else {
                    safeFileCreateDirectory = safeFileCreateDirectory != null ? findFile$default(safeFileCreateDirectory, str2, false, 2, null) : null;
                    if (!(safeFileCreateDirectory != null ? Intrinsics.areEqual((Object) safeFileCreateDirectory.isDirectory(), (Object) true) : false)) {
                    }
                }
            }
            return safeFileCreateDirectory;
        }

        public static SafeFile createFile(final SafeFile safeFile, final String str) {
            return (SafeFile) UniFileWrapperKt.safe(new Function0<SafeFile>() { // from class: com.lagradost.safefile.SafeFile.createFile.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final SafeFile invoke() {
                    return SafeFile.this.createFileOrThrow(str);
                }
            });
        }

        public static SafeFile createDirectory(final SafeFile safeFile, final String str) {
            return (SafeFile) UniFileWrapperKt.safe(new Function0<SafeFile>() { // from class: com.lagradost.safefile.SafeFile.createDirectory.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final SafeFile invoke() {
                    return SafeFile.this.createDirectoryOrThrow(str);
                }
            });
        }

        public static Uri uri(final SafeFile safeFile) {
            return (Uri) UniFileWrapperKt.safe(new Function0<Uri>() { // from class: com.lagradost.safefile.SafeFile.uri.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Uri invoke() {
                    return SafeFile.this.uriOrThrow();
                }
            });
        }

        public static String name(final SafeFile safeFile) {
            return (String) UniFileWrapperKt.safe(new Function0<String>() { // from class: com.lagradost.safefile.SafeFile.name.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return SafeFile.this.nameOrThrow();
                }
            });
        }

        public static String type(final SafeFile safeFile) {
            return (String) UniFileWrapperKt.safe(new Function0<String>() { // from class: com.lagradost.safefile.SafeFile.type.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return SafeFile.this.typeOrThrow();
                }
            });
        }

        public static String filePath(final SafeFile safeFile) {
            return (String) UniFileWrapperKt.safe(new Function0<String>() { // from class: com.lagradost.safefile.SafeFile.filePath.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return SafeFile.this.filePathOrThrow();
                }
            });
        }

        public static Boolean isDirectory(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.isDirectory.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.getIsDir());
                }
            });
        }

        public static Boolean isFile(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.isFile.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.getIsFile());
                }
            });
        }

        public static Long lastModified(final SafeFile safeFile) {
            return (Long) UniFileWrapperKt.safe(new Function0<Long>() { // from class: com.lagradost.safefile.SafeFile.lastModified.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Long invoke() {
                    return Long.valueOf(SafeFile.this.lastModifiedOrThrow());
                }
            });
        }

        public static Long length(final SafeFile safeFile) {
            return (Long) UniFileWrapperKt.safe(new Function0<Long>() { // from class: com.lagradost.safefile.SafeFile.length.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Long invoke() {
                    return Long.valueOf(SafeFile.this.lengthOrThrow());
                }
            });
        }

        public static Boolean canRead(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.canRead.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.canReadOrThrow());
                }
            });
        }

        public static Boolean canWrite(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.canWrite.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.canWriteOrThrow());
                }
            });
        }

        public static Boolean delete(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.delete.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.deleteOrThrow());
                }
            });
        }

        public static Boolean exists(final SafeFile safeFile) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.exists.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.existsOrThrow());
                }
            });
        }

        public static List<SafeFile> listFiles(final SafeFile safeFile) {
            return (List) UniFileWrapperKt.safe(new Function0<List<? extends SafeFile>>() { // from class: com.lagradost.safefile.SafeFile.listFiles.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final List<? extends SafeFile> invoke() {
                    return SafeFile.this.listFilesOrThrow();
                }
            });
        }

        public static /* synthetic */ SafeFile findFileOrThrow$default(SafeFile safeFile, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findFileOrThrow");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return safeFile.findFileOrThrow(str, z);
        }

        public static /* synthetic */ SafeFile findFile$default(SafeFile safeFile, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findFile");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return safeFile.findFile(str, z);
        }

        public static SafeFile findFile(final SafeFile safeFile, final String str, final boolean z) {
            return (SafeFile) UniFileWrapperKt.safe(new Function0<SafeFile>() { // from class: com.lagradost.safefile.SafeFile.findFile.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final SafeFile invoke() {
                    return SafeFile.this.findFileOrThrow(str, z);
                }
            });
        }

        public static Boolean renameTo(final SafeFile safeFile, final String str) {
            return (Boolean) UniFileWrapperKt.safe(new Function0<Boolean>() { // from class: com.lagradost.safefile.SafeFile.renameTo.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SafeFile.this.renameToOrThrow(str));
                }
            });
        }

        public static /* synthetic */ OutputStream openOutputStreamOrThrow$default(SafeFile safeFile, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openOutputStreamOrThrow");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return safeFile.openOutputStreamOrThrow(z);
        }

        public static /* synthetic */ OutputStream openOutputStream$default(SafeFile safeFile, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openOutputStream");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return safeFile.openOutputStream(z);
        }

        public static OutputStream openOutputStream(final SafeFile safeFile, final boolean z) {
            return (OutputStream) UniFileWrapperKt.safe(new Function0<OutputStream>() { // from class: com.lagradost.safefile.SafeFile.openOutputStream.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final OutputStream invoke() {
                    return SafeFile.this.openOutputStreamOrThrow(z);
                }
            });
        }

        public static InputStream openInputStream(final SafeFile safeFile) {
            return (InputStream) UniFileWrapperKt.safe(new Function0<InputStream>() { // from class: com.lagradost.safefile.SafeFile.openInputStream.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final InputStream invoke() {
                    return SafeFile.this.openInputStreamOrThrow();
                }
            });
        }
    }

    /* JADX INFO: compiled from: SafeFile.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\nJ,\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c¨\u0006\u001d"}, d2 = {"Lcom/lagradost/safefile/SafeFile$Companion;", "", "()V", "check", "", "context", "Landroid/content/Context;", "fromAsset", "Lcom/lagradost/safefile/SafeFile;", ContentDisposition.Parameters.FileName, "", "fromFile", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "fromFilePath", "absolutePath", "fromMedia", "folderType", "Lcom/lagradost/safefile/MediaFileContentType;", "path", "external", "", "fromRawFile", "fromResource", "id", "", "fromUri", "uri", "Landroid/net/Uri;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SafeFile fromUri(Context context, Uri uri) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            UniFile uniFileFromUri = UniFile.fromUri(context, uri);
            if (uniFileFromUri == null) {
                return null;
            }
            return new UniFileWrapper(context, uniFileFromUri);
        }

        public final SafeFile fromFile(Context context, File file) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            if (file == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            String separator = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator, "separator");
            sb.append(StringsKt.removePrefix(absolutePath, (CharSequence) separator));
            if (file.isDirectory()) {
                str = File.separator;
            } else {
                str = "";
            }
            String str2 = str;
            Intrinsics.checkNotNullExpressionValue(str2, "if (file.isDirectory) {\n…         \"\"\n            }");
            String str3 = File.separator + File.separator;
            String separator2 = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator2, "separator");
            sb.append(StringsKt.replace$default(str2, str3, separator2, false, 4, (Object) null));
            return fromFilePath(context, sb.toString());
        }

        public final SafeFile fromFilePath(Context context, String absolutePath) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (absolutePath == null) {
                return null;
            }
            for (MediaFileContentType mediaFileContentType : MediaFileContentType.values()) {
                List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{MediaFileKt.toAbsolutePath(mediaFileContentType), MediaFileKt.toPath(mediaFileContentType)});
                ArrayList<String> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
                for (String str : listListOf) {
                    String separator = File.separator;
                    Intrinsics.checkNotNullExpressionValue(separator, "separator");
                    arrayList.add(StringsKt.removePrefix(str, (CharSequence) separator));
                }
                for (String str2 : arrayList) {
                    if (StringsKt.startsWith$default(absolutePath, str2, false, 2, (Object) null)) {
                        String strRemovePrefix = StringsKt.removePrefix(absolutePath, (CharSequence) str2);
                        if (StringsKt.isBlank(strRemovePrefix)) {
                            strRemovePrefix = File.separator;
                        }
                        Intrinsics.checkNotNullExpressionValue(strRemovePrefix, "absolutePath.removePrefi…fBlank { File.separator }");
                        return fromMedia$default(this, context, mediaFileContentType, strRemovePrefix, false, 8, null);
                    }
                }
            }
            return fromRawFile(context, new File(absolutePath));
        }

        public final SafeFile fromAsset(Context context, String filename) {
            UniFile uniFileFromAsset;
            Intrinsics.checkNotNullParameter(context, "context");
            AssetManager assets = context.getAssets();
            if (filename == null || (uniFileFromAsset = UniFile.fromAsset(assets, filename)) == null) {
                return null;
            }
            return new UniFileWrapper(context, uniFileFromAsset);
        }

        public final SafeFile fromResource(Context context, int id) {
            Intrinsics.checkNotNullParameter(context, "context");
            UniFile uniFileFromResource = UniFile.fromResource(context, id);
            if (uniFileFromResource == null) {
                return null;
            }
            return new UniFileWrapper(context, uniFileFromResource);
        }

        private final SafeFile fromRawFile(Context context, File file) {
            UniFile uniFileFromFile;
            if (file == null || (uniFileFromFile = UniFile.fromFile(file)) == null) {
                return null;
            }
            return new UniFileWrapper(context, uniFileFromFile);
        }

        public static /* synthetic */ SafeFile fromMedia$default(Companion companion, Context context, MediaFileContentType mediaFileContentType, String separator, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                separator = File.separator;
                Intrinsics.checkNotNullExpressionValue(separator, "separator");
            }
            if ((i & 8) != 0) {
                z = true;
            }
            return companion.fromMedia(context, mediaFileContentType, separator, z);
        }

        public final SafeFile fromMedia(Context context, MediaFileContentType folderType, String path, boolean external) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(folderType, "folderType");
            Intrinsics.checkNotNullParameter(path, "path");
            if (Build.VERSION.SDK_INT >= 29) {
                return new MediaFile(context, folderType, external, path);
            }
            String str = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + MediaFileKt.toPath(folderType);
            String str2 = File.separator + File.separator;
            String separator = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator, "separator");
            return fromRawFile(context, new File(StringsKt.replace$default(str, str2, separator, false, 4, (Object) null)));
        }

        public final void check(final Context context) {
            Object objM8936constructorimpl;
            String installerPackageName;
            Intrinsics.checkNotNullParameter(context, "context");
            String packageName = context.getPackageName();
            if (packageName != null) {
                int iHashCode = packageName.hashCode();
                if (iHashCode != -226518708) {
                    if (iHashCode != 826197478) {
                        if (iHashCode == 2033316657 && packageName.equals("com.lagradost.cloudstream3.debug")) {
                            return;
                        }
                    } else if (packageName.equals("com.lagradost.cloudstream3.prerelease")) {
                        return;
                    }
                } else if (packageName.equals(BuildConfig.APPLICATION_ID)) {
                    return;
                }
            }
            if (System.currentTimeMillis() % 10 != 0) {
                return;
            }
            List listListOf = CollectionsKt.listOf((Object[]) new String[]{"com.android.vending", "com.google.android.feedback"});
            try {
                Result.Companion companion = Result.INSTANCE;
                if (Build.VERSION.SDK_INT >= 30) {
                    installerPackageName = context.getPackageManager().getInstallSourceInfo(packageName).getInstallingPackageName();
                } else {
                    installerPackageName = context.getPackageManager().getInstallerPackageName(packageName);
                }
                objM8936constructorimpl = Result.m8936constructorimpl(installerPackageName);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                objM8936constructorimpl = null;
            }
            String str = (String) objM8936constructorimpl;
            if (str != null && listListOf.contains(str)) {
                new Handler(context.getMainLooper()).postDelayed(new Runnable() { // from class: com.lagradost.safefile.SafeFile$Companion$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        SafeFile.Companion.check$lambda$9(context);
                    }
                }, ((System.currentTimeMillis() ^ 1193046) % SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) + 20000);
            }
        }

        static final void check$lambda$9(Context context) {
            Intrinsics.checkNotNullParameter(context, "$context");
            try {
                Result.Companion companion = Result.INSTANCE;
                SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                editorEdit.clear();
                editorEdit.apply();
                Result.m8936constructorimpl(editorEdit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SharedPreferences.Editor editorEdit2 = context.getSharedPreferences(DataStoreKt.PREFERENCES_NAME, 0).edit();
                editorEdit2.clear();
                editorEdit2.apply();
                Result.m8936constructorimpl(editorEdit2);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
            try {
                Result.Companion companion5 = Result.INSTANCE;
                File filesDir = context.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
                check$lambda$9$lambda$8$d(filesDir);
                if (Build.VERSION.SDK_INT >= 24) {
                    File dataDir = context.getDataDir();
                    Intrinsics.checkNotNullExpressionValue(dataDir, "context.dataDir");
                    check$lambda$9$lambda$8$d(dataDir);
                }
                File cacheDir = context.getCacheDir();
                Intrinsics.checkNotNullExpressionValue(cacheDir, "context.cacheDir");
                check$lambda$9$lambda$8$d(cacheDir);
                Result.m8936constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion6 = Result.INSTANCE;
                Result.m8936constructorimpl(ResultKt.createFailure(th3));
            }
        }

        private static final void check$lambda$9$lambda$8$d(File file) {
            try {
                Result.Companion companion = Result.INSTANCE;
                file.deleteOnExit();
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(fileArrListFiles, "f.listFiles() ?: return");
                for (File ls : fileArrListFiles) {
                    if (ls.isDirectory()) {
                        Intrinsics.checkNotNullExpressionValue(ls, "ls");
                        check$lambda$9$lambda$8$d(ls);
                    } else {
                        ls.deleteOnExit();
                    }
                }
                Result.m8936constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
        }
    }
}
