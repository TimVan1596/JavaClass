package com.smallfangyu.data;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import com.smallfangyu.data.DVD;
import java.util.List;

/**
 * @author smallfangyu.com
 */
public interface IDvdAnnotate {
    @Select("SELECT * FROM dvd WHERE dvdno= #{dvdno}")
    public DVD getDvdByID(int dvdno);

    @Delete("DELETE FROM dvd WHERE dvdno = #{dvdno}")
    public int deleteDvdById(int dvdno);

    @Insert("INSERT INTO dvd(dvdname,state,picture) VALUES(#{dvdname}, #{state}, #{picture}) ")
    public int insertDvd(DVD dvd);

    @Update("UPDATE dvd SET dvdname=#{dvdname},state = #{state},picture=#{picture} WHERE dvdno =#{dvdno}")
    public int updateDvd(DVD dvd);
}
