package java.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public class InvalidPathException extends IllegalArgumentException {
    private int index;
    private String input;

    public InvalidPathException(String str, String str2, int i) {
        super(str2);
        if (str == null || str2 == null) {
            throw null;
        }
        if (i < -1) {
            throw new IllegalArgumentException();
        }
        this.input = str;
        this.index = i;
    }

    public InvalidPathException(String str, String str2) {
        this(str, str2, -1);
    }

    public String getInput() {
        return this.input;
    }

    public String getReason() {
        return super.getMessage();
    }

    public int getIndex() {
        return this.index;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(getReason());
        if (this.index > -1) {
            sb.append(" at index ");
            sb.append(this.index);
        }
        sb.append(": ");
        sb.append(this.input);
        return sb.toString();
    }
}
