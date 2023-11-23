package com.picpaySimplificado.picpaySimplificado.dtos;

import java.math.BigDecimal;

public record UserDTO(BigDecimal amount, Long senderId, Long reseiverId) {

}
