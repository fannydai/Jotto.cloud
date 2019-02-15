package server.Jotto.Models;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    public User findByusername(String username);
    public List<User> findAll();
}