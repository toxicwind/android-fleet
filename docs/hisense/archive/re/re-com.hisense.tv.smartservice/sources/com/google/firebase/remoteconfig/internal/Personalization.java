package com.google.firebase.remoteconfig.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import e.b.d;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Personalization {
    public static final String ANALYTICS_ORIGIN_PERSONALIZATION = "fp";
    public static final String ARM_INDEX = "armIndex";
    public static final String CHOICE_ID = "choiceId";
    public static final String EXTERNAL_ARM_INDEX_PARAM = "arm_index";
    public static final String EXTERNAL_ARM_VALUE_PARAM = "arm_value";
    public static final String EXTERNAL_EVENT = "personalization_assignment";
    public static final String EXTERNAL_GROUP_PARAM = "group";
    public static final String EXTERNAL_PERSONALIZATION_ID_PARAM = "personalization_id";
    public static final String EXTERNAL_RC_PARAMETER_PARAM = "arm_key";
    public static final String GROUP = "group";
    public static final String INTERNAL_CHOICE_ID_PARAM = "_fpid";
    public static final String INTERNAL_EVENT = "_fpc";
    public static final String PERSONALIZATION_ID = "personalizationId";
    public final Provider<AnalyticsConnector> analyticsConnector;
    public final Map<String, String> loggedChoiceIds = Collections.synchronizedMap(new HashMap());

    public Personalization(Provider<AnalyticsConnector> provider) {
        this.analyticsConnector = provider;
    }

    public void logArmActive(@NonNull String str, @NonNull ConfigContainer configContainer) {
        d dVarL;
        AnalyticsConnector analyticsConnector = this.analyticsConnector.get();
        if (analyticsConnector == null) {
            return;
        }
        d personalizationMetadata = configContainer.getPersonalizationMetadata();
        if (personalizationMetadata.b() < 1) {
            return;
        }
        d configs = configContainer.getConfigs();
        if (configs.b() >= 1 && (dVarL = personalizationMetadata.l(str)) != null) {
            String strA = dVarL.a(CHOICE_ID, "");
            if (strA.isEmpty()) {
                return;
            }
            synchronized (this.loggedChoiceIds) {
                if (strA.equals(this.loggedChoiceIds.get(str))) {
                    return;
                }
                this.loggedChoiceIds.put(str, strA);
                Bundle bundle = new Bundle();
                bundle.putString(EXTERNAL_RC_PARAMETER_PARAM, str);
                bundle.putString(EXTERNAL_ARM_VALUE_PARAM, configs.a(str, ""));
                bundle.putString(EXTERNAL_PERSONALIZATION_ID_PARAM, dVarL.a(PERSONALIZATION_ID, ""));
                bundle.putInt(EXTERNAL_ARM_INDEX_PARAM, dVarL.a(ARM_INDEX, -1));
                bundle.putString("group", dVarL.a("group", ""));
                analyticsConnector.logEvent(ANALYTICS_ORIGIN_PERSONALIZATION, EXTERNAL_EVENT, bundle);
                Bundle bundle2 = new Bundle();
                bundle2.putString(INTERNAL_CHOICE_ID_PARAM, strA);
                analyticsConnector.logEvent(ANALYTICS_ORIGIN_PERSONALIZATION, INTERNAL_EVENT, bundle2);
            }
        }
    }
}
