package j$.nio.channels;

import j$.adapter.AndroidVersionTest;
import j$.desugar.sun.nio.fs.DesugarFileChannel;
import j$.nio.file.FileApiFlips;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.attribute.FileAttributeConversions;
import java.nio.channels.FileChannel;
import java.nio.file.attribute.FileAttribute;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class DesugarChannels {

    /* JADX INFO: renamed from: j$.nio.channels.DesugarChannels$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ FileAttribute[] m(j$.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            if (fileAttributeArr == null) {
                return null;
            }
            int length = fileAttributeArr.length;
            FileAttribute[] fileAttributeArr2 = new FileAttribute[length];
            for (int i = 0; i < length; i++) {
                fileAttributeArr2[i] = FileAttributeConversions.convert(fileAttributeArr[i]);
            }
            return fileAttributeArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.channels.DesugarChannels$1, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass1 {
        public static /* synthetic */ FileChannel m(Path path, Set set, j$.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            return FileChannel.open(Path.Wrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), AnonymousClass0.m(fileAttributeArr));
        }
    }

    public static FileChannel convertMaybeLegacyFileChannelFromLibrary(FileChannel fileChannel) {
        if (fileChannel == null) {
            return null;
        }
        return AndroidVersionTest.is24OrAbove ? fileChannel : DesugarFileChannel.wrap(fileChannel);
    }

    public static FileChannel open(Path path, OpenOption... openOptionArr) {
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, openOptionArr);
        return open(path, hashSet, new j$.nio.file.attribute.FileAttribute[0]);
    }

    public static FileChannel open(Path path, Set set, j$.nio.file.attribute.FileAttribute... fileAttributeArr) {
        if (AndroidVersionTest.is26OrAbove) {
            return AnonymousClass1.m(path, set, fileAttributeArr);
        }
        return DesugarFileChannel.openEmulatedFileChannel(path, set, fileAttributeArr);
    }
}
