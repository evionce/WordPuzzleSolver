package word.puzzle.kata.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PuzzleSolverTest {

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordToFindIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","H","I"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordInReverseIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HELOWRLD");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","E","L","O","W","R","L","D"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HELOWRLD"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetInReverseWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","A","V","A","J"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("JAVA"));
    }

    @Test
    public void whenPuzzleIsReadWithMultipleWordsToFindBothReverseAndForwardsAreFound(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","J","A","V","A"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleWithSameWordIndexesInPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","J","A","V","A"}},wordsToFind);
        assertEquals("HI: (0,0),(0,1)"+"\n"+"JAVA: (0,4),(0,5),(0,6),(0,7)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithVerticalDownWordsPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("ABLE");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"J","A","V","A"},{"X","B","X","X"},{"X","L","X","X"},{"X","E","X","X"}},wordsToFind);
        assertEquals("ABLE: (0,1),(1,1),(2,1),(3,1)"+"\n"+"JAVA: (0,0),(0,1),(0,2),(0,3)",solver.solvePuzzle());
    }
}
