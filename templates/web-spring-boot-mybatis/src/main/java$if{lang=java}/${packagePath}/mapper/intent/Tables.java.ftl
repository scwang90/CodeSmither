/* This class is generated by CodeMan */package ${packageName}.mapper.intent;<#list tables as table>import ${packageName}.mapper.intent.tables.${table.classNameUpper};</#list>/** * Convenience access to all tables in */public class Tables {<#list tables as table>    /**    * The table ${table.className}    */    public static final ${table.classNameUpper} ${table.classNameUpper} = ${packageName}.mapper.intent.tables.${table.classNameUpper}.TABLE;</#list>}