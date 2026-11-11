package j$.nio.file.attribute;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface BasicFileAttributeView extends FileAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements BasicFileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.BasicFileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.BasicFileAttributeView basicFileAttributeView) {
            this.wrappedValue = basicFileAttributeView;
        }

        public static /* synthetic */ BasicFileAttributeView convert(java.nio.file.attribute.BasicFileAttributeView basicFileAttributeView) {
            if (basicFileAttributeView == null) {
                return null;
            }
            if (basicFileAttributeView instanceof Wrapper) {
                return BasicFileAttributeView.this;
            }
            if (basicFileAttributeView instanceof java.nio.file.attribute.DosFileAttributeView) {
                return DosFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.DosFileAttributeView) basicFileAttributeView);
            }
            return basicFileAttributeView instanceof java.nio.file.attribute.PosixFileAttributeView ? PosixFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.PosixFileAttributeView) basicFileAttributeView) : new VivifiedWrapper(basicFileAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.BasicFileAttributeView basicFileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return basicFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ BasicFileAttributes readAttributes() {
            return BasicFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes());
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
            this.wrappedValue.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.BasicFileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.BasicFileAttributeView convert(BasicFileAttributeView basicFileAttributeView) {
            if (basicFileAttributeView == null) {
                return null;
            }
            if (basicFileAttributeView instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) basicFileAttributeView).wrappedValue;
            }
            if (basicFileAttributeView instanceof DosFileAttributeView) {
                return DosFileAttributeView.Wrapper.convert((DosFileAttributeView) basicFileAttributeView);
            }
            return basicFileAttributeView instanceof PosixFileAttributeView ? PosixFileAttributeView.Wrapper.convert((PosixFileAttributeView) basicFileAttributeView) : basicFileAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            BasicFileAttributeView basicFileAttributeView = BasicFileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = BasicFileAttributeView.this;
            }
            return basicFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return BasicFileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return BasicFileAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ java.nio.file.attribute.BasicFileAttributes readAttributes() {
            return BasicFileAttributes.Wrapper.convert(BasicFileAttributeView.this.readAttributes());
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(java.nio.file.attribute.FileTime fileTime, java.nio.file.attribute.FileTime fileTime2, java.nio.file.attribute.FileTime fileTime3) {
            BasicFileAttributeView.this.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    @Override // j$.nio.file.attribute.AttributeView
    String name();

    BasicFileAttributes readAttributes();

    void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3);
}
