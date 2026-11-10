package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class CharMatcher implements Predicate<Character> {
    public static final int DISTINCT_CHARS = 65536;

    private static final class And extends CharMatcher {
        public final CharMatcher first;
        public final CharMatcher second;

        public And(CharMatcher charMatcher, CharMatcher charMatcher2) {
            if (charMatcher == null) {
                throw new NullPointerException();
            }
            this.first = charMatcher;
            if (charMatcher2 == null) {
                throw new NullPointerException();
            }
            this.second = charMatcher2;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return this.first.matches(c2) && this.second.matches(c2);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.first.setBits(bitSet2);
            BitSet bitSet3 = new BitSet();
            this.second.setBits(bitSet3);
            bitSet2.and(bitSet3);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.first);
            String strValueOf2 = String.valueOf(this.second);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 19, "CharMatcher.and(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }
    }

    private static final class Any extends NamedFastMatcher {
        public static final Any INSTANCE = new Any();

        public Any() {
            super("CharMatcher.any()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            if (charMatcher != null) {
                return charMatcher;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c2) {
            return charSequence.length() == 0 ? "" : String.valueOf(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            return charSequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            return charSequence.length() - 1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            if (charSequence != null) {
                return true;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return None.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            if (charMatcher != null) {
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            if (charSequence != null) {
                return "";
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c2) {
            char[] cArr = new char[charSequence.length()];
            Arrays.fill(cArr, c2);
            return new String(cArr);
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            if (charSequence != null) {
                return "";
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i) {
            int length = charSequence.length();
            Preconditions.checkPositionIndex(i, length, FirebaseAnalytics.Param.INDEX);
            if (i == length) {
                return -1;
            }
            return i;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            StringBuilder sb = new StringBuilder(charSequence2.length() * charSequence.length());
            for (int i = 0; i < charSequence.length(); i++) {
                sb.append(charSequence2);
            }
            return sb.toString();
        }
    }

    private static final class AnyOf extends CharMatcher {
        public final char[] chars;

        public AnyOf(CharSequence charSequence) {
            this.chars = charSequence.toString().toCharArray();
            Arrays.sort(this.chars);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Arrays.binarySearch(this.chars, c2) >= 0;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            for (char c2 : this.chars) {
                bitSet.set(c2);
            }
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c2 : this.chars) {
                sb.append(CharMatcher.showCharacter(c2));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final class Ascii extends NamedFastMatcher {
        public static final Ascii INSTANCE = new Ascii();

        public Ascii() {
            super("CharMatcher.ascii()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return c2 <= 127;
        }
    }

    @GwtIncompatible
    private static final class BitSetMatcher extends NamedFastMatcher {
        public final BitSet table;

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return this.table.get(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }

        public BitSetMatcher(BitSet bitSet, String str) {
            super(str);
            this.table = bitSet.length() + 64 < bitSet.size() ? (BitSet) bitSet.clone() : bitSet;
        }
    }

    private static final class BreakingWhitespace extends CharMatcher {
        public static final CharMatcher INSTANCE = new BreakingWhitespace();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            if (c2 != ' ' && c2 != 133 && c2 != 5760) {
                if (c2 == 8199) {
                    return false;
                }
                if (c2 != 8287 && c2 != 12288 && c2 != 8232 && c2 != 8233) {
                    switch (c2) {
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                            break;
                        default:
                            return c2 >= 8192 && c2 <= 8202;
                    }
                }
            }
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    private static final class Digit extends RangesMatcher {
        public static final Digit INSTANCE = new Digit();
        public static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０";

        public Digit() {
            super("CharMatcher.digit()", zeroes(), nines());
        }

        public static char[] nines() {
            char[] cArr = new char[37];
            for (int i = 0; i < 37; i++) {
                cArr[i] = (char) (ZEROES.charAt(i) + '\t');
            }
            return cArr;
        }

        public static char[] zeroes() {
            return ZEROES.toCharArray();
        }
    }

    static abstract class FastMatcher extends CharMatcher {
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    private static final class ForPredicate extends CharMatcher {
        public final Predicate<? super Character> predicate;

        public ForPredicate(Predicate<? super Character> predicate) {
            if (predicate == null) {
                throw new NullPointerException();
            }
            this.predicate = predicate;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return this.predicate.apply(Character.valueOf(c2));
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.predicate);
            return a.a(strValueOf.length() + 26, "CharMatcher.forPredicate(", strValueOf, ")");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        public boolean apply(Character ch) {
            Predicate<? super Character> predicate = this.predicate;
            if (ch != null) {
                return predicate.apply(ch);
            }
            throw new NullPointerException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public static final class InRange extends FastMatcher {
        public final char endInclusive;
        public final char startInclusive;

        public InRange(char c2, char c3) {
            Preconditions.checkArgument(c3 >= c2);
            this.startInclusive = c2;
            this.endInclusive = c3;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return this.startInclusive <= c2 && c2 <= this.endInclusive;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.startInclusive, this.endInclusive + 1);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.startInclusive);
            String strShowCharacter2 = CharMatcher.showCharacter(this.endInclusive);
            StringBuilder sbA = a.a(a.a((Object) strShowCharacter2, a.a((Object) strShowCharacter, 27)), "CharMatcher.inRange('", strShowCharacter, "', '", strShowCharacter2);
            sbA.append("')");
            return sbA.toString();
        }
    }

    private static final class Invisible extends RangesMatcher {
        public static final Invisible INSTANCE = new Invisible();
        public static final String RANGE_ENDS = "  \u00ad\u0605\u061c\u06dd\u070f\u08e2\u1680\u180e\u200f \u2064\u206f\u3000\uf8ff\ufeff\ufffb";
        public static final String RANGE_STARTS = "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u08e2\u1680\u180e\u2000\u2028\u205f\u2066\u3000\ud800\ufeff\ufff9";

        public Invisible() {
            super("CharMatcher.invisible()", RANGE_STARTS.toCharArray(), RANGE_ENDS.toCharArray());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public static final class Is extends FastMatcher {
        public final char match;

        public Is(char c2) {
            this.match = c2;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? this : None.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return c2 == this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new IsNot(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? charMatcher : new Or(this, charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c2) {
            return charSequence.toString().replace(this.match, c2);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match);
            return a.a(a.a((Object) strShowCharacter, 18), "CharMatcher.is('", strShowCharacter, "')");
        }
    }

    private static final class IsEither extends FastMatcher {
        public final char match1;
        public final char match2;

        public IsEither(char c2, char c3) {
            this.match1 = c2;
            this.match2 = c3;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return c2 == this.match1 || c2 == this.match2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match1);
            bitSet.set(this.match2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match1);
            String strShowCharacter2 = CharMatcher.showCharacter(this.match2);
            return a.b(a.a((Object) strShowCharacter2, a.a((Object) strShowCharacter, 21)), "CharMatcher.anyOf(\"", strShowCharacter, strShowCharacter2, "\")");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final class IsNot extends FastMatcher {
        public final char match;

        public IsNot(char c2) {
            this.match = c2;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? new And(this, charMatcher) : charMatcher;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return c2 != this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new Is(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? Any.INSTANCE : this;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(0, this.match);
            bitSet.set(this.match + 1, 65536);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match);
            return a.a(a.a((Object) strShowCharacter, 21), "CharMatcher.isNot('", strShowCharacter, "')");
        }
    }

    private static final class JavaDigit extends CharMatcher {
        public static final JavaDigit INSTANCE = new JavaDigit();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Character.isDigit(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final class JavaIsoControl extends NamedFastMatcher {
        public static final JavaIsoControl INSTANCE = new JavaIsoControl();

        public JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return c2 <= 31 || (c2 >= 127 && c2 <= 159);
        }
    }

    private static final class JavaLetter extends CharMatcher {
        public static final JavaLetter INSTANCE = new JavaLetter();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Character.isLetter(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    private static final class JavaLetterOrDigit extends CharMatcher {
        public static final JavaLetterOrDigit INSTANCE = new JavaLetterOrDigit();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Character.isLetterOrDigit(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    private static final class JavaLowerCase extends CharMatcher {
        public static final JavaLowerCase INSTANCE = new JavaLowerCase();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Character.isLowerCase(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    private static final class JavaUpperCase extends CharMatcher {
        public static final JavaUpperCase INSTANCE = new JavaUpperCase();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return Character.isUpperCase(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    static abstract class NamedFastMatcher extends FastMatcher {
        public final String description;

        public NamedFastMatcher(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.description = str;
        }

        @Override // com.google.common.base.CharMatcher
        public final String toString() {
            return this.description;
        }
    }

    private static class Negated extends CharMatcher {
        public final CharMatcher original;

        public Negated(CharMatcher charMatcher) {
            if (charMatcher == null) {
                throw new NullPointerException();
            }
            this.original = charMatcher;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length() - this.original.countIn(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return !this.original.matches(c2);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return this.original.matchesNoneOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.original.matchesAllOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.original.setBits(bitSet2);
            bitSet2.flip(0, 65536);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.original);
            return a.a(strValueOf.length() + 9, strValueOf, ".negate()");
        }
    }

    static class NegatedFastMatcher extends Negated {
        public NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class None extends NamedFastMatcher {
        public static final None INSTANCE = new None();

        public None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            if (charMatcher != null) {
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c2) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            if (charSequence != null) {
                return 0;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            if (charSequence != null) {
                return -1;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            if (charSequence != null) {
                return -1;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return false;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            if (charSequence != null) {
                return true;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return Any.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            if (charMatcher != null) {
                return charMatcher;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c2) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimLeadingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimTrailingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i) {
            Preconditions.checkPositionIndex(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            if (charSequence2 != null) {
                return charSequence.toString();
            }
            throw new NullPointerException();
        }
    }

    private static final class Or extends CharMatcher {
        public final CharMatcher first;
        public final CharMatcher second;

        public Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
            if (charMatcher == null) {
                throw new NullPointerException();
            }
            this.first = charMatcher;
            if (charMatcher2 == null) {
                throw new NullPointerException();
            }
            this.second = charMatcher2;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return this.first.matches(c2) || this.second.matches(c2);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            this.first.setBits(bitSet);
            this.second.setBits(bitSet);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.first);
            String strValueOf2 = String.valueOf(this.second);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 18, "CharMatcher.or(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }
    }

    private static class RangesMatcher extends CharMatcher {
        public final String description;
        public final char[] rangeEnds;
        public final char[] rangeStarts;

        public RangesMatcher(String str, char[] cArr, char[] cArr2) {
            this.description = str;
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i = 0;
            while (i < cArr.length) {
                Preconditions.checkArgument(cArr[i] <= cArr2[i]);
                int i2 = i + 1;
                if (i2 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i] < cArr[i2]);
                }
                i = i2;
            }
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            int iBinarySearch = Arrays.binarySearch(this.rangeStarts, c2);
            if (iBinarySearch >= 0) {
                return true;
            }
            int i = (~iBinarySearch) - 1;
            return i >= 0 && c2 <= this.rangeEnds[i];
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.description;
        }
    }

    private static final class SingleWidth extends RangesMatcher {
        public static final SingleWidth INSTANCE = new SingleWidth();

        public SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000־א׳\u0600ݐ\u0e00Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ\u0e7f₯℺﷿\ufeffￜ".toCharArray());
        }
    }

    @VisibleForTesting
    static final class Whitespace extends NamedFastMatcher {
        public static final int MULTIPLIER = 1682554634;
        public static final String TABLE = "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000";
        public static final int SHIFT = Integer.numberOfLeadingZeros(31);
        public static final Whitespace INSTANCE = new Whitespace();

        public Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c2) {
            return TABLE.charAt((MULTIPLIER * c2) >>> SHIFT) == c2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            for (int i = 0; i < 32; i++) {
                bitSet.set(TABLE.charAt(i));
            }
        }
    }

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return None.INSTANCE;
        }
        if (length != 1) {
            return length != 2 ? new AnyOf(charSequence) : new IsEither(charSequence.charAt(0), charSequence.charAt(1));
        }
        return new Is(charSequence.charAt(0));
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }

    @Deprecated
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    private String finishCollapseFrom(CharSequence charSequence, int i, int i2, char c2, StringBuilder sb, boolean z) {
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            if (!matches(cCharAt)) {
                sb.append(cCharAt);
                z = false;
            } else if (!z) {
                sb.append(c2);
                z = true;
            }
            i++;
        }
        return sb.toString();
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher) predicate : new ForPredicate(predicate);
    }

    public static CharMatcher inRange(char c2, char c3) {
        return new InRange(c2, c3);
    }

    @Deprecated
    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }

    public static CharMatcher is(char c2) {
        return new Is(c2);
    }

    public static IsEither isEither(char c2, char c3) {
        return new IsEither(c2, c3);
    }

    public static CharMatcher isNot(char c2) {
        return new IsNot(c2);
    }

    @GwtIncompatible
    public static boolean isSmall(int i, int i2) {
        return i <= 1023 && i2 > (i * 4) * 16;
    }

    @Deprecated
    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }

    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    @GwtIncompatible
    public static CharMatcher precomputedPositive(int i, BitSet bitSet, String str) {
        if (i == 0) {
            return None.INSTANCE;
        }
        if (i == 1) {
            return new Is((char) bitSet.nextSetBit(0));
        }
        if (i != 2) {
            return isSmall(i, bitSet.length()) ? SmallCharMatcher.from(bitSet, str) : new BitSetMatcher(bitSet, str);
        }
        char cNextSetBit = (char) bitSet.nextSetBit(0);
        return new IsEither(cNextSetBit, (char) bitSet.nextSetBit(cNextSetBit + 1));
    }

    public static String showCharacter(char c2) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c2 & 15);
            c2 = (char) (c2 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    @Deprecated
    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new And(this, charMatcher);
    }

    public String collapseFrom(CharSequence charSequence, char c2) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (matches(cCharAt)) {
                if (cCharAt != c2 || (i != length - 1 && matches(charSequence.charAt(i + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(charSequence, 0, i);
                    sb.append(c2);
                    return finishCollapseFrom(charSequence, i + 1, length, c2, sb, true);
                }
                i++;
            }
            i++;
        }
        return charSequence.toString();
    }

    public int countIn(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (matches(charSequence.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    public int indexIn(CharSequence charSequence) {
        return indexIn(charSequence, 0);
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public abstract boolean matches(char c2);

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public CharMatcher negate() {
        return new Negated(this);
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new Or(this, charMatcher);
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    @GwtIncompatible
    public CharMatcher precomputedInternal() {
        String strConcat;
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int iCardinality = bitSet.cardinality();
        if (iCardinality * 2 <= 65536) {
            return precomputedPositive(iCardinality, bitSet, toString());
        }
        bitSet.flip(0, 65536);
        int i = 65536 - iCardinality;
        final String string = toString();
        if (string.endsWith(".negate()")) {
            strConcat = string.substring(0, string.length() - 9);
        } else {
            strConcat = ".negate()".length() != 0 ? string.concat(".negate()") : new String(string);
        }
        return new NegatedFastMatcher(this, precomputedPositive(i, bitSet, strConcat)) { // from class: com.google.common.base.CharMatcher.1
            @Override // com.google.common.base.CharMatcher.Negated, com.google.common.base.CharMatcher
            public String toString() {
                return string;
            }
        };
    }

    public String removeFrom(CharSequence charSequence) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int i = 1;
        while (true) {
            iIndexIn++;
            while (iIndexIn != charArray.length) {
                if (matches(charArray[iIndexIn])) {
                    i++;
                } else {
                    charArray[iIndexIn - i] = charArray[iIndexIn];
                    iIndexIn++;
                }
            }
            return new String(charArray, 0, iIndexIn - i);
        }
    }

    public String replaceFrom(CharSequence charSequence, char c2) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        charArray[iIndexIn] = c2;
        while (true) {
            iIndexIn++;
            if (iIndexIn >= charArray.length) {
                return new String(charArray);
            }
            if (matches(charArray[iIndexIn])) {
                charArray[iIndexIn] = c2;
            }
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    @GwtIncompatible
    public void setBits(BitSet bitSet) {
        for (int i = 65535; i >= 0; i--) {
            if (matches((char) i)) {
                bitSet.set(i);
            }
        }
    }

    public String toString() {
        return super.toString();
    }

    public String trimAndCollapseFrom(CharSequence charSequence, char c2) {
        int length = charSequence.length();
        int i = length - 1;
        int i2 = 0;
        while (i2 < length && matches(charSequence.charAt(i2))) {
            i2++;
        }
        int i3 = i;
        while (i3 > i2 && matches(charSequence.charAt(i3))) {
            i3--;
        }
        if (i2 == 0 && i3 == i) {
            return collapseFrom(charSequence, c2);
        }
        int i4 = i3 + 1;
        return finishCollapseFrom(charSequence, i2, i4, c2, new StringBuilder(i4 - i2), false);
    }

    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && matches(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        while (i2 > i && matches(charSequence.charAt(i2))) {
            i2--;
        }
        return charSequence.subSequence(i, i2 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!matches(charSequence.charAt(i))) {
                return charSequence.subSequence(i, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        int i = 0;
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        int length2 = string.length();
        StringBuilder sb = new StringBuilder(a.a(length2, 3, 2, 16));
        do {
            sb.append((CharSequence) string, i, iIndexIn);
            sb.append(charSequence2);
            i = iIndexIn + 1;
            iIndexIn = indexIn(string, i);
        } while (iIndexIn != -1);
        sb.append((CharSequence) string, i, length2);
        return sb.toString();
    }
}
