package org.hsweb.generator.db.template;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.template.TemplateOutput;
import org.webbuilder.sql.DataBase;
import org.webbuilder.sql.TableMetaData;

import java.util.Map;

/**
 * 创建表的模板输出，将模板输出为数据库表。在模板输出配置中，需要提供 table参数，类型为{@link TableMetaData}<br>
 * Created by 浩 on 2016-03-17 0017.
 */
public class TableTemplateOutput implements TemplateOutput {

    protected CodeTemplate template;

    protected DataBase dataBase;

    public TableTemplateOutput(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void output() {
        //生成数据库表
        Map<String, Object> config = template.getInput().getConfig();
        if (config != null) {
            TableMetaData metaData = (TableMetaData) config.get("table");
            if (metaData != null) {
                try {
                    dataBase.createTable(metaData);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            throw new RuntimeException("未获取到模板输入配置");
        }
    }

    @Override
    public void setTemplate(CodeTemplate template) {
        this.template = template;
    }
}
