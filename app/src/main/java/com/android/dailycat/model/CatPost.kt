package com.android.dailycat.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class CatPost(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var image: ByteArray,
    var quote: String,
    var favorite: Boolean = false,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatPost

        if (!image.contentEquals(other.image)) return false
        if (quote != other.quote) return false
        if (favorite != other.favorite) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.contentHashCode()
        result = 31 * result + quote.hashCode()
        result = 31 * result + favorite.hashCode()
        return result
    }


}
