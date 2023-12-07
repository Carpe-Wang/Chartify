import com.chartify.chartify.entity.ChartData;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.impl.ChartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
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
    public void processChartDataWithValidDataShouldReturnBase64Image() {
        Map<Integer, Integer> chartDataMap = new HashMap<>();
        chartDataMap.put(1, 100);
        chartDataMap.put(2, 200);
        chartDataMap.put(500, 1000);
        ChartData chartData = new ChartData(chartDataMap, "CarpeWang's X Axis",
                "CarpeWang's Y Axis","CarpeWang's Test");

        Result<String> result = chartService.generateChart(chartData);
        assertTrue(result.getIsSuccess());
        System.out.println(result.getData());
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+/]+={0,2}$");
        Matcher matcher = pattern.matcher(result.getData());
        assertTrue(matcher.find());
    }
}