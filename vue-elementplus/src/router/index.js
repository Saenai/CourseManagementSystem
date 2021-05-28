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
  {
    path: "/error",
    name: "ErrorPage",
    component: () => import("v/Error.vue"),
  },
  {
    path: "/login",
    name: "LoginPage",
    component: () => import("v/Login.vue"),
  },
  // {
  //   path: "/Admin",
  //   name: "AdminIndex",
  //   component: () => import("v/Admin/index.vue"),
  //   children: [
  //     {
  //       path: "/admin/AdminsManagement",
  //       name: "AdminsManagement",
  //       component: () => import("v/Admin/AdminsManagement.vue"),
  //     },
  //   ],
  // },
  // {
  //   path: "/student",
  //   name: "StudentIndex",
  //   component: () => import("v/Student/index.vue"),
  //   children: [
  //     {
  //       // path: "/admin/AdminsManagement",
  //       // name: "AdminsManagement",
  //       // component: () => import("v/admin/AdminsManagement.vue"),
  //     },
  //   ],
  // },
  // {
  //   path: "/teacher",
  //   name: "TeacherIndex",
  //   component: () => import("v/Teacher/index.vue"),
  //   // children: [
  //   //   {
  //   //     // path: "/admin/AdminsManagement",
  //   //     // name: "AdminsManagement",
  //   //     // component: () => import("v/admin/AdminsManagement.vue"),
  //   //   },
  //   // ],
  // },
];

// 以下のMethodは「children」が使用できないので、

// 以下のMethodを実施する場合、
// ../views所属vueファイルをImportし、
// パス別対応URLをルートする。
// function importViews(r) {
//   r.keys().forEach((key) => {
//     routes.push({ path: key.split(".")[1], component: () => r(key) });
//   });
// }
// importViews(require.context("../views", true, /\.vue$/, "lazy"));

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

// 以下のMethodは画面を示す前に、ユーザーを検証し、使用できるかを確認する
router.beforeEach((to, from, next) => {
  if (to.path === "/error") {
    next();
  } else if (to.path === "/login") {
    // first go login
    next();
  }
  // cant use system without login or token
  else if (
    sessionStorage.getItem("Authorization") === "null" ||
    sessionStorage.getItem("Authorization") === ""
  ) {
    next("/login");
  }
  // cant get page beyond role
  else if (
    (to.path === "/admin" && sessionStorage.getItem("role") === "admin") ||
    (to.path === "/teacher" && sessionStorage.getItem("role") === "teacher") ||
    (to.path === "/student" && sessionStorage.getItem("role") === "student")
  ) {
    next();
  }
  //E0001: Wrong Role to access current page.
  else {
    sessionStorage.setItem("error_code", "E0001");
    next("/Error");
  }
});

export default router;
