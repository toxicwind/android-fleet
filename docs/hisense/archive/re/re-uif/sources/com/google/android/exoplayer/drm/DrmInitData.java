package com.google.android.exoplayer.drm;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public interface DrmInitData {
    SchemeInitData get(UUID uuid);

    public static final class Mapped implements DrmInitData {
        private final Map<UUID, SchemeInitData> schemeData = new HashMap();

        @Override // com.google.android.exoplayer.drm.DrmInitData
        public SchemeInitData get(UUID uuid) {
            return this.schemeData.get(uuid);
        }

        public void put(UUID uuid, SchemeInitData schemeInitData) {
            this.schemeData.put(uuid, schemeInitData);
        }

        public boolean equals(Object obj) {
            if (obj == null || Mapped.class != obj.getClass()) {
                return false;
            }
            Mapped mapped = (Mapped) obj;
            if (this.schemeData.size() != mapped.schemeData.size()) {
                return false;
            }
            for (UUID uuid : this.schemeData.keySet()) {
                if (!Util.areEqual(this.schemeData.get(uuid), mapped.schemeData.get(uuid))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return this.schemeData.hashCode();
        }
    }

    public static final class Universal implements DrmInitData {
        private SchemeInitData data;

        public Universal(SchemeInitData schemeInitData) {
            this.data = schemeInitData;
        }

        @Override // com.google.android.exoplayer.drm.DrmInitData
        public SchemeInitData get(UUID uuid) {
            return this.data;
        }

        public boolean equals(Object obj) {
            if (obj == null || Universal.class != obj.getClass()) {
                return false;
            }
            return Util.areEqual(this.data, ((Universal) obj).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }
    }

    public static final class SchemeInitData {
        public final byte[] data;
        public final String mimeType;

        public SchemeInitData(String str, byte[] bArr) {
            Assertions.checkNotNull(str);
            this.mimeType = str;
            Assertions.checkNotNull(bArr);
            this.data = bArr;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SchemeInitData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeInitData schemeInitData = (SchemeInitData) obj;
            return this.mimeType.equals(schemeInitData.mimeType) && Arrays.equals(this.data, schemeInitData.data);
        }

        public int hashCode() {
            return this.mimeType.hashCode() + (Arrays.hashCode(this.data) * 31);
        }
    }
}
