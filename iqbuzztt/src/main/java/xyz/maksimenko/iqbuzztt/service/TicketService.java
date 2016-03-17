package xyz.maksimenko.iqbuzztt.service;

import java.io.IOException;
import java.util.Set;

import xyz.maksimenko.iqbuzztt.Place;
import xyz.maksimenko.iqbuzztt.Ticket;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;

public interface TicketService {
	@JsonRpcErrors({
        @JsonRpcError(exception=NullPointerException.class,
            code=-1, message="Wrong lux seats"),
    })
	public Set<Ticket> bookTicket(long movieId, String userName, Place[] places);
}
