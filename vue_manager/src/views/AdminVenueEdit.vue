<template>
    <div>
        <a-card :bordered="false" class="header-solid mb-24" :bodyStyle="{padding: 0, paddingTop: '16px'}">
            <template #title>
                <h5 class="font-semibold">Venues</h5>
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
                        <a-form-item class="mb-10" label="Address" :colon="false">
                            <a-input
                                v-decorator="[
                                                'address',
                                                { rules: [{ required: true, message: 'Please input the address!' }] },
                                            ]"
                                placeholder="Address"
                            >
                            </a-input>
                        </a-form-item>
                    </a-col>
                    <a-col :span="12">
                        <a-form-item class="mb-10" label="Type" :colon="false">
                            <a-select
                                v-decorator="[
                                  'type',
                                  { rules: [{ required: true, message: 'Please select a type!' }] },
                                ]"
                                placeholder="Select a option and change input text above"
                                @change="handleSelectChange"
                            >
                                <a-select-option value="Table tennis venue">
                                    Table tennis venue
                                </a-select-option>
                                <a-select-option value="Basketball venue">
                                    Basketball venue
                                </a-select-option>
                                <a-select-option value="Volleyball venue">
                                    Volleyball venue
                                </a-select-option>
                                <a-select-option value="Badminton venue">
                                    Badminton venue
                                </a-select-option>
                                <a-select-option value="Tennis venue">
                                    Tennis venue
                                </a-select-option>
                            </a-select>
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
import { editVenue, getVenueById } from '@/api/venue'

export default {
    query: {
        id: {
            type: String,
            required: true
        }
    },
    data: () => ({
        formItemLayout: {
            labelCol: { span: 6 },
            wrapperCol: { span: 14 }
        }
    }),
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'validate_other' })
        getVenueById(this.$route.query.id).then((res) => {
            if (res.data.code === 8001) {
                console.log(res.data.data)
                this.form.setFieldsValue({
                    name: res.data.data.name,
                    address: res.data.data.address,
                    type: res.data.data.typeName,
                    price: res.data.data.price,
                    upload: [
                        {
                            uid: '-1',
                            name: 'image.png',
                            status: 'done',
                            thumbUrl: res.data.data.image
                        }
                    ]
                })
            }
        })
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    if (values.upload.length === 0) {
                        this.$message.warning('Please upload an image')
                        return
                    }
                    if (values.upload.length !== 1) {
                        this.$message.warning('No more than 1 image')
                        return
                    }
                    values.upload = values.upload[0].thumbUrl
                    // delete data:image/png;base64, at the beginning of the string
                    values.upload = values.upload.slice(22)
                    console.log('Received values of form: ', values)
                    editVenue(this.$route.query.id, values).then((res) => {
                        if (res.data.code === 8010) {
                            this.$message.success('Edit venue successfully')
                            this.$router.push('/admin/venues')
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
