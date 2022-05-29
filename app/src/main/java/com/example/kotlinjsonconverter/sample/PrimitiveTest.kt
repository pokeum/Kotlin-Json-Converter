package com.example.kotlinjsonconverter.sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class PrimitiveTest(
    @JvmField
    @SerialName("Nullable Byte")
    val mNullableByte: Byte?,
    @JvmField
    @SerialName("Byte")
    val mByte: Byte,
    @JvmField
    @SerialName("Nullable Short")
    val mNullableShort: Short?,
    @JvmField
    @SerialName("Short")
    val mShort: Short,
    @JvmField
    @SerialName("Nullable Int")
    val mNullableInt: Int?,
    @JvmField
    @SerialName("Int")
    val mInt: Int,
    @JvmField
    @SerialName("Nullable Long")
    val mNullableLong: Long?,
    @JvmField
    @SerialName("Long")
    val mLong: Long,
    @JvmField
    @SerialName("Nullable Float")
    val mNullableFloat: Float?,
    @JvmField
    @SerialName("Float")
    val mFloat: Float,
    @JvmField
    @SerialName("Nullable Double")
    val mNullableDouble: Double?,
    @JvmField
    @SerialName("Double")
    val mDouble: Double,
    @JvmField
    @SerialName("Nullable Boolean")
    val mNullableBoolean: Boolean?,
    @JvmField
    @SerialName("Boolean")
    val mBoolean: Boolean,
    @JvmField
    @SerialName("Nullable Char")
    val mNullableChar: Char?,
    @JvmField
    @SerialName("Char")
    val mChar: Char,

    /** Add String Test */
    @JvmField
    @SerialName("Nullable String")
    val mNullableString: String?,
    @JvmField
    @SerialName("String")
    val mString: String,

    /** Add Collection */
    @JvmField
    @SerialName("Collection")
    //val mCollection: Collection<Int?>?
    val mCollection: List<Int?>?
    //val mCollection: List<Int>?

) {
    override fun toString(): String {
        return """
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
                mCollection: $mCollection
            }
        """.trimIndent()
    }
}