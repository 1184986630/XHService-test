-- Create table
create table DEAL_ORDER_EXTEND_PROPERTY
(
  order_id     VARCHAR2(80) not null,
  extend_code  VARCHAR2(200),
  extend_value VARCHAR2(2000)
);
-- Add comments to the table 
comment on table  DEAL_ORDER_EXTEND_PROPERTY 
  is '订单扩展信息表';
-- Add comments to the columns 
comment on column DEAL_ORDER_EXTEND_PROPERTY.order_id
  is '订单ID';
comment on column DEAL_ORDER_EXTEND_PROPERTY.extend_code
  is '扩展属性编码';
comment on column DEAL_ORDER_EXTEND_PROPERTY.extend_value
  is '扩展属性值';