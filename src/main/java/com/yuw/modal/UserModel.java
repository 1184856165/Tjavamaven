package com.yuw.modal;



import com.yuw.dao.DBProvider;
import com.yuw.utils.MyUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

public class UserModel {

    /**
     * 验证用户是否登录成功
     *
     * @param userName 用户名
     * @param userPsw  密码
     * @return 验证结果
     */
    public boolean doLogin(String userName, String userPsw) {
        // 默认返回值
        boolean canLogined = false;

        // 查询sql语句
        String strSql = "SELECT * FROM userinfo WHERE username = ? AND userpsw = ?";
        // 创建查询器对象
        //DBProvider dbProvider = new DBProvider();
        //List<Map<String, Object>> list = MyUtils.getNewInstance(DBProvider.class).queryListMap(strSql, userName, userPsw);
        List<Map<String, Object>> list = MyUtils.getNewInstance(DBProvider.class).query(strSql, new MapListHandler(), userName, userPsw);
        // 判断是否登录成功
        if (list != null && list.size() > 0) {
            // 登录成功
            canLogined = true;
        }
        // 返回值
        return canLogined;
    }

}
