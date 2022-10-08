package models

import ANSI_WHITE


import roundTwoDecimals

data class Employee(
    var firstName: String, var surName: String, var gender: Char, var employeeId: Int,
    var grossSalary: Double, var PAYE: Double, var PRSI: Double,
    var annualBonusAmount: Double, var cycleToWorkSchemeMonthlyDeduction: Double ) {



    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. $firstName $surName"
        'f', 'F' -> "Ms. $firstName $surName"
        else -> "$firstName $surName"
    }

    fun monthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun monthlyBonus() = roundTwoDecimals(annualBonusAmount / 12)
    fun grossPay() = roundTwoDecimals(monthlySalary() + monthlyBonus())

    fun payePayableMonthly() = roundTwoDecimals(monthlySalary() * PAYE / 100 * 1)
    fun prsiPayableMonthly() = roundTwoDecimals(monthlySalary() * PRSI / 100 * 1)
    fun totalDeductions() =
        roundTwoDecimals(payePayableMonthly() + prsiPayableMonthly() + cycleToWorkSchemeMonthlyDeduction)

    fun netPay() = roundTwoDecimals(grossPay() - totalDeductions())


    fun getPayslip() {
        println(
            """${ANSI_WHITE}
            -------------------------------------------------------------------------------
                                             Monthly Payslip:   
            -------------------------------------------------------------------------------
             Employee Name: ${getFullName().uppercase()}                Employee ID: ${employeeId}
            
            -------------------------------------------------------------------------------
               PAYMENT DETAILS                           DEDUCTION DETAILS       
            -------------------------------------------------------------------------------
                Salary: ${(monthlySalary())}                           PAYE: ${(payePayableMonthly())} 
                Bonus:  ${(monthlyBonus())}                           PRSI: ${(prsiPayableMonthly())}
                                                         Cycle To Work: ${(cycleToWorkSchemeMonthlyDeduction)}
            ------------------------------------------------------------------------------- 
                Gross:  ${(grossPay())}                         Total Deductions: ${(totalDeductions())}    
            -------------------------------------------------------------------------------
                                           NET PAY: ${(netPay())}
            -------------------------------------------------------------------------------                               
                    """.trimIndent()
        )

    }

    override fun toString(): String {
        return "Employee(firstName='$firstName', surName='$surName', gender=$gender, employeeId=$employeeId, grossSalary=$grossSalary, PAYE=$PAYE, PRSI=$PRSI, annualBonusAmount=$annualBonusAmount, cycleToWorkSchemeMonthlyDeduction=$cycleToWorkSchemeMonthlyDeduction)"
    }


}