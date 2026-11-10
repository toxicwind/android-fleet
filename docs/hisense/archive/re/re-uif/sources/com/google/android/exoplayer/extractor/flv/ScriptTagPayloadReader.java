package com.google.android.exoplayer.extractor.flv;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
final class ScriptTagPayloadReader extends TagPayloadReader {
    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected boolean parseHeader(ParsableByteArray parsableByteArray) {
        return true;
    }

    public ScriptTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected void parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException {
        if (readAmfType(parsableByteArray) != 2) {
            throw new ParserException();
        }
        if ("onMetaData".equals(readAmfString(parsableByteArray))) {
            if (readAmfType(parsableByteArray) != 8) {
                throw new ParserException();
            }
            HashMap<String, Object> amfEcmaArray = readAmfEcmaArray(parsableByteArray);
            if (amfEcmaArray.containsKey("duration")) {
                double dDoubleValue = ((Double) amfEcmaArray.get("duration")).doubleValue();
                if (dDoubleValue > 0.0d) {
                    setDurationUs((long) (dDoubleValue * 1000000.0d));
                }
            }
        }
    }

    private static int readAmfType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.readUnsignedByte();
    }

    private static Boolean readAmfBoolean(ParsableByteArray parsableByteArray) {
        return Boolean.valueOf(parsableByteArray.readUnsignedByte() == 1);
    }

    private static Double readAmfDouble(ParsableByteArray parsableByteArray) {
        return Double.valueOf(Double.longBitsToDouble(parsableByteArray.readLong()));
    }

    private static String readAmfString(ParsableByteArray parsableByteArray) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(unsignedShort);
        return new String(parsableByteArray.data, position, unsignedShort);
    }

    private static ArrayList<Object> readAmfStrictArray(ParsableByteArray parsableByteArray) {
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        ArrayList<Object> arrayList = new ArrayList<>(unsignedIntToInt);
        for (int i = 0; i < unsignedIntToInt; i++) {
            arrayList.add(readAmfData(parsableByteArray, readAmfType(parsableByteArray)));
        }
        return arrayList;
    }

    private static HashMap<String, Object> readAmfObject(ParsableByteArray parsableByteArray) {
        HashMap<String, Object> map = new HashMap<>();
        while (true) {
            String amfString = readAmfString(parsableByteArray);
            int amfType = readAmfType(parsableByteArray);
            if (amfType == 9) {
                return map;
            }
            map.put(amfString, readAmfData(parsableByteArray, amfType));
        }
    }

    private static HashMap<String, Object> readAmfEcmaArray(ParsableByteArray parsableByteArray) {
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        HashMap<String, Object> map = new HashMap<>(unsignedIntToInt);
        for (int i = 0; i < unsignedIntToInt; i++) {
            map.put(readAmfString(parsableByteArray), readAmfData(parsableByteArray, readAmfType(parsableByteArray)));
        }
        return map;
    }

    private static Date readAmfDate(ParsableByteArray parsableByteArray) {
        Date date = new Date((long) readAmfDouble(parsableByteArray).doubleValue());
        parsableByteArray.skipBytes(2);
        return date;
    }

    private static Object readAmfData(ParsableByteArray parsableByteArray, int i) {
        if (i == 0) {
            return readAmfDouble(parsableByteArray);
        }
        if (i == 1) {
            return readAmfBoolean(parsableByteArray);
        }
        if (i == 2) {
            return readAmfString(parsableByteArray);
        }
        if (i == 3) {
            return readAmfObject(parsableByteArray);
        }
        if (i == 8) {
            return readAmfEcmaArray(parsableByteArray);
        }
        if (i == 10) {
            return readAmfStrictArray(parsableByteArray);
        }
        if (i != 11) {
            return null;
        }
        return readAmfDate(parsableByteArray);
    }
}
