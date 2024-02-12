<template>
    <div class="card">
        <el-card v-for="item in sheets"  :key="item[0][0][value]" shadow="hover">
            <div>
                <el-row v-for="item1 in item" :key="item1">
                    <el-col :span="3" v-for="item2 in item1" :key="item2">
                        <span><strong>{{item2.name}}</strong>{{ item2.value }}</span>
                    </el-col>
                </el-row>
            </div>
        </el-card>
    </div>
</template>

<script>
export default {
    name: 'BusinessHistoryList',
    props: {
        props:Array,
        names:Array,
        list: Array,
        slice: Number
    },
    data() {
        return {
            sheets:[]
        }
    },
    mounted() {
        const list = [
            {
                "id": "JHD-20220523-00000",
                "supplier": 1,
                "operator": "xiaoshoujingli",
                "remark": "a",
                "totalAmount": 1000000,
                "state": "审批完成",
                "createTime": "2022-05-23T15:13:59.000+00:00"
            },
            {
                "id": "JHD-20220523-00001",
                "supplier": 1,
                "operator": "xiaoshoujingli",
                "remark": "b",
                "totalAmount": 2200000,
                "state": "审批完成",
                "createTime": "2022-05-23T15:14:34.000+00:00"
            },
            {
                "id": "JHD-20220523-00002",
                "supplier": 1,
                "operator": "xiaoshoujingli",
                "remark": "c",
                "totalAmount": 3450000,
                "state": "审批完成",
                "createTime": "2022-05-23T15:15:57.000+00:00"
            },
            {
                "id": "JHD-20220524-00002",
                "supplier": 1,
                "operator": "xiaoshoujingli",
                "remark": null,
                "totalAmount": 1650000,
                "state": "审批完成",
                "createTime": "2022-05-23T17:02:04.000+00:00"
            }
        ]
        console.log(this.list)
        const sheets = []
        for (let i = 0; i < list.length; i++) {
            const item = []
            for (let j = 0; j < list.length; j++) {
                item.push({ name: this.names[j], value: list[i][this.props[j]] })
            }
            const showItem = []
            let k = 0
            while (k < item.length) {
                showItem.push(item.slice(k, k + this.slice))
            }
            sheets.push(showItem)
        }
        console.log(sheets)
        this.sheets = sheets
    },
    methods: {
        
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