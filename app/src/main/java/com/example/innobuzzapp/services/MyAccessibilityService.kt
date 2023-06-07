package com.example.innobuzzapp.services

import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log


class MyAccessibilityService: android.accessibilityservice.AccessibilityService() {
    var TAG = "MyAccessbilityService.kt"
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if(event.packageName.contains("whatsapp")) {
            Toast.makeText(this, "${event.packageName}", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onAccessibilityEvent: $event")
        }
    }

    override fun onInterrupt() {
        Toast.makeText(this, "interrupted", Toast.LENGTH_LONG).show()
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        var info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
        info.notificationTimeout = 100
        this.serviceInfo = info
    }
}