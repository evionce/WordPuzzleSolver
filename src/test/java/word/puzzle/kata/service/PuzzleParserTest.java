package word.puzzle.kata.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PuzzleParserTest {

    PuzzleParser puzzleParser;

    @Before
    public void setup() {
        puzzleParser = new PuzzleParser();
        ReflectionTestUtils.setField(puzzleParser, "puzzlePath", System.getProperty("user.dir"));
        ReflectionTestUtils.setField(puzzleParser, "puzzleFileName", "/puzzle.txt");
    }

    @Test
    public void whenPuzzleFilePathIsPassedFileExistsInMemory() throws Exception {
        assertThat(puzzleParser.readPuzzleFile().exists());
    }

    @Test
    public void whenPuzzleIsReadWordsToFindAreAvailable() throws Exception {
        puzzleParser.readPuzzleFile();
        List<String> wordsToFind = (List) ReflectionTestUtils.getField(puzzleParser, "wordsToFind");
        assertThat(Arrays.asList(wordsToFind).contains("BONES"));
    }

    @Test
    public void whenStringDelimitedWithCommaIsPassedArrayOfElementsIsReturned(){
        String testString = "H,E,L,O,W,R,L,D";
        String[] testPuzzleLines = puzzleParser.parseWordPuzzleLine(testString);
        assertEquals(8, testPuzzleLines.length);
    }

    @Test
    public void whenArrayIsPassed2DArrayPreservesLineIndex(){
        String[] testLine = new String[]{"E","V","A","N"};
        String[][] puzzle = puzzleParser.build2DPuzzleArray(0, testLine);
        assertEquals("E",puzzle[0][0]);
        assertEquals("V",puzzle[0][1]);
        assertEquals("A",puzzle[0][2]);
        assertEquals("N",puzzle[0][3]);
    }
}
