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
        assertEquals("HI",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordInReverseIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X"},{},{}},wordsToFind);
        assertEquals("HI",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HELOWRLD");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","E","L","O","W","R","L","D"},{},{}},wordsToFind);
        assertEquals("HELOWRLD",solver.solvePuzzle());

    }
}
