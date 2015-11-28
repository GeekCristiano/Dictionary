import java.io.Serializable;
import java.util.Random;

public class ComplexKey implements Comparable, Serializable {
    private Long num;
    private FiveLetterString str;

    public ComplexKey() {
        Random r = new Random();
        num = r.nextLong();
        str = new FiveLetterString();
    }

    public Long getNum() {
        return num;
    }

    public FiveLetterString getStr() {
        return str;
    }

    public int compareTo(Object o) {
        ComplexKey obj = (ComplexKey) o;
        if (num.compareTo(obj.getNum()) != 0)
            return Long.signum(num - obj.getNum());
        else
            return (str.compareTo(obj.getStr()));
    }
}
