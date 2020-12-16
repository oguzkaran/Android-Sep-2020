package org.csystem.app.samples.companyapp

open class Worker(name: String, citizenId: String, address: String, var feePerHour: Double, var hourPerDay: Int)
    : Employee(name, citizenId, address) {
    //...

    override fun calculateInsurancePayment() = feePerHour * hourPerDay * 30
}