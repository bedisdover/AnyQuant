package bl;

import database.UserInfo;
import po.UserPO;
import vo.UserVO;

/**
 * Created by zcy on 2016/5/12.
 *
 */
public class UserLogin {
    public UserVO login(String id,String password){
        UserInfo userInfo = new UserInfo();
        UserPO userPO = userInfo.getUserInfo(id,password);
        if(userPO==null){
            return null;
        }
        else{
            return new UserVO(userPO);
        }
    }
}
