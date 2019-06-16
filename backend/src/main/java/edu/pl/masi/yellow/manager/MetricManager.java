package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.MetricDataEntity;
import edu.pl.masi.yellow.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricManager {
    private MetricRepository metricRepository;

    public void saveMetric(MetricDataEntity entity) {
        metricRepository.save(entity);
    }

    @Autowired
    public void setMetricRepository(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }
}
