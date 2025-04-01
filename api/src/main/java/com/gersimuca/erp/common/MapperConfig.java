package com.gersimuca.erp.common;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MapperConfig {}
