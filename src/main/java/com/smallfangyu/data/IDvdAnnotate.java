package com.smallfangyu.data;

import org.apache.ibatis.annotations.*;
import com.smallfangyu.data.DVD;
import java.util.List;

/**
 * @author smallfangyu.com
 */
public interface IDvdAnnotate {

    @Select("SELECT * FROM dvd,dvdrecy WHERE dvd.`show`=dvdrecy.`show`")
    /*
     * 查询
     * @return List<DVD>
     */
    public List<DVD> getDvd();

    @Delete("DELETE FROM dvd WHERE dvdno = #{dvdno}")
    public int deleteDvdById(int dvdno);

    @Insert("INSERT INTO dvd(dvdname,state,picture) VALUES(#{dvdname}, #{state}, #{picture}) ")
    public int insertDvd(DVD dvd);

    @Update("UPDATE dvd SET dvdname=#{dvdname},state = #{state},picture=#{picture} WHERE dvdno =#{dvdno}")
    public int updateDvd(DVD dvd);
}
