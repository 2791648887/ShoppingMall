package com.shopping.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "userName";
    public static final String TOKEN_PREFIX = "token_prefix";


    public interface RedisCacheExtime {
        int REDIS_SESSION_EXTIME = 60 * 30;//缓存30分钟
    }

    public interface ProductLIstOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    public interface Cart {
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//即购物车未选中状态

        String LIMIT_NUM_FAIL = "limit_num_fail";
        String LIMIT_NUM_SUCCESS="limit_num_success";
    }

    public interface Role {
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");
        private String value;
        private int code;

        ProductStatusEnum(int code,String value) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum OrderStatusEnum {
        CANCELED(0,"已取消"),
        NO_PAY(10,"未付款"),
        PAID(20,"已付款"),
        SHIPPED(40,"已发货"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");

        private int code;
        private String value;

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum order:values()) {
                if (order.getCode()==code) {
                    return order;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface AlipayCallback {
        String READE_STATUS_WAIT_BUYER_PAY = "wait_buyer_pay";
        String TRADE_STATUS_TRADE_SUCCESS = "trade_success";
        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum {
        ALIPAY(1, "支付宝");

        private int code;
        private String value;

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }


        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }


    public enum PaymentTypeEnum {
        ONLINE_PAY(1,"在线支付");

        private int code;
        private String value;

        PaymentTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public static PaymentTypeEnum codeOf(int code) {
            for (PaymentTypeEnum payment : values()) {
                if (payment.getCode()==code) {
                    return payment;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public interface REDIS_LOCK {
        String CLOSE_ORDER_TASK_LOCK = "colse_order_task_lock";//关闭订单分布式锁
    }





}

