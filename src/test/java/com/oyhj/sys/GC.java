package com.oyhj.sys;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GC {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/wage-management-sys?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
        String username="root";
        String password="123456";
        String moduleName ="sys";
        String mapperLocation ="E:\\payroll_system\\src\\main\\resources\\mapper\\"+moduleName;
        String table="users,role,menu,role_menu,user_role,wage_list,staff_wage,department,post,state,list_type,post_dep_user,dep_user,about_edu,user_basewage,usestate,taxandsafe";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("xiaocai") // 设置作者
                           // .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            //.outputDir("D:\\bishe\\payroll_management _system\\src\\main\\java"); // 指定输出目录
                    .outputDir("E:\\payroll_system\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.oyhj") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(table); // 设置需要生成的表名
                            //.addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
