package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;

    @Override
    public ResultVO click(Long id, Date lastClickTime) {
        return null;
    }

    @Override
    public ResultVO getList(Integer pageNum, Integer pageSize, Long id) {
        // 返回给前端的结果
        ResultVO resultVO;

        // 分页相关的参数
        DataVO<PoorWithBLOBs> dataVO;

        // 结果中data对应的用户数组
        List<PoorWithBLOBs> poor;

        // 说明传递了id，那就是findById
        if (id != null) {
            poor = new ArrayList<>();

            // 查询
            PoorWithBLOBs poorWithBLOBs = poorMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (poorWithBLOBs == null) {
                dataVO = new DataVO<>(0L, poor, pageNum, pageSize);

                resultVO = new ResultVO(4000, "没有这个贫困户!", false, dataVO);
            } else {
                // 如果是查询单个，那么点击量应该加1
                click(poorWithBLOBs.getId(), null);

                // 更新点击的次数
                poorWithBLOBs.setClickNum(poorWithBLOBs.getClickNum() + 1);

                // 查到了用户扔到集合中
                poor.add(poorWithBLOBs);

                dataVO = new DataVO<>(1L, poor, pageNum, pageSize);

                resultVO = new ResultVO(1000, "查到了该贫困户!", true, dataVO);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            poor = poorMapper.selectByExampleWithBLOBs(null);

            // 如果数据库是空的，一个人都没查到
            if (poor.size() == 0) {
                dataVO = new DataVO<>(0L, poor, pageNum, pageSize);

                resultVO = new ResultVO(4100, "脱贫了，没穷人了!!!", false, dataVO);
                // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<PoorWithBLOBs> pageInfo = new PageInfo<>(poor);

                dataVO = new DataVO<>(pageInfo.getTotal(), poor, pageNum, pageSize);

                resultVO = new ResultVO(1100, "贫困户查询成功！！！!", true, dataVO);

            }
        }

        return resultVO;
    }

    @Override
    public ResultVO add(PoorWithBLOBs poor) {
        return null;
    }

    @Override
    public ResultVO update(PoorWithBLOBs poor) {
        return null;
    }

    @Override
    public ResultVO delete(Long id) {
        return null;
    }
}
