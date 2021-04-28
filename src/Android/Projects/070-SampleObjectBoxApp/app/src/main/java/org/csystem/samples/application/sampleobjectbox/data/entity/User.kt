package org.csystem.samples.application.sampleobjectbox.data.entity

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import io.objectbox.converter.PropertyConverter
import org.csystem.util.datetime.DateTimeUtil
import java.time.LocalDateTime

@Entity
data class User(@Id var id : Long = 0,
                var name: String = "",
                var username: String = "",
                var password: String = "",
                @Convert(converter = LocalDateTimeConverter::class, dbType = Long::class)
                var registerDate: LocalDateTime = LocalDateTime.now()) {

    public class LocalDateTimeConverter : PropertyConverter<LocalDateTime, Long> {
        override fun convertToEntityProperty(databaseValue: Long?): LocalDateTime
        {
            return DateTimeUtil.toLocalDateTime(if (databaseValue == null) 0 else databaseValue)
        }

        override fun convertToDatabaseValue(entityProperty: LocalDateTime): Long
        {
            return DateTimeUtil.toMilliseconds(entityProperty)
        }
    }


    override fun toString() = username
}