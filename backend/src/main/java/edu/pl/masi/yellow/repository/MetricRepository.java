package edu.pl.masi.yellow.repository;

import edu.pl.masi.yellow.entity.MetricDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository  extends JpaRepository<MetricDataEntity, Integer> {
}
