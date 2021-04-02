package com.Tjise.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;

/**
 * 单例模式提供DBCP数据源
 * 单例模式：一个类只有唯一的一个实例对象；也就是从任何一个途径获取到的这个类的实例对象是唯一的；
 */
public class DBCPDataSource {
    // 1、 定义一个单例模式类的静态实例变量（那个类要实现单例模式就定义这个类的变量）
    private static DataSource dataSource;
    // 2、 将该类的构造方法私有化；【不允许外部进行new操作】
    private DBCPDataSource() {
    }

    // 3、 提供一个静态的获取该类的实例对象的方法入口

    // 注意这里是线程不安全的； 需要添加synchronized 保证单例模式的线程安全
    public static synchronized DataSource getDataSource(){
        // 静态的单例变量的初始化
        if(dataSource == null){

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


            // 第一次获取实例对象时，需要初始化操作
            BasicDataSource basicDataSource =  new BasicDataSource();//BasicDataSourceFactory.createDataSource()
            // 设置数据库连接属性
            basicDataSource.setUrl(strURL4Mysql8);
            basicDataSource.setDriverClassName(strDriverName4Mysql8);
            basicDataSource.setUsername(strUserName);
            basicDataSource.setPassword(strUserPsw);
            // 设置数据源的各项参数属性
            // 数据源性能参数先使用默认的，后面再进行补充
            // 创建的dbcp2的数据源赋值给单例模式实例对象
            dataSource = basicDataSource;
        }
        // 返回单例模式对象
        return  dataSource;
    }
}
