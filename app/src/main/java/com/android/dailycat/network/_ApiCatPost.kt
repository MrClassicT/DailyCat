//package com.android.dailycat.network
//
//import com.android.dailycat.model.CatPost
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class ApiCatPost(
//    val image: ByteArray,
//    val quote: String,
//) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as ApiCatPost
//
//        if (!image.contentEquals(other.image)) return false
//        if (quote != other.quote) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = image.contentHashCode()
//        result = 31 * result + quote.hashCode()
//        return result
//    }
//}
//
//// extension function for an ApiTask List to convert is to a Domain Task List
//fun Flow<List<ApiCatPost>>.asDomainObjects(): Flow<List<CatPost>> {
//    return map {
//        it.asDomainObjects()
//    }
//}
//
//fun List<ApiCatPost>.asDomainObjects(): List<CatPost> {
//    var domainList = this.map {
//        CatPost(it.image, it.quote)
//    }
//    return domainList
//}
