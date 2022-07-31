package com.shopforhome.service.domain.user;

import com.shopforhome.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void save(User user);
    User findById(long id);
    User findByEmailId(String emailId);
    void delete(long id);
    Page<User> findAll(Pageable pageable);
}
