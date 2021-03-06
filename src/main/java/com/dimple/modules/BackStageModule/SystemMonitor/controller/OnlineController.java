package com.dimple.modules.BackStageModule.SystemMonitor.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.SystemMonitor.bean.UserOnline;
import com.dimple.modules.CommonModule.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : OnlineController
 * @description : 在线用户Controller
 * @date : 01/09/19 21:03
 */
@Controller
@Api("在线用户的Controller")
public class OnlineController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/page/online.html")
    @ApiIgnore
    public String onlineUserPage() {
        return "monitor/online";
    }

    @ApiOperation("在线用户列表")
    @Log(title = "作业管理", operateType = OperateType.SELECT)
    @GetMapping("/api/online")
    @ResponseBody
    public Result getOnlineList() {
        List<UserOnline> list = sessionService.getOnlineList();
        return ResultUtil.success(list);
    }

    @ApiOperation("踢出在线用户")
    @GetMapping("/api/online/{sessionId}")
    @Log(title = "作业管理", operateType = OperateType.DELETE)
    @ResponseBody
    public Result forceLogout(@PathVariable String sessionId[]) {
        sessionService.forceLogout(sessionId);
        return ResultUtil.success();
    }


}
