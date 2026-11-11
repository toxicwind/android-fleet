package j$.nio.file.attribute;

import j$.time.ZoneId;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class PosixFilePermissions {
    private static boolean isSet(char c, char c2) {
        if (c == c2) {
            return true;
        }
        if (c == '-') {
            return false;
        }
        ZoneId.AnonymousClass4.m("Invalid mode");
        return false;
    }

    private static boolean isR(char c) {
        return isSet(c, 'r');
    }

    private static boolean isW(char c) {
        return isSet(c, 'w');
    }

    private static boolean isX(char c) {
        return isSet(c, 'x');
    }

    public static Set<PosixFilePermission> fromString(String str) {
        if (str.length() != 9) {
            ZoneId.AnonymousClass4.m("Invalid mode");
            return null;
        }
        EnumSet enumSetNoneOf = EnumSet.noneOf(PosixFilePermission.class);
        if (isR(str.charAt(0))) {
            enumSetNoneOf.add(PosixFilePermission.OWNER_READ);
        }
        if (isW(str.charAt(1))) {
            enumSetNoneOf.add(PosixFilePermission.OWNER_WRITE);
        }
        if (isX(str.charAt(2))) {
            enumSetNoneOf.add(PosixFilePermission.OWNER_EXECUTE);
        }
        if (isR(str.charAt(3))) {
            enumSetNoneOf.add(PosixFilePermission.GROUP_READ);
        }
        if (isW(str.charAt(4))) {
            enumSetNoneOf.add(PosixFilePermission.GROUP_WRITE);
        }
        if (isX(str.charAt(5))) {
            enumSetNoneOf.add(PosixFilePermission.GROUP_EXECUTE);
        }
        if (isR(str.charAt(6))) {
            enumSetNoneOf.add(PosixFilePermission.OTHERS_READ);
        }
        if (isW(str.charAt(7))) {
            enumSetNoneOf.add(PosixFilePermission.OTHERS_WRITE);
        }
        if (isX(str.charAt(8))) {
            enumSetNoneOf.add(PosixFilePermission.OTHERS_EXECUTE);
        }
        return enumSetNoneOf;
    }

    public static FileAttribute<Set<PosixFilePermission>> asFileAttribute(Set<PosixFilePermission> set) {
        final HashSet hashSet = new HashSet(set);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((PosixFilePermission) it.next()).getClass();
        }
        return new FileAttribute() { // from class: j$.nio.file.attribute.PosixFilePermissions.1
            @Override // j$.nio.file.attribute.FileAttribute
            public String name() {
                return "posix:permissions";
            }

            @Override // j$.nio.file.attribute.FileAttribute
            public Set value() {
                return Collections.unmodifiableSet(hashSet);
            }
        };
    }
}
