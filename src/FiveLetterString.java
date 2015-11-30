import java.io.Serializable;
import java.util.Random;

public class FiveLetterString implements Comparable, Serializable {

    public static final int kLen = 5;

    byte[] str = new byte[kLen];

    public FiveLetterString() {
        Random r = new Random();
        for (int i = 0; i < kLen; i++) {
            str[i] = (byte) ('a' + r.nextInt(26));
        }
    }

    byte[] getString() {
        return str;
    }

    @Override
    public int compareTo(Object o) {
        FiveLetterString obj = (FiveLetterString) o;
        for (int i = 0; i < kLen; i++) {
            if (this.str[i] != obj.getString()[i]) {
                if (this.str[i] < obj.getString()[i])
                    return -1;
                else
                    return 1;
            }
        }
        return 0;
    }
}
