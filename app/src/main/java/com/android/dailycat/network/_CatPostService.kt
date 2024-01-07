//package com.android.dailycat.network
//
//import android.util.Log
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.http.GET
//
//
//interface CatPostService {
//    // suspend is added to force the user to call this in a coroutine scope
//    @GET("catposts")
//    suspend fun getCatPosts(): List<ApiCatPost>
//}
//
//// helper function
//fun CatPostService.getCatPostsAsFlow(): Flow<List<ApiCatPost>> = flow {
//    try {
//        emit(getCatPosts())
//    } catch (e: Exception) {
//        Log.e("API", "getCatPostsAsFlow: " + e.stackTraceToString())
//    }
//}
