package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public interface FileAttribute<T> {

    public final /* synthetic */ class VivifiedWrapper implements FileAttribute {
        public final /* synthetic */ java.nio.file.attribute.FileAttribute wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.FileAttribute fileAttribute) {
            this.wrappedValue = fileAttribute;
        }

        public static /* synthetic */ FileAttribute convert(java.nio.file.attribute.FileAttribute fileAttribute) {
            if (fileAttribute == null) {
                return null;
            }
            return fileAttribute instanceof Wrapper ? FileAttribute.this : new VivifiedWrapper(fileAttribute);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.FileAttribute fileAttribute = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileAttribute.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.FileAttribute
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.FileAttribute
        public /* synthetic */ Object value() {
            return this.wrappedValue.value();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.FileAttribute {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.FileAttribute convert(FileAttribute fileAttribute) {
            if (fileAttribute == null) {
                return null;
            }
            return fileAttribute instanceof VivifiedWrapper ? ((VivifiedWrapper) fileAttribute).wrappedValue : new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileAttribute fileAttribute = FileAttribute.this;
            if (obj instanceof Wrapper) {
                obj = FileAttribute.this;
            }
            return fileAttribute.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return FileAttribute.this.hashCode();
        }

        @Override // java.nio.file.attribute.FileAttribute
        public /* synthetic */ String name() {
            return FileAttribute.this.name();
        }

        @Override // java.nio.file.attribute.FileAttribute
        public /* synthetic */ Object value() {
            return FileAttribute.this.value();
        }
    }

    String name();

    T value();
}
