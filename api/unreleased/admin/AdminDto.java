package com.gersimuca.erp.unreleased.admin;

import com.gersimuca.erp.common.annotation.DataTransferObject;
import java.util.Date;

@DataTransferObject
public record AdminDto(
    Long id,
    boolean removed,
    boolean enabled,
    String email,
    String name,
    String surname,
    String photo,
    Date created,
    AdminRole role) {}
