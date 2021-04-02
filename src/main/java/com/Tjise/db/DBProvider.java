package com.Tjise.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBProvider {
    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConn() {
        // 返回值
        Connection connection = null;
        // (0) 准备数据库连接的相关参数
        // 数据库的驱动类的名字 包路径名
        String strDriverName4Mysql8 = "com.mysql.cj.jdbc.Driver";
        String strDriverName4Mysql5 = "com.mysql.jdbc.Driver";
        String strDriverName4Oracle = "oracle.jdbc.driver.OracleDriver";

        // 数据库服务器的连接串
        // 连接串中的 localhost 127.0.0.1 ip地址 服务器名 表示服务器的访问路径  3306 mysql数据库服务器监听的端口号
        String strURL4Mysql8 = "jdbc:mysql://localhost:3306/book_manage?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true";
        String strURL4Mysql5 = "jdbc:mysql://localhost:3306/book_manage?useUnicode=true&characterEncoding=utf8";
        String strURL4Oracle = "jdbc:oracle:thin:@localhost:1521:orcl"; // orcl 表示Oracle数据库安装的实例名，默认是orcl，如果不是默认，则改成自己的

        // 登录数据库的用户名和密码
        String strUserName = "root";
        String strUserPsw = "root";

        try {
            //        (1) 加载数据库驱动类；【使用反射】
            // 如果jvm中没有数据库驱动类Driver实例对象，会自动根据连接串类型进行加载数据库驱动类的实例.
            Class.forName(strDriverName4Mysql8); // 这一行可以不用写，jvm会自动加载
            //        (2) 通过DriverManager类的静态方法获取数据库的连接对象Connection；
            connection = DriverManager.getConnection(strURL4Mysql8, strUserName, strUserPsw);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询操作
     *
     * @param strSql 查询语句
     * @return 查询结果集
     */
    public ResultSet query(String strSql) {
        //        (3) 通过数据库连接对象获取查询器 Statement ；
        ResultSet resultSet = null;
        try {
            Statement statement = getConn().createStatement();
            //        (5) 使用查询器执行SQL语句，如果是查询操作，则会返回一个ResultSet结果集；
            resultSet = statement.executeQuery(strSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }


    /**
     * 查询操作
     *
     * @param strSql 查询语句
     * @return 查询结果集【List+Map】
     */
    public List<Map<String, Object>> queryListMap(String strSql, Object... params) {
        // 返回的结果集
        List<Map<String, Object>> list = new ArrayList<>();
        try {

            //        (3) 通过数据库连接对象获取查询器 Statement ；
            PreparedStatement preparedStatement = getConn().prepareStatement(strSql);
            // 给PreparedStatement设置占位符的参数
            if (params != null && params.length > 0) {
                // 使用for循环依次设置占位符的值
                for (int j = 1; j <= params.length; j++) {
                    preparedStatement.setObject(j, params[j - 1]);
                }
            }
            //        (5) 使用查询器执行SQL语句，如果是查询操作，则会返回一个ResultSet结果集；
            ResultSet resultSet = preparedStatement.executeQuery();

            /////////////////////////////////////////////////////////////////
            // 将查询结果集resultset进行转储，转储到List+Map的集合中
            if (resultSet != null) {
                // 获取结果集中 列 的相关信息
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                // 获取结果集中的类的个数
                int columnCount = resultSetMetaData.getColumnCount();
                // while 遍历结果集的行数
                while (resultSet.next()) {
                    // 每一行用一个Map集合进行保存
                    Map<String, Object> mapRow = new LinkedHashMap<>();
                    // 遍历当前行的列
                    for (int i = 1; i <= columnCount; i++) {
                        // 获取当前列的名字
                        String strColumnName = resultSetMetaData.getColumnName(i); // 数据库的集合操作，下标都是从1开始的
                        // 通过列名获取各列对应的字段值
                        Object objColumnValue = resultSet.getObject(strColumnName);
                        // 将获取的 列名-字段值 键值对存入Map中
                        mapRow.put(strColumnName, objColumnValue);
                    }
                    // 把当前行的Map对象存入List集合中
                    list.add(mapRow);
                }
            }
            /////////////////////////////////////////////////////////////////
            // 关闭资源
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 新增、修改、删除操作
     *
     * @param strSql 查询语句
     * @param params 占位符的参数
     * @return 数据库操作结果
     */
    public int insertandmodifyanddelete(String strSql, Object... params) {
        // 返回结果集
        int i = 0;
        try {
            PreparedStatement preparedStatement = getConn().prepareStatement(strSql);
            // 给PreparedStatement设置占位符的参数
            if (params != null && params.length > 0) {
                // 使用for循环依次设置占位符的值
                for (int j = 1; j <= params.length; j++) {
                    preparedStatement.setObject(j, params[j - 1]);
                }
            }
            //  执行正删改查操作
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

}

