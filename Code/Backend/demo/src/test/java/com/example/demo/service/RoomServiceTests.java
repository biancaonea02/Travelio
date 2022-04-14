package com.example.demo.service;

import com.example.demo.dal.IReservationDal;
import com.example.demo.dal.IRoomDal;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {RoomService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class RoomServiceTests {

    @MockBean
    IRoomDal roomDal;

//    @MockBean
//    IReservationDal reservationDal;
//
//    @InjectMocks
//    RoomService roomService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getRoomByIdTest()
//    {
//        //arrange
//        Room room = new Room(1L, new Hotel());
//        //act
//        when(roomDal.getRoomById(any(Long.class))).thenReturn(room);
//        Room created = roomService.getRoomById(room.getId());
//        //assert
//        Assert.assertEquals(created, room);
//    }
//
//    @Test
//    @Disabled
//    public void getAllRoomsTest()
//    {
//        //arrange
//        Room room1 = new Room(1L, new Hotel());
//        Room room2 = new Room(2L, new Hotel());
//        //act
//        when(roomDal.getAllRooms()).thenReturn(Arrays.asList(room1, room2));
//        List<Room> allRooms = roomService.getAllRooms();
//        Assert.assertEquals(allRooms, Arrays.asList(room1, room2));
//    }
//
//    @Test
//    @Disabled
//    public void addRoomToHotelTest()
//    {
//        //arrange
//        Room room = new Room(1L, new Hotel());
//        //act
//        when(roomDal.addRoomToHotel(any(Room.class))).thenReturn(room);
//        Room created = roomService.addRoomToHotel(room);
//        //assert
//        Assert.assertEquals(created, roomService.addRoomToHotel(room));
//    }
//
//    @Test
//    @Disabled
//    public void getRoomsOfHotelTest()
//    {
//        //arrange
//        Hotel hotel = new Hotel();
//        hotel.setId(1L);
//        Room room1 = new Room(1L, hotel);
//        Room room2 = new Room(2L, hotel);
//        //act
//        when(roomDal.getRoomsOfHotel(any(Long.class))).thenReturn(Arrays.asList(room1, room2));
//        //assert
//        Assert.assertEquals(Arrays.asList(room1, room2), roomService.getRoomsOfHotel(hotel.getId()));
//    }
}
