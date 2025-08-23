package com.example.mlkitapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.google.mlkit.vision.segmentation.SegmentationMask
import java.nio.ByteBuffer

class OverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var mask: SegmentationMask? = null
    private var imageWidth: Int = 0
    private var imageHeight: Int = 0
    private val maskPaint: Paint = Paint().apply {
        color = Color.argb(128, 0, 255, 0) // Semi-transparent green
        style = Paint.Style.FILL
    }

    fun setMask(mask: SegmentationMask?, imageWidth: Int, imageHeight: Int) {
        this.mask = mask
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mask?.let {
            val bitmap = convertMaskToBitmap(it, imageWidth, imageHeight)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }
    }

    private fun convertMaskToBitmap(mask: SegmentationMask, imageWidth: Int, imageHeight: Int): Bitmap {
        val maskBuffer = mask.buffer
        val maskWidth = mask.width
        val maskHeight = mask.height

        val bitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
        val scaleX = imageWidth.toFloat() / maskWidth
        val scaleY = imageHeight.toFloat() / maskHeight

        for (y in 0 until maskHeight) {
            for (x in 0 until maskWidth) {
                val confidence = maskBuffer.float
                if (confidence > 0.5) { // Threshold for segmentation
                    val canvasX = (x * scaleX).toInt()
                    val canvasY = (y * scaleY).toInt()
                    if (canvasX < imageWidth && canvasY < imageHeight) {
                        bitmap.setPixel(canvasX, canvasY, maskPaint.color)
                    }
                }
            }
        }
        maskBuffer.rewind()
        return bitmap
    }
}
