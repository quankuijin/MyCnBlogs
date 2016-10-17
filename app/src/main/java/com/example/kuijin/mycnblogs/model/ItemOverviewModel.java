package com.example.kuijin.mycnblogs.model;

import android.text.TextUtils;

import com.example.kuijin.mycnblogs.presenter.page.itemoverview.IItemOverviewPresenter;

/**
 * Created by kuijin on 2016/9/15.
 */
public class ItemOverviewModel implements IItemOverviewModel {
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWriterImageSrc() {
        return writerImageSrc;
    }
    public void setWriterImageSrc(String writerImageSrc) {
        this.writerImageSrc = writerImageSrc;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getWriterName() {
        return writerName;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
    public String getWriteTime() {
        return writeTime;
    }
    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }
    public String getNewsUrl() {
        return newsUrl;
    }
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
    public int getRecommendCount() {
        return recommendCount;
    }
    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }
    public void setRecommendCount(String recommendCount) {
        if (!TextUtils.isEmpty(recommendCount)) {
            this.recommendCount = Integer.parseInt(recommendCount);
        }
    }
    public int getReadedCount() {
        return readedCount;
    }
    public void setReadedCount(int readedCount) {
        this.readedCount = readedCount;
    }
    public void setReadedCount(String countStr) {
        if (!TextUtils.isEmpty(countStr)) {
            this.readedCount = Integer.parseInt(countStr);
        }
    }
    public int getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
    public void setReplyCount(String countStr) {
        if (!TextUtils.isEmpty(countStr)) {
            this.replyCount = Integer.parseInt(countStr);
        }
    }


    private String title;
    private String writerImageSrc;
    private String summary;
    private String writerName;
    private String writerBlogUrl;
    private String writeTime;
    private String publishTime;
    private int recommendCount;
    private int readedCount;
    private int replyCount;
    private String newsUrl;
    private String Id;

    public int getSize() {
        int size = 0;

        if (null != getTitle()){
            size += getTitle().length();
        }

        if (null != getWriterImageSrc()) {
            size += getWriterImageSrc().length();
        }

        if (null != getSummary()) {
            size += getSummary().length();
        }

        if (null != getWriterName()) {
            size += getWriterName().length();
        }

        if (null != getWriterBlogUrl()) {
            size += getWriterBlogUrl().length();
        }

        if (null != getWriteTime()) {
            size += getWriteTime().length();
        }

        if (null != getPublishTime()) {
            size += getPublishTime().length();
        }

        size += 12; //recommendCount, readedCount, replyCount

        if (null != getNewsUrl()) {
            size += getNewsUrl().length();
        }

        return size;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getWriterBlogUrl() {
        return writerBlogUrl;
    }

    public void setWriterBlogUrl(String writerBlogUrl) {
        this.writerBlogUrl = writerBlogUrl;
    }
}
