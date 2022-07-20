package com.example.kotlinjsonconverter.sample.test

import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class PrimitiveTest(
    @JvmField
    @SerialName("Nullable Byte")         // codegen
    @SerializedName("Nullable Byte")     // gson
    val mNullableByte: Byte?,
    @JvmField
    @SerialName("Byte")
    @SerializedName("Byte")
    val mByte: Byte,
    @JvmField
    @SerialName("Nullable Short")
    @SerializedName("Nullable Short")
    val mNullableShort: Short?,
    @JvmField
    @SerialName("Short")
    @SerializedName("Short")
    val mShort: Short,
    @JvmField
    @SerialName("Nullable Int")
    @SerializedName("Nullable Int")
    val mNullableInt: Int?,
    @JvmField
    @SerialName("Int")
    @SerializedName("Int")
    val mInt: Int,
    @JvmField
    @SerialName("Nullable Long")
    @SerializedName("Nullable Long")
    val mNullableLong: Long?,
    @JvmField
    @SerialName("Long")
    @SerializedName("Long")
    val mLong: Long,
    @JvmField
    @SerialName("Nullable Float")
    @SerializedName("Nullable Float")
    val mNullableFloat: Float?,
    @JvmField
    @SerialName("Float")
    @SerializedName("Float")
    val mFloat: Float,
    @JvmField
    @SerialName("Nullable Double")
    @SerializedName("Nullable Double")
    val mNullableDouble: Double?,
    @JvmField
    @SerialName("Double")
    @SerializedName("Double")
    val mDouble: Double,
    @JvmField
    @SerialName("Nullable Boolean")
    @SerializedName("Nullable Boolean")
    val mNullableBoolean: Boolean?,
    @JvmField
    @SerialName("Boolean")
    @SerializedName("Boolean")
    val mBoolean: Boolean,
    @JvmField
    @SerialName("Nullable Char")
    @SerializedName("Nullable Char")
    val mNullableChar: Char?,
    @JvmField
    @SerialName("Char")
    @SerializedName("Char")
    val mChar: Char,

    /** Add String Test */
    @JvmField
    @SerialName("Nullable String")
    @SerializedName("Nullable String")
    val mNullableString: String?,
    @JvmField
    @SerialName("String")
    @SerializedName("String")
    val mString: String,

    /** Add Collection */
    @JvmField
    @SerialName("Collection")
    @SerializedName("Collection")
    //val mCollection: Collection<Int?>?,
    val mCollection: List<Int?>?,
    //val mCollection: Set<Int?>?,

    /** Add Map */
    @JvmField
    @SerialName("Map")
    @SerializedName("Map")
    val mMap: Map<String, Int?>?
) {
    override fun toString() = """
            {
                mNullableByte: $mNullableByte,
                mByte: $mByte,
                mNullableShort: $mNullableShort,
                mShort: $mShort,
                mNullableInt: $mNullableInt,
                mInt: $mInt,
                mNullableLong: $mNullableLong,
                mLong: $mLong,
                mNullableFloat: $mNullableFloat,
                mFloat: $mFloat,
                mNullableDouble: $mNullableDouble,
                mDouble: $mDouble,
                mNullableBoolean: $mNullableBoolean,
                mBoolean: $mBoolean,
                mNullableChar: $mNullableChar,
                mChar: '$mChar',
                mNullableString: $mNullableString,
                mString: "$mString",
                mCollection: $mCollection,
                mMap: $mMap
            }
        """.trimIndent()
}