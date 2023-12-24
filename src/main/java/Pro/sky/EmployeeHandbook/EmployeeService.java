package Pro.sky.EmployeeHandbook;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 10;
    private Map<String, Employee> employeeMap = new HashMap<>();

    public void addEmployee(String firstName, String lastName) {
        if (employeeMap.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        String key = generateKey(firstName, lastName);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        Employee newEmployee = new Employee(firstName, lastName);
        employeeMap.put(key, newEmployee);
    }

    public void removeEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        if (employeeMap.remove(key) == null) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        Employee employee = employeeMap.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employeeMap);
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + "_" + lastName;
    }
}
