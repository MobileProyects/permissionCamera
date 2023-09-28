package com.example.permission_camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val CAMERA_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamera = findViewById<Button>(R.id.btCamera)

        btCamera.setOnClickListener{
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            requestCameraPermission()
        }else{
            Toast.makeText(this,"You already have permission to use the camera", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //rechazo el permiso antes
            Toast.makeText(this,"I already rejected the permission before, enable it manually in app settings", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,"He never asked for permission, accept or reject", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission was granted", Toast.LENGTH_LONG).show()

                    //funcion...
                }else{
                    Toast.makeText(this, "Denied Permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}