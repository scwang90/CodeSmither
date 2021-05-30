package ${packageName}.mapper.auto.extensions

<#list tables as table>
import ${packageName}.mapper.auto.${table.className}AutoMapper
</#list>
import ${packageName}.mapper.intent.Tables
import ${packageName}.mapper.intent.api.Query
import ${packageName}.mapper.intent.api.WhereQuery
<#list tables as table>
import ${packageName}.mapper.intent.tables.${table.classNameUpper}
</#list>
<#list tables as table>
import ${packageName}.model.db.${table.className}
<#if table.hasCascadeKey>
import ${packageName}.model.db.${table.className}Bean
</#if>
</#list>
import org.apache.ibatis.session.RowBounds
<#list tables as table>
    
fun ${table.className}AutoMapper.count(where: ${table.classNameUpper}.()-> WhereQuery<${table.className}>): Int {
    return this.countWhere(where.invoke(Tables.${table.className}))
}

fun ${table.className}AutoMapper.delete(where: ${table.classNameUpper}.()-> WhereQuery<${table.className}>): Int {
    return this.deleteWhere(where.invoke(Tables.${table.className}))
}

fun ${table.className}AutoMapper.update(setter: ${table.classNameUpper}.()-> ${table.classNameUpper}.SetterQuery): Int {
    return this.updateSetter(setter.invoke(Tables.${table.className}))
}

fun ${table.className}AutoMapper.selectAll(): List<${table.className}> {
    return this.selectWhere(null)
}

fun ${table.className}AutoMapper.select(where: ${table.classNameUpper}.()-> Query<${table.className}>): List<${table.className}> {
    return this.selectWhere(where.invoke(Tables.${table.className}))
}

fun ${table.className}AutoMapper.select(rows: RowBounds, where: ${table.classNameUpper}.()-> Query<${table.className}>): List<${table.className}> {
    return this.selectWhere(where.invoke(Tables.${table.className}), rows)
}

fun ${table.className}AutoMapper.selectOne(where: ${table.classNameUpper}.()-> Query<${table.className}>): ${table.className}? {
    return this.selectOneWhere(where.invoke(Tables.${table.className}))
}
<#if table.hasCascadeKey>

fun ${table.className}AutoMapper.selectBeanAll(): List<${table.className}Bean> {
    return this.selectBeanWhere(null)
}

fun ${table.className}AutoMapper.selectBean(where: ${table.classNameUpper}.()-> Query<${table.className}>): List<${table.className}Bean> {
    return this.selectBeanWhere(where.invoke(Tables.${table.className}))
}

fun ${table.className}AutoMapper.selectBean(rows: RowBounds, where: ${table.classNameUpper}.()-> Query<${table.className}>): List<${table.className}Bean> {
    return this.selectBeanWhere(where.invoke(Tables.${table.className}), rows)
}

fun ${table.className}AutoMapper.selectBeanOne(where: ${table.classNameUpper}.()-> Query<${table.className}>): ${table.className}Bean? {
    return this.selectBeanOneWhere(where.invoke(Tables.${table.className}))
}
</#if>
</#list>