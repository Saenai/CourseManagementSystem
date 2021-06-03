<template>
  <div>
    <router-view />
  </div>
</template>
<script></script>

<style lang="scss">
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
  background-color: #dcdfe6;
}

#app > div {
  height: 100%;
}
#app > div > div {
  height: 100%;
}

::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  background-color: #fff;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background-color: rgba(0, 0, 0, 0.1);
}
</style>
