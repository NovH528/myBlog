<template>
  <div class="my-home-container">
    <!-- 动态背景容器 -->
    <div
      class="background"
      :style="{
        backgroundImage: `url(${currentBackground})`,
        transition: 'background-image 1s ease-in-out'
      }"
    >
      <!-- 顶部导航 -->
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
          <li class="admin-btn" @click="gotoAdminPage">后台管理</li>
          <li class="login-btn">登录</li>
        </ul>
      </div>

      <!-- 时间显示区域 -->
      <div class="time-box">
        <div class="current-time">{{ formattedTime }}</div>
        <div class="current-date">{{ formattedDate }}</div>
        <div class="current-lunar">{{ lunarDate }}</div>
      </div>

      <!-- 搜索框 -->
      <div class="search-box">
        <input
          type="text"
          placeholder="搜点什么吧"
          v-model="searchKeyword"
          @keyup.enter="handleSearch"
        >
        <i class="el-icon-search" @click="handleSearch"></i>
      </div>
    </div>
  </div>
</template>

<script>
// 尝试导入农历库，处理可能的导入失败
import LunarCalendar from 'lunar-calendar';

export default {
  name: 'MyHome',
  data() {
    return {
      backgroundList: [
        require('@/assets/homepage/bg1.jpg'),
        require('@/assets/homepage/bg2.jpg'),
        require('@/assets/homepage/bg3.jpg')
      ],
      currentBackground: '',
      searchKeyword: '',
      timer: null,       // 背景轮播定时器
    }
  },
  computed: {
    formattedTime() {
      const date = new Date()
      const hours = this.formatTime(date.getHours())
      const minutes = this.formatTime(date.getMinutes())
      const seconds = this.formatTime(date.getSeconds()) // 新增秒数处理
      return `${hours}:${minutes}:${seconds} ${date.getHours() >= 12 ? 'PM' : 'AM'}`
    },
    formattedDate() {
      const date = new Date()
      const year = date.getFullYear()
      const month = this.formatTime(date.getMonth() + 1)
      const day = this.formatTime(date.getDate())
      const week = ['日', '一', '二', '三', '四', '五', '六'][date.getDay()]
      return `${year}年 ${month}月 ${day}日 周${week}`
    },
    lunarDate() {
      try {
        const date = new Date()
        const lunarInfo = LunarCalendar.solar2lunar(
          date.getFullYear(),
          date.getMonth() + 1,
          date.getDate()
        )
        // 验证返回字段是否存在
        if (!lunarInfo || !lunarInfo.gzYear || !lunarInfo.monthCn || !lunarInfo.dayCn) {
          return '农历信息获取失败'
        }
        return `${lunarInfo.gzYear}年 ${lunarInfo.monthCn}${lunarInfo.dayCn}`
      } catch (error) {
        console.error('农历计算错误:', error)
        return '农历计算失败'
      }
    }
  },
  created() {
    this.initBackground()
    this.startBackgroundLoop()
  },
  beforeDestroy() {
    // 清除定时器，防止内存泄漏
    if (this.timer) clearInterval(this.timer)
  },
  methods: {
    formatTime(num) {
      return num < 10 ? `0${num}` : num
    },
    initBackground() {
      const randomIndex = Math.floor(Math.random() * this.backgroundList.length)
      this.currentBackground = this.backgroundList[randomIndex]
    },
    startBackgroundLoop() {
      // 10秒切换一次背景
      this.timer = setInterval(() => {
        this.initBackground()
      }, 10 * 60 * 1000)
    },
    handleSearch() {
      if (!this.searchKeyword.trim()) return
      console.log('搜索内容:', this.searchKeyword)
      // 建议使用encodeURIComponent处理搜索关键词
      // this.$router.push(`/search?keyword=${encodeURIComponent(this.searchKeyword)}`)
    },
    gotoAdminPage() {
      // 跳转前验证路由是否存在
      const routeExists = this.$router.options.routes.some(
        route => route.path === '/index'
      )
      if (routeExists) {
        this.$router.push('/index')
      } else {
        console.error('路由/index不存在，请检查路由配置')
        this.$message.warning('后台管理页面不存在') // 需引入Element UI的Message组件
      }
    }
  }
}
</script>

<style scoped>
.my-home-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  position: relative; /* 新增相对定位，方便子元素绝对定位 */
}

.background {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  transition: background-image 1s ease-in-out;
  position: relative;
  color: #fff;
}

/* 导航栏样式调整：靠右显示 */
.top-nav {
  position: absolute;
  top: 20px;
  right: 50px; /* 调整为右侧 */
  display: flex;
  justify-content: flex-end; /* 文字靠右 */
  width: 100%;
  z-index: 999; /* 确保导航栏在最上层 */
}

.top-nav ul {
  list-style: none;
  display: flex;
  gap: 20px;
  font-size: 16px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin: 0; /* 清除默认margin */
  padding: 0; /* 清除默认padding */
}

.admin-btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #409eff; /* 若依主题色 */
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

/* 时间显示区域调整：居中显示 */
.time-box {
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  width: 100%;
}

.current-time {
  font-size: 48px;
  margin-bottom: 10px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

.current-date {
  font-size: 20px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
  margin-bottom: 5px;
}

.current-lunar {
  font-size: 18px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

/* 搜索框调整：更靠上且更长 */
.search-box {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%);
  width: 600px; /* 更长的宽度 */
  max-width: 90%; /* 适配小屏幕 */
  display: flex;
  align-items: center;
}

.search-box input {
  width: 100%;
  padding: 15px 40px 15px 20px; /* 增大内边距 */
  border: none;
  border-radius: 30px;
  outline: none;
  font-size: 16px;
}

/* 修复scoped样式无法穿透到组件内部的问题 */
.search-box ::v-deep(.el-icon-search) {
  position: absolute;
  right: 20px;
  cursor: pointer;
  font-size: 20px; /* 增大图标 */
}

/* 响应式优化 */
@media (max-width: 768px) {
  .current-time {
    font-size: 32px;
  }
  .top-nav ul {
    font-size: 14px;
    gap: 10px;
  }
  .search-box {
    width: 80%;
  }
}
</style>
