-- Create table
create table DEAL_ORDER_STATUS_RECODE
(
  id                VARCHAR2(100) not null,
  order_id          VARCHAR2(80),
  order_status      VARCHAR2(20),
  order_status_name VARCHAR2(80),
  create_time       TIMESTAMP(9),
  remark            VARCHAR2(2000),
  constraint PK_DEAL_ORDER_STATUS_RECODE primary key (id)
);
-- Add comments to the table 
comment on table  DEAL_ORDER_STATUS_RECODE 
  is '订单状态变化记录表';
-- Add comments to the columns 
comment on column DEAL_ORDER_STATUS_RECODE.order_status
  is '订单状态： 10100:等待支付 10101:支付成功 10102:等待处理 10104:订单关闭 10105:开始备货 10111:已发货 10120::货物抵达自提点 10112:交易完成 10541:已拒收 10202:撤单中 10206:撤单失败 10212:撤单完成 10302:退货中 10307:退货失败 10313:退货完成 10402:换货中 10407:换货失败 10412:换货完成 10701  已取消 10702 支付中 10703 支付失败 11104 充值中 11105 充值成功 11106 充值失败 11108 购买成功 11109 购买失败 11201 退款中 11202 退款成功 11203 退款失败 见字典表s_dic.dic_type=''ORDERSTATUS''';