package j$.nio.file;

import j$.nio.file.attribute.AclFileAttributeView;
import j$.nio.file.attribute.DosFileAttributeView;
import j$.nio.file.attribute.DosFileAttributes;
import j$.nio.file.attribute.FileAttributeConversions;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.UserDefinedFileAttributeView;
import j$.util.ConversionRuntimeException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileApiFlips {
    public static Class flipFileAttributes(Class cls) {
        if (cls == null) {
            return null;
        }
        if (cls == BasicFileAttributes.class) {
            return j$.nio.file.attribute.BasicFileAttributes.class;
        }
        if (cls == j$.nio.file.attribute.BasicFileAttributes.class) {
            return BasicFileAttributes.class;
        }
        if (cls == PosixFileAttributes.class) {
            return j$.nio.file.attribute.PosixFileAttributes.class;
        }
        if (cls == j$.nio.file.attribute.PosixFileAttributes.class) {
            return PosixFileAttributes.class;
        }
        if (cls == DosFileAttributes.class) {
            return java.nio.file.attribute.DosFileAttributes.class;
        }
        if (cls == java.nio.file.attribute.DosFileAttributes.class) {
            return DosFileAttributes.class;
        }
        throw ConversionRuntimeException.exception("java.nio.file.attribute.BasicFileAttributes", cls);
    }

    public static Class flipFileAttributeView(Class cls) {
        if (cls == null) {
            return null;
        }
        if (cls == BasicFileAttributeView.class) {
            return j$.nio.file.attribute.BasicFileAttributeView.class;
        }
        if (cls == j$.nio.file.attribute.BasicFileAttributeView.class) {
            return BasicFileAttributeView.class;
        }
        if (cls == PosixFileAttributeView.class) {
            return j$.nio.file.attribute.PosixFileAttributeView.class;
        }
        if (cls == j$.nio.file.attribute.PosixFileAttributeView.class) {
            return PosixFileAttributeView.class;
        }
        if (cls == FileOwnerAttributeView.class) {
            return j$.nio.file.attribute.FileOwnerAttributeView.class;
        }
        if (cls == j$.nio.file.attribute.FileOwnerAttributeView.class) {
            return FileOwnerAttributeView.class;
        }
        if (cls == DosFileAttributeView.class) {
            return java.nio.file.attribute.DosFileAttributeView.class;
        }
        if (cls == java.nio.file.attribute.DosFileAttributeView.class) {
            return DosFileAttributeView.class;
        }
        if (cls == UserDefinedFileAttributeView.class) {
            return java.nio.file.attribute.UserDefinedFileAttributeView.class;
        }
        if (cls == java.nio.file.attribute.UserDefinedFileAttributeView.class) {
            return UserDefinedFileAttributeView.class;
        }
        if (cls == AclFileAttributeView.class) {
            return java.nio.file.attribute.AclFileAttributeView.class;
        }
        if (cls == java.nio.file.attribute.AclFileAttributeView.class) {
            return AclFileAttributeView.class;
        }
        throw ConversionRuntimeException.exception("java.nio.file.attribute.FileAttributeView", cls);
    }

    public static RuntimeException exceptionOpenOption(Object obj) {
        throw ConversionRuntimeException.exception("java.nio.file.OpenOption", obj);
    }

    public static Set flipOpenOptionSet(Set set) {
        if (set == null || set.isEmpty()) {
            return set;
        }
        HashSet hashSet = new HashSet();
        Object next = set.iterator().next();
        if (next instanceof OpenOption) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                try {
                    hashSet.add(OpenOption.Wrapper.convert((OpenOption) it.next()));
                } catch (ClassCastException e) {
                    throw exceptionOpenOption(e);
                }
            }
        } else if (next instanceof java.nio.file.OpenOption) {
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                try {
                    hashSet.add(OpenOption.VivifiedWrapper.convert((java.nio.file.OpenOption) it2.next()));
                } catch (ClassCastException e2) {
                    throw exceptionOpenOption(e2);
                }
            }
        } else {
            throw exceptionOpenOption(next.getClass());
        }
        return hashSet;
    }

    public static RuntimeException exceptionFileTime(Object obj) {
        throw ConversionRuntimeException.exception("java.nio.file.attribute.FileTime", obj);
    }

    public static Object flipMaybeFileTime(Object obj) {
        if (obj instanceof FileTime) {
            try {
                return FileAttributeConversions.convert((FileTime) obj);
            } catch (ClassCastException e) {
                throw exceptionFileTime(e);
            }
        }
        if (!(obj instanceof j$.nio.file.attribute.FileTime)) {
            return obj;
        }
        try {
            return FileAttributeConversions.convert((j$.nio.file.attribute.FileTime) obj);
        } catch (ClassCastException e2) {
            throw exceptionFileTime(e2);
        }
    }

    public static Map flipMapWithMaybeFileTimeValues(Map map) {
        if (map == null || map.isEmpty()) {
            return map;
        }
        HashMap map2 = new HashMap();
        for (String str : map.keySet()) {
            map2.put(str, flipMaybeFileTime(map.get(str)));
        }
        return map2;
    }

    public static RuntimeException exceptionPosixPermission(Object obj) {
        throw ConversionRuntimeException.exception("java.nio.file.attribute.PosixFilePermission", obj);
    }

    public static Set flipPosixPermissionSet(Set set) {
        if (set == null || set.isEmpty()) {
            return set;
        }
        HashSet hashSet = new HashSet();
        Object next = set.iterator().next();
        if (next instanceof PosixFilePermission) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                try {
                    hashSet.add(PosixFilePermission.EnumConversion.convert((PosixFilePermission) it.next()));
                } catch (ClassCastException e) {
                    throw exceptionPosixPermission(e);
                }
            }
        } else if (next instanceof java.nio.file.attribute.PosixFilePermission) {
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                try {
                    hashSet.add(PosixFilePermission.EnumConversion.convert((java.nio.file.attribute.PosixFilePermission) it2.next()));
                } catch (ClassCastException e2) {
                    throw exceptionPosixPermission(e2);
                }
            }
        } else {
            throw exceptionPosixPermission(next.getClass());
        }
        return hashSet;
    }

    public static RuntimeException exceptionWatchEvent(Object obj) {
        throw ConversionRuntimeException.exception("java.nio.file.WatchEvent", obj);
    }

    public static List flipWatchEventList(List list) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Object obj = list.get(0);
        if (obj instanceof WatchEvent) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    arrayList.add(WatchEvent.Wrapper.convert((WatchEvent) it.next()));
                } catch (ClassCastException e) {
                    throw exceptionWatchEvent(e);
                }
            }
        } else if (obj instanceof java.nio.file.WatchEvent) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                try {
                    arrayList.add(WatchEvent.VivifiedWrapper.convert((java.nio.file.WatchEvent) it2.next()));
                } catch (ClassCastException e2) {
                    throw exceptionWatchEvent(e2);
                }
            }
        } else {
            throw exceptionWatchEvent(obj.getClass());
        }
        return arrayList;
    }
}
