package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.EntryEmailDTO;

import java.util.List;

public interface EmailService {
    public void setLocalMode(Boolean localMode);
    public void sendEmails(List<EntryEmailDTO> entryEmailDTOList);
    public void sendLogEmails(List<EntryEmailDTO> entryEmailDTOList);
    public void sendRealEmail(List<EntryEmailDTO> entryEmailDTOList);
}
