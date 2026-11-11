package com.lagradost.cloudstream3.ui.result;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import coil3.request.ErrorResult;
import coil3.request.ImageRequest;
import coil3.request.SuccessResult;
import coil3.util.CoilUtils;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.UiImage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ResultFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001*B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0014J2\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012J\u0017\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u001bJ6\u0010 \u001a\u00020\u00192\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\f\u0010'\u001a\u0004\u0018\u00010(*\u00020)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultFragment;", "", "<init>", "()V", "URL_BUNDLE", "", "NAME_BUNDLE", "API_NAME_BUNDLE", "SEASON_BUNDLE", "EPISODE_BUNDLE", "START_ACTION_BUNDLE", "START_VALUE_BUNDLE", "RESTART_BUNDLE", "newInstance", "Landroid/os/Bundle;", "card", "Lcom/lagradost/cloudstream3/SearchResponse;", ResultFragment.START_ACTION_BUNDLE, "", ResultFragment.START_VALUE_BUNDLE, "(Lcom/lagradost/cloudstream3/SearchResponse;ILjava/lang/Integer;)Landroid/os/Bundle;", "url", ResultFragment.API_NAME_BUNDLE, "name", "updateUI", "", "id", "(Ljava/lang/Integer;)V", "updateUIEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "getUpdateUIEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "bindLogo", "headers", "", "logoView", "Landroid/widget/ImageView;", "titleView", "Landroid/widget/TextView;", "getStoredData", "Lcom/lagradost/cloudstream3/ui/result/ResultFragment$StoredData;", "Landroidx/fragment/app/Fragment;", "StoredData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultFragment {
    private static final String API_NAME_BUNDLE = "apiName";
    private static final String EPISODE_BUNDLE = "episode";
    private static final String NAME_BUNDLE = "name";
    private static final String RESTART_BUNDLE = "restart";
    private static final String SEASON_BUNDLE = "season";
    private static final String START_ACTION_BUNDLE = "startAction";
    private static final String START_VALUE_BUNDLE = "startValue";
    private static final String URL_BUNDLE = "url";
    public static final ResultFragment INSTANCE = new ResultFragment();
    private static final Event<Integer> updateUIEvent = new Event<>();

    private ResultFragment() {
    }

    public static /* synthetic */ Bundle newInstance$default(ResultFragment resultFragment, SearchResponse searchResponse, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return resultFragment.newInstance(searchResponse, i, num);
    }

    public final Bundle newInstance(SearchResponse card, int startAction, Integer startValue) {
        Intrinsics.checkNotNullParameter(card, "card");
        Bundle bundle = new Bundle();
        bundle.putString("url", card.getUrl());
        bundle.putString(API_NAME_BUNDLE, card.getApiName());
        bundle.putString("name", card.getName());
        if (card instanceof DataStoreHelper.ResumeWatchingResult) {
            DataStoreHelper.ResumeWatchingResult resumeWatchingResult = (DataStoreHelper.ResumeWatchingResult) card;
            if (resumeWatchingResult.getSeason() != null) {
                bundle.putInt(SEASON_BUNDLE, resumeWatchingResult.getSeason().intValue());
            }
            if (resumeWatchingResult.getEpisode() != null) {
                bundle.putInt(EPISODE_BUNDLE, resumeWatchingResult.getEpisode().intValue());
            }
        }
        bundle.putInt(START_ACTION_BUNDLE, startAction);
        if (startValue != null) {
            bundle.putInt(START_VALUE_BUNDLE, startValue.intValue());
        }
        bundle.putBoolean(RESTART_BUNDLE, true);
        return bundle;
    }

    public static /* synthetic */ Bundle newInstance$default(ResultFragment resultFragment, String str, String str2, String str3, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i = 0;
        }
        if ((i3 & 16) != 0) {
            i2 = 0;
        }
        return resultFragment.newInstance(str, str2, str3, i, i2);
    }

    public final Bundle newInstance(String url, String apiName, String name, int startAction, int startValue) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(name, "name");
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString(API_NAME_BUNDLE, apiName);
        bundle.putString("name", name);
        bundle.putInt(START_ACTION_BUNDLE, startAction);
        bundle.putInt(START_VALUE_BUNDLE, startValue);
        bundle.putBoolean(RESTART_BUNDLE, true);
        return bundle;
    }

    public static /* synthetic */ void updateUI$default(ResultFragment resultFragment, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        resultFragment.updateUI(num);
    }

    public final void updateUI(Integer id) {
        updateUIEvent.invoke(id);
    }

    public final Event<Integer> getUpdateUIEvent() {
        return updateUIEvent;
    }

    /* JADX INFO: compiled from: ResultFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J[\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0007HÆ\u0001J\u0014\u0010'\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020\rHÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultFragment$StoredData;", "", "url", "", ResultFragment.API_NAME_BUNDLE, "name", "showFillers", "", "dubStatus", "Lcom/lagradost/cloudstream3/DubStatus;", "start", "Lcom/lagradost/cloudstream3/ui/result/AutoResume;", "playerAction", "", ResultFragment.RESTART_BUNDLE, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/lagradost/cloudstream3/DubStatus;Lcom/lagradost/cloudstream3/ui/result/AutoResume;IZ)V", "getUrl", "()Ljava/lang/String;", "getApiName", "getName", "getShowFillers", "()Z", "getDubStatus", "()Lcom/lagradost/cloudstream3/DubStatus;", "getStart", "()Lcom/lagradost/cloudstream3/ui/result/AutoResume;", "getPlayerAction", "()I", "getRestart", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StoredData {
        private final String apiName;
        private final DubStatus dubStatus;
        private final String name;
        private final int playerAction;
        private final boolean restart;
        private final boolean showFillers;
        private final AutoResume start;
        private final String url;

        public static /* synthetic */ StoredData copy$default(StoredData storedData, String str, String str2, String str3, boolean z, DubStatus dubStatus, AutoResume autoResume, int i, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = storedData.url;
            }
            if ((i2 & 2) != 0) {
                str2 = storedData.apiName;
            }
            if ((i2 & 4) != 0) {
                str3 = storedData.name;
            }
            if ((i2 & 8) != 0) {
                z = storedData.showFillers;
            }
            if ((i2 & 16) != 0) {
                dubStatus = storedData.dubStatus;
            }
            if ((i2 & 32) != 0) {
                autoResume = storedData.start;
            }
            if ((i2 & 64) != 0) {
                i = storedData.playerAction;
            }
            if ((i2 & 128) != 0) {
                z2 = storedData.restart;
            }
            int i3 = i;
            boolean z3 = z2;
            DubStatus dubStatus2 = dubStatus;
            AutoResume autoResume2 = autoResume;
            return storedData.copy(str, str2, str3, z, dubStatus2, autoResume2, i3, z3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getShowFillers() {
            return this.showFillers;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final DubStatus getDubStatus() {
            return this.dubStatus;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final AutoResume getStart() {
            return this.start;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getPlayerAction() {
            return this.playerAction;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getRestart() {
            return this.restart;
        }

        public final StoredData copy(String url, String apiName, String name, boolean showFillers, DubStatus dubStatus, AutoResume start, int playerAction, boolean restart) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(dubStatus, "dubStatus");
            return new StoredData(url, apiName, name, showFillers, dubStatus, start, playerAction, restart);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StoredData)) {
                return false;
            }
            StoredData storedData = (StoredData) other;
            return Intrinsics.areEqual(this.url, storedData.url) && Intrinsics.areEqual(this.apiName, storedData.apiName) && Intrinsics.areEqual(this.name, storedData.name) && this.showFillers == storedData.showFillers && this.dubStatus == storedData.dubStatus && Intrinsics.areEqual(this.start, storedData.start) && this.playerAction == storedData.playerAction && this.restart == storedData.restart;
        }

        public int hashCode() {
            int iHashCode = ((((((((this.url.hashCode() * 31) + this.apiName.hashCode()) * 31) + this.name.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.showFillers)) * 31) + this.dubStatus.hashCode()) * 31;
            AutoResume autoResume = this.start;
            return ((((iHashCode + (autoResume == null ? 0 : autoResume.hashCode())) * 31) + this.playerAction) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.restart);
        }

        public String toString() {
            return "StoredData(url=" + this.url + ", apiName=" + this.apiName + ", name=" + this.name + ", showFillers=" + this.showFillers + ", dubStatus=" + this.dubStatus + ", start=" + this.start + ", playerAction=" + this.playerAction + ", restart=" + this.restart + ')';
        }

        public StoredData(String url, String apiName, String name, boolean z, DubStatus dubStatus, AutoResume autoResume, int i, boolean z2) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(dubStatus, "dubStatus");
            this.url = url;
            this.apiName = apiName;
            this.name = name;
            this.showFillers = z;
            this.dubStatus = dubStatus;
            this.start = autoResume;
            this.playerAction = i;
            this.restart = z2;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getApiName() {
            return this.apiName;
        }

        public final String getName() {
            return this.name;
        }

        public final boolean getShowFillers() {
            return this.showFillers;
        }

        public final DubStatus getDubStatus() {
            return this.dubStatus;
        }

        public final AutoResume getStart() {
            return this.start;
        }

        public final int getPlayerAction() {
            return this.playerAction;
        }

        public final boolean getRestart() {
            return this.restart;
        }
    }

    public final StoredData getStoredData(Fragment fragment) {
        String string;
        Bundle arguments;
        String string2;
        Bundle arguments2;
        String string3;
        AutoResume autoResume;
        Bundle arguments3;
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity context = fragment.getContext();
        if (context == null) {
            FragmentActivity activity = fragment.getActivity();
            if (activity == null) {
                return null;
            }
            context = activity;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle arguments4 = fragment.getArguments();
        if (arguments4 == null || (string = arguments4.getString("url")) == null || (arguments = fragment.getArguments()) == null || (string2 = arguments.getString(API_NAME_BUNDLE)) == null || (arguments2 = fragment.getArguments()) == null || (string3 = arguments2.getString("name")) == null) {
            return null;
        }
        boolean z = defaultSharedPreferences.getBoolean(context.getString(R.string.show_fillers_key), false);
        DubStatus dubStatus = AppContextUtils.INSTANCE.getApiDubstatusSettings(context).contains(DubStatus.Dubbed) ? DubStatus.Dubbed : DubStatus.Subbed;
        Bundle arguments5 = fragment.getArguments();
        Integer numValueOf = arguments5 != null ? Integer.valueOf(arguments5.getInt(START_ACTION_BUNDLE)) : null;
        int playerAction = EpisodeAdapter.INSTANCE.getPlayerAction(context);
        Bundle arguments6 = fragment.getArguments();
        boolean z2 = arguments6 != null ? arguments6.getBoolean(RESTART_BUNDLE) : false;
        if (z2 && (arguments3 = fragment.getArguments()) != null) {
            arguments3.putBoolean(RESTART_BUNDLE, false);
        }
        if (numValueOf != null) {
            int iIntValue = numValueOf.intValue();
            Bundle arguments7 = fragment.getArguments();
            Integer numValueOf2 = arguments7 != null ? Integer.valueOf(arguments7.getInt(START_VALUE_BUNDLE)) : null;
            Bundle arguments8 = fragment.getArguments();
            Integer numValueOf3 = arguments8 != null ? Integer.valueOf(arguments8.getInt(EPISODE_BUNDLE)) : null;
            Bundle arguments9 = fragment.getArguments();
            Integer numValueOf4 = arguments9 != null ? Integer.valueOf(arguments9.getInt(SEASON_BUNDLE)) : null;
            Bundle arguments10 = fragment.getArguments();
            if (arguments10 != null) {
                arguments10.remove(START_VALUE_BUNDLE);
            }
            Bundle arguments11 = fragment.getArguments();
            if (arguments11 != null) {
                arguments11.remove(START_ACTION_BUNDLE);
            }
            autoResume = new AutoResume(numValueOf4, numValueOf3, numValueOf2, iIntValue);
        } else {
            autoResume = null;
        }
        return new StoredData(string, string2, string3, z, dubStatus, autoResume, playerAction, z2);
    }

    public final void bindLogo(String url, Map<String, String> headers, final ImageView logoView, final TextView titleView) {
        Intrinsics.checkNotNullParameter(logoView, "logoView");
        Intrinsics.checkNotNullParameter(titleView, "titleView");
        ImageView imageView = logoView;
        CoilUtils.dispose(imageView);
        String str = url;
        if (str == null || StringsKt.isBlank(str)) {
            imageView.setVisibility(8);
            titleView.setVisibility(0);
        } else {
            imageView.setVisibility(0);
            titleView.setVisibility(8);
            ImageLoader.INSTANCE.loadImage(logoView, new UiImage.Image(url, headers), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ResultFragment.bindLogo$lambda$0(logoView, titleView, (ImageRequest.Builder) obj);
                }
            });
        }
    }

    static final Unit bindLogo$lambda$0(final ImageView imageView, final TextView textView, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.listener(new ImageRequest.Listener() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragment$bindLogo$lambda$0$$inlined$listener$default$1
            @Override // coil3.request.ImageRequest.Listener
            public void onCancel(ImageRequest request) {
            }

            @Override // coil3.request.ImageRequest.Listener
            public void onStart(ImageRequest request) {
            }

            @Override // coil3.request.ImageRequest.Listener
            public void onError(ImageRequest request, ErrorResult result) {
                imageView.setVisibility(8);
                textView.setVisibility(0);
            }

            @Override // coil3.request.ImageRequest.Listener
            public void onSuccess(ImageRequest request, SuccessResult result) {
                imageView.setVisibility(0);
                textView.setVisibility(8);
            }
        });
        return Unit.INSTANCE;
    }
}
