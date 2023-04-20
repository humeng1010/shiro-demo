import {createRouter,createWebHashHistory} from "vue-router"
import HomePage from "@/pages/HomePage";
import LoginPage from "@/pages/LoginPage";
import {isLogin} from "@/api";

const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {
            path:"/",
            redirect:"/home",
            meta:{
                needLogin: true
            }
        },
        {
            path:"/home",
            component: HomePage,
            meta:{
                needLogin: true
            }
        },
        {
            path:"/login",
            component: LoginPage,
            meta:{
                needLogin: false
            }
        }
    ]

})
router.beforeEach((to,from,next)=>{

    if (to.meta.needLogin){
        isLogin().then(res=>{
            console.log(res.data)
            if (res.data.success){
                console.log("已经登陆")
                next()
            }else {
                console.log("未登录")
                router.push("/login")
            }
        })
    }else {
        //不需要登陆直接放行
        next()
    }
})

export default router