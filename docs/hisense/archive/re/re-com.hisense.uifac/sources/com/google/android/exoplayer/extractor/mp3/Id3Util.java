package com.google.android.exoplayer.extractor.mp3;

import android.util.Pair;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
final class Id3Util {
    private static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final Charset[] CHARSET_BY_ENCODING = {Charset.forName("ISO-8859-1"), Charset.forName("UTF-16LE"), Charset.forName("UTF-16BE"), Charset.forName("UTF-8")};

    private static boolean canParseMetadata(int i, int i2, int i3, int i4) {
        return i2 != 255 && i >= 2 && i <= 4 && i4 <= 3145728 && (i != 2 || ((i3 & 63) == 0 && (i3 & 64) == 0)) && ((i != 3 || (i3 & 31) == 0) && (i != 4 || (i3 & 15) == 0));
    }

    public static GaplessInfo parseId3(ExtractorInput extractorInput) throws InterruptedException, IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        GaplessInfo gaplessInfo = null;
        int i = 0;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 10);
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readUnsignedInt24() == ID3_TAG) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                int synchSafeInt = parsableByteArray.readSynchSafeInt();
                if (gaplessInfo == null && canParseMetadata(unsignedByte, unsignedByte2, unsignedByte3, synchSafeInt)) {
                    byte[] bArr = new byte[synchSafeInt];
                    extractorInput.peekFully(bArr, 0, synchSafeInt);
                    gaplessInfo = parseGaplessInfo(new ParsableByteArray(bArr), unsignedByte, unsignedByte3);
                } else {
                    extractorInput.advancePeekPosition(synchSafeInt);
                }
                i += synchSafeInt + 10;
            } else {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i);
                return gaplessInfo;
            }
        }
    }

    private static GaplessInfo parseGaplessInfo(ParsableByteArray parsableByteArray, int i, int i2) {
        int synchSafeInt;
        GaplessInfo gaplessInfoCreateFromComment;
        int unsignedIntToInt;
        unescape(parsableByteArray, i, i2);
        parsableByteArray.setPosition(0);
        if (i != 3 || (i2 & 64) == 0) {
            if (i == 4 && (i2 & 64) != 0) {
                if (parsableByteArray.bytesLeft() < 4 || (synchSafeInt = parsableByteArray.readSynchSafeInt()) < 6 || synchSafeInt > parsableByteArray.bytesLeft() + 4) {
                    return null;
                }
                parsableByteArray.setPosition(synchSafeInt);
            }
        } else {
            if (parsableByteArray.bytesLeft() < 4 || (unsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) > parsableByteArray.bytesLeft()) {
                return null;
            }
            if (unsignedIntToInt >= 6) {
                parsableByteArray.skipBytes(2);
                int unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
                parsableByteArray.setPosition(4);
                parsableByteArray.setLimit(parsableByteArray.limit() - unsignedIntToInt2);
                if (parsableByteArray.bytesLeft() < unsignedIntToInt) {
                    return null;
                }
            }
            parsableByteArray.skipBytes(unsignedIntToInt);
        }
        while (true) {
            Pair<String, String> pairFindNextComment = findNextComment(i, parsableByteArray);
            if (pairFindNextComment == null) {
                return null;
            }
            if (((String) pairFindNextComment.first).length() > 3 && (gaplessInfoCreateFromComment = GaplessInfo.createFromComment(((String) pairFindNextComment.first).substring(3), (String) pairFindNextComment.second)) != null) {
                return gaplessInfoCreateFromComment;
            }
        }
    }

    private static Pair<String, String> findNextComment(int i, ParsableByteArray parsableByteArray) {
        int unsignedInt24;
        while (true) {
            if (i == 2) {
                if (parsableByteArray.bytesLeft() < 6) {
                    return null;
                }
                String string = parsableByteArray.readString(3, Charset.forName("US-ASCII"));
                if (string.equals("\u0000\u0000\u0000") || (unsignedInt24 = parsableByteArray.readUnsignedInt24()) == 0 || unsignedInt24 > parsableByteArray.bytesLeft()) {
                    return null;
                }
                if (string.equals(MtkTvConfigTypeBase.S3166_CFG_COUNT_COM)) {
                    break;
                }
                parsableByteArray.skipBytes(unsignedInt24);
            } else if (parsableByteArray.bytesLeft() >= 10) {
                String string2 = parsableByteArray.readString(4, Charset.forName("US-ASCII"));
                if (!string2.equals("\u0000\u0000\u0000\u0000")) {
                    int synchSafeInt = i == 4 ? parsableByteArray.readSynchSafeInt() : parsableByteArray.readUnsignedIntToInt();
                    if (synchSafeInt != 0 && synchSafeInt <= parsableByteArray.bytesLeft() - 2) {
                        int unsignedShort = parsableByteArray.readUnsignedShort();
                        if (!((i == 4 && (unsignedShort & 12) != 0) || (i == 3 && (unsignedShort & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED) != 0)) && string2.equals("COMM")) {
                            unsignedInt24 = synchSafeInt;
                            break;
                        }
                        unsignedInt24 = synchSafeInt;
                        parsableByteArray.skipBytes(unsignedInt24);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if (unsignedByte < 0) {
            return null;
        }
        Charset[] charsetArr = CHARSET_BY_ENCODING;
        if (unsignedByte >= charsetArr.length) {
            return null;
        }
        String[] strArrSplit = parsableByteArray.readString(unsignedInt24 - 1, charsetArr[unsignedByte]).split("\u0000");
        if (strArrSplit.length == 2) {
            return Pair.create(strArrSplit[0], strArrSplit[1]);
        }
        return null;
    }

    private static boolean unescape(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = 0;
        if (i != 4) {
            if ((i2 & 128) != 0) {
                byte[] bArr = parsableByteArray.data;
                int length = bArr.length;
                while (true) {
                    int i4 = i3 + 1;
                    if (i4 >= length) {
                        break;
                    }
                    if ((bArr[i3] & 255) == 255 && bArr[i4] == 0) {
                        System.arraycopy(bArr, i3 + 2, bArr, i4, (length - i3) - 2);
                        length--;
                    }
                    i3 = i4;
                }
                parsableByteArray.setLimit(length);
            }
        } else if (canUnescapeVersion4(parsableByteArray, false)) {
            unescapeVersion4(parsableByteArray, false);
        } else {
            if (!canUnescapeVersion4(parsableByteArray, true)) {
                return false;
            }
            unescapeVersion4(parsableByteArray, true);
        }
        return true;
    }

    private static boolean canUnescapeVersion4(ParsableByteArray parsableByteArray, boolean z) {
        parsableByteArray.setPosition(0);
        while (parsableByteArray.bytesLeft() >= 10 && parsableByteArray.readInt() != 0) {
            long unsignedInt = parsableByteArray.readUnsignedInt();
            if (!z) {
                if ((8421504 & unsignedInt) != 0) {
                    return false;
                }
                unsignedInt = (((unsignedInt >> 24) & 127) << 21) | (unsignedInt & 127) | (((unsignedInt >> 8) & 127) << 7) | (((unsignedInt >> 16) & 127) << 14);
            }
            if (unsignedInt > parsableByteArray.bytesLeft() - 2) {
                return false;
            }
            if ((1 & parsableByteArray.readUnsignedShort()) != 0 && parsableByteArray.bytesLeft() < 4) {
                return false;
            }
            parsableByteArray.skipBytes((int) unsignedInt);
        }
        return true;
    }

    private static void unescapeVersion4(ParsableByteArray parsableByteArray, boolean z) {
        int i;
        parsableByteArray.setPosition(0);
        byte[] bArr = parsableByteArray.data;
        while (parsableByteArray.bytesLeft() >= 10 && parsableByteArray.readInt() != 0) {
            int unsignedIntToInt = z ? parsableByteArray.readUnsignedIntToInt() : parsableByteArray.readSynchSafeInt();
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if ((unsignedShort & 1) != 0) {
                int position = parsableByteArray.getPosition();
                System.arraycopy(bArr, position + 4, bArr, position, parsableByteArray.bytesLeft() - 4);
                unsignedIntToInt -= 4;
                i = unsignedShort & (-2);
                parsableByteArray.setLimit(parsableByteArray.limit() - 4);
            } else {
                i = unsignedShort;
            }
            if ((i & 2) != 0) {
                int position2 = parsableByteArray.getPosition() + 1;
                int i2 = 0;
                int i3 = position2;
                while (true) {
                    i2++;
                    if (i2 >= unsignedIntToInt) {
                        break;
                    }
                    if ((bArr[position2 - 1] & 255) == 255 && bArr[position2] == 0) {
                        position2++;
                        unsignedIntToInt--;
                    }
                    bArr[i3] = bArr[position2];
                    i3++;
                    position2++;
                }
                parsableByteArray.setLimit(parsableByteArray.limit() - (position2 - i3));
                System.arraycopy(bArr, position2, bArr, i3, parsableByteArray.bytesLeft() - position2);
                i &= -3;
            }
            if (i != unsignedShort || z) {
                int position3 = parsableByteArray.getPosition() - 6;
                writeSyncSafeInteger(bArr, position3, unsignedIntToInt);
                bArr[position3 + 4] = (byte) (i >> 8);
                bArr[position3 + 5] = (byte) (i & 255);
            }
            parsableByteArray.skipBytes(unsignedIntToInt);
        }
    }

    private static void writeSyncSafeInteger(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 21) & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
        bArr[i + 1] = (byte) ((i2 >> 14) & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
        bArr[i + 2] = (byte) ((i2 >> 7) & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
        bArr[i + 3] = (byte) (i2 & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
    }
}
