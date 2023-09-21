package com.merit.excel.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.merit.excel.entity.TestTable;
import com.merit.excel.mapper.TestTableMapper;
import com.merit.excel.service.TestTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TestTableServiceImpl extends ServiceImpl<TestTableMapper, TestTable> implements TestTableService {
    @Override
    public String importData(MultipartFile file) throws IOException {
        // 读取Excel模板文件
        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
        Row headerRow = sheet.getRow(3); // 获取表头行
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue()); // 将表头字段存储到列表中
        }
        // 遍历每一行数据，将其转换为Java对象并保存到数据库中
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i); // 获取当前行数据
            Map<String, String> data = new HashMap<>(); // 存储当前行数据的键值对
            if(ObjectUtil.isNotEmpty(row)) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j); // 获取当前单元格数据
                    data.put(headers.get(j), cell.toString()); // 将当前单元格数据存储到map中
                    log.info("打印：{}", cell.toString());
                }
            }
            // TODO: 将当前行数据转换为Java对象并保存到数据库中
            log.info("map,{}",data);
            TestTable testTable = new TestTable();
            testTable.setSubmitUser(data.get("提交人"));
            this.save(testTable);

        }
        return "导入成功";
    }
}
