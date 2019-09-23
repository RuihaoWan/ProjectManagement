package com.rio.proj.mysql.dto;

import com.rio.proj.mysql.constant.OpType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Data
public class ParseTemplate {

    /**
     * Template data structure:
     * private String database;
     * private List<JsonTable> tableList;
     *
     * JsonTable data structure:
     * private String tableName;
     * private Integer level;
     * private List<Column> insert;
     * private List<Column> update;
     * private List<Column> delete; --> parseTemplate
     *                                        |
     * TableTemplate data structure:    <------
     * private String tableName;
     * private String level;
     * private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();
     * private Map<Integer,String> posMap = new HashMap<>();
     */
    private String database;

    private Map<String,TableTemplate> tableTemplateMap = new HashMap<>();

    public static ParseTemplate parse(Template _template) {
        ParseTemplate template = new ParseTemplate();
        template.setDatabase(_template.getDatabase());

        for(JsonTable table : _template.getTableList()) {
            String name = table.getTableName();
            Integer level = table.getLevel();

            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(name);
            tableTemplate.setLevel(level.toString());
            template.tableTemplateMap.put(name,tableTemplate);

            Map<OpType, List<String>> opTypeFieldSetMap =
                    tableTemplate.getOpTypeFieldSetMap();
            for(JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(
                        OpType.ADD,
                        opTypeFieldSetMap,
                        ArrayList::new
                ).add(column.getColumn());
            }
            for(JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(
                        OpType.UPDATE,
                        opTypeFieldSetMap,
                        ArrayList::new
                ).add(column.getColumn());
            }
            for(JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(
                        OpType.DELETE,
                        opTypeFieldSetMap,
                        ArrayList::new
                ).add(column.getColumn());
            }
        }
        return template;
    }

    private static <T,R> R getAndCreateIfNeed(T key, Map<T,R> map,
                                              Supplier<R> factory) {
        return map.computeIfAbsent(key,k->factory.get());
    }
}
