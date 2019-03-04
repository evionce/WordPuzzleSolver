package word.puzzle.kata.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleSolverTest {

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordToFindIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","H","I"},{},{}},wordsToFind);
        assertEquals("HI",solver.solvePuzzleAgainstWord("HI"));
    }

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordInReverseIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X"},{},{}},wordsToFind);
        assertEquals("HI",solver.solvePuzzleAgainstWord("HI"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HELOWRLD");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","E","L","O","W","R","L","D"},{},{}},wordsToFind);
        assertEquals("HELOWRLD",solver.solvePuzzleAgainstWord("HELOWRLD"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetInReverseWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","A","V","A","J"},{},{}},wordsToFind);
        assertEquals("JAVA",solver.solvePuzzleAgainstWord("JAVA"));
    }

    @Test
    public void whenPuzzleIsReadWithMultipleWordsToFindBothReverseAndForwardsAreFound(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","J","A","V","A"},{},{}},wordsToFind);
        assertEquals("HI"+"\n"+"JAVA",solver.solvePuzzle());
    }
}
