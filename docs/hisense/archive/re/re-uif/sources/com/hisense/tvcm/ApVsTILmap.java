package com.hisense.tvcm;

import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ApVsTILmap {
    private static final int[][] mRegionCountry;
    private static HashMap<String, Integer> mLanguageMap = new HashMap<>();
    private static HashMap<String, Integer> mCountryMap = new HashMap<>();
    private static HashMap<String, Integer> mLogoMap = new HashMap<>();
    private static HashMap<Integer, String> mLogoNameMap = new HashMap<>();
    private static final int[][][] array = {new int[][]{new int[]{FacPropertyDemo.AudioMode.STANDARD.ordinal(), 0}, new int[]{FacPropertyDemo.AudioMode.THEATER.ordinal(), 1}, new int[]{FacPropertyDemo.AudioMode.MUSIC.ordinal(), 2}, new int[]{FacPropertyDemo.AudioMode.SPEECH.ordinal(), 3}, new int[]{FacPropertyDemo.AudioMode.LATE_NIGHT.ordinal(), 4}}, new int[][]{new int[]{FacPropertyDemo.PictureMode.VIVID.ordinal(), 0}, new int[]{FacPropertyDemo.PictureMode.STANDARD.ordinal(), 1}, new int[]{FacPropertyDemo.PictureMode.ENERGY_SAVING.ordinal(), 2}, new int[]{FacPropertyDemo.PictureMode.THEATER.ordinal(), 3}, new int[]{FacPropertyDemo.PictureMode.GAME.ordinal(), 4}, new int[]{FacPropertyDemo.PictureMode.SPORT.ordinal(), 5}, new int[]{FacPropertyDemo.PictureMode.CALIBRATED.ordinal(), 6}}, new int[][]{new int[]{FacPropertyDemo.ModelPanelGroup.B1.ordinal(), 0}, new int[]{FacPropertyDemo.ModelPanelGroup.B2.ordinal(), 1}, new int[]{FacPropertyDemo.ModelPanelGroup.B3.ordinal(), 2}}, new int[][]{new int[]{FacPropertyDemo.ColorTemperature.STANDARD.ordinal(), 0}, new int[]{FacPropertyDemo.ColorTemperature.COOL.ordinal(), 3}, new int[]{FacPropertyDemo.ColorTemperature.WARM.ordinal(), 5}}, new int[0][], new int[0][], new int[][]{new int[]{FacPropertyDemo.ZoomMode.AUTO.ordinal(), 0}, new int[]{FacPropertyDemo.ZoomMode.NORMAL.ordinal(), 1}, new int[]{FacPropertyDemo.ZoomMode.ZOOM.ordinal(), 2}, new int[]{FacPropertyDemo.ZoomMode.WIDE.ordinal(), 3}, new int[]{FacPropertyDemo.ZoomMode.DIRECT.ordinal(), 4}, new int[]{FacPropertyDemo.ZoomMode.DOTYBYDOT.ordinal(), 5}, new int[]{FacPropertyDemo.ZoomMode.PANARAMIC.ordinal(), 6}, new int[]{FacPropertyDemo.ZoomMode.CINEMA.ordinal(), 7}}, new int[0][], new int[][]{new int[]{FacPropertyDemo.SourceEnum.TV.ordinal(), 0}, new int[]{FacPropertyDemo.SourceEnum.AV.ordinal(), 1}, new int[]{FacPropertyDemo.SourceEnum.HDMI1.ordinal(), 2}, new int[]{FacPropertyDemo.SourceEnum.HDMI2.ordinal(), 3}, new int[]{FacPropertyDemo.SourceEnum.HDMI3.ordinal(), 4}}, new int[0][], new int[][]{new int[]{FacPropertyDemo.Logo.his.ordinal(), 1}, new int[]{FacPropertyDemo.Logo.vuz.ordinal(), 23}, new int[]{FacPropertyDemo.Logo.lca.ordinal(), 259}, new int[]{FacPropertyDemo.Logo.shp.ordinal(), 49}}, new int[][]{new int[]{FacPropertyDemo.Country.USA.ordinal(), 59}, new int[]{FacPropertyDemo.Country.CAN.ordinal(), 60}, new int[]{FacPropertyDemo.Country.MEX.ordinal(), 61}}, new int[][]{new int[]{FacPropertyDemo.Language.eng.ordinal(), 1}, new int[]{FacPropertyDemo.Language.fre.ordinal(), 2}, new int[]{FacPropertyDemo.Language.spa.ordinal(), 3}, new int[]{FacPropertyDemo.Language.ger.ordinal(), 17}, new int[]{FacPropertyDemo.Language.zho.ordinal(), 16}, new int[]{FacPropertyDemo.Language.chi.ordinal(), 7}}, new int[][]{new int[]{FacPropertyDemo.Region.REGION1.ordinal(), 0}, new int[]{FacPropertyDemo.Region.REGION2.ordinal(), 1}, new int[]{FacPropertyDemo.Region.REGION3.ordinal(), 2}, new int[]{FacPropertyDemo.Region.REGION4.ordinal(), 3}, new int[]{FacPropertyDemo.Region.REGION5.ordinal(), 4}, new int[]{FacPropertyDemo.Region.REGION6.ordinal(), 5}, new int[]{FacPropertyDemo.Region.REGION7.ordinal(), 6}}, new int[][]{new int[]{FacPropertyDemo.CountryForRegion0.UZB.ordinal(), 30}, new int[]{FacPropertyDemo.CountryForRegion0.KGZ.ordinal(), 31}, new int[]{FacPropertyDemo.CountryForRegion0.KAZ.ordinal(), 85}, new int[]{FacPropertyDemo.CountryForRegion0.ARM.ordinal(), 37}, new int[]{FacPropertyDemo.CountryForRegion0.AZE.ordinal(), 35}, new int[]{FacPropertyDemo.CountryForRegion0.GEO.ordinal(), 36}, new int[]{FacPropertyDemo.CountryForRegion0.TKM.ordinal(), 32}, new int[]{FacPropertyDemo.CountryForRegion0.ISR.ordinal(), 49}, new int[]{FacPropertyDemo.CountryForRegion0.MDA.ordinal(), 93}, new int[]{FacPropertyDemo.CountryForRegion0.TJK.ordinal(), 90}, new int[]{FacPropertyDemo.CountryForRegion0.BLR.ordinal(), 92}, new int[]{FacPropertyDemo.CountryForRegion0.TUR.ordinal(), 33}, new int[]{FacPropertyDemo.CountryForRegion0.AFG.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED}, new int[]{FacPropertyDemo.CountryForRegion0.RUS.ordinal(), 29}, new int[]{FacPropertyDemo.CountryForRegion0.UKR.ordinal(), 34}, new int[]{FacPropertyDemo.CountryForRegion0.SRB.ordinal(), 91}}, new int[][]{new int[]{FacPropertyDemo.CountryForRegion1.SAU.ordinal(), 41}, new int[]{FacPropertyDemo.CountryForRegion1.ARE.ordinal(), 42}, new int[]{FacPropertyDemo.CountryForRegion1.KWT.ordinal(), 43}, new int[]{FacPropertyDemo.CountryForRegion1.QAT.ordinal(), 46}, new int[]{FacPropertyDemo.CountryForRegion1.JOR.ordinal(), 47}, new int[]{FacPropertyDemo.CountryForRegion1.BHR.ordinal(), 104}, new int[]{FacPropertyDemo.CountryForRegion1.OMN.ordinal(), 45}, new int[]{FacPropertyDemo.CountryForRegion1.LBN.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT}, new int[]{FacPropertyDemo.CountryForRegion1.IRN.ordinal(), 48}, new int[]{FacPropertyDemo.CountryForRegion1.IRQ.ordinal(), 40}, new int[]{FacPropertyDemo.CountryForRegion1.LBY.ordinal(), 5}, new int[]{FacPropertyDemo.CountryForRegion1.YEM.ordinal(), 44}, new int[]{FacPropertyDemo.CountryForRegion1.SYR.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE}, new int[]{FacPropertyDemo.CountryForRegion1.MAR.ordinal(), 3}, new int[]{FacPropertyDemo.CountryForRegion1.DZA.ordinal(), 23}, new int[]{FacPropertyDemo.CountryForRegion1.EGY.ordinal(), 2}, new int[]{FacPropertyDemo.CountryForRegion1.SDN.ordinal(), 18}, new int[]{FacPropertyDemo.CountryForRegion1.TUN.ordinal(), 97}, new int[]{FacPropertyDemo.CountryForRegion1.GAB.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT}, new int[]{FacPropertyDemo.CountryForRegion1.NGA.ordinal(), 4}, new int[]{FacPropertyDemo.CountryForRegion1.SEN.ordinal(), 96}, new int[]{FacPropertyDemo.CountryForRegion1.MRT.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON}, new int[]{FacPropertyDemo.CountryForRegion1.CIV.ordinal(), 8}, new int[]{FacPropertyDemo.CountryForRegion1.MLI.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING}, new int[]{FacPropertyDemo.CountryForRegion1.NER.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC}, new int[]{FacPropertyDemo.CountryForRegion1.BFA.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN}, new int[]{FacPropertyDemo.CountryForRegion1.GMB.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW}, new int[]{FacPropertyDemo.CountryForRegion1.TCD.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF}, new int[]{FacPropertyDemo.CountryForRegion1.CMR.ordinal(), 13}, new int[]{FacPropertyDemo.CountryForRegion1.GIN.ordinal(), 94}, new int[]{FacPropertyDemo.CountryForRegion1.SOM.ordinal(), 98}, new int[]{FacPropertyDemo.CountryForRegion1.DJI.ordinal(), 19}, new int[]{FacPropertyDemo.CountryForRegion1.UGA.ordinal(), 15}, new int[]{FacPropertyDemo.CountryForRegion1.GHA.ordinal(), 6}, new int[]{FacPropertyDemo.CountryForRegion1.LBR.ordinal(), 11}, new int[]{FacPropertyDemo.CountryForRegion1.SLE.ordinal(), 7}}, new int[][]{new int[]{FacPropertyDemo.CountryForRegion2.ZAF.ordinal(), 24}, new int[]{FacPropertyDemo.CountryForRegion2.ZWE.ordinal(), 14}, new int[]{FacPropertyDemo.CountryForRegion2.ETH.ordinal(), 20}, new int[]{FacPropertyDemo.CountryForRegion2.KEN.ordinal(), 80}, new int[]{FacPropertyDemo.CountryForRegion2.COD.ordinal(), 12}, new int[]{FacPropertyDemo.CountryForRegion2.COG.ordinal(), 79}, new int[]{FacPropertyDemo.CountryForRegion2.ERI.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT}, new int[]{FacPropertyDemo.CountryForRegion2.RWA.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT}, new int[]{FacPropertyDemo.CountryForRegion2.TZA.ordinal(), 16}, new int[]{FacPropertyDemo.CountryForRegion2.AGO.ordinal(), 9}, new int[]{FacPropertyDemo.CountryForRegion2.MDG.ordinal(), 22}, new int[]{FacPropertyDemo.CountryForRegion2.BEN.ordinal(), 10}, new int[]{FacPropertyDemo.CountryForRegion2.MUS.ordinal(), 17}, new int[]{FacPropertyDemo.CountryForRegion2.REU.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP}, new int[]{FacPropertyDemo.CountryForRegion2.MYT.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE}, new int[]{FacPropertyDemo.CountryForRegion2.ZMB.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON}, new int[]{FacPropertyDemo.CountryForRegion2.MWI.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT}, new int[]{FacPropertyDemo.CountryForRegion2.MOZ.ordinal(), 21}, new int[]{FacPropertyDemo.CountryForRegion2.NAM.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF}, new int[]{FacPropertyDemo.CountryForRegion2.BWA.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS}, new int[]{FacPropertyDemo.CountryForRegion2.SYC.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC}, new int[]{FacPropertyDemo.CountryForRegion2.SSD.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED}}, new int[][]{new int[]{FacPropertyDemo.CountryForRegion3.AUS.ordinal(), 51}, new int[]{FacPropertyDemo.CountryForRegion3.NZL.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED}, new int[]{FacPropertyDemo.CountryForRegion3.THA.ordinal(), 25}, new int[]{FacPropertyDemo.CountryForRegion3.VNM.ordinal(), 26}, new int[]{FacPropertyDemo.CountryForRegion3.MMR.ordinal(), 27}, new int[]{FacPropertyDemo.CountryForRegion3.IND.ordinal(), 38}, new int[]{FacPropertyDemo.CountryForRegion3.IDN.ordinal(), 39}, new int[]{FacPropertyDemo.CountryForRegion3.LKA.ordinal(), 81}, new int[]{FacPropertyDemo.CountryForRegion3.MYS.ordinal(), 28}, new int[]{FacPropertyDemo.CountryForRegion3.FJI.ordinal(), 89}, new int[]{FacPropertyDemo.CountryForRegion3.MDV.ordinal(), 88}, new int[]{FacPropertyDemo.CountryForRegion3.BGD.ordinal(), 99}, new int[]{FacPropertyDemo.CountryForRegion3.PAK.ordinal(), 100}, new int[]{FacPropertyDemo.CountryForRegion3.NPL.ordinal(), 102}, new int[]{FacPropertyDemo.CountryForRegion3.KHM.ordinal(), 103}, new int[]{FacPropertyDemo.CountryForRegion3.SGP.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED}, new int[]{FacPropertyDemo.CountryForRegion3.MNG.ordinal(), 101}, new int[]{FacPropertyDemo.CountryForRegion3.LAO.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED}, new int[]{FacPropertyDemo.CountryForRegion3.BRN.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS}}, new int[][]{new int[]{FacPropertyDemo.LanguageForRegion0.hbr.ordinal(), 15}, new int[]{FacPropertyDemo.LanguageForRegion0.eng.ordinal(), 1}, new int[]{FacPropertyDemo.LanguageForRegion0.rus.ordinal(), 6}, new int[]{FacPropertyDemo.LanguageForRegion0.ukr.ordinal(), 13}, new int[]{FacPropertyDemo.LanguageForRegion0.arm.ordinal(), 43}, new int[]{FacPropertyDemo.LanguageForRegion0.geo.ordinal(), 44}, new int[]{FacPropertyDemo.LanguageForRegion0.aze.ordinal(), 49}, new int[]{FacPropertyDemo.LanguageForRegion0.kaz.ordinal(), 48}, new int[]{FacPropertyDemo.LanguageForRegion0.uzb.ordinal(), 11}, new int[]{FacPropertyDemo.LanguageForRegion0.tur.ordinal(), 28}, new int[]{FacPropertyDemo.LanguageForRegion0.ger.ordinal(), 17}, new int[]{FacPropertyDemo.LanguageForRegion0.fre.ordinal(), 2}, new int[]{FacPropertyDemo.LanguageForRegion0.ita.ordinal(), 18}, new int[]{FacPropertyDemo.LanguageForRegion0.spa.ordinal(), 3}, new int[]{FacPropertyDemo.LanguageForRegion0.por.ordinal(), 4}, new int[]{FacPropertyDemo.LanguageForRegion0.dut.ordinal(), 40}, new int[]{FacPropertyDemo.LanguageForRegion0.byl.ordinal(), 52}, new int[]{FacPropertyDemo.LanguageForRegion0.ara.ordinal(), 5}, new int[]{FacPropertyDemo.LanguageForRegion0.chi.ordinal(), 7}, new int[]{FacPropertyDemo.LanguageForRegion0.rum.ordinal(), 41}, new int[]{FacPropertyDemo.LanguageForRegion0.srp.ordinal(), 32}}, new int[][]{new int[]{FacPropertyDemo.LanguageForRegion1.eng.ordinal(), 1}, new int[]{FacPropertyDemo.LanguageForRegion1.fre.ordinal(), 2}, new int[]{FacPropertyDemo.LanguageForRegion1.spa.ordinal(), 3}, new int[]{FacPropertyDemo.LanguageForRegion1.por.ordinal(), 4}, new int[]{FacPropertyDemo.LanguageForRegion1.ara.ordinal(), 5}, new int[]{FacPropertyDemo.LanguageForRegion1.rus.ordinal(), 6}, new int[]{FacPropertyDemo.LanguageForRegion1.chi.ordinal(), 7}, new int[]{FacPropertyDemo.LanguageForRegion1.per.ordinal(), 29}, new int[]{FacPropertyDemo.LanguageForRegion1.tur.ordinal(), 28}, new int[]{FacPropertyDemo.LanguageForRegion1.urd.ordinal(), 45}, new int[]{FacPropertyDemo.LanguageForRegion1.ukr.ordinal(), 13}, new int[]{FacPropertyDemo.LanguageForRegion1.ita.ordinal(), 18}, new int[]{FacPropertyDemo.LanguageForRegion1.geo.ordinal(), 44}, new int[]{FacPropertyDemo.LanguageForRegion1.arm.ordinal(), 43}, new int[]{FacPropertyDemo.LanguageForRegion1.uzb.ordinal(), 11}, new int[]{FacPropertyDemo.LanguageForRegion1.aze.ordinal(), 49}, new int[]{FacPropertyDemo.LanguageForRegion1.kaz.ordinal(), 48}, new int[]{FacPropertyDemo.LanguageForRegion1.rum.ordinal(), 41}, new int[]{FacPropertyDemo.LanguageForRegion1.vie.ordinal(), 8}, new int[]{FacPropertyDemo.LanguageForRegion1.tha.ordinal(), 9}, new int[]{FacPropertyDemo.LanguageForRegion1.ind.ordinal(), 30}, new int[]{FacPropertyDemo.LanguageForRegion1.hin.ordinal(), 12}, new int[]{FacPropertyDemo.LanguageForRegion1.mal.ordinal(), 14}, new int[]{FacPropertyDemo.LanguageForRegion1.zho.ordinal(), 16}, new int[]{FacPropertyDemo.LanguageForRegion1.bur.ordinal(), 10}, new int[]{FacPropertyDemo.LanguageForRegion1.bng.ordinal(), 55}, new int[]{FacPropertyDemo.LanguageForRegion1.mog.ordinal(), 56}}, new int[][]{new int[]{FacPropertyDemo.LanguageForRegion2.eng.ordinal(), 1}, new int[]{FacPropertyDemo.LanguageForRegion2.fre.ordinal(), 2}, new int[]{FacPropertyDemo.LanguageForRegion2.spa.ordinal(), 3}, new int[]{FacPropertyDemo.LanguageForRegion2.por.ordinal(), 4}, new int[]{FacPropertyDemo.LanguageForRegion2.ara.ordinal(), 5}, new int[]{FacPropertyDemo.LanguageForRegion2.rus.ordinal(), 6}, new int[]{FacPropertyDemo.LanguageForRegion2.chi.ordinal(), 7}, new int[]{FacPropertyDemo.LanguageForRegion2.per.ordinal(), 29}, new int[]{FacPropertyDemo.LanguageForRegion2.tur.ordinal(), 28}, new int[]{FacPropertyDemo.LanguageForRegion2.amh.ordinal(), 46}, new int[]{FacPropertyDemo.LanguageForRegion2.urd.ordinal(), 45}, new int[]{FacPropertyDemo.LanguageForRegion2.rum.ordinal(), 41}, new int[]{FacPropertyDemo.LanguageForRegion2.aze.ordinal(), 49}, new int[]{FacPropertyDemo.LanguageForRegion2.ukr.ordinal(), 13}, new int[]{FacPropertyDemo.LanguageForRegion2.ita.ordinal(), 18}, new int[]{FacPropertyDemo.LanguageForRegion2.geo.ordinal(), 44}, new int[]{FacPropertyDemo.LanguageForRegion2.arm.ordinal(), 43}, new int[]{FacPropertyDemo.LanguageForRegion2.uzb.ordinal(), 11}}, new int[][]{new int[]{FacPropertyDemo.LanguageForRegion3.eng.ordinal(), 1}, new int[]{FacPropertyDemo.LanguageForRegion3.fre.ordinal(), 2}, new int[]{FacPropertyDemo.LanguageForRegion3.spa.ordinal(), 3}, new int[]{FacPropertyDemo.LanguageForRegion3.por.ordinal(), 4}, new int[]{FacPropertyDemo.LanguageForRegion3.ara.ordinal(), 5}, new int[]{FacPropertyDemo.LanguageForRegion3.rus.ordinal(), 6}, new int[]{FacPropertyDemo.LanguageForRegion3.chi.ordinal(), 7}, new int[]{FacPropertyDemo.LanguageForRegion3.vie.ordinal(), 8}, new int[]{FacPropertyDemo.LanguageForRegion3.tha.ordinal(), 9}, new int[]{FacPropertyDemo.LanguageForRegion3.ind.ordinal(), 30}, new int[]{FacPropertyDemo.LanguageForRegion3.hin.ordinal(), 12}, new int[]{FacPropertyDemo.LanguageForRegion3.mal.ordinal(), 14}, new int[]{FacPropertyDemo.LanguageForRegion3.zho.ordinal(), 16}, new int[]{FacPropertyDemo.LanguageForRegion3.bur.ordinal(), 10}, new int[]{FacPropertyDemo.LanguageForRegion3.urd.ordinal(), 45}, new int[]{FacPropertyDemo.LanguageForRegion3.bng.ordinal(), 55}, new int[]{FacPropertyDemo.LanguageForRegion3.mog.ordinal(), 56}, new int[]{FacPropertyDemo.LanguageForRegion3.guj.ordinal(), 96}, new int[]{FacPropertyDemo.LanguageForRegion3.kan.ordinal(), 93}, new int[]{FacPropertyDemo.LanguageForRegion3.apr.ordinal(), 94}, new int[]{FacPropertyDemo.LanguageForRegion3.tel.ordinal(), 91}, new int[]{FacPropertyDemo.LanguageForRegion3.tam.ordinal(), 90}, new int[]{FacPropertyDemo.LanguageForRegion3.khm.ordinal(), 69}, new int[]{FacPropertyDemo.LanguageForRegion3.sin.ordinal(), 81}}, new int[][]{new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.his.ordinal(), 1}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.vuz.ordinal(), 23}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.tos.ordinal(), 95}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.alh.ordinal(), 86}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.gua.ordinal(), 55}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.llo.ordinal(), 24}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.bpl.ordinal(), 72}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.vis.ordinal(), 90}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.aba.ordinal(), 70}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.inn.ordinal(), 25}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.ben.ordinal(), 60}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.mag.ordinal(), 68}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.dof.ordinal(), 92}, new int[]{FacPropertyDemo.LogoForEM_NonBrandIsolation.dex.ordinal(), 30}}, new int[][]{new int[]{FacPropertyDemo.ZoomModeForEM.AUTOMATIC.ordinal(), 0}, new int[]{FacPropertyDemo.ZoomModeForEM.NORMAL.ordinal(), 1}, new int[]{FacPropertyDemo.ZoomModeForEM.WIDE.ordinal(), 3}, new int[]{FacPropertyDemo.ZoomModeForEM.DIRECT.ordinal(), 4}, new int[]{FacPropertyDemo.ZoomModeForEM.PANARAMIC.ordinal(), 6}, new int[]{FacPropertyDemo.ZoomModeForEM.MOVIE_ZOOM.ordinal(), 2}}, new int[][]{new int[]{FacPropertyDemo.LogoForCon_brandIsolation.con.ordinal(), 18}, new int[]{FacPropertyDemo.LogoForCon_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForCon_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForTho_brandIsolation.tho.ordinal(), 45}, new int[]{FacPropertyDemo.LogoForTho_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForTho_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForStr_brandIsolation.str.ordinal(), 51}, new int[]{FacPropertyDemo.LogoForStr_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForStr_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForGen_brandIsolation.gen.ordinal(), 19}, new int[]{FacPropertyDemo.LogoForGen_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForGen_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForHaa_brandIsolation.haa.ordinal(), 20}, new int[]{FacPropertyDemo.LogoForHaa_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForHaa_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForTit_brandIsolation.tit.ordinal(), 21}, new int[]{FacPropertyDemo.LogoForTit_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForTit_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.LogoForKmc_brandIsolation.kmc.ordinal(), 22}, new int[]{FacPropertyDemo.LogoForKmc_brandIsolation.wel.ordinal(), 2}, new int[]{FacPropertyDemo.LogoForKmc_brandIsolation.off.ordinal(), 100}}, new int[][]{new int[]{FacPropertyDemo.PictureModeEM.VIVID.ordinal(), 0}, new int[]{FacPropertyDemo.PictureModeEM.STANDARD.ordinal(), 1}, new int[]{FacPropertyDemo.PictureModeEM.ENERGY_SAVING.ordinal(), 2}, new int[]{FacPropertyDemo.PictureModeEM.THEATER_DAY.ordinal(), 3}, new int[]{FacPropertyDemo.PictureModeEM.GAME.ordinal(), 5}, new int[]{FacPropertyDemo.PictureModeEM.SPORT.ordinal(), 4}, new int[]{FacPropertyDemo.PictureModeEM.THEATER_NIGHT.ordinal(), 6}}, new int[][]{new int[]{FacPropertyDemo.CountryForRegion4.PAN.ordinal(), 57}, new int[]{FacPropertyDemo.CountryForRegion4.COL.ordinal(), 83}}, new int[][]{new int[]{FacPropertyDemo.LanguageForRegion4.eng.ordinal(), 1}, new int[]{FacPropertyDemo.LanguageForRegion4.spa.ordinal(), 3}, new int[]{FacPropertyDemo.LanguageForRegion4.por.ordinal(), 4}}, new int[][]{new int[]{FacPropertyDemo.LogoForEM_SA.his.ordinal(), 1}, new int[]{FacPropertyDemo.LogoForEM_SA.kal.ordinal(), 13}}, new int[][]{new int[]{FacPropertyDemo.LogoForJPN.tos.ordinal(), 95}}, new int[][]{new int[]{FacPropertyDemo.CountryForJPN.JPN.ordinal(), MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR}}, new int[][]{new int[]{FacPropertyDemo.LanguageForJPN.jpn.ordinal(), 68}}, new int[][]{new int[]{FacPropertyDemo.PictureModeTW_Toshiba.VIVID.ordinal(), 0}, new int[]{FacPropertyDemo.PictureModeTW_Toshiba.STANDARD.ordinal(), 1}, new int[]{FacPropertyDemo.PictureModeTW_Toshiba.ENERGY_SAVING.ordinal(), 2}, new int[]{FacPropertyDemo.PictureModeTW_Toshiba.THEATER_DAY.ordinal(), 3}, new int[]{FacPropertyDemo.PictureModeTW_Toshiba.GAME.ordinal(), 4}, new int[]{FacPropertyDemo.PictureModeTW_Toshiba.FILMMAKER_MODE.ordinal(), 7}}};

    static {
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_VIE, 8);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_THA, 9);
        mLanguageMap.put("bur", 10);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_UZB, 11);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_HIN, 12);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_UKR, 13);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_MAL, 14);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_HBR, 15);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_GER, 17);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ITA, 18);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SWE, 19);
        mLanguageMap.put(MtkTvConfigTypeBase.S638_CFG_LANG_DAN, 20);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_FIN, 21);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_NOR, 22);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_CZE, 23);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SLK, 24);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_POL, 25);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_HUN, 26);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_BUL, 27);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_TUR, 28);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_PER, 29);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_IND, 30);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_HRV, 31);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SRP, 32);
        mLanguageMap.put("mac", 33);
        mLanguageMap.put("alb", 34);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_LAV, 35);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_EST, 36);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_LIT, 37);
        mLanguageMap.put("kur", 38);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SLV, 39);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_DUT, 40);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_RUM, 41);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_GRE, 42);
        mLanguageMap.put("arm", 43);
        mLanguageMap.put("geo", 44);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_URD, 45);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_AMH, 46);
        mLanguageMap.put("kir", 47);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, 48);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_AZE, 49);
        mLanguageMap.put("bsn", 50);
        mLanguageMap.put("icl", 51);
        mLanguageMap.put("byl", 52);
        mLanguageMap.put("bng", 55);
        mLanguageMap.put("mog", 56);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_JPN, 68);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_KAN, 93);
        mLanguageMap.put("apr", 94);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_TEL, 91);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_TAM, 90);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_GUJ, 96);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_KHM, 69);
        mLanguageMap.put("sin", 81);
        mLanguageMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_KOR, 71);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_RUS, 29);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_UZB, 30);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KGZ, 31);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TKM, 32);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TUR, 33);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_UKR, 34);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_AZE, 35);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_GEO, 36);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ARM, 37);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ISR, 49);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KAZ, 85);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TJK, 90);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SRB, 91);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BLR, 92);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDA, 93);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_AFG, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_EGY, 2);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_DZA, 23);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_IRQ, 40);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SAU, 41);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ARE, 42);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KWT, 43);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_YEM, 44);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_OMN, 45);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_QAT, 46);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_JOR, 47);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_IRN, 48);
        mCountryMap.put("DXB", 50);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BHR, 104);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBN, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SYR, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PSE, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MLI, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SSD, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MAR, 3);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_NGQ, 4);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBY, 5);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_GHA, 6);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SLE, 7);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CIV, 8);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_AGO, 9);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BEN, 10);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBR, 11);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_COD, 12);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CMR, 13);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ZWE, 14);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_UGA, 15);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TZA, 16);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MUS, 17);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SDN, 18);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_DJI, 19);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ETH, 20);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MOZ, 21);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDG, 22);
        mCountryMap.put("ZAF", 24);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_COG, 79);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KEN, 80);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_GIN, 94);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BDI, 95);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SEN, 96);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TUN, 97);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SOM, 98);
        mCountryMap.put("GAM", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MRT, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_NER, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BFA, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TCD, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ERI, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_RWA, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_REU, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYT, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ZMB, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MWI, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_NAM, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BWA, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_SYC, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_GMB, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW));
        mCountryMap.put("THA", 25);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_VNM, 26);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MMR, 27);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS, 28);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_IND, 38);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_IDN, 39);
        mCountryMap.put("AUS", 51);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_LKA, 81);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDV, 88);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_FJI, 89);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BGD, 99);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PAK, 100);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MNG, 101);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_NPL, 102);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KHM, 103);
        mCountryMap.put("SGP", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED));
        mCountryMap.put("NZL", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_LAO, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BRN, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_USA, 59);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CAN, 60);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MEX, 61);
        mCountryMap.put("BRA", 53);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ECU, 82);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PER, 84);
        mCountryMap.put("CHL", 86);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PRY, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_BOL, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PHL, 56);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_VEN, 54);
        mCountryMap.put("ARG", 55);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_URY, 58);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PAN, 57);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_COL, 83);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_TWN, 52);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_HKG, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_TIME_BOUND));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_USA, 59);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CAN, 60);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_MEX, 61);
        mCountryMap.put("NCA", 114);
        mCountryMap.put("HON", 115);
        mCountryMap.put("ESA", 116);
        mCountryMap.put("GUA", 117);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_DOM, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FRAMERATE_NOT_SUPPORT));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CUB, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT));
        mCountryMap.put("TRI", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_JAM, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT));
        mCountryMap.put("CUR", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE));
        mCountryMap.put("BAR", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_REPLAY));
        mCountryMap.put("MAA", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_URI_FILE_OPENED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_PRI, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DESC_ALLOCED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CRI, 87);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR, Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_IDX_CHANGED));
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_GBR, 65);
        mCountryMap.put("FRA", 67);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_DEU, 62);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_ITA, 64);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_POL, 76);
        mCountryMap.put(MtkTvConfigTypeBase.S3166_CFG_COUNT_CZE, 74);
        mLogoMap.put("his", 1);
        mLogoMap.put("gen", 19);
        mLogoMap.put("haa", 20);
        mLogoMap.put("has", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT));
        mLogoMap.put("dor", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN));
        mLogoMap.put("otw", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB));
        mLogoMap.put("sho", 26);
        mLogoMap.put("enf", 27);
        mLogoMap.put("dlc", 28);
        mLogoMap.put("oro", 62);
        mLogoMap.put("alh", 86);
        mLogoMap.put("une", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB));
        mLogoMap.put("tub", 114);
        mLogoMap.put("vuz", 23);
        mLogoMap.put("akn", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT));
        mLogoMap.put("gua", 55);
        mLogoMap.put("wsa", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED));
        mLogoMap.put("liv", 75);
        mLogoMap.put("hif", 115);
        mLogoMap.put("ltt", 116);
        mLogoMap.put("tos", 95);
        mLogoMap.put("szi", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT));
        mLogoMap.put("sie", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON));
        mLogoMap.put("wel", 2);
        mLogoMap.put(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);
        mLogoMap.put("ggb", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH));
        mLogoMap.put("qvy", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR));
        mLogoMap.put("snb", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED));
        mLogoMap.put("thh", Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY));
        mLogoMap.put("lca", 259);
        mLogoNameMap.put(1, "Hisense");
        mLogoNameMap.put(19, "GENERAL SUPREME");
        mLogoNameMap.put(20, "HAAM");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT), "HAAS");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN), "Dora");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB), "O2");
        mLogoNameMap.put(26, "Shownic");
        mLogoNameMap.put(27, "Enfinity");
        mLogoNameMap.put(28, "Dlc");
        mLogoNameMap.put(62, "ORO");
        mLogoNameMap.put(86, "Alhafidh");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB), "Uneva");
        mLogoNameMap.put(114, "Turbo");
        mLogoNameMap.put(23, "Vu");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT), "AKINO");
        mLogoNameMap.put(55, "G-GUARD");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED), "G-WANSA");
        mLogoNameMap.put(75, "LIVERPOOL");
        mLogoNameMap.put(115, "HILIFE");
        mLogoNameMap.put(116, "LT");
        mLogoNameMap.put(95, "Toshiba");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT), "Sanzi");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON), "Siera");
        mLogoNameMap.put(2, "Welcom");
        mLogoNameMap.put(100, "OFF");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH), "Gigabyte");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED), "Sunbrite");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR), "QVY");
        mLogoNameMap.put(Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY), "TheHam");
        mRegionCountry = new int[][]{new int[]{30, 31, 85, 37, 35, 36, 32, 49, 93, 90, 92, 33, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED, 29, 34, 91}, new int[]{41, 42, 43, 46, 47, 104, 45, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, 48, 40, 5, 44, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE, 3, 23, 2, 18, 97, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT, 4, 96, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON, 8, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, 13, 94, 98, 19, 15, 6, 11, 7}, new int[]{24, 14, 20, 80, 12, 79, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT, 16, 9, 22, 10, 17, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, 21, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED}, new int[]{51, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED, 25, 26, 27, 38, 39, 81, 28, 89, 88, 99, 100, 102, 103, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED, 101, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS}, new int[]{57, 83}, new int[]{53, 82, 84, 86, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START, 56, 116, 117}, new int[]{54, 55, 58}};
    }

    public static int ApToTil(int i, int i2) {
        int[][] iArr = array[i];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3][0] == i2) {
                return iArr[i3][1];
            }
        }
        return 0;
    }

    public static int TilToAp(int i, int i2) {
        int[][] iArr = array[i];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3][1] == i2) {
                return iArr[i3][0];
            }
        }
        return 0;
    }

    public static int getRegionAboutCountry(int i) {
        for (int i2 = 0; i2 < 7; i2++) {
            for (int i3 : mRegionCountry[i2]) {
                if (i == i3) {
                    return i2;
                }
            }
        }
        return 0;
    }

    public static int getLanguageIndexByString(String str) {
        HashMap<String, Integer> map = mLanguageMap;
        if (map == null || map.size() <= 0) {
            return 0;
        }
        return mLanguageMap.get(str).intValue();
    }

    public static String getLanguageNameByTilValue(int i) {
        int size = mLanguageMap.size();
        HashMap<String, Integer> map = mLanguageMap;
        if (map == null || size <= 0) {
            return "NG";
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey();
            }
        }
        return "NG";
    }

    public static int getCountryIndexByString(String str) {
        Integer num;
        HashMap<String, Integer> map = mCountryMap;
        if (map == null || map.size() <= 0 || (num = mCountryMap.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public static String getCountryStringByIndex(int i) {
        HashMap<String, Integer> map = mCountryMap;
        if (map == null || map.size() <= 0) {
            return "";
        }
        for (Map.Entry<String, Integer> entry : mCountryMap.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey();
            }
        }
        return "";
    }

    public static String getCountryNameByTilValue(int i) {
        int size = mCountryMap.size();
        HashMap<String, Integer> map = mCountryMap;
        if (map == null || size <= 0) {
            return "NG";
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey();
            }
        }
        return "NG";
    }

    public static int getLogoIndexByStr(String str) {
        Integer num;
        HashMap<String, Integer> map = mLogoMap;
        if (map == null || map.size() <= 0 || (num = mLogoMap.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public static String getLogoNameByTilValue(int i) {
        int size = mLogoMap.size();
        HashMap<String, Integer> map = mLogoMap;
        if (map == null || size <= 0) {
            return "NG";
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey();
            }
        }
        return "NG";
    }

    public static String getLogoFullNameByTilValue(int i) {
        HashMap<Integer, String> map = mLogoNameMap;
        return (map == null || map.size() <= 0) ? "NG" : mLogoNameMap.get(Integer.valueOf(i));
    }
}
