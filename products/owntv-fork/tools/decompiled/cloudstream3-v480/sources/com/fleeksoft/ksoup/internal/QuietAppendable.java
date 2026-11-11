package com.fleeksoft.ksoup.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.fleeksoft.ksoup.exception.SerializationException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: QuietAppendable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u0000 \u00102\u00020\u0001:\u0003\u000e\u000f\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0010\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH&J \u0010\u0004\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&¨\u0006\u0011"}, d2 = {"Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "", "<init>", "()V", "append", "csq", "", "c", "", "chars", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "len", "BaseAppendable", "StringBuilderAppendable", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class QuietAppendable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract QuietAppendable append(char c);

    public abstract QuietAppendable append(CharSequence csq);

    public abstract QuietAppendable append(char[] chars, int offset, int len);

    /* JADX INFO: compiled from: QuietAppendable.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\n\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\n\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/fleeksoft/ksoup/internal/QuietAppendable$BaseAppendable;", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "<init>", "(Ljava/lang/Appendable;)V", "quiet", "action", "Lcom/fleeksoft/ksoup/internal/QuietAppendable$BaseAppendable$Action;", "append", "csq", "", "c", "", "chars", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "len", "Action", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class BaseAppendable extends QuietAppendable {
        private final Appendable a;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: QuietAppendable.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bâ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/fleeksoft/ksoup/internal/QuietAppendable$BaseAppendable$Action;", "", "append", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        interface Action {
            void append();
        }

        public BaseAppendable(Appendable a) {
            Intrinsics.checkNotNullParameter(a, "a");
            this.a = a;
        }

        private final BaseAppendable quiet(Action action) {
            try {
                action.append();
                return this;
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public BaseAppendable append(final CharSequence csq) {
            return quiet(new Action() { // from class: com.fleeksoft.ksoup.internal.QuietAppendable$BaseAppendable$$ExternalSyntheticLambda2
                @Override // com.fleeksoft.ksoup.internal.QuietAppendable.BaseAppendable.Action
                public final void append() throws IOException {
                    this.f$0.a.append(csq);
                }
            });
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public BaseAppendable append(final char c) {
            return quiet(new Action() { // from class: com.fleeksoft.ksoup.internal.QuietAppendable$BaseAppendable$$ExternalSyntheticLambda1
                @Override // com.fleeksoft.ksoup.internal.QuietAppendable.BaseAppendable.Action
                public final void append() throws IOException {
                    this.f$0.a.append(c);
                }
            });
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public QuietAppendable append(final char[] chars, final int offset, final int len) {
            Intrinsics.checkNotNullParameter(chars, "chars");
            return quiet(new Action() { // from class: com.fleeksoft.ksoup.internal.QuietAppendable$BaseAppendable$$ExternalSyntheticLambda0
                @Override // com.fleeksoft.ksoup.internal.QuietAppendable.BaseAppendable.Action
                public final void append() throws IOException {
                    QuietAppendable.BaseAppendable baseAppendable = this.f$0;
                    char[] cArr = chars;
                    int i = offset;
                    baseAppendable.a.append(StringsKt.concatToString(cArr, i, len + i));
                }
            });
        }
    }

    /* JADX INFO: compiled from: QuietAppendable.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\u0007\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/internal/QuietAppendable$StringBuilderAppendable;", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "<init>", "(Ljava/lang/StringBuilder;)V", "append", "csq", "", "c", "", "chars", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "len", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class StringBuilderAppendable extends QuietAppendable {
        private final StringBuilder sb;

        public StringBuilderAppendable(StringBuilder sb) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            this.sb = sb;
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public StringBuilderAppendable append(CharSequence csq) {
            this.sb.append(csq);
            return this;
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public StringBuilderAppendable append(char c) {
            this.sb.append(c);
            return this;
        }

        @Override // com.fleeksoft.ksoup.internal.QuietAppendable
        public QuietAppendable append(char[] chars, int offset, int len) {
            Intrinsics.checkNotNullParameter(chars, "chars");
            StringBuilder sb = this.sb;
            sb.append(chars, offset, (len + offset) - offset);
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            return this;
        }

        public String toString() {
            String string = this.sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }

    /* JADX INFO: compiled from: QuietAppendable.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b¨\u0006\t"}, d2 = {"Lcom/fleeksoft/ksoup/internal/QuietAppendable$Companion;", "", "<init>", "()V", "wrap", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QuietAppendable wrap(Appendable a) {
            Intrinsics.checkNotNullParameter(a, "a");
            return a instanceof StringBuilder ? new StringBuilderAppendable((StringBuilder) a) : new BaseAppendable(a);
        }
    }
}
