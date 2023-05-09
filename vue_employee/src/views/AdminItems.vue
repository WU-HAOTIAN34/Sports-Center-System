<template>
    <div>
        <a-card :bordered="false" class="header-solid mb-24" :bodyStyle="{padding: 0, paddingTop: '16px'}">
            <template #title>
                <h5 class="font-semibold">Equipment</h5>
            </template>
            <div class="mx-25">
                <a-row type="flex" :gutter="24">
                    <a-col :span="24" :md="8">
                        <a-select v-model="pageSize2" @change="onPageSize2Change" style="width: 70px">
                            <a-select-option value="5">5</a-select-option>
                            <a-select-option value="10">10</a-select-option>
                            <a-select-option value="15">15</a-select-option>
                            <a-select-option value="20">20</a-select-option>
                            <a-select-option value="25">25</a-select-option>
                        </a-select>
                        <label for="" class="ml-10">entries per page</label>
                    </a-col>
                    <a-col :span="24" :md="8" class="text-right">
                        <a-input-search placeholder="input search text" style="max-width: 200px;" v-model="query" @change="onSearchChange" />
                    </a-col>
                </a-row>
            </div>
            <a-table class="mt-20" :columns="columns" :data-source="data" :pagination="{pageSize: pageSize2,}">
                <template slot="operation" slot-scope="text, record">
                    <a @click="jump(text,record)">View details</a>
                </template>
            </a-table>
        </a-card>
    </div>

</template>

<script>
import { getAllItems } from '@/api/item'
const stringSorter = function(a, b, attr) {
    if (a[attr] < b[attr]) { return -1 }
    if (a[attr] > b[attr]) { return 1 }
    return 0
}
const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        sorter: (a, b) => a.id - b.id,
        sortDirections: ['descend', 'ascend']
    },
    {
        title: 'Name',
        dataIndex: 'itemName',
        sorter: (a, b) => stringSorter(a, b, 'itemName'),
        sortDirections: ['descend', 'ascend']
    },
    {
        title: 'Price',
        dataIndex: 'price',
        sorter: (a, b) => a.price - b.price,
        sortDirections: ['descend', 'ascend']
    },
    {
        title: 'operation',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' }
    }
]

export default {
    components: {
    },
    data() {
        return {
            columns,
            data: [],
            pageSize: 10,
            pageSize2: 10,
            query: ''

        }
    },
    beforeCreate () {
        getAllItems().then((response) => {
            this.data = response.data.data
            console.log(this.data)
        })
    },
    methods: {
        jump(text, record) {
            this.$router.push({
                path: '/admin/items/detail',
                query: {
                    id: record.id
                }
            })
        },

        // Event handler for second table's size change.
        onPageSize2Change() {
            this.pageSize2 = parseInt(this.pageSize2)
        },

        // Event handler for second table's search.
        onSearchChange() {
            if (this.query.length > 0) {
                this.data = this.data.filter((row) => {
                    for (const key in row) {
                        if (row[key]
                            .toString()
                            .toLowerCase()
                            .includes(this.query.trim().toLowerCase())) { return true }
                    }
                    return false
                })
            } else {
                getAllItems().then((response) => {
                    this.data = response.data.data
                    console.log(this.data)
                })
            }
        }

    }
}

</script>

<style lang="scss">
</style>
