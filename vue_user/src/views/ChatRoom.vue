<template>
    <a-card :bordered="false" class="header-solid h-full mb-24" :bodyStyle="{paddingTop: '14px'}" ref="scrollDiv">
        <div id="chatBox" v-for="(message, index) in messages" :key="index">
            <div class="outgoing_msg" v-if="message.userId==1">
                <div class="sent_msg">
                    <p class="bg-primary">{{ message.info }}</p>
                    <span class="time_date">{{ message.time }}</span>
                </div>
            </div>
            <div class="incoming_msg" v-else>
                <div class="received_msg ">
                    <span class="time_date">{{ message.time }}</span>
                    <div class="received_withd_msg">
                        <p class="">{{ message.info }}</p>
                        <span class="time_date">{{ message.username }}</span>
                    </div>
                </div>
            </div>
            <br>
        </div>
        <a-affix :offset-bottom="bottom">
            <a-form :form="form" layout="inline" @submit="handleSubmit">
                <a-form-item>
                    <a-input v-decorator="['speak', { rules: [{ required: true, message: 'Please input your message' }] }]"/>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit">
                        Send
                    </a-button>
                </a-form-item>
            </a-form>
        </a-affix>
    </a-card>
</template>

<script>
import { getAllChatMessages, sendMessage } from '@/api/speak'

export default {
    data() {
        return {
            form: this.$form.createForm(this),
            messages: [],
            bottom: 20
        }
    },
    beforeMount() {
        if (localStorage.getItem('token') === null) {
            this.$router.replace('/home')
            this.$message.error('Please login first')
        } else {
            getAllChatMessages().then((response) => {
                if (response.data.code === 6005) {
                    this.messages = response.data.data
                } else {
                    this.$message.error(response.data.msg)
                }
            })
        }
    },
    mounted() {
        window.scrollTo(0, 1000)
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    sendMessage(values).then((response) => {
                        if (response.data.code === 6001 || response.data.code === 6002) {
                            this.$message.error(response.data.msg)
                        } else {
                            this.$message.success('Send message successfully')
                            getAllChatMessages().then((response) => {
                                this.messages = response.data.data
                            })
                        }
                    })
                }
            })
        }
    }
}
</script>

<style scoped>
.container_topic{
    max-width: 80%;
    padding-bottom: 20px;
}
.inbox_msg {
    border: 1px solid #c3cae6;
    clear: both;
    overflow: hidden;
}
.received_msg {
    display: inline-block;
    padding: 0 0 0 10px;
    vertical-align: top;
    max-width: 80%;
}
.received_withd_msg p {
    background: #ebebeb none repeat scroll 0 0;
    border-radius: 3px;
    color: #646464;
    font-size: 14px;
    margin: 0;
    padding: 5px 10px 5px 12px;
    width: 100%;
}
.time_date {
    color: #747474;
    display: block;
    font-size: 12px;
    margin: 8px 0 0;
}
.received_withd_msg {
    width: 100%;
}
.mesgs {
    float: left;
    padding: 30px 15px 0 25px;
    width: 100%;
}
.sent_msg p {
    background: #343a40 none repeat scroll 0 0;
    border-radius: 3px;
    font-size: 14px;
    margin: 0;
    color: #fff;
    padding: 5px 10px 5px 12px;
    width: 100%;
}
.outgoing_msg{
    overflow: hidden;
    margin: 26px 0 26px;
}
.sent_msg {
    float: right;
    max-width: 80%;
    padding-right: 10px;
}
.input_msg_write input {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    border: medium none;
    color: #4c4c4c;
    font-size: 15px;
    min-height: 48px;
    width: 100%;
}
.type_msg {
    border-top: 1px solid #c4c4c4;
    position: relative;
}
.msg_send_btn {
    background: #343a40 none repeat scroll 0 0;
    border: medium none;
    border-radius: 50%;
    color: #fff;
    cursor: pointer;
    font-size: 17px;
    height: 33px;
    position: absolute;
    right: 0;
    top: 11px;
    width: 33px;
}
.messaging {
    padding: 0 0 0 0;
}
.msg_history {
    height: 600px;
    overflow-y: auto;
    overflow-x: auto;
}

</style>
