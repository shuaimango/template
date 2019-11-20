package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 */

public class Manhua implements Parcelable {
    public String id;
    public String num;
    public String title;
    public String date;
    public int cover;

    public Manhua() {
    }

    public Manhua(String id, String num, String title, int cover, String date) {
        this.cover = cover;
        this.id = id;
        this.num = num;
        this.title = title;
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.num);
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeInt(this.cover);
    }

    protected Manhua(Parcel in) {
        this.id = in.readString();
        this.num = in.readString();
        this.title = in.readString();
        this.date = in.readString();
        this.cover = in.readInt();
    }

    public static final Creator<Manhua> CREATOR = new Creator<Manhua>() {
        @Override
        public Manhua createFromParcel(Parcel source) {
            return new Manhua(source);
        }

        @Override
        public Manhua[] newArray(int size) {
            return new Manhua[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manhua manhua = (Manhua) o;
        return Objects.equals(id, manhua.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
