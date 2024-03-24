<template>
  <div class="data-container">
    <div class="header">
      <div class="total-orders">
        <span class="label">订单总数:</span>
        <span class="value">{{ totalOrders }}</span>
      </div>
      <div class="total-amount">
        <span class="label">订单金额:</span>
        <span class="value">{{ totalAmount }}</span>
      </div>
    </div>
    <div class="chart-container">
      <div ref="pieChart" class="pie-chart"></div>
      <div ref="barChart" class="bar-chart"></div>
    </div>
    <div class="bottom">
      <div class="bottom-type">
        <span class="label">当月订单分布情况</span>
      </div>
      <div class="bottom-month">
        <span class="label">年度销售数据</span>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  data() {
    return {
      totalOrders: 1200,
      totalAmount: '$150,000',
      barChartData: [150, 200, 120, 300, 180, 250, 400, 350, 300, 280, 220, 180],
      pieChartData: [
        { value: 300, name: '红冰酒' },
        { value: 200, name: '白冰酒' },
        { value: 150, name: '绿冰酒' },
        { value: 100, name: '橙冰酒' },
      ],
    };
  },
  mounted() {
    this.renderBarChart();
    this.renderPieChart();
  },
  methods: {
    renderBarChart() {
      const barChart = echarts.init(this.$refs.barChart);
      const option = {
        title: {
          show:true,
          // text: '月度订单总数统计',
        },
        legend: {
          data: ['订单总数'],
        },
        xAxis: {
          type: 'category',
          data: Array.from({ length: 12 }, (_, i) => i + 1),
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            name: '订单总数',
            type: 'bar',
            data: this.barChartData,
          },
        ],
      };
      barChart.setOption(option);
    },
    renderPieChart() {
      const pieChart = echarts.init(this.$refs.pieChart);
      const option = {
        title: {
          // text: '当月订单单类型分布',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['红冰酒', '白冰酒', '绿冰酒', '橙冰酒'],
        },
        tooltip: {
          trigger: 'item', // Set the tooltip to trigger on each item
          formatter: '{a} <br/>{b}: {c} ({d}%)', // Customize the tooltip content
        },
        series: [
          {
            name: '冰酒种类',
            type: 'pie',
            radius: '50%',
            center: ['50%', '60%'],
            data: this.pieChartData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
            },
          },
        ],
      };
      pieChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.data-container {
  width: 80%;
  margin: 20px auto;
}

.header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.total-orders,
.total-amount {
  font-size: 18px;
  font-weight: bold;
}

.bottom{
  display: flex;
  font-weight: bold;
  color: #464646;
}

.bottom-month,
.bottom-type{
  font-size: 16px;
  text-align: center;
  margin-left: 16%;
  margin-right: 20%;
}


.chart-container {
  margin-top: 50px;
  display: flex;
}

.bar-chart,
.pie-chart {
  width: 50%;
  height: 400px;
}
</style>
