package com.example.kuijin.mycnblogs.common.disk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.example.kuijin.mycnblogs.common.CnBlogsApplication;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public class DiskManager {

    private static final String ItemModelsPath = "itemmodels";
    private static final String HeaderImagePath = "images";
    private static final String ItemModelsSuffix = ".txt";
    private static final String HeaderImageSuffix = ".jpg";

    private static final String Tag = "DiskManager";


    /**
     * 从硬盘中获取博主的头像
     * */
    public static Bitmap getHeaderBitmap(String url) throws FileNotFoundException {
        String path = getPath(HeaderImagePath);
        if (TextUtils.isEmpty(path)) {
            throw new FileNotFoundException("Path of HeaderImage not found!");
        }

        String filePath = path + File.separator + url + HeaderImageSuffix;
        File file = new File(filePath);
        if (file.exists()) {
            Bitmap headerImage = BitmapFactory.decodeFile(filePath);
            return headerImage;
        }

        return null;
    }

    /**
     * 向硬盘中写入博主头像
     * */
    public static boolean setHeaderImageToDisk(String url, Bitmap bitmap) throws IOException {
        String path = getPath(HeaderImagePath);
        if (TextUtils.isEmpty(path)) {
            throw new FileNotFoundException("Path of HeaderImage not found!");
        }
        
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        String filePath = path + File.separator + url + HeaderImageSuffix;
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        
        boolean success = writeToImage(file, bitmap);
        return success;
    }

    private static boolean writeToImage(File file, Bitmap bitmap) throws IOException {
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            return bitmap.compress(Bitmap.CompressFormat.WEBP, 50, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }
        }

        return false;
    }

    /**
     * 从硬盘中获取ItemOverviewModel列表
     * */
    public static List<IItemOverviewModel> getItemOverviewModels(String url) throws IOException {
        String path = getPath(ItemModelsPath);
        if (TextUtils.isEmpty(path)) {
            throw new FileNotFoundException("Path of ItemModels not found!");
        }

        String filePath = path + File.separator + url + ItemModelsSuffix;
        String info = getFileInfos(filePath);
        if (null != info) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ItemOverviewModel>>(){}.getType();
            List<IItemOverviewModel> list = gson.fromJson(info, type);
            return list;
        }

        return null;
    }

    /**
     * 向硬盘中存入ItemOverviewModel列表
     * */
    public static boolean setItemOverviewListToDisk(String url, List<IItemOverviewModel> list) throws IOException {
        String path = getPath(ItemModelsPath);
        if (TextUtils.isEmpty(path)) {
            throw new FileNotFoundException("Path of ItemModels not found!");
        }

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String filePath = path + File.separator + url + ItemModelsSuffix;
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        return writeToFile(filePath, list);
    }

    private static String getFileInfos(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bf = null;

        if (file.exists()) {
            try {
                bf = new BufferedReader(new FileReader(file));

                StringBuffer sb = new StringBuffer();
                String line = bf.readLine();

                while (null != line) {
                    sb.append(line);
                }

                return sb.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw e;
            } catch (IOException ioE) {
                ioE.printStackTrace();
                throw ioE;
            } finally {
                if (null != bf) {
                    bf.close();
                }
            }
        }

        return null;
    }

    private static boolean writeToFile(String filePath, List<IItemOverviewModel> list) throws IOException {
        FileWriter writer = null;

        try {
            writer = new FileWriter(filePath, false);

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ItemOverviewModel>>(){}.getType();
            String infos = gson.toJson(list, type);

            writer.write(infos);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    private static String getPath(String dirName) {
        String path = null;
        boolean hasSdCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (hasSdCard) {
            path = CnBlogsApplication.getApplication().getExternalCacheDir().getAbsolutePath() +
                    File.separator + dirName;
        } else {
            path = CnBlogsApplication.getApplication().getCacheDir().getAbsolutePath() +
                    File.separator + dirName;
        }

        return path;
    }




    private static void testDir() {
        String externalPath = CnBlogsApplication.getApplication().getExternalFilesDir(ItemModelsPath).getAbsolutePath();
        String externalCachePath = CnBlogsApplication.getApplication().getExternalCacheDir().getAbsolutePath();

        File[] mediaDirs;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaDirs = CnBlogsApplication.getApplication().getExternalMediaDirs();
        }

        String noBackupFile;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            noBackupFile = CnBlogsApplication.getApplication().getNoBackupFilesDir().getAbsolutePath();
        }
        String path  = CnBlogsApplication.getApplication().getDir(ItemModelsPath, Context.MODE_PRIVATE).getAbsolutePath();

        String dataPath;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            dataPath = CnBlogsApplication.getApplication().getDataDir().getAbsolutePath();
        }
//        String path = getPath();

        String cachePath = CnBlogsApplication.getApplication().getCacheDir().getAbsolutePath();

        String obbPath = CnBlogsApplication.getApplication().getObbDir().getAbsolutePath();
        String codeCachePath;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            codeCachePath = CnBlogsApplication.getApplication().getCodeCacheDir().getAbsolutePath();
        }

        String filesPath = CnBlogsApplication.getApplication().getFilesDir().getAbsolutePath();

        String ppp = getPath(ItemModelsPath);
    }
}
