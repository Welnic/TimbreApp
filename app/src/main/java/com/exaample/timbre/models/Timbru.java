package com.exaample.timbre.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Utilizator.class,
        parentColumns = "id",
        childColumns = "idUser",
        onDelete = CASCADE))
public class Timbru implements Parcelable {

    //http://www.mocky.io/v2/5dd8e2843100007400055ef2
    @NonNull
    @PrimaryKey
    private String id;
    private String serie;
    private int an;
    private int nou;
    private String tematica;
    private String marime;

    private String idUser;

    public Timbru() {
    }

    public Timbru(Parcel parcel) {
        id = parcel.readString();
        serie = parcel.readString();
        tematica = parcel.readString();
        an = parcel.readInt();
        marime = parcel.readString();
        nou = parcel.readInt();
    }

    public Timbru(String id, String serie, int an, String tematica, String marime) {
        this.id = id;
        this.serie = serie;
        this.tematica = tematica;
        this.an = an;
        this.marime = marime;
    }

    public int getNou() {
        return nou;
    }

    public void setNou(int nou) {
        this.nou = nou;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getMarime() {
        return marime;
    }

    public void setMarime(String marime) {
        this.marime = marime;
    }

    @Override
    public String toString() {
        return "TImbrul " +
                id +
                " cu seria " + serie + " are tematica " + tematica +
                " este fabricat in anul " + an +
                " avand " + marime + "mm";
    }

    public static final Creator<Timbru> CREATOR = new Creator<Timbru>() {
        @Override
        public Timbru createFromParcel(Parcel in) {
            return new Timbru(in);
        }

        @Override
        public Timbru[] newArray(int size) {
            return new Timbru[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(serie);
        dest.writeString(tematica);
        dest.writeInt(an);
        dest.writeString(marime);
        dest.writeInt(nou);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
