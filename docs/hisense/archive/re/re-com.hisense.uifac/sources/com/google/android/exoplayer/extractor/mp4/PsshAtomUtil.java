package com.google.android.exoplayer.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class PsshAtomUtil {
    public static byte[] buildPsshAtom(UUID uuid, byte[] bArr) {
        int length = bArr.length + 32;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length);
        byteBufferAllocate.putInt(Atom.TYPE_pssh);
        byteBufferAllocate.putInt(0);
        byteBufferAllocate.putLong(uuid.getMostSignificantBits());
        byteBufferAllocate.putLong(uuid.getLeastSignificantBits());
        byteBufferAllocate.putInt(bArr.length);
        byteBufferAllocate.put(bArr);
        return byteBufferAllocate.array();
    }

    public static UUID parseUuid(byte[] bArr) {
        Pair<UUID, byte[]> psshAtom = parsePsshAtom(bArr);
        if (psshAtom == null) {
            return null;
        }
        return (UUID) psshAtom.first;
    }

    public static byte[] parseSchemeSpecificData(byte[] bArr, UUID uuid) {
        Pair<UUID, byte[]> psshAtom = parsePsshAtom(bArr);
        if (psshAtom == null) {
            return null;
        }
        if (uuid != null && !uuid.equals(psshAtom.first)) {
            Log.w("PsshAtomUtil", "UUID mismatch. Expected: " + uuid + ", got: " + psshAtom.first + ".");
            return null;
        }
        return (byte[]) psshAtom.second;
    }

    private static Pair<UUID, byte[]> parsePsshAtom(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (parsableByteArray.limit() < 32) {
            return null;
        }
        parsableByteArray.setPosition(0);
        if (parsableByteArray.readInt() != parsableByteArray.bytesLeft() + 4 || parsableByteArray.readInt() != Atom.TYPE_pssh) {
            return null;
        }
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        if (fullAtomVersion > 1) {
            Log.w("PsshAtomUtil", "Unsupported pssh version: " + fullAtomVersion);
            return null;
        }
        UUID uuid = new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
        if (fullAtomVersion == 1) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedIntToInt() * 16);
        }
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != parsableByteArray.bytesLeft()) {
            return null;
        }
        byte[] bArr2 = new byte[unsignedIntToInt];
        parsableByteArray.readBytes(bArr2, 0, unsignedIntToInt);
        return Pair.create(uuid, bArr2);
    }
}
