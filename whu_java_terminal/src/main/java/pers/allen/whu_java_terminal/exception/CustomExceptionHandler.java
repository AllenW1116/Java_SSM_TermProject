package pers.allen.whu_java_terminal.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.allen.whu_java_terminal.utils.JsonData;


@ControllerAdvice //给Controller控制器添加统一的操作或处理
public class CustomExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody //返回Json数据
    public JsonData handle(Exception e){

        logger.error("[ 系统异常 ]{}",e.getMessage());

        if( e instanceof MYException ){

            MYException xdException = (MYException) e;

            return JsonData.buildError(xdException.getCode(),xdException.getMsg());

        }else {

            return JsonData.buildError("全局异常，未知错误");

        }


    }
}
