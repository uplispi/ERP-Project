model 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

**products**
前端
api
const SALE_DETAIL_PRODUCTS =  '/salesDetail/product';
network
D:/学习/大二下/0软工二/erp/erp-frontend/src/network/product/index.js
findAllProduct

后端



**customers**
前端

network
D:/学习/大二下/0软工二/erp/erp-frontend/src/network/purchase/index.js
getAllCustomer()

后端

dao

maybatis

**operators**

api 
const SALE_DETAIL_OPERATOR = '/salesDetail/operators'
network
export const findAllOperators = config => request._get(testAPI.SALE_DETAIL_OPERATOR,config)


controll

get /operators

model
SalesmanVO{name:String}
SalesmanPO{name:String}
service findAllOperators():List<SalesmanVO>

dao findAllOperators():List<SalesmanPO>

xml

```

```

## 查看经营历程表

需求：筛选：日期，单据类型

前端：单据样式参考saleView
界面：
筛选：日期，单据类型//日期要实现吗？
根据：这两个条件获取数据
红冲先不做


（查看一段时间里的所有单据，单据分为：
1. 销售类单据（销售出货单，销售退货单） 
2. 进货类单据（进货单，进货退货单） 
3. 财务类单据（付款单，收款单，现金费用单，工资单）
4. 库存类单据（报溢单，报损单，赠送单）

显示对应的单据
导出ecxel：需要支持post传递参数 用postbody 注意前端不需要params而是config
需要的VO， 时间， 类型
前端表驱动传递一个表名

mybaits需要自己实现所有的selectbytime，PO


所有属性的PO
根据sheet_name选择表单，根据data选择时间

销售出货单



销售退货单

进货单

进货退货单

付款单

收款单

现金费用单

工资单

后端

前端：
每个单据对应一个api （bytime）

7个单据

万能PO，用于集合所有的字段

七个对应的PO VO
七个对应的表格

下载传过来参数

1、工资范围在1-5000元之间的，包括5000元，适用个人所得税税率为0%;
2、工资范围在5000-8000元之间的，包括8000元，适用个人所得税税率为3%;
3、工资范围在8000-17000元之间的，包括17000元，适用个人所得税税率为10%;
4、工资范围在17000-30000元之间的，包括30000元，适用个人所得税税率为20%;
5、工资范围在30000-40000元之间的，包括40000元，适用个人所得税税率为25%;
6、工资范围在40000-60000元之间的，包括60000元，适用个人所得税税率为30%;
7、工资范围在60000-85000元之间的，包括85000元，适用个人所得税税率为35%;
8、工资范围在85000元以上的，适用个人所得税税率为45%


**数据库**
员工编号->姓名->银行账户 employee_sheet
应发工资->(基本工资+提成-缺勤) post_salary_sheet
实发工资（应发工资 - 税收（1，2（1%），3（5%）） - ）
（包含单据编号、员工编号、姓名、银行账户信息、应发工资、扣除税款（个人所得税、失业保险、住房公积金）、实发金额）
|工资单|payroll_sheet||
|---|---|---|
|单据编号|id|varchar(31) GZD前缀使用ID生成器|
|创建时间|create_time|datetime|
|员工编号|employee_id|int|
|应发工资|payable_wages|decimal(10,2)|
|失业保险|unemployment_insurance|decimal(10,2)|
|个人所得税|personal_income_tax|deciaml(10,2)|
|住房公积金|housing_provident_fund|deciaml(10,2)|
|实发金额|actual_amount_issued|deciaml(10,2)|
|状态|state|varchar(31)|


后端
```sql
create table payroll_sheet
(
    id                     varchar(31)    not null comment '工资单号：GZD-yyyymmdd-xxxx'
        primary key,
    operator varchar(31) not null comment '操作员'，
    create_time            datetime       not null comment '创建时间',
    employee_id            int            not null comment '员工编号',
    payable_wages          decimal(10, 2) not null comment '应发工资',
    unemployment_insurance decimal(10, 2) not null comment '失业保险',
    personal_income_tax    decimal(10, 2) not null comment '个人所得税',
    housing_provident_fund decimal(10, 2) not null comment '住房公积金',
    actual_amount_issued   decimal(10, 2) not null comment '实发金额',
    state                  varchar(31)    null comment '状态'
)
    collate = utf8mb4_general_ci;

```

```sql
use `seec_erp`
create table year_end_bonus_sheet(
    'year' int not null comment '年份',
    'employId' int not null comment '员工id',
    'year_end_bonus' decimal(10.2) not null comment '年终奖'
    primary key(`year`, `employId`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
```

Modle
PO

YearEndBonusPO{
    Integer year;//年份
    Integer employId;//员工id
    BigDecimal yearEndBonus;//年薪
}

PayrollPO{
    private Date createTime;//创建日期
    private String operator;//操作员
    private String id;//单据编号
    private String name;//员工名join
    private Integer employeeId;//员工id
    private String bankCard;////银行卡join
    private BigDecimal payableWages;//应发工资
    private BigDecimal unemploymentInsurance;//失业保险
    private BigDecimal personalIncomeTax;//个人所得税
    private BigDecimal housingProvidentFund;//住房公积金
    private BigDecimal actualAmountIssued;//实发金额
    private SaleSheetState state;//状态
    private BigDecimal yearEndBonus;//年终奖
    private Boolean isMonth;//是否月薪

}


VO
PayrollVO{
    private Date createTime;//创建日期
    private String operator;//操作员
    private String id;//单据编号
    private String name;//员工名join
    private Integer employeeId;//员工id
    private String bankCard;////银行卡join
    private BigDecimal payableWages;//应发工资
    private BigDecimal unemploymentInsurance;//失业保险
    private BigDecimal personalIncomeTax;//个人所得税
    private BigDecimal housingProvidentFund;//住房公积金
    private BigDecimal actualAmountIssued;//实发金额
    private SaleSheetState state;//状态
    private BigDecimal yearEndBonus;//年终奖
    private Boolean isMonth;//是否月薪
}

YearEndBonusVO{
    Integer year;//年份
    Integer employId;//员工id
    BigDecimal yearEndBonus;//年薪
}



**control**
PayrollControl

/payroll
查询所有的表单queryAll /queryAll 用到 PayrollPO， PayrollVO


获取所有员工工资参数getDetail（） 用到PayrollPO， PayrollVO



新建表单（默认生成一些数字）显示内容：
/getDetail
/add
添加表单add(PayrollVO):参数不需要id 用到 service添加一些参数


/update
编辑表单update(PayrollVO) 参数需要id 用到PayrollPO，

<!-- /updateState
改变表单的状态upadteState() -->//



<!-- /delete
删除表单delete(表单id) -->

**service**
PayrollServiceImpl{
    queryAll():List<PayrollVO>--dao.queryAll()


    //年终奖考虑（12月？） 月薪年薪（12月）基本工资还是提成
    getDetail():List<PayrollVO>//表驱动策略模式dao.???
    //12月，年终奖，年薪
    //考情（总经理不需要），基本工资+提成/月薪计算应发工资
    ```js
        for(员工表+职业表)
        int pay
        if 12yue 查询年终奖 pay+
          if  年薪  pay+
        else bushi12yue 
            查询工资表
            if yuexin pay+
            if nianxin pay+
        if 不是zongjingli 查询考勤表

    ```


    add(PayrollVO)->dao.add() id生成器
    update(PayrollPO)->dao.update()
}

**dao**
PayrollDao{
    queryAll():List<PayrollPO>

    getDetail():List<PayrollPO>//连接员工表，职业表，需要悬浮元组
    getBouns(year, employid)
    add(PayrollPO)
    update(PayrollPO)
    maxId()找到最大的id
}





前端
api
const PAYROLL_QUERY_ALL = "/api/payroll/queryAll"//查询所有表单
const PAYROLL_ADD = "/api/payroll/add"//添加表单
const PAYROLL_GET_DETAIL = "api/payroll/getDetail"//获取工资细节
cosnt PAYROLL_UPDATE = "api/payroll/update"//更新表单

const PAYROLL_UPDATE_STATE = "api/payroll/state"//更新状态
const PAYROLL_DELETE = "api/payroll/delete"//删除


network
export const queryAll = config=>reuqest._get(testAPI.PAYROLL_QUERY_ALL, config)
export const add = config=>request._post(testAPI.PAYROLL_ADD, config)
export const getDetail = config=>request._post(testAPI.PAYROLL_GET_DETAIL, config)
export const update = config=>request._get(testAPI.PAYROLL_GET_DETAIL, config)
export const updateState =config => request._post(testAPI.PAYROLL_UPDATE_STATE, config)
export const deletePayroll = config => request._delete(testAPI.PAYROLL_DELETE, config)


启动 
加载所有单据queryAll()
加载所有员工的工资详细情况getDetail()


新建表单
handleAdd()->add(PayrollVO)->queryAll() 注意参数

编辑更新表单
handleUpdate()->update(PayrollVO)->queryAll()

bug：可以给一个员工添加多个工资单
UI:
对话框
```
表id
可选择
private Integer employeeId;//员工id
name;员工名

固定
    BigDecimal payableWages;//应发工资
    BigDecimal unemploymentInsurance;//失业保险
    BigDecimal personalIncomeTax;//个人所得税
    BigDecimal housingProvidentFund;//住房公积金
    BigDecimal actualAmountIssued;//实发金额

按钮取消
```






