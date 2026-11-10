package com.google.android.exoplayer.metadata.id3;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.metadata.MetadataParser;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class Id3Parser implements MetadataParser<List<Id3Frame>> {
    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i) {
        if (i == 1) {
            return "UTF-16";
        }
        if (i != 2) {
            return i != 3 ? "ISO-8859-1" : "UTF-8";
        }
        return "UTF-16BE";
    }

    @Override // com.google.android.exoplayer.metadata.MetadataParser
    public boolean canParse(String str) {
        Log.d("Id3Parser", "id3 canParse mimeType = " + str.equals("application/id3"));
        return str.equals("application/id3");
    }

    @Override // com.google.android.exoplayer.metadata.MetadataParser
    public List<Id3Frame> parse(byte[] bArr, int i) throws ParserException {
        Id3Frame binaryFrame;
        Log.d("Id3Parser", "id3 parse");
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        int id3Header = parseId3Header(parsableByteArray);
        while (id3Header > 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int unsignedByte3 = parsableByteArray.readUnsignedByte();
            int unsignedByte4 = parsableByteArray.readUnsignedByte();
            int synchSafeInt = parsableByteArray.readSynchSafeInt();
            if (synchSafeInt <= 1) {
                break;
            }
            parsableByteArray.skipBytes(2);
            if (unsignedByte == 84 && unsignedByte2 == 88 && unsignedByte3 == 88 && unsignedByte4 == 88) {
                try {
                    binaryFrame = parseTxxxFrame(parsableByteArray, synchSafeInt);
                } catch (UnsupportedEncodingException e) {
                    throw new ParserException(e);
                }
            } else if (unsignedByte == 80 && unsignedByte2 == 82 && unsignedByte3 == 73 && unsignedByte4 == 86) {
                binaryFrame = parsePrivFrame(parsableByteArray, synchSafeInt);
            } else if (unsignedByte == 71 && unsignedByte2 == 69 && unsignedByte3 == 79 && unsignedByte4 == 66) {
                binaryFrame = parseGeobFrame(parsableByteArray, synchSafeInt);
            } else if (unsignedByte == 65 && unsignedByte2 == 80 && unsignedByte3 == 73 && unsignedByte4 == 67) {
                binaryFrame = parseApicFrame(parsableByteArray, synchSafeInt);
            } else if (unsignedByte == 84) {
                binaryFrame = parseTextInformationFrame(parsableByteArray, synchSafeInt, String.format(Locale.US, "%c%c%c%c", Integer.valueOf(unsignedByte), Integer.valueOf(unsignedByte2), Integer.valueOf(unsignedByte3), Integer.valueOf(unsignedByte4)));
            } else {
                binaryFrame = parseBinaryFrame(parsableByteArray, synchSafeInt, String.format(Locale.US, "%c%c%c%c", Integer.valueOf(unsignedByte), Integer.valueOf(unsignedByte2), Integer.valueOf(unsignedByte3), Integer.valueOf(unsignedByte4)));
            }
            arrayList.add(binaryFrame);
            id3Header -= synchSafeInt + 10;
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static int indexOfEos(byte[] bArr, int i, int i2) {
        int iIndexOfZeroByte = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return iIndexOfZeroByte;
        }
        while (iIndexOfZeroByte < bArr.length - 1) {
            if (iIndexOfZeroByte % 2 == 0 && bArr[iIndexOfZeroByte + 1] == 0) {
                return iIndexOfZeroByte;
            }
            iIndexOfZeroByte = indexOfZeroByte(bArr, iIndexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static int parseId3Header(ParsableByteArray parsableByteArray) throws ParserException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int unsignedByte3 = parsableByteArray.readUnsignedByte();
        if (unsignedByte != 73 || unsignedByte2 != 68 || unsignedByte3 != 51) {
            throw new ParserException(String.format(Locale.US, "Unexpected ID3 file identifier, expected \"ID3\", actual \"%c%c%c\".", Integer.valueOf(unsignedByte), Integer.valueOf(unsignedByte2), Integer.valueOf(unsignedByte3)));
        }
        parsableByteArray.skipBytes(2);
        int unsignedByte4 = parsableByteArray.readUnsignedByte();
        int synchSafeInt = parsableByteArray.readSynchSafeInt();
        if ((unsignedByte4 & 2) != 0) {
            int synchSafeInt2 = parsableByteArray.readSynchSafeInt();
            if (synchSafeInt2 > 4) {
                parsableByteArray.skipBytes(synchSafeInt2 - 4);
            }
            synchSafeInt -= synchSafeInt2;
        }
        return (unsignedByte4 & 8) != 0 ? synchSafeInt - 10 : synchSafeInt;
    }

    private static TxxxFrame parseTxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfEos = indexOfEos(bArr, 0, unsignedByte);
        String str = new String(bArr, 0, iIndexOfEos, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        return new TxxxFrame(str, new String(bArr, iDelimiterLength, indexOfEos(bArr, iDelimiterLength, unsignedByte) - iDelimiterLength, charsetName));
    }

    private static PrivFrame parsePrivFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, iIndexOfZeroByte, "ISO-8859-1"), Arrays.copyOfRange(bArr, iIndexOfZeroByte + 1, i));
    }

    private static GeobFrame parseGeobFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, iIndexOfZeroByte, "ISO-8859-1");
        int i3 = iIndexOfZeroByte + 1;
        int iIndexOfEos = indexOfEos(bArr, i3, unsignedByte);
        String str2 = new String(bArr, i3, iIndexOfEos - i3, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        int iIndexOfEos2 = indexOfEos(bArr, iDelimiterLength, unsignedByte);
        return new GeobFrame(str, str2, new String(bArr, iDelimiterLength, iIndexOfEos2 - iDelimiterLength, charsetName), Arrays.copyOfRange(bArr, iIndexOfEos2 + delimiterLength(unsignedByte), i2));
    }

    private static ApicFrame parseApicFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, iIndexOfZeroByte, "ISO-8859-1");
        int i3 = bArr[iIndexOfZeroByte + 1] & 255;
        int i4 = iIndexOfZeroByte + 2;
        int iIndexOfEos = indexOfEos(bArr, i4, unsignedByte);
        return new ApicFrame(str, new String(bArr, i4, iIndexOfEos - i4, charsetName), i3, Arrays.copyOfRange(bArr, iIndexOfEos + delimiterLength(unsignedByte), i2));
    }

    private static TextInformationFrame parseTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new TextInformationFrame(str, new String(bArr, 0, indexOfEos(bArr, 0, unsignedByte), charsetName));
    }

    private static BinaryFrame parseBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }
}
