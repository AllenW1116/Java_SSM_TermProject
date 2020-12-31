//配置后端路由
//同时需注意，没有export default，所以在其他vue组件中我们需要按需导入（有大括号{}）
import axios from '../request'

//注册接口
export const registerApi = (phone, pwd , name)=> axios.post("/api/v1/pri/user/register",{ //axios.http方法，有两个参数：第一个是String url
    //第二个是传入的请求参数。传入的请求参数有三种情况：一种是实体类，用json封装，如本例
    "phone":phone,
    "pwd":pwd,
    "name":name
})

//登录接口
export const loginApi = (phone, pwd) => axios.post("/api/v1/pri/user/login",{
    phone,
    pwd
})


//轮播图接口
export const getBanner = () => axios.get("/api/v1/pub/video/list_banner")

//视频列表接口
export const getVideoList = ()=> axios.get("/api/v1/pub/video/list")


//视频详情
export const getVideoDetail = (vid)=> axios.get("/api/v1/pub/video/find_detail_by_id?",{
    params: {//传入请求参数第二种情况：普通类型，用params来接收
        video_id:vid
    }
})

//下单接口
export const saveOrder = (token, vid)=>axios.post("/api/v1/pri/order/save",{
    "video_id":vid
},{
    headers:{ //传入请求参数第三种情况：隐藏在请求头中的信息，如header中的token
        "token":token
    }
})

//订单列表
export const getOrderList = (token)=>axios.get("/api/v1/pri/order/list",{
    params:{
        "token":token
    }
})

//用户信息接口
export const getUserInfo = (token)=>axios.get("/api/v1/pri/user/find_by_token",{
    params:{
        "token":token
    }
})