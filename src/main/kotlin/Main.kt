

fun main(args: Array<String>) {
    println("Pay Slip Printer")
    printPayslip()
}

var firstName: String = "Joe "
var surName: String = "Soap "
var gender: Char = 'm'
var employeeId: Int = 6143
var grossSalary:Double = 67543.21
var PAYE: Float = 38.5f
var PRSI: Float = 5.2f
var annualBonusAmount: Double = 1450.50
var cycleToWorkSchemeMonthlyDeduction: Float = 54.33f
var monthlySalary = grossSalary/12
var monthlyBonus = annualBonusAmount/12
var grossPay = monthlySalary+monthlyBonus

var payePayableMonthly = monthlySalary*PAYE/100*1
var prsiPayableMonthly = monthlySalary*PRSI/100*1
var totalDeductions = payePayableMonthly+prsiPayableMonthly+cycleToWorkSchemeMonthlyDeduction
var netPay =  grossPay - totalDeductions



fun printPayslip(){
    println("\t\t\t\t\t\t\t\t Monthly Payslip")
    println()
    println()
    println("Employee Name: $firstName $surName ( ${gender.uppercase()} ) \t\t\t\t\t\t\t\t Employee ID: $employeeId")
    println()
    println()
    println()
    println("PAYMENT DETAILS \t\t\t\t\t\t\t DEDUCTION DETAILS")
    println()
    println("Salary: ${"%.2f".format(monthlySalary)}\t\t\t\t\t\t\t\t PAYE: ${"%.2f".format(payePayableMonthly)}")
    println("Bonus:  ${"%.2f".format(monthlyBonus)}\t\t\t\t\t\t\t\t PRSI: ${"%.2f".format(prsiPayableMonthly)}")
    println("\t\t\t\t\t\t\t\t\t\t\t Cycle To Work: $cycleToWorkSchemeMonthlyDeduction")
    println()
    println("Gross: ${"%.2f".format(grossPay)}\t\t\t\t\t\t\t\t Total Deductions: ${"%.2f".format(totalDeductions)}")

    println()
    println("\t\t\t\t\t\t NET PAY: ${"%.2f".format(netPay)}")
}