package com.example.gson

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("firstName") val mFirstName: String,
    @SerializedName("age") val mAge: Int,
    @SerializedName("mail") val mMail: String,
    @SerializedName("address") val mAddress: Address,
    @SerializedName("family") val mFamilyMembers: Array<FamilyMember>,
    @Transient val password: String
)