package androidx.compose.ui.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.graphics.vector.compat.XmlVectorParser_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: PainterResources.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a1\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bR\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\f\u001a \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"painterResource", "Landroidx/compose/ui/graphics/painter/Painter;", "id", "", "(ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/painter/Painter;", "loadVectorResource", "Landroidx/compose/ui/graphics/vector/ImageVector;", "theme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "res", "changingConfigurations", "(Landroid/content/res/Resources$Theme;Landroid/content/res/Resources;IILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/ImageVector;", "loadImageBitmapResource", "Landroidx/compose/ui/graphics/ImageBitmap;", "path", "", "errorMessage", "", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PainterResources_androidKt {
    private static final String errorMessage = "Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG, WEBP";

    public static final Painter painterResource(int i, Composer composer, int i2) {
        Composer composer2;
        BitmapPainter bitmapPainter;
        ComposerKt.sourceInformationMarkerStart(composer, 473971343, "C(painterResource)57@2480L7,59@2518L7,60@2573L7:PainterResources.android.kt#ccshc7");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(473971343, i2, -1, "androidx.compose.ui.res.painterResource (PainterResources.android.kt:56)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Context context = (Context) objConsume;
        ProvidableCompositionLocal<Resources> localResources = AndroidCompositionLocals_androidKt.getLocalResources();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = composer.consume(localResources);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Resources resources = (Resources) objConsume2;
        ProvidableCompositionLocal<ResourceIdCache> localResourceIdCache = AndroidCompositionLocals_androidKt.getLocalResourceIdCache();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume3 = composer.consume(localResourceIdCache);
        ComposerKt.sourceInformationMarkerEnd(composer);
        TypedValue typedValueResolveResourcePath = ((ResourceIdCache) objConsume3).resolveResourcePath(resources, i);
        CharSequence charSequence = typedValueResolveResourcePath.string;
        boolean z = true;
        if (charSequence != null && StringsKt.endsWith$default(charSequence, (CharSequence) ".xml", false, 2, (Object) null)) {
            composer.startReplaceGroup(-1771786530);
            ComposerKt.sourceInformation(composer, "66@2814L72,67@2895L34");
            composer2 = composer;
            VectorPainter vectorPainterRememberVectorPainter = VectorPainterKt.rememberVectorPainter(loadVectorResource(context.getTheme(), resources, i, typedValueResolveResourcePath.changingConfigurations, composer2, (i2 << 6) & 896), composer2, 0);
            composer2.endReplaceGroup();
            bitmapPainter = vectorPainterRememberVectorPainter;
        } else {
            composer2 = composer;
            composer2.startReplaceGroup(-1771631096);
            ComposerKt.sourceInformation(composer2, "71@3027L76");
            Resources.Theme theme = context.getTheme();
            ComposerKt.sourceInformationMarkerStart(composer2, 219947899, "CC(remember):PainterResources.android.kt#9igjgp");
            boolean zChanged = composer2.changed(charSequence);
            if ((((i2 & 14) ^ 6) <= 4 || !composer2.changed(i)) && (i2 & 6) != 4) {
                z = false;
            }
            boolean zChanged2 = composer2.changed(theme) | zChanged | z;
            Object objRememberedValue = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = loadImageBitmapResource(charSequence, resources, i);
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            BitmapPainter bitmapPainter2 = new BitmapPainter((ImageBitmap) objRememberedValue, 0L, 0L, 6, null);
            composer2.endReplaceGroup();
            bitmapPainter = bitmapPainter2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        return bitmapPainter;
    }

    private static final ImageVector loadVectorResource(Resources.Theme theme, Resources resources, int i, int i2, Composer composer, int i3) throws XmlPullParserException {
        ComposerKt.sourceInformationMarkerStart(composer, 21855625, "C(loadVectorResource)P(3,2,1)88@3590L7:PainterResources.android.kt#ccshc7");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21855625, i3, -1, "androidx.compose.ui.res.loadVectorResource (PainterResources.android.kt:87)");
        }
        ProvidableCompositionLocal<ImageVectorCache> localImageVectorCache = AndroidCompositionLocals_androidKt.getLocalImageVectorCache();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localImageVectorCache);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ImageVectorCache imageVectorCache = (ImageVectorCache) objConsume;
        ImageVectorCache.Key key = new ImageVectorCache.Key(theme, i);
        ImageVectorCache.ImageVectorEntry imageVectorEntryLoadVectorResourceInner = imageVectorCache.get(key);
        if (imageVectorEntryLoadVectorResourceInner == null) {
            XmlResourceParser xml = resources.getXml(i);
            if (!Intrinsics.areEqual(XmlVectorParser_androidKt.seekToStartTag(xml).getName(), "vector")) {
                throw new IllegalArgumentException(errorMessage);
            }
            imageVectorEntryLoadVectorResourceInner = VectorResources_androidKt.loadVectorResourceInner(theme, resources, xml, i2);
            imageVectorCache.set(key, imageVectorEntryLoadVectorResourceInner);
        }
        ImageVector imageVector = imageVectorEntryLoadVectorResourceInner.getImageVector();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return imageVector;
    }

    private static final ImageBitmap loadImageBitmapResource(CharSequence charSequence, Resources resources, int i) {
        try {
            return ImageResources_androidKt.imageResource(ImageBitmap.INSTANCE, resources, i);
        } catch (Exception e) {
            throw new ResourceResolutionException("Error attempting to load resource: " + ((Object) charSequence), e);
        }
    }
}
