package com.fasterxml.jackson.core.util;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public class BufferRecycler {
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = {4000, 4000, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR};
    protected final AtomicReferenceArray<byte[]> _byteBuffers;
    protected final AtomicReferenceArray<char[]> _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int i, int i2) {
        this._byteBuffers = new AtomicReferenceArray<>(i);
        this._charBuffers = new AtomicReferenceArray<>(i2);
    }

    public final byte[] allocByteBuffer(int i) {
        return allocByteBuffer(i, 0);
    }

    public byte[] allocByteBuffer(int i, int i2) {
        int iByteBufferLength = byteBufferLength(i);
        if (i2 < iByteBufferLength) {
            i2 = iByteBufferLength;
        }
        byte[] andSet = this._byteBuffers.getAndSet(i, null);
        return (andSet == null || andSet.length < i2) ? balloc(i2) : andSet;
    }

    public void releaseByteBuffer(int i, byte[] bArr) {
        this._byteBuffers.set(i, bArr);
    }

    public final char[] allocCharBuffer(int i) {
        return allocCharBuffer(i, 0);
    }

    public char[] allocCharBuffer(int i, int i2) {
        int iCharBufferLength = charBufferLength(i);
        if (i2 < iCharBufferLength) {
            i2 = iCharBufferLength;
        }
        char[] andSet = this._charBuffers.getAndSet(i, null);
        return (andSet == null || andSet.length < i2) ? calloc(i2) : andSet;
    }

    public void releaseCharBuffer(int i, char[] cArr) {
        this._charBuffers.set(i, cArr);
    }

    protected int byteBufferLength(int i) {
        return BYTE_BUFFER_LENGTHS[i];
    }

    protected int charBufferLength(int i) {
        return CHAR_BUFFER_LENGTHS[i];
    }

    protected byte[] balloc(int i) {
        return new byte[i];
    }

    protected char[] calloc(int i) {
        return new char[i];
    }
}
