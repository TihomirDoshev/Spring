package bg.softuni.springdataautomappingobjects;

import bg.softuni.springdataautomappingobjects.models.entities.Address;
import bg.softuni.springdataautomappingobjects.models.entities.Employee;
import bg.softuni.springdataautomappingobjects.models.dto.BasicEmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ControlRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        Address address = new Address("BG","Plovdiv","Maritsa");

        Employee employee = new Employee("First","Last", BigDecimal.TEN,address);

        ModelMapper modelMapper = new ModelMapper();
//
//        PropertyMap<Employee,BasicEmployeeDTO> employeeInfoDTOMap = new PropertyMap<Employee, BasicEmployeeDTO>() {
//            @Override
//            protected void configure() {
//                map().setAddressCity(source.getAddress().toString());
//            }
//        } ;
//        modelMapper.addMappings(employeeInfoDTOMap);
        BasicEmployeeDTO basicEmployeeDTO = modelMapper.map(employee,BasicEmployeeDTO.class);



//        TypeMap<BasicEmployeeDTO,Employee> dtoToEmployeeMap =
//                modelMapper.createTypeMap(BasicEmployeeDTO.class,Employee.class);
//        dtoToEmployeeMap.addMapping(BasicEmployeeDTO::getAddressCity,Employee::setAddress);
//        //dtoToEmployeeMap.addMapping(mapping -> mapping.map(source -> source))
//        //BasicEmployeeDTO personFromDTO = modelMapper.map(BasicEmployeeDTO.class, Employee.class)
//        Employee employeeFromDTO = modelMapper.map(basicEmployeeDTO, Employee.class);

    }
}
