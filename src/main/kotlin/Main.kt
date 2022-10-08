
import controllers.EmployeeAPI
import models.Employee
import mu.KotlinLogging
import kotlin.math.round



var employees = EmployeeAPI()

fun main(args: Array<String>) {
    logger.info {"Launching Employee App"}

    start()
}

val logger = KotlinLogging.logger {}
fun menu(): Int {

    print("""
        | 1. Add Employee
        | 2. List All Employees
        | 3. Search Employees by ID  
        | 4. Search Employees by Name
        | 5. Sort Employees by Salaries Low-High
        | 6. Print Payslip for Employee
        | 7. Delete an Employee
        | 8. Update an Employees Information
        | -1 Exit
        | Enter Option:
        
    """.trimIndent())
    return readLine()!!.toInt()
}
fun roundTwoDecimals(number: Double) = round(number * 100) / 100

fun start() {
    var input: Int

    do{
        input = menu()
        when (input){
            1 -> add()
            2 -> list()
            3 -> searchByID()
            4 -> searchByName()
            5 -> sortByPay()
            6 -> paySlip()
            7 -> deleteEmployee()
            8 -> updateEmployee()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun list(){
    logger.info {"Listing All Current Employees"}

    employees.findAll()
        .forEach{ println(it)}
}

fun searchByID(){
    logger.info{"Searching for employees by ID"}
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

fun searchByName(){
    logger.info{"Searching for employees by Name"}
    val employee = getEmployeeByName()
    if (employee == null)
        println("Check your spelling")
    else
        println(employee)
}
internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}
internal fun getEmployeeByName(): Employee? {
    print("Enter the employee's first name to search by: ")
    val firstName = readLine()!!.toString()
    return employees.findOneName(firstName)
}

fun paySlip(){
    logger.info {"Printing Payslip for Employee "}

    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun sortByPay(){
    println("Sorting Employees by Pay From Low-High")
    employees
        .sortedByPay()
        .forEach {println("Employee: ${it.firstName}, ${it.surName}, ${it.grossSalary}")}
}

fun dummyData() {
    employees.create(Employee("Joe ","Soap",'m',0,35655.43,31.0,7.5,2000.0,25.6))
    employees.create(Employee("Joan ","Murphy",'f',1, 54255.13,32.5,7.0,1500.0,55.3))
    employees.create(Employee("Mary ","Quinn",'f',2,75685.41,40.0,8.5,4500.0,0.0))
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

    employees.create(Employee(firstName , surName, gender, employeeId, grossPay, payePayableMonthly, prsiPayableMonthly, annualBonusAmount, cycleToWorkMonthlyDeduction))
}

 fun removeEmployee() {
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
    return employees.removeOne(Employee(firstName , surName, gender, employeeId, grossPay, payePayableMonthly, prsiPayableMonthly, annualBonusAmount, cycleToWorkMonthlyDeduction))
}

fun deleteEmployee() {
    logger.info{"Deleting Employees by their position in the Array"}
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println("Are you sure you want to remove ${employee.firstName}?")
    var ans: String? = readLine()
    if (ans == "Yes")
        return removeEmployee()

}

fun updateEmployee() {
    val employee = getEmployeeById()
    if (employee == null)
        println("Add new employee first!")
    else
        println("Update ${employee.firstName}'s information" )

}
