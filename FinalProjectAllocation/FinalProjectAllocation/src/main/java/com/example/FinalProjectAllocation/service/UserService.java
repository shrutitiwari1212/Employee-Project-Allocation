package com.example.FinalProjectAllocation.service;

import com.example.FinalProjectAllocation.dto.LoginDTO;
import com.example.FinalProjectAllocation.dto.ResponseDTO;
import com.example.FinalProjectAllocation.dto.SignUpDTO;
import com.example.FinalProjectAllocation.dto.UpdatePasswordRequest;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.UserEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import com.example.FinalProjectAllocation.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    /*public void registerUser(EmployeeEntity employeeEntity, String username, String password) {

        employeeRepository.save(employeeEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password); // Consider hashing passwords
        userEntity.setEmployeeEntity(employeeEntity);

        userRepository.save(userEntity);
    }*/

    public void registerUser(SignUpDTO signUpDTO) {
        // EmployeeEntity
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpName(signUpDTO.getEmpName());
        employeeEntity.setEmail(signUpDTO.getEmail());

        employeeEntity.setDesignation(signUpDTO.getDesignation());
        employeeEntity.setAvailable(signUpDTO.isAvailable());
        employeeEntity.setSkills(signUpDTO.getSkills());


        if (signUpDTO.getProId() != null) {
            Optional<ProjectEntity> project = projectRepository.findById(signUpDTO.getProId());
            project.ifPresent(employeeEntity::setProjectEntity);
        }
        employeeRepository.save(employeeEntity);
        // UserEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpDTO.getUsername());
        userEntity.setPassword(signUpDTO.getPassword());
        userEntity.setRole(signUpDTO.getRole());


        userEntity.setEmployeeEntity(employeeEntity);
        userRepository.save(userEntity);

    }






    public boolean authenticate(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        return user.map(u -> u.getPassword().equals(password)).orElse(false); // password check
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

//    public ResponseDTO login(LoginDTO loginDTO) {
//        Optional<UserEntity> userOpt = userRepository.findByUsername(loginDTO.getUsername());
//        if (userOpt.isPresent()) {
//            UserEntity user = userOpt.get();
//            if (user.getPassword().equals(loginDTO.getPassword())) {
//                Long empId = user.getEmployeeEntity().getEmpId();
//                String empName = user.getEmployeeEntity().getEmpName();
//                return new ResponseDTO("Login successful",empId, user.getRole(), empName);
//            } else {
//                throw new RuntimeException("Invalid password");
//            }
//        } else {
//            throw new RuntimeException("User Not found");
//        }
//    }
public ResponseDTO login(LoginDTO loginDTO) {
    Optional<UserEntity> userOpt = userRepository.findByUsername(loginDTO.getUsername());

    if (userOpt.isPresent()) {
        UserEntity user = userOpt.get();

//            System.out.println("fetched User , " + user);
//            System.out.println("Employee Linked to user: " + user.getEmployee());
//            System.out.println("Employee name: " + user.getEmployee().getEmpName());

        if (user.getPassword().equals(loginDTO.getPassword())) {
            Long empId = user.getEmployeeEntity().getEmpId();
            String empName = user.getEmployeeEntity().getEmpName();

            ResponseDTO responseDTO = new ResponseDTO("Login successful",empId, user.getRole(), empName );
            boolean usingTemp = loginDTO.getPassword().equals("Temp123");
            responseDTO.setNeedsPasswordUpdate(usingTemp);

            return responseDTO;
        } else {
            throw new RuntimeException("Invalid password");
        }
    } else {
        throw new RuntimeException("User Not found");
    }
}


    public String updatePassword(UpdatePasswordRequest request){
        Optional<UserEntity> optionalUser = userRepository.findByUsername(request.getUsername());

        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            user.setPassword(request.getNewPassword());
            userRepository.save(user);
            return  "Password updated successfully!";
        } else {
            return "User not found!";
        }
    }





}
