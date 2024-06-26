package com.develhope.spring.services;


import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.models.costants.Role;
import com.develhope.spring.models.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private CustomerDao customerDao;


    @Override
    public UserEntity loadUserByUsername(String username) {
        UserEntity userLogin = new UserEntity();

        if(!customerDao.findCustomerByEmail(username).isEmpty()){
            userLogin = customerDao.findCustomerByEmail(username).get();
        };
        return userLogin;
    }


    public UserEntity loadUserByUsernameAndRole(String username, Role role) {
        return (UserEntity) switch (role){
            case Role.ADMIN -> null;
            case Role.CUSTOMER -> customerDao.findCustomerByEmail(username).get();
            case Role.OWNER -> null;
            case Role.DRIVER -> null;
        };
    }



//    public String deleteById(String id) {
//        if(userRepository.existsById(id)){
//            userRepository.deleteById(id);
//            return "User eliminato correttamente.";
//        } else {
//            return "Non esiste un User con questo id.";
//        }
//    }
}
