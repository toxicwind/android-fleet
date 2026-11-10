package androidx.core.text;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.core.os.TraceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import b.a.a.a.a;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {
    public static final char LINE_FEED = '\n';

    @NonNull
    public final int[] mParagraphEnds;

    @NonNull
    public final Params mParams;

    @NonNull
    public final Spannable mText;

    @Nullable
    public final PrecomputedText mWrapped;
    public static final Object sLock = new Object();

    @NonNull
    @GuardedBy("sLock")
    public static Executor sExecutor = null;

    private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
            public Params mParams;
            public CharSequence mText;

            public PrecomputedTextCallback(@NonNull final Params params, @NonNull final CharSequence cs) {
                this.mParams = params;
                this.mText = cs;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public PrecomputedTextCompat call() {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }
        }

        public PrecomputedTextFutureTask(@NonNull final Params params, @NonNull final CharSequence text) {
            super(new PrecomputedTextCallback(params, text));
        }
    }

    public PrecomputedTextCompat(@NonNull CharSequence text, @NonNull Params params, @NonNull int[] paraEnds) {
        this.mText = new SpannableString(text);
        this.mParams = params;
        this.mParagraphEnds = paraEnds;
        this.mWrapped = null;
    }

    @SuppressLint({"NewApi"})
    public static PrecomputedTextCompat create(@NonNull CharSequence text, @NonNull Params params) {
        PrecomputedText.Params params2;
        if (text == null) {
            throw new NullPointerException();
        }
        if (params == null) {
            throw new NullPointerException();
        }
        try {
            TraceCompat.beginSection("PrecomputedText");
            if (Build.VERSION.SDK_INT >= 29 && (params2 = params.mWrapped) != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(text, params2), params);
            }
            ArrayList arrayList = new ArrayList();
            int length = text.length();
            int i = 0;
            while (i < length) {
                int iIndexOf = TextUtils.indexOf(text, '\n', i, length);
                i = iIndexOf < 0 ? length : iIndexOf + 1;
                arrayList.add(Integer.valueOf(i));
            }
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
            int i3 = Build.VERSION.SDK_INT;
            StaticLayout.Builder.obtain(text, 0, text.length(), params.getTextPaint(), Integer.MAX_VALUE).setBreakStrategy(params.getBreakStrategy()).setHyphenationFrequency(params.getHyphenationFrequency()).setTextDirection(params.getTextDirection()).build();
            return new PrecomputedTextCompat(text, params, iArr);
        } finally {
            TraceCompat.endSection();
        }
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull final CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        PrecomputedTextFutureTask precomputedTextFutureTask = new PrecomputedTextFutureTask(params, charSequence);
        if (executor == null) {
            synchronized (sLock) {
                if (sExecutor == null) {
                    sExecutor = Executors.newFixedThreadPool(1);
                }
                executor = sExecutor;
            }
        }
        executor.execute(precomputedTextFutureTask);
        return precomputedTextFutureTask;
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        return this.mText.charAt(index);
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    @SuppressLint({"NewApi"})
    public int getParagraphCount() {
        return Build.VERSION.SDK_INT >= 29 ? this.mWrapped.getParagraphCount() : this.mParagraphEnds.length;
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    @SuppressLint({"NewApi"})
    public int getParagraphEnd(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int paraIndex) {
        Preconditions.checkArgumentInRange(paraIndex, 0, getParagraphCount(), "paraIndex");
        return Build.VERSION.SDK_INT >= 29 ? this.mWrapped.getParagraphEnd(paraIndex) : this.mParagraphEnds[paraIndex];
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    @SuppressLint({"NewApi"})
    public int getParagraphStart(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int paraIndex) {
        Preconditions.checkArgumentInRange(paraIndex, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 29) {
            return this.mWrapped.getParagraphStart(paraIndex);
        }
        if (paraIndex == 0) {
            return 0;
        }
        return this.mParagraphEnds[paraIndex - 1];
    }

    @NonNull
    public Params getParams() {
        return this.mParams;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PrecomputedText getPrecomputedText() {
        Spannable spannable = this.mText;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object tag) {
        return this.mText.getSpanEnd(tag);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object tag) {
        return this.mText.getSpanFlags(tag);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object tag) {
        return this.mText.getSpanStart(tag);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.mWrapped.getSpans(i, i2, cls) : (T[]) this.mText.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.mText.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int start, int limit, Class type) {
        return this.mText.nextSpanTransition(start, limit, type);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object what) {
        if (what instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mWrapped.removeSpan(what);
        } else {
            this.mText.removeSpan(what);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object what, int start, int end, int flags) {
        if (what instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mWrapped.setSpan(what, start, end, flags);
        } else {
            this.mText.setSpan(what, start, end, flags);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int start, int end) {
        return this.mText.subSequence(start, end);
    }

    @Override // java.lang.CharSequence
    @NonNull
    public String toString() {
        return this.mText.toString();
    }

    @RequiresApi(28)
    public PrecomputedTextCompat(@NonNull PrecomputedText precomputed, @NonNull Params params) {
        this.mText = precomputed;
        this.mParams = params;
        this.mParagraphEnds = null;
        this.mWrapped = Build.VERSION.SDK_INT < 29 ? null : precomputed;
    }

    public static final class Params {
        public final int mBreakStrategy;
        public final int mHyphenationFrequency;

        @NonNull
        public final TextPaint mPaint;

        @Nullable
        public final TextDirectionHeuristic mTextDir;
        public final PrecomputedText.Params mWrapped;

        public static class Builder {
            public int mBreakStrategy;
            public int mHyphenationFrequency;

            @NonNull
            public final TextPaint mPaint;
            public TextDirectionHeuristic mTextDir;

            public Builder(@NonNull TextPaint paint) {
                this.mPaint = paint;
                int i = Build.VERSION.SDK_INT;
                this.mBreakStrategy = 1;
                this.mHyphenationFrequency = 1;
                this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }

            @NonNull
            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int strategy) {
                this.mBreakStrategy = strategy;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int frequency) {
                this.mHyphenationFrequency = frequency;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDir) {
                this.mTextDir = textDir;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public Params(@NonNull TextPaint paint, @NonNull TextDirectionHeuristic textDir, int strategy, int frequency) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mWrapped = new PrecomputedText.Params.Builder(paint).setBreakStrategy(strategy).setHyphenationFrequency(frequency).setTextDirection(textDir).build();
            } else {
                this.mWrapped = null;
            }
            this.mPaint = paint;
            this.mTextDir = textDir;
            this.mBreakStrategy = strategy;
            this.mHyphenationFrequency = frequency;
        }

        public boolean equals(@Nullable Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Params)) {
                return false;
            }
            Params params = (Params) o;
            if (!equalsWithoutTextDirection(params)) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            return this.mTextDir == params.getTextDirection();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean equalsWithoutTextDirection(@NonNull Params other) {
            int i = Build.VERSION.SDK_INT;
            if (this.mBreakStrategy != other.getBreakStrategy() || this.mHyphenationFrequency != other.getHyphenationFrequency() || this.mPaint.getTextSize() != other.getTextPaint().getTextSize() || this.mPaint.getTextScaleX() != other.getTextPaint().getTextScaleX() || this.mPaint.getTextSkewX() != other.getTextPaint().getTextSkewX()) {
                return false;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (this.mPaint.getLetterSpacing() != other.getTextPaint().getLetterSpacing() || !TextUtils.equals(this.mPaint.getFontFeatureSettings(), other.getTextPaint().getFontFeatureSettings()) || this.mPaint.getFlags() != other.getTextPaint().getFlags()) {
                return false;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (!this.mPaint.getTextLocales().equals(other.getTextPaint().getTextLocales())) {
                return false;
            }
            if (this.mPaint.getTypeface() == null) {
                return other.getTextPaint().getTypeface() == null;
            }
            return this.mPaint.getTypeface().equals(other.getTextPaint().getTypeface());
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(IidStore.JSON_ENCODED_PREFIX);
            StringBuilder sbA = a.a("textSize=");
            sbA.append(this.mPaint.getTextSize());
            sb.append(sbA.toString());
            sb.append(", textScaleX=" + this.mPaint.getTextScaleX());
            sb.append(", textSkewX=" + this.mPaint.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            StringBuilder sbA2 = a.a(", letterSpacing=");
            sbA2.append(this.mPaint.getLetterSpacing());
            sb.append(sbA2.toString());
            sb.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
            int i2 = Build.VERSION.SDK_INT;
            StringBuilder sbA3 = a.a(", textLocale=");
            sbA3.append(this.mPaint.getTextLocales());
            sb.append(sbA3.toString());
            sb.append(", typeface=" + this.mPaint.getTypeface());
            int i3 = Build.VERSION.SDK_INT;
            StringBuilder sbA4 = a.a(", variationSettings=");
            sbA4.append(this.mPaint.getFontVariationSettings());
            sb.append(sbA4.toString());
            sb.append(", textDir=" + this.mTextDir);
            sb.append(", breakStrategy=" + this.mBreakStrategy);
            sb.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
            sb.append("}");
            return sb.toString();
        }

        @RequiresApi(28)
        public Params(@NonNull PrecomputedText.Params wrapped) {
            this.mPaint = wrapped.getTextPaint();
            this.mTextDir = wrapped.getTextDirection();
            this.mBreakStrategy = wrapped.getBreakStrategy();
            this.mHyphenationFrequency = wrapped.getHyphenationFrequency();
            this.mWrapped = Build.VERSION.SDK_INT < 29 ? null : wrapped;
        }
    }
}
