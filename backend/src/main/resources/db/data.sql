;
insert into warehouse_output_sheet_content (id, pid, wo_id, batch_id, quantity, sale_price, remark)
values  (28, '0000000000400000', 'CKD-20220524-00000', 2, 600, 3500.00, ''),
        (29, '0000000000400000', 'CKD-20220524-00000', 1, 600, 3500.00, ''),
        (30, '0000000000400001', 'CKD-20220524-00000', 2, 600, 3500.00, null),
        (35, '0000000000400000', 'CKD-20220524-00001', 1, 100, 2200.00, ''),
        (36, '0000000000400001', 'CKD-20220524-00001', 2, 100, 4000.00, null),
        (37, '0000000000400000', 'CKD-20220524-00002', 1, 100, 3000.00, ''),
        (38, '0000000000400001', 'CKD-20220524-00002', 2, 100, 4200.00, null),
        (39, '0000000000400000', 'CKD-20220626-00000', null, 100, 2200.00, ''),
        (40, '0000000000400001', 'CKD-20220626-00000', null, 100, 4000.00, null),
        (41, '0000000000400000', 'CKD-20220626-00001', null, 100, 2800.00, ''),
        (42, '0000000000400001', 'CKD-20220626-00001', null, 100, 3800.00, null),
        (57, '0000000000400000', 'CKD-20220627-00000', null, 100, 2800.00, ''),
        (58, '0000000000400001', 'CKD-20220627-00000', null, 100, 3800.00, null),
        (59, '0000000000400000', 'CKD-20220629-00000', null, 300, 3000.00, ''),
        (60, '0000000000400001', 'CKD-20220629-00000', null, 500, 4000.00, null);
insert into warehouse_output_sheet (id, operator, create_time, sale_sheet_id, state)
values  ('CKD-20220524-00000', 'kucun', '2022-05-24 00:05:32', 'XSD-20220524-00000', '审批完成'),
        ('CKD-20220524-00001', 'kucun', '2022-05-24 00:33:12', 'XSD-20220524-00001', '审批完成'),
        ('CKD-20220524-00002', 'kucun', '2022-05-24 00:45:38', 'XSD-20220524-00002', '审批完成'),
        ('CKD-20220627-00000', null, '2022-06-27 22:42:33', 'XSD-20220524-00003', '草稿'),
        ('CKD-20220629-00000', null, '2022-06-29 10:46:15', 'XSD-20220524-00004', '草稿');
insert into warehouse_input_sheet_content (id, wi_id, pid, quantity, purchase_price, production_date, remark)
values  (47, 'RKD-20220523-00000', '0000000000400000', 1000, 1000.00, null, 'a'),
        (48, 'RKD-20220523-00001', '0000000000400000', 1000, 1200.00, null, 'b'),
        (49, 'RKD-20220523-00001', '0000000000400001', 500, 2000.00, null, 'b'),
        (50, 'RKD-20220523-00002', '0000000000400000', 500, 1300.00, null, 'c'),
        (51, 'RKD-20220523-00002', '0000000000400001', 1000, 2800.00, null, 'c'),
        (52, 'RKD-20220524-00000', '0000000000400000', 300, 1900.00, null, ''),
        (53, 'RKD-20220524-00000', '0000000000400001', 400, 2700.00, null, null),
        (54, 'RKD-20220626-00000', '0000000000400000', 500, 1500.00, null, ''),
        (55, 'RKD-20220626-00000', '0000000000400001', 500, 2900.00, null, null),
        (56, 'RKD-20220626-00005', '0000000000400000', 600, 1900.00, null, ''),
        (57, 'RKD-20220626-00005', '0000000000400001', 700, 3000.00, null, null);
insert into warehouse_input_sheet (id, batch_id, operator, create_time, state, purchase_sheet_id)
values  ('RKD-20220523-00000', 0, 'kucun', '2022-05-23 23:17:41', '审批完成', 'JHD-20220523-00000'),
        ('RKD-20220523-00001', 1, 'kucun', '2022-05-23 23:17:42', '审批完成', 'JHD-20220523-00001'),
        ('RKD-20220523-00002', 2, 'kucun', '2022-05-23 23:17:44', '审批完成', 'JHD-20220523-00002'),
        ('RKD-20220524-00000', 3, 'kucun', '2022-05-24 01:02:31', '审批完成', 'JHD-20220524-00002'),
        ('RKD-20220626-00000', 4, '库存管理人员', '2022-06-26 19:43:14', '审批完成', 'JHD-20220524-00000'),
        ('RKD-20220626-00005', 5, null, '2022-06-26 19:52:39', '草稿', 'JHD-20220524-00001');
insert into warehouse_gift_sheet_content (id, pid, unit_price, quantity, warehouse_gift_sheet_id, remark)
values  (2, '0000000000500001', 0.00, 10, 'KCZPD-20220704-00000', '赠品');
;
insert into warehouse (id, pid, quantity, purchase_price, batch_id, production_date)
values  (16, '0000000000400000', 0, 1000.00, 0, null),
        (17, '0000000000400000', 150, 1200.00, 1, null),
        (18, '0000000000400001', 350, 2000.00, 1, null),
        (19, '0000000000400000', 0, 1300.00, 2, null),
        (20, '0000000000400001', 200, 2800.00, 2, null),
        (21, '0000000000400000', 300, 1900.00, 3, null),
        (22, '0000000000400001', 400, 2700.00, 3, null),
        (23, '0000000000400000', 500, 1500.00, 4, null),
        (24, '0000000000400001', 500, 2900.00, 4, null);
insert into user (id, name, password, role)
values  (1, 'seecoder', '123456', 'INVENTORY_MANAGER'),
        (2, 'uncln', '123456', 'INVENTORY_MANAGER'),
        (3, 'kucun', '123456', 'INVENTORY_MANAGER'),
        (4, 'sky', '123456', 'ADMIN'),
        (5, 'zxr', '123456', 'SALE_MANAGER'),
        (6, '67', '123456', 'GM'),
        (7, 'xiaoshou', '123456', 'SALE_STAFF'),
        (8, 'Leone', '123456', 'GM'),
        (9, 'xiaoshoujingli', '123456', 'SALE_MANAGER'),
        (12, 'cyj', '123456', 'GM'),
        (13, '销售经理', '123456', 'SALE_MANAGER'),
        (14, '库存管理人员', '123456', 'INVENTORY_MANAGER'),
        (15, '进货销售人员', '123456', 'SALE_STAFF'),
        (16, '财务人员', '123456', 'FINANCIAL_STAFF'),
        (17, '人力资源人员', '123456', 'HR'),
        (18, '总经理', '123456', 'GM');
insert into sale_sheet_content (id, sale_sheet_id, pid, quantity, unit_price, total_price, remark)
values  (26, 'XSD-20220523-00000', '0000000000400000', 100, 5000.00, 500000.00, '卖卖卖1'),
        (27, 'XSD-20220523-00000', '0000000000400001', 400, 2000.00, 800000.00, '卖卖卖2'),
        (28, 'XSD-20220524-00000', '0000000000400000', 600, 3500.00, 2100000.00, ''),
        (29, 'XSD-20220524-00000', '0000000000400001', 600, 3500.00, 2100000.00, null),
        (30, 'XSD-20220524-00001', '0000000000400000', 100, 2200.00, 220000.00, ''),
        (31, 'XSD-20220524-00001', '0000000000400001', 100, 4000.00, 400000.00, null),
        (32, 'XSD-20220524-00002', '0000000000400000', 100, 3000.00, 300000.00, ''),
        (33, 'XSD-20220524-00002', '0000000000400001', 100, 4200.00, 420000.00, null),
        (34, 'XSD-20220524-00003', '0000000000400000', 100, 2800.00, 280000.00, ''),
        (35, 'XSD-20220524-00003', '0000000000400001', 100, 3800.00, 380000.00, null),
        (36, 'XSD-20220524-00004', '0000000000400000', 300, 3000.00, 900000.00, ''),
        (37, 'XSD-20220524-00004', '0000000000400001', 500, 4000.00, 2000000.00, null),
        (38, 'XSD-20220626-00000', '0000000000400001', 145, 10.00, 1450.00, 'k'),
        (53, 'XSD-20220627-00000', '0000000000400000', 45, 44.00, 1980.00, ''),
        (54, 'XSD-20220629-00000', '0000000000400001', 12, 12.00, 144.00, ''),
        (55, 'XSD-20220629-00001', '0000000000400000', 10, 10.00, 100.00, ''),
        (56, 'XSD-20220629-00002', '0000000000400000', 10, 100.00, 1000.00, ''),
        (57, 'XSD-20220629-00003', '0000000000400000', 10, 10.00, 90.00, ''),
        (58, 'XSD-20220629-00004', '0000000000400000', 10, 10.00, 90.00, ''),
        (59, 'XSD-20220629-00005', '0000000000400001', 10, 10.00, 90.00, ''),
        (60, 'XSD-20220629-00006', '0000000000400000', 123, 10.00, 1107.00, ''),
        (64, 'XSD-20220701-00000', '0000000000400000', 10, 100.00, 1000.00, ''),
        (65, 'XSD-20220701-00001', '0000000000400001', 123, 123.00, 15129.00, ''),
        (66, 'XSD-20220701-00001', '0000000000500001', 10, 0.00, 0.00, '赠品'),
        (67, 'XSD-20220701-00002', '0000000000400000', 12, 12.00, 144.00, ''),
        (68, 'XSD-20220704-00000', '0000000000400001', 10, 10.00, 100.00, ''),
        (69, 'XSD-20220704-00000', '0000000000500001', 10, 0.00, 0.00, '赠品');
insert into sale_sheet (id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount)
values  ('XSD-20220523-00000', 2, 'xiaoshoujingli', '卖卖卖', '审批失败', '2022-05-23 23:46:12', 'xiaoshoujingli', 1300000.00, 0.80, 1039800.00, 200.00),
        ('XSD-20220524-00000', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-24 00:04:37', 'xiaoshoujingli', 4200000.00, 0.80, 3359800.00, 200.00),
        ('XSD-20220525-00002', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-25 00:45:25', 'xiaoshoujingli', 720000.00, 0.80, 575800.00, 200.00),
        ('XSD-20220526-00001', 2, 'xiaoshoujingli', null, '审批完成', '2022-05-26 00:32:41', 'xiaoshoujingli', 620000.00, 0.80, 495800.00, 200.00),
        ('XSD-20220620-00003', 2, '销售经理', null, '待一级审批', '2022-06-20 00:37:59', '销售经理', 90.00, 1.00, 90.00, 0.00),
        ('XSD-20220627-00000', 2, '进货销售人员', null, '审批完成', '2022-06-27 21:23:18', '进货销售人员', 1980.00, 45.00, 87647.00, 1453.00),
        ('XSD-20220629-00000', 3, '销售经理', null, '审批完成', '2022-06-29 00:30:34', '销售经理', 144.00, 0.00, 0.00, 0.00),
        ('XSD-20220629-00001', 3, '销售经理', null, '审批失败', '2022-06-29 00:36:28', '销售经理', 100.00, 10.00, 877.00, 123.00),
        ('XSD-20220629-00002', 3, '销售经理', null, '审批失败', '2022-06-29 00:36:56', '销售经理', 1000.00, 1.00, 1000.00, 0.00),
        ('XSD-20220629-00004', 2, '销售经理', null, '审批失败', '2022-06-29 00:41:31', '销售经理', 0.00, 1.00, 90.00, 0.00),
        ('XSD-20220629-00005', 3, '销售经理', null, '待二级审批', '2022-06-29 00:42:33', '销售经理', 100.00, 1.00, 90.00, 0.00),
        ('XSD-20220629-00006', 3, '销售经理', null, '审批完成', '2022-06-29 00:43:02', '销售经理', 1230.00, 1.00, 1007.00, 100.00),
        ('XSD-20220701-00000', 3, '进货销售人员', null, '审批完成', '2022-07-01 15:13:30', '进货销售人员', 1000.00, 10.00, 500.00, 500.00),
        ('XSD-20220701-00001', 3, '进货销售人员', null, '待一级审批', '2022-07-01 15:16:05', '进货销售人员', 15129.00, 10.00, 15129.00, 0.00),
        ('XSD-20220705-00002', 3, '进货销售人员', null, '待一级审批', '2022-07-05 15:16:33', '进货销售人员', 144.00, 5.00, 72.00, 0.00);
insert into sale_returns_sheet_content (id, sale_return_sheet_id, pid, quantity, unit_price, total_price, remark)
values  (60, 'XSTHD-20220709-00000', '0000000000400000', 200, 3500.00, 700000.00, ''),
        (61, 'XSTHD-20220709-00000', '0000000000400001', 100, 3500.00, 350000.00, null);
insert into sale_returns_sheet (id, sale_sheet_id, supplier, operator, remark, state, create_time, salesman, raw_total_amount, discount, final_amount, voucher_amount)
values  ('XSTHD-20220709-00000', 'XSD-20220524-00000', null, 'sky', '', '待一级审批', '2022-07-09 19:49:25', null, null, null, null, null);
;
insert into push_money_sheet (emp_id, push_money)
values  (2, 2501.00),
        (3, 1230.00),
        (9, 2500.00);
insert into purchase_sheet_content (id, purchase_sheet_id, pid, quantity, unit_price, total_price, remark)
values  (51, 'JHD-20220523-00000', '0000000000400000', 1000, 1000.00, 1000000.00, 'a'),
        (52, 'JHD-20220523-00001', '0000000000400000', 1000, 1200.00, 1200000.00, 'b'),
        (53, 'JHD-20220523-00001', '0000000000400001', 500, 2000.00, 1000000.00, 'b'),
        (54, 'JHD-20220523-00002', '0000000000400000', 500, 1300.00, 650000.00, 'c'),
        (55, 'JHD-20220523-00002', '0000000000400001', 1000, 2800.00, 2800000.00, 'c'),
        (56, 'JHD-20220524-00000', '0000000000400000', 500, 1500.00, 750000.00, ''),
        (57, 'JHD-20220524-00000', '0000000000400001', 500, 2900.00, 1450000.00, null),
        (58, 'JHD-20220524-00001', '0000000000400000', 600, 1900.00, 1140000.00, ''),
        (59, 'JHD-20220524-00001', '0000000000400001', 700, 3000.00, 2100000.00, null),
        (60, 'JHD-20220524-00002', '0000000000400000', 300, 1900.00, 570000.00, ''),
        (61, 'JHD-20220524-00002', '0000000000400001', 400, 2700.00, 1080000.00, null),
        (62, 'JHD-20220626-00000', '0000000000400000', 12, 100.00, 1200.00, ''),
        (63, 'JHD-20220709-00000', '0000000000400000', 10, 5000.00, 50000.00, '戴尔电脑'),
        (64, 'JHD-20220709-00000', '0000000000400001', 8, 3999.00, 31992.00, '小米手机'),
        (65, 'JHD-20220709-00001', '0000000000500000', 6, 6099.00, 36594.00, 'intel电脑'),
        (66, 'JHD-20220709-00001', '0000000000500001', 10, 6999.00, 69990.00, 'iphone12'),
        (67, 'JHD-20220709-00002', '0000000000500002', 12, 3999.00, 47988.00, '坚果手机');
insert into purchase_sheet (id, supplier, operator, remark, total_amount, state, create_time)
values  ('JHD-20220523-00000', 1, 'xiaoshoujingli', 'a', 1000000.00, '审批完成', '2022-05-23 23:13:59'),
        ('JHD-20220523-00001', 1, 'xiaoshoujingli', 'b', 2200000.00, '审批完成', '2022-05-23 23:14:34'),
        ('JHD-20220523-00002', 1, 'xiaoshoujingli', 'c', 3450000.00, '审批完成', '2022-05-23 23:15:57'),
        ('JHD-20220524-00000', 1, 'xiaoshoujingli', null, 2200000.00, '审批完成', '2022-05-24 00:56:54'),
        ('JHD-20220524-00001', 1, 'xiaoshoujingli', null, 3240000.00, '审批完成', '2022-05-24 00:57:29'),
        ('JHD-20220524-00002', 1, 'xiaoshoujingli', null, 1650000.00, '审批完成', '2022-05-24 01:02:04'),
        ('JHD-20220626-00000', 1, 'cyj', null, 1200.00, '待二级审批', '2022-06-26 19:52:37'),
        ('JHD-20220709-00000', 2, 'sky', '无', 81992.00, '待一级审批', '2022-07-09 16:33:16'),
        ('JHD-20220709-00001', 12, 'sky', '无', 106584.00, '待一级审批', '2022-07-09 16:34:05'),
        ('JHD-20220709-00002', 16, 'sky', '无', 47988.00, '待一级审批', '2022-07-09 16:34:29');
insert into purchase_returns_sheet_content (id, purchase_returns_sheet_id, pid, quantity, total_price, unit_price, remark)
values  (23, 'JHTHD-20220523-00000', '0000000000400000', 500, 600000.00, 1200.00, 'b'),
        (24, 'JHTHD-20220523-00000', '0000000000400001', 100, 200000.00, 2000.00, 'b'),
        (25, 'JHTHD-20220523-00001', '0000000000400000', 500, 500000.00, 1000.00, 'a'),
        (26, 'JHTHD-20220523-00002', '0000000000400000', 100, 100000.00, 1000.00, 'a'),
        (27, 'JHTHD-20220523-00003', '0000000000400000', 200, 200000.00, 1000.00, 'a'),
        (28, 'JHTHD-20220523-00004', '0000000000400000', 200, 200000.00, 1000.00, 'a'),
        (29, 'JHTHD-20220524-00000', '0000000000400000', 50, 60000.00, 1200.00, 'b'),
        (30, 'JHTHD-20220524-00000', '0000000000400001', 50, 100000.00, 2000.00, 'b'),
        (31, 'JHTHD-20220524-00001', '0000000000400000', 0, 0.00, 1300.00, 'c'),
        (32, 'JHTHD-20220524-00001', '0000000000400001', 50, 140000.00, 2800.00, 'c'),
        (33, 'JHTHD-20220626-00000', '0000000000400000', 1000, 1000000.00, 1000.00, 'a'),
        (34, 'JHTHD-20220627-00000', '0000000000400000', 1000, 1000000.00, 1000.00, 'a'),
        (35, 'JHTHD-20220627-00001', '0000000000400000', 1000, 1000000.00, 1000.00, 'a');
insert into purchase_returns_sheet (id, purchase_sheet_id, operator, state, create_time, total_amount, remark)
values  ('JHTHD-20220523-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:41', 800000.00, '退钱！'),
        ('JHTHD-20220523-00001', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:54', 500000.00, '退钱！！！'),
        ('JHTHD-20220523-00002', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:34:34', 100000.00, '退钱++++'),
        ('JHTHD-20220523-00003', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:39:30', 200000.00, 'mmmmm'),
        ('JHTHD-20220523-00004', 'JHD-20220523-00000', '67', '审批完成', '2022-05-23 23:42:32', 200000.00, 'mmmmk'),
        ('JHTHD-20220524-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-24 01:00:18', 160000.00, null),
        ('JHTHD-20220524-00001', 'JHD-20220523-00002', 'xiaoshoujingli', '待一级审批', '2022-05-24 01:00:34', 140000.00, null),
        ('JHTHD-20220626-00000', 'JHD-20220523-00000', 'cyj', '待一级审批', '2022-06-26 19:52:27', 1000000.00, null),
        ('JHTHD-20220627-00000', 'JHD-20220523-00000', '进货销售人员', '待一级审批', '2022-06-27 20:29:19', 1000000.00, null),
        ('JHTHD-20220627-00001', 'JHD-20220523-00000', '进货销售人员', '待一级审批', '2022-06-27 21:31:47', 1000000.00, null);
insert into promotion_strategy (id, name, discount, reduce, strategy, start, end, gift, gift_num, level)
values  (2, '暑假大促销', 10.00, 520.00, 'reduce', '2022-07-01 00:00:00', '2022-08-31 00:00:00', '', null, 2),
        (3, '618', 6.00, 0.00, 'discount', '2022-06-01 00:00:00', '2022-06-18 00:00:00', null, null, 3),
        (4, '清仓活动', 10.00, 0.00, 'gift', '2022-06-01 00:00:00', '2022-07-31 00:00:00', '0000000000400001', 1, 1);
insert into product (id, name, category_id, type, quantity, purchase_price, retail_price, recent_pp, recent_rp)
values  ('0000000000400000', '戴尔电脑', 4, '戴尔(DELL)Vostro笔记本电脑5410 123色 戴尔成就3500Vostro1625D', 950, 2991.00, 4062.00, 1900.00, 3000.00),
        ('0000000000400001', '小米手机', 4, 'lalalalala', 1450, 2000.00, 3503.00, 3000.00, 4000.00),
        ('0000000000500000', 'intel电脑', 5, 'iphone14maxpro', 0, 6000.00, 10000.00, null, null),
        ('0000000000500001', 'iphone', 5, 'iphone14普通版', 0, 4000.00, 8002.00, null, null),
        ('0000000000500002', '坚果', 5, 'pro3', 0, 2499.00, 3199.00, null, null),
        ('0000000000900003', 'test', 9, 'unknown', 0, 1.00, 2.00, null, null);
insert into post_sheet (id, name, basic_wage, post_wage, type)
values  (1, '库存管理人员', 400.00, 8000.00, '月薪固定薪资制'),
        (2, '进货销售人员', 400.00, 5000.00, '月薪提成制'),
        (3, '财务人员', 400.00, 6500.00, '月薪固定薪资制'),
        (4, '人力资源人员', 400.00, 9000.00, '月薪固定薪资制'),
        (5, '总经理', 400.00, 300000.00, '年薪固定薪资制');
insert into payroll_sheet (id, operator, create_time, employee_id, name, payable_wages, unemployment_insurance, personal_income_tax, housing_provident_fund, actual_amount_issued, bank_card, state)
values  ('GZD-20220709-00000', 'sky', '2022-07-09 21:14:18', 2, 'Sam', 7879.40, 78.79, 86.38, 78.79, 7635.43, '4349219', '待二级审批'),
        ('GZD-20220709-00001', 'sky', '2022-07-09 21:15:03', 1, 'Jam', 8366.40, 83.66, 126.64, 83.66, 8072.43, '434979', '审批失败'),
        ('GZD-20220709-00002', 'sky', '2022-07-09 21:15:08', 4, 'Taylor', 6872.40, 68.72, 56.17, 68.72, 6678.78, '434213179', '待一级审批'),
        ('GZD-20220709-00003', 'sky', '2022-07-09 21:15:54', 5, 'Lana', 9362.40, 93.62, 226.24, 93.62, 8948.91, '43497931231', '待一级审批');
insert into payment_sheet_content (id, account_id, payment_sheet_id, total, remark)
values  (72, 40, 'FKD-20220708-00000', 560.00, ''),
        (73, 39, 'FKD-20220708-00000', 3200.00, null),
        (74, 44, 'FKD-20220708-00001', 3200.00, ''),
        (75, 39, 'FKD-20220708-00001', 2300.00, null),
        (76, 43, 'FKD-20220708-00002', 1.00, '');
insert into payment_sheet (id, customer, operator, state, create_time, total)
values  ('FKD-20220708-00000', 3, '财务人员', '审批完成', null, 3760.00),
        ('FKD-20220708-00001', 13, '财务人员', '待审批', null, 5500.00),
        ('FKD-20220708-00002', 2, '财务人员', '待审批', null, 1.00);
insert into employee_sheet (id, name, phone_num, address, post_id, post_name, bank_card, hiredate)
values  (1, 'Jam', '434636', '南京大学', 1, '库存管理人员', '434979', '2022-03-06'),
        (2, 'Sam', '214636', '北京大学', 2, '进货销售人员', '4349219', '2022-03-02'),
        (3, 'LingLing', '434312', '西安大学', 2, '进货销售人员', '43497921', '2022-03-03'),
        (4, 'Taylor', '4803136', '东京大学', 3, '财务人员', '434213179', '2022-02-06'),
        (5, 'Lana', '432314636', '南京大学', 4, '人力资源人员', '43497931231', '2022-04-06'),
        (6, 'DaMing', '2132434636', '南京大学', 1, '库存管理人员', '43314979', '2022-03-06'),
        (7, 'XiaoHu', '432434636', '东京大学', 5, '总经理', '42134979', '2022-01-06'),
        (8, 'Charles', '412334636', '南京大学', 1, '库存管理人员', '434972319', '2022-02-06'),
        (9, '一一', '16513519567', '上海', 2, '进货销售人员', '165956354545', '2022-05-04');
insert into customer (id, type, level, name, phone, address, zipcode, email, line_of_credit, receivable, payable, operator)
values  (2, '销售商', 4, 'Jack', '15016419864', '南哪儿大学', '515654', '12121@cba.com', 20000000.00, 7561800.00, 1280000.00, 'sky'),
        (3, '供应商', 5, 'cyj', '16316519865', '广东汕头', '515154', '123456789@qq.com', 12.00, 2980.00, -3604.00, 'sky'),
        (12, '销售商', 2, '李勇', '17645646329', '内蒙古', '512534', 'liyong@163.com', 456.00, 334.00, 457.00, '进货销售人员'),
        (13, '销售商', 3, '赵晓', '14465946851', '北京', '515000', 'zhaoxiao123@google.com', 5463.00, 4536.00, 123.00, '进货销售人员'),
        (16, '供应商', 1, '王平', '162134195686', '黑龙江哈尔滨', '561352', '16532615684@qq.com', 12.00, 123.00, 123.00, '销售经理');
insert into collection_sheet_content (id, account_id, collection_sheet_id, total, remark)
values  (102, 39, 'SKD-20220708-00000', 1500.00, '转账条目1'),
        (103, 42, 'SKD-20220708-00000', 5600.00, '转账条目2'),
        (104, 40, 'SKD-20220708-00001', 3500.00, ''),
        (105, 42, 'SKD-20220708-00001', 1622.00, null),
        (106, 43, 'SKD-20220708-00002', 6200.00, ''),
        (107, 42, 'SKD-20220708-00003', 4000.00, '');
insert into collection_sheet (id, customer, operator, state, create_time, total)
values  ('SKD-20220708-00000', 2, '财务人员', '审批完成', null, 7100.00),
        ('SKD-20220708-00001', 13, '财务人员', '审批失败', null, 5122.00),
        ('SKD-20220708-00002', 16, '财务人员', '待审批', null, 6200.00),
        ('SKD-20220708-00003', 3, '财务人员', '待审批', null, 4000.00);
insert into category (id, name, parent_id, is_leaf, item_count, item_index)
values  (1, '商品', 0, 0, 0, 0),
        (2, '电子产品', 1, 0, 0, 0),
        (3, '生活用品', 1, 1, 0, 0),
        (4, '电脑', 2, 1, 2, 2),
        (5, '手机', 2, 1, 3, 3),
        (6, 'ipad', 2, 1, 0, 0),
        (7, '平板电脑', 2, 1, 4, 4);
insert into attendance_record_sheet (emp_id, time)
values  (4, '2022-06-01 08:40:12'),
        (4, '2022-06-02 15:30:12'),
        (4, '2022-06-04 12:01:32'),
        (4, '2022-06-06 09:32:56'),
        (4, '2022-06-07 11:01:32'),
        (4, '2022-06-08 19:32:14'),
        (4, '2022-06-09 10:32:45'),
        (4, '2022-06-10 14:21:25'),
        (4, '2022-06-12 07:34:56'),
        (4, '2022-06-13 02:01:17'),
        (4, '2022-06-14 03:52:35'),
        (4, '2022-06-15 18:31:55'),
        (4, '2022-06-18 19:42:45'),
        (4, '2022-06-19 21:11:57'),
        (4, '2022-06-20 12:52:44'),
        (4, '2022-06-22 14:06:25'),
        (4, '2022-06-23 20:34:55'),
        (4, '2022-06-25 15:12:21'),
        (4, '2022-06-26 12:28:13'),
        (4, '2022-06-27 22:29:18'),
        (4, '2022-06-28 13:57:09'),
        (4, '2022-06-29 21:42:35'),
        (4, '2022-07-07 18:38:46'),
        (4, '2022-07-08 11:10:55'),
        (4, '2022-07-09 17:44:32'),
        (14, '2022-06-07 11:01:32'),
        (14, '2022-06-08 19:32:14'),
        (14, '2022-06-09 10:32:45'),
        (14, '2022-06-10 14:21:25'),
        (14, '2022-06-12 07:34:56'),
        (14, '2022-06-13 02:01:17'),
        (14, '2022-06-14 03:52:35'),
        (14, '2022-06-15 18:31:55'),
        (14, '2022-06-18 19:42:45'),
        (14, '2022-06-19 21:11:57'),
        (14, '2022-06-20 12:52:44'),
        (14, '2022-06-22 14:06:25'),
        (14, '2022-06-23 20:34:55'),
        (14, '2022-06-25 15:12:21'),
        (14, '2022-06-26 12:28:13'),
        (14, '2022-06-27 22:29:18'),
        (14, '2022-06-28 13:57:09'),
        (14, '2022-06-29 21:42:35'),
        (14, '2022-07-01 03:52:35'),
        (14, '2022-07-03 02:01:17'),
        (14, '2022-07-04 07:34:56'),
        (14, '2022-07-05 14:21:25'),
        (14, '2022-07-06 19:32:14'),
        (14, '2022-07-07 11:01:32'),
        (15, '2022-06-07 11:01:32'),
        (15, '2022-06-08 19:32:14'),
        (15, '2022-06-09 10:32:45'),
        (15, '2022-06-10 14:21:25'),
        (15, '2022-06-12 07:34:56'),
        (15, '2022-06-13 02:01:17'),
        (15, '2022-06-14 03:52:35'),
        (15, '2022-06-15 18:31:55'),
        (15, '2022-06-18 19:42:45'),
        (15, '2022-06-19 21:11:57'),
        (15, '2022-06-20 12:52:44'),
        (15, '2022-06-22 14:06:25'),
        (15, '2022-06-23 20:34:55'),
        (15, '2022-06-25 15:12:21'),
        (15, '2022-06-26 12:28:13'),
        (15, '2022-06-27 22:29:18'),
        (15, '2022-06-28 13:57:09'),
        (15, '2022-06-29 21:42:35'),
        (15, '2022-07-01 03:52:35'),
        (15, '2022-07-03 02:01:17'),
        (15, '2022-07-04 07:34:56'),
        (15, '2022-07-05 14:21:25'),
        (15, '2022-07-06 19:32:14'),
        (15, '2022-07-07 11:01:32'),
        (15, '2022-07-07 18:25:40'),
        (16, '2022-06-07 11:01:32'),
        (16, '2022-06-08 19:32:14'),
        (16, '2022-06-09 10:32:45'),
        (16, '2022-06-10 14:21:25'),
        (16, '2022-06-12 07:34:56'),
        (16, '2022-06-13 02:01:17'),
        (16, '2022-06-14 03:52:35'),
        (16, '2022-06-15 18:31:55'),
        (16, '2022-06-18 19:42:45'),
        (16, '2022-06-19 21:11:57'),
        (16, '2022-06-20 12:52:44'),
        (16, '2022-06-22 14:06:25'),
        (16, '2022-06-23 20:34:55'),
        (16, '2022-06-25 15:12:21'),
        (16, '2022-06-26 12:28:13'),
        (16, '2022-06-27 22:29:18'),
        (16, '2022-06-28 13:57:09'),
        (16, '2022-06-29 21:42:35'),
        (16, '2022-07-01 03:52:35'),
        (16, '2022-07-03 02:01:17'),
        (16, '2022-07-04 07:34:56'),
        (16, '2022-07-05 14:21:25'),
        (16, '2022-07-06 19:32:14'),
        (16, '2022-07-07 11:01:32'),
        (16, '2022-07-08 16:33:32'),
        (17, '2022-06-07 11:01:32'),
        (17, '2022-06-08 19:32:14'),
        (17, '2022-06-09 10:32:45'),
        (17, '2022-06-10 14:21:25'),
        (17, '2022-06-12 07:34:56'),
        (17, '2022-06-13 02:01:17'),
        (17, '2022-06-14 03:52:35'),
        (17, '2022-06-15 18:31:55'),
        (17, '2022-06-18 19:42:45'),
        (17, '2022-06-19 21:11:57'),
        (17, '2022-06-20 12:52:44'),
        (17, '2022-06-22 14:06:25'),
        (17, '2022-06-23 20:34:55'),
        (17, '2022-06-25 15:12:21'),
        (17, '2022-06-26 12:28:13'),
        (17, '2022-06-27 22:29:18'),
        (17, '2022-06-28 13:57:09'),
        (17, '2022-06-29 21:42:35'),
        (17, '2022-07-01 03:52:35'),
        (17, '2022-07-03 02:01:17'),
        (17, '2022-07-04 07:34:56'),
        (17, '2022-07-05 14:21:25'),
        (17, '2022-07-06 19:32:14'),
        (17, '2022-07-07 11:01:32'),
        (17, '2022-07-08 16:26:12');
insert into account (id, name, balance)
values  (1, '工资发方', 99999999.00),
        (39, 'Lola', 9999999.00),
        (40, 'Mickle', 9141299.00),
        (42, '小红', 95565952.00),
        (43, '小明', 6590655.00),
        (44, '李四', 3651111.00);