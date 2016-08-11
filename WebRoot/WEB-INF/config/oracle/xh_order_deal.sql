-- Create table
create table XH_ORDER_DEAL
(
  order_id         VARCHAR2(80) not null,
  order_status     VARCHAR2(20),
  complete_status  VARCHAR2(3),
  pay_status       CHAR(1),
  order_createtime DATE,
  order_overtime   DATE,
  status           CHAR(1),
  constraint PK_XH_ORDER_DEAL primary key (order_id)
);
-- Add comments to the table 
comment on table XH_ORDER_DEAL
  is '订单处理表';
-- Add comments to the columns 
comment on column XH_ORDER_DEAL.order_id
  is '主键，订单号：门户系统ID+客户标识+系统当前时间（系统时间精确到天）+流水号自0000001起自增长。按客户标识区分流水号从起点开始计算。';
comment on column XH_ORDER_DEAL.order_status
  is '订单状态';
comment on column XH_ORDER_DEAL.complete_status
  is '报竣状态';
comment on column XH_ORDER_DEAL.pay_status
  is '支付状态 0，已支付，1未支付';
comment on column XH_ORDER_DEAL.order_createtime
  is '订单生成时间';
comment on column XH_ORDER_DEAL.order_overtime
  is '订单超时时间';
comment on column XH_ORDER_DEAL.status
  is '处理状态 0：处理中，1：处理完成，2：处理失败';