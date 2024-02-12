<template>
  <Layout>
    <Title title="商品管理"></Title>
    <div style="margin: 10px auto;">
      <el-button type="primary" @click="addProduct()">新增商品</el-button>
    </div>
    <div style="margin: 10px auto;">
      <el-input v-model="search" placeholder="输入关键字搜索" />
    </div>
    <div class="commodity-body">
      <el-table
        :data="commodityList.filter(data => !search || data.name.includes(search) || data.id.includes(search)) || data.type.includes(search)"
        style="width: 100%;" :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }">
        <el-table-column fixed prop="id" label="编号" width="100">
        </el-table-column>
        <el-table-column prop="name" label="名称" width="150">
        </el-table-column>
        <el-table-column prop="type" label="型号" width="200">
        </el-table-column>
        <el-table-column prop="quantity" label="库存数量" width="120">
        </el-table-column>
        <el-table-column prop="purchasePrice" label="进价" width="100">
        </el-table-column>
        <el-table-column prop="retailPrice" label="零售价" width="100">
        </el-table-column>
        <el-table-column prop="recentPp" label="最近进价" width="100">
        </el-table-column>
        <el-table-column prop="recentRp" label="最近零售价" width="100">
        </el-table-column>
        <el-table-column label="操作">

          <template slot-scope="scope">
            <div>
              <el-button @click="editProduct(scope.row.id)" type="text" size="small">
                编辑
              </el-button>
            </div>
            <div>
              <el-button @click="deleteProduct(scope.row.id)" type="text" size="small">
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="新增商品" :visible.sync="addDialogVisible" width="30%" @close="close()">
      <el-form :model="addForm" :label-width="'100px'" size="mini" ref="addForm">
        <el-form-item label="所属分类" prop="categoryId" :rules="{
          validator: (rule, value, callback) => {
            if (value !== 0 && !value) {
              callback('请选择分类')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-select v-model="addForm.categoryId">
            <el-option v-for="item in classificationList" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名 称" prop="name" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value.trim().length < 3) {
              callback('名称至少需要三个字符')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model="addForm.name" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="类 型" prop="type" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value.trim().length < 3) {
              callback('名称至少需要三个字符')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model="addForm.type" type="textarea" :rows="2" placeholder="请输入商品类型"></el-input>
        </el-form-item>
        <el-form-item label="进价" prop="purchasePrice" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value < 0) {
              callback('商品进价必须是正数')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model.number="addForm.purchasePrice" placeholder="请输入商品进价" type="number" prop="purchasePrice"></el-input>
        </el-form-item>
        <el-form-item label="零售价" prop="retailPrice" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value < 0) {
              callback('商品零售价必须是正数')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model.number="addForm.retailPrice" placeholder="请输入商品零售价" type="number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleAdd(false)">取 消</el-button>
        <el-button type="primary" @click="handleAdd(true)">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改商品信息" :visible.sync="editDialogVisible" width="30%" @close="close()">
      <el-form :model="editForm" :label-width="'100px'" size="mini" ref="editForm">
        <el-form-item label="名 称" prop="name" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value.trim().length < 3) {
              callback('名称至少需要三个字符')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model="editForm.name" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="类 型"  prop="type" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value.trim().length < 3) {
              callback('类型至少需要三个字符')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model="editForm.type" type="textarea" :rows="2" placeholder="请输入商品类型"></el-input>
        </el-form-item>

        <el-form-item label="进价" prop="purchasePrice" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value < 0) {
              callback('商品进价必须是正数')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model.number="editForm.purchasePrice" placeholder="请输入商品进价" type="number" prop="purchasePrice"></el-input>
        </el-form-item>
        <el-form-item label="零售价" prop="retailPrice" :rules="{
          validator: (rule, value, callback) => {
            if (!value || value < 0) {
              callback('商品零售价必须是正数')
            }
            else {
              callback()
            }
          },
          trigger: ['blur', 'change']
        }">
          <el-input v-model.number="editForm.retailPrice" placeholder="请输入商品零售价" type="number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {
  getAllCommodityClassification,
  getAllCommodity,
  createCommodity,
  updateCommodity,
  deleteCommodity
} from "../../network/commodity";
export default {
  components: {
    Layout,
    Title
  },
  data() {
    return {
      commodityList: [],
      search: '',
      classificationList: [],
      addDialogVisible: false,
      addForm: {
        categoryId: '',
        name: '',
        type: '',
        purchasePrice: '',
        retailPrice: ''
      },
      editDialogVisible: false,
      editForm: {},
    }
  },
  mounted() {
    this.getAll();
    getAllCommodityClassification({}).then(_res => {
      this.classificationList = _res.result;
      var tempList = this.classificationList.map((item, index) => {
        return Object.assign({}, { id: item.id, name: item.name })
      })
      this.classificationList = tempList;
    }).catch(err => {
      this.$message({
        type: 'error',
        message: '获取商品分类列表失败!' + err
      })
    })
  },
  methods: {
    getAll() {
      getAllCommodity({}).then(_res => {
        this.commodityList = _res.result;
        this.commodityList.forEach((item) => {
          if (item.recentPp == null) {
            item.recentPp = '暂无';
          }
          if (item.recentRp == null) {
            item.recentRp = '暂无';
          }
        })
      }).catch(err => {
        this.$message({
          type: 'error',
          message: '获取商品列表失败!' + err
        })
      })
    },
    addProduct() {
      this.addDialogVisible = true;
    },
    handleAdd(type) {
      if (type === false) {
        this.addDialogVisible = false;
        this.addForm = {};
      } else if (type === true) {
        console.log('addForm=>', this.addForm)
        this.$refs['addForm'].validate((vaild) => {
          if (vaild) {
            createCommodity(this.addForm).then(_res => {
              if (_res.code === "B0001" || _res.code === "B0002") {
                this.$message({
                  type: 'error',
                  message: _res.msg
                });
              } else {
                this.$message({
                  type: 'success',
                  message: '新增成功!'
                });
                this.addForm = {};
                this.addDialogVisible = false;
                this.getAll();
              }
            })
          }
          else {
            this.$message.error('请检查表单项')
          }
        })

      }
    },
    editProduct(id) {
      this.editForm = JSON.parse(JSON.stringify(this.commodityList.filter(x => x.id === id)[0]));
      if (this.editForm.recentPp === '暂无') {
        this.editForm.recentPp = null;
      }
      if (this.editForm.recentRp === '暂无') {
        this.editForm.recentRp = null;
      }
      this.editDialogVisible = true;
    },
    handleEdit(type) {
      if (type === false) {
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        this.$refs['editForm'].validate(vaild=>{
          console.log('vaild=>', vaild)
          if(vaild){
            updateCommodity(this.editForm).then(_res => {
              if (_res.code === 'B0003') {
                this.$message({
                  type: 'error',
                  message: _res.msg
                })
              } else {
                this.$message({
                  type: 'success',
                  message: '修改成功！'
                })
                this.editForm = {};
                this.editDialogVisible = false;
                this.getAll();
              }
            })
          }
          else{
            this.$message.error('请检查表单项')
          }
        })
        
      }
    },
    deleteProduct(id) {
      let config = {
        params: {
          id: id
        }
      };
      this.$confirm('是否要删除该商品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCommodity(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getAll();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    close() {
      this.addForm = {};
      this.editForm = {};
    }
  }
};
</script>

<style scoped>
.commodity-body {
  margin: 0 auto;
}

.top {
  display: flex;
}

.left {
  float: left;
  width: 80%;
}

.right {
  flex: 1;
}
</style>
