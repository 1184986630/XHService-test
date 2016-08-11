-- Create table
create table DEAL_ORDER_USER_CHOOSE
(
  order_id       VARCHAR2(80) not null,
  order_detailid VARCHAR2(80),
  choose_type    VARCHAR2(200),
  choose_id      VARCHAR2(200),
  choose_name    VARCHAR2(200),
  remark         VARCHAR2(600),
  par_choose     VARCHAR2(500),
  choose_price   NUMBER(8,2)
);
-- Add comments to the table 
comment on table  DEAL_ORDER_USER_CHOOSE 
  is '用户选择表';
-- Add comments to the columns
comment on column DEAL_ORDER_USER_CHOOSE.choose_type
  is '字典项：1.套餐，2.补贴，3.预存款，4.主可选包，5.可选包分项，7主号码，8.副号码，9.已有号码，11,现金补贴,99.其他见字典表s_dic.dic_type=''CHOOSE_TYPE''';
comment on column DEAL_ORDER_USER_CHOOSE.choose_id
  is '当选择项类型为号卡时，此处记录号码';
comment on column DEAL_ORDER_USER_CHOOSE.par_choose
  is '逻辑关系：选择类型为号码，填写大小卡ID；选择类型为可选包分项，填写主可选包ID';
comment on column DEAL_ORDER_USER_CHOOSE.choose_price
  is '选择项价格';