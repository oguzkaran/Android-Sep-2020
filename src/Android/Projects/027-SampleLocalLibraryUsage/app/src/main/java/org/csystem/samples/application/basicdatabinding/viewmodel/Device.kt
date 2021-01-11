package org.csystem.samples.application.basicdatabinding.viewmodel

import org.csystem.samples.application.basicdatabinding.DeviceStatus
import java.time.LocalDate

data class Device(var name: String = "", var host: String = "",
                  var port: Int = 0, var registerDate: LocalDate = LocalDate.now(),
                  var status: DeviceStatus = DeviceStatus.CLOSE)