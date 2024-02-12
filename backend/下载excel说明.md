后端

**controller**
注意@Postmapping以及后面的参数  `@PostMapping(value = "url", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)`如下
```java
    @PostMapping(value = "/warehouse/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER})
    //函数参数和thorw照着写
    public void getWarehouseCountingExcel(HttpServletResponse response) throws UnsupportedEncodingException {、

        //获取excel对象
        SXSSFWorkbook wb = warehouseService.warehouseCountingExcel();

        //发送blob文件流 参数 response照写， wb是你的ecxel对象， 第三个参数是文件名，不需要后缀
        Response.buildBlob(response, wb, "库存盘点");
    }
```
**service**
生成一个excel对象，调用`ExcelGenerator.getHSSFWorkbook(title, values);`即可，titile是一维String数组，values是二维String数组。
另外使用ExcelGenerator.getString()而不是toString()，可以防止null.toString()的问题
```java
 /**
     * 库存盘点
     * 盘点的是当天的库存快照，包括当天的各种商品的
     * 导出Excel:名称，型号，库存数量，库存均价，批次，出厂日期
     *
     */
    @Override
    public SXSSFWorkbook warehouseCountingExcel() {
        List<WarehouseCountingVO> warehouseCountingVOS =  warehouseCounting();
        String[] title = new String[]{"名称","型号","库存数量","库存均价","批次","出厂日期"};
        String[][] values = new String[warehouseCountingVOS.size()][title.length];
        int i = 0;
        for (WarehouseCountingVO warehouseCountingVO : warehouseCountingVOS) {
            values[i][0]=ExcelGenerator.getString(warehouseCountingVO.getProduct().getName());
            values[i][1]=ExcelGenerator.getString(warehouseCountingVO.getProduct().getType());
            values[i][2]=ExcelGenerator.getString(warehouseCountingVO.getQuantity());
            values[i][3]=ExcelGenerator.getString(warehouseCountingVO.getPurchasePrice());
            values[i][4]=ExcelGenerator.getString(warehouseCountingVO.getBatchId());
            values[i][5]=ExcelGenerator.getString(warehouseCountingVO.getProductionDate());
            i++;
        }
        return ExcelGenerator.getHSSFWorkbook(title, values);
    }

```