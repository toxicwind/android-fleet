package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public enum LinkOption implements OpenOption, CopyOption {
    NOFOLLOW_LINKS;

    public abstract /* synthetic */ class EnumConversion {
        public static /* synthetic */ LinkOption convert(java.nio.file.LinkOption linkOption) {
            if (linkOption == null) {
                return null;
            }
            return LinkOption.NOFOLLOW_LINKS;
        }

        public static /* synthetic */ java.nio.file.LinkOption convert(LinkOption linkOption) {
            if (linkOption == null) {
                return null;
            }
            return java.nio.file.LinkOption.NOFOLLOW_LINKS;
        }
    }
}
