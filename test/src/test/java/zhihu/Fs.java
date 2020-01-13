package zhihu;

/**
 * 分数四则运算
 */
public class Fs {
    long numerator; // 分子
    long denominator; // 分母

    Fs() {
    }

    Fs(long a, long b) {
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

    Fs add(Fs r) { // 加法运算
        long a = r.getNumerator();
        long b = r.getDenominator();
        long newNumerator = numerator * b + denominator * a;
        long newDenominator = denominator * b;
        Fs result = new Fs(newNumerator, newDenominator);
        return result;
    }

    @Override
    public String toString() {
        return "Fs{" +
                "fenzi=" + numerator +
                ", fenmu=" + denominator +
                '}';
    }
}