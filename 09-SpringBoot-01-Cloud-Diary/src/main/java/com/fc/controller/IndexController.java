package com.fc.controller;

import com.fc.entity.TbNote;
import com.fc.entity.TbUser;
import com.fc.service.IndexService;
import com.fc.vo.NoteVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("page")
    public ModelAndView page(Integer id, String title, String date,
            @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
            ModelAndView mv, HttpSession session) {
        TbUser user = (TbUser) session.getAttribute("user");

        // 分页查询用户对应的日记
        PageInfo<TbNote> pageInfo = indexService.page(pageNum, pageSize, user.getId(), id, title, date);
        mv.addObject("page", pageInfo);

        // 获取日期分类
        List<NoteVO> dateInfo = indexService.findCountByDate(user.getId());
        session.setAttribute("dateInfo", dateInfo);

        // 获取类别分类
        List<NoteVO> typeInfo = indexService.findCountByType(user.getId());
        session.setAttribute("typeInfo", typeInfo);

        if (id != null) {
            mv.addObject("typeId", id);
        }

        if (title != null && !title.equals("")) {
            mv.addObject("title", title);
        }

        if (date != null && !date.equals("")) {
            mv.addObject("date", date);
        }

        mv.addObject("menu_page", "index");
        mv.addObject("changePage", "/note/list.jsp");
        mv.setViewName("forward:/index.jsp");

        return mv;
    }

    @GetMapping("searchTitle")
    public ModelAndView searchTitle(String title, ModelAndView mv) {

        mv.addObject("title", title);

        mv.setViewName("forward:/index/page");
        return mv;
    }

    @GetMapping("searchDate")
    public ModelAndView searchDate(String date, ModelAndView mv) {

        mv.addObject("date", date);

        mv.setViewName("forward:/index/page");
        return mv;
    }

    @GetMapping("searchType")
    public ModelAndView searchType(Integer id, ModelAndView mv) {

        mv.addObject("id", id);

        mv.setViewName("forward:/index/page");
        return mv;
    }
}
