<template>
  <div id="app">
    <Header v-if="!$route.meta.hiddenHeader"/>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Header from "./components/header/index.vue"
export default {
  name: 'App',
  components: {
    Header
  }
}
</script>

<style>
#app{
  height: 98vh;
  width: 100vw;
}
.content{
  width: 100%;
  height: 100%;
}
</style>
