import java.io.File;

public class ParserImplTest extends ParserTest {
    /**
     * Creator
     * @param file
     * @return
     */
    @Override
    public Parser create(File file) {
        return new ParserImpl(file);
    }

}