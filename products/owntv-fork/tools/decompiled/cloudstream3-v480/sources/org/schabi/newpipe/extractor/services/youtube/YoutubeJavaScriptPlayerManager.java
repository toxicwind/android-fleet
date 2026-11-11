package org.schabi.newpipe.extractor.services.youtube;

import j$.util.Objects;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.JavaScript;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeJavaScriptPlayerManager {

    @Nonnull
    private static final Map<String, String> CACHED_THROTTLING_PARAMETERS = new HashMap();
    private static String cachedJavaScriptPlayerCode;

    @Nullable
    private static String cachedSignatureDeobfuscationFunction;

    @Nullable
    private static Integer cachedSignatureTimestamp;

    @Nullable
    private static String cachedThrottlingDeobfuscationFunction;

    @Nullable
    private static String cachedThrottlingDeobfuscationFunctionName;

    @Nullable
    private static ParsingException sigDeobFuncExtractionEx;

    @Nullable
    private static ParsingException sigTimestampExtractionEx;

    @Nullable
    private static ParsingException throttlingDeobfFuncExtractionEx;

    private YoutubeJavaScriptPlayerManager() {
    }

    @Nonnull
    public static Integer getSignatureTimestamp(@Nonnull String str) throws Exception {
        Integer num = cachedSignatureTimestamp;
        if (num != null) {
            return num;
        }
        ParsingException parsingException = sigTimestampExtractionEx;
        if (parsingException != null) {
            throw parsingException;
        }
        extractJavaScriptCodeIfNeeded(str);
        try {
            cachedSignatureTimestamp = Integer.valueOf(YoutubeSignatureUtils.getSignatureTimestamp(cachedJavaScriptPlayerCode));
        } catch (NumberFormatException e) {
            sigTimestampExtractionEx = new ParsingException("Could not convert signature timestamp to a number", e);
        } catch (ParsingException e2) {
            sigTimestampExtractionEx = e2;
            throw e2;
        } catch (Exception e3) {
            sigTimestampExtractionEx = new ParsingException("Could not get signature timestamp", e3);
            throw e3;
        }
        return cachedSignatureTimestamp;
    }

    @Nonnull
    public static String deobfuscateSignature(@Nonnull String str, @Nonnull String str2) throws Exception {
        ParsingException parsingException = sigDeobFuncExtractionEx;
        if (parsingException != null) {
            throw parsingException;
        }
        extractJavaScriptCodeIfNeeded(str);
        if (cachedSignatureDeobfuscationFunction == null) {
            try {
                cachedSignatureDeobfuscationFunction = YoutubeSignatureUtils.getDeobfuscationCode(cachedJavaScriptPlayerCode);
            } catch (ParsingException e) {
                sigDeobFuncExtractionEx = e;
                throw e;
            } catch (Exception e2) {
                sigDeobFuncExtractionEx = new ParsingException("Could not get signature parameter deobfuscation JavaScript function", e2);
                throw e2;
            }
        }
        try {
            return (String) Objects.requireNonNullElse(JavaScript.run(cachedSignatureDeobfuscationFunction, "deobfuscate", str2), "");
        } catch (Exception e3) {
            throw new ParsingException("Could not run signature parameter deobfuscation JavaScript function", e3);
        }
    }

    @Nonnull
    public static String getUrlWithThrottlingParameterDeobfuscated(@Nonnull String str, @Nonnull String str2) throws Exception {
        String throttlingParameterFromStreamingUrl = YoutubeThrottlingParameterUtils.getThrottlingParameterFromStreamingUrl(str2);
        if (throttlingParameterFromStreamingUrl == null) {
            return str2;
        }
        Map<String, String> map = CACHED_THROTTLING_PARAMETERS;
        CharSequence charSequence = (String) map.get(throttlingParameterFromStreamingUrl);
        if (charSequence != null) {
            return str2.replace(throttlingParameterFromStreamingUrl, charSequence);
        }
        extractJavaScriptCodeIfNeeded(str);
        ParsingException parsingException = throttlingDeobfFuncExtractionEx;
        if (parsingException != null) {
            throw parsingException;
        }
        if (cachedThrottlingDeobfuscationFunction == null) {
            try {
                String deobfuscationFunctionName = YoutubeThrottlingParameterUtils.getDeobfuscationFunctionName(cachedJavaScriptPlayerCode);
                cachedThrottlingDeobfuscationFunctionName = deobfuscationFunctionName;
                cachedThrottlingDeobfuscationFunction = YoutubeThrottlingParameterUtils.getDeobfuscationFunction(cachedJavaScriptPlayerCode, deobfuscationFunctionName);
            } catch (ParsingException e) {
                throttlingDeobfFuncExtractionEx = e;
                throw e;
            } catch (Exception e2) {
                throttlingDeobfFuncExtractionEx = new ParsingException("Could not get throttling parameter deobfuscation JavaScript function", e2);
                throw e2;
            }
        }
        try {
            String strRun = JavaScript.run(cachedThrottlingDeobfuscationFunction, cachedThrottlingDeobfuscationFunctionName, throttlingParameterFromStreamingUrl);
            if (Utils.isNullOrEmpty(strRun)) {
                throw new IllegalStateException("Extracted n-parameter is empty");
            }
            map.put(throttlingParameterFromStreamingUrl, strRun);
            return str2.replace(throttlingParameterFromStreamingUrl, strRun);
        } catch (Exception e3) {
            throw new ParsingException("Could not run throttling parameter deobfuscation JavaScript function", e3);
        }
    }

    public static int getThrottlingParametersCacheSize() {
        return CACHED_THROTTLING_PARAMETERS.size();
    }

    public static void clearAllCaches() {
        cachedJavaScriptPlayerCode = null;
        cachedSignatureDeobfuscationFunction = null;
        cachedThrottlingDeobfuscationFunctionName = null;
        cachedThrottlingDeobfuscationFunction = null;
        cachedSignatureTimestamp = null;
        clearThrottlingParametersCache();
        throttlingDeobfFuncExtractionEx = null;
        sigDeobFuncExtractionEx = null;
        sigTimestampExtractionEx = null;
    }

    public static void clearThrottlingParametersCache() {
        CACHED_THROTTLING_PARAMETERS.clear();
    }

    private static void extractJavaScriptCodeIfNeeded(@Nonnull String str) throws ParsingException {
        if (cachedJavaScriptPlayerCode == null) {
            cachedJavaScriptPlayerCode = YoutubeJavaScriptExtractor.extractJavaScriptPlayerCode(str);
        }
    }
}
