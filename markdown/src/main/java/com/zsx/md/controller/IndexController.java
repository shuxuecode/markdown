package com.zsx.md.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsx.md.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class IndexController {

    private static String context = "";
    private static final String filePath = "D:/tmp/1.md";

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", UUID.randomUUID().toString());
        return "index";
    }

    @GetMapping("/")
    public String index2(Model model) {
        model.addAttribute("name", UUID.randomUUID().toString());
        return "demo";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(String text) {
        System.out.println(text);
        context = text;
        FileUtil.writeFile(text, filePath);
        return "ok";
    }

    @GetMapping("/get")
    @ResponseBody
    public JSONObject get() {

        JSONObject json = new JSONObject();

        json.put("text", context);
        json.put("success", true);

        String text = FileUtil.readFile(filePath);
        json.put("text", text);

        return json;
    }
}
