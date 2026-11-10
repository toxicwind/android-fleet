package com.google.android.exoplayer.text.pgs;

import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PgsFactory {
    private BitmapBuffer bitmap;
    private FileBuffer buffer;
    private Palette palette;
    private List<SubPictureBD> subPictures;

    public PgsFactory(InputStream inputStream, boolean z) throws Exception {
        this.subPictures = new ArrayList();
        PgsParserImpl pgsParserImpl = new PgsParserImpl(inputStream, z);
        this.buffer = pgsParserImpl.getBuffer();
        this.subPictures = pgsParserImpl.getSubPictures();
        pgsParserImpl.getForcedFrameCount();
    }

    public PgsFactory(byte[] bArr, boolean z) throws Exception {
        this.subPictures = new ArrayList();
        PgsParserImpl pgsParserImpl = new PgsParserImpl(bArr, z);
        this.buffer = pgsParserImpl.getBuffer();
        this.subPictures = pgsParserImpl.getSubPictures();
        pgsParserImpl.getForcedFrameCount();
    }

    private BitmapBuffer decodeImage(SubPictureBD subPictureBD, int i) throws Exception {
        long j;
        int i2;
        int imageWidth = subPictureBD.getImageWidth();
        int imageHeight = subPictureBD.getImageHeight();
        int i3 = 0;
        long imageBufferOfs = subPictureBD.getImageObject().getFragmentList().get(0).getImageBufferOfs();
        if (imageWidth > subPictureBD.getWidth() || imageHeight > subPictureBD.getHeight()) {
            throw new Exception("Subpicture too large: " + imageWidth + "x" + imageHeight + " at offset " + ToolBox.toHexLeftZeroPadded(imageBufferOfs, 8));
        }
        BitmapBuffer bitmapBuffer = new BitmapBuffer(imageWidth, imageHeight, (byte) i);
        try {
            try {
                int bufferSize = subPictureBD.getImageObject().getBufferSize();
                byte[] bArr = new byte[bufferSize];
                int imagePacketSize = 0;
                for (ImageObjectFragment imageObjectFragment : subPictureBD.getImageObject().getFragmentList()) {
                    try {
                        int i4 = i3;
                        while (i4 < imageObjectFragment.getImagePacketSize()) {
                            j = imageBufferOfs;
                            try {
                                bArr[imagePacketSize + i4] = (byte) this.buffer.getByte(((long) i4) + imageObjectFragment.getImageBufferOfs());
                                i4++;
                                imageBufferOfs = j;
                            } catch (ArrayIndexOutOfBoundsException unused) {
                                i3 = imagePacketSize;
                                MtkDebugLogUtil.log("PGSParserLog", "Problems during RLE decoding of picture OBJ at offset " + ToolBox.toHexLeftZeroPadded(j + ((long) i3), 8) + "\n", 16);
                                return bitmapBuffer;
                            }
                        }
                        long j2 = imageBufferOfs;
                        imagePacketSize += imageObjectFragment.getImagePacketSize();
                        imageBufferOfs = j2;
                        i3 = 0;
                    } catch (ArrayIndexOutOfBoundsException unused2) {
                        j = imageBufferOfs;
                    }
                }
                j = imageBufferOfs;
                i3 = 0;
                int i5 = 0;
                int i6 = 0;
                do {
                    int i7 = i3 + 1;
                    try {
                        int i8 = bArr[i3] & 255;
                        if (i8 == 0) {
                            i3 = i7 + 1;
                            try {
                                int i9 = bArr[i7] & 255;
                                if (i9 == 0) {
                                    i5 = (i5 / imageWidth) * imageWidth;
                                    if (i6 < imageWidth) {
                                        i5 += imageWidth;
                                    }
                                    i6 = 0;
                                } else {
                                    int i10 = i9 & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED;
                                    if (i10 == 64) {
                                        imagePacketSize = i3 + 1;
                                        i2 = ((i9 - 64) << 8) + (bArr[i3] & 255);
                                        int i11 = 0;
                                        while (i11 < i2) {
                                            bitmapBuffer.getInternalBuffer()[i5] = 0;
                                            i11++;
                                            i5++;
                                        }
                                    } else if (i10 == 128) {
                                        i2 = i9 - 128;
                                        imagePacketSize = i3 + 1;
                                        int i12 = bArr[i3] & 255;
                                        int i13 = 0;
                                        while (i13 < i2) {
                                            bitmapBuffer.getInternalBuffer()[i5] = (byte) i12;
                                            i13++;
                                            i5++;
                                        }
                                    } else if (i10 != 0) {
                                        int i14 = i3 + 1;
                                        int i15 = ((i9 - 192) << 8) + (bArr[i3] & 255);
                                        i3 = i14 + 1;
                                        int i16 = bArr[i14] & 255;
                                        int i17 = 0;
                                        while (i17 < i15) {
                                            bitmapBuffer.getInternalBuffer()[i5] = (byte) i16;
                                            i17++;
                                            i5++;
                                        }
                                        i6 += i15;
                                    } else {
                                        int i18 = 0;
                                        while (i18 < i9) {
                                            int i19 = i5 + 1;
                                            bitmapBuffer.getInternalBuffer()[i5] = 0;
                                            i18++;
                                            i5 = i19;
                                        }
                                        i6 += i9;
                                    }
                                    i6 += i2;
                                    i3 = imagePacketSize;
                                }
                            } catch (ArrayIndexOutOfBoundsException unused3) {
                                MtkDebugLogUtil.log("PGSParserLog", "Problems during RLE decoding of picture OBJ at offset " + ToolBox.toHexLeftZeroPadded(j + ((long) i3), 8) + "\n", 16);
                                return bitmapBuffer;
                            }
                        } else {
                            int i20 = i5 + 1;
                            bitmapBuffer.getInternalBuffer()[i5] = (byte) i8;
                            i6++;
                            i3 = i7;
                            i5 = i20;
                        }
                    } catch (ArrayIndexOutOfBoundsException unused4) {
                        i3 = i7;
                    }
                } while (i3 < bufferSize);
                return bitmapBuffer;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } catch (ArrayIndexOutOfBoundsException unused5) {
            j = imageBufferOfs;
        }
    }

    private Palette decodePalette(SubPictureBD subPictureBD) throws Exception {
        List<PaletteInfo> list = subPictureBD.getPalettes().get(subPictureBD.getImageObject().getPaletteID());
        if (list == null) {
            throw new Exception("Palette ID out of bounds.");
        }
        Palette palette = new Palette(256, false);
        try {
            boolean z = false;
            for (PaletteInfo paletteInfo : list) {
                int paletteOffset = paletteInfo.getPaletteOffset();
                for (int i = 0; i < paletteInfo.getPaletteSize(); i++) {
                    int i2 = this.buffer.getByte(paletteOffset);
                    int i3 = paletteOffset + 1;
                    int i4 = this.buffer.getByte(i3);
                    int i5 = i3 + 1;
                    int i6 = this.buffer.getByte(i5);
                    int i7 = i5 + 1;
                    int i8 = this.buffer.getByte(i7);
                    int i9 = i7 + 1;
                    int i10 = this.buffer.getByte(i9);
                    if (i10 >= palette.getAlpha(i2)) {
                        if (i10 < 14) {
                            i4 = 16;
                            i6 = 128;
                            i8 = 128;
                        }
                        palette.setAlpha(i2, i10);
                    } else {
                        z = true;
                    }
                    palette.setYCbCr(i2, i4, i8, i6);
                    paletteOffset = i9 + 1;
                }
            }
            if (z) {
                MtkDebugLogUtil.log("PGSParserLog", "fade out detected -> patched palette\n", 16);
            }
            return palette;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void decode(SubPictureBD subPictureBD) throws Exception {
        Palette paletteDecodePalette = decodePalette(subPictureBD);
        this.palette = paletteDecodePalette;
        BitmapBuffer bitmapBufferDecodeImage = decodeImage(subPictureBD, paletteDecodePalette.getIndexOfMostTransparentPaletteEntry());
        this.bitmap = bitmapBufferDecodeImage;
        bitmapBufferDecodeImage.getPrimaryColorIndex(this.palette.getAlpha(), 80, this.palette.getY());
        this.bitmap.setPal(this.palette);
    }

    public void decode(int i) throws Exception {
        if (i < this.subPictures.size()) {
            decode(this.subPictures.get(i));
            return;
        }
        throw new Exception("Index " + i + " out of bounds\n");
    }

    public BitmapBuffer getBitmap() {
        return this.bitmap;
    }

    public List<SubPictureBD> getSubPictureList() {
        return this.subPictures;
    }
}
