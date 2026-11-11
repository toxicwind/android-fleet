package org.schabi.newpipe.extractor.services.youtube.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.grack.nanojson.JsonObject;
import j$.util.function.Predicate$CC;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubePlaylistLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMixOrPlaylistLockupInfoItemExtractor implements PlaylistInfoItemExtractor {

    @Nonnull
    private final JsonObject firstMetadataRow;

    @Nonnull
    private final JsonObject lockupMetadataViewModel;

    @Nonnull
    private final JsonObject lockupViewModel;

    @Nonnull
    private PlaylistInfo.PlaylistType playlistType;

    @Nonnull
    private final JsonObject thumbnailViewModel;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    public YoutubeMixOrPlaylistLockupInfoItemExtractor(@Nonnull JsonObject jsonObject) {
        this.lockupViewModel = jsonObject;
        this.thumbnailViewModel = jsonObject.getObject("contentImage").getObject("collectionThumbnailViewModel").getObject("primaryThumbnail").getObject("thumbnailViewModel");
        JsonObject object = jsonObject.getObject(TtmlNode.TAG_METADATA).getObject("lockupMetadataViewModel");
        this.lockupMetadataViewModel = object;
        this.firstMetadataRow = object.getObject(TtmlNode.TAG_METADATA).getObject("contentMetadataViewModel").getArray("metadataRows").getObject(0);
        try {
            this.playlistType = YoutubeParsingHelper.extractPlaylistTypeFromPlaylistId(getPlaylistId());
        } catch (ParsingException unused) {
            this.playlistType = PlaylistInfo.PlaylistType.NORMAL;
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.firstMetadataRow.getArray("metadataParts").getObject(0).getObject("text").getString("content");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        if (this.playlistType != PlaylistInfo.PlaylistType.NORMAL) {
            return null;
        }
        return YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.firstMetadataRow.getArray("metadataParts").getObject(0).getObject("text").getArray("commandRuns").getObject(0).getObject("onTap").getObject("innertubeCommand"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        if (this.playlistType != PlaylistInfo.PlaylistType.NORMAL) {
            return false;
        }
        return YoutubeParsingHelper.hasArtistOrVerifiedIconBadgeAttachment(this.firstMetadataRow.getArray("metadataParts").getObject(0).getObject("text").getArray("attachmentRuns"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        if (this.playlistType != PlaylistInfo.PlaylistType.NORMAL) {
            return -2L;
        }
        try {
            return Long.parseLong(Utils.removeNonDigitCharacters(this.thumbnailViewModel.getArray("overlays").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor$$ExternalSyntheticLambda0
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JsonObject) obj).has("thumbnailOverlayBadgeViewModel");
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return YoutubeMixOrPlaylistLockupInfoItemExtractor.lambda$getStreamCount$1();
                }
            }).getObject("thumbnailOverlayBadgeViewModel").getArray("thumbnailBadges").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor$$ExternalSyntheticLambda2
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JsonObject) obj).has("thumbnailBadgeViewModel");
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return YoutubeMixOrPlaylistLockupInfoItemExtractor.lambda$getStreamCount$3();
                }
            }).getObject("thumbnailBadgeViewModel").getString("text")));
        } catch (Exception e) {
            throw new ParsingException("Could not get playlist stream count", e);
        }
    }

    static /* synthetic */ ParsingException lambda$getStreamCount$1() {
        return new ParsingException("Could not get thumbnailOverlayBadgeViewModel");
    }

    static /* synthetic */ ParsingException lambda$getStreamCount$3() {
        return new ParsingException("Could not get thumbnailBadgeViewModel");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.lockupMetadataViewModel.getObject("title").getString("content");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        if (this.playlistType == PlaylistInfo.PlaylistType.NORMAL) {
            try {
                return YoutubePlaylistLinkHandlerFactory.getInstance().getUrl(getPlaylistId());
            } catch (Exception unused) {
            }
        }
        return YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.lockupViewModel.getObject("rendererContext").getObject("commandContext").getObject("onTap").getObject("innertubeCommand"));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.thumbnailViewModel.getObject("image").getArray("sources"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    @Nonnull
    public PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException {
        return this.playlistType;
    }

    private String getPlaylistId() throws ParsingException {
        String string = this.lockupViewModel.getString("contentId");
        if (Utils.isNullOrEmpty(string)) {
            string = this.lockupViewModel.getObject("rendererContext").getObject("commandContext").getObject("watchEndpoint").getString("playlistId");
        }
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get playlist ID");
        }
        return string;
    }
}
