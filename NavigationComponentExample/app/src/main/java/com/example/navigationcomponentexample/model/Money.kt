package com.example.navigationcomponentexample.model

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

data class Money (val amount: BigDecimal): Parcelable {
    constructor(parcel: Parcel) : this(BigDecimal(0)) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Money> {
        override fun createFromParcel(parcel: Parcel): Money {
            return Money(parcel)
        }

        override fun newArray(size: Int): Array<Money?> {
            return arrayOfNulls(size)
        }
    }
}