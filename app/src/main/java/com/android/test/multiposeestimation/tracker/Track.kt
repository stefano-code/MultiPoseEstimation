package com.android.test.multiposeestimation.tracker


import com.android.test.multiposeestimation.data.Person

data class Track(
    val person: Person,
    val lastTimestamp: Long
)