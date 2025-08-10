<template>
  <!-- 仅包含主内容区域，无侧边栏和顶部导航 -->
  <div class="app-wrapper">
    <app-main />
  </div>
</template>

<script>
// 导入若依原有的主内容组件
import { AppMain } from './components'

export default {
  name: 'MyLayout',
  components: {
    AppMain  // 注册主内容组件
  }
}
</script>

<style scoped>
.app-wrapper {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>
