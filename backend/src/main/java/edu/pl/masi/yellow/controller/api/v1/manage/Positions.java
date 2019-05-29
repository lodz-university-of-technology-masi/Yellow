package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.PositionEntity;
import edu.pl.masi.yellow.manager.PositionManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Positions {
    private PositionManager positionManager;
    private UserManager userManager;

    @RequestMapping(value = "/api/v1/manage/positions", method = RequestMethod.GET)
    public List<PositionEntity> getAllPositions() {
        return this.positionManager.getAllPositions();
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setPositionManager(PositionManager positionManager) {
        this.positionManager = positionManager;
    }
}
