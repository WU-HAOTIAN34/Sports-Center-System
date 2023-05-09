<!--基础版，用户使用，一次只能选一个，最后在session里存一个数-->
<template>
    <div>
      <table class="calendar-table">
        <thead>
          <tr >
              <th :colspan="4"></th>
              <th :colspan="4" :id="100">1</th>
              <th :colspan="4" :id="110">2</th>
              <th :colspan="4" :id="120">3</th>
              <th :colspan="4" :id="130">4</th>
          </tr>
        </thead>
          <tr v-for="(index,i) in list" :key="i">
              <th :colspan="4">{{ index+9+':00 - ' + (index+10)+':00' }}</th>
              <th :colspan="4" :id="index" @click="isClick(index)">&nbsp;&nbsp;&nbsp;</th>
              <th :colspan="4" :id="index+10" @click="isClick(index+10)">&nbsp;&nbsp;&nbsp;</th>
              <th :colspan="4" :id="index+20" @click="isClick(index+20)">&nbsp;&nbsp;&nbsp;</th>
              <th :colspan="4" :id="index+30" @click="isClick(index+30)">&nbsp;&nbsp;&nbsp;</th>
          </tr>
      </table>
    </div>
</template>

<script>

export default {
    components: {
    },
    props: {
        data: {
            type: Array,
            // eslint-disable-next-line vue/require-valid-default-prop
            default: []
        }
    },
    data() {
        return {
            list: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            chosen: -1,
            book_date: ['', '1', '1', '1']
        }
    },
    beforeCreate() {
        this.chosen = -1
    },
    mounted() {
        for (let i = 0; i < 40; i++) {
            const change = document.getElementById(i)
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
        isClick(id) {
            if (this.data[id].open !== 0 && this.data[id].free !== 0) {
                if (this.chosen === -1) {
                    this.chosen = id
                    const change = document.getElementById(id)
                    change.style.backgroundColor = 'green'
                } else if (this.chosen === id) {
                    this.chosen = -1
                    const change = document.getElementById(id)
                    change.style.backgroundColor = 'white'
                }
                sessionStorage.setItem('chosen', this.data[id].id)
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

</style>

<!--用户进阶版，一次只能选多个，最后在session里存一个数组-->
<!--<template>-->
<!--    <div>-->
<!--        <table class="calendar-table">-->
<!--            <thead>-->
<!--            <tr>-->
<!--                <th :colspan="4"></th>-->
<!--                <th :colspan="4" :id="100">1</th>-->
<!--                <th :colspan="4" :id="110">2</th>-->
<!--                <th :colspan="4" :id="120">3</th>-->
<!--                <th :colspan="4" :id="130">4</th>-->
<!--            </tr>-->
<!--            </thead>-->
<!--            <tr v-for="(index, i) in list" :key="i">-->
<!--                <th :colspan="4">{{ index+9+':00 - ' + (index+10)+':00' }}</th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index"-->
<!--                    @click="isClick(index)"-->
<!--                    :class="{'selected-cell': chosen.includes(index)}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+10"-->
<!--                    @click="isClick(index+10)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+10)}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+20"-->
<!--                    @click="isClick(index+20)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+20)}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+30"-->
<!--                    @click="isClick(index+30)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+30)}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--            </tr>-->
<!--        </table>-->
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
<!-- Authors Table Card -->
<!--管理员版本，点击在灰色和红色切换，最后在session里存数组-->
<!--<template>-->
<!--    <div>-->
<!--        <table class="calendar-table">-->
<!--            <thead>-->
<!--            <tr>-->
<!--                <th :colspan="4"></th>-->
<!--                <th :colspan="4" :id="100">1</th>-->
<!--                <th :colspan="4" :id="110">2</th>-->
<!--                <th :colspan="4" :id="120">3</th>-->
<!--                <th :colspan="4" :id="130">4</th>-->
<!--            </tr>-->
<!--            </thead>-->
<!--            <tr v-for="(index, i) in list" :key="i">-->
<!--                <th :colspan="4">{{ index+9+':00 - ' + (index+10)+':00' }}</th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index"-->
<!--                    @click="isClick(index)"-->
<!--                    :class="{'selected-cell': chosen.includes(index), 'gray-cell': data[index].open === 0, 'red-cell': data[index].free === 0}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+10"-->
<!--                    @click="isClick(index+10)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+10), 'red-cell': data[index+10].free === 0, 'gray-cell': data[index+10].open === 0}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+20"-->
<!--                    @click="isClick(index+20)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+20), 'red-cell': data[index+10].free === 0, 'gray-cell': data[index+10].open === 0}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--                <th-->
<!--                    :colspan="4"-->
<!--                    :id="index+30"-->
<!--                    @click="isClick(index+30)"-->
<!--                    :class="{'selected-cell': chosen.includes(index+30), 'red-cell': data[index+10].free === 0, 'gray-cell': data[index+10].open === 0}"-->
<!--                >-->
<!--                    &nbsp;&nbsp;&nbsp;-->
<!--                </th>-->
<!--            </tr>-->
<!--        </table>-->
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
<!--            list: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],-->
<!--            chosen: [], // 改成数组形式-->
<!--            book_date: ['', '1', '1', '1'],-->
<!--            open: []-->
<!--        }-->
<!--    },-->
<!--    mounted() {-->
<!--        for (let i = 0; i < 40; i++) {-->
<!--            const change = document.getElementById(i)-->
<!--            open[i] = this.data[i].open-->
<!--            if (this.data[i].open === 0) {-->
<!--                change.classList.add('gray-cell')-->
<!--            } else if (this.data[i].free === 0) {-->
<!--                change.classList.add('red-cell')-->
<!--            } else {-->
<!--                change.classList.remove('gray-cell', 'red-cell')-->
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
<!--            if (!(this.data[id].open === 1 && this.data[id].free === 0)) {-->
<!--                console.log(id)-->
<!--                if (open[id] === 0) { // 如果该单元格未被预定，切换颜色并更新 booked 属性-->
<!--                    open[id] = 1-->
<!--                    const change = document.getElementById(id)-->
<!--                    change.style.backgroundColor = 'white'-->
<!--                    // change.classList.remove('gray-cell')-->
<!--                    // change.classList.add('white-cell')-->
<!--                } else if (open[id] === 1) { // 如果该单元格已被预定，切换颜色并更新 booked 属性-->
<!--                    open[id] = 0-->
<!--                    const change = document.getElementById(id)-->
<!--                    // change.classList.remove('white-cell')-->
<!--                    // change.classList.add('gray-cell')-->
<!--                    change.style.backgroundColor = 'gray'-->
<!--                }-->
<!--                const index = this.chosen.indexOf(id)-->
<!--                if (index > -1) { // 如果该单元格已经在 chosen 数组中，从 chosen 数组中移除-->
<!--                    this.chosen.splice(index, 1)-->
<!--                } else { // 如果该单元格不在 chosen 数组中，将其添加到 chosen 数组中-->
<!--                    this.chosen.push(id)-->
<!--                }-->
<!--                console.log(this.chosen)-->
<!--                sessionStorage.setItem('chosen', JSON.stringify(this.chosen)) // 存储所有选中的单元格 ID-->
<!--            }-->
<!--        }-->
<!--    }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.white-cell {-->
<!--    background-color: white !important;-->
<!--}-->
<!--.gray-cell {-->
<!--    background-color: gray !important;-->
<!--}-->
<!--.red-cell {-->
<!--    background-color: red !important;-->
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
