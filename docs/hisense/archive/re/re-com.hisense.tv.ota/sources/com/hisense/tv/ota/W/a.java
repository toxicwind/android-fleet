package com.hisense.tv.ota.W;

import android.util.Log;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(String str) {
        if (str == null) {
            Log.e("ChmodZipPackageUtils", "filePath is null");
            return;
        }
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(Paths.get(str, new String[0]), PosixFileAttributeView.class, new LinkOption[0]);
        if (posixFileAttributeView == null) {
            Log.e("ChmodZipPackageUtils", "ChmodFile failed, posixView is null");
            return;
        }
        Log.i("ChmodZipPackageUtils", "old permissions");
        b(posixFileAttributeView);
        c(posixFileAttributeView);
        Log.i("ChmodZipPackageUtils", "new permissions");
        b(posixFileAttributeView);
    }

    public static void b(PosixFileAttributeView posixFileAttributeView) {
        try {
            Log.i("ChmodZipPackageUtils", " permissions = " + PosixFilePermissions.toString(posixFileAttributeView.readAttributes().permissions()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void c(PosixFileAttributeView posixFileAttributeView) {
        try {
            posixFileAttributeView.setPermissions(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
            System.out.println("Permissions set successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
