import req from "@/api/req";

export const getResource = ()=>req("/",{method:"GET"})

export const login = data=>req("/user/login",{method:"POST",data})

export const isLogin = ()=>req.post("/user")

export const logout = ()=>req.post("/user/logout")