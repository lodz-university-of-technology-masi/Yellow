package edu.pl.masi.yellow.controller.api.v1;

import edu.pl.masi.yellow.entity.MetricDataEntity;
import edu.pl.masi.yellow.manager.MetricManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Metrics {
    private MetricManager metricManager;

    @RequestMapping(value = "/api/v1/metrics", method = RequestMethod.POST)
    public void metricSave(@RequestBody MetricDataEntity entity) {
        this.metricManager.saveMetric(entity);
    }

    public void setMetricManager(MetricManager metricManager) {
        this.metricManager = metricManager;
    }
}
