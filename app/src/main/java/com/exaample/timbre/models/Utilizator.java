package com.exaample.timbre.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//  07.12
@Entity
public class Utilizator {
    //dependintele in fis de gradle
//    def room_version = "2.2.2"
//    implementation "androidx.room:room-runtime:$room_version"
//    annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor
    @NonNull
    @PrimaryKey
    public String id;

    @ColumnInfo(name = "nume")
    public String numeUtilizator;

    @ColumnInfo(name = "parola")
    public String parola;

    public Utilizator(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }

    public Utilizator() {
    }
}
