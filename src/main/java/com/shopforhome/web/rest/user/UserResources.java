package com.shopforhome.web.rest.user;

import com.shopforhome.entity.user.User;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserResources {


    @Autowired
    private UserService userService;

    @PutMapping("/edit")
    public ResponseEntity<ActionResponse> edit(@RequestBody User user) {
        userService.save(user);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("User edited successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<User>> getAll(Pageable pageable) {
        Page<User> users=userService.findAll(pageable);
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ActionResponse> delete(@PathVariable("id") long id) {
        userService.delete(id);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("User deleted successfully");
        return ResponseEntity.ok().body(response);
    }


}
