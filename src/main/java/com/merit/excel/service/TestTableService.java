package com.merit.excel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.merit.excel.entity.TestTable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestTableService extends IService<TestTable> {

    /**
     * 导入测试
     * @return
     */
    String importData(MultipartFile file) throws IOException;
}
