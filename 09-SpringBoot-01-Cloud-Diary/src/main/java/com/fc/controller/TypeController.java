package com.fc.controller;

import com.fc.entity.TbNoteType;
import com.fc.entity.TbUser;
import com.fc.service.TypeService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("list")
    public ModelAndView list(ModelAndView mv, HttpSession session) {
        // 根据用户的id获取该用户的所有日记类别
        TbUser user = (TbUser) session.getAttribute("user");

        List<TbNoteType> types = typeService.getTypes(user.getId());

        mv.addObject("list", types);
        mv.addObject("menu_page", "type");
        mv.addObject("changePage", "/type/list.jsp");

        mv.setViewName("forward:/index.jsp");

        return mv;
    }

    @PostMapping("addOrUpdate")
    @ResponseBody
    public ResultVO addOrUpdate(TbNoteType type, HttpSession session) {
        TbUser user = (TbUser) session.getAttribute("user");

        type.setUserId(user.getId());

        ResultVO vo;

        // 如果没有传递类型的id那么执行的是添加
        if (type.getId() == null) {
            vo = typeService.add(type);
        } else {
            vo = typeService.update(type);
        }

        return vo;
    }

    @GetMapping("delete")
    @ResponseBody
    public ResultVO delete(@RequestParam Integer id) {
        ResultVO vo = new ResultVO();
        vo.setCode(0);
        vo.setSuccess(false);

        int affectedRows = typeService.delete(id);

        if (affectedRows == -1) {
            vo.setMessage("当前类别下还有日记，不要删");
        } else if (affectedRows == 0) {
            vo.setMessage("删除失败了~");
        } else {
            vo.setMessage("删除成功！");
            vo.setCode(1);
            vo.setSuccess(true);
        }

        return vo;
    }
}
