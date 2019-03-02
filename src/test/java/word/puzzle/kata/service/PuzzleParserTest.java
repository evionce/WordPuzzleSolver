package word.puzzle.kata.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class PuzzleParserTest {

    PuzzleParser puzzleParser;

    @Before
    public void setup(){
        puzzleParser = new PuzzleParser();
        ReflectionTestUtils.setField(puzzleParser,"puzzlePath",System.getProperty("user.dir") );
        ReflectionTestUtils.setField(puzzleParser,"puzzleFileName","/puzzle.txt");
    }

    @Test
    public void whenPuzzleFilePathIsPassedFileExistsInMemory(){
        assertThat(puzzleParser.readPuzzleFile().exists());
    }
}
