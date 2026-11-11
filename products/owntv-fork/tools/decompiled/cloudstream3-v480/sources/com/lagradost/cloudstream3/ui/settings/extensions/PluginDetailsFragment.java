package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import coil3.Image;
import coil3.request.ImageRequest;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentPluginDetailsBinding;
import com.lagradost.cloudstream3.plugins.BasePlugin;
import com.lagradost.cloudstream3.plugins.Plugin;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.plugins.SitePlugin;
import com.lagradost.cloudstream3.plugins.VotingApi;
import com.lagradost.cloudstream3.ui.BaseBottomSheetDialogFragment;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PluginDetailsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginDetailsFragment;", "Lcom/lagradost/cloudstream3/ui/BaseBottomSheetDialogFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentPluginDetailsBinding;", "data", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;", "<init>", "(Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;)V", "getData", "()Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "updateVoting", ES6Iterator.VALUE_PROPERTY, "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginDetailsFragment extends BaseBottomSheetDialogFragment<FragmentPluginDetailsBinding> {
    private final PluginViewData data;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int iconSizeExact = UIHelper.INSTANCE.getToPx(50);
    private static final Lazy<Integer> iconSize$delegate = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Integer.valueOf(PluginDetailsFragment.INSTANCE.findClosestBase2(PluginDetailsFragment.iconSizeExact, 16, 512));
        }
    });

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: PluginDetailsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentPluginDetailsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentPluginDetailsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentPluginDetailsBinding;", 0);
        }

        public final FragmentPluginDetailsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentPluginDetailsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentPluginDetailsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public final PluginViewData getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginDetailsFragment(PluginViewData data) {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* JADX INFO: compiled from: PluginDetailsFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005H\u0082\u0010R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginDetailsFragment$Companion;", "", "<init>", "()V", "findClosestBase2", "", TypedValues.AttributesType.S_TARGET, "current", "max", "iconSizeExact", "iconSize", "getIconSize", "()I", "iconSize$delegate", "Lkotlin/Lazy;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int findClosestBase2(int target, int current, int max) {
            while (current < max) {
                if (current >= target) {
                    return current;
                }
                current *= 2;
            }
            return max;
        }

        private Companion() {
        }

        static /* synthetic */ int findClosestBase2$default(Companion companion, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 2) != 0) {
                i2 = 16;
            }
            if ((i4 & 4) != 0) {
                i3 = 512;
            }
            return companion.findClosestBase2(i, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getIconSize() {
            return ((Number) PluginDetailsFragment.iconSize$delegate.getValue()).intValue();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseBottomSheetDialogFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentPluginDetailsBinding binding) {
        String string;
        String strReplace$default;
        Intrinsics.checkNotNullParameter(binding, "binding");
        final SitePlugin plugin = this.data.getPluginWrapper().getPlugin();
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        ImageView pluginIcon = binding.pluginIcon;
        Intrinsics.checkNotNullExpressionValue(pluginIcon, "pluginIcon");
        String iconUrl = plugin.getIconUrl();
        ImageLoader.loadImage$default(imageLoader, pluginIcon, (iconUrl == null || (strReplace$default = StringsKt.replace$default(iconUrl, "%size%", String.valueOf(INSTANCE.getIconSize()), false, 4, (Object) null)) == null) ? null : StringsKt.replace$default(strReplace$default, "%exact_size%", String.valueOf(iconSizeExact), false, 4, (Object) null), (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginDetailsFragment.onBindingCreated$lambda$0$0(this.f$0, (ImageRequest.Builder) obj);
            }
        }, 2, (Object) null);
        binding.pluginName.setText(StringsKt.removeSuffix(plugin.getName(), (CharSequence) "Provider"));
        binding.pluginVersion.setText(String.valueOf(plugin.getVersion()));
        TextView textView = binding.pluginDescription;
        String description = plugin.getDescription();
        if (description == null) {
            description = getString(R.string.no_data);
            Intrinsics.checkNotNullExpressionValue(description, "getString(...)");
        }
        textView.setText(description);
        binding.pluginSize.setText(plugin.getFileSize() == null ? getString(R.string.no_data) : Formatter.formatFileSize(getContext(), plugin.getFileSize().longValue()));
        binding.pluginAuthor.setText(plugin.getAuthors().isEmpty() ? getString(R.string.no_data) : CollectionsKt.joinToString$default(plugin.getAuthors(), ", ", null, null, 0, null, null, 62, null));
        binding.pluginStatus.setText(getResources().getStringArray(R.array.extension_statuses)[plugin.getStatus()]);
        TextView textView2 = binding.pluginTypes;
        List<String> tvTypes = plugin.getTvTypes();
        textView2.setText((tvTypes == null || tvTypes.isEmpty()) ? getString(R.string.no_data) : CollectionsKt.joinToString$default(plugin.getTvTypes(), ", ", null, null, 0, null, null, 62, null));
        TextView textView3 = binding.pluginLang;
        if (plugin.getLanguage() == null) {
            string = getString(R.string.no_data);
        } else {
            String nameNextToFlagEmoji$default = SubtitleHelper.getNameNextToFlagEmoji$default(SubtitleHelper.INSTANCE, plugin.getLanguage(), null, 2, null);
            if (nameNextToFlagEmoji$default == null) {
                nameNextToFlagEmoji$default = plugin.getLanguage();
            }
            string = nameNextToFlagEmoji$default;
        }
        textView3.setText(string);
        binding.githubBtn.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginDetailsFragment.onBindingCreated$lambda$0$1(plugin, view);
            }
        });
        if (!VotingApi.INSTANCE.canVote(plugin)) {
            binding.upvote.setAlpha(0.6f);
        }
        if (this.data.isDownloaded()) {
            BasePlugin basePlugin = PluginManager.INSTANCE.getUrlPlugins().get(plugin.getUrl());
            if (basePlugin == null) {
                basePlugin = PluginManager.INSTANCE.getPlugins().get(plugin.getUrl());
            }
            final Plugin plugin2 = basePlugin instanceof Plugin ? (Plugin) basePlugin : null;
            if ((plugin2 != null ? plugin2.getOpenSettings() : null) != null && getContext() != null) {
                ImageView actionSettings = binding.actionSettings;
                Intrinsics.checkNotNullExpressionValue(actionSettings, "actionSettings");
                actionSettings.setVisibility(0);
                binding.actionSettings.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PluginDetailsFragment.onBindingCreated$lambda$0$2(plugin2, this, plugin, view);
                    }
                });
            } else {
                ImageView actionSettings2 = binding.actionSettings;
                Intrinsics.checkNotNullExpressionValue(actionSettings2, "actionSettings");
                actionSettings2.setVisibility(8);
            }
        } else {
            ImageView actionSettings3 = binding.actionSettings;
            Intrinsics.checkNotNullExpressionValue(actionSettings3, "actionSettings");
            actionSettings3.setVisibility(8);
        }
        binding.upvote.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginDetailsFragment.onBindingCreated$lambda$0$3(binding, plugin, this, view);
            }
        });
        Coroutines.INSTANCE.ioSafe(binding, new PluginDetailsFragment$onBindingCreated$1$5(plugin, this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$0(final PluginDetailsFragment pluginDetailsFragment, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginDetailsFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginDetailsFragment.onBindingCreated$lambda$0$0$0(this.f$0, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindingCreated$lambda$0$0$0(PluginDetailsFragment pluginDetailsFragment, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = pluginDetailsFragment.getContext();
        if (context == null) {
            return null;
        }
        return ImageUtilKt.getImageFromDrawable(context, R.drawable.ic_baseline_extension_24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1(SitePlugin sitePlugin, View view) {
        if (sitePlugin.getRepositoryUrl() != null) {
            CloudStreamApp.Companion.openBrowser$default(CloudStreamApp.INSTANCE, sitePlugin.getRepositoryUrl(), false, null, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$2(Plugin plugin, PluginDetailsFragment pluginDetailsFragment, SitePlugin sitePlugin, View view) {
        try {
            Function1<Context, Unit> openSettings = plugin.getOpenSettings();
            Intrinsics.checkNotNull(openSettings);
            Context contextRequireContext = pluginDetailsFragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
            openSettings.invoke(contextRequireContext);
        } catch (Throwable th) {
            Log.e("PluginAdapter", "Failed to open " + sitePlugin.getName() + " settings: " + Log.getStackTraceString(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$3(FragmentPluginDetailsBinding fragmentPluginDetailsBinding, SitePlugin sitePlugin, PluginDetailsFragment pluginDetailsFragment, View view) {
        Coroutines.INSTANCE.ioSafe(fragmentPluginDetailsBinding, new PluginDetailsFragment$onBindingCreated$1$4$1(sitePlugin, pluginDetailsFragment, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateVoting(int value) {
        SitePlugin plugin = this.data.getPluginWrapper().getPlugin();
        FragmentPluginDetailsBinding binding = getBinding();
        if (binding != null) {
            binding.pluginVotes.setText(String.valueOf(value));
            if (VotingApi.INSTANCE.hasVoted(plugin)) {
                ImageView imageView = binding.upvote;
                Context context = getContext();
                imageView.setImageTintList(ColorStateList.valueOf(context != null ? UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary) : R.color.colorPrimary));
            } else {
                ImageView imageView2 = binding.upvote;
                Context context2 = getContext();
                imageView2.setImageTintList(ColorStateList.valueOf(context2 != null ? UIHelper.INSTANCE.colorFromAttribute(context2, com.google.android.material.R.attr.colorOnSurface) : R.color.white));
            }
        }
    }
}
