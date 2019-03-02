package word.puzzle.kata.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PuzzleParserTest {

    String puzzlePath = System.getProperty("user.dir")+"/puzzle.txt";

    @Test
    public void whenPuzzleFilePathIsPassedFileExistsInMemory(){
        PuzzleParser puzzleParser = new PuzzleParser();
        assertThat(puzzleParser.readPuzzleFile(puzzlePath));
    }
}
