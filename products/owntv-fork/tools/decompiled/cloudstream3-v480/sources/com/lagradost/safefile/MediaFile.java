package com.lagradost.safefile;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.actions.SearchIntents;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MediaFile.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 >2\u00020\u0001:\u0002>?B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0007H\u0016J\u0012\u0010\u0019\u001a\u00020\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\u001b\u001a\u00020\u00012\b\u0010\u001c\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\tH\u0002J\u0014\u0010!\u001a\u00020\f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\fH\u0002J\b\u0010$\u001a\u00020\u0007H\u0016J\b\u0010%\u001a\u00020\u0007H\u0016J\b\u0010&\u001a\u00020\tH\u0016J\u001a\u0010'\u001a\u00020\u00012\b\u0010\u001c\u001a\u0004\u0018\u00010\t2\u0006\u0010(\u001a\u00020\u0007H\u0016J\b\u0010)\u001a\u00020\u0007H\u0016J\b\u0010*\u001a\u00020\u0007H\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020,H\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00010/H\u0016J\b\u00100\u001a\u00020\tH\u0016J\u0010\u00101\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0002J\b\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0007H\u0016J\u0012\u00107\u001a\u0002082\b\b\u0002\u0010\u001c\u001a\u00020\tH\u0003J\u0012\u00109\u001a\u00020\u00072\b\u0010:\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010;\u001a\u00020\tH\u0016J\b\u0010<\u001a\u00020\tH\u0016J\b\u0010=\u001a\u00020\fH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/lagradost/safefile/MediaFile;", "Lcom/lagradost/safefile/SafeFile;", "context", "Landroid/content/Context;", "folderType", "Lcom/lagradost/safefile/MediaFileContentType;", "external", "", "absolutePath", "", "(Landroid/content/Context;Lcom/lagradost/safefile/MediaFileContentType;ZLjava/lang/String;)V", "baseUri", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "isDir", "isFile", "namePath", "relativePath", "sanitizedAbsolutePath", "appendRelativePathOrThrow", "path", "folder", "canReadOrThrow", "canWriteOrThrow", "createDirectoryOrThrow", "directoryName", "createFileOrThrow", "displayName", "createUriFromContent", "values", "Landroid/content/ContentValues;", "mime", "createUriOrThrow", "delete", "uri", "deleteOrThrow", "existsOrThrow", "filePathOrThrow", "findFileOrThrow", "ignoreCase", "isDirectoryOrThrow", "isFileOrThrow", "lastModifiedOrThrow", "", "lengthOrThrow", "listFilesOrThrow", "", "nameOrThrow", "niceMime", "openInputStreamOrThrow", "Ljava/io/InputStream;", "openOutputStreamOrThrow", "Ljava/io/OutputStream;", "append", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/safefile/MediaFile$QueryResult;", "renameToOrThrow", "name", "toString", "typeOrThrow", "uriOrThrow", "Companion", "QueryResult", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MediaFile implements SafeFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Uri baseUri;
    private final ContentResolver contentResolver;
    private final Context context;
    private final boolean external;
    private final MediaFileContentType folderType;
    private final boolean isDir;
    private final boolean isFile;
    private final String namePath;
    private final String relativePath;
    private final String sanitizedAbsolutePath;

    /* JADX INFO: compiled from: MediaFile.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaFileContentType.values().length];
            try {
                iArr[MediaFileContentType.Downloads.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaFileContentType.Audio.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MediaFileContentType.Video.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MediaFileContentType.Images.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MediaFile(Context context, MediaFileContentType folderType, boolean z, String absolutePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(folderType, "folderType");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        this.context = context;
        this.folderType = folderType;
        this.external = z;
        String strReplaceDuplicateFileSeparators = MediaFileKt.replaceDuplicateFileSeparators(absolutePath);
        this.sanitizedAbsolutePath = strReplaceDuplicateFileSeparators;
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        boolean zEndsWith$default = StringsKt.endsWith$default(strReplaceDuplicateFileSeparators, separator, false, 2, (Object) null);
        this.isDir = zEndsWith$default;
        this.isFile = !zEndsWith$default;
        String strReplaceDuplicateFileSeparators2 = MediaFileKt.replaceDuplicateFileSeparators(MediaFileKt.toPath(folderType) + File.separator + strReplaceDuplicateFileSeparators);
        String separator2 = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator2, "separator");
        String strSubstringBeforeLast$default = StringsKt.substringBeforeLast$default(strReplaceDuplicateFileSeparators2, separator2, (String) null, 2, (Object) null);
        this.relativePath = strSubstringBeforeLast$default;
        String separator3 = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator3, "separator");
        String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(strReplaceDuplicateFileSeparators, separator3, (String) null, 2, (Object) null);
        this.namePath = strSubstringAfterLast$default;
        this.baseUri = MediaFileKt.toUri(folderType, z);
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
        this.contentResolver = contentResolver;
        String separator4 = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator4, "separator");
        StringsKt.endsWith$default(strSubstringBeforeLast$default, separator4, false, 2, (Object) null);
        StringsKt.contains$default((CharSequence) strSubstringBeforeLast$default, (CharSequence) (File.separator + File.separator), false, 2, (Object) null);
        String separator5 = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator5, "separator");
        StringsKt.contains$default((CharSequence) strSubstringAfterLast$default, (CharSequence) separator5, false, 2, (Object) null);
        if (zEndsWith$default) {
            StringsKt.isBlank(strSubstringAfterLast$default);
        } else {
            StringsKt.isBlank(strSubstringAfterLast$default);
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean canRead() {
        return SafeFile.DefaultImpls.canRead(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean canWrite() {
        return SafeFile.DefaultImpls.canWrite(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createDirectory(String str) {
        return SafeFile.DefaultImpls.createDirectory(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createFile(String str) {
        return SafeFile.DefaultImpls.createFile(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean delete() {
        return SafeFile.DefaultImpls.delete(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean exists() {
        return SafeFile.DefaultImpls.exists(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String filePath() {
        return SafeFile.DefaultImpls.filePath(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile findFile(String str, boolean z) {
        return SafeFile.DefaultImpls.findFile(this, str, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile gotoDirectory(String str, boolean z) {
        return SafeFile.DefaultImpls.gotoDirectory(this, str, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean isDirectory() {
        return SafeFile.DefaultImpls.isDirectory(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean isFile() {
        return SafeFile.DefaultImpls.isFile(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Long lastModified() {
        return SafeFile.DefaultImpls.lastModified(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Long length() {
        return SafeFile.DefaultImpls.length(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public List<SafeFile> listFiles() {
        return SafeFile.DefaultImpls.listFiles(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String name() {
        return SafeFile.DefaultImpls.name(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public InputStream openInputStream() {
        return SafeFile.DefaultImpls.openInputStream(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public OutputStream openOutputStream(boolean z) {
        return SafeFile.DefaultImpls.openOutputStream(this, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean renameTo(String str) {
        return SafeFile.DefaultImpls.renameTo(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String type() {
        return SafeFile.DefaultImpls.type(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Uri uri() {
        return SafeFile.DefaultImpls.uri(this);
    }

    public /* synthetic */ MediaFile(Context context, MediaFileContentType mediaFileContentType, boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, mediaFileContentType, (i & 4) != 0 ? true : z, str);
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getSanitizedAbsolutePath() {
        return this.sanitizedAbsolutePath;
    }

    /* JADX INFO: compiled from: MediaFile.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u001e\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¨\u0006\b"}, d2 = {"Lcom/lagradost/safefile/MediaFile$Companion;", "", "()V", "splitFilenameExt", "Lkotlin/Pair;", "", "name", "splitFilenameMime", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Pair<String, String> splitFilenameExt(String name) {
            String str = name;
            int i = -1;
            int length = str.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i2 = length - 1;
                    if (str.charAt(length) == '.') {
                        i = length;
                        break;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                }
            }
            if (i <= 0) {
                return TuplesKt.to(name, null);
            }
            String strSubstring = StringsKt.substring(name, RangesKt.until(i + 1, name.length()));
            return StringsKt.isBlank(strSubstring) ? TuplesKt.to(name, null) : TuplesKt.to(StringsKt.substring(name, RangesKt.until(0, i)), strSubstring);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Pair<String, String> splitFilenameMime(String name) {
            Pair<String, String> pairSplitFilenameExt = splitFilenameExt(name);
            return TuplesKt.to(pairSplitFilenameExt.component1(), MimeTypes.INSTANCE.fromExtToMime(pairSplitFilenameExt.component2()));
        }
    }

    private final MediaFile appendRelativePathOrThrow(String path, boolean folder) {
        if (!this.isDir) {
            throw new IllegalArgumentException("Requires this to be a directory to appendRelativePath".toString());
        }
        if (Intrinsics.areEqual(this.relativePath, path)) {
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.sanitizedAbsolutePath);
        sb.append(path);
        sb.append(folder ? File.separator : "");
        return new MediaFile(this.context, this.folderType, this.external, sb.toString());
    }

    private final String niceMime(String mime) {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = WhenMappings.$EnumSwitchMapping$0[this.folderType.ordinal()];
        if (i == 1) {
            return mime;
        }
        if (i == 2) {
            str = "audio/";
        } else if (i == 3) {
            str = "video/";
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            str = "image/";
        }
        sb.append(str);
        sb.append(StringsKt.substringAfter$default(mime, "/", (String) null, 2, (Object) null));
        return sb.toString();
    }

    private final Uri createUriFromContent(ContentValues values, String mime) throws IOException {
        for (String str : mime == null ? CollectionsKt.listOf((Object) null) : CollectionsKt.distinct(CollectionsKt.listOf((Object[]) new String[]{mime, niceMime(mime), null}))) {
            if (str != null) {
                values.put("mime_type", str);
            } else {
                values.remove("mime_type");
            }
            try {
                Uri uriInsert = this.contentResolver.insert(this.baseUri, values);
                if (uriInsert != null) {
                    return uriInsert;
                }
            } catch (IllegalArgumentException e) {
                SafeFileKt.logError(e);
            }
        }
        throw new IOException("Illegal mime type");
    }

    static /* synthetic */ Uri createUriOrThrow$default(MediaFile mediaFile, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaFile.namePath;
        }
        return mediaFile.createUriOrThrow(str);
    }

    private final Uri createUriOrThrow(String displayName) {
        if (displayName == null) {
            throw new IllegalArgumentException("Requires non null display name".toString());
        }
        if (this.isDir) {
            Pair pairSplitFilenameMime = INSTANCE.splitFilenameMime(displayName);
            String str = (String) pairSplitFilenameMime.component1();
            String str2 = (String) pairSplitFilenameMime.component2();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", displayName);
            contentValues.put("title", str);
            String str3 = this.relativePath;
            String separator = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator, "separator");
            if (StringsKt.contains$default((CharSequence) str3, (CharSequence) separator, false, 2, (Object) null)) {
                contentValues.put("relative_path", this.relativePath + File.separator);
            }
            return createUriFromContent(contentValues, str2);
        }
        throw new IllegalArgumentException("Requires this to be a directory to create sub files".toString());
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createFileOrThrow(final String displayName) {
        if (!this.isDir) {
            throw new IllegalArgumentException("Requires a directory to createFile".toString());
        }
        if (displayName == null) {
            throw new IllegalArgumentException("Requires non null name".toString());
        }
        if (((Uri) UniFileWrapperKt.safe(new Function0<Uri>() { // from class: com.lagradost.safefile.MediaFile.createFileOrThrow.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Uri invoke() {
                return MediaFile.this.query(displayName).getUri();
            }
        })) == null) {
            createUriOrThrow(displayName);
        }
        return appendRelativePathOrThrow(displayName, false);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createDirectoryOrThrow(String directoryName) {
        if (directoryName == null) {
            throw new IllegalArgumentException("Non null directory".toString());
        }
        return appendRelativePathOrThrow(directoryName, true);
    }

    /* JADX INFO: compiled from: MediaFile.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/safefile/MediaFile$QueryResult;", "", "uri", "Landroid/net/Uri;", "lastModified", "", "length", "(Landroid/net/Uri;JJ)V", "getLastModified", "()J", "getLength", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class QueryResult {
        private final long lastModified;
        private final long length;
        private final Uri uri;

        public static /* synthetic */ QueryResult copy$default(QueryResult queryResult, Uri uri, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = queryResult.uri;
            }
            if ((i & 2) != 0) {
                j = queryResult.lastModified;
            }
            if ((i & 4) != 0) {
                j2 = queryResult.length;
            }
            return queryResult.copy(uri, j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Uri getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getLastModified() {
            return this.lastModified;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getLength() {
            return this.length;
        }

        public final QueryResult copy(Uri uri, long lastModified, long length) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return new QueryResult(uri, lastModified, length);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueryResult)) {
                return false;
            }
            QueryResult queryResult = (QueryResult) other;
            return Intrinsics.areEqual(this.uri, queryResult.uri) && this.lastModified == queryResult.lastModified && this.length == queryResult.length;
        }

        public int hashCode() {
            return (((this.uri.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.lastModified)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.length);
        }

        public String toString() {
            return "QueryResult(uri=" + this.uri + ", lastModified=" + this.lastModified + ", length=" + this.length + ')';
        }

        public QueryResult(Uri uri, long j, long j2) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uri = uri;
            this.lastModified = j;
            this.length = j2;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final long getLastModified() {
            return this.lastModified;
        }

        public final long getLength() {
            return this.length;
        }
    }

    static /* synthetic */ QueryResult query$default(MediaFile mediaFile, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaFile.namePath;
        }
        return mediaFile.query(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QueryResult query(String displayName) throws IOException {
        Cursor cursorQuery = this.contentResolver.query(this.baseUri, new String[]{"_id", "date_modified", "_size"}, "relative_path='" + this.relativePath + File.separator + "' AND _display_name='" + displayName + '\'', null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToNext()) {
                    Uri uriWithAppendedId = ContentUris.withAppendedId(this.baseUri, cursor2.getLong(cursor2.getColumnIndexOrThrow("_id")));
                    Intrinsics.checkNotNullExpressionValue(uriWithAppendedId, "withAppendedId(\n        … id\n                    )");
                    QueryResult queryResult = new QueryResult(uriWithAppendedId, cursor2.getLong(cursor2.getColumnIndexOrThrow("date_modified")), cursor2.getLong(cursor2.getColumnIndexOrThrow("_size")));
                    CloseableKt.closeFinally(cursor, null);
                    return queryResult;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        throw new IOException("ContentResolver Query not found");
    }

    @Override // com.lagradost.safefile.SafeFile
    public Uri uriOrThrow() {
        return query$default(this, null, 1, null).getUri();
    }

    @Override // com.lagradost.safefile.SafeFile
    public String nameOrThrow() {
        if (!this.isFile) {
            throw new IllegalArgumentException("Cant get name of a directory".toString());
        }
        return this.namePath;
    }

    @Override // com.lagradost.safefile.SafeFile
    public String typeOrThrow() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String filePathOrThrow() {
        return MediaFileKt.replaceDuplicateFileSeparators(this.relativePath + File.separator + this.namePath);
    }

    @Override // com.lagradost.safefile.SafeFile
    /* JADX INFO: renamed from: isDirectoryOrThrow, reason: from getter */
    public boolean getIsDir() {
        return this.isDir;
    }

    @Override // com.lagradost.safefile.SafeFile
    /* JADX INFO: renamed from: isFileOrThrow, reason: from getter */
    public boolean getIsFile() {
        return this.isFile;
    }

    @Override // com.lagradost.safefile.SafeFile
    public long lastModifiedOrThrow() {
        if (!this.isFile) {
            throw new IllegalArgumentException("Cant get modified on a directory".toString());
        }
        return query$default(this, null, 1, null).getLastModified();
    }

    @Override // com.lagradost.safefile.SafeFile
    public long lengthOrThrow() throws IOException {
        if (!this.isFile) {
            throw new IllegalArgumentException("Cant get length on a directory".toString());
        }
        QueryResult queryResultQuery$default = query$default(this, null, 1, null);
        long length = queryResultQuery$default.getLength();
        if (length > 0) {
            return length;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = this.contentResolver.openFileDescriptor(queryResultQuery$default.getUri(), "r");
            try {
                ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorOpenFileDescriptor;
                Long lValueOf = parcelFileDescriptor != null ? Long.valueOf(parcelFileDescriptor.getStatSize()) : null;
                CloseableKt.closeFinally(parcelFileDescriptorOpenFileDescriptor, null);
                if (lValueOf != null) {
                    return lValueOf.longValue();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(parcelFileDescriptorOpenFileDescriptor, th);
                    throw th2;
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Throwable th3) {
            SafeFileKt.logError(th3);
        }
        InputStream inputStreamOpenInputStreamOrThrow = openInputStreamOrThrow();
        try {
            long jAvailable = inputStreamOpenInputStreamOrThrow.available();
            return jAvailable;
        } finally {
            SafeFileKt.closeQuietly(inputStreamOpenInputStreamOrThrow);
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean canReadOrThrow() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean canWriteOrThrow() {
        throw new NotImplementedError(null, 1, null);
    }

    private final boolean delete(Uri uri) {
        return this.contentResolver.delete(uri, null, null) > 0;
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean deleteOrThrow() throws IOException {
        if (this.isDir) {
            List<SafeFile> listListFilesOrThrow = listFilesOrThrow();
            if ((listListFilesOrThrow instanceof Collection) && listListFilesOrThrow.isEmpty()) {
                return true;
            }
            Iterator<T> it = listListFilesOrThrow.iterator();
            while (it.hasNext()) {
                if (!((SafeFile) it.next()).deleteOrThrow()) {
                    return false;
                }
            }
            return true;
        }
        return delete(uriOrThrow());
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean existsOrThrow() {
        if (this.isDir) {
            return true;
        }
        try {
            query$default(this, null, 1, null);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public List<SafeFile> listFilesOrThrow() throws IOException {
        if (!this.isDir) {
            throw new IllegalArgumentException("Cant list files on a file".toString());
        }
        Cursor cursorQuery = this.contentResolver.query(this.baseUri, new String[]{"_display_name"}, "relative_path='" + this.relativePath + File.separator + '\'', null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                ArrayList arrayList = new ArrayList(cursor2.getCount());
                while (cursor2.moveToNext()) {
                    int columnIndex = cursor2.getColumnIndex("_display_name");
                    if (columnIndex != -1) {
                        String name = cursor2.getString(columnIndex);
                        Intrinsics.checkNotNullExpressionValue(name, "name");
                        arrayList.add(appendRelativePathOrThrow(name, false));
                    }
                }
                ArrayList arrayList2 = arrayList;
                CloseableKt.closeFinally(cursor, null);
                return arrayList2;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        throw new IOException("Null contentResolver.query");
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile findFileOrThrow(String displayName, boolean ignoreCase) throws FileNotFoundException {
        if (!this.isDir) {
            throw new IllegalArgumentException("Cant find a file is the current is not a directory".toString());
        }
        if (displayName == null) {
            throw new IllegalArgumentException("Non null displayName ".toString());
        }
        MediaFile mediaFileAppendRelativePathOrThrow = appendRelativePathOrThrow(displayName, false);
        if (mediaFileAppendRelativePathOrThrow.existsOrThrow()) {
            return mediaFileAppendRelativePathOrThrow;
        }
        throw new FileNotFoundException("No file found");
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean renameToOrThrow(String name) {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.safefile.SafeFile
    public OutputStream openOutputStreamOrThrow(boolean append) throws IOException {
        Uri uri = uri();
        if (uri != null) {
            try {
                OutputStream outputStreamOpenOutputStream = this.contentResolver.openOutputStream(uri, append ? "wa" : "wt");
                if (outputStreamOpenOutputStream != null) {
                    return outputStreamOpenOutputStream;
                }
            } catch (Throwable unused) {
                Unit unit = Unit.INSTANCE;
            }
        }
        OutputStream outputStreamOpenOutputStream2 = this.contentResolver.openOutputStream(createUriOrThrow$default(this, null, 1, null), "w");
        if (outputStreamOpenOutputStream2 != null) {
            return outputStreamOpenOutputStream2;
        }
        throw new IOException("Null InputStream");
    }

    @Override // com.lagradost.safefile.SafeFile
    public InputStream openInputStreamOrThrow() throws IOException {
        InputStream inputStreamOpenInputStream = this.contentResolver.openInputStream(uriOrThrow());
        if (inputStreamOpenInputStream != null) {
            return inputStreamOpenInputStream;
        }
        throw new IOException("Null InputStream");
    }
}
