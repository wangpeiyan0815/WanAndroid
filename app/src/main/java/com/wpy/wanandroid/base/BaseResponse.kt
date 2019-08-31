package com.wpy.wanandroid.base

data class BaseResponse<T>(val errorMsg: String, val errorCode: String, var data: T)