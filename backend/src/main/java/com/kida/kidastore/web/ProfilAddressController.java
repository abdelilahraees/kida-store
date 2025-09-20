package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Request.AddressDtoRequest;
import com.kida.kidastore.models.dto.Response.AddressDtoResponse;
import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import com.kida.kidastore.services.IProfilService;
import com.kida.kidastore.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile/addresses")
public class ProfilAddressController {


    private IProfilService profilService;

    public ProfilAddressController(IProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<AddressDtoResponse>> addAddress(@Valid @RequestBody AddressDtoRequest addressDto) {
        AddressDtoResponse addressDtoResponse = profilService.addAddress(addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(addressDtoResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> removeAddress(@PathVariable Long id) {
        profilService.removeAddress(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<AddressDtoResponse>>> getAllAddress() {
        List<AddressDtoResponse> address = profilService.getAllAddress();
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<AddressDtoResponse>> getAddressById(@PathVariable Long id) {
        AddressDtoResponse addressDtoResponse = profilService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(addressDtoResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<AddressDtoResponse>> updateAddress(@PathVariable Long id, @RequestBody AddressDtoRequest addressDto) {
        AddressDtoResponse addressDtoResponse = profilService.updateAddress(id, addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(addressDtoResponse));
    }
}
