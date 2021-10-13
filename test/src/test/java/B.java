import java.lang.reflect.Field;
import java.util.HashSet;

/**
 * @author rensong.pu
 * @date 2020/9/1 17:01 星期二
 **/
public class B {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        System.out.println(cache);
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);

        Integer[] newCache = (Integer[]) myCache.get(cache);
        newCache[132] = newCache[133];

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }
}
