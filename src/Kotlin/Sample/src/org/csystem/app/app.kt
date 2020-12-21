/*----------------------------------------------------------------------------------------------------------------------
    Tarih-zaman sınıflarının withXXX metotları
    Sınıf Çalışması: Klavyeden alınan gün, ay ve yıl bilgilerine göre kişinin doğum günü geçmişse
    "geçmiş doğum gününüz kutlu olsun", o an doğum günü ise "doğum gününüz kutlu olsun", doğum günü henüz gelmemişse
    "doğum gününüzü şimdiden kutlarız" mesajlarından birini ekrana basan programı yazınız
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

fun main()
{
    val now = LocalDate.now()
    val birthDate = LocalDate.of(1976, Month.SEPTEMBER, 10)
    val birthDay = birthDate.withYear(now.year)

    val age = ChronoUnit.DAYS.between(birthDate, now) / 365.0

    println(age)
    println(birthDay)
}
