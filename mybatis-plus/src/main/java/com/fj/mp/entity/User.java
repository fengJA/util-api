package com.fj.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)  //指定id类型为自增长
    private Long id;
    private String userName;
    @TableField(select = false) // 该字段不参与查询
    private String password;
    private String name;
    private Integer age;
    @TableField("email") // 解决字段与数据库不匹配的问题
    private String mail;


    public static void main(String[] args) {
        String time = "2020-05-21 15:34:22";
//        System.out.println(time > LocalDateTime.now());
    }
}
