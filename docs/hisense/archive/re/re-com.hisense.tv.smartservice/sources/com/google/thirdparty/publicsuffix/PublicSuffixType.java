package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.net.InetAddresses;

/* JADX INFO: loaded from: classes2.dex */
@Beta
@GwtCompatible
public enum PublicSuffixType {
    PRIVATE(InetAddresses.IPV6_DELIMITER, ','),
    REGISTRY('!', '?');

    public final char innerNodeCode;
    public final char leafNodeCode;

    PublicSuffixType(char c2, char c3) {
        this.innerNodeCode = c2;
        this.leafNodeCode = c3;
    }

    public static PublicSuffixType fromCode(char c2) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c2 || publicSuffixType.getLeafNodeCode() == c2) {
                return publicSuffixType;
            }
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("No enum corresponding to given code: ");
        sb.append(c2);
        throw new IllegalArgumentException(sb.toString());
    }

    public char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    public char getLeafNodeCode() {
        return this.leafNodeCode;
    }
}
