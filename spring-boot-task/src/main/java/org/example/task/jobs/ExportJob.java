package org.example.task.jobs;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.entity.Student;
import org.example.task.mapper.StudentMapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class ExportJob extends QuartzJobBean {

    private final StudentMapper studentMapper;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("开始执行");

        List<Student> students = studentMapper.selectList(null);

        String fileName = "/C:/Users/18361/Desktop/新建文件夹/" + UUID.randomUUID() + ".xlsx";

        EasyExcel.write(fileName , Student.class)
                .sheet("学生数据")
                .doWrite(() -> students);
    }
}
