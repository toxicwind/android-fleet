package org.jsoup.helper;

import com.google.re2j.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes8.dex */
final class Re2jRegex extends Regex {
    private static final Pattern unused = Pattern.compile("");
    private final com.google.re2j.Pattern re2jPattern;

    private Re2jRegex(com.google.re2j.Pattern pattern) {
        super(unused);
        this.re2jPattern = pattern;
    }

    public static Regex compile(String str) {
        try {
            return new Re2jRegex(com.google.re2j.Pattern.compile(str));
        } catch (OutOfMemoryError | StackOverflowError e) {
            throw new ValidationException("Pattern complexity error: " + e.getMessage());
        } catch (RuntimeException e2) {
            throw new ValidationException("Pattern syntax error: " + e2.getMessage());
        }
    }

    @Override // org.jsoup.helper.Regex
    public Regex.Matcher matcher(CharSequence charSequence) {
        return new Re2jMatcher(this.re2jPattern.matcher(charSequence));
    }

    @Override // org.jsoup.helper.Regex
    public String toString() {
        return this.re2jPattern.toString();
    }

    private static final class Re2jMatcher implements Regex.Matcher {
        private final Matcher delegate;

        Re2jMatcher(Matcher matcher) {
            this.delegate = matcher;
        }

        @Override // org.jsoup.helper.Regex.Matcher
        public boolean find() {
            return this.delegate.find();
        }
    }
}
