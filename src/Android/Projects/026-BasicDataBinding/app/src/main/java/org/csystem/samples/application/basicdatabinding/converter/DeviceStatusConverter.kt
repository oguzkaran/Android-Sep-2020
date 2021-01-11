package org.csystem.samples.application.basicdatabinding.converter

import androidx.databinding.InverseMethod
import org.csystem.samples.application.basicdatabinding.DeviceStatus

object DeviceStatusConverter {
    @InverseMethod("toDeviceStatus")
    @JvmStatic
    fun toInt(status: DeviceStatus) = status.ordinal

    @JvmStatic
    fun toDeviceStatus(ordinal: Int) = DeviceStatus.values()[ordinal]
}