package com.gym1;
import com.gym1.entity.VenueType;
import com.gym1.service.VenueTypeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueTypeServiceTests {

    @Autowired
    private VenueTypeService venueTypeService;

    @Test
    public void getAllTest01(){
        List<String> venueTypes = venueTypeService.getAll();
        Assert.assertEquals("Table tennis venue", venueTypes.get(0));
    }

    @Test
    public void getAllTest02(){
        List<String> venueTypes = venueTypeService.getAll();
        Assert.assertEquals("Basketball venue", venueTypes.get(1));
    }

    @Test
    public void getAllTest03(){
        List<String> venueTypes = venueTypeService.getAll();
        Assert.assertEquals("Volleyball venue", venueTypes.get(2));
    }

    @Test
    public void getAllTest04(){
        List<String> venueTypes = venueTypeService.getAll();
        Assert.assertEquals("Badminton venue", venueTypes.get(3));
    }

    @Test
    public void getAllTest05(){
        List<String> venueTypes = venueTypeService.getAll();
        Assert.assertEquals("Tennis venue", venueTypes.get(4));
    }

}
