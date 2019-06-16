package edu.pl.masi.yellow.controller.api.v1;

import edu.pl.masi.yellow.entity.MetricDataEntity;
import edu.pl.masi.yellow.manager.MetricManager;
import edu.pl.masi.yellow.model.LoginToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Metrics {
    private MetricManager metricManager;

    @RequestMapping(value = "/api/v1/metrics", method = RequestMethod.POST)
    public void metricSave(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @RequestBody MetricDataEntity entity,
                           HttpServletRequest request) {
        if (authToken != null)
            this.metricManager.saveMetric(entity, authToken.getUserName(), request);
    }

    public void setMetricManager(MetricManager metricManager) {
        this.metricManager = metricManager;
    }
}
