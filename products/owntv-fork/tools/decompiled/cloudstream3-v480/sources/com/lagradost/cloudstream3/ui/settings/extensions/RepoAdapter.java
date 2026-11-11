package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import coil3.request.ImageRequest;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.RepositoryItemBinding;
import com.lagradost.cloudstream3.databinding.RepositoryItemTvBinding;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RepoAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bBM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001d\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u0012\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J&\u0010\u0017\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\fR(\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR(\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/RepoAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", SetupFragmentExtensions.SETUP_EXTENSION_BUNDLE_IS_SETUP, "", "clickCallback", "Lkotlin/Function2;", "", "Lkotlin/ExtensionFunctionType;", "imageClickCallback", "<init>", "(ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "()Z", "getClickCallback", "()Lkotlin/jvm/functions/Function2;", "getImageClickCallback", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RepoAdapter extends NoStateAdapter<RepositoryData> {
    public static final String SHAREABLE_REPO_SEPARATOR = " : ";
    private final Function2<RepoAdapter, RepositoryData, Unit> clickCallback;
    private final Function2<RepoAdapter, RepositoryData, Unit> imageClickCallback;
    private final boolean isSetup;

    /* JADX INFO: renamed from: isSetup, reason: from getter */
    public final boolean getIsSetup() {
        return this.isSetup;
    }

    public final Function2<RepoAdapter, RepositoryData, Unit> getClickCallback() {
        return this.clickCallback;
    }

    public final Function2<RepoAdapter, RepositoryData, Unit> getImageClickCallback() {
        return this.imageClickCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RepoAdapter(boolean z, Function2<? super RepoAdapter, ? super RepositoryData, Unit> clickCallback, Function2<? super RepoAdapter, ? super RepositoryData, Unit> imageClickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(RepoAdapter._init_$lambda$0((RepositoryData) obj, (RepositoryData) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        Intrinsics.checkNotNullParameter(imageClickCallback, "imageClickCallback");
        this.isSetup = z;
        this.clickCallback = clickCallback;
        this.imageClickCallback = imageClickCallback;
    }

    static final boolean _init_$lambda$0(RepositoryData a, RepositoryData b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getUrl(), b.getUrl());
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        RepositoryItemBinding repositoryItemBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (Globals.INSTANCE.isLayout(2)) {
            RepositoryItemTvBinding repositoryItemTvBindingInflate = RepositoryItemTvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(repositoryItemTvBindingInflate, "inflate(...)");
            repositoryItemBinding = repositoryItemTvBindingInflate;
        } else {
            RepositoryItemBinding repositoryItemBindingInflate = RepositoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(repositoryItemBindingInflate, "inflate(...)");
            repositoryItemBinding = repositoryItemBindingInflate;
        }
        return new ViewHolderState<>(repositoryItemBinding);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof RepositoryItemBinding) {
            BaseAdapter.INSTANCE.clearImage(((RepositoryItemBinding) view).entryIcon);
        } else if (view instanceof RepositoryItemTvBinding) {
            BaseAdapter.INSTANCE.clearImage(((RepositoryItemTvBinding) view).entryIcon);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final RepositoryData item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        boolean zContains = ArraysKt.contains(RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES(), item);
        int i = this.isSetup ? R.drawable.netflix_download : R.drawable.ic_baseline_delete_outline_24;
        final ViewBinding view = holder.getView();
        if (view instanceof RepositoryItemTvBinding) {
            RepositoryItemTvBinding repositoryItemTvBinding = (RepositoryItemTvBinding) view;
            if (!zContains || this.isSetup) {
                repositoryItemTvBinding.actionButton.setImageResource(i);
            }
            repositoryItemTvBinding.actionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RepoAdapter.onBindContent$lambda$0$0(this.f$0, item, view2);
                }
            });
            repositoryItemTvBinding.repositoryItemRoot.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RepoAdapter.onBindContent$lambda$0$1(this.f$0, item, view2);
                }
            });
            repositoryItemTvBinding.mainText.setText(item.getName());
            repositoryItemTvBinding.subText.setText(item.getUrl());
            String iconUrl = item.getIconUrl();
            if (iconUrl != null && iconUrl.length() != 0) {
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                ImageView entryIcon = repositoryItemTvBinding.entryIcon;
                Intrinsics.checkNotNullExpressionValue(entryIcon, "entryIcon");
                ImageLoader.loadImage$default(imageLoader, entryIcon, item.getIconUrl(), (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoAdapter.onBindContent$lambda$0$2(view, (ImageRequest.Builder) obj);
                    }
                }, 2, (Object) null);
                return;
            }
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            ImageView entryIcon2 = repositoryItemTvBinding.entryIcon;
            Intrinsics.checkNotNullExpressionValue(entryIcon2, "entryIcon");
            ImageLoader.loadImage$default(imageLoader2, entryIcon2, Integer.valueOf(R.drawable.ic_github_logo), (Function1) null, 2, (Object) null);
            return;
        }
        if (view instanceof RepositoryItemBinding) {
            RepositoryItemBinding repositoryItemBinding = (RepositoryItemBinding) view;
            if (!zContains || this.isSetup) {
                repositoryItemBinding.actionButton.setImageResource(i);
            }
            repositoryItemBinding.actionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RepoAdapter.onBindContent$lambda$1$0(this.f$0, item, view2);
                }
            });
            repositoryItemBinding.repositoryItemRoot.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RepoAdapter.onBindContent$lambda$1$1(this.f$0, item, view2);
                }
            });
            repositoryItemBinding.repositoryItemRoot.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda6
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return RepoAdapter.onBindContent$lambda$1$2(item, view2);
                }
            });
            repositoryItemBinding.mainText.setText(item.getName());
            repositoryItemBinding.subText.setText(item.getUrl());
            String iconUrl2 = item.getIconUrl();
            if (iconUrl2 != null && iconUrl2.length() != 0) {
                ImageLoader imageLoader3 = ImageLoader.INSTANCE;
                ImageView entryIcon3 = repositoryItemBinding.entryIcon;
                Intrinsics.checkNotNullExpressionValue(entryIcon3, "entryIcon");
                ImageLoader.loadImage$default(imageLoader3, entryIcon3, item.getIconUrl(), (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoAdapter.onBindContent$lambda$1$3(view, (ImageRequest.Builder) obj);
                    }
                }, 2, (Object) null);
                return;
            }
            ImageLoader imageLoader4 = ImageLoader.INSTANCE;
            ImageView entryIcon4 = repositoryItemBinding.entryIcon;
            Intrinsics.checkNotNullExpressionValue(entryIcon4, "entryIcon");
            ImageLoader.loadImage$default(imageLoader4, entryIcon4, Integer.valueOf(R.drawable.ic_github_logo), (Function1) null, 2, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$0(RepoAdapter repoAdapter, RepositoryData repositoryData, View view) {
        repoAdapter.imageClickCallback.invoke(repoAdapter, repositoryData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$1(RepoAdapter repoAdapter, RepositoryData repositoryData, View view) {
        repoAdapter.clickCallback.invoke(repoAdapter, repositoryData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$0$2(ViewBinding viewBinding, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        Context context = ((RepositoryItemTvBinding) viewBinding).getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        loadImage.error(ImageUtilKt.getImageFromDrawable(context, R.drawable.ic_github_logo));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$1$0(RepoAdapter repoAdapter, RepositoryData repositoryData, View view) {
        repoAdapter.imageClickCallback.invoke(repoAdapter, repositoryData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$1$1(RepoAdapter repoAdapter, RepositoryData repositoryData, View view) {
        repoAdapter.clickCallback.invoke(repoAdapter, repositoryData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindContent$lambda$1$2(RepositoryData repositoryData, View view) {
        UIHelper.INSTANCE.clipboardHelper(TextUtilKt.txt(R.string.repo_copy_label, new Object[0]), repositoryData.getName() + " : \n " + repositoryData.getUrl());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$1$3(ViewBinding viewBinding, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        Context context = ((RepositoryItemBinding) viewBinding).getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        loadImage.error(ImageUtilKt.getImageFromDrawable(context, R.drawable.ic_github_logo));
        return Unit.INSTANCE;
    }
}
