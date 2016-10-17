package com.example.kuijin.mycnblogs.common.xml;

import android.util.Xml;

import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/20.
 */
public class ItemOverviewModelXmlPullParser {
    private static final String ENTRY = "entry";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String SUMMARY = "summary";
    private static final String PUBLISHEDTIME = "published";
    private static final String UPDATEDTIME = "upadted";
    private static final String AUTHORNAME = "name";
    private static final String AUTHORBLOG = "uri";
    private static final String AUTHORIMAGE = "avatar";
    private static final String BLOGURL = "link";
    private static final String BLOGURLATTRIBUTE = "href";
    private static final String DIGGSCOUNT = "diggs"; //推荐
    private static final String VIEWCOUNT = "views";
    private static final String COMMENTSCOUNT = "comments";

    public static List<IItemOverviewModel> getItemOverviewModels(String xmlStr, String encoding) throws XmlPullParserException, IOException {
        InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());
        return getItemOverviewModels(inputStream, encoding);
    }

    public static List<IItemOverviewModel> getItemOverviewModels(InputStream inputStream, String encoding) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, encoding);

        List<IItemOverviewModel> listResult = null;
        ItemOverviewModel item = null;
        int eventType = parser.getEventType();

        while (XmlPullParser.END_DOCUMENT != eventType) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    listResult = new ArrayList<>(ConfigManager.getItemOverviewModelPageSize());
                    break;
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    if (ENTRY.equals(name)) {
                        item = new ItemOverviewModel();
                    }
                    if (null != item) {
                        if (ID.equals(name)) {
                            parser.next();
                            item.setId(parser.getText());
                        } else if (TITLE.equals(name)) {
                            parser.next();
                            item.setTitle(parser.getText());
                        } else if (SUMMARY.equals(name)) {
                            parser.next();
                            item.setSummary(parser.getText());
                        } else if (PUBLISHEDTIME.equals(name)) {
                            parser.next();
                            item.setWriteTime(parser.getText());
                        } else if (UPDATEDTIME.equals(name)) {
                            parser.next();
                            item.setPublishTime(parser.getText());
                        } else if (AUTHORNAME.equals(name)) {
                            parser.next();
                            item.setWriterName(parser.getText());
                        } else if (AUTHORBLOG.equals(name)) {
                            parser.next();
                            item.setWriterBlogUrl(parser.getText());
                        } else if (AUTHORIMAGE.equals(name)) {
                            parser.next();
                            item.setWriterImageSrc(parser.getText());
                        } else if (BLOGURL.equals(name)) {
                            item.setNewsUrl(parser.getAttributeValue(null, BLOGURLATTRIBUTE));
                        } else if (DIGGSCOUNT.equals(name)) {
                            parser.next();
                            item.setRecommendCount(parser.getText());
                        } else if (VIEWCOUNT.equals(name)) {
                            parser.next();
                            item.setReadedCount(parser.getText());
                        } else if (COMMENTSCOUNT.equals(name)) {
                            parser.next();
                            item.setReplyCount(parser.getText());
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (ENTRY.equals(parser.getName())) {
                        listResult.add(item);
                        item = null;
                    }
                    break;
            }

            eventType = parser.next();
        }

        return listResult;
    }
}
