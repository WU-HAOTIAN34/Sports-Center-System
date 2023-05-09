<template>
    <div>

        <a-card :bordered="false" class="card-product header-solid mb-24">
            <template #title>
                <h6>Item Details</h6>
            </template>

            <a-row type="flex" :gutter="[24]">
                <a-col :span="24" :lg="12" :xl="10" class="col-gallery">
                    <img class="gallery-img shadow-3 img-contain" :src="this.itemData.image" height="100%">
                </a-col>

                <a-col :span="24" :lg="12" :xl="{ span: 12, offset: 2 }" class="col-info">

                    <h3>{{ this.itemData.itemName }}</h3>

                    <h5 class="mb-0 mt-20 font-semibold">Price: {{ this.itemData.price }} CNY</h5>

                    <h6 class="mt-20 font-semibold"><small>Description:</small></h6>

                    <li><p class="mb-5 text-lg text-muted">{{ this.itemData.info }}</p></li>
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
                                <a-tooltip slot="datetime">
                                    <span>{{ item.commentTime }}</span>
                                </a-tooltip>
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
import { getItemInfo } from '@/api/item'
import { deleteItemComment, getItemCommentById } from '@/api/itemComment'

export default ({
    query: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            form: this.$form.createForm(this),
            itemData: {},
            description: [],
            comments: [],
            moment

        }
    },
    beforeMount() {
        getItemInfo(this.$route.query.id).then(res => {
            this.itemData = res.data.data
            console.log(this.itemData)
        })
        getItemCommentById(this.$route.query.id).then(res => {
            this.comments = res.data.data
            console.log(this.comments)
        })
    },

    methods: {
        confirm(e) {
            console.log(e)
            deleteItemComment(e).then(res => {
                this.$message.success('Delete successfully')
                getItemCommentById(this.$route.query.id).then(res => {
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
