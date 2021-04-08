package com.example.epifi.utils

import java.util.*
import java.util.regex.Pattern

object DateValidator {
    /**
     * Method to validate date, month and year. It is checking the year, month and date are valid or not, through all conditions provided.
     */
    fun validateDate(date: String): Boolean {
        val regex = "^(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$"
        val matcher = Pattern.compile(regex).matcher(date)
        return if (matcher.matches()) {
            matcher.reset()
            if (matcher.find()) {
                val dateDetails = date.split("/")
                val day: String = dateDetails[1]
                val month: String = dateDetails[0]
                val year: String = dateDetails[2]
                if (validateMonthWithMaxDate(day, month)) {
                    false
                } else if (isFebruaryMonth(month)) {
                    if (isLeapYear(year)) {
                        leapYearWith29Date(day)
                    } else {
                        notLeapYearFebruary(day)
                    }
                } else {
                    isBeforeToday(year.toInt(),month.toInt(),day.toInt())
                }
            } else {
                false
            }
        } else {
            false
        }
    }

    private fun validateMonthWithMaxDate(day: String, month: String): Boolean = day == "31" && (month == "4" || month == "6" || month == "9" || month == "11" || month == "04" || month == "06" || month == "09")
    private fun isFebruaryMonth(month: String): Boolean = month == "2" || month == "02"
    private fun isLeapYear(year: String): Boolean = year.toInt() % 4 == 0
    private fun leapYearWith29Date(day: String): Boolean = !(day == "30" || day == "31")
    private fun notLeapYearFebruary(day: String): Boolean = !(day == "29" || day == "30" || day == "31")

    /**
     * Method to check if date provided is less than the current date of system
     */
    private fun isBeforeToday(year: Int, month: Int, day: Int): Boolean {
        val today = Calendar.getInstance()
        val myDate = Calendar.getInstance()
        myDate[year, month] = day
        return myDate.before(today)
    }
}