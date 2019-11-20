package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 */

public class Discuz implements Parcelable {
    public String id;
    public String master_name;
    public String title;
    public String content;
    public String date;
    public int view_num;
    public int msg_num;

    public Discuz() {
    }

    public Discuz(String id, String master_name, String title, String content, String date, int view_num, int msg_num) {
        this.id = id;
        this.master_name = master_name;
        this.title = title;
        this.content = content;
        this.date = date;
        this.view_num = view_num;
        this.msg_num = msg_num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.master_name);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.date);
        dest.writeInt(this.view_num);
        dest.writeInt(this.msg_num);
    }

    protected Discuz(Parcel in) {
        this.id = in.readString();
        this.master_name = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.date = in.readString();
        this.view_num = in.readInt();
        this.msg_num = in.readInt();
    }

    public static final Parcelable.Creator<Discuz> CREATOR = new Parcelable.Creator<Discuz>() {
        @Override
        public Discuz createFromParcel(Parcel source) {
            return new Discuz(source);
        }

        @Override
        public Discuz[] newArray(int size) {
            return new Discuz[size];
        }
    };
}
