package org.csystem.app.samples.companyapp

fun runCompanyApp()
{
    var manager = Manager("Ali", "12345678912", "Mecidiyeköy", 20000.0)
    var worker = Worker("Veli", "2345678904", "Şişli", 90.5, 7)
    var salesManager = SalesManager("Selami", "12375678912", "Bayburt", 20000.0, 3000.5)

    //...

    payInsurance(manager)
    payInsurance(worker)
    payInsurance(salesManager)
}