<template>
  <Layout title="库存赠品单">
    <Title title="库存赠品单审批"></Title>
    <div class="body">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane label="待审批" name="PENDING_LEVEL_1">
          <div v-if="pendingLevel1List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <inventory-gift-sheet-list :list="pendingLevel1List" :type="1" @refresh="getWarehouseGiftSheet()"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <inventory-gift-sheet-list :list="successList" :type="3"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <inventory-gift-sheet-list :list="failureList" :type="4"/>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import InventoryGiftSheetList from "@/views/inventory/components/InventoryGiftSheetList";
import {getWarehouseGiftSheetByState} from "@/network/warehouse";
export default {
  name: "InventoryGiftSheetView",
  components: {Layout, Title, InventoryGiftSheetList},
  data(){
    return {
      activeName: 'PENDING_LEVEL_1',
      inventoryGiftSheetList: [],
      pendingLevel1List: [],
      successList: [],
      failureList: [],
    }
  },
  mounted() {
      getWarehouseGiftSheetByState({}).then(_res => {
        this.inventoryGiftSheetList = _res.result;
        this.pendingLevel1List = this.inventoryGiftSheetList.filter(item => item.state === '待审批')
        this.successList = this.inventoryGiftSheetList.filter(item => item.state === '审批完成')
        this.failureList = this.inventoryGiftSheetList.filter(item => item.state === '审批失败')
      })
  },
  methods: {
    getWarehouseGiftSheet(){
      getWarehouseGiftSheetByState({}).then(_res => {
        this.inventoryGiftSheetList = _res.result;
        this.pendingLevel1List = this.inventoryGiftSheetList.filter(item => item.state === '待审批')
        this.successList = this.inventoryGiftSheetList.filter(item => item.state === '审批完成')
        this.failureList = this.inventoryGiftSheetList.filter(item => item.state === '审批失败')
      })
    }
  }
}
</script>

<style scoped>

</style>