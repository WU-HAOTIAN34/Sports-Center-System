package com.gym1.mapper;


import com.gym1.entity.Speak;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface SpeakMapper {

    int addSpeak(Speak speak);

    List<Speak> queryAllSpeak();

    int deleteSpeakById(int id);

}
