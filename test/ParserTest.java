import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

public abstract class ParserTest {
    /**
     * Get instance
     * @param file
     * @return
     */
    public abstract Parser create(File file);

    @Test(expected = AssertionError.class)
    public void AssertionEnalbedTest() {
        assert false;
    }

    @Test
    public void initTest() throws FileNotFoundException, EmptyOrderException, NoSuchOrderException {
        File file = new File("C:\\Users\\xr\\Desktop\\Code\\java\\parser\\test\\doc.txt");
        Parser p = create(file);
        p.init();
        System.out.println(p.getOrders());
        System.out.println(p.getSpecialWords());
    }

    @Test(expected = NoSuchOrderException.class)
    public void NoSuchOrderExceptionTest() throws FileNotFoundException, EmptyOrderException, NoSuchOrderException {
        File file = new File("C:\\Users\\xr\\Desktop\\Code\\java\\parser\\test\\doc.txt");
        Parser p = create(file);
        p.init();
        p.parse("aa");
    }

    @Test
    public void parseTest() throws FileNotFoundException, EmptyOrderException, NoSuchOrderException {
        File file = new File("C:\\Users\\xr\\Desktop\\Code\\java\\parser\\test\\doc.txt");
        Parser p = create(file);
        p.init();
        String list = "list";
        String help = "help";
        String get = "get";
        List<String> l = p.parse("list");
        assert l.get(0).equals(list);
        assert list.equals(p.parse("list  ").get(0));
        assert help.equals(p.parse("help").get(0));
        assert get.equals(p.parse("get aa.java").get(0));
        assert "aa.java".equals(p.parse("get aa.java").get(1));

    }
}