import kotlin.math.round


var firstName: String = "Joe "
var surName: String = "Soap "
var gender: Char = 'm'
var employeeId: Int = 6143
var grossSalary:Double = 67543.21
var PAYE: Double = 38.5
var PRSI: Double = 5.2
var annualBonusAmount: Double = 1450.50
var cycleToWorkSchemeMonthlyDeduction: Double = 54.33

fun main(args: Array<String>) {

    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> println("Monthly Salary: ${monthlySalary()}")
            2 -> println("Monthly PRSI: ${prsiPayableMonthly()}")
            3 -> println("Monthly PAYE: ${payePayableMonthly()}")
            4 -> println("Monthly Gross Pay: ${grossPay()}")
            5 -> println("Monthly Total Deductions: ${totalDeductions()}")
            6 -> println("Monthly Net Pay: ${netPay()}")
            7 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while(input!=-1)

    println(getFullName())
    println("Monthly Salary: ${monthlySalary()}")
    println("Monthly Bonus: ${monthlyBonus()}")
    println("Gross Pay: ${grossPay()}")
    println("PAYE: ${payePayableMonthly()}")
    println("PRSI: ${prsiPayableMonthly()}")
    println("Total Deductions: ${totalDeductions()}")
    println("Net Pay: ${netPay()}")
    println(getPayslip())

}

fun menu() : Int{
    print("""
    Employee Menu for ${getFullName()}
    1. Monthy Salary
    2. Monthly PRSI
    3. Monthly PAYE
    4. Monthly Gross Pay
    5. Monthly Total Deductions 
    6. Monthly Net Pay
    7. Full Payslip
    -1. Exit
    Enter Option : """.trimIndent())
    return readLine()!!.toInt()
}
fun monthlySalary() = roundTwoDecimals(grossSalary/12)
fun monthlyBonus() = roundTwoDecimals(annualBonusAmount/12)
fun grossPay() = roundTwoDecimals(monthlySalary()+monthlyBonus())

fun payePayableMonthly() = roundTwoDecimals(monthlySalary()*PAYE/100*1)
fun prsiPayableMonthly() = roundTwoDecimals(monthlySalary()*PRSI/100*1)
fun totalDeductions() = roundTwoDecimals(payePayableMonthly()+prsiPayableMonthly()+cycleToWorkSchemeMonthlyDeduction)
fun netPay() = roundTwoDecimals(grossPay()- totalDeductions())

fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. $firstName $surName"
        'f', 'F' -> "Ms. $firstName $surName"
    else  -> "$firstName $surName"
}


fun getPayslip(){
    println(
        """
            -------------------------------------------------------------------------------
                                             Monthly Payslip:   
            -------------------------------------------------------------------------------
             Employee Name: ${firstName.uppercase()}${surName.uppercase()}(${gender.uppercase()})                         Employee ID: $employeeId
            
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