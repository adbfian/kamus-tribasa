package com.bafika.adbfian.tribasaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adbfian on 24/09/2016.
 */
public class DataKata implements Parcelable {

    public int id;
    public String ngoko_kata;
    public String ngoko_suara;
    public String kromo_kata;
    public String kromo_suara;
    public String indo_kata;
    public String indo_suara;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndo_kata() {
        return indo_kata;
    }

    public void setIndo_kata(String indo_kata) {
        this.indo_kata = indo_kata;
    }

    public String getIndo_suara() {
        return indo_suara;
    }

    public void setIndo_suara(String indo_suara) {
        this.indo_suara = indo_suara;
    }

    public String getKromo_kata() {
        return kromo_kata;
    }

    public void setKromo_kata(String kromo_kata) {
        this.kromo_kata = kromo_kata;
    }

    public String getKromo_suara() {
        return kromo_suara;
    }

    public void setKromo_suara(String kromo_suara) {
        this.kromo_suara = kromo_suara;
    }

    public String getNgoko_kata() {
        return ngoko_kata;
    }

    public void setNgoko_kata(String ngoko_kata) {
        this.ngoko_kata = ngoko_kata;
    }

    public String getNgoko_suara() {
        return ngoko_suara;
    }

    public void setNgoko_suara(String ngoko_suara) {
        this.ngoko_suara = ngoko_suara;
    }

    protected DataKata(Parcel in) {
        this.id = in.readInt();
        this.ngoko_kata = in.readString();
        this.ngoko_suara = in.readString();
        this.kromo_kata = in.readString();
        this.kromo_suara = in.readString();
        this.indo_kata = in.readString();
        this.indo_suara = in.readString();

    }

    public static final Parcelable.Creator<DataKata> CREATOR = new Parcelable.Creator<DataKata>() {
        @Override
        public DataKata createFromParcel(Parcel source) {
            return new DataKata(source);
        }

        @Override
        public DataKata[] newArray(int i) {
            return new DataKata[i];
        }
    };

    public DataKata() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.ngoko_kata);
        parcel.writeString(this.ngoko_suara);
        parcel.writeString(this.kromo_kata);
        parcel.writeString(this.kromo_suara);
        parcel.writeString(this.indo_kata);
        parcel.writeString(this.indo_suara);
    }
}
