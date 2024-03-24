



<template>
  <div class="home-view">
    <!-- Left Section - Introduction -->
    <div class="left-section">
      <div class="intro">冰酒销售商店</div>
      <div class="subtitle">品质保证，丰富口味</div>
      <div class="content">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎光临冰酒销售商店，我们致力于提供最优质的冰酒产品。我们的酒庄拥有丰富的经验和传统的酿酒工艺，保证为您带来口感纯正、香醇浓郁的冰酒。</p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无论是红冰酒、白冰酒、绿冰酒还是橙冰酒，我们都有广泛的选择，满足您不同口味的需求。我们采用最新的酿酒技术，确保每一瓶冰酒都是高品质的享受。</p>
      </div>

      <div class="about-us-box">
        关于我们
      </div>
    </div>

    <!-- Right Section - Four Images -->
    <div class="right-section">
      <div class="demo-image__preview">
        <el-image
            style="width: 200px; height: 200px"
            :src="url"
            :preview-src-list="srcList">
        </el-image>
      </div>
      <div class="demo-image__preview">
        <el-image
            style="width: 200px; height: 200px"
            :src="url"
            :preview-src-list="srcList">
        </el-image>
      </div>
      <div class="demo-image__preview">
        <el-image
          style="width: 200px; height: 200px"
          :src="url"
          :preview-src-list="srcList">
        </el-image>
      </div>
      <div class="demo-image__preview">
        <el-image
          style="width: 200px; height: 200px"
          :src="url"
          :preview-src-list="srcList">
        </el-image>
      </div>



    </div>
  </div>
</template>

<style scoped>
.home-view {
  display: flex;
  max-width: 1200px;
  padding: 20px;
  margin-right: 50px;
}

.left-section {
  flex: 1;
  padding: 20px;
}

p{
  color: #909cae;
}

.right-section {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  margin-left: 100px;
}
.about-us-box {
  width: 90px;
  text-align: center;
  bottom: 20px;
  left: 20px;
  padding: 10px;
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  border-radius: 4px;
}
.intro {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.subtitle {
  font-size: 18px;
  color: #3f5263;
  margin-bottom: 15px;
}

.content {
  margin-top: 30px;
  margin-bottom: 30px;
  line-height: 1.5;
  color: #555;
}

.image-container {
  width: 48%;
  margin-bottom: 20px;
}

.image-container img {
  width: 100%;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>



<script>
export default {
  data() {
    return {
      url: 'https://img2.baidu.com/it/u=427287467,3028414454&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=667',
      srcList: [
        'https://img2.baidu.com/it/u=427287467,3028414454&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=667',
        'https://img2.baidu.com/it/u=3686739208,1576509755&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=667'
      ]
    }
  }
}
</script>