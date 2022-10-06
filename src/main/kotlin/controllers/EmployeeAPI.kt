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

    fun groupAll(): List<Employee> {
        return employees
    }

    fun findOneName(id: String): Employee? {
        return employees.find {p -> p.firstName == id}
    }

    fun create(employee: Employee){
        employee.employeeId = getId()
        employees.add(employee)
    }


    fun removeOne(employee: Int) {
        employees.removeAt(employee)
    }
}
