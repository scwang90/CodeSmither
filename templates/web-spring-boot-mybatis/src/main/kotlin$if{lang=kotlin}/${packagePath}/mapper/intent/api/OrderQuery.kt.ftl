package ${packageName}.mapper.intent.apiinterface OrderQuery<T> : Query<T> {    fun orderBy(vararg orders: OrderQuery<T>): OrderQuery<T>}