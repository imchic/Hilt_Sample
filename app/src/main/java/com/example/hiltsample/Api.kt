package com.example.hiltsample

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @FormUrlEncoded
    @POST("home/selectAppVersion.do")
    suspend fun callAppVersionApi(
        //@Field("sysDiv") appType: String,
        @Path("sysDiv") appType: String,
    ): Response<AppVersionResponse>

    @FormUrlEncoded
    @POST("sps/selectSurvHuInfo.do")
    suspend fun callSurvHuInfo(
        @Field("cpCd") pCpcd: String,
        @Field("cdwCd") pCdwCd: String,
        @Field("ttbCd") pTtbCd: String,
        @Field("pEdId") pEdId: String,
        @Field("hhdLtnmId") hhdLtnmId: String,
        @Field("hhdNum") hhdNum: String,
        @Field("survId") survId: String,
    ): Response<SurvHuInfoResponse>
}