package com.grack.nanojson;

import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes6.dex */
public final class JsonAppendableWriter extends JsonWriterBase<JsonAppendableWriter> implements JsonSink<JsonAppendableWriter> {
    JsonAppendableWriter(OutputStream outputStream, String str) {
        super(outputStream, str);
    }

    JsonAppendableWriter(Appendable appendable, String str) {
        super(appendable, str);
    }

    public void done() throws JsonWriterException {
        super.doneInternal();
        try {
            if (this.appendable instanceof Flushable) {
                ((Flushable) this.appendable).flush();
            } else if (this.out != null) {
                this.out.flush();
            }
        } catch (IOException e) {
            throw new JsonWriterException(e);
        }
    }
}
