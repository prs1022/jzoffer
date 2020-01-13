package zhihu;

import java.util.StringTokenizer;

/**
 * 分数四则运算
 */
public class Fe {
    long numerator; // 分子
    long denominator; // 分母

    Fe() {
    }

    Fe(long a, long b) {
        if (a == 0) {
            numerator = 0;
            denominator = 1;
        } else {
            setNumeratorAndDenominator(a, b);
        }
    }

    void setNumeratorAndDenominator(long a, long b) { // 设置分子和分母
        long c = f(Math.abs(a), Math.abs(b)); // 计算最大公约数
        numerator = a / c;
        denominator = b / c;
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    long getNumerator() {
        return numerator;
    }

    long getDenominator() {
        return denominator;
    }

    long f(long a, long b) { // 求a和b的最大公约数
        if (a < b) {
            long c = a;
            a = b;
            b = c;
        }
        long r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            ;
            r = a % b;
        }
        return b;
    }

    Fe add(Fe r) { // 加法运算
        long a = r.getNumerator();
        long b = r.getDenominator();
        long newNumerator = numerator * b + denominator * a;
        long newDenominator = denominator * b;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    Fe sub(Fe r) { // 减法运算
        long a = r.getNumerator();
        long b = r.getDenominator();
        long newNumerator = numerator * b - denominator * a;
        long newDenominator = denominator * b;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    Fe muti(Fe r) { // 乘法运算
        long a = r.getNumerator();
        long b = r.getDenominator();
        long newNumerator = numerator * a;
        long newDenominator = denominator * b;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    Fe div(Fe r) { // 除法运算
        long a = r.getNumerator();
        long b = r.getDenominator();
        long newNumerator = numerator * b;
        long newDenominator = denominator * a;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    static Fe qiuyu(double left, double right) { // 求余运算
        double point_result = Math.IEEEremainder(left, right);
        int r1 = (int) point_result;
        double r2 = point_result - r1;
        int newNumerator = r1 * 10000 + (int) (r2 * 10000);
        int newDenominator = 10000;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    static Fe pow(double left, double right) { // 求幂运算left^right
        double point_result = Math.pow(left, right);
        int r1 = (int) point_result;
        double r2 = point_result - r1;
        int newNumerator = r1 * 10000 + (int) (r2 * 10000);
        int newDenominator = 10000;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    static Fe max(double left, double right) { // 求两数中的较大值
        double point_result = Math.min(left, right);
        int r1 = (int) point_result;
        double r2 = point_result - r1;
        int newNumerator = r1 * 10000 + (int) (r2 * 10000);
        int newDenominator = 10000;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    static Fe min(double left, double right) { // 求两数中的较小值
        double point_result = Math.min(left, right);
        int r1 = (int) point_result;
        double r2 = point_result - r1;
        int newNumerator = r1 * 10000 + (int) (r2 * 10000);
        int newDenominator = 10000;
        Fe result = new Fe(newNumerator, newDenominator);
        return result;
    }

    // 封装了具体运算，主要为对输入进行转换，对输出封装
    public static void compute(String data1, String operation, String data2) {
        StringTokenizer fenxi = new StringTokenizer(data1, "/");
        int data1_1 = Integer.parseInt(fenxi.nextToken());
        int data1_2 = Integer.parseInt(fenxi.nextToken());
        fenxi = new StringTokenizer(data2, "/");
        int data2_1 = Integer.parseInt(fenxi.nextToken());
        int data2_2 = Integer.parseInt(fenxi.nextToken());
        Fe r1 = new Fe(data1_1, data1_2);
        Fe r2 = new Fe(data2_1, data2_2);
        Fe result;
        long a, b;
        if (operation.equals("+")) { // 两数相加
            result = r1.add(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("-")) { // 两数相减
            result = r1.sub(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("*")) { // 两数相乘
            result = r1.muti(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("/")) { // 两数相除
            result = r1.div(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("%")) { // 两数求余
            double left = (double) data1_1 / (double) data1_2;
            double right = (double) data2_1 / (double) data2_2;
            result = qiuyu(left, right);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("^")) { // 两数求幂
            double left = (double) data1_1 / (double) data1_2;
            double right = (double) data2_1 / (double) data2_2;
            result = pow(left, right);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("max")) { // 两数中的较大值
            double left = (double) data1_1 / (double) data1_2;
            double right = (double) data2_1 / (double) data2_2;
            result = max(left, right);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
        if (operation.equals("min")) { // 两数中的较小值
            double left = (double) data1_1 / (double) data1_2;
            double right = (double) data2_1 / (double) data2_2;
            result = min(left, right);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1 + " " + operation + " " + data2 + " = " + a + "/" + b);
        }
    }

    @Override
    public String toString() {
        return "Fe{" +
                "fenzi=" + numerator +
                ", fenmu=" + denominator +
                '}';
    }
}