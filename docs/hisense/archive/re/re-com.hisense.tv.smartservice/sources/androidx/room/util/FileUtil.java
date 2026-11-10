package androidx.room.util;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.common.primitives.UnsignedLong;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FileUtil {
    @SuppressLint({"LambdaLast"})
    public static void copy(@NonNull ReadableByteChannel readableByteChannel, @NonNull FileChannel fileChannel) {
        try {
            int i = Build.VERSION.SDK_INT;
            fileChannel.transferFrom(readableByteChannel, 0L, UnsignedLong.UNSIGNED_MASK);
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }
}
