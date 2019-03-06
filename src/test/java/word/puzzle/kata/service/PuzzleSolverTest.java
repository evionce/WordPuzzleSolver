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
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","H","I"},{},{},{}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordInReverseIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X"},{},{}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HELOWRLD");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","E","L","O","W","R","L","D"},{},{},{},{},{},{},{},{},{},{}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HELOWRLD"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetInReverseWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","A","V","A","J"},{},{},{},{},{},{},{}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("JAVA"));
    }

    @Test
    public void whenPuzzleIsReadWithMultipleWordsToFindBothReverseAndForwardsAreFound(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","J","A","V","A"},{},{},{},{},{},{},{}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleWithSameWordIndexesInPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","H","J","A","V","A"},{},{},{},{},{},{},{}},wordsToFind);
        assertEquals("HI: (1,0),(0,0)"+"\n"+"JAVA: (4,0),(5,0),(6,0),(7,0)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithVerticalDownWordsPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("ABLE");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"J","A","V","A"},{"X","B","X","X"},{"X","L","X","X"},{"X","E","X","X"}},wordsToFind);
        assertEquals("ABLE: (1,0),(1,1),(1,2),(1,3)"+"\n"+"JAVA: (0,0),(1,0),(2,0),(3,0)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithVerticalUpwardsPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("ABLE");
        wordsToFind.add("JAVA");
        wordsToFind.add("KATA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"J","A","V","A"},{"X","B","X","T"},{"X","L","X","A"},{"X","E","X","K"}},wordsToFind);
        assertEquals("ABLE: (1,0),(1,1),(1,2),(1,3)"+"\n"
                +"JAVA: (0,0),(1,0),(2,0),(3,0)"+"\n"
                +"KATA: (3,3),(3,2),(3,1),(3,0)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithDiagonalDownwardPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("ABLE");
        wordsToFind.add("JAVA");
        wordsToFind.add("KATA");
        wordsToFind.add("JBAK");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"J","A","V","A"},{"X","B","X","T"},{"X","L","A","A"},{"X","E","X","K"}},wordsToFind);
        assertEquals("ABLE: (1,0),(1,1),(1,2),(1,3)"+"\n"
                +"JAVA: (0,0),(1,0),(2,0),(3,0)"+"\n"
                +"KATA: (3,3),(3,2),(3,1),(3,0)"+"\n"
                +"JBAK: (0,0),(1,1),(2,2),(3,3)",solver.solvePuzzle());
    }
}
