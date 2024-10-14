package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper , User> {
    public boolean createUser(User user) {
        return this.save(user);
    }
    // 更新⽤户，⾃动填充更新时间
    public boolean updateUser(User user) {
        return this.updateById(user);
    }
    // 逻辑删除⽤户
    public boolean deleteUserById(Long id) {
        // 使⽤逻辑删除
        return this.removeById(id);
    }
    // 根据ID查询⽤户
    public User getUserById(Long id) {
        // 查询单个⽤户
        return this.getById(id);
    }
    // 按名字模糊查询⽤户
    public List<User> getUsersByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 查询未逻辑删除的数据
        queryWrapper.like(User::getName, name).eq(User::getDeleted, 0);
        return this.list(queryWrapper);
    }
    public Page<User> getUsersByPage(int currentPage, int pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>()
                ;
        // 查询未逻辑删除的数据
        queryWrapper.eq(User::getDeleted, 0);
        return this.page(new Page<>(currentPage, pageSize), queryWrapper);
    }
}
