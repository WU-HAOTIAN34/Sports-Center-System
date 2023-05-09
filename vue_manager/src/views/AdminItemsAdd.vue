<template>
    <div>
        <a-card :bordered="false" class="header-solid mb-24" :bodyStyle="{padding: 0, paddingTop: '16px'}">
            <template #title>
                <h5 class="font-semibold">Add an equipment</h5>
            </template>
            <a-form
                id="components-form-demo-validate-other"
                :form="form"
                v-bind="formItemLayout"
                @submit="handleSubmit"
            >

                <a-row :gutter="[24]">
                    <a-col :span="12">
                        <a-form-item class="mb-10" label="Name" :colon="false">
                            <a-input
                                v-decorator="[
                                                'name',
                                                { rules: [{ required: true, message: 'Please input your name!' }] },
                                            ]"
                                placeholder="Name"
                            >
                            </a-input>
                        </a-form-item>
                    </a-col>
                    <a-col :span="12">
                        <a-form-item class="mb-10" label="Info" :colon="false">
                            <a-input
                                v-decorator="[
                                                'info',
                                                { rules: [{ required: true, message: 'Please input the address!' }] },
                                            ]"
                                placeholder="Info"
                            >
                            </a-input>
                        </a-form-item>
                    </a-col>
                    <a-col :span="12">
                        <a-form-item class="mb-10" label="Price" :colon="false">
                            <a-input
                                v-decorator="['price',
                                                { rules: [{ required: true, message: 'Please input your name!' }] },]"
                                type="number"
                                placeholder="Price"
                            >
                            </a-input>
                        </a-form-item>
                    </a-col>
                </a-row>

                <a-form-item label="Upload">
                    <a-upload
                        v-decorator="[
              'upload',
              {
                valuePropName: 'fileList',
                getValueFromEvent: normFile,
              },
            ]"
                        name="logo"
                        action="/upload.do"
                        list-type="picture"
                    >
                        <a-button> <a-icon type="upload" /> Click to upload </a-button>
                    </a-upload>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 12, offset: 6 }">
                    <a-button type="primary" html-type="submit">
                        Submit
                    </a-button>
                </a-form-item>
            </a-form>
        </a-card>
    </div>

</template>

<script>
import { addItem } from '@/api/item'

export default {
    data: () => ({
        formItemLayout: {
            labelCol: { span: 6 },
            wrapperCol: { span: 14 }
        }
    }),
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'validate_other' })
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    if (values.upload === undefined) {
                        this.$message.warning('Please upload an image')
                        return
                    }
                    if (values.upload.length !== 1) {
                        this.$message.warning('No more than 1 image')
                        return
                    }
                    values.upload = values.upload[0].thumbUrl
                    values.upload = values.upload.slice(22)
                    console.log('Received values of form: ', values)
                    addItem(values).then((res) => {
                        console.log(res)
                        if (res.data.code === 3004) {
                            this.$message.success('Item added successfully')
                            this.$router.push('/admin/items')
                        } else {
                            this.$message.error(res.data.msg)
                        }
                    })
                }
            })
        },
        normFile(e) {
            console.log('Upload event:', e)
            if (Array.isArray(e)) {
                return e
            }
            return e && e.fileList
        }
    }
}
</script>
<style>
#components-form-demo-validate-other .dropbox {
    height: 180px;
    line-height: 1.5;
}
</style>
