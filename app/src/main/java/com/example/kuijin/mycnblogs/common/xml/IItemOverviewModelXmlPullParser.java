package com.example.kuijin.mycnblogs.common.xml;

import com.example.kuijin.mycnblogs.model.IItemOverviewModel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by kuijin on 2016/10/18.
 */
public interface IItemOverviewModelXmlPullParser {
    static final String ENTRY = "entry";
    static final String ID = "id";
    static final String TITLE = "title";
    static final String SUMMARY = "summary";
    static final String PUBLISHEDTIME = "published";
    static final String UPDATEDTIME = "upadted";
    static final String AUTHORNAME = "name";
    static final String AUTHORBLOG = "uri";
    static final String AUTHORIMAGE = "avatar";
    static final String BLOGURL = "link";
    static final String BLOGURLATTRIBUTE = "href";
    static final String DIGGSCOUNT = "diggs"; //推荐
    static final String VIEWCOUNT = "views";
    static final String COMMENTSCOUNT = "comments";

    public List<IItemOverviewModel> getItemOverviewModels(String xmlStr, String encoding) throws XmlPullParserException, IOException;
    public List<IItemOverviewModel> getItemOverviewModels(InputStream inputStream, String encoding) throws XmlPullParserException, IOException;
}
