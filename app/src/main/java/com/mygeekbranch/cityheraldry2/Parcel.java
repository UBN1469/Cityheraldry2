package com.mygeekbranch.cityheraldry2;

import java.io.Serializable;
//Класс обмена между фрагментами
public class Parcel implements Serializable {

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCitiName() {
        return citiName;
    }

    private  int imageIndex;
    private String citiName;

    public Parcel(int imageIndex, String citiName) {
        this.imageIndex = imageIndex;
        this.citiName = citiName;
    }


}
