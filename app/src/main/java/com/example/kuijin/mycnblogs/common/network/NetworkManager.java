package com.example.kuijin.mycnblogs.common.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import com.example.kuijin.mycnblogs.common.CnBlogsApplication;
import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public class NetworkManager {

    public static final int UNKNOW = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;
    public static final int NETWORK_TYPE_MOBILE_SUPL = 5;
    public static final int NETWORK_TYPE_MOBILE_DUL = 6;
    public static final int NETWORK_TYPE_MOBILE_HIPRI = 7;
    public static final int NETWORK_TYPE_WIMAX = 8;
    public static final int NETWORK_TYPE_BLUETOOTH = 9;
    public static final int NETWORK_TYPE_DUMMY = 10;
    public static final int NETWORK_TYPE_ETHERNET = 11;

    private static INetwork getINetwork() {
        INetwork network = NetworkVolley.getInstance();
        return network;
    }

    public static int getNetworkType() {
        int type = UNKNOW;

        Context context = CnBlogsApplication.getApplication();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (null == networkInfo) {
            return UNKNOW;
        }

        int networkType = networkInfo.getType();
        if (!networkInfo.isAvailable()) {
            return UNKNOW;
        }

        if (networkType == ConnectivityManager.TYPE_WIFI) {
            type = NETWORK_TYPE_WIFI;
        } else if (networkType == ConnectivityManager.TYPE_MOBILE) {
            int subType = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
                subType = networkInfo.getSubtype();
            }
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    type = NETWORK_TYPE_2G;
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    type = NETWORK_TYPE_3G;
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    type = NETWORK_TYPE_4G;
                    break;
                default:
                    // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                    String _strSubTypeName = networkInfo.getSubtypeName();
                    if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000"))  {
                        type = NETWORK_TYPE_3G;
                    }

                    break;
            }
        }

        return type;
    }

    public static void getItemOverviewModels(String url,
                                                                @NonNull INetwork.ResponseItemOverviewModelListener listener,
                                                                @NonNull INetwork.ResponseErrorListener errorListener) {
        getINetwork().getItemOverviewModels(url, listener, errorListener);
    }

    public static boolean canAccessWeb() {
        int networkType = getNetworkType();
        if (networkType == NETWORK_TYPE_WIFI) {
            return true;
        } else if (networkType == NETWORK_TYPE_2G || networkType == NETWORK_TYPE_3G || networkType == NETWORK_TYPE_4G) {
            if (ConfigManager.getCanUseMobileNetwork()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public static void getImage(String url, int maxWidth, int maxHeight, Bitmap.Config config,
                                @NonNull INetwork.ResponseImageListenrer listenrer,
                                @NonNull INetwork.ResponseErrorListener errorListener) {
        getINetwork().getImage(url, maxWidth, maxHeight, config, listenrer, errorListener);
    }
}
