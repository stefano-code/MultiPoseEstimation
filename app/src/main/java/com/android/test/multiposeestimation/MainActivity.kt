package com.android.test.multiposeestimation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.android.test.multiposeestimation.ml.MoveNetMultiPose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // https://github.com/tensorflow/examples/blob/master/lite/examples/pose_estimation/android/app/build.gradle

    companion object {
        private const val FRAGMENT_DIALOG = "dialog"
    }

    /** A [SurfaceView] for camera preview.   */
    private lateinit var surfaceView: SurfaceView

    private var cameraController: CameraController? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(this, "This app needs camera permission.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        surfaceView = findViewById(R.id.surfaceView)
        checkCameraPermission()
    }

    override fun onStart() {
        super.onStart()
        startCamera()
    }

    override fun onResume() {
        cameraController?.resume()
        super.onResume()
    }

    override fun onPause() {
        cameraController?.close()
        cameraController = null
        super.onPause()
    }

    // start camera
    private fun startCamera() {
            if (cameraController == null) {
                cameraController = CameraController(surfaceView).apply {
                        prepareCamera()
                    }
                lifecycleScope.launch(Dispatchers.Main) {
                    cameraController?.initCamera()
                }
            }
            createPoseEstimator()
    }

    private fun createPoseEstimator() {

        val poseDetector = MoveNetMultiPose.create(
            this
        )
                poseDetector?.let { detector ->
            cameraController?.setDetector(detector)
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}