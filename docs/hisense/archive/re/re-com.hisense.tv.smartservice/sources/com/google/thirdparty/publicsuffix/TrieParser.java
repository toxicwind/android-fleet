package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@GwtCompatible
public final class TrieParser {
    public static final Joiner PREFIX_JOINER = new Joiner("");

    public static int doParseTrieToBuilder(List<CharSequence> list, CharSequence charSequence, int i, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int length = charSequence.length();
        int i2 = i;
        char cCharAt = 0;
        while (i2 < length && (cCharAt = charSequence.charAt(i2)) != '&' && cCharAt != '?' && cCharAt != '!' && cCharAt != ':' && cCharAt != ',') {
            i2++;
        }
        list.add(0, reverse(charSequence.subSequence(i, i2)));
        if (cCharAt == '!' || cCharAt == '?' || cCharAt == ':' || cCharAt == ',') {
            String strJoin = PREFIX_JOINER.join(list);
            if (strJoin.length() > 0) {
                builder.put(strJoin, PublicSuffixType.fromCode(cCharAt));
            }
        }
        int iDoParseTrieToBuilder = i2 + 1;
        if (cCharAt != '?' && cCharAt != ',') {
            while (iDoParseTrieToBuilder < length) {
                iDoParseTrieToBuilder += doParseTrieToBuilder(list, charSequence, iDoParseTrieToBuilder, builder);
                if (charSequence.charAt(iDoParseTrieToBuilder) == '?' || charSequence.charAt(iDoParseTrieToBuilder) == ',') {
                    iDoParseTrieToBuilder++;
                    break;
                }
            }
        }
        list.remove(0);
        return iDoParseTrieToBuilder - i;
    }

    public static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int iDoParseTrieToBuilder = 0;
        while (iDoParseTrieToBuilder < length) {
            iDoParseTrieToBuilder += doParseTrieToBuilder(new LinkedList(), charSequence, iDoParseTrieToBuilder, builder);
        }
        return builder.build();
    }

    public static CharSequence reverse(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse();
    }
}
