package j$.desugar.sun.nio.fs;

import io.ktor.http.ContentDisposition;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes3.dex */
abstract class DesugarAbstractBasicFileAttributeView implements BasicFileAttributeView {
    static final Set basicAttributeNames = DesugarUtil.newSet(ContentDisposition.Parameters.Size, "creationTime", "lastAccessTime", "lastModifiedTime", "fileKey", "isDirectory", "isRegularFile", "isSymbolicLink", "isOther");

    protected DesugarAbstractBasicFileAttributeView() {
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
    public String name() {
        return "basic";
    }

    public void setAttribute(String str, Object obj) {
        if (str.equals("lastModifiedTime")) {
            setTimes((FileTime) obj, null, null);
            return;
        }
        if (str.equals("lastAccessTime")) {
            setTimes(null, (FileTime) obj, null);
            return;
        }
        if (str.equals("creationTime")) {
            setTimes(null, null, (FileTime) obj);
            return;
        }
        throw new IllegalArgumentException("'" + name() + ":" + str + "' not recognized");
    }

    static class AttributesBuilder {
        private boolean copyAll;
        private Set names = new HashSet();
        private Map map = new HashMap();

        private AttributesBuilder(Set set, String[] strArr) {
            for (String str : strArr) {
                if (str.equals(Marker.ANY_MARKER)) {
                    this.copyAll = true;
                } else {
                    if (!set.contains(str)) {
                        throw new IllegalArgumentException("'" + str + "' not recognized");
                    }
                    this.names.add(str);
                }
            }
        }

        static AttributesBuilder create(Set set, String[] strArr) {
            return new AttributesBuilder(set, strArr);
        }

        boolean match(String str) {
            return this.copyAll || this.names.contains(str);
        }

        void add(String str, Object obj) {
            this.map.put(str, obj);
        }

        Map unmodifiableMap() {
            return Collections.unmodifiableMap(this.map);
        }
    }

    final void addRequestedBasicAttributes(BasicFileAttributes basicFileAttributes, AttributesBuilder attributesBuilder) {
        if (attributesBuilder.match(ContentDisposition.Parameters.Size)) {
            attributesBuilder.add(ContentDisposition.Parameters.Size, Long.valueOf(basicFileAttributes.size()));
        }
        if (attributesBuilder.match("creationTime")) {
            attributesBuilder.add("creationTime", basicFileAttributes.creationTime());
        }
        if (attributesBuilder.match("lastAccessTime")) {
            attributesBuilder.add("lastAccessTime", basicFileAttributes.lastAccessTime());
        }
        if (attributesBuilder.match("lastModifiedTime")) {
            attributesBuilder.add("lastModifiedTime", basicFileAttributes.lastModifiedTime());
        }
        if (attributesBuilder.match("fileKey")) {
            attributesBuilder.add("fileKey", basicFileAttributes.fileKey());
        }
        if (attributesBuilder.match("isDirectory")) {
            attributesBuilder.add("isDirectory", Boolean.valueOf(basicFileAttributes.isDirectory()));
        }
        if (attributesBuilder.match("isRegularFile")) {
            attributesBuilder.add("isRegularFile", Boolean.valueOf(basicFileAttributes.isRegularFile()));
        }
        if (attributesBuilder.match("isSymbolicLink")) {
            attributesBuilder.add("isSymbolicLink", Boolean.valueOf(basicFileAttributes.isSymbolicLink()));
        }
        if (attributesBuilder.match("isOther")) {
            attributesBuilder.add("isOther", Boolean.valueOf(basicFileAttributes.isOther()));
        }
    }

    public Map readAttributes(String[] strArr) {
        AttributesBuilder attributesBuilderCreate = AttributesBuilder.create(basicAttributeNames, strArr);
        addRequestedBasicAttributes(readAttributes(), attributesBuilderCreate);
        return attributesBuilderCreate.unmodifiableMap();
    }
}
