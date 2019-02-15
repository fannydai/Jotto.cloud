package server.Jotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import server.Jotto.Models.*;


@RestController
public class UserRestController{

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    /**
     * Controller for registration form POST
     * Input: RequestBody (JSON) mapping to RegistrationForm (Class)
     * Output: ResponseBody (JSON) Parameters: 'status': 'success' or 'failure', 'username' : logged in user's username
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public RegistrationResult register(@RequestBody RegistrationForm regform ){
        System.out.println(regform.getUsername());
        User user = new User(regform.getUsername(),regform.getPassword());
        userRepository.save(user);
        RegistrationResult res = new RegistrationResult();
        res.setStatus("success");
        res.setUsername(user.username);
        return res;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(@RequestBody User user){
        System.out.println("dsadasd");
        return true;
    }

}