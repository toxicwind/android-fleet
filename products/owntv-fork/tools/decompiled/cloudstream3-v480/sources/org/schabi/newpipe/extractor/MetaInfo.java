package org.schabi.newpipe.extractor;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class MetaInfo implements Serializable {
    private Description content;
    private String title;
    private List<String> urlTexts;
    private List<URL> urls;

    public MetaInfo(@Nonnull String str, @Nonnull Description description, @Nonnull List<URL> list, @Nonnull List<String> list2) {
        this.title = "";
        this.urls = new ArrayList();
        new ArrayList();
        this.title = str;
        this.content = description;
        this.urls = list;
        this.urlTexts = list2;
    }

    public MetaInfo() {
        this.title = "";
        this.urls = new ArrayList();
        this.urlTexts = new ArrayList();
    }

    @Nonnull
    public String getTitle() {
        return this.title;
    }

    public void setTitle(@Nonnull String str) {
        this.title = str;
    }

    @Nonnull
    public Description getContent() {
        return this.content;
    }

    public void setContent(@Nonnull Description description) {
        this.content = description;
    }

    @Nonnull
    public List<URL> getUrls() {
        return this.urls;
    }

    public void setUrls(@Nonnull List<URL> list) {
        this.urls = list;
    }

    public void addUrl(@Nonnull URL url) {
        this.urls.add(url);
    }

    @Nonnull
    public List<String> getUrlTexts() {
        return this.urlTexts;
    }

    public void setUrlTexts(@Nonnull List<String> list) {
        this.urlTexts = list;
    }

    public void addUrlText(@Nonnull String str) {
        this.urlTexts.add(str);
    }
}
