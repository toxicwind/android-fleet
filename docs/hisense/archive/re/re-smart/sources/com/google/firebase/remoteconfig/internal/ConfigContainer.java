package com.google.firebase.remoteconfig.internal;

import e.b.a;
import e.b.b;
import e.b.d;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ConfigContainer {
    public static final String ABT_EXPERIMENTS_KEY = "abt_experiments_key";
    public static final String CONFIGS_KEY = "configs_key";
    public static final Date DEFAULTS_FETCH_TIME = new Date(0);
    public static final String FETCH_TIME_KEY = "fetch_time_key";
    public static final String PERSONALIZATION_METADATA_KEY = "personalization_metadata_key";
    public a abtExperiments;
    public d configsJson;
    public d containerJson;
    public Date fetchTime;
    public d personalizationMetadata;

    public static class Builder {
        public a builderAbtExperiments;
        public d builderConfigsJson;
        public Date builderFetchTime;
        public d builderPersonalizationMetadata;

        public ConfigContainer build() {
            return new ConfigContainer(this.builderConfigsJson, this.builderFetchTime, this.builderAbtExperiments, this.builderPersonalizationMetadata);
        }

        public Builder replaceConfigsWith(Map<String, String> map) {
            this.builderConfigsJson = new d((Map<?, ?>) map);
            return this;
        }

        public Builder withAbtExperiments(a aVar) {
            try {
                this.builderAbtExperiments = new a(aVar.toString());
            } catch (b unused) {
            }
            return this;
        }

        public Builder withFetchTime(Date date) {
            this.builderFetchTime = date;
            return this;
        }

        public Builder withPersonalizationMetadata(d dVar) {
            try {
                this.builderPersonalizationMetadata = new d(dVar.toString());
            } catch (b unused) {
            }
            return this;
        }

        public Builder() {
            this.builderConfigsJson = new d();
            this.builderFetchTime = ConfigContainer.DEFAULTS_FETCH_TIME;
            this.builderAbtExperiments = new a();
            this.builderPersonalizationMetadata = new d();
        }

        public Builder replaceConfigsWith(d dVar) {
            try {
                this.builderConfigsJson = new d(dVar.toString());
            } catch (b unused) {
            }
            return this;
        }

        public Builder(ConfigContainer configContainer) {
            this.builderConfigsJson = configContainer.getConfigs();
            this.builderFetchTime = configContainer.getFetchTime();
            this.builderAbtExperiments = configContainer.getAbtExperiments();
            this.builderPersonalizationMetadata = configContainer.getPersonalizationMetadata();
        }
    }

    public static ConfigContainer copyOf(d dVar) {
        d dVarL = dVar.l(PERSONALIZATION_METADATA_KEY);
        if (dVarL == null) {
            dVarL = new d();
        }
        return new ConfigContainer(dVar.f(CONFIGS_KEY), new Date(dVar.g(FETCH_TIME_KEY)), dVar.e(ABT_EXPERIMENTS_KEY), dVarL);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ConfigContainer) {
            return this.containerJson.toString().equals(((ConfigContainer) obj).toString());
        }
        return false;
    }

    public a getAbtExperiments() {
        return this.abtExperiments;
    }

    public d getConfigs() {
        return this.configsJson;
    }

    public Date getFetchTime() {
        return this.fetchTime;
    }

    public d getPersonalizationMetadata() {
        return this.personalizationMetadata;
    }

    public int hashCode() {
        return this.containerJson.hashCode();
    }

    public String toString() {
        return this.containerJson.toString();
    }

    public ConfigContainer(d dVar, Date date, a aVar, d dVar2) {
        d dVar3 = new d();
        dVar3.a(CONFIGS_KEY, dVar);
        dVar3.b(FETCH_TIME_KEY, date.getTime());
        dVar3.a(ABT_EXPERIMENTS_KEY, aVar);
        dVar3.a(PERSONALIZATION_METADATA_KEY, dVar2);
        this.configsJson = dVar;
        this.fetchTime = date;
        this.abtExperiments = aVar;
        this.personalizationMetadata = dVar2;
        this.containerJson = dVar3;
    }

    public static Builder newBuilder(ConfigContainer configContainer) {
        return new Builder(configContainer);
    }
}
