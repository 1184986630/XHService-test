-- Create table
create table DEAL_ORDER_XH_FLAG
(
  main_phone           VARCHAR2(20) not null,
  small_phone          VARCHAR2(20),
  channel_id           VARCHAR2(200) not null,
  flag                 INTEGER not null,
  updated_at           TIMESTAMP(9),
  xh_status            INTEGER,
  xh_status_updated_at TIMESTAMP(9)
);
-- Add comments to the table 
comment on table  DEAL_ORDER_XH_FLAG 
  is '小号办理状态表';
-- Add comments to the columns 
comment on column DEAL_ORDER_XH_FLAG.main_phone
  is '主号';
comment on column DEAL_ORDER_XH_FLAG.small_phone
  is '小号';
comment on column DEAL_ORDER_XH_FLAG.channel_id
  is '渠道号';
comment on column DEAL_ORDER_XH_FLAG.flag
  is '标志位，1字节保存，0000 0000，目前只使用低四位，对应订购、续订、退订和订购可选包。1为可以操作。';
comment on column DEAL_ORDER_XH_FLAG.updated_at
  is '标志位更新时间';
comment on column DEAL_ORDER_XH_FLAG.xh_status
  is '小号状态，1有效期，2保留期，3过期';
comment on column DEAL_ORDER_XH_FLAG.xh_status_updated_at
  is '小号状态更新时间';