package j$.nio.file.attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface UserDefinedFileAttributeView extends FileAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements UserDefinedFileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.UserDefinedFileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.UserDefinedFileAttributeView userDefinedFileAttributeView) {
            this.wrappedValue = userDefinedFileAttributeView;
        }

        public static /* synthetic */ UserDefinedFileAttributeView convert(java.nio.file.attribute.UserDefinedFileAttributeView userDefinedFileAttributeView) {
            if (userDefinedFileAttributeView == null) {
                return null;
            }
            return userDefinedFileAttributeView instanceof Wrapper ? UserDefinedFileAttributeView.this : new VivifiedWrapper(userDefinedFileAttributeView);
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ void delete(String str) throws IOException {
            this.wrappedValue.delete(str);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.UserDefinedFileAttributeView userDefinedFileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return userDefinedFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ List list() {
            return this.wrappedValue.list();
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int read(String str, ByteBuffer byteBuffer) {
            return this.wrappedValue.read(str, byteBuffer);
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int size(String str) {
            return this.wrappedValue.size(str);
        }

        @Override // j$.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int write(String str, ByteBuffer byteBuffer) {
            return this.wrappedValue.write(str, byteBuffer);
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.UserDefinedFileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.UserDefinedFileAttributeView convert(UserDefinedFileAttributeView userDefinedFileAttributeView) {
            if (userDefinedFileAttributeView == null) {
                return null;
            }
            return userDefinedFileAttributeView instanceof VivifiedWrapper ? ((VivifiedWrapper) userDefinedFileAttributeView).wrappedValue : userDefinedFileAttributeView.new Wrapper();
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ void delete(String str) {
            UserDefinedFileAttributeView.this.delete(str);
        }

        public /* synthetic */ boolean equals(Object obj) {
            UserDefinedFileAttributeView userDefinedFileAttributeView = UserDefinedFileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = UserDefinedFileAttributeView.this;
            }
            return userDefinedFileAttributeView.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return UserDefinedFileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ List list() {
            return UserDefinedFileAttributeView.this.list();
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView, java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return UserDefinedFileAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int read(String str, ByteBuffer byteBuffer) {
            return UserDefinedFileAttributeView.this.read(str, byteBuffer);
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int size(String str) {
            return UserDefinedFileAttributeView.this.size(str);
        }

        @Override // java.nio.file.attribute.UserDefinedFileAttributeView
        public /* synthetic */ int write(String str, ByteBuffer byteBuffer) {
            return UserDefinedFileAttributeView.this.write(str, byteBuffer);
        }
    }

    void delete(String str);

    List list();

    @Override // j$.nio.file.attribute.AttributeView
    String name();

    int read(String str, ByteBuffer byteBuffer);

    int size(String str);

    int write(String str, ByteBuffer byteBuffer);
}
