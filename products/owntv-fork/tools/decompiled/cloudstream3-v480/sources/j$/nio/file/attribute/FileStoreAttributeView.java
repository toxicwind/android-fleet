package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public interface FileStoreAttributeView extends AttributeView {

    public final /* synthetic */ class VivifiedWrapper implements FileStoreAttributeView {
        public final /* synthetic */ java.nio.file.attribute.FileStoreAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.FileStoreAttributeView fileStoreAttributeView) {
            this.wrappedValue = fileStoreAttributeView;
        }

        public static /* synthetic */ FileStoreAttributeView convert(java.nio.file.attribute.FileStoreAttributeView fileStoreAttributeView) {
            if (fileStoreAttributeView == null) {
                return null;
            }
            return fileStoreAttributeView instanceof Wrapper ? FileStoreAttributeView.this : new VivifiedWrapper(fileStoreAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.FileStoreAttributeView fileStoreAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileStoreAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.FileStoreAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.FileStoreAttributeView convert(FileStoreAttributeView fileStoreAttributeView) {
            if (fileStoreAttributeView == null) {
                return null;
            }
            return fileStoreAttributeView instanceof VivifiedWrapper ? ((VivifiedWrapper) fileStoreAttributeView).wrappedValue : fileStoreAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileStoreAttributeView fileStoreAttributeView = FileStoreAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = FileStoreAttributeView.this;
            }
            return fileStoreAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return FileStoreAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return FileStoreAttributeView.this.name();
        }
    }
}
