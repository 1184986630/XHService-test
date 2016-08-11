-- Create table
create table DEAL_BILL_PAYRESPONSE
(
  id              VARCHAR2(64) not null,
  uptranseq       VARCHAR2(200),
  trandate        VARCHAR2(200),
  retncode        VARCHAR2(200),
  retninfo        VARCHAR2(200),
  orderreqtranseq VARCHAR2(200),
  orderseq        VARCHAR2(80),
  orderamount     NUMBER(20,2),
  productamount   NUMBER(20,2),
  attachamount    NUMBER(20,2),
  curtype         VARCHAR2(200),
  encodetype      VARCHAR2(200),
  attach          VARCHAR2(200),
  sign            VARCHAR2(500),
  disable_opid    VARCHAR2(100),
  disable_date    TIMESTAMP(9),
  create_date     TIMESTAMP(9),
  rec_status      NUMBER(1) not null,
  remark          VARCHAR2(200),
  order_ye        VARCHAR2(6) default 2,
  platform_id     VARCHAR2(20) default 1,
  constraint PK_DEAL_BILL_PAYRESPONSE primary key (id, rec_status)
);
-- Add comments to the table 
comment on table DEAL_BILL_PAYRESPONSE
  is '支付反馈记录表';
-- Add comments to the columns 
comment on column DEAL_BILL_PAYRESPONSE.id
  is '主键';
comment on column DEAL_BILL_PAYRESPONSE.uptranseq
  is '支付平台交易流水号';
comment on column DEAL_BILL_PAYRESPONSE.trandate
  is '支付平台交易日期';
comment on column DEAL_BILL_PAYRESPONSE.retncode
  is '处理结果码';
comment on column DEAL_BILL_PAYRESPONSE.retninfo
  is '处理结果解释码';
comment on column DEAL_BILL_PAYRESPONSE.orderreqtranseq
  is '订单请求交易流水号';
comment on column DEAL_BILL_PAYRESPONSE.orderseq
  is '订单号';
comment on column DEAL_BILL_PAYRESPONSE.orderamount
  is '订单总金额';
comment on column DEAL_BILL_PAYRESPONSE.productamount
  is '产品金额';
comment on column DEAL_BILL_PAYRESPONSE.attachamount
  is '附加金额';
comment on column DEAL_BILL_PAYRESPONSE.curtype
  is '币种';
comment on column DEAL_BILL_PAYRESPONSE.encodetype
  is '加密方式';
comment on column DEAL_BILL_PAYRESPONSE.attach
  is 'SP附加信息';
comment on column DEAL_BILL_PAYRESPONSE.sign
  is '数字签名';
comment on column DEAL_BILL_PAYRESPONSE.disable_opid
  is '修改操作员';
comment on column DEAL_BILL_PAYRESPONSE.disable_date
  is '修改时间';
comment on column DEAL_BILL_PAYRESPONSE.create_date
  is '创建时间';
comment on column DEAL_BILL_PAYRESPONSE.rec_status
  is '记录状态[1]:0为无效，1为有效';
comment on column DEAL_BILL_PAYRESPONSE.remark
  is '备注';
comment on column DEAL_BILL_PAYRESPONSE.order_ye
  is '2代表二期数据 11代表一期迁移数据';
comment on column DEAL_BILL_PAYRESPONSE.platform_id
  is '支付平台ID';
