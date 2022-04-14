package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IHotelService;
import com.example.demo.serviceInterfaces.IRoomService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {RoomController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(RoomController.class)
public class RoomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IRoomService roomService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getRoomByIdTest() throws Exception {
        //arrange
        Room room = new Room();
        room.setId(1L);
        Mockito.when(roomService.getRoomById(room.getId())).thenReturn(room);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/room/{id}",room.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllRoomsTest() throws Exception {
        //arrange
        Room room1 = new Room(1L, new Hotel());
        Room room2 = new Room(2L, new Hotel());
        List<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2));
        Mockito.when(roomService.getAllRooms()).thenReturn(rooms);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/room")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getRoomsOfHotel() throws Exception {
        //arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Room room1 = new Room(1L, hotel);
        Room room2 = new Room(2L, hotel);
        List<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2));
        when(roomService.getRoomsOfHotel(hotel.getId())).thenReturn(rooms);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/room/getHotelRooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idHotel", hotel.getId().toString())
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

}
