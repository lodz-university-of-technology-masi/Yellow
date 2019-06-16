package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.MetricDataEntity;
import edu.pl.masi.yellow.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class MetricManager {
    private MetricRepository metricRepository;

    public void saveMetric(MetricDataEntity entity, String userName, HttpServletRequest request) {
        entity.setUserName(userName);

        entity.setIpAddress(request.getRemoteAddr());
        String userBrowser = request.getHeader("User-Agent");

        if (userBrowser.toLowerCase().contains("Chrome"))
            entity.setBrowser("C");
        else if (userBrowser.toLowerCase().contains("Firefox"))
            entity.setBrowser("F");
        else
            entity.setBrowser("I");

        entity.setTimestampSave((int) System.currentTimeMillis());

        metricRepository.save(entity);
    }

    @Autowired
    public void setMetricRepository(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }
}
