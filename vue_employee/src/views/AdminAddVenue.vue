<template>
    <a-form
        id="components-form-demo-validate-other"
        :form="form"
        v-bind="formItemLayout"
        @submit="handleSubmit"
    >
        <a-form-item>
            <a-input
                v-decorator="['string1']"
                placeholder="string1"
            >
            </a-input>
        </a-form-item>
        <a-form-item>
            <a-input
                v-decorator="['string2']"
                placeholder="string2"
            >
            </a-input>
        </a-form-item>
        <a-form-item>
            <a-input
                v-decorator="['number']"
                type="number"
                placeholder="number"
            >
            </a-input>
        </a-form-item>
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
</template>

<script>
import { addNewVenue } from '@/api/venue'

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
                    addNewVenue(values).then(res => {
                        console.log(res.data)
                    })
                    console.log('Received values of form: ', values)
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
