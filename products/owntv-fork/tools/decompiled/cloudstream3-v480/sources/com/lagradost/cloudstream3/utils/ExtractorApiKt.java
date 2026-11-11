package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.Ksoup;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.select.Elements;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.extractors.Acefile;
import com.lagradost.cloudstream3.extractors.Ahvsh;
import com.lagradost.cloudstream3.extractors.Aico;
import com.lagradost.cloudstream3.extractors.Asnwish;
import com.lagradost.cloudstream3.extractors.Auvexiug;
import com.lagradost.cloudstream3.extractors.Awish;
import com.lagradost.cloudstream3.extractors.BgwpCC;
import com.lagradost.cloudstream3.extractors.BigwarpArt;
import com.lagradost.cloudstream3.extractors.BigwarpIO;
import com.lagradost.cloudstream3.extractors.Blogger;
import com.lagradost.cloudstream3.extractors.ByseBuho;
import com.lagradost.cloudstream3.extractors.ByseQekaho;
import com.lagradost.cloudstream3.extractors.ByseSX;
import com.lagradost.cloudstream3.extractors.ByseVepoin;
import com.lagradost.cloudstream3.extractors.Bysezejataos;
import com.lagradost.cloudstream3.extractors.Cavanhabg;
import com.lagradost.cloudstream3.extractors.Cda;
import com.lagradost.cloudstream3.extractors.Cdnplayer;
import com.lagradost.cloudstream3.extractors.CdnwishCom;
import com.lagradost.cloudstream3.extractors.CloudMailRu;
import com.lagradost.cloudstream3.extractors.ContentX;
import com.lagradost.cloudstream3.extractors.CsstOnline;
import com.lagradost.cloudstream3.extractors.D0000d;
import com.lagradost.cloudstream3.extractors.D000dCom;
import com.lagradost.cloudstream3.extractors.DBfilm;
import com.lagradost.cloudstream3.extractors.Dailymotion;
import com.lagradost.cloudstream3.extractors.DatabaseGdrive;
import com.lagradost.cloudstream3.extractors.DatabaseGdrive2;
import com.lagradost.cloudstream3.extractors.DesuArcg;
import com.lagradost.cloudstream3.extractors.DesuDrive;
import com.lagradost.cloudstream3.extractors.DesuOdchan;
import com.lagradost.cloudstream3.extractors.DesuOdvip;
import com.lagradost.cloudstream3.extractors.Dhcplay;
import com.lagradost.cloudstream3.extractors.Dhtpre;
import com.lagradost.cloudstream3.extractors.Dokicloud;
import com.lagradost.cloudstream3.extractors.DoodCxExtractor;
import com.lagradost.cloudstream3.extractors.DoodLaExtractor;
import com.lagradost.cloudstream3.extractors.DoodPmExtractor;
import com.lagradost.cloudstream3.extractors.DoodShExtractor;
import com.lagradost.cloudstream3.extractors.DoodSoExtractor;
import com.lagradost.cloudstream3.extractors.DoodToExtractor;
import com.lagradost.cloudstream3.extractors.DoodWatchExtractor;
import com.lagradost.cloudstream3.extractors.DoodWfExtractor;
import com.lagradost.cloudstream3.extractors.DoodWsExtractor;
import com.lagradost.cloudstream3.extractors.DoodYtExtractor;
import com.lagradost.cloudstream3.extractors.Doodporn;
import com.lagradost.cloudstream3.extractors.Doodspro;
import com.lagradost.cloudstream3.extractors.DoodstreamCom;
import com.lagradost.cloudstream3.extractors.Dooood;
import com.lagradost.cloudstream3.extractors.Ds2play;
import com.lagradost.cloudstream3.extractors.Ds2video;
import com.lagradost.cloudstream3.extractors.DsstOnline;
import com.lagradost.cloudstream3.extractors.Dsvplay;
import com.lagradost.cloudstream3.extractors.Dumbalag;
import com.lagradost.cloudstream3.extractors.Dwish;
import com.lagradost.cloudstream3.extractors.Embedgram;
import com.lagradost.cloudstream3.extractors.EmturbovidExtractor;
import com.lagradost.cloudstream3.extractors.Evoload;
import com.lagradost.cloudstream3.extractors.Evoload1;
import com.lagradost.cloudstream3.extractors.Ewish;
import com.lagradost.cloudstream3.extractors.FEmbed;
import com.lagradost.cloudstream3.extractors.FEnet;
import com.lagradost.cloudstream3.extractors.Fastream;
import com.lagradost.cloudstream3.extractors.FeHD;
import com.lagradost.cloudstream3.extractors.Fembed9hd;
import com.lagradost.cloudstream3.extractors.FileMoon;
import com.lagradost.cloudstream3.extractors.FileMoonIn;
import com.lagradost.cloudstream3.extractors.FileMoonSx;
import com.lagradost.cloudstream3.extractors.FilemoonV2;
import com.lagradost.cloudstream3.extractors.Filesim;
import com.lagradost.cloudstream3.extractors.Firestream;
import com.lagradost.cloudstream3.extractors.FlaswishCom;
import com.lagradost.cloudstream3.extractors.Flyfile;
import com.lagradost.cloudstream3.extractors.FourCX;
import com.lagradost.cloudstream3.extractors.FourPichive;
import com.lagradost.cloudstream3.extractors.FourPlayRu;
import com.lagradost.cloudstream3.extractors.Fplayer;
import com.lagradost.cloudstream3.extractors.FsstOnline;
import com.lagradost.cloudstream3.extractors.GDMirrorbot;
import com.lagradost.cloudstream3.extractors.GUpload;
import com.lagradost.cloudstream3.extractors.GamoVideo;
import com.lagradost.cloudstream3.extractors.Gdriveplayer;
import com.lagradost.cloudstream3.extractors.Gdriveplayerapi;
import com.lagradost.cloudstream3.extractors.Gdriveplayerapp;
import com.lagradost.cloudstream3.extractors.Gdriveplayerbiz;
import com.lagradost.cloudstream3.extractors.Gdriveplayerco;
import com.lagradost.cloudstream3.extractors.Gdriveplayerfun;
import com.lagradost.cloudstream3.extractors.Gdriveplayerio;
import com.lagradost.cloudstream3.extractors.Gdriveplayerme;
import com.lagradost.cloudstream3.extractors.Gdriveplayerorg;
import com.lagradost.cloudstream3.extractors.Gdriveplayerus;
import com.lagradost.cloudstream3.extractors.Geodailymotion;
import com.lagradost.cloudstream3.extractors.Gofile;
import com.lagradost.cloudstream3.extractors.GoodstreamExtractor;
import com.lagradost.cloudstream3.extractors.Guccihide;
import com.lagradost.cloudstream3.extractors.Guxhag;
import com.lagradost.cloudstream3.extractors.HDMomPlayer;
import com.lagradost.cloudstream3.extractors.HDPlayerSystem;
import com.lagradost.cloudstream3.extractors.HDStreamAble;
import com.lagradost.cloudstream3.extractors.Habetar;
import com.lagradost.cloudstream3.extractors.Haxloppd;
import com.lagradost.cloudstream3.extractors.Hgcloudto;
import com.lagradost.cloudstream3.extractors.HglinkTo;
import com.lagradost.cloudstream3.extractors.HgplayCDN;
import com.lagradost.cloudstream3.extractors.HlsWish;
import com.lagradost.cloudstream3.extractors.Hotlinger;
import com.lagradost.cloudstream3.extractors.HubCloud;
import com.lagradost.cloudstream3.extractors.Hxfile;
import com.lagradost.cloudstream3.extractors.InternetArchive;
import com.lagradost.cloudstream3.extractors.JWPlayer;
import com.lagradost.cloudstream3.extractors.Jeniusplay;
import com.lagradost.cloudstream3.extractors.Jodwish;
import com.lagradost.cloudstream3.extractors.Keephealth;
import com.lagradost.cloudstream3.extractors.KotakAnimeid;
import com.lagradost.cloudstream3.extractors.Kotakajair;
import com.lagradost.cloudstream3.extractors.Krakenfiles;
import com.lagradost.cloudstream3.extractors.Kswplayer;
import com.lagradost.cloudstream3.extractors.LayarKaca;
import com.lagradost.cloudstream3.extractors.Linkbox;
import com.lagradost.cloudstream3.extractors.LuluStream;
import com.lagradost.cloudstream3.extractors.Lulustream1;
import com.lagradost.cloudstream3.extractors.Lulustream2;
import com.lagradost.cloudstream3.extractors.Luluvdoo;
import com.lagradost.cloudstream3.extractors.Luxubu;
import com.lagradost.cloudstream3.extractors.Lvturbo;
import com.lagradost.cloudstream3.extractors.MailRu;
import com.lagradost.cloudstream3.extractors.Maxstream;
import com.lagradost.cloudstream3.extractors.Mdy;
import com.lagradost.cloudstream3.extractors.Mediafire;
import com.lagradost.cloudstream3.extractors.Megacloud;
import com.lagradost.cloudstream3.extractors.Meownime;
import com.lagradost.cloudstream3.extractors.MetaGnathTuggers;
import com.lagradost.cloudstream3.extractors.MixDrop;
import com.lagradost.cloudstream3.extractors.MixDropAg;
import com.lagradost.cloudstream3.extractors.MixDropBz;
import com.lagradost.cloudstream3.extractors.MixDropCh;
import com.lagradost.cloudstream3.extractors.MixDropPs;
import com.lagradost.cloudstream3.extractors.MixDropSi;
import com.lagradost.cloudstream3.extractors.MixDropTo;
import com.lagradost.cloudstream3.extractors.Movhide;
import com.lagradost.cloudstream3.extractors.Moviehab;
import com.lagradost.cloudstream3.extractors.MoviehabNet;
import com.lagradost.cloudstream3.extractors.Moviesm4u;
import com.lagradost.cloudstream3.extractors.Mp4Upload;
import com.lagradost.cloudstream3.extractors.Multimovies;
import com.lagradost.cloudstream3.extractors.Multimoviesshg;
import com.lagradost.cloudstream3.extractors.Mvidoo;
import com.lagradost.cloudstream3.extractors.Mwish;
import com.lagradost.cloudstream3.extractors.MxDropTo;
import com.lagradost.cloudstream3.extractors.MyVidPlay;
import com.lagradost.cloudstream3.extractors.NathanFromSubject;
import com.lagradost.cloudstream3.extractors.Nekostream;
import com.lagradost.cloudstream3.extractors.Nekowish;
import com.lagradost.cloudstream3.extractors.Neonime7n;
import com.lagradost.cloudstream3.extractors.Neonime8n;
import com.lagradost.cloudstream3.extractors.Obeywish;
import com.lagradost.cloudstream3.extractors.Odnoklassniki;
import com.lagradost.cloudstream3.extractors.OkRuHTTP;
import com.lagradost.cloudstream3.extractors.OkRuHTTPMobile;
import com.lagradost.cloudstream3.extractors.OkRuSSL;
import com.lagradost.cloudstream3.extractors.OkRuSSLMobile;
import com.lagradost.cloudstream3.extractors.PeaceMakerst;
import com.lagradost.cloudstream3.extractors.Peytonepre;
import com.lagradost.cloudstream3.extractors.Pichive;
import com.lagradost.cloudstream3.extractors.PixelDrain;
import com.lagradost.cloudstream3.extractors.PixelDrainDev;
import com.lagradost.cloudstream3.extractors.PlayLtXyz;
import com.lagradost.cloudstream3.extractors.PlayRu;
import com.lagradost.cloudstream3.extractors.PlayerVoxzer;
import com.lagradost.cloudstream3.extractors.Playerwish;
import com.lagradost.cloudstream3.extractors.Playmogo;
import com.lagradost.cloudstream3.extractors.Rabbitstream;
import com.lagradost.cloudstream3.extractors.RapidVid;
import com.lagradost.cloudstream3.extractors.Rasacintaku;
import com.lagradost.cloudstream3.extractors.Ryderjet;
import com.lagradost.cloudstream3.extractors.SBfull;
import com.lagradost.cloudstream3.extractors.Sbasian;
import com.lagradost.cloudstream3.extractors.Sbface;
import com.lagradost.cloudstream3.extractors.Sbflix;
import com.lagradost.cloudstream3.extractors.Sblona;
import com.lagradost.cloudstream3.extractors.Sblongvu;
import com.lagradost.cloudstream3.extractors.Sbnet;
import com.lagradost.cloudstream3.extractors.Sbrapid;
import com.lagradost.cloudstream3.extractors.Sbsonic;
import com.lagradost.cloudstream3.extractors.Sbspeed;
import com.lagradost.cloudstream3.extractors.Sbthe;
import com.lagradost.cloudstream3.extractors.SecvideoOnline;
import com.lagradost.cloudstream3.extractors.Sendvid;
import com.lagradost.cloudstream3.extractors.Server1uns;
import com.lagradost.cloudstream3.extractors.SfastwishCom;
import com.lagradost.cloudstream3.extractors.ShaveTape;
import com.lagradost.cloudstream3.extractors.SibNet;
import com.lagradost.cloudstream3.extractors.Simpulumlamerop;
import com.lagradost.cloudstream3.extractors.Smoothpre;
import com.lagradost.cloudstream3.extractors.Sobreatsesuyp;
import com.lagradost.cloudstream3.extractors.Ssbstream;
import com.lagradost.cloudstream3.extractors.StreamEmbed;
import com.lagradost.cloudstream3.extractors.StreamHLS;
import com.lagradost.cloudstream3.extractors.StreamM4u;
import com.lagradost.cloudstream3.extractors.StreamSB;
import com.lagradost.cloudstream3.extractors.StreamSB1;
import com.lagradost.cloudstream3.extractors.StreamSB10;
import com.lagradost.cloudstream3.extractors.StreamSB11;
import com.lagradost.cloudstream3.extractors.StreamSB2;
import com.lagradost.cloudstream3.extractors.StreamSB3;
import com.lagradost.cloudstream3.extractors.StreamSB4;
import com.lagradost.cloudstream3.extractors.StreamSB5;
import com.lagradost.cloudstream3.extractors.StreamSB6;
import com.lagradost.cloudstream3.extractors.StreamSB7;
import com.lagradost.cloudstream3.extractors.StreamSB8;
import com.lagradost.cloudstream3.extractors.StreamSB9;
import com.lagradost.cloudstream3.extractors.StreamSilk;
import com.lagradost.cloudstream3.extractors.StreamTape;
import com.lagradost.cloudstream3.extractors.StreamTapeNet;
import com.lagradost.cloudstream3.extractors.StreamTapeXyz;
import com.lagradost.cloudstream3.extractors.StreamWishExtractor;
import com.lagradost.cloudstream3.extractors.Streamcash;
import com.lagradost.cloudstream3.extractors.StreamhideCom;
import com.lagradost.cloudstream3.extractors.StreamhideTo;
import com.lagradost.cloudstream3.extractors.Streamhub2;
import com.lagradost.cloudstream3.extractors.Streamlare;
import com.lagradost.cloudstream3.extractors.StreamoUpload;
import com.lagradost.cloudstream3.extractors.Streamplay;
import com.lagradost.cloudstream3.extractors.Streamsss;
import com.lagradost.cloudstream3.extractors.Streamwish2;
import com.lagradost.cloudstream3.extractors.Strwish;
import com.lagradost.cloudstream3.extractors.Strwish2;
import com.lagradost.cloudstream3.extractors.Supervideo;
import com.lagradost.cloudstream3.extractors.Swdyu;
import com.lagradost.cloudstream3.extractors.Swhoi;
import com.lagradost.cloudstream3.extractors.TRsTX;
import com.lagradost.cloudstream3.extractors.Tantifilm;
import com.lagradost.cloudstream3.extractors.TauVideo;
import com.lagradost.cloudstream3.extractors.Techinmind;
import com.lagradost.cloudstream3.extractors.Tubeless;
import com.lagradost.cloudstream3.extractors.Uasopt;
import com.lagradost.cloudstream3.extractors.Up4FunTop;
import com.lagradost.cloudstream3.extractors.Up4Stream;
import com.lagradost.cloudstream3.extractors.Upstream;
import com.lagradost.cloudstream3.extractors.UpstreamExtractor;
import com.lagradost.cloudstream3.extractors.Uqload;
import com.lagradost.cloudstream3.extractors.Uqload1;
import com.lagradost.cloudstream3.extractors.Uqload2;
import com.lagradost.cloudstream3.extractors.Uqloadbz;
import com.lagradost.cloudstream3.extractors.Uqloadcx;
import com.lagradost.cloudstream3.extractors.UqloadsXyz;
import com.lagradost.cloudstream3.extractors.Urochsunloath;
import com.lagradost.cloudstream3.extractors.Userload;
import com.lagradost.cloudstream3.extractors.Userscloud;
import com.lagradost.cloudstream3.extractors.Uservideo;
import com.lagradost.cloudstream3.extractors.Vicloud;
import com.lagradost.cloudstream3.extractors.VidHideHub;
import com.lagradost.cloudstream3.extractors.VidHidePro;
import com.lagradost.cloudstream3.extractors.VidHidePro1;
import com.lagradost.cloudstream3.extractors.VidHidePro2;
import com.lagradost.cloudstream3.extractors.VidHidePro3;
import com.lagradost.cloudstream3.extractors.VidHidePro4;
import com.lagradost.cloudstream3.extractors.VidHidePro5;
import com.lagradost.cloudstream3.extractors.VidHidePro6;
import com.lagradost.cloudstream3.extractors.VidMoxy;
import com.lagradost.cloudstream3.extractors.VidNest;
import com.lagradost.cloudstream3.extractors.VidStack;
import com.lagradost.cloudstream3.extractors.VidaaraxCom;
import com.lagradost.cloudstream3.extractors.VidaaraxNet;
import com.lagradost.cloudstream3.extractors.Vidara;
import com.lagradost.cloudstream3.extractors.VidaraSo;
import com.lagradost.cloudstream3.extractors.Vidaraa;
import com.lagradost.cloudstream3.extractors.Vidaratem;
import com.lagradost.cloudstream3.extractors.Vidaraw;
import com.lagradost.cloudstream3.extractors.Vidarax;
import com.lagradost.cloudstream3.extractors.Vidavaca;
import com.lagradost.cloudstream3.extractors.Vide0Net;
import com.lagradost.cloudstream3.extractors.Videa;
import com.lagradost.cloudstream3.extractors.VideoSeyred;
import com.lagradost.cloudstream3.extractors.Videzz;
import com.lagradost.cloudstream3.extractors.Vidgomunime;
import com.lagradost.cloudstream3.extractors.Vidgomunimesb;
import com.lagradost.cloudstream3.extractors.VidhideExtractor;
import com.lagradost.cloudstream3.extractors.Vidmoly;
import com.lagradost.cloudstream3.extractors.Vidmolybiz;
import com.lagradost.cloudstream3.extractors.Vidmolyme;
import com.lagradost.cloudstream3.extractors.Vidmolyto;
import com.lagradost.cloudstream3.extractors.Vido;
import com.lagradost.cloudstream3.extractors.Vidoza;
import com.lagradost.cloudstream3.extractors.Vids;
import com.lagradost.cloudstream3.extractors.Vidsonic;
import com.lagradost.cloudstream3.extractors.VinovoSi;
import com.lagradost.cloudstream3.extractors.VinovoTo;
import com.lagradost.cloudstream3.extractors.VkExtractor;
import com.lagradost.cloudstream3.extractors.Voe;
import com.lagradost.cloudstream3.extractors.Voe1;
import com.lagradost.cloudstream3.extractors.Voe2;
import com.lagradost.cloudstream3.extractors.Vtbe;
import com.lagradost.cloudstream3.extractors.Watchadsontape;
import com.lagradost.cloudstream3.extractors.Wibufile;
import com.lagradost.cloudstream3.extractors.WishembedPro;
import com.lagradost.cloudstream3.extractors.Wishfast;
import com.lagradost.cloudstream3.extractors.Wishonly;
import com.lagradost.cloudstream3.extractors.XStreamCdn;
import com.lagradost.cloudstream3.extractors.Xenolyzb;
import com.lagradost.cloudstream3.extractors.Yipsu;
import com.lagradost.cloudstream3.extractors.YourUpload;
import com.lagradost.cloudstream3.extractors.YoutubeExtractor;
import com.lagradost.cloudstream3.extractors.YoutubeMobileExtractor;
import com.lagradost.cloudstream3.extractors.YoutubeNoCookieExtractor;
import com.lagradost.cloudstream3.extractors.YoutubeShortLinkExtractor;
import com.lagradost.cloudstream3.extractors.Yufiles;
import com.lagradost.cloudstream3.extractors.Yuguaab;
import com.lagradost.cloudstream3.extractors.Zplayer;
import com.lagradost.cloudstream3.extractors.ZplayerV2;
import com.lagradost.cloudstream3.extractors.Ztreamhub;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.CodecsKt;
import io.ktor.http.URLUtilsKt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.uuid.Uuid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import org.jsoup.helper.HttpConnection;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a]\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032)\b\u0002\u0010\"\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010&0#¢\u0006\u0002\b'H\u0086@¢\u0006\u0002\u0010(\u001ae\u0010)\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010+\u001a\u00020\u00162)\b\u0002\u0010\"\u001a#\b\u0001\u0012\u0004\u0012\u00020*\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010&0#¢\u0006\u0002\b'H\u0086@¢\u0006\u0002\u0010,\u001ae\u0010)\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010+\u001a\u00020\n2)\b\u0002\u0010\"\u001a#\b\u0001\u0012\u0004\u0012\u00020*\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010&0#¢\u0006\u0002\b'H\u0087@¢\u0006\u0002\u0010-\u001a\u0010\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005\u001a\u0010\u00106\u001a\u0004\u0018\u00010\u00052\u0006\u00107\u001a\u00020\u0005\u001a\u000e\u00108\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u0005\u001a\u0016\u00109\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010:\u001a>\u0010;\u001a\u00020<2\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020%0>2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020%0>H\u0086@¢\u0006\u0002\u0010A\u001aJ\u0010;\u001a\u00020<2\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00052\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020%0>2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020%0>H\u0086@¢\u0006\u0002\u0010C\u001a\u000e\u0010I\u001a\u00020F2\u0006\u0010 \u001a\u00020\u0005\u001a\u000e\u0010J\u001a\u00020<2\u0006\u0010 \u001a\u00020\u0005\u001a\u000e\u0010K\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005\u001a \u0010L\u001a\u0004\u0018\u00010\u00052\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010O\u001a\u0012\u0010P\u001a\u00020\u0005*\u00020F2\u0006\u0010\u0004\u001a\u00020\u0005\"\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u001c\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\f\u001a\u0004\b\u0011\u0010\u000e\"\u001c\u0010\u0012\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\f\u001a\u0004\b\u0014\u0010\u000e\"\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0011\u0010\u0019\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018\"\u0011\u0010\u001b\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101\"\u000e\u00105\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020F0E¢\u0006\b\n\u0000\u001a\u0004\bG\u0010H¨\u0006Q"}, d2 = {"toUs", "", "inferTypeFromUrl", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", ImagesContract.URL, "", "INFER_TYPE", "getINFER_TYPE", "()Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "CLEARKEY_DRM_UUID", "Lkotlin/uuid/Uuid;", "getCLEARKEY_DRM_UUID$annotations", "()V", "getCLEARKEY_DRM_UUID", "()Lkotlin/uuid/Uuid;", "WIDEVINE_DRM_UUID", "getWIDEVINE_DRM_UUID$annotations", "getWIDEVINE_DRM_UUID", "PLAYREADY_DRM_UUID", "getPLAYREADY_DRM_UUID$annotations", "getPLAYREADY_DRM_UUID", "CLEARKEY_UUID", "Ljava/util/UUID;", "getCLEARKEY_UUID", "()Ljava/util/UUID;", "WIDEVINE_UUID", "getWIDEVINE_UUID", "PLAYREADY_UUID", "getPLAYREADY_UUID", "newExtractorLink", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "source", "name", "type", "initializer", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newDrmExtractorLink", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;", "uuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/UUID;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Lkotlin/uuid/Uuid;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "schemaStripRegex", "Lkotlin/text/Regex;", "getSchemaStripRegex", "()Lkotlin/text/Regex;", "getQualityFromName", "", "qualityName", "packedRegex", "getPacked", TypedValues.Custom.S_STRING, "getAndUnpack", "unshortenLinkSafe", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadExtractor", "", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractorApis", "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "getExtractorApis", "()Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "getExtractorApiFromName", "requireReferer", "httpsify", "getPostForm", "requestUrl", "html", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixUrl", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ExtractorApiKt {
    private static final Uuid CLEARKEY_DRM_UUID;
    private static final UUID CLEARKEY_UUID;
    private static final ExtractorLinkType INFER_TYPE = null;
    private static final Uuid PLAYREADY_DRM_UUID;
    private static final UUID PLAYREADY_UUID;
    private static final Uuid WIDEVINE_DRM_UUID;
    private static final UUID WIDEVINE_UUID;
    private static final AtomicMutableList<ExtractorApi> extractorApis;
    private static final Regex packedRegex;
    private static final Regex schemaStripRegex;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$getPostForm$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1361, 1363}, m = "getPostForm", n = {"requestUrl", "html", "document", "inputs", "op", "id", "mode", "hash", "requestUrl", "html", "document", "inputs", "op", "id", "mode", "hash"}, nl = {1363, 1372}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.getPostForm(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$loadExtractor$2, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, l = {927, 929, 937, 958}, m = "loadExtractor", n = {ImagesContract.URL, "referer", "subtitleCallback", "callback", ImagesContract.URL, "referer", "subtitleCallback", "callback", ImagesContract.URL, "referer", "subtitleCallback", "callback", "currentUrl", "compareUrl", "extractor", "index", ImagesContract.URL, "referer", "subtitleCallback", "callback", "currentUrl", "compareUrl", "extractor", "index"}, nl = {929, 930, 938, 959}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.loadExtractor(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {533}, m = "newDrmExtractorLink", n = {"source", "name", ImagesContract.URL, "type", "uuid", "initializer", "builder"}, nl = {534}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C09241 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C09241(Continuation<? super C09241> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.newDrmExtractorLink((String) null, (String) null, (String) null, (ExtractorLinkType) null, (UUID) null, (Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object>) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {556}, m = "newDrmExtractorLink", n = {"source", "name", ImagesContract.URL, "type", "uuid", "initializer", "builder"}, nl = {557}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C09263 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C09263(Continuation<? super C09263> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.newDrmExtractorLink((String) null, (String) null, (String) null, (ExtractorLinkType) null, (Uuid) null, (Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object>) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newExtractorLink$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 0, 0}, l = {TypedValues.PositionType.TYPE_PERCENT_X}, m = "newExtractorLink", n = {"source", "name", ImagesContract.URL, "type", "initializer", "builder"}, nl = {TypedValues.PositionType.TYPE_PERCENT_Y}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C09271 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C09271(Continuation<? super C09271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.newExtractorLink(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$unshortenLinkSafe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt", f = "ExtractorApi.kt", i = {0}, l = {894}, m = "unshortenLinkSafe", n = {ImagesContract.URL}, nl = {895}, s = {"L$0"}, v = 2)
    static final class C09291 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09291(Continuation<? super C09291> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApiKt.unshortenLinkSafe(null, this);
        }
    }

    public static /* synthetic */ void getCLEARKEY_DRM_UUID$annotations() {
    }

    public static /* synthetic */ void getPLAYREADY_DRM_UUID$annotations() {
    }

    public static /* synthetic */ void getWIDEVINE_DRM_UUID$annotations() {
    }

    public static final long toUs(long j) {
        return j * 1000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExtractorLinkType inferTypeFromUrl(String str) {
        String strDecodeURLPart$default;
        try {
            strDecodeURLPart$default = CodecsKt.decodeURLPart$default(URLUtilsKt.Url(str).getEncodedPath(), 0, 0, null, 7, null);
        } catch (Throwable unused) {
            strDecodeURLPart$default = null;
        }
        if (strDecodeURLPart$default != null && StringsKt.endsWith$default(strDecodeURLPart$default, ".m3u8", false, 2, (Object) null)) {
            return ExtractorLinkType.M3U8;
        }
        if (strDecodeURLPart$default != null && StringsKt.endsWith$default(strDecodeURLPart$default, ".mpd", false, 2, (Object) null)) {
            return ExtractorLinkType.DASH;
        }
        if (strDecodeURLPart$default == null || !StringsKt.endsWith$default(strDecodeURLPart$default, ".torrent", false, 2, (Object) null)) {
            return StringsKt.startsWith$default(str, "magnet:", false, 2, (Object) null) ? ExtractorLinkType.MAGNET : ExtractorLinkType.VIDEO;
        }
        return ExtractorLinkType.TORRENT;
    }

    public static final ExtractorLinkType getINFER_TYPE() {
        return INFER_TYPE;
    }

    static {
        Uuid uuidFromLongs = Uuid.INSTANCE.fromLongs(-2129748144642739255L, 8654423357094679310L);
        CLEARKEY_DRM_UUID = uuidFromLongs;
        Uuid uuidFromLongs2 = Uuid.INSTANCE.fromLongs(-1301668207276963122L, -6645017420763422227L);
        WIDEVINE_DRM_UUID = uuidFromLongs2;
        Uuid uuidFromLongs3 = Uuid.INSTANCE.fromLongs(-7348484286925749626L, -6083546864340672619L);
        PLAYREADY_DRM_UUID = uuidFromLongs3;
        CLEARKEY_UUID = new UUID(uuidFromLongs.getMostSignificantBits(), uuidFromLongs.getLeastSignificantBits());
        WIDEVINE_UUID = new UUID(uuidFromLongs2.getMostSignificantBits(), uuidFromLongs2.getLeastSignificantBits());
        PLAYREADY_UUID = new UUID(uuidFromLongs3.getMostSignificantBits(), uuidFromLongs3.getLeastSignificantBits());
        schemaStripRegex = new Regex("^(https:|)//(www\\.|)");
        packedRegex = new Regex("eval\\(function\\(p,a,c,k,e,.*\\)\\)");
        extractorApis = Coroutines.INSTANCE.atomicListOf(new Mp4Upload(), new StreamTape(), new StreamTapeNet(), new ShaveTape(), new StreamTapeXyz(), new Watchadsontape(), new MixDropBz(), new MixDropCh(), new MixDropTo(), new MixDropAg(), new MixDrop(), new MixDropPs(), new Mdy(), new MxDropTo(), new MixDropSi(), new XStreamCdn(), new StreamSB(), new Sblona(), new Vidgomunimesb(), new StreamSilk(), new StreamSB1(), new StreamSB2(), new StreamSB3(), new StreamSB4(), new StreamSB5(), new StreamSB6(), new StreamSB7(), new StreamSB8(), new StreamSB9(), new StreamSB10(), new StreamSB11(), new SBfull(), new Streamhub2(), new Ssbstream(), new Sbthe(), new Vidgomunime(), new Sbflix(), new Streamsss(), new Sbspeed(), new Sbsonic(), new Sbface(), new Sbrapid(), new Lvturbo(), new Fastream(), new Videa(), new FEmbed(), new FeHD(), new Fplayer(), new DBfilm(), new Luxubu(), new LayarKaca(), new Rasacintaku(), new FEnet(), new Kotakajair(), new Cdnplayer(), new Uqload(), new Uqload1(), new Uqload2(), new Uqloadcx(), new Uqloadbz(), new Evoload(), new Evoload1(), new UpstreamExtractor(), new Odnoklassniki(), new TauVideo(), new SibNet(), new ContentX(), new Hotlinger(), new FourCX(), new PlayRu(), new FourPlayRu(), new Pichive(), new FourPichive(), new HDMomPlayer(), new HDPlayerSystem(), new VideoSeyred(), new PeaceMakerst(), new HDStreamAble(), new RapidVid(), new TRsTX(), new VidMoxy(), new Sobreatsesuyp(), new PixelDrain(), new PixelDrainDev(), new MailRu(), new OkRuSSL(), new OkRuSSLMobile(), new OkRuHTTP(), new OkRuHTTPMobile(), new Sendvid(), new DoodCxExtractor(), new DoodPmExtractor(), new DoodToExtractor(), new DoodSoExtractor(), new DoodLaExtractor(), new Dooood(), new D0000d(), new D000dCom(), new DoodstreamCom(), new DoodWsExtractor(), new DoodShExtractor(), new DoodWatchExtractor(), new DoodWfExtractor(), new DoodYtExtractor(), new Doodspro(), new Dsvplay(), new Zplayer(), new ZplayerV2(), new Upstream(), new Maxstream(), new Tantifilm(), new Userload(), new Supervideo(), new Streamcash(), new PlayerVoxzer(), new Blogger(), new YourUpload(), new Hxfile(), new KotakAnimeid(), new Neonime8n(), new Neonime7n(), new Yufiles(), new Aico(), new JWPlayer(), new Meownime(), new DesuArcg(), new DesuOdchan(), new DesuOdvip(), new DesuDrive(), new Keephealth(), new Sbnet(), new Sbasian(), new Sblongvu(), new Fembed9hd(), new StreamM4u(), new Krakenfiles(), new Gofile(), new Vicloud(), new Uservideo(), new Userscloud(), new Movhide(), new StreamhideCom(), new StreamhideTo(), new Wibufile(), new FileMoonIn(), new Moviesm4u(), new Filesim(), new Multimoviesshg(), new Ahvsh(), new Guccihide(), new FileMoon(), new FileMoonSx(), new FilemoonV2(), new Vido(), new Linkbox(), new Acefile(), new Embedgram(), new Mvidoo(), new Streamplay(), new Vidmoly(), new Vidmolyme(), new Vidmolyto(), new Vidmolybiz(), new Voe(), new Voe1(), new Voe2(), new Tubeless(), new Moviehab(), new MoviehabNet(), new Jeniusplay(), new StreamoUpload(), new Vidara(), new Vidavaca(), new Vidaraa(), new Vidaraw(), new Vidarax(), new VidaraSo(), new Vidaratem(), new VidaaraxCom(), new VidaaraxNet(), new GamoVideo(), new Gdriveplayerapi(), new Gdriveplayerapp(), new Gdriveplayerfun(), new Gdriveplayerio(), new Gdriveplayerme(), new Gdriveplayerbiz(), new Gdriveplayerorg(), new Gdriveplayerus(), new Gdriveplayerco(), new GoodstreamExtractor(), new Gdriveplayer(), new DatabaseGdrive(), new DatabaseGdrive2(), new Mediafire(), new YoutubeExtractor(), new YoutubeShortLinkExtractor(), new YoutubeMobileExtractor(), new YoutubeNoCookieExtractor(), new Streamlare(), new PlayLtXyz(), new Cda(), new Dailymotion(), new Ztreamhub(), new Rabbitstream(), new Dokicloud(), new Megacloud(), new VidhideExtractor(), new VidHidePro(), new VidHidePro1(), new VidHidePro2(), new VidHidePro3(), new VidHidePro4(), new VidHidePro5(), new VidHidePro6(), new VidHideHub(), new Ryderjet(), new VidNest(), new Dhtpre(), new Dhcplay(), new HglinkTo(), new HgplayCDN(), new Habetar(), new Yuguaab(), new Guxhag(), new Auvexiug(), new Xenolyzb(), new Haxloppd(), new Cavanhabg(), new Dumbalag(), new Uasopt(), new Smoothpre(), new Peytonepre(), new LuluStream(), new Lulustream1(), new Lulustream2(), new Luluvdoo(), new StreamWishExtractor(), new StreamHLS(), new BigwarpIO(), new BigwarpArt(), new BgwpCC(), new WishembedPro(), new CdnwishCom(), new FlaswishCom(), new SfastwishCom(), new Playerwish(), new StreamEmbed(), new EmturbovidExtractor(), new Vtbe(), new SecvideoOnline(), new FsstOnline(), new CsstOnline(), new DsstOnline(), new Simpulumlamerop(), new Urochsunloath(), new NathanFromSubject(), new Yipsu(), new MetaGnathTuggers(), new Geodailymotion(), new Mwish(), new Hgcloudto(), new Dwish(), new Ewish(), new Kswplayer(), new Wishfast(), new Streamwish2(), new Strwish(), new Strwish2(), new Awish(), new Obeywish(), new Jodwish(), new Swhoi(), new Multimovies(), new UqloadsXyz(), new Doodporn(), new Asnwish(), new Nekowish(), new Nekostream(), new Swdyu(), new Wishonly(), new Ds2play(), new Ds2video(), new Vidsonic(), new InternetArchive(), new VidStack(), new GDMirrorbot(), new Techinmind(), new Server1uns(), new VinovoSi(), new VinovoTo(), new Vidoza(), new Videzz(), new CloudMailRu(), new HubCloud(), new VkExtractor(), new Bysezejataos(), new ByseSX(), new ByseVepoin(), new ByseBuho(), new MyVidPlay(), new Playmogo(), new Vide0Net(), new Up4Stream(), new Up4FunTop(), new GUpload(), new HlsWish(), new ByseQekaho(), new Flyfile(), new Firestream(), new Vids());
    }

    public static final Uuid getCLEARKEY_DRM_UUID() {
        return CLEARKEY_DRM_UUID;
    }

    public static final Uuid getWIDEVINE_DRM_UUID() {
        return WIDEVINE_DRM_UUID;
    }

    public static final Uuid getPLAYREADY_DRM_UUID() {
        return PLAYREADY_DRM_UUID;
    }

    public static final UUID getCLEARKEY_UUID() {
        return CLEARKEY_UUID;
    }

    public static final UUID getWIDEVINE_UUID() {
        return WIDEVINE_UUID;
    }

    public static final UUID getPLAYREADY_UUID() {
        return PLAYREADY_UUID;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newExtractorLink(String str, String str2, String str3, ExtractorLinkType extractorLinkType, Function2<? super ExtractorLink, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ExtractorLink> continuation) {
        C09271 c09271;
        if (continuation instanceof C09271) {
            c09271 = (C09271) continuation;
            if ((c09271.label & Integer.MIN_VALUE) != 0) {
                c09271.label -= Integer.MIN_VALUE;
            } else {
                c09271 = new C09271(continuation);
            }
        } else {
            c09271 = new C09271(continuation);
        }
        Object obj = c09271.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09271.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ExtractorLink extractorLink = (ExtractorLink) c09271.L$5;
            ResultKt.throwOnFailure(obj);
            return extractorLink;
        }
        ResultKt.throwOnFailure(obj);
        ExtractorLink extractorLink2 = new ExtractorLink(str, str2, str3, (String) null, (Integer) null, extractorLinkType == null ? INFER_TYPE : extractorLinkType, (Map) null, (String) null, 216, (DefaultConstructorMarker) null);
        c09271.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c09271.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c09271.L$2 = SpillingKt.nullOutSpilledVariable(str3);
        c09271.L$3 = SpillingKt.nullOutSpilledVariable(extractorLinkType);
        c09271.L$4 = SpillingKt.nullOutSpilledVariable(function2);
        c09271.L$5 = extractorLink2;
        c09271.label = 1;
        return function2.invoke(extractorLink2, c09271) == coroutine_suspended ? coroutine_suspended : extractorLink2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newExtractorLink$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt$newExtractorLink$2", f = "ExtractorApi.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    public static final class C09282 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        int label;

        C09282(Continuation<? super C09282> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09282(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((C09282) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newExtractorLink$default(String str, String str2, String str3, ExtractorLinkType extractorLinkType, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            extractorLinkType = null;
        }
        if ((i & 16) != 0) {
            function2 = new C09282(null);
        }
        return newExtractorLink(str, str2, str3, extractorLinkType, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newDrmExtractorLink(String str, String str2, String str3, ExtractorLinkType extractorLinkType, UUID uuid, Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super DrmExtractorLink> continuation) {
        C09241 c09241;
        if (continuation instanceof C09241) {
            c09241 = (C09241) continuation;
            if ((c09241.label & Integer.MIN_VALUE) != 0) {
                c09241.label -= Integer.MIN_VALUE;
            } else {
                c09241 = new C09241(continuation);
            }
        } else {
            c09241 = new C09241(continuation);
        }
        Object obj = c09241.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09241.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            DrmExtractorLink drmExtractorLink = (DrmExtractorLink) c09241.L$6;
            ResultKt.throwOnFailure(obj);
            return drmExtractorLink;
        }
        ResultKt.throwOnFailure(obj);
        DrmExtractorLink drmExtractorLink2 = new DrmExtractorLink(str, str2, str3, (String) null, (Integer) null, extractorLinkType == null ? INFER_TYPE : extractorLinkType, (Map) null, (String) null, (String) null, (String) null, Uuid.INSTANCE.fromLongs(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()), (String) null, (HashMap) null, (String) null, 15320, (DefaultConstructorMarker) null);
        c09241.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c09241.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c09241.L$2 = SpillingKt.nullOutSpilledVariable(str3);
        c09241.L$3 = SpillingKt.nullOutSpilledVariable(extractorLinkType);
        c09241.L$4 = SpillingKt.nullOutSpilledVariable(uuid);
        c09241.L$5 = SpillingKt.nullOutSpilledVariable(function2);
        c09241.L$6 = drmExtractorLink2;
        c09241.label = 1;
        return function2.invoke(drmExtractorLink2, c09241) == coroutine_suspended ? coroutine_suspended : drmExtractorLink2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$2", f = "ExtractorApi.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09252 extends SuspendLambda implements Function2<DrmExtractorLink, Continuation<? super Unit>, Object> {
        int label;

        C09252(Continuation<? super C09252> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09252(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DrmExtractorLink drmExtractorLink, Continuation<? super Unit> continuation) {
            return ((C09252) create(drmExtractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newDrmExtractorLink$default(String str, String str2, String str3, ExtractorLinkType extractorLinkType, UUID uuid, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            extractorLinkType = null;
        }
        if ((i & 32) != 0) {
            function2 = new C09252(null);
        }
        return newDrmExtractorLink(str, str2, str3, extractorLinkType, uuid, (Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object>) function2, (Continuation<? super DrmExtractorLink>) continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newDrmExtractorLink(String str, String str2, String str3, ExtractorLinkType extractorLinkType, Uuid uuid, Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super DrmExtractorLink> continuation) {
        C09263 c09263;
        if (continuation instanceof C09263) {
            c09263 = (C09263) continuation;
            if ((c09263.label & Integer.MIN_VALUE) != 0) {
                c09263.label -= Integer.MIN_VALUE;
            } else {
                c09263 = new C09263(continuation);
            }
        } else {
            c09263 = new C09263(continuation);
        }
        Object obj = c09263.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09263.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            DrmExtractorLink drmExtractorLink = (DrmExtractorLink) c09263.L$6;
            ResultKt.throwOnFailure(obj);
            return drmExtractorLink;
        }
        ResultKt.throwOnFailure(obj);
        DrmExtractorLink drmExtractorLink2 = new DrmExtractorLink(str, str2, str3, (String) null, (Integer) null, extractorLinkType == null ? INFER_TYPE : extractorLinkType, (Map) null, (String) null, (String) null, (String) null, uuid, (String) null, (HashMap) null, (String) null, 15320, (DefaultConstructorMarker) null);
        c09263.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c09263.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c09263.L$2 = SpillingKt.nullOutSpilledVariable(str3);
        c09263.L$3 = SpillingKt.nullOutSpilledVariable(extractorLinkType);
        c09263.L$4 = SpillingKt.nullOutSpilledVariable(uuid);
        c09263.L$5 = SpillingKt.nullOutSpilledVariable(function2);
        c09263.L$6 = drmExtractorLink2;
        c09263.label = 1;
        return function2.invoke(drmExtractorLink2, c09263) == coroutine_suspended ? coroutine_suspended : drmExtractorLink2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$4, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt$newDrmExtractorLink$4", f = "ExtractorApi.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<DrmExtractorLink, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DrmExtractorLink drmExtractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(drmExtractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newDrmExtractorLink$default(String str, String str2, String str3, ExtractorLinkType extractorLinkType, Uuid uuid, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            extractorLinkType = null;
        }
        if ((i & 32) != 0) {
            function2 = new AnonymousClass4(null);
        }
        return newDrmExtractorLink(str, str2, str3, extractorLinkType, uuid, (Function2<? super DrmExtractorLink, ? super Continuation<? super Unit>, ? extends Object>) function2, (Continuation<? super DrmExtractorLink>) continuation);
    }

    public static final Regex getSchemaStripRegex() {
        return schemaStripRegex;
    }

    public static final int getQualityFromName(String str) {
        if (str == null) {
            return Qualities.Unknown.getValue();
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String string = StringsKt.trim((CharSequence) StringsKt.replace$default(lowerCase, TtmlNode.TAG_P, "", false, 4, (Object) null)).toString();
        Qualities qualities = Intrinsics.areEqual(string, "4k") ? Qualities.P2160 : null;
        if (qualities != null) {
            return qualities.getValue();
        }
        Integer intOrNull = StringsKt.toIntOrNull(string);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        return Qualities.Unknown.getValue();
    }

    public static final String getPacked(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        MatchResult matchResultFind$default = Regex.find$default(packedRegex, string, 0, 2, null);
        if (matchResultFind$default != null) {
            return matchResultFind$default.getValue();
        }
        return null;
    }

    public static final String getAndUnpack(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        String strUnpack = new JsUnpacker(getPacked(string)).unpack();
        return strUnpack == null ? string : strUnpack;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object unshortenLinkSafe(String str, Continuation<? super String> continuation) {
        C09291 c09291;
        Exception exc;
        if (continuation instanceof C09291) {
            c09291 = (C09291) continuation;
            if ((c09291.label & Integer.MIN_VALUE) != 0) {
                c09291.label -= Integer.MIN_VALUE;
            } else {
                c09291 = new C09291(continuation);
            }
        } else {
            c09291 = new C09291(continuation);
        }
        C09291 c09292 = c09291;
        Object objUnshorten$default = c09292.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09292.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objUnshorten$default);
            try {
                if (!ShortLink.INSTANCE.isShortLink(str)) {
                    return str;
                }
                ShortLink shortLink = ShortLink.INSTANCE;
                c09292.L$0 = str;
                c09292.label = 1;
                try {
                    objUnshorten$default = ShortLink.unshorten$default(shortLink, str, null, c09292, 2, null);
                    if (objUnshorten$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str = str;
                } catch (Exception e) {
                    exc = e;
                    str = str;
                }
            } catch (Exception e2) {
                e = e2;
                exc = e;
            }
            ArchComponentExtKt.logError(exc);
            return str;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        str = (String) c09292.L$0;
        try {
            ResultKt.throwOnFailure(objUnshorten$default);
        } catch (Exception e3) {
            e = e3;
            exc = e;
            ArchComponentExtKt.logError(exc);
            return str;
        }
        return (String) objUnshorten$default;
    }

    public static final Object loadExtractor(String str, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Boolean> continuation) {
        return loadExtractor(str, null, function1, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0126  */
    /* JADX WARN: Code duplicated, block: B:49:0x018e  */
    /* JADX WARN: Code duplicated, block: B:50:0x018f A[LOOP:0: B:37:0x0120->B:50:0x018f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:53:0x01af  */
    /* JADX WARN: Code duplicated, block: B:63:0x0213  */
    /* JADX WARN: Code duplicated, block: B:64:0x0214 A[LOOP:1: B:52:0x01ad->B:64:0x0214, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x01cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x01a2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x017a, code lost:
    
        if (r8.getUrl(r14, r10, r9, r2, r7) == r1) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0200, code lost:
    
        if (r3.getUrl(r14, r4, r5, r6, r7) == r1) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object loadExtractor(java.lang.String r19, java.lang.String r20, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r21, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r22, kotlin.coroutines.Continuation<? super java.lang.Boolean> r23) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.ExtractorApiKt.loadExtractor(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object loadExtractor$default(String str, String str2, Function1 function1, Function1 function2, Continuation continuation, int i, Object obj) throws CancellationException {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return loadExtractor(str, str2, function1, function2, continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApiKt$loadExtractor$3, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApiKt$loadExtractor$3", f = "ExtractorApi.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScopeKt.ensureActive(coroutineScope);
            return Unit.INSTANCE;
        }
    }

    public static final AtomicMutableList<ExtractorApi> getExtractorApis() {
        return extractorApis;
    }

    public static final ExtractorApi getExtractorApiFromName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        for (ExtractorApi extractorApi : extractorApis) {
            if (Intrinsics.areEqual(extractorApi.getName(), name)) {
                return extractorApi;
            }
        }
        return extractorApis.get(0);
    }

    public static final boolean requireReferer(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getExtractorApiFromName(name).getRequiresReferer();
    }

    public static final String httpsify(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (!StringsKt.startsWith$default(url, "//", false, 2, (Object) null)) {
            return url;
        }
        return "https:" + url;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x010a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object getPostForm(String str, String str2, Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
        Document document;
        Elements elementsSelect;
        String str3;
        String str4;
        String str5;
        int i;
        String str6;
        String str7;
        String str8;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        int i3 = 2;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = null;
            document = Ksoup.parse$default(Ksoup.INSTANCE, str2, null, 2, null);
            elementsSelect = document.select("Form > input");
            if (elementsSelect.size() < 4) {
                return null;
            }
            Iterator<Element> it = elementsSelect.iterator();
            String str9 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            while (it.hasNext()) {
                Object obj3 = obj2;
                Element next = it.next();
                int i4 = i3;
                String strAttr = next.attr(ES6Iterator.VALUE_PROPERTY);
                Iterator<Element> it2 = it;
                String strAttr2 = next.attr("name");
                int iHashCode = strAttr2.hashCode();
                if (iHashCode != 3355) {
                    if (iHashCode != 3553) {
                        if (iHashCode != 3195150) {
                            if (iHashCode == 3357091 && strAttr2.equals("mode")) {
                                str3 = strAttr;
                            }
                        } else if (strAttr2.equals("hash")) {
                            str9 = strAttr;
                        }
                    } else if (strAttr2.equals("op")) {
                        str5 = strAttr;
                    }
                } else if (strAttr2.equals("id")) {
                    str4 = strAttr;
                }
                i3 = i4;
                obj2 = obj3;
                it = it2;
            }
            Object obj4 = obj2;
            i = i3;
            if (str5 == null || str4 == null || str3 == null || str9 == null) {
                return obj4;
            }
            anonymousClass1.L$0 = str;
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(document);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(elementsSelect);
            anonymousClass1.L$4 = str5;
            anonymousClass1.L$5 = str4;
            anonymousClass1.L$6 = str3;
            anonymousClass1.L$7 = str9;
            anonymousClass1.label = 1;
            String str10 = str9;
            if (DelayKt.delay(5000L, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            str6 = str;
            str7 = str10;
            str8 = str2;
        } else {
            if (i2 == 1) {
                String str11 = (String) anonymousClass1.L$7;
                str3 = (String) anonymousClass1.L$6;
                str4 = (String) anonymousClass1.L$5;
                str5 = (String) anonymousClass1.L$4;
                elementsSelect = (Elements) anonymousClass1.L$3;
                document = (Document) anonymousClass1.L$2;
                str8 = (String) anonymousClass1.L$1;
                String str12 = (String) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                str7 = str11;
                str6 = str12;
                i = 2;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return ((NiceResponse) obj).getText();
        }
        Requests app2 = MainActivityKt.getApp();
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("content-type", HttpConnection.FORM_URL_ENCODED);
        pairArr[1] = TuplesKt.to("referer", str6);
        pairArr[i] = TuplesKt.to("user-agent", MainAPIKt.USER_AGENT);
        pairArr[3] = TuplesKt.to("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        Map mapMapOf = MapsKt.mapOf(pairArr);
        Pair[] pairArr2 = new Pair[4];
        pairArr2[0] = TuplesKt.to("op", str5);
        pairArr2[1] = TuplesKt.to("id", str4);
        pairArr2[i] = TuplesKt.to("mode", str3);
        pairArr2[3] = TuplesKt.to("hash", str7);
        Map mapMapOf2 = MapsKt.mapOf(pairArr2);
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str6);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str8);
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(document);
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(elementsSelect);
        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass1.label = i;
        Object objPost$default = Requests.post$default(app2, str6, mapMapOf, null, null, null, mapMapOf2, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 65500, null);
        if (objPost$default == coroutine_suspended) {
            return coroutine_suspended;
        }
        obj = objPost$default;
        return ((NiceResponse) obj).getText();
    }

    public static final String fixUrl(ExtractorApi extractorApi, String url) {
        Intrinsics.checkNotNullParameter(extractorApi, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith$default(url, "http", false, 2, (Object) null) || StringsKt.startsWith$default(url, "{\"", false, 2, (Object) null)) {
            return url;
        }
        String str = url;
        if (str.length() == 0) {
            return "";
        }
        if (StringsKt.startsWith$default(url, "//", false, 2, (Object) null)) {
            return "https:" + url;
        }
        if (StringsKt.startsWith$default((CharSequence) str, '/', false, 2, (Object) null)) {
            return extractorApi.getMainUrl() + url;
        }
        return extractorApi.getMainUrl() + '/' + url;
    }
}
