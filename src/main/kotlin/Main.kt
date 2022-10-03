import kotlin.math.round


fun main(args: Array<String>) {
    add()

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
fun monthlySalary() = roundTwoDecimals(employee.grossSalary/12)
fun monthlyBonus() = roundTwoDecimals(employee.annualBonusAmount/12)
fun grossPay() = roundTwoDecimals(monthlySalary()+monthlyBonus())

fun payePayableMonthly() = roundTwoDecimals(monthlySalary()*employee.PAYE/100*1)
fun prsiPayableMonthly() = roundTwoDecimals(monthlySalary()*employee.PRSI/100*1)
fun totalDeductions() = roundTwoDecimals(payePayableMonthly()+prsiPayableMonthly()+employee.cycleToWorkSchemeMonthlyDeduction)
fun netPay() = roundTwoDecimals(grossPay()- totalDeductions())

fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun getFullName() = when (employee.gender) {
        'm', 'M' -> "Mr. $employee.firstName $employee.surName"
        'f', 'F' -> "Ms. $employee.firstName $employee.surName"
    else  -> "$employee.firstName $employee.surName"
}


fun getPayslip(){
    println(
        """
            -------------------------------------------------------------------------------
                                             Monthly Payslip:   
            -------------------------------------------------------------------------------
             Employee Name: ${employee.firstName.uppercase()}${employee.surName.uppercase()}(${employee.gender.uppercase()})                         Employee ID: ${employee.employeeId}
            
            -------------------------------------------------------------------------------
               PAYMENT DETAILS                           DEDUCTION DETAILS       
            -------------------------------------------------------------------------------
                Salary: ${(monthlySalary())}                           PAYE: ${(payePayableMonthly())} 
                Bonus:  ${(monthlyBonus())}                           PRSI: ${(prsiPayableMonthly())}
                                                         Cycle To Work: ${(employee.cycleToWorkSchemeMonthlyDeduction)}
            ------------------------------------------------------------------------------- 
                Gross:  ${(grossPay())}                         Total Deductions: ${(totalDeductions())}    
            -------------------------------------------------------------------------------
                                           NET PAY: ${(netPay())}
            -------------------------------------------------------------------------------                               
                    """.trimIndent()
    )

}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surName = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter Employee ID: ")
    val employeeId = readLine()!!.toInt()
    print("Enter Gross Salary: ")
    val grossPay = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePayableMonthly = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPayableMonthly = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonusAmount = readLine()!!.toDouble()
    print("Enter Cycle To Work Deduction: ")
    val cycleToWorkMonthlyDeduction = readLine()!!.toDouble()

    employee = Employee(firstName, surName, gender, employeeId, grossPay, payePayableMonthly, prsiPayableMonthly, annualBonusAmount, cycleToWorkMonthlyDeduction)
}