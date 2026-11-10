package b.c.a.b;

import com.google.firebase.storage.internal.Util;
import com.google.gson.internal.bind.util.ISO8601Utils;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class g extends SimpleDateFormat {
    public g() {
        super(Util.ISO_8601_FORMAT, Locale.US);
        setTimeZone(TimeZone.getTimeZone(ISO8601Utils.UTC_ID));
    }

    public final String a(String str) {
        if (str != null) {
            return str.replaceAll("Z$", "+0000");
        }
        return null;
    }

    @Override // java.text.DateFormat
    public Date parse(String str) {
        return super.parse(a(str));
    }

    @Override // java.text.DateFormat, java.text.Format
    public Object parseObject(String str, ParsePosition parsePosition) {
        return super.parseObject(a(str), parsePosition);
    }

    @Override // java.text.SimpleDateFormat, java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        return super.parse(a(str), parsePosition);
    }

    @Override // java.text.Format
    public Object parseObject(String str) {
        return super.parseObject(a(str));
    }
}
