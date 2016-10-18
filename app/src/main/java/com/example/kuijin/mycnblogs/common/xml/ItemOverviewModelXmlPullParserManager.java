package com.example.kuijin.mycnblogs.common.xml;

import com.example.kuijin.mycnblogs.common.GlobalInfos;

/**
 * Created by kuijin on 2016/10/18.
 */
public class ItemOverviewModelXmlPullParserManager {

    public static IItemOverviewModelXmlPullParser getXmlPullParser(int fragementType) {
        if (fragementType == GlobalInfos.FragmentTypeHomePage) {
            return new ItemOverviewModelXmlPullParser();
        }

        return null;
    }
}
