package com.lwyykj.core.bean.activity;

import java.util.ArrayList;
import java.util.List;

public class userQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public userQuery() {
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

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
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

        public Criteria andAuidIsNull() {
            addCriterion("auid is null");
            return (Criteria) this;
        }

        public Criteria andAuidIsNotNull() {
            addCriterion("auid is not null");
            return (Criteria) this;
        }

        public Criteria andAuidEqualTo(Short value) {
            addCriterion("auid =", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidNotEqualTo(Short value) {
            addCriterion("auid <>", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidGreaterThan(Short value) {
            addCriterion("auid >", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidGreaterThanOrEqualTo(Short value) {
            addCriterion("auid >=", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidLessThan(Short value) {
            addCriterion("auid <", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidLessThanOrEqualTo(Short value) {
            addCriterion("auid <=", value, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidIn(List<Short> values) {
            addCriterion("auid in", values, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidNotIn(List<Short> values) {
            addCriterion("auid not in", values, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidBetween(Short value1, Short value2) {
            addCriterion("auid between", value1, value2, "auid");
            return (Criteria) this;
        }

        public Criteria andAuidNotBetween(Short value1, Short value2) {
            addCriterion("auid not between", value1, value2, "auid");
            return (Criteria) this;
        }

        public Criteria andAuserIsNull() {
            addCriterion("auser is null");
            return (Criteria) this;
        }

        public Criteria andAuserIsNotNull() {
            addCriterion("auser is not null");
            return (Criteria) this;
        }

        public Criteria andAuserEqualTo(String value) {
            addCriterion("auser =", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserNotEqualTo(String value) {
            addCriterion("auser <>", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserGreaterThan(String value) {
            addCriterion("auser >", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserGreaterThanOrEqualTo(String value) {
            addCriterion("auser >=", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserLessThan(String value) {
            addCriterion("auser <", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserLessThanOrEqualTo(String value) {
            addCriterion("auser <=", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserLike(String value) {
            addCriterion("auser like", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserNotLike(String value) {
            addCriterion("auser not like", value, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserIn(List<String> values) {
            addCriterion("auser in", values, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserNotIn(List<String> values) {
            addCriterion("auser not in", values, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserBetween(String value1, String value2) {
            addCriterion("auser between", value1, value2, "auser");
            return (Criteria) this;
        }

        public Criteria andAuserNotBetween(String value1, String value2) {
            addCriterion("auser not between", value1, value2, "auser");
            return (Criteria) this;
        }

        public Criteria andApassIsNull() {
            addCriterion("apass is null");
            return (Criteria) this;
        }

        public Criteria andApassIsNotNull() {
            addCriterion("apass is not null");
            return (Criteria) this;
        }

        public Criteria andApassEqualTo(String value) {
            addCriterion("apass =", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassNotEqualTo(String value) {
            addCriterion("apass <>", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassGreaterThan(String value) {
            addCriterion("apass >", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassGreaterThanOrEqualTo(String value) {
            addCriterion("apass >=", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassLessThan(String value) {
            addCriterion("apass <", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassLessThanOrEqualTo(String value) {
            addCriterion("apass <=", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassLike(String value) {
            addCriterion("apass like", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassNotLike(String value) {
            addCriterion("apass not like", value, "apass");
            return (Criteria) this;
        }

        public Criteria andApassIn(List<String> values) {
            addCriterion("apass in", values, "apass");
            return (Criteria) this;
        }

        public Criteria andApassNotIn(List<String> values) {
            addCriterion("apass not in", values, "apass");
            return (Criteria) this;
        }

        public Criteria andApassBetween(String value1, String value2) {
            addCriterion("apass between", value1, value2, "apass");
            return (Criteria) this;
        }

        public Criteria andApassNotBetween(String value1, String value2) {
            addCriterion("apass not between", value1, value2, "apass");
            return (Criteria) this;
        }

        public Criteria andAnameIsNull() {
            addCriterion("aname is null");
            return (Criteria) this;
        }

        public Criteria andAnameIsNotNull() {
            addCriterion("aname is not null");
            return (Criteria) this;
        }

        public Criteria andAnameEqualTo(String value) {
            addCriterion("aname =", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotEqualTo(String value) {
            addCriterion("aname <>", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThan(String value) {
            addCriterion("aname >", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThanOrEqualTo(String value) {
            addCriterion("aname >=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThan(String value) {
            addCriterion("aname <", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThanOrEqualTo(String value) {
            addCriterion("aname <=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLike(String value) {
            addCriterion("aname like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotLike(String value) {
            addCriterion("aname not like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameIn(List<String> values) {
            addCriterion("aname in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotIn(List<String> values) {
            addCriterion("aname not in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameBetween(String value1, String value2) {
            addCriterion("aname between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotBetween(String value1, String value2) {
            addCriterion("aname not between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAmailIsNull() {
            addCriterion("amail is null");
            return (Criteria) this;
        }

        public Criteria andAmailIsNotNull() {
            addCriterion("amail is not null");
            return (Criteria) this;
        }

        public Criteria andAmailEqualTo(String value) {
            addCriterion("amail =", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailNotEqualTo(String value) {
            addCriterion("amail <>", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailGreaterThan(String value) {
            addCriterion("amail >", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailGreaterThanOrEqualTo(String value) {
            addCriterion("amail >=", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailLessThan(String value) {
            addCriterion("amail <", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailLessThanOrEqualTo(String value) {
            addCriterion("amail <=", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailLike(String value) {
            addCriterion("amail like", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailNotLike(String value) {
            addCriterion("amail not like", value, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailIn(List<String> values) {
            addCriterion("amail in", values, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailNotIn(List<String> values) {
            addCriterion("amail not in", values, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailBetween(String value1, String value2) {
            addCriterion("amail between", value1, value2, "amail");
            return (Criteria) this;
        }

        public Criteria andAmailNotBetween(String value1, String value2) {
            addCriterion("amail not between", value1, value2, "amail");
            return (Criteria) this;
        }

        public Criteria andAtelIsNull() {
            addCriterion("atel is null");
            return (Criteria) this;
        }

        public Criteria andAtelIsNotNull() {
            addCriterion("atel is not null");
            return (Criteria) this;
        }

        public Criteria andAtelEqualTo(String value) {
            addCriterion("atel =", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelNotEqualTo(String value) {
            addCriterion("atel <>", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelGreaterThan(String value) {
            addCriterion("atel >", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelGreaterThanOrEqualTo(String value) {
            addCriterion("atel >=", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelLessThan(String value) {
            addCriterion("atel <", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelLessThanOrEqualTo(String value) {
            addCriterion("atel <=", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelLike(String value) {
            addCriterion("atel like", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelNotLike(String value) {
            addCriterion("atel not like", value, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelIn(List<String> values) {
            addCriterion("atel in", values, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelNotIn(List<String> values) {
            addCriterion("atel not in", values, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelBetween(String value1, String value2) {
            addCriterion("atel between", value1, value2, "atel");
            return (Criteria) this;
        }

        public Criteria andAtelNotBetween(String value1, String value2) {
            addCriterion("atel not between", value1, value2, "atel");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Boolean value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Boolean value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Boolean value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Boolean value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Boolean value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Boolean> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Boolean> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Boolean value1, Boolean value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andGidIsNull() {
            addCriterion("gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(Short value) {
            addCriterion("gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(Short value) {
            addCriterion("gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(Short value) {
            addCriterion("gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(Short value) {
            addCriterion("gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(Short value) {
            addCriterion("gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(Short value) {
            addCriterion("gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<Short> values) {
            addCriterion("gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<Short> values) {
            addCriterion("gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(Short value1, Short value2) {
            addCriterion("gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(Short value1, Short value2) {
            addCriterion("gid not between", value1, value2, "gid");
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