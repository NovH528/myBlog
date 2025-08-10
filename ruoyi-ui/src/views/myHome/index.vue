<template>
  <div class="my-home-container">
    <!-- 动态背景容器 -->
    <div class="background-container">
      <!-- 新增：中性灰过渡层（用于缓冲色彩差异） -->
      <div class="transition-mask" :class="{ 'active': isFadingOut }"></div>

      <!-- 动态生成所有背景图片容器（替换原有的3个固定容器） -->
      <div
        class="background-image"
        v-for="(img, index) in backgroundList"
        :key="index"
        :class="{ 'active': activeBackgroundIndex === index, 'fade-out': isFadingOut && activeBackgroundIndex !== index }"
        :style="{ backgroundImage: `url(${img})` }"
      ></div>

      <!-- 顶部导航（保持不变） -->
      <div class="top-nav">
        <ul>
          <li>首页</li>
          <li>家</li>
          <li>随笔</li>
          <li>记录</li>
          <li>相册</li>
          <li>百宝箱</li>
          <li>留言</li>
          <li>联系我</li>
          <el-button @click="gotoAdminPage">后台首页</el-button>
          <li class="login-btn">登录</li>
        </ul>
      </div>

      <!-- 时间显示区域（保持不变） -->
      <div class="time-box">
        <div class="current-time">{{ formattedTime }}</div>
        <div class="current-date">{{ formattedDate }}</div>
        <div class="current-lunar">{{ lunarDate }}</div>
      </div>

      <!-- 搜索栏容器（样式完全不变，仅替换内部内容） -->
      <div class="search-box">
        <!-- 引入搜索组件，不添加额外样式 -->
        <SearchBox @search="handleSearch" />
      </div>
    </div>
  </div>
</template>

<script>
// 导入新的农历库（保持不变）
const { Lunar } = require('lunar-javascript');

// 导入搜索组件（新增）
import SearchBox from '@/components/SearchBox/index.vue';

export default {
  name: 'MyHome',
  components: { SearchBox }, // 注册组件（新增）
  data() {
    return {
      backgroundList: [
        require('@/assets/homepage/bg1.jpg'),
        require('@/assets/homepage/bg2.jpg'),
        require('@/assets/homepage/bg3.jpg')
      ],
      activeBackgroundIndex: 0, // 当前激活的背景图索引
      searchKeyword: '',
      backgroundTimer: null,    // 背景轮播定时器
      timeUpdateTimer: null,    // 时间更新定时器
      currentDateObj: new Date(), // 当前日期对象
      // 新增：控制旧图淡出状态
      isFadingOut: false
    }
  },
  computed: {
    // 时间、日期、农历计算（保持不变）
    formattedTime() {
      const date = this.currentDateObj;
      const hours = this.formatTime(date.getHours());
      const minutes = this.formatTime(date.getMinutes());
      const seconds = this.formatTime(date.getSeconds());
      return `${hours}:${minutes}:${seconds} ${date.getHours() >= 12 ? 'PM' : 'AM'}`;
    },
    formattedDate() {
      const date = this.currentDateObj;
      const year = date.getFullYear();
      const month = this.formatTime(date.getMonth() + 1);
      const day = this.formatTime(date.getDate());
      const week = ['日', '一', '二', '三', '四', '五', '六'][date.getDay()];
      return `${year}年 ${month}月 ${day}日 周${week}`;
    },
    lunarDate() {
      try {
        const lunar = Lunar.fromDate(this.currentDateObj);
        const year = lunar.getYearInChinese();
        const month = lunar.getMonthInChinese();
        const day = lunar.getDayInChinese();
        if (!year || !month || !day) {
          return '农历信息获取失败';
        }
        return `${year}年 ${month}月${day}`;
      } catch (error) {
        console.error('农历计算错误:', error);
        return '农历计算失败';
      }
    }
  },
  created() {
    this.initBackground();
    this.startBackgroundLoop();
  },
  mounted() {
    // 时间更新定时器（保持不变）
    this.timeUpdateTimer = setInterval(() => {
      this.currentDateObj = new Date();
    }, 1000);
  },
  beforeDestroy() {
    // 清除定时器（保持不变）
    if (this.backgroundTimer) clearInterval(this.backgroundTimer);
    if (this.timeUpdateTimer) clearInterval(this.timeUpdateTimer);
  },
  methods: {
    // 工具方法（保持不变）
    formatTime(num) {
      return num < 10 ? `0${num}` : num;
    },
    initBackground() {
      this.activeBackgroundIndex = Math.floor(Math.random() * this.backgroundList.length);
    },
    // 核心修改：背景切换逻辑增加淡出延迟
    startBackgroundLoop() {
      // 每10秒切换一次（测试用，实际可改回10分钟：10*60*1000）
      this.backgroundTimer = setInterval(() => {
        this.switchBackground();
      }, 2 * 10 * 1000);
    },
    // 新增：平滑切换背景的方法
    switchBackground() {
      // 1. 开始过渡：显示中性灰层，旧图开始淡出并降低对比度
      this.isFadingOut = true;

      // 2. 延迟1秒（过渡中期）后切换图片索引
      setTimeout(() => {
        this.activeBackgroundIndex = (this.activeBackgroundIndex + 1) % this.backgroundList.length;
      }, 1000);

      // 3. 延迟3秒（过渡结束）后隐藏中性灰层，恢复正常显示
      setTimeout(() => {
        this.isFadingOut = false;
      }, 3000);
    },
    // 跳转后台方法（保持不变）
    gotoAdminPage() {
      const adminRoute = '/index';
      this.$router.push(adminRoute).catch(err => {
        console.error('后台首页跳转失败:', err);
        this.$message.warning('后台首页不存在或无权限');
      });
    },
// 搜索事件处理（新增，接收搜索关键词）
    handleSearch(keyword) {
      console.log('搜索内容:', keyword);
      // 如需跳转结果页，添加：
      this.$router.push({ path: '/search/result', query: { keyword } });
    }

  }
}
</script>

<style scoped>
.my-home-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  position: relative;
}

.background-container {
  width: 100%;
  height: 100%;
  position: relative;
  color: #fff;
  overflow: hidden;
}

/* 背景图片样式：增加亮度和对比度过渡 */
.background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  opacity: 0;
  /* 新增：亮度和对比度过渡，总时长3秒 */
  transition:
    opacity 3s ease-in-out,
    filter 3s ease-in-out;
  filter: brightness(1) contrast(1); /* 默认状态 */
  z-index: 1;
}

/* 激活状态：新图淡入并逐渐恢复亮度/对比度 */
.background-image.active {
  opacity: 1;
  filter: brightness(0.8) contrast(0.9); /* 初始低对比状态 */
  z-index: 2;
}

/* 淡出状态：旧图淡出并降低亮度/对比度 */
.background-image.fade-out {
  opacity: 0;
  filter: brightness(0.7) contrast(0.8); /* 弱化旧图 */
  z-index: 1;
}

/* 新增：中性灰过渡层（缓冲色彩差异） */
.transition-mask {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(128, 128, 128, 0.2); /* 半透明灰色 */
  opacity: 0;
  transition: opacity 3s ease-in-out;
  z-index: 3; /* 位于图片上方 */
  pointer-events: none; /* 不影响点击交互 */
}

/* 过渡时显示中性灰层 */
.transition-mask.active {
  opacity: 1;
}


/* 导航栏样式（保持不变） */
.top-nav {
  position: absolute;
  top: 20px;
  right: 50px;
  display: flex;
  justify-content: flex-end;
  width: 100%;
  z-index: 10;
}

.top-nav ul {
  list-style: none;
  display: flex;
  gap: 20px;
  font-size: 16px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0;
  padding: 0;
}

.admin-btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #409eff;
  border-radius: 4px;
  color: #409eff;
}

.admin-btn:hover {
  background-color: rgba(64, 158, 255, 0.2);
}

.login-btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #fff;
  border-radius: 4px;
}

/* 时间显示区域（保持不变） */
.time-box {
  position: absolute;
  top: 25%;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  width: 100%;
  z-index: 10;
}

.current-time {
  font-size: 64px;
  margin-bottom: 15px;
  text-shadow: 2px 2px 8px rgba(0,0,0,0.7);
}

.current-date {
  font-size: 24px;
  text-shadow: 2px 2px 8px rgba(0,0,0,0.7);
  margin-bottom: 10px;
}

.current-lunar {
  font-size: 22px;
  text-shadow: 2px 2px 8px rgba(0,0,0,0.7);
}

/* 搜索栏样式（保持不变） */
.search-box {
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translateX(-50%);
  width: 700px;
  max-width: 90%;
  display: flex;
  align-items: center;
  z-index: 10;
}

.search-box input {
  width: 100%;
  padding: 18px 45px 18px 25px;
  border: none;
  border-radius: 30px;
  outline: none;
  font-size: 18px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.2);
}

.search-box .el-icon-search {
  position: absolute;
  right: 20px;
  cursor: pointer;
  font-size: 22px;
  color: #666;
}

/* 响应式优化（保持不变） */
@media (max-width: 768px) {
  .current-time {
    font-size: 42px;
  }
  .current-date {
    font-size: 18px;
  }
  .current-lunar {
    font-size: 16px;
  }
  .top-nav ul {
    font-size: 14px;
    gap: 10px;
  }
  .search-box {
    width: 85%;
    top: 50%;
  }
  .search-box input {
    font-size: 16px;
    padding: 15px 40px 15px 20px;
  }
}
</style>
