-- Create table
create table DEAL_XH_DC_ORDER_STATUS
(
  order_id     VARCHAR2(80) not null,
  order_status VARCHAR2(80),
  updata_time  TIMESTAMP(3)
);
-- Add comments to the table 
comment on table DEAL_XH_DC_ORDER_STATUS
  is '底层小号订单报竣状态表';
-- Add comments to the columns 
comment on column DEAL_XH_DC_ORDER_STATUS.order_id
  is '小号订单号';
comment on column DEAL_XH_DC_ORDER_STATUS.order_status
  is '订单报竣状态';
comment on column DEAL_XH_DC_ORDER_STATUS.updata_time
  is '订单状态更新时间（000000：报竣开始状态，001000：it报竣成功，002000：it报竣失败，001001：ims报竣成功，001002：ims报竣失败）';