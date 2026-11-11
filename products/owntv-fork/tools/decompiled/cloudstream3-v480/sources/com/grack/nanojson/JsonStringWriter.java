package com.grack.nanojson;

/* JADX INFO: loaded from: classes6.dex */
public final class JsonStringWriter extends JsonWriterBase<JsonStringWriter> {
    JsonStringWriter(String str) {
        super(new StringBuilder(), str);
    }

    public String done() {
        super.doneInternal();
        return this.appendable.toString();
    }
}
