package ${packageName}.mapper.intent

<#list tables as table>
import ${packageName}.mapper.intent.tables.${table.classNameUpper}
</#list>


/**
 * Convenience access to all tables in
 */
object Tables {
<#list tables as table>

    /**
     * The table ${table.className}
     */
    val ${table.className} = ${table.classNameUpper}.TABLE
</#list>

}
