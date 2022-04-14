package com.example.demo.dal;

import com.example.demo.TestApplication;
import com.example.demo.dal.implementation.RoomDalJPA;
import com.example.demo.dal.implementation.UserDalJPA;
import com.example.demo.dao.RoomDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {RoomDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class RoomDalTests {

    @MockBean
    RoomDao roomRepository;

    @InjectMocks
    RoomDalJPA roomDal;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRoomByIdTest()
    {
        //arrange
        Optional<Room> room = Optional.of(new Room(1L, new Hotel()));
        //act
        when(roomRepository.findById(any(Long.class))).thenReturn(room);
        Room created = roomDal.getRoomById(room.get().getId());
        //assert
        Assert.assertEquals(Optional.of(created), room);
    }

    @Test
    public void getAllRoomsTest()
    {
        //arrange
        Room room1 = new Room(1L, new Hotel());
        Room room2 = new Room(2L, new Hotel());
        //act
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room1, room2));
        List<Room> allRooms = roomDal.getAllRooms();
        Assert.assertEquals(allRooms, Arrays.asList(room1, room2));
    }

    @Test
    public void addRoomToHotelTest()
    {
        //arrange
        Room room = new Room(1L, new Hotel());
        //act
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        Room created = roomDal.addRoomToHotel(room);
        //assert
        Assert.assertEquals(created, roomRepository.save(room));
    }
}
