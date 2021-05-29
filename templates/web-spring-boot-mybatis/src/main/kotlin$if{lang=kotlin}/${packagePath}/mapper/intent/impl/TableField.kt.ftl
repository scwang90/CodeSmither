/*
  This class is generated by CodeMan
 */
package ${packageName}.mapper.intent.impl

import ${packageName}.mapper.intent.api.WhereQuery

class TableField<Table, Type : Any>(val name: String) {

    fun asc(): QueryOrder<Table> {
        return QueryOrder(name, false)
    }

    fun desc(): QueryOrder<Table> {
        return QueryOrder(name, true)
    }

    fun eq(value: Type): WhereQuery<Table> {
        return equal(value)
    }

    fun equal(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "=", value)
    }

    fun ne(value: Type): WhereQuery<Table> {
        return notEqual(value)
    }

    fun notEqual(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "<>", value)
    }

    fun like(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "LIKE", value)
    }

    fun contains(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "LIKE", "%$value%")
    }

    fun startsWith(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "LIKE", "$value%")
    }

    fun endsWith(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "LIKE", "%$value")
    }

    fun lt(value: Type): WhereQuery<Table> {
        return lessThan(value)
    }

    fun lessThan(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "<", value)
    }

    fun le(value: Type): WhereQuery<Table> {
        return lessOrEqual(value)
    }

    fun lessOrEqual(value: Type): WhereQuery<Table> {
        return QueryWhere(name, "<=", value)
    }

    fun gt(value: Type): WhereQuery<Table> {
        return greaterThan(value)
    }

    fun greaterThan(value: Type): WhereQuery<Table> {
        return QueryWhere(name, ">", value)
    }

    fun ge(value: Type): WhereQuery<Table> {
        return greaterOrEqual(value)
    }

    fun greaterOrEqual(value: Type): WhereQuery<Table> {
        return QueryWhere(name, ">=", value)
    }

    fun inList(value: List<Type>): WhereQuery<Table> {
        return QueryWhere(name, "IN", value)
    }

    val isNull: WhereQuery<Table>
        get() = QueryWhere(name, "IS NULL", null)
    val isNotNull: WhereQuery<Table>
        get() = QueryWhere(name, "IS NOT NULL", null)
}