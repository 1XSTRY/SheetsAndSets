package Pro.sky.EmployeeHandbook;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
class EmployeeService {
    private static final int MAX_EMPLOYEES = 10;
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.add(newEmployee);
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.remove(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        int index = employees.indexOf(employeeToFind);
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(index);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}

@ResponseStatus
class EmployeeNotFoundException extends RuntimeException {
}

@ResponseStatus
class EmployeeStorageIsFullException extends RuntimeException {
}

@ResponseStatus
class EmployeeAlreadyAddedException extends RuntimeException {
}