package okhttp3.g0.k;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class h implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f210a;
    boolean b;
    String c;

    h(List list) {
        this.f210a = list;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Object obj2;
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (objArr == null) {
            objArr = okhttp3.g0.e.b;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return Boolean.TRUE;
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.b = true;
            return null;
        }
        if (name.equals("protocols") && objArr.length == 0) {
            return this.f210a;
        }
        if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
            if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                return method.invoke(this, objArr);
            }
            this.c = (String) objArr[0];
            return null;
        }
        List list = (List) objArr[0];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (this.f210a.contains(list.get(i))) {
                obj2 = list.get(i);
                String str = (String) obj2;
                this.c = str;
                return str;
            }
        }
        obj2 = this.f210a.get(0);
        String str2 = (String) obj2;
        this.c = str2;
        return str2;
    }
}
