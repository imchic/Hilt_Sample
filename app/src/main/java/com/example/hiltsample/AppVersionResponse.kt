package com.example.hiltsample

data class AppVersionResponse (
    val appVersionList: List<AppVersionResponse>,
    val sysDiv: String,
    val appVersionNo: String,
    val memo: String,
    val appVersionNoTest: String,
    val memoTest: String,
    val webVersionNo: String,
    val webVersionNoTest: String,
    val networkLevel: String,
    val networkOnlineSignal: String,
    val networkOfflineSignal: String,
    val networkSignal: String,
    val urgencyMemo: String,
    val urgencyMemoNo: String,
)
