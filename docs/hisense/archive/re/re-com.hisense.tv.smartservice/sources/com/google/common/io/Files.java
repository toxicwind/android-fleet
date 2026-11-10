package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class Files {
    public static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>() { // from class: com.google.common.io.Files.2
        @Override // com.google.common.graph.SuccessorsFunction
        public Iterable<File> successors(File file) {
            File[] fileArrListFiles;
            return (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) ? ImmutableList.of() : Collections.unmodifiableList(Arrays.asList(fileArrListFiles));
        }
    };
    public static final int TEMP_DIR_ATTEMPTS = 10000;

    private static final class FileByteSink extends ByteSink {
        public final File file;
        public final ImmutableSet<FileWriteMode> modes;

        public String toString() {
            String strValueOf = String.valueOf(this.file);
            String strValueOf2 = String.valueOf(this.modes);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 20, "Files.asByteSink(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }

        public FileByteSink(File file, FileWriteMode... fileWriteModeArr) {
            if (file == null) {
                throw new NullPointerException();
            }
            this.file = file;
            this.modes = ImmutableSet.copyOf(fileWriteModeArr);
        }

        @Override // com.google.common.io.ByteSink
        public FileOutputStream openStream() {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }
    }

    private static final class FileByteSource extends ByteSource {
        public final File file;

        public FileByteSource(File file) {
            if (file == null) {
                throw new NullPointerException();
            }
            this.file = file;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: X */
        @Override // com.google.common.io.ByteSource
        public byte[] read() throws X {
            Closer closerCreate = Closer.create();
            try {
                try {
                    FileInputStream fileInputStream = (FileInputStream) closerCreate.register(openStream());
                    byte[] byteArray = ByteStreams.toByteArray(fileInputStream, fileInputStream.getChannel().size());
                    closerCreate.close();
                    return byteArray;
                } catch (Throwable th) {
                    throw closerCreate.rethrow(th);
                }
            } catch (Throwable th2) {
                closerCreate.close();
                throw th2;
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws FileNotFoundException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return this.file.isFile() ? Optional.of(Long.valueOf(this.file.length())) : Absent.INSTANCE;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.file);
            return a.a(strValueOf.length() + 20, "Files.asByteSource(", strValueOf, ")");
        }

        @Override // com.google.common.io.ByteSource
        public FileInputStream openStream() {
            return new FileInputStream(this.file);
        }

        public /* synthetic */ FileByteSource(File file, AnonymousClass1 anonymousClass1) {
            if (file != null) {
                this.file = file;
                return;
            }
            throw new NullPointerException();
        }
    }

    private enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY { // from class: com.google.common.io.Files.FilePredicate.1
            @Override // java.lang.Enum
            public String toString() {
                return "Files.isDirectory()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE { // from class: com.google.common.io.Files.FilePredicate.2
            @Override // java.lang.Enum
            public String toString() {
                return "Files.isFile()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isFile();
            }
        };

        /* synthetic */ FilePredicate(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    @Deprecated
    public static void append(CharSequence charSequence, File file, Charset charset) throws X {
        asCharSink(file, charset, FileWriteMode.APPEND).write(charSequence);
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr);
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file, null);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    public static void copy(File file, OutputStream outputStream) throws X {
        asByteSource(file).copyTo(outputStream);
    }

    @Beta
    public static void createParentDirs(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        String strValueOf = String.valueOf(file);
        throw new IOException(a.a(strValueOf.length() + 39, "Unable to create parent directories of ", strValueOf));
    }

    @Beta
    @Deprecated
    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(21);
        sb.append(jCurrentTimeMillis);
        sb.append(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        String string = sb.toString();
        for (int i = 0; i < 10000; i++) {
            File file2 = new File(file, a.a(a.a((Object) string, 11), string, i));
            if (file2.mkdir()) {
                return file2;
            }
        }
        StringBuilder sbA = a.a(a.a((Object) string, a.a((Object) string, 66)), "Failed to create directory within 10000 attempts (tried ", string, "0 to ", string);
        sbA.append(9999);
        sbA.append(')');
        throw new IllegalStateException(sbA.toString());
    }

    @Beta
    public static boolean equal(File file, File file2) {
        if (file == null) {
            throw new NullPointerException();
        }
        if (file2 == null) {
            throw new NullPointerException();
        }
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return asByteSource(file).contentEquals(asByteSource(file2));
        }
        return false;
    }

    @Beta
    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    @Beta
    public static String getFileExtension(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        String name = new File(str).getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : name.substring(iLastIndexOf + 1);
    }

    @Beta
    public static String getNameWithoutExtension(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        String name = new File(str).getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? name : name.substring(0, iLastIndexOf);
    }

    @Beta
    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) {
        return asByteSource(file).hash(hashFunction);
    }

    @Beta
    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    @Beta
    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }

    @Beta
    public static MappedByteBuffer map(File file) {
        if (file != null) {
            return map(file, FileChannel.MapMode.READ_ONLY);
        }
        throw new NullPointerException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public static MappedByteBuffer mapInternal(File file, FileChannel.MapMode mapMode, long j) throws X {
        if (file == null) {
            throw new NullPointerException();
        }
        if (mapMode == null) {
            throw new NullPointerException();
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                FileChannel fileChannel = (FileChannel) closerCreate.register(((RandomAccessFile) closerCreate.register(new RandomAccessFile(file, mapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw"))).getChannel());
                if (j == -1) {
                    j = fileChannel.size();
                }
                MappedByteBuffer map = fileChannel.map(mapMode, 0L, j);
                closerCreate.close();
                return map;
            } catch (Throwable th) {
                throw closerCreate.rethrow(th);
            }
        } catch (Throwable th2) {
            closerCreate.close();
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    public static void move(File file, File file2) throws X, IOException {
        if (file == null) {
            throw new NullPointerException();
        }
        if (file2 == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (file.renameTo(file2)) {
            return;
        }
        copy(file, file2);
        if (file.delete()) {
            return;
        }
        if (file2.delete()) {
            String strValueOf = String.valueOf(file);
            throw new IOException(a.a(strValueOf.length() + 17, "Unable to delete ", strValueOf));
        }
        String strValueOf2 = String.valueOf(file2);
        throw new IOException(a.a(strValueOf2.length() + 17, "Unable to delete ", strValueOf2));
    }

    @Beta
    public static BufferedReader newReader(File file, Charset charset) {
        if (file == null) {
            throw new NullPointerException();
        }
        if (charset != null) {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        }
        throw new NullPointerException();
    }

    @Beta
    public static BufferedWriter newWriter(File file, Charset charset) {
        if (file == null) {
            throw new NullPointerException();
        }
        if (charset != null) {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    @Beta
    @Deprecated
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) {
        return (T) asByteSource(file).read(byteProcessor);
    }

    @Beta
    @Deprecated
    public static String readFirstLine(File file, Charset charset) {
        return asCharSource(file, charset).readFirstLine();
    }

    @Beta
    public static List<String> readLines(File file, Charset charset) {
        return (List) asCharSource(file, charset).readLines(new LineProcessor<List<String>>() { // from class: com.google.common.io.Files.1
            public final List<String> result = new ArrayList();

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String str) {
                this.result.add(str);
                return true;
            }

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:20:0x004f  */
    @Beta
    public static String simplifyPath(String str) {
        String strSubstring;
        if (str == null) {
            throw new NullPointerException();
        }
        if (str.length() == 0) {
            return ".";
        }
        Iterable<String> iterableSplit = Splitter.on('/').omitEmptyStrings().split(str);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = iterableSplit.iterator();
        while (true) {
            byte b2 = 0;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            int iHashCode = next.hashCode();
            if (iHashCode != 46) {
                if (iHashCode == 1472 && next.equals("..")) {
                    b2 = 1;
                } else {
                    b2 = -1;
                }
            } else if (!next.equals(".")) {
                b2 = -1;
            }
            if (b2 != 0) {
                if (b2 != 1) {
                    arrayList.add(next);
                } else if (arrayList.size() <= 0 || ((String) arrayList.get(arrayList.size() - 1)).equals("..")) {
                    arrayList.add("..");
                } else {
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
        String strJoin = Joiner.on('/').join(arrayList);
        if (str.charAt(0) == '/') {
            String strValueOf = String.valueOf(strJoin);
            strSubstring = strValueOf.length() != 0 ? "/".concat(strValueOf) : new String("/");
        } else {
            strSubstring = strJoin;
        }
        while (strSubstring.startsWith("/../")) {
            strSubstring = strSubstring.substring(3);
        }
        if (strSubstring.equals("/..")) {
            return "/";
        }
        return "".equals(strSubstring) ? "." : strSubstring;
    }

    @Beta
    public static byte[] toByteArray(File file) {
        return asByteSource(file).read();
    }

    @Beta
    @Deprecated
    public static String toString(File file, Charset charset) {
        return asCharSource(file, charset).read();
    }

    @Beta
    public static void touch(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }
        if (file.createNewFile() || file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        String strValueOf = String.valueOf(file);
        throw new IOException(a.a(strValueOf.length() + 38, "Unable to update modification time of ", strValueOf));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    public static void write(byte[] bArr, File file) throws X {
        new FileByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    public static void copy(File file, File file2) throws X {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(new FileByteSink(file2, new FileWriteMode[0]));
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode) {
        return mapInternal(file, mapMode, -1L);
    }

    @CanIgnoreReturnValue
    @Beta
    @Deprecated
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) {
        return (T) asCharSource(file, charset).readLines(lineProcessor);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode, long j) {
        Preconditions.checkArgument(j >= 0, "size (%s) may not be negative", j);
        return mapInternal(file, mapMode, j);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    @Deprecated
    public static void write(CharSequence charSequence, File file, Charset charset) throws X {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Beta
    @Deprecated
    public static void copy(File file, Charset charset, Appendable appendable) throws X {
        asCharSource(file, charset).copyTo(appendable);
    }
}
