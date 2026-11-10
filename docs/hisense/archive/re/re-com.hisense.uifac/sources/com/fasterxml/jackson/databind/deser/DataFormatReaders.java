package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DataFormatReaders {

    public static class Match {
        public abstract JsonParser createParserWithMatch() throws IOException;

        public abstract ObjectReader getReader();

        public abstract boolean hasMatch();
    }

    public abstract Match findFormat(byte[] bArr, int i, int i2) throws IOException;

    public abstract String toString();
}
