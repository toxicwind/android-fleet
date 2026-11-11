package j$.nio.file.attribute;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface DosFileAttributeView extends BasicFileAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements DosFileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.DosFileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.DosFileAttributeView dosFileAttributeView) {
            this.wrappedValue = dosFileAttributeView;
        }

        public static /* synthetic */ DosFileAttributeView convert(java.nio.file.attribute.DosFileAttributeView dosFileAttributeView) {
            if (dosFileAttributeView == null) {
                return null;
            }
            return dosFileAttributeView instanceof Wrapper ? DosFileAttributeView.this : new VivifiedWrapper(dosFileAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.DosFileAttributeView dosFileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return dosFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView, j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ BasicFileAttributes readAttributes() {
            return BasicFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes());
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView, j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ DosFileAttributes readAttributes() {
            return DosFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes());
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setArchive(boolean z) throws IOException {
            this.wrappedValue.setArchive(z);
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setHidden(boolean z) throws IOException {
            this.wrappedValue.setHidden(z);
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setReadOnly(boolean z) throws IOException {
            this.wrappedValue.setReadOnly(z);
        }

        @Override // j$.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setSystem(boolean z) throws IOException {
            this.wrappedValue.setSystem(z);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
            this.wrappedValue.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.DosFileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.DosFileAttributeView convert(DosFileAttributeView dosFileAttributeView) {
            if (dosFileAttributeView == null) {
                return null;
            }
            return dosFileAttributeView instanceof VivifiedWrapper ? ((VivifiedWrapper) dosFileAttributeView).wrappedValue : dosFileAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            DosFileAttributeView dosFileAttributeView = DosFileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = DosFileAttributeView.this;
            }
            return dosFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return DosFileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.DosFileAttributeView, java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return DosFileAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.DosFileAttributeView, java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ java.nio.file.attribute.BasicFileAttributes readAttributes() {
            return BasicFileAttributes.Wrapper.convert(DosFileAttributeView.this.readAttributes());
        }

        @Override // java.nio.file.attribute.DosFileAttributeView, java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ java.nio.file.attribute.DosFileAttributes readAttributes() {
            return DosFileAttributes.Wrapper.convert(DosFileAttributeView.this.readAttributes());
        }

        @Override // java.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setArchive(boolean z) {
            DosFileAttributeView.this.setArchive(z);
        }

        @Override // java.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setHidden(boolean z) {
            DosFileAttributeView.this.setHidden(z);
        }

        @Override // java.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setReadOnly(boolean z) {
            DosFileAttributeView.this.setReadOnly(z);
        }

        @Override // java.nio.file.attribute.DosFileAttributeView
        public /* synthetic */ void setSystem(boolean z) {
            DosFileAttributeView.this.setSystem(z);
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(java.nio.file.attribute.FileTime fileTime, java.nio.file.attribute.FileTime fileTime2, java.nio.file.attribute.FileTime fileTime3) {
            DosFileAttributeView.this.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
    String name();

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    DosFileAttributes readAttributes();

    void setArchive(boolean z);

    void setHidden(boolean z);

    void setReadOnly(boolean z);

    void setSystem(boolean z);
}
