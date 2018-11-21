package com.smallfangyu.data;

import java.util.List;

public interface IDvd {
    public List<DVD> getDvdList();

    public void insertDvd(DVD dvd);

    public void updateDvd(DVD dvd);

    public void deleteDvd(int id);

    public DVD  getDvd(int id);
}
