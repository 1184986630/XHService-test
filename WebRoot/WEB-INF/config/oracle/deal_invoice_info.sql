-- Create table
create table DEAL_INVOICE_INFO
(
  order_id             VARCHAR2(80) not null,
  invoice_level        VARCHAR2(4),
  invoice_type_code    VARCHAR2(30),
  invoice_type         VARCHAR2(20),
  invoice_lb_code      VARCHAR2(20),
  invoice_lb           VARCHAR2(50),
  invoice_title_code   VARCHAR2(30),
  invoice_title        VARCHAR2(100),
  invoice_tsy          VARCHAR2(100),
  invoice_sm           VARCHAR2(100),
  price                NUMBER(10,2),
  invoice_url          VARCHAR2(500),
  invoice_pdfurl       VARCHAR2(500),
  invoice_code         VARCHAR2(80),
  invoice_number       VARCHAR2(80),
  invoicecaptcha       VARCHAR2(80),
  invoice_outer_pdfurl VARCHAR2(200),
  invoice_email        VARCHAR2(200),
  invoice_status       VARCHAR2(20),
  invoice_phone        VARCHAR2(200),
  status_change_time   TIMESTAMP(3)
);
-- Add comments to the table 
comment on table DEAL_INVOICE_INFO
  is '发票信息表';
-- Add comments to the columns 
comment on column DEAL_INVOICE_INFO.order_id
  is '订单号';
comment on column DEAL_INVOICE_INFO.invoice_level
  is '类型等级（1.总计 2.预存款，3.物品费） ';
comment on column DEAL_INVOICE_INFO.invoice_type_code
  is '发票类型编码：根据类型等级填写不同内容';
comment on column DEAL_INVOICE_INFO.invoice_type
  is '发票类型 查看字典表 s_dic where dic_type=''INVOICETYPE'' ';
comment on column DEAL_INVOICE_INFO.invoice_lb_code
  is '发票类别编码 查看字典表 s_dic where dic_type=''INVOICE_CONTENT''  ';
comment on column DEAL_INVOICE_INFO.invoice_lb
  is '发票类别 查看字典表 s_dic where dic_type=''INVOICE_CONTENT''  ';
comment on column DEAL_INVOICE_INFO.invoice_title_code
  is '发票title编码';
comment on column DEAL_INVOICE_INFO.invoice_title
  is '发票title';
comment on column DEAL_INVOICE_INFO.invoice_tsy
  is '提示语';
comment on column DEAL_INVOICE_INFO.invoice_sm
  is '说明';
comment on column DEAL_INVOICE_INFO.price
  is '价格';
comment on column DEAL_INVOICE_INFO.invoice_url
  is '电子发票URL';
comment on column DEAL_INVOICE_INFO.invoice_pdfurl
  is '电子发票PDF URL';
comment on column DEAL_INVOICE_INFO.invoice_code
  is '发票代码';
comment on column DEAL_INVOICE_INFO.invoice_number
  is '发票号码';
comment on column DEAL_INVOICE_INFO.invoicecaptcha
  is '发票防伪码';
comment on column DEAL_INVOICE_INFO.invoice_email
  is '发票发送邮箱';
comment on column DEAL_INVOICE_INFO.invoice_status
  is '发票状态（未索取0，开票中1，开票成功2，开票失败3，邮件发送成功4，邮件发送失败5）';
comment on column DEAL_INVOICE_INFO.invoice_phone
  is '发票发送至手机号';
comment on column DEAL_INVOICE_INFO.status_change_time
  is '发票状态变化时间';