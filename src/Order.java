import java.util.ArrayList;
import java.util.List;

/**
 * 一条命令，包括名称，大小，正则表达式
 */
public class Order {
    private final String name;
    private final String regex;
    private final List<String> param;
    private final int length;

    /** 
     * AF:
     * name 命令名称，唯一标识
     * regex 正则表达式，用于判断是否合法
     * param 参数列表
     * length 用空格隔开的块的数目
     * RI:
     * length>=1
     * param!=null(但param.size()可以为0)
    */

    /**
     * 构造
     * @param name
     * @param regex
     * @param l
     */

    public Order(String name, String regex, List<String> param, int l) {
        this.name = name;
        this.regex = regex;
        this.param = new ArrayList<>(param);
        this.length = l;
        checkRep();
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public List<String> getParam() {
        return new ArrayList<String>(param);
    }

    public int size() {
        return length;
    }

    private boolean sameValue(Order that) {
        return this.name.equals(that.name);
    }

    @Override
    public boolean equals(Object that) {
        return (that instanceof Order) && (this.sameValue((Order) that));
    }

    @Override
    public int hashCode() {
        int re = 17;
        re = 31 * re + name.hashCode();
        return re;
    }

    /**
     * length >= 1
     */
    private void checkRep() {
        assert length >= 1;
        assert param != null;
    }

    @Override
    public String toString() {
        StringBuffer p = new StringBuffer();
        for (String pa : param)
            p.append(pa + " ");
        String params = new String(p);
        return new String(
                "name = " + name + "\nregex = " + regex + "\nparams = " + params + "\nlength = " + length + "\n");
    }
}