package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public interface FileAttributeView extends AttributeView {

    public final /* synthetic */ class VivifiedWrapper implements FileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.FileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.FileAttributeView fileAttributeView) {
            this.wrappedValue = fileAttributeView;
        }

        public static /* synthetic */ FileAttributeView convert(java.nio.file.attribute.FileAttributeView fileAttributeView) {
            if (fileAttributeView == null) {
                return null;
            }
            if (fileAttributeView instanceof Wrapper) {
                return FileAttributeView.this;
            }
            if (fileAttributeView instanceof java.nio.file.attribute.BasicFileAttributeView) {
                return BasicFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.BasicFileAttributeView) fileAttributeView);
            }
            if (fileAttributeView instanceof java.nio.file.attribute.FileOwnerAttributeView) {
                return FileOwnerAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.FileOwnerAttributeView) fileAttributeView);
            }
            return fileAttributeView instanceof java.nio.file.attribute.UserDefinedFileAttributeView ? UserDefinedFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.UserDefinedFileAttributeView) fileAttributeView) : new VivifiedWrapper(fileAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.FileAttributeView fileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.FileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.FileAttributeView convert(FileAttributeView fileAttributeView) {
            if (fileAttributeView == null) {
                return null;
            }
            if (fileAttributeView instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) fileAttributeView).wrappedValue;
            }
            if (fileAttributeView instanceof BasicFileAttributeView) {
                return BasicFileAttributeView.Wrapper.convert((BasicFileAttributeView) fileAttributeView);
            }
            if (fileAttributeView instanceof FileOwnerAttributeView) {
                return FileOwnerAttributeView.Wrapper.convert((FileOwnerAttributeView) fileAttributeView);
            }
            return fileAttributeView instanceof UserDefinedFileAttributeView ? UserDefinedFileAttributeView.Wrapper.convert((UserDefinedFileAttributeView) fileAttributeView) : fileAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileAttributeView fileAttributeView = FileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = FileAttributeView.this;
            }
            return fileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return FileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return FileAttributeView.this.name();
        }
    }
}
