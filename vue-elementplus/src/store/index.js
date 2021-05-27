import { createStore } from "vuex";

export default createStore({
  state: {
    Authorization: sessionStorage.getItem("Authorization")
      ? sessionStorage.getItem("Authorization")
      : "",
    id: sessionStorage.getItem("id") ? sessionStorage.getItem("id") : "",
    name: sessionStorage.getItem("name") ? sessionStorage.getItem("name") : "",
    role: sessionStorage.getItem("role") ? sessionStorage.getItem("role") : "",
  },
  getters: {},
  mutations: {
    // 修改token，并将token存入sessionStorage
    changeLogin(state, user) {
      state.Authorization = user.Authorization;
      state.id = user.id;
      state.name = user.name;
      state.role = user.role;
      sessionStorage.setItem("Authorization", user.Authorization);
      sessionStorage.setItem("id", user.id);
      sessionStorage.setItem("name", user.name);
      sessionStorage.setItem("role", user.role);
    },
    clearLogin() {
      sessionStorage.clear();
      state.clear();
    },
  },
  actions: {},
  modules: {},
});
