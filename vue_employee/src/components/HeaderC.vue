<template>
    <component :is="navbarFixed ? 'a-affix' : 'div'" :offset-top="top">
        <a-layout-header>
            <a-row type="flex">
                <a-col :span="24" :md="6">
                    <div class="ant-page-header-heading">
                        <span class="ant-page-header-heading-title">{{ this.$route.name }}</span>
                    </div>
                </a-col>

                <a-col :span="24" :md="18" class="header-control">
                    <a-dropdown :trigger="['click']" overlayClassName="header-notifications-dropdown" :getPopupContainer="() => wrapper"></a-dropdown>
                    <a-button type="link" class="sidebar-toggler" @click="$emit('toggleSidebar', ! sidebarCollapsed) , resizeEventHandler()">
                        <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z"/></svg>
                    </a-button>
                </a-col>
            </a-row>
        </a-layout-header>
    </component>
</template>

<script>

export default ({
    props: {
        // Header fixed status.
        navbarFixed: {
            type: Boolean,
            default: false
        },

        // Sidebar collapsed status.
        sidebarCollapsed: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            // Fixed header/sidebar-footer ( Affix component ) top offset.
            top: 0,

            // Search input loading status.
            searchLoading: false,

            // The wrapper element to attach dropdowns to.
            wrapper: document.body
        }
    },
    methods: {
        resizeEventHandler() {
            this.top = this.top ? 0 : -0.01
            // To refresh the header if the window size changes.
            // Reason for the negative value is that it doesn't activate the affix unless
            // scroller is anywhere but the top of the page.
        },
        onSearch(value) {
        }
    },
    mounted: function() {
        // Set the wrapper to the proper element, layout wrapper.
        this.wrapper = document.getElementById('layout-dashboard')
    },
    created() {
        // Registering window resize event listener to fix affix elements size
        // error while resizing.
        window.addEventListener('resize', this.resizeEventHandler)
    },
    destroyed() {
        // Removing window resize event listener.
        window.removeEventListener('resize', this.resizeEventHandler)
    }
})

</script>
