package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public interface OpenOption {

    public final /* synthetic */ class VivifiedWrapper implements OpenOption {
        public final /* synthetic */ java.nio.file.OpenOption wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.OpenOption openOption) {
            this.wrappedValue = openOption;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [j$.nio.file.OpenOption, java.nio.file.StandardOpenOption] */
        /* JADX WARN: Type inference failed for: r1v4, types: [j$.nio.file.OpenOption, java.nio.file.LinkOption] */
        public static /* synthetic */ OpenOption convert(java.nio.file.OpenOption openOption) {
            if (openOption == 0) {
                return null;
            }
            if (openOption instanceof Wrapper) {
                return OpenOption.this;
            }
            if (openOption instanceof LinkOption) {
                return LinkOption.EnumConversion.convert((LinkOption) openOption);
            }
            return openOption instanceof StandardOpenOption ? StandardOpenOption.EnumConversion.convert((StandardOpenOption) openOption) : new VivifiedWrapper(openOption);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.OpenOption openOption = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return openOption.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.OpenOption {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.OpenOption convert(OpenOption openOption) {
            if (openOption == null) {
                return null;
            }
            if (openOption instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) openOption).wrappedValue;
            }
            if (openOption instanceof LinkOption) {
                return LinkOption.EnumConversion.convert((LinkOption) openOption);
            }
            return openOption instanceof StandardOpenOption ? StandardOpenOption.EnumConversion.convert((StandardOpenOption) openOption) : openOption.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            OpenOption openOption = OpenOption.this;
            if (obj instanceof Wrapper) {
                obj = OpenOption.this;
            }
            return openOption.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return OpenOption.this.hashCode();
        }
    }
}
