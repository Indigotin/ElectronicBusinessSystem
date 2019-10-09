package com.hlq.course.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNull() {
            addCriterion("item_num is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNotNull() {
            addCriterion("item_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumEqualTo(Integer value) {
            addCriterion("item_num =", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotEqualTo(Integer value) {
            addCriterion("item_num <>", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThan(Integer value) {
            addCriterion("item_num >", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_num >=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThan(Integer value) {
            addCriterion("item_num <", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThanOrEqualTo(Integer value) {
            addCriterion("item_num <=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumIn(List<Integer> values) {
            addCriterion("item_num in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotIn(List<Integer> values) {
            addCriterion("item_num not in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumBetween(Integer value1, Integer value2) {
            addCriterion("item_num between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotBetween(Integer value1, Integer value2) {
            addCriterion("item_num not between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("item_price is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("item_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("item_price =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("item_price <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("item_price >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("item_price <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("item_price in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("item_price not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceIsNull() {
            addCriterion("item_total_price is null");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceIsNotNull() {
            addCriterion("item_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceEqualTo(BigDecimal value) {
            addCriterion("item_total_price =", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("item_total_price <>", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("item_total_price >", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_total_price >=", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceLessThan(BigDecimal value) {
            addCriterion("item_total_price <", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_total_price <=", value, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceIn(List<BigDecimal> values) {
            addCriterion("item_total_price in", values, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("item_total_price not in", values, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_total_price between", value1, value2, "itemTotalPrice");
            return (Criteria) this;
        }

        public Criteria andItemTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_total_price not between", value1, value2, "itemTotalPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}