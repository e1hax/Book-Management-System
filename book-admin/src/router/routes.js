
export const constantRoutes = [{
    path: "/login",
    // name: "login",
    component: () => import('@/components/login'),
    meta: {
        hiddenHeader: true
    }
},
{
    path: "/register",
    // name: "register",
    component: () => import('@/components/register'),
    meta: {
        hiddenHeader: true
    }
},
{
    path: "/book",
    // name: "book",
    title:"图书管理",
    component: () => import('@/pages/Book')
},
{
    path: "/audience",
    // name: "audience",
    title:"读者管理",
    component: () => import('@/pages/Audience')
},
{
    path: "/borrowed",
    // name: "borrowed",
    title:"借还管理",
    component: () => import('@/pages/Borrowed')
},
{
    path: "/bookall",
    // name: "bookall",
    title:"所有图书",
    component: () => import('@/pages/Aookall')
},
{
    path: "/pipeline",
    // name: "pipeline",
    title:"用户流水",
    component: () => import('@/pages/Pipeline')
}
]
export const adminRoutes = [
    {
        path: "/book",
        // name: "book",
        title:"图书管理",
        component: () => import('@/pages/Book')
    },
    {
        path: "/audience",
        // name: "audience",
        title:"读者管理",
        component: () => import('@/pages/Audience')
    },
    {
        path: "/borrowed",
        // name: "borrowed",
        title:"借还管理",
        component: () => import('@/pages/Borrowed')
    }
]
export const userRoutes = [
    {
        path: "/bookall",
        // name: "bookall",
        title:"所有图书",
        component: () => import('@/pages/Aookall')
    },
    {
        path: "/pipeline",
        // name: "pipeline",
        title:"用户流水",
        component: () => import('@/pages/Pipeline')
    }

]
