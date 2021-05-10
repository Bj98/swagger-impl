package com.example.swaggerimpl;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/test")
public class TestController {

    ConcurrentMap<Long,TestModel> testModels=new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public TestModel getTestModel(@PathVariable Long id){
        return testModels.get(id);
    }

    @GetMapping("/")
    public List<TestModel> getAllTestModels(){
        return new ArrayList<TestModel>(testModels.values());
    }

    @PostMapping("/")
    public TestModel addTestModel(@RequestBody TestModel testModel){
        testModels.put(testModel.getId(),testModel);
        return testModel;
    }
}
