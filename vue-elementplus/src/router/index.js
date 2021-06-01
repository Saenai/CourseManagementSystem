import { createRouter, createWebHashHistory } from "vue-router";
const routes = [
  {
    path: "/",
    redirect: "/login",
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
  {
    path: "/admin",
    name: "AdminIndex",
    component: () => import("v/Admin/index.vue"),
    children: [
      {
        path: "/admin/home",
        name: "AdminHome",
        component: () => import("v/Admin/Home.vue"),
      },
      {
        path: "/admin/adminsmanage",
        name: "AdminAdminsManage",
        component: () => import("v/Admin/AdminsManage.vue"),
      },
      {
        path: "/admin/studentsmanage",
        name: "AdminStudentsManage",
        component: () => import("v/Admin/StudentsManage.vue"),
      },
      {
        path: "/admin/teachersmanage",
        name: "AdminTeachersManage",
        component: () => import("v/Admin/TeachersManage.vue"),
      },
    ],
  },
  {
    path: "/student",
    name: "StudentIndex",
    component: () => import("v/Student/index.vue"),
    children: [
      {
        path: "/student/home",
        name: "StudentHome",
        component: () => import("v/Student/Home.vue"),
      },
      {
        path: "/student/courseinfo",
        name: "StudentCourseInfo",
        component: () => import("v/Student/CourseInfo.vue"),
      },
      {
        path: "/student/studentsmanage",
        name: "StudentFileDownload",
        component: () => import("v/Student/FileDownload.vue"),
      },
    ],
  },
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
  let isNotAuthenticated =
    sessionStorage.getItem("Authorization") === null ||
    sessionStorage.getItem("Authorization") === "" ||
    sessionStorage.getItem("Authorization") === "null";
  let err = sessionStorage.getItem("error_code");
  let role = sessionStorage.getItem("role");
  let dist = to.path;

  console.log(
    sessionStorage.getItem("Authorization"),
    sessionStorage.getItem("Authorization") === null,
    isNotAuthenticated,
    err,
    role,
    dist
  );

  if (dist === "/error") {
    if (err === null) {
      next("/login");
    } else next();
  } else if (!isNotAuthenticated) {
    if (dist === "/login") {
      next("/" + role);
    } else if (
      ((dist === "/admin" || dist.indexOf("/admin/") != -1) &&
        role === "admin") ||
      ((dist === "/teacher" || dist.indexOf("/teacher/") != -1) &&
        role === "teacher") ||
      ((dist === "/student" || dist.indexOf("/student/") != -1) &&
        role === "student")
    ) {
      next();
    } else {
      sessionStorage.setItem("error_code", "E02");
      next("/error");
    }
  } else if (isNotAuthenticated) {
    if (dist === "/login") {
      next();
    } else {
      next("/login");
    }
  }
  //E0001: Wrong Role to access current page.
  else {
    sessionStorage.setItem("error_code", "E01");
    next("/error");
  }
});
export default router;
