/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : seec_erp

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 24/05/2022 01:11:23
*/
use`seec_erp`;

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

INSERT INTO seec_erp.account (id, name, balance) VALUES (1, '工资发方', 99999999.00);
INSERT INTO seec_erp.account (id, name, balance) VALUES (39, 'Lola', 9999999.00);
INSERT INTO seec_erp.account (id, name, balance) VALUES (40, 'Mickle', 9141299.00);
INSERT INTO seec_erp.account (id, name, balance) VALUES (42, '小红', 95565952.00);
INSERT INTO seec_erp.account (id, name, balance) VALUES (43, '小明', 6590655.00);
INSERT INTO seec_erp.account (id, name, balance) VALUES (44, '李四', 3651111.00);


-- ----------------------------
DROP TABLE IF EXISTS `attendance_record_sheet`;
create table attendance_record_sheet
(
    emp_id int      not null comment '员工id',
    time   datetime not null comment '日期',
    primary key (emp_id, time)
)
    collate = utf8mb4_general_ci;

INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-01 08:40:12');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-02 15:30:12');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-04 12:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-06 09:32:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-08 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-09 10:32:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-10 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-12 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-13 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-14 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-15 18:31:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-18 19:42:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-19 21:11:57');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-20 12:52:44');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-22 14:06:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-23 20:34:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-25 15:12:21');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-26 12:28:13');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-27 22:29:18');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-28 13:57:09');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-06-29 21:42:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-07-07 18:38:46');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-07-08 11:10:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (4, '2022-07-09 17:44:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-08 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-09 10:32:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-10 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-12 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-13 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-14 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-15 18:31:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-18 19:42:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-19 21:11:57');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-20 12:52:44');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-22 14:06:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-23 20:34:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-25 15:12:21');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-26 12:28:13');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-27 22:29:18');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-28 13:57:09');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-06-29 21:42:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-01 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-03 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-04 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-05 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-06 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (14, '2022-07-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-08 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-09 10:32:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-10 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-12 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-13 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-14 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-15 18:31:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-18 19:42:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-19 21:11:57');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-20 12:52:44');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-22 14:06:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-23 20:34:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-25 15:12:21');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-26 12:28:13');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-27 22:29:18');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-28 13:57:09');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-06-29 21:42:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-01 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-03 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-04 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-05 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-06 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (15, '2022-07-07 18:25:40');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-08 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-09 10:32:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-10 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-12 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-13 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-14 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-15 18:31:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-18 19:42:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-19 21:11:57');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-20 12:52:44');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-22 14:06:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-23 20:34:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-25 15:12:21');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-26 12:28:13');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-27 22:29:18');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-28 13:57:09');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-06-29 21:42:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-01 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-03 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-04 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-05 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-06 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (16, '2022-07-08 16:33:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-08 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-09 10:32:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-10 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-12 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-13 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-14 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-15 18:31:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-18 19:42:45');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-19 21:11:57');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-20 12:52:44');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-22 14:06:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-23 20:34:55');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-25 15:12:21');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-26 12:28:13');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-27 22:29:18');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-28 13:57:09');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-06-29 21:42:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-01 03:52:35');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-03 02:01:17');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-04 07:34:56');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-05 14:21:25');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-06 19:32:14');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-07 11:01:32');
INSERT INTO seec_erp.attendance_record_sheet (emp_id, time) VALUES (17, '2022-07-08 16:26:12');

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

INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('商品', 0, 0, 0, 0);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('电子产品', 1, 0, 0, 0);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('生活用品', 1, 1, 0, 0);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('电脑', 2, 1, 2, 2);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('手机', 2, 1, 3, 3);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('ipad', 2, 1, 0, 0);
INSERT INTO seec_erp.category (name, parent_id, is_leaf, item_count, item_index) VALUES ('平板电脑', 2, 1, 4, 4);


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

INSERT INTO seec_erp.collection_sheet (id, customer, operator, state, create_time, total) VALUES ('SKD-20220708-00000', 2, '财务人员', '审批完成', null, 7100.00);
INSERT INTO seec_erp.collection_sheet (id, customer, operator, state, create_time, total) VALUES ('SKD-20220708-00001', 13, '财务人员', '审批失败', null, 5122.00);
INSERT INTO seec_erp.collection_sheet (id, customer, operator, state, create_time, total) VALUES ('SKD-20220708-00002', 16, '财务人员', '待审批', null, 6200.00);
INSERT INTO seec_erp.collection_sheet (id, customer, operator, state, create_time, total) VALUES ('SKD-20220708-00003', 3, '财务人员', '待审批', null, 4000.00);


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

INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (102, 39, 'SKD-20220708-00000', 1500.00, '转账条目1');
INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (103, 42, 'SKD-20220708-00000', 5600.00, '转账条目2');
INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (104, 40, 'SKD-20220708-00001', 3500.00, '');
INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (105, 42, 'SKD-20220708-00001', 1622.00, null);
INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (106, 43, 'SKD-20220708-00002', 6200.00, '');
INSERT INTO seec_erp.collection_sheet_content (id, account_id, collection_sheet_id, total, remark) VALUES (107, 42, 'SKD-20220708-00003', 4000.00, '');


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

INSERT INTO seec_erp.customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator) VALUES (2, '销售商', 4, 'Jack', '15016419864', '南哪儿大学', '515654', '12121@cba.com', 20000000.00, 7561800.00, 1280000.00, 'sky');
INSERT INTO seec_erp.customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator) VALUES (3, '供应商', 5, 'cyj', '16316519865', '广东汕头', '515154', '123456789@qq.com', 12.00, 2980.00, -3604.00, 'sky');
INSERT INTO seec_erp.customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator) VALUES (12, '销售商', 2, '李勇', '17645646329', '内蒙古', '512534', 'liyong@163.com', 456.00, 334.00, 457.00, '进货销售人员');
INSERT INTO seec_erp.customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator) VALUES (13, '销售商', 3, '赵晓', '14465946851', '北京', '515000', 'zhaoxiao123@google.com', 5463.00, 4536.00, 123.00, '进货销售人员');
INSERT INTO seec_erp.customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator) VALUES (16, '供应商', 1, '王平', '162134195686', '黑龙江哈尔滨', '561352', '16532615684@qq.com', 12.00, 123.00, 123.00, '销售经理');


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

INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (1, 'Jam', '434636', '南京大学', 1, '库存管理人员', '434979', '2022-03-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (2, 'Sam', '214636', '北京大学', 2, '进货销售人员', '4349219', '2022-03-02');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (3, 'LingLing', '434312', '西安大学', 2, '进货销售人员', '43497921', '2022-03-03');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (4, 'Taylor', '4803136', '东京大学', 3, '财务人员', '434213179', '2022-02-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (5, 'Lana', '432314636', '南京大学', 4, '人力资源人员', '43497931231', '2022-04-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (6, 'DaMing', '2132434636', '南京大学', 1, '库存管理人员', '43314979', '2022-03-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (7, 'XiaoHu', '432434636', '东京大学', 5, '总经理', '42134979', '2022-01-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (8, 'Charles', '412334636', '南京大学', 1, '库存管理人员', '434972319', '2022-02-06');
INSERT INTO seec_erp.employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate) VALUES (9, '一一', '16513519567', '上海', 2, '进货销售人员', '165956354545', '2022-05-04');



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

INSERT INTO seec_erp.payment_sheet (id, customer, operator, state, create_time, total) VALUES ('FKD-20220708-00000', 3, '财务人员', '审批完成', null, 3760.00);
INSERT INTO seec_erp.payment_sheet (id, customer, operator, state, create_time, total) VALUES ('FKD-20220708-00001', 13, '财务人员', '待审批', null, 5500.00);
INSERT INTO seec_erp.payment_sheet (id, customer, operator, state, create_time, total) VALUES ('FKD-20220708-00002', 2, '财务人员', '待审批', null, 1.00);


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

INSERT INTO seec_erp.payment_sheet_content (id, account_id, payment_sheet_id, total, remark) VALUES (72, 40, 'FKD-20220708-00000', 560.00, '');
INSERT INTO seec_erp.payment_sheet_content (id, account_id, payment_sheet_id, total, remark) VALUES (73, 39, 'FKD-20220708-00000', 3200.00, null);
INSERT INTO seec_erp.payment_sheet_content (id, account_id, payment_sheet_id, total, remark) VALUES (74, 44, 'FKD-20220708-00001', 3200.00, '');
INSERT INTO seec_erp.payment_sheet_content (id, account_id, payment_sheet_id, total, remark) VALUES (75, 39, 'FKD-20220708-00001', 2300.00, null);
INSERT INTO seec_erp.payment_sheet_content (id, account_id, payment_sheet_id, total, remark) VALUES (76, 43, 'FKD-20220708-00002', 1.00, '');


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

INSERT INTO seec_erp.payroll_sheet (id, operator, create_time, employee_id, name, payable_wages, unemployment_insurance, personal_income_tax, housing_provident_fund, actual_amount_issued, bank_card, state) VALUES ('GZD-20220709-00000', 'sky', '2022-07-09 21:14:18', 2, 'Sam', 7879.40, 78.79, 86.38, 78.79, 7635.43, '4349219', '待二级审批');
INSERT INTO seec_erp.payroll_sheet (id, operator, create_time, employee_id, name, payable_wages, unemployment_insurance, personal_income_tax, housing_provident_fund, actual_amount_issued, bank_card, state) VALUES ('GZD-20220709-00001', 'sky', '2022-07-09 21:15:03', 1, 'Jam', 8366.40, 83.66, 126.64, 83.66, 8072.43, '434979', '审批失败');
INSERT INTO seec_erp.payroll_sheet (id, operator, create_time, employee_id, name, payable_wages, unemployment_insurance, personal_income_tax, housing_provident_fund, actual_amount_issued, bank_card, state) VALUES ('GZD-20220709-00002', 'sky', '2022-07-09 21:15:08', 4, 'Taylor', 6872.40, 68.72, 56.17, 68.72, 6678.78, '434213179', '待一级审批');
INSERT INTO seec_erp.payroll_sheet (id, operator, create_time, employee_id, name, payable_wages, unemployment_insurance, personal_income_tax, housing_provident_fund, actual_amount_issued, bank_card, state) VALUES ('GZD-20220709-00003', 'sky', '2022-07-09 21:15:54', 5, 'Lana', 9362.40, 93.62, 226.24, 93.62, 8948.91, '43497931231', '待一级审批');



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

INSERT INTO seec_erp.post_sheet (id, name, basic_wage, post_wage, type) VALUES (1, '库存管理人员', 400.00, 8000.00, '月薪固定薪资制');
INSERT INTO seec_erp.post_sheet (id, name, basic_wage, post_wage, type) VALUES (2, '进货销售人员', 400.00, 5000.00, '月薪提成制');
INSERT INTO seec_erp.post_sheet (id, name, basic_wage, post_wage, type) VALUES (3, '财务人员', 400.00, 6500.00, '月薪固定薪资制');
INSERT INTO seec_erp.post_sheet (id, name, basic_wage, post_wage, type) VALUES (4, '人力资源人员', 400.00, 9000.00, '月薪固定薪资制');
INSERT INTO seec_erp.post_sheet (id, name, basic_wage, post_wage, type) VALUES (5, '总经理', 400.00, 300000.00, '年薪固定薪资制');


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

INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000400000', '戴尔电脑', 4, '戴尔(DELL)Vostro笔记本电脑5410 123色 戴尔成就3500Vostro1625D', 950, 2991.00, 4062.00, 1900.00, 3000.00);
INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000400001', '小米手机', 4, 'lalalalala', 1450, 2000.00, 3503.00, 3000.00, 4000.00);
INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000500000', 'intel电脑', 5, 'iphone14maxpro', 0, 6000.00, 10000.00, null, null);
INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000500001', 'iphone', 5, 'iphone14普通版', 0, 4000.00, 8002.00, null, null);
INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000500002', '坚果', 5, 'pro3', 0, 2499.00, 3199.00, null, null);
INSERT INTO seec_erp.product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp) VALUES ('0000000000900003', 'test', 9, 'unknown', 0, 1.00, 2.00, null, null);



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

INSERT INTO seec_erp.promotion_strategy (id, name, discount, reduce, strategy, start, end, gift, gift_num, level) VALUES (2, '暑假大促销', 10.00, 520.00, 'reduce', '2022-07-01 00:00:00', '2022-08-31 00:00:00', '', null, 2);
INSERT INTO seec_erp.promotion_strategy (id, name, discount, reduce, strategy, start, end, gift, gift_num, level) VALUES (3, '618', 6.00, 0.00, 'discount', '2022-06-01 00:00:00', '2022-06-18 00:00:00', null, null, 3);
INSERT INTO seec_erp.promotion_strategy (id, name, discount, reduce, strategy, start, end, gift, gift_num, level) VALUES (4, '清仓活动', 10.00, 0.00, 'gift', '2022-06-01 00:00:00', '2022-07-31 00:00:00', '0000000000400001', 1, 1);

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

INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220523-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:41', 800000.00, '退钱！');
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220523-00001', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:54', 500000.00, '退钱！！！');
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220523-00002', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:34:34', 100000.00, '退钱++++');
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220523-00003', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:39:30', 200000.00, 'mmmmm');
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220523-00004', 'JHD-20220523-00000', '67', '审批完成', '2022-05-23 23:42:32', 200000.00, 'mmmmk');
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220524-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-24 01:00:18', 160000.00, null);
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220524-00001', 'JHD-20220523-00002', 'xiaoshoujingli', '待一级审批', '2022-05-24 01:00:34', 140000.00, null);
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220626-00000', 'JHD-20220523-00000', 'cyj', '待一级审批', '2022-06-26 19:52:27', 1000000.00, null);
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220627-00000', 'JHD-20220523-00000', '进货销售人员', '待一级审批', '2022-06-27 20:29:19', 1000000.00, null);
INSERT INTO seec_erp.purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark) VALUES ('JHTHD-20220627-00001', 'JHD-20220523-00000', '进货销售人员', '待一级审批', '2022-06-27 21:31:47', 1000000.00, null);

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

INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (23, 'JHTHD-20220523-00000', '0000000000400000', 500, 600000.00, 1200.00, 'b');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (24, 'JHTHD-20220523-00000', '0000000000400001', 100, 200000.00, 2000.00, 'b');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (25, 'JHTHD-20220523-00001', '0000000000400000', 500, 500000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (26, 'JHTHD-20220523-00002', '0000000000400000', 100, 100000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (27, 'JHTHD-20220523-00003', '0000000000400000', 200, 200000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (28, 'JHTHD-20220523-00004', '0000000000400000', 200, 200000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (29, 'JHTHD-20220524-00000', '0000000000400000', 50, 60000.00, 1200.00, 'b');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (30, 'JHTHD-20220524-00000', '0000000000400001', 50, 100000.00, 2000.00, 'b');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (31, 'JHTHD-20220524-00001', '0000000000400000', 0, 0.00, 1300.00, 'c');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (32, 'JHTHD-20220524-00001', '0000000000400001', 50, 140000.00, 2800.00, 'c');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (33, 'JHTHD-20220626-00000', '0000000000400000', 1000, 1000000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (34, 'JHTHD-20220627-00000', '0000000000400000', 1000, 1000000.00, 1000.00, 'a');
INSERT INTO seec_erp.purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark) VALUES (35, 'JHTHD-20220627-00001', '0000000000400000', 1000, 1000000.00, 1000.00, 'a');


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

INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220523-00000', 1, 'xiaoshoujingli', 'a', 1000000.00, '审批完成', '2022-05-23 23:13:59');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220523-00001', 1, 'xiaoshoujingli', 'b', 2200000.00, '审批完成', '2022-05-23 23:14:34');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220523-00002', 1, 'xiaoshoujingli', 'c', 3450000.00, '审批完成', '2022-05-23 23:15:57');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220524-00000', 1, 'xiaoshoujingli', null, 2200000.00, '审批完成', '2022-05-24 00:56:54');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220524-00001', 1, 'xiaoshoujingli', null, 3240000.00, '审批完成', '2022-05-24 00:57:29');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220524-00002', 1, 'xiaoshoujingli', null, 1650000.00, '审批完成', '2022-05-24 01:02:04');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220626-00000', 1, 'cyj', null, 1200.00, '待二级审批', '2022-06-26 19:52:37');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220709-00000', 2, 'sky', '无', 81992.00, '待一级审批', '2022-07-09 16:33:16');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220709-00001', 12, 'sky', '无', 106584.00, '待一级审批', '2022-07-09 16:34:05');
INSERT INTO seec_erp.purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time) VALUES ('JHD-20220709-00002', 16, 'sky', '无', 47988.00, '待一级审批', '2022-07-09 16:34:29');



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

INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (51, 'JHD-20220523-00000', '0000000000400000', 1000, 1000.00, 1000000.00, 'a');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (52, 'JHD-20220523-00001', '0000000000400000', 1000, 1200.00, 1200000.00, 'b');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (53, 'JHD-20220523-00001', '0000000000400001', 500, 2000.00, 1000000.00, 'b');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (54, 'JHD-20220523-00002', '0000000000400000', 500, 1300.00, 650000.00, 'c');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (55, 'JHD-20220523-00002', '0000000000400001', 1000, 2800.00, 2800000.00, 'c');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (56, 'JHD-20220524-00000', '0000000000400000', 500, 1500.00, 750000.00, '');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (57, 'JHD-20220524-00000', '0000000000400001', 500, 2900.00, 1450000.00, null);
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (58, 'JHD-20220524-00001', '0000000000400000', 600, 1900.00, 1140000.00, '');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (59, 'JHD-20220524-00001', '0000000000400001', 700, 3000.00, 2100000.00, null);
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (60, 'JHD-20220524-00002', '0000000000400000', 300, 1900.00, 570000.00, '');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (61, 'JHD-20220524-00002', '0000000000400001', 400, 2700.00, 1080000.00, null);
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (62, 'JHD-20220626-00000', '0000000000400000', 12, 100.00, 1200.00, '');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (63, 'JHD-20220709-00000', '0000000000400000', 10, 5000.00, 50000.00, '戴尔电脑');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (64, 'JHD-20220709-00000', '0000000000400001', 8, 3999.00, 31992.00, '小米手机');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (65, 'JHD-20220709-00001', '0000000000500000', 6, 6099.00, 36594.00, 'intel电脑');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (66, 'JHD-20220709-00001', '0000000000500001', 10, 6999.00, 69990.00, 'iphone12');
INSERT INTO seec_erp.purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (67, 'JHD-20220709-00002', '0000000000500002', 12, 3999.00, 47988.00, '坚果手机');


-- ----------------------------
DROP TABLE IF EXISTS `push_money_sheet`;
create table push_money_sheet
(
    emp_id     int            not null comment '员工id'
        primary key,
    push_money decimal(10, 2) not null comment '提成'
)
    collate = utf8mb4_general_ci;

INSERT INTO seec_erp.push_money_sheet (emp_id, push_money) VALUES (2, 2501.00);
INSERT INTO seec_erp.push_money_sheet (emp_id, push_money) VALUES (3, 1230.00);
INSERT INTO seec_erp.push_money_sheet (emp_id, push_money) VALUES (9, 2500.00);

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

INSERT INTO seec_erp.sale_returns_sheet (id, sale_sheet_id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSTHD-20220709-00000', 'XSD-20220524-00000', null, 'sky', '', '待一级审批', '2022-07-09 19:49:25', null, null, null, null, null);



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

INSERT INTO seec_erp.sale_returns_sheet_content (id, sale_return_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (60, 'XSTHD-20220709-00000', '0000000000400000', 200, 3500.00, 700000.00, '');
INSERT INTO seec_erp.sale_returns_sheet_content (id, sale_return_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (61, 'XSTHD-20220709-00000', '0000000000400001', 100, 3500.00, 350000.00, null);



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

INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220523-00000', 2, 'xiaoshoujingli', '卖卖卖', '审批失败', '2022-05-23 23:46:12', 'xiaoshoujingli', 1300000.00, 0.80, 1039800.00, 200.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220524-00000', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-24 00:04:37', 'xiaoshoujingli', 4200000.00, 0.80, 3359800.00, 200.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220525-00002', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-25 00:45:25', 'xiaoshoujingli', 720000.00, 0.80, 575800.00, 200.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220526-00001', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-26 00:32:41', 'xiaoshoujingli', 620000.00, 0.80, 495800.00, 200.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220620-00003', 2, '销售经理', null, '待一级审批', '2022-06-20 00:37:59', '销售经理', 90.00, 1.00, 90.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220627-00000', 2, '进货销售人员', null, '审批完成', '2022-06-27 21:23:18', '进货销售人员', 1980.00, 45.00, 87647.00, 1453.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00000', 3, '销售经理', null, '审批完成', '2022-06-29 00:30:34', '销售经理', 144.00, 0.00, 0.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00001', 3, '销售经理', null, '审批失败', '2022-06-29 00:36:28', '销售经理', 100.00, 10.00, 877.00, 123.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00002', 3, '销售经理', null, '审批失败', '2022-06-29 00:36:56', '销售经理', 1000.00, 1.00, 1000.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00004', 2, '销售经理', null, '审批失败', '2022-06-29 00:41:31', '销售经理', 0.00, 1.00, 90.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00005', 3, '销售经理', null, '待二级审批', '2022-06-29 00:42:33', '销售经理', 100.00, 1.00, 90.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220629-00006', 3, '销售经理', null, '审批完成', '2022-06-29 00:43:02', '销售经理', 1230.00, 1.00, 1007.00, 100.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220701-00000', 3, '进货销售人员', null, '审批完成', '2022-07-01 15:13:30', '进货销售人员', 1000.00, 10.00, 500.00, 500.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220701-00001', 3, '进货销售人员', null, '待一级审批', '2022-07-01 15:16:05', '进货销售人员', 15129.00, 10.00, 15129.00, 0.00);
INSERT INTO seec_erp.sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount) VALUES ('XSD-20220705-00002', 3, '进货销售人员', null, '待一级审批', '2022-07-05 15:16:33', '进货销售人员', 144.00, 5.00, 72.00, 0.00);


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

INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (26, 'XSD-20220523-00000', '0000000000400000', 100, 5000.00, 500000.00, '卖卖卖1');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (27, 'XSD-20220523-00000', '0000000000400001', 400, 2000.00, 800000.00, '卖卖卖2');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (28, 'XSD-20220524-00000', '0000000000400000', 600, 3500.00, 2100000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (29, 'XSD-20220524-00000', '0000000000400001', 600, 3500.00, 2100000.00, null);
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (30, 'XSD-20220524-00001', '0000000000400000', 100, 2200.00, 220000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (31, 'XSD-20220524-00001', '0000000000400001', 100, 4000.00, 400000.00, null);
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (32, 'XSD-20220524-00002', '0000000000400000', 100, 3000.00, 300000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (33, 'XSD-20220524-00002', '0000000000400001', 100, 4200.00, 420000.00, null);
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (34, 'XSD-20220524-00003', '0000000000400000', 100, 2800.00, 280000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (35, 'XSD-20220524-00003', '0000000000400001', 100, 3800.00, 380000.00, null);
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (36, 'XSD-20220524-00004', '0000000000400000', 300, 3000.00, 900000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (37, 'XSD-20220524-00004', '0000000000400001', 500, 4000.00, 2000000.00, null);
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (38, 'XSD-20220626-00000', '0000000000400001', 145, 10.00, 1450.00, 'k');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (53, 'XSD-20220627-00000', '0000000000400000', 45, 44.00, 1980.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (54, 'XSD-20220629-00000', '0000000000400001', 12, 12.00, 144.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (55, 'XSD-20220629-00001', '0000000000400000', 10, 10.00, 100.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (56, 'XSD-20220629-00002', '0000000000400000', 10, 100.00, 1000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (57, 'XSD-20220629-00003', '0000000000400000', 10, 10.00, 90.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (58, 'XSD-20220629-00004', '0000000000400000', 10, 10.00, 90.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (59, 'XSD-20220629-00005', '0000000000400001', 10, 10.00, 90.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (60, 'XSD-20220629-00006', '0000000000400000', 123, 10.00, 1107.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (64, 'XSD-20220701-00000', '0000000000400000', 10, 100.00, 1000.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (65, 'XSD-20220701-00001', '0000000000400001', 123, 123.00, 15129.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (66, 'XSD-20220701-00001', '0000000000500001', 10, 0.00, 0.00, '赠品');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (67, 'XSD-20220701-00002', '0000000000400000', 12, 12.00, 144.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (68, 'XSD-20220704-00000', '0000000000400001', 10, 10.00, 100.00, '');
INSERT INTO seec_erp.sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark) VALUES (69, 'XSD-20220704-00000', '0000000000500001', 10, 0.00, 0.00, '赠品');



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

INSERT INTO seec_erp.user (id, name, password, role) VALUES (1, 'seecoder', '123456', 'INVENTORY_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (2, 'uncln', '123456', 'INVENTORY_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (3, 'kucun', '123456', 'INVENTORY_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (4, 'sky', '123456', 'ADMIN');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (5, 'zxr', '123456', 'SALE_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (6, '67', '123456', 'GM');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (7, 'xiaoshou', '123456', 'SALE_STAFF');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (8, 'Leone', '123456', 'GM');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (9, 'xiaoshoujingli', '123456', 'SALE_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (12, 'cyj', '123456', 'GM');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (13, '销售经理', '123456', 'SALE_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (14, '库存管理人员', '123456', 'INVENTORY_MANAGER');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (15, '进货销售人员', '123456', 'SALE_STAFF');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (16, '财务人员', '123456', 'FINANCIAL_STAFF');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (17, '人力资源人员', '123456', 'HR');
INSERT INTO seec_erp.user (id, name, password, role) VALUES (18, '总经理', '123456', 'GM');



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

INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (16, '0000000000400000', 0, 1000.00, 0, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (17, '0000000000400000', 150, 1200.00, 1, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (18, '0000000000400001', 350, 2000.00, 1, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (19, '0000000000400000', 0, 1300.00, 2, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (20, '0000000000400001', 200, 2800.00, 2, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (21, '0000000000400000', 300, 1900.00, 3, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (22, '0000000000400001', 400, 2700.00, 3, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (23, '0000000000400000', 500, 1500.00, 4, null);
INSERT INTO seec_erp.warehouse (id, pid, quantity, purchase_price, batch_id, production_date) VALUES (24, '0000000000400001', 500, 2900.00, 4, null);



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

INSERT INTO seec_erp.warehouse_gift_sheet_content (id, pid, unit_price, quantity, warehouse_gift_sheet_id, remark) VALUES (2, '0000000000500001', 0.00, 10, 'KCZPD-20220704-00000', '赠品');



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

INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220523-00000', 0, 'kucun', '2022-05-23 23:17:41', '审批完成', 'JHD-20220523-00000');
INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220523-00001', 1, 'kucun', '2022-05-23 23:17:42', '审批完成', 'JHD-20220523-00001');
INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220523-00002', 2, 'kucun', '2022-05-23 23:17:44', '审批完成', 'JHD-20220523-00002');
INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220524-00000', 3, 'kucun', '2022-05-24 01:02:31', '审批完成', 'JHD-20220524-00002');
INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220626-00000', 4, '库存管理人员', '2022-06-26 19:43:14', '审批完成', 'JHD-20220524-00000');
INSERT INTO seec_erp.warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id) VALUES ('RKD-20220626-00005', 5, null, '2022-06-26 19:52:39', '草稿', 'JHD-20220524-00001');



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

INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (47, 'RKD-20220523-00000', '0000000000400000', 1000, 1000.00, null, 'a');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (48, 'RKD-20220523-00001', '0000000000400000', 1000, 1200.00, null, 'b');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (49, 'RKD-20220523-00001', '0000000000400001', 500, 2000.00, null, 'b');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (50, 'RKD-20220523-00002', '0000000000400000', 500, 1300.00, null, 'c');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (51, 'RKD-20220523-00002', '0000000000400001', 1000, 2800.00, null, 'c');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (52, 'RKD-20220524-00000', '0000000000400000', 300, 1900.00, null, '');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (53, 'RKD-20220524-00000', '0000000000400001', 400, 2700.00, null, null);
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (54, 'RKD-20220626-00000', '0000000000400000', 500, 1500.00, null, '');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (55, 'RKD-20220626-00000', '0000000000400001', 500, 2900.00, null, null);
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (56, 'RKD-20220626-00005', '0000000000400000', 600, 1900.00, null, '');
INSERT INTO seec_erp.warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark) VALUES (57, 'RKD-20220626-00005', '0000000000400001', 700, 3000.00, null, null);




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

INSERT INTO seec_erp.warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state) VALUES ('CKD-20220524-00000', 'kucun', '2022-05-24 00:05:32', 'XSD-20220524-00000', '审批完成');
INSERT INTO seec_erp.warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state) VALUES ('CKD-20220524-00001', 'kucun', '2022-05-24 00:33:12', 'XSD-20220524-00001', '审批完成');
INSERT INTO seec_erp.warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state) VALUES ('CKD-20220524-00002', 'kucun', '2022-05-24 00:45:38', 'XSD-20220524-00002', '审批完成');
INSERT INTO seec_erp.warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state) VALUES ('CKD-20220627-00000', null, '2022-06-27 22:42:33', 'XSD-20220524-00003', '草稿');
INSERT INTO seec_erp.warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state) VALUES ('CKD-20220629-00000', null, '2022-06-29 10:46:15', 'XSD-20220524-00004', '草稿');



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

INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (28, '0000000000400000', 'CKD-20220524-00000', 2, 600, 3500.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (29, '0000000000400000', 'CKD-20220524-00000', 1, 600, 3500.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (30, '0000000000400001', 'CKD-20220524-00000', 2, 600, 3500.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (35, '0000000000400000', 'CKD-20220524-00001', 1, 100, 2200.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (36, '0000000000400001', 'CKD-20220524-00001', 2, 100, 4000.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (37, '0000000000400000', 'CKD-20220524-00002', 1, 100, 3000.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (38, '0000000000400001', 'CKD-20220524-00002', 2, 100, 4200.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (39, '0000000000400000', 'CKD-20220626-00000', null, 100, 2200.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (40, '0000000000400001', 'CKD-20220626-00000', null, 100, 4000.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (41, '0000000000400000', 'CKD-20220626-00001', null, 100, 2800.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (42, '0000000000400001', 'CKD-20220626-00001', null, 100, 3800.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (57, '0000000000400000', 'CKD-20220627-00000', null, 100, 2800.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (58, '0000000000400001', 'CKD-20220627-00000', null, 100, 3800.00, null);
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (59, '0000000000400000', 'CKD-20220629-00000', null, 300, 3000.00, '');
INSERT INTO seec_erp.warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark) VALUES (60, '0000000000400001', 'CKD-20220629-00000', null, 500, 4000.00, null);



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

