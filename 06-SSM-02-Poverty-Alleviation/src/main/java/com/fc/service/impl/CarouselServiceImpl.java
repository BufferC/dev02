package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import com.fc.vo.DataVO;
import com.fc.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public ResultVO getList(Integer pageNum, Integer pageSize, Integer id) {
        // 返回给前端的结果
        ResultVO resultVO;

        // 分页相关的参数
        DataVO<Carousel> carouselDataVO;

        // 结果中data对应的用户数组
        List<Carousel> carousels;

        // 说明传递了id，那就是findById
        if (id != null) {
            carousels = new ArrayList<>();

            // 查询
            Carousel carousel = carouselMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (carousel == null) {
                carouselDataVO = new DataVO<>(0L, carousels, pageNum, pageSize);

                resultVO = new ResultVO(4000, "查无此图!", false, carouselDataVO);
            } else {
                // 查到了用户扔到集合中
                carousels.add(carousel);

                carouselDataVO = new DataVO<>(1L, carousels, pageNum, pageSize);

                resultVO = new ResultVO(1000, "查到了该图!", true, carouselDataVO);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            carousels = carouselMapper.selectByExample(null);

            // 如果数据库是空的，一个人都没查到
            if (carousels.size() == 0) {
                carouselDataVO = new DataVO<>(0L, carousels, pageNum, pageSize);

                resultVO = new ResultVO(4100, "啥图都没!!!", false, carouselDataVO);
                // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<Carousel> pageInfo = new PageInfo<>(carousels);

                carouselDataVO = new DataVO<>(pageInfo.getTotal(), carousels, pageNum, pageSize);

                resultVO = new ResultVO(1100, "轮播图查询成功！！！!", true, carouselDataVO);

            }
        }

        return resultVO;
    }

    @Override
    public ResultVO add(Carousel carousel) {
        ResultVO vo;

        // 如果没有告知轮播图的状态，默认是不可用的
        if (carousel.getAvailable() == null) {
            carousel.setAvailable(false);
        }

        int affectedRows = carouselMapper.insertSelective(carousel);

        if (affectedRows > 0) {
            vo = new ResultVO(1000, "添加图片成功！！", true, carousel);
        } else {
            vo = new ResultVO(5000, "添加图片失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVO update(Carousel carousel) {
        int affectedRows = carouselMapper.updateByPrimaryKeySelective(carousel);

        ResultVO vo;

        if (affectedRows > 0) {
            // 修改完成之后，再重新查询一次，保证返回给前端的是最新最全的数据
            carousel = carouselMapper.selectByPrimaryKey(carousel.getId());

            vo = new ResultVO(1000, "修改图片成功！！", true, carousel);
        } else {
            vo = new ResultVO(5000, "修改图片失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVO delete(Integer id) {
        int affectedRows = carouselMapper.deleteByPrimaryKey(id);

        ResultVO vo;

        if (affectedRows > 0) {
            vo = new ResultVO(1000, "删除图片成功！！", true, null);
        } else {
            vo = new ResultVO(5000, "删除图片失败！！", false, null);
        }

        return vo;
    }
}
