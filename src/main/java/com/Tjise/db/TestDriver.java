package com.Tjise.db;

import com.Tjise.bean.UserInfoBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestDriver {
    public static void main(String[] args) {
        // 单例模式测试
        test01();
        System.out.println("===============================");
        test02();
        System.out.println("===============================");
        test03();

    }

    /**
     * DBUtils工具使用测试——List+Bean
     */
    public static void test03() {
        // 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(DBCPDataSource.getDataSource());
        // 进行查询操作

        //        (4) 准备数据库的SQL语句，用于处理业务逻辑；
        String strSql = "select * from userinfo";
        //        (5) 使用查询器执行SQL语句，如果是查询操作，则会返回一个ResultSet结果集；
        List<UserInfoBean> listUsers = null;
        try {
            listUsers = (List<UserInfoBean>) queryRunner.query(strSql, new BeanListHandler(UserInfoBean.class));
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        //        (6) 遍历List+Map结果集对象，获取数据库中的数据，进行相关的业务逻辑处理；
        for (UserInfoBean bean : listUsers) {
            // 遍历map
            System.out.println(bean.toString());
        }
    }

    /**
     * DBUtils工具使用测试——List+Map
     */
    public static void test02() {
        // 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(DBCPDataSource.getDataSource());
        // 进行查询操作

        //        (4) 准备数据库的SQL语句，用于处理业务逻辑；
        String strSql = "select * from userinfo .";
        //        (5) 使用查询器执行SQL语句，如果是查询操作，则会返回一个ResultSet结果集；
        List<Map<String, Object>> listMap = null;
        try {
            listMap = queryRunner.query(strSql, new MapListHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //        (6) 遍历List+Map结果集对象，获取数据库中的数据，进行相关的业务逻辑处理；
        for (Map<String, Object> map : listMap) {
            // 遍历map
            Set<String> setKeys = map.keySet();
            for (String strKey : setKeys) {
                System.out.print("   " + map.get(strKey));
            }
            //换行
            System.out.println();
        }

    }

    /**
     * 单例模式测试
     */
    public static void test01() {
        DataSource dataSource = DBCPDataSource.getDataSource();
        DataSource dataSource1 = DBCPDataSource.getDataSource();
        System.out.println("dataSource1 == dataSource::::" + (dataSource1 == dataSource));
        //DBCPDataSource dbcpDataSource = new DBCPDataSource();
    }

}