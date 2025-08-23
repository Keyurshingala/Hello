package com.example.mlkitapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mlkitapp.databinding.ActivityDocumentScannerBinding
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class DocumentScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDocumentScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocumentScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(false)
            .setPageLimit(10)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG, GmsDocumentScannerOptions.RESULT_FORMAT_PDF)
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
            .build()

        val scanner = GmsDocumentScanning.getClient(options)
        val scannerLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val scanningResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
                scanningResult?.getJpgs()?.let { pages ->
                    if (pages.isNotEmpty()) {
                        val firstPageUri = pages[0].uri
                        Glide.with(this)
                            .load(firstPageUri)
                            .into(binding.ivScannedImage)
                    }
                }
            }
        }

        binding.btnStartScan.setOnClickListener {
            scanner.getStartScanIntent(this)
                .addOnSuccessListener { intentSender ->
                    scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        }
    }
}
