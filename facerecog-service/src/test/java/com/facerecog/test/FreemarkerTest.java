package com.facerecog.test;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @Classname FreemarkerTest
 * @Description
 * @Date 2020/6/4 14:04
 * @Created by cjw
 */
@SpringBootTest
public class FreemarkerTest {

    //基于模板生成静态化文件
    @Test
    public void testGenerateHtml() throws IOException, TemplateException {
        //创建配置类
        Configuration configuration=new Configuration(Configuration.getVersion());
        //设置模板路径
//        String classpath = this.getClass().getResource("/").getPath();
        String classpath = "D:\\Intellij idea project\\facerecog\\facerecog-service\\target\\classes\\";
        File file = new File(classpath + "/templates/");
        configuration.setDirectoryForTemplateLoading(file);
        //设置字符集
//        configuration.setDefaultEncoding("utf‐8"); 报错
        //加载模板
        Template template = configuration.getTemplate("test.ftl");
        //数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name","小明");
        //静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //静态化内容
        System.out.println(content);
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test.html"));
        int copy = IOUtils.copy(inputStream, fileOutputStream);
    }
}
