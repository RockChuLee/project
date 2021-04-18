package com.imooc.project;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/20 17:52
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 1. 创建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 2. 创建一个全局配置对象
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取用户工作目录，即项目根路径
        gc.setOutputDir(projectPath + "/src/main/java");//设置输出目录
        gc.setAuthor("XXX");//设置作者名
        gc.setOpen(false);//设置完成后是否打开文件
        gc.setServiceName("%sService");//设置Service类的名字
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);//全局配置对象设置近代码生产器对象中

        // 3. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/project?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 4. 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));//分模块开发使用
        pc.setParent("com.imooc");
        mpg.setPackageInfo(pc);

        // 5. 自定义配置（自定义参数，或者自定义模板的时候使用）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //是否覆盖或者重新创建，如果不需要覆盖，注释本段
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
                return true;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 6. 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);//设置为null，不然会覆盖自定义配置
        mpg.setTemplate(templateConfig);

        // 7. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//是否使用Lombok模式
        strategy.setRestControllerStyle(false);//是否是@RestController注解

        //如果输入y继承基类，输入其他的不继承
        if (scanner("是否继承基类").equalsIgnoreCase("y")) {
            // 公共实体父类
            strategy.setSuperEntityClass("com.imooc.project.entity.BaseEntity");
            // 写于父类中的公共字段
            strategy.setSuperEntityColumns("deleted", "create_time", "modified_time", "create_account_id", "modified_account_id");
        }
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));//手动输入表生成Controller
        // 公共父类（Controller的公共父类）
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        strategy.setControllerMappingHyphenStyle(true);//ControllerMapping中是否是驼峰命名风格

        //逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
        //自动填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill modifiedTime = new TableFill("modified_time", FieldFill.UPDATE);
        TableFill createAccountId = new TableFill("create_account_id", FieldFill.INSERT);
        TableFill modifiedAccountId = new TableFill("modified_account_id", FieldFill.UPDATE);
        tableFills.add(createTime);
        tableFills.add(modifiedTime);
        tableFills.add(createAccountId);
        tableFills.add(modifiedAccountId);
        strategy.setTableFillList(tableFills);

//        strategy.setTablePrefix(pc.getModuleName() + "_");//表命前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());//使用的是什么模板引擎

        //执行
        mpg.execute();
    }
}
