package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;

public interface TrackingLocationRest {

    @Operation(summary = "Fetch Tracking Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch Tracking Location",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseTrackingLocationDto[].class))})
    })
    List<ResponseTrackingLocationDto> findAll();

    @Operation(summary = "Save Tracking Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save Tracking Location"),
            @ApiResponse(responseCode = "400", description = "Input Validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseValidationError.class)))})
    void save(@Valid RequestTrackingLocationDto dto);
}
