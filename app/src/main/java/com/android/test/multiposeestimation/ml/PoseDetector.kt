package com.android.test.multiposeestimation.ml


import android.graphics.Bitmap
import com.android.test.multiposeestimation.data.Person

interface PoseDetector : AutoCloseable {

    fun estimatePoses(bitmap: Bitmap): List<Person>

    fun lastInferenceTimeNanos(): Long
}