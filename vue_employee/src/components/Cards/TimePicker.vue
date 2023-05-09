<template>
    <div>
        <a-button-group class="mb-24">
            <a-button @click="selectAll" type="primary">select all</a-button>
            <a-button @click="deselectAll">deselect all</a-button>
        </a-button-group>
        <table class="calendar-table">
            <thead>
            <tr>
                <th :colspan="4" ></th>
                <th v-for="i in timeList" :key="i" colspan="4">{{ i+9+':00 - ' + (i+10)+':00' }}</th>
            </tr>
            </thead>
            <tr v-for="(index, i) in vueneList" :key="i">
                <th :colspan="4">{{ headers[i] }}</th>
                <th v-for="j in timeList" :key="j"
                    :colspan="4"
                    :id="index*10+j"
                    @click="isClick(index*10+j)"
                >
                    &nbsp;&nbsp;&nbsp;
                </th>
            </tr>
        </table>
        <h5>After selecting time slots, click the button to open or close the time slots you choose</h5>
        <a-button-group>
            <a-button @click="setOpen">open</a-button>
            <a-button @click="setClose" type="danger">close</a-button>
        </a-button-group>
    </div>
</template>

<script>
import { setOpen, setClose } from '@/api/venueState'

export default {
    components: {
    },
    props: {
        data: {
            type: Array,
            // eslint-disable-next-line vue/require-valid-default-prop
            default: []
        },
        day: {
            type: Number,
            default: 0
        },
        headers: {
            type: Array,
            // eslint-disable-next-line vue/require-valid-default-prop
            default: []
        }
    },
    data() {
        return {
            timeList: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            vueneList: [],
            vueneLength: 0,
            timeLength: this.data.length,
            chosen: [], // 改成数组形式
            book_date: ['', '1', '1', '1'],
            open: []
        }
    },
    beforeMount () {
        this.venueLength = this.data.length / 10
        console.log(this.venueLength)
        for (let i = 0; i < this.venueLength; i++) {
            this.vueneList.push(i)
        }
        console.log(this.vueneList)
    },
    mounted() {
        for (let i = 0; i < this.timeLength; i++) {
            const change = document.getElementById(i)
            this.open[i] = 0
            // this.open[i] = this.data[i].open
            console.log(this.data[i].id)
            // this.data[i].open = 1
            if (this.data[i].open === 0) {
                change.style.backgroundColor = 'grey'
            } else if (this.data[i].free === 0) {
                change.style.backgroundColor = 'red'
            } else {
                console.log(change)
                change.style.backgroundColor = 'white'
            }
        }
        for (let j = 0; j < 4; j++) {
            const t = j * 10
            let dataString = this.data[t].begin
            if (dataString[5] === '0') {
                dataString = dataString[0] + dataString[1] + dataString[2] + dataString[3] +
                    dataString[4] + dataString[6] + dataString[7] + dataString[8] + dataString[9]
            } else {
                dataString = dataString[0] + dataString[1] + dataString[2] + dataString[3] + dataString[4] +
                    dataString[5] + dataString[6] + dataString[7] + dataString[8] + dataString[9]
            }

            dataString = dataString.replace('-', '.')
            dataString = dataString.replace('-', '.')
            const change = document.getElementById(t + 100)
            change.innerHTML = dataString
        }
        console.log(this.book_date)
        console.log(this.data)
    },
    methods: {
        selectAll() {
            for (let i = 0; i < this.timeLength; i++) {
                if (this.open[i] === 0 && !(this.data[i].open === 1 && this.data[i].free === 0)) { // 如果该单元格未被选择，切换颜色并更新 booked 属性
                    this.open[i] = 1
                    const change = document.getElementById(i)
                    // //
                    // // change.innerHTML = '已预定'
                    change.style.backgroundColor = 'green'
                    // this.$set(this.data[id], 'open', 1)
                    // change.class.remove('gray-cell')
                    // change.classList.add('white-cell')
                    this.chosen.push(i + 1)
                }
            }
        },
        deselectAll() {
            for (let i = 0; i < this.timeLength; i++) {
                if (this.open[i] === 1 && !(this.data[i].open === 1 && this.data[i].free === 0)) { // 如果该单元格被选择，切换颜色并更新 booked 属性
                    this.open[i] = 0
                    const change = document.getElementById(i)
                    if (this.data[i].open === 0) {
                        change.style.backgroundColor = 'grey'
                    } else {
                        change.style.backgroundColor = 'white'
                    }
                    // this.$set(this.data[id], 'open', 1)
                    // change.class.remove('gray-cell')
                    // change.classList.add('white-cell')
                }
                const index = this.chosen.indexOf(i + 1)
                if (index > -1) { // 如果该单元格已经在 chosen 数组中，从 chosen 数组中移除
                    this.chosen.splice(index, 1)
                }
            }
        },
        setOpen() {
            // const map = "{list='" + sessionStorage.getItem('chosen') + "'}"
            let map = this.chosen.toString()
            for (let i = 0; i < map.length; i++) {
                if (map[i] === ',') {
                    map = map.replace(',', ' ')
                }
            }
            map = map.replace('[', '')
            map = map.replace(']', '')
            console.log(this.chosen)
            setOpen(this.day, map).then((response) => {
                console.log(response)
                alert('Update Successfully!')
                this.$router.go(0)
            }).catch((error) => {
                console.log(error)
            })
        },
        setClose() {
            // const map = "{list='" + sessionStorage.getItem('chosen') + "'}"
            let map = this.chosen.toString()
            for (let i = 0; i < map.length; i++) {
                if (map[i] === ',') {
                    map = map.replace(',', ' ')
                }
            }
            map = map.replace('[', '')
            map = map.replace(']', '')
            console.log(this.chosen)
            setClose(this.day, map).then((response) => {
                console.log(response)
                alert('Update Successfully!')
                this.$router.go(0)
            }).catch((error) => {
                console.log(error)
            })
        },
        isClick(id) {
            if (!(this.data[id].open === 1 && this.data[id].free === 0)) {
                console.log(id)
                if (this.open[id] === 0) { // 如果该单元格未被选择，切换颜色并更新 booked 属性
                    this.open[id] = 1
                    const change = document.getElementById(id)
                    // //
                    // // change.innerHTML = '已预定'
                    change.style.backgroundColor = 'green'
                    // this.$set(this.data[id], 'open', 1)
                    // change.class.remove('gray-cell')
                    // change.classList.add('white-cell')
                } else if (this.open[id] === 1) { // 如果该单元格已被预定，切换颜色并更新 booked 属性
                    this.open[id] = 0
                    console.log(this.open[id])
                    const change = document.getElementById(id)
                    if (this.data[id].open === 0) {
                        change.style.backgroundColor = 'grey'
                    } else {
                        change.style.backgroundColor = 'white'
                    }
                    //
                    // // change.innerHTML = '可预定'
                    // // change.classList.add('gray-cell')
                    // change.style.backgroundColor = 'gray'
                    // this.$set(this.data[id], 'open', 0)
                }
                const index = this.chosen.indexOf(id + 1)
                if (index > -1) { // 如果该单元格已经在 chosen 数组中，从 chosen 数组中移除
                    this.chosen.splice(index, 1)
                } else { // 如果该单元格不在 chosen 数组中，将其添加到 chosen 数组中
                    this.chosen.push(id + 1)
                }
                // console.log(this.chosen)
                sessionStorage.setItem('chosen', JSON.stringify(this.chosen)) // 存储所有选中的单元格 ID
            }
        }
    }
}
</script>

<style>
a-col {
    border-collapse: collapse;
    border: 1px solid #dee4f5;
    background-color: gray;
}
.calendar-table {
    border-collapse: collapse;
}
.calendar-table th {
    vertical-align: inherit;
    font-weight: bold;
}
.calendar-table tr {
    height: 30px;
    width: 20px;
}
.calendar-table tr, td, th {
    user-select: none;
    border: 1px solid #dee4f5;
    text-align: center;
    min-width: 30px;
    line-height: 1.8em;
    transition: background 0.2s ease;
}
.white-cell {
    background-color: white !important;
}
.gray-cell {
    background-color: gray !important;
}
.red-cell {
    background-color: red !important;
}
.calendar-table {
    border-collapse: collapse;
}
.calendar-table th {
    vertical-align: inherit;
    font-weight: bold;
}
.calendar-table tr {
    height: 30px;
    width: 40px;
}
.calendar-table tr,
td,
th {
    user-select: none;
    border: 1px solid #dee4f5;
    text-align: center;
    min-width: 30px;
    line-height: 1.8em;
    transition: background 0.2s ease;
}
</style>

<!--用户进阶版，一次只能选多个，最后在session里存一个数组-->
<!--<template>-->
<!--    <div>-->
<!--        <table class="calendar-table">-->
<!--                        <thead>-->
<!--                        <tr>-->
<!--                            <th :colspan="4" ></th>-->
<!--                            <th v-for="i in timeList" :key="i" colspan="4">{{ i+9+':00 - ' + (i+10)+':00' }}</th>-->
<!--                        </tr>-->
<!--                        </thead>-->
<!--                        <tr v-for="(index, i) in vueneList" :key="i">-->
<!--                            <td :colspan="4">vuene</td>-->
<!--                            <th v-for="j in timeList" :key="j" :colspan="4" :id="index*10+j" @click="isClick(index*10+j)"> &nbsp;&nbsp;&nbsp;</th>-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+0" @click="isClick(index*10)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+1" @click="isClick(index*10+1)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+2" @click="isClick(index*10+2)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+3" @click="isClick(index*10+3)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+4" @click="isClick(index*10+4)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+5" @click="isClick(index*10+5)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td :colspan="4" :id="index*10+6" @click="isClick(index*10+6)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+7" @click="isClick(index*10+7)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td :colspan="4" :id="index*10+8" @click="isClick(index*10+8)"></td>&ndash;&gt;-->
<!--&lt;!&ndash;                            <td  :colspan="4" :id="index*10+9" @click="isClick(index*10+9)"></td>&ndash;&gt;-->

<!--                        </tr>-->
<!--        </table>-->
<!--                <table class="calendar-table">-->
<!--                    <thead>-->
<!--                    <tr>-->
<!--                        <th colspan="1"></th>-->
<!--                        <th v-for="i in timeList" :key="i" colspan="1">{{ i+9+':00 - ' + (i+10)+':00' }}</th>-->
<!--                    </tr>-->
<!--                    </thead>-->
<!--                    <tbody>-->
<!--                    &lt;!&ndash; 每一行都是一个 tr 元素 &ndash;&gt;-->
<!--                    <tr v-for="(index, i) in vueneList" :key="i">-->
<!--                        &lt;!&ndash; 每个单元格都是一个 td 元素 &ndash;&gt;-->
<!--                        <td :colspan="1" class="time-cell">vue</td>-->
<!--                        <td :colspan="1" :id="index*10" @click="isClick(index*10)" :class="{'selected-cell': chosen.includes(index*10+0)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+1" @click="isClick(index*10+1)" :class="{'selected-cell': chosen.includes(index*10+1)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+2" @click="isClick(index*10+2)" :class="{'selected-cell': chosen.includes(index*10+2)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+3" @click="isClick(index*10+3)" :class="{'selected-cell': chosen.includes(index*10+3)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+4" @click="isClick(index*10+4)" :class="{'selected-cell': chosen.includes(index*10+4)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+5" @click="isClick(index*10+5)" :class="{'selected-cell': chosen.includes(index*10+5)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+6" @click="isClick(index*10+6)" :class="{'selected-cell': chosen.includes(index*10+6)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+7" @click="isClick(index*10+7)" :class="{'selected-cell': chosen.includes(index*10+7)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+8" @click="isClick(index*10+8)" :class="{'selected-cell': chosen.includes(index*10+8)}" ></td>-->
<!--                        <td :colspan="1" :id="index*10+9" @click="isClick(index*10+9)" :class="{'selected-cell': chosen.includes(index*10+9)}" ></td>-->
<!--                    </tr>-->
<!--                    </tbody>-->
<!--                </table>-->
<!--&lt;!&ndash;        <table class="calendar-table">&ndash;&gt;-->
<!--&lt;!&ndash;            <thead>&ndash;&gt;-->
<!--&lt;!&ndash;            <tr>&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4"></th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4" :id="100">1</th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4" :id="110">2</th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4" :id="120">3</th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4" :id="130">4</th>&ndash;&gt;-->
<!--&lt;!&ndash;            </tr>&ndash;&gt;-->
<!--&lt;!&ndash;            </thead>&ndash;&gt;-->
<!--&lt;!&ndash;            <tr v-for="(index, i) in list" :key="i">&ndash;&gt;-->
<!--&lt;!&ndash;                <th :colspan="4">{{ index+9+':00 - ' + (index+10)+':00' }}</th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th&ndash;&gt;-->
<!--&lt;!&ndash;                    :colspan="4"&ndash;&gt;-->
<!--&lt;!&ndash;                    :id="index"&ndash;&gt;-->
<!--&lt;!&ndash;                    @click="isClick(index)"&ndash;&gt;-->
<!--&lt;!&ndash;                    :class="{'selected-cell': chosen.includes(index)}"&ndash;&gt;-->
<!--&lt;!&ndash;                >&ndash;&gt;-->
<!--&lt;!&ndash;                    &nbsp;&nbsp;&nbsp;&ndash;&gt;-->
<!--&lt;!&ndash;                </th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th&ndash;&gt;-->
<!--&lt;!&ndash;                    :colspan="4"&ndash;&gt;-->
<!--&lt;!&ndash;                    :id="index+10"&ndash;&gt;-->
<!--&lt;!&ndash;                    @click="isClick(index+10)"&ndash;&gt;-->
<!--&lt;!&ndash;                    :class="{'selected-cell': chosen.includes(index+10)}"&ndash;&gt;-->
<!--&lt;!&ndash;                >&ndash;&gt;-->
<!--&lt;!&ndash;                    &nbsp;&nbsp;&nbsp;&ndash;&gt;-->
<!--&lt;!&ndash;                </th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th&ndash;&gt;-->
<!--&lt;!&ndash;                    :colspan="4"&ndash;&gt;-->
<!--&lt;!&ndash;                    :id="index+20"&ndash;&gt;-->
<!--&lt;!&ndash;                    @click="isClick(index+20)"&ndash;&gt;-->
<!--&lt;!&ndash;                    :class="{'selected-cell': chosen.includes(index+20)}"&ndash;&gt;-->
<!--&lt;!&ndash;                >&ndash;&gt;-->
<!--&lt;!&ndash;                    &nbsp;&nbsp;&nbsp;&ndash;&gt;-->
<!--&lt;!&ndash;                </th>&ndash;&gt;-->
<!--&lt;!&ndash;                <th&ndash;&gt;-->
<!--&lt;!&ndash;                    :colspan="4"&ndash;&gt;-->
<!--&lt;!&ndash;                    :id="index+30"&ndash;&gt;-->
<!--&lt;!&ndash;                    @click="isClick(index+30)"&ndash;&gt;-->
<!--&lt;!&ndash;                    :class="{'selected-cell': chosen.includes(index+30)}"&ndash;&gt;-->
<!--&lt;!&ndash;                >&ndash;&gt;-->
<!--&lt;!&ndash;                    &nbsp;&nbsp;&nbsp;&ndash;&gt;-->
<!--&lt;!&ndash;                </th>&ndash;&gt;-->
<!--&lt;!&ndash;            </tr>&ndash;&gt;-->
<!--&lt;!&ndash;        </table>&ndash;&gt;-->

<!--    </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--    components: {-->
<!--    },-->
<!--    props: {-->
<!--        data: {-->
<!--            type: Array,-->
<!--            // eslint-disable-next-line vue/require-valid-default-prop-->
<!--            default: []-->
<!--        }-->
<!--    },-->
<!--    data() {-->
<!--        return {-->
<!--            timeList: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],-->
<!--            vueneList: [0, 1, 2, 3],-->
<!--            list: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],-->
<!--            chosen: [], // 改成数组形式-->
<!--            book_date: ['', '1', '1', '1']-->
<!--        }-->
<!--    },-->
<!--    mounted() {-->
<!--        for (let i = 0; i < 40; i++) {-->
<!--            const change = document.getElementById(i)-->
<!--            // this.data[i].open = 1;-->
<!--            if (this.data[i].open === 0) {-->
<!--                change.style.backgroundColor = 'grey'-->
<!--            } else if (this.data[i].free === 0) {-->
<!--                change.style.backgroundColor = 'red'-->
<!--            } else {-->
<!--                console.log(change)-->
<!--                change.style.backgroundColor = 'white'-->
<!--            }-->
<!--        }-->
<!--        for (let j = 0; j < 4; j++) {-->
<!--            const t = j * 10-->
<!--            let dataString = this.data[t].begin-->
<!--            if (dataString[5] === '0') {-->
<!--                dataString =-->
<!--                    dataString[0] +-->
<!--                    dataString[1] +-->
<!--                    dataString[2] +-->
<!--                    dataString[3] +-->
<!--                    dataString[4] +-->
<!--                    dataString[6] +-->
<!--                    dataString[7] +-->
<!--                    dataString[8] +-->
<!--                    dataString[9]-->
<!--            } else {-->
<!--                dataString =-->
<!--                    dataString[0] +-->
<!--                    dataString[1] +-->
<!--                    dataString[2] +-->
<!--                    dataString[3] +-->
<!--                    dataString[4] +-->
<!--                    dataString[5] +-->
<!--                    dataString[6] +-->
<!--                    dataString[7] +-->
<!--                    dataString[8] +-->
<!--                    dataString[9]-->
<!--            }-->

<!--            dataString = dataString.replace('-', '.')-->
<!--            dataString = dataString.replace('-', '.')-->
<!--            const change = document.getElementById(t + 100)-->
<!--            change.innerHTML = dataString-->
<!--        }-->
<!--        console.log(this.book_date)-->
<!--        console.log(this.data)-->
<!--    },-->
<!--    methods: {-->
<!--        isClick(id) {-->
<!--            console.log(id)-->
<!--            if (this.data[id].open !== 0 && this.data[id].free !== 0) {-->
<!--                if (!this.chosen.includes(id)) { // 如果该单元格未被选中，添加到 chosen 数组-->
<!--                    this.chosen.push(id)-->
<!--                    const change = document.getElementById(id)-->
<!--                    change.style.backgroundColor = 'green'-->
<!--                } else { // 如果该单元格已被选中，从 chosen 数组中删除该单元格 ID-->
<!--                    const index = this.chosen.indexOf(id)-->
<!--                    if (index > -1) {-->
<!--                        this.chosen.splice(index, 1)-->
<!--                    }-->
<!--                    const change = document.getElementById(id)-->
<!--                    change.style.backgroundColor = 'white'-->
<!--                }-->
<!--                sessionStorage.setItem('chosen', JSON.stringify(this.chosen)) // 存储所有选中的单元格 ID-->
<!--            }-->
<!--        }-->
<!--    }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.selected-cell {-->
<!--    background-color: green !important;-->
<!--}-->
<!--a-col {-->
<!--    border-collapse: collapse;-->
<!--    border: 1px solid #dee4f5;-->
<!--    background-color: gray;-->
<!--}-->
<!--.calendar-table {-->
<!--    border-collapse: collapse;-->
<!--}-->
<!--.calendar-table th {-->
<!--    vertical-align: inherit;-->
<!--    font-weight: bold;-->
<!--}-->
<!--.calendar-table tr {-->
<!--    height: 30px;-->
<!--    width: 20px;-->
<!--}-->
<!--.calendar-table tr,-->
<!--td,-->
<!--th {-->
<!--    user-select: none;-->
<!--    border: 1px solid #dee4f5;-->
<!--    text-align: center;-->
<!--    min-width: 30px;-->
<!--    line-height: 1.8em;-->
<!--    transition: background 0.2s ease;-->
<!--}-->
<!--</style>-->
