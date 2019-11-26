package com.qingchen.websocket.controller;

import com.qingchen.websocket.config.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Created by ChenJiaQiang on 2019/11/19 15:21
 */
@Controller
@RequestMapping("/websocket")
public class SendMsgController {

    @Autowired
    private WebSocket webSocket;

    //页面请求
    @GetMapping("/link")
    public ModelAndView socket(@RequestParam("id") String id) {
        System.out.println("into!");
        ModelAndView mav=new ModelAndView("webSocket");
        mav.addObject("id", id);
        return mav;
    }

    //推送数据接口（群发）
    @ResponseBody
    @GetMapping("/groupSending")
    public String pushToWeb(@RequestParam("message") String message) {
        try {
            webSocket.GroupSending(message);
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败:"+e.getMessage();
        }
        return "发送成功";
    }

    //推送数据接口（点对点）
    @ResponseBody
    @GetMapping("/sendingToOne")
    public String pushToOne(@RequestParam("id") String id,@RequestParam("message") String message){
        try {
            webSocket.AppointSending(id, message);
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
        return "发送成功";
    }

}
