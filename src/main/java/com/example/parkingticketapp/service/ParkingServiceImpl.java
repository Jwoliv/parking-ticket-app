package com.example.parkingticketapp.service;

import com.example.parkingticketapp.enums.TypeAction;
import com.example.parkingticketapp.mapper.ActionResponseMapper;
import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.mapper.TicketMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.repository.interfaces.ParkingRepository;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.enums.CrudAction;
import com.example.parkingticketapp.shared.request.ActivityParkingRequest;
import com.example.parkingticketapp.shared.response.ActionResponse;
import com.example.parkingticketapp.shared.response.UseParkingActivityResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ParkingServiceImpl implements ParkingService {
    @Setter(onMethod = @__(@Autowired))
    private ParkingRepository parkingRepository;
    @Setter(onMethod = @__(@Autowired))
    private ParkingMapper parkingMapper;
    @Setter(onMethod = @__(@Autowired))
    private TicketMapper ticketMapper;
    @Setter(onMethod = @__(@Autowired))
    private ActionResponseMapper actionResponseMapper;

    @Override
    public Parking findById(Long id) {
        return parkingRepository.findById(id).orElseThrow();
    }

    @Override
    public ParkingDto generateInfoAboutParking(Long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow();
        return parkingMapper.entityParkingToDto(parking);
    }

    @Override
    public ActionResponse<ParkingDto> saveNewParking(ParkingDto parkingDto) {
        Parking parking = parkingMapper.dtoParkingToEntity(parkingDto);
        Parking savedParking = parkingRepository.save(parking).orElseThrow();
        ParkingDto newParkingDto =  parkingMapper.entityParkingToDto(savedParking);
        return actionResponseMapper.toResponse(newParkingDto, CrudAction.CREATE);
    }

    @Override
    public ActionResponse<ParkingDto> deleteById(Long id) {
        Parking parking = parkingRepository.deleteById(id);
        ParkingDto parkingDto = parkingMapper.entityParkingToDto(parking);
        return actionResponseMapper.toResponse(parkingDto, CrudAction.DELETE);
    }

    @Override
    public ActionResponse<ParkingDto> updateExistedParking(ParkingDto parkingDto) {
        Parking parking = parkingMapper.dtoParkingToEntity(parkingDto);
        Parking updateParking = parkingRepository.updateById(parking.getId(), parking);
        ParkingDto newParkingDto = parkingMapper.entityParkingToDto(updateParking);
        return actionResponseMapper.toResponse(newParkingDto, CrudAction.UPDATE);
    }

    @Override
    public void updateAvailableParkingSpaces(Long id, Long availableParkingSpaces) {
        parkingRepository.updateAvailableParkingSpaces(id, availableParkingSpaces);
    }

    @Override
    public Boolean checkAvailableSeats(Parking parking) {
        return parking.getAvailableParkingSpaces() > 0;
    }

    @Override
    public UseParkingActivityResponse checkInToParking(ActivityParkingRequest request, Ticket ticket) {
        Parking parking = parkingRepository.findById(request.getParkingId()).orElseThrow();
        if (validateBeforeParkingActivity(ticket, parking)) {
            return UseParkingActivityResponse.builder()
                    .ticket(ticketMapper.entityTicketToDto(ticket))
                    .parking(parkingMapper.entityParkingToDto(parking))
                    .typeAction(TypeAction.CHECK_IN)
                    .activeTime(LocalDateTime.now())
                    .build();
        }
        return null;
    }

    @Override
    public UseParkingActivityResponse checkOutToParking(ActivityParkingRequest request, Ticket ticket) {
        Parking parking = parkingRepository.findById(request.getParkingId()).orElseThrow();
        if (validateBeforeParkingActivity(ticket, parking)) {
            return UseParkingActivityResponse.builder()
                    .ticket(ticketMapper.entityTicketToDto(ticket))
                    .parking(parkingMapper.entityParkingToDto(parking))
                    .typeAction(TypeAction.CHECK_OUT)
                    .activeTime(LocalDateTime.now())
                    .build();
        }
        return null;
    }

    private Boolean validateBeforeParkingActivity(Ticket ticket, Parking parking) {
        return isTimeValid(ticket) && isParkingTicketValid(ticket.getKey(), parking);
    }

    private Boolean isTimeValid(Ticket ticket) {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isAfter(ticket.getStartTime()) && currentTime.isBefore(ticket.getEndTime());
    }

    private Boolean isParkingTicketValid(String ticketKey, Parking parking) {
        return parking.getTickets().stream()
                .map(Ticket::getKey)
                .toList()
                .contains(ticketKey);
    }

}
