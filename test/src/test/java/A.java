import java.util.HashSet;
import java.util.Set;

/**
 * @author rensong.pu
 * @date 2020/9/1 16:32 星期二
 **/
public class A{

    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        s.add("abc");
        s.add("abc");
        System.out.println(s.size());
    }
}