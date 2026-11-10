package com.hisense.tvcm;

import android.content.Context;
import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes.dex */
public class PropertyParseHandler extends DefaultHandler {
    private SourceConfiguration mSourceConfiguration;
    private String tagname;

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }

    public PropertyParseHandler(Context context) {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.tagname == null) {
            return;
        }
        String str = new String(cArr, i, i2);
        if (this.tagname.equalsIgnoreCase("source")) {
            try {
                this.mSourceConfiguration.setOriSourceName(str);
                return;
            } catch (NumberFormatException unused) {
                Log.d("Fac_PropertyParseHandler", "parse source format exception!");
                return;
            }
        }
        if (this.tagname.equalsIgnoreCase("name")) {
            this.mSourceConfiguration.setDisplayName(str);
            return;
        }
        if (this.tagname.equalsIgnoreCase("isenabled")) {
            this.mSourceConfiguration.setEnabled(Boolean.parseBoolean(str));
            return;
        }
        if (this.tagname.equalsIgnoreCase("isshown")) {
            this.mSourceConfiguration.setShown(Boolean.parseBoolean(str));
        } else if (this.tagname.equalsIgnoreCase("count")) {
            this.mSourceConfiguration.setCountOfThisSource(Integer.parseInt(str));
        } else if (this.tagname.equalsIgnoreCase("tilvalue")) {
            this.mSourceConfiguration.setTilValue(Integer.parseInt(str));
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this.tagname = null;
        if (str2 != null && str2.equalsIgnoreCase("source")) {
            FacPropertyDemo.SourceEnum.addToShownSourceList(this.mSourceConfiguration);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        Log.i("Fac_PropertyParseHandler", "start parse int_property.xml");
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (str2 == null) {
            Log.d("Fac_PropertyParseHandler", "startElement, local name null");
            return;
        }
        this.tagname = str2;
        if (str2.equalsIgnoreCase("inputsource")) {
            this.mSourceConfiguration = new SourceConfiguration();
        }
    }
}
