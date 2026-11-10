package com.google.common.net;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Beta
@GwtCompatible(emulated = true)
public final class InternetDomainName {
    public static final int MAX_DOMAIN_PART_LENGTH = 63;
    public static final int MAX_LENGTH = 253;
    public static final int MAX_PARTS = 127;
    public static final int NO_SUFFIX_FOUND = -1;
    public final String name;
    public final ImmutableList<String> parts;
    public final int publicSuffixIndex;
    public final int registrySuffixIndex;
    public static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".。．｡");
    public static final Splitter DOT_SPLITTER = Splitter.on(InetAddresses.IPV4_DELIMITER);
    public static final Joiner DOT_JOINER = Joiner.on(InetAddresses.IPV4_DELIMITER);
    public static final CharMatcher DASH_MATCHER = CharMatcher.anyOf("-_");
    public static final CharMatcher DIGIT_MATCHER = new CharMatcher.InRange('0', '9');
    public static final CharMatcher LETTER_MATCHER = new CharMatcher.InRange('a', 'z').or(new CharMatcher.InRange('A', 'Z'));
    public static final CharMatcher PART_CHAR_MATCHER = DIGIT_MATCHER.or(LETTER_MATCHER).or(DASH_MATCHER);

    public InternetDomainName(String str) {
        String lowerCase = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom(str, InetAddresses.IPV4_DELIMITER));
        lowerCase = lowerCase.endsWith(".") ? lowerCase.substring(0, lowerCase.length() - 1) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= 253, "Domain name too long: '%s':", lowerCase);
        this.name = lowerCase;
        this.parts = ImmutableList.copyOf(DOT_SPLITTER.split(lowerCase));
        Preconditions.checkArgument(this.parts.size() <= 127, "Domain has too many parts: '%s'", lowerCase);
        Preconditions.checkArgument(validateSyntax(this.parts), "Not a valid domain name: '%s'", lowerCase);
        this.publicSuffixIndex = findSuffixOfType(Absent.INSTANCE);
        this.registrySuffixIndex = findSuffixOfType(Optional.of(PublicSuffixType.REGISTRY));
    }

    private InternetDomainName ancestor(int i) {
        Joiner joiner = DOT_JOINER;
        ImmutableList<String> immutableList = this.parts;
        return from(joiner.join(immutableList.subList(i, immutableList.size())));
    }

    private int findSuffixOfType(Optional<PublicSuffixType> optional) {
        int size = this.parts.size();
        for (int i = 0; i < size; i++) {
            String strJoin = DOT_JOINER.join(this.parts.subList(i, size));
            if (matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(strJoin)))) {
                return i;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(strJoin)) {
                return i + 1;
            }
            if (matchesWildcardSuffixType(optional, strJoin)) {
                return i;
            }
        }
        return -1;
    }

    public static InternetDomainName from(String str) {
        if (str != null) {
            return new InternetDomainName(str);
        }
        throw new NullPointerException();
    }

    public static boolean isValid(String str) {
        try {
            from(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean matchesType(Optional<PublicSuffixType> optional, Optional<PublicSuffixType> optional2) {
        return optional.isPresent() ? optional.equals(optional2) : optional2.isPresent();
    }

    public static boolean matchesWildcardSuffixType(Optional<PublicSuffixType> optional, String str) {
        List<String> listSplitToList = DOT_SPLITTER.limit(2).splitToList(str);
        return listSplitToList.size() == 2 && matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(listSplitToList.get(1))));
    }

    public static boolean validatePart(String str, boolean z) {
        if (str.length() >= 1 && str.length() <= 63) {
            if (PART_CHAR_MATCHER.matchesAllOf(CharMatcher.Ascii.INSTANCE.retainFrom(str)) && !DASH_MATCHER.matches(str.charAt(0)) && !DASH_MATCHER.matches(str.charAt(str.length() - 1))) {
                return (z && DIGIT_MATCHER.matches(str.charAt(0))) ? false : true;
            }
        }
        return false;
    }

    public static boolean validateSyntax(List<String> list) {
        int size = list.size() - 1;
        if (!validatePart(list.get(size), true)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!validatePart(list.get(i), false)) {
                return false;
            }
        }
        return true;
    }

    public InternetDomainName child(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        String str2 = this.name;
        StringBuilder sb = new StringBuilder(a.a((Object) str2, str.length() + 1));
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        return from(sb.toString());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) obj).name);
        }
        return false;
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public boolean hasPublicSuffix() {
        return this.publicSuffixIndex != -1;
    }

    public boolean hasRegistrySuffix() {
        return this.registrySuffixIndex != -1;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isPublicSuffix() {
        return this.publicSuffixIndex == 0;
    }

    public boolean isRegistrySuffix() {
        return this.registrySuffixIndex == 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return this.registrySuffixIndex == 1;
    }

    public boolean isTopPrivateDomain() {
        return this.publicSuffixIndex == 1;
    }

    public boolean isUnderPublicSuffix() {
        return this.publicSuffixIndex > 0;
    }

    public boolean isUnderRegistrySuffix() {
        return this.registrySuffixIndex > 0;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", this.name);
        return ancestor(1);
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(this.publicSuffixIndex);
        }
        return null;
    }

    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return ancestor(this.registrySuffixIndex);
        }
        return null;
    }

    public String toString() {
        return this.name;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", this.name);
        return ancestor(this.registrySuffixIndex - 1);
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", this.name);
        return ancestor(this.publicSuffixIndex - 1);
    }
}
