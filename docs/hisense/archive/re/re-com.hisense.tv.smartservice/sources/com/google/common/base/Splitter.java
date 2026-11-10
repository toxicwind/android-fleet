package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Splitter {
    public final int limit;
    public final boolean omitEmptyStrings;
    public final Strategy strategy;
    public final CharMatcher trimmer;

    @Beta
    public static final class MapSplitter {
        public static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        public final Splitter entrySplitter;
        public final Splitter outerSplitter;

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : this.outerSplitter.split(charSequence)) {
                Iterator itAccess$000 = Splitter.access$000(this.entrySplitter, str);
                Preconditions.checkArgument(itAccess$000.hasNext(), INVALID_ENTRY_MESSAGE, str);
                String str2 = (String) itAccess$000.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str2), "Duplicate key [%s] found.", str2);
                Preconditions.checkArgument(itAccess$000.hasNext(), INVALID_ENTRY_MESSAGE, str);
                linkedHashMap.put(str2, (String) itAccess$000.next());
                Preconditions.checkArgument(!itAccess$000.hasNext(), INVALID_ENTRY_MESSAGE, str);
            }
            return Collections.unmodifiableMap(linkedHashMap);
        }

        public MapSplitter(Splitter splitter, Splitter splitter2) {
            this.outerSplitter = splitter;
            if (splitter2 == null) {
                throw new NullPointerException();
            }
            this.entrySplitter = splitter2;
        }
    }

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        public int limit;
        public int offset = 0;
        public final boolean omitEmptyStrings;
        public final CharSequence toSplit;
        public final CharMatcher trimmer;

        public SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = charSequence;
        }

        public abstract int separatorEnd(int i);

        public abstract int separatorStart(int i);

        @Override // com.google.common.base.AbstractIterator
        public String computeNext() {
            int i = this.offset;
            while (true) {
                int i2 = this.offset;
                if (i2 == -1) {
                    return endOfData();
                }
                int iSeparatorStart = separatorStart(i2);
                if (iSeparatorStart == -1) {
                    iSeparatorStart = this.toSplit.length();
                    this.offset = -1;
                } else {
                    this.offset = separatorEnd(iSeparatorStart);
                }
                int i3 = this.offset;
                if (i3 == i) {
                    this.offset = i3 + 1;
                    if (this.offset > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i < iSeparatorStart && this.trimmer.matches(this.toSplit.charAt(i))) {
                        i++;
                    }
                    while (iSeparatorStart > i && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                        iSeparatorStart--;
                    }
                    if (!this.omitEmptyStrings || i != iSeparatorStart) {
                        int i4 = this.limit;
                        if (i4 == 1) {
                            iSeparatorStart = this.toSplit.length();
                            this.offset = -1;
                            while (iSeparatorStart > i && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                                iSeparatorStart--;
                            }
                        } else {
                            this.limit = i4 - 1;
                        }
                        return this.toSplit.subSequence(i, iSeparatorStart).toString();
                    }
                    i = this.offset;
                }
            }
        }
    }

    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    public Splitter(Strategy strategy, boolean z, CharMatcher charMatcher, int i) {
        this.strategy = strategy;
        this.omitEmptyStrings = z;
        this.trimmer = charMatcher;
        this.limit = i;
    }

    public static /* synthetic */ Iterator access$000(Splitter splitter, CharSequence charSequence) {
        return splitter.strategy.iterator(splitter, charSequence);
    }

    public static Splitter fixedLength(final int i) {
        Preconditions.checkArgument(i > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.4
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.4.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i2) {
                        return i2;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i2) {
                        int i3 = i2 + i;
                        if (i3 < this.toSplit.length()) {
                            return i3;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public static Splitter on(final CharMatcher charMatcher) {
        if (charMatcher != null) {
            return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.1
                @Override // com.google.common.base.Splitter.Strategy
                public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                    return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.1.1
                        @Override // com.google.common.base.Splitter.SplittingIterator
                        public int separatorEnd(int i) {
                            return i + 1;
                        }

                        @Override // com.google.common.base.Splitter.SplittingIterator
                        public int separatorStart(int i) {
                            return charMatcher.indexIn(this.toSplit, i);
                        }
                    };
                }
            });
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public static Splitter onPattern(String str) {
        return on(Platform.compilePattern(str));
    }

    private Iterator<String> splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    public Splitter limit(int i) {
        Preconditions.checkArgument(i > 0, "must be greater than zero: %s", i);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, i);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence charSequence) {
        if (charSequence != null) {
            return new Iterable<String>() { // from class: com.google.common.base.Splitter.5
                @Override // java.lang.Iterable
                public Iterator<String> iterator() {
                    return Splitter.access$000(Splitter.this, charSequence);
                }

                public String toString() {
                    Joiner joiner = new Joiner(", ");
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    StringBuilder sbAppendTo = joiner.appendTo(sb, (Iterable<?>) this);
                    sbAppendTo.append(']');
                    return sbAppendTo.toString();
                }
            };
        }
        throw new NullPointerException();
    }

    public List<String> splitToList(CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException();
        }
        Iterator<String> it = this.strategy.iterator(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.Whitespace.INSTANCE);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(on(str));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char c2) {
        return withKeyValueSeparator(on(c2));
    }

    public static Splitter on(final String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return on(str.charAt(0));
        }
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.2
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.2.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i) {
                        return str.length() + i;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i) {
                        int length = str.length();
                        int length2 = this.toSplit.length() - length;
                        while (i <= length2) {
                            for (int i2 = 0; i2 < length; i2++) {
                                if (this.toSplit.charAt(i2 + i) != str.charAt(i2)) {
                                    i++;
                                }
                            }
                            return i;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        if (charMatcher != null) {
            return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
        }
        throw new NullPointerException();
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(this, splitter);
    }

    public Splitter(Strategy strategy) {
        CharMatcher.None none = CharMatcher.None.INSTANCE;
        this.strategy = strategy;
        this.omitEmptyStrings = false;
        this.trimmer = none;
        this.limit = Integer.MAX_VALUE;
    }

    @GwtIncompatible
    public static Splitter on(Pattern pattern) {
        return on(new JdkPattern(pattern));
    }

    public static Splitter on(final CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.matcher("").matches(), "The pattern may not match the empty string: %s", commonPattern);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.3
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                final CommonMatcher commonMatcherMatcher = commonPattern.matcher(charSequence);
                return new SplittingIterator(this, splitter, charSequence) { // from class: com.google.common.base.Splitter.3.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i) {
                        return commonMatcherMatcher.end();
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i) {
                        if (commonMatcherMatcher.find(i)) {
                            return commonMatcherMatcher.start();
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public static Splitter on(char c2) {
        return on(new CharMatcher.Is(c2));
    }
}
