package bl;

import org.junit.Test;
import vo.UserVO;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/12.
 *
 */
public class UserLoginTest {
    @Test
    public void loginTest(){
        UserLogin userLogin = new UserLogin();
        UserVO userVO = userLogin.login("123456789@qq.com","123456");
        assertNotNull(userVO);

        userVO = userLogin.login("123@qq.com","123456");
        assertNull(userVO);
    }

}