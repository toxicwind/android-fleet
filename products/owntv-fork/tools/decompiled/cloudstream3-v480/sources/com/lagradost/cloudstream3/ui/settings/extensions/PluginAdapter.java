package com.lagradost.cloudstream3.ui.settings.extensions;

import android.app.Activity;
import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import coil3.request.ImageRequest;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.RepositoryItemBinding;
import com.lagradost.cloudstream3.plugins.BasePlugin;
import com.lagradost.cloudstream3.plugins.Plugin;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.plugins.SitePlugin;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import io.ktor.util.date.GMTDateParser;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PluginAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J&\u0010\u0016\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0017R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;", "showRepositoryNames", "", "iconClickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "", "<init>", "(ZLkotlin/jvm/functions/Function1;)V", "getShowRepositoryNames", "()Z", "getIconClickCallback", "()Lkotlin/jvm/functions/Function1;", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginAdapter extends NoStateAdapter<PluginViewData> {
    private final Function1<PluginWrapper, Unit> iconClickCallback;
    private final boolean showRepositoryNames;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return PluginAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });
    private static final int iconSizeExact = UIHelper.INSTANCE.getToPx(32);
    private static final Lazy<Integer> iconSize$delegate = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Integer.valueOf(PluginAdapter.INSTANCE.findClosestBase2(PluginAdapter.iconSizeExact, 16, 512));
        }
    });

    public /* synthetic */ PluginAdapter(boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, function1);
    }

    public final Function1<PluginWrapper, Unit> getIconClickCallback() {
        return this.iconClickCallback;
    }

    public final boolean getShowRepositoryNames() {
        return this.showRepositoryNames;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PluginAdapter(boolean z, Function1<? super PluginWrapper, Unit> iconClickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(PluginAdapter._init_$lambda$0((PluginViewData) obj, (PluginViewData) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(iconClickCallback, "iconClickCallback");
        this.showRepositoryNames = z;
        this.iconClickCallback = iconClickCallback;
    }

    static final boolean _init_$lambda$0(PluginViewData a, PluginViewData b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getPluginWrapper().getPlugin().getInternalName(), b.getPluginWrapper().getPlugin().getInternalName()) && Intrinsics.areEqual(a.getPluginWrapper().getRepositoryData().getUrl(), b.getPluginWrapper().getRepositoryData().getUrl());
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        RepositoryItemBinding repositoryItemBindingBind = RepositoryItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(Globals.INSTANCE.isLayout(2) ? R.layout.repository_item_tv : R.layout.repository_item, parent, false));
        Intrinsics.checkNotNullExpressionValue(repositoryItemBindingBind, "bind(...)");
        return new RepositoryViewHolderState(repositoryItemBindingBind);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof RepositoryViewHolderState) {
            RepositoryViewHolderState repositoryViewHolderState = (RepositoryViewHolderState) holder;
            repositoryViewHolderState.setRecycleCount(repositoryViewHolderState.getRecycleCount() + 1);
        }
        ViewBinding view = holder.getView();
        if (view instanceof RepositoryItemBinding) {
            BaseAdapter.INSTANCE.clearImage(((RepositoryItemBinding) view).entryIcon);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final PluginViewData item, int position) {
        int i;
        boolean z;
        UiText uiTextTxt;
        String strReplace$default;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        RepositoryItemBinding repositoryItemBinding = view instanceof RepositoryItemBinding ? (RepositoryItemBinding) view : null;
        if (repositoryItemBinding == null) {
            return;
        }
        final View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        SitePlugin plugin = item.getPluginWrapper().getPlugin();
        boolean z2 = plugin.getStatus() == 0;
        final String strRemoveSuffix = StringsKt.removeSuffix(plugin.getName(), (CharSequence) "Provider");
        float f = z2 ? 0.6f : 1.0f;
        final boolean z3 = !StringsKt.startsWith$default(item.getPluginWrapper().getPlugin().getUrl(), "http", false, 2, (Object) null);
        repositoryItemBinding.mainText.setAlpha(f);
        repositoryItemBinding.subText.setAlpha(f);
        MaterialButton repositoryNameText = repositoryItemBinding.repositoryNameText;
        Intrinsics.checkNotNullExpressionValue(repositoryNameText, "repositoryNameText");
        repositoryNameText.setVisibility(this.showRepositoryNames ? 0 : 8);
        if (this.showRepositoryNames) {
            repositoryItemBinding.repositoryNameText.setText(item.getPluginWrapper().getRepositoryData().getName());
        } else {
            repositoryItemBinding.repositoryNameText.setText("");
        }
        if (item.isDownloaded()) {
            i = R.drawable.ic_baseline_delete_outline_24;
        } else {
            i = R.drawable.netflix_download;
        }
        TextView nsfwMarker = repositoryItemBinding.nsfwMarker;
        Intrinsics.checkNotNullExpressionValue(nsfwMarker, "nsfwMarker");
        TextView textView = nsfwMarker;
        List<String> tvTypes = plugin.getTvTypes();
        textView.setVisibility(tvTypes != null ? tvTypes.contains("NSFW") : false ? 0 : 8);
        repositoryItemBinding.actionButton.setImageResource(i);
        repositoryItemBinding.actionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.iconClickCallback.invoke(item.getPluginWrapper());
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PluginAdapter.onBindContent$lambda$1(z3, item, itemView, view2);
            }
        });
        if (item.isDownloaded()) {
            BasePlugin basePlugin = PluginManager.INSTANCE.getUrlPlugins().get(plugin.getUrl());
            if (basePlugin == null) {
                basePlugin = PluginManager.INSTANCE.getPlugins().get(plugin.getUrl());
            }
            final Plugin plugin2 = basePlugin instanceof Plugin ? (Plugin) basePlugin : null;
            if ((plugin2 != null ? plugin2.getOpenSettings() : null) != null) {
                ImageView actionSettings = repositoryItemBinding.actionSettings;
                Intrinsics.checkNotNullExpressionValue(actionSettings, "actionSettings");
                actionSettings.setVisibility(0);
                repositoryItemBinding.actionSettings.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PluginAdapter.onBindContent$lambda$2(plugin2, itemView, strRemoveSuffix, view2);
                    }
                });
            } else {
                ImageView actionSettings2 = repositoryItemBinding.actionSettings;
                Intrinsics.checkNotNullExpressionValue(actionSettings2, "actionSettings");
                actionSettings2.setVisibility(8);
            }
        } else {
            ImageView actionSettings3 = repositoryItemBinding.actionSettings;
            Intrinsics.checkNotNullExpressionValue(actionSettings3, "actionSettings");
            actionSettings3.setVisibility(8);
        }
        String iconUrl = plugin.getIconUrl();
        String strReplace$default2 = (iconUrl == null || (strReplace$default = StringsKt.replace$default(iconUrl, "%size%", String.valueOf(INSTANCE.getIconSize()), false, 4, (Object) null)) == null) ? null : StringsKt.replace$default(strReplace$default, "%exact_size%", String.valueOf(iconSizeExact), false, 4, (Object) null);
        String str = strReplace$default2;
        if (str == null || StringsKt.isBlank(str)) {
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView entryIcon = repositoryItemBinding.entryIcon;
            Intrinsics.checkNotNullExpressionValue(entryIcon, "entryIcon");
            ImageLoader.loadImage$default(imageLoader, entryIcon, Integer.valueOf(R.drawable.ic_baseline_extension_24), (Function1) null, 2, (Object) null);
        } else {
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            ImageView entryIcon2 = repositoryItemBinding.entryIcon;
            Intrinsics.checkNotNullExpressionValue(entryIcon2, "entryIcon");
            ImageLoader.loadImage$default(imageLoader2, entryIcon2, strReplace$default2, (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginAdapter$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PluginAdapter.onBindContent$lambda$3(itemView, (ImageRequest.Builder) obj);
                }
            }, 2, (Object) null);
        }
        TextView extVersion = repositoryItemBinding.extVersion;
        Intrinsics.checkNotNullExpressionValue(extVersion, "extVersion");
        extVersion.setVisibility(0);
        repositoryItemBinding.extVersion.setText("v" + plugin.getVersion());
        String language = plugin.getLanguage();
        if (language == null || StringsKt.isBlank(language)) {
            TextView langIcon = repositoryItemBinding.langIcon;
            Intrinsics.checkNotNullExpressionValue(langIcon, "langIcon");
            langIcon.setVisibility(8);
        } else {
            TextView langIcon2 = repositoryItemBinding.langIcon;
            Intrinsics.checkNotNullExpressionValue(langIcon2, "langIcon");
            langIcon2.setVisibility(0);
            TextView textView2 = repositoryItemBinding.langIcon;
            String nameNextToFlagEmoji$default = SubtitleHelper.getNameNextToFlagEmoji$default(SubtitleHelper.INSTANCE, plugin.getLanguage(), null, 2, null);
            if (nameNextToFlagEmoji$default == null) {
                nameNextToFlagEmoji$default = plugin.getLanguage();
            }
            textView2.setText(nameNextToFlagEmoji$default);
        }
        TextView extVotes = repositoryItemBinding.extVotes;
        Intrinsics.checkNotNullExpressionValue(extVotes, "extVotes");
        extVotes.setVisibility(8);
        if (plugin.getFileSize() != null) {
            TextView extFilesize = repositoryItemBinding.extFilesize;
            Intrinsics.checkNotNullExpressionValue(extFilesize, "extFilesize");
            extFilesize.setVisibility(0);
            repositoryItemBinding.extFilesize.setText(Formatter.formatShortFileSize(itemView.getContext(), plugin.getFileSize().longValue()));
        } else {
            TextView extFilesize2 = repositoryItemBinding.extFilesize;
            Intrinsics.checkNotNullExpressionValue(extFilesize2, "extFilesize");
            extFilesize2.setVisibility(8);
        }
        TextView textView3 = repositoryItemBinding.mainText;
        if (z2) {
            z = true;
            uiTextTxt = TextUtilKt.txt(R.string.single_plugin_disabled, strRemoveSuffix);
        } else {
            z = true;
            uiTextTxt = TextUtilKt.txt(strRemoveSuffix);
        }
        TextUtilKt.setText(textView3, uiTextTxt);
        TextView subText = repositoryItemBinding.subText;
        Intrinsics.checkNotNullExpressionValue(subText, "subText");
        TextView textView4 = subText;
        String description = plugin.getDescription();
        textView4.setVisibility((description == null || StringsKt.isBlank(description)) ? z : false ? 8 : 0);
        repositoryItemBinding.subText.setText(AppContextUtils.INSTANCE.html(plugin.getDescription()));
    }

    static final void onBindContent$lambda$1(boolean z, PluginViewData pluginViewData, View view, View view2) {
        if (z) {
            return;
        }
        PluginDetailsFragment pluginDetailsFragment = new PluginDetailsFragment(pluginViewData);
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Activity activity = companion.getActivity(context);
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        pluginDetailsFragment.show(((AppCompatActivity) activity).getSupportFragmentManager(), "PluginDetails");
    }

    static final void onBindContent$lambda$2(Plugin plugin, View view, String str, View view2) {
        try {
            Function1<Context, Unit> openSettings = plugin.getOpenSettings();
            if (openSettings != null) {
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                openSettings.invoke(context);
            }
        } catch (Throwable th) {
            Log.e("PluginAdapter", "Failed to open " + str + " settings: " + Log.getStackTraceString(th));
        }
    }

    static final Unit onBindContent$lambda$3(View view, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        loadImage.error(ImageUtilKt.getImageFromDrawable(context, R.drawable.ic_baseline_extension_24));
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: PluginAdapter.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0004\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000fH\u0082\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cR:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginAdapter$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "findClosestBase2", "", TypedValues.AttributesType.S_TARGET, "current", "max", "iconSizeExact", "iconSize", "getIconSize", "()I", "iconSize$delegate", "Lkotlin/Lazy;", "prettyCount", "", "number", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return PluginAdapter.sharedPool;
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
            return ((Number) PluginAdapter.iconSize$delegate.getValue()).intValue();
        }

        public final String prettyCount(Number number) {
            Intrinsics.checkNotNullParameter(number, "number");
            char[] cArr = {' ', 'k', GMTDateParser.MONTH, 'B', 'T', 'P', 'E'};
            long jLongValue = number.longValue();
            double d = jLongValue;
            int iFloor = (int) Math.floor(Math.log10(d));
            int i = iFloor / 3;
            if (iFloor >= 3 && i < 7) {
                return new DecimalFormat("#0.00").format(d / Math.pow(10.0d, i * 3)) + cArr[i];
            }
            return new DecimalFormat().format(jLongValue);
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 15);
        return Unit.INSTANCE;
    }
}
