package com.coaching_system.CoachingApp.Controller.Security;

import com.coaching_system.CoachingApp.Model.Users;
import com.coaching_system.CoachingApp.Service.JwtService;
import com.coaching_system.CoachingApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public String login(@RequestBody Users users)
    {
      System.out.println(users);
      Authentication authentication = authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));

      if(authentication.isAuthenticated())
          return jwtService.generateToken(users.getUsername());
      else
          return "Login Failed";
    }


}
