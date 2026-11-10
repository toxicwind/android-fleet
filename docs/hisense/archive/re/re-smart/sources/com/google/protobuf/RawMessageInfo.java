package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class RawMessageInfo implements MessageInfo {
    public final MessageLite defaultInstance;
    public final int flags;
    public final String info;
    public final Object[] objects;

    public RawMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        char cCharAt;
        this.defaultInstance = messageLite;
        this.info = str;
        this.objects = objArr;
        int i = 1;
        try {
            cCharAt = str.charAt(0);
        } catch (StringIndexOutOfBoundsException unused) {
            char[] charArray = str.toCharArray();
            String str2 = new String(charArray);
            try {
                try {
                    cCharAt = str2.charAt(0);
                    str = str2;
                } catch (StringIndexOutOfBoundsException unused2) {
                    char[] cArr = new char[str2.length()];
                    str2.getChars(0, str2.length(), cArr, 0);
                    String str3 = new String(cArr);
                    try {
                        cCharAt = str3.charAt(0);
                        str = str3;
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e2) {
                        e = e2;
                        str2 = str3;
                        throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e3) {
                e = e3;
                throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
            } catch (StringIndexOutOfBoundsException e4) {
                e = e4;
                throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
            }
        }
        if (cCharAt < 55296) {
            this.flags = cCharAt;
            return;
        }
        int i2 = cCharAt & 8191;
        int i3 = 13;
        while (true) {
            int i4 = i + 1;
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 55296) {
                this.flags = (cCharAt2 << i3) | i2;
                return;
            } else {
                i2 |= (cCharAt2 & 8191) << i3;
                i3 += 13;
                i = i4;
            }
        }
    }

    @Override // com.google.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public Object[] getObjects() {
        return this.objects;
    }

    public String getStringInfo() {
        return this.info;
    }

    @Override // com.google.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    @Override // com.google.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
