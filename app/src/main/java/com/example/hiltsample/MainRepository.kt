package com.example.hiltsample

import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: Api
) {
    fun getBaeUrl() = NetworkModule.BASE_URL

    suspend fun callAppVersion(): ResponseBody<AppVersionResponse> {
        return try {
            ApiResponse.create(api.callAppVersionApi("fSurvId")).apply {
                if (!body?.appVersionList.isNullOrEmpty()) {
                    Timber.d(body.toString())
                }
            }
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

    suspend fun callSurvHuInfo(): ResponseBody<SurvHuInfoResponse> {
        return try {
            ApiResponse.create(api.callSurvHuInfo(
                "99",
                "050",
                "60",
                "3999308",
                "3900040499",
                "001",
                "39"
            )).apply {
                //if (!body?.appVersionList.isNullOrEmpty()) {
                    Timber.d(body.toString())
                //}
            }
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

    //suspend fun callAppVersion() = api.callAppVersionApi("fSurvId")
}
