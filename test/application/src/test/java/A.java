/**
 * @author rensong.pu
 * @date 2020/9/1 16:32 星期二
 **/
public class A{
    double weight = 2.0;
    {
        weight = 2.1;
        System.out.println("我是代码块");
    }

    public A(){
        weight = 2.2;
    }
    static{
        System.out.println("我是静态代码块");
    }
    public static void main(String[] args) {
        A a = new A();
        A a1 = new A();
        System.out.println(a.weight);
    }
}