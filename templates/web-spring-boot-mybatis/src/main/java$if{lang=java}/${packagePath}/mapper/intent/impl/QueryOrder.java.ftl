package ${packageName}.mapper.intent.impl;import ${packageName}.mapper.intent.api.OrderQuery;import java.util.Arrays;import java.util.LinkedList;import java.util.List;public class QueryOrder<T> implements OrderQuery<T> {    public final String column;    public final boolean desc;    public final List<OrderQuery<T>> orders = new LinkedList<>();    public QueryOrder(String column, boolean desc) {        this.desc = desc;        this.column = column;        this.orders.add(this);    }    @Override    public List<OrderQuery<T>> getOrders() {        return orders;    }    @Override    @SafeVarargs    public final OrderQuery<T> orderBy(OrderQuery<T>... orders) {        this.orders.addAll(Arrays.asList(orders));        return this;    }}