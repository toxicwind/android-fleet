package org.mozilla.javascript;

import org.mozilla.javascript.ast.AstNode;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class IRFactory$$ExternalSyntheticLambda0 implements Parser.Transformer {
    public final /* synthetic */ IRFactory f$0;

    public /* synthetic */ IRFactory$$ExternalSyntheticLambda0(IRFactory iRFactory) {
        this.f$0 = iRFactory;
    }

    @Override // org.mozilla.javascript.Parser.Transformer
    public final Node transform(AstNode astNode) {
        return this.f$0.transform(astNode);
    }
}
