package com.fleeksoft.ksoup.parser;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: TokenQueue.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class TokenQueue$consumeCssIdentifier$identifier$1 extends FunctionReferenceImpl implements Function1<Character, Boolean> {
    TokenQueue$consumeCssIdentifier$identifier$1(Object obj) {
        super(1, obj, TokenQueue.Companion.class, "isIdent", "isIdent(C)Z", 0);
    }

    public final Boolean invoke(char c) {
        return Boolean.valueOf(((TokenQueue.Companion) this.receiver).isIdent(c));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
        return invoke(ch.charValue());
    }
}
