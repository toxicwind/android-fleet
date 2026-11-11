package com.grack.nanojson;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes6.dex */
public final class JsonWriter {

    public static final class JsonWriterContext {
        private final String indent;

        private JsonWriterContext(String str) {
            this.indent = str;
        }

        public JsonAppendableWriter on(OutputStream outputStream) {
            return new JsonAppendableWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")), this.indent);
        }

        public JsonAppendableWriter on(PrintStream printStream) {
            return new JsonAppendableWriter((Appendable) printStream, this.indent);
        }

        public JsonAppendableWriter on(Appendable appendable) {
            return new JsonAppendableWriter(appendable, this.indent);
        }

        public JsonStringWriter string() {
            return new JsonStringWriter(this.indent);
        }
    }

    private JsonWriter() {
    }

    public static String escape(String str) {
        String strString = string(str);
        return strString.substring(1, strString.length() - 1);
    }

    public static JsonWriterContext indent(String str) {
        if (str == null) {
            throw new IllegalArgumentException("indent must be non-null");
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && str.charAt(i) != '\t') {
                throw new IllegalArgumentException("Only tabs and spaces are allowed for indent.");
            }
        }
        return new JsonWriterContext(str);
    }

    public static JsonAppendableWriter on(OutputStream outputStream) {
        return new JsonAppendableWriter(outputStream, (String) null);
    }

    public static JsonAppendableWriter on(PrintStream printStream) {
        return new JsonAppendableWriter((Appendable) printStream, (String) null);
    }

    public static JsonAppendableWriter on(Appendable appendable) {
        return new JsonAppendableWriter(appendable, (String) null);
    }

    public static JsonStringWriter string() {
        return new JsonStringWriter(null);
    }

    public static String string(Object obj) {
        return ((JsonStringWriter) new JsonStringWriter(null).value(obj)).done();
    }
}
