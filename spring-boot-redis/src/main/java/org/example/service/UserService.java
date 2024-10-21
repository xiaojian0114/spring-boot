package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.User;
import org.example.vo.UserInfoVO;
import org.example.vo.UserLoginVO;

public interface UserService extends IService<User> {

    UserLoginVO loginByPhone(String phone  , String code);

    boolean checkUserEnabled(Long userId);

    UserInfoVO userInfo(Long userId);
}
