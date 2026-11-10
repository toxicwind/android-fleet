package okhttp3;

import com.hmct.cloud.sdk.utils.Constants;
import d.g;
import d.h;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class FormBody extends RequestBody {
    public static final MediaType CONTENT_TYPE = MediaType.get(Constants.CONTENTTYPE_URLENCODED);
    public final List<String> encodedNames;
    public final List<String> encodedValues;

    public static final class Builder {
        public final Charset charset;
        public final List<String> names;
        public final List<String> values;

        public Builder() {
            this(null);
        }

        public Builder add(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.names.add(HttpUrl.canonicalize(str, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.charset));
            this.values.add(HttpUrl.canonicalize(str2, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.charset));
            return this;
        }

        public Builder addEncoded(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.names.add(HttpUrl.canonicalize(str, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.charset));
            this.values.add(HttpUrl.canonicalize(str2, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.charset));
            return this;
        }

        public FormBody build() {
            return new FormBody(this.names, this.values);
        }

        public Builder(Charset charset) {
            this.names = new ArrayList();
            this.values = new ArrayList();
            this.charset = charset;
        }
    }

    public FormBody(List<String> list, List<String> list2) {
        this.encodedNames = Util.immutableList(list);
        this.encodedValues = Util.immutableList(list2);
    }

    private long writeOrCountBytes(h hVar, boolean z) {
        g gVar = z ? new g() : hVar.a();
        int size = this.encodedNames.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                gVar.writeByte(38);
            }
            gVar.a(this.encodedNames.get(i));
            gVar.writeByte(61);
            gVar.a(this.encodedValues.get(i));
        }
        if (!z) {
            return 0L;
        }
        long j = gVar.f2102c;
        gVar.clear();
        return j;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return writeOrCountBytes(null, true);
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    public String encodedName(int i) {
        return this.encodedNames.get(i);
    }

    public String encodedValue(int i) {
        return this.encodedValues.get(i);
    }

    public String name(int i) {
        return HttpUrl.percentDecode(encodedName(i), true);
    }

    public int size() {
        return this.encodedNames.size();
    }

    public String value(int i) {
        return HttpUrl.percentDecode(encodedValue(i), true);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(h hVar) {
        writeOrCountBytes(hVar, false);
    }
}
