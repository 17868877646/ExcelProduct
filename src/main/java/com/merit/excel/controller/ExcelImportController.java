package com.merit.excel.controller;

import cn.hutool.core.util.ObjectUtil;
import com.merit.excel.entity.TestTable;
import com.merit.excel.service.TestTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class ExcelImportController {

    @Autowired
    private TestTableService testTableService;
    @PostMapping("/import")
    public String importData(@RequestParam("file") MultipartFile file) throws Exception {
        try {
           return testTableService.importData(file);
        } catch (Exception e) {
            log.error("导入失败：{}",e.getMessage(),e);
            return "导入失败";
        }
    }
}


