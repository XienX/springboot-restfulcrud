package com.restfulcrud.springboot.controller;

import com.restfulcrud.springboot.dao.DepartmentDao;
import com.restfulcrud.springboot.dao.EmployeeDao;
import com.restfulcrud.springboot.entities.Department;
import com.restfulcrud.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //返回员工列表页面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //查出部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将参数和入参对象的属性进行一一绑定，要求请求参数的名字和JavaBean对象的属性名一样
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
//        System.out.println(employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    //来到修改页面
    @GetMapping("/emp/{id}")
    public String toEditpage(@PathVariable("id")Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    //员工修改
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);

        return "redirect:/emps";
    }
}
