package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public interface CopyOption {

    public final /* synthetic */ class VivifiedWrapper implements CopyOption {
        public final /* synthetic */ java.nio.file.CopyOption wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.CopyOption copyOption) {
            this.wrappedValue = copyOption;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [j$.nio.file.CopyOption, java.nio.file.StandardCopyOption] */
        /* JADX WARN: Type inference failed for: r1v4, types: [j$.nio.file.CopyOption, java.nio.file.LinkOption] */
        public static /* synthetic */ CopyOption convert(java.nio.file.CopyOption copyOption) {
            if (copyOption == 0) {
                return null;
            }
            if (copyOption instanceof Wrapper) {
                return CopyOption.this;
            }
            if (copyOption instanceof LinkOption) {
                return LinkOption.EnumConversion.convert((LinkOption) copyOption);
            }
            return copyOption instanceof StandardCopyOption ? StandardCopyOption.EnumConversion.convert((StandardCopyOption) copyOption) : new VivifiedWrapper(copyOption);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.CopyOption copyOption = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return copyOption.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.CopyOption {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.CopyOption convert(CopyOption copyOption) {
            if (copyOption == null) {
                return null;
            }
            if (copyOption instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) copyOption).wrappedValue;
            }
            if (copyOption instanceof LinkOption) {
                return LinkOption.EnumConversion.convert((LinkOption) copyOption);
            }
            return copyOption instanceof StandardCopyOption ? StandardCopyOption.EnumConversion.convert((StandardCopyOption) copyOption) : copyOption.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            CopyOption copyOption = CopyOption.this;
            if (obj instanceof Wrapper) {
                obj = CopyOption.this;
            }
            return copyOption.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return CopyOption.this.hashCode();
        }
    }
}
