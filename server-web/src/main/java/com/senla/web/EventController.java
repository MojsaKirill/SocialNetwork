package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.EventService;
import com.senla.beans.CustomUser;
import com.senla.beans.Event;
import com.senla.beans.User;
import com.senla.data.WebConstants;
import com.senla.web.dto.EventDto;
import com.senla.web.dto.SimpleDto;
import com.senla.web.dto.UserDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/events", produces = WebConstants.APPLICATION_JSON)
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = Logger.getLogger(EventController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<EventDto> getEvents(@CurrentUser CustomUser customUser) {
        try {
            List<Event> events = eventService.getEventsByUserId(customUser.getId());
            List<EventDto> eventsDTO = new ArrayList<>();
            for (Event event : events) {
                eventsDTO.add(modelMapper.map(event, EventDto.class));
            }
            return eventsDTO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean addEvent(@RequestBody Event event, @CurrentUser CustomUser customUser) {
        try {
            eventService.createEvent(event, customUser.getCurrentUser());
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/subscribe/{id}", method = RequestMethod.POST)
    public Boolean subscribeToEvent(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return eventService.subscribeToEvent(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/unsubscribe/{id}", method = RequestMethod.POST)
    private Boolean unsubscribeToEvent(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return eventService.unsubscribeToEvent(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/subscribers/{id}", method = RequestMethod.GET)
    public List<UserDto> getSubscribers(@PathVariable(value = "id") long id) {
        try {
            List<User> subscribers = eventService.getEventSubscribers(id);
            List<UserDto> usersDto = new ArrayList<>();
            for (User user : subscribers) {
                usersDto.add(modelMapper.map(user, UserDto.class));
            }
            return usersDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventDto getEvent(@PathVariable(value = "id") long id) {
        try {
            EventDto eventDto = modelMapper.map(eventService.getEventById(id), EventDto.class);
            return eventDto;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editEvent(@RequestBody Event event, @CurrentUser CustomUser customUser) {
        try {
            return eventService.editEvent(event, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteEvent(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return eventService.deleteEvent(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<EventDto> findEvent(@RequestBody SimpleDto<String> name) {
        try {
            List<Event> events = eventService.findEvent(name.getData());
            List<EventDto> eventsDto = new ArrayList<>();
            for (Event event : events) {
                eventsDto.add(modelMapper.map(event, EventDto.class));
            }
            return eventsDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
