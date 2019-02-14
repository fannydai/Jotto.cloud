package server.Jotto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import org.springframework.security.core.userdetails.User;

@RestController
@CrossOrigin
public class UserRestController{

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(@RequestBody User user){
        System.out.println("dsadasd");
        return true;

    }
}