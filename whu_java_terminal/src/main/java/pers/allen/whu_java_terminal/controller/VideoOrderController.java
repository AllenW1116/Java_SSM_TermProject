package pers.allen.whu_java_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.allen.whu_java_terminal.model.entity.VideoOrder;
import pers.allen.whu_java_terminal.model.request.VideoOrderRequest;
import pers.allen.whu_java_terminal.service.VideoOrderService;
import pers.allen.whu_java_terminal.utils.JsonData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;


    /**
     * 下单接口
     * @return
     */
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody(required=false) VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        //注意，这个地方的videoOrderRequesr中其实只要有video_id就可以了，因为拦截器会帮你从token里面拿到user_id

        Integer userId = (Integer) request.getAttribute("user_id");


        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());

        return rows == 0 ? JsonData.buildError("下单失败"):JsonData.buildSuccess();
    }


    /**
     * 订单列表
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);

    }

}
