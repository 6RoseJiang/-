<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo" />
      <a-menu
          v-model:selectedKeys="selectedKeys"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">index</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content style="padding: 0 50px">
      <a-row>
        <a-col :span="24">
          <a-statistic title="Real-time number of people" :value="32"/>
        </a-col>
      </a-row>
      <a-divider style="height: 2px; background-color: #057cb3" />
        <a-radio-group v-model:value="size" style="right: -600px">
          <a-radio-button value="large" @click="handleDayHref">Week</a-radio-button>
          <a-radio-button value="default" @click="handleWeekHref()">Day</a-radio-button>
        </a-radio-group>
      <a-carousel ref="aCarousel"  :after-change="onChange">
        <div><h3>
          <a-row>
            <a-col :span="12">
              <div id="lineChart" style="width: 700px; height: 493px"></div>
            </a-col>
            <a-col :span="12">
              <div id="pieChart" style="width: 700px; height: 493px"></div>
            </a-col>
          </a-row>


        </h3></div>
        <div><h3>
              <div id="barChart" style="width: 1400px; height: 493px"></div>
        </h3></div>
        <div><h3>
          3
<!--          多加一个...  这是他那个组件的问题 -->
        </h3></div>
      </a-carousel>
    </a-layout-content>
<!--    <a-layout-footer style="text-align: center">-->
<!--&lt;!&ndash;    <div id="barChart" style="width: 1400px; height: 485px"></div>//放在这里就能正常显示&ndash;&gt;-->
<!--    </a-layout-footer>-->
  </a-layout>
</template>
<script>
import {defineComponent, reactive, ref, toRaw, watch, onMounted} from 'vue';
import * as echarts from 'echarts';
import {detailFlow, totalFlow} from "@/api/api";
export default defineComponent({
  setup() {
    onMounted(()=>{
      init()
    })

    let currentDay = new Date().toDateString();
    console.log(currentDay)
    function init(){
      const data = [{"id":'1', "day":currentDay}]
        // {"id":'2', "day":"Tuesday"},
        // {"id":'3', "day":"Wednesday"},
        // {"id":'4', "day":"Thursday"},
        // {"id":'5', "day":"Friday"},
        // {"id":'6', "day":"Saturday"},
        // {"id":'7', "day":"Sunday"}];//把你要传的id 放到这里


      const myLineChart = echarts.init(document.getElementById('lineChart'));
      const myPieChart = echarts.init(document.getElementById('pieChart'));
      const numbers = reactive([]); //数组
      const day = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
      const arr= [];


      function getTotal(id) {
        return totalFlow(id,null).then(res => {
          numbers.push(res.data.data.totalNumber) // 这一步改变了numbers
        })
      }
      data.map((item)=>{ // 这是一个循环 循环ids 这个数组
        //item 指 3   2  数组中的每一个  ids = ['3','2'];
        //  工作里最好不要这么操作..  会被喷的  正常情况下是 让前端把data 那个数组整个传给后端  然后后端返回一个列表数据
        getTotal(item.id)
      })

      // watch 监听了 numbers numbers 改变时会触发(newVal, oldVal)=>{ } 这个函数
      watch(numbers,(newVal, oldVal)=>{ // 监听了 number 的变化  当number 变化后 执行 这个函数
        //第一个参数是  newVal  numbers 这个值更新后的数据   第二参数是 oldVal 更新前的数据
        myLineChart.setOption({
          title: {

          },
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              // data: newVal,
              data: [40, 50, 55, 45, 25, 30, 43],
              type: 'line'
            }
          ]
        });


        const Data = toRaw(numbers);

        Data.forEach((item, i) => {
          arr.push({
            value: item,
            name: day[i]
          })
        })

        const arr2 = arr;
        console.log(arr2)


        myPieChart.setOption({
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: 'center'
          },
          series: [
            {
              name: 'Flow From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 40,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              // data: arr2
              data: [{ value: 40, name: 'Monday' },
                     { value: 50, name: 'Tuesday' },
                     { value: 55, name: 'Wednesday' },
                     { value: 45, name: 'Thursday' },
                     { value: 25, name: 'Friday' },
                     { value: 30, name: 'Saturday' },
                     { value: 43, name: 'Sunday' }]
            }
          ]
        })

      })

    }
    function initBarChart(){
      const myBarChart = echarts.init(document.getElementById('barChart'));
      const days = reactive([]);
      const dataDays = ['11', '12', '13', '14']
      const dataAxis = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', "22:00", "23:00", "24:00"]
      const dataBar = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220, 190, 130, 230, 150, 250];
      const yMax = 500;

      const dataShadow = [];
      for (let i = 0; i < dataDays.length; i++) {
        dataShadow.push(yMax);
      }

      myBarChart.setOption({
        xAxis: {
          data: dataAxis,
          axisLabel: {
            inside: true,
            color: '#fff'
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          z: 10
        },
        yAxis: {
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#999'
          }
        },
        dataZoom: [
          {
            type: 'inside'
          }
        ],
        series: [
          {
            type: 'bar',
            showBackground: true,
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: '#83bff6' },
                { offset: 0.5, color: '#188df0' },
                { offset: 1, color: '#188df0' }
              ])
            },
            emphasis: {
              itemStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: '#2378f7' },
                  { offset: 0.7, color: '#2378f7' },
                  { offset: 1, color: '#83bff6' }
                ])
              }
            },
            data: dataBar
          }
        ]
      })
      const zoomSize = 6;
      myBarChart.on('click', function (params) {
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myBarChart.dispatchAction({
          type: 'dataZoom',
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue:
              dataAxis[Math.min(params.dataIndex + zoomSize / 2, dataBar.length - 1)]
        });
      })
    }

    const aCarousel = ref(null)

    const handleDayHref = () => {
      //aCarousel  在dom 元素上写上 对应的名字 或者写一个函数 将对应的参数赋值给 aCarousel  你就能拿到这个元素
      // 上面这个是vue 提供的方法 方便你操作dom
      aCarousel.value.goTo(0) // goTo 是 antd 走马灯提供的方法  两个参数 一个是你要跳转的那一页 一个是是否有动画
      // onChange(0) // 你这个方法不管用的的原因是 因为这个方法只是在改变对应页数之后执行的 本身和改变页数没有关系
    }
    const handleWeekHref =() => {
      aCarousel.value.goTo(1)
      // nextTick(()=>{
      //   initBarChart()
      // })

      // onChange(1)
    }

    const onChange = current => {
      console.log(current,'current')
      initBarChart()

    };

    const number = reactive({
      inNumber: null,
      outNumber: null,
      totalNumber: null,
    });

    // const number = reactive({
    //   // inNumber: null,
    //   // outNumber: null,
    //   TotalNumber: null,
    // });

    function getTotal(id) {
      totalFlow(id,null).then(res => {
        number.totalNumber = res.data.data.totalNumber
      })
      return Number(number.totalNumber)
    }

    function getIn(id) {
      detailFlow(id,null).then(res => {
        number.inNumber = res.data.data.inNumber
      })
      return Number(number.inNumber)
    }

    function getOut(id) {
      detailFlow(id,null).then(res => {
        number.outNumber = res.data.data.outNumber
      })
      return Number(number.outNumber)
    }

    getTotal(2);
    getIn(2);
    getOut(2);

    return {
      aCarousel,
      number,
      onChange,
      handleDayHref,
      handleWeekHref,
      selectedKeys: ref(['1']),
      size: ref('large')
    };
  },
  mounted() {

  },
  methods: {

  },
});
</script>
<style scoped>

</style>