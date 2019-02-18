package server.Jotto.Models;
import org.springframework.data.annotation.Id;


/**
 * POJO model representation of move in Jotto game.
 */
public class JottoMoveModel{
    @Id
    public String id;
    String guessedword;

    
}