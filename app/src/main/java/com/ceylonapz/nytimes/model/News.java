package com.ceylonapz.nytimes.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ceylonapz.nytimes.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AMAL on 2018-08-08.
 */

public class News extends BaseObservable implements Serializable {

    @SerializedName("title")
    @Bindable
    private final String title;

    @SerializedName("byline")
    @Bindable
    private final String byline;

    @SerializedName("published_date")
    @Bindable
    private final String published_date;

    public News(String byline, String title, String published_date) {
        this.title = title;
        this.byline = byline;
        this.published_date = published_date;

        notifyPropertyChanged(BR.title);
        notifyPropertyChanged(BR.byline);
        notifyPropertyChanged(BR.published_date);
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }
}
