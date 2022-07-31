package com.shopforhome.web.rest.auth;

import com.shopforhome.config.JwtTokenUtil;
import com.shopforhome.entity.user.User;
import com.shopforhome.model.auth.JwtRequest;
import com.shopforhome.model.auth.JwtResponse;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.auth.JwtUserDetailsService;
import com.shopforhome.service.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final User user=this.userService.findByEmailId(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails,user);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<ActionResponse> save(@RequestBody User user) {
        ActionResponse response=new ActionResponse();
        try {
            this.userService.save(user);
            response.setSuccessful(true);
            response.setException(false);
            response.setMessage("User added successfully");
            return ResponseEntity.ok().body(response);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            response.setSuccessful(false);
            response.setException(true);
            response.setResult("Internal Server Error: Please try after some time");
            return ResponseEntity.internalServerError().body(response);
        }

    }

}
