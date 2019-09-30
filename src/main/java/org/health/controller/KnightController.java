package org.health.controller;

import org.health.servive.KnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/knight")
public class KnightController {
    private final KnightService knightService;

    @Autowired
    public KnightController(KnightService knightService) {
        this.knightService = knightService;
    }

    @RequestMapping("/fight/{enemy}")
    public String getAchievement(@PathVariable("enemy") String enemy, Model model) {
        model.addAttribute("enemy", "We fight with " + enemy);
        model.addAttribute("result", knightService.getAchievement(enemy));
        return "fight";
    }
}
