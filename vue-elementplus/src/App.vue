<template>
  <div>
    <router-view />
  </div>
</template>
<script></script>

<style>
body {
  margin: 0;
  height: 100%;
}

.el-container {
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  position: fixed;
  background-color: #f5f5f5;
}

#app > div {
  height: 100%;
}
#app > div > div {
  height: 100%;
}
</style>
