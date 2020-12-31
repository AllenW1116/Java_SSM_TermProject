<template>
  <div>
    <!-- 轮播图组件 -->
    <home-banner :banners="banners"></home-banner>
    <!-- 视频列表组件 -->
    <video-list :videoList="videoList"></video-list>
    <!-- 底部导航栏组件 -->
    <common-footer></common-footer>
  </div>
</template>


<script>
import HomeBanner from "./Component/Banner";
import VideoList from "./Component/VideoList";
import CommonFooter from "@/components/CommonFooter";
import { getBanner, getVideoList } from "@/api/getData.js"; //按需导入！

export default {
  //注册组件
  components: {
    HomeBanner,
    VideoList,
    CommonFooter
  },
  //声明数据源。实际上就是声明数据，但是固定写法写为函数的形式
  data() {
    return {
      banners: [],  //绑定到template中注册的HomeBanner组件上
      videoList: []  //绑定到template中注册的VideoList组件上
    };
  },
  //定义方法
  methods: {

     // 获取轮播图数据
    async getBannerData() {
      try {
        const result = await getBanner(); //async、await 异步方法声明。和c#是一样的！
        console.log(result);
        console.log(result.data.code == 0)  
        if (result.data.code == 0) {  //后端自定义的状态码，0为返回成功
          this.banners = result.data.data;  //this.banners是刚刚声明的数据
        }
      }catch(error){
          console.log(error)
      }
    },

    //获取视频列表
    async getVList(){  //这个函数名不要和导入的函数名相同！
        try{
            const result = await getVideoList();
            if (result.data.code == 0) {
                this.videoList = result.data.data;
            }
        }catch(error){
            console.log(error)
        }
    }
  },
  mounted(){
      //页面渲染的时候就调用方法获取数据！
      this.getBannerData();
      this.getVList()
  }
};
</script>

<style lang="scss" scoped></style>
