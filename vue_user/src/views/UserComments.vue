<template>

    <div>
        <a-card :bordered="false" class="card-order header-solid mb-24 mx-auto mt-20 mb-50" :bodyStyle="{paddingTop: 0}" style="max-width: 800px;">
            <a-tabs class="tabs-sliding" default-active-key="1">
                <a-tab-pane key="1" tab="Venues">
                    <h6 v-if="venueComments.length==0">no comments</h6>
                    <div v-for="(item, i) in venueComments" :key="i">
                        <a-row type="flex" align="middle">
                            <a-col>
                                <a-avatar :size="78" :src="item.venueImage" />
                            </a-col>
                            <a-col class="pl-15">
                                <h5 class="mb-0">{{ item.venueName }}</h5>
                                <a class="text-dark">{{ item.commentTime }}</a>
                            </a-col>
                            <a-col :span="24" :md="12" class="ml-auto" style="display: flex; align-items: center; justify-content: flex-end">
                                <a-popconfirm
                                    title="Are you sure delete this comment?"
                                    ok-text="Yes"
                                    cancel-text="No"
                                    @confirm="confirmVenue(item.id)"
                                    @cancel="cancel"
                                >
                                    <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path class="fill-danger" fill-rule="evenodd" clip-rule="evenodd" d="M9 2C8.62123 2 8.27497 2.214 8.10557 2.55279L7.38197 4H4C3.44772 4 3 4.44772 3 5C3 5.55228 3.44772 6 4 6L4 16C4 17.1046 4.89543 18 6 18H14C15.1046 18 16 17.1046 16 16V6C16.5523 6 17 5.55228 17 5C17 4.44772 16.5523 4 16 4H12.618L11.8944 2.55279C11.725 2.214 11.3788 2 11 2H9ZM7 8C7 7.44772 7.44772 7 8 7C8.55228 7 9 7.44772 9 8V14C9 14.5523 8.55228 15 8 15C7.44772 15 7 14.5523 7 14V8ZM12 7C11.4477 7 11 7.44772 11 8V14C11 14.5523 11.4477 15 12 15C12.5523 15 13 14.5523 13 14V8C13 7.44772 12.5523 7 12 7Z" fill="#111827"/>
                                    </svg>
                                    <a href="#" class="text-danger">Delete</a>
                                </a-popconfirm>
                            </a-col>
                        </a-row>
                        <a-row type="flex" class="mt-15" style="flex-wrap: nowrap;">
                            <a-col flex="1 1 10px"></a-col>
                            <a-col flex="1 1 auto">
                                <p>
                                    {{ item.info }}
                                </p>
                            </a-col>
                        </a-row>
                        <hr class="gradient-line">
                    </div>
                </a-tab-pane>
                <a-tab-pane key="2" tab="Equipments">
                    <h6 v-if="itemComments.length==0">no comments</h6>
                    <div v-for="(item, i) in itemComments" :key="i">
                        <a-row type="flex" align="middle">
                            <a-col>
                                <a-avatar :size="78" :src="item.itemImage" />
                            </a-col>
                            <a-col class="pl-15">
                                <h5 class="mb-0">{{ item.itemName }}</h5>
                                <a class="text-dark">{{ item.commentTime }}</a>
                            </a-col>
                            <a-col :span="24" :md="12" class="ml-auto" style="display: flex; align-items: center; justify-content: flex-end">
                                <a-popconfirm
                                    title="Are you sure delete this comment?"
                                    ok-text="Yes"
                                    cancel-text="No"
                                    @confirm="confirmItem(item.id)"
                                    @cancel="cancel"
                                >
                                    <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path class="fill-danger" fill-rule="evenodd" clip-rule="evenodd" d="M9 2C8.62123 2 8.27497 2.214 8.10557 2.55279L7.38197 4H4C3.44772 4 3 4.44772 3 5C3 5.55228 3.44772 6 4 6L4 16C4 17.1046 4.89543 18 6 18H14C15.1046 18 16 17.1046 16 16V6C16.5523 6 17 5.55228 17 5C17 4.44772 16.5523 4 16 4H12.618L11.8944 2.55279C11.725 2.214 11.3788 2 11 2H9ZM7 8C7 7.44772 7.44772 7 8 7C8.55228 7 9 7.44772 9 8V14C9 14.5523 8.55228 15 8 15C7.44772 15 7 14.5523 7 14V8ZM12 7C11.4477 7 11 7.44772 11 8V14C11 14.5523 11.4477 15 12 15C12.5523 15 13 14.5523 13 14V8C13 7.44772 12.5523 7 12 7Z" fill="#111827"/>
                                    </svg>
                                    <a href="#" class="text-danger">Delete</a>
                                </a-popconfirm>
                            </a-col>
                        </a-row>
                        <a-row type="flex" class="mt-15" style="flex-wrap: nowrap;">
                            <a-col flex="1 1 10px"></a-col>
                            <a-col flex="1 1 auto">
                                <p>
                                    {{ item.info }}
                                </p>
                            </a-col>
                        </a-row>
                        <hr class="gradient-line">
                    </div>
                </a-tab-pane>
            </a-tabs>
        </a-card>
    </div>

</template>

<script>
import { getUserItemComment, deleteItemComment } from '@/api/itemComment'
import { getUserVenueComment, deleteVenueComment } from '@/api/venueComment'

export default {
    data() {
        return {
            venueComments: [],
            itemComments: []
        }
    },
    beforeMount() {
        getUserItemComment().then(res => {
            this.itemComments = res.data.data
        })
        getUserVenueComment().then(res => {
            this.venueComments = res.data.data
        })
    },
    methods: {
        confirmVenue(e) {
            deleteVenueComment(e).then(res => {
                if (res.data.code === 1010) {
                    this.$message.success('Delete successfully')
                    this.venueComments = this.venueComments.filter(item => item.id !== e)
                } else {
                    this.$message.error(res.data.msg)
                }
            })
        },
        confirmItem(e) {
            deleteItemComment(e).then(res => {
                if (res.data.code === 1010) {
                    this.$message.success('Delete successfully')
                    this.itemComments = this.itemComments.filter(item => item.id !== e)
                } else {
                    this.$message.error(res.data.msg)
                }
            })
        },
        cancel(e) {
        }
    }
}

</script>

<style>
</style>
