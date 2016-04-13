import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 宋益明 on 16-4-13.
 *
 * 测试正则表达式
 */
public class PatternTest {

    /**
     * 判断是否含有数字
     */
    public static boolean hasNumber(String string) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(hasNumber("10001"));
    }
}
