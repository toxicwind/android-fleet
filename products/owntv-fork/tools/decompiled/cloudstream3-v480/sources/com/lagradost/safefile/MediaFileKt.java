package com.lagradost.safefile;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: MediaFile.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0004\u001a\u0014\u0010\u0007\u001a\u00020\b*\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"replaceDuplicateFileSeparators", "", "path", "defaultPrefix", "Lcom/lagradost/safefile/MediaFileContentType;", "toAbsolutePath", "toPath", "toUri", "Landroid/net/Uri;", "external", "", "library_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MediaFileKt {

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

    public static final String toPath(MediaFileContentType mediaFileContentType) {
        Intrinsics.checkNotNullParameter(mediaFileContentType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[mediaFileContentType.ordinal()];
        if (i == 1) {
            String DIRECTORY_DOWNLOADS = Environment.DIRECTORY_DOWNLOADS;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_DOWNLOADS, "DIRECTORY_DOWNLOADS");
            return DIRECTORY_DOWNLOADS;
        }
        if (i == 2) {
            String DIRECTORY_MUSIC = Environment.DIRECTORY_MUSIC;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_MUSIC, "DIRECTORY_MUSIC");
            return DIRECTORY_MUSIC;
        }
        if (i == 3) {
            String DIRECTORY_MOVIES = Environment.DIRECTORY_MOVIES;
            Intrinsics.checkNotNullExpressionValue(DIRECTORY_MOVIES, "DIRECTORY_MOVIES");
            return DIRECTORY_MOVIES;
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        String DIRECTORY_PICTURES = Environment.DIRECTORY_PICTURES;
        Intrinsics.checkNotNullExpressionValue(DIRECTORY_PICTURES, "DIRECTORY_PICTURES");
        return DIRECTORY_PICTURES;
    }

    public static final String defaultPrefix(MediaFileContentType mediaFileContentType) {
        Intrinsics.checkNotNullParameter(mediaFileContentType, "<this>");
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getExternalStorageDirectory().absolutePath");
        return absolutePath;
    }

    public static final String toAbsolutePath(MediaFileContentType mediaFileContentType) {
        Intrinsics.checkNotNullParameter(mediaFileContentType, "<this>");
        return replaceDuplicateFileSeparators(defaultPrefix(mediaFileContentType) + File.separator + toPath(mediaFileContentType));
    }

    public static final String replaceDuplicateFileSeparators(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Regex regex = new Regex(File.separator + '+');
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        return regex.replace(path, separator);
    }

    public static final Uri toUri(MediaFileContentType mediaFileContentType, boolean z) {
        Uri uri;
        Uri contentUri;
        Intrinsics.checkNotNullParameter(mediaFileContentType, "<this>");
        if (Build.VERSION.SDK_INT >= 29) {
            String str = z ? "external_primary" : "internal";
            int i = WhenMappings.$EnumSwitchMapping$0[mediaFileContentType.ordinal()];
            if (i == 1) {
                contentUri = MediaStore.Downloads.getContentUri(str);
            } else if (i == 2) {
                contentUri = MediaStore.Audio.Media.getContentUri(str);
            } else if (i == 3) {
                contentUri = MediaStore.Video.Media.getContentUri(str);
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                contentUri = MediaStore.Images.Media.getContentUri(str);
            }
            Intrinsics.checkNotNullExpressionValue(contentUri, "{\n        val volume =\n …i(volume)\n        }\n    }");
            return contentUri;
        }
        if (z) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[mediaFileContentType.ordinal()];
            if (i2 == 1) {
                uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
            } else if (i2 == 2) {
                uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            } else if (i2 == 3) {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else {
                if (i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }
        } else {
            int i3 = WhenMappings.$EnumSwitchMapping$0[mediaFileContentType.ordinal()];
            if (i3 == 1) {
                uri = MediaStore.Downloads.INTERNAL_CONTENT_URI;
            } else if (i3 == 2) {
                uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
            } else if (i3 == 3) {
                uri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
            } else {
                if (i3 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
            }
        }
        Intrinsics.checkNotNullExpressionValue(uri, "{\n        if (external) …        }\n        }\n    }");
        return uri;
    }
}
