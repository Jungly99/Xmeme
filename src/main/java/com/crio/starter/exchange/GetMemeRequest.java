package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMemeRequest {
    
    @NotNull 
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String caption;
}