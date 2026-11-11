package com.fleeksoft.ksoup.parser;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParseError.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\nJ\n\u0010\u0010\u001a\u00020\u0005H\u0096\u0080\u0004R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/fleeksoft/ksoup/parser/ParseError;", "", "reader", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "errorMsg", "", "<init>", "(Lcom/fleeksoft/ksoup/parser/CharacterReader;Ljava/lang/String;)V", CloudStreamPackage.POSITION_EXTRA, "", "(ILjava/lang/String;)V", "getPos", "()I", "cursorPos", "getErrorMsg", "()Ljava/lang/String;", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ParseError {
    private final String cursorPos;
    private final String errorMsg;
    private final int pos;

    public final int getPos() {
        return this.pos;
    }

    public final String getErrorMsg() {
        return this.errorMsg;
    }

    public ParseError(CharacterReader reader, String errorMsg) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        this.pos = reader.pos();
        this.cursorPos = reader.posLineCol();
        this.errorMsg = errorMsg;
    }

    public ParseError(int i, String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        this.pos = i;
        this.cursorPos = String.valueOf(i);
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return "<" + this.cursorPos + ">: " + this.errorMsg;
    }
}
