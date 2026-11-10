package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class PatternFilenameFilter implements FilenameFilter {
    public final Pattern pattern;

    public PatternFilenameFilter(String str) {
        this(Pattern.compile(str));
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return this.pattern.matcher(str).matches();
    }

    public PatternFilenameFilter(Pattern pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }
        this.pattern = pattern;
    }
}
