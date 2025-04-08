package com.company.VirtuHub.Content_Management_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentApprovalRequestDto {


    private Long contentId;
    private boolean isApproved;
    private String rejectionReason;
}
