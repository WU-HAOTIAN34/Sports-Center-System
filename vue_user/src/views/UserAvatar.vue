<template>
    <a-form
        id="components-form-demo-validate-other"
        :form="form"
        v-bind="formItemLayout"
        @submit="handleSubmit"
    >
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
import { updateUserAvatar } from '@/api/user'

export default {
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'validate_other' })
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    values.upload = values.upload[0].thumbUrl
                    values.upload = values.upload.slice(22)
                    updateUserAvatar(values).then(() => {
                        this.$message.success('Avatar updated successfully')
                    })
                }
            })
        },
        normFile(e) {
            if (Array.isArray(e)) {
                return e
            }
            return e && e.fileList
        }
    }
}
</script>

<style scoped>

</style>
