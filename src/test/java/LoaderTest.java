import junit.framework.TestCase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoaderTest extends TestCase {
    String staffFile;
    List<String> lines;

    @Override
    protected void setUp() throws Exception {
        staffFile = "data/movementList.csv";
        lines = Files.readAllLines(Paths.get(staffFile));
    }

    public void testReplaceComma(){
        String s = lines.get(64);
        String actual = Loader.replaceComma(s, ' ');
        System.out.println(actual);
    }

    public void testCostType(){
        String[] fragments = lines.get(1).split(",");

        String s = fragments[5];
        String actual = Loader.clearCostType(s);
        String expected = "809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW";
        System.out.println(s);
        assertEquals(expected, actual);
    }

}
