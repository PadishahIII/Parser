import java.io.FileNotFoundException;
import java.util.List;

/**
 * 命令解释器
 * 根据有关命令格式的文档生成命令正则表达式
 * 处理输入字符串，无效或返回命令名称
 * 
 * 
 */
public interface Parser {

    /**
     * 处理文档，生成正则表达式
     * @throws FileNotFoundException
     * @throws NoSuchOrderException
     * @throws EmptyOrderException
     */
    public void init() throws FileNotFoundException, EmptyOrderException, NoSuchOrderException;

    /**
     * 读入字符串，返回分解后的单词列表
     * 无效则抛出异常
     * @param cmd
     * @return null if illegal, or return the name of cmd
     * @throws NoSuchOrderException
     */
    public List<String> parse(String cmd) throws NoSuchOrderException;

    /**
     * 所有命令的字符串
     * @return
     */
    public String getOrders();

    /**
     * 所有special word
     * @return
     */
    public String getSpecialWords();

}