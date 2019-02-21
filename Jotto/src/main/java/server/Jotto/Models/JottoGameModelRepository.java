package server.Jotto.Models;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JottoGameModelRepository extends MongoRepository<JottoGameModel, String> {
    public JottoGameModel findByid(String id);
    public List<JottoGameModel> findAll();
}