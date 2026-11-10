package e.a.a.a;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f2148a = BigInteger.valueOf(1024);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f2149b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f2150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final BigInteger f2151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final BigInteger f2152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final BigInteger f2153f;

    static {
        BigInteger bigInteger = f2148a;
        f2149b = bigInteger.multiply(bigInteger);
        f2150c = f2148a.multiply(f2149b);
        f2151d = f2148a.multiply(f2150c);
        f2152e = f2148a.multiply(f2151d);
        f2148a.multiply(f2152e);
        f2153f = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
        f2148a.multiply(f2153f);
        File[] fileArr = new File[0];
    }

    public static FileOutputStream a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException(b.a.a.a.a.a("Directory '", parentFile, "' could not be created"));
            }
        } else {
            if (file.isDirectory()) {
                throw new IOException(b.a.a.a.a.a("File '", file, "' exists but is a directory"));
            }
            if (!file.canWrite()) {
                throw new IOException(b.a.a.a.a.a("File '", file, "' cannot be written to"));
            }
        }
        return new FileOutputStream(file, z);
    }

    public static boolean b(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                a(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x006e  */
    public static void c(File file) throws IOException {
        boolean zA;
        File canonicalFile;
        File parentFile;
        File[] fileArrListFiles;
        if (!file.isDirectory()) {
            boolean zExists = file.exists();
            if (file.delete()) {
                return;
            }
            if (!zExists) {
                throw new FileNotFoundException(b.a.a.a.a.a("File does not exist: ", file));
            }
            throw new IOException(b.a.a.a.a.a("Unable to delete file: ", file));
        }
        if (file.exists()) {
            if (f.f2156a) {
                zA = f.a(file);
            } else if (d.a()) {
                zA = false;
            } else {
                File file2 = file.getParent() == null ? file : new File(file.getParentFile().getCanonicalFile(), file.getName());
                if (file2.getCanonicalFile().equals(file2.getAbsoluteFile()) && (file.exists() || (parentFile = (canonicalFile = file.getCanonicalFile()).getParentFile()) == null || !parentFile.exists() || (fileArrListFiles = parentFile.listFiles(new b(canonicalFile))) == null || fileArrListFiles.length <= 0)) {
                    zA = false;
                } else {
                    zA = true;
                }
            }
            if (!zA) {
                a(file);
            }
            if (!file.delete()) {
                throw new IOException(b.a.a.a.a.a("Unable to delete directory ", file, "."));
            }
        }
    }

    public static FileInputStream d(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(b.a.a.a.a.a("File '", file, "' does not exist"));
        }
        if (file.isDirectory()) {
            throw new IOException(b.a.a.a.a.a("File '", file, "' exists but is a directory"));
        }
        if (file.canRead()) {
            return new FileInputStream(file);
        }
        throw new IOException(b.a.a.a.a.a("File '", file, "' cannot be read"));
    }

    public static void a(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    IOException e2 = null;
                    for (File file2 : fileArrListFiles) {
                        try {
                            c(file2);
                        } catch (IOException e3) {
                            e2 = e3;
                        }
                    }
                    if (e2 != null) {
                        throw e2;
                    }
                    return;
                }
                throw new IOException(b.a.a.a.a.a("Failed to list contents of ", file));
            }
            throw new IllegalArgumentException(file + " is not a directory");
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static List<String> a(File file, String str) {
        FileInputStream fileInputStreamD;
        Charset charsetA = a.a(str);
        try {
            fileInputStreamD = d(file);
            try {
                List<String> listA = e.a(fileInputStreamD, a.a(charsetA));
                e.a(fileInputStreamD);
                return listA;
            } catch (Throwable th) {
                th = th;
                e.a(fileInputStreamD);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamD = null;
        }
    }

    public static void a(File file, String str, Collection<?> collection) {
        FileOutputStream fileOutputStreamA;
        try {
            fileOutputStreamA = a(file, false);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStreamA);
                e.a(collection, null, bufferedOutputStream, str);
                bufferedOutputStream.flush();
                fileOutputStreamA.close();
                e.a(fileOutputStreamA);
            } catch (Throwable th) {
                th = th;
                e.a(fileOutputStreamA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamA = null;
        }
    }
}
