package c.a.b;

import com.google.common.base.Supplier;
import java.net.ProxySelector;

/* JADX INFO: loaded from: classes2.dex */
public class Rc implements Supplier<ProxySelector> {
    @Override // com.google.common.base.Supplier
    public ProxySelector get() {
        return ProxySelector.getDefault();
    }
}
