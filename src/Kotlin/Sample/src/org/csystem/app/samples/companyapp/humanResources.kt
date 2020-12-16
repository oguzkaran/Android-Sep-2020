package org.csystem.app.samples.companyapp

//...
fun payInsurance(employee: Employee)
{
    println("Name:${employee.name}")
    println("Citizen Id:${employee.citizenId}")
    println("Address:${employee.address}")
    println("Payment:${employee.calculateInsurancePayment()}")
    println("//////////////////")
}