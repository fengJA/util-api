package com.fj.test.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.sql.*;
import java.util.Random;

public class TestAddDataToDb {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/gmall?useUnicode=true&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
        String username = "root";
        String passwd = "1235FJ";


        Connection connection = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection(url, username, passwd);
            String sql = "insert into pms (name,des) values(?,?)";
            pre = connection.prepareStatement(sql);
            for (int i = 0; i < 200; i++) {
                String names = Integer.toString(i);
                String des = Integer.toString(i + 1000);
                pre.setString(1,names);
                pre.setString(2,des);
                pre.execute();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pre != null){
                try {
                    pre.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                    throw new RuntimeException("没有关闭1");
                }
            }

            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                    throw new RuntimeException("没有关闭2");
                }
            }
        }

    }
}
