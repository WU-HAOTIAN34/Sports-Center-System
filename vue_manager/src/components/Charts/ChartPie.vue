<template>
    <div>
        <!-- Pie chart -->
        <canvas ref="chart" class="chart-pie" :style="{'height': height + 'px'}"></canvas>
        <!-- / Pie chart -->
    </div>
</template>

<script>
import { Chart, registerables } from 'chart.js'
Chart.register(...registerables)

export default ({
    props: [
        'data'
    ],
    data() {
        return {
            chart: null
        }
    },
    watch: {
        data: {
            handler: function (val, oldVal) {
                this.chart.data = val
                this.chart.update()
            },
            deep: true
        }
    },
    mounted () {
        const ctx = this.$refs.chart.getContext('2d')
        this.chart = new Chart(ctx, {
            type: 'pie',
            data: this.data,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: true
                    }
                },
                interaction: {
                    intersect: true,
                    mode: 'index'
                },
                scales: {
                    y: {
                        grid: {
                            drawBorder: false,
                            display: false,
                            drawOnChartArea: false,
                            drawTicks: false
                        },
                        ticks: {
                            display: false
                        }
                    },
                    x: {
                        grid: {
                            drawBorder: false,
                            display: false,
                            drawOnChartArea: false,
                            drawTicks: false
                        },
                        ticks: {
                            display: false
                        }
                    }
                }
            }
        })
    },
    // Right before the component is destroyed,
    // also destroy the chart.
    beforeDestroy: function () {
        this.chart.destroy()
    }
})

</script>

<style lang="scss" scoped>
</style>
