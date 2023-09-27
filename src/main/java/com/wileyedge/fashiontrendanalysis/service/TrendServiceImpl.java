package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.TrendDao;
import com.wileyedge.fashiontrendanalysis.model.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrendServiceImpl implements TrendService {

    private final TrendDao trendDao;

    @Autowired
    public TrendServiceImpl(TrendDao trendDao) {
        this.trendDao = trendDao;
    }


    @Override
    public List<Trend> getAllTrends() {
        return trendDao.getAllTrends();
    }

    @Override
    public Trend getTrendById(Long trendId) {
        return trendDao.getTrendById(trendId);
    }

    @Override
    public Long addTrend(Trend trend) {
        return trendDao.addTrend(trend);
    }

    @Override
    public boolean updateTrend(Long trendId, Trend trend) {
        return trendDao.updateTrend(trendId, trend);
    }

    @Override
    public boolean deleteTrend(Long trendId) {
        return trendDao.deleteTrend(trendId);
    }

    @Override
    public List<Trend> getTrendsByCategory(Long categoryId) {
        return trendDao.getTrendsByCategory(categoryId);
    }

    @Override
    public List<Trend> getTrendsByDesigner(Long designerId) {
        return trendDao.getTrendsByDesigner(designerId);
    }

    @Override
    public List<Trend> getTrendsByLocation(String location) {
        return trendDao.getTrendsByLocation(location);
    }

    @Override
    public List<Trend> getTrendsBySeason(String season) {
        return trendDao.getTrendsBySeason(season);
    }


    @Override
    public boolean associateTrendWithCategory(Long trendId, Long categoryId) {
        return trendDao.associateTrendWithCategory(trendId, categoryId);
    }

    @Override
    public boolean dissociateTrendFromCategory(Long trendId, Long categoryId) {
        return trendDao.dissociateTrendFromCategory(trendId, categoryId);
    }

    @Override
    @Transactional
    public boolean setTrendPopularity(Long trendId, int score) {
        return trendDao.setTrendPopularity(trendId, score);
    }

    @Override
    public int getTrendPopularity(Long trendId) {
        return trendDao.getTrendPopularity(trendId);
    }


}

