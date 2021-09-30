package com.go.game.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping
public class PageController {

    private final ObjectMapper mapper;

    @Autowired
    public PageController(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @GetMapping
    public String index(Model model){
        /* ========== Maps ========== */
        model.addAttribute("maps",this.readData("maps.json"));
        /* ========== Obstruction ========== */
        model.addAttribute("obstruction",this.readData("obstruction.json"));
        /* ========== Character ========== */
        model.addAttribute("character",this.readData("character.json"));
        /* ========== monster ========== */
        model.addAttribute("monster",this.readData("monster.json"));
        /* ========== Items ========== */
        model.addAttribute("items",this.readData("items.json"));
        /* ========== Response ========== */
        return "/pages/index";
    }

    private Object readData(String path){
        try{
            /* ========== Open file ========== */
            File file = new File(getClass().getResource("/static/data/"+path).getFile());
            /* ========== Maps ========== */
            return this.mapper.readValue(file,Object.class);
        }catch(Exception ex){
            return null;
        }
    }

}
