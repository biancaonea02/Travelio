package com.example.demo.dao;


import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class RoomDaoTests {

    @Mock
    RoomDao roomRepository;

    @Test
    public void getRoomByIdTest()
    {
        //arrange
        Long id = 1L;
        Room room = new Room(id, new Hotel());
        //act
        when(roomRepository.getById(1L)).thenReturn(room);
        //assert
        Assert.assertEquals(id, roomRepository.getById(1L).getId());

    }

    @Test
    public void getAllTest()
    {
        //arrange
        Room room1 = new Room(1L, new Hotel());
        Room room2 = new Room(2L, new Hotel());
        //act
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room1, room2));
        //assert
        Assert.assertEquals(Arrays.asList(room1, room2), roomRepository.findAll());
    }

    @Test
    public void saveRoomTest()
    {
        //arrange
        Room room = new Room(1L, new Hotel());
        //act
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        //assert
        Assert.assertEquals(room, roomRepository.save(room));
    }
}
