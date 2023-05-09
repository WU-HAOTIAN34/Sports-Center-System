<!--
	This is the Invoice page, it uses the dashboard layout in:
	"./layouts/Dashboard.vue" .
 -->

<template>

    <div>

        <a-card id="printContent" :bordered="false" class="card-invoice header-solid mb-24 mx-auto my-50" style="max-width: 800px;">
            <template #title>
                <img src="images/logo-ct-black.png" class="brand">
            </template>
            <a-row :gutter="[24]" type="flex" class="invoice-header">
                <a-col :span="24" :md="8">
                    <h6 class="font-semibold">
                        Southwest Jiaotong University, Chengdu, Sichuan, China
                    </h6>
                </a-col>
                <a-col :span="24" :md="8" class="ml-auto text-right">
                    <h6 class="font-semibold">
                        Billed to: {{ order.data.userName}}
                    </h6>
                    <p>
                        {{ order.data.phoneNum }}
                    </p>
                </a-col>
            </a-row>
            <a-row :gutter="[24]" type="flex" class="invoice-info mt-10">
                <a-col :span="24" :md="8">
                    <h6 class="font-semibold text-muted mb-0"> Invoice no </h6>
                    <h5 class="font-semibold"> #{{ order.data.id }} </h5>
                </a-col>
                <a-col :span="24" :md="10" class="ml-auto text-right">
                    <a-row :gutter="[24]" type="flex" class="invoice-info">
                        <a-col :span="24" :md="12">
                            <h6 class="font-semibold text-muted mb-0"> Date: </h6>
                        </a-col>
                        <a-col :span="24" :md="12">
                            <h6 class="font-semibold mb-0"> {{ order.data.orderTime }} </h6>
                        </a-col>
                    </a-row>
                </a-col>
            </a-row>

            <table class="table-invoice mt-10">
                <thead>
                <tr>
                    <th colspan="4" class="font-semibold">Venue</th>
                    <th class="font-semibold">start time</th>
                    <th class="font-semibold">end time</th>
                    <th class="font-semibold">Amount</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4"> {{ order.data.name }} </td>
                    <td>{{ order.data.begin }}</td>
                    <td>{{ order.data.end }}</td>
                    <td>CNY {{ order.data.price }}</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="4"></th>
                    <th></th>
                    <th> <h5 class="font-semibold">Total</h5> </th>
                    <th> <h5 class="font-semibold">CNY {{ order.data.price }}</h5> </th>
                </tr>
                </tfoot>
            </table>

            <img :src="order.QRCode" alt="" width="150px">
            <a-row :gutter="[24]" type="flex" class="invoice-message mt-20 pt-20">
                <a-col :span="24" :md="8">
                    <h5 class="font-semibold">
                        Thank you!
                    </h5>
                    <p>
                        If you encounter any issues related to the invoice you can contact us at:
                    </p>
                </a-col>
            </a-row>

            <a-row :gutter="[24]" type="flex" class="invoice-foot">
                <a-col :span="24" :md="12">
                    <h6 class="font-semibold">
                        <span class="text-muted">Email:</span> 841359795@qq.com
                    </h6>
                </a-col>
                <a-col :span="24" :md="12" class="ml-auto text-right" id="printButton">
                    <a-button type="primary" @click="print">PRINT</a-button>
                </a-col>
            </a-row>

        </a-card>

    </div>

</template>

<script>

import { getVenueOrderReceipt } from '@/api/order'

export default {
    query: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            order: []
        }
    },
    beforeMount() {
        getVenueOrderReceipt(this.$route.query.id).then(res => {
            this.order = res.data
            console.log(this.order)
        })
    },
    methods: {
        print() {
            const newstr = document.getElementById('printContent').innerHTML
            const oldstr = document.body.innerHTML
            document.body.innerHTML = newstr

            document.getElementById('printButton').style.display = 'none' // 隐藏不需要打印的元素
            window.print()
            document.body.innerHTML = oldstr
            window.location.reload()
            return false
        }
    }
}
</script>

<style>
@media print {
    .ant-layout-sider.sider-primary,
    .sidebar-overlay {
        display: none ;
    }
}
</style>
