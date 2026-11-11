package org.json;

/* JADX INFO: loaded from: classes8.dex */
public class JSONParserConfiguration extends ParserConfiguration {
    private boolean overwriteDuplicateKey = false;
    private boolean strictMode;
    private boolean useNativeNulls;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.json.ParserConfiguration
    public JSONParserConfiguration clone() {
        JSONParserConfiguration jSONParserConfiguration = new JSONParserConfiguration();
        jSONParserConfiguration.overwriteDuplicateKey = this.overwriteDuplicateKey;
        jSONParserConfiguration.strictMode = this.strictMode;
        jSONParserConfiguration.maxNestingDepth = this.maxNestingDepth;
        jSONParserConfiguration.keepStrings = this.keepStrings;
        jSONParserConfiguration.useNativeNulls = this.useNativeNulls;
        return jSONParserConfiguration;
    }

    @Override // org.json.ParserConfiguration
    public JSONParserConfiguration withMaxNestingDepth(int i) {
        JSONParserConfiguration jSONParserConfigurationClone = clone();
        jSONParserConfigurationClone.maxNestingDepth = i;
        return jSONParserConfigurationClone;
    }

    public JSONParserConfiguration withOverwriteDuplicateKey(boolean z) {
        JSONParserConfiguration jSONParserConfigurationClone = clone();
        jSONParserConfigurationClone.overwriteDuplicateKey = z;
        return jSONParserConfigurationClone;
    }

    public JSONParserConfiguration withUseNativeNulls(boolean z) {
        JSONParserConfiguration jSONParserConfigurationClone = clone();
        jSONParserConfigurationClone.useNativeNulls = z;
        return jSONParserConfigurationClone;
    }

    public JSONParserConfiguration withStrictMode() {
        return withStrictMode(true);
    }

    public JSONParserConfiguration withStrictMode(boolean z) {
        JSONParserConfiguration jSONParserConfigurationClone = clone();
        jSONParserConfigurationClone.strictMode = z;
        return jSONParserConfigurationClone;
    }

    public boolean isOverwriteDuplicateKey() {
        return this.overwriteDuplicateKey;
    }

    public boolean isUseNativeNulls() {
        return this.useNativeNulls;
    }

    public boolean isStrictMode() {
        return this.strictMode;
    }
}
