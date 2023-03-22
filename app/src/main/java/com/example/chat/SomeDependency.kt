package com.example.chat

import javax.inject.Inject

class SomeDependency @Inject constructor(
    val info: String,
    val count: Int
) {
}