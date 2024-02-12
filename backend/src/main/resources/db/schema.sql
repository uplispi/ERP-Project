

-- ----------------------------
DROP TABLE IF EXISTS `account`;
create table account
(
    id      int auto_increment comment '账户id'
        primary key,
    name    varchar(255)   not null comment '账户名称',
    balance decimal(10, 2) null comment '余额'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `attendance_record_sheet`;
create table attendance_record_sheet
(
    emp_id int      not null comment '员工id',
    time   datetime not null comment '日期',
    primary key (emp_id, time)
)
    collate = utf8mb4_general_ci;


-- ----------------------------
DROP TABLE IF EXISTS `category`;
create table category
(
    id         int auto_increment
        primary key,
    name       varchar(255) not null comment '名字',
    parent_id  int          not null comment '父节点id',
    is_leaf    tinyint      not null comment '是否为叶节点',
    item_count int          not null comment '商品个数',
    item_index int          not null comment '插入的下一个index'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `collection_sheet`;
create table collection_sheet
(
    id          varchar(31)    not null comment '收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx）'
        primary key,
    customer    int            null comment '客户',
    operator    varchar(31)    null comment '操作员',
    state       varchar(31)    null comment '单据状态',
    create_time datetime       null comment '创建时间',
    total       decimal(10, 2) null comment '总额'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `collection_sheet_content`;
create table collection_sheet_content
(
    id                  int auto_increment comment '转账列表id'
        primary key,
    account_id          int            null comment '账户id',
    collection_sheet_id varchar(31)    null comment '收款单id',
    total               decimal(10, 2) null comment '转账金额',
    remark              varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `customer`;
create table customer
(
    id             int auto_increment comment '编号'
        primary key,
    type           varchar(31)    null comment '分类(供应商\销售商)',
    level          int            null comment '级别（五级，一级普通用户，五级VIP客户）',
    name           varchar(31)    null comment '姓名',
    phone          varchar(15)    null comment '电话号码',
    address        varchar(255)   null comment '地址',
    zipcode        char(6)        null comment '邮编',
    email          varchar(255)   null comment '电子邮箱',
    line_of_credit decimal(10, 2) null comment '应收额度本公司给客户的信用额度，客户欠本公司的钱的总额不能超过应收额度）',
    receivable     decimal(10, 2) null comment '应收（客户还应付给本公司但还未付的钱， 即本公司应收的钱）',
    payable        decimal(10, 2) null comment '应付（本公司欠客户的钱）',
    operator       varchar(255)   null comment '默认业务员'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `employee_sheet`;
create table employee_sheet
(
    id        int auto_increment comment '员工id'
        primary key,
    name      varchar(31)  not null comment '姓名',
    phone_num varchar(15)  null comment '联系电话',
    address   varchar(255) null comment '住址',
    post_id   int          null comment '岗位id',
    post_name varchar(31)  null comment '岗位名称',
    bank_card varchar(32)  null comment '银行卡号',
    hiredate  date         not null comment '入职日期 yyyyMMdd'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `payment_sheet`;
create table payment_sheet
(
    id          varchar(31)    not null comment '收款单单据编号（格式为：FKD-yyyyMMdd-xxxxx）'
        primary key,
    customer    int            null comment '客户',
    operator    varchar(31)    null comment '操作员',
    state       varchar(31)    null comment '单据状态',
    create_time datetime       null comment '创建时间',
    total       decimal(10, 2) null comment '总额'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `payment_sheet_content`;
create table payment_sheet_content
(
    id               int auto_increment comment '转账列表id'
        primary key,
    account_id       int            null comment '账户id',
    payment_sheet_id varchar(31)    null comment '付款单id',
    total            decimal(10, 2) null comment '转账金额',
    remark           varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `payroll_sheet`;
create table payroll_sheet
(
    id                     varchar(31)    not null comment '工资单号：GZD-yyyymmdd-xxxx'
        primary key,
    operator               varchar(31)    null comment '操作员',
    create_time            datetime       null comment '创建时间',
    employee_id            int            null comment '员工编号',
    name                   varchar(35)    null comment '员工姓名',
    payable_wages          decimal(10, 2) null comment '应发工资',
    unemployment_insurance decimal(10, 2) null comment '失业保险',
    personal_income_tax    decimal(10, 2) null comment '个人所得税',
    housing_provident_fund decimal(10, 2) null comment '住房公积金',
    actual_amount_issued   decimal(10, 2) null comment '实发金额',
    bank_card              varchar(35)    null comment '银行卡',
    state                  varchar(31)    null comment '状态'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `post_sheet`;
create table post_sheet
(
    id         int auto_increment comment '岗位id'
        primary key,
    name       varchar(255)   not null comment '名称',
    basic_wage decimal(10, 2) not null comment '基本工资',
    post_wage  decimal(10, 2) not null comment '岗位工资',
    type       varchar(255)   not null comment '薪资类型'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `product`;
create table product
(
    id             char(16)       not null comment '分类id(11位) + 位置(5位) = 编号'
        primary key,
    name           varchar(255)   not null comment '名字',
    category_id    int            not null comment '商品分类id',
    type           varchar(255)   not null comment '商品型号',
    quantity       int            not null comment '商品数量',
    purchase_price decimal(10, 2) not null comment '进价',
    retail_price   decimal(10, 2) not null comment '零售价',
    recent_pp      decimal(10, 2) null comment '最近进价',
    recent_rp      decimal(10, 2) null comment '最近零售价'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `promotion_strategy`;
create table promotion_strategy
(
    id       int auto_increment comment '自增id'
        primary key,
    name     varchar(255)   null comment '名字',
    discount decimal(10, 2) null comment '折扣',
    reduce   decimal(10, 2) null comment '减',
    strategy varchar(255)   null comment '策略',
    start    datetime       null comment '开始日期',
    end      datetime       null comment '结束日期',
    gift     varchar(255)   null comment '赠品id',
    gift_num int default 0  null comment '赠品数量',
    level    int            null comment '用户级别'
)
    collate = utf8mb4_general_ci;


-- ----------------------------
DROP TABLE IF EXISTS `purchase_returns_sheet`;
create table purchase_returns_sheet
(
    id                varchar(31)    null comment '进货退货单id',
    purchase_sheet_id varchar(31)    null comment '关联的进货单id',
    operator          varchar(31)    null comment '操作员',
    state             varchar(31)    null comment '单据状态',
    create_time       datetime       null comment '创建时间',
    total_amount      decimal(10, 2) null comment '退货的总金额',
    remark            varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;


-- ----------------------------
DROP TABLE IF EXISTS `purchase_returns_sheet_content`;
create table purchase_returns_sheet_content
(
    id                        int auto_increment comment '自增id'
        primary key,
    purchase_returns_sheet_id varchar(31)    null comment '进货退货单id',
    pid                       char(16)       null comment '商品id',
    quantity                  int            null comment '数量',
    total_price               decimal(10, 2) null comment '该商品的总金额',
    unit_price                decimal(10, 2) null comment '该商品的单价',
    remark                    varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `purchase_sheet`;
create table purchase_sheet
(
    id           varchar(31)    not null comment '进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx'
        primary key,
    supplier     int            null comment '供应商',
    operator     varchar(31)    null comment '操作员',
    remark       varchar(255)   null comment '备注',
    total_amount decimal(10, 2) null comment '总额合计',
    state        varchar(31)    null comment '单据状态',
    create_time  datetime       null comment '创建时间'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `purchase_sheet_content`;
create table purchase_sheet_content
(
    id                int auto_increment comment '自增id'
        primary key,
    purchase_sheet_id varchar(31)    null comment '进货单id',
    pid               char(16)       null comment '商品id',
    quantity          int            null comment '数量',
    unit_price        decimal(10, 2) null comment '单价',
    total_price       decimal(10, 2) null comment '金额',
    remark            varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `push_money_sheet`;
create table push_money_sheet
(
    emp_id     int            not null comment '员工id'
        primary key,
    push_money decimal(10, 2) not null comment '提成'
)
    collate = utf8mb4_general_ci;


-- ----------------------------
DROP TABLE IF EXISTS `salary_sheet`;
create table salary_sheet
(
    id           varchar(31)    not null comment '工资单单据编号（格式为：GZD-yyyyMMdd-xxxxx）'
        primary key,
    employee_id  int            null comment '员工id',
    name         varchar(255)   not null comment '员工姓名',
    account_id   int            null comment '银行账户',
    raw_salary   decimal(10, 2) null comment '应发工资',
    tax          decimal(10, 2) null comment '税款',
    final_salary decimal(10, 2) null comment '实发工资'
)
    collate = utf8mb4_general_ci;


-- ----------------------------
DROP TABLE IF EXISTS `sale_returns_sheet`;
create table sale_returns_sheet
(
    id               varchar(31)    not null comment '销售退货单单据编号（格式为：XSTHD-yyyyMMdd-xxxxx'
        primary key,
    sale_sheet_id    varchar(31)    null comment '关联的销售单id',
    supplier         int            null comment '销售商',
    operator         varchar(31)    null comment '操作员',
    remark           varchar(255)   null comment '备注',
    state            varchar(31)    null comment '单据状态',
    create_time      datetime       null comment '创建时间',
    salesman         varchar(32)    null comment '业务员',
    raw_total_amount decimal(10, 2) null comment '折让前总金额',
    discount         decimal(10, 2) null comment '折扣',
    final_amount     decimal(10, 2) null comment '折让后金额',
    voucher_amount   decimal(10, 2) null comment '代金券金额'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `sale_returns_sheet_content`;
create table sale_returns_sheet_content
(
    id                   int auto_increment comment '自增id'
        primary key,
    sale_return_sheet_id varchar(31)    null comment '销售退货单id',
    pid                  char(16)       null comment '商品id',
    quantity             int            null comment '数量',
    unit_price           decimal(10, 2) null comment '单价',
    total_price          decimal(10, 2) null comment '金额',
    remark               varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `sale_sheet`;
create table sale_sheet
(
    id               varchar(31)    not null comment '进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx'
        primary key,
    supplier         int            null comment '供应商',
    operator         varchar(31)    null comment '操作员',
    remark           varchar(255)   null comment '备注',
    state            varchar(31)    null comment '单据状态',
    create_time      datetime       null comment '创建时间',
    salesman         varchar(32)    null comment '业务员',
    raw_total_amount decimal(10, 2) null comment '折让前总金额',
    discount         decimal(10, 2) null comment '折扣',
    final_amount     decimal(10, 2) null comment '折让后金额',
    voucher_amount   decimal(10, 2) null comment '代金券金额'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `sale_sheet_content`;
create table sale_sheet_content
(
    id            int auto_increment comment '自增id'
        primary key,
    sale_sheet_id varchar(31)    null comment '进货单id',
    pid           char(16)       null comment '商品id',
    quantity      int            null comment '数量',
    unit_price    decimal(10, 2) null comment '单价',
    total_price   decimal(10, 2) null comment '金额',
    remark        varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `user`;
create table user
(
    id       int auto_increment comment '用户id'
        primary key,
    name     varchar(255) not null comment '用户名',
    password varchar(255) not null comment '用户密码',
    role     varchar(255) not null comment '用户身份'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
create table warehouse
(
    id              int auto_increment comment '库存id'
        primary key,
    pid             char(16)       not null comment '商品编号',
    quantity        int            not null comment '数量',
    purchase_price  decimal(10, 2) not null comment '进价',
    batch_id        int            not null comment '批次',
    production_date datetime       null comment '出厂日期'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `warehouse_gift_sheet`;
create table warehouse_gift_sheet
(
    id            varchar(31) not null comment '库存赠品单单据编号（格式为：KCZPD-yyyyMMdd-xxxxx）'
        primary key,
    customer_id   int         null comment '客户id',
    operator      varchar(31) null comment '操作员',
    state         varchar(31) null comment '单据状态',
    create_time   datetime    null comment '创建时间',
    sale_sheet_id varchar(31) not null comment '关联得到销售单单据'
)
    collate = utf8mb4_general_ci;



-- ----------------------------
DROP TABLE IF EXISTS `warehouse_gift_sheet_content`;
create table warehouse_gift_sheet_content
(
    id                      int auto_increment comment '条目id'
        primary key,
    pid                     varchar(31)    null comment '赠品id',
    unit_price              decimal(10, 2) null comment '商品单价',
    quantity                int            not null comment '赠品数量',
    warehouse_gift_sheet_id varchar(31)    null comment '关联的库存赠品单id',
    remark                  varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `warehouse_input_sheet`;
create table warehouse_input_sheet
(
    id                varchar(32)  not null comment 'RKD + 日期 + index = 入库单编号'
        primary key,
    batch_id          int          not null comment '批次',
    operator          varchar(255) null comment '操作员',
    create_time       datetime     not null comment '创建时间',
    state             varchar(31)  null comment '单据状态',
    purchase_sheet_id varchar(31)  null comment '关联的进货单id'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `warehouse_input_sheet_content`;
create table warehouse_input_sheet_content
(
    id              int auto_increment comment '主键'
        primary key,
    wi_id           varchar(31)    not null comment '入库单编号',
    pid             char(16)       not null comment '商品id',
    quantity        int            not null comment '商品数量',
    purchase_price  decimal(10, 2) not null comment '单价',
    production_date datetime       null comment '出厂日期',
    remark          varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;





-- ----------------------------
DROP TABLE IF EXISTS `warehouse_output_sheet`;
create table warehouse_output_sheet
(
    id            varchar(31)  not null comment 'CKD + date + index = 出库单id'
        primary key,
    operator      varchar(255) null comment '操作员名字',
    create_time   datetime     not null comment '创建时间',
    sale_sheet_id varchar(31)  null comment '销售单id',
    state         varchar(31)  null comment '单据状态'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `warehouse_output_sheet_content`;
create table warehouse_output_sheet_content
(
    id         int auto_increment comment '出库商品列表id'
        primary key,
    pid        char(16)       not null comment '商品id',
    wo_id      varchar(31)    not null comment '出库单单据编号',
    batch_id   int            null comment '批次',
    quantity   int            not null comment '数量',
    sale_price decimal(10, 2) not null comment '对应批次的单价',
    remark     varchar(255)   null comment '备注'
)
    collate = utf8mb4_general_ci;




-- ----------------------------
DROP TABLE IF EXISTS `year_end_bonus_sheet`;
create table year_end_bonus_sheet
(
    year           int            not null comment '年份',
    employee_id    int            not null comment '员工id',
    year_end_bonus decimal(10, 2) not null comment '年终奖',
    primary key (year, employee_id)
)
    collate = utf8mb4_general_ci;

