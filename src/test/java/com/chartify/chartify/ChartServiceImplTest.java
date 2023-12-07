import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.impl.ChartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChartServiceImplTest {

    @Autowired
    private ChartServiceImpl chartService;

    @BeforeEach
    void setUp() {
        chartService = new ChartServiceImpl();
    }

    @Test
    void processChartDataWithValidDataShouldReturnBase64Image() {
        // 创建模拟的有效ChartData对象
        ChartData validChartData = new ChartData();
        validChartData.setX(Map.of("1", 2, "2", 3,"1000",10));
        validChartData.setY(Map.of("1", 3, "2", 4,"5000",10000));

        // 调用generateChart方法而不是processChartData
        Result<String> result = chartService.generateChart(validChartData);

        // 验证结果是否成功，并且data包含Base64字符串
        assertTrue(result.getIsSuccess());
        assertNotNull(result.getData());
        // 假设Base64字符串以"data:image/png;base64,"开始
        System.out.println(result.getData());
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+/]+={0,2}$");
        Matcher matcher = pattern.matcher(result.getData());
        assertTrue(matcher.find());
    }

    @Test
    void processChartDataWithInvalidDataShouldThrowException() {
        // 创建模拟的无效ChartData对象
        ChartData invalidChartData = new ChartData();
        invalidChartData.setX(Map.of("1", 2)); // X轴只有一个数据点
        invalidChartData.setY(Map.of()); // Y轴没有数据点

        // 执行generateChart方法并验证是否抛出了IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            chartService.generateChart(invalidChartData);
        });

        // 验证异常消息内容
        String expectedMessage = "X轴或Y轴数据不能为空";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}