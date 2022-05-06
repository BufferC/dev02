package com.fc.controller;

import com.fc.entity.TbNote;
import com.fc.entity.TbNoteType;
import com.fc.entity.TbUser;
import com.fc.service.NoteService;
import com.fc.service.TypeService;
import com.fc.vo.NoteVO;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TypeService typeService;

    @GetMapping("detail")
    public ModelAndView detail(@RequestParam Integer id, ModelAndView mv) {
        NoteVO note = noteService.findById(id);

        // 高亮显示
        mv.addObject("menu_page", "note");
        mv.addObject("note", note);
        // 包含的页面
        mv.addObject("changePage", "/note/detail.jsp");

        mv.setViewName("forward:/index.jsp");

        return mv;
    }

    @GetMapping("view")
    public ModelAndView view(Integer id, HttpSession session, ModelAndView mv) {
        // 判断id是否传递过来了

        // 就是从添加页面进去的
        if (id != null) {
            NoteVO note = noteService.findById(id);

            mv.addObject("noteInfo", note);
        }

        // id存在说明我们要根据id去做修改操作
        TbUser user = (TbUser) session.getAttribute("user");

        // 根据用户id获取该用户所有的日记分类
        List<TbNoteType> types = typeService.getTypes(user.getId());

        mv.addObject("typeList", types);
        mv.addObject("changePage", "/note/view.jsp");
        mv.addObject("menu_page", "note");

        mv.setViewName("forward:/index.jsp");

        return mv;
    }

    @PostMapping("addOrUpdate")
    public ModelAndView addOrUpdate(ModelAndView mv, TbNote note) {
        ResultVO vo;

        // 如果传递了日记的id，那么执行修改操作，否则就去添加
        if (note.getId() != null) {
            vo = noteService.update(note);
        } else {
            vo = noteService.add(note);
        }

        // 添加或者修改成功的情况
        if (vo.getCode() == 1) {
            mv.setViewName("redirect:/index/page");
        } else {
            mv.addObject("id", note.getId());
            mv.addObject("resultInfo", vo.getData());

            mv.setViewName("forward:view");
        }

        mv.addObject("menu_page", "note");

        return mv;
    }

    @GetMapping("delete")
    @ResponseBody
    public ResultVO delete(@RequestParam Integer id) {
        return noteService.delete(id);
    }
}
