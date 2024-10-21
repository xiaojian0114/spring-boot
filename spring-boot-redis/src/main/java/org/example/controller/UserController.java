package org.example.controller;

//import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.servlet.http.HttpServletRequest;
//
//import lombok.AllArgsConstructor;
//
//import org.example.enums.ErrorCode;
//import org.example.exception.ServerException;
//import org.example.utils.JwtUtil;
//import org.example.vo.UserInfoVO;
//import org.springframework.web.bind.annotation.*;
//import org.example.result.Result;
//import org.example.service.UserService;
//import org.example.vo.UserLoginVO;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;



import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.cache.TokenStoreCache;
import org.example.enums.ErrorCode;
import org.example.exception.ServerException;
import org.example.utils.JwtUtil;
import org.example.vo.UserInfoVO;


import org.springframework.web.bind.annotation.*;
import org.example.result.Result;
import org.example.service.UserService;
import org.example.vo.UserLoginVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/user")


@AllArgsConstructor

public class UserController {
    private final UserService userService;

    //
    private final TokenStoreCache tokenStoreCache; // 添加成员变量


    @PostMapping("/login")
    @Operation(summary = "手机短信登录")

    public Result<UserLoginVO> loginByPhone(@RequestParam("phone")
                                            String phone, @RequestParam("code")
                                            String code) {
        return Result.ok(userService.loginByPhone(phone, code));
    }


    @GetMapping("info")
    @Operation(summary="查询用户信息")
    public Result<UserInfoVO> userInfo() {
        // 获得 HttpServletRequest 请求对象
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        // 调用工具方法，从 request 中获得 accessToken
        String accessToken = JwtUtil.getAccessToken(request);
        // accessToken 为空，抛出 UNAUTHORIZED 的异常信息
        if (StringUtils.isBlank(accessToken)){
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }
        // 校验 accessToken 是否有效，无效也是抛出 UNAUTHORIZED 异常信息
        if( !JwtUtil.validate(accessToken)){
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }// 根据 accessToken ，从 Redis 中查询到用户信息
         UserLoginVO user = tokenStoreCache.getUser(accessToken);
        // 没查到，抛出 LOGIN_STATUS_EXPIRE 异常信息
        if(ObjectUtils.isEmpty(user)) {
            throw new ServerException(ErrorCode.LOGIN_STATUS_EXPIRE);}
        // 验证用户是否可用
         boolean enabledFlag = userService.checkUserEnabled(user.getPkId());
        // 不可用，抛出 ACCOUNT_DISABLED 异常信息
        if(!enabledFlag){
            throw new ServerException(ErrorCode.ACCOUNT_DISABLED);
        }
        // 根据 id 查询到用户信息，返回给客户端
        return Result.ok(userService.userInfo(user.getPkId()));
    }
    }

