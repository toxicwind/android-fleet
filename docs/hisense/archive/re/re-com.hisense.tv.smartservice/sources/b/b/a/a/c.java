package b.b.a.a;

import android.content.Intent;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c implements b.b.a.g<Intent> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<Integer, String> f6a = new HashMap();

    static {
        int iIntValue;
        for (Field field : Intent.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().startsWith("FLAG_")) {
                try {
                    Object obj = field.get(Intent.class);
                    iIntValue = ((obj instanceof Integer) || obj.getClass().getSimpleName().equals("int")) ? ((Integer) obj).intValue() : 0;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (f6a.get(Integer.valueOf(iIntValue)) == null) {
                    f6a.put(Integer.valueOf(iIntValue), field.getName());
                }
            }
        }
    }

    @Override // b.b.a.g
    public String a(Intent intent) {
        Intent intent2 = intent;
        StringBuilder sb = new StringBuilder(Intent.class.getSimpleName() + " [" + b.b.a.g.f39a);
        StringBuilder sbA = b.a.a.a.a.a("%s = %s");
        sbA.append(b.b.a.g.f39a);
        sb.append(String.format(sbA.toString(), "Scheme", intent2.getScheme()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "Action", intent2.getAction()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "DataString", intent2.getDataString()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "Type", intent2.getType()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "Package", intent2.getPackage()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "ComponentInfo", intent2.getComponent()));
        String str = "%s = %s" + b.b.a.g.f39a;
        Object[] objArr = new Object[2];
        objArr[0] = "Flags";
        int flags = intent2.getFlags();
        StringBuilder sb2 = new StringBuilder();
        Iterator<Integer> it = f6a.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if ((iIntValue & flags) == iIntValue) {
                sb2.append(f6a.get(Integer.valueOf(iIntValue)));
                sb2.append(" | ");
            }
        }
        if (TextUtils.isEmpty(sb2.toString())) {
            sb2.append(flags);
        } else if (sb2.indexOf("|") != -1) {
            sb2.delete(sb2.length() - 2, sb2.length());
        }
        objArr[1] = sb2.toString();
        sb.append(String.format(str, objArr));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "Categories", intent2.getCategories()));
        sb.append(String.format("%s = %s" + b.b.a.g.f39a, "Extras", new a().a(intent2.getExtras())));
        return sb.toString() + "]";
    }

    @Override // b.b.a.g
    public Class<Intent> a() {
        return Intent.class;
    }
}
