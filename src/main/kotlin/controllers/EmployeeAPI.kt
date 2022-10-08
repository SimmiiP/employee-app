package controllers

import models.Employee

var lastId = 0

internal fun getId(): Int {
    return lastId++
}
class EmployeeAPI {


    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find {p -> p.employeeId == id}
    }

    fun findOneName(id: String): Employee? {
        return employees.find {p -> p.firstName == id}
    }

    fun sortedByPay(): List<Employee> {
        return employees.sortedBy { p -> p.grossSalary }

    }

    fun create(employee: Employee){
        employee.employeeId = getId()
        employees.add(employee)
    }
    
    fun removeOne(employee: Employee) {
        employees.removeAt(employee)
    }

    private fun <E> java.util.ArrayList<E>.removeAt(employee: E) {
        TODO("Not yet implemented")
    }}

