package com.example.mlkitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mlkitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDocumentScanner.setOnClickListener {
            startActivity(Intent(this, DocumentScannerActivity::class.java))
        }

        binding.btnSelfieSegmentation.setOnClickListener {
            startActivity(Intent(this, SelfieSegmentationActivity::class.java))
        }

        binding.btnSubjectSegmentation.setOnClickListener {
            startActivity(Intent(this, SubjectSegmentationActivity::class.java))
        }
    }
}
