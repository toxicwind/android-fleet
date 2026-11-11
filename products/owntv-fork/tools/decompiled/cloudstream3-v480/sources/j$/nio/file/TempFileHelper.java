package j$.nio.file;

import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.PosixFilePermissions;
import j$.sun.security.action.GetPropertyAction;
import j$.time.ZoneId;
import java.security.SecureRandom;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes3.dex */
abstract class TempFileHelper {
    private static final Path tmpdir = Path.CC.of(GetPropertyAction.privilegedGetProperty("java.io.tmpdir"), new String[0]);
    private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
    private static final SecureRandom random = new SecureRandom();

    /* JADX INFO: renamed from: j$.nio.file.TempFileHelper$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ long m(long j, long j2) {
            if (j2 < 0) {
                return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
            }
            if (j >= 0) {
                return j / j2;
            }
            long j3 = ((j >>> 1) / j2) << 1;
            return j3 + ((long) (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1));
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.TempFileHelper$1, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass1 {
        public static /* synthetic */ String m(long j, int i) {
            if (j == 0) {
                return "0";
            }
            if (j > 0) {
                return Long.toString(j, i);
            }
            if (i < 2 || i > 36) {
                i = 10;
            }
            int i2 = 64;
            char[] cArr = new char[64];
            int i3 = i - 1;
            if ((i & i3) == 0) {
                int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
                do {
                    i2--;
                    cArr[i2] = Character.forDigit(((int) j) & i3, i);
                    j >>>= iNumberOfTrailingZeros;
                } while (j != 0);
            } else {
                long jM = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : AnonymousClass0.m(j, i);
                long j2 = i;
                cArr[63] = Character.forDigit((int) (j - (jM * j2)), i);
                i2 = 63;
                while (jM > 0) {
                    i2--;
                    cArr[i2] = Character.forDigit((int) (jM % j2), i);
                    jM /= j2;
                }
            }
            return new String(cArr, i2, 64 - i2);
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.TempFileHelper$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ String m(long j) {
            return AnonymousClass1.m(j, 10);
        }
    }

    private static Path generatePath(String str, String str2, Path path) {
        Path path2 = path.getFileSystem().getPath(str + AnonymousClass2.m(random.nextLong()) + str2, new String[0]);
        if (path2.getParent() != null) {
            ZoneId.AnonymousClass4.m("Invalid prefix or suffix");
            return null;
        }
        return path.resolve(path2);
    }

    private static class PosixPermissions {
        static final FileAttribute dirPermissions;
        static final FileAttribute filePermissions;

        static {
            PosixFilePermission posixFilePermission = PosixFilePermission.OWNER_READ;
            PosixFilePermission posixFilePermission2 = PosixFilePermission.OWNER_WRITE;
            filePermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(posixFilePermission, posixFilePermission2));
            dirPermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(posixFilePermission, posixFilePermission2, PosixFilePermission.OWNER_EXECUTE));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0062, code lost:
    
        if (r7 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x006f, code lost:
    
        return j$.nio.file.Files.createFile(r1, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static j$.nio.file.Path create(j$.nio.file.Path r4, java.lang.String r5, java.lang.String r6, boolean r7, j$.nio.file.attribute.FileAttribute[] r8) {
        /*
            java.lang.String r0 = ""
            if (r5 != 0) goto L5
            r5 = r0
        L5:
            if (r6 != 0) goto Ld
            if (r7 == 0) goto Lb
            r6 = r0
            goto Ld
        Lb:
            java.lang.String r6 = ".tmp"
        Ld:
            if (r4 != 0) goto L11
            j$.nio.file.Path r4 = j$.nio.file.TempFileHelper.tmpdir
        L11:
            boolean r0 = j$.nio.file.TempFileHelper.isPosix
            if (r0 == 0) goto L5a
            j$.nio.file.FileSystem r0 = r4.getFileSystem()
            j$.nio.file.FileSystem r1 = j$.nio.file.FileSystems.getDefault()
            if (r0 != r1) goto L5a
            int r0 = r8.length
            r1 = 0
            if (r0 != 0) goto L31
            if (r7 == 0) goto L28
            j$.nio.file.attribute.FileAttribute r8 = j$.nio.file.TempFileHelper.PosixPermissions.dirPermissions
            goto L2a
        L28:
            j$.nio.file.attribute.FileAttribute r8 = j$.nio.file.TempFileHelper.PosixPermissions.filePermissions
        L2a:
            r0 = 1
            j$.nio.file.attribute.FileAttribute[] r0 = new j$.nio.file.attribute.FileAttribute[r0]
            r0[r1] = r8
            r8 = r0
            goto L5a
        L31:
            r0 = r1
        L32:
            int r2 = r8.length
            if (r0 >= r2) goto L47
            r2 = r8[r0]
            java.lang.String r2 = r2.name()
            java.lang.String r3 = "posix:permissions"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L44
            goto L5a
        L44:
            int r0 = r0 + 1
            goto L32
        L47:
            int r0 = r8.length
            int r2 = r0 + 1
            j$.nio.file.attribute.FileAttribute[] r2 = new j$.nio.file.attribute.FileAttribute[r2]
            int r3 = r8.length
            java.lang.System.arraycopy(r8, r1, r2, r1, r3)
            if (r7 == 0) goto L55
            j$.nio.file.attribute.FileAttribute r8 = j$.nio.file.TempFileHelper.PosixPermissions.dirPermissions
            goto L57
        L55:
            j$.nio.file.attribute.FileAttribute r8 = j$.nio.file.TempFileHelper.PosixPermissions.filePermissions
        L57:
            r2[r0] = r8
            r8 = r2
        L5a:
            java.lang.SecurityManager r0 = java.lang.System.getSecurityManager()
        L5e:
            j$.nio.file.Path r1 = generatePath(r5, r6, r4)     // Catch: java.nio.file.InvalidPathException -> L7f
            if (r7 == 0) goto L6b
            j$.nio.file.Path r4 = j$.nio.file.Files.createDirectory(r1, r8)     // Catch: java.nio.file.FileAlreadyExistsException -> L5e java.lang.SecurityException -> L69
            return r4
        L69:
            r5 = move-exception
            goto L70
        L6b:
            j$.nio.file.Path r4 = j$.nio.file.Files.createFile(r1, r8)     // Catch: java.nio.file.FileAlreadyExistsException -> L5e java.lang.SecurityException -> L69
            return r4
        L70:
            j$.nio.file.Path r6 = j$.nio.file.TempFileHelper.tmpdir
            if (r4 != r6) goto L7e
            if (r0 == 0) goto L7e
            java.lang.SecurityException r4 = new java.lang.SecurityException
            java.lang.String r5 = "Unable to create temporary file or directory"
            r4.<init>(r5)
            throw r4
        L7e:
            throw r5
        L7f:
            r4 = move-exception
            if (r0 == 0) goto L89
            java.lang.String r4 = "Invalid prefix or suffix"
            j$.time.ZoneId.AnonymousClass4.m(r4)
            r4 = 0
            return r4
        L89:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.nio.file.TempFileHelper.create(j$.nio.file.Path, java.lang.String, java.lang.String, boolean, j$.nio.file.attribute.FileAttribute[]):j$.nio.file.Path");
    }

    static Path createTempFile(Path path, String str, String str2, FileAttribute[] fileAttributeArr) {
        return create(path, str, str2, false, fileAttributeArr);
    }

    static Path createTempDirectory(Path path, String str, FileAttribute[] fileAttributeArr) {
        return create(path, str, null, true, fileAttributeArr);
    }
}
