package io.guozhihong.demo.controller;

import io.guozhihong.demo.model.TableModel;
import io.guozhihong.demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public List<TableModel> getTable(){
        return tableService.findAll();
    }

    @PostMapping("/tables/add")
    @ResponseBody//传回body到UI
    public String add(@RequestBody TableModel table){//requestBody JSON专用
        return tableService.add(table);
    }

    @DeleteMapping("/tables/del")
    public String delete(@RequestParam("sid") Long sid, @RequestParam("cid") Long cid){//RequestParam通过URL传入需要的参数
            return tableService.delete(sid,cid);
    }

    @DeleteMapping("/tables/del/all")
    public String deleteAll(){//"!非常危险"
        return tableService.deleteAll();
    }

    @PutMapping("table/update/student")
    public String updateStudent(@RequestParam("cid") Long cid, @RequestParam("sid") Long sid,@RequestParam("newsid") Long newSid){
        return tableService.updateStudent(sid,cid,newSid);
    }

    @PutMapping("table/update/course")
    public String updateCourse(@RequestParam("sid") Long sid, @RequestParam("cid") Long cid,@RequestParam("newcid") Long newCid){
        return tableService.updateCourse(sid,cid,newCid);
    }
}
