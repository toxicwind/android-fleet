package com.lagradost.nicehttp;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal._UtilCommonKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;
import okio.Source;

/* JADX INFO: compiled from: OkioHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\tJ\u001a\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/lagradost/nicehttp/OkioHelper;", "", "<init>", "()V", "UNICODE_BOMS", "Lokio/Options;", "getUNICODE_BOMS", "()Lokio/Options;", "readBomAsCharset", "Ljava/nio/charset/Charset;", "Lokio/BufferedSource;", "default", "writeMax", "", "Lokio/Buffer;", "source", "Lokio/Source;", "max", "readLimited", "", TtmlNode.TAG_BODY, "Lokhttp3/ResponseBody;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OkioHelper {
    public static final OkioHelper INSTANCE = new OkioHelper();
    private static final Options UNICODE_BOMS = Options.INSTANCE.of(ByteString.INSTANCE.decodeHex("efbbbf"), ByteString.INSTANCE.decodeHex("feff"), ByteString.INSTANCE.decodeHex("fffe0000"), ByteString.INSTANCE.decodeHex("fffe"), ByteString.INSTANCE.decodeHex("0000feff"));

    private OkioHelper() {
    }

    public final Options getUNICODE_BOMS() {
        return UNICODE_BOMS;
    }

    public final Charset readBomAsCharset(BufferedSource bufferedSource, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(charset, "default");
        int iSelect = bufferedSource.select(UNICODE_BOMS);
        if (iSelect == -1) {
            return charset;
        }
        if (iSelect == 0) {
            return Charsets.UTF_8;
        }
        if (iSelect == 1) {
            return Charsets.UTF_16BE;
        }
        if (iSelect == 2) {
            return Charsets.INSTANCE.UTF32_LE();
        }
        if (iSelect == 3) {
            return Charsets.UTF_16LE;
        }
        if (iSelect == 4) {
            return Charsets.INSTANCE.UTF32_BE();
        }
        throw new AssertionError();
    }

    public final long writeMax(Buffer buffer, Source source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = 0;
        while (j2 < j) {
            long j3 = source.read(buffer, 8192L);
            if (j3 == -1) {
                break;
            }
            j2 += j3;
        }
        return j2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v8 */
    public final String readLimited(ResponseBody body, long max) {
        Charset charsetCharset$default;
        String string;
        Field field;
        Charset charsetCharset$default2;
        Intrinsics.checkNotNullParameter(body, "body");
        BufferedSource source = body.getSource();
        String th = null;
        try {
            BufferedSource bufferedSource = source;
            try {
                Field[] fields = bufferedSource.getClass().getFields();
                Intrinsics.checkNotNullExpressionValue(fields, "getFields(...)");
                Field[] fieldArr = fields;
                int length = fieldArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        field = null;
                        break;
                    }
                    field = fieldArr[i];
                    if (Intrinsics.areEqual(field.getName(), "source")) {
                        break;
                    }
                    i++;
                }
                Field field2 = field;
                Object obj = field2 != null ? field2.get(bufferedSource) : null;
                Source source2 = obj instanceof Source ? (Source) obj : null;
                OkioHelper okioHelper = INSTANCE;
                MediaType mediaType = body.getMediaType();
                if (mediaType == null || (charsetCharset$default2 = MediaType.charset$default(mediaType, null, 1, null)) == null) {
                    charsetCharset$default2 = Charsets.UTF_8;
                }
                Charset bomAsCharset = okioHelper.readBomAsCharset(bufferedSource, charsetCharset$default2);
                if (source2 == null) {
                    System.out.println((Object) "Exception in NiceHttp: Failed to get .source with reflection");
                    String string2 = bufferedSource.readString(bomAsCharset);
                    if (source != null) {
                        try {
                            source.close();
                        } catch (Throwable unused) {
                        }
                    }
                    return string2;
                }
                if (okioHelper.writeMax(bufferedSource.getBuffer(), source2, max) >= max) {
                    throw new IllegalStateException("Called .text on a text file with Content-Length > " + max + " bytes, this throws an exception to prevent OOM. If you know what you are doing, use .body/textLarge/documentLarge to download larger files");
                }
                string = bufferedSource.getBuffer().readString(bomAsCharset);
                if (source != null) {
                    try {
                        source.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                String str = th;
                th = string;
                th = str;
                if (th == 0) {
                    _UtilCommonKt.closeQuietly(body);
                    return th;
                }
                throw th;
            } catch (SecurityException e) {
                System.out.println((Object) "Exception in NiceHttp: Failed to get .source with reflection");
                e.printStackTrace();
                OkioHelper okioHelper2 = INSTANCE;
                MediaType mediaType2 = body.getMediaType();
                if (mediaType2 == null || (charsetCharset$default = MediaType.charset$default(mediaType2, null, 1, null)) == null) {
                    charsetCharset$default = Charsets.UTF_8;
                }
                string = bufferedSource.readString(okioHelper2.readBomAsCharset(bufferedSource, charsetCharset$default));
            }
        } catch (Throwable th3) {
            th = th3;
            if (source != null) {
                try {
                    source.close();
                } catch (Throwable th4) {
                    ExceptionsKt.addSuppressed(th, th4);
                }
            }
        }
    }
}
