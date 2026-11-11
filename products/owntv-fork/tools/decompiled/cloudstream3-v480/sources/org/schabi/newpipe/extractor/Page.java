package org.schabi.newpipe.extractor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class Page implements Serializable {

    @Nullable
    private final byte[] body;
    private final Map<String, String> cookies;
    private final String id;
    private final List<String> ids;
    private final String url;

    public Page(String str, String str2, List<String> list, Map<String, String> map, @Nullable byte[] bArr) {
        this.url = str;
        this.id = str2;
        this.ids = list;
        this.cookies = map;
        this.body = bArr;
    }

    public Page(String str) {
        this(str, null, null, null, null);
    }

    public Page(String str, String str2) {
        this(str, str2, null, null, null);
    }

    public Page(String str, String str2, byte[] bArr) {
        this(str, str2, null, null, bArr);
    }

    public Page(String str, byte[] bArr) {
        this(str, null, null, null, bArr);
    }

    public Page(String str, Map<String, String> map) {
        this(str, null, null, map, null);
    }

    public Page(List<String> list) {
        this(null, null, list, null, null);
    }

    public Page(List<String> list, Map<String, String> map) {
        this(null, null, list, map, null);
    }

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }

    public List<String> getIds() {
        return this.ids;
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public static boolean isValid(Page page) {
        if (page != null) {
            return (Utils.isNullOrEmpty(page.getUrl()) && Utils.isNullOrEmpty(page.getIds())) ? false : true;
        }
        return false;
    }

    @Nullable
    public byte[] getBody() {
        return this.body;
    }
}
