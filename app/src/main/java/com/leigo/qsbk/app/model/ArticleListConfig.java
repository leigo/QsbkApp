package com.leigo.qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/8/18.
 */
public class ArticleListConfig implements Parcelable, Serializable {

    public boolean mIsShuffle;
    public String mTitle;
    public String mUniqueName;
    public String mUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ArticleListConfig [mTitle=" + this.mTitle + ", mUniqueName=" + this.mUniqueName + ", mUrl=" + this.mUrl + ", mIsShuffle=" + this.mIsShuffle + "]";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (mIsShuffle ? 1 : 0));
        dest.writeString(mTitle);
        dest.writeString(mUniqueName);
        dest.writeString(mUrl);
    }

    public static final Parcelable.Creator<ArticleListConfig> CREATOR = new Parcelable.Creator<ArticleListConfig>() {

        @Override
        public ArticleListConfig createFromParcel(Parcel source) {
            return new ArticleListConfig(source);

        }

        @Override
        public ArticleListConfig[] newArray(int size) {
            return new ArticleListConfig[size];
        }
    };

    private ArticleListConfig(Parcel source) {
        mIsShuffle = source.readByte() != 0;
        mTitle = source.readString();
        mUniqueName = source.readString();
        mUrl = source.readString();
    }
}
