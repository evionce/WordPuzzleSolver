package word.puzzle.kata.service;

import org.junit.Test;
import word.puzzle.kata.service.setup.DataSetup;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PuzzleSolverTest {

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordToFindIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","H","I"},{"X","X","X"},{"X","X","X"},{"X","X","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadHorizontalMatchingWordInReverseIsReturned(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X"},{"X","X","X"},{"X","X","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HELLO");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","H","E","L","L","O"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HELLO"));
    }

    @Test
    public void whenPuzzleIsReadWithLongerTargetInReverseWordTargetIsReturn(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","A","V","A","J"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("JAVA"));
    }

    @Test
    public void whenPuzzleIsReadWithMultipleWordsToFindBothReverseAndForwardsAreFound(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","J","A","V","A"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"}},wordsToFind);
        assertTrue(solver.solvePuzzle().contains("HI"));
    }

    @Test
    public void whenPuzzleWithSameWordIndexesInPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("HI");
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"I","H","X","J","A","V","A"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"},{"X","X","X","X","X","X","X"}},wordsToFind);
        assertEquals("HI: (1,0),(0,0)"+"\n"+"JAVA: (3,0),(4,0),(5,0),(6,0)",solver.solvePuzzle());
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

    @Test
    public void whenPuzzleWithDiagonalUpwardPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("KATA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"A","X","X","X"},{"X","T","X","X"},{"X","X","A","X"},{"X","X","X","K"}},wordsToFind);
        assertEquals("KATA: (3,3),(2,2),(1,1),(0,0)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithDiagonalUpwardRightPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("KATA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","X","X","A"},{"X","X","T","X"},{"X","A","X","X"},{"K","X","X","X"}},wordsToFind);
        assertEquals("KATA: (0,3),(1,2),(2,1),(3,0)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleWithDiagonalDownwardRightPrintLineWillSignifyTherePosition(){
        List<String> wordsToFind = new ArrayList<>();
        wordsToFind.add("JAVA");
        PuzzleSolver solver = new PuzzleSolver(new String[][]{{"X","X","X","J"},{"X","X","A","X"},{"X","V","X","X"},{"A","X","X","X"}},wordsToFind);
        assertEquals("JAVA: (3,0),(2,1),(1,2),(0,3)",solver.solvePuzzle());
    }

    @Test
    public void whenPuzzleIsPassedSampleFileSampleOutputIsPrinted(){
        DataSetup dataSetup = new DataSetup();
        PuzzleSolver solver = dataSetup.setupRealPuzzle();
        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)"+"\n"
                +"KHAN: (5,9),(5,8),(5,7),(5,6)"+"\n"
                +"KIRK: (4,7),(3,7),(2,7),(1,7)"+"\n"
                +"SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)"+"\n"
                +"SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)"+"\n"
                +"SULU: (3,3),(2,2),(1,1),(0,0)"+"\n"
                +"UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)",solver.solvePuzzle());
    }
}

