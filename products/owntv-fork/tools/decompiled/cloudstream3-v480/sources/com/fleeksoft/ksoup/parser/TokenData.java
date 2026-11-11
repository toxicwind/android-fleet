package com.fleeksoft.ksoup.parser;

import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.ported.Character;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TokenData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\nH\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\nJ\u0006\u0010\u0004\u001a\u00020\u0005J\n\u0010\u0016\u001a\u00020\u0005H\u0096\u0080\u0004R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/fleeksoft/ksoup/parser/TokenData;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "set", "", "str", "append", "c", "", "appendCodePoint", "codepoint", "", "flipToBuilder", "hasData", "", "reset", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TokenData {
    private StringBuilder builder;
    private String value;

    public final void set(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        reset();
        this.value = str;
    }

    public final void append(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        StringBuilder sb = this.builder;
        if (sb != null) {
            Intrinsics.checkNotNull(sb);
            sb.append(str);
        } else {
            if (this.value != null) {
                flipToBuilder();
                StringBuilder sb2 = this.builder;
                Intrinsics.checkNotNull(sb2);
                sb2.append(str);
                return;
            }
            this.value = str;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void append(char c) {
        StringBuilder sb = this.builder;
        if (sb != null) {
            Intrinsics.checkNotNull(sb);
            sb.append(c);
        } else {
            if (this.value != null) {
                flipToBuilder();
                StringBuilder sb2 = this.builder;
                Intrinsics.checkNotNull(sb2);
                sb2.append(c);
                return;
            }
            this.value = String.valueOf(c);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void appendCodePoint(int codepoint) {
        StringBuilder sb = this.builder;
        if (sb != null) {
            Intrinsics.checkNotNull(sb);
            sb.appendCodePoint(codepoint);
        } else {
            if (this.value != null) {
                flipToBuilder();
                StringBuilder sb2 = this.builder;
                Intrinsics.checkNotNull(sb2);
                sb2.appendCodePoint(codepoint);
                return;
            }
            this.value = StringsKt.concatToString(Character.INSTANCE.toChars(codepoint));
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void flipToBuilder() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        this.builder = sbBorrowBuilder;
        Intrinsics.checkNotNull(sbBorrowBuilder);
        sbBorrowBuilder.append(this.value);
        this.value = null;
    }

    public final boolean hasData() {
        return (this.builder == null && this.value == null) ? false : true;
    }

    public final void reset() {
        StringBuilder sb = this.builder;
        if (sb != null) {
            StringUtil.INSTANCE.releaseBuilderVoid(sb);
            this.builder = null;
        }
        this.value = null;
    }

    public final String value() {
        StringBuilder sb = this.builder;
        if (sb != null) {
            this.value = sb.toString();
            StringUtil.INSTANCE.releaseBuilder(sb);
            this.builder = null;
            String str = this.value;
            Intrinsics.checkNotNull(str);
            return str;
        }
        String str2 = this.value;
        return str2 == null ? "" : str2;
    }

    public String toString() {
        String string;
        StringBuilder sb = this.builder;
        if (sb != null && (string = sb.toString()) != null) {
            return string;
        }
        String str = this.value;
        return str == null ? "" : str;
    }
}
