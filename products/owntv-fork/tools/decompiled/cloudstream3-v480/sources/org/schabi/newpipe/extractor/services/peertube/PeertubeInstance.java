package org.schabi.newpipe.extractor.services.peertube;

import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeInstance {
    public static final PeertubeInstance DEFAULT_INSTANCE = new PeertubeInstance("https://framatube.org", "FramaTube");
    private String name;
    private final String url;

    public PeertubeInstance(String str) {
        this.url = str;
        this.name = "PeerTube";
    }

    public PeertubeInstance(String str, String str2) {
        this.url = str;
        this.name = str2;
    }

    public String getUrl() {
        return this.url;
    }

    public void fetchInstanceMetaData() throws Exception {
        try {
            Response response = NewPipe.getDownloader().get(this.url + "/api/v1/config");
            if (response == null || Utils.isBlank(response.responseBody())) {
                throw new Exception("unable to configure instance " + this.url);
            }
            try {
                this.name = JsonUtils.getString(JsonParser.object().from(response.responseBody()), "instance.name");
            } catch (JsonParserException | ParsingException e) {
                throw new Exception("unable to parse instance config", e);
            }
        } catch (IOException | ReCaptchaException e2) {
            throw new Exception("unable to configure instance " + this.url, e2);
        }
    }

    public String getName() {
        return this.name;
    }
}
