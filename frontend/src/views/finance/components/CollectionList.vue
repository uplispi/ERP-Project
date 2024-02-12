<template>
  <div class="card">
    <el-card v-for="(item, index) in list" :index="item.index" :key="item.id" shadow="hover">
      <template #header>
        <el-row>
          <el-col :span="18">
            <span><strong>id: </strong>{{item.id}}</span>
            <el-button v-if="authorization() === 1" style="margin-left: 10px"
                       type="success" icon="el-icon-check" circle size="mini" @click="approval(item.id)"></el-button>
            <el-button v-if="authorization() === 1"
                       type="danger" icon="el-icon-close" circle size="mini" @click="deny(item.id)"></el-button>
            <span style="margin-left: 10px">
              <el-tag v-if="type === 3" effect="dark" type='success'>审核通过</el-tag>
              <el-tag v-if="type === 4" effect="dark" type='danger'>审核未通过</el-tag>
            </span>

          </el-col>
          <el-col :span="6">
            <el-button class="button" type="text"
                       v-if="!showAll[index]"
                       @click="changeState(index)">
              展开
            </el-button>
            <el-button class="button" type="text"
                       v-if="showAll[index]"
                       @click="changeState(index)">
              收起</el-button>
          </el-col>

        </el-row>
      </template>
      <div>
        <el-row>
          <el-col :span="3">
            <span><strong>id: </strong>{{item.id}}</span>
          </el-col>
          <el-col :span="6">
            <span><strong>操作员: </strong>{{item.operator}}</span>
          </el-col>
          <el-col :span="4">
            <span><strong>客户id: </strong>{{item.customer}}</span>
          </el-col>
          <el-col :span="6">
            <span><strong>总额合计: </strong>{{item.total}}(元)</span>
          </el-col>
        </el-row>

        <div v-if="showAll[index]" style="margin-top: 15px">
          <div style="margin-bottom: 15px"><strong>详细转账记录:</strong></div>
          <el-table
              :data="item.collectionSheetContentList"
              stripe
              style="width: 100%"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
            <el-table-column
                prop="id"
                label="id"
                width="100">
            </el-table-column>
            <el-table-column
                prop="accountId"
                label="银行账户id"
                width="180">
            </el-table-column>
            <el-table-column
                prop="total"
                label="转账金额(元)"
                width="200">
            </el-table-column>
            <el-table-column
                prop="remark"
                label="备注">
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-card>


  </div>
</template>

<script>
  import {approvalCollectionSheet} from "@/network/finance";

  export default {
    name: "CollectionList",
    props: {
      list: Array,
      type: Number,
    },
    data(){
      return{
        showAll: [],
      }
    },
    mounted() {
      this.showAll = new Array(this.list.length).fill(false)
    },
    methods:{
      changeState(index){
        this.$set(this.showAll, index, !this.showAll[index])
      },
      // 审批权限
      authorization(){
        // 总经理审批收款单
        if (this.type === 1 && (sessionStorage.getItem('role') === 'GM'|| sessionStorage.getItem('role') === 'ADMIN')) {
          return 1
        }
      },

      // 审批通过
      approval(id){
        let config = {
          params: {
            collectionSheetId: id,
            state: 'SUCCESS',
          }
        }
        approvalCollectionSheet(config).then(_res => {
          this.$emit("refresh")
          this.$message({
            message: '操作成功!',
            type: 'success'
          })
        })
      },

      // 审批不通过
      deny(id){
        let config = {
          params: {
            collectionSheetId: id,
            state: 'FAILURE',
          }
        }
        approvalCollectionSheet(config).then(_res => {
          this.$emit("refresh")
          this.$message({
            message: '操作成功!',
            type: 'success'
          })
        })
      }
    }

  }

</script>

<style lang="scss" scoped>
.card {
  width: 80%;
  margin: 0 auto;
  .button {
    float: right;
    padding: 3px 0
  }
}
.el-card {
  margin-bottom: 20px;
  background: #EEF7F2;
}
</style>