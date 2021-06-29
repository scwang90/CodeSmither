package ${packageName}.mapper.intent.implimport ${packageName}.mapper.intent.api.OrderQueryimport com.fasterxml.jackson.annotation.JsonIgnoreimport com.fasterxml.jackson.annotation.JsonIgnoreProperties@JsonIgnoreProperties("orders")class QueryOrder<T>(val column:String, val desc:Boolean) : WhereItem<T>(), OrderQuery<T> {    @JsonIgnore    private val _orders = mutableListOf<OrderQuery<T>>()    override val orders: List<OrderQuery<T>>        get() {            return _orders        }    init {        _orders.add(this)    }    override fun orderBy(vararg orders: OrderQuery<T>): OrderQuery<T> {        _orders.addAll(orders)        return this    }}