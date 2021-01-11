package org.csystem.samples.application.basicdatabinding

enum class DeviceStatus(val str: String) {
   CONNECTED("Bağlı"), DISCONNECTED("Bağlı Değil"), CLOSE("Kapalı");
   override fun toString() = str
}