package com.gym1.service;


import com.gym1.entity.Speak;
import com.gym1.mapper.SpeakMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class SpeakService {

    @Autowired
    SpeakMapper speakMapper;

    public int sendSpeak(int id, String info){
        Date date = new Date(System.currentTimeMillis());
        Speak speak = new Speak(date, id, info);
        int res = 0;
        try{
            res = speakMapper.addSpeak(speak);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public List<Speak> getAllSpeak(int id){
        List<Speak> speaks = speakMapper.queryAllSpeak();
        for (Speak speak : speaks){
            System.out.println(speak.getUserId());
            if (speak.getUserId() == id){
                speak.setUserId(1);
            }else{
                speak.setUserId(0);
            }
        }
        return speaks;
    }

    public int deleteSpeak(int id){
        int res = 0;
        try{
            res = speakMapper.deleteSpeakById(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

}
