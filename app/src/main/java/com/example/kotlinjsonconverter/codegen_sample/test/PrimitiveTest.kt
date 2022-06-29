package com.example.kotlinjsonconverter.codegen_sample.test

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class PrimitiveTest(
    @JvmField
    @SerialName("Nullable Byte")         // codegen
    @JsonName("Nullable Byte")           // abson
    @SerializedName("Nullable Byte")     // gson
    val mNullableByte: Byte?,
    @JvmField
    @SerialName("Byte")
    @JsonName("Byte")
    @SerializedName("Byte")
    val mByte: Byte,
    @JvmField
    @SerialName("Nullable Short")
    @JsonName("Nullable Short")
    @SerializedName("Nullable Short")
    val mNullableShort: Short?,
    @JvmField
    @SerialName("Short")
    @JsonName("Short")
    @SerializedName("Short")
    val mShort: Short,
    @JvmField
    @SerialName("Nullable Int")
    @JsonName("Nullable Int")
    @SerializedName("Nullable Int")
    val mNullableInt: Int?,
    @JvmField
    @SerialName("Int")
    @JsonName("Int")
    @SerializedName("Int")
    val mInt: Int,
    @JvmField
    @SerialName("Nullable Long")
    @JsonName("Nullable Long")
    @SerializedName("Nullable Long")
    val mNullableLong: Long?,
    @JvmField
    @SerialName("Long")
    @JsonName("Long")
    @SerializedName("Long")
    val mLong: Long,
    @JvmField
    @SerialName("Nullable Float")
    @JsonName("Nullable Float")
    @SerializedName("Nullable Float")
    val mNullableFloat: Float?,
    @JvmField
    @SerialName("Float")
    @JsonName("Float")
    @SerializedName("Float")
    val mFloat: Float,
    @JvmField
    @SerialName("Nullable Double")
    @JsonName("Nullable Double")
    @SerializedName("Nullable Double")
    val mNullableDouble: Double?,
    @JvmField
    @SerialName("Double")
    @JsonName("Double")
    @SerializedName("Double")
    val mDouble: Double,
    @JvmField
    @SerialName("Nullable Boolean")
    @JsonName("Nullable Boolean")
    @SerializedName("Nullable Boolean")
    val mNullableBoolean: Boolean?,
    @JvmField
    @SerialName("Boolean")
    @JsonName("Boolean")
    @SerializedName("Boolean")
    val mBoolean: Boolean,
    @JvmField
    @SerialName("Nullable Char")
    @JsonName("Nullable Char")
    @SerializedName("Nullable Char")
    val mNullableChar: Char?,
    @JvmField
    @SerialName("Char")
    @JsonName("Char")
    @SerializedName("Char")
    val mChar: Char,

    /** Add String Test */
    @JvmField
    @SerialName("Nullable String")
    @JsonName("Nullable String")
    @SerializedName("Nullable String")
    val mNullableString: String?,
    @JvmField
    @SerialName("String")
    @JsonName("String")
    @SerializedName("String")
    val mString: String,

    /** Add Collection */
    @JvmField
    @SerialName("Collection")
    @JsonName("Collection")
    @SerializedName("Collection")
    //val mCollection: Collection<Int?>?,
    val mCollection: List<Int?>?,

    /** Add Map */
    @JvmField
    @SerialName("Map")
    @JsonName("Map")
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