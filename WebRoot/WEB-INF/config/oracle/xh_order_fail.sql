-- Create table
create table XH_ORDER_FAIL
( 
  order_id        VARCHAR2(80) not null,
  order_status    VARCHAR2(20),
  complete_status VARCHAR2(3),
  constraint PK_XH_ORDER_FAIL primary key (order_id)
);
-- Add comments to the table 
comment on table XH_ORDER_FAIL
  is '订单失败表';
-- Add comments to the columns 
comment on column XH_ORDER_FAIL.order_id
  is '主键，订单号：门户系统ID+客户标识+系统当前时间（系统时间精确到天）+流水号自0000001起自增长。按客户标识区分流水号从起点开始计算。';
comment on column XH_ORDER_FAIL.order_status
  is '订单状态';
comment on column XH_ORDER_FAIL.complete_status
  is '报竣状态';