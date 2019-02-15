package server.Jotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        RegistrationResult res = new RegistrationResult();

        if(userRepository.findByusername(regform.getUsername()) == null){
            User user = new User(regform.getUsername(),regform.getPassword());
            userRepository.save(user);
            res.setStatus("success");
            res.setUsername(user.username);
        }else{
            //user with username already exists
            res.setStatus("failure");
            res.setUsername("");
        }
        /** 
         * Query checkIfExistUser = new Query();
        checkIfExistUser.addCriteria(Criteria.where("username").is(regform.getUsername()));
        List<User> users = mongoTemplate.find(checkIfExistUser,User.class);
        */
        return res;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(@RequestBody User user){
        System.out.println("dsadasd");
        return true;
    }

}