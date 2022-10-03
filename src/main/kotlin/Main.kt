import kotlin.math.round

fun main(args: Array<String>) {
    println(getFullName())
    printPayslip()
}

var firstName: String = "Joe "
var surName: String = "Soap "
var gender: Char = 'm'
var employeeId: Int = 6143
var grossSalary:Double = 67543.21
var PAYE: Double = 38.5
var PRSI: Double = 5.2
var annualBonusAmount: Double = 1450.50
var cycleToWorkSchemeMonthlyDeduction: Double = 54.33



var monthlySalary = roundTwoDecimals(grossSalary/12)
var monthlyBonus = roundTwoDecimals(annualBonusAmount/12)
var grossPay = roundTwoDecimals(monthlySalary+monthlyBonus)

var payePayableMonthly = roundTwoDecimals(monthlySalary*PAYE/100*1)
var prsiPayableMonthly = roundTwoDecimals(monthlySalary*PRSI/100*1)
var totalDeductions = roundTwoDecimals(payePayableMonthly+prsiPayableMonthly+cycleToWorkSchemeMonthlyDeduction)
var netPay =  roundTwoDecimals(grossPay - totalDeductions)

fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. $firstName $surName"
        'f', 'F' -> "Ms. $firstName $surName"
    else  -> "$firstName $surName"
}


fun printPayslip(){
    println(
        """
            -------------------------------------------------------------------------------
                                             Monthly Payslip:   
            -------------------------------------------------------------------------------
             Employee Name: ${firstName.uppercase()}${surName.uppercase()}(${gender.uppercase()})                         Employee ID: $employeeId
            
            -------------------------------------------------------------------------------
               PAYMENT DETAILS                           DEDUCTION DETAILS       
            -------------------------------------------------------------------------------
                Salary: ${(monthlySalary)}                           PAYE: ${(payePayableMonthly)} 
                Bonus:  ${(monthlyBonus)}                           PRSI: ${(prsiPayableMonthly)}
                                                         Cycle To Work: ${(cycleToWorkSchemeMonthlyDeduction)}
            ------------------------------------------------------------------------------- 
                Gross:  ${(grossPay)}                         Total Deductions: ${(totalDeductions)}    
            -------------------------------------------------------------------------------
                                           NET PAY: ${(netPay)}
            -------------------------------------------------------------------------------                               
                    """.trimIndent()
    )

}