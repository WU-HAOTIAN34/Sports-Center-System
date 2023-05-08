<template>

    <div>
        <a-tabs class="tabs-sliding" default-active-key="1">
            <a-tab-pane key="1" tab="Venues">
                <a-row :gutter="24" type="flex">
                    <a-col :span="24" :lg="24" class="mb-24">
                        <a-card class="card-calendar">
                            <FullCalendar :options="calendarOptions" />
                        </a-card>
                    </a-col>
                    <a-col :span="24" :md="16" class="mb-24">
                        <a-card :bordered="false" class="header-solid h-full" :bodyStyle="{paddingTop: 0, paddingBottom: '16px' }">
                            <h6 class="font-semibold my-20">Appointment Information</h6>
                            <a-radio-group @change="handleChange">
                                <a-radio-button value="all">
                                    All
                                </a-radio-button>
                                <a-radio-button value="uncommented">
                                    Uncommented
                                </a-radio-button>
                            </a-radio-group>
                            <p v-if="venueOrders.length==0">no order</p>
                            <a-row :gutter="[24, 24]" v-for="order in this.venueOrders" :key="order">
                                <a-col :span="24">
                                    <a-card :bordered="false" class="card-billing-info">
                                        <div class="col-info">
                                            <a-descriptions :column="1">
                                                <a-descriptions-item label="ID">
                                                    {{ order.id }}
                                                </a-descriptions-item>
                                                <a-descriptions-item label="Begin Time">
                                                    {{ order.begin }}
                                                </a-descriptions-item>
                                                <a-descriptions-item label="End Time">
                                                    {{ order.end }}
                                                </a-descriptions-item>
                                                <a-descriptions-item label="Venue Name">
                                                    {{ order.name }}
                                                </a-descriptions-item>
                                                <a-descriptions-item label="Address">
                                                    {{ order.address }}
                                                </a-descriptions-item>
                                            </a-descriptions>
                                        </div>
                                        <div class="col-action" v-if="order.userid=1">
                                            <a-popconfirm
                                                title="Are you sure delete this order?"
                                                ok-text="Yes"
                                                cancel-text="No"
                                                @confirm="confirmVenue(order.id)"
                                                @cancel="cancel"
                                            >
                                                <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path class="fill-danger" fill-rule="evenodd" clip-rule="evenodd" d="M9 2C8.62123 2 8.27497 2.214 8.10557 2.55279L7.38197 4H4C3.44772 4 3 4.44772 3 5C3 5.55228 3.44772 6 4 6L4 16C4 17.1046 4.89543 18 6 18H14C15.1046 18 16 17.1046 16 16V6C16.5523 6 17 5.55228 17 5C17 4.44772 16.5523 4 16 4H12.618L11.8944 2.55279C11.725 2.214 11.3788 2 11 2H9ZM7 8C7 7.44772 7.44772 7 8 7C8.55228 7 9 7.44772 9 8V14C9 14.5523 8.55228 15 8 15C7.44772 15 7 14.5523 7 14V8ZM12 7C11.4477 7 11 7.44772 11 8V14C11 14.5523 11.4477 15 12 15C12.5523 15 13 14.5523 13 14V8C13 7.44772 12.5523 7 12 7Z" fill="#111827"/>
                                                </svg>
                                                <a href="#" class="text-danger">Delete</a>
                                            </a-popconfirm>
                                        </div>
                                        <div class="col-action">
                                            <a-button type="link" size="small" @click="venueJump(order.id)">
                                                <span class="text-primary">Receipt</span>
                                            </a-button>
                                        </div>
                                        <div class="col-action">
                                            <a-button @click="showVenueModal">
                                                Comment
                                            </a-button>
                                            <a-modal v-model="visibleVenue" title="Make Comment" :footer="null">
                                                <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleVenueSubmit(order.id)">
                                                    <a-form-item label="Comment">
                                                        <a-input
                                                            v-decorator="['comment', { rules: [{ required: true, message: 'Please input your comment!' }] }]"
                                                        />
                                                    </a-form-item>
                                                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
                                                        <a-button type="primary" html-type="submit">
                                                            Submit
                                                        </a-button>
                                                    </a-form-item>
                                                </a-form>
                                            </a-modal>
                                        </div>
                                    </a-card>
                                </a-col>
                            </a-row>
                        </a-card>
                    </a-col>
                    <a-col :span="24" :md="8">
                        <CardNextEvents
                            :data="eventsData" class="mb-24"
                        ></CardNextEvents>
                    </a-col>
                </a-row>
            </a-tab-pane>
            <a-tab-pane key="2" tab="Equipments">
                <a-col :span="24" :md="16" class="mb-24">
                    <a-card :bordered="false" class="header-solid h-full" :bodyStyle="{paddingTop: 0, paddingBottom: '16px' }">
                        <h6 class="font-semibold my-20">Purchase Information</h6>
                        <a-radio-group @change="handleChange">
                            <a-radio-button value="all">
                                All
                            </a-radio-button>
                            <a-radio-button value="uncommented">
                                Uncommented
                            </a-radio-button>
                        </a-radio-group>
                        <p v-if="itemOrders.length==0">no order</p>
                        <a-row :gutter="[24, 24]" v-for="order in this.itemOrders" :key="order">
                            <a-col :span="24">
                                <a-card :bordered="false" class="card-billing-info">
                                    <div class="col-info">
                                        <a-descriptions :column="1">
                                            <a-descriptions-item label="ID">
                                                {{ order.id }}
                                            </a-descriptions-item>
                                            <a-descriptions-item label="Item name">
                                                {{ order.itemName }}
                                            </a-descriptions-item>
                                            <a-descriptions-item label="Number">
                                                {{ order.number }}
                                            </a-descriptions-item>
                                            <a-descriptions-item label="Price">
                                                {{ order.price }}
                                            </a-descriptions-item>
                                        </a-descriptions>
                                    </div>
                                    <div class="col-action" v-if="order.userid=1">
                                        <a-popconfirm
                                            title="Are you sure delete this order?"
                                            ok-text="Yes"
                                            cancel-text="No"
                                            @confirm="confirmItem(order.id)"
                                            @cancel="cancel"
                                        >
                                            <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path class="fill-danger" fill-rule="evenodd" clip-rule="evenodd" d="M9 2C8.62123 2 8.27497 2.214 8.10557 2.55279L7.38197 4H4C3.44772 4 3 4.44772 3 5C3 5.55228 3.44772 6 4 6L4 16C4 17.1046 4.89543 18 6 18H14C15.1046 18 16 17.1046 16 16V6C16.5523 6 17 5.55228 17 5C17 4.44772 16.5523 4 16 4H12.618L11.8944 2.55279C11.725 2.214 11.3788 2 11 2H9ZM7 8C7 7.44772 7.44772 7 8 7C8.55228 7 9 7.44772 9 8V14C9 14.5523 8.55228 15 8 15C7.44772 15 7 14.5523 7 14V8ZM12 7C11.4477 7 11 7.44772 11 8V14C11 14.5523 11.4477 15 12 15C12.5523 15 13 14.5523 13 14V8C13 7.44772 12.5523 7 12 7Z" fill="#111827"/>
                                            </svg>
                                            <a href="#" class="text-danger">Delete</a>
                                        </a-popconfirm>
                                    </div>
                                    <div class="col-action">
                                        <a-button type="link" size="small" @click="itemJump(order.id)">
                                            <span class="text-primary">Receipt</span>
                                        </a-button>
                                    </div>
                                    <div class="col-action">
                                        <a-button @click="showItemModal">
                                            Comment
                                        </a-button>
                                        <a-modal v-model="visibleItem" title="Make Comment" :footer="null">
                                            <a-form :form="form2" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleItemSubmit(order.id)">
                                                <a-form-item label="Comment">
                                                    <a-input
                                                        v-decorator="['comment', { rules: [{ required: true, message: 'Please input your comment!' }] }]"
                                                    />
                                                </a-form-item>
                                                <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
                                                    <a-button type="primary" html-type="submit">
                                                        Submit
                                                    </a-button>
                                                </a-form-item>
                                            </a-form>
                                        </a-modal>
                                    </div>
                                </a-card>
                            </a-col>
                        </a-row>
                    </a-card>
                </a-col>
            </a-tab-pane>
            <!-- / Notifications Tab -->
        </a-tabs>
    </div>

</template>

<script>
import moment from 'moment'
import '@fullcalendar/core/vdom'
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import CardNextEvents from '../components/Cards/CardNextEvents'
import {
    getUserOrders,
    getUncommentedVenueOrders,
    getUserItemOrders,
    getUncommentedItemOrders,
    deleteOrder,
    deleteItemOrder
} from '@/api/order'
import { makeVenueComment } from '@/api/venueComment'
import { makeItemComment } from '@/api/itemComment'

// Next event's list.
// const eventsData = [
//     {
//         id: '1',
//         title: 'No.1 Basketball venue',
//         code: '29 April 2023, at 13:00',
//         iconClass: 'text-primary',
//         icon: 'calendar',
//         iconBgColor: 'rgba(234,6,6,.03)'
//     }
// ]

// Chart data.
const chartData = {
    labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    datasets: [{
        label: 'Visitors',
        tension: 0.5,
        pointRadius: 0,
        borderColor: '#fff',
        borderWidth: 2,
        data: [50, 45, 60, 60, 80, 65, 90, 80, 100],
        maxBarThickness: 6,
        fill: true
    }]
}

export default {
    components: {
        FullCalendar,
        CardNextEvents
    },
    data() {
        return {
            eventsData: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
            form2: this.$form.createForm(this),
            visibleVenue: false,
            visibleItem: false,
            comments: [],
            submitting: false,
            value: '',
            moment,
            activeKey: ['1'],
            venueOrders: [],
            itemOrders: [],

            // Chart data.
            chartData,

            // Calendar options.
            calendarOptions: {
                plugins: [dayGridPlugin, interactionPlugin],
                contentHeight: 'auto',
                initialView: 'dayGridMonth',
                headerToolbar: {
                    start: 'title', // will normally be on the left. if RTL, will be on the right
                    center: '',
                    end: 'today prev,next' // will normally be on the right. if RTL, will be on the left
                },
                selectable: true,
                editable: true,
                initialDate: '2023-05-01',
                events: [],
                views: {
                    month: {
                        titleFormat: {
                            month: 'long',
                            year: 'numeric'
                        }
                    },
                    agendaWeek: {
                        titleFormat: {
                            month: 'long',
                            year: 'numeric',
                            day: 'numeric'
                        }
                    },
                    agendaDay: {
                        titleFormat: {
                            month: 'short',
                            year: 'numeric',
                            day: 'numeric'
                        }
                    }
                }
            }

        }
    },
    beforeMount() {
        getUserOrders().then(res => {
            this.venueOrders = res.data.data
            // add elements to this.calendarOptions.events
            for (let i = 0; i < this.venueOrders.length; i++) {
                this.calendarOptions.events.push({
                    title: this.venueOrders[i].name,
                    start: this.venueOrders[i].begin,
                    end: this.venueOrders[i].end,
                    className: 'bg-warning text-dark'
                })
                // add elements to this.eventsData which means the next events
                this.eventsData.push({
                    id: this.venueOrders[i].id,
                    title: this.venueOrders[i].name,
                    code: this.venueOrders[i].begin + ' - ' + this.venueOrders[i].end,
                    iconClass: 'text-primary',
                    icon: 'calendar',
                    iconBgColor: 'rgba(234,6,6,.03)'
                })
            }
        })
        getUserItemOrders().then(res => {
            this.itemOrders = res.data.data
        })
        this.$forceUpdate()
    },
    methods: {
        itemJump(id) {
            this.$router.push({
                path: '/itemInvoice',
                query: {
                    id: id
                }
            })
        },
        venueJump(id) {
            this.$router.push({
                path: '/venueInvoice',
                query: {
                    id: id
                }
            })
        },
        showVenueModal() {
            this.visibleVenue = true
        },
        handleVenueSubmit: function (id) {
            this.form.validateFields((err, values) => {
                if (!err) {
                    makeVenueComment(id, values).then(res => {
                        if (res.data.code === 1003) {
                            this.visibleVenue = false
                            this.form.resetFields()
                            this.$message.success('Comment successfully!')
                        } else {
                            this.$message.error(res.data.msg)
                        }
                    })
                }
            })
        },
        showItemModal() {
            this.visibleItem = true
        },
        handleItemSubmit: function (id) {
            this.form2.validateFields((err, values) => {
                if (!err) {
                    makeItemComment(id, values).then(res => {
                        this.visibleItem = false
                        this.form.resetFields()
                        this.$message.success('Comment successfully!')
                        // eslint-disable-next-line node/handle-callback-err
                    }).catch(err => {
                        this.$message.error('Comment failed!')
                    })
                }
            })
        },
        handleChange(e) {
            if (e.target.value === 'all') {
                getUserOrders().then(res => {
                    this.venueOrders = res.data.data
                })
                getUserItemOrders().then(res => {
                    this.itemOrders = res.data.data
                })
            } else {
                getUncommentedVenueOrders().then(res => {
                    this.venueOrders = res.data.data
                })
                getUncommentedItemOrders().then(res => {
                    this.itemOrders = res.data.data
                })
            }
            this.$forceUpdate()
        },
        confirmVenue(e) {
            deleteOrder(e).then(res => {
                this.$message.success('Delete successfully!')
                this.venueOrders = this.venueOrders.filter(item => item.id !== e)
                this.eventsData = this.eventsData.filter(item => item.id !== e)
            })
        },
        confirmItem(e) {
            deleteItemOrder(e).then(res => {
                this.$message.success('Delete successfully!')
                this.itemOrders = this.itemOrders.filter(item => item.id !== e)
            })
        },
        cancel(e) {
        }
    }
}

</script>

<style>
</style>
