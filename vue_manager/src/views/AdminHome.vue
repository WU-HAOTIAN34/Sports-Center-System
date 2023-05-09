<template>
    <div>
        <a-card>
            <a-row :gutter="24" type="flex" align="stretch">
                <a-col :span="24" :lg="12" class="mb-24">
                    <h5 class="font-semibold">Statistics</h5>
                    <p>
                        Here are some charts to show the statistics of the system, helping you to manage the sports centre better.
                    </p>

                </a-col>
            </a-row>

            <a-row :gutter="24" type="flex" align="stretch">
                <a-col :span="24" :lg="14" class="mb-24">
                    <a-card :bordered="false" class="header-solid" :bodyStyle="{padding: '0 12px 8px 3px'}">
                        <template #title>
                            <h6>Venue bookings this week</h6>
                        </template>
                        <a-card :bordered="false" class="dashboard-bar-chart">
                            <chart-bar :height="220" :data="barChartData1"></chart-bar>
                            <a-row class="card-footer" type="flex" justify="center" align="top">
                                <a-col>
                                    <h4>{{ total1 }}</h4>
                                    <span>Orders</span>
                                </a-col>
                            </a-row>
                        </a-card>
                    </a-card>
                </a-col>
                <a-col :span="24" :lg="10" class="mb-24">
                    <a-card :bordered="false" class="header-solid" :bodyStyle="{padding: '0 12px 8px 3px'}">
                        <a-card :bordered="false" class="dashboard-bar-line header-solid">
                            <template #title>
                                <h6>Sports Centre income in the last 6 weeks</h6>
                                <p>0 is this week, 1 is last week, and so on</p>
                            </template>
                            <template #extra>
                                <a-badge color="primary" class="badge-dot-success" text="Income" />
                            </template>
                            <chart-line :height="310" :data="lineChartData"></chart-line>
                        </a-card>
                    </a-card>
                </a-col>
                <a-col :span="24" :lg="14" class="mb-24">
                    <a-card :bordered="false" class="header-solid" :bodyStyle="{padding: '0 12px 8px 3px'}">
                        <template #title>
                            <h6>Equipment orders this week</h6>
                        </template>
                        <a-card :bordered="false" class="dashboard-bar-chart">
                            <chart-bar :height="220" :data="barChartData2"></chart-bar>
                            <a-row class="card-footer" type="flex" justify="center" align="top">
                                <a-col>
                                    <h4>{{ total2 }}</h4>
                                    <span>Orders</span>
                                </a-col>
                            </a-row>
                        </a-card>
                    </a-card>
                </a-col>
                <a-col :span="24" :lg="10" class="mb-24">
                    <a-card :bordered="false" class="header-solid" :bodyStyle="{padding: '0 12px 8px 3px'}">
                        <template #title>
                            <h6>Venues quantity distribution</h6>
                        </template>
                        <a-card :bordered="false" class="dashboard-bar-chart">
                            <ChartPie :data="pieData"></ChartPie>
                        </a-card>
                    </a-card>
                </a-col>
            </a-row>
        </a-card>
    </div>
</template>

<script>
import { getItemBooking, getVenueBooking, getVenueProportion, getWeeklyTendency } from '@/api/chart'
import ChartBar from '../components/Charts/ChartBar'
import ChartLine from '../components/Charts/ChartLine'
import ChartPie from '../components/Charts/ChartPie'
export default {
    components: {
        ChartBar,
        ChartLine,
        ChartPie
    },
    data() {
        return {
            rise: 0,
            total1: 0,
            total2: 0,
            venueProportion: [],
            venueBooking: [],
            itemBooking: [],
            tendency: [],
            barChartData1: {
                labels: [],
                datasets: [{
                    label: 'Bookings',
                    backgroundColor: '#fff',
                    borderWidth: 0,
                    borderSkipped: false,
                    borderRadius: 6,
                    data: [],
                    maxBarThickness: 20
                }]
            },
            barChartData2: {
                labels: [],
                datasets: [{
                    label: 'Orders',
                    backgroundColor: '#fff',
                    borderWidth: 0,
                    borderSkipped: false,
                    borderRadius: 6,
                    data: [],
                    maxBarThickness: 20
                }]
            },
            lineChartData: {
                labels: [],
                datasets: [{
                    label: 'Income',
                    tension: 0.4,
                    pointRadius: 0,
                    borderColor: 'green',
                    borderWidth: 3,
                    data: [14, 29, 29, 34, 23, 40],
                    maxBarThickness: 6
                }]
            },
            pieData: {
                labels: [],
                datasets: [{
                    label: 'Venues',
                    weight: 9,
                    cutout: 0,
                    tension: 0.9,
                    pointRadius: 2,
                    borderWidth: 2,
                    backgroundColor: ['#d20962', '#f47721', '#7ac143', '#00a78e', '#00bce4', '#7d3f98'],
                    data: [],
                    fill: false
                }]
            }
        }
    },
    mounted() {
        getVenueBooking().then(res => {
            this.venueBooking = res.data.data
            this.barChartData1.labels = Object.keys(this.venueBooking)
            this.barChartData1.datasets[0].data = Object.values(this.venueBooking).map(item => Number(item))
            this.total1 = Object.values(this.venueBooking).reduce((total, item) => total + Number(item), 0)
            this.$forceUpdate()
        })
        getItemBooking().then(res => {
            this.itemBooking = res.data.data
            this.barChartData2.labels = Object.keys(this.itemBooking)
            this.barChartData2.datasets[0].data = Object.values(this.itemBooking).map(item => Number(item))
            this.total2 = Object.values(this.itemBooking).reduce((total, item) => total + Number(item), 0)
            this.$forceUpdate()
        })
        getWeeklyTendency().then(res => {
            this.tendency = res.data.data
            this.lineChartData.datasets[0].data = Object.values(this.tendency).reverse()
            this.lineChartData.labels = Object.keys(this.tendency).reverse()
        })
        getVenueProportion().then(res => {
            this.venueProportion = res.data.data
            this.pieData.labels = Object.keys(this.venueProportion)
            this.pieData.datasets[0].data = Object.values(this.venueProportion).map(item => Number(item))
            console.log(this.pieData)
        })
    },
    methods: {
    }
}
</script>

<style lang="scss">
</style>
