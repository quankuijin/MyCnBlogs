package com.example.kuijin.mycnblogs.common.network;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Xml;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kuijin.mycnblogs.common.CnBlogsApplication;
import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.common.xml.IItemOverviewModelXmlPullParser;
import com.example.kuijin.mycnblogs.common.xml.ItemOverviewModelXmlPullParser;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by kuijin on 2016/9/19.
 */
public class NetworkVolley implements INetwork {
    private RequestQueue requestQueue = null;

    private static NetworkVolley volly = null;
    private NetworkVolley() {
        Volley volly = new Volley();
        requestQueue = Volley.newRequestQueue(CnBlogsApplication.getApplication());
    }

    public static NetworkVolley getInstance() {
        if (null == volly) {
            volly = new NetworkVolley();
        }

        return volly;
    }

    @Override
    public void getItemOverviewModels(String url,
                                      @NonNull final ResponseItemOverviewModelListener listener,
                                      @NonNull final ResponseErrorListener errorListener,
                                      @NonNull final IItemOverviewModelXmlPullParser xmlPullParser) {
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("NetworkVolley", "getItemOverviewModels",
                    "url is null or empty!", CnBlogsLog.LEVEL_ERROR);

            errorListener.onErrorResponse("url is null");
            return;
        }

//        if (1 > pageIndex) {
//            CnBlogsLog.write("NetworkVolley", "getItemOverviewModels",
//                    "pageIndex is less than 0 or equals to 0!", CnBlogsLog.LEVEL_ERROR);
//            errorListener.onErrorResponse("pageIndex is 0");
//            return;
//        }
//
//        if (1 > pageSize) {
//            CnBlogsLog.write("NetworkVolley", "getItemOverviewModels",
//                    "pageSize is less than 0 or equals to 0!", CnBlogsLog.LEVEL_ERROR);
//            errorListener.onErrorResponse("pageSize is 0");
//            return;
//        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (TextUtils.isEmpty(response)) {
                            CnBlogsLog.write("NetworkVolley", "getItemOverviewModels",
                                    "StringRequset.onResponse response string is null or empty",
                                    CnBlogsLog.LEVEL_ERROR);
                            errorListener.onErrorResponse("response from network is null");
                            return;
                        }

                        List<IItemOverviewModel> list = null;
                        try {
                            list = xmlPullParser.getItemOverviewModels(response, "UTF_8");
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                            CnBlogsLog.write(e);
                            errorListener.onErrorResponse(e.getMessage());
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            CnBlogsLog.write(e);
                            errorListener.onErrorResponse(e.getMessage());
                            return;
                        }

                        listener.onResponse(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CnBlogsLog.write(error);
                        errorListener.onErrorResponse(error.getMessage());
                    }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void getImage(String url, int maxWidth, int maxHeight, Bitmap.Config config,
                         @NonNull final ResponseImageListenrer listenrer,
                         @NonNull final ResponseErrorListener errorListener) {
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("NetworkVolly", "getImage", "url is null or empty!", CnBlogsLog.LEVEL_ERROR);
            errorListener.onErrorResponse("url is null");
            return ;
        }

        if (0 > maxWidth) {
            CnBlogsLog.write("NetworkVolley", "getImage", "maxWidth is less than 0!", CnBlogsLog.LEVEL_ERROR);
            maxWidth = 0;
        }
        if (0 > maxHeight) {
            CnBlogsLog.write("NetworkVolley", "getImage", "maxHeight is less than 0!", CnBlogsLog.LEVEL_ERROR);
            maxHeight = 0;
        }

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listenrer.onResponse(response);
            }
        }, maxWidth, maxHeight, config, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CnBlogsLog.write(error);
                errorListener.onErrorResponse(error.getMessage());
            }
        });

        requestQueue.add(imageRequest);
    }


}
