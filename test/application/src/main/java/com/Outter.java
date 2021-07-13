package java.com;

/**
 * @author rensong.pu
 * @date 2021/1/22 15:44 星期五
 **/
public class Outter {
    private Integer  a;
    Integer  b;
    public  Integer c;
    class Inner{
        void print(){
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        Inner in = new Outter().new Inner();
        in.print();
    }
}
