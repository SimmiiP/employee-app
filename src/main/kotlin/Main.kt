
import controllers.EmployeeAPI
import models.Employee
import mu.KotlinLogging
import kotlin.math.round

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

var employees = EmployeeAPI()

fun main(args: Array<String>) {
    logger.info {"Launching Employee App"}

    start()
}

val logger = KotlinLogging.logger {}
fun menu(): Any {
    print("""
        ${ANSI_RED}| 1. Add Employee
        ${ANSI_GREEN}| 2. List All Employees
        ${ANSI_PURPLE}| 3. Search Employees by ID  
        ${ANSI_BLUE}| 4. Search Employees by Name
        ${ANSI_YELLOW}| 5. Sort Employees by Salaries Low-High
        ${ANSI_WHITE}| 6. Print Payslip for Employee
        ${ANSI_CYAN}| 7. Delete an Employee
        ${ANSI_BLACK}| 8. Update an Employees Information
        ${ANSI_RED}| -1 Exit $ANSI_RESET
        | Enter Option:
        
    """.trimIndent())
    return readLine()!!.toInt()

}
fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun start() {
    var input: Int
    do{
        input = menu() as Int
        when (input){
            1 -> add()
            2 -> list()
            3 -> searchByID()
            4 -> searchByName()
            5 -> sortByPay()
            6 -> paySlip()
            7 -> deleteEmployee()
            /*8 -> changeEmployee()*/
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
    logger.info{"${ANSI_PURPLE}Searching for employee by ID"}
    val employee = getEmployeeById()
    if (employee == null)
        println("${ANSI_PURPLE}No employee found ")
    else
        println(employee)
}

fun searchByName(){
    logger.info{"${ANSI_BLUE}Searching for employees by Name"}
    val employee = getEmployeeByName()
    if (employee == null)
        println("${ANSI_BLUE}Check your spelling")
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
    logger.info{"${ANSI_YELLOW}Sorting the employees by pay Low-High or High-Low"}
    println("${ANSI_YELLOW}To sort from low to high input - Ascending, To sort from high to low input - Descending ")
    val up: String? = readLine()
    if (up == "Ascending" )
    employees
        .sortedByPay()
        .forEach {println("${ANSI_YELLOW}Employee: ${it.firstName}, ${it.surName}, ${it.grossSalary}")}

    if (up == "Descending")
    employees
        .sortedByPayDown()
        .forEach {println("${ANSI_YELLOW}Employee: ${it.firstName}, ${it.surName}, ${it.grossSalary}")}
}

fun dummyData() {
    employees.create(Employee("Joe ","Soap",'m',0,35655.43,31.0,7.5,2000.0,25.6))
    employees.create(Employee("Joan ","Murphy",'f',1, 54255.13,32.5,7.0,1500.0,55.3))
    employees.create(Employee("Mary ","Quinn",'f',2,75685.41,40.0,8.5,4500.0,0.0))
}

fun add(){
    print("${ANSI_RED}Enter first name: ")
    val firstName = readLine().toString()
    print("${ANSI_RED}Enter surname: ")
    val surName = readLine().toString()
    print("${ANSI_RED}Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("${ANSI_RED}Enter Employee ID: ")
    val employeeId = readLine()!!.toInt()
    print("${ANSI_RED}Enter Gross Salary: ")
    val grossPay = readLine()!!.toDouble()
    print("${ANSI_RED}Enter PAYE %: ")
    val payePayableMonthly = readLine()!!.toDouble()
    print("${ANSI_RED}Enter PRSI %: ")
    val prsiPayableMonthly = readLine()!!.toDouble()
    print("${ANSI_RED}Enter Annual Bonus: ")
    val annualBonusAmount = readLine()!!.toDouble()
    print("${ANSI_RED}Enter Cycle To Work Deduction: ")
    val cycleToWorkMonthlyDeduction = readLine()!!.toDouble()

    employees.create(Employee(firstName , surName, gender, employeeId, grossPay, payePayableMonthly, prsiPayableMonthly, annualBonusAmount, cycleToWorkMonthlyDeduction))
}


fun deleteEmployee() {
    logger.info{"${ANSI_CYAN}Deleting Employees by their position in the Array"}
    val employee = getEmployeeById()
    if (employee == null)
        println("${ANSI_CYAN}No employee found")
    else
        println("${ANSI_CYAN}Are you sure you want to remove ${employee.firstName}?")
    val ans: String? = readLine()
    if (ans == "Yes")
        return employees.removeEmployee(employee)
    if (ans == "No")
        println("${ANSI_CYAN}Nevermind")

}

/*fun changeEmployee() {
    val employee = getEmployeeById()
    if (employee == null)
        println("Add new employee first!")
    else
        println("Update ${employee.firstName}'s information")
    if (employee != null) {
        return employees.update()
    }
}*/

