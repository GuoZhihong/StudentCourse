package io.guozhihong.system.controller;


import io.guozhihong.system.model.TableModel;
import io.guozhihong.system.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/system/tables")
@RestController
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/all")
    public List<TableModel> getTable(){
        return tableService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody//传回body到UI
    public String add(@RequestBody TableModel table){//requestBody JSON专用
        return tableService.add(table);
    }

    @DeleteMapping("/del")
    public String delete(@RequestParam("sid") Long sid, @RequestParam("cid") Long cid){//RequestParam通过URL传入需要的参数
            return tableService.delete(sid,cid);
    }

    @DeleteMapping("/del/all")
    public String deleteAll(){//"!非常危险"
        return tableService.deleteAll();
    }

    @PutMapping("/update")
    @ResponseBody
    public String update(@RequestBody TableModel tableModel){
        return tableService.update(tableModel);
    }

    @GetMapping("/invalidId")
    public String invalidId(){
        log.info("filter success");
        return  "Timetable Id does not exist";
    }
}
