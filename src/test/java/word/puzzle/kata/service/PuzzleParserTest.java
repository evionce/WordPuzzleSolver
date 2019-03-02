package word.puzzle.kata.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.*;

public class PuzzleParserTest {

    PuzzleParser puzzleParser;
    List<String> wordsToFind;

    @Before
    public void setup() {
        puzzleParser = new PuzzleParser();
        wordsToFind = new ArrayList<>();
        setField(puzzleParser, "puzzlePath", System.getProperty("user.dir"));
        setField(puzzleParser, "puzzleFileName", "/puzzle.txt");
    }

    @Test
    public void whenPuzzleFilePathIsPassedFileExistsInMemory() throws Exception {
        assertThat(puzzleParser.readPuzzleFile().exists());
    }

    @Test
    public void whenPuzzleIsReadWordsToFindAreAvailable() throws Exception {
        puzzleParser.readPuzzleFile();
        List wordsToFind = (List) getField(puzzleParser, "wordsToFind");
        assertThat(Objects.equals(wordsToFind, "BONES"));
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
        PuzzleSolver solver = puzzleParser.build2DPuzzleArray(0, testLine);
        String[][] puzzle = (String[][]) getField(solver,"puzzle");
        assertEquals("E",puzzle[0][0]);
        assertEquals("V",puzzle[0][1]);
        assertEquals("A",puzzle[0][2]);
        assertEquals("N",puzzle[0][3]);
    }

    @Test
    public void whenPuzzleIsParsedClassIsConstructedWithCorrectArrays(){
        wordsToFind.add("JRE");
        setField(puzzleParser,"wordsToFind", wordsToFind); //
        PuzzleSolver solver = puzzleParser.build2DPuzzleArray(2, new String[]{"J","D","K"});
        String[][] puzzle = (String[][]) getField(solver,"puzzle");
        assertEquals("J",puzzle[2][0]);
        assertEquals("D",puzzle[2][1]);
        assertEquals("K",puzzle[2][2]);
        assertEquals("JRE", solver.getWordsToFind().get(0));
    }
}
