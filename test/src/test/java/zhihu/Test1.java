package zhihu;

/**
 * 用java求出数列2/1,3/2,5/3,8/5,13/......前30项和是多少?
 * 求解
 *
 * @author rensong.pu
 * @date 2019/11/22 10:32 星期五
 **/
public class Test1 {
    public static void main(String[] args) {
        int fenzi = 2;
        int fenmu = 1;
        int tmp;
        Fs sum = new Fs(0, 1);
        for (int i = 0; i < 30; i++) {
            Fs fe = new Fs(fenzi, fenmu);
            System.out.println(fe);
            sum = sum.add(fe);
            tmp = fenzi;
            fenzi = fenzi + fenmu;
            fenmu = tmp;
        }
        System.out.println("前30项和:" + sum);
    }
}
