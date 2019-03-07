package word.puzzle.kata;

import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import word.puzzle.kata.service.PuzzleParser;

@Service
public class KataAppRunner implements ApplicationRunner {

    @Autowired BeanFactory factory;
    @Setter String resultFormatted;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PuzzleParser puzzleParser = factory.getBean(PuzzleParser.class);
        puzzleParser.parseAndSolvePuzzle();
        System.out.println(resultFormatted);
    }
}
