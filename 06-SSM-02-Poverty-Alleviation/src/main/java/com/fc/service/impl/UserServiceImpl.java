package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.DataVO;
import com.fc.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO getList(Integer pageNum, Integer pageSize, Long id) {
        // 返回给前端的结果
        ResultVO resultVO;

        // 分页相关的参数
        DataVO<User> userDataVO;

        // 结果中data对应的用户数组
        List<User> users;

        // 说明传递了id，那就是findById
        if (id != null) {
            users = new ArrayList<>();

            // 查询
            User user = userMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (user == null) {
                userDataVO = new DataVO<>(0L, users, pageNum, pageSize);

                resultVO = new ResultVO(4000, "查无此人!", false, userDataVO);
            } else {
                // 查到了用户扔到集合中
                users.add(user);

                userDataVO = new DataVO<>(1L, users, pageNum, pageSize);

                resultVO = new ResultVO(1000, "查到了该用户!", true, userDataVO);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            users = userMapper.selectByExample(null);

            // 如果数据库是空的，一个人都没查到
            if (users.size() == 0) {
                userDataVO = new DataVO<>(0L, users, pageNum, pageSize);

                resultVO = new ResultVO(4100, "没有用户!!!", false, userDataVO);
            // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<User> pageInfo = new PageInfo<>(users);

                userDataVO = new DataVO<>(pageInfo.getTotal(), users, pageNum, pageSize);

                resultVO = new ResultVO(1100, "用户查询成功！！！!", true, userDataVO);

            }

        }

        return resultVO;
    }

    @Override
    public ResultVO add(User user) {
        ResultVO vo;
        // 判断是否存在创建时间，没有就自己加上
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }

        int affectedRows = userMapper.insertSelective(user);

        if (affectedRows > 0) {
            vo = new ResultVO(1000, "添加用户成功！！", true, user);
        } else {
            vo = new ResultVO(5000, "添加用户失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVO update(User user) {
        int affectedRows = userMapper.updateByPrimaryKeySelective(user);

        ResultVO vo;

        if (affectedRows > 0) {
            // 修改完成之后，再重新查询一次，保证返回给前端的是最新最全的数据
            user = userMapper.selectByPrimaryKey(user.getId());

            vo = new ResultVO(1000, "修改用户成功！！", true, user);
        } else {
            vo = new ResultVO(5000, "修改用户失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVO delete(Long id) {
        int affectedRows = userMapper.deleteByPrimaryKey(id);

        ResultVO vo;

        if (affectedRows > 0) {
            vo = new ResultVO(1000, "删除用户成功！！", true, null);
        } else {
            vo = new ResultVO(5000, "删除用户失败！！", false, null);
        }

        return vo;
    }
}
