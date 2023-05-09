<template>
    <div>

        <a-card :bordered="false" class="card-product header-solid mb-24">
            <template #title>
                <h6>Venue Details</h6>
            </template>

            <a-row type="flex" :gutter="[24]">
                <a-col :span="24" :lg="12" :xl="10" class="col-gallery">
                    <img class="gallery-img shadow-3 img-contain" :src="this.venueData.image" height="100%">
                </a-col>

                <a-col :span="24" :lg="12" :xl="{ span: 12, offset: 2 }" class="col-info">

                    <h3>{{ this.venueData.name }}</h3>

                    <div class="rating">
                        <a-icon type="star" theme="filled" />
                        <a-icon type="star" theme="filled" />
                        <a-icon type="star" theme="filled" />
                        <a-icon type="star" theme="filled" />
                        <a-icon type="star" />
                    </div>

                    <h5 class="mb-0 mt-20 font-semibold">Price: {{ this.venueData.price }} (CNY/hour)</h5>
                    <h5 class="mb-0 mt-20 font-semibold">address: {{ this.venueData.address }}</h5>

                    <h6 class="mt-20 font-semibold"><small>Description:</small></h6>

                    <ul v-for="des in description" :key="des">
                        <li><p class="mb-5 text-lg text-muted">{{ des }}</p></li>
                    </ul>
                </a-col>

            </a-row>
            <a-row type="flex" :gutter="[24]" class="mt-50">
                <a-col :span="24">
                    <a-list
                        class="comment-list"
                        :header="`${comments.length} replies`"
                        item-layout="horizontal"
                        :data-source="comments"
                    >
                        <a-list-item slot="renderItem" slot-scope="item">
                            <a-comment :author="item.username" :avatar="item.image">
                                <p slot="content">
                                    {{ item.info }}
                                </p>
                                <span>{{ item.commentTime }}</span>
                                <a-popconfirm
                                    title="Are you sure delete this task?"
                                    ok-text="Yes"
                                    cancel-text="No"
                                    @confirm="confirm(item.id)"
                                    @cancel="cancel"
                                >
                                    <a href="#">Delete</a>
                                </a-popconfirm>
                            </a-comment>
                        </a-list-item>
                    </a-list>

                </a-col>
            </a-row>

        </a-card>

    </div>
</template>

<script>
import moment from 'moment'
import { getVenueById } from '@/api/venue'
import { deleteVenueComment, getVenueCommentById } from '@/api/venueComment'

export default ({
    query: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            venueData: {},
            description: [],
            comments: [],
            moment

        }
    },
    beforeMount() {
        getVenueById(this.$route.query.id).then(res => {
            this.venueData = res.data.data
            this.description = this.venueData.introduction.split('\n')
            console.log(this.venueData)
        })
        getVenueCommentById(this.$route.query.id).then(res => {
            this.comments = res.data.data
            console.log(this.comments)
        })
    },

    methods: {
        confirm(e) {
            console.log(e)
            deleteVenueComment(e).then(res => {
                this.$message.success(res.data.data)
                getVenueCommentById(this.$route.query.id).then(res => {
                    this.comments = res.data.data
                })
            })
        },
        cancel(e) {
        }
    }
})

</script>

<style>
</style>
