<template>
    <div class="app-container">
        <div id="tim" class="byted-weektime">
            <div class="calendar">
                <div id="box">
                    <div id="kuang" :style="{width:kuangObj.width+'px',height:kuangObj.height+'px',top:kuangObj.top+'px',left:kuangObj.left+'px',bottom:kuangObj.bottom+'px',right:kuangObj.right+'px'}"></div>
                    <table class="calendar-table" style="width:800px">
                        <thead class="calendar-head"><tr>
                            <th rowspan="6" class="week-td">Day/Time</th>
                            <th colspan="12">09:00 - 22:00</th>
                        <tr>
                            <td colspan="1" v-for="index in tableHeader">{{index}}</td>
                        </tr>
                        </thead>
                        <tbody id="tableBody">
                        <tr>
                            <td>Monday</td>
                            <td @mousedown.prevent="handleMouseDown(i,0)" @mouseup.prevent="handleMouseUp(i,0)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[0]"></td>
                        </tr>
                        <tr>
                            <td>Tuesday</td>
                            <td @mousedown.prevent="handleMouseDown(i,1)" @mouseup.prevent="handleMouseUp(i,1)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[1]"></td>
                        </tr>
                        <tr>
                            <td>Wednesday</td>
                            <td @mousedown.prevent="handleMouseDown(i,2)" @mouseup.prevent="handleMouseUp(i,2)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[2]"></td>
                        </tr>
                        <tr>
                            <td>Thursday</td>
                            <td @mousedown.prevent="handleMouseDown(i,3)" @mouseup.prevent="handleMouseUp(i,3)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[3]"></td>
                        </tr>
                        <tr>
                            <td>Friday</td>
                            <td @mousedown.prevent="handleMouseDown(i,4)" @mouseup.prevent="handleMouseUp(i,4)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[4]"></td>
                        </tr>
                        <tr>
                            <td>Saturday</td>
                            <td @mousedown.prevent="handleMouseDown(i,5)" @mouseup.prevent="handleMouseUp(i,5)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[5]"></td>
                        </tr>
                        <tr>
                            <td>Sunday</td>
                            <td @mousedown.prevent="handleMouseDown(i,6)" @mouseup.prevent="handleMouseUp(i,6)" class="calendar-atom-time" :class="item.class" v-for="(item,i) in rowUnit[6]"></td>
                        </tr>

                        <tr>
                            <td colspan="49" class="td-table-tip">
                                <div class="clearfix">
                                    <span class="pull-left tip-text">click to pick a time </span> <a @click="clear" class="pull-right"> Clear</a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                          <td colspan="49" class="timeContent">
                            <div v-for="(item,index) in timeStr" v-show="item.length">
                              <span>{{weekDate[index+1]}}: </span>
                              <strong><span>{{item}}</span></strong>
                            </div>
                          </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'TimePicker',
    props: ['timmer'],
    data() {
        Array.prototype.remove = function(varElement)
        {
            var numDeleteIndex = -1;
            for (var i=0; i<this.length; i++)
            {
                // 严格比较，即类型与数值必须同时相等。
                if (this[i] === varElement)
                {
                    this.splice(i, 1);
                    numDeleteIndex = i;
                    break;
                }
            }
            return numDeleteIndex;
        };
        return {
            tableHeader:['09:00-10:00','10:00-11:00','11:00-12:00','12:00-13:00','13:00-14:00','14:00-15:00','15:00-16:00','16:00-17:00','17:00-18:00','18:00-19:00','19:00-20:00','20:00-21:00','21:00-22:00'],
            weekDate:{'1':'Monday','2':'Tuesday','3':'Wednesday','4':'Thursday','5':'Friday','6':'Saturday','7':'Sunday'},
            rowUnit:[],
            timeContent:[{"arr":[]},{"arr":[]},{"arr":[]},{"arr":[]},{"arr":[]},{"arr":[]},{"arr":[]}],
            timeSection:[],
            timeStr:[],
            beginDay:0,
            beginTime:0,
            downEvent:false,
            kuangObj:{
                width:0,
                height:0,
                top:0,
                left:0,
                bottom:0,
                right:0,
                oldLeft:0,
                oldTop:0,
                flag:false
            }
        }
    },
    created(){
        this.init();
    },
    watch:{
        timmer(newValue,oldValue){
            if(newValue){
                this.timeContent = newValue;
                this.historyData();
            }

        },
    },
    mounted(){
        this.timeContent = this.timmer;
        this.viewData();
        var oBox = document.getElementById("tim");

        var oDiv = document.getElementById("kuang");
        //鼠标按下，获取初始点
        oBox.onmousedown = function (ev) {
            ev = window.event || ev;
            //1.获取按下的点
            var x1 = ev.pageX - $("#tim").offset().left;
            var y1 = ev.pageY - $("#tim").offset().top;
            oBox.onmousemove = function (ev) {
                ev = window.event || ev;
                var x2 = ev.pageX - $("#tim").offset().left;
                var y2 = ev.pageY - $("#tim").offset().top;
                //3.设置div的样式
                oDiv.style.left = ((x2 > x1 ? x1 : x2)-10) +"px";
                oDiv.style.top = ((y2 > y1 ? y1 : y2)-10) +"px";
                oDiv.style.width = (Math.abs(x2-x1))+"px";
                oDiv.style.height =(Math.abs(y2-y1))+"px";
            }
            oBox.onmouseup = function (ev) {
                oBox.onmousemove = null;
                oDiv.style.left = 0 +"px";
                oDiv.style.top = 0 +"px";
                oDiv.style.width = 0+"px";
                oDiv.style.height =0+"px";
            }
            return false;  //解除在划动过程中鼠标样式改变的BUG
        }
        document.onmouseup = function () {
            oBox.onmousemove = null;
        }
    },
    methods: {
        // 回显父组件传递过来的数据
        historyData(){
            this.timeContent = this.timmer;
            this.viewData();
            var oBox = document.getElementById("tim");

            var oDiv = document.getElementById("kuang");
        },
        init(){
            for (let i = 0; i < 7; i++) {
                let arr = []
                for (let j = 0; j < 12; j++) {
                    arr.push({class:null,timeData:j});
                }
                this.rowUnit.push(arr);
                this.timeContent.push({arr:[]});
                this.timeSection.push([]);
                this.timeStr.push('');
            }
        },
        handleMouseDown(i,day){
            this.downEvent = true //按下时鼠标不在范围内则不算
            this.beginDay = day
            this.beginTime = i
        },
        handleMouseUp(i,day){
            //当点击事件是在table内才触发选取数据操作
            if (this.downEvent) {
                //选时间段
                let _this = this
                let begin = this.beginTime
                let start = begin <= i ? begin : i //x轴 起点
                let length = Math.abs(begin - i)
                let end = start + length           //x轴 终点

                let dayStart = this.beginDay <= day ? this.beginDay : day //y轴 起点
                let dayLength = Math.abs(this.beginDay - day)
                let dayEnd = dayStart + dayLength                         //y轴 终点
                //当框选范围内所有块都是选中状态时,执行反选
                function isAdd() {
                    for (let x = dayStart; x < dayEnd+1; x++) {
                        for (let y = start; y < end+1; y++) {
                            if(_this.rowUnit[x][y].class == null) return true
                        }
                    }
                    return false
                }

                if (isAdd()) {
                    //没选中的全都选上
                    for (let x = dayStart; x < dayEnd+1; x++) {
                        for (let y = start; y < end+1; y++) {
                            if(this.rowUnit[x][y].class == null) {
                                this.rowUnit[x][y].class = 'ui-selected'
                                this.timeContent[x].arr.push(this.rowUnit[x][y].timeData)
                            }
                        }
                    }
                }else{ //反选
                    for (let x = dayStart; x < dayEnd+1; x++) {
                        for (let y = start; y < end+1; y++) {
                            this.rowUnit[x][y].class = null
                            this.timeContent[x].arr.remove(this.rowUnit[x][y].timeData)
                        }
                    }
                }
                //过滤时间段,将临近的时间段合并
                this.filterTime(dayStart,dayEnd)
                //this.$emit('getTimmer',this.timeContent);
            }
            this.downEvent = false
        },
        filterTime(start,end) {  //选中的x,y坐标信息 x:0-47  y:0-6
            function sortCut(arr) {  //提取连续的数字
                var result = []
                arr.forEach(function (v, i) {
                    var temp = result[result.length - 1];
                    if (!i) {
                        result.push([v]);
                    } else if (v % 1 === 0 && v - temp[temp.length - 1] == 1) {
                        temp.push(v)
                    } else {
                        result.push([v])
                    }
                });
                return result
            }
            function toStr(num) {
                if (Number.isInteger(num)) {
                    let str = num<10 ? ('0'+num) : num.toString()
                    return str+':00'
                }else{
                    let str =Math.floor(num)<10 ? ('0'+Math.floor(num)) : Math.floor(num).toString()
                    return str+':30'
                }
            }
            function timeToStr(arr) {  //把数组转成方便人看到字符串
                let str = ''
                arr.forEach((arr,index)=>{
                    let str1 = ''
                    if (index == 0) {
                        str1 = toStr(arr[0]) + '~' + toStr(arr[1])
                    }else{
                        str1 = ' , ' + toStr(arr[0]) + '~' + toStr(arr[1])
                    }
                    str += str1
                })
                return str
            }
            //排序,分割成
            for (let i = start; i < end+1; i++) {
                let arr1 = sortCut(this.timeContent[i].arr.sort((a, b) => a - b))
                let arr2 = []
                arr1.forEach((arr)=>{   //转成带小数点的时间段,以及供前端显示的字符串
                    let arr3 = []
                    arr3.push(arr[0])
                    arr3.push(arr[arr.length-1])
                    arr2.push(arr3)
                })
                this.timeStr[i] = timeToStr(arr2)
                this.timeSection[i] = arr2
            }
        },
        clear(){
            this.rowUnit.forEach((item)=>{
                item.forEach((item1)=>{
                    item1.class=null
                })
            })
            this.timeContent.forEach((item)=>{
                item.arr = []
            })
            this.timeSection.forEach((item)=>{
                //赋值成空数组[]出问题
                item.length = 0
            })
            //遍历赋值成'',不管用
            this.timeStr.length = 0
            for (let i = 0; i < 7; i++) {
                this.timeStr.push('')
            }
            //this.initState = true
        },
        //画框操作
        kuangMove(){
            if(!this.kuangObj.flag) return
            if (this.downEvent) {
                let x1 = this.kuangObj.oldLeft
                let y1 = this.kuangObj.oldTop
                let x2 = event.layerX
                let y2 = event.layerY
                this.kuangObj.left = (x2 > x1 ? x1 : x2)
                this.kuangObj.top = (y2 > y1 ? y1 : y2)
                this.kuangObj.width = Math.abs(x2-x1)
                this.kuangObj.height =Math.abs(y2-y1)
            }
        },
        kuangDown(){
            this.kuangObj.flag = true
            this.kuangObj.oldLeft = event.layerX
            this.kuangObj.oldTop = event.layerY
        },
        kuangUp(){
            this.kuangObj.flag = false
            this.clearDragData()
        },
        kuangLeave(){
            this.kuangObj.flag = false
            this.clearDragData()
        },
        clearDragData(){
            for(let prop in this.kuangObj){
                this.kuangObj[prop] = 0
            }
        },
        // 回显数据
        viewData(){
            for(let i=0;i<this.timeContent.length;i++){
                if(this.timeContent[i].arr.length > 0){
                    let temp = this.timeContent[i].arr;
                    for(let j=0;j<temp.length;j++){
                        for(let z=0;z<=12;z++){
                            if(z == temp[j]){
                                this.rowUnit[i][z].class = 'ui-selected';
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
</script>
<style scoped>
.byted-weektime .calendar{-webkit-user-select:none;position:relative;display:inline-block}
/*.byted-weektime .calendar .schedule{background:#2F88FF;width:0;height:0;position:fixed;display:none;top:0;left:0;pointer-events:none;-webkit-transition:all 400ms ease;-moz-transition:all 400ms ease;-ms-transition:all 400ms ease;transition:all 400ms ease}*/
.byted-weektime .calendar .calendar-table{border-collapse:collapse;border-radius:4px}
.byted-weektime .calendar .calendar-table tr .calendar-atom-time:hover{background:#ccc}
.byted-weektime .calendar .calendar-table tr .ui-selected{background:#2F88FF}
.byted-weektime .calendar .calendar-table tr .ui-selected:hover{background:#2F88FF}
.byted-weektime .calendar .calendar-table tr,.byted-weektime .calendar .calendar-table td,.byted-weektime .calendar .calendar-table th{border:1px solid #ccc;font-size:12px;text-align:center;min-width:11px;line-height:1.8em;-webkit-transition:background 200ms ease;-moz-transition:background 200ms ease;-ms-transition:background 200ms ease;transition:background 200ms ease}
.byted-weektime .calendar .calendar-table tbody tr{height:30px}
.byted-weektime .calendar .calendar-table tbody tr td:first-child{background:#F8F9FA}
.byted-weektime .calendar .calendar-table thead th,.byted-weektime .calendar .calendar-table thead td{background:#F8F9FA}
.byted-weektime .calendar .calendar-table .td-table-tip{line-height:2.4em;padding:0 12px 0 19px;background:#fff !important}
.byted-weektime .calendar .calendar-table .td-table-tip .clearfix{height:46px;line-height:46px}
.byted-weektime .calendar .calendar-table .td-table-tip .pull-left{font-size:14px;color:#333333}
.byted-weektime .week-td{width:75px;padding:20px 0}
.byted-weektime a{cursor:pointer;color:#2F88FF;font-size:14px}
#kuang{position: absolute;background-color: blue;opacity: 0.3;}
#box{position: relative;}
</style>


