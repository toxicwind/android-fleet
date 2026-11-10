package com.ju.lib.utils.file;

import b.a.a.a.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ZipUtils {
    public static final int BUFF_SIZE = 1048576;

    public static Enumeration<?> getEntriesEnumeration(File file) {
        return new ZipFile(file).entries();
    }

    public static ArrayList<String> getEntriesNames(File file) {
        ArrayList<String> arrayList = new ArrayList<>();
        Enumeration<?> entriesEnumeration = getEntriesEnumeration(file);
        while (entriesEnumeration.hasMoreElements()) {
            arrayList.add(new String(getEntryName((ZipEntry) entriesEnumeration.nextElement()).getBytes("GB2312"), "8859_1"));
        }
        return arrayList;
    }

    public static String getEntryComment(ZipEntry zipEntry) {
        return new String(zipEntry.getComment().getBytes("GB2312"), "8859_1");
    }

    public static String getEntryName(ZipEntry zipEntry) {
        return new String(zipEntry.getName().getBytes("GB2312"), "8859_1");
    }

    public static void unZipFile(File file, String str) throws Throwable {
        InputStream inputStream;
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file);
        FileOutputStream fileOutputStream = null;
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                inputStream = zipFile.getInputStream(zipEntryNextElement);
                try {
                    File file3 = new File(new String((str + File.separator + zipEntryNextElement.getName()).getBytes(Charset.defaultCharset()), "utf-8"));
                    if (!file3.exists()) {
                        File parentFile = file3.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        if (zipEntryNextElement.isDirectory()) {
                            file3.mkdir();
                        } else {
                            file3.createNewFile();
                        }
                    }
                    if (file3.isFile()) {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                        try {
                            byte[] bArr = new byte[1048576];
                            while (true) {
                                int i = inputStream.read(bArr);
                                if (i <= 0) {
                                    break;
                                } else {
                                    fileOutputStream2.write(bArr, 0, i);
                                }
                            }
                            fileOutputStream2.close();
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            try {
                                zipFile.close();
                                throw th;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                throw th;
                            }
                        }
                    }
                    inputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            try {
                zipFile.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    @Deprecated
    public static void upZipFile(File file, String str) throws Throwable {
        unZipFile(file, str);
    }

    public static ArrayList<File> upZipSelectedFile(File file, String str, String str2) throws IOException {
        ArrayList<File> arrayList = new ArrayList<>();
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (zipEntryNextElement.getName().contains(str2)) {
                InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                StringBuilder sbA = a.a(str);
                sbA.append(File.separator);
                sbA.append(zipEntryNextElement.getName());
                File file3 = new File(new String(sbA.toString().getBytes("8859_1"), "GB2312"));
                if (!file3.exists()) {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file3.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                byte[] bArr = new byte[1048576];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
                inputStream.close();
                fileOutputStream.close();
                zipFile.close();
                arrayList.add(file3);
            }
        }
        return arrayList;
    }

    public static void zipFile(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        StringBuilder sbA = a.a(str);
        sbA.append(str.trim().length() == 0 ? "" : File.separator);
        sbA.append(file.getName());
        String str2 = new String(sbA.toString().getBytes(Charset.defaultCharset()), "utf-8");
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            zipOutputStream.putNextEntry(new ZipEntry(a.a(str2, "/")));
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    zipFile(file2, zipOutputStream, str2);
                }
                return;
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int i = bufferedInputStream.read(bArr);
            if (i == -1) {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, i);
        }
    }

    public static void zipFiles(Collection<File> collection, File file) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            zipFile(it.next(), zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    public static void zipFiles(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            zipFile(it.next(), zipOutputStream, "");
        }
        zipOutputStream.setComment(str);
        zipOutputStream.close();
    }
}
