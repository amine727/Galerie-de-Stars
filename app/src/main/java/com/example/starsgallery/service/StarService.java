package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null)
            instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new Star("George Clooney", "https://...", 4));
        stars.add(new Star("Kate Bosworth", "https://...", 3));
        stars.add(new Star("Michelle Rodriguez", "https://...", 5));
    }

    public boolean create(Star o) {
        return stars.add(o);
    }

    public boolean update(Star o) {
        return true;
    }

    public boolean delete(Star o) {
        return stars.remove(o);
    }

    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public List<Star> findAll() {
        return stars;
    }
}