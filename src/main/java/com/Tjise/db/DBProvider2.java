package com.Tjise.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class DBProvider2 {

    /**
     * 查询操作
     *
     * @param sql    查询sql语句
     * @param rsh    结果集标识类型
     * @param params 参数列表
     * @param <T>    返回值泛型
     * @return 返回指定类型查询结果集
     */
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
        // 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(DBCPDataSource.getDataSource());
        // 进行查询操作
        T t = null;
        try {
            t = queryRunner.query(sql, rsh, params);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        // 返回值
        return t;
    }

    /**
     * 修改、删除、增加操作
     *
     * @param sql    数据库操作的sql语句
     * @param params sql语句的参数
     * @return 数据库操作的结果
     */
    public int execute(String sql, Object... params) {
        // 默认返回值
        int i = 0;
        // 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(DBCPDataSource.getDataSource());
        // 执行增加、修改、删除操作
        try {
            i = queryRunner.execute(sql, params);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

}