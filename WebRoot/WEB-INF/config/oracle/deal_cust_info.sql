-- Create table
create table DEAL_CUST_INFO
(
  user_id          VARCHAR2(100) not null,
  order_id         VARCHAR2(80) not null,
  order_detail_id  VARCHAR2(80) not null,
  crm_cust_id      VARCHAR2(100),
  idcardno         VARCHAR2(20),
  idcard_postcode  VARCHAR2(10),
  cust_name        VARCHAR2(200),
  cust_affress     VARCHAR2(1000),
  prodoffer_id     VARCHAR2(200),
  term_id          VARCHAR2(200),
  phone_number     VARCHAR2(15),
  area_pri_code    VARCHAR2(200),
  area_city_code   VARCHAR2(200),
  area_county_code VARCHAR2(200),
  order_error      VARCHAR2(200),
  order_tran_id    VARCHAR2(200),
  wap_tran_id      VARCHAR2(200),
  downflag         VARCHAR2(200) default '0',
  backinfo         VARCHAR2(200),
  disable_opid     VARCHAR2(100),
  disable_date     TIMESTAMP(9),
  create_date      TIMESTAMP(9),
  remark           VARCHAR2(200) default '1',
  contact_num      VARCHAR2(50),
  account_type     VARCHAR2(50),
  constraint PK_DEAL_CUST_INFO primary key (user_id, order_id, order_detail_id)
);
-- Add comments to the table 
comment on table  DEAL_CUST_INFO 
  is '入网信息表';
-- Add comments to the columns
comment on column DEAL_CUST_INFO.user_id
  is '主键ID';
comment on column DEAL_CUST_INFO.crm_cust_id
  is '省CRM客户ID';
comment on column DEAL_CUST_INFO.idcardno
  is '身份证号码';
comment on column DEAL_CUST_INFO.idcard_postcode
  is '身份证归属地邮编';
comment on column DEAL_CUST_INFO.cust_name
  is '姓名';
comment on column DEAL_CUST_INFO.cust_affress
  is '家庭住址';
comment on column DEAL_CUST_INFO.prodoffer_id
  is '套餐编号';
comment on column DEAL_CUST_INFO.term_id
  is '终端编码';
comment on column DEAL_CUST_INFO.phone_number
  is '选择的号码';
comment on column DEAL_CUST_INFO.area_pri_code
  is '省落地方编码';
comment on column DEAL_CUST_INFO.area_city_code
  is '市编码';
comment on column DEAL_CUST_INFO.area_county_code
  is '县编码';
comment on column DEAL_CUST_INFO.order_error
  is '正式单错误信息';
comment on column DEAL_CUST_INFO.order_tran_id
  is '正式单流水号';
comment on column DEAL_CUST_INFO.wap_tran_id
  is '掌厅WAP流水号';
comment on column DEAL_CUST_INFO.downflag
  is '下发标识：0未下发，1下发成功 见字典表s_dic.dic_type=''DOWNFLAG''';
comment on column DEAL_CUST_INFO.backinfo
  is '报文返回请求描述';
comment on column DEAL_CUST_INFO.disable_opid
  is '修改操作员';
comment on column DEAL_CUST_INFO.disable_date
  is '修改时间';
comment on column DEAL_CUST_INFO.create_date
  is '创建时间';
comment on column DEAL_CUST_INFO.remark
  is '备注';
comment on column DEAL_CUST_INFO.contact_num
  is '联系号码';
comment on column DEAL_CUST_INFO.account_type
  is '入网用户类型 201 手机用户 202 固话用户 203 宽带用户';