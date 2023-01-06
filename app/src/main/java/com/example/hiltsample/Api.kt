package com.example.hiltsample

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("home/selectAppVersion.do")
    suspend fun callAppVersionApi(
        @Field("sysDiv") appType: String,
    ): Response<AppVersionResponse>
}