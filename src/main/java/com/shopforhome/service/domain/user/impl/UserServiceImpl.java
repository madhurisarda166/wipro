package com.shopforhome.service.domain.user.impl;

import com.shopforhome.entity.user.User;
import com.shopforhome.repository.user.UserRepository;
import com.shopforhome.service.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class implements User Service
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public void save(User user){
        log.debug("Request to save user");

        String encryptedPassword=bcryptEncoder.encode(user.getPassword()!=null?user.getPassword():"");
        user.setPassword(encryptedPassword);

        user=userRepository.save(user);
        //In future we will write code here to send email to verify his/her account

    }

    @Override
    public User findById(long id) {
        Optional<User> user=this.userRepository.findById(id);
        if(user.isPresent()) {
            return  user.get();
        }
        return null;
    }

    @Override
    public User findByEmailId(String emailId) {
        User user=this.userRepository.findByEmailId(emailId);
        if(user==null) return null;
        return user;
    }

    @Override
    public void delete(long id) {
        User user=findById(id);
        if(user!=null) this.userRepository.delete(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> users=userRepository.findAll(pageable);
        return users;

    }

}
