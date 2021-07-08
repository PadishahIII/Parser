import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 命令解释器
 * 
 * doc中的特殊字符串：
 * tinyFileName:简写的文件名，xxx.txt
 * //rawFileName:文件名全称
 * 
 */
public class ParserImpl implements Parser {
    private final File file;
    private final Set<Order> orders = new HashSet<>();
    private final Map<String, String> specialWordsInDoc = new HashMap<>();

    /**
     * AF:
     * orders 解析得到的命令列表
     * specialWordsInDoc key 特殊字符串 value 对应的正则表达式
     * RI:
     * nothing
     */

    /**
     * 构造器
     * @param file 用特定格式存储命令的文档
     */
    public ParserImpl(File file) {
        this.file = file;
        BuildSpecialWords();
    }

    @Override
    public void init() throws FileNotFoundException, EmptyOrderException, NoSuchOrderException {
        Scanner scanner = new Scanner(file);
        StringBuffer strs = new StringBuffer();
        while (scanner.hasNext()) {
            strs.append(scanner.nextLine() + "\n");
        }
        scanner.close();
        String str = new String(strs);
        List<String> list = Arrays.asList(str.split("\n"));
        for (String s : list) {
            buildLine(s);
        }
    }

    @Override
    public List<String> parse(String cmd) throws NoSuchOrderException {
        for (Order order : orders) {
            String regex = order.getRegex();
            Pattern pattern = Pattern.compile(regex);
            java.util.regex.Matcher matcher = pattern.matcher(cmd);
            if (matcher.find()) {
                List<String> list = new ArrayList<>();
                list.add(order.getName());
                List<String> l = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                l.removeAll(Arrays.asList(" "));
                l.remove(0);
                list.addAll(l);
                return new ArrayList<String>(list);
            }
        }
        throw new NoSuchOrderException();
    }

    /**
     * String of all orders
     * @return
     */
    public String getOrders() {
        StringBuffer str = new StringBuffer();
        str.append("orders:\n");
        for (Order o : orders)
            str.append(o.toString() + "\n");
        return new String(str);
    }

    public String getSpecialWords() {
        StringBuffer str = new StringBuffer();
        str.append("Special words:\n");
        for (String s : specialWordsInDoc.keySet())
            str.append(s + "  " + specialWordsInDoc.get(s) + "\n");
        return new String(str);
    }

    /**
     * 规定doc中的特殊字符串
     */
    private void BuildSpecialWords() {
        String tinyFileNameRegex = new String("[a-zA-Z0-9\\-]+\\.[a-zA-Z]+");

        specialWordsInDoc.put(new String("tinyFileName"), tinyFileNameRegex);
    }

    /**
     * build a single line from String s
     * @param s
     * @throws EmptyOrderException
     * @throws NoSuchOrderException
     */
    private void buildLine(String s) throws EmptyOrderException, NoSuchOrderException {
        List<String> list = new ArrayList<>(Arrays.asList(s.split("\\b")));
        list.removeAll(Arrays.asList(" ", "\n"));

        int size = list.size();
        if (size <= 0)
            throw new EmptyOrderException();
        String name = list.get(0);
        String regex;
        List<String> params = new ArrayList<>();
        for (int i = 1; i < size; i++)
            params.add(list.get(i));

        if (size == 1)
            regex = new String(name);
        else if (size == 2)
            regex = new String(name + "\\s" + specialWordsInDoc.get(list.get(1)) + "\\s*");
        else
            throw new NoSuchOrderException();
        orders.add(new Order(name, regex, params, size));

    }

}

class EmptyOrderException extends Exception {
    public EmptyOrderException() {
        super();
    }
}

class NoSuchOrderException extends Exception {
    public NoSuchOrderException() {
        super();
    }
}
