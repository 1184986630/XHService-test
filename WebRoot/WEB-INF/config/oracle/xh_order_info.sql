-- Create table
create table XH_ORDER_INFO
(
  order_id                VARCHAR2(80) not null,
  user_id                 VARCHAR2(64),
  order_source            VARCHAR2(20),
  ts_gs_code              VARCHAR2(400),
  ts_gs_nm                CHAR(40),
  prov_code               VARCHAR2(20),
  prov_name               VARCHAR2(200),
  city_code               VARCHAR2(20),
  city_name               VARCHAR2(200),
  prov_code_v             VARCHAR2(20),
  prov_name_v             VARCHAR2(200),
  city_code_v             VARCHAR2(20),
  city_name_v             VARCHAR2(200),
  sale_id                 VARCHAR2(40),
  ts_mk_type_id           CHAR(40),
  pk_id                   VARCHAR2(40),
  kxb_id                  VARCHAR2(40),
  price                   NUMBER(11),
  order_status            VARCHAR2(20),
  pay_tran_id             VARCHAR2(200),
  order_createtime        DATE,
  order_conmpletetime     DATE,
  order_closed_time       DATE,
  order_expiretime        DATE,
  order_overtime          DATE,
  order_pay_time          DATE,
  order_remark            VARCHAR2(2000),
  disable_date            DATE,
  disable_opid            VARCHAR2(100),
  rec_status              CHAR(1) not null,
  order_item_group_id     VARCHAR2(200),
  order_item_group_status VARCHAR2(100),
  constraint PK_XH_ORDER_INFO primary key (order_id)
);
-- Add comments to the table 
comment on table XH_ORDER_INFO
  is '订单表';
-- Add comments to the columns 
comment on column XH_ORDER_INFO.order_id
  is '主键，订单号：门户系统ID+客户标识+系统当前时间（系统时间精确到天）+流水号自0000001起自增长。按客户标识区分流水号从起点开始计算。';
comment on column XH_ORDER_INFO.user_id
  is '客户标识';
comment on column XH_ORDER_INFO.order_source
  is '订单来源：空 和 1web 2 wap 3 理想客服端 4 易讯 5mini营业厅 6 win8客户端 7 智能电视客户端';
comment on column XH_ORDER_INFO.ts_gs_code
  is '店铺编码';
comment on column XH_ORDER_INFO.ts_gs_nm
  is '店铺id';
comment on column XH_ORDER_INFO.prov_code
  is '主号省编码';
comment on column XH_ORDER_INFO.prov_name
  is '主号省名称';
comment on column XH_ORDER_INFO.city_code
  is '主号市编码';
comment on column XH_ORDER_INFO.city_name
  is '主号市名称';
comment on column XH_ORDER_INFO.prov_code_v
  is '小号省编码';
comment on column XH_ORDER_INFO.prov_name_v
  is '小号省名称';
comment on column XH_ORDER_INFO.city_code_v
  is '小号市编码';
comment on column XH_ORDER_INFO.city_name_v
  is '小号市名称';
comment on column XH_ORDER_INFO.sale_id
  is '销售品ID';
comment on column XH_ORDER_INFO.ts_mk_type_id
  is '销售品类型，对应销售品类型表设置：小号订购、续订、退订等';
comment on column XH_ORDER_INFO.pk_id
  is '套餐ID';
comment on column XH_ORDER_INFO.kxb_id
  is '可选包ID';
comment on column XH_ORDER_INFO.price
  is '价格：分';
comment on column XH_ORDER_INFO.order_status
  is '订单状态：20100:办理中 20101:办理成功 20102:办理失败 10100:等待支付 10104:订单关闭 10703:支付失败 11201:退款中 11202:退款成功 11203:退款失败 10103:证件审核不通过 10701:已取消 见字典表s_dic.dic_type="ORDERSTATUS"';
comment on column XH_ORDER_INFO.pay_tran_id
  is '订单支付流水号';
comment on column XH_ORDER_INFO.order_createtime
  is '订单生成时间';
comment on column XH_ORDER_INFO.order_conmpletetime
  is '订单完成时间';
comment on column XH_ORDER_INFO.order_closed_time
  is '订单关闭时间';
comment on column XH_ORDER_INFO.order_expiretime
  is '订单失效时间：销售品配置，超过这个时间不允许支付，但可以接收支付反馈';
comment on column XH_ORDER_INFO.order_overtime
  is '订单超时时间：失效时间+30分钟，设置为订单关闭状态';
comment on column XH_ORDER_INFO.order_pay_time
  is '支付完成时间';
comment on column XH_ORDER_INFO.order_remark
  is '修改备注';
comment on column XH_ORDER_INFO.disable_date
  is '修改时间';
comment on column XH_ORDER_INFO.disable_opid
  is '修改操作员';
comment on column XH_ORDER_INFO.rec_status
  is '记录状态:0为无效，1为有效';
comment on column XH_ORDER_INFO.order_item_group_id
  is '订单项分组ID';
comment on column XH_ORDER_INFO.order_item_group_status
  is '订单分组ID 报竣，如果一个订单项有两个订单项分组，当第一个订单项报竣时，改为1,原来是Remark3 见字典表s_dic.dic_type=''ORDER_ITEM_GROUP_STATUS''';