package b.b.a.a;

import android.os.Message;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class e implements b.b.a.g<Message> {
    @Override // b.b.a.g
    public String a(Message message) {
        Message message2 = message;
        if (message2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(message2.getClass().getName() + " [" + b.b.a.g.f39a);
        sb.append(String.format("%s = %s", "what", Integer.valueOf(message2.what)));
        sb.append(b.b.a.g.f39a);
        sb.append(String.format("%s = %s", "when", Long.valueOf(message2.getWhen())));
        sb.append(b.b.a.g.f39a);
        sb.append(String.format("%s = %s", "arg1", Integer.valueOf(message2.arg1)));
        sb.append(b.b.a.g.f39a);
        sb.append(String.format("%s = %s", "arg2", Integer.valueOf(message2.arg2)));
        sb.append(b.b.a.g.f39a);
        sb.append(String.format("%s = %s", "data", new a().a(message2.getData())));
        sb.append(b.b.a.g.f39a);
        sb.append(String.format("%s = %s", IconCompat.EXTRA_OBJ, b.b.a.c.a.a(message2.obj)));
        return b.a.a.a.a.a(sb, b.b.a.g.f39a, "]");
    }

    @Override // b.b.a.g
    public Class<Message> a() {
        return Message.class;
    }
}
