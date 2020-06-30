package com.example.app.entity

// kotlin 要求 data class 的参数必须带上 var/val，强制创建成员变量
data class User(var username: String?, var password: String?, var code: String?) {

    constructor() : this(null, null, null)

}