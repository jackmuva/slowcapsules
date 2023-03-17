package com.jackmu.slowcapsules.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryEmailDTO {
    private String entryText;
    private String subscriberEmail;
    private String title;

}
