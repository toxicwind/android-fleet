package com.fleeksoft.ksoup.internal;

import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: SharedConstants.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/internal/SharedConstants;", "", "<init>", "()V", "UserDataKey", "", "AttrRangeKey", "RangeKey", "EndRangeKey", "XmlnsAttr", "DEFAULT_CHAR_BUFFER_SIZE", "", "DefaultBufferSize", "FormSubmitTags", "", "getFormSubmitTags", "()[Ljava/lang/String;", "[Ljava/lang/String;", "DummyUri", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SharedConstants {
    public static final String AttrRangeKey = "ksoup.attrs";
    public static final int DEFAULT_CHAR_BUFFER_SIZE = 8192;
    public static final int DefaultBufferSize = 8192;
    public static final String DummyUri = "https://dummy.example/";
    public static final String EndRangeKey = "ksoup.end";
    public static final String RangeKey = "ksoup.start";
    public static final String UserDataKey = "/ksoup.userdata";
    public static final String XmlnsAttr = "ksoup.xmlns-";
    public static final SharedConstants INSTANCE = new SharedConstants();
    private static final String[] FormSubmitTags = {TvContractCompat.PARAM_INPUT, "keygen", "object", "select", "textarea"};

    private SharedConstants() {
    }

    public final String[] getFormSubmitTags() {
        return FormSubmitTags;
    }
}
