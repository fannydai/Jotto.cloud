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
public class UserRestController{

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestParam("username")String username, @RequestParam("password")String password){
        System.out.println(username+ password);
        return true;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(@RequestBody User user){
        System.out.println("dsadasd");
        return true;
    }
}