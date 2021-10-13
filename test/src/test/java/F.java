import java.util.*;

/**
 * @author rensong.pu
 * @date 2020/8/12 15:17 星期三
 **/
public class F {
    public static void main(String[] args) {
       Map map = new HashMap();
       map.put(null,"");
       map.put("null",null);
       map.put(null,null);
        System.out.println(map.size());

        Set set  = new HashSet();
        set.add(null);
        set.add("null");
        System.out.println(set.size());

        set = new TreeSet();
        set.add("null");
        set.add(null);
        System.out.println(set.size());
    }

    public boolean test(){
        Object val = null;
        if(val==null) return false;
        return true;
    }
}
