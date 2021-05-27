import { createRouter, createWebHashHistory } from "vue-router";
const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/error",
  },
];

// 以下のMethodを実施する場合、
// ../views所属vueファイルをImportし、
// パス別対応URLをルートする。
function importViews(r) {
  r.keys().forEach((key) => {
    routes.push({ path: key.split(".")[1], component: () => r(key) });
    console.log({ key: key, com: () => r(key) });
  });
}
importViews(require.context("../views", true, /\.vue$/, "lazy"));

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.path === "/login") {
    next();
  } else {
    let token = sessionStorage.getItem("Authorization");

    if (token === "null" || token === "") {
      next("/login");
    } else {
      next();
    }
  }
});

export default router;
